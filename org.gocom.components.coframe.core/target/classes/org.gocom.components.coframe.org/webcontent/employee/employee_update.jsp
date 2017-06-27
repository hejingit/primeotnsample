<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>员工修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/coframe/tools/skins/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/coframe/org/js/org_common.js"></script>
<style type="text/css">
    fieldset
    {
        border:solid 1px #aaa;
    }        
    .hideFieldset
    {
        border-left:0;
        border-right:0;
        border-bottom:0;
    }
    .hideFieldset .fieldset-body
    {
        display:none;
    }
</style>
</head>
<body>

<div class="nui-fit" style="padding-top:5px">
<form id="form">
<div class="nui-tabs">
	<div title="基本信息"  showCloseButton="false" style="border-buttom:0px;">
		<div id="form1">
			<input class="nui-hidden" name="employee.orgid" />
			<input class="nui-hidden" name="employee.empid" />
			<table style="width:100%;table-layout:fixed;" class="nui-form-table" >
				<tr>
					<td class="nui-form-label"><label for="empname$text">员工姓名：</label></td>
					<td><input id="empname" class="nui-textbox" name="employee.empname" required="true" vtype="maxLength:50"/></td>
					<td class="nui-form-label"><label for="empcode$text">员工代码：</label></td>
					<td><input id="empcode" class="nui-textbox" name="employee.empcode" required="true" vtype="maxLength:30"/></td>
				</tr>		
				<tr class="odd">
					<td class="nui-form-label"><label for="gender$text">性别：</label></td>
					<td><input id="gender" name="employee.gender" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_GENDER" /></td>
					<td class="nui-form-label"><label for="birthdate$text">出生日期：</label></td>
					<td><input id="birthdate" name="employee.birthdate" class="nui-datepicker" /></td>
				</tr>				
				<tr>
					<td class="nui-form-label"><label for="cardtype$text">证件类型：</label></td>
					<td><input id="cardtype" name="employee.cardtype" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_CARDTYPE" /></td>
					<td class="nui-form-label"><label for="cardno$text">证件号码：</label></td>
					<td><input id="cardno" name="employee.cardno" class="nui-textbox" vtype="maxLength:20"/></td>
				</tr>				
				<tr class="odd">
					<td class="nui-form-label"><label for="indate$text">入职日期：</label></td>
					<td><input id="indate" name="employee.indate" class="nui-datepicker" /></td>
					<td class="nui-form-label"><label for="outdate$text">离职日期：</label></td>
					<td><input id="outdate" name="employee.outdate" class="nui-datepicker"  onvalidation="onOutdateValidation" />
					</td>
				</tr>				
				<tr>
					<td class="nui-form-label"><label for="empstatus$text">人员状态：</label></td>
					<td><input id="empstatus" name="employee.empstatus" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_EMPSTATUS" /></td>
					<td class="nui-form-label"><label for="mobileno$text">手机号码：</label></td>
					<td><input id="mobileno" name="employee.mobileno" class="nui-textbox" vtype="maxLength:14" /></td>
				</tr>		
				<tr class="odd">
					<td class="nui-form-label"><label for="sortno$text">排列顺序：</label></td>
					<td><input id="sortno" class="nui-textbox" name="employee.sortno" vtype="int" /></td>
					<td></td>
					<td></td>
				</tr>				
			</table>
		</div>
		<div id="form3" style="padding-top:5px;">	
			<fieldset border="0">
				<input class="nui-hidden" name="user.operatorId" />
				<input class="nui-hidden" name="user.password" />
		        <legend><label><input type="checkbox" checked id="userRefCheckbox" onclick="toggleFieldSet(this, 'form3')" hideFocus/>&nbsp;用户信息</label></legend>
		        <div class="fieldset-body">
		            <table style="width:100%;table-layout:fixed;" class="nui-form-table" >
						<tr>
							<td class="nui-form-label"><label for="userId$text">用户登录名：</label></td>
							<td><input id="userId" class="nui-textbox" name="user.userId" required="true" vtype="maxLength:30" /></td>
							<td class="nui-form-label"><label for="userstatus$text">用户状态：</label></td>
							<td><input id="userstatus" name="user.status" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_USERSTATUS" value="1"/></td>
						</tr>
						<tr class="odd">
							<td class="nui-form-label"><label for="authmode$text">认证模式：</label></td>
							<td>
							<input id="user.authmode" class="nui-dictcombobox nui-form-input" name="user.authmode" value="local"
          						valueField="dictID" textField="dictName" dictTypeId="COF_AUTHMODE"/>
          						<!-- 
<input id="authmode" name="user.authmode" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_AUTHMODE" value="local"/>
							 	-->
							</td>
							<td class="nui-form-label"><label for="invaldate$text">密码失效日期：</label></td>
							<td><input id="invaldate" class="nui-datepicker" name="user.invaldate" /></td>
						</tr>		
						<tr>
							<td class="nui-form-label"><label for="startdate$text">有效开始时间：</label></td>
							<td><input id="startdate" name="user.startdate" class="nui-datepicker" /></td>
							<td class="nui-form-label"><label for="enddate$text">有效截止时间：</label></td>
							<td><input id="enddate" name="user.enddate" class="nui-datepicker" onvalidation="onEnddateValidation" />
							</td>
						</tr>
						<tr class="odd">
					        <td class="nui-form-label"><label for="menutype$text">菜单风格：</label></td>
							<td colspan="3"><input id="menutype" name="user.menutype" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox" dictTypeId="COF_SKINLAYOUT" value="default"/></td>
						</tr>
					</table>
		        </div>
			</fieldset>
	    </div>
    </div>
		
	<div title="详细信息" showCloseButton="false" style="width:100%;border-buttom:0px;">
		<div id="form2">
			<table style="width:100%;table-layout:fixed;" class="nui-form-table" >
				<tr>
					<td class="nui-form-label"><label for="haddress$text">家庭地址：</label></td>
					<td colspan="3"><input id="haddress" name="employee.haddress" class="nui-textbox nui-form-input"    style="width:410px;"  vtype="maxLength:128"/></td>
				</tr>
				<tr class="odd">
					<td class="nui-form-label"><label for="hzipcode$text">家庭邮编：</label></td>
					<td><input id="hzipcode" name="employee.hzipcode" class="nui-textbox nui-form-input" vtype="int;rangeLength:0,10"/></td>
					<td class="nui-form-label"><label for="htel$text">家庭电话：</label></td>
					<td><input id="htel" name="employee.htel" class="nui-textbox nui-form-input"  vtype="phone;rangeLength:0,20"  /></td>
				</tr>	
				<tr>
					<td class="nui-form-label"><label for="qq$text">QQ号码：</label></td>
					<td><input id="qq" name="employee.qq" class="nui-textbox nui-form-input"  vtype="maxLength:16"  /></td>
					<td class="nui-form-label"><label for="weibo$text">微博：</label></td>
					<td><input id="weibo" name="employee.weibo" class="nui-textbox nui-form-input" style="width:410px;" vtype="maxLength:255"/></td>
				</tr>	
				<tr class="odd">
					<td class="nui-form-label"><label for="pemail$text">私人邮箱：</label></td>
					<td><input id="pemail" name="employee.pemail" class="nui-textbox nui-form-input" vtype="email;rangeLength:0,128"/></td>
					<td class="nui-form-label"><label for="oemail$text">办公室邮箱：</label></td>
					<td><input id="oemail" name="employee.oemail" class="nui-textbox nui-form-input" vtype="email" vtype="email;rangeLength:0,128"/></td>
				</tr>
				<tr>
					<td class="nui-form-label"><label for="oaddress$text">办公地址：</label></td>
					<td colspan="3"><input id="oaddress" name="employee.oaddress" class="nui-textbox nui-form-input" style="width:410px;"  vtype="maxLength:255" /></td>
				</tr>
				<tr class="odd">
					<td class="nui-form-label"><label for="ozipcode$text">办公室邮编：</label></td>
					<td><input id="ozipcode" name="employee.ozipcode" class="nui-textbox nui-form-input" vtype="int;rangeLength:0,10" /></td>
					<td class="nui-form-label"><label for="faxno$text">传真号码：</label></td>
					<td><input id="faxno" name="employee.faxno" class="nui-textbox nui-form-input"  vtype="maxLength:14"  /></td>
				</tr>				
				<tr>
					<td class="nui-form-label"><label for="otel$text">办公室电话：</label></td>
					<td><input id="otel" name="employee.otel" class="nui-textbox nui-form-input"  vtype="phone;rangeLength:0,20"  /></td>
					<td class="nui-form-label"><label for="party$text">政治面貌：</label></td>
					<td><input id="party" name="employee.party" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="COF_PARTYVISAGE" /></td>
				</tr>
				<tr class="odd">
					<td class="nui-form-label"><label for="major$text">直接主管：</label></td>
					<td><input id="major" name="employee.major" allowInput="false" showClose="true" oncloseclick="onCloseClick" class="nui-buttonEdit nui-form-input" onbuttonclick="selectMajor"/></td>
					<td class="nui-form-label"><label for="degree$text">职级：</label></td>
					<td><input id="degree" name="employee.degree" data="data" emptyText="请选择" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="COF_EMPLEVEL" /></td>
				</tr>			
				<tr>
					<!-- 
					<td class="nui-form-label"><label for="orgidlist$text">可管理机构：</label></td> 
					<td><input id="orgidlist" name="employee.orgidlist"  allowInput="false" showClose="true" oncloseclick="onCloseClick" class="nui-buttonEdit nui-form-input" onbuttonclick="selectOrg" /></td>
					 -->
					<td class="nui-form-label"><label for="specialty$text">可管理角色：</label></td>
					<td><input id="specialty" name="employee.specialty"  allowInput="false" showClose="true" oncloseclick="onCloseClick" class="nui-buttonEdit nui-form-input" onbuttonclick="selectRole"/></td>
				</tr>	
				<tr class="odd">
					<td class="nui-form-label"><label for="workexp$text">工作描述：</label></td>
					<td colspan="3"><input id="workexp" name="employee.workexp" class="nui-textarea nui-form-input"  vtype="maxLength:512"/></td>
				</tr>			
				<tr>
					<td class="nui-form-label"><label for="remark$text">备注：</label></td>
					<td colspan="3"><input id="remark" name="employee.remark" class="nui-textarea nui-form-input"  vtype="maxLength:512"/></td>
				</tr>
			</table>
		</div>
	</div>
</div>
</form>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" style="width:60px;" iconCls="icon-save" onclick="update">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="resetBtn_01" style="width:60px;" iconCls="icon-reset" onclick="resetForm">重置</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="width:60px;display:none;" onclick="cancel">取消</a>
</div>
</div>

<script type="text/javascript">
	 nui.parse();
	 
	 $(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			window['parentNode'] = node;
		}
		$(".mini-textbox-input").first().focus();
	});

    var form = new nui.Form("#form");
	var form1 = new nui.Form("#form1");
	var form2 = new nui.Form("#form2");
	var form3 = new nui.Form("#form3");

    function update() {
    	var data = {};
       	//校验
		form1.validate();
        if (form1.isValid()==false) return;
		form2.validate();
        if (form2.isValid()==false) return;
        if($("#userRefCheckbox")[0].checked){
        	form3.validate();
        	if (form3.isValid()==false) return;
        	//提交所有数据
        	data = form.getData(true,true);
        }else{
        	//只提交emp的数据
        	var form1Data = form1.getData(true,true);
        	var form2Data = form2.getData(true,true);
        	if(!form1Data || !form2Data) return;
        	if(form2Data.employee){
	        	for(var p in form2Data.employee){
	        		form1Data.employee[p] = form2Data.employee[p];
	        	}
        	}
        	data = form1Data;
        }
        var json = nui.encode(data);
        $.ajax({
            url: "org.gocom.components.coframe.org.employee.updateEmployee.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.response;
            	if(response){
	            	if(response.flag){
	            		if(window.isCloseWindow){
		            		CloseWindow("ok");
		            		return;
	            		}
	            		window['formData']=data;
	            		nui.alert(response.message);
	            		//刷新其父节点
	            		if(window.parent){
	            			window.parent.refreshParentNode();
	            		}
	            		return;
	            	}
            	}
            	nui.alert("修改失败，请联系管理员");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                CloseWindow();
            }
        });
    }

    //var orgidlistBtn = nui.get("orgidlist");
	var specialtyBtn = nui.get("specialty");
	var majorBtn = nui.get("major");
    ////////////////////
    //标准方法接口定义
    function SetData(data) {
    	if(data.action=="update"){
    		window.isCloseWindow = true;
    		showCancelBtn();
    	}
        //跨页面传递的数据对象，克隆后才可以安全使用
        data = nui.clone(data);
		var json = nui.encode({template:data});
        $.ajax({
            url: "org.gocom.components.coframe.org.employee.getEmployee.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            async: false,//非异步，即同步
            success: function (data) {
                var o = nui.decode(data);
                form1.setData(o);
                //var orgidlist = o.employee.orgidlist;
                var specialty = o.employee.specialty;
                var major = o.employee.major;
                //o.employee.orgidlist = analysiString(orgidlist);
                //o.employee.specialty = analysiString(specialty);
                //o.employee.major=loadEmpnameById(major);
                form2.setData(o);
	            //orgidlistBtn.setValue(orgidlist);
	            //orgidlistBtn.setText(analysiString(orgidlist));
	            specialtyBtn.setValue(specialty);
	            specialtyBtn.setText(analysiString(specialty));
	            majorBtn.setValue(major);
	            majorBtn.setText(loadEmpnameById(major));
                window['formData1'] = o;
                window['parentNode'] = o.employee;
                loadUser(o.employee);
            }
        });
    }
    
    function analysiString(value){
	    if(value==null){
	        return null;
	    }
	    var values = value.split(",");
        var strs = [];
        for(var i=0,len=values.length;i<len;i++){
            var str = values[i].split(":");
            strs.push(str[1]);
        }
        return strs.join(",");
	}
    
    function loadUser(data){
    	 if(data.operatorid){
        	var userData = nui.encode({template:{operatorId:data.operatorid}});
	        $.ajax({
	        	url:"org.gocom.components.coframe.rights.UserManager.getCapUser.biz.ext",
	        	data:userData,
	        	cache:false,
	        	type:'POST',
	        	contentType:'text/json',
	        	success:function(ret){
	        		var o = nui.decode(ret);
	        		form3.setData(o);
	        		window['formData3'] = o;
	        	}
	        });
        }else{
           var checkbox = $("#userRefCheckbox")[0];
	       checkbox.checked = false;
	       toggleFieldSet(checkbox,'form3');
        }
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
    
    function cancel(e) {
        CloseWindow("cancel");
    }
	
	function resetForm(){
		//form.reset();
		var data = window['formData'];
		var data1 = window['formData1'];
		var data3 = window['formData3'];
		if(data1){
			form1.setData(data1);
			form2.setData(data1);
		}
		if(data3){
			form3.setData(data3);
		}
		if(data){
			form.setData(data);
		}
		if(!data1 && !data2){
			form.reset();
		}
	}
	
	//校验日期
	function onOutdateValidation(e){
       	var o = form.getData();
       	var org = o.employee || {};
		if(org.outdate && org.indate && org.outdate<=org.indate){
			e.errorText = "离职日期必须大于入职日期";
			e.isValid = false;
		}
	}
	
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.user || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}
	}
	
    var bootPath = "<%=request.getContextPath() %>";
    
    //选择机构
    function selectOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/coframe/org/employee/select_manageorg_tree.jsp",
            showMaxButton: false,
            title: "选择员工",
            width: 500,
            height: 400,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   parentNode: window['parentNode'],
                   ids:ids,
                   texts:texts
                };
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }
    
    function selectRole(e) {
    	var btnEdit = this;
        nui.open({
            url: bootPath + "/coframe/org/employee/select_managed_role.jsp",
            showMaxButton: false,
            title: "选择可管理角色",
            width: 400,
            height: 450,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   parentNode: window['parentNode'],
                   ids:ids,
                   texts:texts
                };
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }    
    
    function showCancelBtn(){
    	$("#cancelBtn_01").show();
    	$("#resetBtn_01").hide();
    }
    
  	//展开，折叠
    function toggleFieldSet(ck, id) {
        var dom = document.getElementById(id);
        if(ck.checked){
        	dom.className = "";//展开
        }else{
        	dom.className = "hideFieldset";
        }
    }
    
    function selectMajor(e){
    	var btnEdit = this;
        nui.open({
            url: bootPath + "/coframe/org/employee/select_major.jsp",
            showMaxButton: false,
            title: "选择直接主管",
            width: 800,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });
    }
    
    
    function loadEmpnameById(id){
 	var ret = "";
 	if(!id){
 		return ret;
 	}
 	var json = nui.encode({template:{empid:id}});
 	$.ajax({
         url: "org.gocom.components.coframe.org.employee.getEmployee.biz.ext",
         type: 'POST',
         data: json,
         cache: false,
         contentType:'text/json',
         cache: false,
         async: false,//非异步，即同步
         success: function (data) {
             var o = nui.decode(data);
            	ret = o.employee.empname;
         }
     });
     return ret;
 }
   function onCloseClick(e){
    	var btnEdit = e.sender;
        btnEdit.setValue("");
        btnEdit.setText("");
    }
</script>

</body>
</html>