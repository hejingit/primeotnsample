<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@ page import="com.eos.workflow.data.WFWorkItem"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): train
- Date: 2016-08-16 09:50:51
- Description:
    --%>
    <head>
        <title>
            报销单审批
        </title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript">
        </script>
    	<%@ include file="/bizform/bizformCommon.jsp" %>
    </head>
<%
	UserObject userObject = (UserObject)request.getSession().getAttribute("userObject");
	Long workItemID=(Long)request.getAttribute("workItemID");
%>
    <body>
        <!-- 标识页面是查看(query)、修改(edit)、新增(add) -->
        <input name="pageType" class="nui-hidden"/>
        <div id="dataform1" style="padding-top:5px;">
            <!-- hidden域 -->
            <div class="nui-hidden" name="trainexpenseinfo.__type" id="entityName" value="sdo:com.primeton.train.expense.expensedataset.TrainExpenseinfo">
            </div>
            <div class="nui-hidden" name="trainexpenseinfo.expid">
            </div>
            <div class="nui-hidden" name="trainexpenseinfo.empid" id="empid">
            </div>
            <div class="nui-hidden" name="trainexpenseinfo.orgOrganization.orgid">
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
					    <input id="manager" class="nui-textbox" name="trainexpenseinfo.manager" required="true"/>
					</td>
					<td class="form_label">
		  部门经理意见:
					</td>
					<td colspan="1">
					    <input class="nui-textarea" name="trainexpenseinfo.managerAdvice" required="true"/>
					</td>
                </tr>
            </table>
            
        </div>
        <script type="text/javascript">
            nui.parse();

			var form = new nui.Form("#dataform1");
			
			/**
			* 表单框架初始化时调用：
			* 用于表单数据初始化
			*/
			function initData(e){
				loadData();
			}

			/**
			* 表单提交之前调用：
			* 可能用于数据校验等操作
			*/
			function beforeSubmit(e){
				form.validate();
	    	 	e.submit = form.isValid();
			}

			/**
			* 返回需要提交的业务数据 
			*/
			function getSubmitData(e) {
				//下面是示例
				var data = form.getData();
				var entity = {
					entity : data.trainexpenseinfo
					};
				alert(nui.encode(entity));
				return entity;
			}
			
			/**
			* 表单提交之后调用
			* 有可能需要返回业务数据主键
			* e.data = {
			  pkID: 1,
			  workItemID: 1,
			  processInstID: 1
			}
			*/
			function afterSubmit(e){
				if(e.returnData.exception){
					nui.alert("提交出错，请联系管理员");
					return;
				}
				nui.alert("提交成功");
                if (window.CloseOwnerWindow)
                	return window.CloseOwnerWindow(action);
                else window.close();
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

			//页面打开时调用方法    	
			function loadData() {
				var form = new nui.Form("dataform1");
				
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
						nui.get("entityName").setValue("sdo:com.primeton.train.expense.expensedataset.TrainExpenseinfo");
						nui.get("workItemID").setValue(<%=workItemID%>);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.responseText);
					}
				});
			}
                    
       </script>
     </body>
  </html>
