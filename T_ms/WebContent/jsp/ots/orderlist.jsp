<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		if ($("#userid").val() == "") {
			alert("还没有登录！");
			window.location.href = "${pageContext.request.contextPath}/jsp/ots/login.jsp";
		}
	})

	function test() {
		alert('1' + value);
	}
	function search() {
		var searchName = $('#searchName').val();
		var searchType = $('#searchType').val();
		alert(searchName + " " + searchType);
	}

	function pay(orderNum, totalPrice) {
		var temp = document.createElement("form");
		temp.action = "${pageContext.request.contextPath}/user/pay.do";
		//创建from表单及其子元素  
		var form = $("<form></form>");
		//设置form的属性值  
		form.attr("action", "${pageContext.request.contextPath}/user/pay.do");
		form.attr("method", "post");
		//创建一个文本框  
		var input_text1 = $("<input type='hidden' id='userid' name='userid' />");
		input_text1.attr("value", $("#userid").val());
		var input_text2 = $("<input type='hidden' id='ordernum' name='ordernum' />");
		input_text2.attr("value", orderNum);
		var input_text3 = $("<input type='hidden' id='totalprice' name='totalprice' />");
		input_text3.attr("value", totalPrice);
		form.append(input_text1);
		form.append(input_text2);
		form.append(input_text3);

		//提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		form.appendTo(document.body).submit();
		return false;

	}
	function deleteById(orderId) {
		alert("orderId" + orderId);
		$.ajax({
			type : "post", //请求方式
			url : "${pageContext.request.contextPath}/order/deleteById.do", //发送请求地址
			data : {
				orderid : orderId
			}, //发送给数据库的数据     
			dataType : "json",
			//请求成功后的回调函数有两个参数
			success : function(data) {
				if (data[0].msg == "success") {
					alert("取消成功");
					location.reload();
				} else if (data[0].msg == "error") {
					alert("取消失败");
				} else if (data[0].msg == "empty1") {
					alert("参数为空");
				}
			},
			error : function(e) {
				alert('错误' + e);
			}
		});
	}
</script>
<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
#orderTable {
	border: 0px solid green;
	background-color: #FFFFFF;
	border-radius: 5px;
	margin: 8px auto;
	width: 95%;
	padding: 5px;
}

.blueStyle {
	color: blue;
	bgcolor: blue;
}

#pageBox {
    text-align: right;
    margin-right: 30px;
}

</style>
<body>
	<jsp:include page="head.jsp" />
	<div id="main">
		<div id="left">
			<jsp:include page="left.jsp" />
		</div>
		<div id="med">
			<!-- 主要部分 -->
			<input id="userid" type="hidden" name="userid" value="${user.id}">
			<c:choose>
				<c:when test="${msg == 'empty1' }">
					<p>暂时没有订单</p>
				</c:when>
				<c:when test="${msg == 'success' }">
					<c:forEach items="${pageModel.list}" var="userOrder">
						<!-- 根据订单的编号查询订单的详细信息 -->
						<table id="orderTable" border="0" cellspacing="1" cellpadding="1">
							<tr bgcolor="#F6F6F6">
								<td colspan="5"><a
									href="${pageContext.request.contextPath}/order/orderdetailQT.do?ordernum=${userOrder.ordernum}"
									title="订单详情">编号：${userOrder.ordernum}</a>
							</tr>
							<tr>
								<td  >收件人：${userOrder.name}</td>
								<td>总价：${userOrder.totalprice}</td>
								<td >支付状态：${userOrder.paystate}</td>
								<td >处理状态：${userOrder.mastate}</td>
								<td rowspan="2"><c:if test="${userOrder.paystate =='未支付' }">
						<button onclick="deleteById(this.value)"
											value="${userOrder.orderid }" class="btn btn-success">取消</button>
										<button onclick="pay(this.value,this.name)"
											value="${userOrder.ordernum}" name="${userOrder.totalprice}" class="btn btn-info">支付
										</button>
									</c:if></td>
							<tr>
								<td colspan="3">地址：${userOrder.address}</td>
								<td></td>
								
							</tr>
						</table>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>error</p>
				</c:otherwise>
			</c:choose>

			<p id="pageBox">
				<a
					href="${pageContext.request.contextPath}/order/listByUserid.do?pageNo=${pageModel.firstPage}&userid=2">第一页</a>
				<a
					href="${pageContext.request.contextPath}/order/listByUserid.do?pageNo=${pageModel.previousPage}&userid=2">上一页</a>
				<a
					href="${pageContext.request.contextPath}/order/listByUserid.do?pageNo=${pageModel.nextPage}&userid=2">下一页</a>
				<a
					href="${pageContext.request.contextPath}/order/listByUserid.do?pageNo=${pageModel.lastPage}&userid=2">尾页</a>
			</p>
			<!-- 主要部分结束 -->
		</div>
		<div id="right">
			<jsp:include page="right.jsp" />
		</div>
	</div>
	<jsp:include page="bottom.jsp" />
</body>
</html>