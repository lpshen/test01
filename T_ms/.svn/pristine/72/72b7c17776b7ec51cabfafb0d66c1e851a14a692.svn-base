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
    	if(row.state == '已启用'){
        return '<a href="#"  style="text-decoration:none;" onclick="editState('+index+')">停用</a>'; 
    	}else if(row.state == '未启用'){
    		return '<a href="#"  style="text-decoration:none;"  onclick="editState('+index+')">启用</a>';	
    	}
    }
    <!--操作状态 -->
    function editState(index){  
        $('#shop').datagrid('selectRow',index);// 关键在这里  
        var row = $('#shop').datagrid('getSelected');  
        if (row){
        	if(confirm("确定改变此条状态？")){
        	$.ajax({
				   type:"post", //请求方式
				   url:"../../shop/editState.do?userid=12", //发送请求地址
				   data:{
					   id:row.id,
					   state:row.state
				   }, //发送给数据库的数据     
				   dataType : "json",
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('状态修改成功！');
						   $('#shop').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,shopName,userid,type));
					   }
					   else if(data[0].msg == "失败"){
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
    <!--修改 -->
    function update(){
    	 var row = $('#shop').datagrid('getSelected');
    	 if(row){
    		 $('#update_id').val(row.id);
    		 $('#update_shopName').textbox('setValue',row.shopname);
    		 $('#update_address').textbox('setValue',row.address);
    		 $('#update_phonenum').textbox('setValue',row.phonenum);
    		 $('#update_pictureurl').textbox('setValue',row.pictureurl);
    		 $('#update-dlg').dialog('open');

    	 }else{
    		 $.messager.alert('提示','请选择行','info');
    	 }
    }
    function shop_update(){
    	$.ajax({
    		url:'../../shop/update.do',
    		data:{
    			id:$('#update_id').val(),
    			shopName:$('#update_shopName').textbox('getValue'),
    			address:$('#update_address').textbox('getValue'),
    			phonenum:$('#update_phonenum').textbox('getValue'),
    			pictureUrl:$('#update_pictureurl').textbox('getValue')
    		},
    		dataType:'json',
    		type:'POST',
    		success:function(data){
    			if(data[0].msg=='成功'){
    				$.messager.alert('提示','修改成功！','info');
    				$('#update-dlg').dialog('close');
					   $('#shop').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,shopName,userid,type));
    			}else if(data[0].msg == '失败'){
    				$.messager.alert('提示','修改失败！','info');
    			}
    			
    		}
    	});
    }
    <!--删除 -->
    function deletedate(){
    	 var _list = {};  
    	 var row = $('#shop').datagrid('getSelections'); //多行选择
    	if(row.length > 0){
    		alert("row.length"+row.length+"row.id"+row[0].id);
    		for (var i = 0; i < row.length; i++) {  
    		    _list["selectedIDs[" + i + "]"] = row[i].id;  
    		}
    		_list["selectedIDsLength"] = row.length;  
    		$.ajax({  
    		    url: '../../shop/delete.do',  
    		    //data: { "selectedIDs": _list },  
    		    data: _list,  
    		    dataType: "json",  
    		    type: "POST",  
    		    //traditional: true,  
    		    success: function (data) {  
    		    	if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('删除成功！');
						   $('#shop').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,shopName,userid,type));
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
    var shopName = "";
    var userid = "";
    
    function Search(){
    	date = $("input[name='date']").val();//使用id 获取不了 这里使用name 
    	shopName = $("#shopName").val();
    	userid = $("#userid").val();
    	$('#shop').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,shopName,userid));
    };
   function loadData(date,shopName,userid){
		$('#shop').datagrid({
			url: '../../shop/list.do?date='+date+'&shopName='+shopName+'&userid='+userid,
			title: '店铺管理',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'id',title:'Id',width:50},
				{field:'adminid',title:'商家Id',width:80},
				{field:'shopname',title:'店铺名',width:100},
				{field:'address',title:'地址',width:150},
				{field:'descri',title:'描述',width:150},
				{field:'phonenum',title:'电话',width:100},
				{field:'pictureurl',title:'图片路径',width:150},
				{field:'time',title:'添加时间',width:150},
				{field:'state',title:'状态',width:50},
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
    $(function(){$('#shop').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,shopName,userid));})
    </script>
    </head>
    <body>
    <table id = "shop"></table>
    <div id="tb" style="padding:5px;height:auto">
    	<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')" iconCls="icon-add" plain="true">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="update()" plain="true">编辑</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="deletedate()" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			添加日期: <input id ="date" name = "date" class="easyui-datebox" style="width:150px">
			店铺名:<input id = "shopName" class='easyui-textbox' style="width:150px">
			商家id:<input id = "userid" class='easyui-textbox' style="width:150px">
			<a href="#" class="easyui-linkbutton" onclick="Search()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- dialog 添加对话框 -->
	<div id="dlg" class="easyui-dialog" title="添加店铺" style="display: none;overflow: hidden; width:400px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="addTable" enctype="multipart/form-data" method="post">
			<table>
				<tr><td>店家名称：</td><td><input id="shopName" name="shopName"  class='easyui-textbox' data-options="prompt:'请输入店名',missingMessage:'此处不能为空',invalidMessage:'长度为3~10之间',required:true,validType:'length[3,15]'" style="width:250px"></td></tr>
				<tr><td>地址：&nbsp;</td><td><input id="address"  name="address"class='easyui-textbox' data-options="prompt:'地址',missingMessage:'此处不能为空',required:true,validType:'length[1,25]'" style="width:250px"></td></tr>
				<tr><td>电话：&nbsp;</td><td><input id="phonenum" name="phonenum" class='easyui-textbox' data-options="prompt:'电话',missingMessage:'此处不能为空',required:true,validType:'mobile'"  style="width:250px"></td></tr>
				<tr><td>添加图片：</td><td><input id="pictrueUrl"  name="pictrueUrl" class='easyui-filebox' data-options="missingMessage:'此处不能为空',required:true"  style="width:250px;height:30px"></td></tr>
				<tr><td>描述：&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input id="describe" name="describe" type="message" class='easyui-textbox' data-options="multiline:true,prompt:'描述',missingMessage:'此处不能为空',required:true,validType:'length[1,80]'" style="width:250px;height:60px"></td></tr>
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
	
		<!-- dialog 修改对话框 -->
	<div id="update-dlg" class="easyui-dialog" title="添加店铺" style="display: none;overflow: hidden; width:400px;height:300px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#update-dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="updateTable"  method="post">
			<table>
				<tr><td>店铺ID:</td><td><input id = "update_id" disabled="true"></td></tr>
				<tr><td>店家名称：</td><td><input id="update_shopName"  class='easyui-textbox' data-options="prompt:'请输入店名',missingMessage:'此处不能为空',invalidMessage:'长度为3~10之间',required:true,validType:'length[3,15]'" style="width:250px"></td></tr>
				<tr><td>地址：&nbsp;</td><td><input id="update_address"  class='easyui-textbox' data-options="prompt:'地址',missingMessage:'此处不能为空',required:true,validType:'length[1,25]'" style="width:250px"></td></tr>
				<tr><td>电话：&nbsp;</td><td><input id="update_phonenum"  class='easyui-textbox' data-options="prompt:'电话',missingMessage:'此处不能为空',required:true,validType:'mobile'"  style="width:250px"></td></tr>
				<tr><td>图片路径：</td><td><input id="update_pictureurl"   class='easyui-textbox' data-options="missingMessage:'此处不能为空',required:true"  style="width:250px;height:30px"></td></tr>
			</table>
		</form>
	</div>

	<div id="update-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="shop_update()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#update-dlg').dialog('close')">取消</a>
	</div>
	</body>
	<script type="text/javascript">
		function addSave(){
			 var form = new FormData(document.getElementById("addTable"));
			 $.ajax({
				   type:"post", //请求方式
				   url:"../../shop/insert.do?userid=12", //发送请求地址
				   data:form, //发送给数据库的数据     
				   dataType : "json",
			       contentType: false,    
			       processData: false,
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						  // alert('修改成功！');
						  $.messager.alert('My Title','修改成功！');
						   $('#shop').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,shopName,userid));
					   }
					   else if(data[0].msg == "失败"){
						  // alert('修改失败！');
						   $.messager.alert('My Title','修改成功！');
					   }   
				   },
				   error: function(e) { 
					   alert('错误'+e); 
					   } 
				   });
		}
	</script>
</html>

