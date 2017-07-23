<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<style>
span.error {
	color: #C00;
	padding: 0 6px;
	font-size: 12px;
	font-family: '微软雅黑';
}

#med form {
	background-color: wheat;
	margin: 0px auto;
	height: 550px;
}

#med table {
	position: relative;
    left: 260px;;
}
</style>

<script type="text/javascript">
	function pay() {
		var temp = document.createElement("form");
		temp.action = "${pageContext.request.contextPath}/user/pay.do";
		//创建from表单及其子元素  
		var form = $("<form></form>");
		//设置form的属性值  
		form.attr("action", "${pageContext.request.contextPath}/user/pay.do");
		form.attr("method", "post");
		//创建一个文本框  
		var input_text1 = $("<input type='text' id='userid' name='userid' />");
		input_text1.attr("value", $("#userid").val());
		var input_text2 = $("<input type='text' id='ordernum' name='ordernum' />");
		input_text2.attr("value", $("#ordernum").val());
		var input_text3 = $("<input type='text' id='totalprice' name='totalprice' />");
		input_text3.attr("value", $("#totalprice").val());
		form.append(input_text1);
		form.append(input_text2);
		form.append(input_text3);

		//提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		form.appendTo(document.body).submit();
		return false;

	}
	function chageCode() {
		$('#codeImage').attr(
				'src',
				'${pageContext.request.contextPath}/user/authCode.do?abc='
						+ Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
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
			<!-- 	<p>用户名：${userModel.username}</p>
		<p>积分：${userModel.credits} &nbsp;<a href="${pageContext.request.contextPath}/jsp/ots/JF.jsp">积分规则</a></p>
		<p>电话：${userModel.phonenum}</p>
		<p>邮箱：${userModel.email}</p>
		<p>余额：${userModel.money}￥</p>
		 -->
			<!-- 注册 -->
			<div>
				<form action="${pageContext.request.contextPath}/user/register.do"
					method="post" id="jsForm">
					<table>
						<tr>
							<td colspan="2"><h3 style="margin-left: 100px">用户注册</h3></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td><input name="username" placeholder="必填" required
								data-msg-required="不能为空" data-msg-minlength="请输入最小2位"
								minlength="2" maxlength="15" type="text"></td>
							<td><div id="aa"></div></td>
						</tr>
						<tr>
							<td>电话</td>
							<td><input name="phonenum" type="text" placeholder="手机号"
								required data-rule-mobile="true" data-msg-required="请输入手机号"
								data-msg-mobile="请输入正确格式"></td>
						</tr>
						<tr>
							<td>邮箱</td>
							<td><input name="email" type="text" placeholder="邮箱"
								required data-rule-mm="true" data-msg-required="请输入邮箱"
								data-msg-mm="请输入正确格式"></td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input name="password" type="password" value=""
								placeholder="新密码" required id="password"></td>
						</tr>
						<tr>
							<td>确认密码</td>
							<td><input name="repassword" type="password" value=""
								placeholder="确认新密码" required equalTo="#password"></td>
						</tr>
						<tr>
							<td>验证码</td>
							<td><input type="text" name="authCode" id="authCode"
								placeholder="必填" required data-msg-required="不能为空"></td>
						</tr>
						<tr>
							<td></td>
							<td><img type="image"
								src="${pageContext.request.contextPath}/user/authCode.do"
								id="codeImage" onclick="chageCode()"
								margin-left:8px; vertical-align:bottom" width="83" height="20"
								title="点击获取验证码" style="cursor: pointer;" /></td>
						</tr>
						<tr>
							<td colspan="2"><input style="margin: 15px 12px 0px 86px"
								type="submit" value="注册"><input type="reset"></td>
						</tr>
					</table>
					<p color="red">${msg}</p>

				</form>
			</div>
			<!-- 主要部分结束 -->
		</div>
		<div id="right">
			<jsp:include page="right.jsp" />
		</div>
	</div>
	<jsp:include page="bottom.jsp" />
</body>
<script type="text/javascript">
	$(function() {
		//jquery.validate
		$("#jsForm").validate({
			submitHandler : function() {
				//验证通过后 的js代码写在这里
				this.submit();//提交表单请求
			}
		})

	})
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.extend.js"></script>
</html>