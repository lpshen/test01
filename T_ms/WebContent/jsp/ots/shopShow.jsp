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
	function subUserMsg() {
		var userId = $("#userId").val();
		var usermsg = $("#usermsg").val();

		if (userId != "") {
			alert("userid" + userId + "usermsg" + usermsg);
			if (usermsg != "") {
				$
						.ajax({
							type : "post", //请求方式
							url : "${pageContext.request.contextPath}/message/insert.do", //发送请求地址
							data : {
								userId : userId,
								usermsg : usermsg
							}, //发送给数据库的数据     
							dataType : "json",
							//请求成功后的回调函数有两个参数
							success : function(data) {
								if (data[0].msg == "success") {
									alert('留言成功！');
									window.location.href = "${pageContext.request.contextPath}/shop/shopShow.do?pageNo=1";
								} else if (data[0].msg == "fail") {
									alert('留言失败！');
								}
							},
							error : function(e) {
								alert('错误' + e);
							}
						});
			} else {
				alert("留言不能为空！");
			}
		} else {
			alert("要先登录才能留言！");
		}
	}
</script>
<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css"
	rel="stylesheet" type="text/css" />
<style>
#reply {
	border: 2px solid #C7D4E0;
	border-radius: 3px;
	background-color: #EBEFF3;
	margin: 5px 10px 0 10px;
	background-color: #EBEFF3;
	padding: 2px;
}

#pageReply {
	background-color: white;
}

#usermsg {
	width: 500px;
	height: 60px;
	margin-left: 22px;
	border-radius: 10px;
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
			<table>
				<tr>
					<td clospan="3" rowspan="7"><img alt="食物图片"
						src="${pageContext.request.contextPath}/upload/${shop.pictureurl}"
						height="200" width="200"></td>
					<td>名称：${shop.shopname }</td>
				</tr>
				<tr>
					<td>店铺Id：${shop.id }</td>
				</tr>
				<tr>
					<td>描述：${shop.descri }</td>
				</tr>
				<tr>
					<td>电话：${shop.phonenum }</td>
				</tr>
				<tr>
					<td>地址：${shop.address }</td>
				</tr>
			</table>
			<p>
				&nbsp;用户留言
			</p>
			<p>
				<textarea id="usermsg" name="usermsg" maxlength="80"></textarea>
				&nbsp;<button class="btn btn-info" onclick="subUserMsg()">提交</button>
			</p>

			<div id="pageReply">
				<c:forEach items="${pageModel.list}" var="message">
					<div id="reply">
						<div style="float: right;">${message.messagetime }</div>
						<div>${message.userid}：${ message.content}</div>
						<div style="float: right;">${message.replytime }</div>
						<div>回复：${ message.reply}</div>
					</div>
				</c:forEach>
				<p align="center">
					<a
						href="${pageContext.request.contextPath}/shop/shopShow.do?pageNo=${pageModel.firstPage}">第一页</a>
					<a
						href="${pageContext.request.contextPath}/shop/shopShow.do?pageNo=${pageModel.previousPage}">上一页</a>
					<a
						href="${pageContext.request.contextPath}/shop/shopShow.do?pageNo=${pageModel.nextPage}">下一页</a>
					<a
						href="${pageContext.request.contextPath}/shop/shopShow.do?pageNo=${pageModel.lastPage}">尾页</a>

				</p>
			</div>
			<input id="userId" name="userId" type="hidden" value="${user.id }">
			<!-- 			
				<p>店铺Id：${oneFoodModel.merid }</p> 
				<p>名称：${oneFoodModel.grename }</p>
				<p><img alt="食物图片" src="${pageContext.request.contextPath}/upload/${oneFoodModel.pictureurl}" height="200" width="200"></p>
				<p>原价：<s>${oneFoodModel.oriprice }</s>￥</p>
				<p>现价：${oneFoodModel.curprice }￥</p>
				<p>店铺：${oneFoodModel.shopname }</p>
				<p>	
					<input id= "add" type="button" value="+" onclick = "add()">
					<input id ="count" style= "width:20px;" type="text" value="1">
					<input id ="sub" type="button" value="-" onclick = "sub()">
				</p>  
				<button onclick="buy()">立即下单</button><button onclick="addCart()">加入购物车</button>
 -->

			<!-- 主要部分结束 -->
		</div>
		<div id="right">
			<jsp:include page="right.jsp" />
		</div>
	</div>
	<jsp:include page="bottom.jsp" />

</body>
</html>