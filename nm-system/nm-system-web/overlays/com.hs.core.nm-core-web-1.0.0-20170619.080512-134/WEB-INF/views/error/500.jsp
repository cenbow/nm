<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%
    //设置返回码200，避免浏览器自带的错误页面
    response.setStatus(200);
%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8"/>
	<title>出错啦！</title>
	<style type="text/css">
		body{margin:0;padding:0;font:14px/1.6 'microsoft yahei';background:#fff;}
		a:link,a:visited{color:#007ab7;text-decoration:none;}
		h1{
			position:relative;
			z-index:2;
			width:540px;
			height:0;
			margin:110px auto 15px;
			padding:230px 0 0;
			overflow:hidden;
			background-image: url(${ctx}/static/images/404.jpg);
			background-repeat: no-repeat;
		}
		h2{
			position:absolute;
			top:55px;
			left:233px;
			margin:0;
			font-size:0;
			text-indent:-999px;
			-moz-user-select:none;
			-webkit-user-select:none;
			user-select:none;
			cursor:default;
			width: 404px;
			height: 90px;
		}
		h2 em{display:block;font:italic bold 200px/120px "Times New Roman",Times,Serif;text-indent:0;letter-spacing:-5px;color:rgba(216,226,244,0.3);}
		.link a{
			background: none repeat scroll 0 0 #f95605;
		    border-radius: 5px;
		    color: #fff;
		    font-size: 18px;
		    font-weight: bold;
		    margin-right: 1em;
		    padding: 12px 24px
		}
		.link a:hover {background:#e04e04;}
		.link,.texts{width:540px;margin:0 auto 15px;color:#505050;}
		.texts{line-height:2;}
		.texts dd{margin:0;padding:0 0 0 15px;}
		.texts ul{margin:0;padding:0;}
		.portal{color:#505050;text-align:center;white-space:nowrap;word-spacing:0.45em;}
		.portal a:link,.portal a:visited{color:#505050;word-spacing:0;}
		.portal a:hover,.portal a:active{color:#007ab7;}
		.portal span{display:inline-block;height:38px;line-height:35px;background:url(img/portal.png) repeat-x;}
		.portal span span{padding:0 0 0 20px;background:url(img/portal.png) no-repeat 0 -40px;}
		.portal span span span{padding:0 20px 0 0;background-position:100% -80px;}
		.STYLE1 {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 65px;
		}
		dt {font-size:20px;color:#e04e04;}
	</style>
	<!--[if lte IE 8]>
		<style type="text/css">
			h2 em{color:#e4ebf8;}
		</style>
	<![endif]-->
	</head>

	<body>
	    <h1></h1>
	    <dl class="texts">
	        <dt><%=request.getAttribute("errormsg") %></dt>
	    </dl>
	    <p class="link">
	        <a href="javascript:history.go(-1);">&#9666;返回上一页</a>
	    </p>
	</body>
</html>
