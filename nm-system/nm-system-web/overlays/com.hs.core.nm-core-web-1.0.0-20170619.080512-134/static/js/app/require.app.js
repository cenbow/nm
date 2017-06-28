(function () {
	require.config({
		paths: {
			"static": [
				"static/"
			],
			"echarts": [
			    //"http://cdn.bootcss.com/echarts/2.2.7/echarts-all",
				"static/component/echarts/echarts-all"
			],
			"require.css/css": [
				//"http://apps.bdimg.com/libs/require-css/0.1.8/css.min",
				"static/component/require-css/0.1.8/css.min"
			],
			"require.css.extend/css.extend": [
				"static/component/require-css-extend/0.1.8/css.extend.min"
			],
			"jquery": [
				//"http://libs.baidu.com/jquery/1.11.3/jquery.min",
				"static/component/jquery/jquery-1.11.3.min"
			],

			"jquery.cookie": [
				//"http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie",
				"static/component/jquery-cookie/jquery.cookie"
			],

			//bootstrap
			"bootstrap": [
				//"http://libs.baidu.com/bootstrap/3.3.4/js/bootstrap.min",
				"static/component/bootstrap/js/bootstrap.min"
			],
			//bootstrap-datetimepicker 日期
			"bootstrap.datetimepicker": [
				//"http://cdn.bootcss.com/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min",
				"static/component/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min"
			],
			
			//moment 日期国际化处理
			"moment": [
				//"http://cdn.bootcss.com/moment.js/2.11.1/moment.min",
				"static/component/moment/moment.min"
			],
			"moment.with.locales": [
				//"http://cdn.bootcss.com/moment.js/2.11.1/moment-with-locales.min",
				"static/component/moment/moment-with-locales.min"
			],
			"locales": [
				"static/component/moment/locales.min"
			],

			"bootbox": [
				"static/component/bootbox/bootbox.min"
			],
			"toastr": [
				"static/component/toastr/toastr.min"
			],

			"screenfull": [
				"static/component/screenfull/screenfull"
			],

			"dropzone": [
				//"http://cdn.bootcss.com/dropzone/4.1.1/min/dropzone.min",
				"static/component/dropone/min/dropzone.min"
			],
			
			"jquery.slimscroll": [
               	//"http://cdn.bootcss.com/jQuery-slimScroll/1.3.3/jquery.slimscroll.min",
               	"static/component/slimScroll/jquery.slimscroll.min"
			],

			"jquery.validate": [
				"static/component/jquery-validate/jquery.validate.min"
			],
			"jquery.validate.min": [
				"static/component/jquery-validate/jquery.validate.min"
			],
			"jquery.validate.additional.methods": [
				"static/component/jquery-validate/additional-methods.min"
			],
			"jquery.validate.method.extend": [
				"static/component/jquery-validate/jquery.validate.method.extend"
			],
			"jquery.validate.extend": [
				"static/component/jquery-validate/jquery.validate.extend"
			],

			"app": [
				"static/js/app/app"
			],
			"app.code": [
				"static/js/app/app.code"
			],
			"appui": [
				"static/js/app/appui"
			],
			"appui.template.extend": [
				"static/js/app/appui.template.extend"
			],
			"appui.plugins.extend": [
				"static/js/app/appui.plugins.extend"
			],
			"appui.grid": [
				"static/js/app/appui.grid"
			],
			"appui.page": [
				"static/js/app/appui.page"
			]
		},
		map: {
			"*": {
				"css2": "require.css.extend/css.extend"
			}
		},
		shim: {
			"require.css.extend/css.extend": {
				deps: [
					"require.css/css"
				]
			},
			"jquery.cookie": {
				deps: [
					"jquery"
				]
			},
			"locales": {
				deps: [
					"moment.with.locales"
				]
			},
			"moment.with.locales": {
				deps: [
					""
				]
			},
			"bootstrap": {
				deps: [
					"jquery",
					"css2!static/component/bootstrap/css/bootstrap.min",
					"css2!static/component/font-awesome/css/font-awesome.min"
				]
			},
			"bootstrap.datetimepicker": {
				deps: [
					"bootstrap",
					"locales",
					"css2!static/component/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min"
				]
			},
			"jquery.validate": {
				deps: [
					"jquery"
				]
			},
			"jquery.validate.additional.methods": {
				deps: [
					"jquery.validate.min"
				]
			},
			"jquery.validate.method.extend": {
				deps: [
					"jquery.validate.additional.methods"
				]
			},
			"jquery.validate.extend": {
				deps: [
					"jquery.validate.method.extend"
				]
			},
			
			"jquery.slimscroll": {
				deps: [
			       "jquery"
		        ]
			},

			"dropzone": {
				deps: [
					"jquery",
					"css2!static/component/dropone/min/basic.min",
					"css2!static/component/dropone/min/dropzone.min"
				]
			},
			"bootbox": {
				deps: [
					"bootstrap"
				]
			},
			"toastr": {
				deps: [
					"css2!static/component/toastr/toastr.min"
				]
			},
			"app": {
				deps: [
					"jquery"
				]
			},
			"app.code": {
				deps: [
					"app"
				]
			},
			"appui": {
				deps: [
					"jquery",
					"bootstrap",
					"app",
					"css2!static/js/app/css/appui.css"
				]
			},
			"appui.page": {
				deps: [
					"appui"
				]
			},
			"appui.grid": {
				deps: [
					"appui"
				]
			},
			"appui.template.extend": {
				deps: [
					"appui"
				]
			},
			"appui.plugins.extend": {
				deps: [
					"appui",
					"bootbox",
					"toastr"
				]
			},
			"static/js/protal/require.protal": {
				deps: [
					"jquery",
					"css2!static/js/app/css/appui.css"
				]
			}
		}
	});
})();
(function () {
	if ( typeof window.jQuery == "undefined" ) {
		window.__jquery_ready_handlers__ = [];
		window.$ = function ( handlers ) {
			__jquery_ready_handlers__.push( handlers );
		};
		require( ["jquery"], function ( $ ) {
			window.$ = window.jQuery = $;
			var handlers = window.__jquery_ready_handlers__;
			
			window.__jquery_ready_handlers__ = [];
			try { delete window.__jquery_ready_handlers__; } catch( e ) {};
			
			/*$.each( handlers , function( index, handler ) {
				handler();
			} );*/


			var handler;
			for ( var index = 0; index < handlers.length; index++ ) {
				handler = handlers[ index ];
				if ( typeof handler == "function" ) handler();
			}

		} );
	}
	var App = {};
	App.init = function () {
		var args = arguments;
		require([
			"app",
			"app.code",
			"bootstrap",
			"appui.plugins.extend",
			"appui.template.extend",
			"appui.grid",
			"appui.page",
			"jquery.slimscroll",
			"bootstrap.datetimepicker",
			"jquery.validate.extend"
		], function () {
			var $ = require("jquery");
			//appui.loading.hide(function () {});
			$(function () {
				app.init.apply(app, args);
			});
		});
	};
	window.app = App;
	var assets = [
	              	"bootstrap",
	              	"jquery.slimscroll",
	              	"jquery.validate.extend",
	              	"toastr",
	              	"bootbox",
              		"dropzone"
    ], asset;
	for (var i = 0; i < assets.length; i++) {
		asset = [ assets[i] ];
		require( asset );
	}
	
})();
(function () {
	require([
		"static/js/protal/require.protal"
	]);
})();