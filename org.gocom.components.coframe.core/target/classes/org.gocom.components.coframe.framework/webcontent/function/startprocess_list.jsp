<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@include file="/coframe/tools/skins/common.jsp" %>
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-06 14:34:40
  - Description:
-->
<head>
</head>
<body>
	<div class="nui-fit" style="padding:5px;">
		<div id="datagrid1" class="nui-datagrid" url="org.gocom.components.coframe.flowconfig.processMgr.getFlows.biz.ext"  idField="processDefID" allowResize="false"
		    sizeList="[10,20,30,50]" pageSize="10" style="width:100%;height:100%;" onrowdblclick="onrowdblclick">
		    <div property="columns">
		        <div field="processDefID" width="10" >流程ID</div>
		        <div field="processDefName" width="20" headerAlign="center" allowSort="true">流程名称</div>    
		        <div field="processChName" width="30" headerAlign="center" allowSort="true">流程中文名</div>                            
		        <div field="versionSign" width="20" align="center" headerAlign="center" allowSort="true">版本号</div>
		    </div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
    nui.parse();
    
    var grid = nui.get("datagrid1");
    grid.load();
    grid.hideColumn(0);
    function getSelectedData() {
        var node = grid.getSelected();
        var resPara = "_eosFlowAction=action5&proDefID="+node.processDefID+"&desc="+node.processChName;
        var resUrl = "com.primeton.cap.sce.previewStartProcess.flow?processDefId="+node.processDefName+"&desc="+node.processChName+"&wfInstName="+node.processDefName+"inst"+"&_eosFlowKey=5f68feb1-3d03-48b3-a6c9-3c7f486cb7eb.view0";
        var resType = "startprocess";
        var resdata = nui.decode({resUrl:resUrl,resPara:resPara,resType:resType});
        return resdata;
    }
    function onrowdblclick(){
		parent.onOk();
	}
</script>

