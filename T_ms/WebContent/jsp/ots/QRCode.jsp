<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信支付</title>
<meta name="description" content="微信支付" />
<meta name="keywords" content="微信支付" />
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>

	<script type="text/javascript">
	   var timer;
		$(function(){
			var handler = function(){
				var out_trade_no = $('input[name=out_trade_no]').val();
				var service = $('input[name=service]').val();
				var version = $('input[name=version]').val();
				var charset = $('input[name=charset]').val();
				var sign_type = $('input[name=sign_type]').val();
				var userId = $('input[name=userId]').val();
				var money = $('input[name=money]').val();
				$.post("${pageContext.request.contextPath}/weChat/payResultQuery.do?out_trade_no="+out_trade_no
						+"&service="+service+"&version="+version+"&charset="+charset+"&sign_type="+sign_type,null,function(msg){
					//alert(msg);
					if(msg == '1'){
 						alert("支付成功");
						//document.location.href="../jsp/ots/success.jsp";
						document.location.href="${pageContext.request.contextPath}/recharge/saveRechargeRecord.do?userId="+userId+"&money="+money;
						clearInterval(timer);
					}
				});
			}
			timer = setInterval(handler , 5000);
		});
	</script>
</head>
<body>
<div align="center" bgcolor="#666666">
   <div>
<!--扫描代码-->
	<input type="hidden" name="out_trade_no"  value="${out_trade_no}"/>
	<input type="hidden" name="service" value="trade.single.query" readonly="readonly" maxlength="32"  placeholder="长度32"/>
	<input  type="hidden" name="version" value="1.0" readonly="readonly" maxlength="8"  placeholder="长度8"/>
	<input  type="hidden" name="charset" value="UTF-8" readonly="readonly" maxlength="8"  placeholder="长度8"/>
	<input  type="hidden" name="sign_type" value="MD5" readonly="readonly" maxlength="8"  placeholder="长度8"/>
	<input type="hidden" name ="userId" value="${user.id}"> 
	<input type="hidden" name = "money" value="${total_fee/100}">
      <div class="s-con" id="codem">
	   <div class="m26">
               <h1>订单提交成功，请您尽快付款！</h1>
               <div class="num"><span class="color1 ml16" style="font-size:15px;">订单号：<label id="out_trade_no" class="orange">${out_trade_no}</label></span><span class="color1 ml16">请您在提交订单后 <span class="orange">5分钟</span> 内完成支付，否则订单会自动取消。</span></div>
         </div>
         <div class="title"><span class="color1 ml16" style="font-size:15px;">商品名称：<label class="orange">${body}</label></span></br><span class="color1 ml16" style="font-size:15px;">订单金额：<label class="orange">${total_fee/100.00}</label>元</span>
		 
		 
		 </div>
         <div class="scan"><img src="${code_img_url}"/></div>
 <!--         <div class="question">
           <div class="new"><a href="http://www.zhifuka.net/gateway/weifutong/bind.html" target="_blank">微信支付如何绑定银行卡?</a></div>
          </div>
  -->
      </div>
<!--扫描代码结束-->
<!--底部代码-->
      <div class="s-foot">-------</div>
<!--底部代码结束-->
   </div>
</div>
</body>
</html>
