/*
 * Copyright 2013 Primeton.
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

import java.util.List;

import org.gocom.components.coframe.org.groupdataset.OrgGroup;


/**
 * 机构授权服务接口
 * 
 * @author liuzn (mailto:liuzn@primeton.com)
 */
public interface IGroupAuthService {

	/**
	 * 机构数组转机构列表
	 * 
	 * @param orgEmployees
	 *            机构数组
	 * @return 机构列表
	 */
	List<OrgGroup> arrayToList(OrgGroup[] orgs);

	/**
	 * 匹配机构的授权信息
	 * 
	 * @param orgEmployeeList
	 *            机构列表
	 * @param roleId
	 *            授权的角色
	 * @return 包含授权信息的机构列表
	 */
	List<OrgGroup> getOrgAuth(List<OrgGroup> orgList,
			String roleId);
	
	/**
	 * 保存角色的可管理工作组授权信息
	 * 
	 * @param groups
	 *            可管理工作组
	 * @param roleId
	 *            授权的角色
	 * @return 是否成功保存
	 */
	boolean saveAuthGroupBatch(String[] groups, String roleId);
	
	/**
	 * 获取角色的可管理工作组
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 可管理工作组
	 */
	OrgGroup[] getManageGroupbyRoleId(String roleId);

}
