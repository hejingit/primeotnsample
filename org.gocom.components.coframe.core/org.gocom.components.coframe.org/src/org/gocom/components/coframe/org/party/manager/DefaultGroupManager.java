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
package org.gocom.components.coframe.org.party.manager;

import static org.gocom.components.coframe.org.util.IOrgConstants.EMPID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.EMP_REF_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.GROUPID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.GROUP_REF_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.POSITIONID_PROPERTY;
import static org.gocom.components.coframe.org.util.IOrgConstants.POSITION_REF_PROPERTY;

import java.util.ArrayList;
import java.util.List;

import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.groupdataset.OrgEmpgroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.groupdataset.OrgGroupposi;
import org.gocom.components.coframe.tools.IConstants;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;

/**
 * 工作组信息管理，提供对工作组数据实体的数据库操作（持久化）
 *
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public class DefaultGroupManager extends CoframeDASDaoSupport {

	/**	springBean名称标识 */
	public static final String SPRING_BEAN_NAME = "DefaultGroupManagerBean";
	
	/**
	 * 根据租户ID查询所有工作组信息列表
	 * @param tenantID 租户ID
	 * @return 工作组对象信息列表
	 */
	public OrgGroup[] getAllGroups(String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, criteria);
	}

	/**
	 * 根据工作组ID和租户ID查询工作组信息
	 * 
	 * @param orgid 工作组ID
	 * @param tenantID 租户ID
	 * @return 工作组对象信息，未找到返回null
	 */
	public OrgGroup getGroupById(String groupid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(GROUPID_PROPERTY, groupid));
		OrgGroup[] groupArray = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, criteria);
		if (groupArray != null && groupArray.length == 1) {
			return groupArray[0];
		}
		return null;
	}

	/**
	 * 根据租户ID查询组织结构根对象信息列表
	 * 
	 * @param tenantID 租户ID
	 * @return 组织结构对象信息列表
	 */
	public OrgGroup[] getRootGroups(String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.isNull(GROUP_REF_PROPERTY));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, criteria);
	}

	/**
	 * 根据父工作组ID和租户ID获取子工作组列表
	 * @param parentOrgid
	 * @param tenantID
	 * @return
	 */
	public OrgGroup[] getSubGroups(String parentGroupid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(GROUP_REF_PROPERTY + "." + GROUPID_PROPERTY, parentGroupid));
		return getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, criteria);
	}

	/**
	 * 根据工作组ID和租户ID获取父工作组
	 * @param orgid
	 * @param tenantID
	 * @return
	 */
	public OrgGroup getParentGroup(String groupid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgGroup.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(GROUPID_PROPERTY, groupid));
		criteria.addAssociation(GROUP_REF_PROPERTY);
		OrgGroup[] orgs = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroup.class, criteria);
		if (orgs != null && orgs.length == 1) {
			OrgGroup org = orgs[0];
			return org.getOrgGroup();
		}
		return null;
	}

	/**
	 * 根据工作组ID和租户ID获取员工列表
	 * @param orgid
	 * @param tenantID
	 * @return
	 */
	public OrgEmployee[] getEmpsByGroup(String groupid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgEmpgroup.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(GROUPID_PROPERTY, groupid));
		criteria.addAssociation(EMP_REF_PROPERTY);
		criteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + IConstants.TENANT_PROPERTY, tenantID));
		criteria.asc(EMP_REF_PROPERTY+".sortno");
		OrgEmpgroup[] empgroups = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmpgroup.class, criteria);
		getDASTemplate().expandEntitiesRelation(empgroups, EMP_REF_PROPERTY); // 虽然用了addAssociation()，此处也必须展开关联实体，否则报ClassCastException

		// 将员工工作组关系列表组装为员工列表
		List<OrgEmployee> emps = new ArrayList<OrgEmployee>(empgroups.length);
		for(OrgEmpgroup empgroup : empgroups) {
			emps.add(empgroup.getOrgEmployee());
		}
		return emps.toArray(new OrgEmployee[emps.size()]);
	}

	/**
	 * 通过员工ID和租户ID获取员工所在父工作组列表
	 * @param empid
	 * @param tenantID
	 * @return
	 */
	public OrgGroup[] getParentGroupsByEmp(String empid, String tenantID) {
		// 先查出员工工作组关系
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgEmpgroup.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(EMP_REF_PROPERTY + "." + EMPID_PROPERTY, empid));
		OrgEmpgroup[] empgroups = getDASTemplate().queryEntitiesByCriteriaEntity(OrgEmpgroup.class, criteria);
		
		// 将员工工作组关系列表组装为工作组列表
		List<OrgGroup> groups = new ArrayList<OrgGroup>(empgroups.length);
		for(OrgEmpgroup empgroup : empgroups) {
			OrgGroup group = OrgGroup.FACTORY.create();
			group.setGroupid(empgroup.getGroupid());
			getDASTemplate().expandEntity(group);
			groups.add(group);
		}
		return groups.toArray(new OrgGroup[groups.size()]);
	}

	/**
	 * 根据工作组ID和租户ID获取工作组下的岗位
	 * @param orgid
	 * @param tenantID
	 * @return
	 */
	public OrgPosition[] getPositionsByGroup(String groupid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgGroupposi.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		
		//added by yangyong
//		criteria.add(ExpressionHelper.eq(GROUPID_PROPERTY, groupid));//这个属性没有，所以任何工作组下面会出现所有的岗位
		criteria.addAssociation(GROUP_REF_PROPERTY);
		criteria.add(ExpressionHelper.eq(GROUP_REF_PROPERTY + "." + GROUPID_PROPERTY, groupid));
		//added end
		
		criteria.addAssociation(POSITION_REF_PROPERTY);
		criteria.add(ExpressionHelper.eq(POSITION_REF_PROPERTY + "." + IConstants.TENANT_PROPERTY, tenantID));
		OrgGroupposi[] groupposis = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroupposi.class, criteria);
		getDASTemplate().expandEntitiesRelation(groupposis, POSITION_REF_PROPERTY); // 虽然用了addAssociation()，此处也必须展开关联实体，否则报ClassCastException

		// 将员工工作组关系列表组装为员工列表
		List<OrgPosition> positions = new ArrayList<OrgPosition>(groupposis.length);
		for(OrgGroupposi empgroup : groupposis) {
			positions.add(empgroup.getOrgPosition());
		}
		return positions.toArray(new OrgPosition[positions.size()]);
		
	}

	/**
	 * 根据岗位ID和租户ID获取岗位所在的父工作组
	 * @param positionid
	 * @param tenantID
	 * @return
	 */
	public OrgGroup getParentGroupByPosition(String positionid, String tenantID) {
		IDASCriteria criteria = getDASTemplate().createCriteria(OrgGroupposi.QNAME);
		criteria.add(ExpressionHelper.eq(IConstants.TENANT_PROPERTY, tenantID));
		criteria.add(ExpressionHelper.eq(POSITIONID_PROPERTY, positionid));
		criteria.addAssociation(POSITION_REF_PROPERTY);
		OrgGroupposi[] positions = getDASTemplate().queryEntitiesByCriteriaEntity(OrgGroupposi.class, criteria);
		if(positions != null && positions.length == 1) {
			return positions[0].getOrgGroup();
		}
		return null;
	}
	
}
