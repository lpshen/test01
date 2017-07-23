<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<style>
#med p, #med a {
	margin: 10px;
	font-family: 微软雅黑;
	font-size: 15px;
}
#userInfo{
    height: 300px;
    width: 360px;
    margin: 13px auto;
    padding-top: 4px;
    background-color: white;
}

#med{
    background-image:url(${pageContext.request.contextPath}/image/yhxx.png ); width: 360px;
    background-repeat: no-repeat;
    background-size:101%; 
}

#med a {
	color: blue;
}

.inter{
 margin: 20px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		if ($("#userid").val() == "") {
			alert("还没有登录！");
			window.location.href = "${pageContext.request.contextPath}/jsp/ots/login.jsp";
		}
	})
	function pay() {
		var temp = document.createElement("form");
		temp.action = "${pageContext.request.contextPath}/user/pay.do";
		//创建from表单及其子元素  
		var form = $("<form></form>");
		//设置form的属性值  
		form.attr("action", "${pageContext.request.contextPath}/user/pay.do");
		form.attr("method", "post");
		//创建一个文本框  
		var input_text1 = $("<input type='text' id='userid' name='userid' />");
		input_text1.attr("value", $("#userid").val());
		var input_text2 = $("<input type='text' id='ordernum' name='ordernum' />");
		input_text2.attr("value", $("#ordernum").val());
		var input_text3 = $("<input type='text' id='totalprice' name='totalprice' />");
		input_text3.attr("value", $("#totalprice").val());
		form.append(input_text1);
		form.append(input_text2);
		form.append(input_text3);

		//提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		form.appendTo(document.body).submit();
		return false;

	}
</script>
<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css"
	rel="stylesheet" type="text/css" />
<body>
	<jsp:include page="head.jsp" />
	<div id="main">
		<div id="left">
			<jsp:include page="left.jsp" />
		</div>
		<div id="med">
			<div id="userInfo">
		<p align="center" style="font-size: 30px;">用户信息</p><br>
				<div style="margin-left: 10px;">
				<div><p style="position: re lative;background-color: #CCC0BE;border: 3px solid #E5DAD8;">
					用户名：${userModel.username}&nbsp;<a class="inter" style="left:200px;top:-10px; margin-left: 107px;"
						href="${pageContext.request.contextPath}/user/findByUserid1.do?userid=${user.id}">修改</a>
				</p></div>
				<div><p style="position: relative;background-color: #CCC0BE;border: 3px solid #E5DAD8;">
					积分：${userModel.credits} &nbsp;<a class="inter" style="position: absolute;left:180px;top:-10px;"
						href="${pageContext.request.contextPath}/jsp/ots/JF.jsp">积分规则</a>
				</div>
				<div><p style="background-color: #CCC0BE;border: 3px solid #E5DAD8;">电话：${userModel.phonenum}</p></div>
				<div><p style="background-color: #CCC0BE;border: 3px solid #E5DAD8;">邮箱：${userModel.email}</p></div>
				<div><p style="position: relative;background-color: #CCC0BE;border: 3px solid #E5DAD8;">
					余额：${userModel.money}￥&nbsp;<a class="inter" style="position: absolute;left:200px;top:-10px; margin-left: -9px;"
						href="${pageContext.request.contextPath}/jsp/ots/recharge.jsp">去充值</a>
				</p></div>
				
				<!-- 主要部分 -->
				<input id="userid" type="hidden" value="${user.id}">
				<p>
					<a
						href="${pageContext.request.contextPath}/recharge/listByPageCZ.do?userid=${user.id}">充值记录</a>
					<a
						href="${pageContext.request.contextPath}/recharge/listByPageZF.do?userid=${user.id}">支付记录</a>
					<a
						href="${pageContext.request.contextPath}/jsp/ots/updatePassword.jsp">修改密码</a>
				</p>
				</div>
				<!-- 主要部分结束 -->
				<div></div>
				<div>
					
				</div>
			</div>
		</div>
		<div id="right">
			<jsp:include page="right.jsp" />
		</div>
	</div>
	<jsp:include page="bottom.jsp" />
</body>
</html>