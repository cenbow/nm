define( function() {
	var base = require( "app/base" )
	, message = base.message
	, tools = base.tools
	, moment = require( "moment" );

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
		this.loadBefore = function() {};

		/*** 页面布局 */
		this.layout = function() {
			var global = this.getGlobal();

			global.ui.find( ".input" ).input();

			var config = {
					align: "center",
					indexCol: true,
					checkCol: true,
					nextCol: false,
					multi: true,
					remote: {
						url: "admin/system/principalToken/selectList"
					},
					cols: [],
					page: true,
					query: ".grid-query",
					sort: true,
					events: {},
					customEvents: []
			};

			var dateRenderer = function( val, item, index ) {
				return tools.dateUtil.format( val, "YYYY-MM-DD hh:mm:ss" );
			};
			
			var statusRenderer = function( val, item, index) {
				if ( val < 0 ) return "永久有效";
				if ( val == 0 ) return "已注销";
				
				return moment( item.updtDate ).add( val, 'm' ).isBefore( new Date() ) ? "已失效" : "正常";
			};
			
			var statusCss = function( val, item, index ) {
				val = item.validMins;
				if ( val < 0 ) return "bg-warning";
				if ( val == 0 ) return "bg-info"
				
				console.log(moment( item.updtDate ).add( val, 'm' ).isBefore( new Date() ));
				
				return moment( item.updtDate ).add( val, 'm' ).isBefore( new Date() ) ? "bg-danger" : "bg-success";
			}

			var cols = config.cols;
			cols[ cols.length ] = { title: "状态", name: "validMins", width: "60px", align: 'center', renderer: statusRenderer, css: statusCss };
			cols[ cols.length ] = { title: "访问令牌", name: "accessToken", width: "350px" };
			cols[ cols.length ] = { title: "登录账号", name: "loginNo", width: "100px", align: "left" };
			cols[ cols.length ] = { title: "客户端地址", name: "clientIp", width: "120px", align: "right" };
			cols[ cols.length ] = { title: "服务接口", name: "remoteIp", width: "120px", align: "right" };
			cols[ cols.length ] = { title: "有效分数", name: "validMins", width: "80px", align: "right" };
			cols[ cols.length ] = { title: "添加时间", name: "instDate", width: "160px", align: 'right', renderer: dateRenderer };
			cols[ cols.length ] = { title: "更新时间", name: "updtDate", width: "160px", align: 'right', sortDefault: true, sortDesc: 'desc', renderer: dateRenderer };
			global.ui.grid( "#grid1", config );

		};

		/*** 加载后 */
		this.loadAfter = function() {};

		/*** 验证*/
		this.validate = function() {
			var global = this.getGlobal();
		};

		/*** 绑定事件 */
		this.bindEvent = function() {
			var global = this.getGlobal();

			global.ui.id( "btnClear" ).click( function() {
				global.handlers.doClear();
			} );
			
			global.ui.id( "btnDelete" ).click( function() {
				var items = global.ui.grid( "#grid1" ).selectedRows();
				if ( items.length == 0 ) {
					return message.error( "请至少选择一条操作数据." );
				}
				global.handlers.doDelete( items );
			} );
			
			global.ui.id( "btnView" ).click( function() {
				var items = global.ui.grid( "#grid1" ).selectedRows();
				if ( items.length != 1 ) {
					return message.error( "请选择一条操作数据." );
				}

				global.handlers.openView( items[ 0 ] );
			} );

		};

		this.handlers.load = function() {};
		
		//删除
		this.handlers.doClear = function() {
			var global = this.getGlobal();

			global.dialog.confirm( "确定清空异常信息缓存", function( event, index ) { 
				if ( index != 0 ) return;

				$.ajax( {
					url: "system/principalToken/clear",
					type: "POST",
					beforeSend: function() {
						global.loading.show();
					},
					success: function( command ) {
						message.success( command.message );
						global.ui.grid( "#grid1" ).load( { pageNo: 1 } );
					},
					complete: function() {
						global.loading.fadeOut();
					}
				} )
			} );

		};
		
		//删除
		this.handlers.doDelete = function( items ) {
			var global = this.getGlobal();

			var content = format( "确定删除已选择的[ {0} ]操作数据?", [ items.length ] );
			global.dialog.confirm( content, function( event, index ) { 
				if ( index != 0 ) return;

				var accessTokens = [];

				for ( var index = 0; index < items.length; index++ ) {
					accessTokens.push( items[ index ].accessToken );
				}

				var command = new base.command();
				command.setJsonArray( accessTokens );

				$.ajax( {
					url: "admin/system/principalToken/delete",
					type: "POST",
					data: command.submit(),
					beforeSend: function() {
						global.loading.show();
					},
					success: function( command ) {
						message.success( command.message );
						global.ui.grid( "#grid1" ).load( { pageNo: 1 } );
					},
					complete: function() {
						global.loading.fadeOut();
					}
				} )
			} );

		};

		
		//重置登录密码视图
		this.handlers.openView = function( item ) {
			var global = this.getGlobal();
			
			global.page.open( {
				title: tools.format( '<font color="red" style="font-weight: bold;">[错误码 {0}]</font> 异常信息视图', [ item.errorCode ] ),
				url: "admin/system/principalToken/form",
				size: 'modal-md',
				params: {
					item: item
				},
				events: {
					hidden: function ( closed, data ) {
						if ( !closed ) return;
						global.ui.grid( "#grid1" ).load();
					}
				}
			} ) 
		};

	};

	return Global;
} );