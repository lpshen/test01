<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#bottom {
	height: 100px;
	border: 1px solid black;
}

.foot {
	width: 100%;
	background: #E1E1E1;
	margin-top: 14px;
}

.foot img {
	margin-left: 38px;
	margin-top: 15px;
	width: 95%
}

.foot div {
	text-align: center;
	line-height: 30px;
}
</style>
</head>
<div class="foot">
	<img src="${pageContext.request.contextPath}/image/foot.png" alt="">
	<div>合肥师范学院开发团队</div>
	<div>地址：合肥市金寨路327号（三孝口校区） 合肥市经济开发区莲花路1688号（锦绣校区）</div>
</div>
</html>