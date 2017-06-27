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
package org.gocom.components.coframe.rights.gradeauth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.tools.IConstants;
import org.gocom.components.coframe.tools.superadmin.SuperAdminService;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.IUserObject;
import com.primeton.cap.party.Party;
import com.primeton.cap.party.manager.PartyRuntimeManager;

/**
 * 分级授权的类，获取可管理的机构列表和可管理的角色列表
 * 
 * @author caozw@primeton.com
 * 
 */
public class GradeAuthService {
	
	public static final String SPRING_BEAN_NAME = "GradeAuthBean";
	
	/**
	 * 分级授权的方法，获取可管理的机构列表，从员工的参与者中获取可授权机构列表
	 * 
	 * @return
	 */
	public List<Party> getManagedOrgList() {
		List<Party> partyList = new ArrayList<Party>();
		
		if (SuperAdminService.currUserIsSupserAdmin()) {
			return PartyRuntimeManager.getInstance().getRootPartyList(
					IConstants.ORG_PARTY_TYPE_ID);
		}
		
		if(!judgeIsEmp()) {
			return partyList;
		}
		
		String userId = getUserId();
		
		Party empParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				userId, IConstants.EMP_PARTY_TYPE_ID);
		String orgIdAndNames = empParty.getExtAttribute(IConstants.MANAGED_ORGS);
		if (orgIdAndNames != null) {
			String[] orgIdAndNameArray = StringUtils.split(orgIdAndNames, ",");
			if (orgIdAndNameArray != null) {
				for (String orgIdAndName : orgIdAndNameArray) {
					Party orgParty = PartyRuntimeManager.getInstance()
							.getPartyByPartyID(StringUtils.split(orgIdAndName, ":")[0],
									IConstants.ORG_PARTY_TYPE_ID);
					if(orgParty != null) {
						partyList.add(orgParty);
					}
				}
			}
		}
		return partyList;
	}

	
	
	private String getUserId() {
		IMUODataContext muoContext = DataContextManager.current()
				.getMUODataContext();
		IUserObject userObject = muoContext.getUserObject();
		if (userObject != null) {
			return userObject.getUserId();
		}
		return null;
	}
	
	private boolean judgeIsEmp() {
		IMUODataContext muoContext = DataContextManager.current()
				.getMUODataContext();
		IUserObject userObject = muoContext.getUserObject();
		if (userObject != null) {
			if(userObject.getUserOrgId() == null || "".equals(userObject.getUserOrgId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 分级授权的方法，获取可管理的角色列表，从员工的参与者中获取可授权角色列表
	 * 
	 * @return
	 */
	public List<Party> getManagedRoleList() {
		List<Party> partyList = new ArrayList<Party>();
		if (SuperAdminService.currUserIsSupserAdmin()) {
			return PartyRuntimeManager.getInstance().getAllPartyList(
					IConstants.ROLE_PARTY_TYPE_ID);
		}
		if(!judgeIsEmp()) {
			return partyList;
		}
		String userId = getUserId();
		Party empParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				userId, IConstants.EMP_PARTY_TYPE_ID);
		String roleIdAndNames = empParty.getExtAttribute(IConstants.MANAGED_ROLES);
		if (roleIdAndNames != null) {
			String[] roleIdAndNameArray = StringUtils.split(roleIdAndNames, ",");
			if (roleIdAndNameArray != null) {
				for (String roleIdAndName : roleIdAndNameArray) {
					Party roleParty = PartyRuntimeManager.getInstance()
							.getPartyByPartyID(StringUtils.split(roleIdAndName, ":")[0],
									IConstants.ROLE_PARTY_TYPE_ID);
					if(roleParty != null) {
						partyList.add(roleParty);
					}
				}
			}
		}
		return partyList;
	}
}
