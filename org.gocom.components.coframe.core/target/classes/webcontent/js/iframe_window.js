mini.ux.Window = function () {
    mini.ux.Window.superclass.constructor.call(this);    
}
mini.extend(mini.ux.Window, mini.Window, {
    title: "New Window",
    width: 800,
    height: 500,
    minWidth: 250,
    minHeight: 100,
    showMaxButton: true,
    showMinButton: true,
    showModal: false,
    allowResize: true,
    iconPath:"",
    uiCls: "mini-ux-window"
});
mini.regClass(mini.ux.Window, "ux.window");

mini.ux.IFrameWindow = function () {
    mini.ux.IFrameWindow.superclass.constructor.call(this);    
}
mini.extend(mini.ux.IFrameWindow, mini.ux.Window, {
    title: "IFrame Window",
    url: "",
    _getIconPath:function(){
      	var imagePath = this.iconPath;
        if(imagePath){
        	if(imagePath.indexOf("")==0){
        		imagePath = nui.context + imagePath;
        	}
        }else{
        	imagePath = nui.context + "/skins/win7/css/desktop/images/application.png";
        }
        return imagePath;
    },
    active:function(){
    	if(!this.windowModel){
    		return;
    	}
    	this.windowModel.style.display = "none";
    },
    unActive:function(){
    	if(!this.windowModel ||!this.el){
    		return;
    	}
    	this.windowModel.style.width = this.el.style.width;
    	this.windowModel.style.top = this.el.style.top;
    	this.windowModel.style.left = this.el.style.left;
    	this.windowModel.style.height = this.el.style.height;
    	this.windowModel.style.zIndex = this.el.style.zIndex;
    	this.windowModel.style.display = "";
    },
     _create: function () {
        this.el = document.createElement("div");
        this.el.className = "start-window x-menu ux-start-menu";
        var s = '<div>'
	        		+ '<div class="ux-start-menu-tl"><div class="ux-start-menu-tr"><div class="ux-start-menu-tc"><div class="x-window-header x-unselectable x-panel-icon">'
	            	+ '<div style="height:22px;padding-top:5px;padding-left:5px;"><span style="display:inline;position:absolute;top:10px;left:10px;"><img src="' +  this._getIconPath() + '" style="border:0px;width:16px;height:16px;"/></span><span class="mini-panel-icon"></span><div class="mini-panel-title"></div><div class="mini-tools" style="top:1px;"></div></div>'
					+ '</div></div></div></div>'
					+ '<div class="x-window-bwrap">'
						+ '<div class="ux-start-menu-ml"><div class="ux-start-menu-mc ux-start-menu-bwrap"><div class="ux-start-menu-body ux-start-menu-body"><div class="ux-start-menu-tools-panel" style="padding-right:6px;">'
				            + '<div class="mini-panel-viewport window-body">'
				                + '<div class="mini-panel-toolbar"></div>'
				                + '<div class="mini-panel-body" style="padding:0px;"></div>'
				                + '<div class="mini-panel-footer"></div>'
				                + '<div class="mini-resizer-trigger"></div>'
				            + '</div>'
			            + '</div></div></div></div>'
		           		+ '<div class="ux-start-menu-bl x-panel-nofooter"><div class="ux-start-menu-br"><div class="ux-start-menu-bc"></div></div></div>'
		            	+ '<a class="x-menu-focus" href="#" onclick="return false;" tabindex="-1"></a>'
		            + '</div>'
	            + '</div>';
        this.el.innerHTML = s;
		this.windowModel = mini.append(this.el, '<div class="mini-modal" style="display:none"></div>');
        this._borderEl = this.el.firstChild;
        this._headerEl = this._borderEl.firstChild;
        this._viewportEl = this._borderEl.lastChild;

        this._toolbarEl = mini.byClass('mini-panel-toolbar', this.el);
        this._bodyEl = mini.byClass('mini-panel-body', this.el);
        this._footerEl = mini.byClass('mini-panel-footer', this.el);
        this._resizeGridEl = mini.byClass('mini-resizer-trigger', this.el);

        var hi = mini.byClass('mini-panel-header-inner', this.el);
        this._iconEl = mini.byClass('mini-panel-icon', this.el);
        this._titleEl = mini.byClass('mini-panel-title', this.el);
        this._toolsEl = mini.byClass('mini-tools', this.el);

        mini.setStyle(this._bodyEl, this.bodyStyle);
        var win = this;
        $(this._headerEl).dblclick(function(){
        	if(win.state == "max"){
        		win.restore();
        	}else{
        		win.max();
        	}
        	return false;
        });

        this._doTitle();
    },
    _initButtons: function () {
        this.buttons = [];

        var close = this.createButton({ name: "close", cls: "window-close", visible: this.showCloseButton });
        this.buttons.push(close);

        var max = this.createButton({ name: "max", cls: "window-max", visible: this.showMaxButton });
        this.buttons.push(max);

        var min = this.createButton({ name: "min", cls: "window-min", visible: this.showMinButton });
        this.buttons.push(min);

        var collapse = this.createButton({ name: "collapse", cls: "window-normal", visible: this.showCollapseButton });
        this.buttons.push(collapse);
    },
    getViewportHeight: function (content) {
        var h = this.getHeight(true) - this.getHeaderHeight();
        if (content) {
            var padding2 = mini.getPaddings(this._viewportEl);
            var border2 = mini.getBorders(this._viewportEl);
            var margin2 = mini.getMargins(this._viewportEl);
            if (jQuery.boxModel) {
                h = h - padding2.top - padding2.bottom - border2.top - border2.bottom;
            }
            h = h - margin2.top - margin2.bottom;
        }
        h = h - 6;
        return h;
    },
    max: function () {
        this.state = "max";
        this.show();

        var button = this.getButton("max");
        if (button) {
            button.cls = "window-normal";
            this._doTools();
        }
    },
    restore: function () {
        this.state = "restore";
        this.show(this.x, this.y);

        var button = this.getButton("max");
        if (button) {
            button.cls = "window-max";
            this._doTools();
        }
    }
});
mini.regClass(mini.ux.IFrameWindow, "ux.iframewindow");

mini.ux.Window = function () {
    mini.ux.Window.superclass.constructor.call(this);    
}
mini.extend(mini.ux.Window, mini.Window, {
    title: "",
    width: 250,
    height: 300,
    minWidth: 250,
    minHeight: 100,
    showMaxButton: false,
    showMinButton: false,
    showModal: true,
    showShadow:true,
    allowResize: false,
    closeAction:"destory",
    uiCls: "nui-ux-start"
});
mini.regClass(mini.ux.Window, "ux.start");
