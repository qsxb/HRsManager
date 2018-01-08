<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息列表</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function(){
	$(".del_id").click(function(){
	var	reason = prompt("请输入删除原因:","");
		if (reason != null){
		alert("原因是" + reason +"!");
		}
	})
})
	function addUser() {
		window.location.href = "${pageContext.request.contextPath}/addEmployee";
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
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>对员工的增删改查</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">
								<td>员工ID</td>
								<td>员工名称</td>
								<td>所在职位</td>
								<td>所在部门</td>
								<td>员工性别</td>
								<td>员工薪资</td>
								<td>联系方式</td>
								<td>状态</td>
								<td>删除该员工记录</td>
								<td>开除该员工</td>
								<td>换岗操作</td>
							</tr>
							<c:forEach items="${key_employeeMsg_list}" var="list">
								<tr>
									<td>${list.eid}</td>
									<td>${list.ename}</td>
									<td>${list.ejob.jname}</td>
									<td>${list.dept.dname}</td>
									<td>${list.esex}</td>
									<td>${list.esalary}</td>
									<td>${list.etel}</td>
									<td>${list.estatus}</td>
									<td><a
										href="${pageContext.request.contextPath}/delEmployee?id=${list.eid}"
										class="del_id"> <img alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/i_del.jpg">
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/kaichu?id=${list.eid}">
											<img alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/kaichu.png">
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/modifyEmployee?id=${list.eid}">
											<img alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/i_edit.jpg">
									</a></td>
								</tr>
							</c:forEach>
						</table> 第 <font color="red">${key_employeeMsg_pb.page}</font>/${key_employeeMsg_pb.totalPage}页
						<c:if test="${key_employeeMsg_pb.page != 1}">
							<a
								href="${pageContext.request.contextPath}/loadEmployeeList?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/loadEmployeeList?page=${key_employeeMsg_pb.page-1}">上一页</a>
						</c:if> <c:forEach begin="1" end="${key_employeeMsg_pb.totalPage}"
							var="i">
							<c:if test="${key_employeeMsg_pb.page == i}">
      [${i}]
  </c:if>
							<c:if test="${key_employeeMsg_pb.page != i}">
								<a
									href="${pageContext.request.contextPath}/loadEmployeeList?page=${i}">[${i}]</a>
							</c:if>
						</c:forEach> <c:if
							test="${key_employeeMsg_pb.page != key_employeeMsg_pb.totalPage}">
							<a
								href="${pageContext.request.contextPath}/loadEmployeeList?page=${key_employeeMsg_pb.page+1}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/loadEmployeeList?page=${key_employeeMsg_pb.totalPage}">尾页</a>
						</c:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>