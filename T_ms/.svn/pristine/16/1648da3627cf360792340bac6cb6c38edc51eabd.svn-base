<%@ page language="java" import="java.util.*" pageEncoding="utf-8" 
	contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>Insert title here!</title>
  </head>
  
  <body>
	<!-- 进行支付的页面 -->
	<form action="<c:url value='/yeepay/pay.do'/>">
		商品订单号：<input type="text" name="p2_Order" value="${orderNum }">${orderNum }<br/>
		支付金额：<input type="text" name="p3_Amt" value="${totalPrice }">${totalPrice }<br/>
		选择银行：<br/>
		中国农业银行<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"><br/>
		建设银行<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"><br/>
		工商银行<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"><br/>
		交通银行<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"><br/>
		<input type="submit" value="支付">
	</form>
  </body>
</html>
