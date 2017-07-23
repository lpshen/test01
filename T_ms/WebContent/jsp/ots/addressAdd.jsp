<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加地址</title>
<style>span.error{color:#C00; padding:0 6px;font-size:12px;font-family:'微软雅黑';}</style>
</head>
	<link href="${pageContext.request.contextPath}/jsp/ots/css/style.css" rel="stylesheet" type="text/css" />
<body>
<jsp:include  page="head.jsp"/> 
<div id="main">
	<div id="left">
		<jsp:include page="left.jsp"/>
	</div>
	<div id="med">
	<p align="center" style="font-size: 30px;">添加地址</p>
	
	<!-- 主要部分 -->
		<form  action="${pageContext.request.contextPath}/address/addSubmit.do" method="post" id ="addAddress">
		<table align="center">
		<tr>
			<td>省</td><td>
				<select  name="province" required>
					<option value="">--请选择--</option>
					<c:forEach items="${provinces}" var="province">
						<option value="${province.code}">${province.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>市</td><td>
				<select  name="city" required>
					<option value="">--请选择--</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>区</td><td>
				<select  name="area" required>
					<option value="">--请选择--</option>
				</select>
			</td>	
		</tr>
		<tr><td>详细地址：</td><td><input name = "address" type="text" placeholder="必填" required data-msg-required="不能为空" maxlength="40"></td></tr>
		<tr><td>邮编：</td><td><input  name ="postcode" type="text" placeholder="必填" required data-msg-required="不能为空" maxlength="6"></td></tr>
		<tr><td>收件人：</td><td><input  name="name" type="text" placeholder="必填" required data-msg-required="不能为空" data-msg-minlength="请输入最小2位" minlength="2" maxlength="15"></td></tr>
		<tr><td>收件人电话：</td><td><input  name = "phonenum" type="text" placeholder="手机号" required data-rule-mobile="true" data-msg-required="请输入手机号" data-msg-mobile="请输入正确格式"></td></tr>
		<tr></td><td><td><input name ="userid" type="hidden" value="${user.id }"></td></tr>
		<tr><td align="center"><input  type="submit" value="提交"></td><td>
				<input name="reset" type="reset" value="取消"></td></tr>
		</table>
		</form>
	<!-- 主要部分结束 -->
	</div>
	<div id="right">
		<jsp:include page="right.jsp"/>
	</div>
</div>
<jsp:include  page="bottom.jsp"/> 

</body>
<script type="text/javascript">
$(function(){
	
    //jquery.validate
	$("#addAddress").validate({
		submitHandler: function() {
			//验证通过后 的js代码写在这里
				this.submit();
		}
	})
	
	$("select[name = 'province']").change(function(){
		if($(this).val() == ""){
			return;
		}
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/area/listBySearch.do",
			data:{
			parent_id:$(this).val(),
		    type:3 //市级
		    }, 
			dataType:"json",
			success:function(data){
				$("select[name = 'city']").empty();
				$("select[name = 'area']").empty();
				$("select[name = 'city']").append("<option value='"+2+"'>请选择</option>");
				for(var i = 0;i<data.length;i++){
					$("select[name = 'city']").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");						
				}
			}
		});
		
	})
	$("select[name = 'city']").change(function(){
		if($(this).val() == ""){
			return;
		}
		$("select[name = 'area']").empty();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/area/listBySearch.do",
			data:{
			parent_id:$(this).val(),
		    type:4 //区级
		    }, 
			dataType:"json",
			success:function(data){
				for(var i = 0;i<data.length;i++){
					$("select[name = 'area']").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");						
				}
			}
		});
		
	})

		
})
</script>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ots/js/jquery.validate.extend.js"></script>
</html>