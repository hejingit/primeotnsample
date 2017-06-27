<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/coframe/tools/skins/common.jsp"%>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>
桌面图标配置
</title>
<link id="css_skin" rel="stylesheet" type="text/css"
	href="<%=contextPath%>/coframe/tools/skins/skin1/css/style.css" />
</head>
<body style="width:100%;height:100%;overflow:hidden;">
	<table style="width:100%;height:100%;overflow:hidden;">
	<tr>
		<td valign="top" style="height:100%;">
		<div id="panel1" class="mini-panel" title="从菜单导入" style="width:100%;height:100%;" 
		    showToolbar="true" showCollapseButton="false" showFooter="false" allowResize="false" collapseOnTitleClick="false"
		>
		    <!--toolbar-->
		    <div property="toolbar" style="padding-left:5px;">
		        <input type='button' class="nui-button" text='全选' onclick="selectAll()" plain="true"/>
		        <input type='button' class="nui-button" text='清空' onclick="clearAll()" plain="true"/>
		        <input type='button' class="nui-button" text='生成图标' plain="true" onclick="importIcon()" iconCls="icon-downgrade"/>
		    </div>
		   <ul id="menuTree" onload="autoStartTreeOnload"
				class="nui-tree" showCheckBox="true"
				url="org.gocom.components.coframe.auth.LoginManager.getMenuData.biz.ext"
				style="width:180px;padding:5px;" showTreeIcon="true"
				dataField="treeNodes" textField="menuName" idField="menuPrimeKey"
				expandOnNodeClick="true" resultAsTree="true"
				nodesField="childrenMenuTreeNodeList" showTreeLines="false"
				showTreeLines="false" showExpandButtons="false"
				checkRecursive="true" expandOnLoad="true">
			</ul>  
		
		</div>
			
		</td>
		<td valgin="top" style="height:100%;">
			
				<div>
					<div class="mini-toolbar"
						style="border-bottom:0;padding:0px;">
						<table style="width:100%;">
							<tr>
								<td style="width:100%;">
									<a class="mini-button"
										iconCls="icon-remove" onclick="removeRow()" plain="true">
										删除
									</a>
									<span class="separator"></span>
									<a class="mini-button"
										iconCls="icon-save" onclick="saveData()" plain="true">
										保存
									</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="nui-fit">
				<div id="iconGrid" class="mini-datagrid"
					url="org.gocom.components.coframe.skins.win7.win7Service.getMyConfig.biz.ext"
					idField="iconId" allowResize="true" allowCellEdit="true" showPager="false"
					allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true" dataField="icons"
					style="width:100%;">
					<div property="columns">
						<div type="indexcolumn"></div>
						<div type="checkcolumn"></div>
						<div field="iconText" width="100" allowSort="true">
							文本
							<input property="editor"
								class="nui-textbox"  />
						</div>
						<div field="iconTitle" width="100" allowSort="true">
							标题
							<input property="editor"
								class="nui-textbox"  />
						</div>
						<div field="iconPath" width="100" allowSort="true">
							图标路径
							<input property="editor"
								class="nui-textbox"  />
						</div>
						<div field="iconOrder" width="100" allowSort="true">
							显示顺序
							<input property="editor"
								class="nui-spinner"  />
						</div>
						<div field="iconDesc" width="100" allowSort="true">
							描述
							<input property="editor"
								class="nui-textbox"  />
						</div>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
		
    <script type="text/javascript">

     
        mini.parse();

        var grid = nui.get("iconGrid");
        var tree = nui.get("menuTree");
        grid.load();

        //////////////////////////////////////////////////////

        function importIcon() {          
            var nodes = tree.getCheckedNodes();
            if(!nodes){
            	return;
            }
            var datas = grid.getData();
            for(var i=0;i<nodes.length;i++){
            	var node = nodes[i];
            	if(!node.linkAction){
            		continue;
            	}
            	var hasMenu = false;
            	for(var j=0;j<datas.length;j++){
            		var row = datas[j];
            		if(row.menuId == node.menuPrimeKey){
            			hasMenu = true;
            			break;
            		}
            	}
            	if(!hasMenu){
            		grid.addRow({
            			iconText:node.menuName,
            			iconTitle:node.menuName,
            			iconPath:node.imagePath,
            			menuId:node.menuPrimeKey
            		});
            	}
            }
        }
        
        function selectAll(){
        	tree.checkAllNodes();
        }
        
        function clearAll(){
			tree.uncheckAllNodes();
        }
        
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
        }
        function saveData() {
            var data = grid.getData();
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "org.gocom.components.coframe.skins.win7.win7Service.updateIconsConfig.biz.ext",
                cache: false,
                contentType:'text/json',
                data: nui.encode({icons: data }),
                type: "post",
                success: function (text) {
                	var result = nui.decode(text);
		            if(!result.exception){
	                   grid.reload();
		            }else{
		               nui.alert(result.exception.message);
		            }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });
        }




    </script>	

</body>
</html>
