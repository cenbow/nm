<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理</title>
	<style type="text/css">
		${param.PAGEID} .container2 > .page-header:FIRST-CHILD {margin: 0 0 15px 0;!important}
	</style>
	<script type="text/javascript">Global( "resources/admin/system/principalToken/static/principalToken.form", {} );</script>
</head>
<body>
	<div class="container-fluid container2">
		<div class="page-header"><i class="fa fa-info"></i> 摘要信息：</div>
		<pre><code class="java" id="summary"></code></pre>
		
		<div class="page-header"><i class="fa fa-info"></i> 异常信息：</div>
		<pre><code class="java" id="exception"></code></pre>
		
		<div class="page-header"><i class="fa fa-info"></i> 用户主体信息：</div>
		<pre><code class="javascript" id="principal"></code></pre>
		
		<div class="page-header"><i class="fa fa-info"></i> 请求头信息：</div>
		<pre><code class="javascript" id="requestHeaders"></code></pre>
		
		<div class="page-header"><i class="fa fa-info"></i> 请求参数信息：</div>
		<pre><code class="javascript" id="requestParams"></code></pre>
		
		<div class="page-header"><i class="fa fa-info"></i> 请求体信息：</div>
		<pre><code class="javascript" id="requestBody"></code></pre>
		
		<div class="page-header"><i class="fa fa-info"></i> 请求属性信息：</div>
		<pre><code class="javascript" id="requestAttributes"></code></pre>
		
		<div class="page-header"><i class="fa fa-info"></i> 会话属性信息：</div>
		<pre><code class="javascript" id="requestAttributes"></code></pre>
	</div>
</body>
</html>