<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>奖惩信息列表</title>
<script type="text/javascript">
	function re() {
		window.location.href = "${pageContext.request.contextPath}/adminAddRe";
	}
	function pe() {
		window.location.href = "${pageContext.request.contextPath}/adminAddPe";
	}
</script>
</head>
<body>
	<div>${key_msg}</div>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/##" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>对奖惩的增删改查</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" onclick="re()">奖励</button>
						<button type="button" onclick="pe()">惩罚</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: lightblue">

								<td>奖惩信息ID</td>
								<td>奖励时间</td>
								<td>奖励金额</td>
								<td>奖励原因</td>
								<td>惩罚时间</td>
								<td>惩罚金额</td>
								<td>惩罚原因</td>
								<td>员工姓名</td>
								<td>删除</td>
								<td>修改</td>
							</tr>
							<c:forEach items="${key_repeMsg_list}" var="list">
								<tr>
									<td>${list.rpid}</td>
									<td>${list.retime}</td>
									<td>${list.resal}</td>
									<td>${list.rereason}</td>
									<td>${list.petime}</td>
									<td>${list.pesal}</td>
									<td>${list.pereason}</td>
									<td>${list.employee.ename}</td>
									<td><a
										href="${pageContext.request.contextPath}/delRepeMsg?id=${list.rpid}"><img
											alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/i_del.jpg"></a></td>
									<td><a
										href="${pageContext.request.contextPath}/modifyRepeMsg?id=${list.rpid}"><img
											alt="图片加载异常"
											src="${pageContext.request.contextPath}/images/i_edit.jpg"></a></td>
								</tr>
							</c:forEach>
						</table> 第${key_repeMsg_pb.page}/${key_repeMsg_pb.totalPage}页 <c:if
							test="${key_repeMsg_pb.page != 1}">
							<a href="${pageContext.request.contextPath}/loadRepeList?page=1">首页</a>
							<a
								href="${pageContext.request.contextPath}/loadRepeList?page=${key_repeMsg_pb.page-1}">上一页</a>
						</c:if> <c:forEach end="${key_repeMsg_pb.totalPage}" begin="1" var="i">
							<a
								href="${pageContext.request.contextPath}/loadRepeList?page=${i}">[${i}]</a>
						</c:forEach> <c:if test="${key_repeMsg_pb.page != key_repeMsg_pb.totalPage}">
							<a
								href="${pageContext.request.contextPath}/loadRepeList?page=${key_repeMsg_pb.page+1}">下一页</a>
							<a
								href="${pageContext.request.contextPath}/loadRepeList?page=${key_repeMsg_pb.totalPage}">尾页</a>
						</c:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>