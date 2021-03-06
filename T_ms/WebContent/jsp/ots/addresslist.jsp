<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<style>
#med li,#med div,#med a,#med p{
	margin: 10px;
	font-family: 微软雅黑;
	font-size: 15px;
}
#med{
    background-image:url(${pageContext.request.contextPath}/image/yhxx.png ); width: 360px;
    background-repeat: no-repeat;
    background-size:101%; 
}

#med a {
	color: blue;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		if($("#userid").val() == ""){
			alert("还没有登录！");
			window.location.href="${pageContext.request.contextPath}/jsp/ots/login.jsp";
		}
	})
	function test(){
		alert('1'+value);
	}
	function search(){
		var searchName = $('#searchName').val();
		var searchType = $('#searchType').val();
		alert(searchName+" "+searchType);
	}
	function deleteById(id){
		var flag = confirm("确定删除本条？");
		if(flag){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/address/deleteById.do",
			data:{
				addressId:id
			},
			dataType:"json",
			success:function(data){
				if(data[0].msg == "success"){
					alert("删除成功");
					window.location.href = "${pageContext.request.contextPath}/address/listByUserid.do?userid=${user.id}";
				}else{
					alert("操作失败");
				}
			}
		});}
	}
	
	//添加
	function add(){
		var userid = $("#userid").val();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/address/judgeCount.do",
			data:{
				userid:userid
			},
			dataType:"json",
			success:function(data){
				if(data[0].msg == "YES"){
					window.location.href = "${pageContext.request.contextPath}/area/addressAdd.do?userid=${user.id}";
				}else{
					alert("添加的地址不能大于5条！");
				}
			}
		})
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
			<!-- 主要部分 -->
			<input id="userid" type="hidden" name="userid" value="${user.id}">
			<h2>地址：</h2>
			<c:choose>
				<c:when test="${msg == 'error' }">
				error
			</c:when>
				<c:when test="${msg == 'empty1' }">
					<p>你还没有添加地址！</p>
				</c:when>
				<c:otherwise>
					<ol>
					  <div style="background-color: white;width: 720px;">
						<c:forEach items="${addresslist}" var="address">
							<li style="margin: 0 0 0 25px;">${address.name}&nbsp;&nbsp;${address.phonenum}&nbsp;&nbsp;${address.address}&nbsp;&nbsp;${address.postcode}&nbsp;&nbsp;
							<br><button><a
								href="#" onclick="deleteById(${address.id})">删除</a></button></li>
								<hr>
						</c:forEach>	
						</div>
					</ol>
				</c:otherwise>
			</c:choose>

			<a href="#" onclick="add()" style="margin: 70px;">添加收货地址</a>
			<!-- 主要部分结束 -->
		</div>
		<div id="right">
			<jsp:include page="right.jsp" />
		</div>
	</div>
	<jsp:include page="bottom.jsp" />
</body>
</html>