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
			$("#tcontent").blur(function(){
				var length = $("#tcontent").val().length;
				if(length == 0){
					$("#contentErrorMsg").text("培训内容不能为空");
				}else{
					$("#contentErrorMsg").text("");
				}
				});
			$("#taddress").blur(function(){
				var length = $("#taddress").val().length;
				if(length == 0){
					$("#addressErrorMsg").text("培训地点不能为空");
				}else{
					$("#addressErrorMsg").text("");
				}
				});
				$("#add_submit").click(function(){
					if(($("#tcontent").val().length) != 0 && $("#taddress").val().length != 0){
						$("#addTrainMsg").submit();
					}
				})
			})
        </script>
</HEAD>
<body>
	<form id="addTrainMsg"
		action="${pageContext.request.contextPath}/saveTrainMsg" method="post">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>添加培训信息</STRONG> </strong></td>
			</tr>

			<tr>
				<td bgColor="#f5fafe">培训时间:</td>
				<td bgColor="#f5fafe">培训内容:</td>
				<td bgColor="#f5fafe">培训地点:</td>
				<td bgColor="#f5fafe">培训人员:</td>
				<td bgColor="#f5fafe">选择培训官:</td>
			</tr>
			<tr>
				<td bgColor="#ffffff"><input type="datetime-local" value=""
					name="ttime" id="ttime" /></td>
				<td bgColor="#ffffff"><input type="text" value=""
					name="tcontent" id="tcontent" /> <font id="contentErrorMsg"
					color="red"></font></td>
				<td bgColor="#ffffff"><input type="text" value=""
					name="taddress" id="taddress" /> <font id="addressErrorMsg"
					color="red"></font></td>
				<td bgColor="#ffffff"><select name="trainEmplName">
						<c:forEach items="${session_listEmplName}" var="emplName">
							<option value="${emplName.eid}">${emplName.ename}</option>
						</c:forEach>
				</select></td>
				<td bgColor="#ffffff">
					<!-- 培训官的名字 --> <select name="trainBossName">
						<c:forEach items="${session_listEmplName}" var="emplName">
							<option value="${emplName.eid}">${emplName.ename}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="button" id="add_submit" value="确定" class="button_ok">
						&#30830;&#23450;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<INPUT class="button_ok" type="button" onclick="history.go(-1)"
					value="返回" /> <span id="Label1"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>