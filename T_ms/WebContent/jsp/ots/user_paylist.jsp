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
	function pay(){
		var temp = document.createElement("form");
		temp.action = "${pageContext.request.contextPath}/user/pay.do";
		//创建from表单及其子元素  
		  var form=$("<form></form>");   
		  //设置form的属性值  
		  form.attr("action","${pageContext.request.contextPath}/user/pay.do");    
		     form.attr("method", "post");    
		     //创建一个文本框  
		     var input_text1 = $("<input type='text' id='userid' name='userid' />");  
		     input_text1.attr("value",$("#userid").val() );   
		     var input_text2 = $("<input type='text' id='ordernum' name='ordernum' />");  
		     input_text2.attr("value",$("#ordernum").val()); 
		     var input_text3 = $("<input type='text' id='totalprice' name='totalprice' />");  
		     input_text3.attr("value",$("#totalprice").val() ); 
		     form.append(input_text1);
		     form.append(input_text2); 
		     form.append(input_text3); 

		  //提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		         form.appendTo(document.body).submit();  
		  return false;
		
	}

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
				<div>
				<p style="font-size: 30px; " align="center">消费记录</p>
					<table width="90%" align="center" style="border: 2px solid #FFFFFF;">
					<tr><th align="center">用户ID</th><th align="center">消费金额</th><th align="center">时间</th><th align="center">订单编号</th></tr>
					<c:forEach items="${pageModel.list}" var="payRecord">
						<tr ><td align="center">${payRecord.userId}</td><td align="center">${ payRecord.money}</td><td align="center">${payRecord.time }</td><td align="center">${payRecord.ordernum}</td></tr>
					</c:forEach>
					</table>
					<p align="center">
						<a href="${pageContext.request.contextPath}/recharge/listByPageZF.do?pageNo=${pageModel.firstPage}&userid=${user.id}">第一页</a>
						<a href="${pageContext.request.contextPath}/recharge/listByPageZF.do?pageNo=${pageModel.previousPage}&userid=${user.id}">上一页</a>
						<a href="${pageContext.request.contextPath}/recharge/listByPageZF.do?pageNo=${pageModel.nextPage}&userid=${user.id}">下一页</a>
						<a href="${pageContext.request.contextPath}/recharge/listByPageZF.do?pageNo=${pageModel.lastPage}&userid=${user.id}">尾页</a>
					
					</p>
				</div>
	<!-- 主要部分结束 -->
	</div>
	<div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 
</body>
</html>