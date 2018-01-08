<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>面试官面试结果</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#admin").click(function() {
			window.location.href = "admin?status=1"
		});
		$("#notAdmin").click(function() {
			window.location.href = "notAdmin?status=0"
		});
	})
</script>
</head>
<body>
	<input type="button" value="录用" id="admin" />
	<input type="button" value="不录用" id="notAdmin" />
</body>
</html>