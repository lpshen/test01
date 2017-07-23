<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<style>
#main ol{
 margin-left: 60px;
}
#main .JFCJ{
 color: green;
}
</style>
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
	function listRecord(){
		var userid = $("#userid").val();
		if(userid == ""){
			alert("你还没有登录！");
			window.location.href="${pageContext.request.contextPath}/jsp/ots/login.jsp";
		}else{
			window.location.href="${pageContext.request.contextPath}/award/list.do?userid="+userid;
		}
		
	};
	function CJ(){
		var userid = $("#userid").val();
		if(userid != ""){
			if(confirm("本次抽奖将消耗五个积分，是否继续？")){
				
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/user/judgeCredits.do",
			data:{
			userid:userid	
			},
			dataType:"json",
			success:function(data){
					if(data[0].msg == 'BZ'){
						alert("积分不足，暂时不能抽奖！");
					}else if(data[0].msg == 'CZ'){
						window.location.href = "${pageContext.request.contextPath}/awardMenu/list.do";
					}
				}
				
			
		});}}else{
			alert("你还没有登录！");
			window.location.href="${pageContext.request.contextPath}/jsp/ots/login.jsp";
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
	<input id="userid" type="hidden" value="${user.id}">
	
	<h2>&nbsp;积分规则:<hr></h2>
	<ol>
		<li>用户下单赠送积分（消费金额每10元赠送1个积分取值按四舍五入。如：消费金额为14元赠送1个积分，消费金额为15元赠送2个积分）</li>
		<li>用户注册首次登陆赠送2个积分</li>

	</ol>
	<a class="JFCJ" href="javascript:void(0);" onclick="CJ()" >积分抽奖</a>	
	<a class="JFCJ" href="javascript:void(0);" onclick="listRecord()"> 抽奖记录</a>
	<!-- 主要部分结束 -->
	</div>
	<div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 
</body>
</html>