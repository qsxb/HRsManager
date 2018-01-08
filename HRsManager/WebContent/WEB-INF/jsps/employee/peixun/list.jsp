<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训信息列表</title>
</head>
<body>
	<div>${key_msg}</div>
	<c:if test="${! empty key_train_msg}">
		<form id="Form1" name="Form1"
			action="${pageContext.request.contextPath}/##" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center"
				bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3"><strong>关于我的培训消息</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">
									<td>培训信息ID</td>
									<td>培训时间</td>
									<td>培训内容</td>
									<td>培训地点</td>
									<td>培训官</td>
								</tr>
								<tr>
									<td>${key_train_msg.tid}</td>
									<td>${key_train_msg.ttime}</td>
									<td>${key_train_msg.tcontent}</td>
									<td>${key_train_msg.taddress}</td>
									<td>${key_train_msg.employee.ename}</td>
								</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</c:if>
</body>
</html>