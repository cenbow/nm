define( function() {
	//=======================================================
	// 获取基础组件
	//=======================================================
	var base = require( "app/base" )
		, tools = base.tools
		, format = tools.format
		, message = base.message;
	
	
	//=======================================================
	// 定义全局对象
	//=======================================================
	function Global( vars ) {

		var global = this; //全局对象
		this.handlers = {};//处理程序
		this.handlers.getGlobal = function() { return global; };
		this.vars = $.extend( true, vars, {});//全局变梁
		this.theme = "full"; //主题设置

		//组件入口函数  相当于java.main
		this.init = function() {
			this.loadBefore();
			this.layout();
			this.bindEvent();
			this.loadAfter();
		};

		//初始化远程请求处理
		this.loadBefore = function() {
			var global = this.getGlobal();
		};

		//after load
		this.loadAfter = function() {
			var global = this.getGlobal();
		};


		//页面布局
		this.layout = function() {
			var global = this.getGlobal();
			
			var url = global.params.url;

			var id = "frameIFrame_" + tools.uuid();

			var content = format( '<iframe id="{2}" name="{2}" src="{0}" style="border:none; width: 100%; height: {1}px"></iframe>', [ url, $( window ).height() - 6 ] );
			
			var $frame = $( content ).appendTo( global.ui.id( "#iframeContainer" ) );

			global.section.bind( "section.custom.resize", function( event, width, height) {
				
				if ( typeof height != "number" ) {
					return false;
				}
				var h = height - global.section.find( ".page-header:visible:eq(0)" ).outerHeight( true );
				//var h = height - global.section.find( ".page-header:eq(0)" ).outerHeight( true );

				$frame.css( "height", h - 20 );
				
			} ).trigger( "main.resize.tabpage.section" );

			$( window.frames[ id ] ).bind( "unload", function () {
				if ( global.__super__ ) {
					global.close();
				} else if ( $.isEmptyObject( base.globals ) ) {
					window.close();
				}
			} );
			
		};


		this.bindEvent = function () {
			var global = this.getGlobal();
		};
		
		//=======================================================
		// 业务逻辑申明
		//=======================================================
		this.handlers.load = function() {
			
		};
	};
	
	return Global;
	
} );