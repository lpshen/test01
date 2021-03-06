<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<style>span.error{color:#C00; padding:0 6px;font-size:12px;font-family:'微软雅黑';}</style>
  <script type="text/javascript">
      function change(){
    	  
		 var userid = $("input[name='userid']").val();
		 var username= $("input[name='username']").val();
		 var phonenum = $("input[name='phonenum']").val();
		 var email = $("input[name='email']").val();
		 $.ajax({
			 type:"post",
			 url:"${pageContext.request.contextPath}/user/updateUserAll.do",
			 data: {
				 userid:userid,
				 username:username,
				 phonenum:phonenum,
				 email:email
			 },
			 datatype:"json",
			 success:function(data){
				 if(data[0].msg="success"){
					alert("修改成功"); 
					window.location.href = "${pageContext.request.contextPath}/user/findByUserid.do?userid=2";
				 }else{
					 alert("修改失败");
				 }
			 },
			 err:function(e){
				 alert("错误"+e);
			 }
		 });
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
   	<form id="updateInfo">
     	<table align="center">
     		<tr>
     			<td>
     				<input type="hidden" name="userid" value="${user.id}">
     			</td>
     		</tr>
     		<tr>
     			<td>用户名</td>
     			<td>
     				<input type="text" name="username" value="${userModel.username}" placeholder="必填" required data-msg-required="不能为空" data-msg-minlength="请输入最小2位" minlength="2" maxlength="15">
     			</td>
     		</tr>
     		<tr>
     			<td>积分</td>
     			<td>
     				<input type="text" name="credits" value="${userModel.credits}" disabled="true">
     			</td>
     		</tr>
     		<tr>
     			<td>电话</td>
     			<td>
     				<input type="text" name="phonenum" value="${userModel.phonenum}"  placeholder="手机号" required data-rule-mobile="true" data-msg-required="请输入手机号" data-msg-mobile="请输入正确格式">
     			</td>
     		</tr>	
     		<tr>
     			<td>邮箱</td>
     			<td>
     				<input type="text" name="email" value="${userModel.email}" placeholder="邮箱" required data-rule-mm="true" data-msg-required="请输入邮箱" data-msg-mm="请输入正确格式">
     			</td>
     		</tr>		
     		<tr>
     			<td>余额</td>
     			<td>
     				<input type="text" name="money" value="${userModel.money}" disabled="true">
     			</td>
     		</tr>  
     		 		
     	</table>
     	
     		<p align="center"><input type="submit" value="修改"></p>		
     			
     </form>
	</div>
    <div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 
</body>
<script type="text/javascript">
$(function(){
    //jquery.validate
	$("#updateInfo").validate({
		submitHandler: function() {
			//验证通过后 的js代码写在这里
			change();
		}
	})
		
})

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.extend.js"></script>
</html>