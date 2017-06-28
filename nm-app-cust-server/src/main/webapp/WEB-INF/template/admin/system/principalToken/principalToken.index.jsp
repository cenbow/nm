<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理</title>
	<script type="text/javascript">Global( "resources/admin/system/principalToken/static/principalToken.index", {} );</script>
</head>
<body>
	<div class="container-fluid">
		<div class="grid-query">
			<input type="text" class="input"  name="accessToken" data-options="{txtPrefix: '认证令牌'}" />
			<div class="input">
				<button type="button" class="btn btn-info btn-search" id="btnSearch1"><i class="fa fa-search"></i> 查询</button>
			</div>
		</div>
		<table id="grid1">
			<caption>
				<button type="button" class="btn btn-default" id="btnView"><i class="fa fa-eye"></i> 查看</button>
				<button type="button" class="btn btn-default" id="btnDelete"><i class="fa fa-remove"></i> 删除</button>
				<button type="button" class="btn btn-default" id="btnClear"><i class="fa fa-remove"></i> 清空</button>
			</caption>
		</table>
	</div>
</body>
</html>