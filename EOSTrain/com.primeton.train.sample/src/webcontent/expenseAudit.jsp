<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
<%@ page import="com.eos.workflow.data.WFWorkItem"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): train
  - Date: 2016-12-07 19:43:52
  - Description:
-->
<head>
<title>财务审阅</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript"></script>
    
</head>
<%
	Long workItemID=(Long)request.getAttribute("workItemID");
%>

<body>
      <div id="dataform1" style="padding-top:5px;">
		<!-- hidden域 -->
		<div class="nui-hidden" name="trainexpenseinfo.expid">
		</div>
		<div class="nui-hidden" name="trainexpenseinfo.empid" id="empid">
		</div>
		<div class="nui-hidden" name="trainexpenseinfo.processinstid">
		</div>
		<div class="nui-hidden" name="workItemID" id="workItemID">
		</div>
		
		<table style="width:100%;height:100%;table-layout:fixed;" class="nui-form-table">
		<tr>
		</tr>
		<tr>
		<td class="form_label">
		报销单编号:
		</td>
		<td colspan="1">
		<input class="nui-textbox" name="trainexpenseinfo.expcode" allowInput="false"/>
		</td>
		<td class="form_label">
		报销人姓名:
		</td>
		<td colspan="1">
		<input allowInput="false" class="nui-textbox" name="trainexpenseinfo.expname" id="expname" allowInput="false"/>
		</td>
		</tr>
		<tr>
		<td class="form_label">
		报销日期:
		</td>
		<td colspan="1">
		<input class="nui-datepicker" name="trainexpenseinfo.expdate" allowInput="false"/>
		</td>
		<td class="form_label">
		报销金额:
		</td>
		<td colspan="1">
		<input class="nui-textbox" name="trainexpenseinfo.expmoney" allowInput="false"/>
		</td>
		</tr>
		<tr>
		<td class="form_label">
		报销原因:
		</td>
		<td colspan="3">
		<input class="nui-textarea" name="trainexpenseinfo.reason" allowInput="false"/>
		</td>
		</tr>
		<tr>
		<td class="form_label">
		是否预算内项目:
		</td>
		<td colspan="1">
		<input class="nui-dictcombobox" dictTypeId="COF_YESORNO" name="trainexpenseinfo.isbudgetitem" allowInput="false"/>
		</td>
		<td class="form_label">
		受益部门:
		</td>
		<td colspan="1">
		<input class="nui-textbox" name="trainexpenseinfo.orgOrganization.orgname" allowInput="false"/>
		</td>
		</tr>
		<tr>
		<td class="form_label">
		部门经理:
		</td>
		<td colspan="1">
		<input id="manager" class="nui-textbox" name="trainexpenseinfo.manager" allowInput="false"/>
		</td>
		<td class="form_label">
		部门经理意见:
		</td>
		<td colspan="1">
		<input class="nui-textarea" name="trainexpenseinfo.managerAdvice" allowInput="false"/>
		</td>
		</tr>
		<tr>
		<td class="form_label">
		财务审阅:
		</td>
		<td colspan="1">
		<input class="nui-textbox" name="trainexpenseinfo.auditor" required="true"/>
		</td>
		<td class="form_label">
		财务审阅意见:
		</td>
		<td colspan="1">
		<input class="nui-textarea" name="trainexpenseinfo.auditorAdvice" required="true"/>
		</td>
		</tr>
		<tr>
		</tr>
		</table>
		
		<div class="nui-toolbar" style="padding:0px;" borderStyle="border:0;">
		<table width="100%">
		<tr>
		<td style="text-align:center;" colspan="4">
		<a class="nui-button" iconCls="icon-save" onclick="onAudit()">
		记账
		</a>
		<span style="display:inline-block;width:25px;">
		</span>
		<a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">
		取消
		</a>
		</td>
		</tr>
		</table>
		</div>
		</div>
         


	<script type="text/javascript">
    	nui.parse();
    	loadData();

            //同意
            function onAudit() {
	auditData();
            }

            //取消
            function onCancel() {
	CloseWindow("cancel");
            }

			//页面打开时调用方法，根据workItemID加载表单内容
			function loadData() {
				var form = new nui.Form("dataform1");
	
				//将workItemID传递给加载逻辑			
				var data = {workItemID:<%=workItemID %>};
				var json = nui.encode(data);
	
				nui.ajax({
					url : "com.primeton.train.sample.setSign.getExpense.biz.ext",
					data : json,
					type : "POST",
					cache : false,
					contentType : "text/json",
					success : function(o) {
						form.setData(o);
						nui.get("workItemID").setValue(<%=workItemID%>);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.responseText);
					}
				});
			}

            function auditData(){

                var form = new nui.Form("#dataform1");
                form.setChanged(false);
                form.validate();
                if(form.isValid()==false) return;
			
                var data = form.getData();

                var json = nui.encode(data);

                $.ajax({
                    url:"com.primeton.train.sample.setSign.auditExpense.biz.ext",
                    type:'POST',
                    data:json,
                    cache:false,
                    contentType:'text/json',
                    success:function(text){
                        var returnJson = nui.decode(text);
                        if(returnJson.exception == null){
                            CloseWindow("ok");
                        }else{
                            nui.alert("提交失败", "系统提示", function(action){
                                if(action == "ok" || action == "close"){
                                    //CloseWindow("saveFailed");
                                }
                                });
                            }
                        }
                });
            }    

            //关闭窗口
            function CloseWindow(action) {
	if (action == "close" && form.isChanged()) {
	if (confirm("数据被修改了，是否先保存？")) {
	saveData();
                    }
                }
                if (window.CloseOwnerWindow)
	return window.CloseOwnerWindow(action);
                else window.close();
            }
    	
    </script>
</body>
</html>