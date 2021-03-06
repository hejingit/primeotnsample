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
package org.gocom.components.coframe.tools;

/**
 * TODO 此处填写 class 信息
 * 
 * @author guwei (mailto:guwei@primeton.com)
 */
public interface IConstants {

	// 所有实体中的租户属性
	String TENANT_PROPERTY = "tenantId";

	String ORG_ISLEAF_YES = "0";

	String ORG_ISLEAF_NOT = "1";

	// 用户PartyType标识
	String USER_PARTY_TYPE_ID = "user";
	//	 用户USERID标识
	String USER_ID = "userid";
	// 机构PartyType标识
	String ORG_PARTY_TYPE_ID = "org";

//	 机构PartyType标识
	String GROUP_PARTY_TYPE_ID = "group";
	
	String ROLE_PARTY_TYPE_ID = "role";

	String EMP_PARTY_TYPE_ID = "emp";
	
	String POSITION_PARTY_TYPE_ID = "position";
	
	String ORG_SEQ = "ORG_SEQ";
	
	String PARENT_ORG_IDS = "parentOrgIds";
	
	//用户的全部机构列表
	String ORG_LIST="orglist";

	String ORG_ROLE_PARTY_TYPE_ID = "orgRole";

	String EMAIL = "email";
	
	//个人邮箱
	String PEMAIL = "pemail";

	//企业邮箱
	String OEMAIL = "oemail";
	
	//用户的手机号码
	String MOBILE="mobile";
	
	String ROLE_LIST = "roleList";
	
	// 模块ResourceType标识
	String MODULE_RES_TYPE_ID = "module";
	
	String DEFAULT_PASSWORD = "000000";
	
	// 可管理机构列表
	String MANAGED_ORGS = "MANAGED_ORGS";
	
//	 可管理机构列表
	String MANAGED_GROUPS = "MANAGED_GROUPS";
	
	// 可管理角色列表
	String MANAGED_ROLES = "MANAGED_ROLES";

	// 是否可管理
	String IS_MANAGED = "isManaged";
	
	//菜单风格
	String MENU_TYPE="menutype";
	
}