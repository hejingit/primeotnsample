<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/coframe/tools/skins/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
	<title>添加IP访问规则</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link id="css_skin" rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/tools/skins/skin1/css/style.css"/>
</head>
<body >
<div class="nui-fit" style="padding-top:5px">
	<div id="webform">
		<input class="nui-hidden" name="rules.rulesId"/>
		<table style="width:100%;table-layout:fixed;" class="nui-form-table">
        	<tr>
            	<th class="nui-form-label"><label for="rules.startIP$text">起始IP：</label></th>
                <td><input id="startIP" class="nui-textbox nui-form-input" name="rules.startIP" required="true" onvalidation="onStartIPValidation" emptyText="例如：192.168.1.10" /></td>
            </tr>
            <tr class="odd">
                <th class="nui-form-label"><label for="rules.endIP$text">结束IP：</label></th>
                <td><input id="endIP" class="nui-textbox nui-form-input" name="rules.endIP" required="true" onvalidation="onEndIPValidation" emptyText="例如：192.168.1.20" /></td>
            </tr>
            <tr>
                <th class="nui-form-label"><label for="rules.rulesType$text">规则类型：</label></th>
                <td><input id="rulesType" name="rules.rulesType" class="nui-dictcombobox nui-form-input" value="prohibit" valueField="dictID" textField="dictName" dictTypeId="COFRAME_RULES_TYPE" /></td>
            </tr>    
            <tr class="odd">
                <th class="nui-form-label"><label for="rules.enabled$text">是否生效：</label></th>
                <td><input id="enabled" name="rules.enabled" class="nui-dictcombobox nui-form-input" value="Y" valueField="dictID" textField="dictName" dictTypeId="COFRAME_RULES_ENABLE" /></td>
            </tr>
            <tr>
                <th class="nui-form-label"><label for="rules.remark$text">备  注：</label></th>
                <td><input id="remark" name="rules.remark" class="nui-textarea nui-form-input"/></td>
            </tr>
        </table>
	</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button" style="width:60px;" iconCls="icon-save" onclick="doSave()">保存</a>
		<span style="display:inline-block;width:25px;"></span>
		<a class="nui-button" style="width:60px;" iconCls="icon-cancel" onclick="doCancel()">取消</a>
</div>
</body>
<script language=javascript>
	nui.parse();
	var form = new nui.Form("#webform");
	function doSave(){
        form.validate();
        if (form.isValid() == false) return;
		var rulesdata = form.getData(true,true);
		nui.ajax({
		        url: 'org.gocom.components.coframe.policy.ipRuleComponent.verificationIp.biz.ext',
                type: "post",
                data: rulesdata, 
                cache: false,
                contentType: 'text/json',
                success: function (text) {
                	if(text.flag == 'true'){
                		nui.alert('IP规则段出现重复,请重置IP段!');
                	}else{
                		saveData(rulesdata);
                	}
                },
               error: function (jqXHR, textStatus, errorThrown) {
	               nui.alert(jqXHR.responseText);
	               CloseWindow();
               }
        });
	}
	
	
	function saveData(rulesdata){
		nui.ajax({
		        url: 'org.gocom.components.coframe.policy.ipRuleComponent.editAccessRules.biz.ext',
                type: "post",
                data: rulesdata, 
                cache: false,
                contentType: 'text/json',
                success: function (text) {
                	window.CloseOwnerWindow("ok");
                },
               error: function (jqXHR, textStatus, errorThrown) {
	               nui.alert(jqXHR.responseText);
	               CloseWindow();
               }
        });
	}

    function SetData(data) {
	    data = nui.clone(data);
	    if (data) {
		    var formData = {rules : data};
		    form.setData(formData);
	    }
    }
    
	//关闭窗口
	function doCancel(){
 		if(window.CloseOwnerWindow){
 			return window.CloseOwnerWindow("Cancel");
 		}else{
 			window.close();
 		} 
	}    
             
	function checkIp(ip){   
   		var re = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
   		return re.test(ip);   
	}  


	var startIP,endIP="";
	
	//起始IP验证
	function onStartIPValidation(e){
		var sdata = form.getData(); 
		startIP = sdata.rules.startIP;
		if(checkIp(startIP)==false){//调用验证IP地址函数
			e.errorText = "请输入合法的IP地址!";
			e.isValid = false;
		}else{
			checkStartIp(e);
		}
	}
	
	//验证起始IP地址段是否比结束IP地址段小
	function checkStartIp(e){
		var data = form.getData();
		endIP = data.rules.endIP;
		if(checkIp(endIP)){
			startIP = data.rules.startIP;
			var startIPs =  startIP.split(".");  
			var endIPs = endIP.split(".");
			var flag = false;
			for(var i=0;i<startIPs.length;i++){
				if(parseInt(startIPs[i])<parseInt(endIPs[i])){
					flag = true;
					break;
				}
			}
			if(!flag){
				e.errorText = "输入的起始IP地址段要小于结束IP地址段!";
			  	e.isValid = false;
			}
		}
	}
  	
  	//结束IP验证
	function onEndIPValidation(e){
		var edata = form.getData();
		endIP = edata.rules.endIP;
		if(checkIp(endIP)==false){//调用验证IP地址函数
			e.errorText = "请输入合法的IP地址!";
			e.isValid = false;
		}else{
			checkEndIp(e);
		}
	}
	
	//验证结束IP地址段是否比起始IP地址段大
	function checkEndIp(e){
		var data = form.getData();
		startIP = data.rules.startIP;
		if(checkIp(startIP)){
			endIP = data.rules.endIP;
			var startIPs =  startIP.split(".");  
			var endIPs = endIP.split(".");
			var flag = false;
			for(var i=0;i<endIPs.length;i++){
				if(parseInt(endIPs[i])>parseInt(startIPs[i])){
					flag = true;
					break;
				}
			}
			if(!flag){
				e.errorText = "输入的结束IP地址段要大于起始IP地址段!";
			  	e.isValid = false;
			}
		}
	}
</Script>
</html>
