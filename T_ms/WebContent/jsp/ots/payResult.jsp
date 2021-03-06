<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	
	</script>
	<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css" rel="stylesheet" type="text/css" />
<body>
<jsp:include  page="head.jsp"/> 
<div id="main">
	<div id="left">
		<jsp:include page="left.jsp"/>
	</div>
	<div id="med">
	<!-- 主要部分 -->
	<p><h3>&nbsp;&nbsp;支付状态：</h3></p><hr>
		<c:choose>
			<c:when test="${msg == 'success' }">
				<p>&nbsp;&nbsp;订单编号：${ordernum }</p>
				<p style="color: red;">&nbsp;&nbsp;您已支付成功！</p>
			</c:when>
			<c:when test="${msg == 'buzu' }">
				<p style="color: red;">&nbsp;&nbsp;账户余额不足，支付失败！请【<a href="${pageContext.request.contextPath}/user/findByUserid.do?userid=${user.id}">充值</a>】</p>
			</c:when>
			<c:otherwise>
				<p style="color: red;">&nbsp;&nbsp;支付失败</p>
			</c:otherwise>
		</c:choose>
	<!-- 主要部分结束 -->
	</div>
	<div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 
</body>
</html>