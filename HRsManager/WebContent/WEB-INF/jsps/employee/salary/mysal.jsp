<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的薪资界面(员工)</title>
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

#strDate {
	margin-left: 20px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#strDate")
				.blur(
						function() {
							var strDate = $("#strDate").val();
							window.location.href = "${pageContext.request.contextPath}/myWhichMonth?strDate="
									+ strDate;
						})
	})
</script>
</head>
<body>
	<div>${key_msg}</div>
	<div id="div_month_id">
		选择月份<input type="date" value="${key_date_msg}" name="strDate"
			id="strDate" /> <span style="color: black">我的工资条信息界面</span>
	</div>
	<c:if test="${!empty key_salary_msg}">
		<table border="1px" cellpadding="0" cellspacing="0" id="table1_id">
			<thead style="background: lightblue">
				<tr>
					<td>我的编号</td>
					<td>我的姓名</td>
					<td>基本工资</td>
					<td>绩效工资</td>
					<td>奖励工资</td>
					<td>惩罚工资</td>
					<td>社保</td>
					<td>剩余工资</td>
					<td>年份</td>
					<td>月份</td>
					<td>有异议</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${key_salary_msg.employee.eid}</td>
					<td>${key_salary_msg.employee.ename}</td>
					<td>${key_salary_msg.ssalary}</td>
					<td>${key_salary_msg.sjxsal}</td>
					<td>${key_salary_msg.sresal}</td>
					<td>${key_salary_msg.spesal}</td>
					<td>${key_salary_msg.ssbsal}</td>
					<td>${key_salary_msg.stotalsal}</td>
					<td>${key_salary_msg.year}</td>
					<td>${key_salary_msg.month}</td>
					<td><a
						href="${pageContext.request.contextPath}/salReview?eid=${key_salary_msg.employee.eid}&sid=${key_salary_msg.sid}"><img
							src="${pageContext.request.contextPath}/images/yiyi.png"></a></td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty key_salary_msg}">
		<table border="1px" cellpadding="0" cellspacing="0" id="table2_id">
			<thead style="background: lightblue">
				<tr>
					<td>我的编号</td>
					<td>我的姓名</td>
					<td>基本工资</td>
					<td>绩效工资</td>
					<td>奖励工资</td>
					<td>惩罚工资</td>
					<td>社保</td>
					<td>剩余工资</td>
					<td>年份</td>
					<td>月份</td>
					<td>有异议</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="8">本月无薪资记录!</td>
				</tr>
			</tbody>
		</table>
		</tbody>
	</c:if>
</body>
</html>