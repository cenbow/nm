<%@ page import="com.hs.commons.auth.SessionUserUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; if(basePath == null || basePath.equals("")) basePath = "/"; pageContext.setAttribute("basePath", basePath);%>
<!DOCTYPE html>
<html>
<head>
	<%--<link type="text/css" rel="stylesheet" href="static/js/protal/css/protal.css" />
	<link type="text/css" rel="stylesheet" href="static/js/protal/css/protal.theme.css" />--%>
	<!--[if lte IE 8]><link type="text/css" rel="stylesheet" href="rstatic/js/protal/css/protal.ie8.css" /><![endif]-->
	<sitemesh:write property='head' />
	<script type="text/javascript" src="static/js/protal/require.protal.js"></script>
		<%--<script type="text/javascript" src="http://172.16.12.215/api/client.js?client=${staffNo}&channel=${systemNo}"></script>--%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<header class="main-header">
		<!-- Logo -->
		<a href="index.jsp" class="logo">
			<span class="logo-mini">NM</span>
			<span class="logo-lg"><img src="static/images/logo.png" style="width: 86%; position: relative; top: -10px; height: 65px; left: -45px;"></span>
		</a>

		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<shiro:authenticated>
					<li class="dropdown user user-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
							<i class="fa fa-user-md fa-lg"></i><span class="hidden-xs">当前用户：<shiro:principal property="loginNo"/></span>
						</a>
						<ul class="dropdown-menu">
							<li><a class="" href="javascript:;" id="btnChangePassword"><i class="fa fa-edit fa-lg"></i> 修改密码</a></li>
							<li><a class="" href="logout?service=${basePath}"><i class="fa fa-sign-out fa-lg"></i> 注销登陆</a></li>
						</ul>
					</li>
					<li><a href="#" data-toggle-fullscreen=""><em class="fa fa-expand"></em></a></li>
					<li>
						<a href="#" data-toggle="control-sidebar" title="返回首页，关闭所有页面"><i class="fa fa-home fa-lg"></i></a>
					</li>
					</shiro:authenticated>
				</ul>
			</div>
		</nav>


	</header>
	<aside class="main-sidebar">
		<shiro:authenticated>
		<div class="user-panel">
			<!-- <div class="pull-left image">
				<img src="static/js/protal/images/user2-160x160.jpg" class="img-circle" alt="User Image">
			</div> -->
			<div class="info">
				<p><i class="fa fa-user"></i> 姓名：<shiro:principal property="staffName" defaultValue="真实姓名"/></p>
				<p><i class="fa fa-group"></i> 机构：<shiro:principal property="orgName" defaultValue="组织机构"/></p>
			</div>
		</div>
		</shiro:authenticated>

		<form action="#" method="get" class="sidebar-form hide">
			<div class="input-group">
				<input name="q" class="form-control" placeholder="Search..." type="text">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
			</div>
		</form>

		<ul class="sidebar-menu" id="leftMenu">
			<li class="header hide"><i class="fa fa-list"></i> <span>Menu Navigation</span></li>
		</ul>
	</aside>
	<aside class="main-menubar" id="mainMenubar"></aside>
	<aside class="control-sidebar control-sidebar-dark">
		<div class="">
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs hide" style="border-bottom: 1px solid #1A2226;">
				<li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i> 切换选项卡</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<ul class="sidebar-menu history-sidebar-menu"></ul>
				</div>
			</div>

		</div>
	</aside>
	<div class="control-sidebar-bg"></div>

	<div class="content-wrapper">
		<div class="nav-tabs-header" id="frameTabsHeader">
			<div class="nav-tabs-arrow nav-tabs-arrow-left">
				<a href="javascript:;"><i class="fa fa-arrow-left fa-lg"></i></a>
			</div>
			<div class="nav-tabs-content">
				<ul class="nav nav-tabs" id="frameTabs">
					<!-- <li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询01</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询02</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询03</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询04</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询05</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询06</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询07</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询08</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询09</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询10</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询1</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询12</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询13</a><i class="fa fa-remove nav-tabs-close"></i></li>
					<li><a href="javascript:;"><i class="fa fa-caret-right"></i> 产品信息查询14</a><i class="fa fa-remove nav-tabs-close"></i></li> -->
				</ul>
			</div>
			<div class="nav-tabs-arrow nav-tabs-arrow-right">
				<a href="javascript:;"><i class="fa fa-arrow-right fa-lg"></i></a>
			</div>
		</div>
		<div class="sections" id="sections"></div>
		<div class="welcome content" id="mainWelcome">
			<sitemesh:write property='body' />
		</div>
	</div>
</div>
</body>
</html>
