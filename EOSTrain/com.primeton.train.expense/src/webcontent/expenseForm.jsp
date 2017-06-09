<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): train
  - Date: 2016-12-05 10:59:30
  - Description:
-->
<head>
<title>报销单新增页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript"></script>
    
</head>
<body>
<!-- 标识页面是查看(query)、修改(edit)、新增(add) -->
    <input name="pageType" class="nui-hidden"/>
    <div id="dataform1" style="padding-top:5px;">
      <!-- hidden域 -->
      <input class="nui-hidden" name="trainexpenseinfo.expid"/>
      <input class="nui-hidden" name="trainexpenseinfo.empid"/>
      <input class="nui-hidden" name="trainexpenseinfo.expname"/>
      <input class="nui-hidden" name="trainexpenseinfo.processinstid"/>
      
      <table style="width:100%;height:100%;table-layout:fixed;" class="nui-form-table">
        <tr>
          <td class="form_label">
            报销单编号:
          </td>
          <td colspan="1">
            <input class="nui-textbox" name="trainexpenseinfo.expcode" required="true"/>
          </td>
          <td class="form_label">
            报销日期:
          </td>
          <td colspan="1">
            <input class="nui-datepicker" name="trainexpenseinfo.expdate"/>
          </td>
        </tr>
        <tr>
          <td class="form_label">
            报销金额:
          </td>
          <td colspan="1">
            <input class="nui-textbox" name="trainexpenseinfo.expmoney"/>
          </td>
          <td class="form_label">
            报销原因:
          </td>
          <td colspan="1">
            <input class="nui-textbox" name="trainexpenseinfo.reason"/>
          </td>
        </tr>
        <tr>
          <td class="form_label">
            是否预算内项目:
          </td>
          <td colspan="1">
            <input class="nui-dictcombobox" dictTypeId="train_yesno" name="trainexpenseinfo.isbudgetitem"/>
          </td>
          <td class="form_label">
            受益部门:
          </td>
          <td colspan="1">
            <%--<input class="nui-textbox" name="trainexpenseinfo.benefitorgid"/>--%>
            <input id="lookup2" name="trainexpenseinfo.orgOrganization.orgid"
					class="nui-lookup" style="width: 200px;" textField="orgname" valueField="orgid" popupWidth="auto"
					popup="#gridPanel" grid="#datagrid1" multiSelect="true" />
            
          </td>
        </tr>
      </table>
      <div class="nui-toolbar" style="padding:0px;" borderStyle="border:0;">
        <table width="100%">
          <tr>
            <td style="text-align:center;" colspan="4">
              <a class="nui-button" iconCls="icon-save" onclick="onOk()">
                保存
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
    <div id="gridPanel" class="nui-panel" title="header" iconCls="icon-add" 
	showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0" borderStyle="border:0">
	<div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
	<div style="float:left;padding-bottom:2px;">
	<span>部门名称：</span>
	<input id="keyText" class="nui-textbox" style="width:160px;" onenter="onSearchClick"/>
	<a class="nui-button" onclick="onSearchClick">查询</a>
	<a class="nui-button" onclick="onClearClick">清除</a>
	</div>
	<div style="float:right;padding-bottom:2px;">
	<a class="nui-button" onclick="onCloseClick">关闭</a>
	</div>
	<div style="clear:both;"></div>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;" borderStyle="border:0" showPageSize="false"
	showPageIndex="false" dataField="orgs" url="com.primeton.train.expense.expense.queryOrg.biz.ext">
	<div property="columns">
	<div type="checkcolumn"></div>
	<div field="orgcode" width="120" headerAlign="center" allowSort="true">机构编码</div>
	<div field="orgname" width="120" headerAlign="center" allowSort="true">机构名称</div>
	<div field="parentorgid" width="120" headerAlign="center" allowSort="true">上级机构编号</div>
	</div>
	</div>
	</div>	
    

	<script type="text/javascript">
    	nui.parse();
    	function saveData(){

        var form = new nui.Form("#dataform1");
        form.setChanged(false);
        //保存
        var urlStr = "com.primeton.train.expense.expense.expenseAdd.biz.ext";
        var pageType = nui.getbyName("pageType").getValue();//获取当前页面是编辑还是新增状态
        //编辑
        if(pageType=="edit"){
          urlStr = "com.primeton.train.expense.expense.expenseUpdate.biz.ext";
        }
        form.validate();
        if(form.isValid()==false) return;

        var data = form.getData(false,true);
        var json = nui.encode(data);

        $.ajax({
          url:urlStr,
          type:'POST',
          data:json,
          cache:false,
          contentType:'text/json',
          success:function(text){
            var returnJson = nui.decode(text);
            if(returnJson.exception == null){
              CloseWindow("saveSuccess");
            }else{
              nui.alert("保存失败", "系统提示", function(action){
                if(action == "ok" || action == "close"){
                  //CloseWindow("saveFailed");
                }
                });
              }
            }
            });
          }

          //页面间传输json数据
          function setFormData(data){
            //跨页面传递的数据对象，克隆后才可以安全使用
            var infos = nui.clone(data);

            //保存list页面传递过来的页面类型：add表示新增、edit表示编辑
            nui.getbyName("pageType").setValue(infos.pageType);

            //如果是点击编辑类型页面
            if (infos.pageType == "edit") {
              var json = infos.record;

              var form = new nui.Form("#dataform1");//将普通form转为nui的form
              form.setData(json);
              form.setChanged(false);
              //lookup后编辑时获取到部门信息
              nui.get("lookup2").setText(json.trainexpenseinfo.orgOrganization.orgname);
            }
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

          //确定保存或更新
          function onOk() {
            saveData();
          }

          //取消
          function onCancel() {
            CloseWindow("cancel");
          }
          
          //lookup用到的方法
          var grid = nui.get("datagrid1");
		var keyText = nui.get("keyText");

		grid.load();

		function onSearchClick(e) {
			grid.load({
				orgname : keyText.value
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
          
    </script>
</body>
</html>