<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工主页</title>
<style>
body {
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-BASE-COLOR: #dee3f7;
}
</style>
</head>
<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/loadEmplTop"
		name="topFrame" scrolling="NO" noresize>
	<frameset cols="300,*" frameborder="0" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/loadEmplLeft"
			name="leftFrame" noresize scrolling="YES">
		<frame src="${pageContext.request.contextPath}/loadEmplWelcome"
			name="mainFrame">
	</frameset>
</frameset>
</html>