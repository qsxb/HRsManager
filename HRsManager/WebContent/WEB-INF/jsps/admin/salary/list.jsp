<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算工资界面</title>
<style type="text/css">
#div_month_id {
	margin-left: 20px;
}

td {
	padding-left: 10px;
	text-align: center;
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
							window.location.href = "${pageContext.request.contextPath}/whichMonth?strDate="
									+ strDate;
						})
	})
</script>
</head>
<body>
	<div id="div_month_id">
		选择月份<input type="date" value="${key_date_msg}" name="strDate"
			id="strDate" /> <span style="color: black">所有员工当月工资条信息界面</span>
	</div>
	<table border="1" cellpadding="0" cellspacing="0" id="table1">
		<thead style="background: lightblue">
			<tr>
				<td>员工id</td>
				<td>员工姓名</td>
				<td>员工职位</td>
				<td>员工部门</td>
				<td>员工状态</td>
				<td>本月旷工天数</td>
				<td>本月迟到天数</td>
				<td>本月早退天数</td>
				<td>本月罚款金额</td>
				<td>本月奖励金额</td>
				<td>本月基本工资</td>
				<td>本月绩效</td>
				<td>本月社保</td>
				<td>应发本月工资</td>
				<td>结算状态</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${key_employeeCusttom_list}" var="employeeCusttom">
				<tr>
					<td>${employeeCusttom.eid}</td>
					<td>${employeeCusttom.ename}</td>
					<td>${employeeCusttom.ejob.jname}</td>
					<td>${employeeCusttom.dept.dname}</td>
					<td>${employeeCusttom.estatus}</td>
					<td>${employeeCusttom.kgDay}</td>
					<td>${employeeCusttom.cdDay}</td>
					<td>${employeeCusttom.ztDay}</td>
					<td>${employeeCusttom.pesal}</td>
					<td>${employeeCusttom.resal}</td>
					<td>${employeeCusttom.esalary}</td>
					<td>${employeeCusttom.jxsal}</td>
					<td>${employeeCusttom.sbsal}</td>
					<td>${employeeCusttom.totalSal}</td>
					<td>已自动完成结算</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<table id="table2"></table>
	</div>

</body>
</html>