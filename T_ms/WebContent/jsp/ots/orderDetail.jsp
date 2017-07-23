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
<style>
 tr th{
 	text-align: center;
 	width: 100px;
 }
  tr td{
 	text-align: center;
 		width: 100px;
 }
 
 .titel{
 	font-family: 微软雅黑;
 	font-size: 30px;
 }
</style>
<body>
<jsp:include  page="head.jsp"/> 
<div id="main">
	<div id="left">
		<jsp:include page="left.jsp"/>
	</div>
	<div id="med">
	<p class="titel" align="center">订单明细</p>
	<!-- 主要部分 -->
		<table align="center">
		<tr><th>菜名</th><th>类型</th><th>单价（￥）</th><th>数量（份）</th></tr>
		<c:forEach items="${list}" var="orderDetails">
		<tr><td>${orderDetails.grename}</td><td>${orderDetails.type}</td><td>${orderDetails.oriprice}</td><td>${orderDetails.count}</td></tr>
		</c:forEach>
		
		</table>
		<p align="center">总价：${one.totalprice }</p>
		<p align="center">联系人：${one.name }</p>
		<p align="center">电话：${one.phonenum }</p>
		<p align="center">时间：${one.time }</p>
		<p align="center">地址：${one.address }</p>
	<!-- 主要部分结束 -->
	</div>
	<div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 
</body>
</html>