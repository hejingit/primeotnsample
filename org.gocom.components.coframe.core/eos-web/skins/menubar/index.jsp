<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.primeton.cap.AppUserManager"%>
<meta http-equiv="x-ua-compatible" content="IE=8;" />
<html>
<head>
<%@include file="/coframe/tools/skins/common.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>普元-用户机构及权限管理系统</title>
<style type="text/css">
.dtHighLight{
	background:#F0F8FF !important;
}
.set {
    background: url(<%=contextPath%>/coframe/tools/skins/skin1/images/user-set.gif) no-repeat !important;
    background-position-y: center !important;
    color: #025087;
    padding-left: 20px;
    margin-right: 5px;
}
.login-out {
    background: url(<%=contextPath%>/coframe/tools/skins/skin1/images/user-loginOut.gif) no-repeat !important;
    background-position-y: center !important;
    color: #025087;
    padding-left: 20px;
    margin-right: 5px;
}
</style>
</head>
<body class="index"> 
	<div id="header">
		<div class="head-in clearfix">
			<div class="fl clearfix">
				<h1 class="logo"></h1>
				<h2 class="name">应用基础框架</h2>
			</div>
			
	        <div style="position:absolute;right:12px;top:10px;font-size:12px;line-height:28px;font-weight:normal;margin-right: 5px;">
	        	<span class="font-1"><strong>您好，<%=AppUserManager.getCurrentUserId() %></strong></span>        	
	        </div>
	        <div style="position:absolute;right:12px;top:40px;font-size:12px;line-height:28px;font-weight:normal;">
				<a class="set" href="#" onclick="javascript:updatepwd()">修改密码</a>&nbsp;&nbsp;<a class="login-out" href="<%=contextPath%>/coframe/auth/login/logout.jsp" target="_top">注销</a>
	        </div>
	        						 
		</div>
	</div>
	
	<div id="container">
	    <div id="menu1" class="nui-menubar" style="width:100%;"
	            url="org.gocom.components.coframe.auth.LoginManager.getMenuList.biz.ext" onitemclick="onItemClick" 
	            dataField="treeNodes" idField="id" parentField="pid" 
	        >
	    </div>				 
		<!--主内容区-->
		<div class="nui-fit">
			<!--面包屑导航条-->
			<div class="positionbar">
				<ul class="position clearfix" id="positionbar">
					<li class="index">
						<a><span>首页</span></a><b class="arrow"></b>
					</li>
				</ul>
			</div>
			<!--主体内容显示区-->
			<div class="submain radius">
				<b class="b1"></b>
				<b class="b2"></b>
				<div class="fmain">
					<div class="nui-fit" style="padding:5px;">
					<iframe id="mainframe" src="<%=contextPath %>/coframe/auth/welcome/welcome.jsp" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
					</div>
				</div>
				<b class="b3"></b>
				<b class="b4"></b>
			</div>
		</div>
	</div>
	
	<div id="footer">
		<p>Copyright © 2003-2016 普元信息技术股份有限公司 版权所有 <%=com.primeton.ext.common.l7e.ImprimaturMgr.getImprimatur().getEditionInfo(com.eos.data.datacontext.DataContextManager.current().getCurrentLocale())%></p>
	</div> 
</body> 
</html>
<script type="text/javascript">
	nui.parse();
	
	var contextPath = "<%=contextPath%>";
	
	var iframe = document.getElementById("mainframe");
	       
	function onItemClick(e){
   		var item = e.item;
   		var isLeaf = item.isLeaf;
   		var url = item.url;
   		if(isLeaf){
   			if(url.indexOf("http") < 0 ){
   				iframe.src = contextPath + "/" + item.url;
   			}else{
   				iframe.src = item.url;
   			}	
	   		setPositionBar("<li><!--[if lt IE 8]><span class='left'></span><![endif]--><a>" + item.text + "</a><b class='arrow'></b></li>",item);
   		}
   	}
   	
	function setPositionBar(position,node){
   		if (node.ownerMenu&&node.ownerMenu.ownerItem){
   			var positionHtml = "<li><!--[if lt IE 8]><span class='left'></span><![endif]--><a>" + node.ownerMenu.ownerItem.text + "</a><b class='arrow'></b></li>"+position;
   			var parentNode = node.ownerMenu.ownerItem;
   			setPositionBar(positionHtml, parentNode);
   		}else{
			var positionHtml = "<li class='index'><a><span>首页</span></a><b class='arrow'></b></li>"+position;
			$("#positionbar").html(positionHtml);
   		}
   	}
   	
   	function updatepwd(){
   		var jspUrl = "<%=contextPath%>/coframe/rights/user/update_password.jsp";
		nui.open({
			url: jspUrl,
			title:"修改密码",
			width: "370px",
			height: "200px"
		});
   	}
	
</script>