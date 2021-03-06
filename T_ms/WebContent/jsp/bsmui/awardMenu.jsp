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
    
    <!-- 添加   -->
    function addSave(){
    	 var awardName = $("input[name='awardName']").val();
    //	 var awardNum = $("#awardNum").val();
    	  var awardNum = $('#awardNum').combobox('getValue');
		// var form = new FormData(document.getElementById("addTable"));
		if(awardName != ""){
		 $.ajax({
			   type:"post", //请求方式
			   url:"../../awardMenu/insert.do", //发送请求地址
			   data:{
				   awardName: awardName,
				   awardNum:awardNum
			   }, //发送给数据库的数据     
			   dataType : "json",
		//       contentType: false,    
		 //      processData: false,
			   //请求成功后的回调函数有两个参数
			   success:function(data){
				   if(data[0].msg == "success"){
					   $('#dlg').dialog('close');
					   $.messager.alert('提示','修改成功！','info');
					   $('#user').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,phonenum));

				   }
				   else if(data[0].msg == "fail"){
					   $.messager.alert('提示','修改失败！（注意序号不能重复）','info');
				   }
				   else if(data[0].msg == "bigger"){
					   $.messager.alert('提示','数据不能大于6条!','info');
				   }
			   },
			   error: function(e) { 
				   alert('错误'+e); 
				   } 
			   });
		}else{
			$.messager.alert('提示','数据不能为空！','info');
		}
	}
    <!--操作按钮 -->
    function formatOper(val,row,index){
    	if(row.state == '已启用'){
        return '<a href="#"  style="text-decoration:none;" onclick="editState('+index+')">停用</a>'; 
    	}else if(row.state == '未启用'){
    		return '<a href="#"  style="text-decoration:none;" onclick="editState('+index+')">启用</a>';	
    	}
    }
    <!--操作状态 -->
    function editState(index){  
        $('#user').datagrid('selectRow',index);// 关键在这里  
        var row = $('#user').datagrid('getSelected');  
        if (row){
        	if(confirm("确定改变此条状态？")){
        	$.ajax({
				   type:"post", //请求方式
				   url:"../../greens/editState.do?phonenum=12", //发送请求地址
				   data:{
					   menuid:row.menuid,
					   state:row.state
				   }, //发送给数据库的数据     
				   dataType : "json",
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('状态修改成功！');
						   $('#user').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,phonenum));
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
    	var row = $('#user').datagrid('getSelected');
    	//alert(row.awardId +","+row.awardName+","+row.awardNum);
    	if(row){
    		 $("#update_awardId").textbox("setValue",row.awardId);
        	 $("#update_awardName").textbox("setValue",row.awardName);
        	 $("#update_awardNum").textbox("setValue",row.awardNum);
         	$('#update-dlg').dialog('open');
    	}else{
    		$.messager.alert('提示','请选择一行!','info');
    	}
    }
    
    function awardMenu_update(){
    	if($("#update_awardName").textbox("getValue") != ""){
    	$.ajax({
    		url:'../../awardMenu/update.do',
    		data:{
    		 awardId:$("#update_awardId").textbox("getValue"),
    		 awardName:$("#update_awardName").textbox("getValue"),
    		 awardNum:$("#update_awardNum").textbox("getValue")
    		},
    		type:'POST',
    		dataType:'json',
    	    success: function (data) {
		    	if(data[0].msg == "success"){
					   $('#update-dlg').dialog('close');
					   $.messager.alert('提示','修改成功！','info');
					   $('#user').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(messagetime,replytime,userid,state));
				   }
				   else if(data[0].msg == "fail"){
					   $.messager.alert('提示','修改失败！','info');
				   }    
		    } 
    	});
    	}else{
    		$.messager.alert('提示','奖品名不能为空！','info');
    	}
    	
    }

    <!--删除 -->
    function deletedate(){
    	 var _list = {};  
    	 var row = $('#user').datagrid('getSelections'); //多行选择
    	if(row.length > 0){
    		alert("row.length"+row.length+"row.id"+row[0].awardId);
    		//alert("row.id"+row[0].id);
    		for (var i = 0; i < row.length; i++) {  
    		    _list["selectedIDs[" + i + "]"] = row[i].awardId;  
    		}
    		_list["selectedIDsLength"] = row.length;  
    		$.ajax({  
    		    url: '../../awardMenu/delete.do',  
    		    //data: { "selectedIDs": _list },  
    		    data: _list,  
    		    dataType: "json",  
    		    type: "POST",  
    		    //traditional: true,  
    		    success: function (data) {  
    		    	if(data[0].msg == "success"){
						   $('#dlg').dialog('close');
						   alert('删除成功！');
						   $('#user').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,phonenum));
					   }
					   else if(data[0].msg == "fail"){
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
    var phonenum = "";
    
    function Search(){
    	date = $("input[name='date']").val();//使用id 获取不了 这里使用name
    	userName = $("#userName").val();
    	phonenum = $("#phonenum").val();
    	$('#user').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,phonenum));
    };
   function loadData(date,userName,phonenum){
		$('#user').datagrid({
			url: '../../awardMenu/listMenu.do',
			title: '积分菜单',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'awardId',title:'Id',width:80},
				{field:'awardNum',title:'序号',width:80},
				{field:'awardName',title:'奖品名',width:100}
			]],
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
    $(function(){$('#user').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,phonenum));})
    </script>
    </head>
    <body>
    <table id = "user"></table>
    <div id="tb" style="padding:5px;height:auto">
    	<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="update()"plain="true">编辑</a>
		</div>
	</div>
	<!-- dialog 修改对话框 -->
	<div id="update-dlg" class="easyui-dialog" title="修改菜单" style="display: none;overflow: hidden; width:400px;height:300px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#update-dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="updateTable"  method="post">
			<table>
				<tr><td>ID:</td><td><input id="update_awardId" name ="update_awardId" class='easyui-textbox'  disabled="true"  style="width:250px"></td></tr>
				<tr><td>序号：</td><td><input id="update_awardNum" name="update_awardNum" class='easyui-textbox' disabled="true" style="width:250px" ></td></tr>
				<tr><td>奖品名称：&nbsp;</td><td><input id="update_awardName"  name="update_awardName"class='easyui-textbox' data-options="prompt:'请输入字符',missingMessage:'此处不能为空',invalidMessage:'长度为3~15之间',required:true,validType:'length[3,15]'" style="width:250px"></td></tr>
 			   </tr>
			</table>
			
	<div id="update-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="awardMenu_update()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#update-dlg').dialog('close')">取消</a>
	</div>
		</form>
	</div>
	<!-- dialog 添加对话框 -->
	<div id="dlg" class="easyui-dialog" title="添加奖品" style="display: none;overflow: hidden; width:400px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="addTable" enctype="multipart/form-data" method="post">
			<table>
				<tr><td>奖品名称</td><td><input id="awardName" name="awardName" class='easyui-textbox' data-options="prompt:'请输入菜名',missingMessage:'此处不能为空',invalidMessage:'长度为3~10之间',required:true,validType:'length[3,10]'" style="width:250px"></td></tr>			
				<tr><td>序号</td>
					<td>
						<select id="awardNum" name="awardNum" class="easyui-combobox" panelHeight='auto'>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
						</select>
					</td>
				</tr>	
			
			</table>
		
<!--		<table>
				<tr><td>添加菜名：</td><td><input id="userName" name="userName"  class='easyui-textbox' data-options="prompt:'请输入菜名',missingMessage:'此处不能为空',invalidMessage:'长度为3~10之间',required:true,validType:'length[3,10]'" style="width:250px"></td></tr>
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
  -->			</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addSave()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	</body>
	
</html>

