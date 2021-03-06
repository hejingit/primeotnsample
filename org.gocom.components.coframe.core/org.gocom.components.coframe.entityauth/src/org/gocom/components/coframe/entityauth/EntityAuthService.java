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
package org.gocom.components.coframe.entityauth;

import java.util.ArrayList;
import java.util.List;

import org.gocom.components.coframe.entityauth.pojo.ConstantPool;

import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;
import com.primeton.cap.auth.AuthResource;
import com.primeton.cap.auth.IAuthManagerService;
import com.primeton.cap.auth.manager.AuthRuntimeManager;
import com.primeton.cap.party.Party;

/**
 * Entity授权服务实现类
 * 
 * @author lijt (mailto:lijt@primeton.com)
 */
public class EntityAuthService implements IEntityAuthService {
	private static Logger log = TraceLoggerFactory.getLogger(EntityAuthService.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gocom.components.coframe.entityauth.IEntityAuthService#authBatch(com.primeton.cap.party.Party,
	 *      java.lang.String[], java.lang.String[])
	 */
	public synchronized boolean authBatch(Party party, String[] authRuleIds,
			String[] delRuleIds) {
		List<AuthResource> authResList = null;
		List<AuthResource> delResList = null;
		if (authRuleIds != null) {
			authResList = new ArrayList<AuthResource>();
			for (String authRuleId : authRuleIds) {
				AuthResource authResource = new AuthResource();
				authResource.setResourceID(authRuleId);
				authResource.setResourceType(ConstantPool.RES_TYPE);
				authResource.setState("1");
				authResList.add(authResource);
			}
		}
		if (delRuleIds != null) {
			delResList = new ArrayList<AuthResource>();
			for (String delRuleId : delRuleIds) {
				AuthResource authResource = new AuthResource();
				authResource.setResourceID(delRuleId);
				authResource.setResourceType(ConstantPool.RES_TYPE);
				authResource.setState("0");
				delResList.add(authResource);
			}
		}
		ITransactionManager txManager = TransactionManagerFactory
				.getTransactionManager();
		txManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		boolean flag=true;
		try{
			flag= AuthRuntimeManager.getInstance().delAuthResBatch(party,
					delResList, IAuthManagerService.DEL_MODE_SINGLE);
			if (flag) {
				flag=AuthRuntimeManager.getInstance().addOrUpdateAuthResBatch(party,
						authResList);
			}
		}catch(Throwable t){
			log.error("Auth failure, please do the operation again or contact the sysadmin.", t);
			flag=false;
		}finally{
			if(flag){
				txManager.commit();
			}else{
				txManager.rollback();
				AuthRuntimeManager.getInstance().delRoleAuthCache(party);
			}
		}
		return flag;
	}

}
