<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript"src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
    var date = "";
    var loginname = "";
    
    function Search(){
    	date = $("input[name='date']").val();<!--使用id 获取不了 这里使用name  -->
    	loginname = $("#loginname").val();
    	$('#loginlog').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,loginname));
    };
   function loadData(date,loginname){
		$('#loginlog').datagrid({
			url: '../../loglogin/list.do?date='+date+'&loginname='+loginname,
			title: '登录日志',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'id',title:'id',width:80},
				{field:'role',title:'角色',width:100},
				{field:'time',title:'时间',width:150},
				{field:'userid',title:'登录id',width:80},
				{field:'username',title:'登录名',width:100}
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
    $(function(){$('#loginlog').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,loginname));})
    </script>
    </head>
    <body>
    <table id = "loginlog"></table>
    <div id="tb" style="padding:5px;height:auto">
		<div>
			登录日期: <input id ="date" name = "date" class="easyui-datebox" style="width:150px">
			登录名:<input id = "loginname" class='easyui-textbox' style="width:150px"> 
			<a href="#" class="easyui-linkbutton" onclick="Search()" iconCls="icon-search">查询</a>
		</div>
	</div>
	</body>
</html>

