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

#jsTable{
    width: 400px;
    position: relative;
    left: 32%;
}
 #jsTable tr{
    position: relative;
    display: block;
    margin: 5px 0;
    } 

#med p{
    font-family: 微软雅黑;
    font-size: 20px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ots/laydate/laydate.js"></script>

<script type="text/javascript">
	function submit() {
		var date = $("input[name = 'date']").val();
		var tableType = $("select[name = 'tableType']").val();
		var useTime = $("select[name = 'useTime']").val();
		var diningTableId = $("select[name = 'diningTableId']").val();
		var phoneNum = $("input[name = 'phoneNum']").val();
		var linkMan = $("input[name = 'linkMan']").val();
		var userId = $("#userId").val();
		//	alert("date:"+date+"tableType"+tableType+"useTime"+useTime+"ID"+diningTableId+"phoneNum"+phoneNum+"linkMan:"+linkMan+"UserId"+userId);
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/reserveTable/insert.do",
			data : {
				date : date,
				tableType : tableType,
				useTime : useTime,
				diningTableId : diningTableId,
				phoneNum : phoneNum,
				linkMan : linkMan,
				userId : userId
			},
			dataType : "json",
			success : function(data) {
				if (data[0].msg == "success") {
					alert("添加成功");
				} else if (data[0].msg == "fail") {
					alert("添加失败");
				} else if (data[0].msg == "exists") {
					alert("失败，该时段本餐位已被预定,请更更换你的选择");
				}
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
			<p align="center">餐桌预定</p>
			<hr>
			<br>
			<form id="jsTable">
				<input id="userId" type="hidden" value="${user.id}">
				<table>
					<tr>
						<td>就餐日期：</td>
						<td><input name="date" class="laydate-icon"
							onclick="laydate()" placeholder="必填" required
							data-msg-required="不能为空"></td>
					</tr>
					<tr>
						<td>就餐时间：</td>
						<td><select name="useTime" required>
								<option value="">--请选择--</option>
								<option value="09:00">09:00</option>
								<option value="11:00">11:00</option>
								<option value="13:00">13:00</option>
								<option value="15:00">15:00</option>
								<option value="17:00">17:00</option>
								<option value="19:00">19:00</option>
								<option value="21:00">21:00</option>
						</select></td>
					</tr>
					<tr>
						<td>就餐人数：</td>
						<td><select name="tableType" required>
								<option value="">--请选择--</option>
								<option value="2">2</option>
								<option value="4">4</option>
								<option value="6">6</option>
								<option value="8">8</option>
								<option value="10">10</option>
						</select></td>
					</tr>
					<tr>
						<td>选择桌号：</td>
						<td><select name="diningTableId" required>
								<option value="">--请选择--</option>
						</select></td>
					</tr>
					<tr>
						<td>手机号码：</td>
						<td><input name="phoneNum" type="text" placeholder="手机号"
							required data-rule-mobile="true" data-msg-required="请输入手机号"
							data-msg-mobile="请输入正确格式"></td>
						<td></td>
					</tr>
					<tr>
						<td>联系人：&nbsp;</td>
						<td><input name="linkMan" type="text" placeholder="必填"
							required data-msg-required="不能为空" data-msg-minlength="请输入最小2位"
							minlength="2" maxlength="15"></td>
					</tr>
					<tr>
						<tr>
							<td colspan="2"><input style="margin: 15px 12px 0px 100px"
								class="box11" type="submit" value="提交"><input
								type="reset"></td>
						</tr>
					</tr>
				</table>
			</form>
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

		$("#jsTable").validate({
			submitHandler : function() {
				//验证通过后 的js代码写在这里
				submit();
			}
		})

		$("select[name = 'tableType']")
				.change(
						function() {
							if ($(this).val() == "") {
								$("select[name = 'diningTableId']").empty();
								return;
							}
							$("select[name = 'diningTableId']").empty();
							$
									.ajax({
										type : "post",
										url : "${pageContext.request.contextPath}/diningTable/listByTableType.do",
										data : {
											tableType : $(this).val()
										},
										dataType : "json",
										success : function(data) {

											//	$("#tableNum").append("<option value='"+0+"'>请选择</option>");
											for (var i = 0; i < data.length; i++) {
												//显示桌号 传的是Id
												$(
														"select[name = 'diningTableId']")
														.append(
																"<option value='"+data[i].diningTableId+"'>"
																		+ data[i].tableNum
																		+ "</option>");
											}
										}
									});
						});
	})
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.extend.js"></script>
</html>