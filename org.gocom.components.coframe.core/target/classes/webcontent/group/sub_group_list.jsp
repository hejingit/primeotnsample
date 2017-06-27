<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): gouyl
  - Date: 2013-10-09 14:43:27
  - Description:子工作组管理
-->
<head>
<title>下级工作组</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/coframe/tools/skins/common.jsp" %>
</head>
<body>
<div style="padding:5px 5px 0px 5px;">
	 <div class="nui-toolbar" style="border-bottom:0;">
        <table style="width:100%;">
            <tr>
	            <td style="width:100%;">
	                <a class="nui-button" iconCls="icon-add" onclick="add">增加</a>
	            	<a class="nui-button" iconCls="icon-edit" onclick="edit" id="edit_btn">编辑</a>
	            	<a class="nui-button" iconCls="icon-remove" onclick="remove">删除</a>
	            </td>
            </tr>
        </table>
    </div>
</div>
<div class="nui-fit" style="padding:0px 5px 5px 5px">
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;" 
	url="org.gocom.components.coframe.org.group.queryGroup.biz.ext" dataField="treeNodes" onselectionchanged="selectionChanged" ajaxData="getAjaxData" idField="id" allowResize="true" sizeList="[2,10,20,50,100]" pageSize="10" multiSelect="true">
	    <div property="columns">
	        <div type="checkcolumn"></div>
	        <div field="groupname" width="120" headerAlign="center" >工作组名称</div>    
	        <div field="grouptype" width="120" renderer="renderOrgType" headerAlign="center" renderer="renderGroupType" >工作组类型</div>    
	        <div field="groupstatus" width="120" renderer="renderGroupStatus" headerAlign="center" >工作组状态</div>    
	    </div>
	</div>
</div>

<div id="removePrompt" style="width:100%;display:none;">
   <table>
     <tr>
       <td>
          <div class="mini-messagebox-question"></div>
       </td>
       <td>&nbsp;&nbsp;&nbsp;确实想要删除选中工作组吗?</td>
     </tr>
   </table>
</div>

<script type="text/javascript">
	nui.parse();
	
	(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		}
	})();
	
	var grid = nui.get("datagrid1");
	grid.load();
	
	var bootPath = "<%=request.getContextPath() %>/";
	
	function add(){
		nui.open({
            url: bootPath + "coframe/org/group/group_add.jsp",
            title: "添加下级工作组", width: 600, height: 350,
            onload:function(){
            	var iframe = this.getIFrameEl();
                var data = {parentNode:window.parentNode||{}};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
               grid.reload();
               if(action=="ok"){
	                if(window.parent){
	               		window.parent.refreshCurrentNode();
	               	}
               }
            }
        });
	}
	
	function edit(){
		var row = grid.getSelected();
        if (row) {
			nui.open({
	            url: bootPath + "coframe/org/group/group_update.jsp",
	            title: "修改下级工作组", width: 610, height: 350,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	                data.action="update";
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	                grid.reload();
	                if(action=="ok"){
		                if(window.parent){
		               		window.parent.refreshCurrentNode();
		               	}
              		}
	            }
	        });
		}else{
			nui.alert("请选中一条记录");
		}
	}
	
	function remove(){
		var rows = grid.getSelecteds();
        if (rows && rows.length > 0) {
            removePrompt(rows);
        }else{
			nui.alert("请选中一条记录");
		}
	}
	
	var prompt = document.getElementById("removePrompt");
	function removePrompt(rows){
	   prompt.style.display="";
	   nui.showMessageBox({
	      width:300,
	      title:'系统提示',
	      buttons:["ok","cancel"],
	      html:prompt,
	      showModal: false,
	      callback: function (action) {
             if(action=="ok"){
                window['result']={
                  action:true
                };
             }else{
                window['result']={
                  action:false
                };
             }
             executeRemove(rows);
          }
	   });
	}
	
	function executeRemove(rows){
	    var result = window.result;
	    if(result.action){
	       	var json = nui.encode(getRemoveData(rows, "OrgGroup"));
	        grid.loading("操作中，请稍后......");
	        $.ajax({
	            //url: "org.gocom.components.coframe.org.organization.deleteOrg.biz.ext",
	            url:"org.gocom.components.coframe.org.group.deleteNodes.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	                grid.reload();
	                if(!text.exception){
		                if(window.parent){
		               		window.parent.refreshCurrentNode();
		               	}
	               }
	            },
	            error: function () {
	            }
	        });
        }
	}
	
	function selectionChanged(){
		var rows = grid.getSelecteds();
		if(rows.length>1){
			//disable edit button
			nui.get("edit_btn").disable();
		}else{
			nui.get("edit_btn").enable();
		}
	}
	
	//组合删除的数据；需要固定的格式
	function getRemoveData(rows, nodeType){
		var childs = [];
		for(var i=0,len=rows.length;i<len;i++){
			var child = rows[i];
			child.nodeId = child.groupid;
			child.nodeType = nodeType;
			child.orgGroup = null;
			childs.push(child);			
		}
		var parentId = "";
		var parentType = "";
		if(window.parentNode){
			parentId = window.parentNode.nodeId;
			parentType = window.parentNode.nodeType;
		}
		var data = {
			childs:childs,
			parentType:parentType,
			parentId:parentId
		}
		return data;
	}
	
	function getAjaxData(){
		if(window.parentNode){
			return {"criteria":{"_expr":[{"orgGroup.groupid":window.parentNode.groupid||"","_op":"="}],
								"_order":[{"_asc":"createtime"}]}
			};
		}
	}
	
	function renderGroupType(e){
		return nui.getDictText("COF_ORGTYPE", e.row.grouptype);
	}
	
	function renderGroupStatus(e){
		return nui.getDictText("COF_ORGSTATUS", e.row.groupstatus);
	}
	
	/*
	function changeValue(rowid){
		var row = grid.getRow(0);
		if(!row.internal_selected){
			grid.select(row);
		}else{
			grid.deselect(row);
		}
		grid.updateRow(row,{internal_selected:!row.internal_selected});
	}
	
	function checkboxRender(e){
		//debugger;
		if(grid.isSelected(e.row)){
			e.row.internal_selected = true;
			return "<img onclick='changeValue(" + e.record.id + ");' src='/default/coframe/tools/images/checkbox/checkbox_yes.png'/>";
		}else{
			e.row.internal_selected = false;
			return "<img onclick='changeValue(" + e.record.id + ")' src='/default/coframe/tools/images/checkbox/checkbox_no.png'/>";
		}
		return "";
	}
	*/
</script>

</body>
</html>