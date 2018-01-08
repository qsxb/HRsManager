<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录界面</title>
<link href="${pageContext.request.contextPath}/css/general.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	color: white;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		//前台用户名非空校验
		$("#username").blur(function() {
			if ($("#username").val().length == 0) {
				$("#adminNameError").text("用户名不能为空!");
			} else {
				$("#adminNameError").text("");
			}
		});
		//前台用密码非空校验
		$("#password").blur(function() {
			if ($("#password").val().length == 0) {
				$("#adminPwdError").text("密码不能为空!");
			} else {
				$("#adminPwdError").text("");
			}
		});
		$("#enter_id").click(
				function() {
					if ($("#username").val().length != 0
							&& $("#password").val().length != 0) {
						$("#adminNameError").text("");
						$("#adminPwdError").text("");
						$("#adminLoginId").submit();

					} else {
						$("#adminNameError").text("用户名不能为空!");
						$("#adminPwdError").text("密码不能为空!");
					}
				})
	})
</script>
</head>
<body style="background: #278296">
	<%--  <jsp:forward page="index.jsp"/> --%>
	<div>${key_msg}</div>
	<form method="post"
		action="${pageContext.request.contextPath }/validateAdminEnter"
		id="adminLoginId">
		<table cellspacing="0" cellpadding="0" style="margin-top: 100px"
			align="center">
			<tr>
				<td style="padding-left: 50px">
					<table>
						<tr>
							<td>管理员姓名:</td>
							<td><input type="text" value="${key_adminname_msg}"
								name="username" id="username" /></td>
							<td><font color="red"><span id="adminNameError"></span></font></td>
						</tr>
						<tr>
							<td>管理员密码:</td>
							<td><input type="password" value="${key_adminpwd_msg}"
								name="password" id="password" /></td>
							<td><font color="red"><span id="adminPwdError"></span></font></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="button" value="进入管理中心" id="enter_id" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>