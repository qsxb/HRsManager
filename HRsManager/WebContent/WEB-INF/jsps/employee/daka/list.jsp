<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日常打卡操作</title>
<style type="text/css">
#div_id {
	width: 600px;
	height: 500px;
}

.sign_img_in {
	margin-left: 100px;
	margin-top: 100px;
}

.sign_img_out {
	margin-left: 300px;
	margin-top: 50px;
}

.div_class {
	float: left;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#sign_img_in").click(function() {
			//点击上班打卡跳转后台
			window.location.href = "${pageContext.request.contextPath}/signIn";
		});
		$("#sign_img_out")
				.click(
						function() {
							//点击下班打卡跳转后台,规定只能先打上班卡 才能再打下班卡
							var inputData = $("#signIn").val();
							if (inputData != "") {
								window.location.href = "${pageContext.request.contextPath}/signOut";
							}
						});
	})
</script>
</head>
<body>
	<c:if test="${empty session_signIn && empty session_signOut}">
		<!-- 没有打卡显示的界面 -->
		<div id="div_id">
			<input type="hidden" value="${session_signIn}" id="signIn" />
			<div id="sign_in" class="div_class">
				<img id="sign_img_in" class="sign_img_in" alt="图片加载异常"
					src="${pageContext.request.contextPath}/images/morning2.png">
				<span>上班打卡</span>
			</div>
			<div id="sign_out" class="div_class">
				<img id="sign_img_out" class="sign_img_out" alt="图片加载异常"
					src="${pageContext.request.contextPath}/images/evening2.png">
				<span>下班打卡</span>
			</div>
		</div>
	</c:if>
	<!-- 打了上班卡显示的界面 -->
	<c:if test="${!empty session_signIn && empty session_signOut}">
		<div>${key_msg}</div>
		<div id="div_id">
			<div id="sign_in" class="div_class">
				<img alt="图片加载异常" class="sign_img_in"
					src="${pageContext.request.contextPath}/images/morning1.png">
				<span>上班打卡</span>
			</div>
			<div id="sign_out" class="div_class">
				<img id="sign_img_out" class="sign_img_out" alt="图片加载异常"
					src="${pageContext.request.contextPath}/images/evening2.png">
				<span>下班打卡</span>
			</div>
		</div>
	</c:if>
	<!-- 打了下班卡的界面 -->
	<c:if test="${!empty session_signOut }">
		<div>${key_msg}</div>
		<div id="div_id">
			<div id="sign_in" class="div_class">
				<img alt="图片加载异常" class="sign_img_in"
					src="${pageContext.request.contextPath}/images/morning1.png">
				<span>上班打卡</span>
			</div>
			<div id="sign_out" class="div_class">
				<img alt="图片加载异常" class="sign_img_out"
					src="${pageContext.request.contextPath}/images/evening1.png">
				<span>下班打卡</span>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty session_signOut && ! empty session_signIn}">
		<div>${key_msg}</div>
		<div id="div_id">
			<div id="sign_in" class="div_class">
				<img id="sign_img_in" class="sign_img_in" alt="图片加载异常"
					src="${pageContext.request.contextPath}/images/morning2.png">
				<span>上班打卡</span>
			</div>
			<div id="sign_out" class="div_class">
				<img id="sign_img_out" class="sign_img_out" alt="图片加载异常"
					src="${pageContext.request.contextPath}/images/evening2.png">
				<span>下班打卡</span>
			</div>
		</div>
	</c:if>
</body>
</html>