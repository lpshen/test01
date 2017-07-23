﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<%  
   //页面每隔30秒自动刷新一遍       
 //  response.setHeader("refresh" , "30" );  
%> 
<html lang="en">
	<head>
	<meta charset="UTF-8">
	<title>后台管理系统</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/extend_validatebox.js"></script>
	<script type="text/javascript"src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>


    <script type="text/javascript">
    <!--操作按钮 -->
    function formatOper(val,row,index){
    	if(row.state == '已启用'){
        return '<a href="#"  onclick="editState('+index+')">充值</a>'; 
    	}else if(row.state == '未启用'){
    		return '---';	
    	}
    }
    <!--操作状态 -->
    function editState(index){
    	 $('#pay_record').datagrid('selectRow',index);// 关键在这里  
         var row = $('#pay_record').datagrid('getSelected');
    	 $("#userid_rechar").val(row.id);
    	 $("#userName_rechar").val(row.username);
    	 $("#credits_rechar").val(row.credits);
    	 $("#money_rechar").val(row.money);
    	 
     	$('#dlg').dialog('open');
       
    } 
    
    <!--删除 -->
    function deletedate(){
    	 var _list = {};  
    	 var row = $('#pay_record').datagrid('getSelections'); //多行选择
    	if(row.length > 0){
    		alert("row.length"+row.length+"row.id"+row[0].id);
    		//alert("row.id"+row[0].id);
    		for (var i = 0; i < row.length; i++) {  
    		    _list["selectedIDs[" + i + "]"] = row[i].id;  
    		}
    		_list["selectedIDsLength"] = row.length;  
    		$.ajax({  
    		    url: '../../user/delete.do',  
    		    //data: { "selectedIDs": _list },  
    		    data: _list,  
    		    dataType: "json",  
    		    type: "POST",  
    		    //traditional: true,  
    		    success: function (data) {  
    		    	if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('删除成功！');
						   $('#pay_record').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,ordernum));
					   }
					   else if(data[0].msg == "失败"){
						   alert('删除失败！');
					   }    
    		    }  
    		}); 
    	}else{
    		alert("请选择行");
    	}

    }
    
    
    var date = "";
    var userName = "";
    var ordernum = "";
    
    function Search(){
    	date = $("input[name='date']").val();//使用id 获取不了 这里使用name
    	userName = $("#userName").val();
    	ordernum = $("#ordernum").val();
    	$('#pay_record').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,ordernum));
    };
   function loadData(date,userName,ordernum){
		$('#pay_record').datagrid({
			url: '../../recharge/list_pay.do?date='+date+'&userName='+userName+'&ordernum='+ordernum,
			title: '用户消费记录',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'id',title:'Id',width:80},
				{field:'username',title:'用户名',width:80},
				{field:'money',title:'消费金额（￥）',width:80},
				{field:'ordernum',title:'订单编号',width:150},
				{field:'time',title:'消费时间',width:150},
				{field:'state',title:'用户状态',width:80}
			]],
			pagination:true,
			pageSize:'20',
			toolbar:'#tb'
			
		});
	}
    
    function pagerFilter(data){
		if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
			data = {
				total: data.length,
				rows: data
			}
		}
		var dg = $(this);
		var opts = dg.datagrid('options');
		var pager = dg.datagrid('getPager');
		pager.pagination({
			onSelectPage:function(pageNum, pageSize){
				opts.pageNumber = pageNum;
				opts.pageSize = pageSize;
				pager.pagination('refresh',{
					pageNumber:pageNum,
					pageSize:pageSize
				});
				dg.datagrid('loadData',data);
			}
		});
		if (!data.originalRows){
			data.originalRows = (data.rows);
		}
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = (data.originalRows.slice(start, end));
		return data;
	}
    <!--客户端分页 -->
    $(function(){$('#pay_record').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,ordernum));})
    </script>
    </head>
    <body>
    <table id = "pay_record"></table>
    <div id="tb" style="padding:5px;height:auto">
		<div>
			消费日期: <input id ="date" name = "date" class="easyui-datebox" style="width:150px">
			用户名:<input id = "userName" class='easyui-textbox' style="width:150px">
			订单编号:<input id = "ordernum" class='easyui-textbox' style="width:150px">
			<a href="#" class="easyui-linkbutton" onclick="Search()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- dialog 添加对话框 -->
	<div id="dlg" class="easyui-dialog" title="账户充值" style="display: none;overflow: hidden; width:400px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="rechargeTable" enctype="multipart/form-data" method="post">
			<table>
				<tr><td>用户id：</td><td><input id="userid_rechar" name="userid_rechar"  disabled="true "></td></tr>
				<tr><td>用户名：&nbsp;</td><td><input id="userName_rechar" disabled="true "></td></tr>
				<tr><td>积分：&nbsp;</td><td><input id="credits_rechar" disabled="true "></td></tr>
				<tr><td>账户余额（￥）：&nbsp;</td><td><input id="money_rechar" disabled="true "></td></tr>
				<tr><td>充值金额：&nbsp;</td><td><input id="remoney_rechar" name="remoney_rechar" class='easyui-textbox' data-options="prompt:'现价',missingMessage:'此处不能为空',required:true,validType:['currency','length[1,5]']"  style="width:150px"></td></tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="recharge()">确认充值</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	</body>
	<script type="text/javascript">
		function recharge(){
			 $.ajax({
				   type:"post", //请求方式
				   url:"../../user/recharge.do", //发送请求地址
				   data:{
					   userid:$("#userid_rechar").val(),
					   remoney:$("#remoney_rechar").val()
				   }, //发送给数据库的数据     
				   dataType : "json",
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('充值成功！');
						   $('#pay_record').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,ordernum));
					   }
					   else if(data[0].msg == "失败"){
						   alert('充值失败！');
					   }   
				   },
				   error: function(e) { 
					   alert('错误'+e); 
					   } 
				   });
		}
	</script>
</html>

