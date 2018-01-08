<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请职位填写简历页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    /* 填写简历阶段的前端验证  */
    /*验证年龄 */
    $(function(){
    	$("#age").blur(function(){
    		if($("#age").val().length == 0){
    			$("#age_error_msg").text("年龄不能为空");
    		}if($("#age").val().length != 0){
    			if($("#age").val()>0 && $("#age").val()<=100){
    				$("#age_error_msg").text("");
    			}else{
    				$("#age_error_msg").text("年龄输入格式不正确(0-100)");
    			}
    		}
    	})
    	/*验证联系方式 */
    	$("#tel").blur(function(){
    	var phone = $("#tel").val();
          if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(phone))){
        	  $("#tel_error_msg").text("手机号格式不正确!");
          }else{
        	  $("#tel_error_msg").text(""); 
          }
    	});
    	/*验证邮箱  */
    	$("#emailshow").blur(function(){
    		var email = $("#emailshow").val();
    		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
    		if(reg.test(email) == true){
    			$("#email_error_msg").text("");
    		}
    		if(reg.test(email) == false){
    			$("#email_error_msg").text("邮箱格式不正确!");
    		}
    	});
    	/*表单提交前台验证  */
    	$("#submit_id").click(function(){
    		if($("#age").val().length == 0 || !(/^1[3|4|5|8][0-9]\d{4,8}$/.test($("#tel").val())) || !(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test($("#emailshow").val()))){
    		} else{
    			$("#writeResumeForm").submit();
    		}
    	});
    });
</script>
<style type="text/css">
body {
	background-image:
		url(${pageContext.request.contextPath}/images/shanghai.jpg);
}
</style>
</head>
<body>
	当前用户${userName_in_session},欢迎你
	<hr>
	当前时间
	<fmt:formatDate value="<%= new Date()%>" pattern="yyyy-MM-dd HH:mm" />
	<br />
	<br />
	<form action="saveResume" method="post" id="writeResumeForm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<th width="25%"><span>*</span><font id="Lusername">姓名</font></th>
				<td colspan="3"><input type="text" name="username"
					value="${userName_in_session}" id="username" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th width="20%"><span>*</span><font id="Lusername">年龄</font></th>
				<td colspan="3"><input type="text" name="age" value="" id="age" />
				</td>
				<!-- 验证回显信息 -->
				<td><font color="red" id="age_error_msg"> </font></td>
			</tr>
			<tr>
				<th width="20%"><span>*</span><font id="Lgender">性别</font></th>
				<td colspan="3"><input type="radio" name="gender" value="男"
					checked="checked" />男 <input type="radio" name="gender" value="女" />女</td>
			</tr>
			<!-- <tr>
			<th><span>*</span><font id="Lbirth_date">出生日期</font></th>
			<td colspan="3">
			<input type="text" name="birthday" id="birthday" value=""></td>
		</tr> -->
			<tr>
				<th><span>*</span><font id="LresidenceF_button">学历</font></th>
				<td colspan="3"><select name="education">
						<option selected="selected" value="本科">本科</option>
						<option value="博士">博士</option>
						<option value="硕士">硕士</option>
						<option value="大专">大专</option>
						<option value="其他">其他</option>
				</select></td>
			</tr>
			<tr>
				<th><span>*</span><font id="Lcontact_num0">联系方式</font></th>
				<td colspan="3"><input type="text" maxlength="11" value=""
					name="tel" id="tel" /></td>
				<!-- 验证回显信息 -->
				<td><font color="red" id="tel_error_msg"> </font></td>
			</tr>

			<tr>
				<th><span>*</span><font id="Lemail">电子邮箱</font></th>
				<td colspan="3"><input type="text" id="emailshow" name="email"
					value="" /></td>
				<!-- 验证回显信息 -->
				<td><font color="red" id="email_error_msg"> </font></td>
			</tr>
			<tr>
				<th><span class="m_left5"></span><font id="Font2">政治面貌</font></th>
				<td colspan="3"><select name="polstatus">
						<option selected="selected" value="共青团员">共青团员</option>
						<option value="中共党员">中共党员</option>
						<option value="群众">群众</option>
				</select></td>
			</tr>
			<tr>
				<th><span class="m_left5"></span><font id="Font2">应聘部门</font></th>
				<td colspan="3"><input type="text"
					value="${key_advertise.adeptName}" name="deptName"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<th><span class="m_left5"></span><font id="Font2">应聘职位</font></th>
				<td colspan="3"><input type="text"
					value="${key_advertise.ajobName}" name="jobName"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<th><span class="m_left5"></span><font id="Font2">应聘薪资</font></th>
				<td colspan="3"><input type="text"
					value="${key_advertise.asalary}" name="rsalary" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th><input type="button" value="保存" id="submit_id"></th>
				<td><input type="button" value="返回" onclick="history.go(-1)" /></td>
			</tr>
		</table>
	</form>
</body>
</html>