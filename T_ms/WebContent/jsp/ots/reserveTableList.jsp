<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/jsp/ots/laydate/laydate.js"></script>

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
	$(function(){
		if($("#userId").val() == ""){
			window.location.href="${pageContext.request.contextPath}/jsp/ots/login.jsp";
		}
	})

	</script>
	<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css" rel="stylesheet" type="text/css" />
<style>
 #orderTable{
    margin: 0px auto;
} 
</style>

<body>
<input id= "userId" type="hidden" value="${user.id}"> 
<jsp:include  page="head.jsp"/> 
<div id="main">
	<div id="left">
		<jsp:include page="left.jsp"/>
	</div>
	<div id="med">
	<!-- 主要部分 -->
	<h2 align ="center">当前预定</h2>
	<hr>
	<br>
	<c:choose>
		<c:when test="${pageModel.list == '[]' }">
		<p>当前没有预定</p>
		</c:when>
		<c:otherwise>
		
					<c:forEach items="${pageModel.list}" var="reserveTable">
					<table id="orderTable" border="0" cellspacing="1" cellpadding="1" width="90%">
						<tr bgcolor="#EEEEEE">
						<td colspan="5">&nbsp;</tr>
						<tr><td  width="20%">餐桌id：${ reserveTable.diningTableId}</td><td  width="20%">日期：${reserveTable.reserveDate}</td><td colspan="2" width="30%">时段：${reserveTable.reserveTime}</td>
						<tr><td >电话：${reserveTable.phonenum}</td><td width="30%">联系人：${reserveTable.linkman}</td><td>状态：${reserveTable.state }</td>
						<td><c:if test="${reserveTable.state =='已预订' }"> 操作：
							<button href="#">取消 </button>
						</c:if></td></tr>
					</table>
					</c:forEach>
					<p align="center">
						<a href="${pageContext.request.contextPath}/reserveTable/listBySearch.do?userId=${user.id }&pageNo=${pageModel.firstPage}">第一页</a>
						<a href="${pageContext.request.contextPath}/reserveTable/listBySearch.do?userId=${user.id }&pageNo=${pageModel.previousPage}">上一页</a>
						<a href="${pageContext.request.contextPath}/reserveTable/listBySearch.do?userId=${user.id }&pageNo=${pageModel.nextPage}">下一页</a>
						<a href="${pageContext.request.contextPath}/reserveTable/listBySearch.do?userId=${user.id }&pageNo=${pageModel.lastPage}">尾页</a>
					
					</p>
		</c:otherwise>
	</c:choose>
	<a style="margin: 46px;" href="${pageContext.request.contextPath}/jsp/ots/reserveTable.jsp"> 立即预定</a>
	
	<!-- 主要部分结束 -->
	</div>
	<div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 
</body>
</html>