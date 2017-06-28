define( [ "css2!resources/admin/frame/homepage/static/homepage.index" ], function() {
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
		this.handlers = new base.handlers( this );
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