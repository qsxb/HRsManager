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
	$("#resal").blur(function(){
		var length = $("#resal").val().length;
		if(length == 0){
			$("#errorSalMsg").text("奖励金额不能为空");
		}else{
			$("#errorSalMsg").text("");
		}
		});
	$("#rereason").blur(function(){
		var length = $("#rereason").val().length;
		if(length == 0){
			$("#errorReasonMsg").text("奖励原因不能为空");
		}else{
			$("#errorReasonMsg").text("");
		}
		});
		$("#add_submit").click(function(){
			if(($("#resal").val().length) != 0 || ($("#rereason").val().length) != 0){
				$("#addReMsg").submit();
			}
		})
	})
	</script>
</HEAD>
<body>
	<form id="addReMsg" name="Form1"
		action="${pageContext.request.contextPath}/saveReMsg" method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="10"
					height="26"><strong><STRONG>添加奖励信息</STRONG> </strong></td>
			</tr>

			<tr>
				<td bgColor="#f5fafe">奖励时间:</td>
				<td bgColor="#ffffff"><input type="datetime-local"
					name="retime" value="" id="retime" /></td>
				<td bgColor="#f5fafe">奖励金额:</td>
				<td bgColor="#ffffff"><input type="text" name="resal" value=""
					id="resal" /></td>
				<td bgColor="#f5fafe"><font id="errorSalMsg" color="red"></font>
				</td>
				<td bgColor="#f5fafe">奖励原因:</td>
				<td bgColor="#ffffff"><input type="text" name="rereason"
					value="" id="rereason" /></td>
				<td bgColor="#f5fafe"><font id="errorReasonMsg" color="red"></font>
				</td>
				<td bgColor="#f5fafe">奖励对象:</td>
				<td bgColor="#ffffff"><select name="chooseEmplName">
						<c:forEach items="${key_employee_list}" var="employee">
							<option value="${employee.eid}">${employee.ename}</option>
						</c:forEach>
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