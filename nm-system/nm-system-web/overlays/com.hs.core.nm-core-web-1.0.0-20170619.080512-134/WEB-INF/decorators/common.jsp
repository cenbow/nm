<!DOCTYPE html>
<html lang="en">
<head>
	<!--
		服务器脚本生成
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
		<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; if(basePath == null || basePath.equals("")) basePath = "/"; pageContext.setAttribute("basePath", basePath);%>
	-->
    <base href="${basePath}"/>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta content="text/html"/>
	<title> <sitemesh:write property='title' /> 柠檬金服 </title>
	<script type="text/javascript">window.VERSION="${version}";</script>
	<link rel="shortcut icon" href="favicon.ico?version=${version}" type="image/x-icon" />
	<script type="text/javascript" src="static/component/requirejs/require.min.js"></script>
	<script type="text/javascript" src="static/js/app/require.app.js?version=${version}"></script>
	<script type="text/javascript" src="static/js/constant/PubConstant.jsp"></script>
	<script type="text/javascript" src="static/js/constant/SelfConstant.jsp"></script>
	<script type="text/javascript" src="static/js/ola/ola_api.js"></script>
	<sitemesh:write property='head' />
</head>
<body>
	<div style="display: none;" class="ui-main-loading" id="loading">
		<div class="ui-loading-con">
			<div class="ui-loading-circle"></div>
			<img src="static/images/loading_logo.png" alt="">
		</div>
	</div>
	<div class="body" style="display: none;" id="mainAppBody">
		<sitemesh:write property='body'/>
	</div>
<!-- 消息推送 -->
<% 
	pageContext.setAttribute("server", com.hs.utils.ParamUtils.getParam("pubshMsg") );
	pageContext.setAttribute("client", com.hs.commons.auth.SessionUserUtil.getCurrentUser().getStaffNo() );
	pageContext.setAttribute("channel", com.hs.commons.auth.SessionUserUtil.getCurrentUser().getSysCode() );
%>
<script type="text/javascript">
	( function() {
		var server = false;
		<shiro:hasPermission name="push-msg-perm">server = "${server}/api/client.js?client=${client}&channel=${channel}";</shiro:hasPermission>
		if ( server ) {  document.write( "<script src=\"" + server + "\"><" + "/script>" ); } 
	} )();
</script>
</body>
</html>