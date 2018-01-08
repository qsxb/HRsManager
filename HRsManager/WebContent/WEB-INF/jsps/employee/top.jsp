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

#myMsg {
	margin-left: 50px;
}

a {
	text-decoration: none
}
</style>
</head>
<body>
	<div id="top_div">
		<div id="div_id">我的个人中心</div>
		<div id="myMsg">
			<span><img
				src="${pageContext.request.contextPath}/images/touxiang.png" /></span> <span>用户:&nbsp;&nbsp;<font
				color="red">${session_employee_msg.ename}</font></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><img
				src="${pageContext.request.contextPath}/images/msg.png" /></span> <span>&nbsp;&nbsp;<a
				href="#">我的消息</a></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span><img
				src="${pageContext.request.contextPath}/images/exist.png" /></span> <span>&nbsp;&nbsp;<a
				href="${pageContext.request.contextPath}/employeeExist"
				target="_parent">退出</a></span>
		</div>
	</div>
</body>
</html>