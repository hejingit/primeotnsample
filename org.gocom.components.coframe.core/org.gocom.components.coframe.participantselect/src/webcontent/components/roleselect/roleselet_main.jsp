<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="/coframe/tools/skins/common.jsp" %>

<html>

<head>
<title>角色选择</title>
</head>
<body>
<div class="mini-toolbar">
	<div class="mini-toolbar">
		<table width="100%">
			<tr>
				<td>
					<div style="text-align:left;line-height:30px;"
						borderStyle="border:1;">
						<a class="mini-button" iconCls="icon-add"
							plain="true" onclick="selectAll()">
							全部选中
						</a>
						<a class="mini-button" iconCls="icon-remove"
							plain="true" onclick="removeAll()">
							全部删除
						</a>
					</div>
				</td>
				<td>
					<div id="searchsearch"
						style="text-align:right;line-height:30px;height:100%;"
						borderStyle="border:2;">
						<label>名称：</label>
						<input id="rolename" class="mini-textbox"
							style="width:150px;" onenter="onKeyEnter" /> 
						<a class="mini-button" style="width:90px;" iconCls="icon-search"  
							onclick="roleSearch()">
							角色查询
						</a>
					</div>
				</td>
			</tr>
		</table>


	</div>
</div>
<div class="mini-fit">

	<div id="rolegrid1" class="mini-datagrid"
		style="width:100%;height:100%;" borderStyle="border:0;"
		url="org.gocom.components.coframe.participantselect.roleselect.roleListWithPage.biz.ext"
		allowCellSelect="true" showPager="true" totalField="page.count"
		sizeList=[20,40,60,100] allowRowSelect="true"
		onrowclick="onrowclick">

		<div property="columns">
			<div field="roleId" visible="false" width="80"
				headerAlign="center" align="center" allowSort="true">
				角色编号
			</div>
			<div field="roleCode" width="80" headerAlign="center"
				align="center" allowSort="true">
				角色代码
			</div>
			<div field="roleName" width="100" headerAlign="center"
				align="center" allowSort="true">
				角色名称
			</div>
			<div field="roleselect" width="30" headerAlign="center"
				align="center" renderer="showcheckbox">
				选择

			</div>

		</div>
	</div>
</div>
</div>

<div property="footer" class="mini-toolbar"
	style="padding:2px;border-top:0;border-left:0;border-right:0;">
	<div>选择角色：</div>
	<div>
		<input id="selectroles" class="mini-textboxlist"
			name="selectroles" textName="selectrolesName" required="true"
			style="width:100%;height:80px;" value="" text="" valueField="id"
			textField="text" />
	</div>
</div>
<div class="mini-toolbar"
	style="text-align:center;padding-top:8px;padding-bottom:8px;"
	borderStyle="border:0;">
	<a class="mini-button" style="width:60px;" iconCls="icon-ok"  
		onclick="closeWindow('ok')">
		确定
	</a>
	<span style="display:inline-block;width:25px;"></span>
	<a class="mini-button" style="width:60px;" iconCls="icon-cancel"  
		onclick="closeWindow('clean')">
		清除
	</a>
	<span style="display:inline-block;width:25px;"></span>
	<a class="mini-button" style="width:60px;" iconCls="icon-close"  
		onclick="closeWindow('cancel')">
		取消
	</a>
</div>
<script type="text/javascript">
	nui.parse();
 
    var _minSize = 0;//最小选择
    
    var _maxSize= 10;//最大选择
    
	var grid=nui.get("rolegrid1");
	
	var selectRoleObj = nui.get("selectroles");
	var lastSearch;
	var _selectVal = [] ;
	
	selectRoleObj.on("valuechanged", onValueChanged) ;
	
	 
	/**
	* 
	*/
	function roleSearch() {
		grid.setUrl("org.gocom.components.coframe.participantselect.roleselect.roleListWithPage.biz.ext") ;
	    var sname = nui.get("rolename").value ;
	    grid.load({ name: sname }) ;
	   
	}
	 function onrowclick(e){
		 
		var record = e.record;
		var checked = $("#chk_" + record.roleId).attr("checked");
		if(checked){
			$("#chk_" + record.roleId).removeAttr("checked");
		}else{
			$("#chk_" + record.roleId).attr("checked"  ,'true') ;
        }
        checkboxChange("#chk_" + record.roleId) ;
	}
	function chkClick(obj){
		 
		obj.checked=!obj.checked;
	}
	function showcheckbox(e){
		var record=e.record ;
		
		//判断是否在结果集中
	 
		var checkstr = isSelectExist(record.roleId) ? " checked" : "" ;
		return '<input id="chk_'+record.roleId+'" type="checkbox" targetId="'+record.roleId+'" targetName="'+record.roleName
			+ '" targetCode="' + record.roleCode + '" name="roleselect" ' + checkstr + ' class="selcheckbox" onClick="chkClick(this)">';
	
	}
	
	function setData(data) {
		_selectVal=data.data;
		init() ;
	}
	/**
	* 初始化页面的时候执行
	*/
	function init(){
		var wfVals=[];
		var wfNames=[];
		for(var i=0,len=_selectVal.length;i<len;i++){
			var obj = _selectVal[i];
			 
				wfVals.push(getViewValue(obj));
				wfNames.push(getViewName(obj));
		}
		selectRoleObj.setValue(wfVals.join(','));
		selectRoleObj.setText(wfNames.join(','));
	}
	function checkboxChange(obj) {
		var targetId = $(obj).attr("targetId") ;
		var targetName = $(obj).attr("targetName") ;
		var targetCode = $(obj).attr("targetCode") ;
		var checked = $(obj).attr("checked") ;
		 
		 
		if(checked){
			addSelect(targetName ,targetId , targetCode) ;
		}else{
			removeSelect(targetId);
		}
	}
	 
	
	/**
	* 判断该用户是否已经被选中
	*/ 
	function isSelectExist( val ){
		val= val + "";
		var _selectrole_val = selectRoleObj.getValue() ;
	 	var _selectArray = _selectrole_val.split(",") ;
	 	var _index = $.inArray(val,_selectArray) ;
	 	return _index < 0 ? false : true ;
	}
    /**
    * 添加用户
    */
    function addSelect(name,val,code) {
        var _selectrole_val = selectRoleObj.getValue() ;
        var _selectrole_name = selectRoleObj.getText() ;
      
        var _selectArray = _selectrole_val.split(",") ;
        var _index=$.inArray(val,_selectArray) ;
      
       	var seprator =  _selectrole_val=="" ?"" : ",";
        if(_index < 0){
        	_selectrole_val = _selectrole_val + seprator + val ;
           
            _selectrole_name = _selectrole_name + seprator + name ;
           
            selectRoleObj.setValue(_selectrole_val) ;
            
            selectRoleObj.setText(_selectrole_name) ;
           
           _selectVal.push(getRoleVal(val,name,code)) ;
           
        }
            
    }
    
    /**
    * 创建机构json数据
    */
    function getRoleVal(v_roleid,v_rolename,v_rolecode) {
    	return {type:"role",data:{roleid:v_roleid , rolename:v_rolename,rolecode:v_rolecode}} ;
    }
    function getViewName(obj){
    	 return obj.data.rolename ;
    }
    function getViewValue(obj){
    	return obj.data.roleid ;
    }
    
    /**
    * 删除选择的用户
    */
	function removeSelect(val) {
		 
		var _selectrole_val = selectRoleObj.getValue();
		var _selectrole_name = selectRoleObj.getText();
		
		var _selectArray = _selectrole_val.split(",") ;
		var _nameArray = _selectrole_name.split(",") ;
		
		var _index = $.inArray(val,_selectArray) ;
		
		if(_index >= 0){
		    _selectArray.splice(_index , 1) ;
		    
		    _nameArray.splice(_index , 1) ;
			  
		    _selectVal.splice(_index ,1) ;
		}
		
		selectRoleObj.setValue(_selectArray.join(",")) ;
		
		selectRoleObj.setText(_nameArray.join(",")) ;
	}
	
	   
	function onValueChanged(e){
		
		var _data = grid.getData() ;
		var _isExist ;
		$.each(_data ,function(_index,record) {  
           _isExist = isSelectExist(record.targetId) ;
            
           if(_isExist){
           		$("#chk_" + record.targetId).attr("checked"  ,'true') ;
           }else{
           		$("#chk_" + record.targetId).removeAttr("checked");
           }
        });
        
        //结果集合内删除被用户点击的
        var valArray = selectRoleObj.getValue().split(",") ;
        
        $.each(_selectVal ,function(_index,record) {
          	var roleid = record.data.roleid ;
          	if (roleid!= valArray[_index]) {
            	_selectVal.splice(_index ,1) ;
            	return false ;
            }
             
         });
    }
	function selectAll(){
		var _data = grid.getData() ;
		$.each(_data ,function(_index,record) {  
          	 $("#chk_" + record.targetId).attr("checked"  ,'true') ;
          	 checkboxChange("#chk_" + record.targetId);
         });
   	}
	function removeAll(){
		var _data = grid.getData() ;
		$.each(_data ,function(_index,record) {  
          	$("#chk_" + record.targetId).removeAttr("checked") ;
          	 checkboxChange("#chk_" + record.targetId) ;
         });
	}
	
	  
	var retData = {};
	 function closeWindow(action) {            
            if (action == "ok"){
            	var _val = selectRoleObj.getValue();
            	var valArray = _val.split(",") ;
            	var aLen = (_val == "") ? 0 :valArray.length ;
            	if(aLen < _minSize){
            		//当记录少于最少值时候
            		nui.alert("选择用户低于最少用户数：" + _minSize);
            		return ;
            	}
            	if(_maxSize >=0 && aLen > _maxSize){
            		//
            		nui.alert("选择用户超过最多用户数：" + _maxSize);
            		return ;
            	}
           		 
				retData.data = _selectVal;
				retData.value=selectRoleObj.getValue() ;
				retData.name = selectRoleObj.getText() ;
				saveRecent();
			}else if (action == "cancel"){
            	//取消所有
            }else if (action =="clean"){
            	//清除
            }
            if (window.CloseOwnerWindow) 
            	return window.CloseOwnerWindow(action);
            else window.close() ;            
        }
        function getData(){
        	return retData ;
        }
    roleSearch();
    function saveRecent(){
    	//
    	var selList=_selectVal;
    	var dataList=[];
    	for(var i=0,len=selList.length;i<len;i++){
    		var selItem=selList[i];
    		
    		var dataItem={};
    		switch(selItem.type){
    			case 'org':
    				dataItem.targetId=selItem.data.orgid;
    				break;
    			case 'emp':
    				dataItem.targetId=selItem.data.userid;
    				break;
    			case 'role':
    				dataItem.targetId=selItem.data.roleid;
    				break;
    		}
    		dataItem.targetType=selItem.type;
    		dataList.push(dataItem);
    	}
    	
    	
    	nui.ajax({
            url: "org.gocom.components.coframe.participantselect.recentvisit.add.biz.ext",
            type: 'POST' ,
            data: {
            	dataList:dataList
            } ,
            cache: false ,
            contentType:'text/json' ,
            
            success: function (text) {
            	var response = text.retCode ;
            	if(response!="1"){
	            	 nui.alert("处理失败，请联系管理员");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
               // nui.alert(jqXHR.responseText);
               nui.alert("插入最新访问入库出错");
               // CloseWindow();
            }
        });
    }
 </script>
</body>
</html>