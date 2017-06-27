<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): lijt (mailto:lijt@primeton.com)
  - Date: 2013-04-15 09:00:32
  - Description:实体规则维护与授权
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<%@include file="/coframe/tools/skins/common.jsp" %>
<%@ page import="com.eos.system.utility.StringUtil" %>
<head>
	<title>实体规则授权</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<style>
		.icon-rule-all{
			background:url(<%=contextPath%>/entityauth/images/rule_all.png) no-repeat;
		}
		.icon-rule-assign{
			background:url(<%=contextPath%>/entityauth/images/rule_assign.png) no-repeat;
		}		
		.icon-rule-disassign{
			background:url(<%=contextPath%>/entityauth/images/rule_disassign.png) no-repeat;
		}		
		
/*		.mini-button-text {
    		display: inline-block;
    		line-height: 16px;
    		padding: 2px 5px 3px 19px;
    		vertical-align: top;
		}
*/
		.icon-edit{background-position: left center;}
		.icon-remove{background-position: left center;}
	</style>
</head>
<body>
<%
String partyId = StringUtil.htmlFilter(request.getParameter("roleId"));
String partyType = "role";
String url = "org.gocom.components.coframe.entityauth.rule.getEntityCapRules.biz.ext";
%>
<div class="nui-fit" id="fit1" style="padding:10px;">
	
	<div id="entityForm2">
		<div id="nui-form2-toolbar" class="nui-toolbar" style="text-align:left;line-height:25px;padding:5px 0px 5px 10px;" borderStyle="border-bottom:0;">
			<table id="employeeToolBar2" >
				<tr>
					<td nowrap align="right">实体名称：</td>
					<td align="left">
						<input class="nui-combobox" id="qName" name="qName" textField="QName" valueField="QName" 
							url="org.gocom.components.coframe.entityauth.entity.getEntityInfos.biz.ext" dataField="entityInfos" value="" emptyText="请输入实体名称" showNullItem="true" nullItemText="所有实体" allowInput="true" valueFromSelect="true"/>
					</td>
					<td nowrap align="right">规则名称：</td>
					<td align="left"><input id="ruleName" class="nui-textbox" name="ruleName" emptyText="请输入规则名称" onenter="onKeyEnter"/>  </td>
					<td style="width:90px;">
					<div id="searchChose" class="nui-combobox" onitemclick="onItemClick" value="1" width="60px"
        				textField="text" valueField="id"></div>
					</td>
					<td style="width:70px;"><input class="nui-button" iconCls="icon-search" text="查询" onclick="searchTotal()" /></td>
					<td style="width:1px;"><input id="searchType" class="nui-hidden" value="1"/></td>
				</tr>
			</table>
		</div>
	</div>
	
	<div class="nui-toolbar" style="border-bottom:0;padding:2px;">
        <table >
            <tr>
                <td style="width:100%;">
                    <a class="nui-button" iconCls="icon-add" onclick="add">增加</a>
                    <a class="nui-button" iconCls="icon-save" onclick="saving" id="btn_save">批量授权</a>   
                </td>
                <td style="white-space:nowrap;">
            </td>
            </tr>
        </table>           
    </div>
    <div class="nui-fit">
		<div id="datagrid1" class="nui-datagrid" url="<%=url %>"  idField="id" allowResize="false"
			     style="height:100%;width:100%;" ajaxData="setGridData" multiSelect="true" allowRowSelect="true" 
			     onload="onGridLoad"
			     allowCellEdit="true" showModified="false" allowSortColumn="false" showPager="false">
		    <div property="columns">
		        <div field="authIconFlag" width="20" headerAlign="center" allowSort="true" align="center" renderer="onOperateRenderer">操作</div>  
		        <div field="id" width="18">规则ID</div>
		        <div field="name" width="40" headerAlign="center" allowSort="true">规则名称</div>
		        <div field="type" width="20" headerAlign="center" allowSort="true">规则类型</div>
		        <div field="namespace" width="80" headerAlign="center" allowSort="true">命名空间</div>
		        <div type="checkboxcolumn" field="auth" width="18" trueValue="1" falseValue="0">是否授权</div>
		    </div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var namespace = "";
    var searchType = "1";
    var ruleName = "";
	nui.parse();
	var fit1 = document.getElementById("fit1");
	
	function resize(){
		var w = document.body.clientWidth;
		w = parseInt(w);
		fit1.style.width = w > 700 ? w - 20 : 700+"px";
	}
	window.onresize = resize;
	resize();
	var width = nui.get("nui-form2-toolbar").getWidth( );
	nui.get('qName').setWidth(450);
	var grid = nui.get("datagrid1");
    grid.load();
    grid.hideColumn(1);
    
    
    var searchChose=nui.get("searchChose");
	var searchTypeData=[
		{id:"1",text:"全部"},
		{id:"2",text:"已授权"},
		{id:"3",text:"未授权"}
	];
	
	searchChose.setData(searchTypeData);
	
	function onItemClick(e){
		nui.get("searchType").setValue(e.item.id);
	}
    
    function setGridData(){
    	return {"ruleName": ruleName,"namespace": namespace,"searchType":searchType, party:{"id": "<%=partyId %>", "partyTypeID": "<%=partyType %>"}};
    }
	
	function searchTotal(){
		grid.deselectAll();
		namespace = nui.get('qName').getValue();
    	searchType = nui.get("searchType").getValue();
    	ruleName = nui.get("ruleName").getValue();
    	if(!ruleName) ruleName = "";
    	grid.load({ "ruleName": ruleName,"namespace":namespace,"searchType":searchType, party:{"id": "<%=partyId %>", "partyTypeID": "<%=partyType %>"}});
	}
	
	var auths = [{ id: "0", text: '未授权', img:"<img title='未授权' src='<%=contextPath%>/entityauth/images/rule_disassign.png'/>"}, { id: "1", text: '已授权', img:"<img title='已授权' src='<%=contextPath%>/entityauth/images/rule_assign.png'/>"}];    
	function onAuthRenderer(e) {
		for (var i = 0, l = auths.length; i < l; i++) {
        	var g = auths[i];
            if (g.id == e.value) {
            	return g.img;
            }
        }
        return "";
	}
	
	function onOperateRenderer(e) {
		var record = e.record;
		var uid = record._uid;
		return "<a class='mini-button-text mini-button-icon icon-edit' title='编辑' style='cursor:pointer' onclick='edit(\""+uid+"\")'>&nbsp;</a><a style='cursor:pointer' title='删除' class='mini-button-text mini-button-icon icon-remove' onclick='removeRecord(\""+uid+"\")'>&nbsp;</a>";
	}
	
	//打开添加规则页面
	function add(){
		var qName = nui.get('qName').getValue();
		if(""==qName) {
			alert("请先选择一个实体名称！");
			nui.get('qName').focus();
		} else {
			nui.open({
				url: "<%=contextPath%>/entityauth/rule/add_entity_rule.jsp",
				title: "添加规则", width: "695px", height: "500px",
				onload: function () {
                	var iframe = this.getIFrameEl();
                	var data = {qName: qName, resauth:{"partyId": "<%=partyId %>", "partyType": "<%=partyType %>"}};
                	iframe.contentWindow.loadData(data);
            	},
            	ondestroy: function (action) {
                	grid.reload();
            	}
			});
		}
	}
	
	function onGridLoad(){
		var ruleData = grid.getData();
		nui.get("btn_save").set({"enabled":(ruleData.length>0)});
	}
	
	// 保存权限
	function saving(e){
		e.sender.setEnabled(false);
		var entityWithAuth = [];
		var entityNoAuth = [];
		var ruleData = grid.getData();
		if (ruleData.length<1)return;
		
		for(var i = 0; i < ruleData.length; i++){
			if(ruleData[i].auth == "1"){
				entityWithAuth.push(ruleData[i].id);
			}else{
				entityNoAuth.push(ruleData[i].id);
			}
		}
		
		var sendData = {party:{"id":"<%= partyId%>", "partyTypeID": "<%=partyType %>"}, partyWithAuth:entityWithAuth, partyNoAuth:entityNoAuth};
		$.ajax({
			url:"org.gocom.components.coframe.entityauth.rule.storePartyAuth.biz.ext",
			type: "POST",
			data: nui.encode(sendData),
			cache: false,
			contentType: "text/json",
			success: function(res){
				var returnJson = nui.decode(res);
				if(res.exception || res.saveResult == false){
					nui.alert("权限设置失败");
				}else{
					nui.alert("权限设置成功");
				}
				grid.reload();
				grid.deselectAll();
				e.sender.setEnabled(true);
			},
			error: function () {
            	nui.alert("权限设置失败");
				e.sender.setEnabled(true);
            }
		});
	}
	
	//编辑规则
	function edit(row_uid){
		var row = grid.getRowByUID(row_uid);
		var ruleId = row.id;
		var ruleName = row.name;
		var namespace = row.namespace;		
		nui.open({
			url: "<%=contextPath%>/entityauth/rule/edit_entity_rule.jsp",
			title: "编辑规则", width: "695px", height: "500px",
			onload: function () {
            	var iframe = this.getIFrameEl();
            	var data = {ruleId:ruleId, qName: namespace, ruleName:ruleName, resauth:{"partyId": "<%=partyId %>", "partyType": "<%=partyType %>"}};
            	iframe.contentWindow.loadData(data);
        	},
        	ondestroy: function (action) {
            	grid.reload();
        	}
		});
	}
	
	// 删除规则
	function removeRecord(row_uid) {
		var row = grid.getRowByUID(row_uid);
        var rules = [];
		rules.push({id:row.id});
		
		var sendData = {party:{"id":"<%= partyId%>", "partyTypeID": "<%=partyType %>"}, rules:rules};
		//alert(JSON.stringify(sendData));
 		
    	nui.confirm("确定删除选中记录,同时删除所有角色授权？", "提示",
        	function (action) {            
            	if (action == "ok") {
                	$.ajax({
						url:"org.gocom.components.coframe.entityauth.rule.delRules.biz.ext",
						type: "POST",
						data: nui.encode(sendData),
						cache: false,
						contentType: "text/json",
						success: function(text){
							var returnJson = nui.decode(text);
							if(returnJson.flag=="success"){
								nui.alert("删除成功");
								grid.reload();
							}else{
								nui.alert("删除失败");
							}
						},
						error: function(jqXHR, textStatus, errorThrown){}
					});
            	}
        	}
    	);
    } 
    document.getElementById("qName").style.width = "190px";
</script>
</body>
</html>
