<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改培训信息页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function(){
		$("#add_submit").click(function(){
			if(($("#tcontent").val().length) != 0 || ($("#taddress").val().length) != 0){
				$("#updateTrain").submit();
			}
		})
	})
	</script>
</head>
<body>
	<form id="updateTrain" name="Form1"
		action="${pageContext.request.contextPath}/updateTrainMsg?id=${key_train_msg.tid}"
		method="post">
		<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
			border="1" id="DataGrid1"
			style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
			<tr
				style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">

				<td>培训信息ID</td>
				<td>培训时间</td>
				<td>培训内容</td>
				<td>培训地点</td>
				<td>培训人员</td>
			</tr>
			<tr>
				<td>${key_train_msg.tid}</td>
				<%-- <td><fmt:formatDate value="${list.ttime}" pattern="yyyy-MM-dd HH:mm"/></td> --%>
				<td><input type="datetime-local" value="${key_train_msg.ttime}"
					name="ttime" id="ttime" /></td>
				<td><input type="text" value="${key_train_msg.tcontent}"
					name="tcontent" id="tcontent" />
				<td><input type="text" value="${key_train_msg.taddress}"
					name="taddress" id="taddress" />
				<td><select name="selectTrainEmployeeName">
						<c:forEach items="${key_train_msg.list}" var="employee">
							<option value="${employee.eid}">${employee.ename}</option>
						</c:forEach>
						<c:forEach items="${key_emplName_msg}" var="empl">
							<option value="${empl.eid}">${empl.ename}</option>
						</c:forEach>
						<%-- <c:forEach items="${key_train_msg.list}" var="employee">
								<option value="${employee.eid}">${employee.ename}</option>
							</c:forEach> --%>
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