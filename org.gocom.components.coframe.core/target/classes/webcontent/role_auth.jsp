<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="org.gocom.components.coframe.tools.tab.TabPageManager"%>
<%@page import="org.gocom.components.coframe.tools.tab.TabPage"%>
<% List<TabPage> tabList = TabPageManager.INSTANCE.getTabPageList("AuthTab");%>
<html>
<!-- 
  - Author(s): liuzn (mailto:liuzn@primeton.com)
  - Date: 2013-03-07 09:58:59
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
	<title>角色授权</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/coframe/tools/skins/common.jsp" %>
</head>
<body>
<style>
#authTabs .mini-tabs-bodys{
	padding:0px;
}
</style>
<div class="nui-fit" style="width:100%; height:100%; padding:0px 0px 0px 0px;">
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div id="region1" region="west" title="角色授权" showHeader="true" class="sub-sidebar" 
	width="400" allowResize="false">

		<div class="search-condition">
			<div class="list">
				<div id="form1">
					<table id="table1" class="table">
						<tr>
							<td  >
								<input class="nui-textbox" name="criteria._expr[0].roleCode" emptyText="角色代码" style="width:120px;" />
								<input class="nui-hidden" name="criteria._expr[0]._op" value="like" />
								<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all" />
							</td>
							<td  >
								<input class="nui-textbox" name="criteria._expr[1].roleName" emptyText="角色名称" style="width:120px;"/>
								<input class="nui-hidden" name="criteria._expr[1]._op" value="like" />
								<input class="nui-hidden" name="criteria._expr[1]._likeRule" value="all" />
							</td>
							<td></td>
							<td >
								<input class="nui-button" iconCls="icon-search" text="查询" onclick="search" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>	
		
		<div class="nui-fit" style="padding:0px 5px 5px 5px;">
			<div id="roleGrid" class="nui-datagrid" style="width:100%;height:99%;"
				url="org.gocom.components.coframe.rights.RoleManager.queryAuthorizedRole.biz.ext"
				idField="roleId" multiSelect="true" allowAlternating="true" showPager="false"
				showReloadButton="false" showPageSize="false" showPageInfo="false"
				ondrawcell="drawRoleAuthConfig">
				<div property="columns">
					<div field="roleCode" width="38%">角色代码</div>
					<div field="roleName" width="38%">角色名称</div>
					<div name="roleAuthConfig" width="24%">授权配置</div>
				</div>
			</div>
		</div>
	 </div>
    <div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="authTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;" onactivechanged="changeTab">
			<%	for(TabPage tab : tabList){ %>
					<div id="<%= tab.getId() %>" name="<%= tab.getId() %>" title="<%= tab.getTitle() %>" url=""></div>
			<%	} %>
			<%-- <div id="funcTab" name="funcTab" title="功能" url=""></div>
			<div id="formTab" name="formTab" title="表单" url=""></div>
			<div id="viewTab" name="viewTab" title="视图" url=""></div>
			<div id="empTab" name="empTab" title="员工" url=""></div>
			<div id="orgTab" name="orgTab" title="机构" url=""></div>
			<div id="positionTab" name="positionTab" title="岗位" url=""></div> --%>
		</div>
	</div>
</div>
</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var form1 = new nui.Form("#form1");
	var roleGrid = nui.get("roleGrid");
	var authTabs = nui.get("authTabs");
	roleGrid.load();
	var authTabArray = [];
	<%	for(TabPage tab : tabList){ %>
			var <%= tab.getId() %> = {name:"<%= tab.getId() %>", title:"<%= tab.getTitle() %>", path:"<%=request.getContextPath() %><%= tab.getUrl() %>"};
			authTabArray.push(<%= tab.getId() %>);
	<%	} %>

	<%-- 设置Tab的URL及参数 --%>
	function setUrlParam(url,paramObj){
		if(!url){
			return url;
		}
		var params = [];
		for(var pop in paramObj){
			params.push(pop + "=" + paramObj[pop]);
		}
		var paramStr = params.join("&");
		if(url.indexOf("?")>=0){
			return url + "&" + paramStr;
		}else{
			return url + "?" + paramStr;
		}
	}

	<%-- 装载选定角色相应的tab页 --%>
	function reloadTab(paramObj){
		for(var i = 0; i < authTabArray.length; i++){
			var authTabElem = authTabArray[i];
			var settingTab = authTabs.getTab(i);
			settingTab.url = setUrlParam(authTabElem.path, paramObj);
		}
		var currentTab = authTabs.getActiveTab();
		authTabs.reloadTab(currentTab);
	}

	function drawRoleAuthConfig(e){
		if(e.column.name == "roleAuthConfig"){
			//alert(e.record.roleId);
			e.cellHtml = "<a style='color:#1B3F91;text-decoration:underline;' href='#' id='"+e.record.roleId+"' onclick='authConfig(this)'>配置</a>";
		}
	}

	function authConfig(obj){
		var layout1 = nui.get("layout1");
		var paramObj = {roleId:obj.id};
		reloadTab(paramObj);
	}

	function changeTab(e){
		authTabs.reloadTab(e.tab);
	}
	
	function search(){
		var form1Data = form1.getData(false, true);
        roleGrid.load(form1Data);
	}
</script>
