<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function(){
	$("#deptname").blur(function(){
		var length = $("#deptname").val().length;
		if(length == 0){
			$("#errorMsg").text("部门名不能为空");
		}else{
			$("#errorMsg").text("");
		}
		});
		$("#add_submit").click(function(){
			if(($("#deptname").val().length) != 0){
				$("#addJobName").submit();
			}
		})
	})
	</script>
</HEAD>

<body>
	<form id="addJobName" name="Form1"
		action="${pageContext.request.contextPath}/updateDeptName?id=${key_dept_msg.did}"
		method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>修改部门名称</STRONG> </strong></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					部门名称：</td>
				<td width="18%" bgColor="#ffffff"><input type="text"
					name="deptname" value="${key_dept_msg.dname}" id="deptname" /></td>
				<td width="18%" bgColor="#f5fafe"><font id="errorMsg"
					color="red"></font></td>
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