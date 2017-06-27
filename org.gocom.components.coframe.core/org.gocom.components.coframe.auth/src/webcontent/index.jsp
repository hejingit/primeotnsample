<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page import="org.gocom.components.coframe.tools.IConstants" %>
<%@page pageEncoding="UTF-8"%>
<%
	String skin = "default";
	Object userObj = session.getAttribute("userObject");
	if(userObj != null){
		UserObject userObject = (UserObject)userObj;
		if(userObject.getAttributes().get(IConstants.MENU_TYPE) != null){
			skin = (String)userObject.getAttributes().get(IConstants.MENU_TYPE);
		}
	}
	
	response.sendRedirect(request.getContextPath() + "/skins/"+skin+"/index.jsp");
 %>