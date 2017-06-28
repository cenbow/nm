define( [ "css2!highlight/zenburn", "highlight.pack" ], function() {
	var base = require( "app/base" )
	, message = base.message
	, tools = base.tools;

	function Global( vars ) {

		/*** 默认全局变量定义 */
		this.vars = {};
		/* 合并全局变量 */
		this.vars = $.extend( true, this.vars, vars );

		/** 逻辑处理对象 */
		this.handlers = new base.handlers( this );

		/*** 初始化 */
		this.init = function() {
			this.loadBefore();
			this.layout();
			this.loadAfter();
			this.validate();
			this.bindEvent();
		};

		/*** 加载前 */
		this.loadBefore = function() {
			var global = this.getGlobal();
			this.handlers.load();
		};

		/*** 页面布局 */
		this.layout = function() {
			var global = this.getGlobal();

			global.ui.find( ".input" ).input();

		};

		/*** 加载后 */
		this.loadAfter = function() {
			var global = this.getGlobal();
		};

		/*** 验证*/
		this.validate = function() {
			var global = this.getGlobal();

			global.ui.validate( "#form1", {
				rules: {
					currentLoginPassword: { required: true, rangelength: [ 4, 20 ] },
					resetLoginPassword: { required: true, rangelength: [ 4, 20 ] }
				}
			} );
		};

		/*** 绑定事件 */
		this.bindEvent = function() {
			var global = this.getGlobal();

			global.ui.id( "btnSaveOrUpdate" ).click( function() {
				global.handlers.saveOrUpdate();
			} );

		};

		this.handlers.load = function() {
			var global = this.getGlobal();

			var item = global.params.item;
			if (!item) return;
			
			function replaceAll( s1, s2, s3 ) {
				return s1.replace( new RegExp( s2, "gm" ), s3 )
			}
			
			var summary = [];
			summary.push( "&#12288;&#12288;错误码 > " + item.errorCode || "无");
			summary.push( "&#12288;请求地址 > " + item.requestUrl || "无");
			summary.push( "客户端地址 > " + item.requestIp || "无");
			summary.push( "&#12288;请求时间 > " + item.date || "无" );
			item.summary = summary.join( "\n" );

			var value;
			for ( var key in item ) {
				value = item[ key ] || "";
				if ( typeof value == "object" || ( typeof value == "string" && value.substring( 0, 1 ) == "{" ) ) {
					value = JSON.stringify( value );
					value = replaceAll(value, "{", "{<br/>");
					value = replaceAll(value, "; ", ";<br/>");
					value = replaceAll(value, ",\"", ",<br/>\"");
					value = replaceAll(value, ",\\\"", ",<br/>\\\"");
					value = replaceAll(value, "}", "<br/>}");
				}
				if ( value == "null" ) value = "";
				global.ui.id( key ).html( value || "No Value." );
			}
			$('pre code').each(function(i, block) {
				hljs.highlightBlock(block);
			});
		};



	};

	return Global;
} );