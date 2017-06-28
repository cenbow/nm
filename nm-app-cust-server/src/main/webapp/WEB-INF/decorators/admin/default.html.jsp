<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><sitemesh:write property='title' />经营管理及决策支持系统</title>
<script type="text/javascript">
	( function ( window ) {
		var args = []; window.App = window.Global = function () { args.push( arguments ); };
		require( [ "jquery", "app/frame/frame.html" ], function ( $ ) {
			$( function () {
				$( window ).trigger( "resize" );
				$( document ).trigger( "main.ready", [ args ] );
			} );
		} );
	} )( window );
</script>
<sitemesh:write property='head' />
</head>
<body>
	<div class="section">
		<div class="section-headr"></div>
		<div class="sections-wrapper"></div>
		<div class="section-container">
			<div class="section-loading2">
				<div class="section-loadding-spiner">
					<i class="fa fa-spinner fa-spin"></i> <span>数据处理中...</span>
				</div>
			</div>
			<div class="page-container"><sitemesh:write property='body' /></div>
		</div>
	</div>
</body>
</html>