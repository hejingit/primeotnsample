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
package org.gocom.components.coframe.org.party.impl;

import java.util.List;

import org.gocom.components.coframe.org.groupdataset.OrgGroup;
import org.gocom.components.coframe.org.party.manager.DefaultGroupManager;
import org.gocom.components.coframe.org.util.OrgPartyAdaptUtil;

import com.eos.spring.BeanFactory;
import com.primeton.cap.party.IPartyTypeDataService;
import com.primeton.cap.party.Party;

/**
 * 工作组的Party类型数据服务类
 *
 * @author gouyl (mailto:gouyl@primeton.com)
 */
public class GroupPartyTypeDataService implements IPartyTypeDataService {

	private DefaultGroupManager bean;

	public GroupPartyTypeDataService() {
		BeanFactory beanFactory = BeanFactory.newInstance();
		bean = beanFactory.getBean(DefaultGroupManager.SPRING_BEAN_NAME);
	}

	public List<Party> getAllPartyList(String tenantID) {
		OrgGroup[] groups = bean.getAllGroups(tenantID);
		return OrgPartyAdaptUtil.adapt(groups);
	}

	public Party getPartyByPartyID(String partyID, String tenantID) {
		OrgGroup org = bean.getGroupById(partyID, tenantID);
		return OrgPartyAdaptUtil.adapt(org);
	}

	public List<Party> getRootPartyList(String tenantID) {
		OrgGroup[] orgs = bean.getRootGroups(tenantID);
		return OrgPartyAdaptUtil.adapt(orgs);
	}

}
