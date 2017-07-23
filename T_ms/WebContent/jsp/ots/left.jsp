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
    .leftImg img {
	    width: 202px;
	    margin-bottom: 20px;
}
    
</style>
</head>
	<div class = "leftImg">
		<img alt="图片" src="${pageContext.request.contextPath}/image/dc.jpg">
	</div>
	<div>
		<span>特色推荐&nbsp;<a href="${pageContext.request.contextPath}/greens/listBySearchQT.do">更多</a>>></span>
			<ul>
		<li><a href="${pageContext.request.contextPath}/greens/listBySearchQT.do">全部</a></li>
		<li><a href="${pageContext.request.contextPath}/greens/listBySearchQT.do?type=特色菜">特色菜</a></li>
		<li><a href="${pageContext.request.contextPath}/greens/listBySearchQT.do?type=美味套餐">美味套餐</a></li>
		<li><a href="${pageContext.request.contextPath}/greens/listBySearchQT.do?type=饮品">饮品</a></li>
		<li><a href="${pageContext.request.contextPath}/greens/listBySearchQT.do?type=家常菜">家常菜</a></li>
		<li><a href="${pageContext.request.contextPath}/greens/listBySearchQT.do?type=小吃">小吃</a></li>
	</ul>
	</div>
	<div>
		
	</div>


</html>