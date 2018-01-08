<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
</script>
</HEAD>
<body>
	<form
		action="${pageContext.request.contextPath}/updateUserResume?id=${key_recyle_resumeMsg.rid}"
		method="post">
		<table>
			<thead>
				<tr align="center" background="gray">
					<td colspan="5">修改简历操作</td>
				</tr>
			</thead>
			<tr bgColor="#afd1f3" align="center">
				<td>姓名</td>
				<td>年龄</td>
				<td>性别</td>
				<td>学历</td>
				<td>电话号码</td>
				<td>邮箱</td>
				<td>政治面貌</td>
			</tr>
			<tr align="center">
				<td><input type="text" value="${key_recyle_resumeMsg.rname}"
					name="name" readonly="readonly" /></td>
				<td><input type="text" value="${key_recyle_resumeMsg.rage}"
					name="age" id="age" /></td>
				<td><input type="radio" name="sex" value="男" checked="checked" />男
					<input type="radio" name="sex" value="女" />女</td>
				<td><select name="education">
						<option value="${key_recyle_resumeMsg.reducation}">${key_recyle_resumeMsg.reducation}</option>
						<option value="本科">本科</option>
						<option value="博士">博士</option>
						<option value="硕士">硕士</option>
						<option value="高中">高中</option>
						<option value="其他">其他</option>
				</select></td>
				<td><input type="text" value="${key_recyle_resumeMsg.rtel}"
					name="phone" id="phone" /></td>
				<td><input type="email" value="${key_recyle_resumeMsg.remail}"
					name="email" id="email" /></td>
				<td><select name="polstatus">
						<option value="${key_recyle_resumeMsg.rpolstatus}"
							selected="selected">${key_recyle_resumeMsg.rpolstatus}</option>
						<option value="中共党员">中共党员</option>
						<option value="共青团员">共青团员</option>
						<option value="群众">群众</option>
				</select></td>
			</tr>
			<tr align="center">
				<td><input type="button" onclick="history.go(-1)" value="返回" /></td>
				<td><input type="submit" value="提交" /></td>
				<td><input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</HTML>