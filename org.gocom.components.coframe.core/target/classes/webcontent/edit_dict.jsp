<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@include file="/coframe/dict/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 陈鹏
  - Date: 2013-12-14 14:48:59
  - Description:
-->
<body>
<div class="nui-fit" style="padding-top:5px;">
	<form id="dict_form" method="post">	
		<input class="nui-hidden" name="status"/>
		<input class="nui-hidden" name="rank"/>
		<input class="nui-hidden" name="seqno"/>
		<input class="nui-hidden" name="action"/>
		<table class="nui-form-table" style="width:100%;table-layout:fixed;*width:90%;">
		<tr>
			<th class="nui-form-label"><label for="parentid$text">上级字典项代码：</label></th>
			<td><input id="parentid" name="parentid" class="nui-textbox nui-form-input" readonly="true"/></td>
		</tr>
		<tr class="odd">
			<th class="nui-form-label"><label for="dicttypeid$text">类型代码：</label></th>
			<td>
				<input id="dicttypeid" name="eosDictType.dicttypeid" vtype="maxLength:128" required="true" class="nui-combobox nui-form-input"
					textField="dicttypeid" valueField="dicttypeid" emptyText="请选择" allowInput="false"/>
			</td>
		</tr>
		<tr>
			<th class="nui-form-label"><label for="dictid$text">字典项代码：</label></th>
			<td><input id="dictid" name="dictid" vtype="maxLength:128" required="true" class="nui-textbox nui-form-input"/></td>
		</tr>
		<tr class="odd">
			<th class="nui-form-label"><label for="dictname$text">字典项名称：</label></th>
			<td><input id="dictname" name="dictname" vtype="maxLength:255" required="true" class="nui-textbox nui-form-input"/></td>
		</tr>
		<tr>
			<th class="nui-form-label"><label for="sortno$text">排序：</label></th>
			<td><input id="sortno" name="sortno" vtype="int;maxLength:11" class="nui-textbox nui-form-input"/></td>
		</tr>
		</table>
	</form>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-save" onclick="saveForm();">保存</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" iconCls="icon-close" onclick="closeWindow('cancel');">关闭</a>
</div>
</body>
</html>

<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("dict_form");

	function loadForm(data) {
		data = nui.clone(data);
		
		if(data.parentdicttypeid != null){
			$.ajax({
				url: "org.gocom.components.coframe.dict.DictManager.queryDictType.biz.ext",
				type: 'post',
				data: nui.encode({dicttypeid:data.parentdicttypeid}),
				cache: false,
				contentType:'text/json',
				success: function (json) {
					nui.get("dicttypeid").load(json.data);
				}
			});
		}
		else{
			nui.get("dicttypeid").load([{dicttypeid:data.eosDictType.dicttypeid, dicttypename:data.eosDictType.dicttypename}]);
			nui.get("dicttypeid").setReadOnly(true);
		}
		
		form.setData(data);
		form.setChanged(false);
		
		if(data.action == 'edit'){
			nui.get('dictid').setReadOnly(true);
		}
	}
	
	function saveForm() {
		form.validate();
		if (form.isValid() == false) return;

		$.ajax({
			url: "org.gocom.components.coframe.dict.DictManager.saveDict.biz.ext",
			type: 'post',
			data: nui.encode({data:form.getData()}),
			cache: false,
			contentType:'text/json',
			success: function (json) {
				if(json.status == 'success'){
					closeWindow('ok');
				}
				else if(json.status == 'exist') nui.alert("记录已存在！");
				else nui.alert("保存失败！");
				
			},
			error: function (jqXHR, textStatus, errorThrown) {
				nui.alert("保存失败！");
				closeWindow('ok');
			}
		});
	}
    
	function closeWindow(action) {            
		if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
		else window.close();
	}

</script>