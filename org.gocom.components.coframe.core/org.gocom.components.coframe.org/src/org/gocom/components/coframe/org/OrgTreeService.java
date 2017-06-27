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

import static org.gocom.components.coframe.org.util.IOrgConstants.EMPID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.EMP_REF_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.ORGID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.ORG_REF_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.POSITIONID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.POSITION_REF_PROPERTY;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.dataset.OrgEmporg;
import org.gocom.components.coframe.org.dataset.OrgEmpposition;
import org.gocom.components.coframe.org.dataset.OrgOrganization;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.dataset.QueryPositionEmp;
import org.gocom.components.coframe.org.util.IOrgConstants;
import org.gocom.components.coframe.org.util.OrgHelper;

import com.eos.das.entity.DASManager;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.foundation.data.DataObjectUtil;
import com.primeton.cap.TenantManager;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
import commonj.sdo.DataObject;

/**
 * 组织机构树服务类
 *
 *  @author yangzhou (mailto:yangzhou@primeton.com)
 */
public class OrgTreeService extends CoframeDASDaoSupport implements IOrgTreeService{
	
	private static final String EXPANDED = "expanded";

	private static final String IS_LEAF = "isLeaf";

	private IOrgOrganizationService organizationService;
	
	private IOrgPositionService positionService;
	
	/**
	 * 查询机构下的所有子机构，参数orgid为空时返回顶级机构
	 * @param orgid
	 * @return
	 */
	public OrgOrganization[] querySubOrgs(String orgid) {
		OrgOrganization[] orgs = organizationService.querySubOrgs(orgid);
		
		// 设置NUI所需的属性
		for(OrgOrganization org : orgs) {
//			boolean isLeaf = !"n".equalsIgnoreCase(org.getIsleaf());
			org.setBoolean(IS_LEAF, false);
			org.setBoolean(EXPANDED, false);
		}
		
		return orgs;
	}
	
	/**
	 * 查询机构下的所有岗位
	 * @param orgid
	 * @return
	 */
	public OrgPosition[] queryPositionsOfOrg(String orgid) {
		OrgPosition[] positions = organizationService.queryPositionsOfOrg(orgid);;
		
		// 设置NUI所需的属性
		for(OrgPosition position : positions) {
//			boolean isLeaf = !"n".equalsIgnoreCase(position.getIsleaf());
			position.setBoolean(IS_LEAF, false);
			position.setBoolean(EXPANDED, false);
		}
		
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
		
		// 设置NUI所需的属性
		for(OrgEmployee emp : emps) {
			emp.setBoolean(IS_LEAF, true);
			emp.setBoolean(EXPANDED, true);
		}
		
		return emps;
	}
	
	/**
	 * 获取岗位下的所有子岗位
	 * @param positionid
	 * @return
	 */
	public OrgPosition[] querySubPositions(String positionid) {
		OrgPosition[] positions = positionService.querySubPositions(positionid);
		
		// 设置NUI所需的属性
		for(OrgPosition position : positions) {
//			boolean isLeaf = !"n".equalsIgnoreCase(position.getIsleaf());
			position.setBoolean(IS_LEAF, false);
			position.setBoolean(EXPANDED, false);
		}
		
		return positions;
	}
	
	/**
	 * 获取岗位下的所有员工
	 * @param orgid
	 * @return
	 */
	public QueryPositionEmp[] queryEmployeesOfPosition(String positionid) {
		QueryPositionEmp[] emps = positionService.queryEmployeesOfPosition(positionid);
		
		// 设置NUI所需的属性
		for(QueryPositionEmp emp : emps) {
			emp.setBoolean(IS_LEAF, true);
			emp.setBoolean(EXPANDED, true);
		}
		
		return emps;
	}
	
	/**
	 * 复制机构到另一个机构中作为子机构
	 * @param orgid
	 * @param targetOrgid
	 */
	public void copyOrgToOrg(String orgid, String targetOrgid) {
		OrgOrganization targetOrg = getOrganizationById(targetOrgid);
		
		OrgOrganization org = getOrganizationById(orgid);
		OrgHelper.expandOrganizationPropertyByParent(org, targetOrg);
		
		// 克隆一个新机构
		DataObject newOrg = DataObjectUtil.convertDataObject(org, OrgOrganization.QNAME, false);
		getDASTemplate().getPrimaryKey(newOrg);
		newOrg.set("orgcode", newOrg.getString("orgcode")+newOrg.getString("orgid"));
		newOrg.set(ORG_REF_PROPERTY, targetOrg);
		getDASTemplate().insertEntity(newOrg);
	}
	
	/**
	 * 复制岗位到另一个机构下
	 * @param positionid
	 * @param targetOrgid
	 */
	public void copyPositionToOrg(String positionid, String targetOrgid) {
		OrgOrganization targetOrg = getOrganizationById(targetOrgid);
		
		OrgPosition position = loadPositionWithOrg(positionid);
		
		// 克隆一个新岗位
		DataObject newPosition = DataObjectUtil.convertDataObject(position, OrgPosition.QNAME, false);
		getDASTemplate().getPrimaryKey(newPosition);
		newPosition.set("posicode", newPosition.getString("posicode")+newPosition.getString("positionid"));
		newPosition.set(POSITION_REF_PROPERTY, null);
		newPosition.set(ORG_REF_PROPERTY, targetOrg);
		getDASTemplate().insertEntity(newPosition);
	}
	/**
	 * 复制岗位到另一个岗位下
	 * @param positionid
	 * @param targetPositionid
	 */
	public void copyPositionToPosition(String positionid, String targetPositionid) {
		OrgPosition targetPosition = loadPositionWithOrg(targetPositionid);
		
		OrgPosition position = loadPositionWithOrg(positionid);
		
		// 克隆一个新岗位
		DataObject newPosition = DataObjectUtil.convertDataObject(position, OrgPosition.QNAME, false);
		getDASTemplate().getPrimaryKey(newPosition);
		newPosition.set("posicode", newPosition.getString("posicode")+newPosition.getString("positionid"));
		newPosition.set(POSITION_REF_PROPERTY, targetPosition);
		newPosition.set(ORG_REF_PROPERTY, targetPosition.getOrgOrganization());
		getDASTemplate().insertEntity(newPosition);
	}

	/**
	 * 移动机构到另一个机构中作为子机构
	 * @param orgid
	 * @param targetOrgid
	 */
	public void moveOrgToOrg(String orgid, String targetOrgid) {
		OrgOrganization targetOrg = getOrganizationById(targetOrgid);
		OrgOrganization org = getOrganizationById(orgid);
		org.setOrgOrganization(targetOrg);
		
		//级别在父机构的级别上增加1
		org.setOrglevel(targetOrg.getOrglevel().add(BigDecimal.valueOf(1)));
		//存在父机构:父机构顺序+本机构ID+".";
		org.setOrgseq(targetOrg.getOrgseq() + org.getOrgid()+".");
		getDASTemplate().updateEntity(org);
	}
	
	/**
	 * 移动岗位到一个机构下
	 * @param positionid
	 * @param targetOrgid
	 */
	public void movePositionToOrg(String positionid, String targetOrgid) {
		OrgOrganization targetOrg = getOrganizationById(targetOrgid);
		OrgPosition position = loadPositionWithOrg(positionid);
		position.setOrgOrganization(targetOrg);
		position.setOrgPosition(null);
		getDASTemplate().updateEntity(position);
	}

	/**
	 * 移动岗位到另一个岗位下做为子岗位
	 * @param positionid
	 * @param targetOrgid
	 */
	public void movePositionToPosition(String positionid, String targetPositionid) {
		OrgPosition targetPosition = loadPositionWithOrg(targetPositionid);
		OrgPosition position = loadPositionWithOrg(positionid);
		position.setOrgOrganization(targetPosition.getOrgOrganization());
		position.setOrgPosition(targetPosition);
		getDASTemplate().updateEntity(position);
	}

	/**
	 * 通过机构ID获取机构对象
	 * @param orgid
	 */
	private OrgOrganization getOrganizationById(String orgid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgOrganization.QNAME);
		dasCriteria.add(ExpressionHelper.eq(IOrgConstants.ORGID_PROPERTY, orgid));
		OrgOrganization[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgOrganization.class, dasCriteria);
		if(orgs != null && orgs.length == 1) {
			return orgs[0];
		}
		return null;
	}
	
	/**
	 * 复制员工到岗位
	 * @param empid
	 * @param targetPositionid
	 */
	public int copyEmpToPosition(String empid, String targetPositionid) {
		OrgEmpposition targetEmpposition = loadEmppositionWithPositionWithOrg(empid, targetPositionid);
		
		// 当员工在目标岗位中不存在时,才做复制处理
		if(targetEmpposition != null) 
			return -1;
		
		OrgPosition targetPosition = loadPositionWithOrg(targetPositionid);
		
		// 先复制员工到机构中
		copyEmpToOrg(empid, String.valueOf(targetPosition.getOrgOrganization().getOrgid()));
		// 再保存员工岗位关系
		targetEmpposition = createEmpposition(empid, targetPositionid);
		getDASTemplate().insertEntity(targetEmpposition);
		return 1;
	}
	
	public int copyEmpToOrg(String empid, String targetOrgid) {
		OrgEmporg targetEmporg = loadEmporgWithEmp(empid, targetOrgid);
		
		// 当员工在目标机构中不存在时,才做复制处理
		if(targetEmporg != null) 
			return -1;
		
		targetEmporg = createEmporg(empid, targetOrgid);
		getDASTemplate().insertEntity(targetEmporg);
		return 1;
	}
	
	/**
	 * 移动人员从当前机构到另一个机构
	 * @param fromOrgid
	 * @param parentOrgid
	 */
	public int moveEmpFromOrgToOrg(String empid, String fromOrgid, String targetOrgid) {
		OrgEmporg fromEmporg = loadEmporgWithEmp(empid, fromOrgid);
		OrgEmporg tartgetEmporg = loadEmporgWithEmp(empid, targetOrgid);
		
		// 当员工在目标机构中不存在时
		if(fromEmporg != null && tartgetEmporg == null) {
			tartgetEmporg = createEmporg(empid, targetOrgid);
			getDASTemplate().insertEntity(tartgetEmporg);
			
			// 修改员工的主管机构
			OrgEmployee emp = tartgetEmporg.getOrgEmployee();
			emp.setOrgid(tartgetEmporg.getOrgid());
			getDASTemplate().updateEntity(emp);
			
			getDASTemplate().deleteEntity(fromEmporg);
			return 1;
		}
		
		return -1;
	}
	
	/**
	 * 移动人员从岗位到机构
	 * @param empid
	 * @param fromPositionid
	 * @param targetOrgid
	 */
	public int moveEmpFromPositionToOrg(String empid, String fromPositionid, String targetOrgid) {
		OrgEmpposition fromEmpposition = loadEmppositionWithPositionWithOrg(empid, fromPositionid);
		OrgEmporg tartgetEmporg = loadEmporgWithEmp(empid, targetOrgid);
		
		// 当员工在目标机构中不存在时
		if(fromEmpposition != null && tartgetEmporg == null) {
			OrgOrganization fromOrg = fromEmpposition.getOrgPosition().getOrgOrganization();
			
			// 在不同机构下时的处理
			if(!String.valueOf(fromOrg.getOrgid()).equals(targetOrgid)) { 
				IDASCriteria dasCriteria = DASManager.createCriteria(OrgEmpposition.QNAME);
				dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "." + ORGID_PROPERTY, fromOrg.getOrgid()));
				dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
				dasCriteria.addAssociation(POSITION_REF_PROPERTY);
				int count = getDASTemplate().count(dasCriteria);
				//员工在当前机构中存在多个岗位中
				if (count>1){
					//不修改主管机构，不从当前机构中删除
					tartgetEmporg = createEmporg(empid, targetOrgid);
					getDASTemplate().insertEntity(tartgetEmporg);
				}else{
					moveEmpFromOrgToOrg(empid, String.valueOf(fromOrg.getOrgid()), targetOrgid);
				}
			}
			
			getDASTemplate().deleteEntity(fromEmpposition);
			return 1;
		}
		
		//当员工在目标机构中存在，且为同一机构，即仅仅是从岗位中移到机构下，删除岗位关系
		if(fromEmpposition != null && tartgetEmporg != null){
			OrgOrganization fromOrg = fromEmpposition.getOrgPosition().getOrgOrganization();
			if (String.valueOf(fromOrg.getOrgid()).equals(targetOrgid)) {
				getDASTemplate().deleteEntity(fromEmpposition);
				return 1;
			}
		}
		
		return -1;
	}
	
	/**
	 * 移动人员从机构到岗位
	 * @param empid
	 * @param positionid
	 * @param targetOrgid
	 */
	public int moveEmpFromOrgToPosition(String empid, String fromOrgid, String targetPositionid) {
		OrgEmporg fromEmporg = loadEmporgWithEmp(empid, fromOrgid);
		OrgEmpposition targetEmpposition = loadEmppositionWithPositionWithOrg(empid, targetPositionid);
		
		// 当员工在当前机构中存在,并且在目标岗位中不存在时,才做移动处理
		if(fromEmporg != null && targetEmpposition == null) {
			OrgPosition targetPosition = loadPositionWithOrg(targetPositionid);
			
			if(targetPosition != null) {
				OrgOrganization targetOrg = targetPosition.getOrgOrganization();
				
				// 在不同机构下时的处理
				if(targetOrg != null && !String.valueOf(targetOrg.getOrgid()).equals(fromOrgid)) {
					//删除员工与当前机构的关系
					getDASTemplate().deleteEntity(fromEmporg);
					//移动员工信息到目标机构
					moveEmpFromOrgToOrg(empid, fromOrgid, String.valueOf(targetOrg.getOrgid()));
				}
				
				targetEmpposition = createEmpposition(empid, targetPositionid);
				getDASTemplate().insertEntity(targetEmpposition);
			}
			return 1;
		}

		return -1;
	}

	/**
	 * 移动员工从指定岗位到目标岗位
	 * @param empid
	 * @param positionid
	 */
	public int moveEmpFromPositionToPosition(String empid, String positionid, String targetPositionid) {
		OrgEmpposition fromEmpposition = loadEmppositionWithPositionWithOrg(empid, positionid);
		OrgEmpposition targetEmpposition = loadEmppositionWithPositionWithOrg(empid, targetPositionid);
		
		// 当员工在当前岗位中存在,并且在目标岗位中不存在时,才做移动处理
		if(fromEmpposition != null && targetEmpposition == null) {
			OrgOrganization fromOrg = fromEmpposition.getOrgPosition().getOrgOrganization();
			OrgPosition targetPosition = loadPositionWithOrg(targetPositionid);
			
			if(targetPosition != null) {
				OrgOrganization targetOrg = targetPosition.getOrgOrganization();
				String targetOrgid = String.valueOf(targetOrg.getOrgid());
			
				// 在不同机构下时的处理
				if(!fromOrg.getOrgid().equals(targetOrg.getOrgid())) {
					IDASCriteria dasCriteria = DASManager.createCriteria(OrgEmpposition.QNAME);
					dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "." + ORGID_PROPERTY, fromOrg.getOrgid()));
					dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
					dasCriteria.addAssociation(POSITION_REF_PROPERTY);
					int count = getDASTemplate().count(dasCriteria); 
					//员工在当前机构中存在多个岗位中
					if (count>1){
						OrgEmporg tartgetEmporg = loadEmporgWithEmp(empid, targetOrgid);
						if (tartgetEmporg==null){
							//不修改主管机构，不从当前机构中删除
							tartgetEmporg = createEmporg(empid, targetOrgid);
							getDASTemplate().insertEntity(tartgetEmporg);
						}
					}else{
						moveEmpFromOrgToOrg(empid, String.valueOf(fromOrg.getOrgid()), targetOrgid);
					}
				}
				
			}
			
			targetEmpposition = createEmpposition(empid, targetPositionid);
			getDASTemplate().deleteEntity(fromEmpposition);
			getDASTemplate().insertEntity(targetEmpposition);
			return 1;
		}
		return -1;
	}
	/**
	 * 删除人员的员工岗位关系
	 * @param empid
	 * @param orgid
	 */
	private void deleteEmpposition(String empid, String positionid) {
		IDASCriteria dasCriteria = DASManager.createCriteria(OrgEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "." + POSITIONID_PROPERTY, positionid));
		getDASTemplate().deleteByCriteriaEntity(dasCriteria);
	}

	
	/**
	 * 删除人员在机构下的员工岗位关系
	 * @param empid
	 * @param orgid
	 */
	private void deleteEmppositionOfOrg(String empid, String orgid) {
		IDASCriteria dasCriteria = DASManager.createCriteria(OrgEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "." + ORG_REF_PROPERTY + "." + ORGID_PROPERTY, orgid));
		getDASTemplate().deleteByCriteriaEntity(dasCriteria);
	}

	/**
	 * 加载员工与机构的关联实体，并且级联加载员工实体
	 * @param empid
	 * @param orgid
	 * @return
	 */
	private OrgEmporg loadEmporgWithEmp(String empid, String orgid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmporg.QNAME);
		dasCriteria.add(ExpressionHelper.eq(ORGID_PROPERTY, orgid));
		dasCriteria.addAssociation(EMP_REF_PROPERTY);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		OrgEmporg[] emporgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmporg.class, dasCriteria);
		if(emporgs != null && emporgs.length == 1){
			getDASTemplate().expandRelation(emporgs[0], EMP_REF_PROPERTY); // 加载关联的员工实体
			return emporgs[0];
		}
		return null;
	}
	
	/**
	 * 加载员工与岗位的关联实体，级联加载岗位实体以及岗位关联的机构实体
	 * @param empid
	 * @param positionid
	 * @return
	 */
	private OrgEmpposition loadEmppositionWithPositionWithOrg(String empid, String positionid) {
		IDASCriteria dasCriteria = DASManager.createCriteria(OrgEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "." + POSITIONID_PROPERTY, positionid));
		dasCriteria.addAssociation(POSITION_REF_PROPERTY);
		dasCriteria.addAssociation(POSITION_REF_PROPERTY + "." + ORG_REF_PROPERTY);
		OrgEmpposition[] emppositions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmpposition.class, dasCriteria);
		if(emppositions != null && emppositions.length == 1){
			getDASTemplate().expandRelation(emppositions[0], POSITION_REF_PROPERTY); // 加载关联的岗位实体
			getDASTemplate().expandRelation(emppositions[0].getOrgPosition(), ORG_REF_PROPERTY); // 加载岗位关联的机构实体
			return emppositions[0];
		}
		return null;
	}
	
	/**
	 * 加载岗位，级联加载父机构
	 * @param positionid
	 * @return
	 */
	private OrgPosition loadPositionWithOrg(String positionid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgPosition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(POSITIONID_PROPERTY, positionid));
		dasCriteria.addAssociation(ORG_REF_PROPERTY);
		OrgPosition[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgPosition.class, dasCriteria);
		if(positions != null && positions.length == 1){
			return positions[0];
		}
		return null;
	}
	
	/**
	 * 创建员工与机构的关联实体
	 * @param empid
	 * @param orgid
	 * @return
	 */
	private OrgEmporg createEmporg(String empid, String orgid) {
		OrgEmployee emp = OrgEmployee.FACTORY.create();
		emp.setEmpid(new BigDecimal(empid));
		
		OrgEmporg emporg = OrgEmporg.FACTORY.create();
		emporg.setOrgid(new BigDecimal(orgid));
		emporg.setTenantid(TenantManager.getCurrentTenantID());
		emporg.setOrgEmployee(emp);
		return emporg;
	}
	
	/**
	 * 创建员工与岗位的关联实体
	 * @param empid
	 * @param positionid
	 * @return
	 */
	private OrgEmpposition createEmpposition(String empid, String positionid) {
		OrgEmployee emp = OrgEmployee.FACTORY.create();
		emp.setEmpid(new BigDecimal(empid));
		emp.setTenantid(TenantManager.getCurrentTenantID());
		
		OrgPosition position = OrgPosition.FACTORY.create();
		position.setPositionid(new BigDecimal(positionid));
		position.setTenantid(TenantManager.getCurrentTenantID());
		
		OrgEmpposition empposition = OrgEmpposition.FACTORY.create();
		empposition.setTenantid(TenantManager.getCurrentTenantID());
		empposition.setOrgEmployee(emp);
		empposition.setOrgPosition(position);
		
		return empposition;
	}

	public void setOrganizationService(IOrgOrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public void setPositionService(IOrgPositionService positionService) {
		this.positionService = positionService;
	}

}
