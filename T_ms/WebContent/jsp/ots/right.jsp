<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	ul li{
	    font-size: 15px;
    	font-family: 微软雅黑;
    }
    div span{
     font-size: 18px;
    	font-family: 微软雅黑;
    	margin-left: 5px
    }
    
</style>
</head>
	<div>
	<img alt="" src="${pageContext.request.contextPath}/image/Rtu.jpg">
	</div>
	<div>
		<p style="font-size: 20px;">派送时间:</p>
		<p style="font-size: 20px;">&nbsp;&nbsp;&nbsp;9:00-20:00</p>
	</div>
	<div>
		<span>信息展示：&nbsp;<a href="${pageContext.request.contextPath}/greens/listBySearchQT.do">更多</a>>></span>
			<ul>
		<li><a href="${pageContext.request.contextPath}/jsp/ots/article_read.html">配送规则</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/ots/team.jsp">网站公告</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/ots/reliefStatement.html">免责声明</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/ots/OpenProve.html">开业证明</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/ots/helpForCust.html">帮助中心</a></li>
	</ul>
	</div>


</html>