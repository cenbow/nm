<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<% String path = request.getContextPath(); String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; if(basePath == null || basePath.equals("")) basePath = "/"; pageContext.setAttribute("basePath", basePath);%>
<!DOCTYPE html>
<html>
<head>
    <title>
    	<sitemesh:write property='title' /> - 柠檬金服
  	</title>

	<sitemesh:write property='head' />
</head>
<body>
	<div class="ui-page-html">
		<div class="ui-page-body">
			<sitemesh:write property='body' />
		</div>
	</div>
</body></html>