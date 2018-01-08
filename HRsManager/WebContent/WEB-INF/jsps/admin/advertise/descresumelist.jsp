<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简历信息列表</title>
<script type="text/javascript">
</script>
</head>
<body>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/sendMsgToemail?id=${key_resume.rid}"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>获取到的简历信息</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">

								<td>简历信息ID</td>
								<td>姓名</td>
								<td>年龄</td>
								<td>性别</td>
								<td>学历</td>
								<td>电话号码</td>
								<td>邮箱</td>
								<td>政治面貌</td>
								<td>面试部门</td>
								<td>面试职位</td>
							</tr>
							<tr>
								<td>${key_resume.rid}</td>
								<td>${key_resume.rname}</td>
								<td>${key_resume.rage}</td>
								<td>${key_resume.rsex}</td>
								<td>${key_resume.reducation}</td>
								<td>${key_resume.rtel}</td>
								<td>${key_resume.remail}</td>
								<td>${key_resume.rpolstatus}</td>
								<td>${key_resume.deptName}</td>
								<td>${key_resume.jobName}</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td>请输入面试时间 <input type="datetime-local" value=""
									name="faceTime" />
								</td>
								<td><select name="whoGoFaceId">
										<c:forEach items="${key_listEmpl_msg}" var="empl">
											<option value="${empl.eid}">${empl.ename}</option>
										</c:forEach>
								</select></td>
								<td><input type="submit" value="面试" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>