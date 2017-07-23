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
    	if(row.state == '未回复'){
        return '<a href="#"  style="text-decoration:none;" onclick="editState('+index+')">回复</a>'; 
    	}else if(row.state == '已回复'){
    		return '---';	
    	}
    }
    <!--操作状态 -->
    function editState(index){  
        $('#message').datagrid('selectRow',index);// 关键在这里  
        var row = $('#message').datagrid('getSelected');  
        $("#reply_userid").val(row.userid);
        $("#reply_content").val(row.content);
    	$("#reply_id").val(row.id);
    	$('#dlg').dialog('open');
    } 
   
    //批量回复
    function batchReply(){
   	 var _list = {};  
   	 var row = $('#message').datagrid('getSelections'); //多行选择
   	if(row.length > 0){
   		//alert("row.length"+row.length);
   		//alert("row.id"+row[0].menuid);
   		for (var i = 0; i < row.length; i++) {  
   		    _list["selectedIDs[" + i + "]"] = row[i].id;  
   		}
   		_list["selectedIDsLength"] = row.length;  //所选行数
   		_list["batch_reply"] = type = $('#pl_reply').combobox('getValue');//获取批量回复内容
   		$.ajax({  
   		    url: '../../message/batch_reply.do',  
   		    //data: { "selectedIDs": _list },  
   		    data: _list,  
   		    dataType: "json",  
   		    type: "POST",  
   		    //traditional: true,  
   		    success: function (data) {  
   		    	if(data[0].msg == "成功"){
						   $('#pldlg').dialog('close');//关闭对话框
						   alert('回复成功！');
						   $('#message').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(messagetime,replytime,userid,state));//加载页面
					   }
					   else if(data[0].msg == "失败"){
						   alert('回复失败！');
					   }    
   		    }  
   		}); 
   	}else{
   		alert("请选择行");
   	}

   }

    <!--删除 -->
    function deletedate(){
    	 var _list = {};  
    	 var row = $('#message').datagrid('getSelections'); //多行选择
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
						   alert('删除成功！');
						   $('#message').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(messagetime,replytime,userid,state));
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
    
    //回复打开窗口
    function open_dlg(){
    	var row = $('#message').datagrid('getSelected');
    	if(row){
    		 $("#reply_id").val(row.id);
    		 $("#reply_userid").val(row.userid);
    		 $("#reply_content").val(row.content);
    		 $('#dlg').dialog('open');
    	}else{
    		alert("请选择行");
    	}
    }
    
    
    var messagetime = "";
    var replytime = "";
    var state = "";
    var userid = "";
    
    function Search(){
    	messagetime = $("input[name='messagetime']").val();//使用id 获取不了 这里使用name
    	replytime = $("input[name='replytime']").val();
    	state = $('#state').combobox('getValue');<!--easyui中获取select选中值 -->
    	userid = $("#userid").val();
    	$('#message').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(messagetime,replytime,userid,state));
    };
   function loadData(messagetime,replytime,userid,state){
		$('#message').datagrid({
			url: '../../message/listBySearch.do?messagetime='+messagetime+'&replytime='+replytime+'&userid='+userid+'&state='+state,
			title: '留言管理',
			height:'560px',
			fitColumns: true,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'id',title:'Id',width:50},
				{field:'userid',title:'用户Id',width:50},
				{field:'adminid',title:'商户Id',width:50},
				{field:'content',title:'用户留言',width:150},
				{field:'messagetime',title:'留言时间',width:150},
				{field:'reply',title:'回复',width:150},
				{field:'replytime',title:'回复时间',width:150},
				{field:'replyid',title:'回复人id',width:80},
				{field:'evaluate',title:'评价',width:50},
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
    $(function(){$('#message').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(messagetime,replytime,userid,state));})
    </script>
    </head>
    <body>
    <table id = "message"></table>
    <div id="tb" style="padding:5px;height:auto">
    	<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" onclick="open_dlg()" iconCls="icon-add" plain="true">回复</a>
			<a href="#" class="easyui-linkbutton" onclick="$('#pldlg').dialog('open')"iconCls="icon-edit" plain="true">批量回复</a>
		</div>
		<div>
			留言日期: <input id ="messagetime" name = "messagetime" class="easyui-datebox" style="width:150px">
			回复日期: <input id ="replytime" name = "replytime" class="easyui-datebox" style="width:150px">
			用户id:<input id = "userid" class='easyui-textbox' style="width:150px">
			回复状态: 
			<select id = "state" name="state" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="">—请选择—</option>
				<option value="未回复">未回复</option>
				<option value="已回复">已回复</option>
				
			</select>&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" onclick="Search()" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- dialog 单条回复对话框 -->
	<div id="dlg" class="easyui-dialog" title="回复" style="display: none;overflow: hidden; width:400px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#dlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="addTable"  method="post">
			<table >
				<tr><td>留言id：</td><td><input id="reply_id" disabled="true"></td></tr>
				<tr><td>用户id：</td><td><input id="reply_userid" disabled="true"></td></tr>
				<tr><td>留言内容：&nbsp;</td><td><textarea id="reply_content" name="reply_content" disabled="true"  style="width:250px;height:60px;">这里写内容</textarea></td></tr>
				<tr><td>回复：&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input id="reply_reply" class='easyui-textbox' data-options="multiline:true,prompt:'填写回复内容',missingMessage:'此处不能为空',required:true,validType:'length[1,80]'" style="width:250px;height:60px"></td></tr>
			</table>
			
		</form>
	</div>
	
		<!-- dialog 批量回复对话框 -->
	<div id="pldlg" class="easyui-dialog" title="批量回复" style="display: none;overflow: hidden; width:300px;height:250px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: '#pldlg-buttons',
				closed: true          
			">
			   <!--closed : true 解决加载时对话框弹出 -->
		<form id="addTable"  method="post">
			<table >
				<tr><td>批量回复：</td><td><select id="pl_reply" name="pl_reply" class="easyui-combobox" name="language" panelHeight='auto' >
												<option value="感谢亲的惠顾！小店将持续提供美味">感谢亲的惠顾！小店将持续提供美味</option>
												<option value="感谢亲提出的宝贵意见！">感谢亲提出的宝贵意见！</option>
												<option value="小店主美出了鼻涕泡">小店主美出了鼻涕泡！</option>
												<option value="客官常来啊">客官常来啊！</option>
												<option value="店小二正在热情赶来">店小二正在热情赶来!</option>
										  </select></td></tr>
			</table>
			
		</form>
	</div>
	<div id="pldlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="batchReply()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#pldlg').dialog('close')">取消</a>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="replySingle()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	</body>
	<script type="text/javascript">
	
	//单条回复
	function replySingle(){
			 $.ajax({
				   type:"post", //请求方式
				   url:"../../message/replySingle.do", //发送请求地址
				   data:{
				        reply :$("#reply_reply").val(),
				    	messageid :$("#reply_id").val()
				   }, //发送给数据库的数据     
				   dataType : "json",
				   //请求成功后的回调函数有两个参数
				   success:function(data){
					   if(data[0].msg == "成功"){
						   $('#dlg').dialog('close');
						   alert('回复成功！');
						   $('#message').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(date,userName,phonenum));
					   }
					   else if(data[0].msg == "失败"){
						   alert('回复失败！');
					   }   
				   },
				   error: function(e) { 
					   alert('错误'+e); 
					   } 
				   });
		}
	

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
						   $('#message').datagrid({loadFilter:pagerFilter}).datagrid('loadData', loadData(messagetime,replytime,userid,state));
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

