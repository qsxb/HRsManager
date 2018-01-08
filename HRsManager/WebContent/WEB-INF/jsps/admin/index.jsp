<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主页</title>
<style>
body {
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-BASE-COLOR: #dee3f7;
}
</style>
</head>
<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/loadTop" name="topFrame"
		scrolling="NO" noresize>
	<frameset cols="159,*" frameborder="0" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/loadLeft"
			name="leftFrame" noresize scrolling="YES">
		<frame src="${pageContext.request.contextPath}/loadWelcome"
			name="mainFrame">
	</frameset>
</frameset>
</html>