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
package org.gocom.components.coframe.auth.login;

import java.util.ArrayList;
import java.util.List;

import org.gocom.components.coframe.auth.DefaultAuthManagerService;
import org.gocom.components.coframe.auth.menu.LevelMenuTreeNode;
import org.gocom.components.coframe.init.UserObjectInit;

import com.eos.access.http.OnlineUserManager;
import com.eos.data.datacontext.UserObject;
import com.eos.foundation.data.DataContextUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.primeton.cap.auth.IAuthManagerService;
import com.primeton.cap.auth.MenuTree.MenuTreeNode;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.ext.das.common.DataObjectHelper;

import commonj.sdo.DataObject;

/**
 * 登录服务类
 * 
 * @author shitf (mailto:shitf@primeton.com)
 */
public class LoginService {
	
	/**
	 * 登录一个UserObject
	 * @param userObject UserObject对象
	 */
	public void login(UserObject userObject){
		OnlineUserManager.login(userObject);
	}
	
	/**
	 * 
	 * 构造userObject
	 * @param userObject
	 * @param userId
	 */
	public UserObject initUserObject(String userId) {
		return UserObjectInit.INSTANCE.init(userId);
	}
	
	/**
	 * 获取首页菜单数据
	 * @return
	 */
	public List<MenuTreeNode> getUserMenuTree(){
		IAuthManagerService service = new DefaultAuthManagerService();
		return service.getUserMenuTree().getMenuTreeRootNodeList();
	}
	
	public DataObject[] getUserMenuList(){
		List<MenuTreeNode> menuTree = getUserMenuTree();
		List<DataObject> menuList = new ArrayList<DataObject>();
		for (MenuTreeNode node : menuTree) {
			getUserMenu(menuList,(LevelMenuTreeNode)node,null);
		}
		return menuList.toArray(new DataObject[menuList.size()]);
	}
	
	private void getUserMenu(List<DataObject> menuList,LevelMenuTreeNode node,String pid){
		boolean isLeaf = node.getChildrenMenuTreeNodeList()==null;
		DataObject menu = DataObjectHelper.createDataObject(null);
		menu.set("text"        ,node.getMenuName());
		menu.set("id"          ,node.getMenuPrimeKey());
		menu.set("pid"         ,pid);
		menu.set("url"         ,node.getLinkAction());
		menu.set("iconCls"     ,"");
		menu.set("iconPosition",node.getImagePath());
		menu.set("isLeaf"      ,isLeaf);
		
		menuList.add(menu);
		
		if (!isLeaf&&node.getChildrenMenuTreeNodeList().size()>0){
			List<MenuTreeNode> childMenuTree = node.getChildrenMenuTreeNodeList();
			for (MenuTreeNode menuTreeNode : childMenuTree) {
				getUserMenu(menuList,(LevelMenuTreeNode)menuTreeNode,node.getMenuPrimeKey());
			}
		}
	}
	
	/**
	 * 获取当前应用首页菜单数据
	 * @return
	 */
	public List<MenuTreeNode> getUserMenuTreeByAppCode(String appCode){
		IAuthManagerService service = new DefaultAuthManagerService();
		return service.getUserMenuTreeByAppCode(appCode).getMenuTreeRootNodeList();
	}
	
	/**
	 * 注销用户
	 * @param userObject
	 */
	public UserObject logout(String uniqueId){
		return (UserObject) OnlineUserManager.logoutByUniqueId(uniqueId);
	}
}
