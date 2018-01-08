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
						d.add('0101', '01', '部门管理', '', '', 'mainFrame');
						d
								.add(
										'010101',
										'0101',
										'部门管理',
										'${pageContext.request.contextPath}/loadDeptList',
										'', 'mainFrame');
						d.add('0102', '01', '职位管理', '', '', 'mainFrame');
						d
								.add(
										'010201',
										'0102',
										'职位管理',
										'${pageContext.request.contextPath}/loadJobList',
										'', 'mainFrame');
						d.add('0103', '01', '员工管理', '', '', 'mainFrame');
						d
								.add(
										'010301',
										'0103',
										'员工管理',
										'${pageContext.request.contextPath}/loadEmployeeList',
										'', 'mainFrame');
						/* 	d.add('010302','0103','查看员工该月考勤','#','','mainFrame'); */
						d.add('0104', '01', '招聘信息管理', '', '', 'mainFrame');
						d
								.add(
										'010401',
										'0104',
										'招聘信息管理',
										'${pageContext.request.contextPath}/loadAdvertiseList',
										'', 'mainFrame');
						d
								.add(
										'010402',
										'0104',
										'查看投递的简历信息',
										'${pageContext.request.contextPath}/loadResumeList',
										'', 'mainFrame');
						d.add('0105', '01', '培训信息管理', '', '', 'mainFrame');
						d
								.add(
										'010501',
										'0105',
										'培训信息管理',
										'${pageContext.request.contextPath}/loadTrainList',
										'', 'mainFrame');
						d.add('0106', '01', '考勤管理', '', '', 'mainFrame');
						d
								.add(
										'010601',
										'0106',
										'查看考勤情况',
										'${pageContext.request.contextPath}/loadAttenceList',
										'', 'mainFrame');
						d.add('0107', '01', '奖励惩罚管理', '', '', 'mainFrame');
						d
								.add(
										'010701',
										'0107',
										'奖励惩罚管理',
										'${pageContext.request.contextPath}/loadRepeList',
										'', 'mainFrame');
						d.add('0108', '01', '薪资管理', '', '', 'mainFrame');
						d
								.add(
										'010801',
										'0108',
										'一键结算薪资操作',
										'${pageContext.request.contextPath}/loadSalaryList',
										'', 'mainFrame');
						d
								.add(
										'010802',
										'0108',
										'查看工资复议',
										'${pageContext.request.contextPath}/loadReviewList',
										'', 'mainFrame');
						document.write(d);
					</script>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>