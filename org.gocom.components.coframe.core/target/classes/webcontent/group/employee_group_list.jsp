<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): gouyl
  - Date: 2013-10-09 14:43:27
  - Description:工作组人员
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/coframe/tools/skins/common.jsp" %>
<link id="css_icon" rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/org/css/org.css"/>
<title>工作组人员</title>
<body>
<div style="padding:5px;">
   <div class="search-condition">
     <div class="list">
		<div id="form1">
			<table style="width:100%;table-layout:fixed;padding:5px;" class="table" >
				<tr>
					<td class="tit">登录名：</td>
					<td>
						<input class="nui-textbox" name="userid" style="width:95%"/>
					</td>
					<td class="tit">人员姓名：</td>
					<td>
						<input class="nui-textbox" name="empname" style="width:95%"/>
					</td>
					<td class="btn-wrap">
						<input class="nui-button" text="查询" onclick="search" iconCls="icon-search"/>
					</td>
				</tr>					
			</table>
		</div>
	</div>
  </div>
</div>
<div style="padding:5px 5px 0px 5px;">
	 <div class="nui-toolbar" style="border-bottom:0;">
        <table style="width:100%;">
            <tr>
	            <td style="width:100%;">
	                <a class="nui-button" iconCls="icon-add" onclick="add">增加</a>
	            	<a class="nui-button" iconCls="icon-remove" onclick="remove">删除</a>
	            </td>
            </tr>
        </table>
    </div>
</div>
<div class="nui-fit" style="padding:0px 5px 5px 5px;">
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;" 
	    url="org.gocom.components.coframe.org.empgroup.queryEmpInGroup.biz.ext" dataField="emps" ajaxData="getAjaxData" sizeList="[10,20,50,100]" multiSelect="true">
	    <div property="columns">
	        <div type="checkcolumn"></div>
	       	<div field="orgEmployee.empname" width="120" headerAlign="center" >人员姓名</div>
	        <div field="orgEmployee.empcode" width="120" headerAlign="center" >人员代码</div>
	        <div field="orgEmployee.userid" width="120" headerAlign="center" >登录名</div>
	     </div>
	</div>
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
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
    
    grid.load();
	
	function search(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
		var formData = form.getData(false, true);
		
		//var json = nui.encode(formData);
        grid.load(formData);
	}
	
	var bootPath = "<%=request.getContextPath() %>";
	
	function add(){
		nui.open({
            url: bootPath + "/coframe/org/group/employee_groupposi_add.jsp",
            title: "添加岗位人员", width: 530, height: 300,
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
	
	function remove(){
		var rows = grid.getSelecteds();
		var node = window.parentNode||{};
		if(rows && rows.length>0){
			var empGroups = [];
			for(var i=0,len=rows.length;i<len;i++){
				var empGroup = {orgEmployee:{empid:rows[i].orgEmployee.empid},groupid:rows[i].groupid};
				empGroups.push(empGroup);
			}
			var json = nui.encode({empGroups:empGroups});
			grid.loading("操作中，请稍后......");
            $.ajax({
                url: "org.gocom.components.coframe.org.empgroup.deleteEmpgroup.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (text) {
                    grid.reload();
                    if(window.parent.refreshCurrentNode){
                    	window.parent.refreshCurrentNode();
                    }
                },
                error: function () {
                }
            });
		}
	}
	
	function resetForm(){
		form.reset();
	}
	
    function getAjaxData(){
    	var node = window.parentNode || {};
    	if("OrgGroup"==node.nodeType){
    		return {groupid:node.groupid};
    	}
    }      
	
</script>

</body>
</html>