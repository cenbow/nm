/**
 * Created by Mifeng.He(bee) on 2015/10/27.
 */
(function ($) {
	'use strict';

	var config = {
		template: [
			'<div class="{cssCol}"><div class="form-group {cssFormGroupSize} ui-input-group"><label class="control-label {cssLabel}">{txtLabel}</label><span class="target"></span></div></div>',
			'<div class="input-group"></div>',
			'<div class="{cssPrefix}">{txtPrefix}</div>',
			'<div class="{cssSuffix}"><i class="{cssSuffixIcon}"></i>{txtSuffix}</div>',
			'<div class="{cssInputGroup}"></div>'
		],
		defaults: {
			cssCol: "col-xs-6 col-sm-3 col-md-3 col-lg-3",
			cssFormGroupSize: "form-group-sm",
			cssLabel: "",
			txtLabel: "",
			cssInputGroup: "",
			cssPrefix: "",
			cssPrefixIcon: "",
			txtPrefix: "",
			cssSuffix: "",
			txtSuffix: "",
			cssSuffixIcon: "",
			$target: null,
			_before: function(options){
				var that = options;
				if(!that.cssPrefix)
					that.cssPrefix = Boolean(that.cssPrefixIcon || that.txtPrefix) ? 'input-group-addon' : 'hide';

				if(!that.cssSuffix)
					that.cssSuffix = Boolean(that.cssSuffixIcon || that.txtSuffix) ? 'input-group-addon' : 'hide';

			},
			before: function(options){
				options._before.call(this, options);

			},
			after: function(options){

			}
		}
	};

	var InputGroup = function (options) {
		this.opts = $.extend(true, {}, config.defaults, options);
		this.tmpl = config.template;
		this.init();
	}

	InputGroup.prototype.init = function () {
		var opts = this.opts;
		var tmpl = this.tmpl;

		var $target = opts.$target;
		$target.addClass("form-control");
		opts.before(opts);
		var $Group = $(this.fmt(tmpl[0], opts)).insertAfter($target);

		$Group.find(".target").replaceWith($target);

		if(opts.cssPrefix != 'hide' || opts.cssSuffix != 'hide'){
			$target.wrap(tmpl[1]);
			if(opts.cssPrefix != 'hide') $(this.fmt(tmpl[2], opts)).insertBefore($target);
			if(opts.cssSuffix != 'hide') $(this.fmt(tmpl[3], opts)).insertAfter($target);
			if(opts.cssInputGroup.length){
				$Group.find('.input-group').wrap(this.fmt(tmpl[4], opts));
			}
		}else if(opts.cssInputGroup.length){
			opts.$target.wrap(this.fmt(tmpl[4], opts));
		}
	};


	/**
	 * get uuid
	 * @param len 长度
	 * @param radix 基数
	 * @returns {string}
	 */
	InputGroup.prototype.uuid = function(len, radix) {
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
	InputGroup.prototype.fmt = function () {
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

	$.fn.inputGroup = function () {
		var $thats = $(this), $that, options, opts;
		if ($thats.length == 0) logger.warn($thats.selector, 'jQuery.fn.inputGroup found length is 0.');
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
			new InputGroup(opts);
		});

		return $thats;
	}
})(jQuery);