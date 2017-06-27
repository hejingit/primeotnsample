<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/coframe/tools/skins/common.jsp"%>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>
自动启动配置
</title>
<link id="css_skin" rel="stylesheet" type="text/css"
	href="<%=contextPath%>/coframe/tools/skins/skin1/css/style.css" />
</head>
<body>
	<div class="nui-fit"> 
		<ul id="menuTree" onload="autoStartTreeOnload"
		class="nui-tree" showCheckBox="true"
		url="org.gocom.components.coframe.auth.LoginManager.getMenuData.biz.ext"
		style="width:208px;padding:5px;" showTreeIcon="true"
		dataField="treeNodes" textField="menuName" idField="menuPrimeKey"
		expandOnNodeClick="true" resultAsTree="true"
		nodesField="childrenMenuTreeNodeList" showTreeLines="false"
		showTreeLines="false" showExpandButtons="false" checkRecursive="false" autoCheckParent="false" expandOnLoad="true"> </ul> 
	</div>
	<div class="nui-toolbar"
	style="text-align:center;padding-top:5px;padding-bottom:5px;"
	borderStyle="border:0;">
	<a class="nui-button" style="width:60px;" iconCls="icon-save"
		onclick="onOk()">
		保存
	</a>
	<a class="nui-button" style="width:60px;" iconCls="icon-cancel"
		onclick="onCancel()">
		取消
	</a>
</div>
</body>
</html>
<script type="text/javascript">
    nui.parse();
     function autoStartTreeOnload(sender,xhr,treeData){
    	nui.ajax({
         url:"org.gocom.components.coframe.skins.win7.win7Service.getMyConfig.biz.ext",
         type:'POST',
         data:{},
         success:function(text){
            var result = nui.decode(text);
            if(!result.exception){
	           initDesktopAutoStarts(result.autoStarts);
            }else{
               nui.alert(result.exception.message);
            }
         }
       });
    }
    function onOk(){
    	var menuTree = nui.get("menuTree");
    	var nodes = menuTree.getCheckedNodes();
    	var autoStarts = [];
    	if(nodes){
    		for(var i=0;i<nodes.length;i++){
    			var node = nodes[i];
    			autoStarts.push({
    				menuId:node.menuPrimeKey
    			});
    		}
    	}
    	nui.ajax({
         url:"org.gocom.components.coframe.skins.win7.win7Service.updateAutoStartConfig.biz.ext",
         type:'POST',
         cache: false,
         contentType:'text/json',
         data:nui.encode({autoStarts:autoStarts}),
         success:function(text){
            var result = nui.decode(text);
            if(!result.exception){
	           CloseWindow("ok");
            }else{
               nui.alert(result.exception.message);
            }
         }
       });
    }
    function initDesktopAutoStarts(autoStarts){
    	if(!autoStarts){
    		return;
    	}
    	var menuTree = nui.get("menuTree");
    	for(var i=0;i<autoStarts.length;i++){
    		var menuNode = menuTree.getNode(autoStarts[i].menuId);
    		if(menuNode){
    			menuTree.checkNode(menuNode);
    		}
    	}
    }
    function onCancel(){
    	CloseWindow("cancel");
    }
     function CloseWindow(action) { 
         if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
         else window.close();
         return true;            
     }
</script>
