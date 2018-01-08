<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工复议信息列表</title>
</head>
<body>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>对员工复议的一些操作</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">

								<td>复议信息ID</td>
								<td>员工编号</td>
								<td>员工姓名</td>
								<td>异议年份</td>
								<td>异议月份</td>
								<td>原因</td>
								<td>批阅</td>
								<td>操作状态</td>
							</tr>
							<c:forEach items="${key_list_msg}" var="list">
								<tr>
									<td>${list.srid}</td>
									<td>${list.employee.eid}</td>
									<td>${list.employee.ename}</td>
									<td>${list.salary.year}</td>
									<td>${list.salary.month}</td>
									<td>${list.srreason}</td>
									<td><a
										href="${pageContext.request.contextPath}/lookSalReviewMsg?id=${list.srid}"><img
											alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/jlck.png"></a></td>
									<td>${list.srstatus}</td>
								</tr>
							</c:forEach>
						</table> 第${key_salReview_pb_msg.page}/${key_salReview_pb_msg.totalPage}页
						<c:if test="${key_salReview_pb_msg.page != 1}">
							<a
								href="${pageContext.request.contextPath}/loadReviewList?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/loadReviewList?page=${key_salReview_pb_msg.page-1}">上一页</a>
						</c:if> <c:forEach end="${key_salReview_pb_msg.totalPage}" begin="1"
							var="i">
							<a
								href="${pageContext.request.contextPath}/loadReviewList?page=${i}">[${i}]</a>
						</c:forEach> <c:if
							test="${key_salReview_pb_msg.page != key_salReview_pb_msg.totalPage}">
							<a
								href="${pageContext.request.contextPath}/loadReviewList?page=${key_salReview_pb_msg.page+1}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/loadReviewList?page=${key_salReview_pb_msg.totalPage}">尾页</a>
						</c:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>