/**
 * Created by Mifeng.He(bee) on 2016/2/2.
 */
(function () {

	require(["jquery"], function ($) {
		$(function () {
			$("#loading").show().next().show();
		});
	});
	requirejs.config({
		shim: {
			"static/js/protal/protal": {
				deps: [
					"jquery.cookie",
					"screenfull",
					"css2!static/js/protal/css/protal",
					"css2!static/js/protal/css/protal.theme",
					"css2!static/js/protal/css/protal.extend"
				]
			},
			"static/js/homepage/homepage": {
				deps: [
					"echarts"
				]
			}
		}
	});


	app.init(function () {
		require([
			"static/js/protal/protal"
		], function () {
			$("#loading").fadeOut( 200 );
			require(["static/js/homepage/homepage.js"]);
		});
	}, true);

})();
