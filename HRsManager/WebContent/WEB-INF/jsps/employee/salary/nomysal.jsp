<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>味道工资结算日期显示界面</title>
</head>
<body>
	当前时间:
	<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd HH:mm" />
	<font color="black"><span>${session_employee_msg.ename}:</span></font>
	<h3>每月工资20之后发放,请耐心等待~~</h3>
</body>
</html>