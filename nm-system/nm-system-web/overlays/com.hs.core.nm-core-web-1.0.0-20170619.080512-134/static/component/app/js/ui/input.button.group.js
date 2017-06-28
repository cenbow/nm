/**
 * Created by Mifeng.He(bee) on 2015/10/27.
 */
(function ($) {
	'use strict';
	var config = {
		template: [
			'<div class="{cssCol}"><div class="form-group ui-input-button-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span><div class="input-group {cssInputButtonGroupSize}"><input type="text" class="form-control" readonly/><div class="{cssSuffix}"><button value="" type="button" class="btn {cssSuffixBtn}"><i class="{cssSuffixIcon}"></i> {txtSuffix}</button></div></div></div>',
			'<div class="{cssPrefix}">{txtPrefix}</div>',
			'<div class="{cssInputButtonGroup}"></div>'
		],
		defaults: {
			cssCol: 'col-xs-6 col-sm-3 col-md-3 col-lg-3',
			cssFormGroupSize: 'form-group-sm',
			cssLabel: '',
			txtLabel: '',
			cssInputButtonGroup:'',
			cssInputButtonGroupSize: 'input-group-sm',
			cssPrefix: "",
			cssPrefixIcon: "",
			txtPrefix: "",
			cssSuffix: "input-group-btn",
			txtSuffix: "",
			cssSuffixBtn: "btn-default",
			cssSuffixIcon: "fa fa-search",
			itemLabel: 'label',
			itemValue: 'value',
			_before: function(options){
				var that = options;
				if(!that.cssPrefix)
					that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';
				if(that.cssShowMaxHeight == 'auto' && ((that.showItemHeight | 0) * (that.showItemNum | 0)) > 0 ) that.cssShowMaxHeight = (that.showItemHeight | 0) * (that.showItemNum | 0);

				that.cssShowMaxHeight = that.cssShowMaxHeight + 10 + "px";
			},
			before: function(options){
				options._before.call(this, options);
				//add other call js
			},
			_after: function(options){},
			after: function(options){
				options._after.call(this, options);
				//add other call js
			},
			events: {
				btnClick: { data: {}, handler: function (event) {}},
				change:  { data: {}, handler: function (event, item) {}}
			}
		}
	};

	var InputButtonGroup = function ($target, options) {
		this.$target = $target;
		this.opts = $.extend(true, {}, config.defaults, options);
		this.tmpls = config.template;
		this.init();
		this.bindEvents();
	};

	InputButtonGroup.prototype.init = function () {
		var opts = this.opts
			, tmpls = this.tmpls
			, $target = this.$target
			, $targetShow
			, $button;

		$target.addClass("form-control");
		opts.before(opts);

		opts.$target.addClass("sr-only");

		var $Group = $(this.fmt(tmpls[0], opts)).insertAfter(opts.$target);
		$Group.find(".target").replaceWith(opts.$target);
		opts.$Group = $Group;

		if(opts.cssInputGroup.length){
			opts.$Group.find('.input-group').wrap(this.fmt(tmpls[2], opts));
		}

	    $targetShow = this.$targetShow = $Group.find('.form-control');
		if(opts.cssPrefix != 'hide' || opts.cssSuffix != 'hide'){
			if(opts.cssPrefix != 'hide') $(this.fmt(tmpls[1], opts)).insertBefore($targetShow);
		}

		$button = $Group.find('button');

		this.$button = $button;
		this.$targetShow = $targetShow;

		$Group = null;
	};


	InputButtonGroup.prototype.bindEvents = function () {
		var that = this
			, $target = that.$target
			, $button = that.$button
			, opts = that.opts;

		if (opts.events.change !== false)
			$target.bind('change.event.ui', opts.events.change.data, opts.events.change.handler);

		$target.bind('change.val.ui', {that: that}, function (event) {
			var that = event.data.that
				, $target = that.$target
				, $targetShow = that.$targetShow
				, opts = that.opts
				, item = $target.data('VALUE');

			$targetShow.val(item[opts.itemLabel]);
			$target.val(item[opts.itemValue]);

			$target.triggerHandler('change.event.ui', item);

		});

		if (opts.events.btnClick !== false)
			$button.bind('click', {that: that, data: opts.events.btnClick.data}, function (event) {
				var that = event.data.that
					, $target = that.$target;
				event.data = event.data.data;
				opts.events.btnClick.handler.call($target, event);
			});
	};



	InputButtonGroup.prototype.uuid = function(len, radix) {
		if(!radix) radix = len * 2;
		var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
		var uuid = [], i;
		radix = radix || chars.length;

		if (len) for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
		else {
			var r; uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-'; uuid[14] = '4';
			for (i = 0; i < 36; i++) {
				if (!uuid[i]) {
					r = 0 | Math.random()*16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}
		return uuid.join('');
	};

	/**
	 * tempalte
	 * @returns {string}
	 */
	InputButtonGroup.prototype.fmt = function () {
		var tmpl = arguments[0] || "";
		if ($.isArray(tmpl)) tmpl = tmpl.join('');
		if (arguments.length == 2 && typeof(arguments[1]) != "array") {
			var dta = arguments[1];
			var format = { };
			return tmpl.replace(/{(\w+)}/g, function(m1, m2) {
				if (!m2)return "";
				return (format && format[m2]) ? format[m2](dta[m2]) || '' : dta[m2] || '';
			});
		} else {
			var args = arguments[1];
			return tmpl.replace(/{(\d+)}/g, function(match, number) {
				return typeof args[number + 1] != 'undefined' ? args[number + 1] || '' : match || '';
			});
		}

	};

	$.fn.inputButtonGroup = function () {
		var $thats = $(this), $that, options, opts;
		if ($thats.length == 0) logger.warn($thats.selector, 'jQuery.fn.inputButtonGroup found length is 0.');
		if (arguments.length == 0) options = {};
		else if (typeof arguments[0] == "string") options = {txtLabel: arguments[0]};
		else if ($.isPlainObject(arguments[0])) options = arguments[0];

		$thats.each(function () {

			$that = $(this);
			if ($that.hasClass('ui-group')) return true;

			$that.addClass('ui-group');
			opts = $that.attr('options');
			if (typeof opts == 'undefined') opts = {};
			else if (typeof opts == 'string') {
				$that.removeAttr('options');
				if (opts.indexOf('{') == 0) opts = eval(opts);
				else opts = {txtLabel: opts};
			}
			opts = $.extend(true, {}, options, opts);

			opts.$target = $that;
			new InputButtonGroup(opts);
		});

		return $thats;
	}
})(jQuery);