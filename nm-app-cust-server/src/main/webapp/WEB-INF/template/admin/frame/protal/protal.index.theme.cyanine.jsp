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
	<title>经营管理及决策支持系统</title>
	<link type="image/x-icon" rel="shortcut icon" href="assets/favicon.ico"/>
	<script type="text/javascript">
		require( [ "bootstrap", "css2!resources/admin/frame/protal/static/protal.index.theme.cyanine" ], function(){
			require( [ "resources/admin/frame/protal/static/protal.index.theme.cyanine" ], function() {
				var $ = require( "jquery" );
				$( function() {
					$( window ).trigger( "resize" );
					$( document ).trigger( "main.ready" );
				} );
			} );
		} );
	</script>
</head>
<body>
	<div class="nm-frame-left">
		<div class="nm-frame-banner" id="nmFrameBanner">
			<img alt="logo" src="resources/admin/frame/protal/static/images/logo.png" />
			<!-- <span>数据决策系统</span> -->
		</div>
		<div class="nm-frame-menu">
			<div class="nm-menu-scroll" id="nmMenuScroll">
				<div class="nm-menutree" id="nmMenuTree"></div>
			</div>
		</div>
	</div>
	<div class="nm-frame-right">
		<div class="nm-frame-header" id="nMFrameHeader">
			<div class="pull-left">柠檬科技有限责任公司</div>
			<div class="pull-right">
				<a href="frame/logout" class="header-logout header-link" title="注销"><i class="fa fa-sign-out fa-2x"></i></a>
			</div>
		</div>
		<div class="nm-frame-body" id="nMFrameBody">
			<div class="nm-menutree-toggle-btn" id="nmMenuTreeToggleBtn"></div>
			<div class="nm-frame-content">
				<div class="nm-tab">
					<div class="nm-tab-btns" id="nmTabBtns">
						<!-- <a class="nm-tab-homepage">
							<i class="fa fa-home"></i>
						</a> -->
						<ul id="nmTabs"></ul>
						<a class="nm-tab-more">
							<i class="fa fa-th"></i>
						</a>
					</div>
					<div class="nm-tab-contents" id="nmTabContents">
						<div class="nm-tab-loading" id="nmTabLoading">
							<div><i class="fa fa-spinner fa-spin"></i> 加载中...</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="nm-frame-footer"></div>
</body>
</html>