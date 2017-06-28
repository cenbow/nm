/**
 * @author Mifeng.He(bee)
 * @date 2015.09.10
 * 工具类
 */
(function($){
	$.ajaxSetup({
		type: 'GET',
		dataType: 'json',
		cache: false,
		statusCode: {
			404: function (xhr) {
				appui.message.error("请求路径不存在!");
			},
			500: function (xhr) {
				appui.message.error(xhr.responseJSON.errorMsg || "系统异常");
			},
			900: function (xhr) {
				appui.message.error(xhr.responseJSON.errorMsg || "系统异常");
			}
		}
	});



	/**
	 * String 扩展 全部替换
	 * @param reallyDo 要替换的字符
	 * @param replaceWith 替换后的字符
	 * @param ignoreCase boolean（可选）是否忽略大小写 默认false
	 * @returns {string}
	 */
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
		if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
			return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
		} else {
			return this.replace(reallyDo, replaceWith);
		}
	};

	function App () {

	}

	/**
	 * 初始化全局函数
	 * @param global （必含有init入口函数） 有值时缓存global对象, 无值时 执行所有缓存的global对象, 依次调用入口global的init入口函数
	 */
	App.prototype.init = function (global, newGlobal) {
		if (typeof arguments[0] == "function") {
			this.init({init: global});
			if (typeof arguments[1] == "boolean" && arguments[1] === true) {
				this.init();
			}
			return;
		}

		var app = this;
		var globals = $(app).data('globals') || [];

		if (typeof global == 'boolean' ) {
			if (global === true) $(app).removeData('globals');
			return globals;
		}

		$(app).removeData('globals');

		if(typeof global == 'undefined' && globals.length) {
			var context = $(app).data('context') || '';
			$(app).removeData('context');
			var initGlobals = [];
			while (globals.length > 0) {
				global = globals.shift();
				if (typeof global == 'undefined') continue;

				if (global.namespace) {
					if (global.context || (context && $(context).length)) {
						global.context = $(global.context);
						if (global.context.length == 0) global.context = $(context);
						if (global._namespace) global.selector = $('.' + global._namespace, global.context).not('.app-has-init');
						if ((global.selector == null || global.selector.length == 0) && global.__namespace) global.selector = $('.' + global.__namespace, global.context).not('.app-has-init');
						if (global.selector == null || global.selector.length == 0) global.selector = $('.' + global.namespace, global.context).not('.app-has-init');
						if (global.selector.length == 2) throw new Error("app.selector " + global.selector.selector + " don't only one.");
					} else {
						if (global._namespace) global.selector = $('.' + global._namespace).not('.app-has-init');
						if (global.selector == null || global.selector.length == 0) global.selector = $('.' + global.namespace).not('.app-has-init');
						if (global.selector.length == 2) throw new Error("app.selector " + global.selector.selector + " don't only one.");
					}

					if (typeof window._modal != 'undefined' && typeof global._modal == 'undefined') {
						global.modal = global._modal = window._modal;
					}

					if (global.selector.length != 1) {
						try {
							console.log('warning: ".' + global._namespace + '" not only one jQuery Element Object.');
						} catch (e) {
						}
					} else if (global.selector.length == 1) {
						global.selector.addClass('app-has-init');
						//var id = "app_" + appui.uuid(32);
						//global.selector.attr('id', id);
						//global.selector = $($.trim(global.selector.selector) + '#' + id);
					}

				}

				if (typeof global.init == 'function') global.init();

				initGlobals.push(global);
			}
			if (global.namespace) {
				var $selector = $(global.context);
				if ($selector.length == 1) $selector.data('globals', initGlobals);
			}
			return initGlobals;
		}

		if ($.isArray(global)) {
			globals = $.merge(globals, global);
			$(app).data('globals', globals);
		} else if ($.isPlainObject(global)) {
			if ($.isPlainObject(newGlobal)) $.extend(true, global, newGlobal);
			globals.push(global);
			$(app).data('globals', globals);
		} else if (typeof global == 'string') {
			global = app.defined(global);
			if (typeof global.refresh == 'undefined') global = app.refresh(global.namespace, global); //绑定及时更新状态
			global = app.init(global, newGlobal);
		}


		return global;
	};

	/**
	 * 定义命名空间
	 * @param namespace 命令空间用法
	 * @return 返回定义的命名空间
	 **/
	App.prototype.defined = function namespace(namespace, flag) {
		if (!namespace) return window;

		var spaces = namespace.split('.');

		var n = window, len = spaces.length - 1;

		if (!n['otod']) n = n['otod'] = {};
		else n = n['otod'];
		if (!flag) {
			var __namespace = namespace;
			var _namespace = spaces.join('-');
			namespace = spaces.join('_') + '_' + 'ns_' + appui.uuid(16, 32);
			n = n[namespace] = {
				namespace: 'otod["' + namespace + '"]',
				_namespace: _namespace,
				__namespace: __namespace,
				__globals__: [],
				push: function (global) {
					if (typeof global == 'undefined') return -1;
					if (typeof global == 'function') global = global();
					if (typeof global == "object")
						if (typeof global.namespace == 'string') global = global.namespace;

					if (typeof global != 'string') return -1;
					if (global.indexOf('otod[') != 0 ) return -1;

					this.__globals__.push(global);
				}
			};
			return n;
		}

		for (var i = 0; i < spaces.length; i++) {

			if (n[spaces[i]]) {
				if (i != len) {
					n = n[spaces[i]];

				} else {
					var _space = spaces[i];
					var _namespace = spaces.join('-');
					spaces[i] = 'N_' + appui.uuid(16, 32);
					spaces.push(_space);
					n = n[spaces[i]] = {};
					n= n[_space] = {
						namespace: spaces.join('.'),
						__namespace: namespace,
						_namespace: _namespace
					};
				}
			} else
			if (i != len) n = n = n[spaces[i]] = {};
			else n = n[spaces[i]] = {
				namespace: namespace,
				_namespace: spaces.join('-')
			};
		}
		return n;
	};

	/**
	 * 缓存能及时更新对象
	 * @param namespace
	 * @param that
	 * @returns {*}
	 */
	App.prototype.refresh = function (namespace, that) {
		var app = this;
		if (typeof that == 'undefined') return $(app).data(namespace);
		$(app).data(namespace, that);
		that.refresh  = function () {
			return $(app).data(namespace);
		};
		return app.refresh(namespace);
	};

	/**
	 * 销毁app global对象
	 */
	App.prototype.die = function () {
		var app = this;
		if (arguments.length == 0) return;
		var argument = arguments[0];
		if (typeof argument == "function") argument = argument();
		if ($.isArray(argument)) {
			return $.each(argument, function (i, argu) {
				app.die(argu);
			});
		}
		if (typeof argument == 'string' && argument.length > 0 && argument.indexOf('otod[') == 0) argument = this.refresh(argument);
		if (typeof argument != 'object' || typeof argument.namespace != 'string' || argument.namespace.indexOf('otod[') != 0) return;

		var global = argument;
		var globals = global.__globals__;
		global.__globals__ = null;

		if ($.isArray(globals) && globals.length > 0) $.each(globals, function (i, globalSub) {
			app.die(globalSub);
		});


		try {
			$(app).removeData(global.namespace);
			eval(appui.fmt('if (typeof {global} != "undefined") delete {global};', {global: global.namespace}))
		} catch (e) {
			try {
				console.log(global.namespace + ":" + e);
			} catch (e) {
			}
		}

	};

	App.prototype.toTreeJson = function (data, options) {
		var app = this;
		if (typeof data == 'undefined') return [];
		if (!$.isArray(data)) data = [data];

		var defaults = {
			id: 'id',
			pid: 'pid',
			children: 'children'
		};
		var opts = $.extend(true, defaults, options);

		var r = [], tmpMap = {};
		for (var i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][opts.id]] = data[i];
		}
		for (var i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][opts.pid]] && data[i][opts.id] != data[i][opts.pid]) {
				if (!tmpMap[data[i][opts.pid]][opts.children])
					tmpMap[data[i][opts.pid]][opts.children] = [];
				tmpMap[data[i][opts.pid]][opts.children].push(data[i]);
			} else {
				r.push(data[i]);
			}
		}
		return r;
	};

	App.prototype.data = function (data) {
		var app = this;
		if (typeof data == 'undefined' || data == null) data = {success: true};
		else if (typeof data == 'string' || typeof data == 'number') data = {success: true, t: data};
		else if ($.isArray(data)) data = {success: true, list: data};
		else if ($.isPlainObject(data)) {
			if (typeof data.success != 'boolean') data = {success: true, t: data};
		} else return data = {success: true, t: data};

		if (typeof data.msg == 'undefined' || data.msg == null || data.msg.length == 0)
			data.msg = data.success ? '操作成功.' : '操作失败.';

		return data;
	};

	App.prototype.toFixed = function (money, prefix) {
		var app = this;
		if (typeof money == 'boolean' || typeof money == 'function') return money;
		if (typeof money == 'string') money = $.trim(money);
		if (typeof money == 'undefined') money = 0;
		if (typeof money != 'number') money = money - 0;
		if (!Number(money)) money = 0;
		if (typeof prefix == 'boolean' && prefix == true) prefix = '';
		else if (typeof prefix != 'string') prefix = "￥ ";
		return prefix + money.toFixed(2);
	};

	App.prototype.params = function () {
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		for (var i = 0; i < hashes.length; i++) {
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	};

	App.prototype.param = function (name) {
		return this.params()[name];
	};

	window.app = new App();
})(jQuery);

