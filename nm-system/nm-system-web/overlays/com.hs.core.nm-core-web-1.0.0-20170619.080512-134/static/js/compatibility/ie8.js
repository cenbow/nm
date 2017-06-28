/**
 * Created by Mifeng.He(bee) on 2015/10/29.
 */
(function () {
	/*ie8 indexOf*/
	if (!Array.prototype.indexOf) {
		Array.prototype.indexOf = function(elt /*, from*/){
			var len = this.length >>> 0;
			var from = Number(arguments[1]) || 0;
			from = (from < 0) ? Math.ceil(from) : Math.floor(from);
			if (from < 0)
				from += len;
			for (; from < len; from++){
				if (from in this && this[from] === elt)
					return from;
			}
			return -1;
		};
	}

	/*ie8 trim*/
	String.prototype.trim = function () { return this.replace(/(^\s*)|(\s*$)/g, ""); };
	String.prototype.ltrim = function () { return this.replace(/(^\s*)/g, ""); };
	String.prototype.rtrim = function () { return this.replace(/(\s*$)/g, ""); };


	if (typeof window.console == 'undefined')
		window.console = (function () {var c = {}; c.log = c.info = c.warn = c.error = function () {}; return c; })()
})();
