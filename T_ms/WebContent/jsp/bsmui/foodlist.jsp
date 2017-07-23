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
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>


    <script type="text/javascript">
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
        $('#food').datagrid('selectRow',index);// 关键在这里  
        var row = $('#food').datagrid('getSelected');  
        if (row){
        	if(confirm("确定改变此条状态？")){
        	$.ajax({
				   type:"post", //请求方式
				   url:"../../greens/editState.do?userid=12", //发送请求地址
				   data:{
					   menuid:row.menuid,
					   state:row.state
				   }, //发送给数据库的数据     
				   dataType : "json",
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   $.messager.alert('提示','状态修改成功！','info');
						   $('#food').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,foodName,userid,type));
					   }
					   else if(data[0].msg == "失败"){
						   $.messager.alert('提示','状态修改失败！','info');
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
    	var row = $('#food').datagrid('getSelected');
    	if(row){
    		 $("#update_menuid").val(row.menuid);
        	 $("#update_foodName").textbox("setValue",row.grename);
        	 $("#update_oriPrice").textbox("setValue",row.oriprice);
        	 $("#update_curPrice").textbox("setValue",row.curprice);
        	 $("#update_pictureUrl").textbox("setValue",row.pictureurl);
        	 
         	$('#update-dlg').dialog('open');
    	}else{
    		$.messager.alert('提示','请选择一行!','info');
    	}
    }
    function food_update(){
    	$.ajax({
    		url:'../../greens/update.do',
    		data:{
       		 menuid:$("#update_menuid").val(),
        	 foodName:$("#update_foodName").textbox("getValue"),
        	 oriPrice:$("#update_oriPrice").textbox("getValue"),
        	 curPrice:$("#update_curPrice").textbox("getValue"),
        	 pictureUrl:$("#update_pictureUrl").textbox("getValue")	
    		},
    		type:'POST',
    		dataType:'json',
    	    success: function (data) {
    	    	alert("@"+ data[0]);
		    	if(data[0].msg == "success"){
					   $('#update-dlg').dialog('close');
					   $.messager.alert('提示','修改成功！','info');
					   $('#food').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(messagetime,replytime,userid,state));
				   }
				   else if(data[0].msg == "failed"){
					   $.messager.alert('提示','修改失败！','info');
				   }    
		    } 
    	});
    	
    }
    <!--删除 -->
    function deletedate(){
    	 var _list = {};  
    	 var row = $('#food').datagrid('getSelections'); //多行选择
    	if(row.length > 0){
    		//alert("row.length"+row.length);
    		//alert("row.id"+row[0].menuid);
    		for (var i = 0; i < row.length; i++) {  
    		    _list["selectedIDs[" + i + "]"] = row[i].menuid;  
    		}
    		_list["selectedIDsLength"] = row.length;  
    		$.ajax({  
    		    url: '../../greens/delete.do',  
    		    //data: { "selectedIDs": _list },  
    		    data: _list,  
    		    dataType: "json",  
    		    type: "POST",  
    		    //traditional: true,  
    		    success: function (data) {
    		    	if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   $.messager.alert('提示','删除成功！','info');
						   $('#food').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,foodName,userid,type));
					   }
					   else if(data[0].msg == "失败"){
						   $.messager.alert('提示','删除失败！','info');
					   }    
    		    }  
    		}); 
    	}else{
    		$.messager.alert('提示','请选择行！','info');
    	}

    }
    
    
    var date = "";
    var foodName = "";
    var type = "";
    var userid = "";
    
    function Search(){
    	date = $("input[name='date']").val();//使用id 获取不了 这里使用name
    	foonName = $("#foodName").val();
    	type = $('#type').combobox('getValue');<!--easyui中获取select选中值 -->
    	userid = $("#userid").val();
    	$('#food').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,foodName,userid,type));
    };
   function loadData(date,foodName,userid,type){
		$('#food').datagrid({
			url: '../../greens/list.do?date='+date+'&foodName='+foodName+'&userid='+userid+'&type='+type,
			title: '菜单管理',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'menuid',title:'Id',width:80},
				{field:'merid',title:'商家Id',width:80},
				{field:'grename',title:'菜名',width:100},
				{field:'oriprice',title:'原价（￥）',width:50},
				{field:'curprice',title:'现价（￥）',width:50},
				{field:'type',title:'类型',width:100},
				{field:'pictureurl',title:'图片路径',width:200},
				{field:'time',title:'添加时间',width:150},
				{field:'state',title:'状态',width:80},
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
    $(function(){$('#food').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,foodName,userid,type));})
    </script>
    </head>
    <body>
    <table id = "food"></table>
    <div id="tb" style="padding:5px;height:auto">
    	<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')" iconCls="icon-add" plain="true">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="update()"plain="true">编辑</a>
			<a href="#" class="easyui-linkbutton" onclick="deletedate()" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			添加日期: <input id ="date" name = "date" class="easyui-datebox" style="width:150px">
			菜名:<input id = "foodName" class='easyui-textbox' style="width:150px">
			商家id:<input id = "userid" class='easyui-textbox' style="width:150px">
			菜的类型: 
			<select id = "type" name="type" class="easyui-combobox" panelHeight="auto" style="width:100px"><!--panelHeight="auto" 下拉框长度自适应  -->
				<option value="">—请选择—</option>
				<option value="特色菜">特色菜</option>
				<option value="美味套餐">美味套餐</option>
				<option value="饮品">饮品</option>
				<option value="家常菜">家常菜</option>
				<option value="小吃">小吃</option>
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
				<tr><td>添加菜名：</td><td><input id="foodName" name="foodName"  class='easyui-textbox' data-options="prompt:'请输入菜名',missingMessage:'此处不能为空',invalidMessage:'长度为3~10之间',required:true,validType:'length[3,10]'" style="width:250px"></td></tr>
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
				<tr><td>菜单ID:</td><td><input id="update_menuid"  disabled="true" ></td></tr>
				<tr><td>菜名：</td><td><input id="update_foodName" name="update_foodName" class='easyui-textbox' data-options="prompt:'请输入菜名',missingMessage:'此处不能为空',invalidMessage:'长度为3~10之间',required:true,validType:'length[3,10]'" style="width:250px" ></td></tr>
				<tr><td>原价（￥）：&nbsp;</td><td><input id="update_oriPrice"  name="update_oriPrice"class='easyui-textbox' data-options="prompt:'原价',missingMessage:'此处不能为空',required:true,validType:['currency','length[1,5]']" style="width:250px"></td></tr>
				<tr><td>现价（￥）：&nbsp;</td><td><input id="update_curPrice" name="update_curPrice" class='easyui-textbox' data-options="prompt:'现价',missingMessage:'此处不能为空',required:true,validType:['currency','length[1,5]']"  style="width:250px"></td></tr>
				<tr><td>图片路径：</td><td><input id="update_pictureUrl"  name="update_pictureUrl" class='easyui-textbox' data-options="missingMessage:'此处不能为空',required:true"  style="width:250px"></td></tr>
 			   </tr>
			</table>
			
	<div id="update-dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="food_update()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#update-dlg').dialog('close')">取消</a>
	</div>
		</form>
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
						   $.messager.alert('提示','修改成功！','info');
						   $('#food').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,foodName,userid,type));
					   }
					   else if(data[0].msg == "失败"){
						   $.messager.alert('提示','修改失败！','info');
					   }   
				   },
				   error: function(e) { 
					   alert('错误'+e); 
					   } 
				   });
		}
	</script>
</html>

