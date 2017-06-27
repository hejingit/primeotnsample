<%@page import="com.eos.system.Constants"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.eos.access.http.security.config.HttpSecurityConfig"%>
<%@page import="com.eos.system.utility.StringUtil"%>
<%@page pageEncoding="UTF-8"%>

<%
	String original_url=null;
	Object objAttr=request.getParameter("original_url");
	if(objAttr != null){
		original_url=StringUtil.htmlFilter((String)objAttr);
	}
	
	String contextPath = StringUtil.htmlFilter(request.getContextPath());
	String url = null;
	if(StringUtils.isNotBlank(original_url) && !StringUtils.equals(original_url, "null")){
		//有可能不存在或上来就访问了login.jsp
		if(original_url.indexOf("login.jsp")==-1){
			url=contextPath + original_url;
		}else{
			url=contextPath + "/coframe/auth/index.jsp";
		}
	}else{
		url=contextPath + "/coframe/auth/index.jsp";
	}
	//协议跳转
	HttpSecurityConfig securityConfig = new HttpSecurityConfig();
   	boolean isOpenSecurity = securityConfig.isOpenSecurity();
   	if(isOpenSecurity){
   		boolean isAllInHttps = securityConfig.isAllInHttps();
   		if(!isAllInHttps){
   			String ip = securityConfig.getHost();
   			String http_port = securityConfig.getHttp_port();
   			url = "http://" + ip + ":" + http_port + url;
			String serverType = HttpSecurityConfig.INSTANCE.getServerType();
			System.out.println(serverType);
			if(!(StringUtils.equals(serverType, Constants.SERVER_TYPE_WEBLOGIC) || StringUtils.equals(serverType, Constants.SERVER_TYPE_WEBSPHERE))){
				String sessionID = session.getId();
				Cookie cookie = new Cookie("JSESSIONID", sessionID);
				cookie.setPath(contextPath);
				response.addCookie(cookie);
	   		}
		}
   	}
	response.sendRedirect(url);
%>
