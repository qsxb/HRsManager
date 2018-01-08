<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重新计算工资结果界面</title>
</head>
<body>
	<div>
		${key_salary.employee.ename} <span>:该月应得工资</span>${key_salary.stotalsal}
	</div>
	<div>
		<input type="button" onclick="history.go(-1)" value="返回" />
	</div>
</body>
</html>