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
package org.gocom.components.coframe.org.party.ref.impl;

import java.util.List;

import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.party.manager.DefaultGroupManager;
import org.gocom.components.coframe.org.util.OrgPartyAdaptUtil;

import com.eos.spring.BeanFactory;
import com.primeton.cap.party.IPartyTypeRefDataService;
import com.primeton.cap.party.Party;

/**
 * 工作组自关联数据服务类
 *
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public class GroupGroupRefDataService implements IPartyTypeRefDataService {

	private DefaultGroupManager bean;

	public GroupGroupRefDataService() {
		BeanFactory beanFactory = BeanFactory.newInstance();
		bean = beanFactory.getBean(DefaultGroupManager.SPRING_BEAN_NAME);
	}
	
	public List<Party> getChildrenPartyList(String parentPartyID, String tenantID) {
		// 根据工作组获取子工作组
		OrgGroup[] orgs = bean.getSubGroups(parentPartyID, tenantID);
		return OrgPartyAdaptUtil.adapt(orgs);
	}

	public List<Party> getParentPartyList(String childPartyID, String tenantID) {
		// 获取工作组的父工作组
		OrgGroup org = bean.getParentGroup(childPartyID, tenantID);
		return OrgPartyAdaptUtil.adaptToList(org);
	}

}
