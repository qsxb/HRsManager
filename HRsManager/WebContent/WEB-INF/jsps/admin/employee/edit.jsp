<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	   function findJob() {
			var deptId = $("#selectDeptNameId").attr("value");
			$.ajax({
					url : "${pageContext.request.contextPath}/changeDeptAndJob",
					type : "post",
					data :{
						deptId : deptId
					},
					dataType:"json",
				   success : function(result) {
					   $("#selectJobNameId").empty();
					  $.each(result,function(index,ele){
						$("#selectJobNameId").append("<option value="+ele.jname+">"  
	                            + ele.jname + "</option>")
					 }); 
						}
					});
		}
	   $(function(){
		$("#add_submit").click(function(){
			$("#addJobName").submit();
		})
	   })
	</script>
</HEAD>
<body>
	<form id="addJobName" name="Form1"
		action="${pageContext.request.contextPath}/updateEmployeeMsg?id=${key_employee_msg.eid}"
		method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>对员工进行换岗的操作</STRONG> </strong></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					员工所处部门:</td>
				<td width="18%" bgColor="#ffffff"><select name="selectDeptName"
					id="selectDeptNameId" onchange="findJob()">
						<option value="" selected="selected">${key_employee_msg.dept.dname}</option>
						<c:forEach items="${key_listDept_msg}" var="listDept">
							<option value="${listDept.did}">${listDept.dname}</option>
						</c:forEach>
				</select></td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					员工所处部门对应的职位:</td>
				<td width="18%" bgColor="#ffffff"><select name="selectJobName"
					id="selectJobNameId">
						<option value="" selected="selected">${key_employee_msg.ejob.jname}</option>
						<option value="0">请选择</option>
				</select></td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="button" id="add_submit" value="确定" class="button_ok">
						&#30830;&#23450;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>