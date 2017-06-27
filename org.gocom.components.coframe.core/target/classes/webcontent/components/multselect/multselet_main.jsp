<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="/coframe/tools/skins/common.jsp" %>

<html>

<head>
<title>参与者选择</title>
</head>
<body>
<div class="nui-fit">
<!-- 在线用户 -->
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="30%" showCollapseButton="true">
         <div id="deptsearch" class="mini-fit" style="height:100%;" borderStyle="border:0;">
            <ul id="depttree" class="nui-tree" url="" style="width:100%;height:100%;"
                showTreeIcon="true" textField="name" idField="id" parentField="parentId" resultAsTree="false"
                onbeforeload="onBeforeTreeLoad"  showExpandButtons="true"
                expandOnLoad="false"
             >        
            </ul>
        </div>
      </div>
    <div showCollapseButton="true">
         <div class="mini-toolbar" style="line-height:30px;" borderStyle="border:0;">
         	<table width="100%">
         	<tr>
         		<td style="text-align:left;">
	           		<div id="searchtype" class="mini-radiobuttonlist" repeatLayout="table" repeatDirection="vertical"
				    textField="text" valueField="id" value="1"
				    data='[{ "id": "1", "text": "机构" },{ "id": "2", "text": "用户" }]'>
				 	</div>
	            </td>
         		<td style="text-align:right;">
	           		<a class="mini-button" iconCls="icon-add" plain="true" onclick="selectAll()">全部选中</a>
	           	 	<a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeAll()">全部删除</a> 
	            </td>
            </tr>
            </table>
    	</div>
         <div class="mini-fit" >
            
            <div id="usergrid1" class="mini-datagrid" style="width:100%;height:100%;" 
                borderStyle="border:0;"
                url=""
                allowCellSelect="true"   
               showPager="true"
               totalField="page.count"
               sizeList=[20,40,60,100]
               allowRowSelect="true"
               onrowclick="onrowclick"
            >
            
                <div property="columns">            
                    <div field="orgcode" width="80" headerAlign="center"  align="center" allowSort="true">机构代码</div>
                   <div field="targetName" width="100" headerAlign="center" align="center" allowSort="true">机构名称</div>
                   <div field="targetId" width="100" visible="false" headerAlign="center" align="center" allowSort="true">机构ID</div>
                   
                   <div field="userid" width="80" headerAlign="center"  align="center" allowSort="true">员工帐号</div>
                   <div field="targetName" width="100" headerAlign="center" align="center" allowSort="true">员工姓名</div>
                   <div field="pemail" width="100" headerAlign="center" align="center" allowSort="true">邮箱</div>
                   <div field="mobileno" width="100"  headerAlign="center" align="center" allowSort="true">手机</div>
                   <div field="orgname" width="100" headerAlign="center"  align="center" allowSort="true">所属机构</div>
                   <div field="empid" width="80" visible="false"  headerAlign="center"  align="center" allowSort="true">empid</div>
                   <div field="targetId" width="80" visible="false"  headerAlign="center"  align="center" allowSort="true">userid</div>
                   <div field="userselect" width="30" headerAlign="center"  align="center"  renderer="showcheckbox">选择
                    	 
                     </div>                                   
                    
                </div>
            </div>  
        </div>
    </div>        
</div>

</div>
<div property="footer" class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
<div>选择用户和机构：</div>
<div>
<input id="selectUsers" class="mini-textboxlist" name="selectUsers" textName="selectUsersName" required="true" style="width:100%;height:50px;"
        value="" text=""
        valueField="id" textField="text" 
/>
</div>
</div>
<div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" iconCls="icon-ok"  onclick="closeWindow('ok')">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" iconCls="icon-cancel" style="width:60px;" onclick="closeWindow('clean')">清除</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" iconCls="icon-close"  style="width:60px;" onclick="closeWindow('cancel')">取消</a>
    </div>
<script type="text/javascript">
	nui.parse();
	var _deptconfig ="1" ;
    var _deptlist = "" ;
    var _deptconfigval = "" ;
    var _datatype = "" ;
    var _minSize = 0;//最小选择
    
    var _maxSize= 10;//最大选择
    
    var currentOrgid = "";
	var grid=nui.get("usergrid1");
	var selectUserObj = nui.get("selectUsers");
	var _selectVal = [] ;
	selectUserObj.on("valuechanged", onValueChanged);
	nui.get("searchtype").on("valuechanged", searchTypeChange);
	//机构查询
	 var tree = nui.get("depttree");
     
     tree.on("nodeselect", function (e) {
     	currentOrgid=e.node.id;
     	search();
	});
	
	function onrowclick(e){
		//debugger;
		var record = e.record;
		var checked = $("#chk_" + record.targetId).attr("checked");
		if(checked){
			$("#chk_" + record.targetId).removeAttr("checked");
		}else{
			$("#chk_" + record.targetId).attr("checked"  ,'true') ;
        }
        checkboxChange("#chk_" + record.targetId) ;
	}
	
	function showcheckbox(e){
		var record=e.record;
		
		//判断是否在结果集中
		// debugger;
		 
		
		var checkstr = isSelectExist(getValue(record.targetId)) ? " checked" : "" ;
	 
		return '<input id="chk_'+record.targetId+'" type="checkbox" targetId="'+record.targetId+'" targetName="'+record.targetName
			+ '" userid="' + record.userid +'" mobileno="' + record.mobileno+  '" pemail="' + record.pemail + '" orgcode="' + record.orgcode 
			+  '" name="useselect" ' + checkstr + ' class="selcheckbox" onClick="chkClick(this)">';
	
	}
	function chkClick(obj){
		 
		obj.checked=!obj.checked;
	} 
	function checkboxChange(obj) {
		
		var checked = $(obj).attr("checked");
		
		var jsonObj = createObj(obj) ;
		
	 	if(checked){
			addSelectUser(jsonObj) ;
		}else{
			removeSelectUser(jsonObj);
		}
	}
	 
	function deptSearch(){
		var depttree = nui.get("depttree");
	 	depttree.setUrl("org.gocom.components.coframe.participantselect.deptselect.deptList.biz.ext") ;
   	 	var rootNode = depttree.getRootNode();
   	 	var nodes = depttree.getChildNodes(rootNode) ;
   	 	
		if(nodes != "undefined" || nodes.length>0){
			depttree.selectNode(nodes[0]);
		}
	}
	function createObj(obj){
		switch(getType()){
    		case "1"://机构方式查询
    		 	return getOrgVal($(obj).attr("targetId")
    		 	,$(obj).attr("orgcode"),$(obj).attr("targetName")) ;
    			break;
    		case "2"://用户方式查询
    			return getEmpVal($(obj).attr("targetId"),$(obj).attr("mobileno")
    				,$(obj).attr("pemail"),$(obj).attr("targetName"),$(obj).attr("userid"));
    			break;
    	}
	}
	/**
	* 判断该用户是否已经被选中
	*/ 
	function isSelectExist( _val ){
		//alert(_val);
		_val= _val+"";
		var _selectuser_val = selectUserObj.getValue() ;
	 	var _selectArray = _selectuser_val.split(",") ;
	 	var _index = $.inArray(_val,_selectArray) ;
	 	return _index < 0 ? false : true ;
	}
    /**
    * 添加用户
    */
    function addSelectUser(obj) {
            var _selectuser_val = selectUserObj.getValue() ;
            var _selectuser_name = selectUserObj.getText() ;;
          	var val = getViewValue(obj);
          	var name = getViewName(obj);
            var _selectArray = _selectuser_val.split(",") ;
            var _index=$.inArray(val,_selectArray) ;
          
           	var seprator =  _selectuser_val=="" ?"" : ",";
            if(_index < 0){
            	_selectuser_val = _selectuser_val + seprator + val ;
	           
	            _selectuser_name = _selectuser_name + seprator + name ;
	           
	            selectUserObj.setValue(_selectuser_val) ;
	            
	            selectUserObj.setText(_selectuser_name) ;
	      
	           _selectVal.push(obj) ;
	           
            }
            
    }
    function getViewName(obj){
    	switch(obj.type){
    		case "org":
    			return obj.data.orgname ;
    			break;
    		case "emp":
    			return obj.data.username ;
    			break;
    	}
    
    }
    function getViewValue(obj){
    	switch(obj.type){
    		case "org":
    			return "org:" + obj.data.orgid ;
    			break;
    		case "emp":
    			return "emp:"+ obj.data.empid ;
    			break;
    	}
    }
    function getValue(targetId){
     
    	switch(getType()){
    		case "1":
    			return "org:" + targetId ;
    			break;
    		case "2":
    			return "emp:"+ targetId ;
    			break;
    	}
    }
    
    /**
    * 创建机构json数据
    */
    function getOrgVal(v_orgid,v_orgcode,v_orgname) {
    	return {type:"org",data:{orgid:v_orgid , orgcode:v_orgcode,orgname:v_orgname}} ;
    }
    /**
    * 创建机构json数据
    */
    function getEmpVal(v_empid,v_mobileno,v_pemail,v_username,v_userid) {
    	return {type:"emp",data:{userid:v_userid , mobileno:v_mobileno,pemail:v_pemail,username:v_username,empid:v_empid}} ;
    }
    
    /**
    * 删除选择的用户
    */
	function removeSelectUser(obj) {
		 
		var _selectuser_val = selectUserObj.getValue();
		var _selectuser_name = selectUserObj.getText();
		
		var _selectArray = _selectuser_val.split(",") ;
		var _nameArray = _selectuser_name.split(",") ;
		var val = getViewValue(obj);
       	var _index = $.inArray(val,_selectArray) ;
		
		if(_index >= 0){
		    _selectArray.splice(_index , 1) ;
		    
		    _nameArray.splice(_index , 1) ;
			  
		    _selectVal.splice(_index ,1) ;
		}
		
		selectUserObj.setValue(_selectArray.join(",")) ;
		
		selectUserObj.setText(_nameArray.join(",")) ;
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
        var valArray = selectUserObj.getValue().split(",") ;
        
        $.each(_selectVal ,function(_index,record) {
          	var val = getViewValue(record.data) ;
          	if (val!= valArray[_index]) {
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
	
	function onBeforeTreeLoad(e) {
        var tree = e.sender;    //树控件
        var node = e.node;      //当前节点
        var params = e.params;  //参数对象
        
       
        
        params.depttype = _deptconfig ;
        params.orglevel =	_deptconfigval;
        params.orglists = _deptlist ;
        
        params.orgid = node.id ;
    }
 
 	function setData(data) {
		_deptconfig = data.deptConfig ;
		_deptlist = data.deptList ;
		_deptconfigval = data.deptConfigVal ;
		_minSize = data.minSize ;
		_maxSize = data.maxSize;
		_datatype = data.dataType ;
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
		selectUserObj.setValue(wfVals.join(','));
		selectUserObj.setText(wfNames.join(','));
		
		 
	}
	 
	/**
	* 初始化页面的时候执行
	*/
	var retData = {};
	 function closeWindow(action) {            
            if (action == "ok"){
            	var _val = selectUserObj.getValue();
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
				//alert(_selectVal);
				//debugger;
				retData.value=selectUserObj.getValue();
				retData.name = selectUserObj.getText() ;
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
    deptSearch();
    function search(){
     	//alert("search") ;
    	switch(getType()){
    		case "1"://机构方式查询
    			grid.showColumn(grid.getColumns()[0]);
    			grid.showColumn(grid.getColumns()[1]);
    			//grid.hideColumn(grid.getColumns()[2]);
    			grid.hideColumn(grid.getColumns()[3]);
    			grid.hideColumn(grid.getColumns()[4]);
    			grid.hideColumn(grid.getColumns()[5]);
    			grid.hideColumn(grid.getColumns()[6]);
    			grid.hideColumn(grid.getColumns()[7]);
    			grid.setUrl("org.gocom.components.coframe.participantselect.deptselect.deptChildList.biz.ext") ;	
				grid.load({ orgid:currentOrgid ,append:true}) ;
				
    			break;
    		case "2"://用户方式查询
    			grid.hideColumn(grid.getColumns()[0]);
    			grid.hideColumn(grid.getColumns()[1]);
    			//grid.showColumn(grid.getColumns()[2]);
    			grid.showColumn(grid.getColumns()[3]);
    			grid.showColumn(grid.getColumns()[4]);
    			grid.hideColumn(grid.getColumns()[5]);
    			grid.hideColumn(grid.getColumns()[6]);
    			grid.showColumn(grid.getColumns()[7]);
    			grid.setUrl("org.gocom.components.coframe.participantselect.userselect.deptUserList.biz.ext");	
				grid.load({ orgid:currentOrgid ,datatype:"1"}) ;
    			break;
    	}
    }
    function getType(){
    	return nui.get("searchtype").getValue();
    }
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
    				dataItem.targetId=selItem.data.empid;
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
                nui.alert("插入最新访问入库出错");
                //CloseWindow();
            }
        });
    }
    function searchTypeChange(e){
    	search() ;
    }
 </script>
</body>
</html>