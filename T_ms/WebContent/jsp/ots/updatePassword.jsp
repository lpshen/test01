<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>span.error{color:#C00; padding:0 6px;font-size:12px;font-family:'微软雅黑';}</style>
<script type="text/javascript">
	function updatepass(){
		var userid = $("input[name='userid']").val();
		var password=$("input[name='password']").val();
		var password1=$("input[name='password1']").val();
		var password2=$("input[name='password2']").val();
		var password3=$("input[name='password2']").val();
		if(password==" "){
			alert("您尚未登录");
			window.location.href="${pageContext.request.contextPath}/jsp/ots/login.jsp";
		}else{
			if(password!=password1){
				alert("您输入的原始密码不正确");
			}
			if(password2!=password3){
				alert("新密码输入不同");
			}
		}
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/user/updatePassword.do",
			data:{
				userid:userid,
				password:password2
			},
			datatype:"json",
			success:function(data){
				if(data[0].msg="success"){
					alert("密码修改成功");
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
<jsp:include page="head.jsp"/>
<div id="main">
	<div id="left">
		<jsp:include page="left.jsp"/>
	</div>
	<div id="med">
	<p align="center" style="font-size: 30px">修改密码</p>
	<form id="updatePassword">
		<table align="center">
		    <tr>
				<td>
					<input type="hidden" name="userid" value="${user.id}">
				</td>
				<td>
					<input type="hidden" name="password" value="${user.password}">
				</td>
			</tr>
			<tr>
			   <td>
			   		原密码
			   <td>
				<td>
					<input type="text" name="password1" placeholder="密码" required data-msg-required="请输入原始密码" minlength="6" data-msg-minlength="至少输入6个字符">
				</td>
			</tr>
			<tr>
			   <td>
			   		新密码
			   <td>
				<td>
					<input type="text" name="password2" value="" placeholder="新密码" required id="password">
				</td>
			</tr>
			<tr>
			   <td>
			   		确认密码
			   <td>
				<td>
					<input type="text" name="password3" value="" placeholder="确认新密码" required equalTo="#password">
				</td>
			</tr>
		</table>
			   	<p align="center">	<input type="submit" value="修改"></p>
	</form>
	</div>
	<div id="right">
		<jsp:include page="left.jsp"/>
	</div>
</div>

</body>
<script type="text/javascript">
$(function(){
    //jquery.validate
	$("#updatePassword").validate({
		submitHandler: function() {
			//验证通过后 的js代码写在这里
			updatepass();
		}
	})
		
})

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.extend.js"></script>
</html>