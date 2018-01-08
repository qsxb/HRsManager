<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顶部</title>
<style type="text/css">
#top_div {
	background-color: rgb(230, 230, 230);
	width: 100%;
	height: 250px;
}

#div_id {
	font-size: 40px;
	text-align: center;
}

#span_username_id {
	margin-left: 10px;
}
</style>
</head>
<body>
	<div id="top_div">
		<div id="div_id">欢迎来到人力资源管理系统</div>
		<div>
			<span id="span_username_id">欢迎:<font color="red">${session_adminname}</font></span>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp; <img
			src="${pageContext.request.contextPath}/" />
	</div>
</body>
</html>