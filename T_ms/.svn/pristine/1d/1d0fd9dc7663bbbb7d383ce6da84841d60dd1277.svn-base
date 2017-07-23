<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#nav li {
			float:left;
			}
			#nav ul {
				/*border: 1px solid green;*/
				list-style-type: none;
				margin: 0px;
				padding: 0px;
			}
				#nav li a {
				text-decoration: none;
				/* 让超链接显示为块级标签形式，否则只有在超链接之上才显示为小手形状，我们希望在导航栏的这一行都可以点击跳转 */
				display: block;
				padding: 5px;
				/*border-left: 12px solid #711515;*/
				border-left: 1px;
			}
		#nav {
		}	
</style>
</head>
<body background="${pageContext.request.contextPath}/image/04.jpg">

	<div id="nav">
	<ul>
		<li><a href="${pageContext.request.contextPath}/greens/listBySearchQT.do" target="main">首页</a></li>
		<li><a href="${pageContext.request.contextPath}/cart/findByUserid.do?userid=2" target="main">购物车</a></li>
		<li><a href="${pageContext.request.contextPath}/order/listByUserid.do?userid=2" target="main">我的订单</a></li>
		<li><a href="${pageContext.request.contextPath}/user/findByUserid.do?userid=2" target="main">账户信息</a></li>
		<li><a href="${pageContext.request.contextPath}/area/addressAdd.do?userid=2" target="main">添加地址</a></li>
		<li><a href="${pageContext.request.contextPath}/address/listByUserid.do?userid=2" target="main">收货地址</a></li>
	</ul>
	</div>

</body>
</html>