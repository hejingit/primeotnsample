<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/coframe/tools/skins/common.jsp"%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title>风格偏好配置</title>
	<link id="css_skin" rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/tools/skins/skin1/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/skins/win7/css/colors/icons.css"/>
	
</head>
<body>
<div class="nui-fit"> 
<fieldset align="center"><legend>界面风格设置</legend>

<a onclick="selectStyle('black')" id="black-btn" class="style-button style-button-select" checkOnClick="true" groupName="style" iconCls="icon-black" >
	<div class="icon-black">&nbsp;</div>
</a>
<a onclick="selectStyle('red')" id="red-btn" class="style-button" checkOnClick="true" groupName="style"  iconCls="icon-green" >
	<div class="icon-red">&nbsp;</div>
</a>
<div style="height:10px;font-size:1">&nbsp;</div>
<a onclick="selectStyle('green')" id="green-btn" class="style-button" checkOnClick="true" groupName="style"  iconCls="icon-red" >
	<div class="icon-green">&nbsp;</div>
</a>
<a onclick="selectStyle('blue')" id="blue-btn" class="style-button" checkOnClick="true" groupName="style"  iconCls="icon-blue" >
	<div class="icon-blue">&nbsp;</div>
</a>
</fieldset>
</div>
</body>
</html>
<script>
	nui.parse();
	var myConfig = {
		openType:"single",
		defaultMax : false,
		defaultWidth:800,
		defaultHeight:600,
		deskStyle:"black"
	};
	function loadMyConfig(){
    	nui.ajax({
         url:"org.gocom.components.coframe.skins.win7.win7Service.getMyConfig.biz.ext",
         type:'POST',
         data:{},
         success:function(text){
            var result = nui.decode(text);
            if(!result.exception){
	           initConfig(result.config);
            }else{
               nui.alert(result.exception.message);
            }
         }
       });
    }
	window.onload = function(){
		loadMyConfig();
	}
	function initConfig(config){
		if(config){
			myConfig = config;
		}
		$.extend(myConfig,config);
		if(myConfig.deskStyle){
			changeSelect(myConfig.deskStyle);
		}
	}
	
	function changeSelect(styleName){
		$(".style-button").removeClass("style-button-select");
		$("#" + styleName + "-btn").addClass("style-button-select");
	}
	
	function selectStyle(styleName){
		changeSelect(styleName);
		myConfig.deskStyle = styleName;
		onOk();
	}
	
	
	 function onOk(){
    	nui.ajax({
         url:"org.gocom.components.coframe.skins.win7.win7Service.updateDesktopConfig.biz.ext",
         type:'POST',
         cache: false,
         contentType:'text/json',
         data:nui.encode({config:myConfig}),
         success:function(text){
            var result = nui.decode(text);
            if(!result.exception){
               parent.initDesktopConfig(myConfig);
	           CloseWindow("ok");
            }else{
               nui.alert(result.exception.message);
            }
         }
       });
    }
     function CloseWindow(action) { 
         if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
         else window.close();
         return true;            
     }
</script>