<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购物车</title>
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
	function pay() {
		var sel = [];
		if ($("input[name ='checkbox']").is(':checked')) {
			$("input[name = 'checkbox']:checked").each(function() {
				sel.push($(this).val());
			});
			var temp = document.createElement("form");
			//  temp.action = "${pageContext.request.contextPath}/order/creatOrder.do";
			//创建from表单及其子元素  
			var form = $("<form></form>");
			//设置form的属性值  
			form.attr("action",
					"${pageContext.request.contextPath}/cart/creatOrder.do");
			form.attr("method", "post");
			//创建一个文本框  
			var input_text1 = $("<input type='hidden' id='sel' name='sel' />");
			input_text1.attr("value", sel);
			var input_text2 = $("<input type='hidden' id='userid' name='userid' />");
			input_text2.attr("value", $("#userid").val());
			form.append(input_text1);
			form.append(input_text2);
			//提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
			form.appendTo(document.body).submit();
			return false;
		} else {
			alert("还没有选择");
		}

	}
	function deleteOne(value) {
		var userid = $("#userid").val();
		$
				.ajax({
					type : "post", //请求方式
					url : "${pageContext.request.contextPath}/cart/deleteById.do", //发送请求地址
					data : {
						id : value
					}, //发送给数据库的数据     
					dataType : "json",
					//请求成功后的回调函数有两个参数
					success : function(data) {
						if (data[0].msg == "success") {
							alert('状态修改成功！');
							window.location.href = "${pageContext.request.contextPath}/cart/cartShow.do?userid="
									+ userid;
						} else if (data[0].msg == "fail") {
							alert('状态修改失败！');
							window.location.href =  "${pageContext.request.contextPath}/cart/cartShow.do?userid="
									+ userid;
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
#cartTable {
	border: 5px solid #E2E6E6;
    width: 100%;
    margin: 9px 4px;
    background-color: #EFFAD4
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
				<c:when test="${msg == 'empty1'}">
					<p>购物车为空</p>

				</c:when>
				<c:when test="${msg == 'error'}">
					<p>未登录或参数错误！</p>

				</c:when>
				<c:otherwise>
					<c:forEach items="${pageModel.list}" var="cart">
						<table id="cartTable">
							<tr>
								<td><input name="checkbox" type="checkbox"
									value="${cart.id}"></td>
								<td><img
									src="${pageContext.request.contextPath}/upload/${cart.pictureurl}"
									height="100" width="100""></td>
								<td class="textStyle">菜名：${cart.good}</td>
								<td class="textStyle">单价：${cart.oriprice}￥</td>
								<td class="textStyle">数量：${cart.count}份</td>
								<td class="textStyle">总价：${cart.oriprice*cart.count}￥</td>
								<td><button onclick="deleteOne(${cart.id})"
										class="textStyle btn btn-info">删除</button></td>
							</tr>
						</table>
					</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${msg != 'empty1'}">
				<p align="center">
					<button onclick="pay()" class="btn btn-primary">去 结 算</button>
				</p>
			</c:if>
			<p id="pageBox">
				<a
					href="${pageContext.request.contextPath}/cart/cartShow.do?pageNo=${pageModel.firstPage}&userid=${user.id}">第一页</a>
				<a
					href="${pageContext.request.contextPath}/cart/cartShow.do?pageNo=${pageModel.previousPage}&userid=${user.id}">上一页</a>
				<a
					href="${pageContext.request.contextPath}/cart/cartShow.do?pageNo=${pageModel.nextPage}&userid=${user.id}">下一页</a>
				<a
					href="${pageContext.request.contextPath}/cart/cartShow.do?pageNo=${pageModel.lastPage}&userid=${user.id}">尾页</a>
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