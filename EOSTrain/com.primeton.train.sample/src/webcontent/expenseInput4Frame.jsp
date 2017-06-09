<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ page import="com.eos.data.datacontext.UserObject"%>
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
            报销单录入
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
        <div id="dataform1" style="padding-top:5px;">
            <!-- hidden域 -->
            <div class="nui-hidden" name="trainexpenseinfo.__type" id="entityName" value="sdo:com.primeton.train.expense.expensedataset.TrainExpenseinfo">
            </div>
            <div class="nui-hidden" name="trainexpenseinfo.expid">
            </div>
            <div class="nui-hidden" name="trainexpenseinfo.empid" id="empid" value=<%=userObject.getUserId() %>>
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
                        <input class="nui-textbox" name="trainexpenseinfo.expcode" required="true"/>
                    </td>
                    <td class="form_label">
                        报销人姓名:
                    </td>
                    <td colspan="1">
                        <input allowInput="false" class="nui-textbox" name="trainexpenseinfo.expname" id="expname" value="<%=userObject.getUserRealName() %>"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        报销日期:
                    </td>
                    <td colspan="1">
                        <input class="nui-datepicker" name="trainexpenseinfo.expdate" required="true"/>
                    </td>
                    <td class="form_label">
                        报销金额:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.expmoney" required="true" vtype="float"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        报销原因:
                    </td>
                    <td colspan="3">
                        <input class="nui-textarea" name="trainexpenseinfo.reason"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        是否预算内项目:
                    </td>
                    <td colspan="1">
                        <input class="nui-dictcombobox" dictTypeId="COF_YESORNO" name="trainexpenseinfo.isbudgetitem"/>
                    </td>
                    <td class="form_label">
                        受益部门:
                    </td>
                    <td colspan="1">
                        <input id="lookup2" class="nui-lookup" name="trainexpenseinfo.orgOrganization.orgid"
                        	textField="orgname" valueField="orgid" popupWidth="auto"
							popup="#gridPanel" grid="#datagrid1" multiSelect="false" value="5e9d1625-d14e-49f2-b618-efcaadeeca71" text="" />
                    </td>
                </tr>
                <tr>
                </tr>
            </table>
 
			<div id="gridPanel" class="nui-panel" title="header" iconCls="icon-add" style="width: 450px; height: 250px;"
					showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0" borderStyle="border:0">
					<div property="toolbar" style="padding: 5px; padding-left: 8px; text-align: center;">
						<div style="float: left; padding-bottom: 2px;">
							<span>部门名称：</span> <input id="keyText" class="nui-textbox"
								style="width: 160px;" onenter="onSearchClick" /> <a
								class="nui-button" onclick="onSearchClick">查询</a> <a
								class="nui-button" onclick="onClearClick">清除</a>
						</div>
						<div style="float: right; padding-bottom: 2px;">
							<a class="nui-button" onclick="onCloseClick">关闭</a>
						</div>
						<div style="clear: both;"></div>
					</div>
					<div id="datagrid1" class="nui-datagrid"
						style="width: 100%; height: 100%;" borderStyle="border:0"
						showPageSize="false" dataField="orgs" showPageIndex="false"
						url="com.primeton.train.expense.expense.queryOrg.biz.ext">
						<div property="columns">
							<div type="indexcolumn"></div>
							<div field="orgid" visible="false" headerAlign="center"
								allowSort="true">机构ID</div>
							<div field="orgcode" width="120" headerAlign="center"
								allowSort="true">机构编码</div>
							<div field="orgname" width="120" headerAlign="center"
								allowSort="true">机构名称</div>
							<div field="parentorgid" width="120" headerAlign="center"
								allowSort="true">上级机构</div>
						</div>
					</div>
			</div>

            <div class="nui-toolbar" style="padding:0px;" borderStyle="border:0;">
                <table width="100%">
                    <tr>
                        <td style="text-align:center;" colspan="4">
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
            
	        var grid = nui.get("datagrid1");
	        var keyText = nui.get("keyText");
	
	        grid.load();
	
	        function onSearchClick(e) {
	            grid.load({
	                key: keyText.value
	            });
	        }
	        function onCloseClick(e) {
	            var lookup2 = nui.get("lookup2");
	            lookup2.hidePopup();
	        }
	        function onClearClick(e) {
	            var lookup2 = nui.get("lookup2");
	            lookup2.deselectAll();
	        }


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
				var data = form.getData();
				var entity = {
					entity : data.trainexpenseinfo
					};
				//alert(nui.encode(data));
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

            //暂存
            function onOk() {
            	saveData();
            }

            //取消
            function onCancel() {
            	CloseWindow("cancel");
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
						
						var empid=nui.get("empid").getValue();
						if (empid == null || empid == undefined || empid == ""){
							nui.get("empid").setValue(<%=userObject.getUserId() %>);
							nui.get("expname").setValue("<%=userObject.getUserRealName() %>");
						}
						else{
							nui.get("lookup2").setText(o.trainexpenseinfo.orgOrganization.orgname);
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.responseText);
					}
				});
			}
            
        </script>
     </body>
  </html>
