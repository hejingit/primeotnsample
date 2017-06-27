<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.system.utility.StringUtil"%>
<%@include file="/coframe/dict/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 陈鹏
  - Date: 2013-11-28 10:21:23
  - Description:
-->
<head>
<title>业务字典配置</title>
</head>
<body style="margin:0px;height:100%;">
 
<table border="0" style="width:100%;height:100%;*height:98%;">
<tr>
<td style="width:55%;height:100%;" valign="top">
	<div class="nui-fit">
	<div class="nui-panel" title="业务字典类型" style="width:100%;height:100%;"
	    showToolbar="false" showCollapseButton="false" showFooter="false" allowResize="false" >
	    
		<div class="search-condition" style="height:30px;">
		<form id="query_dict_type_form" method="post">
		<table border="0" style="width:100%;" align="center">
			<tr>
				<td align="right">类型代码：</td>
				<td><input id="dicttypeid" name="dicttypeid" class="nui-textbox" style="width:100px;" onenter="onKeyEnter"/></td>
				<td align="right">类型名称：</td>
				<td><input id="dicttypename" name="dicttypename" class="nui-textbox" style="width:100px;" onenter="onKeyEnter"/></td>
				<td><a class="nui-button" onclick="searchDictType();">查询</a></td>
			</tr>
			<tr height="3px;"><td colspan="2"></td></tr>
		</table>
		</form>
		</div>
		
	  <div style="margin:10px 0px 0px 0px;">
	    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
	      <table style="width:100%;">
	        <tr>
	           <td style="width:100%;">
			<a class="nui-button" plain="true" iconCls="icon-add" onclick="addDictType()" >添加</a>
			<a class="nui-button" plain="true" iconCls="icon-addnew" onclick="addSubDictType()" id="btn_addSubDictType">添加子类型</a>
	        <a class="nui-button" plain="true" iconCls="icon-edit" onclick="editDictType()" id="btn_editDictType">修改</a>
            <a class="nui-button" plain="true" iconCls="icon-remove" onclick="removeDictType()" id="btn_removeDictType">删除</a>
			<a class="nui-button" plain="true" iconCls="icon-download" onclick="importDict();">导入</a>&nbsp;
			<a class="nui-button" plain="true" iconCls="icon-upload" onclick="exportDict();">导出</a>&nbsp;
			<a class="nui-button" plain="true" onclick="refreshDictCache();">刷新缓存</a>
	           </td>
	        </tr>
	      </table>
	    </div>
	  </div>
	    
	  <div class="nui-fit">
	    <div id="dict_type_tg" class="nui-treegrid" style="width:100%;height:100%;"
		    showPager="true" pageSize="10" sizeList="[10,20,50]" allowAlternating="true" multiSelect="true"
		    url="org.gocom.components.coframe.dict.DictManager.queryDictType.biz.ext"
			onselectionchanged="onDictTypeSelected" ondrawnode="onDictTypeDrawNode" onload="onDictTypeLoad"
		    dataField="data" idField="dicttypeid" treeColumn="dicttypeid">
		    <div property="columns" width="20">
		        <div type="checkcolumn" ></div>
		        <div name="dicttypeid" field="dicttypeid" allowSort="true" width="50%">类型代码</div>
		        <div field="dicttypename" allowSort="true" width="50%">类型名称</div>
		    </div>
		</div>
	  </div>
		
	</div>
	</div>
</td>
<td style="height:100%;" rowspan="2" valign="top">
	<div class="nui-fit">
	<div class="nui-panel" title="业务字典项" style="width:100%;height:100%;"
	    showToolbar="true" showCollapseButton="false" showFooter="false" allowResize="false" >
		<div property="toolbar" >
	        <a class="nui-button" plain="true" iconCls="icon-add" onclick="addDict()" >添加</a>
			<a class="nui-button" plain="true" iconCls="icon-addnew" onclick="addSubDict()" id="btn_addSubDict">添加子项</a>
	        <a class="nui-button" plain="true" iconCls="icon-edit" onclick="editDict()" id="btn_editDict">修改</a>
            <a class="nui-button" plain="true" iconCls="icon-remove" onclick="removeDict()" id="btn_removeDict">删除</a>
	    </div>
	    
	    <div id="dict_tg" class="nui-treegrid" style="width:100%;height:100%;" autoLoad="false"
	    	 showPager="true" pageSize="15" sizeList="[15,25,50]" allowAlternating="true" multiSelect="true"
		    url="org.gocom.components.coframe.dict.DictManager.queryDict.biz.ext" onbeforeload="onBeforeTreeLoad" 
		    onselectionchanged="onDictSelected"  ondrawnode="onDictDrawNode" onnodeclick="onDictNodeClick"
		    dataField="data" idField="dictid" treeColumn="dictid">
		    <div property="columns">
		        <div type="checkcolumn" ></div>
		        <div name="dictid" field="dictid" allowSort="true" width="45%">字典项代码</div>
		        <div field="dictname" allowSort="true" width="47%">字典项名称</div>
		        <div field="sortno" allowSort="true" width="8%">排序</div>
		    </div>
		</div>
	    
	</div>
	</div>
</td>
</tr>
</table>

<div id="importWindow" class="nui-window" title="业务字典导入" style="width:650px;" 
    showModal="true" allowResize="true" allowDrag="true">    
    <div>
    	<form id="import_dict_form" action="org.gocom.components.coframe.dict.impl.importDict.flow?_eosFlowAction=importDict" method="post" enctype="multipart/form-data">
        <table border="0" style="width:500px;height:100px;" align="center">
			<tr>
				<td width="35%" align="left">请选择您要导入的Excel文件:</td>
				<td><span id="uploadSpan"><input type="file" id="dict_file" name="dict_file" size="60" style="width:300px;"></span></td>
			</tr>
			<tr>
				<td colspan="2">注：导入时将根据主键匹配覆盖已存在的数据。</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<a class="nui-button" onclick="startUpload();">导入</a>&nbsp;
					<a class="nui-button" onclick="resetImport();">重置</a>&nbsp;
					<a class="nui-button" onclick="cancelImport();">取消</a>
				</td>
			</tr>
        </table>
        </form>
    </div>
</div>

</body>
</html>

<script type="text/javascript">
	nui.parse();
	
	var dict_type_tg = nui.get("dict_type_tg");
	var dict_tg = nui.get("dict_tg");
	var seldicttypeid;
	var seldictid;
	var importWindow = nui.get("importWindow");
	
	function onKeyEnter(e) {
		searchDictType();
	}
	function searchDictType() {
        var dicttypeid = nui.get("dicttypeid").getValue();
        var dicttypename = nui.get("dicttypename").getValue();
        dict_type_tg.load({ id: dicttypeid, name: dicttypename});
        seldicttypeid = null;
        seldictid = null;
    }
    function importDict() {
    	resetImport();        
       	importWindow.show();
    }
    function exportDict() {        
        var form = document.getElementById("query_dict_type_form");
		form.action = "org.gocom.components.coframe.dict.impl.exportDict.flow";
        form.submit();
    }
    function resetQuery() {
    	var form = new nui.Form("query_dict_type_form");
    	form.reset();
    }
    function refreshDictCache(){
    	nui.ajax({
			url: "org.gocom.components.coframe.dict.DictManager.refreshDictCache.biz.ext",
			type: "post",
			cache: false,
			contentType: 'text/json',
			success: function (json) {
				if(json.status == 'success'){
				nui.alert("刷新缓存成功！");
			}
			else nui.alert("刷新缓存失败！");
			},
			error: function () {
				nui.alert("刷新缓存失败！");
			}
		});
    }
    function onDictTypeSelected(e) {
        var grid = e.sender;
		
        dict_tg.clearData();
        seldicttypeid = null;
        seldictid = null;
        
        var rows = grid.getSelecteds();
        if(rows.length>1){
            nui.get("btn_addSubDictType").disable();
            nui.get("btn_editDictType").disable();
            nui.get("btn_removeDictType").enable();
        }else if(rows.length==1){
            nui.get("btn_addSubDictType").enable();
            nui.get("btn_editDictType").enable();
            nui.get("btn_removeDictType").enable();
            
            var record = grid.getSelected();
	        dict_tg.load({ dicttypeid: record.dicttypeid });
	        seldicttypeid = record.dicttypeid;
        }else{
            nui.get("btn_addSubDictType").disable();
            nui.get("btn_editDictType").disable();
            nui.get("btn_removeDictType").disable();
        }
    }
	function onDictTypeDrawNode(e){//节点加载完清空参数，避免影响查询和翻页
		dict_type_tg._dataSource.loadParams.dicttypeid = null;
	}
	function onDictTypeLoad(e){//加载第一个类型的字典项
		nui.parse();		
		seldicttypeid = null;
        seldictid = null;
		if(e.data[0] != null) {
			var grid = e.sender;
			if (grid.getSelecteds().length==0 )
				grid.select(grid.getRow(0));
		}else
			nui.get("dict_tg").clearData();
	}
	function checkDictTypeSelected() {
		if(seldicttypeid == null){
			nui.alert("请先选择一条业务字典类型记录！");
			return false;
		}
		else return true;
	}
    function addDictType() {
		nui.open({
			url: "<%=contextPath%>/coframe/dict/edit_dict_type.jsp",
			title: "添加字典类型", width: 320, height: 180,
			onload: function () {
				var iframe = this.getIFrameEl();
				var data = {action:"add"};
				iframe.contentWindow.loadForm(data);
			},
			ondestroy: function (action) {
				if (action=="ok")
				dict_type_tg.reload();
			}
		});
    }
    function addSubDictType() {
		if(checkDictTypeSelected()){
			nui.open({
				url: "<%=contextPath%>/coframe/dict/edit_dict_type.jsp",
				title: "添加子类型", width: 320, height: 180,
				onload: function () {
					var iframe = this.getIFrameEl();
					var parent = dict_type_tg.getSelected();
					var data = {rank:parent.rank, parentid:parent.dicttypeid, seqno:parent.seqno};
					data.action = 'add';
					iframe.contentWindow.loadForm(data);
				},
				ondestroy: function (action) {
					if (action=="ok")
					dict_type_tg.reload();
				}
			});
		}
    }
    function editDictType() {
    	var rows = dict_type_tg.getSelecteds();
    	if(rows && rows.length!=1){
    		alert("请先选择一条业务字典项记录！");
    		return;
    	}
    	if(checkDictTypeSelected()){
			nui.open({
				url: "<%=contextPath%>/coframe/dict/edit_dict_type.jsp",
				title: "修改字典类型", width: 320, height: 180,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = nui.clone(dict_type_tg.getSelected());
					data.action = 'edit';
					iframe.contentWindow.loadForm(data);
				},
				ondestroy: function (action) {
					if (action=="ok")
					dict_type_tg.reload();
				}
			});
		}
    }
    function removeDictType() {
    	var rows = dict_type_tg.getSelecteds();
	    if(rows.length>=1){
	    	
	    	nui.confirm("所有关联的业务字典类型和业务字典项都将被删除，确认删除业务字典类型？","", function(action){
	    		if(action == 'ok'){
					nui.ajax({
						url: "org.gocom.components.coframe.dict.DictManager.removeDictType.biz.ext",
						type: "post",
						data: nui.encode({data:rows}),
						cache: false,
						contentType: 'text/json',
						success: function (json) {
							if(json.status == 'success'){
								seldicttypeid = null;
								dict_tg.clearData();
								dict_type_tg.reload();								
							}
							else nui.alert("删除失败！");
						},
						error: function () {
							nui.alert("删除失败！");
						}
					});
				}
			});
	    }else{
	    	nui.alert("请先选择一条业务字典项记录！");
	    }
    }
    
    function onDictSelected(e) {
        var grid = e.sender;
        
        seldictid = null;
        
        var rows = grid.getSelecteds();
        if(rows.length>1){
            nui.get("btn_addSubDict").disable();
            nui.get("btn_editDict").disable();
            nui.get("btn_removeDict").enable();
        }else if(rows.length==1){
            nui.get("btn_addSubDict").enable();
            nui.get("btn_editDict").enable();
            nui.get("btn_removeDict").enable();
            seldictid = grid.getSelected().dictid;
        }else{
            nui.get("btn_addSubDict").disable();
            nui.get("btn_editDict").disable();
            nui.get("btn_removeDict").disable();
        }
          
    }
    function onDictDrawNode(e) {//节点加载完清空参数，避免影响查询和翻页
	    dict_tg._dataSource.loadParams.dictid = null;
	    dict_tg._dataSource.loadParams.parenttypeid = null;
    }
    function onDictNodeClick(e) {
	    dict_tg._dataSource.loadParams.parenttypeid = e.node.eosDictType.dicttypeid;
    }
	function onBeforeTreeLoad (e) {
    	if (e.node&&e.node.eosDictType) 
			dict_tg._dataSource.loadParams.parenttypeid = e.node.eosDictType.dicttypeid.toString();
	}
    
    
    function checkDictSelected() {
		if(seldictid == null){
			nui.alert("请先选择一条业务字典项记录！");
			return false;
		}
		else return true;
	}
    function addDict() {
    	if(checkDictTypeSelected()){
			nui.open({
				url: "<%=contextPath%>/coframe/dict/edit_dict.jsp",
				title: "添加字典项", width: 320, height: 250,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = {action:"add"};
					data.eosDictType = dict_type_tg.getSelected();
					iframe.contentWindow.loadForm(data);
				},
				ondestroy: function (action) {
					if (action=="ok")
					dict_tg.reload();
				}
			});
		}
    }
    function addSubDict() {
		if(checkDictSelected()){
			nui.open({
				url: "<%=contextPath%>/coframe/dict/edit_dict.jsp",
				title: "添加子项", width: 320, height: 250,
				onload: function () {
					var iframe = this.getIFrameEl();
					var parent = dict_tg.getSelected();
					var data = {rank:parent.rank, parentid:parent.dictid, seqno:parent.seqno};
					data.action = 'add';
					data.parentdicttypeid = parent.eosDictType.dicttypeid;
					iframe.contentWindow.loadForm(data);
				},
				ondestroy: function (action) {
					if (action=="ok")
					dict_tg.reload();
				}
			});
		}
    }
    function editDict() {
    	if(checkDictSelected()){
			nui.open({
				url: "<%=contextPath%>/coframe/dict/edit_dict.jsp",
				title: "修改字典项", width: 320, height: 250,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = nui.clone(dict_tg.getSelected());
					data.action = 'edit';
					iframe.contentWindow.loadForm(data);
				},
				ondestroy: function (action) {
					if (action=="ok")
					dict_tg.reload();
				}
			});
		}
    }
    function removeDict() {
    	var rows = dict_tg.getSelecteds();
	    if(rows.length>=1){

	    	nui.confirm("所有关联的业务字典项都将被删除，确认删除业务字典项？","", function(action){
	    		if(action == 'ok'){
					nui.ajax({
						url: "org.gocom.components.coframe.dict.DictManager.removeDict.biz.ext",
						type: "post",
						data: nui.encode({data:rows}),
						cache: false,
						contentType: 'text/json',
						success: function (json) {
							if(json.status == 'success'){
								seldictid = null;
								dict_tg.reload();
							}
							else nui.alert("删除失败！");
						},
						error: function () {
							nui.alert("删除失败！");
						}
					});
				}
			});
	    }else{
	    	nui.alert("请先选择一条业务字典项记录！");
	    }
    }
	
	function startUpload() {
        var form = $("#import_dict_form");
        var file = $("#dict_file").val();
        
        if (file == "") {
			nui.alert("请选择文件！");
			return;
		}
		var reg = /.xls$/;
		if (!reg.test(file))
		{
			nui.alert('请选择Excel格式(*.xls)文件！');
			return;
		}
		form.submit();
    }
    function resetImport() {
		var html=document.getElementById('uploadSpan').innerHTML; 
		document.getElementById('uploadSpan').innerHTML=html; 
    }
    function cancelImport() {
    	importWindow.hide();
    }
    
    var retcode = '<%= StringUtil.htmlFilter(request.getAttribute("retCode")==null?"":request.getAttribute("retCode").toString()) %>';
    if(retcode != ""){
        if(retcode == -1){
        	nui.alert('导入失败！');
        }else{
        	nui.alert('导入成功！');
        }
    }
</script>