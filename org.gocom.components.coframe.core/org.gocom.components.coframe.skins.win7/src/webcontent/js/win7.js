  /*var modules = [
        { name: "iframe", text: "Test", iconCls: "desk-window", title: "", type: "ux.iframewindow", url: "../datagrid/datagrid.html" },
        { name: "simple", text: "Window", iconCls: "desk-window", title: "Window", type: "ux.iframewindow" },
        { name: "iframe", text: "测试窗体", iconCls: "desk-window", title: "测试窗体", type: "ux.iframewindow", url: "http://www.baidu.com" },
        { name: "grid", text: "Grid Window", iconCls: "desk-window", title: "", type: "ux.iframewindow" },
        { name: "splitter", text: "Splitter Window", iconCls: "desk-window", title: "", type: "ux.iframewindow" },
        { name: "tabs", text: "Tabs Window", iconCls: "desk-window", title: "", type: "ux.iframewindow" },        
        { name: "map", text: "Map Window", iconCls: "desk-window", title: "", type: "ux.iframewindow" },
        { name: "chart", text: "Chart Window", iconCls: "desk-window", title: "", type: "ux.iframewindow" },
        { name: "layout", text: "Layout Window", iconCls: "desk-window", title: "", type: "ux.iframewindow", url: "js/windows/layout.html" }
    ];*/
    /*var widgets = [{
    	id:'search-widget',
    	width:310,
    	height:43,
    	right:10,
    	top:2,
    	content:'<div class="search-float"></div>'
    }];*/
    //desktop
    var desk = new mini.ux.DeskTop();
    desk.render(document.body);
	var deskConfig = {
		openType:"single",
		defaultMax : false,
		defaultWidth:800,
		defaultHeight:600,
		deskStyle:"black"
	};
	
	//desk.setWidgets(widgets);
    
    //desk.addHTML('<div style="position:absolute;right:10px;top:10px;width:200px;height:200px;background:red;"></div>');

    //modules
    desk.setModules([]);
    

    //module click
    desk.on("moduleclick", function (e) {
        var module = e.module;
        openDeskWindow(module.menuId,module.title,module.url);
    });
    
    function openDeskWindow(id,title,url,size){
    	var win = null;
    	if(deskConfig.openType == "single"){
    		win = windowCache[id];
    	}
	    if (win) {
			if(win.destroyed){
				win = null;
			}else{
	            desk.showWindow(win);
	            hideStartMenu();
	            return;
			}  
	    }
        if (!win) {
            win = windowCache[id] = desk.createWindow("ux.iframewindow");
            if (win) {
            	win.setTitle(title);
                if (win.type == "ux.iframewindow") {
                	if(url.indexOf("/")==0){
		            	url = nui.context + url;
		            }
                    win.setUrl(url);
                }
            }
        }
        if (win) {
        	if(size){
        		win.setWidth(size.width);
        		win.setHeight(size.height);
        	}else{
        		if(deskConfig.defaultMax){
        			desk.maxWindow(win);
        		}else{
        			if(deskConfig.defaultWidth){
	        			win.setWidth(deskConfig.defaultWidth);
        			}
        			if(deskConfig.defaultHeight){
	        			win.setHeight(deskConfig.defaultHeight);
        			}
        		}
        	}
            desk.showWindow(win);
        }
        hideStartMenu();
    }
    desk.on("showDeskTop",function(){
    	 hideStartMenu();
    	 return true;
    });
     desk.on("startclick", function (e) {
     	if("visible" == $("#start-menu").css("visibility")){
     		hideStartMenu();
     	}else{
     		/*var position = $(e.target).position();
     		var searchBtn = nui.get("search-btn");
     		searchBtn.setText("");
     		var menuTree = nui.get("menuTree");
     		menuTree.clearFilter();*/
	     	$("#start-menu,#person-icon").css("visibility","visible");
	     	_doModal();
     		searchBtn.focus();
     	}
     });

	 $(document).bind('click', function(event){
	  //解决点击节点时自动隐藏的问题
	  if(isNodeClick){
	  	isNodeClick = false;
	  	return;
	  }
      if(!$(event.target).closest("#start-menu,#person-icon").length){
       	hideStartMenu();
      }
    });

    nui.parse();
    var isNodeClick = false;
    var menuTree = nui.get("menuTree");
    var windowCache = {};
    
    menuTree.on("nodeclick",function(sender){
    	isNodeClick = true;
    	openMenuNode(sender.node);
    	return false;
    });
    
    function openMenuNode(node){
    	if(node.linkAction){
    		openDeskWindow(node.menuPrimeKey,node.menuName,node.linkAction);
    	}
    }
    
    //处理桌面背景
    function initDesktopConfig(config){
    	var bgPath = nui.context + "/skins/win7/css/images/default.jpg";
    	if(config){
	    	if(config.bgPicturePath){
	    		bgPath = config.bgPicturePath;
	    		if(bgPath.indexOf("/")==0){
	    			bgPath = nui.context + bgPath;
	    		}
	    	}
	    	deskConfig = config;
	    	if(deskConfig.defaultWidth){
		    	deskConfig.defaultWidth = deskConfig.defaultWidth>100?deskConfig.defaultWidth:800;
	    	}
	    	if(deskConfig.defaultHeight){
		    	deskConfig.defaultHeight = deskConfig.defaultWidth>100?deskConfig.defaultHeight:600;
	    	}
    	}
	    $(".mini-desktop-viewport").css("background","url("+ bgPath +") no-repeat");
	    $(".mini-desktop-viewport").css("height","100%");
	    var styleName = config.deskStyle;
	    if(!styleName){
	    	styleName = "black";
	    }
	    var url = nui.context + "/skins/win7/css/colors/" + styleName + "/color.css";
	    $("#color-link").attr("href",url);
    }
    //处理桌面图标
    function initDesktopIcons(icons){
    	var desktopIcons = [];
    	if(icons){
	    	for(var i=0;i<icons.length;i++){
	    		var icon = icons[i];
	    		var iconUrl = "about:blank";
	    		var menuCode = icon.menuId;
	    		var menuNode = menuTree.getNode(icon.menuId);
	    		if(menuNode && menuNode.linkAction){
	    			iconUrl = menuNode.linkAction;
	    		}
	    		var desktopIcon = { 
	    			name: icon.iconName,
	    			text: icon.iconText, 
	    			iconPath: icon.iconPath, 
	    			title: icon.iconTitle, 
	    			type: "ux.iframewindow", 
	    			url: iconUrl,
	    			menuId:menuCode
	    		}
	    		desktopIcons.push(desktopIcon);
	    	}
    	}
    	desk.setModules(desktopIcons);
    	desk._doUpdateModules();
    }
    //处理自动启动
    function initDesktopAutoStarts(autoStarts){
    	if(!autoStarts){
    		return;
    	}
    	for(var i=0;i<autoStarts.length;i++){
    		var menuNode = menuTree.getNode(autoStarts[i].menuId);
    		if(menuNode){
    			openMenuNode(menuNode);
    		}
    	}
    }
    
    function menuTreeOnload(sender,xhr,treeData){
    	nui.ajax({
         url:"org.gocom.components.coframe.skins.win7.win7Service.getMyConfig.biz.ext",
         type:'POST',
         data:{},
         success:function(text){
            var result = nui.decode(text);
            if(!result.exception){
	           //处理桌面背景、桌面图标和自动启动
	           initDesktopConfig(result.config,data);
	           initDesktopIcons(result.icons,data);
	           initDesktopAutoStarts(result.autoStarts,data);
            }else{
               nui.alert(result.exception.message);
            }
         }
       });
    }
    nui.parse();
    var searchBtn = nui.get("search-btn");
    
    function filterMenuTree(){
    	menuTree.expandAll();
	   	menuTree.filter(function (node){
	   		if(searchBtn.getValue()==""){
	   			menuTree.clearFilter();
	   		}
    		if(node.menuName && node.menuName.indexOf(searchBtn.getText())>=0){	    			
    			return true;
	    	}
	    	return false;
	    });
    }
    
    function closeSearchText(){
   		searchBtn.setText("");
	    menuTree.clearFilter();
	    return false;
    }
    searchBtn.on("enter",function(e){
	    return filterMenuTree();
    });
    searchBtn.on("buttonclick",function(e){
	    return filterMenuTree();
    });
    searchBtn.on("closeclick",function(e){
    	return closeSearchText();
    });
    function logout(){
    	window.location = nui.context + "/coframe/auth/login/logout.jsp";
    }
    function openStyleSettings(){
    	openDeskWindow("settingsStyle","风格偏好设置","/skins/win7/style_config.jsp",{width:440,height:420});
    }
    function openIconSettings(){
    	openDeskWindow("settingsIconWindow","桌面图标设置","/skins/win7/icon_config.jsp");
    }
    function openBgSettings(){
    	openDeskWindow("settingsBgWindow","桌面背景设置","/skins/win7/bg_config.jsp");
    }
    function openAutoStartSettings(){
    	openDeskWindow("settingsAutoStartWindow","自动启动设置","/skins/win7/auto_start_config.jsp",{width:200,height:400});
    }
    function hideStartMenu(){
    	 $("#start-menu,#person-icon").css("visibility","hidden");
    	 if(modelElement){    	 
    	 	modelElement.style.display = "none";
    	 }
    }
    function changePassword(){
    	openDeskWindow("settingsChangePwdWindow","修改密码","/coframe/rights/user/update_password.jsp",{width:370,height:230});
    }
    var modelElement;
    function _doModal() {
	    if (!modelElement) {
            modelElement = mini.append(document.body, '<div class="mini-modal" style="display:none"></div>');
            modelElement.onclick = function(){
            	hideStartMenu();
            }
        }
        //this._modalEl.style.display = "none";
        //        this._modalEl.style.width = "100px";
        //        this._modalEl.style.height = "100px";
        function resizeModal() {
            mini.repaint(document.body);
            var dd = document.documentElement;
            var scrollWidth = parseInt(Math.max(document.body.scrollWidth, dd ? dd.scrollWidth : 0));
            var scrollHeight = parseInt(Math.max(document.body.scrollHeight, dd ? dd.scrollHeight : 0));

            var vbox = mini.getViewportBox();
            var height = vbox.height;
            if (height < scrollHeight) height = scrollHeight;

            var width = vbox.width;
            if (width < scrollWidth) width = scrollWidth;

            modelElement.style.display = "block";

            modelElement.style.height = height + "px";
            modelElement.style.width = width + "px"; //"100%";
            modelElement.style.zIndex = 10000;
        }
        setTimeout(function () {
            if (modelElement) {
                modelElement.style.display = "none";
                resizeModal.call();
            }
        }, 1);
    }
    