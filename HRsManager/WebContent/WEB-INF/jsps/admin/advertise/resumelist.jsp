<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简历信息列表</title>
</head>
<body>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
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
								<td>简历读取状态</td>
								<td>查看详情</td>
							</tr>
							<c:forEach items="${key_resumeMsg}" var="resume">
								<tr>
									<td>${resume.rid}</td>
									<td>${resume.rname}</td>
									<td>${resume.rage}</td>
									<td>${resume.rsex}</td>
									<td>${resume.reducation}</td>
									<td>${resume.rtel}</td>
									<td>${resume.remail}</td>
									<td>${resume.rpolstatus}</td>
									<td>${resume.deptName}</td>
									<td>${resume.jobName}</td>
									<td>${resume.rreadStatus}</td>
									<td><a
										href="${pageContext.request.contextPath}/loadauditionDesc?id=${resume.user.uid}"><img
											src="${pageContext.request.contextPath}/images/i_desc.jpg" /></a>
									</td>
								</tr>
							</c:forEach>
						</table> 第${key_resumeMsgPb.page}/${key_resumeMsgPb.totalPage}页 <c:if
							test="${key_resumeMsgPb.page != 1}">
							<a
								href="${pageContext.request.contextPath}/loadResumeList?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/loadResumeList?page=${key_resumeMsgPb.page-1}">上一页</a>
						</c:if> <c:forEach end="${key_resumeMsgPb.totalPage}" begin="1" var="i">
							<a
								href="${pageContext.request.contextPath}/loadResumeList?page=${i}">[${i}]</a>
						</c:forEach> <c:if test="${key_resumeMsgPb.page != key_resumeMsgPb.totalPage}">
							<a
								href="${pageContext.request.contextPath}/loadResumeList?page=${key_resumeMsgPb.page+1}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/loadResumeList?page=${key_resumeMsgPb.totalPage}">尾页</a>
						</c:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>