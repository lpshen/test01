<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加到购物车</title>
</head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	function buy(){//在之前检查用户登录状态
		var menuid = $('#menuid').val();
		alert('buy'+menuid);
	}
	function addCart(){//在之前检查用户登录状态
		var menuid = $('#menuid').val();
		alert('addCart'+menuid);
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/cart/insert.do",
			data:{
			menuid:$('#menuid').val(),
		    userid:$('#userid').val(),
		    good:$('#good').val(),
		    pictureurl:$('#pictureurl').val(),
		    oriprice:$('#oriprice').val(),
		    curprice:$('#curprice').val()
			},
			dataType:"json",
			success:function(data){}
		});
	}
	function add(){
		var count =parseInt($('#count').val());
		if(count < 100){
			count += 1;
			$('#count').val(count);
		}else{
			alert('数值过大!');
		}
	}
	function sub(){
		var count =parseInt($('#count').val());
		if(count > 1){
			count -= 1;
			$('#count').val(count);
		}
	}
	
	</script>
	<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css" rel="stylesheet" type="text/css" />
<body>
<jsp:include  page="head.jsp"/> 
<div id="main">
	<div id="left">
		<jsp:include page="left.jsp"/>
	</div>
	<div id="med">med
	<!-- 主要部分 -->

		<c:choose>
			<c:when test="${msg == 'error' }">
				error
			</c:when>
			<c:otherwise>
				<input id="menuid" type="hidden" value="${oneFoodModel.menuid }">
				<input id="merid" type="hidden" value="${oneFoodModel.merid }">
				<input id="userid" type="hidden" value="${user.id}"> 
				<input id="good" type="hidden" value="${oneFoodModel.grename }"> 
				<input id="pictureurl" type="hidden" value="${oneFoodModel.pictureurl }"> 
				<input id="oriprice" type="hidden" value="${oneFoodModel.oriprice }"> 
				<input id="curprice" type="hidden" value="${oneFoodModel.curprice }"> 
				<p>名称：${oneFoodModel.grename }</p>
				<p>URL：${oneFoodModel.pictureurl }</p>
				<p>原价：<s>${oneFoodModel.oriprice }</s>￥</p>
				<p>现价：${oneFoodModel.curprice }￥</p>
				<p>店铺：${oneFoodModel.shopname }</p>
				<p>	
					<input id= "add" type="button" value="+" onclick = "add()">
					<input id ="count" style= "width:20px;" type="text" value="1">
					<input id ="sub" type="button" value="-" onclick = "sub()">
				</p>  
				<button onclick="buy()">立即下单</button><button onclick="addCart()">加入购物车</button>
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