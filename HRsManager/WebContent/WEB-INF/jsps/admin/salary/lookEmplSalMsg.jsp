<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看该员工异议的月薪情况</title>
<style type="text/css">
#table1_id {
	margin-left: 20px;
	margin-top: 20px;
}

td {
	padding-left: 10px;
	width: 50px;
	height: 10px;
}
</style>
</head>
<body>
	<table border="1px" cellpadding="0" cellspacing="0" id="table1_id">
		<thead style="background: lightblue">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>基本工资</td>
				<td>绩效工资</td>
				<td>奖励工资</td>
				<td>惩罚工资</td>
				<td>社保</td>
				<td>剩余工资</td>
				<td>年份</td>
				<td>月份</td>
				<td>没问题,驳回</td>
				<td>有出入,请注意在下月薪资查看</td>
				<td>重新计算一遍薪资</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${key_salary.employee.eid}</td>
				<td>${key_salary.employee.ename}</td>
				<td>${key_salary.ssalary}</td>
				<td>${key_salary.sjxsal}</td>
				<td>${key_salary.sresal}</td>
				<td>${key_salary.spesal}</td>
				<td>${key_salary.ssbsal}</td>
				<td>${key_salary.stotalsal}</td>
				<td>${key_salary.year}</td>
				<td>${key_salary.month}</td>
				<td><a
					href="${pageContext.request.contextPath}/rejectyy?srid=${key_srid_msg}">驳回</a></td>
				<td><a
					href="${pageContext.request.contextPath}/reissue?eid=${key_salary.employee.eid}&month=${key_salary.month+1}&srid=${key_srid_msg}">有出入,下月补发</a></td>
				<td><a
					href="${pageContext.request.contextPath}/recyle?sid=${key_salary.sid}">重新计算一遍</a></td>
			</tr>
		</tbody>
	</table>
</body>
</html>