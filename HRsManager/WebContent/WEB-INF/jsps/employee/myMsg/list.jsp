<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工查看个人信息列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div>${key_msg}</div>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">
								<td>我的ID</td>
								<td>我的用户名</td>
								<td>我的密码</td>
								<td>我的职位</td>
								<td>我的部门</td>
								<td>性别</td>
								<td>我的月薪</td>
								<td>联系方式</td>
								<td>修改基本信息</td>
							</tr>
							<tr>
								<td>${key_em_msg.eid}</td>
								<td>${key_em_msg.ename}</td>
								<td>${key_em_msg.epwd}</td>
								<td>${key_em_msg.ejob.jname}</td>
								<td>${key_em_msg.dept.dname}</td>
								<td>${key_em_msg.esex}</td>
								<td>${key_em_msg.esalary}</td>
								<td>${key_em_msg.etel}</td>
								<td><a
									href="${pageContext.request.contextPath}/modifyEmployeeMsg?id=${key_em_msg.eid}">
										<img alt="图片加载异常"
										src="${pageContext.request.contextPath}/images/i_edit.jpg">
								</a></td>
							</tr>
							</td>
							</tr>
							</TBODY>
						</table>
						</form>
</body>
</html>