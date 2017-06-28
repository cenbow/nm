<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>内嵌页面</title>
<style type="text/css">
	${param.PAGEID}.section {}
	${param.PAGEID}.section .section-container {padding: 0 15px !important;}
	${param.PAGEID}.section .page-container {padding-top: 0;}
	${param.PAGEID}.section .page-container #iframeContainer { position: relative; top: 2px;}
</style>
<script type="text/javascript">
	App( "resources/frame/iframe/static/iframe.index", {} );
</script>
</head>
<body class="container-fluid">
	<div class="row row0" id="iframeContainer"></div>
</body>
</html>