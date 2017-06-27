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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gocom.components.coframe.org.dataset.OrgEmployee;
import org.gocom.components.coframe.org.util.OrgPartyAdaptUtil;

import com.primeton.cap.party.Party;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
/**
 * 机构角色相关的员工信息查询
 *
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public class DefaultOrgRoleManager extends CoframeDASDaoSupport {
	/**	springBean名称标识 */
	public static final String SPRING_BEAN_NAME = "DefaultOrgRoleManagerBean";
	
	/**
	 * 根据orgid,roleid查询emp
	 * @param orgRole是用逗号隔开的orgid和roleid（如："102,203"）
	 * @param tenantID 租户ID
	 * @return Party对象信息列表
	 */
	public List<Party> getEmpsByOrgRole(String orgRole, String tenantID) {
		Map<String,String> params=new HashMap<String, String>();
		params.put("roleid", orgRole.split(",")[0]);
		params.put("orgid", orgRole.split(",")[1]);
		params.put("tenantid", tenantID);
		OrgEmployee[] emps=getDASTemplate().queryByNamedSql(OrgEmployee.class, "org.gocom.components.coframe.org.orgRoleEmp.select_orgRoleEmp", params);
		return OrgPartyAdaptUtil.adapt(emps);
	}
}

