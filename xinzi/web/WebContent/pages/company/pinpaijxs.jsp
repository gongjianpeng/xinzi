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
		<!-- by make start -->
		<link href="../../css/all_css.css" rel="stylesheet" type="text/css" />
		
		<!-- by make end -->
		<script src="../../jquery/js/jquery.min.js"></script>
		<script src="../../ui/om-core.js"></script>
		<script src="../../ui/om-mouse.js"></script>
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
			   	        id:"pinpaijxsDiv",
			   	     	header:false,
			   	        region:"center"
			   	    }],
			   	    fit:true,
			   	    spacing:2
	    }); 
			$('#pinpaijxsTable').omGrid({
             title : '品牌列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		             '<a id="lockButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="解除授权审核" />&nbsp;解除授权审核&nbsp;</a>'+
		            '</div>',
		        dataSource : 'getCompanyqiye3.do' ,
		        limit : 15,
		        showIndex : false,
		        height : 'fit',
		        autoFit : true,
		        height:'fit',
		        colModel : [{header : "品牌名称", name : 'pinpai', align : 'center'},                              
                              {header : "公司名称", name : 'companyname', align : 'center'},
                              {header : "解除授权申请", name : 'status', align : 'center'},
                              {header : "品牌文化", name : 'culture', align : 'center'}
               			 ],							
				onRowDblClick:function(rowIndex,rowData){
				    isAdd = false;
			        $('#operation').val("update");
                //    showDialog("修改",rowData);//Display dialog
			
				}
	        });
	        
	        var dialog = $("#dialog-form").omDialog({
            	height:400,
        		width:460,
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
			    $("input[name='logo']",dialog).val(data.logo); 
			    $("input[name='contents']",dialog).val(data.contents); 
			    $("input[name='culture']",dialog).val(data.culture);
			    $("input[name='FileUrl']",dialog).val(data.FileUrl);
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
				 	$('#pinpaijxsTable').omGrid('setData','getCompanyqiye2.do');
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
            $('#create').die().live('click', function() {
            	
                isAdd = true;
                $('#operation').val("add");
                showDialog("<fmt:message key='dialog.submit.add'/>");//Display dialog
            });
            $('#updateButton').die().live('click', function() {
                var selections=$('#pinpaijxsTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
                    return false;
                }
           
                           var toDeleteRecordID=selections[0].id;
                               var  pinpai=selections[0].pinpai;
                               alert(""+toDeleteRecordID+pinpai );
                isAdd = false;
                $('#operation').val("update");
                showDialog("修改成功",selections[0]);//Display dialog
            });
         
            
            $('#deleteButton').die().live('click', function(e) {
                var selections=$('#pinpaijxsTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                   $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
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
		                $.post('saveBrandjxs.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#pinpaijxsTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             
               $('#lockButton').die().live('click', function(e) {
                
                var selections=$('#pinpaijxsTable').omGrid('getSelections',true);
                 var status=selections[0].status;
                if (selections.length == 0) {
                   $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
                    return false;
                }else
                 if (status=='truelock'||status=='') {
                   // var type=selections[0].type;
                    $.omMessageBox.alert({title:'提示',content:'授权的品牌暂未收到解除授权消息！'});
                   
                    return false;
                }if(status=='收到品牌解除申请'){
                $.omMessageBox.confirm({
                   title:'提示',
                   content:'确定要解除授权品牌吗 ?',
                   onClose:function(value){
                        if(value==false)
                        	return false;
                        $('#operation').val("truelock");
		                var toDeleteRecordID=selections[0].id;
		                $.post('companyqiyeOperate.do',{operation:'truelock',id:toDeleteRecordID},function(){
		                    $('#pinpaijxsTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
                 }
             });
             
             $('#query').bind('click', function(e) {
                var qName=$('#brandName').val();
               
                if(qName==""){ 
                    $('#pinpaijxsTable').omGrid("setData", 'getCompanyqiye2.do');
                
                  }
                  else{
                 $('#pinpaijxsTable').omGrid("setData", 'getCompanyqiye2.do?pinpai='+encodeURI(qName));
                 }
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
<fmt:bundle basename="messages.messages_brandjxs" />
<body style="overflow: hidden">
	<div id="panel">
	<form id="queryForm">
		<table class="table1" style="float: left">
			<tr class="tr1">
				<td class="td1">
				品牌名称:<input id="brandName" name="brandName" class="input1"/>
				<input id="query" type="button" class="button09"  value="查询"/></td>
			</tr>
		</table>
	</form>
	</div>
	<div id="pinpaijxsDiv" class="div1">
		<table id="pinpaijxsTable" class="table2"></table>
	</div>
		
	<div id="dialog-form">
	<form id="ipForm" action="saveBrandjxs.do" method="post"><input name="operation" id="operation" style="display: none" /> 
	<input name="id" id="id" style="display: none">
		<table>
			<tr>
				<td>品牌名称：</td>
				<td><input name="name" id="name"></td>
			</tr>
			<tr>
				<td>品牌logo：</td>
				<td><input name="logo" id="logo"></input></td>
			</tr>
			<!--<tr>
				<td>品牌路径：</td>  
				<td><input name="FileUrl" id="FileUrl"></input></td>
			
			</tr>
			--><tr>
				<td>品牌介绍：</td>
				<td><textarea name="contents" ></textarea></td>
			</tr>
			<tr>
				<td>品牌文化：</td>
				<td><textarea name="culture" ></textarea></td>
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