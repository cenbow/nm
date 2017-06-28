/**
 * Created by Mifeng.He(bee) on 2015/11/3.
 */
(function ($) {
	var app = {}, appui = {};
	if (typeof $ == 'undefined' || $ == null || $.fn == null) throw new Error("require jquery.1.11.x.js.");

	var __scripts__ = this.document['scripts'], __app_path__;

	for(var i = __scripts__.length; i > 0; i--) {
		if (__scripts__[i - 1].src.indexOf("all.js") > -1) {
			__app_path__ = __scripts__[i - 1].src.substring(0, __scripts__[i - 1].src.lastIndexOf("/") + 1);
			break;
		}
	}

	function requireJs () {
		for (var i = 0; i < arguments.length; i++) {
			if (typeof arguments[i] != 'string'  && arguments[i].indexOf('.css') == -1) continue;
			document.write('<script type="text/javascript" src="' + __app_path__ + arguments[i] + '" ></script>');
		}
	}

	function requireCss () {
		for (var i = 0; i < arguments.length; i++) {
			if (typeof arguments[i] != 'string' && arguments[i].indexOf('.js') == -1) continue;
			document.write('<link type="text/css" rel="stylesheet" href="' + __app_path__ + arguments[i] + '" />');
		}
	}

	requireCss('./css/ui/index.css');
	requireJs(
		'./js/logger.js',
		'./js/base.js',
		'./js/ui/input.group.js',
		'./js/ui/input.button.group.js'
	);

})(jQuery);