<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/coframe/tools/skins/common.jsp"%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title>桌面背景配置</title>
	<link id="css_skin" rel="stylesheet" type="text/css" href="<%=contextPath%>/coframe/tools/skins/skin1/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/skins/win7/css/images/background/bgs.css"/>  
</head>
<body>
<div class="nui-fit">
	<fieldset><legend>系统桌面图片</legend>
		<a onclick="selectBg('one')" id="one" class="style-button style-button-select" checkOnClick="true" groupName="style">
		<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_one.jpg'/></div>
		</a>
		<a onclick="selectBg('two')" id="two" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_two.jpg'/></div>
		</a>
		<a onclick="selectBg('three')" id="three" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_three.jpg'/></div>
		</a>
		<a onclick="selectBg('four')" id="four" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_four.jpg'/></div>
		</a>
		<a onclick="selectBg('five')" id="five" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_five.jpg'/></div>
		</a>
		<div style="height:10px;font-size:1">&nbsp;</div>
		<a onclick="selectBg('six')" id="six" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_six.jpg'/></div>
		</a>
		<a onclick="selectBg('seven')" id="seven" class="style-button" checkOnClick="true" groupName="style">
		<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_seven.jpg'/></div>
		</a>
		<a onclick="selectBg('eight')" id="eight" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_eight.jpg'/></div>
		</a>
		<a onclick="selectBg('nine')" id="nine" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_nine.jpg'/></div>
		</a>
		<a onclick="selectBg('ten')" id="ten" class="style-button" checkOnClick="true" groupName="style">
			<div class="bg-div"><img class="bg-img" src='<%=contextPath%>/skins/win7/css/images/background/default_ten.jpg'/></div>
		</a>
	</fieldset>
	<fieldset><legend>自定义图片</legend>
		<div id="user-images" class="images"></div>
	</fieldset>
	<!-- 上传图片 -->
	<div id="upload-images"></div>
	<input type="button" value="上传" onclick="startUpload()"/>
		<script>
			var sf = null,up_area_id="upload-images";
			jQuery(function(){
				sf=new SWFFileUpload({
					renderTo:'#'+up_area_id,
					title:'自定义图片上传',
					cancelBtnText:'取消上传',
					saveType:1,
					fileTypes:'*.jpg;*.png',
					fileQueryLimit:1,
					autoUpload:false,
					hiddenType:'nui'
				});
			});
			function startUpload(succ){
				sf.startUpload(function(){
				var fileIds=sf.getValue();
				if(fileIds==""){
					return;
				}
				nui.ajax({
		        	url: "org.gocom.components.coframe.skins.win7.win7Service.addUserBg.biz.ext",
                    type: "post",
                    data: {files:fileIds.split(",")}, 
                    cache: false,
                    contentType: 'text/json',
                    success: function (text) {
	                    var result = nui.decode(text);
			            if(!result.exception){
				           	getDefaultBg();
		                	sf.setValue('');
			            }else{
		                	nui.alert(result.exception.message);
		                }
                    }
                });
				},function(){
					nui.alert('文件上传失败！');
				});
			}
		</script>
</div>
</body>
<script src="<%=contextPath %>/common/components/swffileupload/swfupload/swffileupload.js" type="text/javascript"></script>
<script text="text/javascript">

	nui.parse();//转换nui格式
	var myConfig = {
		openType:"single",
		defaultMax : false,
		defaultWidth:800,
		defaultHeight:600,
		deskStyle:"black"
	};
	var selectedBg = '';
	getDefaultBg();//获取默认的背景
	
	
	//改变选择样式
	function changeSelect(bgName){
		$(".style-button").removeClass("style-button-select");
		$("#" + bgName).addClass("style-button-select");
	}
	
	//选择背景图片
	function selectBg(bgName){
		changeSelect(bgName);
		doSetBg(bgName);
	}
	
	//设置背景图片
	function doSetBg(bgName){
		var pathStr = "/skins/win7/css/images/background/default_"+bgName+".jpg"
		if(bgName.length == 32){
			pathStr = "/com.primeton.components.web.fileupload.getfile.flow?fileId="+bgName
		}
		var contact = {custompic:{bgPicturePath:pathStr}};
		var data = nui.encode(contact);
		nui.ajax({
	        url: "org.gocom.components.coframe.skins.win7.win7Service.setUserBg.biz.ext",
	        type: "post",
	        data: data, 
	        cache: false,
	        contentType: 'text/json',
	        success: function (text) {
	        	var result = nui.decode(text);
	            if(!result.exception){
	            	myConfig.bgPicturePath = pathStr;
		            parent.initDesktopConfig(myConfig);
		        	selectedBg = pathStr;
	            }else{
	               nui.alert(result.exception.message);
            	}
	        }
        });
	}
	
	//获取默认的背景
	function getDefaultBg(){
		nui.ajax({
		    url: "org.gocom.components.coframe.skins.win7.win7Service.getUserBg.biz.ext",
		    type: "post",
		    success: function (getbgs) {
		        var userbghtml='';  
		        var bgs = nui.encode(getbgs.userbgs);
		        var config = nui.decode(getbgs.userconfig);
		        if(Object.prototype.toString.call(getbgs.userbgs)=="[object Array]" && getbgs.userbgs.length>0){
			        $.each(getbgs.userbgs,function(i,r){
		         		var _userhtml=
			         	"<a id='"+r.fileName+"' class='style-button' checkOnClick='true' groupName='style' style='margin-right:4px;'>"
			         	+"<div class='delbtn-div'><img id='"+r.fileName+"'class='delbtn' src='<%=contextPath%>/skins/win7/css/images/del.png' title='删除' onclick='deleteBg(this)'/></div>"
						+"<div onclick=\"selectBg('"+r.fileName+"')\" class='bg-div'><img class='bg-img' src='<%=contextPath%>/com.primeton.components.web.fileupload.getfile.flow?fileId="+r.fileName+"'/></div></a>"
			        	userbghtml=userbghtml+_userhtml;
			        });
		        }else{
	        		var userbghtml=
		         	"<a class='style-button' checkOnClick='true' groupName='style'>"
					+"<div class='bg-div'><img class='bg-img' src='<%=contextPath%>/skins/win7/css/images/background/noPic.jpg'/></div></a>"
		        }
		        $('#user-images').html(userbghtml);
		        if(getbgs.userbgs.length >=3){
		        	sf.setDisable();
		        }else{
		        	sf.setEnable();
		        }
		        if(config.bgPicturePath!=undefined){
		        	selectedBg = config.bgPicturePath;
	        		defSelected(config.bgPicturePath);
		        }
		    }
		});
	}
	
	//选中默认的背景图
	function defSelected(bgPicturePath){
		if(bgPicturePath.indexOf("_")>0){
			changeSelect(bgPicturePath.substring(bgPicturePath.indexOf("_")+1,bgPicturePath.indexOf(".")));
		}else{
			changeSelect(bgPicturePath.substring(bgPicturePath.indexOf("=")+1,bgPicturePath.length));
		}
		
	}
	
	//删除背景图
	function deleteBg(img){
		if(selectedBg.indexOf(img.id)>0){
			nui.alert("图片已被设置为背景，不允许删除！");
			return;
		}
		var contact = {selectedPic:img.id};
		var data = nui.encode(contact);
		nui.ajax({
		        url: "org.gocom.components.coframe.skins.win7.win7Service.delUserBg.biz.ext",
	            type: "post",
	            data: data, 
	            cache: false,
	            contentType: 'text/json',
	            success: function (text) {
	            	var result = nui.decode(text);
		            if(!result.exception){
		            	sf.removeFile(img.id);
	               		getDefaultBg();
		            }else{
		               nui.alert(result.exception.message);
	            	} 
	            }
	    });
	}
</script>
</html>
