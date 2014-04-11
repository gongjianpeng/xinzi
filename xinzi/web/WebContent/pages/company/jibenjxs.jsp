<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="../../themes/apusic/om-all.css" />
		<link href="../../css/css.css" rel="stylesheet" type="text/css" />
		<script src="../../jquery/js/jquery.min.js"></script>
		<script src="../../ui/om-core.js"></script>
		<script src="../../ui/om-mouse.js"></script>
		<!-- by make start -->
		<link href="../../css/all_css.css" rel="stylesheet" type="text/css" />
		
		<!-- by make end -->
		<script src="../../ui/om-tree.js"></script>
		<script src="../../ui/om-menu.js"></script>
		<script src="../../ui/om-draggable.js"></script> 
		<script src="../../ui/om-position.js"></script>
		<script src="../../ui/om-resizable.js"></script>
		<script src="../../ui/om-dialog.js"></script>
		<script src="../../ui/om-messagebox.js"></script>
		<script src="../../ui/om-messagetip.js"></script>
		<script src="../../ui/om-buttonbar.js"></script>
		<script src="../../ui/om-button.js"></script>
		<script src="../../ui/om-ajaxsubmit.js"></script>
		<script src="../../ui/om-grid.js"></script>
		<script src="../../ui/om-grid-headergroup.js"></script>
		<script src="../../ui/om-validate.js"></script>
		<script src="../../ui/om-combo.js"></script>
		<script src="../../ui/om-calendar.js"></script>
		<script src="../../ui/om-panel.js"></script>
		<script src="../../ui/om-tabs.js"></script>
		<script src="../../ui/om-borderlayout.js"></script>
		<script src="../../ui/om-fileupload.js"></script>
		<style>
html,body {
	font-size: 14px;
	font-family: tahoma;
	border: 0px;
	padding: 0px;
	margin: 0px;
	height: 100%;
	width: 100%;
	overflow: hidden;
}

div#confirm {
	height: 25px;
	line-height: 25px;
	font-weight: bold;
}

div#tipCon {
	height: 25px;
	line-height: 25px;
	font-weight: bold;
}

.om-grid div.hDiv td {
	text-align: center;
}

.om-grid img {
	cursor: pointer;
	vertical-align: middle !important;
	margin-top: -5px;
	padding: 0px;
	border: 0px;
}

.om-grid a {
	cursor: pointer;
	vertical-align: middle !important;
	font-weight: normal;
	font-size: 12px;
}

.om-grid div.bDiv td {
	vertical-align: middle !important;
	cursor: pointer;
}

.search {
	background-image: url(../../images/icons/search.gif) !important;
}

input[type='text'] {
	margin: 0px;
	padding: 0px;
	height: 18px;
	width: 140px
}
</style>
		<script>
		var gridData;
		$(document).ready(function() {
          $('body').omBorderLayout({
			   panels:[
			        {
			       		id:"panel", 
			   	        resizable:false,
			   	        collapsible:false,
			   	        title: decodeURI("${param.windowname}"),
			   	        region:"north",
			   	        height:57
			        },
			        {
			   	        id:"jibenjxsDiv",
			   	     	header:false,
			   	        region:"center"
			   	    }],
			   	    fit:true,
			   	    spacing:2
	    }); 
			$('#jibenjxsTable').omGrid({
             title : '基本参数列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="addButton"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="updateButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="deleteButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '</div>',
		        dataSource : 'getParameterjxs.do' ,
		        limit : 15,
		        showIndex : false,
		        autoFit : true,
		        height:'fit',
		        colModel : [{header : "参数名称", name : 'name', align : 'center'},                              
                              {header : "价格", name : 'price', align : 'center'},
                              {header : "单位", name : 'unit', align : 'center'},
                              {header : "尺寸", name : 'chichun', align : 'center'},
                              {header : "规格", name : 'guige', align : 'center'},
                              {header : "备注", name : 'remark', align : 'center'}
                             
               			 ],							
				onRowDblClick:function(rowIndex,rowData){
				    isAdd = false;
			        $('#operation').val("update");
                    showDialog("修改",rowData);//Display dialog
			
				}
	        });
	        
	        var dialog = $("#dialog-form").omDialog({
            	height:400,
        		width:430,
        		resizable : false,
                autoOpen : false,
                modal : true,
                resizable : false,
                buttons : {
                    "确定" : function(){
		                submitDialog();
		                return false; //Block form's default submit action
		            },
                    "取消" : function() {
                        $("#dialog-form").omDialog("close");//Close dialog
                    }
                }
            });
	       	
			var showDialog=function(title,rowdata){
			    var data = rowdata || {} ;
			    $("input[name='id']",dialog).val(data.id);
		        $("input[name='name']",dialog).val(data.name); 
			    $("input[name='price']",dialog).val(data.price); 
			    $("input[name='unit']",dialog).val(data.unit);
			    $("input[name='chichun']",dialog).val(data.chichun); 
			    $("input[name='guige']",dialog).val(data.guige); 
			    $("textArea[name='remark']",dialog).val(data.remark);
			    	$("input[name='org']",dialog).val(data.org);
                 	$("input[name='createPerson']",dialog).val(data.createPerson);
                 	$("input[name='createPersonId']",dialog).val(data.createPersonId);
	            dialog.omDialog("option", "title",title);
                dialog.omDialog("open");// show the dialog
	        };
	        function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#jibenjxsTable').omGrid('setData','getParameterjxs.do');
				 	$.omMessageTip.show({title: "提示", content: "操作成功", timeout: 1500});
			 	} else {
				 	$.omMessageTip.show({title: "提示", content: "操作失败", timeout: 1500});
			 	}
			}
            var options = {
				success : showResponse
			  };
	       	var submitDialog = function(){
	       		if(validator.form()){
	       			$("#ipForm").omAjaxSubmit(options);
	       		}
	       		return false;
	      	};
	      	
            var isAdd = true; //Is the add operation in a pop-up window or modify the operation?
            $('#addButton').die().live('click', function() {
            	
                isAdd = true;
                $('#operation').val("add");
                showDialog("<fmt:message key='dialog.submit.add'/>");//Display dialog
            });
            $('#updateButton').die().live('click', function() {
                var selections=$('#jibenjxsTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                     $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
           			 return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("修改",selections[0]);//Display dialog
            });
            
            $('#deleteButton').die().live('click', function(e) {
                var selections=$('#jibenjxsTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                     $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
           			return false;
                }
                $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要删除吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("delete");
		                var toDeleteRecordID=selections[0].id;
		                $.post('saveParameterjxs.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#jibenjxsTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             $('#query').bind('click', function(e) {
                var qName=$('#jibenName').val();
                 $('#jibenjxsTable').omGrid("setData", 'getParameterjxs.do?jibenName='+encodeURI(qName));
              });
            
            // Validate form
            var validator = $('#ipForm').validate({
                rules : { 
                    name : {required : true}
                }, 
                messages : {
                    name : {required : "*"}
                }
            });
			
        });
    </script>
    
</head>
<fmt:bundle basename="messages.messages_quote" />
<body style="overflow: hidden">
	<div id="panel">
	<form id="queryForm">
		<table class="table1" style="float: left">
			<tr class="tr1">
				<td class="td1">
				参数名称:<input id="jibenName" name="jibenName" class="input1"/>
				<input id="query" type="button" class="button09"  value="查询"/></td>
			</tr>
		</table>
	</form>
	</div>
	<div id="jibenjxsDiv" class="div1">
		<table id="jibenjxsTable" class="table2"></table>
	</div>
		
	<div id="dialog-form">
	<form id="ipForm" action="saveParameterjxs.do" method="post"><input name="operation" id="operation" style="display: none" /> <input name="id" style="display: none">
		<table>
			<tr>
				<td style="float: right;">参数名称：</td>
				<td><input name="name" id="name"></td>
			</tr>
			<tr>
				<td style="float: right;">价格：</td>
				<td><input name="price" id="price" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"></td>
			</tr>
			<tr>
				<td style="float: right;">单位：</td>
				<td><input id="unit" name="unit" /></td>
			</tr>
			<tr>
				<td style="float: right;">尺寸：</td>
				<td><input id="chichun" name="chichun" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"/></td>
			</tr>
			<tr>
				<td style="float: right;">规格：</td>
				<td><input id="guige" name="guige" /></td>
			</tr>
			<tr>
				<td style="float: right; line-height:60px;">备注：</td>
				<td><textarea name="remark"	></textarea></td>
			</tr>
			<tr style="display: none;">
				<td><input id="org" name="org"></input></td>
				<td><input id="createPerson" name="createPerson"></input></td>
				<td><input id="createPersonId" name="createPersonId"></input></td>
			</tr>
		</table>
		</form>
		</div>
	</body>
</html>