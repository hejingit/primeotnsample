<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): gouyl
  - Date: 2013-10-09 14:43:27
  - Description:工作组管理树
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<%@page import="org.gocom.components.coframe.tools.superadmin.SuperAdminService"%>
<%@include file="/coframe/tools/skins/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/tools/icons/icon.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工作组树</title>
</head>
<body>
<style>
	#tree .mini-grid-viewport{
		background-color:transparent !important;
	}
	#tree  .mini-panel-viewport{
		background-color:transparent !important;
	}
	#orgTabs .mini-tabs-bodys{
		padding:0px;
	}
</style>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="工作组管理" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;" 
			showTreeIcon="true" textField="nodeName" 
			idField="nodeId" resultAsTree="true" dataField="treeNodes"
			onbeforeload="onBeforeTreeLoad" onnodeclick="onNodeClick"
			allowDrag="true" allowDrop="true"  allowLeafDropIn="true"
			ongivefeedback="onGiveFeedback" ondrop="onDrop" onbeforedrop="onBeforeDrop"
			contextMenu="#treeMenu">
	    </ul>		
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" onactivechanged="changeTab"></div>
    </div>
</div>

<ul id="treeMenu" class="nui-contextmenu" onbeforeopen="onBeforeOpen"></ul>

<div id="dragOptionDiv" style="width:100%;display:none;">
	<input name="dragAction" type="radio" value="move" checked="checked"/>移动&nbsp;
	<input name="dragAction" type="radio" value="copy"/>复制&nbsp;
	<input name="dragAction" type="radio" value="cancel"/>取消
</div>

<div id="empRemovePrompt" style="width:100%;display:none;">
   <table>
     <tr>
       <td>
          <div class="mini-messagebox-question"></div>
       </td>
       <td>&nbsp;&nbsp;&nbsp;确定删除所选员工?</td>
     </tr>
   </table>
</div>

<div id="orgRemovePrompt" style="width:100%;display:none;">
   <table>
     <tr>
       <td>
          <div class="mini-messagebox-question"></div>
       </td>
       <td>&nbsp;&nbsp;&nbsp;确定删除所选工作组？</td>
     </tr>
   </table>
</div>

<script type="text/javascript">

	var currentNode = null;

	$(function() {
		nui.parse();
    
	    var contextPath = "<%=contextPath %>";
	    var tree = nui.get("tree");
	    var rootNode = {nodeName: "工作组树", nodeType: "Root", isLeaf: false, expanded: false, iconCls: "icon-arrow-merge"};
	    
	    tree.addNode(rootNode);
	    tree.selectNode(rootNode);
	    currentNode = rootNode;
	    refreshNode(rootNode);
	    showNodeTabs(rootNode);
	});
        
    function onBeforeTreeLoad(e) {
	    if(e.data.nodeId==""){
	    	 e.url="org.gocom.components.coframe.auth.partyauth.ManageableRes.getManageableGroup.biz.ext";
	    }else{
	    	e.url = "org.gocom.components.coframe.org.group.queryTreeChildNodes.biz.ext";
	    }
    	// 增加nodeType参数以便区分是加载工作组下的结点还是岗位下的结点
		e.params.nodeType = e.node.nodeType;
    }
    
    // 权限页面
    var party_auth = { title: '权限设置', url: '<%=contextPath%>/coframe/auth/partyauth/auth.jsp' };
	var party_graph = { title: '权限计算', url: '<%=contextPath%>/coframe/auth/authgraph/auth_graph.jsp' };

	// 定义Tab页面 //
	// 根结点对应的Tab页
	var group_list = { title: '本级工作组', url: '<%=contextPath%>/coframe/org/group/group_index.jsp' };
	var employee_index = { title: '员工查询', url: '<%=contextPath%>/coframe/org/employee/employee_index.jsp' };
	
	// 工作组结点对应的Tab页
	var group_add = { title: '新增工作组', url: '<%=contextPath%>/coframe/org/group/group_add.jsp' };
	var group_update  = { title: '本级工作组', url: '<%=contextPath%>/coframe/org/group/group_update.jsp' };
	var sub_group_list  = { title: '下级工作组', url: '<%=contextPath%>/coframe/org/group/sub_group_list.jsp' };
	var group_position_list  = { title: '下级岗位', url: '<%=contextPath%>/coframe/org/group/position_group_list.jsp' };
	var group_employee_list  = { title: '员工信息', url: '<%=contextPath%>/coframe/org/group/employee_group_list.jsp' };
	
	// 岗位结点对应的Tab页
	var position_org_add = { title: '新增工作组岗位', url: '<%=contextPath%>/coframe/org/group/position_group_add.jsp' };
	var position_sub_add = { title: '新增子岗位', url: '<%=contextPath%>/coframe/org/position/position_sub_add.jsp' };
	var position_update = { title: '岗位基本信息', url: '<%=contextPath%>/coframe/org/group/position_group_update.jsp' };
	var posotion_position_list = { title: '下级岗位', url: '<%=contextPath%>/coframe/org/position/position_list.jsp' };
	var position_employee_list = { title: '员工列表', url: '<%=contextPath%>/coframe/org/group/employee_groupposi_list.jsp' };
	
	// 员工结点对应的Tab页
	var employee_basicinfo_update = { title: '基本信息', url: '<%=contextPath%>/coframe/org/employee/employee_basicinfo_update.jsp' };
	var employee_detailinfo_update = { title: '详细信息', url: '<%=contextPath%>/coframe/org/employee/employee_detailinfo_update.jsp' };
	var employee_org_add = { title: '新增工作组员工', url: '<%=contextPath%>/coframe/org/group/employee_groupposi_add.jsp' };
	var employee_posi_add = { title: '新增岗位员工', url: '<%=contextPath%>/coframe/org/group/employee_groupposi_add.jsp' };

	// 树结点类型与Tab页面的映射
	var orgTabs_map = {
		<%=SuperAdminService.currUserIsSupserAdmin()?"Root: [group_list],":""%>
		//OrgGroup: [group_update, sub_group_list, group_position_list, group_employee_list, party_auth, party_graph],<%-- 屏蔽岗位功能 update by linfeng 20141020--%>
		OrgGroup: [group_update, sub_group_list, group_employee_list, party_auth, party_graph],
		OrgPosition: [position_update, posotion_position_list, position_employee_list, party_auth, party_graph],
		OrgEmployee: [employee_basicinfo_update, employee_detailinfo_update, party_auth, party_graph]
	};
	
	// 右键菜单定义 //
	// 根结点右键菜单
	var rootMenu = [
		{text: "增加工作组", onclick: "onAddRootOrg", iconCls: "icon-add"},
		{text: "刷新", onclick: "onRefreshNode", iconCls: "icon-reload"}
	];
	
	// 工作组右键菜单
	var groupMenu = [
		{text: "增加下级工作组", onclick: "onAddOrgOfGroup", iconCls: "icon-add"},
		//{text: "增加下级岗位", onclick: "onAddPositionOfGroup", iconCls: "icon-add"},<%--屏蔽岗位功能 update by linfeng 20141020--%>
		{text: "增加工作组员工", onclick: "onAddEmpOfGroup", iconCls: "icon-add"},
		{text: "维护工作组权限", onclick: "onModifyGroupAuth", iconCls: "icon-edit"},
		{text: "删除本级工作组", onclick: "onRemoveGroup", iconCls: "icon-remove"},
		{text: "刷新", onclick: "onRefreshNode", iconCls: "icon-reload"}
	];
	
	// 岗位右键菜单
	var positionMenu = [
		{text: "增加下级岗位", onclick: "onAddPositionOfPosition", iconCls: "icon-add"},
		{text: "增加岗位员工", onclick: "onAddEmpOfPosition", iconCls: "icon-add"},
		{text: "维护岗位权限", onclick: "onModifyPositionAuth", iconCls: "icon-edit"},
		{text: "删除本级岗位", onclick: "onRemovePosition", iconCls: "icon-remove"},
		{text: "刷新", onclick: "onRefreshNode", iconCls: "icon-reload"}
	];
	
	// 员工右键菜单
	var empMenu = [
		{text: "维护员工权限", onclick: "onModifyEmpAuth", iconCls: "icon-add"},
		{text: "删除员工", onclick: "onRemoveEmp", iconCls: "icon-remove"}
	];
	
	// 树结点类型与右键菜单的映射
	var menu_map = {
	//	<%=SuperAdminService.currUserIsSupserAdmin()?"Root: rootMenu,":""%>
		Root: rootMenu,
		OrgGroup: groupMenu,
		OrgPosition: positionMenu,
		OrgEmployee: empMenu
	};

	/**
	 * 显示指定结点对应的Tab页
	 */
	function showNodeTabs(node) {
		var orgTabs = orgTabs_map[node.nodeType];
		if(!orgTabs) return;
		
		var tabs = nui.get("orgTabs");
		tabs.setTabs(orgTabs);
		$("#orgTabs").show();
		//触发第一个tab的SetData对象
		var firstTab = tabs.getTab(0);
		if(firstTab){
			firstTab.onload = function(e){
				var tab = e.sender;
				var iframe = tab.getTabIFrameEl(firstTab);
				if(iframe && iframe.contentWindow && iframe.contentWindow.SetData){
					iframe.contentWindow.SetData(getCurrentNode());
				}
			}
		}
		
	}
	
	/**
	 * 树结点被鼠标左键单击后，在右侧打开对应的页面
	 */
	function onNodeClick(e){
		if (currentNode._state!="removed"&&e.node.nodeType==currentNode.nodeType){
			currentNode = e.node;
			var currentTab = nui.get("orgTabs").getActiveTab();
			nui.get("orgTabs").reloadTab(currentTab);
		}else{
			showNodeTabs(e.node);
			currentNode = e.node;
		}
	}
	
	/**
	 * 获取当前结点对象，可提供给子页面调用，以避免在子页面之间传递结点参数
	 */
	this.getCurrentNode = function() {
		return currentNode;
	}
	
	/**
	 * 获取当前选中结点对象，可提供给子页面调用
	 */
	this.getSelectedNode = function() {
		var tree = nui.get("tree");
		return tree.getSelectedNode();
	}
	
	/**
	 * 获取父结点对象，可提供给子页面调用
	 * @param node NUI的树结点对象，默认为当前树结点currentNode
	 */
	this.getParentNode = function(node) {
		node = node || getCurrentNode();
		var tree = nui.get("tree");
		return tree.getParentNode(node);
	}
	
	/**
	 * 刷新指定结点，可提供给子页面调用
	 * @param node NUI的树结点对象
	 */
	this.refreshNode = function(node) {
		var tree = nui.get("tree");
		tree.loadNode(node);
	}
	
	/**
	 * 刷新当前结点下的子节点，可提供给子页面调用
	 */
	this.refreshCurrentNode = function() {
		refreshNode(getCurrentNode());
	}
	
	/**
	 * 刷新指定结点的父结点，可提供给子页面调用
	 * @param node NUI的树结点对象，默认为当前树结点currentNode
	 */
	this.refreshParentNode = function(node) {
		node = node || getCurrentNode();
		refreshNode(getParentNode(node));
	}
	
	/**
	 * 递归搜索父工作组结点
	 */
	function searchParentOrgNode(node) {
		if(!node || !node.nodeType) return null;
		
		if(node.nodeType == "OrgGroup") return node;
		
		return searchParentOrgNode(getParentNode(node));
	}
	
	/**
	 * 根据结点类型显示
	 */
	function onBeforeOpen(e) {
		var tree = nui.get("tree");
		var node = null;
		var cell = tree._getCellByEvent(e.htmlEvent);
		if(cell) {
			node = cell[0];
		}
		if(!node) {
			node = tree.getSelectedNode();
		}
		var menu = e.sender;

		var menuList = menu_map[node.nodeType];
		if(!menuList || menuList.length == 0) {
			e.cancel = true;
			return;
		}
		
		menu.loadList(nui.clone(menuList)); //这里菜单项必须要克隆，否则第二次点击时无法触发事件
	}
	
	/**
	 * 树结点拖拽前处理，判断被拖拽结点是否能拖动到目标结点下
	 */
	function onGiveFeedback(e) {
		var tree = e.sender;
		var node = e.node;              //被拖拽的节点
		node.parentNode = tree.getParentNode(node); //被拖拽节点的父节点（扩展的参数，在onDrop()事件中会被使用）
		var targetNode = e.targetNode;  //目标投放节点
		
		// 只处理add，不处理before和after，即不处理调整结点顺序的情况
//		if(effect == "before" || effect == "after") {
//			e.effect = "no";
//			return;
//		}
		
		if(node.parentNode == targetNode) {
			e.effect = "no";
			return;
		}
		
		switch(node.nodeType) {
		case "OrgGroup":
			if(targetNode.nodeType != "OrgGroup") {
				e.effect = "no";
			}
			break;
		case "OrgPosition":
			if(targetNode.nodeType != "OrgGroup" && targetNode.nodeType != "OrgPosition") {
				e.effect = "no";
			}
			break;
		case "OrgEmployee":
			if(targetNode.nodeType != "OrgGroup" && targetNode.nodeType != "OrgPosition") {
				e.effect = "no";
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * 拖动到目标结点前的事件处理
	 */
	function onBeforeDrop(e) {
		var dragAction = e.dragAction; //投放方式：add|before|after
	
		var tree = e.sender;
		var dragNode = e.dragNode; //被拖拽的节点
		var dragParentNode = dragNode.parentNode; //被拖拽节点的父节点
		var dropNode = e.dropNode; //目标投放节点
		
		var data = {
			dragNodeId: dragNode.nodeId, 
			dragNodeType: dragNode.nodeType,
			dragParentNodeId: dragParentNode.nodeId,
			dragParentNodeType: dragParentNode.nodeType,
			dropNodeId: dropNode.nodeId,
			dropNodeType: dropNode.nodeType
		};
		
		// 打开拖拽结点处理选项，选择“移动”、“复制”或“取消”
    	var $dragOption = $("#dragOptionDiv").clone();
        $dragOption.show();
        mini.showMessageBox({
            width: 250,
            title: "请选择您需要的操作",
            buttons: ["ok", "cancel"],
            html: $dragOption[0].innerHTML,
            callback: function (action) {
            	if (action == "ok") {
                    var dragOptionVal = $("input[name='dragAction']:checked").val();
                    var url = null;
					if(dragOptionVal == "move") {
						var url = "org.gocom.components.coframe.org.group.moveNode.biz.ext";
					} else if(dragOptionVal == "copy") {
						var url = "org.gocom.components.coframe.org.group.copyNode.biz.ext";
					}
                    
                    if(url) {
						var json = nui.encode(data);
						$.ajax({
				            url: url,
				            type: 'POST',
				            data: json,
				            contentType:'text/json',
				            success: function (text) {
				            	refreshNode(dragParentNode);
				            	refreshNode(dropNode);
				            }
						});
                    }
                } 
            }
        });
        
        // 阻止拖动后将结点直接移动，要通过刷新来体现移动结果
        e.cancel = true;
	}
	
	/**
	 * 弹出提示框，在指定时间后自动消失
	 * @params message 提示消息
	 * @params title 提示标题，默认为"提示"
	 * @params timeout 提示框多长时间后消失，单位毫秒，默认为500毫秒
	 */
	function alertTip(message, title, timeout) {
		title = title || "提示";
		timeout = timeout || 500;
		var messageid = nui.loading(message, title);
		setTimeout(function () {
			nui.hideMessageBox(messageid);
		}, timeout);
	}
	
	/**
	 * 打开对话框，自动处理onload和ondestroy事件
	 * @params params 额外提供params.data属性
	 */
	function openDialog(params) {
		var openParams = nui.clone(params);
		
		openParams.onload || (openParams.onload = function () {
			var iframe = this.getIFrameEl();
			var contentWindow = iframe.contentWindow;
			
			if(contentWindow.SetData) {
				contentWindow.SetData(openParams.data);
			}
		});
		
		// 子窗口点确定时刷新当前结点
		openParams.ondestroy || (openParams.ondestroy = function (action) {
			if (action == "ok") {
				refreshNode(getSelectedNode());
				var currentTab = nui.get("orgTabs").getActiveTab();
				nui.get("orgTabs").reloadTab(currentTab);
			}
		});
		
		nui.open(openParams);
	}
	
	// 右键菜单的处理方法 //
	
	function onAddRootOrg(e) {
		openDialog({
			title: "新增工作组",
			url: group_add.url,
			width:610,
			height:350
		});
	}
	
	function onAddOrgOfGroup(e) {
		openDialog({
			title: "新增下级工作组",
			url: group_add.url,
			data: {parentNode: getSelectedNode()},
			width:610,
			height:350
		});
	}
	
	function onAddPositionOfGroup(e) {
		openDialog({
			title: "新增下级岗位",
			url: position_org_add.url,
			data: {parentNode: getSelectedNode()},
			width:600,
			height:150
		});		
	}
	
	function onAddEmpOfGroup(e) {
		openDialog({
			title: "新增工作组员工",
			url: employee_org_add.url,
			data: {parentNode: getSelectedNode()},
			width:600,
			height:450
		});
	}
	
	function onModifyGroupAuth(e) {
		openDialog({
			title: "维护工作组权限",
			url: party_auth.url,
			data: {parentNode: getSelectedNode()},
			width:600,
			height:420
		});
	}
	
	function onAddPositionOfPosition(e) {
		openDialog({
			title: "新增下级岗位",
			url: position_sub_add.url,
			data: {parentNode: getSelectedNode()},
			width:600,
			height:180
		});
	}
	
	function onAddEmpOfPosition(e) {
		openDialog({
			title: "新增岗位员工",
			url: employee_posi_add.url,
			data: {parentNode: getSelectedNode()},
			width:530,
			height:300
		});
	}
	
	function onModifyPositionAuth(e) {
		openDialog({
			title: "维护岗位权限",
			url: party_auth.url,
			data: {parentNode: getSelectedNode()},
			width:600,
			height:420
		});
	}
	
	function onModifyEmpAuth(e) {
		openDialog({
			title: "维护员工权限",
			url: party_auth.url,
			data: {parentNode: getSelectedNode()},
			width:600,
			height:420
		});
	}
	
	function onRefreshNode(e) {
		refreshNode(getSelectedNode());
	}
	
	var empPrompt = document.getElementById("empRemovePrompt");
	function onRemoveEmp(e){
	   var tree = nui.get("tree");
	   var node = tree.getSelectedNode();
	   var parent = tree.getParentNode(node);
	   if(parent.nodeType=="OrgPosition") {
			removeEmpFromPosition();
	   } else {
	   		removeEmpFromOrg();
	   }
	}
	
	function executeEmpRemove(){
	    var result = window.result;
	    if(result.action){
		    var tree = nui.get("tree");
			var node = tree.getSelectedNode();
			var parentNode = tree.getParentNode(node);
			
			var data = {
				nodeId: node.nodeId,
				nodeType: node.nodeType,
				parentId: parentNode.nodeId,
				parentType: parentNode.nodeType,
				isDeleteCascade:window.result.isDeleteEmpCascade
			}
			
			var json = nui.encode(data);
	        nui.ajax({
	            url: "org.gocom.components.coframe.org.organization.deleteNode.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	alertTip("删除成功");
	                tree.loadNode(parentNode);
	                var currentTab = nui.get("orgTabs").getActiveTab();
					nui.get("orgTabs").reloadTab(currentTab);
	            },
	            error: function () {
	            	alertTip("删除员工失败，请联系管理员");
	            }
	        });	
        }
	}
	
	function removeEmpFromPosition() {
		nui.confirm("确定删除所选员工？", "提示？",
        function (action) {            
            if (action == "ok") {
            	var tree = nui.get("tree");
				var node = tree.getSelectedNode();
				var parentNode = tree.getParentNode(node);
				
				var data = {
					nodeId: node.nodeId,
					nodeType: node.nodeType,
					parentId: parentNode.nodeId,
					parentType: parentNode.nodeType
				}
				var json = nui.encode(data);
		        nui.ajax({
		            url: "org.gocom.components.coframe.org.organization.deleteNode.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	alertTip("删除成功");
		                refreshNode(searchParentOrgNode(node));
		                hideTabsIfCurrentNodeRemoved();
		                var currentTab = nui.get("orgTabs").getActiveTab();
						nui.get("orgTabs").reloadTab(currentTab);
		            },
		            error: function () {
		            	alertTip("删除员工失败，请联系管理员");
		            }
		        });	
             } 
          });
          return;
	}
	
	function removeEmpFromOrg() {
		empPrompt.style.display="";
	    nui.showMessageBox({
	      width:300,
	      title:'系统提示',
	      buttons:["ok","cancel"],
	      html:empPrompt,
	      showModal: false,
	      callback: function (action) {
	         //var isDeleteEmpCascade = document.getElementById("isDeleteEmpCascade").checked;
             if(action=="ok"){
                window['result']={
                  action:true
                };
             }else{
                window['result']={
                  action:false
                };
             }
             executeEmpRemove();
          }
	   });
	}
	
	var orgPrompt = document.getElementById("orgRemovePrompt");
	function onRemoveGroup(e){
	   orgPrompt.style.display="";
	   nui.showMessageBox({
	      width:300,
	      title:'系统提示',
	      buttons:["ok","cancel"],
	      html:orgPrompt,
	      showModal: false,
	      callback: function (action) {
	         //var isDeleteOrgCascade = document.getElementById("isDeleteOrgCascade").checked;
             if(action=="ok"){
                window['result']={
                  action:true
                };
                hideTabsIfCurrentNodeRemoved();
             }else{
                window['result']={
                  action:false
                };
             }
             executeOrgRemove();
          }
	   });
	}
	
	function executeOrgRemove(){
	    var result = window.result;
	    if(result.action){
		    var tree = nui.get("tree");
			var node = tree.getSelectedNode();
			var parentNode = tree.getParentNode(node);
			
			var data = {
				nodeId: node.nodeId,
				nodeType: node.nodeType,
				parentId: parentNode.nodeId,
				parentType: parentNode.nodeType,
				isDeleteCascade:window.result.isDeleteOrgCascade
			}
			
			var json = nui.encode(data);
	        nui.ajax({
	            url: "org.gocom.components.coframe.org.group.deleteNode.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	alertTip("删除成功");
	                tree.loadNode(parentNode);
	                hideTabsIfCurrentNodeRemoved();
	                var currentTab = nui.get("orgTabs").getActiveTab();
					nui.get("orgTabs").reloadTab(currentTab);
	            },
	            error: function () {
	            	alertTip("删除工作组失败，请联系管理员");
	            }
	        });	
        }
	}
	
	function onRemovePosition(e) {
		nui.confirm("确定删除所选岗位？", "提示？",
            function (action) {            
                if (action == "ok") {
                	var tree = nui.get("tree");
					var node = tree.getSelectedNode();
					var parentNode = tree.getParentNode(node);
					
					var data = {
						nodeId: node.nodeId,
						nodeType: node.nodeType,
						parentId: parentNode.nodeId,
						parentType: parentNode.nodeType
					}
					
					var json = nui.encode(data);
				
			        nui.ajax({
			            url: "org.gocom.components.coframe.org.group.deleteNode.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	alertTip("删除成功");
			                tree.loadNode(parentNode);
			                hideTabsIfCurrentNodeRemoved();
			                var currentTab = nui.get("orgTabs").getActiveTab();
							nui.get("orgTabs").reloadTab(currentTab);
			            },
			            error: function () {
			            	alertTip("删除岗位失败，请联系管理员");
			            }
			        });	
                } 
            }
        );			
	}
	
	/**
	 * 如果删除的结点正好是当前选中结点，则隐藏该被删除结点对应的Tab页
	 */
	function hideTabsIfCurrentNodeRemoved() {
		var selectedNode = getSelectedNode();
		var currentNode = getCurrentNode();
		if(selectedNode == currentNode) {
			$("#orgTabs").hide();
		}
	}
	function changeTab(e){
		nui.get("orgTabs").reloadTab(e.tab);
	}
</script>

</body>
</html>