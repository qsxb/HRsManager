<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
</script>
</HEAD>
<body>
	<form action="${pageContext.request.contextPath}/saveJxMsg"
		method="post">
		&nbsp; <input type="hidden" value="${key_eid_msg}" name="eid" />
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>添加绩效信息</STRONG> </strong></td>
			</tr>

			<tr>
				<td bgColor="#f5fafe">金额:</td>
			</tr>
			<tr>
				<td bgColor="#ffffff"><input type="text" value="" name="jxsal"
					id="jxsal" /></td>
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