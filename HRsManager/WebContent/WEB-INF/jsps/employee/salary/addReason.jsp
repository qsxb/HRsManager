<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写薪资异议原因界面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function(){
	$("#reason").blur(function(){
		var length = $("#reason").val().length;
		if(length == 0){
			$("#errorMsg").text("职位名不能为空");
		}else{
			$("#errorMsg").text("");
		}
		});
		$("#add_submit").click(function(){
			if(($("#reason").val().length) != 0){
				$("#addsalReason").submit();
			}
		})
	})
	</script>
</head>
<body>
	<form id="addsalReason" name="Form1"
		action="${pageContext.request.contextPath}/saveSalReviewReason?eid=${key_eid}&sid=${key_sid}"
		method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>添加薪资异议原因</STRONG> </strong></td>
			</tr>

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					原因:</td>
				<td width="18%" bgColor="#ffffff"><input type="text"
					name="reason" value="" id="reason" /></td>
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
</html>