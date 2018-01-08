<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工考勤信息列表</title>
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
			id="strDate" />
	</div>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>查看考勤</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">
								<td>员工ID</td>
								<td>员工名称</td>
								<td>员工职位</td>
								<td>员工部门</td>
								<td>缺勤天数</td>
								<td>迟到天数</td>
								<td>早退天数</td>
								<td>本月绩效奖金</td>
							</tr>
							<c:forEach items="${key_employeeCusttom_list}"
								var="employeeCusttom">
								<tr>
									<td>${employeeCusttom.eid}</td>
									<td>${employeeCusttom.ename}</td>
									<td>${employeeCusttom.ejob.jname}</td>
									<td>${employeeCusttom.dept.dname}</td>
									<td>${employeeCusttom.kgDay}</td>
									<td>${employeeCusttom.cdDay}</td>
									<td>${employeeCusttom.ztDay}</td>
									<td>${employeeCusttom.jxsal}</td>
								</tr>
							</c:forEach>
						</table>
						<%--  第 <font color="red">${key_employeeMsg_pb.page}</font>/${key_employeeMsg_pb.totalPage}页
						<c:if test="${key_employeeMsg_pb.page != 1}">
							<a href="${pageContext.request.contextPath}/loadEmployeeList?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/loadEmployeeList?page=${key_employeeMsg_pb.page-1}">上一页</a>
						</c:if> <c:forEach begin="1" end="${key_employeeMsg_pb.totalPage}" var="i">
							<c:if test="${key_employeeMsg_pb.page == i}">
      [${i}]
  </c:if>
							<c:if test="${key_employeeMsg_pb.page != i}">
								<a
									href="${pageContext.request.contextPath}/loadEmployeeList?page=${i}">[${i}]</a>
							</c:if>
						</c:forEach> <c:if test="${key_employeeMsg_pb.page != key_employeeMsg_pb.totalPage}">
							<a
								href="${pageContext.request.contextPath}/loadEmployeeList?page=${key_employeeMsg_pb.page+1}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/loadEmployeeList?page=${key_employeeMsg_pb.totalPage}">尾页</a>
						</c:if> --%>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>