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
		var deptName = $("#selectDeptNameId").attr("value");
		$.ajax({
				url : "${pageContext.request.contextPath}/findJob",
				type : "post",
				data :{
					deptName : deptName
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
</script>
</HEAD>
<body>
	<form action="${pageContext.request.contextPath}/saveAdvertiseMsg"
		method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>添加招聘信息</STRONG> </strong></td>
			</tr>

			<tr>
				<td bgColor="#f5fafe">部门名称：</td>
				<td bgColor="#f5fafe">职位名称：</td>
				<td bgColor="#f5fafe">月薪：</td>
				<td bgColor="#f5fafe">招聘人数：</td>
			</tr>
			<tr>
				<td bgColor="#ffffff"><select name="deptName"
					id="selectDeptNameId" onchange="findJob()">
						<c:forEach items="${key_dept_list}" var="list">
							<option value="${list.dname}">${list.dname}</option>
						</c:forEach>
				</select></td>
				<td bgColor="#ffffff"><select name="jobName"
					id="selectJobNameId">
						<option value="0">请选择</option>
				</select></td>
				<td bgColor="#ffffff"><select name="salary" id="selectSalaryId">
						<option value="--">请选择</option>
						<option value="3000">3000</option>
						<option value="4000">3000</option>
						<option value="5000">5000</option>
						<option value="6000">5000</option>
						<option value="7000">5000</option>
				</select></td>
				<td bgColor="#ffffff"><input type="text" value="" name="number" />
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" id="add_submit" value="确定" class="button_ok">
						&#30830;&#23450;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<INPUT class="button_ok" type="button" onclick="history.go(-1)"
					value="返回" /> <span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>