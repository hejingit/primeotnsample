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
package org.gocom.components.coframe.participantselect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gocom.components.coframe.auth.party.DefaultPartyManagerService;
import org.gocom.components.coframe.org.dataset.OrgOrganization;

import com.eos.system.annotation.Bizlet;
import com.primeton.cap.party.Party;
import commonj.sdo.DataObject;

public class PartyManagerHelper {
	DefaultPartyManagerService partManagerService = new DefaultPartyManagerService();

	/**
	 * 获取全局机构根对象列表
	 * 
	 */
	@Bizlet("获取全局机构根对象列表")
	public ArrayList<HashMap> doGetOrgRootPartyList() {

		List<Party> list = partManagerService.doGetRootPartyList("org",
				"default");

		return convertTree(list, "-1", "org");
	}

	/**
	 * 获取全局组根对象列表
	 * 
	 */
	@Bizlet("获取全局组根对象列表")
	public ArrayList<HashMap> doGetGroupRootPartyList() {

		List<Party> list = partManagerService.doGetRootPartyList("group",
				"default");

		return convertTree(list, "-1", "group");
	}

	/**
	 * 获取全局机构根对象列表
	 * 
	 */
	@Bizlet("获取用户所在机构根对象列表")
	public ArrayList<HashMap> doGetUserOrgPartyList(String childPartyID) {

		Map<String, List<Party>> map = partManagerService
				.doGetDirectAssociateParentPartyList(childPartyID, "emp",
						new String[] { "org" }, "default");

		return convertOrgTree(map.get("org"), "-1");
	}

	/**
	 * 获取全局机构根对象列表
	 * 
	 */
	@Bizlet("满足指定层级的机构列表，并且包含用户所在机构")
	public ArrayList<HashMap> getOrgLevelByUserPartList(DataObject[] selOrgs,
			DataObject[] userOrgs) {

		List<Party> retList = new ArrayList<Party>();

		List<String> selOrgList = new ArrayList<String>();

		for (DataObject uObj : userOrgs) {
			String orgseq = (String) uObj.get("orgseq");
			String userOrgid = String.valueOf((BigDecimal) uObj.get("orgid"));
			for (DataObject sObj : selOrgs) {
				String levelOrgid = String.valueOf((BigDecimal) sObj
						.get("orgid"));
				String selseq = (String) sObj.get("orgseq");
				if (selOrgList.contains(levelOrgid)) {
					continue;
				}
				if (orgseq.indexOf("." + levelOrgid + ".") >= 0
						|| selseq.indexOf("." + userOrgid + ".") >= 0) {
					// 存在
					Party p = new Party();
					p.setId(levelOrgid);
					p.setName((String) sObj.get("orgname"));
					p.setTenantID("default");
					p.setPartyTypeID("org");

					retList.add(p);

					selOrgList.add(levelOrgid);
					break;
				}
			}
		}

		return convertOrgTree(retList, "-1");
	}

	/**
	 * 获取某一个用户所机构的上级机构
	 * 
	 */
	@Bizlet("通过用户获取用户机构的上级机构对象列表")
	public ArrayList<HashMap> doGetParentOrgByUserIdPartyList(
			String childPartyID) {
		Map<String, List<Party>> map = partManagerService
				.doGetDirectAssociateParentPartyList(childPartyID, "emp",
						new String[] { "org" }, "default");

		List<Party> pList = map.get("org");
		ArrayList<HashMap> retList = new ArrayList<HashMap>();

		for (Party orgParty : pList) {
			String id = orgParty.getId();
			// 获取具体机构的上级机构
			ArrayList<HashMap> tmpList = doGetParentOrgPartyList(id);
			retList.addAll(tmpList);
		}

		return retList;
	}

	/**
	 * 获取某一个用户所机构的上级机构
	 * 
	 */
	@Bizlet("通过指定机构列表，获取有效的机构集合")
	public ArrayList<HashMap> doGetOrgsPartyList(OrgOrganization[] orgs) {
		List<Party> pList = new ArrayList<Party>();
		for (OrgOrganization org : orgs) {
			Party p = new Party();
			p.setId(String.valueOf(org.getOrgid()));
			p.setName(org.getOrgname());
			p.setTenantID("default");
			p.setPartyTypeID("org");
			pList.add(p);

		}
		return convertOrgTree(pList, "-1");
	}

	/**
	 * 获取某一个用户所机构的上级机构
	 * 
	 */
	@Bizlet("通过用户获取用户机构的下级机构对象列表")
	public ArrayList<HashMap> doGetChildOrgByUserIdPartyList(String childPartyID) {
		Map<String, List<Party>> map = partManagerService
				.doGetDirectAssociateParentPartyList(childPartyID, "emp",
						new String[] { "org" }, "default");

		List<Party> pList = map.get("org");
		ArrayList<HashMap> retList = new ArrayList<HashMap>();

		for (Party orgParty : pList) {
			String id = orgParty.getId();
			// 获取具体机构的上级机构
			ArrayList<HashMap> tmpList = doGetOrgChlidPartyList(id);
			retList.addAll(tmpList);
		}

		return retList;
	}

	/**
	 * 获取某一个机构的上级机构
	 * 
	 */
	@Bizlet("通过机构号获取机构的上级机构对象列表")
	public ArrayList<HashMap> doGetParentOrgPartyList(String childPartyID) {

		Map<String, List<Party>> map = partManagerService
				.doGetDirectAssociateParentPartyList(childPartyID, "org",
						new String[] { "org" }, "default");

		return convertTree(map.get("org"), "-1", "org");
	}

	/**
	 * 当上级机构存在时候，去除下级机构
	 */
	public ArrayList<HashMap> mergeOrgs(DataObject[] dataObjs) {

		for (DataObject dObj : dataObjs) {
			dObj.get("ORGSEQ");

		}
		return null;

	}

	/**
	 * 获取指定机构下属机构根对象列表
	 * 
	 */
	@Bizlet("获取指定机构下属机构根对象列表")
	public ArrayList<HashMap> doGetOrgChlidPartyList(String partyId) {
		return convertTree(getChildList(partyId, "org"), partyId, "org");
	}

	/**
	 * 获取指定组下属组对象列表
	 * 
	 */
	@Bizlet("获取指定组下属组对象列表")
	public ArrayList<HashMap> doGetGroupChlidPartyList(String partyId) {
		return convertTree(getChildList(partyId, "group"), partyId, "group");
	}

	private List<Party> getOrgChildList(String partyId) {
		Map<String, List<Party>> map = partManagerService
				.doGetDirectAssociateChildPartyList(partyId, "org",
						new String[] { "org" }, "default");
		List<Party> childList = map.get("org");
		return childList;
	}

	private ArrayList<HashMap> convertOrgTree(List<Party> pList,
			String parentPartyId) {
		return convertTree(pList, parentPartyId, "org");
	}

	private ArrayList<HashMap> convertGroupTree(List<Party> pList,
			String parentPartyId) {
		return convertTree(pList, parentPartyId, "group");
	}

	private ArrayList<HashMap> convertTree(List<Party> pList,
			String parentPartyId, String partyType) {
		ArrayList<HashMap> retList = new ArrayList<HashMap>();
		if (pList != null && pList.size() > 0) {
			for (Party pObj : pList) {
				HashMap nodeMap = new HashMap();
				String id = pObj.getId();
				String name = pObj.getName();
				nodeMap.put("id", id);
				nodeMap.put("name", name);
				boolean isLeaf = true;
				// 判断该机构是否有下属节点

				List<Party> childList = getChildList(id, partyType);
				if (childList != null && childList.size() > 0) {
					isLeaf = false;
				}
				nodeMap.put("isLeaf", isLeaf);
				nodeMap.put("parentId", parentPartyId);
				retList.add(nodeMap);
			}
		}

		return retList;

	}

	private List<Party> getChildList(String partyId, String partyType) {
		Map<String, List<Party>> map = partManagerService
				.doGetDirectAssociateChildPartyList(partyId, partyType,
						new String[] { partyType }, "default");
		List<Party> childList = map.get(partyType);
		return childList;
	}

}
