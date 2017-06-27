<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.system.utility.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): liuzn (mailto:liuzn@primeton.com)
  - Date: 2013-03-13 18:45:52
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
	<title>机构授权</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/coframe/tools/skins/common.jsp" %>
	<style type="text/css">
		#orgToolBar{
			width: 100%;
			margin: 0px;
			border: 0px none transparent;
			border-collapse: collapse;
		}
		#orgToolBar td{
			padding: 0px;
			border: 1px solid transparent;
		}
	</style>
</head>
<body>
<div id="panel1" class="nui-panel" style="width:100%;height:100%;" showHeader="false"
    showToolbar="true" showCollapseButton="false" showFooter="false">
    
    <div property="toolbar" style="padding:10px;">
    	<table style="width:100%;">
                <tr>
                <td style="width:100%;">
                	<a class="nui-button" iconCls="icon-save" onclick="saveTree" title="保存"></a>
                	<span class="separator"></span>
			        <a class="nui-button" iconCls="icon-expand" onclick="expandAll()" title="全部展开"></a>
					<a class="nui-button" iconCls="icon-collapse" onclick="collapseAll()" title="全部折叠"></a>
                </td>
                <td style="white-space:nowrap;">
                	机构名称：
                	<input id="key" class="nui-textbox" style="width:100px;" onenter="onKeyEnter" emptyText="请输入查询条件" />
					<a class="nui-button" style="width:60px;" iconCls="icon-search" onclick="search()">查询</a>
                </td>
            </tr>
        </table> 
    </div>
    
   <table style="width:100%;height:100%;*width:99%;*height:90%;">
      <tr>
         <td>所有机构</td>
         <td></td>
         <td>可管理机构</td>
      </tr>
      <tr>
         <td style="width:40%;height:100%;border:1px solid #878787;vertical-align:top;">
            <ul id="tree1" class="nui-tree" style="width:100%;" url="org.gocom.components.coframe.auth.partyauth.ManageableRes.getManageableOrg.biz.ext"
	          dataField="treeNodes" showTreeIcon="true" textField="nodeName" idField="nodeId" resultAsTree="false" onload="loaded" onbeforeload="onBeforeTreeLoad"
	          onnodedblclick="addManagerOrg" >
	        </ul>
         </td>
         <td style="width:20%;height:100%;text-align:center;">
            <input type="button" id="addBtn" style="width:65px;margin-top:15px;" class="nui-button" onclick="addManagerOrg" text="添加" /><br />
			 <input type="button" id="allDeleteBtn" class="nui-button" style="width:65px;margin-top:15px;" text="全部删除" onclick="deleteAllOrgResource"/><br />
			 <input type="button" id="deleteBtn" class="nui-button" style="width:65px;margin-top:15px;" text="删除" onclick="deleteOrgResource" /><br />
         </td>
         <td style="width:40%;">
            <div class="nui-fit">
              <div id="listbox1" class="nui-listbox" style="width:100%;height:100%;" 
               textField="nodeName" valueField="nodeId" multiSelect="true">
              </div>
            </div>
         </td>
      </tr>
   </table>
</div>
</div>
</body>
</html>
<script type="text/javascript">
	 nui.parse();
    
    var contextPath = "<%=request.getContextPath() %>";
    var tree = nui.get("tree1");
    var listbox = nui.get("listbox1");
    var flag=false;
    SetData();
    function onBeforeTreeLoad(e) {
    	if(e.data.nodeId==""||!e.data.nodeId){
    	 	e.url="org.gocom.components.coframe.auth.partyauth.ManageableRes.getManageableOrg.biz.ext";
    	}else{
    		e.url = "org.gocom.components.coframe.org.organization.getOrgStaticTree.biz.ext";
    	}
    	
    	// 增加nodeType参数以便区分是加载机构下的结点还是岗位下的结点
		e.params.nodeType = e.node.nodeType;
    }
    
    function addOrgResource(){
        var node = tree.getSelectedNode();
        if(!node){
            nui.alert("请选择一个机构");
        }
        var parentNodes = tree.getAncestors(node);
        var items = listbox.getData();
        for(var i=0,len=items.length;i<len;i++){
            if(node.nodeId==items[i].nodeId){
                nui.alert("已拥有对该机构的管理权限");
                return;
            }
            for(var j=0,len1=parentNodes.length;j<len1;j++){
	            if(parentNodes[j].nodeId==items[i].nodeId){
	                nui.alert("已拥有对该机构的父机构的管理权限");
	                return;
	            }
            }
        }
        
        tree.cascadeChild(node, function (childNode) {
            for(var i=0,len=items.length;i<len;i++){
                if(childNode.nodeId==items[i].nodeId){
                    listbox.removeItems([items[i]]);
                }
            }
        });

        listbox.addItem(node);
    }
    
    function addManagerOrg(){
    	var node = tree.getSelectedNode();
    	if(!tree.isExpandedNode (node)){
    		flag=true;
    		tree.loadNode (node);
    	}else{
    		addOrgResource();
    	}
    }
    
    function loaded(e){
    	if(flag){
    		addOrgResource();
    		flag=false;
    	}
    }
    
    function SetData(e){
       var json = nui.encode({roleId:"<%=request.getParameter("roleId") %>"});
       var orgs;
       $.ajax({
            url: "org.gocom.components.coframe.org.organizationAuth.getManagerOrgByRole.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (res) {
            	if(res.exception || res.data == false) {
            		nui.alert("数据加载失败");
            	} else {
            		orgs=res.orgs;
            		if(orgs!=""){
				       for(var i=0;i<orgs.length;i++){
				          var node ={
				             nodeId:orgs[i].orgid,
				             nodeName:orgs[i].orgname
				          };
				          listbox.addItem(node);
				       }
			       }
            	}  
            },
            error: function () {
            	nui.alert("数据加载失败");
            }
        });
       
    }
    
	function GetData() {
		var items = listbox.getData();
		var ids = [];
		for(var i=0;i<items.length;i++){
			ids.push(items[i].nodeId);
		}
        return ids;
    }
    
    function deleteOrgResource(){
        var nodes = listbox.getSelecteds();
        if(nodes.length==0){
            nui.alert("请选择至少一个可管理机构");
            return;
        }
        listbox.removeItems(nodes);
    }
    
    function deleteAllOrgResource(){
        listbox.removeAll();
    }
    
    function expandAll(){
		tree.expandAll();
	}
	
	function collapseAll(){
		tree.collapseAll();
	}
	
	function search(){
		var filtedNodes = [];
		var key = nui.get("key").getValue();
		if(key == ""){
			tree.clearFilter();
		}else{
			var rootNode = tree.getRootNode();
			tree.cascadeChild(
				rootNode,
				function(node){
					var pNode = tree.getParentNode(node);
					var nofind = true;
					for(i = 0; i < filtedNodes.length; i++){
						if(filtedNodes[i] == pNode.nodeId){
							filtedNodes.push(node.nodeId);
							nofind = false;
							break;
						}
					}
					if(nofind){
						var text = node.nodeName ? node.nodeName.toLowerCase() : "";
						if(text.indexOf(key.toLowerCase()) != -1){
							filtedNodes.push(node.nodeId);
						}
					}
				}
			);
			tree.filter(function(node){
				for(i = 0; i < filtedNodes.length; i++){
					if(filtedNodes[i] == node.nodeId){
						return true;
					}
				}
			});
		}
	}
	
	function saveTree(e){
		e.sender.setEnabled(false);
		var orgDatas = GetData();
		var json = nui.encode({orgs:orgDatas,roleId:"<%=request.getParameter("roleId") %>"});
        $.ajax({
            url: "org.gocom.components.coframe.org.organizationAuth.saveOrganizationAuth.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (res) {
            	if(res.exception || res.data == false) {
            		nui.alert("权限设置失败");
            	} else {
            		nui.alert("权限设置成功");
            	}  
            	e.sender.setEnabled(true);          	
            },
            error: function () {
            	nui.alert("权限设置失败");
            	e.sender.setEnabled(true);
            }
        });
	}
</script>
