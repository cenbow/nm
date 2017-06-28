<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<style type="text/css">
			#uploadForm {display: none}
			#uploadFrame {display: none}
		</style>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
		<script type="text/javascript">
			function addImage(image) {
				$('#uploadImg').before('<img src="' + image.networkAddress + '" width="80">');
			}
		</script>
	</head>
	<body>
		<div id="ct-image">
			
			<img id="uploadImg" src="http://ico.ooopic.com/ajax/iconpng/?id=97367.png" width="80">
		</div>
		<form id="uploadForm" target="uploadFrame" action="${pageContext.request.contextPath}/system/attachment/save" method="post" enctype="multipart/form-data">
			<input type="file" name="file">
			<input type="text" name="script" value="addImage">
		</form>
		<iframe id="uploadFrame" name="uploadFrame"></iframe>
		<script type="text/javascript">
			$('#uploadImg').on('click', function() {
				$('#uploadForm input[name=file]')[0].click();
			});
			$('#uploadForm input[name=file]').on('change', function() {
				$('#uploadForm').submit();
			});
		</script>
	</body>
</html>