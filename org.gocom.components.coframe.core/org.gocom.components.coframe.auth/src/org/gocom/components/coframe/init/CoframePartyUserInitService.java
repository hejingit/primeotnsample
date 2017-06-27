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
package org.gocom.components.coframe.init;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.tools.IConstants;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.ISessionMap;
import com.eos.data.datacontext.IUserObject;
import com.eos.data.datacontext.UserObject;
import com.eos.spring.BeanFactory;
import com.primeton.cap.ISystemConstants;
import com.primeton.cap.party.impl.DefaultPartyUserInitService;
import commonj.sdo.DataObject;

/**
 * 用户初始化实现类
 * 
 * @author caozw (mailto:caozw@primeton.com)
 */
public class CoframePartyUserInitService extends DefaultPartyUserInitService {
	 
	public IUserObject initUserObject(String userId, String tenantId) {
		UserObject userObject = null;		
		ISessionMap sessionMap = DataContextManager.current().getSessionCtx();		
		if (sessionMap != null) {
			userObject = (UserObject)sessionMap.getUserObject();
		}
		if (userObject == null) {
			IMUODataContext muo = DataContextManager.current().getMUODataContext();
			if (muo != null) {
				userObject = (UserObject)muo.getUserObject();
			}
		}
		if (userObject == null) {
			userObject = new UserObject();
		}
		
		BeanFactory beanFactory = BeanFactory.newInstance();
		InitUserObjectService bean = beanFactory.getBean(InitUserObjectService.SPRING_BEAN_NAME);
		
		//取用户基本信息，考虑到用户属于多机构的情况，可能会返回多条记录
		DataObject[] datas = bean.getUserBaseInfo(userId);

		String empId = null;
		Set<String> parentOrgIds = new HashSet<String>();
		Set<String> orgList=new HashSet<String>();
		for (int i = 0; i < datas.length; i++) {
			DataObject data = datas[i];
			if (i==0){
				userObject.put(ISystemConstants.USER_ID, userId);
				userObject.setUserName(data.getString("userName"));
				userObject.setUserMail(data.getString("email"));

				empId = data.getString("empId");
				if (empId!=null){
					userObject.setUserId(empId);
					userObject.setUserRealName(data.getString("empName"));
					userObject.setUserMail(data.getString("pEmail"));
					userObject.setUserOrgId(data.getString("orgId"));
					userObject.setUserOrgName(data.getString("orgName"));
					
					userObject.put(IConstants.MENU_TYPE, data.getString("menutype"));
				}
			}
			if (empId!=null){
				//遍历机构的orgSeqs，将机构的所有父机构数据遍历出来
				String orgSeq = data.getString("orgSeq");
				if(orgSeq!=null){				
					String []orgIdArr = StringUtils.split(orgSeq,".");
					for(String orgIdStr:orgIdArr){
						if(StringUtils.isNotEmpty(orgIdStr) && !StringUtils.equals(orgIdStr, data.getString("organizationId"))){
							parentOrgIds.add(orgIdStr);
						}
					}
				}
				
				orgList.add(data.getString("organizationId"));
			}
		}
		
		//如果只有用户没有员工，则把该处设置为userId
		if(empId==null){
			userObject.setUserId(userId);
		}
		//所有父机构的ID（包含多机构的情况），使用，号分隔
		userObject.put(IConstants.PARENT_ORG_IDS, StringUtils.join(parentOrgIds.iterator(), ','));
		
		//将当前用户的所有机构，放在扩展属性中
		userObject.put(IConstants.ORG_LIST, StringUtils.join(orgList.iterator(), ','));
		
		userObject.put(ISystemConstants.TENENT, tenantId);
		
		//取用户权限
		DataObject[] roles = bean.getUserPartyAuth(userId, empId);
		
		Set<String> roleSet = new HashSet<String>();
		for (DataObject role : roles) {
			roleSet.add(role.getString("ROLE_ID"));
		}
		String roleList = StringUtils.join(roleSet.toArray(new String[] {}), com.primeton.cap.auth.IConstants.SPLIET);
		userObject.put(IConstants.ROLE_LIST, roleList); 
		
		return userObject;
	}

}
