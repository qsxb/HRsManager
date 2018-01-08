<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工查看所有部门职位员工信息</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function findEmployee() {
		var jobId = $("#selectListJobId").attr("value");
		$.ajax({
			url : "${pageContext.request.contextPath}/getEmployeeMsgByjobId",
			type : "post",
			data : {
				jobId : jobId
			},
			dataType : "json",
			success : function(result) {
				$("#selectListEmployeeId").empty();
				$.each(result, function(index, ele) {
					$("#selectListEmployeeId").append(
							"<option value="+ele.ename+">" + ele.ename
									+ "</option>")
				});
			}
		});
	}
</script>
</head>
<body>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>查看公司所有部门职位,员工</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">

								<td>部门名称</td>
								<td>部门下所有职位</td>
								<td>部门下所有职位对应所有的员工</td>
							</tr>
							<c:forEach items="${key_deptMsg_list}" var="list">
								<tr>
									<td>${list.dname}</td>
									<td><select name="selectListJob" id="selectListJobId"
										onchange="findEmployee()">
											<c:forEach items="${list.job}" var="listJob">
												<option value="${listJob.jid}">${listJob.jname}</option>
											</c:forEach>
									</select></td>
									<td><select name="selectListEmployee"
										id="selectListEmployeeId">
											<option value="0">请选择</option>
									</select></td>
								</tr>
							</c:forEach>
						</table> 第 <font color="red">${key_deptMsg_pb.page}</font>/${key_deptMsg_pb.totalPage}页
						<c:if test="${key_deptMsg_pb.page != 1}">
							<a
								href="${pageContext.request.contextPath}/deptJobEmployeeMsg?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/deptJobEmployeeMsg?page=${key_deptMsg_pb.page-1}">上一页</a>
						</c:if> <c:forEach begin="1" end="${key_deptMsg_pb.totalPage}" var="i">
							<c:if test="${key_deptMsg_pb.page == i}">
      [${i}]
  </c:if>
							<c:if test="${key_deptMsg_pb.page != i}">
								<a
									href="${pageContext.request.contextPath}/deptJobEmployeeMsg?page=${i}">[${i}]</a>
							</c:if>
						</c:forEach> <c:if test="${key_deptMsg_pb.page != key_deptMsg_pb.totalPage}">
							<a
								href="${pageContext.request.contextPath}/deptJobEmployeeMsg?page=${key_deptMsg_pb.page+1}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/loadDeptList?page=${key_deptMsg_pb.totalPage}">尾页</a>
						</c:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>