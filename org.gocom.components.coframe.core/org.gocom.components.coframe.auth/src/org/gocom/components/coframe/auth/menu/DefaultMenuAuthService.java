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
package org.gocom.components.coframe.auth.menu;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.gocom.components.coframe.auth.service.AuthPartyRuntimeService;
import org.gocom.components.coframe.auth.service.PartyAuthModel;
import org.gocom.components.coframe.framework.IAppMenuService;
import org.gocom.components.coframe.framework.application.AppMenu;
import org.gocom.components.coframe.framework.constants.IAppConstants;
import org.gocom.components.coframe.tools.IAuthConstants;

import com.eos.das.entity.criteria.CriteriaType;
import com.eos.spring.BeanFactory;
import com.eos.system.utility.StringUtil;
import com.primeton.cap.auth.MenuTree;
import com.primeton.cap.auth.MenuTree.MenuTreeNode;
import com.primeton.cap.auth.manager.AuthRuntimeManager;
import com.primeton.cap.management.resource.IManagedResource;
import com.primeton.cap.management.resource.IMenuResourceManager;
import com.primeton.cap.management.resource.manager.ResourceConfigurationManager;
import com.primeton.cap.management.resource.manager.ResourceRuntimeManager;
import com.primeton.cap.party.Party;

/**
 * TODO 此处填写 class 信息
 * 
 * @author caozw (mailto:caozw@primeton.com)
 */
public class DefaultMenuAuthService {

	private static final String SPRING_MENU_BEAN = "AppMenuBean";

	private List<Party> roles = new ArrayList<Party>();
	
	private String appCode = "";

	/**
	 * 根据角色列表创建菜单授权服务，一般情况下，角色列表存在session中
	 * 
	 * @param roles
	 */
	public DefaultMenuAuthService(List<Party> roles) {
		super();
		if (roles != null) {
			this.roles.addAll(roles);
		}
	}
	
	/**
	 * 
	 * @param roles 
	 * @param appCode 应用编码
	 */
	public DefaultMenuAuthService(List<Party> roles, String appCode){
		super();
		if (roles != null) {
			this.roles.addAll(roles);
		}
		if(StringUtils.isNotBlank(appCode)){
			this.appCode = appCode;
		}
	}

	/**
	 * 根据partyId和partyType获取菜单信息，一般情况下，用于管理员预览用户菜单
	 * 
	 * @param partyId
	 * @param partyType
	 */
	public DefaultMenuAuthService(String partyId, String partyType) {
		super();
		PartyAuthModel partyModel = AuthPartyRuntimeService.getPartyModel(
				partyId, partyType);
		if (partyModel != null) {
			this.roles.addAll(partyModel.getRoles());
		}
	}

	/**
	 * 获取菜单处理的bean
	 * 
	 * @return
	 */
	private IAppMenuService getMenuService() {
		BeanFactory beanFactory = BeanFactory.newInstance();
		return beanFactory.getBean(SPRING_MENU_BEAN);
	}

	/**
	 * 获取完整菜单树
	 * 
	 * @return
	 */
	public MenuTree getAllPartyAuthMenuTree() {
		MenuTree tree = new MenuTree();
		List<LevelMenuTreeNode> nodes = new ArrayList<LevelMenuTreeNode>();
		IAppMenuService menuService = getMenuService();
		if (menuService != null) {
			CriteriaType criteriaType = CriteriaType.FACTORY.create();
			AppMenu[] menus = menuService.queryAppMenus(criteriaType);
			computeMenuNodes(menus, nodes, true);
		}
		for (LevelMenuTreeNode node : nodes) {
			tree.addMenuTreeRootNode(node);
		}
		return tree;
	}

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public List<LevelMenuTreeNode> getRootPartyAuthMenuTree() {
		return getPartyAuthLevelMenuTreeNodeByParent(null);
	}

	/**
	 * 根据父节点获得下一级菜单
	 * 
	 * @param parentMenuNode
	 * @return
	 */
	public List<LevelMenuTreeNode> getPartyAuthLevelMenuTreeNodeByParent(
			LevelMenuTreeNode parentMenuNode) {
		List<LevelMenuTreeNode> nodes = new ArrayList<LevelMenuTreeNode>();
		IAppMenuService menuService = getMenuService();
		if (menuService != null) {
			CriteriaType criteriaType = CriteriaType.FACTORY.create();
			if (parentMenuNode != null) {
				// TODO 模型不对
				criteriaType.set("_expr[0]/menuseq", "."
						+ parentMenuNode.getMenuCode() + ".");
				criteriaType.set("_expr[0]/_op", "like");
				criteriaType.set("_expr[0]/_likeRule", "all");
			} else {

			}
			AppMenu[] menus = menuService.queryAppMenus(criteriaType);
			computeMenuNodes(menus, nodes, false);
		}
		return nodes;
	}

	/**
	 * 计算出一菜单棵树
	 * 
	 * @param menus
	 * @param nodes
	 * @param addChild
	 */
	private void computeMenuNodes(AppMenu[] menus,
			List<LevelMenuTreeNode> nodes, boolean addChild) {
		List<AppMenu> menuList = getAuthedMenuList(menus);
		Map<String, Set<LevelMenuTreeNode>> parentIdNodeMap = new HashMap<String, Set<LevelMenuTreeNode>>();
		Map<String, LevelMenuTreeNode> nodeMap = new HashMap<String, LevelMenuTreeNode>();
		// 定义菜单的根级别，遍历菜单，找到最小的级别
		int rootLevel = Integer.MAX_VALUE;
		// 把菜单根据父ID存到一个map中，再遍历一次，可找到所有菜单的子菜单
		for (AppMenu menu : menuList) {
			rootLevel = Math.min(rootLevel, menu.getMenulevel());
			LevelMenuTreeNode node = createMenuNode(menu);
			nodeMap.put(menu.getMenuid(), node);
			String parentMenuId = menu.getString("appMenu/menuid");
			if (parentMenuId != null) {
				Set<LevelMenuTreeNode> childNodes = parentIdNodeMap
						.get(parentMenuId);
				if (childNodes == null) {
					childNodes = new HashSet<LevelMenuTreeNode>();
					parentIdNodeMap.put(parentMenuId, childNodes);
				}
				childNodes.add(node);
			}
		}
		// 构造树，先看根节点
		for (AppMenu menu : menuList) {
			if (rootLevel == menu.getMenulevel()) {
				LevelMenuTreeNode menuNode = nodeMap.get(menu.getMenuid());
				if (computeMenuNode(menuNode, parentIdNodeMap, nodeMap,
						addChild)) {
					nodes.add(menuNode);
				}
			}
		}
		// TODO 不太敢直接改已有逻辑，写得很乱，再做一次反向计算，牺牲性能来确保功能
		for(int i=nodes.size();i>0;i--){
			deleteExtraNode(nodes, nodes.get(i-1));
		}
		
	}

	private void deleteExtraNode(List<LevelMenuTreeNode> nodes,
			LevelMenuTreeNode currentChildMenuTreeNode) {
		List<MenuTreeNode>childMenuTreeNodelList = currentChildMenuTreeNode.getChildrenMenuTreeNodeList();
		if(childMenuTreeNodelList != null){
			for(int i=childMenuTreeNodelList.size();i>0;i--){
				MenuTreeNode childMenuTreeNode = childMenuTreeNodelList.get(i-1);
				deleteExtraNode(childMenuTreeNodelList, childMenuTreeNode);
			}
		}
		if((childMenuTreeNodelList==null || childMenuTreeNodelList.isEmpty()) && StringUtil.isEmpty(currentChildMenuTreeNode.getLinkAction())){
			nodes.remove(currentChildMenuTreeNode);
		}
	}

	private void deleteExtraNode(List<MenuTreeNode> nodes,
			MenuTreeNode currentChildMenuTreeNode) {
		List<MenuTreeNode>childMenuTreeNodelList = currentChildMenuTreeNode.getChildrenMenuTreeNodeList();
		if(childMenuTreeNodelList != null){
			for(int i=childMenuTreeNodelList.size();i>0;i--){
				MenuTreeNode childMenuTreeNode = childMenuTreeNodelList.get(i-1);
				deleteExtraNode(childMenuTreeNodelList, childMenuTreeNode);
			}
		}
		
		if((childMenuTreeNodelList==null || childMenuTreeNodelList.isEmpty()) && StringUtil.isEmpty(currentChildMenuTreeNode.getLinkAction())){
			nodes.remove(currentChildMenuTreeNode);
		}
	}

	/**
	 * 计算菜单节点，如果是没有孩子的空菜单，则不展示，返回结果代表是否展示该菜单
	 * 
	 * @param currMenuNode
	 * @param parentIdNodeMap
	 * @param nodeMap
	 * @param addChild
	 * @return
	 */
	private boolean computeMenuNode(LevelMenuTreeNode currMenuNode,
			Map<String, Set<LevelMenuTreeNode>> parentIdNodeMap,
			Map<String, LevelMenuTreeNode> nodeMap, boolean addChild) {
		String menuId = currMenuNode.getMenuPrimeKey();

		// 需要看子菜单
		Set<LevelMenuTreeNode> childNodes = parentIdNodeMap.get(menuId);
		// 如果有子菜单，则增加菜单
		if (childNodes != null && childNodes.size() > 0) {
			// 如果需要增加child，则计算child
			if (addChild) {
				List<LevelMenuTreeNode> childNodeList = new ArrayList<LevelMenuTreeNode>();
				for (LevelMenuTreeNode childNode : childNodes) {
					if (computeMenuNode(childNode, parentIdNodeMap, nodeMap,
							addChild)) {
						childNodeList.add(childNode);
					}
				}
				// 通过displayorder,menucode排序
				sortMenuNode(childNodeList);
				for (LevelMenuTreeNode childNode : childNodeList) {
					currMenuNode.addChildMenuTreeNode(childNode);
				}
				//若子菜单为空则返回false，修复portal中发现BUG42053
				if(childNodeList.size()==0){
					return false;
				}

			}
			return true;
		} else {
			// 如果没有子，有URL，则增加菜单
			if (currMenuNode.getLinkAction() != null) {
				return true;
			} else {
				// 如果没有子，没有URL，则过滤空菜单
				return false;
			}

		}
	}

	/**
	 * 通过 display order,menucode排序
	 * 
	 * @param childNodeList
	 */
	private void sortMenuNode(List<LevelMenuTreeNode> childNodeList) {
		Collections.sort(childNodeList, new Comparator<LevelMenuTreeNode>() {

			public int compare(LevelMenuTreeNode node1, LevelMenuTreeNode node2) {
				int i1 = node1.getDisplayOrder();
				int i2 = node2.getDisplayOrder();
				if (node1.getDisplayOrder()==node2.getDisplayOrder())
					return node1.getMenuCode().toLowerCase().compareTo(node1.getMenuCode().toLowerCase());
				return node1.getDisplayOrder() - node2.getDisplayOrder();
			}

		});
	}

	/**
	 * 把APPMenu转为 menutree 节点
	 * 
	 * @param menu
	 * @return
	 */
	private LevelMenuTreeNode createMenuNode(AppMenu menu) {
		LevelMenuTreeNode node = new LevelMenuTreeNode();
		// TODO 菜单的ID与表中的不统一
		// node.setMenuId(menu.getMenuid());
		node.setMenuPrimeKey(menu.getMenuid());
		node.setMenuSeq(menu.getMenuseq());
		node.setLevel(menu.getMenulevel());
		node.setMenuCode(menu.getMenucode());
		node.setFunctionCode(menu.getFunccode());
		node.setMenuName(menu.getMenulabel());
		node.setExpandPath(menu.getExpandpath());
		node.setImagePath(menu.getImagepath());
		node.setLinkAction(menu.getMenuaction());
		node.setLinkResId(menu.getFunccode());
		node.setLinkType(IAuthConstants.FUNCTION_TO_RESOURCE_TYPE);
		node.setOpenMode(menu.getOpenmode());
		node.setDisplayOrder(menu.getString("displayorder")==null?Integer.MAX_VALUE:menu.getDisplayorder());
		return node;
	}

	/**
	 * 根据用户权限判断用户是否有权限访问菜单
	 * 
	 * @param partyId
	 * @param partyType
	 * @param menus
	 * @return
	 */
	private List<AppMenu> getAuthedMenuList(AppMenu[] menus) {
		List<AppMenu> menuList = new ArrayList<AppMenu>();
		// 迭代所有menu，根据functionCode判断资源授权状况
		for (AppMenu menu : menus) {
			// 判断是否是叶子节点，如果是叶子节点则根据功能过滤菜单，否则说明菜单没有绑定功能，直接添加
			if (StringUtils.equals("1", menu.getIsleaf())) {
				// 如果是叶子节点，且没有绑定功能，则菜单不可访问
				if (menu.getFunccode() != null) {
					// 先拿到功能资源，查看功能资源是否为公共资源
					IManagedResource resource = ResourceRuntimeManager
							.getInstance().getManagedResource(
									menu.getFunccode(),
									IAuthConstants.FUNCTION_TO_RESOURCE_TYPE);
					// 如果拿不到功能，说明功能不可用，直接跳过
					if (resource == null) {
						continue;
					}
					// 如果当前应用编码不等于功能的应用编码或者没有当前应用编码为空，直接跳过
					if (StringUtils.isNotBlank(appCode) && !appCode.equals(resource.getAttribute(IAppConstants.APP_CODE))){
						continue;
					}
					// 如果功能是公共的，则不校验，直接加菜单
					if (StringUtils.equals("0", resource
							.getAttribute(IAppConstants.FUNCTION_IS_CHECK))) {
						menu.setMenuaction(getMenuUrl(resource));
						menuList.add(menu);
					} else {
						// 如果未授权，则不添加该菜单
						if (canAccessFunction(menu.getFunccode())) {
							// 如果已授权，则取出URL
							menu.setMenuaction(getMenuUrl(resource));
							menuList.add(menu);
						}
					}
				}
			} else {
				menuList.add(menu);
			}
		}
		return menuList;
	}

	/**
	 * 获取所有角色对该功能的授权信息
	 * 
	 * @param functionCode
	 * @return
	 */
	public boolean canAccessFunction(String functionCode) {
		Set<String> stateSet = new HashSet<String>();
		for (Party role : roles) {
			String state = AuthRuntimeManager.getInstance()
					.getAuthResourceState(role, functionCode,
							IAuthConstants.FUNCTION_TO_RESOURCE_TYPE);
			if (state != null) {
				stateSet.add(state);
			}
		}
		String states[] = stateSet.toArray(new String[0]);
		IMenuResourceManager menuResourceManager = ResourceConfigurationManager
				.getInstance().getMenuResourceManager(
						IAuthConstants.FUNCTION_TO_RESOURCE_TYPE);
		if (menuResourceManager != null) {
			if (menuResourceManager.canAccess(states)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取menu的URL，从功能的资源中获取，资源缓存过
	 * 
	 * @param funccode
	 * @return
	 */
	private String getMenuUrl(IManagedResource resource) {
		if (resource != null) {
			// 如果是本地应用，则直接返回
			if (resource.getAttribute(IAppConstants.APP_URL) == null) {
				return resource.getAttribute(IAppConstants.FUNCTION_URL);
			} else {
				// 如果是远程应用，则需要拼接URL
				URL url;
				try {
					String appUrl = (String) resource
							.getAttribute(IAppConstants.APP_URL);
					if (!appUrl.endsWith("/")) {
						appUrl = appUrl + "/";
					}
					String functionUrl = (String) resource.getAttribute(IAppConstants.FUNCTION_URL);
					if(functionUrl.startsWith("/")){
						functionUrl = functionUrl.substring(1);
					}
					url = new URL(new URL(appUrl), functionUrl);
					return url.toString();
				} catch (MalformedURLException e) {
					return resource.getAttribute(IAppConstants.APP_URL) + "/"
							+ resource.getAttribute(IAppConstants.FUNCTION_URL);
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			URL url = new URL(new URL("http://127.0.0.1:8080/abc/"),
					"a.b.c.flow");
			System.out.println(url.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
