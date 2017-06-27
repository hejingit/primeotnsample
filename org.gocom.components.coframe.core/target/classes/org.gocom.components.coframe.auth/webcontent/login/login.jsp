<%@page import="com.eos.system.utility.StringUtil"%>
<%@page import="com.eos.access.http.security.config.HttpSecurityConfig"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.primeton.cap.AppUserManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): shitf
  - Date: 2013-03-07 15:24:13
  - Description:
-->
<head>
<title>普元-用户登录</title>
<%
   String contextPath=request.getContextPath();
   String url = null;
   HttpSecurityConfig securityConfig = new HttpSecurityConfig();
   boolean isOpenSecurity = securityConfig.isOpenSecurity();
   if(isOpenSecurity){
   		boolean isAllInHttps = securityConfig.isAllInHttps();
   		if(!isAllInHttps){
   			String ip = securityConfig.getHost();
   			String https_port = securityConfig.getHttps_port();
   			url = "https://" + ip + ":" + https_port + contextPath + "/coframe/auth/login/org.gocom.components.coframe.auth.login.login.flow";
   		}else{
   			url = "org.gocom.components.coframe.auth.login.login.flow"; 
   		}
   }else{
   		url = "org.gocom.components.coframe.auth.login.login.flow";
   }
 %>
<script type="text/javascript" src="<%=contextPath%>/common/nui/nui.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/coframe/auth/login/css/style.css" />
</head>
<%
	String original_url=null;
	Object objAttr=request.getAttribute("original_url");
	if(objAttr != null){
		original_url=StringUtil.htmlFilter((String)objAttr);
	}
 %>
<body class="login">

<div id="warpper" class="wrap">
		<div class="main">
			<div id="form1" class="login-box">
				<h3>欢迎来到应用基础框架</h3>
				<form method="post"	name="loginForm" onsubmit="return login();" action="<%=url%>" >
					<input id="original_url" class="nui-hidden" name="original_url" value="<%=original_url %>"/>
					<p class="login-item">
					  <em>用户名：</em>
					  <input class="nui-textbox" id="userId" name="userId" style="width:237px;height:26px;"
					   onenter="keyboardLogin" onvalidation="onCheckUserId"/>
					</p>
					<p class="login-item">
					  <em>密　码：</em>
					  <input name="password" id="password"  class="nui-password" vtype="minLength:6" minLengthErrorText="密码不能少于6个字符"
			                onenter="keyboardLogin" style="width:237px;height:26px;" onvalidation="onCheckPassword" 
			                autocomplete="off" />
					</p>
					<p id="error" class="login-error" style="display:inline-block;height:20px;color:red;"></p>
					<p class="login-btn center">
						<input class="log" type="submit" value="登 录" />
					</p>
				</form>
			</div>
		</div>
		<div class="foot">
			<p>Copyright © 2003-2016 普元信息技术股份有限公司 版权所有<span></span></p>
		</div>
	</div>

<script type="text/javascript">
     if(window.top!=window){
		window.top.location = window.location;
	 }
     nui.parse();
  
     var form = new nui.Form("#form1");
     
     nui.get("userId").focus();
     
     function onCheckUserId(e){
       if (e.isValid) {
         if(e.value==""){
           e.errorText = "用户名不能为空";
           e.isValid = false;
         }
       }
     }
     
     function onCheckPassword(e){
       if (e.isValid) {
         if(e.value==""){
           e.errorText = "密码不能为空";
           e.isValid = false;
         }
       }
     }
     <% 
     	Object result = request.getAttribute("result");
     	String userName = (String)request.getAttribute("userId");
     	if (userName==null)userName="";
     	String password = (String)request.getAttribute("password");
     	if (password==null)password="";
     	if(result != null){
     		Integer resultCode = (Integer)result;
     		 if(resultCode == 0){
		     	out.println("showError('密码错误！')");
		     }else if(resultCode == -1){
		     	out.println("showError('用户不存在！')");
		     }else if(resultCode == -2){
		     	out.println("showError('用户无权限登录，请联系系统管理员！')");
		     }else if(resultCode == 3){
		     	out.println("showError('用户已过期！')");
		     }else if(resultCode == 4){
		     	out.println("showError('用户未到开始使用时间！')");
		     }else if(resultCode == 5){
		     	out.println("showError('密码已过期！')");
		     }else if(resultCode == -3){
      			out.println("showError('查询用户信息失败，请联系系统管理员检查数据库连接！')");
     		 }else{
      			out.println("showError('未知的异常，请联系系统管理员！')");
     		 }
     	}
	  %>
      function showError(msg){
      	 $("#error").html(msg);
      }
      
      //获取键盘 Enter 键事件并响应登录
     function keyboardLogin(e){
       login();
     }
     function login(){
     	var form = new nui.Form("#form1");
        form.validate();
        if (form.isValid() == false) 
        	return false;
        
        document.loginForm.submit();
     }
     $(function(){
 		var validateResult = "<%=result %>";
 		nui.get("userId").setValue("<%=userName %>");
 		nui.get("password").setValue("<%=password %>");
 	 });
</script>
 </body>
  <%
 	request.getSession().invalidate();
 	Cookie[] cookies = request.getCookies();
 	if(cookies != null){
 		for(int i=0;i<cookies.length;i++){
 			if(StringUtil.equals("jsessioinid", cookies[i].getName())){
 				cookies[i].setMaxAge(0);
 			}
 		}
 	
 	}
  %>
</html>
