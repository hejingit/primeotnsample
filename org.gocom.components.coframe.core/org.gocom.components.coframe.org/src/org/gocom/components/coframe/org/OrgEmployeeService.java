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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.dataset.OrgEmporg;
import org.gocom.components.coframe.org.dataset.OrgEmpposition;
import org.gocom.components.coframe.org.dataset.OrgOrganization;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.dataset.QueryEmpOrg;
import org.gocom.components.coframe.org.dataset.QueryEmpUser;
import org.gocom.components.coframe.org.groupdataset.OrgEmpgroup;
import org.gocom.components.coframe.org.util.IOrgConstants;
import org.gocom.components.coframe.org.util.OrgHelper;
import org.gocom.components.coframe.org.util.OrgResponse;
import org.gocom.components.coframe.rights.dataset.CapPartyauth;
import org.gocom.components.coframe.rights.dataset.CapUser;
import org.gocom.components.coframe.rights.dataset.impl.CapUserImpl;
import org.gocom.components.coframe.rights.gradeauth.GradeAuthService;
import org.gocom.components.coframe.rights.partyauth.DefaultPartyAuthManager;
import org.gocom.components.coframe.rights.user.DefaultUserManager;
import org.gocom.components.coframe.rights.user.ICapUserService;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
import org.gocom.components.coframe.tools.IConstants;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.das.entity.criteria.ExprType;
import com.eos.das.entity.criteria.LogicType;
import com.eos.das.entity.criteria.impl.ExprTypeImpl;
import com.eos.das.entity.criteria.impl.LogicTypeImpl;
import com.eos.foundation.PageCond;
import com.eos.spring.BeanFactory;
import com.primeton.cap.party.Party;

/**
 * 人员服务类
 *
 *  @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgEmployeeService extends CoframeDASDaoSupport implements IOrgEmployeeService{
	
	private ICapUserService userService = null;
	
	private IOrgEmporgService emporgService = null;
	
	private IOrgEmppositionService empPositionService = null;
	
	public OrgResponse addOrgEmployee(OrgEmployee emp, CapUser user, OrgOrganization org){
		if(!validateEmpcode(emp)){
			return new OrgResponse(false, "员工代码："+emp.getEmpcode() + "已存在");
		}
		//存在用户关联
		if(!StringUtils.isBlank(user.getUserId())){
			CapUser existsUser = userService.getCapUserByUserId(user.getUserId());
			//emp是否关联用户
			if(existsUser != null){//不能关联用户
				OrgEmployee[] userRelatedEmployees = queryEmployeesByOperatorId(existsUser);
				if(userRelatedEmployees != null && userRelatedEmployees.length > 0){
					return new OrgResponse(false, "用户登录名:"+existsUser.getUserId()+"已关联员工");
				}
				user.setOperatorId(existsUser.getOperatorId());
				user.setPassword(DefaultUserManager.INSTANCE.encodeString(user.getPassword()));
				userService.updateCapUser(user);//覆盖已有的用户信息
			}else{//新用户
				user.setUserName(user.getUserId());
				userService.addCapUser(user);
			}
			//关联用户和员工
			emp.setOperatorid(user.getOperatorId());
			emp.setUserid(user.getUserId());
		}
		
		//设置主键
		getDASTemplate().getPrimaryKey(emp);
		
		OrgHelper.expandEmployeeProperty(emp);
		
		emp.setOrgid(org.getOrgid());
		getDASTemplate().insertEntity(emp);
		
		//添加员工机构关系
		addEmporg(emp, org);
		return new OrgResponse(true, "添加成功");
	}
	
	/**
	 * 检验员工code是否合格
	 * @param emp
	 * @return true合格，false不合格
	 */
	public boolean validateEmpcode(OrgEmployee emp) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmployee.QNAME);
		dasCriteria.add(ExpressionHelper.eq(IOrgConstants.EMPCODE_PROPERTY, emp.getEmpcode()));
		OrgEmployee[] existsEmps = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmployee.class, dasCriteria);
		if(existsEmps == null || existsEmps.length == 0){
			return true;
		}
		if(emp.getEmpid() != null && existsEmps[0].getEmpid().equals(emp.getEmpid())){//修改的情况，只能为1个
			return existsEmps.length == 1;
		}else{//新增,必定没有
			return existsEmps.length == 0;
		}
	}
	
	/**
	 * 添加机构和人员的关系
	 * @param emp 员工
	 * @param org 机构
	 */
	private void addEmporg(OrgEmployee emp, OrgOrganization org){
		OrgEmporg orgEmpAssosiation = OrgEmporg.FACTORY.create();
		orgEmpAssosiation.setOrgEmployee(emp);
		orgEmpAssosiation.setOrgid(org.getOrgid());
		emporgService.addOrgEmporg(orgEmpAssosiation);
	}

	/**
	 * 根据用户查询出它关联的员工
	 * @param user
	 * @return
	 */
	private OrgEmployee[] queryEmployeesByOperatorId(CapUser user) {
		if(user != null && user.getOperatorId() != null){
			IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmployee.QNAME);
			dasCriteria.add(ExpressionHelper.eq("operatorid", user.getOperatorId()));
			return getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmployee.class, dasCriteria);
		}
		return new OrgEmployee[0];
	}

	public void deleteOrgEmployee(OrgEmployee[] emps){
		if(emps == null) return;
		for(OrgEmployee emp:emps){
			empPositionService.deleteEmppositionsByEmp(emp);
			emporgService.deleteEmporgByEmp(emp);
			getDASTemplate().deleteEntityCascade(emp);
		}
	}

	public void getOrgEmployee(OrgEmployee emp){
		getDASTemplate().expandEntity(emp);
	}
	
	public QueryEmpOrg[] queryOrgEmployees(CriteriaType criteriaType,PageCond pageCond){
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(QueryEmpOrg.class, dasCriteria, pageCond);
	}
	
	public QueryEmpUser[]  queryEmpUsers(CriteriaType criteriaType,PageCond pageCond){
		criteriaType.set_entity(QueryEmpUser.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		dasCriteria.asc("sortno");
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(QueryEmpUser.class, dasCriteria, pageCond);
	}
	
	/**
	 * 查询条件中加入可管理的机构过滤条件
	 * @param criteriaType
	 */
	public CriteriaType addAuthorizedCriteria(CriteriaType criteriaType){
		GradeAuthService bean = BeanFactory.newInstance().getBean(GradeAuthService.SPRING_BEAN_NAME);
		List<Party> orgList = bean.getManagedOrgList();
		if (orgList==null||orgList.size()==0)
			return null;
		
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
		
		return criteriaType;
	}
	
	public int countOrgEmployees(CriteriaType criteria) {
		criteria.set_entity(QueryEmpOrg.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}
	
	public OrgResponse updateOrgEmployee(OrgEmployee emp) {
		if(!validateEmpcode(emp)){
			return new OrgResponse(false, "不能修改为已存在的员工代码:"+emp.getEmpcode());
		}
		getDASTemplate().updateEntity(emp);
		return new OrgResponse(true, "修改成功");
	}
	
    public OrgResponse updateOrgEmployee(OrgEmployee emp, CapUser user){
		if(!validateEmpcode(emp)){
			return new OrgResponse(false, "不能修改为已存在的员工代码:"+emp.getEmpcode());
		}
		//存在用户关联
		CapUser capUser=new CapUserImpl();  // bug: 46048 修改员工信息，修改操作员会产生两条操作员记录
		capUser.setOperatorId(user.getOperatorId());
		userService.getCapUser(capUser);
		if(!StringUtils.isBlank(user.getUserId())){
//			CapUser existsUser = userService.getCapUserByUserId(user.getUserId());
//			if(existsUser == null){
//				user.setPassword(IConstants.DEFAULT_PASSWORD);
//				userService.addCapUser(user);
//				emp.setUserid(user.getUserId());
//				emp.setOperatorid(user.getOperatorId());
//			}else{//可以关联原来的员工
//				if(existsUser.getOperatorId().equals(user.getOperatorId())){//同一个用户，可以更新
//					userService.updateCapUser(user);
//				}else{//不同用户，则不能关联已存在员工的用户
//					OrgEmployee[] employees = queryEmployeesByOperatorId(existsUser);
//					if(employees.length > 0){
//						return new OrgResponse(false, "用户登录名:"+existsUser.getUserId()+"已关联员工");
//					}
//				}
//				emp.setOperatorid(existsUser.getOperatorId());
//				emp.setUserid(existsUser.getUserId());
//			}

			if(capUser.getUserId()!=null && capUser.getUserId().trim().length()>0) { //found，更新操作员，前提是操作员没有授权过
				if(hasPartyAuth(user.getOperatorId())){
					return new OrgResponse(false, "用户登录名:"+capUser.getUserId()+"已经授权");
				}
				if(capUser.getPassword()==null) {
					String passwdEncode=DefaultUserManager.INSTANCE.encodeString(IConstants.DEFAULT_PASSWORD);
					user.setPassword(passwdEncode);
				}
				user.setUserName(emp.getEmpname());
				userService.updateCapUser(user);
				emp.setOperatorid(user.getOperatorId());
				emp.setUserid(user.getUserId());				
			}else {  // not found,新增操作员
				user.setPassword(IConstants.DEFAULT_PASSWORD);
				user.setUserName(emp.getEmpname());
				if(user.getAuthmode()==null)
					user.setAuthmode("local");
				if(user.getStatus()==null)
					user.setStatus("1");
				if(user.getTenantId()==null)
					user.setTenantId("default");
				if(user.getMenutype()==null)
					user.setMenutype("default");
				user.setStartdate(new Date());
				userService.addCapUser(user);
				emp.setUserid(user.getUserId());
				emp.setOperatorid(user.getOperatorId());				
			}
		}else{
			if(hasPartyAuth(user.getOperatorId())){
				return new OrgResponse(false, "原用户登录名:"+capUser.getUserId()+"已经授权");
			}
			//取消员工和用户的关联
			emp.setUserid(null);
			emp.setOperatorid(null);
		}
		getDASTemplate().updateEntity(emp);
		return new OrgResponse(true, "修改成功");
    }
    
    private boolean hasPartyAuth(BigDecimal operatorId) {
		CapUser capUser=new CapUserImpl();  // bug: 46048 修改员工信息，修改操作员会产生两条操作员记录
		capUser.setOperatorId(operatorId);
		userService.getCapUser(capUser);
		if(capUser.getUserId()!=null && capUser.getUserId().trim().length()>0) {
			//检查操作员是否已经授权
			Party party=new Party();
			party.setId(capUser.getUserId());
			party.setPartyTypeID(IConstants.USER_PARTY_TYPE_ID);
			DefaultPartyAuthManager partyAuthManager=new DefaultPartyAuthManager();
			CapPartyauth[] auths=partyAuthManager.getCapPartyauthListByParty(party);
			if(auths.length>0)
				return true;
			else
				return false;
		}else
			return false;
    	
    }    
	/* (non-Javadoc)
     * @see org.gocom.components.coframe.org.IOrgEmployeeService#deleteEmpAndOrgRelationship(org.gocom.components.coframe.org.dataset.OrgOrganization)
     */
    public void deleteEmpAndOrgRelationship(OrgOrganization org) {
    	if(org.getOrgid() == null) return;
    	OrgEmporg[] empOrgs = emporgService.queryOrgEmporgsByOrg(org);
    	if(empOrgs != null && empOrgs.length > 0){
    		emporgService.deleteOrgEmporg(empOrgs);
    	}
    }
    
    
    public void deleteOrgEmployee(String id, String parentId, String parentType,String isDeleteUserCascade) {
    	if(StringUtils.isBlank(parentType)) return;
    	OrgEmployee empWillBeDelete = OrgEmployee.FACTORY.create();
    	empWillBeDelete.setEmpid(new BigDecimal(id));
    	if("true".equals(isDeleteUserCascade)){
    		getOrgEmployee(empWillBeDelete);
    	}
    	if(IOrgConstants.NODE_TYPE_ORG.equals(parentType)){//父节点是机构
    		//if("true".equals(isDeleteUserCascade)){
    			//删除机构和人员的关系
    			OrgEmporg empOrgAssosiation = OrgEmporg.FACTORY.create();
    			empOrgAssosiation.setOrgid(new BigDecimal(parentId));
    			empOrgAssosiation.setOrgEmployee(empWillBeDelete);
    			emporgService.deleteOrgEmporg(new OrgEmporg[]{empOrgAssosiation});
    			//删除人员与岗位的关系
        		IDASCriteria dasCriteria_del = getDASTemplate().createCriteria(OrgEmpposition.QNAME);
        		dasCriteria_del.add(ExpressionHelper.eq("orgEmployee.empid", id));
        		getDASTemplate().deleteByCriteriaEntity(dasCriteria_del);
        		//删除人员与工作组的关系
        		IDASCriteria dasCriteria_del2 = getDASTemplate().createCriteria(OrgEmpgroup.QNAME);
        		dasCriteria_del2.add(ExpressionHelper.eq("orgEmployee.empid", id));
        		getDASTemplate().deleteByCriteriaEntity(dasCriteria_del2);
        	//}
    		//判断人员是否在其他机构下，如果不在，则删除这个员工
    		OrgEmporg[] existsEmpOrgAssosiations = emporgService.queryOrgEmporgsByEmp(empWillBeDelete);
    		if(existsEmpOrgAssosiations.length == 0){
    			deleteOrgEmployee(new OrgEmployee[]{empWillBeDelete});
    		}
    		
    	}else if(IOrgConstants.NODE_TYPE_ORGPOSI.equals(parentType)){//父节点是岗位
    		//直接删除人员和岗位的关联关系
    		OrgEmpposition empPositionAssosiation = OrgEmpposition.FACTORY.create();
    		OrgPosition position = OrgPosition.FACTORY.create();
    		position.setPositionid(new BigDecimal(parentId));
    		empPositionAssosiation.setOrgEmployee(empWillBeDelete);
    		empPositionAssosiation.setOrgPosition(position);
    		empPositionService.deleteOrgEmpposition(new OrgEmpposition[]{empPositionAssosiation});
    	}else if(IOrgConstants.NODE_TYPE_GROUP.equals(parentType)){//父节点是岗位
    		//删除工作组和人员的关系
    		OrgEmpgroup empgroup = OrgEmpgroup.FACTORY.create();
    		empgroup.setGroupid(new BigDecimal(parentId));
    		empgroup.setOrgEmployee(empWillBeDelete);
    		getDASTemplate().deleteEntity(empgroup);
    	}
    	CapUser capUser = CapUser.FACTORY.create();
    	capUser.setUserId(empWillBeDelete.getUserid());
    	if(capUser.getUserId()!=null){
    		userService.deleteCapUserByTemplate(capUser);
    	}
    }
    
	public OrgEmployee[] queryEmpsAllowAddInPosition(String userid, String empname, String positionid, PageCond page) {
		OrgEmployee[] emps = getDASTemplate().queryByNamedSqlWithPage(
				OrgEmployee.class, "org.gocom.components.coframe.org.empQuery.selectEmpAllowAdd", page, getNameSqlParamsMap(userid, empname, positionid));
		return emps;
	}
	
	/**
	 * 工作组中用户分页查询
	 * @param userid
	 * @param empname
	 * @param page
	 * @return
	 */
	public OrgEmployee[] queryEmpsAllowAddInGroup(String userid, String empname, PageCond page) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmployee.QNAME);
		if(!StringUtils.isBlank(userid)){
			dasCriteria.add(ExpressionHelper.eq("userid", userid));
		}
		if(!StringUtils.isBlank(empname)){
			dasCriteria.add(ExpressionHelper.eq("empname", empname));
		}
		OrgEmployee[] emps=getDASTemplate().queryEntitiesByCriteriaEntityWithPage(OrgEmployee.class, dasCriteria, page);
		return emps;
	}
	
	public Integer countEmpsAllowAddInPosition(String userid, String empname, String positionid) {
		Integer[] empSize = getDASTemplate().queryByNamedSql(Integer.class,"org.gocom.components.coframe.org.empQuery.countEmpAllowAdd", getNameSqlParamsMap(userid, empname, positionid));
		if(empSize == null || empSize.length == 0) return 0;
		return empSize[0];
	}

	/**
	 * 工作组中用户数量
	 * @param userid
	 * @param empname
	 * @return
	 */
	public Integer countEmpsAllowAddInGroup(String userid, String empname) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmployee.QNAME);
		if(!StringUtils.isBlank(userid)){
			dasCriteria.add(ExpressionHelper.eq("userid", userid));
		}
		if(!StringUtils.isBlank(empname)){
			dasCriteria.add(ExpressionHelper.eq("empname", empname));
		}
		return getDASTemplate().count(dasCriteria);
	}
	
	public OrgEmployee[] queryEmpsInPosition(String userid, String empname, String positionid, PageCond page) {
		OrgEmployee[] emps = getDASTemplate().queryByNamedSqlWithPage(
				OrgEmployee.class, "org.gocom.components.coframe.org.empQuery.selectEmpInPosition",page, getNameSqlParamsMap(userid, empname, positionid));
		return emps;		
	}
	
	public Integer countEmpsInPosition(String userid, String empname, String positionid) {
		Integer[] empSize = getDASTemplate().queryByNamedSql(Integer.class, "org.gocom.components.coframe.org.empQuery.countEmpInPosition", getNameSqlParamsMap(userid, empname, positionid));
		if(empSize == null || empSize.length == 0) return 0;
		return empSize[0];
	}
	
	private HashMap<String, Object> getNameSqlParamsMap(String userid, String empname, String positionid){
		HashMap<String, Object> nameSqlParamsMap = new HashMap<String, Object>();
		if(!StringUtils.isBlank(userid)){
			nameSqlParamsMap.put("userid", userid);
		}
		if(!StringUtils.isBlank(empname)){
			nameSqlParamsMap.put("empname", empname);
		}
		if(!StringUtils.isBlank(positionid)){
			nameSqlParamsMap.put("positionid", new BigDecimal(positionid));
		}
		return nameSqlParamsMap;
	}
	
	public void setUserService(ICapUserService userService) {
		this.userService = userService;
	}

	public void setEmporgService(IOrgEmporgService emporgService) {
		this.emporgService = emporgService;
	}

	public void setEmpPositionService(IOrgEmppositionService empPositionService) {
		this.empPositionService = empPositionService;
	}
	
	public void deleteCapUserRelatedEmp(String userId){
		CapUser capUser = CapUser.FACTORY.create();
		capUser.setUserId(userId);
		getDASTemplate().deleteByTemplate(capUser);
	}

}