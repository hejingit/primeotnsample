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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.org.dataset.OrgPartyDataObject;
import org.gocom.components.coframe.rights.gradeauth.GradeAuthService;
import org.gocom.components.coframe.tools.IConstants;
import org.gocom.components.coframe.tools.superadmin.SuperAdminService;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.IUserObject;
import com.eos.spring.BeanFactory;
import com.primeton.cap.auth.manager.AuthRuntimeManager;
import com.primeton.cap.party.Party;
import com.primeton.cap.party.manager.PartyRuntimeManager;
import commonj.sdo.DataObject;

/**
 * 分级授权机构服务类
 * 
 * @author yangzhou (mailto:yangzhou@primeton.com)
 */
public class GradeAuthOrgService {

	/**
	 * 获取可管理机构列表
	 * @return
	 */
	public List<Party> getManagedOrgList() {
		return getGradeAuthService().getManagedOrgList();
	}
	
	
	/**
	 * 获取可管理工作组列表
	 * @return
	 */
	public List<Party> getManagedGroupList() {
		List<Party> partyList = new ArrayList<Party>();

		if (SuperAdminService.currUserIsSupserAdmin()) {
			return PartyRuntimeManager.getInstance().getRootPartyList(
					IConstants.GROUP_PARTY_TYPE_ID);
		}

		if (!judgeIsEmp()) {
			return partyList;
		}
		String userId = getUserId();

		Party empParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				userId, IConstants.EMP_PARTY_TYPE_ID);
		partyList = AuthRuntimeManager.getInstance()
				.getPartyListByTypeWithRole(empParty, "group");
		OrgGroupService groupService=BeanFactory.newInstance().getBean(OrgGroupService.SPRING_BEAN_NAME);
		List<Party> deleteList=new ArrayList<Party>();
		Set set=new HashSet();
		for(Party group:partyList){
			set.add(group.getId());
		}
		for(Party group:partyList){
			DataObject parent=groupService.getOrgGroupnParent(group.getId());
			if(parent!=null){
				while(parent!=null){
					String parentid=parent.getString("groupid");
					if(set.contains(parentid)){
						deleteList.add(group);
					}
					parent=groupService.getOrgGroupnParent(parentid);
				}
			}else{
				continue;
			}
		}
		partyList.removeAll(deleteList);	
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
	
	
	public List<Party> getAuthorizedRoles(String partyId, String partyType) {
		List<Party> empManagedRoleList = getEmpManagedRoleList();
		if(empManagedRoleList == null) return new ArrayList<Party>();
		
		// 可管理角色列表
		List<Party> managedRolePartyList = getManagedRoleList(partyId, partyType);
		for(Party roleParty : empManagedRoleList) {
			roleParty.putExtAttribute(IConstants.IS_MANAGED,
					managedRolePartyList.contains(roleParty) ? "true" : "false");
		}
		
		return empManagedRoleList;
	}
	
	/**
	 * 获取当前用户对应员工的可管理角色列表
	 * @return
	 */
	public List<Party> getEmpManagedRoleList() {
		return getGradeAuthService().getManagedRoleList();
	}
	
	/**
	 * 获取当前用户对应员工的可管理角色列表
	 * @param empid
	 * @return
	 */
	public List<Party> getEmpManagedRoleList(String empid) {
		return getManagedRoleList(empid, IConstants.EMP_PARTY_TYPE_ID);
	}
	
	/**
	 * 根据partyId和partyTypeId获取可管理角色列表
	 * @param partyId
	 * @param partyTypeId
	 * @return
	 */
	public List<Party> getManagedRoleList(String partyId, String partyTypeId) {
		List<Party> results = new ArrayList<Party>();
		
		Party empParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				partyId, partyTypeId);
		if(empParty != null) {
			String roleIdAndNames = empParty.getExtAttribute(IConstants.MANAGED_ROLES);
			if (roleIdAndNames != null) {
				String[] roleIdAndNameArray = StringUtils.split(roleIdAndNames, ",");
				if (roleIdAndNameArray != null) {
					for (String roleIdAndName : roleIdAndNameArray) {
						if (roleIdAndName != null) {
							Party roleParty = PartyRuntimeManager.getInstance()
							.getPartyByPartyID(StringUtils.split(roleIdAndName, ":")[0],
									IConstants.ROLE_PARTY_TYPE_ID);
							if(roleParty != null) {
								results.add(roleParty);
							}
						}
					}
				}
			}
		}
		
		return results;
	}
	
	public List<OrgPartyDataObject> convertToDataObjectList(List<Party> partyList) {
		if(partyList == null || partyList.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<OrgPartyDataObject> partyDataObjectList = new ArrayList<OrgPartyDataObject>(partyList.size());
		for(Party party : partyList) {
			partyDataObjectList.add(convertToDataObject(party));
		}
		return partyDataObjectList;
	}
	
	public OrgPartyDataObject convertToDataObject(Party party) {
		OrgPartyDataObject partyDataObject = OrgPartyDataObject.FACTORY.create();
		
		if(party == null) return partyDataObject;
		
		partyDataObject.setId(party.getId());
		partyDataObject.setCode(party.getCode());
		partyDataObject.setName(party.getName());
		partyDataObject.setTenantID(party.getTenantID());
		partyDataObject.set(IConstants.IS_MANAGED, party.getExtAttribute(IConstants.IS_MANAGED));
		
		return partyDataObject;
	}

	// GradeAuthBean在另一个构件包，所以直接用BeanFactory获取
	private GradeAuthService getGradeAuthService() {
		return BeanFactory.newInstance().getBean(GradeAuthService.SPRING_BEAN_NAME);
	}
}
