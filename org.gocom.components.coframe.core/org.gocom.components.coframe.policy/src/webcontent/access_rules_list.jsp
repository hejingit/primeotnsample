<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/coframe/tools/skins/common.jsp"%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title>IP访问规则</title>
	<link id="css_skin" rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/tools/skins/skin1/css/style.css"/>
</head>
<body style="margin:0px;">
<div class="nui-toolbar" style="padding:2px;border-bottom:0;">
	<table style="width:100%;">
		<tr>
			<td style="width:100%;">
				<a class="nui-button" iconCls="icon-add" style="margin-right:3px"
					onclick="addRules()" >
					添加
				</a>
				<a class="nui-button" iconCls="icon-edit" style="margin-right:3px"
					onclick="editRules()" id = "edit_btn">
					编辑
				</a>
				<a class="nui-button" iconCls="icon-remove"
					onclick="removeRules()">
					删除
				</a>	
			</td>
		</tr>
	</table>
</div>
<div class="nui-fit">
	<div id="rulesgrid" class="nui-datagrid"
		style="width:100%;height:100%;" allowResize="false" pageSize="10"
		url="org.gocom.components.coframe.policy.ipRuleComponent.getAccessRules.biz.ext"
		idField="rulesId" dataField="rules" multiSelect="true" onselectionchanged="selectionChanged"
	    showPager="false">
		<div property="columns">
			<div type="checkcolumn" width=4%>选择</div>
			<div field="startIP" headerAlign="center" align="center" width=12%>
				起始IP
			</div>
			<div field="endIP" headerAlign="center" align="center" width=12%>
				结束IP
			</div>
			<div renderer="renderRulesType" field="rulesType" headerAlign="center" align="center" width=12%>
				规则类型
			</div>
			<div field="remark" headerAlign="center" align="center" width=52%>
				备注
			</div>
			<div renderer="renderRulesEnabled" field="enabled" headerAlign="center" align="center" width=8%>
				是否生效
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
nui.parse();
var grid = nui.get("rulesgrid");
grid.load();

function addRules(){
 openDialog({
    	  	title: "添加IP访问规则",
            url: '<%=contextPath%>/policy/access_rules_edit.jsp',
            width: 400,
            height: 255
    	    });
}

function editRules() {
    var row = grid.getSelected();
    if (row) {
     openDialog(
    	{	title: "编辑IP访问规则",
            url: '<%=contextPath%>/policy/access_rules_edit.jsp',
            data: row,
            width: 400,
            height: 255
            });
    } else {
        nui.alert("请选中一条记录");
    }
}

function renderRulesType(e){
		return nui.getDictText("COFRAME_RULES_TYPE", e.row.rulesType);
	}
function renderRulesEnabled(e){
		return nui.getDictText("COFRAME_RULES_ENABLE", e.row.enabled);
	}

function removeRules(){
            var rows = grid.getSelecteds();
            var data = {select_objs:rows};
            var delrules = nui.encode(data);
            if (rows!='') {
                if (confirm("确定删除选中记录？")) {
                    grid.loading("操作中，请稍后......");
                    nui.ajax({
                        url: "org.gocom.components.coframe.policy.ipRuleComponent.deleteAccessRules.biz.ext",
                        type: "post",
                        data: delrules, 
                        cache: false,
                        contentType: 'text/json',
                        success: function (text) {
                            grid.reload();
                        },
                        error: function () {
                        }
                    });
                }
            } else {
                nui.alert("请选中一条记录");
            }
}
        
//改变编辑按钮样式
function selectionChanged() {
    var rows = grid.getSelecteds();
    if (rows.length > 1) {
        //disable edit button
        nui.get("edit_btn").disable();
    } else {
        nui.get("edit_btn").enable();
    }
}

function openDialog(params) {
    var openParams = nui.clone(params);
    openParams.onload || (openParams.onload = function() {
        var iframe = this.getIFrameEl();
        var contentWindow = iframe.contentWindow;
        if (contentWindow.SetData) {
            contentWindow.SetData(openParams.data);
        }
    });
    // 子窗口点保存时刷新父窗口
    openParams.ondestroy || (openParams.ondestroy = function() {
             grid.reload();
       });

    nui.open(openParams);
}
</script>
</html>