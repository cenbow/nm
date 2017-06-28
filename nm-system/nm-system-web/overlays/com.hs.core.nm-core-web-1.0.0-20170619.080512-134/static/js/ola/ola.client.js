//分机号(系统登录后赋值)
var OLA_EXTN = "";
//队列编号
var OLA_QUEUE = "";
( function() {
	
	/**
	 * 加载OLA连接
	 * @param url {string} 路径 访问ola文件资源路径 注意
	 * @param callkbak {function} 回调函数 参数OLA
	 */
	function load( url, callback ) {
		url += "ola.connect.html";
		var id = name = "IFRAME" + ( "" + Math.random() ).substring( 2 );
		var $iframe = $( '<iframe src="' + url + '" id="' + id + '" name="' + name + '" style="display:none; width: 1px; height: 1px;"/>' ).appendTo( "body" );
		
		$iframe.bind( "load", function( event ) {
			var OLA = window.frames[ name ].OLA;
			callback( OLA );
		} );
	} 
	$( function() {
		load( "js/ola/", function( OLA ) {
			window.ola = new OLA({
				ola_extn: OLA_EXTN, //分机号
				ola_queue: OLA_QUEUE //队列编号
			});
//			top.ola.init();
		} );
	} );
} )();