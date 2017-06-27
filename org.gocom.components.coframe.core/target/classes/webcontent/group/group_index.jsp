<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): gouyl
  - Date: 2013-10-09 14:43:27
  - Description:工作组管理主页
-->
<style>
#table1 .tit{
	height: 10px;
    line-height: 10px;
}
#table1 td{
	height: 10px;
    line-height: 10px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/coframe/tools/skins/common.jsp" %>
<link id="css_icon" rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/org/css/org.css"/>
<title>工作组列表</title>
<body>
 <div class="search-condition">
   <div class="list">
	 <div id="form1" style="padding:5px;">
		<table style="width:100%;table-layout:fixed;" id="table1" class="table" >
			<tr>
				<td class="tit">工作组名称：</td>
				<td>
					<input class="nui-textbox" name="criteria._expr[1].groupname" style="width:95%" vtype="maxLength:64"/>
					<input class="nui-hidden" name="criteria._expr[1]._op" value="like"/>
					<input class="nui-hidden" name="criteria._expr[1]._likeRule" value="all"/>
				</td>
				<td class="tit">工作组类型：</td>
				<td>
					<input name="criteria._expr[2].grouptype" data="data"  valueField="dictID" textField="dictName" 
						class="nui-dictcombobox" emptyText="全部" nullItemText="全部" dictTypeId="COF_ORGTYPE" style="width:95%"
						showNullItem="true" nullItemText="全部"/>
					<input class="nui-hidden" name="criteria._expr[2]._op" value="="/>
				</td>
				
				<td class="tit">工作组状态：</td>
				<td>
					<input name="criteria._expr[3].groupstatus" data="data" valueField="dictID" textField="dictName" 
						class="nui-dictcombobox" emptyText="全部" dictTypeId="COF_ORGSTATUS" style="width:95%"
						showNullItem="true" nullItemText="全部"/>
					<input class="nui-hidden" name="criteria._expr[3]._op" value="="/>
				</td>
				<td  class="btn-wrap">
					<input class="nui-button" text="查询" iconCls="icon-search" onclick="search"/>
				</td>
			</tr>					
		</table>
	</div>
  </div>
 </div>

	<div class="nui-fit" style="padding:5px;">
		<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;parding:5px;"
		    url="org.gocom.components.coframe.org.group.queryGroup.biz.ext" dataField="treeNodes" sizeList="[10,20,50,100]">
		    <div property="columns">
		        <div type="indexcolumn"></div>                
		        <div field="groupname" width="120" headerAlign="center" >工作组名称</div>    
		        <div field="grouptype" width="120" headerAlign="center" renderer="renderGrouptype">工作组类型</div> 
		        <div field="groupstatus" width="120" headerAlign="center"  renderer="renderGroupstatus">状态</div>    
		        <div field="startdate" width="120" headerAlign="center" >生效日期</div>    
		        <div field="enddate" width="120" headerAlign="center" >失效日期</div>    
		    </div>
		</div>
	</div>

<script type="text/javascript">
	nui.parse();
	
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

    function renderGrouptype(e) {
		return nui.getDictText("COF_ORGTYPE",e.row.grouptype);
	}
	function renderGroupstatus(e) {
		return nui.getDictText("COF_ORGSTATUS",e.row.groupstatus);
	}
</script>

</body>
</html>