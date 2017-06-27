<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="x-ua-compatible" content="IE=8;" />
<html>
<!-- 
  - Author(s): lenovo
  - Date: 2013-11-04 16:03:40
  - Description:
-->
<head>
	<%@include file="/coframe/tools/skins/common.jsp" %>
	<title>工作桌面</title>
</head>
<body>
	<div id="person-icon" style="position: absolute; z-index: 15001; visibility: hidden; left: 268px; bottom:464px; width:80px;height:80px">
		<img id="person-image" src="<%=contextPath %>/skins/win7/css/images/default_user.png" class="person-picture"/>
	</div>
	<div class="x-menu ux-start-menu" id="start-menu"
		style="position: absolute; z-index: 15000; visibility: hidden; left: 0px; bottom:41px; width: 381px;overflow:hidden;">
		<div class="ux-start-menu-tl">
			<div class="ux-start-menu-tr">
				<div class="ux-start-menu-tc">
					<div style="height:10px;"></div>
				</div>
			</div>
		</div>
		<div class="x-window-bwrap" style="overflow: hidden;">
			<div class="ux-start-menu-ml" style="overflow: hidden;">
				<div class="ux-start-menu-mc ux-start-menu-bwrap" style="overflow: hidden;">
					<div class="ux-start-menu-body"
						style="position: relative; height: 450px;overflow: hidden;">
						<div class="ux-start-menu-apps-panel start-window"
							style="padding: 2px; position: absolute; overflow: hidden; width: 213px; height: 444px;">
							<div style="overflow-y: auto;overflow-x: hidden;width: 202px; height: 394px;float:left;padding:6px;">
							<ul id="menuTree" onload="menuTreeOnload" class="nui-tree" url="org.gocom.components.coframe.auth.LoginManager.getMenuData.biz.ext" style="width:200px;height:390px;" 
			                    showTreeIcon="true" dataField="treeNodes" textField="menuName" idField="menuPrimeKey" expandOnNodeClick="true"
			                    resultAsTree="true" nodesField="childrenMenuTreeNodeList" showTreeLines="false" showTreeLines="false"
			                    showExpandButtons="false" expandOnLoad="true">        
			                </ul>
			                </div>
			                <div class="search-container" style="overflow: hidden;width: 213px; height:40px;float:left;padding-left:6px;padding-top:10px;">
			                	<input id="search-btn" showClose="true" class="mini-buttonedit searchbox"/>
			                </div>
						</div>
						<div
							class="x-border-panel ux-start-menu-tools-panel"
							style="overflow: hidden;padding: 2px 4px 2px 2px; position: absolute; width: 150px; left: 219px; height: 446px;">
							<div style="padding:30px 5px 5px 5px;">
							<div
								class="ux-menu-item x-window-header x-unselectable">
								<span class="ux-menu-item-text">
								<b><b:write property="userObject/userName" scope="s"/></b>
								</span>
							</div>
							<div
								class="ux-menu-item x-window-header x-unselectable" onclick="openBgSettings();">
								<span class="ux-menu-item-text">
								桌面背景设置
								</span>
							</div>
							<div
								class="ux-menu-item x-window-header x-unselectable" onclick="openIconSettings();">
								<span class="ux-menu-item-text">
								桌面图标设置
								</span>
							</div>
							<div
								class="ux-menu-item x-window-header x-unselectable" onclick="openStyleSettings();">
								<span class="ux-menu-item-text">
								风格偏好设置
								</span>
							</div>
							<div
								class="ux-menu-item x-window-header x-unselectable" onclick="openAutoStartSettings();">
								<span class="ux-menu-item-text">
								自动启动设置
								</span>
							</div>
							<div
								class="ux-menu-item x-window-header x-unselectable" onclick="changePassword();">
								<span class="ux-menu-item-text">
								修改密码
								</span>
							</div>
							<div
								class="ux-menu-item x-window-header x-unselectable" onclick="logout();">
								<span class="ux-menu-item-text">
								注销
								</span>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="ux-start-menu-bl x-panel-nofooter">
				<div class="ux-start-menu-br">
					<div class="ux-start-menu-bc"></div>
				</div>
			</div>
		</div>
		<a class="x-menu-focus" href="#" onclick="return false;"
			tabindex="-1">
		</a>
	</div>

</body>
</html>

    <style type="text/css">
    html, body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }    
    </style>


<script src="<%=contextPath %>/skins/win7/js/desktop.js" type="text/javascript"></script>
<link href="<%=contextPath %>/skins/win7/css/desktop/desktop.css" rel="stylesheet" type="text/css" />
<link id="color-link" rel="stylesheet" type="text/css" href="<%=contextPath %>/skins/win7/css/colors/black/color.css"/>
<!-- windows -->
<script src="<%=contextPath %>/skins/win7/js/iframe_window.js" type="text/javascript"></script>

<script type="text/javascript" src="<%=contextPath %>/skins/win7/js/win7.js">
</script>