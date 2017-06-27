<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/coframe/tools/skins/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-01 10:31:32
  - Description:编辑应用，tab页和弹出面板使用
-->
<head>
</head>
<body>
	<div class="nui-fit" style="padding-top:5px">
		<div id="form1">
	    	<table style="width:100%;table-layout:fixed;" class="nui-form-table" >
	            <tr>
	                <th class="nui-form-label"><label for="appapplication.appname$text">应用名称：</label></th>
	                <td>    
	                    <input id="appapplication.appname" name="appapplication.appname" class="nui-textbox nui-form-input" required="true"  vtype="rangeLength:1,20"/>
	                    <input name="appapplication.appid" class="nui-hidden"/>
	                    <input id="type" name="type" class="nui-hidden" />
	                </td>
	                <th class="nui-form-label"><label for="appapplication.appcode$text">应用代码：</label></th>
	                <td >                        
	                    <input id="appapplication.appcode" name="appapplication.appcode" class="nui-textbox nui-form-input" required="true" vtype="rangeLength:1,20"/>
	                </td>
	                
	            </tr>
	            <tr class="odd">
	                <th class="nui-form-label"><label for="appapplication.opendate$text">开通日期：</label></th>
	                <td>
	                	<input id="appapplication.opendate" name="appapplication.opendate" class="nui-datepicker nui-form-input" allowInput="false"/>   
	                </td>
	                <th class="nui-form-label"><label for="appapplication.protocoltype$text">协议类型：</label></th>
	                <td>
	                	<input id="appapplication.protocoltype" name="appapplication.protocoltype" id="combo1" class="nui-combobox nui-form-input" textField="text" valueField="id" 
	                    data="combo_protocoltype" value="http"/>                       
	                </td>
	            </tr>
	            <tr>
	                <th class="nui-form-label"><label for="appapplication.apptype$text">应用类别：</label></th>
	                <td> 
	                	<input id="appapplication.apptype" class="nui-dictcombobox nui-form-input" name="appapplication.apptype" value="0" 
                   		 valueField="dictID" textField="dictName" dictTypeId="COF_APPTYPE" onvaluechanged="appTypeChange"/>
	                </td>
	                <th class="nui-form-label"><label for="appapplication.isopen$text">是否开通：</label></th>
	                <td> 
	                	<input id="appapplication.isopen" class="nui-dictcombobox nui-form-input" name="appapplication.isopen" value="1" 
                   		 valueField="dictID" textField="dictName" dictTypeId="COF_YESORNO"/>   
	                </td>
	            </tr>
	            <tr class="odd">
	                <th class="nui-form-label"><label for="appapplication.ipaddr$text">应用IP或域名：</label></th>
	                <td>    
	                    <input id="appapplication.ipaddr" name="appapplication.ipaddr" class="nui-textbox nui-form-input" onvalidation="ipvalidation"/>
	                </td>
	                <th class="nui-form-label"><label for="appapplication.ipport$text">应用端口：</label></th>
	                <td >                        
	                    <input id="appapplication.ipport" name="appapplication.ipport" class="nui-textbox nui-form-input" vtype="range:0,65535" />
	                </td>
	            </tr>
	            <tr>
	                <th class="nui-form-label"><label for="appapplication.url$text">应用上下文：</label></th>
	                <td colspan="3">                        
	                    <input id="appapplication.url" name="appapplication.url" class="nui-textbox nui-form-input" style="width:420px;" onvalidation="urlvalidation"/>
	                </td>
	            </tr>
	            <tr class="odd">
	                <th class="nui-form-label"><label for="appapplication.appdesc$text">应用描述：</label></th>
	                <td colspan="3">    
	                    <input id="appapplication.appdesc" name="appapplication.appdesc" class="nui-textarea nui-form-input" style="width:420px;height:100px;" vtype="rangeLength:1,255"/>
	                </td>
	            </tr>
	        </table> 
	              
	    </div>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button" style="width:60px;" iconCls="icon-save" onclick="onOk()">保存</a>
		<span style="display:inline-block;width:25px;"></span>
		<a class="nui-button" style="width:60px;" iconCls="icon-cancel" onclick="onCancel()">取消</a>
	</div>
    <script type="text/javascript">
    	var combo_protocoltype = [{id:"http",text:"http"},{id:"https",text:"https"}];
        nui.parse();
        var form = new nui.Form("form1");
        function SaveData() {
            var o = form.getData(true,true);            

            form.validate();
            if (form.isValid() == false) return;

            var json = nui.encode(o);
            //alert(JSON.stringify(json));
            $.ajax({
                url: "org.gocom.components.coframe.framework.ApplicationManager.updateApplication.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (text) {
                    CloseWindow("ok");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }
         ////////////////////
        //标准方法接口定义
        function SetData(data) {
            //跨页面传递的数据对象，克隆后才可以安全使用
                data = nui.clone(data);
				var json = nui.encode({template:data});
				//alert(JSON.stringify(json));
                $.ajax({
                    url: "org.gocom.components.coframe.framework.ApplicationManager.getApplication.biz.ext",
                    type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    cache: false,
                    success: function (text) {
                        var o = nui.decode(text);
                        form.setData(o);
                        if(data.type){
			                var type = nui.get("type");
			                type.setValue(data.type);
		                }
		                appTypeChange();
                        form.setChanged(false);
                    }
                });
        }
        
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
       function ipvalidation(e){
        	if(e.value == "") return ;
        	 if (e.isValid) {
                var ip_pattern = /^((0[0-9]|1[0-9]\d{1,2})|(2[0-5][0-5])|(2[0-4][0-9])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-5][0-5])|(2[0-4][0-9])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-4][0-9])|(2[0-5][0-5])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-4][0-9])|(2[0-5][0-5])|(\d{1,2}))$/;
            	var domain_pattern = /^([\w-]+\.)+((com)|(net)|(org)|(gov\.cn)|(info)|(cc)|(com\.cn)|(net\.cn)|(org\.cn)|(name)|(biz)|(tv)|(cn)|(mobi)|(name)|(sh)|(ac)|(io)|(tw)|(com\.tw)|(hk)|(com\.hk)|(ws)|(travel)|(us)|(tm)|(la)|(me\.uk)|(org\.uk)|(ltd\.uk)|(plc\.uk)|(in)|(eu)|(it)|(jp))$/;
            	var flag_ip = ip_pattern.test(e.value);
            	var flag_domain = domain_pattern.test(e.value);
                if (!flag_ip&&!flag_domain) {
			   		e.errorText = "不是正确的IP或域名";
			   		e.isValid = false;
                }
            }
        }
        
        function urlvalidation(e){
        	if(e.value == ""||e.value == "/") return;
        	if(e.isValid){
        		var pattern = /^\/\w{2,30}$/;
        		 if (pattern.test(e.value) == false) {
                    e.errorText = "必须以/开头,如非根应用需要2-30个字符";
                    e.isValid = false;
                }
        	}
        }
        function appTypeChange(e){
        	var appType = nui.get("appapplication.apptype").getValue();
        	
    		nui.get("appapplication.ipaddr").set({"required":appType=="1"});
    		nui.get("appapplication.ipport").set({"required":appType=="1"});
    		nui.get("appapplication.url").set({"required":appType=="1"});
    		
    		nui.get("appapplication.ipaddr").set({"enabled":appType=="1"});
    		nui.get("appapplication.ipport").set({"enabled":appType=="1"});
    		nui.get("appapplication.url").set({"enabled":appType=="1"});
        }
    </script>
</body>
</html>