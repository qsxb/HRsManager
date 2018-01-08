<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门信息列表</title>
<script type="text/javascript">
	function addUser() {
		window.location.href = "${pageContext.request.contextPath}/addDept";
	}
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
function findEmployee() {
	var jobId = $("#selectListJobId").attr("value");
	$.ajax({
			url : "${pageContext.request.contextPath}/emplGetEmployeeMsgByjobId",
			type : "post",
			data :{
				jobId : jobId
			},
			dataType:"json",
		   success : function(result) {
			   $("#selectListEmployeeId").empty();
			  $.each(result,function(index,ele){
				$("#selectListEmployeeId").append("<option value="+ele.ename+">"  
                        + ele.ename + "</option>")
			 }); 
				}
			});
}
</script>
</head>
<body>
	<div>${key_msg}</div>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>对部门的增删改查</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addUser()">&#28155;&#21152;
						</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">

								<td>部门ID</td>
								<td>部门名称</td>
								<td>部门创建时间</td>
								<td>部门下所有职位</td>
								<td>部门下所有职位对应所有的员工</td>
								<td>删除</td>
								<td>修改</td>
							</tr>
							<c:forEach items="${key_deptMsg_list}" var="list">
								<tr>
									<td>${list.did}</td>
									<td>${list.dname}</td>
									<td>${list.dcreatetime}</td>
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
									<td><a
										href="${pageContext.request.contextPath}/delDept?id=${list.did}">
											<img alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/i_del.jpg">
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/modifyDept?id=${list.did}">
											<img alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/i_edit.jpg">
									</a></td>
								</tr>
							</c:forEach>
						</table> 第 <font color="red">${key_deptMsg_pb.page}</font>/${key_deptMsg_pb.totalPage}页
						<c:if test="${key_deptMsg_pb.page != 1}">
							<a href="${pageContext.request.contextPath}/loadDeptList?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/loadDeptList?page=${key_deptMsg_pb.page-1}">上一页</a>
						</c:if> <c:forEach begin="1" end="${key_deptMsg_pb.totalPage}" var="i">
							<c:if test="${key_deptMsg_pb.page == i}">
      [${i}]
  </c:if>
							<c:if test="${key_deptMsg_pb.page != i}">
								<a
									href="${pageContext.request.contextPath}/loadDeptList?page=${i}">[${i}]</a>
							</c:if>
						</c:forEach> <c:if test="${key_deptMsg_pb.page != key_deptMsg_pb.totalPage}">
							<a
								href="${pageContext.request.contextPath}/loadDeptList?page=${key_deptMsg_pb.page+1}">下一页</a>
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