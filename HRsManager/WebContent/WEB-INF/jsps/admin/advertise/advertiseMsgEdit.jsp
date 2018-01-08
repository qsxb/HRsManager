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
	function findJob() {
		var deptName = $("#selectDeptNameId").attr("value");
		$.ajax({
			url : "${pageContext.request.contextPath}/updateFindJob",
			type : "post",
			data : {
				deptName : deptName
			},
			dataType : "json",
			success : function(result) {
				$("#selectJobNameId").empty();
				$.each(result, function(index, ele) {
					$("#selectJobNameId").append(
							"<option value="+ele.jname+">" + ele.jname
									+ "</option>")
				});
			}
		});
	}
</script>
</HEAD>
<body>
	<form
		action="${pageContext.request.contextPath}/updateAdvertise?id=${key_advertise.aid}"
		method="post">
		<table>
			<thead>
				<tr align="center" background="gray">
					<td colspan="5">修改招聘信息操作</td>
				</tr>
			</thead>
			<tr bgColor="#afd1f3" align="center">
				<td>招聘信息Id</td>
				<td>部门名称</td>
				<td>部门下职位</td>
				<td>薪资</td>
				<td>招聘人数</td>
			</tr>
			<tr align="center">
				<td><input type="text" value="${key_advertise.aid}" name="aid"
					readonly="readonly" /></td>
				<td><select name="selectDeptName" id="selectDeptNameId"
					onchange="findJob()">
						<option value="" selected="selected">${key_advertise.adeptName}</option>
						<c:forEach items="${key_deptMsg}" var="list">
							<option value="${list.dname}">${list.dname}</option>
						</c:forEach>
				</select></td>
				<td><select name="selectJobName" id="selectJobNameId">
						<option value="" selected="selected">${key_advertise.ajobName}</option>
						<option value="0">请选择</option>
				</select></td>
				<td><select name="selectSalary">
						<option value="${key_advertise.asalary}">${key_advertise.asalary}</option>
						<option value="4000">4000</option>
						<option value="5000">5000</option>
						<option value="6000">6000</option>
				</select></td>
				<td><input type="text" value="${key_advertise.anumber}"
					name="number" id="number" /></td>
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