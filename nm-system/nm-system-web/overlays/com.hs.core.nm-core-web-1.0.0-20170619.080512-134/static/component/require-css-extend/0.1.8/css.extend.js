/**
 * css.extend.js 0.1.8
 * 针对require-css 0.1.8 打出的补丁
 * Mifeng.He(bee 2504637134@qq.com)
 * 样式可以多个配置
 * 配置格式：
 * css!css1|css2|css3 用|分隔
 */
define(['require.css/css'], function (cssApi) {
	cssApi.__load__ = cssApi.load;
	cssApi.load = function (cssId, req, load, config) {
		var flag = true, 
			exception = false;
			cssIds = cssId.split('|');
			var index = 1;
		while (flag && cssIds.length > 0) {
			try {
				this.__load__(cssIds.shift(), req, load, config);
			} catch (e) {
				flag = false;
				exception = e;
			}
			cssIds = [];
		}
		
		if (exception) {
			if (window.console ) 
				if (window.console.error) window.console.error('CSS: ' + cssId);
				else if (window.console.log) window.console.log('CSS: ' + cssId);
			throw exception;
		}
	};
	return cssApi;
})