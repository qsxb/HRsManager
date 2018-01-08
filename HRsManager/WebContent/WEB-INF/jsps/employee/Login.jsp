<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工登录界面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login1.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(
			function() {
				$("#verifyCodeImage").click(
						function() {
							$("#verifyCodeImage").attr(
									"src",
									"${pageContext.request.contextPath}/vCode?a="
											+ new Date().getTime());
						})
				//校验用户名
				$("#username_id").blur(function() {
					if ($("#username_id").val().length == 0) {
						$("#error_username_msg").text("用户名不能为空");
					} else {
						$("#error_username_msg").text("");
					}
				})
				//校验密码
				$("#password_id").blur(function() {
					if ($("#password_id").val().length == 0) {
						$("#error_pwd_msg").text("密码不能为空");
					} else {
						$("#error_pwd_msg").text("");
					}
				})
				//校验验证码
				$("#randomCode_id").blur(function() {
					var id = "randomCode_id";
					var value = $("#" + id).val();
					if (value == "") {
						$("#errormsg_random_id").text("验证码不能为空");
					} else if (value.length != 4) {
						$("#errormsg_random_id").text("验证码长度不符合");
					} else {
						$.ajax({
							url : "validateCode",
							data : {
								randomCode : value
							},
							type : "post",
							dateType : "json",
							success : function(result) {
								if ($.trim(result) == "false") {
									$("#errormsg_random_id").text("验证码错误");
								} else {
									$("#errormsg_random_id").text("");
								}
							},
							error : function(result) {
								window.alert("456");
							}
						});
					}
				});
				/*表单提交前台验证  */
				$("#add_submit").click(
						function() {
							if ($("#password_id").val().length == 0
									|| $("#username_id").val().length == 0
									|| $("#randomCode_id").val().length == 0) {

							} else {
								$("#employeeForm").submit();
							}
						});
			})
</script>
</head>
<body>
	<div>
		<c:if test="${! empty key_exist_msg}">
         ${key_exist_msg}
      </c:if>
	</div>
	<form id="employeeForm"
		action="${pageContext.request.contextPath}/validateEmplLogin"
		method="post">
		<!-- 4行4列 -->
		<table>
			<tr>
				<td>用户名:</td>
				<td colspan="2"><input type="text" value="" id="username_id"
					name="username" /></td>
				<td><font color="red" id="error_username_msg"></font></td>

			</tr>
			<tr>
				<td>密码:</td>
				<td colspan="2"><input type="password" value=""
					id="password_id" name="pwd" /></td>
				<td><font color="red" id="error_pwd_msg"></font></td>

			</tr>
			<tr>
				<td>验证码:</td>
				<td colspan="2"><input type="text" value="" id="randomCode_id"
					name="randomCode" />
					<p id="errormsg_random_id"></p></td>
				<td><img title="看不清,换一张" alt="未正常显示" style="cursor: pointer;"
					src="${pageContext.request.contextPath}/vCode" id="verifyCodeImage" />
				</td>
			</tr>
		</table>
		<div id="submit_id">
			<input type="reset" value="重置" /> <input type="button"
				id="add_submit" value="登录" /> <input type="button"
				onclick="history.go(-1)" value="返回" />
		</div>
	</form>
</body>
</html>