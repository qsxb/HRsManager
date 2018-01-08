<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤信息列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#strDate").blur(function(){
		var strDate = $("#strDate").val();
		window.location.href = "${pageContext.request.contextPath}/whichDay?page=1&strDate="+strDate;
	})
})
</script>
</head>
<body>
	<div>${key_msg}</div>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<tr>
				<td bgColor="#afd1f3"><input type="date"
					value="${key_date_msg}" name="strDate" id="strDate" /></td>
			</tr>
			<TBODY>
				<tr>
					<td align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<thead>
								<tr>
									<td bgColor="#f5fafe">应到人数</td>
									<td>${key_countEmployee}</td>
									<td bgColor="red">已到人数</td>
									<td>${key_attenceEmployee}</td>
									<td bgColor="#f5fafe">缺勤人数</td>
									<td>${key_countEmployee-key_attenceEmployee}</td>
								</tr>
							</thead>
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">
								<td>员工id</td>
								<td>员工姓名</td>
								<td>上班时间</td>
								<td>下班时间</td>
								<td>迟到状态</td>
								<td>早退状态</td>
							</tr>
							<c:forEach items="${key_attence_list}" var="attence">
								<tr>
									<td>${attence.employee.eid}</td>
									<td>${attence.employee.ename}</td>
									<td><fmt:formatDate value="${attence.onworktime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
									<td><fmt:formatDate value="${attence.offworktime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
									<td>${attence.comelatestatus}</td>
									<td>${attence.leftearlystatus}</td>
								</tr>
							</c:forEach>
						</table> 第 <font color="red">${key_attence_pb.page}</font>/${key_attence_pb.totalPage}页
						<c:if test="${key_attence_pb.page != 1}">
							<a
								href="${pageContext.request.contextPath}/loadAttenceList?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/loadAttenceList?page=${key_attence_pb.page-1}">上一页</a>
						</c:if> <c:forEach begin="1" end="${key_attence_pb.totalPage}" var="i">
							<c:if test="${key_attence_pb.page == i}">
      [${i}]
  </c:if>
							<c:if test="${key_attence_pb.page != i}">
								<a
									href="${pageContext.request.contextPath}/loadAttenceList?page=${i}">[${i}]</a>
							</c:if>
						</c:forEach> <c:if test="${key_attence_pb.page != key_attence_pb.totalPage}">
							<a
								href="${pageContext.request.contextPath}/loadAttenceList?page=${key_attence_pb.page+1}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/loadAttenceList?page=${key_attence_pb.totalPage}">尾页</a>
						</c:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>