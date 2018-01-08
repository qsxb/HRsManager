<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台主页</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		var whenregist = "";
		var whenlogin = "";
		/* 异步加载新用户注册界面 */
		$(document)
				.on(
						"click",
						"#a_regist_id",
						function() {
							/* $("#a_regist_id").click(function(){  */
							whenregist = "<form action='${pageContext.request.contextPath}/nowRegist' method='post' id='registForm'>"
									+ "<table id='middle_right_table'>"
									+ "<thead>"
									+ "<tr id='middle_right_table_title'>"
									+ "<td><font color='lightblue' size='5px'><span id='a_regist_id' class='a_span_id'>新用户注册</span></font></td>"
									+ "<td><font color='lightblue' size='5px'><span id='a_login_id' class='a_span_id'>老用户登录</span></font></td>"
									+ "</tr>"
									+ "</thead>"
									+ "<tbody id='tbody_id'>"
									+ "<tr>"
									+ "<td colspan='2'><img alt='图片加载异常' src='${pageContext.request.contextPath}/images/10s.jpg'> </td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<input type='text' value='' name='registName' id='registName' placeholder='请输入用户名' class='input_class' maxlength='15'/>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<font color='red' id='registNameErrorMsg'>"
									+ "<c:if test='${! empty key_existUserError}'>"
									+ "${key_existUserError}"
									+ "</c:if >"
									+ "</font>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<input type='text' value='' name='email' id='email' placeholder='请输入邮箱' class='input_class' maxlength='20 />"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<font color='red' id='emailErrorMsg'>"
									+ "</font>"
									+ "</td>"
									+ "</tr>"
									+ "<td colspan='2'>"
									+ "<input type='password' value='' name='registPwd' id='registPwd' placeholder='请输入密码' class='input_class' maxlength='15'/>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<font color='red' id='registPwdErrorMsg'>"
									+ "</font>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<input type='checkbox' value='' id='checkBox'/><font color='gray'>我接受本网站的招聘的用户协议</font>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'> <img alt='图片加载异常' src='${pageContext.request.contextPath}/images/nowRegist.jpg' id='img_regist_submit'></td>"
									+ "</tr>"
									+ "</tbody>"
									+ "</table>"
									+ "</form>";
							$("#middle_right_div").html(whenregist);
						});
		/* 异步加载老用户登录部分 */
		$(document)
				.on(
						"click",
						"#a_login_id",
						function() {
							/* $("#a_login_id").click(function(){ */
							whenlogin = "<form action='${pageContext.request.contextPath}/nowLogin' method='post' id='loginForm'>"
									+ "<table id='middle_right_table'>"
									+ "<thead>"
									+ "<tr id='middle_right_table_title'>"
									+ "<td><font color='lightblue' size='5px'><span id='a_regist_id' class='a_span_id'>新用户注册</span></font></td>"
									+ "<td><font color='lightblue' size='5px'><span id='a_login_id' class='a_span_id'>老用户登录</span></font></td>"
									+ "</tr>"
									+ "</thead>"
									+ "<tbody id='tbody_id'>"
									+ "<tr>"
									+ "<td colspan='2'><img alt='图片加载异常' src='${pageContext.request.contextPath}/images/10s1.jpg'> </td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<input type='text' value='' name='loginName' id='loginName' placeholder='请输入用户名' class='input_class' maxlength='15'/>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<font color='red' id='loginNameErrorMsg'>"
									+ "</font>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<input type='password' value='' name='loginPwd' id='loginPwd' placeholder='请输入密码' class='input_class' maxlength='15'/>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'>"
									+ "<font color='red' id='loginPwdErrorMsg'>"
									+ "</font>"
									+ "</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td >"
									+ "<input type='checkbox' value=''/><font color='gray'>自动登录</font>"
									+ "</td>"
									+ "<td><span>忘记密码?</span></td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='2'> <img alt='图片加载异常' src='${pageContext.request.contextPath}/images/nowlogin.jpg' id='img_login_submit'></td>"
									+ "</tr>"
									+ "</tbody>"
									+ "</table>"
									+ "</form>";
							$("#middle_right_div").html(whenlogin);
						});
		$(document).on("mouseover", ".a_span_id", function() {
			$(this).css("color", "rgb(1,77,163)")
		});
		$(document).on("mouseout", "a_span_id", function() {
			$(this).css("color", "rgb(1,77,163)");
		});
		/* 前段验证注册用户名部分 */
		$(document).on("blur", "#registName", function() {
			if ($("#registName").val().length == 0) {
				$("#registNameErrorMsg").text("注册用户名不能为空!");
			} else {
				$("#registNameErrorMsg").text("");
			}
		});
		/* 前段验证注册密码部分 */
		$(document).on("blur", "#registPwd", function() {
			if ($("#registPwd").val().length == 0) {
				$("#registPwdErrorMsg").text("注册密码不能为空");
			} else {
				$("#registPwdErrorMsg").text("");
			}
		})
		/*点击勾选我同意注册部分*/
		$(document).on("click", "#checkBox", function() {
			if ($('#checkBox').attr('checked')) {
			}
		});

		/* 点击立即注册图片所做的操作 */
		$(document).on(
				"click",
				"#img_regist_submit",
				function() {
					if ($("#registPwd").val().length != 0
							&& $("#registPwd").val().length != 0) {
						if ($('#checkBox').attr('checked')) {
							$("#registForm").submit();
						}

					}
				});
		/* 前端登录部分用户名的验证  */
		$(document).on("blur", "#loginName", function() {
			if ($("#loginName").val().length == 0) {
				$("#loginNameErrorMsg").text("登录用户名不能为空!");
			} else {
				$("#loginNameErrorMsg").text("");
			}
		});
		/* 前端登录部分密码的验证 */
		$(document).on("blur", "#loginPwd", function() {
			if ($("#loginPwd").val().length == 0) {
				$("#loginPwdErrorMsg").text("登录密码不能为空!");
			} else {
				$("#loginPwdErrorMsg").text("");
			}
		});
		/* 前端点击登录图片跳转的操作 */
		$(document).on(
				"click",
				"#img_login_submit",
				function() {
					if ($("#loginName").val().length != 0
							|| $("#loginPwd").val().length != 0) {
						alert("哈哈");
						$("#loginForm").submit();
					}
				});
		/*前端4个小标题变色*/
		$(".title_class1").mouseover(function() {
			$(".title_class1").css("color", "yellow");
		});
		$(".title_class1").mouseout(function() {
			$(".title_class1").css("color", "white");
		});
		$(".title_class2").mouseover(function() {
			$(".title_class2").css("color", "yellow");
		});
		$(".title_class2").mouseout(function() {
			$(".title_class2").css("color", "white");
		});
		$(".title_class3").mouseover(function() {
			$(".title_class3").css("color", "yellow");
		});
		$(".title_class3").mouseout(function() {
			$(".title_class3").css("color", "white");
		});
		$(".title_class4").mouseover(function() {
			$(".title_class4").css("color", "yellow");
		});
		$(".title_class4").mouseout(function() {
			$(".title_class4").css("color", "white");
		});
		//鼠标移入管理员登录颜色变色
		$("#adminLogin_id").mouseover(function() {
			$("#adminLogin_id").css("color", "white");
		});
		$("#adminLogin_id").mouseout(function() {
			$("#adminLogin_id").css("color", "yellow");
		});
		//鼠标移入员工登录颜色变色
		$("#employeeLogin_id").mouseover(function() {
			$("#employeeLogin_id").css("color", "white");
		})
		$("#employeeLogin_id").mouseout(function() {
			$("#employeeLogin_id").css("color", "yellow");
		})
	});
</script>
<style type="text/css">
body {
	/* background-image:
		url(${pageContext.request.contextPath}/images/shanghai.jpg); */
	
}

#top_div {
	border: 1px solid red;
	width: 100%;
	height: 200px;
}

#middle_div {
	border: 1px solid red;
	width: 100%;
	height: 450px;
	float: left;
	background-image:
		url(${pageContext.request.contextPath}/images/shanghai.jpg);
}

#middle_left_div {
	border: 1px solid red;
	width: 60%;
	height: 450px;
	float: left;
}

#middle_right_div {
	border: 1px solid red;
	width: 39%;
	height: 450px;
	float: left;
}

#bottom_div {
	border: 1px solid green;
	width: 100%;
	height: 250px;
	clear: both;
	background: white;
}

#top_div01 {
	width: 100%;
	height: 80px;
	background: rgb(1, 77, 163);
}

#top_div02 {
	width: 100%;
	height: 120px;
	background: white;
}

#top_div02 span {
	margin-top: 100px;
	padding-left: 40px;
}

#table_left {
	margin-left: 150px;
	float: left;
}

#table_right {
	margin-left: 500px;
	float: left;
	padding-top: 20px;
}

#table_right tr td a {
	color: yellow;
}

#table_right tr td {
	padding-left: 20px;
	margin-top: 20px;
}

#table_left tr td {
	padding-top: 20px;
	padding-left: 20px;
}

#table_left tr td a {
	color: white;
}

#bottom_line_div {
	text-align: center;
}

#bottom_110_div {
	text-align: center;
	padding-top: 20px;
}

#bottom_4_div {
	margin-top: 20px;
	text-align: center;
}

#bottom_4_div span {
	padding-left: 50px;
}

#bottom_desc_div {
	text-align: center;
}

#middle_left_table {
	border: 1px solid black;
	width: 70%;
	height: 350px;
	margin: 0 auto;
	background: rgb(1, 77, 163);
	filter: alpha(opacity : 30);
	opacity: 0.8;
	border-radius: 15px;
}

#middle_right_table {
	border: 1px solid red;
	width: 70%;
	height: 350px;
	margin: 0 auto;
	background: white;
	border-radius: 15px;
}

#head_id {
	font-size: 20px;
	color: white;
}

#head1_id {
	font-size: 20px;
	color: white;
}

.head2_id {
	font-size: 20px;
	color: black;
}

a {
	text-decoration: none
}

.input_class {
	width: 80%;
	height: 30px;
}

#middle_right_table {
	text-align: center;
}

.a_span_id {
	cursor: pointer;
}
</style>
</head>
<body>
	<div id="top_div">
		<div id="top_div01">
			<table cellpadding="0" cellspacing="0" id="table_left">
				<tr>
					<td><a href="${pageContext.request.contextPath}/loadIndex"
						class="title_class1">首页</a></td>
					<td style="color: white">&nbsp;|&nbsp;</td>
					<td><a href="#" class="title_class2">公司简历</a></td>
					<td style="color: white">&nbsp;|&nbsp;</td>
					<td><a href="#" class="title_class3">职位搜索</a></td>
					<td style="color: white">&nbsp;|&nbsp;</td>
					<td><a href="${pageContext.request.contextPath}/intoMyMsg"
						class="title_class4">游客信息</a></td>
					<td></td>
			</table>
			<table id="table_right" cellpadding="0" cellspacing="0">
				<tr>
					<td><a href="${pageContext.request.contextPath}/adminLogin"
						class="changeColor" id="adminLogin_id">管理员登录</a></td>
					<td style="color: white">|</td>
					<td><a href="${pageContext.request.contextPath}/employeeLogin"
						class="changeColor" id="employeeLogin_id">员工登录</a></td>
				</tr>
			</table>
		</div>
		<div id="top_div02">
			<div id="top_div0201">
				<span> <img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span> <span> <img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span> <span> <img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span> <span><img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span>
			</div>
			<div id="top_div0202">
				<span> <img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span> <span> <img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span> <span> <img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span> <span><img alt="看不清,加载异常"
					src="${pageContext.request.contextPath}/images/goodjob.jpg">
				</span>
			</div>
		</div>
	</div>
	<div id="middle_div">
		<div id="middle_left_div">
			<!-- 到时候这里通过取数据库中的数据在这里循环显示出来 -->
			<table id="middle_left_table" cellpadding="0" cellspacing="0">
				<tr id="head1_id">
					<td>招聘部门</td>
					<td>招聘职位</td>
					<td>薪资待遇</td>
					<td>招聘人数</td>
				</tr>
				<c:forEach items="${key_advertiseMsg}" var="list">
					<tr id="head_id">
						<td>${list.adeptName}</td>
						<td>${list.ajobName}</td>
						<td>${list.asalary}</td>
						<td>${list.anumber}</td>
						<td><a
							href="${pageContext.request.contextPath}/applyFor?id=${list.aid}">
								<img alt="图片加载异常"
								src="${pageContext.request.contextPath}/images/wantposition.jpg">
						</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="middle_right_div">
			<form action="index.jsp" method="post" id="registForm">
				<table id="middle_right_table">
					<thead>
						<tr id="middle_right_table_title">
							<td><font color="lightblue" size="5px"><span
									id="a_regist_id" class="a_span_id">新用户注册</span></font></td>
							<td><font color="lightblue" size="5px"><span
									id="a_login_id" class="a_span_id">老用户登录</span></font></td>
						</tr>
					</thead>
					<tbody id="tbody_id">
						<tr>
							<td colspan="2"><img alt="图片加载异常"
								src="${pageContext.request.contextPath}/images/10s.jpg"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="text" value=""
								name="registName" id="registName" placeholder="请输入用户名"
								class="input_class" maxlength="15" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<!-- 显示错误信息的 --> <font color="red" id="registNameErrorMsg">
									<c:if test="${! empty key_existUserError}">
		     ${key_existUserError}
		 </c:if>

							</font>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="text" value="" name="email"
								id="email" placeholder="请输入邮箱" class="input_class"
								maxlength="20" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<!-- 显示错误信息的 --> <font color="red" id="emailErrorMsg"> </font>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="password" value=""
								name="registPwd" id="registPwd" placeholder="请输入密码"
								class="input_class" maxlength="15" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<!-- 显示错误信息的 --> <font color="red" id="registPwdErrorMsg">
							</font>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="checkbox" value=""
								name="checkBox" /><font color="gray">我接受本网站的招聘的用户协议</font></td>
						</tr>
						<tr>
							<td colspan="2"><img alt="图片加载异常"
								src="${pageContext.request.contextPath}/images/nowRegist.jpg"
								id="img_regist_submit"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div id="bottom_div">
		<div id="bottom_4_div">
			<span><img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/1.jpg"> </span> <span><img
				alt="图片加载异常" src="${pageContext.request.contextPath}/images/2.jpg">
			</span> <span><img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/3.jpg"> </span> <span><img
				alt="图片加载异常" src="${pageContext.request.contextPath}/images/4.jpg">
			</span>
		</div>
		<div id="bottom_line_div">
			<img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/line.jpg">
		</div>
		<div id="bottom_desc_div">
			<span> 本公司介绍 &nbsp;&nbsp;|&nbsp;&nbsp; 网站地址
				&nbsp;&nbsp;|&nbsp;&nbsp; 加入我们 &nbsp;&nbsp;|&nbsp;&nbsp; 法律声明
				&nbsp;&nbsp;| &nbsp;&nbsp;隐私政策 &nbsp;&nbsp;|&nbsp;&nbsp;
				联系方式&nbsp;&nbsp; |&nbsp;&nbsp; 常见问题 </span> <br />
			<br /> <span>京ICP备12025925号 电信业务审批[2001]字第233号函
				京公网安备110105000322 </span>
		</div>
		<div id="bottom_110_div">
			<span><img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/110.jpg"> </span> <span><img
				alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/1101.jpg"> </span>
		</div>
	</div>
</body>
</html>