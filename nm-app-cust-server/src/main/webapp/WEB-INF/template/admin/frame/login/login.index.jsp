<!DOCTYPE html>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; if(basePath == null || basePath.equals("")) basePath = "/"; pageContext.setAttribute("basePath", basePath);%>
	<base href="${basePath }" />
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>柠檬移动分期服务集成中心V1</title>
	<link type="image/x-icon" rel="shortcut icon" href="assets/favicon.ico"/>
	
	<link type="text/css" rel="stylesheet" href="assets/component/bootstrap/3.3.4/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="assets/component/font-awesome/4.5.0/css/font-awesome.min.css"/>
	<script type="text/javascript" src="assets/component/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="assets/component/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<!--[if IE 8]>
	<script type="text/javascript" src="assets/component/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script type="text/javascript" src="assets/component/respond/1.4.2/respond.min.js"></script>
	<script type="text/javascript" src="assets/component/respond/1.4.2/respond.matchmedia.addListener.min.js"></script>
	<script type="text/javascript" src="assets/component/bootstrap-ie8/1.1.0/bootstrap.ie8.js"></script>
	<![endif]-->
	<!--[if lte IE 7]>
	<script src="assets/component/browser-support/1.1.0/browser.support.ie7.js"></script>
	<![endif]-->
	
	<link type="text/css" rel="stylesheet" href="assets/component/toastr/2.12/toastr.min.css"/>	
	<script type="text/javascript" src="assets/component/toastr/2.12/toastr.min.js"></script>
	
	<link type="text/css" rel="stylesheet" href="resources/admin/frame/login/login.index.css"/>

	<link type="image/x-icon" rel="shortcut icon" href="favicon.ico?3.1"/>
	
	<style type="text/css">#toast-container>div  { width: 320px!important; }</style>
	
	<script type="text/javascript"> var GLOBAL_PARAMS = { BASE_PATH: "${basePath}" }; </script>
	<script type="text/javascript" src="resources/admin/frame/login/login.index.js"></script>
	
</head>
<body class="">
<div class="frame-login-container">
	<img src="resources/admin/frame/login/images/bg.jpg">
	<div class="frame-login-form">
		<div class="mask"></div>
		<form autocomplete="off">
			<h1>柠檬科技</h1>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-user"></i></div>
				<input type="text" class="form-control" name="name" placeholder="用户名" autocomplete="off"/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-keyboard-o"></i></div>
				<input type="password" class="form-control" name="password" placeholder="密码" autocomplete="off"/>
			</div>
			<div>
				<button type="button" class="btn btn-primary btn-submit" id="btnSubmit" >登录</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>