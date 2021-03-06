<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<style>
span.error {
	color: #C00;
	padding: 0 6px;
	font-size: 12px;
	font-family: '微软雅黑';
}
#LOGON{
  margin: 10px 0;
}

#jsForm{
    background-color: wheat;
    width: 380px;
    border:5px solid #EEE4E4;
    margin: 0px auto;
    height: 220px;
    }


#jsForm table{
	position: relative;
    left: 55px;
}
</style>
</head>
<script type="text/javascript">
	//验证码
	function chageCode() {
		$('#codeImage').attr(
				'src',
				'${pageContext.request.contextPath}/user/authCode.do?abc='
						+ Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
	}
	function login() {
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
		$
				.ajax({
					type : "post", //请求方式
					url : "${pageContext.request.contextPath}/user/login.do", //发送请求地址
					data : {
						username : username,
						password : password
					}, //发送给数据库的数据     
					dataType : "json",
					//请求成功后的回调函数有两个参数
					success : function(data) {
						if (data[0].msg == "success") {
							alert("登录成功");
							// location.reload();
							window.location.href = "${pageContext.request.contextPath}/greens/listBySearchQT.do";
						} else if (data[0].msg == "error") {
							alert("登录失败");
						} else if (data[0].msg == "empty1") {
							alert("参数为空");
						}
					},
					error : function(e) {
						alert('错误' + e);
					}
				});

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
			<!-- 登录 -->
			<div id="LOGON">
			<form id="jsForm">
				<input id="msg" type="hidden" value="${msg}">
				<table>
					<tr>
						<td colspan="2" ><h3 style="margin-left: 100px">用户登录</h3></td>
						</tr>
					<tr>
						<td>账户名</td>
						<td  ><input name="username" placeholder="必填" required
							data-msg-required="不能为空" data-msg-minlength="请输入最小2位"
							minlength="2" maxlength="15" type="text"></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input name="password" type="password" placeholder="密码"
							required data-msg-required="请输入原始密码" minlength="6"
							data-msg-minlength="至少输入6个字符"></td>
					</tr>
					<tr>
						<td colspan="2" ><input style="margin:8px  12px 0px 86px" class="box11" type="submit" value="登录"><input type="reset"></td>
					</tr>

				</table>
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
		var msg = $("#msg").val();
		if (msg == "nologin") {
			alert("请登录后操作！");
		}
		//jquery.validate
		$("#jsForm").validate({
			submitHandler : function() {
				//验证通过后 的js代码写在这里
				login();

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