<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改奖惩信息页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function(){
		$("#add_submit").click(function(){
				$("#updateRepe").submit();
		})
	})
	</script>
</head>
<body>
	<form id="updateRepe" name="Form1"
		action="${pageContext.request.contextPath}/updateRepeMsg?id=${key_repe_msg.rpid}"
		method="post">
		<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
			border="1" id="DataGrid1"
			style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
			<tr
				style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">

				<td>奖惩信息ID</td>
				<td>奖励时间</td>
				<td>奖励金额</td>
				<td>奖励原因</td>
				<td>惩罚时间</td>
				<td>惩罚金额</td>
				<td>惩罚原因</td>
				<td>员工姓名</td>
			</tr>
			<tr>
				<td>${key_repe_msg.rpid}</td>
				<td><input type="datetime-local" value="${key_repe_msg.retime}"
					name="retime" id="retime" /></td>
				<td><input type="text" value="${key_repe_msg.resal}"
					name="resal" id="resal" /></td>
				<td><input type="text" value="${key_repe_msg.rereason}"
					name="rereason" id="rereason" /></td>
				<td><input type="text" value="${key_repe_msg.petime}"
					name="petime" id="petime" /></td>
				<td><input type="text" value="${key_repe_msg.pesal}"
					name="pesal" id="pesal" /></td>
				<td><input type="text" value="${key_repe_msg.pereason}"
					name="pereason" id="pereason" /></td>
				<td><select name="selectRepeEmployeeName">
						<option selected="selected">${key_repe_msg.employee.ename}</option>
						<c:forEach items="${key_employee_list}" var="employee">
							<option value="${employee.eid}">${employee.ename}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="button" id="add_submit" value="确定" class="button_ok">
						&#30830;&#23450;</button>
					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>
					<INPUT class="button_ok" type="button" onclick="history.go(-1)"
					value="返回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>