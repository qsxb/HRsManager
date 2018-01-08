<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左边菜单界面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/left.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/dtree.css" type="text/css">
<style type="text/css">
.dtree {
	margin-left: 10px;
}
</style>
</head>
<body>
	<table width="100" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="12"></td>
		</tr>
	</table>
	<table width="100%" border="0">
		<tr>
			<td>
				<div class="dtree">

					<a href="javascript: d.openAll();">展开所有</a> | <a
						href="javascript: d.closeAll();">关闭所有</a>

					<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/dtree.js"></script>
					<script type="text/javascript">
						d = new dTree('d');
						d.add('01', -1, '系统菜单树');
						d.add('0101', '01', '我的信息', '', '', 'mainFrame');
						d
								.add(
										'010101',
										'0101',
										'我的基本信息',
										'${pageContext.request.contextPath}/userloadMsg?uname=${userName_in_session}',
										'', 'mainFrame');
						d.add('0102', '01', '我的简历', '', '', 'mainFrame');
						d
								.add(
										'010201',
										'0102',
										'查看简历',
										'${pageContext.request.contextPath}/userloadResumeMsg?uname=${userName_in_session}',
										'', 'mainFrame');
						document.write(d);
					</script>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>