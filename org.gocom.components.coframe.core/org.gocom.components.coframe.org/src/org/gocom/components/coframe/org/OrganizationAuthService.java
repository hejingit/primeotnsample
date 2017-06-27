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
 * 机构授权服务接口的实现类
 * 
 * @author liuzn (mailto:liuzn@primeton.com)
 */
public class OrganizationAuthService implements IOrganizationAuthService {
	public static final String SPRING_BEAN_NAME = "orgAuthService";
	private static Logger log = TraceLoggerFactory.getLogger(OrganizationAuthService.class);

	public List<OrgOrganization> arrayToList(OrgOrganization[] orgs) {
		return Arrays.asList(orgs);
	}

	public List<OrgOrganization> getOrgAuth(List<OrgOrganization> orgList,
			String roleId) {
		Party roleParty = PartyRuntimeManager.getInstance().getPartyByPartyID(
				roleId, IConstants.ROLE_PARTY_TYPE_ID);
		String[] partyTypes = { IConstants.ORG_PARTY_TYPE_ID };
		Map<String, List<Party>> partyMap = PartyRuntimeManager.getInstance()
				.getDirectAssociateChildPartyList(roleId,
						IConstants.ROLE_PARTY_TYPE_ID, partyTypes);
		List<Party> partAuthList = partyMap.get(IConstants.ORG_PARTY_TYPE_ID);
		matchPartyAuth(partAuthList, orgList);
		return orgList;
	}

	/**
	 * 匹配授权列表和机构列表，向机构列表中增加授权信息
	 * 
	 * @param partAuthList
	 *            有该角色的授权列表
	 * @param orgList
	 *            机构列表
	 */
	private void matchPartyAuth(List<Party> partAuthList,
			List<OrgOrganization> orgList) {
		Iterator<Party> partyItr = partAuthList.iterator();
		while (partyItr.hasNext()) {
			Party authPart = partyItr.next();
			Iterator<OrgOrganization> orgItr = orgList.iterator();
			while (orgItr.hasNext()) {
				OrgOrganization org = orgItr.next();
				if (authPart.getId().equals(org.getOrgid().toString())) {
					org.set("auth", "1");
				}
			}
		}
	}

	public boolean saveAuthOrganizationBatch(String[] orgs, String roleId) {
		Party party = getParty(roleId);
		AuthRuntimeManager manager = AuthRuntimeManager.getInstance();
		List<AuthResource> authList = new ArrayList<AuthResource>();
		for(int i = 0; i < orgs.length; i++){
			String resId = orgs[i];
			String resType = "organization";
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
			result = manager.delAuthResBatch(party, manager.getAuthResListByRole(party, "organization"), 0);
			if (result) {
				result = manager.addOrUpdateAuthResBatch(party, authList);
			}
		}catch (Throwable t) {
			log.error("Save AuthOrganization failure, please do the operation again or contact the sysadmin.", t);
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
	
	private static Party getParty(String roleId){
		return new Party(IConstants.ROLE_PARTY_TYPE_ID, roleId, null, null, TenantManager.getCurrentTenantID());
	}

	public OrgOrganization[] getManageOrgbyRoleId(String roleId) {
		OrgOrganizationService organizationService=BeanFactory.newInstance().getBean(OrgOrganizationService.SPRING_BEAN_NAME);
		Party party = getParty(roleId);
		AuthRuntimeManager manager = AuthRuntimeManager.getInstance();
		List<AuthResource> resourceList=manager.getAuthResListByRole(party, "organization");
		int length=resourceList.size();
		String[] ids=new String[length];
		for(int i=0;i<length;i++){
			ids[i]=resourceList.get(i).getResourceID();
		}
		OrgOrganization[] result=organizationService.queryOrgOrganizationsByIds(ids);
		return result;
	}

}
