<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): train
- Date: 2016-12-05 14:56:27
- Description:
    --%>
    <head>
        <title>
            TrainExpenseinfo录入
        </title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript">
        </script>
    </head>
    <body>
        <!-- 标识页面是查看(query)、修改(edit)、新增(add) -->
        <input name="pageType" class="nui-hidden"/>
        <div id="dataform1" style="padding-top:5px;">
            <!-- hidden域 -->
            <div class="nui-hidden" name="trainexpenseinfo.expid">
            </div>
            <div class="nui-hidden" name="trainexpenseinfo.processinstid">
            </div>
            <input class="nui-hidden" name="trainexpenseinfo.expid"/>
            <table style="width:100%;height:100%;table-layout:fixed;" class="nui-form-table">
                <tr>
                    <td class="form_label">
                        报销单编号:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.expcode"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        报销人id:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.empid"/>
                    </td>
                    <td class="form_label">
                        报销人姓名:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.expname"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        报销日期:
                    </td>
                    <td colspan="1">
                        <input class="nui-datepicker" name="trainexpenseinfo.expdate"/>
                    </td>
                    <td class="form_label">
                        报销金额:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.expmoney"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        报销原因:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.reason"/>
                    </td>
                    <td class="form_label">
                        是否预算内项目:
                    </td>
                    <td colspan="1">
                        <input class="nui-dictcombobox" dictTypeId="train_yesno" name="trainexpenseinfo.isbudgetitem"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        经理:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.manager"/>
                    </td>
                    <td class="form_label">
                        经理意见:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.managerAdvice"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        财务审阅:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.auditor"/>
                    </td>
                    <td class="form_label">
                        财务审阅意见:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.auditorAdvice"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        orgid:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="trainexpenseinfo.orgOrganization.orgid"/>
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
        <script type="text/javascript">
            nui.parse();

            function saveData(){

                var form = new nui.Form("#dataform1");
                form.setChanged(false);
                //保存
                var urlStr = "com.primeton.train.expenseauto.trainexpenseinfobiz.addTrainExpenseinfo.biz.ext";
                var pageType = nui.getbyName("pageType").getValue();//获取当前页面是编辑还是新增状态
                //编辑
                if(pageType=="edit"){
                    urlStr = "com.primeton.train.expenseauto.trainexpenseinfobiz.updateTrainExpenseinfo.biz.ext";
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
                </script>
            </body>
        </html>
