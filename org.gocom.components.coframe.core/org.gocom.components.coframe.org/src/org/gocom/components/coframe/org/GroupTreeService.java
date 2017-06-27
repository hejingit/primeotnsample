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
import static org.gocom.components.coframe.org.util.IOrgConstants.GROUPID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.GROUP_REF_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.POSITIONID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.POSITION_REF_PROPERTY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.dataset.OrgEmpposition;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.dataset.QueryPositionEmp;
import org.gocom.components.coframe.org.groupdataset.OrgEmpgroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroupposi;
import org.gocom.components.coframe.org.util.IOrgConstants;
import org.gocom.components.coframe.org.util.OrgHelper;
import org.gocom.components.coframe.org.util.OrgTreeNodeHelper;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;

import com.eos.das.entity.DASManager;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.foundation.data.DataObjectUtil;
import com.primeton.cap.TenantManager;
import com.primeton.cap.party.Party;
import commonj.sdo.DataObject;

/**
 * 工作组树服务类
 *
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public class GroupTreeService extends CoframeDASDaoSupport implements IGroupTreeService{
	
	public final static String SPRING_BEAN_NAME = "GroupTreeService";
	private static final String EXPANDED = "expanded";

	private static final String IS_LEAF = "isLeaf";

	private IOrgGroupService groupService;
	
	private IOrgPositionService positionService;
	
	/**
	 * 查询工作组下的所有子工作组，参数orgid为空时返回顶级工作组
	 * @param groupid
	 * @return
	 */
	public OrgGroup[] querySubGroups(String groupid) {
		OrgGroup[] groups = groupService.querySubOrgGroups(groupid);
		
		// 设置NUI所需的属性
		for(OrgGroup group : groups) {
			group.setBoolean(IS_LEAF, false);
			group.setBoolean(EXPANDED, false);
		}
		
		return groups;
	}
	
	/**
	 * 查询工作组下的所有岗位
	 * @param groupid
	 * @return
	 */
	public OrgPosition[] queryPositionsOfGroup(String groupid) {
		OrgPosition[] positions = groupService.queryPositionsOfGroup(groupid);;
		
		// 设置NUI所需的属性
		if(positions!=null){
			for(OrgPosition position : positions) {
				position.setBoolean(IS_LEAF, false);
				position.setBoolean(EXPANDED, false);
			}
		}
		return positions;
	}

	/**
	 * 查询在工作组下且未分配到此工作组下级岗位的员工
	 * @param groupid
	 * @return
	 */
	public OrgEmployee[] queryEmployeesOfGroupNotInPosition(String groupid) {
		if(StringUtils.isBlank(groupid)) {
			return new OrgEmployee[0];
		}
		
		OrgEmployee[] emps = getDASTemplate().queryByNamedSql(
				OrgEmployee.class, "org.gocom.components.coframe.org.organization.select_groupemp", groupid);
		
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
	 * 复制工作组到另一个工作组中作为子工作组
	 * @param orgid
	 * @param targetOrgid
	 */
	public void copyGroupToGroup(String groupid, String targetOrgid) {
		OrgGroup targetGroup = getGroupById(targetOrgid);
		
		OrgGroup group = getGroupById(groupid);
		OrgHelper.expandGroupPropertyByParent(group, targetGroup);
		// 克隆一个新工作组
		DataObject newGroup = DataObjectUtil.convertDataObject(group, OrgGroup.QNAME, false);
		getDASTemplate().getPrimaryKey(newGroup);
		newGroup.set(GROUP_REF_PROPERTY, targetGroup);
		getDASTemplate().insertEntity(newGroup);
	}
	
	/**
	 * 移动工作组到另一个工作组中作为子工作组
	 * @param orgid
	 * @param targetOrgid
	 */
	public void moveGroupToGroup(String groupid, String targetGroupid) {
		OrgGroup targetGroup = getGroupById(targetGroupid);
		OrgGroup group = getGroupById(groupid);
		group.setOrgGroup(targetGroup);
		
		//级别在父机构的级别上增加1
		group.setGrouplevel(targetGroup.getGrouplevel()+1);
		//存在父机构:父机构顺序+本机构ID+".";
		group.setGroupseq(targetGroup.getGroupseq() + group.getGroupid()+".");
		getDASTemplate().updateEntity(group);
	}

	/**
	 * 通过工作组ID获取工作组对象
	 * @param orgid
	 */
	public OrgGroup getGroupById(String groupid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		dasCriteria.add(ExpressionHelper.eq(IOrgConstants.GROUPID_PROPERTY, groupid));
		OrgGroup[] groups = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, dasCriteria);
		if(groups != null && groups.length == 1) {
			return groups[0];
		}
		return null;
	}
	
	/**
	 * 复制员工到岗位
	 * @param empid
	 * @param targetPositionid
	 */
	public void copyEmpToPosition(String empid, String targetPositionid) {
		OrgEmpposition targetEmpposition = loadEmppositionWithPosition(empid, targetPositionid);
		
		// 当员工在目标岗位中不存在时,才做复制处理
		if(targetEmpposition == null) {
			// 保存员工岗位关系
			targetEmpposition = createEmpposition(empid, targetPositionid);
			getDASTemplate().insertEntity(targetEmpposition);
		}
	}
	
	public void copyEmpToGroup(String empid, String targetGroupid) {
		OrgEmpgroup targetEmpgroup = loadEmpgroupWithEmp(empid, targetGroupid);
		
		// 当员工在目标工作组中不存在时,才做复制处理
		if(targetEmpgroup == null) {
			targetEmpgroup = createEmpgroup(empid, targetGroupid);
			getDASTemplate().insertEntity(targetEmpgroup);
		}
	}
	
	/**
	 * 移动人员从当前工作组到另一个工作组
	 * @param fromOrgid
	 * @param parentOrgid
	 */
	public void moveEmpFromGroupToGroup(String empid, String fromGroupid, String targetGroupid) {
		OrgEmpgroup fromEmpgroup = loadEmpgroupWithEmp(empid, fromGroupid);
		OrgEmpgroup tartgetEmpgroup = loadEmpgroupWithEmp(empid, targetGroupid);
		
		// 当员工在目标工作组中不存在时
		if(fromEmpgroup != null && tartgetEmpgroup == null) {
			tartgetEmpgroup = createEmpgroup(empid, targetGroupid);
			getDASTemplate().insertEntity(tartgetEmpgroup);
		}
		
		getDASTemplate().deleteEntity(fromEmpgroup);
	}
	
	/**
	 * 移动人员从岗位到工作组
	 * @param empid
	 * @param fromPositionid
	 * @param targetOrgid
	 */
	public void moveEmpFromPositionToGroup(String empid, String fromPositionid, String targetGroupid) {
		OrgEmpposition fromEmpposition = loadEmppositionWithPosition(empid, fromPositionid);
		OrgEmpgroup tartgetEmpgroup = loadEmpgroupWithEmp(empid, targetGroupid);
		// 当员工在目标工作组中不存在时
		if(fromEmpposition != null && tartgetEmpgroup == null) {
			OrgEmpgroup empgroup=OrgEmpgroup.FACTORY.create();
			OrgEmployee employee=OrgEmployee.FACTORY.create();
			employee.setEmpid(new BigDecimal(empid));
			empgroup.setOrgEmployee(employee);
			empgroup.setGroupid(new BigDecimal(targetGroupid));
			empgroup.setTenantId(TenantManager.getCurrentTenantID());
			getDASTemplate().insertEntity(empgroup);
		}
		getDASTemplate().deleteEntity(fromEmpposition);
	}
	
	
	/**
	 * 移动人员从工作组到岗位
	 * @param empid
	 * @param positionid
	 * @param targetOrgid
	 */
	public void moveEmpFromGroupToPosition(String empid, String fromGroupid, String targetPositionid) {
		OrgEmpgroup fromEmpgroup = loadEmpgroupWithEmp(empid, fromGroupid);
		OrgEmpposition targetEmpposition = loadEmppositionWithPosition(empid, targetPositionid);
		// 当员工在当前工作组中存在,并且在目标岗位中不存在时,才做移动处理
		if(fromEmpgroup != null && targetEmpposition == null) {
			OrgGroupposi targetPosition = loadPositionWithGroup(targetPositionid);
			if(targetPosition != null) {
				OrgEmpgroup empgroup=loadEmpgroupWithEmp(empid,fromGroupid);
				getDASTemplate().deleteEntity(empgroup);
				targetEmpposition = createEmpposition(empid, targetPositionid);
				getDASTemplate().insertEntity(targetEmpposition);
			}
		}
	}

	/**
	 * 移动员工从指定岗位到目标岗位
	 * @param empid
	 * @param positionid
	 */
	public void moveEmpFromPositionToPosition(String empid, String positionid, String targetPositionid) {
		OrgEmpposition fromEmpposition = loadEmppositionWithPosition(empid, positionid);
		OrgEmpposition targetEmpposition = loadEmppositionWithPosition(empid, targetPositionid);
		
		// 当员工在当前岗位中存在,并且在目标岗位中不存在时,才做移动处理
		if(fromEmpposition != null && targetEmpposition == null) {
			targetEmpposition = createEmpposition(empid, targetPositionid);
			getDASTemplate().deleteEntity(fromEmpposition);
			getDASTemplate().insertEntity(targetEmpposition);
		}
	}
	
	/**
	 * 删除人员在工作组下的员工岗位关系
	 * @param empid
	 * @param orgid
	 */
//	private void deleteEmppositionOfGroup(String empid, String groupid) {
//		IDASCriteria dasCriteria = DASManager.createCriteria(OrgEmpposition.QNAME);
//		OrgGroupposi groupposi=loadGroupPosiWithPosition(groupid);
//		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
//		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY, groupposi.getOrgPosition()));
//		getDASTemplate().deleteByCriteriaEntity(dasCriteria);
//	}

	/**
	 * 加载员工与工作组的关联实体，并且级联加载员工实体
	 * @param empid
	 * @param orgid
	 * @return
	 */
	private OrgEmpgroup loadEmpgroupWithEmp(String empid, String groupid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgEmpgroup.QNAME);
		dasCriteria.add(ExpressionHelper.eq(GROUPID_PROPERTY, groupid));
		dasCriteria.addAssociation(EMP_REF_PROPERTY);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		OrgEmpgroup[] emporgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmpgroup.class, dasCriteria);
		if(emporgs != null && emporgs.length == 1){
			getDASTemplate().expandRelation(emporgs[0], EMP_REF_PROPERTY); // 加载关联的员工实体
			return emporgs[0];
		}
		return null;
	}
	
	/**
	 * 加载员工与岗位的关联实体，级联加载岗位实体以及岗位关联的工作组实体
	 * @param empid
	 * @param positionid
	 * @return
	 */
	private OrgEmpposition loadEmppositionWithPosition(String empid, String positionid) {
		IDASCriteria dasCriteria = DASManager.createCriteria(OrgEmpposition.QNAME);
		dasCriteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "." + POSITIONID_PROPERTY, positionid));
		dasCriteria.addAssociation(POSITION_REF_PROPERTY);
		OrgEmpposition[] emppositions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmpposition.class, dasCriteria);
		if(emppositions != null && emppositions.length == 1){
			getDASTemplate().expandRelation(emppositions[0], POSITION_REF_PROPERTY); // 加载关联的岗位实体
			return emppositions[0];
		}
		return null;
	}
	
	/**
	 * 加载岗位，级联加载父工作组
	 * @param positionid
	 * @return
	 */
//	private OrgGroupposi loadGroupPosiWithPosition(String groupid) {
//		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroupposi.QNAME);
//		dasCriteria.add(ExpressionHelper.eq(GROUP_REF_PROPERTY+"."+GROUPID_PROPERTY, groupid));
//		dasCriteria.addAssociation(POSITION_REF_PROPERTY);
//		OrgGroupposi[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroupposi.class, dasCriteria);
//		if(positions != null && positions.length == 1){
//			return positions[0];
//		}
//		return null;
//	}
	
	/**
	 * 加载岗位，级联加载父工作组
	 * @param positionid
	 * @return
	 */
	private OrgGroupposi loadPositionWithGroup(String positionid) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(OrgGroupposi.QNAME);
		dasCriteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY+"."+POSITIONID_PROPERTY, positionid));
		dasCriteria.addAssociation(GROUP_REF_PROPERTY);
		OrgGroupposi[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroupposi.class, dasCriteria);
		if(positions != null && positions.length == 1){
			return positions[0];
		}
		return null;
	}
	
	/**
	 * 创建员工与工作组的关联实体
	 * @param empid
	 * @param orgid
	 * @return
	 */
	private OrgEmpgroup createEmpgroup(String empid, String groupid) {
		OrgEmployee emp = OrgEmployee.FACTORY.create();
		emp.setEmpid(new BigDecimal(empid));
		
		OrgEmpgroup empgroup = OrgEmpgroup.FACTORY.create();
		empgroup.setGroupid(new BigDecimal(groupid));
		empgroup.setTenantId(TenantManager.getCurrentTenantID());
		empgroup.setOrgEmployee(emp);
		return empgroup;
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

	public void setGroupService(IOrgGroupService groupService) {
		this.groupService = groupService;
	}

	public void setPositionService(IOrgPositionService positionService) {
		this.positionService = positionService;
	}
	
	public static DataObject[] buildGroupTreeNodes(OrgGroup[] subgroups){
		List<Party> partyList=new ArrayList<Party>();
		for(OrgGroup group:subgroups){
			Party party=new Party();
			party.setCode(group.getGroupname());
			party.setId(group.getGroupid().toString());
			party.setName(group.getGroupname());
			party.setPartyTypeID("group");
			party.setTenantID(group.getTenantId());
			partyList.add(party);
		}
		 return OrgTreeNodeHelper.buildGroupTreeNodes(partyList);
		}

}