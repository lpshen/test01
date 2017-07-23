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
    	if(row.paystate == '未支付'){
    		return '---';
    	}else{
    	if(row.mastate == '已处理'){
        return '---'; 
    	}else if(row.mastate == '未处理'){
    		return '<a href="#"  style="text-decoration:none;" onclick="editState('+index+')">处理</a>';	
    	}
    	}
    }
    
    function formatO(val,row,index){
    	return '<a href="#"  style="text-decoration:none;" onclick="findMX('+row.ordernum+')">详情</a>';	
    }
    //订单明细查询
    function findMX(ordernum){
    	alert("ordernum"+ordernum);
    	$.ajax({
			   type:"post", //请求方式
			   url:"../../order/orderdetailHT.do", //发送请求地址
			   data:{
				   ordernum:ordernum
			   }, //发送给数据库的数据     
			   dataType : "json",
			   //请求成功后的回调函数有两个参数
			   success:function(data){
 					alert("json"+data.length);
 					var text1 ="<tr><th>菜名</th><th>类型</th><th>单价（￥）</th><th>数量（份）</th></tr>" ;
 					for(var i=0;i< data.length;i++){
 						text1 +="<tr><td>"+data[i].grename+"</td><td>"+data[i].type+"</td><td>"+data[i].oriprice+"</td><td>"+data[i].count+"</td></tr>";
 					}
 					var text2 = "<tr><td>总价：</td><td>"+data[0].totalprice+"</td></tr>";
 					text2 += "<tr><td>联系人：</td><td>"+data[0].name+"</td></tr>";
 					text2 += "<tr><td>电话：</td><td>"+data[0].phonenum+"</td></tr>";
 					text2 += "<tr><td>时间：</td><td>"+data[0].time+"</td></tr>";
 					text2 += "<tr><td>地址：</td><td>"+data[0].address+"</td></tr>";
 					$("#orderDetail1").html(text1);
 					$("#orderDetail2").html(text2);
 					$('#dlg').dialog('open');
 					
			   },
			   error: function(e) { 
				   alert('错误'+e); 
				   } 
			   }); 
    	
    }
    <!--操作状态 -->
    function editState(index){  
        $('#order').datagrid('selectRow',index);// 关键在这里  
        var row = $('#order').datagrid('getSelected');  
        if (row){
        	if(confirm("确定改变此条状态？")){
        	$.ajax({
				   type:"post", //请求方式
				   url:"../../order/editState.do", //发送请求地址
				   data:{
					   ordernum:row.ordernum
				   }, //发送给数据库的数据     
				   dataType : "json",
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "success"){
						   $('#dlg').dialog('close');
						   alert('状态修改成功！');
						   $('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,orderNum,userid,type));
					   }
					   else if(data[0].msg == "fail"){
						   alert('状态修改失败！');
					   }   
				   },
				   error: function(e) { 
					   alert('错误'+e); 
					   } 
				   }); 
        }  
        }
    } 
    <!--删除 -->
    function deletedate(){
    	 var _list = {};  
    	 var row = $('#order').datagrid('getSelections'); //多行选择
    	if(row.length > 0){
    		alert("row.length"+row.length+"row.id"+row[0].menuid);
    		//alert("row.id"+row[0].menuid);
    		for (var i = 0; i < row.length; i++) {  
    		    _list["selectedIDs[" + i + "]"] = row[i].orderid;  
    		}
    		_list["selectedIDsLength"] = row.length;  
    		$.ajax({  
    		    url: '../../order/delete.do',  
    		    //data: { "selectedIDs": _list },  
    		    data: _list,  
    		    dataType: "json",  
    		    type: "POST",  
    		    //traditional: true,  
    		    success: function (data) {  
    		    	if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('删除成功！');
						   $('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,orderNum,userid,type));
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
    var orderNum = "";
    var type = "";
    var userid = "";
    
    function Search(){
    	date = $("input[name='date']").val();//使用id 获取不了 这里使用name
    	orderNum = $("#orderNum").val();
    	type = $('#type').combobox('getValue');<!--easyui中获取select选中值 -->
    	userid = $("#userid").val();
    	$('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,orderNum,userid,type));
    };
   function loadData(date,orderNum,userid,type){
		$('#order').datagrid({
			url: '../../order/list.do?date='+date+'&orderNum='+orderNum+'&userid='+userid+'&type='+type,
			title: '订单管理',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'orderid',title:'Id',width:80},
				{field:'ordernum',title:'订单编号',width:200},
				{field:'merid',title:'商家id',width:80},
				{field:'userid',title:'用户id',width:80},
				{field:'totalprice',title:'总价',width:80},
				{field:'time',title:'添加时间',width:200},
				{field:'paystate',title:'支付状态',width:80},
				{field:'mastate',title:'处理状态',width:80},
				{field:'message',title:'明细',width:100, formatter:formatO},
				{field:'_operate',title:'操作',width:80,align:'center',formatter:formatOper}
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
    $(function(){$('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,orderNum,userid,type));})
    </script>
    </head>
    <body>
    <table id = "order"></table>
    <div id="tb" style="padding:5px;height:auto">
		<div>
			添加日期: <input id ="date" name = "date" class="easyui-datebox" style="width:150px">
			订单编号:<input id = "orderNum" class='easyui-textbox' style="width:150px">
			用户id:<input id = "userid" class='easyui-textbox' style="width:150px">
			处理状态: 
			<select id = "type" name="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="">—请选择—</option>
				<option value="已处理">已处理</option>
				<option value="未处理">未处理</option>
			</select>&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" onclick="Search()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- dialog 明细展示对话框 -->
	<div id="dlg" class="easyui-dialog" title="订单明细" style="display: none;overflow: hidden; width:400px;height:auto;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->

				<table id="orderDetail1">
				</table>
				
				<table id="orderDetail2">
				</table>


				
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	</body>
	<script type="text/javascript">
		function addSave(){
			 var form = new FormData(document.getElementById("addTable"));
			 $.ajax({
				   type:"post", //请求方式
				   url:"../../greens/insert.do?userid=12", //发送请求地址
				   data:form, //发送给数据库的数据     
				   dataType : "json",
			       contentType: false,    
			       processData: false,
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('修改成功！');
						   $('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,orderNum,userid,type));
					   }
					   else if(data[0].msg == "失败"){
						   alert('修改失败！');
					   }   
				   },
				   error: function(e) { 
					   alert('错误'+e); 
					   } 
				   });
		}
	</script>
</html>

