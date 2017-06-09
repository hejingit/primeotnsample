<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): train
- Date: 2016-12-05 15:03:19
- Description:
    --%>
    <head>
        <title>
            TrainEmp录入
        </title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript">
        </script>
    </head>
    <body>
        <fieldset style="border:solid 1px #aaa;position:relative;margin:5px 2px 0px 2px;">
            <legend>
                TrainEmp
            </legend>
            <div id="dataform1" style="padding-top:5px;">
                <!-- hidden域 -->
                <div class="nui-hidden" name="trainemp.empid">
                </div>
                <input class="nui-hidden" name="trainemp.trainContactss" id="trainemp.trainContactss"/>
                <input class="nui-hidden" name="trainemp.empid" id="trainemp.empid"/>
                <table style="width:100%;height:95%;table-layout:fixed;" class="nui-form-table">
                    <tr>
                        <td class="form_label">
                            orgid:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="trainemp.orgid"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form_label">
                            empcode:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="trainemp.empcode"/>
                        </td>
                        <td class="form_label">
                            empname:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="trainemp.empname"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form_label">
                            sex:
                        </td>
                        <td colspan="1">
                            <input class="nui-dictcombobox" dictTypeId="gender" name="trainemp.sex"/>
                        </td>
                        <td class="form_label">
                            birthday:
                        </td>
                        <td colspan="1">
                            <input class="nui-datepicker" name="trainemp.birthday"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form_label">
                            postcode:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="trainemp.postcode"/>
                        </td>
                        <td class="form_label">
                            address:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="trainemp.address"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form_label">
                            phone:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="trainemp.phone"/>
                        </td>
                        <td class="form_label">
                            wage:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="trainemp.wage"/>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
        <!-- 从表的修改 -->
        <div style="margin:0px 2px 0px 2px;" >
            <div class="nui-tabs" id="tab" activeIndex="0" style="width:100%;height:100%;">
                <div title="TrainContacts">
                    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
                        <table style="width:100%;">
                            <tr>
                                <td style="width:100%;">
                                    TrainContacts
                                </td>
                                <td >
                                    <a class="nui-button " iconCls="icon-add" onclick="gridAddRow('grid_0')"  plain="true" tooltip="增加">
                                        &nbsp;
                                    </a>
                                </td>
                                <td >
                                    <a class="nui-button " iconCls="icon-remove" onclick="gridRemoveRow('grid_0')"  plain="true" tooltip="删除">
                                        &nbsp;
                                    </a>
                                </td>
                                <td >
                                    <a class="nui-button " iconCls="icon-reload" onclick="gridReload('grid_0')"  plain="true" tooltip="刷新">
                                        &nbsp;
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div 
                            id="grid_0"
                            class="nui-datagrid"
                            style="width:100%;height:150px;"
                            showPager="false"
                            sortMode="client"
                            url="com.primeton.relationauto.trainempbiz.getTrainEmp.biz.ext"
                            dataField="trainemp.trainContactss"
                            allowCellEdit="true"
                            allowCellSelect="true"
                            multiSelect="true"
                            editNextOnEnterKey="true">

                        <div property="columns">
                            <div type="checkcolumn">
                            </div>
                            <div field="contactname" allowSort="true" align="left" headerAlign="center" width="">
                                contactname
                                <input class="nui-textbox" enabled="true" name="contactname" property="editor" required="false" visible="true"/>
                            </div>
                            <div field="relation" allowSort="true" align="left" headerAlign="center" width="">
                                relation
                                <input class="nui-textbox" enabled="true" name="relation" property="editor" required="false" visible="true"/>
                            </div>
                            <div field="phone" allowSort="true" align="left" headerAlign="center" width="">
                                phone
                                <input class="nui-textbox" enabled="true" name="phone" property="editor" required="false" visible="true"/>
                            </div>
                            <div field="postcode" allowSort="true" align="left" headerAlign="center" width="">
                                postcode
                                <input class="nui-textbox" enabled="true" name="postcode" property="editor" required="false" visible="true"/>
                            </div>
                            <div field="address" allowSort="true" align="left" headerAlign="center" width="">
                                address
                                <input class="nui-textbox" enabled="true" name="address" property="editor" required="false" visible="true"/>
                            </div>
                            <div field="email" allowSort="true" align="left" headerAlign="center" width="">
                                email
                                <input class="nui-textbox" enabled="true" name="email" property="editor" required="false" visible="true"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
            var form = new nui.Form("#dataform1");
            var tab = nui.get("tab");
            form.setChanged(false);

            function onOk(){
                saveData();
            }

            function gridAddRow(datagrid){
                var grid = nui.get(datagrid);
                grid.addRow({});
            }

            function gridRemoveRow(datagrid) {
                var grid = nui.get(datagrid);
                var rows = grid.getSelecteds();
                if (rows.length > 0) {
                    grid.removeRows(rows, true);
                }
            }
            function gridReload(datagrid){
                var grid = nui.get(datagrid);
                grid.reload();
            }

            function setGridData(datagrid,dataid){
                var grid = nui.get(datagrid);
                var grid_data = grid.getData();
                nui.get(dataid).setValue(grid_data);
            }
            function setData(data){
                data = nui.clone(data);
                nui.get("grid_0").load({trainemp:data});
                var json = nui.encode({trainemp:data});
                $.ajax({
                    url:"com.primeton.relationauto.trainempbiz.getTrainEmp.biz.ext",
                    type:'POST',
                    data:json,
                    cache:false,
                    contentType:'text/json',
                    success:function(text){
                        obj = nui.decode(text);
                        form.setData(obj);
                        form.setChanged(false);
                    }
                    });
                }


                function saveData(){
                    form.validate();
                    if(form.isValid()==false) return;
                    setGridData("grid_0","trainemp.trainContactss");
                    var data = form.getData(false,true);
                    var json = nui.encode(data);
					alert(json);
                    $.ajax({
                        url:"com.primeton.relationauto.trainempbiz.updateTrainEmp.biz.ext",
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

                        function onReset(){
                            form.reset();
                            form.setChanged(false);
                        }

                        function onCancel(){
                            CloseWindow("cancel");
                        }

                        function CloseWindow(action){

                            if(action=="close"){

                                }else if(window.CloseOwnerWindow)
                                return window.CloseOwnerWindow(action);
                                else
                                return window.close();
                            }
                        </script>
                    </body>
                </html>
