<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>积分抽奖活动</title>

		<link href="${pageContext.request.contextPath}/jsp/ots/JF/css/style.css" rel="stylesheet" type="text/css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ots/JF/js/awardRotate.js"></script>

		<script type="text/javascript">
			var turnplate = {
				restaraunts: [], //大转盘奖品名称
				colors: [], //大转盘奖品区块对应背景颜色
				outsideRadius: 192, //大转盘外圆的半径
				textRadius: 140, //大转盘奖品位置距离圆心的距离
				insideRadius: 68, //大转盘内圆的半径
				startAngle: 0, //开始角度
				bRotate: false //false:停止;ture:旋转
			};


			$(document).ready(function() {
				if($("#value").val() == ","){
					alert("要登录才可以抽奖，您还没有登录！");
					window.location.href = "${pageContext.request.contextPath}/jsp/ots/login.jsp";
				}
					//	alert("开始"+$("#menu1").val());
						//动态添加大转盘的奖品与奖品区域背景颜色
				turnplate.restaraunts = [$("#menu1").val(), $("#menu2").val(), $("#menu3").val(), $("#menu4").val(), $("#menu5").val(), $("#menu6").val()];
				turnplate.colors = ["#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF"];

				// wheelcanvas 转盘放置位置的id
				var rotateTimeOut = function() {
				//	alert("rotateTimeOut");
					$('#wheelcanvas').rotate({
						angle: 0,
						animateTo: 2160,
						duration: 8000,
						callback: function() {
							alert('网络超时，请检查您的网络设置！');
						}
					});
				};

				//旋转转盘 item:奖品位置; txt：提示语;
				var rotateFn = function(item, txt) {
					
					var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length * 2));
					if(angles < 270) {
						angles = 270 - angles;
					} else {
						angles = 360 - angles + 270;
					}
					$('#wheelcanvas').stopRotate();
					$('#wheelcanvas').rotate({
						angle: 0,
						animateTo: angles + 1800,
						duration: 8000,
						callback: function() {
						//	alert("恭喜您："+txt);
						//	alert("##"+$("#value").val());
							//添加中奖纪录到awards表
							$.ajax({
									type:"post",
									url:"${pageContext.request.contextPath}/award/add.do",
									data:{
										awardName:txt,
										value:$("#value").val()
									},
									dataType:"json",
									success:function(data){}
								});
							
							if(confirm("您获得："+txt + "，查看我的中奖记录")) {
								//alert("查看我的中奖记录");
								//根据UesrId 查看中奖纪录
								window.location.href = "${pageContext.request.contextPath}/award/list.do?userid="+$("#userId").val();
						//		$.ajax({
						//			type:"post",
						//			url:"${pageContext.request.contextPath}/award/list.do",
						//			data:{},
						//			dataType:"json",
						//			success:function(data){}
						//		});
								
							}

							turnplate.bRotate = !turnplate.bRotate;
						}
					});
				};

				$('.pointer').click(function() {
					if(turnplate.bRotate) return;
					turnplate.bRotate = !turnplate.bRotate;
					//获取随机数(奖品个数范围内)
					var item = rnd(1, turnplate.restaraunts.length);
					//奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
					rotateFn(item, turnplate.restaraunts[item - 1]);
					/* switch (item) {
						case 1:
							rotateFn(252, turnplate.restaraunts[0]);
							break;
						case 2:
							rotateFn(216, turnplate.restaraunts[1]);
							break;
						case 3:
							rotateFn(180, turnplate.restaraunts[2]);
							break;
						case 4:
							rotateFn(144, turnplate.restaraunts[3]);
							break;
						case 5:
							rotateFn(108, turnplate.restaraunts[4]);
							break;
						case 6:
							rotateFn(72, turnplate.restaraunts[5]);
							break;
						case 7:
							rotateFn(36, turnplate.restaraunts[6]);
							break;
						case 8:
							rotateFn(360, turnplate.restaraunts[7]);
							break;
						case 9:
							rotateFn(324, turnplate.restaraunts[8]);
							break;
						case 10:
							rotateFn(288, turnplate.restaraunts[9]);
							break;
					} */
					console.log(item);
				});
			});
			
			//在点击转盘时就已经计算好了抽到什么奖 n是1，m是6 （奖品的数量）这里返回的是中奖
			function rnd(n, m) {
				var random = Math.floor(Math.random() * (m - n + 1) + n);
			//	alert( Math.random())
				return random;

			}

			//页面所有元素加载完毕后执行drawRouletteWheel()方法对转盘进行渲染
			window.onload = function() {
				drawRouletteWheel();
			};

			function drawRouletteWheel() {
				var canvas = document.getElementById("wheelcanvas");
				if(canvas.getContext) {
					//根据奖品个数计算圆周角度
					var arc = Math.PI / (turnplate.restaraunts.length / 2);
					var ctx = canvas.getContext("2d");
					//在给定矩形内清空一个矩形
					ctx.clearRect(0, 0, 422, 422);
					//strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式  
					ctx.strokeStyle = "#FFBE04";
					//font 属性设置或返回画布上文本内容的当前字体属性
					ctx.font = '16px Microsoft YaHei';
					for(var i = 0; i < turnplate.restaraunts.length; i++) {
						var angle = turnplate.startAngle + i * arc;
						ctx.fillStyle = turnplate.colors[i];
						ctx.beginPath();
						//arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）    
						ctx.arc(211, 211, turnplate.outsideRadius, angle, angle + arc, false);
						ctx.arc(211, 211, turnplate.insideRadius, angle + arc, angle, true);
						ctx.stroke();
						ctx.fill();
						//锁画布(为了保存之前的画布状态)
						ctx.save();

						//----绘制奖品开始----
						ctx.fillStyle = "#E5302F";
						var text = turnplate.restaraunts[i];
						var line_height = 17;
						//translate方法重新映射画布上的 (0,0) 位置
						ctx.translate(211 + Math.cos(angle + arc / 2) * turnplate.textRadius, 211 + Math.sin(angle + arc / 2) * turnplate.textRadius);

						//rotate方法旋转当前的绘图
						ctx.rotate(angle + arc / 2 + Math.PI / 2);

						/** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) **/
						if(text.indexOf("M") > 0) { //流量包
							var texts = text.split("M");
							for(var j = 0; j < texts.length; j++) {
								ctx.font = j == 0 ? 'bold 20px Microsoft YaHei' : '16px Microsoft YaHei';
								if(j == 0) {
									ctx.fillText(texts[j] + "M", -ctx.measureText(texts[j] + "M").width / 2, j * line_height);
								} else {
									ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
								}
							}
						} else if(text.indexOf("M") == -1 && text.length > 6) { //奖品名称长度超过一定范围 
							text = text.substring(0, 6) + "||" + text.substring(6);
							var texts = text.split("||");
							for(var j = 0; j < texts.length; j++) {
								ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
							}
						} else {
							//在画布上绘制填色的文本。文本的默认颜色是黑色
							//measureText()方法返回包含一个对象，该对象包含以像素计的指定字体宽度
							ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
						}

						//添加对应图标
						if(text.indexOf("闪币") > 0) {
							var img = document.getElementById("shan-img");
							img.onload = function() {
								ctx.drawImage(img, -15, 10);
							};
							ctx.drawImage(img, -15, 10);
						} else if(text.indexOf("谢谢参与") >= 0) {
							var img = document.getElementById("sorry-img");
							img.onload = function() {
								ctx.drawImage(img, -15, 10);
							};
							ctx.drawImage(img, -15, 10);
						}
						//把当前画布返回（调整）到上一个save()状态之前 
						ctx.restore();
						//----绘制奖品结束----
					}
				}
			}
			
		</script>
	</head>

	<body style="background:#e62d2d;overflow-x:hidden;">

		<br>
		<!-- 代码 开始 -->
		<c:forEach items="${awardMenus }" var="awardMenu">
			<input id="menu${awardMenu.awardNum}" type="hidden" value="${awardMenu.awardName}">
		</c:forEach>
		<input id="menu1" type="hidden">
		<input id="menu2" type="hidden">
		<input id="menu3" type="hidden">
		<input id="menu4" type="hidden">
		<input id="menu5" type="hidden">
		<input id="menu6" type="hidden">
		<input id="userId" type="hidden" value="${user.id}">
		<input id ="value" name ="value" type="text" value="${user.username},${user.id}"
 		<img src="${pageContext.request.contextPath}/jsp/ots/JF/images/1.png" id="shan-img" style="display:none;" />
		<img src="${pageContext.request.contextPath}/jsp/ots/JF/images/2.png" id="sorry-img" style="display:none;" />
		<div class="banner">
			<h1 align="center" style="margin-bottom: 10px;">活  动  抽  奖  页  面</h1>
			<div class="turnplate" style="background-image:url(${pageContext.request.contextPath}/jsp/ots/JF/images/turnplate-bg.png);background-size:100% 100%;">
				<canvas class="item" id="wheelcanvas" width="422px" height="422px"></canvas>
				<img class="pointer" src="${pageContext.request.contextPath}/jsp/ots/JF/images/turnplate-pointer.png" />
			</div>
		</div>
		<!-- 代码 结束 -->

		<!--<div style="text-align:center;margin:20px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p>来源：<a href="http://www.datouwang.com/" target="_blank">大头网</a></p>
<p>转载请注明出处，此代码仅供学习交流，请勿用于商业用途。</p>
<p><script src="http://www.datouwang.com/img/js/demo_ad.js"></script><center style="display:none"><script src="http://www.datouwang.com/img/js/demo_tj.js"></script></center></p>
</div>
-->
	</body>

</html>