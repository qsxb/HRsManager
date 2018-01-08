<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左边菜单界面</title>
<style type="text/css">
#left_div_id{width: 300px;height: 700px;background-color:rgb(230,230,230);border: 1px solid red;}
 .myImg{padding-left: 30px;padding-top: 30px;} 
</style>
</head>
<body>
<div id="left_div_id">
<div id="one" class="myImg">
<img alt="图片加载异常" src="${pageContext.request.contextPath}/images/grzx.png">
<span class="msg">个人信息</span>
</div>
<div id="two" class="myImg">
<img alt="图片加载异常" src="${pageContext.request.contextPath}/images/deptjob.png">
<span class="msg">部门职位信息</span>
</div>
<div id="three" class="myImg">
<img alt="图片加载异常" src="${pageContext.request.contextPath}/images/peixun.png">
<span class="msg">培训信息</span>
</div>
<div id="four" class="myImg">
<img alt="图片加载异常" src="${pageContext.request.contextPath}/images/repe.png">
<span class="msg">奖惩信息</span>
</div>
<div id="five" class="myImg">
<img alt="图片加载异常" src="${pageContext.request.contextPath}/images/daka.png">
<span class="msg">考勤打卡</span>
</div>
<div id="six" class="myImg">
<img alt="图片加载异常" src="${pageContext.request.contextPath}/images/salary.png">
<span class="msg">薪资信息</span>
</div>
</div>
</body>
</html>