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
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/extend_validatebox.js"></script>
	<script type="text/javascript"src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>


    <script type="text/javascript">
    <!--操作按钮 -->
    function formatOper(val,row,index){
    	if(row.state == '已处理'){
    		return '---';
    	}else{
    		return '<a href="#"  style="text-decoration:none;" onclick="editState('+index+')">处理</a>';	
    	}
    }
    <!--操作状态 -->
    function editState(index){  
        $('#order').datagrid('selectRow',index);// 关键在这里  
        var row = $('#order').datagrid('getSelected');  
        if (row){
        	if(confirm("确定改变此条状态？")){
        	$.ajax({
				   type:"post", //请求方式
				   url:"../../reserveTable/editState.do", //发送请求地址
				   data:{
					   reserveId:row.reserveId
				   }, //发送给数据库的数据     
				   dataType : "json",
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "success"){
						   $('#dlg').dialog('close');
						   alert('状态修改成功！');
						   $('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(reserve_date,phonenum,linkman,state));
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
						   $('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,phonenum,linkman,state));
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
    
    
    var reserve_date = "";
    var phonenum = "";
    var state = "";
    var linkman = "";
    
    function Search(){
    	reserve_date = $("input[name='reserve_date']").val();//使用id 获取不了 这里使用name
    	phonenum = $("#phonenum").val();
    	state = $('#state').combobox('getValue');<!--easyui中获取select选中值 -->
    	linkman = $("#linkman").val();
    	$('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(reserve_date,phonenum,linkman,state));
    };
   function loadData(reserve_date,phonenum,linkman,state){
		$('#order').datagrid({
			url: '../../reserveTable/list.do?reserve_date='+reserve_date+'&phonenum='+phonenum+'&linkman='+linkman+'&state='+state,
			title: '订单管理',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'reserveId',title:'Id',width:80},
				{field:'userId',title:'用户Id',width:150},
				{field:'reserveDate',title:'预定日期',width:80},
				{field:'reserveTime',title:'用桌时间段',width:80},
				{field:'phonenum',title:'预留电话',width:150},
				{field:'linkman',title:'联系人姓名',width:80},
				{field:'state',title:'状态',width:200},
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
    $(function(){$('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(reserve_date,phonenum,linkman,state));})
    </script>
    </head>
    <body>
    <table id = "order"></table>
    <div id="tb" style="padding:5px;height:auto">
		<div>
			预定日期: <input id ="reserve_date" name = "reserve_date" class="easyui-datebox" style="width:150px">
			电话号码:<input id = "phonenum" class='easyui-textbox' style="width:150px">
			预定人姓名:<input id = "linkman" class='easyui-textbox' style="width:150px">
			处理状态: 
			<select id = "state" name="state" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="">—请选择—</option>
				<option value="已处理">已处理</option>
				<option value="未处理">未处理</option>
			</select>&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" onclick="Search()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- dialog 添加对话框 -->
	<div id="dlg" class="easyui-dialog" title="添加食物" style="display: none;overflow: hidden; width:400px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="addTable" enctype="multipart/form-data" method="post">
			<table>
				<tr><td>添加菜名：</td><td><input id="phonenum" name="phonenum"  class='easyui-textbox' data-options="prompt:'请输入菜名',missingMessage:'此处不能为空',invalidMessage:'长度为3~10之间',required:true,validType:'length[3,10]'" style="width:250px"></td></tr>
				<tr><td>原价（￥）：&nbsp;</td><td><input id="oriPrice"  name="oriPrice"class='easyui-textbox' data-options="prompt:'原价',missingMessage:'此处不能为空',required:true,validType:['currency','length[1,5]']" style="width:250px"></td></tr>
				<tr><td>现价（￥）：&nbsp;</td><td><input id="curPrice" name="curPrice" class='easyui-textbox' data-options="prompt:'现价',missingMessage:'此处不能为空',required:true,validType:['currency','length[1,5]']"  style="width:250px"></td></tr>
				<tr><td>添加图片：</td><td><input id="pictrueUrl"  name="pictrueUrl" class='easyui-filebox' data-options="missingMessage:'此处不能为空',required:true"  style="width:250px;height:30px"></td></tr>
				<tr><td>描述：&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input id="describe" name="describe" type="message" class='easyui-textbox' data-options="multiline:true,prompt:'描述',missingMessage:'此处不能为空',required:true,validType:'length[1,80]'" style="width:250px;height:60px"></td></tr>
				<tr><td>食物类型：</td><td><select id="type" name="type" class="easyui-combobox" name="language" panelHeight='auto' >
												<option value="特色菜">特色菜</option>
												<option value="美味套餐">美味套餐</option>
												<option value="饮品">饮品</option>
												<option value="家常菜">家常菜</option>
												<option value="小吃">小吃</option>
										  </select></td></tr>
				<tr> <td style="text-align:right;">状态：&nbsp;&nbsp;&nbsp;&nbsp;</td>
       									 <td style="text-align:left">
									            <span class="radioSpan">
									                <input id="state" name="state" type="radio" name="adminFlag" value="已启用" >已启用</input>
									                <input id="state" name="state" type="radio" name="adminFlag" value="未启用" checked="checked">未启用</input>
									            </span>
      								     </td>
 			   </tr>
 			   <tr><td><input type="reset"   value="重置"></td></tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addSave()">保存</a>
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
						   $('#order').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(reserve_date,phonenum,linkman,state));
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

