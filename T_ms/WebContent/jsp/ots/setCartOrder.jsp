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
	function submitOrder(){
		 if($("input[name ='addressid']:checked").val() == null&& $("input[name ='addressid']:checked").val() == undefined){
			 alert("请选择收货地址");
			 return;
		 }
		var temp = document.createElement("form");
		//创建from表单及其子元素  
		  var form=$("<form></form>");   
		  //设置form的属性值  
		  form.attr("action","${pageContext.request.contextPath}/order/bulidOrder.do");    
		     form.attr("method", "post");    
		     //创建一个文本框  
		     var input_text1 = $("<input type='hidden' id='sel' name='sel' />");  
		     input_text1.attr("value",$("#sel").val() );   
		     var input_text2 = $("<input type='hidden' id='userid' name='userid' />");  
		     input_text2.attr("value",$("#userid").val() ); 
		     var input_text3 = $("<input type='hidden' id='addressid' name='addressid' />");  
		     input_text3.attr("value",$("input[name ='addressid']:checked").val() ); 
		     var input_text4 = $("<input type='hidden' id='merid' name='merid' />");  
		     input_text4.attr("value",$("#merid").val() ); 

		     form.append(input_text1);
		     form.append(input_text2); 
		     form.append(input_text3);
		     form.append(input_text4);
		  //提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		         form.appendTo(document.body).submit();  
		  return false;
		
	}
	$(function(){
		$("#addressid").attr("checked",false);
	})


	</script>
	<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css" rel="stylesheet" type="text/css" />
<style>
#box{
margin:8px 10px;
background-color: white;

}

</style>
<body>
<jsp:include  page="head.jsp"/> 
<div id="main">
	<div id="left">
		<jsp:include page="left.jsp"/>
	</div>
	<div id="med">
	<!-- 主要部分 -->
		<c:if test="${msg2 == 'empty2' }">
			没有菜单
		</c:if>
		<input id="sel" name="sel" type="hidden" value="${sel}">
		<input id="userid" name="userid" type="hidden" value="${user.id}">
		<input id="merid" name="merid" type="hidden" value="${cartList.get(0).merid }">
		<c:forEach items="${cartList}" var="cartModel">	
		<div id="box">
			<div class="foodlist">
			<span class="">菜名：${cartModel.good }</span>
			<span>原价：￥<s>${cartModel.oriprice }</s></span>
			<span>现价：￥${cartModel.curprice }</span>
			<span>数量：${cartModel.count }&nbsp;份</span>
			</div>
		</div>
		</c:forEach>	
		<c:choose>
			<c:when test="${msg1 == 'empty1' }">
			<p>你还没有添加地址！</p>
			<p><button>去添加地址</button></p>
			</c:when>
			<c:otherwise>
				<c:forEach items = "${addresslist}" var="address">
				<p><input type="radio" id="addressid" name = "addressid" value="${address.id}">${address.address}${address.postcode}${address.name}${address.phonenum}</p>
				</c:forEach>
			</c:otherwise>
		</c:choose> 
		<p align="center"><button onclick="submitOrder()"  class="btn btn-success">提交订单</button></p>
	<!-- 主要部分结束 -->
	</div>
	<div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 
</body>
</html>