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
	function buy(){//在之前检查用户登录状态
		 var menuid = $("#menuid").val();
		 var userid = $("#userid").val();
		 var good = $("#good").val();
		 var pictureurl = $("#pictureurl").val();
		 var oriprice = $("#oriprice").val();
		 var curprice = $("#curprice").val();
		 var count = $("#count").val();
		 var merid = $("#merid").val();
		var temp = document.createElement("form");
		  temp.action = "${pageContext.request.contextPath}/cart/setOrder.do";
		//创建from表单及其子元素  
		  var form=$("<form></form>");   
		  //设置form的属性值  
		  form.attr("action","${pageContext.request.contextPath}/cart/setOrder.do");    
		     form.attr("method", "post");    
		     //创建一个文本框  
		     var input_text1 = $("<input type='hidden' id='menuid' name='menuid' />");  
		     input_text1.attr("value",menuid );   
		     var input_text2 = $("<input type='hidden' id='userid' name='userid' />");  
		     input_text2.attr("value",userid ); 
		     var input_text3 = $("<input type='hidden' id='good' name='good' />");  
		     input_text3.attr("value",good ); 
		     var input_text4 = $("<input type='hidden' id='pictureurl' name='pictureurl' />");  
		     input_text4.attr("value",pictureurl ); 
		     var input_text5 = $("<input type='hidden' id='oriprice' name='oriprice' />");  
		     input_text5.attr("value",oriprice ); 
		     var input_text6 = $("<input type='hidden' id='curprice' name='curprice' />");  
		     input_text6.attr("value",curprice ); 
		     var input_text7 = $("<input type='hidden' id='count' name='count' />");  
		     input_text7.attr("value",count ); 
		     var input_text8 = $("<input type='hidden' id='merid' name='merid' />");  
		     input_text8.attr("value",merid ); 
 
		     form.append(input_text1);
		     form.append(input_text2); 
		     form.append(input_text3); 
		     form.append(input_text4); 
		     form.append(input_text5); 
		     form.append(input_text6); 
		     form.append(input_text7); 
		     form.append(input_text8);


		  //提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		         form.appendTo(document.body).submit();  
		  return false;

	}
	function addCart(){//在之前检查用户登录状态
		var menuid = $('#menuid').val();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/cart/insert.do",
			data:{
			menuid:$('#menuid').val(),
			merid:$('#merid').val(),
		    userid:$('#userid').val(),
		    good:$('#good').val(),
		    pictureurl:$('#pictureurl').val(),
		    oriprice:$('#oriprice').val(),
		    curprice:$('#curprice').val(),
		    count:$('#count').val()
			},
			dataType:"json",
			success:function(data){
				if(data[0].msg == 'success'){
					alert('成功加入购物车！');
				}
			}
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
	<div id="med">
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
				<input id="shopname" type="hidden" value="${oneFoodModel.shopname }">
				<table>
					<tr><td clospan="3" rowspan="7"><img alt="食物图片" src="${pageContext.request.contextPath}/upload/${oneFoodModel.pictureurl}" height="200" width="200"></td><td>名称：${oneFoodModel.grename }</td></tr>
					<tr><td>店铺Id：${oneFoodModel.merid }</td></tr>
					<tr><td>原价：<s>${oneFoodModel.oriprice }</s>￥</td></tr>
					<tr><td>现价：${oneFoodModel.curprice }￥</td></tr>
					<tr><td>店铺：${oneFoodModel.shopname }</td></tr>
					<tr><td>
						<input id= "add" type="button" value="+" onclick = "add()">
						<input id ="count" style= "width:20px;" type="text" value="1">
						<input id ="sub" type="button" value="-" onclick = "sub()">
					</td></tr>
					<tr><td><button onclick="buy()">立即下单</button><button onclick="addCart()">加入购物车</button></td></tr>
				</table>
				
				
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