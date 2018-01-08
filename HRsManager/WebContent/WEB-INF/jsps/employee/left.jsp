<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左边菜单界面</title>
<style type="text/css">
#left_div_id {
	width: 290px;
	height: 500px;
	background-color: rgb(230, 230, 230);
	border: 1px solid red;
}

.myImg {
	padding-left: 30px;
	padding-top: 30px;
}

a {
	text-decoration: none
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$(".msgOne").mouseover(function() {
			$(".textMsgOne").css("color", "red");
		})
		$(".msgOne").mouseout(function() {
			$(".textMsgOne").css("color", "blue");
		});
		$(".msgTwo").mouseover(function() {
			$(".textMsgTwo").css("color", "red");
		})
		$(".msgTwo").mouseout(function() {
			$(".textMsgTwo").css("color", "blue");
		});
		$(".msgThree").mouseover(function() {
			$(".textMsgThree").css("color", "red");
		})
		$(".msgThree").mouseout(function() {
			$(".textMsgThree").css("color", "blue");
		});
		$(".msgFour").mouseover(function() {
			$(".textMsgFour").css("color", "red");
		})
		$(".msgFour").mouseout(function() {
			$(".textMsgFour").css("color", "blue");
		});
		$(".msgFive").mouseover(function() {
			$(".textMsgFive").css("color", "red");
		})
		$(".msgFive").mouseout(function() {
			$(".textMsgFive").css("color", "blue");
		});
		$(".msgSix").mouseover(function() {
			$(".textMsgSix").css("color", "red");
		})
		$(".msgSix").mouseout(function() {
			$(".textMsgSix").css("color", "blue");
		});
	})
</script>
</head>
<body>
	<div id="left_div_id">
		<div id="one" class="myImg">
			<img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/grzx.png"> <span
				class="msgOne"><a
				href="${pageContext.request.contextPath}/loadMyMsgList"
				target="mainFrame" class="textMsgOne">个人信息</a></span>
		</div>
		<div id="two" class="myImg">
			<img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/deptjob.png"> <span
				class="msgTwo"><a
				href="${pageContext.request.contextPath}/deptJobEmployeeMsg"
				class="textMsgTwo" target="mainFrame">部门职位信息</a></span>
		</div>
		<div id="three" class="myImg">
			<img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/peixun.png"> <span
				class="msgThree"><a
				href="${pageContext.request.contextPath}/trainMsg"
				class="textMsgThree" target="mainFrame">培训信息</a></span>
		</div>
		<div id="four" class="myImg">
			<img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/repe.png"> <span
				class="msgFour"><a
				href="${pageContext.request.contextPath}/repeMsg"
				class="textMsgFour" target="mainFrame">奖惩信息</a></span>
		</div>
		<div id="five" class="myImg">
			<img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/daka.png"> <span
				class="msgFive"><a
				href="${pageContext.request.contextPath}/signInMsg"
				class="textMsgFive" target="mainFrame">考勤打卡</a></span>
		</div>
		<div id="six" class="myImg">
			<img alt="图片加载异常"
				src="${pageContext.request.contextPath}/images/salary.png"> <span
				class="msgSix"><a
				href="${pageContext.request.contextPath}/lookMySal"
				class="textMsgSix" target="mainFrame">薪资信息</a></span>
		</div>
	</div>
</body>
</html>