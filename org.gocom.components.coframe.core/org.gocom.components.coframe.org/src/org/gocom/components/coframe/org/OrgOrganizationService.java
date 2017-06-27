/*
 * Copyright 2013 Primeton Technologies Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gocom.components.coframe.org;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.dataset.OrgOrganization;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.dataset.impl.OrgOrganizationImpl;
import org.gocom.components.coframe.org.util.OrgHelper;
import org.gocom.components.coframe.org.util.OrgResponse;
import org.gocom.components.coframe.rights.gradeauth.GradeAuthService;
import org.gocom.components.coframe.tools.IConstants;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.das.entity.criteria.ExprType;
import com.eos.das.entity.criteria.LogicType;
import com.eos.das.entity.criteria.OrderbyType;
import com.eos.das.entity.criteria.impl.ExprTypeImpl;
import com.eos.das.entity.criteria.impl.LogicTypeImpl;
import com.eos.foundation.PageCond;
import com.eos.spring.BeanFactory;
import com.primeton.cap.party.Party;
import commonj.sdo.DataObject;

import org.gocom.components.coframe.tools.CoframeDASDaoSupport;

/**
 * 组织机构服务类
 *
 *  @author yangzhou (mailto:yangzhou@primeton.com)
 */
public class OrgOrganizationService extends CoframeDASDaoSupport implements IOrgOrganizationService{
	
	public final static String SPRING_BEAN_NAME = "OrgOrganizationService";
	
	private IOrgPositionService positionService = null;
	
	private IOrgEmployeeService employeeService = null;
	
	/* (non-Javadoc)
	 * @see org.gocom.components.coframe.org.IOrgOrganizationService#addOrgOrganization(org.gocom.components.coframe.org.dataset.OrgOrganization)
	 */
	public OrgResponse addOrgOrganization(OrgOrganization org){
		if(!validateOrgcode(org)){
			return new OrgResponse(false, "机构代码:"+org.getOrgcode()+"已存在");
		}
		//设置主键
		getDASTemplate().getPrimaryKey(org);
		OrgOrganization parentOrg = org.getOrgOrganization();
		if(parentOrg != null && parentOrg.getOrgid() != null){
			getOrgOrganization(parentOrg);
		}
		OrgHelper.expandOrganizationPropertyByParent(org, parentOrg);
		getDASTemplate().insertEntity(org);
		//更新父机构
		if(parentOrg != null && parentOrg.getOrgid() != null){
			OrgHelper.expandParentOrganizationProperty(parentOrg);
			getDASTemplate().updateEntity(parentOrg);
		}
		return new OrgResponse(true, "添加成功");
	}
	
	/**
	 * 检验机构code是否合格
	 * @param orgcode
	 * @return true合格，false不合格
	 */
	public boolean validateOrgcode(OrgOrganization org) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgOrganization.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgcode", org.getOrgcode()));
		OrgOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgOrganization.class, dasCriteria);
		if(orgs == null || orgs.length == 0){
			return true;
		}
		if(org.getOrgid() != null && orgs[0].getOrgid().equals(org.getOrgid())){//修改的情况，只能为1个
			return orgs.length == 1;
		}else{//新增,必定没有
			return orgs.length == 0;
		}
	}

	public void deleteOrgOrganization(OrgOrganization[] orgs){
		if(orgs == null) return;
		for(OrgOrganization org : orgs){
			//删除机构和人员的关联关系
			if(employeeService != null){
				employeeService.deleteEmpAndOrgRelationship(org);
			}
			//删除子机构，子岗位
			deleteSubOrgByParent(org);
			getDASTemplate().deleteEntity(org);
		}
	}
	
	/**
	 * 根据父机构删除子机构
	 * 删除子机构规则：
	 * 	   1.删除子岗位，删除子岗位之前必须先删除子岗位关联的人员关系
	 * 	   2.删除子机构：删除子机构之前，先级联删除其对应的子机构相关内容
	 * @param parentOrg 父机构
	 */
	private void deleteSubOrgByParent(OrgOrganization parentOrg){
		//删除子机构岗位，删除子岗位之前先删除子岗位关联的人员关系
		if(positionService != null){
			positionService.deleteOrgPositionCascadeByParentOrg(parentOrg);
		}
		//删除子机构
		OrgOrganization[] children = querySubOrgs(parentOrg.getOrgid()+"");
		if(children != null){
			deleteOrgOrganization(children);
		}
	}

	public void getOrgOrganization(OrgOrganization org){
		getDASTemplate().expandEntity(org);
	}
	
	/**
	 * 获取子机构同时获取父机构信息
	 * @param child
	 */
	public OrgOrganization getOrgOrganizationWithParent(OrgOrganization child){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgOrganization.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgid", child.getOrgid()));
		dasCriteria.addAssociation("orgOrganization");
		dasCriteria.asc("sortno");
		OrgOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgOrganization.class, dasCriteria);
		if(orgs != null && orgs.length == 1){
			return orgs[0];
		}
		return OrgOrganization.FACTORY.create();
	}
	
	/**
	 * 获取取父机构信息
	 * @param child
	 */
	public DataObject getOrgOrganizationParent(String childId){
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgOrganization.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgid", new BigDecimal(childId)));
		dasCriteria.addAssociation("orgOrganization");
		dasCriteria.asc("sortno");
		OrgOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgOrganization.class, dasCriteria);
		if(orgs != null && orgs.length == 1){
			DataObject parent=orgs[0].getDataObject(29);
			if(parent!=null){
				return parent;
			}
		}
		return null;
	}
	
	/**
	 * 查询条件中加入可管理的机构过滤条件
	 * @param criteriaType
	 */
	public int addAuthorizedCriteria(CriteriaType criteriaType){
		GradeAuthService bean = BeanFactory.newInstance().getBean(GradeAuthService.SPRING_BEAN_NAME);
		List<Party> orgList = bean.getManagedOrgList();
		if (orgList.size()==0)
			return 0;
		
		List<LogicType> exList = criteriaType.get_or();
		if (exList==null){
			exList = new ArrayList<LogicType>();
			criteriaType.set_or(exList);
		}
		
		LogicType logicType = new LogicTypeImpl();
		logicType.set_expr(new ArrayList<ExprType>());

		for (Party party : orgList) {
			ExprType ex = new ExprTypeImpl();
			ex.set_op("like");
			ex.set_likeRule("end");
			ex.set("orgseq", party.getExtAttribute(IConstants.ORG_SEQ));
			logicType.get_expr().add(ex);
		}
		criteriaType.get_or().add(logicType);
		return orgList.size();
	}
	
	public OrgOrganization[]  queryOrgOrganizations(CriteriaType criteriaType,PageCond pageCond){
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		dasCriteria.asc("orglevel");
		dasCriteria.asc("sortno");
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(OrgOrganization.class, dasCriteria, pageCond);
	}
	
	/**
	 * 查出所有的机构
	 * @return
	 */
	public OrgOrganization[] queryAllOrganizations(){
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity(OrgOrganization.QNAME);
		List<OrderbyType> orderList = new ArrayList<OrderbyType>();
		OrderbyType orderbyType = OrderbyType.FACTORY.create();
		orderbyType.set("_property", "sortno");
		orderbyType.set("_sort", "asc");
		orderList.add(orderbyType);
		criteria.set_orderby(orderList);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().queryEntitiesByCriteriaEntity(OrgOrganization.class, dasCriteria);
	}
	
	public int countOrgOrganizations(CriteriaType criteria) {
		criteria.set_entity(OrgOrganization.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}
	
	/**
	 * 查询机构下的所有子机构，参数orgid为空时返回顶级机构
	 * @param orgid
	 * @return
	 */
	public OrgOrganization[] querySubOrgs(String orgid) {
		// 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgOrganization.QNAME);
		dasCriteria.asc("sortno");
		if(StringUtils.isBlank(orgid)) {
			// orgid为空则查询顶级机构
			dasCriteria.add(ExpressionHelper.isNull("orgOrganization"));
		} else {
			dasCriteria.add(ExpressionHelper.eq("orgOrganization.orgid", orgid));
		}
		
		OrgOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgOrganization.class, dasCriteria);
		
		return orgs;
	}
	
	/**
	 * 查询机构下的所有岗位
	 * @param orgid
	 * @return
	 */
	public OrgPosition[] queryPositionsOfOrg(String orgid) {
		if(StringUtils.isBlank(orgid)) {
			return new OrgPosition[0];
		}
		
		// 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgOrganization.orgid", orgid));
		dasCriteria.add(ExpressionHelper.isNull("orgPosition.positionid"));
		
		OrgPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgPosition.class, dasCriteria);
		
		return positions;
	}

	/**
	 * 查询在机构下且未分配到此机构下级岗位的员工
	 * @param orgid
	 * @return
	 */
	public OrgEmployee[] queryEmployeesOfOrgNotInPosition(String orgid) {
		if(StringUtils.isBlank(orgid)) {
			return new OrgEmployee[0];
		}
		
		OrgEmployee[] emps = getDASTemplate().queryByNamedSql(
				OrgEmployee.class, "org.gocom.components.coframe.org.organization.select_orgemp", orgid);
		
		return emps;
	}
	
	/**
	 * 查询机构下的所有员工
	 * @param orgid
	 * @return
	 */
	public OrgEmployee[] queryEmployeesOfOrg(String orgid) {
		if(StringUtils.isBlank(orgid)) {
			return new OrgEmployee[0];
		}
		
		// 设置查询条件
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmployee.QNAME);
		dasCriteria.add(ExpressionHelper.eq("orgid", orgid));
		
		OrgEmployee[] emps = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmployee.class, dasCriteria);
		
		return emps;
	}
	
    public OrgResponse updateOrgOrganization(OrgOrganization org){
		if(!validateOrgcode(org)){
			return new OrgResponse(false, "不能修改为已存在的机构代码:"+org.getOrgcode());
		}
		getDASTemplate().updateEntity(org);
		return new OrgResponse(true, "修改成功");
    }

    public void deleteOrgOrganization(String id) {
    	if(!StringUtils.isBlank(id)){
    		OrgOrganization org = OrgOrganization.FACTORY.create();
    		org.setOrgid(new BigDecimal(id));
    		deleteOrgOrganization(new OrgOrganization[]{org});
    	}
    }
    
	public void setPositionService(IOrgPositionService positionService) {
		this.positionService = positionService;
	}

	public void setEmployeeService(IOrgEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public OrgOrganization[] queryOrgOrganizationsByIds(String[] ids) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgOrganization.QNAME);
		if(ids.length == 0){
			return new OrgOrganizationImpl[0];
		}
		dasCriteria.add(ExpressionHelper.in("orgid", ids));
		dasCriteria.asc("sortno");
		return getDASTemplate().queryEntitiesByCriteriaEntity(OrgOrganization.class, dasCriteria);
	}
	
}