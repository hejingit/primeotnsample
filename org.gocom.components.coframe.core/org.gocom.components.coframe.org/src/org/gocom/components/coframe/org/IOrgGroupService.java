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

import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.dataset.OrgPosition;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.util.OrgResponse;

import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;

/**
 * 组织工作组服务类
 *
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public interface IOrgGroupService{

	/**
	 * 添加工作组
	 * @param orgOrganization
	 * @return 添加工作组状态对象
	 */
	OrgResponse addOrgGroup(OrgGroup orgGroup);

	/**
	 *删除工作组
	 * @param orgGroups OrgGroup[]
	 */
	void deleteOrgGroup(OrgGroup[] orgGroups);
	
	/**
	 * 根据id删除工作组
	 * @param id
	 */
	void deleteOrgGroup(String id);

	/**
	 *
	 * @param OrgGroup OrgGroup
	 */
	void getOrgGroup(OrgGroup orgGroup);

	/**
	 *
	 * @param criteria CriteriaType
	 * @param page PageCond
	 * @return OrgOrganization[]
	 */
	OrgGroup[] queryOrgGroups(CriteriaType criteriaType,
			PageCond pageCond);

	/**
	 * 修改工作组对象
	 * @param orgOrganization
	 * @return 修改工作组状态
	 */
	OrgResponse updateOrgGroup(OrgGroup orgGroup);
	
	/**
	 * @param criteria
	 * @return
	 */
	int countOrgGroups(CriteriaType criteria);
	
	/**
	 * 查询工作组下的所有子工作组，参数groupid为空时返回顶级工作组
	 * @param orgid
	 * @return
	 */
	OrgGroup[] querySubOrgGroups(String groupid);
	
	/**
	 * 查询工作组下的所有岗位
	 * @param orgid
	 * @return
	 */
	OrgPosition[] queryPositionsOfGroup(String groupid);
	/**
	 * 查询工作组下的所有员工
	 * @param orgid
	 * @return
	 */
	OrgEmployee[] queryEmployeesOfGroup(String groupid);
	
	/**
	 * 获取子工作组同时获取父工作组信息
	 * @param child
	 */
	public OrgGroup getOrgGroupnWithParent(OrgGroup child);
}