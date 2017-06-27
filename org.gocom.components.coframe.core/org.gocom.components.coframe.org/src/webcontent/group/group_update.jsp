<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/coframe/tools/skins/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): gouyl
  - Date: 2013-10-09 14:43:27
  - Description:工作组管理树
-->
<head>
<title>工作组修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%request.setCharacterEncoding("UTF-8");%>
<script type="text/javascript" src="<%=contextPath%>/coframe/org/js/org_common.js"></script>
</head>
<body>
<div class="nui-fit" style="padding-top:5px;min-width:450px;min-height:200px;">
	<div id="form1" style="width:100%;height:99%;overflow:hidden;">
		<input class="nui-hidden" name="orgGroup.groupid" />
		<input class="nui-hidden" name="orgGroup.orgGroup.groupid" />
		<input class="nui-hidden" name="orgGroup.orgGroup.grouplevel" />
		<input class="nui-hidden" name="orgGroup.orgGroup.groupseq" />
		<table style="width:100%;height:290px;table-layout:fixed;" class="nui-form-table">
			<tr>
				<th class="nui-form-label"><label for="groupname$text">工作组名称：</label></th>
				<td><input id="groupname" class="nui-textbox nui-form-input" name="orgGroup.groupname"  vtype="maxLength:50" required="true"/></td>
				<th class="nui-form-label"><label for="groupstatus$text">工作组状态：</label></th>
				<td><input id="groupstatus" name="orgGroup.groupstatus" data="data" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="COF_ORGSTATUS" />
				</td>
			</tr>					
			<tr class="odd">
				<th class="nui-form-label"><label for="groupParentname$text">上级工作组：</label></th>
				<td><input id="groupParentname" class="nui-textbox nui-form-input" name="orgGroup.orgGroup.groupname" allowInput="false" /></td>
				<th class="nui-form-label"><label for="grouptype$text">工作组类型：</label></th>
				<td><input id="grouptype" name="orgGroup.grouptype" data="data" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" emptyText="请选择" dictTypeId="COF_ORGTYPE" />
				</td>
			</tr>					
			<tr>
				<th class="nui-form-label"><label for="startdate$text">生效日期：</label></th>
				<td><input id="startdate" name="orgGroup.startdate" class="nui-datepicker nui-form-input" /></td>
				<th class="nui-form-label"><label for="enddate$text">失效日期：</label></th>
				<td><input id="enddate" name="orgGroup.enddate" class="nui-datepicker nui-form-input"  onvalidation="onEnddateValidation" />
				</td>
			</tr>				
			<tr class="odd">
				<th class="nui-form-label"><label for="groupdesc$text">工作组描述：</label></th>
				<td colspan="3"><input id="groupdesc" name="orgGroup.groupdesc" class="nui-textarea nui-form-input" style="width:95%;height:150px;" vtype="maxLength:512"/></td>
			</tr>
		</table>
	</div>
  </div>
  <div class="mini-toolbar" borderstyle="border:0;" style="text-align: center; padding-top: 5px; padding-bottom: 5px; border: 0px none;">
    	<a class="nui-button"  style="width:60px;" iconCls="icon-save" onclick="update">保存</a>
		<span style="display:inline-block;width:25px;"></span>
		<a class="nui-button" id="resetBtn_01" style="width:60px;" iconCls="icon-reset" onclick="resetForm">重置</a>
		<span style="display:inline-block;width:25px;"></span>
		<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="width:60px;display:none;" onclick="cancel">取消</a>
  </div>

<script type="text/javascript">
	nui.parse();
    var form = new nui.Form("form1");
	
	(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			window['parentNode'] = node;
		}
	})();
	
    function update() {
        var o = form.getData(true,true);            
        form.validate();
        if (form.isValid() == false) return;
        var json = nui.encode(o);
        $.ajax({
            url: "org.gocom.components.coframe.org.group.updateGroup.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.response;
            	if(response && response.flag && window.isCloseWindow){
            		CloseWindow("ok");
            	}else{
            		nui.alert(response.message);
            		window['formData'] = o;
            		if(response.flag && window.parent){
            			window.parent.refreshParentNode();
            		}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
    }

    ////////////////////
    //标准方法接口定义
    function SetData(data) {
    	if(data.action=="update"){
    		window.isCloseWindow = true;
    		showCancelBtn();
    		$("#form1").css("height","100%");
    	}
        //跨页面传递的数据对象，克隆后才可以安全使用
        data = nui.clone(data);
		var json = nui.encode({template:data});
        $.ajax({
            url: "org.gocom.components.coframe.org.group.getGroupWithParent.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                form.setData(o);
                window['formData'] = o;
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
    function cancel(e) {
    	if(window.isCloseWindow){
	        CloseWindow("cancel");
    	}
    }
	
	//校验日期
	//失效日期必须大于生效日期
	function onEnddateValidation(e){
       	var o = form.getData();
       	var org = o.orgGroup || {};
		if(org.enddate && org.startdate && org.enddate<=org.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}
	}
	
     function showCancelBtn(){
    	$("#cancelBtn_01").show();
    	$("#resetBtn_01").hide();
    }
    
    function resetForm(){
		var data = window['formData'];
		if(data){
			form.setData(data);
		}else{
			form.reset();
		}
	}
    
</script>

</body>
</html>