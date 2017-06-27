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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gocom.components.coframe.org.dataset.OrgOrganization;
import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.tools.IConstants;

import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.spring.BeanFactory;
import com.eos.system.logging.Logger;
import com.primeton.cap.TenantManager;
import com.primeton.cap.auth.AuthResource;
import com.primeton.cap.auth.manager.AuthRuntimeManager;
import com.primeton.cap.party.Party;
import com.primeton.cap.party.manager.PartyRuntimeManager;

/**
 * 工作组授权服务接口的实现类
 * 
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public class GroupAuthService implements IGroupAuthService {
	private static Logger log = TraceLoggerFactory.getLogger(GroupAuthService.class);
	public final static String SPRING_BEAN_NAME = "groupAuthService";
	public List<OrgGroup> arrayToList(OrgGroup[] orgs) {
		return Arrays.asList(orgs);
	}

	public List<OrgGroup> getOrgAuth(List<OrgGroup> orgList,
			String roleId) {
		Party roleParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				roleId, IConstants.ROLE_PARTY_TYPE_ID);
		String[] partyTypes = { IConstants.GROUP_PARTY_TYPE_ID };
		Map<String, List<Party>> partyMap = PartyRuntimeManager.getInstance()
				.getDirectAssociateChildPartyList(roleId,
						IConstants.ROLE_PARTY_TYPE_ID, partyTypes);
		List<Party> partAuthList = partyMap.get(IConstants.GROUP_PARTY_TYPE_ID);
		matchPartyAuth(partAuthList, orgList);
		return orgList;
	}

	/**
	 * 匹配授权列表和工作组列表，向工作组列表中增加授权信息
	 * 
	 * @param partAuthList
	 *            有该角色的授权列表
	 * @param orgList
	 *            工作组列表
	 */
	private void matchPartyAuth(List<Party> partAuthList,
			List<OrgGroup> orgList) {
		Iterator<Party> partyItr = partAuthList.iterator();
		while (partyItr.hasNext()) {
			Party authPart = partyItr.next();
			Iterator<OrgGroup> orgItr = orgList.iterator();
			while (orgItr.hasNext()) {
				OrgGroup org = orgItr.next();
				if (authPart.getId().equals(org.getGroupid().toString())) {
					org.set("auth", "1");
				}
			}
		}
	}

	public boolean saveAuthGroupBatch(String[] groups, String roleId) {
		Party party = getParty(roleId);
		AuthRuntimeManager manager = AuthRuntimeManager.getInstance();
		List<AuthResource> authList = new ArrayList<AuthResource>();
		for(int i = 0; i < groups.length; i++){
			String resId = groups[i];
			String resType = "group";
			AuthResource resource = new AuthResource();
			resource.setResourceID(resId);
			resource.setResourceType(resType);
			resource.setState("1");
			authList.add(resource);
		}
		ITransactionManager txManager = TransactionManagerFactory
				.getTransactionManager();
		boolean result = true;
		txManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		try{
			result = manager.delAuthResBatch(party, manager.getAuthResListByRole(party, "group"), 0);
			if (result) {
				result = manager.addOrUpdateAuthResBatch(party, authList);
			}
		}catch (Throwable t) {
			log.error("Save AuthGroup failure, please do the operation again or contact the sysadmin.", t);
			result = false;
		}finally{
			if(result){
				txManager.commit();
			}else{
				txManager.rollback();
				manager.delRoleAuthCache(party);
			}
		}
		return result;
	}

	public OrgGroup[] getManageGroupbyRoleId(String roleId) {
		GroupTreeService groupTreeService=BeanFactory.newInstance().getBean(GroupTreeService.SPRING_BEAN_NAME);
		Party party = getParty(roleId);
		AuthRuntimeManager manager = AuthRuntimeManager.getInstance();
		List<AuthResource> resourceList=manager.getAuthResListByRole(party, "group");
		int length=resourceList.size();
		OrgGroup[] result=new OrgGroup[length];
		for(int i=0;i<length;i++){
			result[i]=groupTreeService.getGroupById(resourceList.get(i).getResourceID());
		}
		return result;
	}
	
	private static Party getParty(String roleId){
		return new Party(IConstants.ROLE_PARTY_TYPE_ID, roleId, null, null, TenantManager.getCurrentTenantID());
	}

}
