<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): gouyl
  - Date: 2013-10-09 14:43:27
  - Description:添加工作组
-->
<head>
<title>工作组添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/coframe/tools/skins/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/coframe/org/js/org_common.js"></script>
</head>
<body>
<div class="nui-fit" style="padding-top:5px;min-width:450px;min-height:250px;">
	<div id="form1" style="width:100%;height:99%;overflow:hidden;">
		<input class="nui-hidden" name="orgGroup.orgGroup.groupid" />
		<input class="nui-hidden" name="orgGroup.orgGroup.grouplevel" />
		<input class="nui-hidden" name="orgGroup.orgGroup.groupseq" />
		<table style="width:100%;table-layout:fixed;" class="nui-form-table">
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
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  style="width:60px;" iconCls="icon-save" onclick="add">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" style="width:60px;" onclick="cancel">取消</a>
</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	
	function add(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
       var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
            url: "org.gocom.components.coframe.org.group.addGroup.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var response = text.ret || {};
            	if(response){
            		if(response.flag){
            			CloseWindow("ok");
            		}else{
            			nui.alert(response.message);
            		}
            	}else{
            		nui.alert("添加失败，请联系管理员");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                CloseWindow();
            }
        });
        
	}
	
	function cancel(){
		CloseWindow("cancel");
	}
	
	function SetData(data){
		data = nui.clone(data);
		if(data){
			var formData = {
				orgGroup:{orgGroup:data.parentNode}
			};
			form.setData(formData);
		}
	}
	
	function resetForm(){
		form.reset();
	}
	
	//校验日期
	//失效日期必须大于生效日期
	function onEnddateValidation(e){
       	var o = form.getData();
       	var group = o.orgGroup || {};
		if(group.enddate && group.startdate && group.enddate<=group.startdate){
			e.errorText = "失效日期必须大于生效日期";
			e.isValid = false;
		}
	}
	
</script>

</body>
</html>