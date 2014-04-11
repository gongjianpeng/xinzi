
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
<title>
<fmt:message key='datadictionary.title' />
</title>
<style type="text/css">
html,body {
	font-size: 14px;
	font-family: tahoma;
	border:0px;
	padding:0px;
	margin:0px;
	height:100%;
	width:100%;
	overflow:hidden;
}
.om-grid div.bDiv td {
	border-bottom: 1px solid #fff;
	vertical-align: top;
	white-space: nowrap;
	cursor: pointer;
}
.om-grid div.hDiv td {
	text-align: center;
}
.om-grid img{
	cursor:pointer;
	vertical-align: middle !important;
	margin-top: -5px;
	padding:0px;
	border:0px;
}
.om-grid a{
	cursor: pointer;
	vertical-align: middle !important;
	font-weight:normal;
	font-size: 12px;
}

.om-grid div.bDiv td {
	vertical-align: middle !important;
	cursor: pointer;
}
.search {
	background-image: url(../../images/icons/search.gif) !important;
}
</style>
<link rel="stylesheet" type="text/css" href="../../themes/apusic/om-all.css" />
<link href="../../css/css.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript">
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
				   	        id:"dataDictionaryDiv",
				   	        //title:"",
				   	     	header:false,
				   	        region:"center"
				   	    }],
				   	    fit : true,
				   	    spacing:2
		    });
		    
        
            $('#dataDictionaryTable').omGrid({
             title : '订单数据列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		            '</div>',
	           //	dataSource :'getDataDictionarys.do',
	           	limit : 0,
	            height : 'fit',
                width : '100%',
                showIndex : false,
                colModel : [ 
                               {header : "订单编号", name : 'code', align : 'left', width : $('#dataDictionaryDiv').outerWidth()*0.2}, 
                              {header : "订单名称", name : 'name', align : 'left', width : $('#dataDictionaryDiv').outerWidth()*0.2},
                              {header : "订单时间", name : 'type', align : 'left', width :  $('#dataDictionaryDiv').outerWidth()*0.2},
                              {header : "备注", name : 'remark', align : 'left', width : $('#dataDictionaryDiv').outerWidth()*0.2}
              
                ],
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
            var dialog = $("#dialog-form").omDialog({
            	height:490,
        		width:500,
                autoOpen : false,
                modal : true,
                resizable : true,
                buttons : {
                    "<fmt:message key='dialog.submit'/>" : function(){
		                submitDialog();
		                return false; //Block form's default submit action
		            },
                    "<fmt:message key='dialog.cancel'/>" : function() {
                        $("#dialog-form").omDialog("close");//Close dialog
                    }
                }
            });
            //Displays a dialog and initialized the data entered into the box
            var showDialog = function(title,rowData){
                rowData = rowData || {};
                $("input[name='id']",dialog).val(rowData.id);
                $("input[name='code']",dialog).val(rowData.code);
                $("input[name='name']",dialog).val(rowData.name);
                if(rowData.type!=null)
               		$("input[name='type']",dialog).val(rowData.type);
                if(rowData.remark!=null)
                	$("input[name='remark']",dialog).val(rowData.remark);
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	//$('#dataDictionaryTable').omGrid('setData','getDataDictionarys.do');
				 	alert("excute success!");
			 	} else {
				 	alert("excute failed!");
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
            $('#modify').die().live('click', function() {
                var selections=$('#dataDictionaryTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    alert("<fmt:message key='dialog.submit.modify.confirm'/>");
                    return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
            $('#delete').die().live('click', function(e) {
                var selections=$('#dataDictionaryTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    alert("<fmt:message key='dialog.submit.modify.confirm'/>");
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
		                $.post('dataDictionaryOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             
              $('#starttime').omCalendar();
              $('#endtime').omCalendar();
             
             $('#query').bind('click', function(e) {
               var qCode=$('#qCode').val();
                var qName=$('#qName').val();
                var qType=$('#qType').val();
                if(qCode==""&&qName===""&&qType==""){ //No query criteria to, to display all data
                    $('#dataDictionaryTable').omGrid("setData", 'operateDataDictionary.do');
                }else{ //Query, display the query data
                    $('#dataDictionaryTable').omGrid("setData", 'operateDataDictionary.do?qCode='+encodeURI(qCode)+'&qName='+encodeURI(qName)+'&qType='+encodeURI(qType));
                }
                
            });
            
            // Validate form
            var validator = $('#ipForm').validate({
                rules : {
                    code : {required: true}, 
                    name : {required : true},
                    type : {required : true}
                }, 
                messages : {
                    code : {required : "<fmt:message key='table.code.confirm'/>"},
                    name : {required : "<fmt:message key='table.name.confirm'/>"},
                    type : {required : "<fmt:message key='table.type.confirm'/>"}
                }
            });
			
        });
    </script>
    

</head>
<fmt:bundle basename="classpath:messages.messages_dataDictionary" />
<body style="overflow: hidden">
<div id="panel">
<form id="queryForm">
<table border="0" cellspacing="0" cellpadding="0">
   <div>
	<tr height="30" align="left">
		<td text-align:center align="right">
		订单编号：</font> <input id="qType">
		订单名称：</font> <input id="qType" >
		开始时间：</font> <input id="starttime" > 
		结束时间：</font> <input id="endtime" >
		 <input id="query" type="button" value="<fmt:message key='datadictionary.search'/>" class="button09" />
		</td>
	</tr>
	</div>
</table>
</form>
</div>

<div id="dataDictionaryDiv" style="width: 'auto'">
<table id="dataDictionaryTable"></table>
</div>
<div id="dialog-form">
<form id="ipForm" action="dataDictionaryOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
<table>
	<tr>
		<td>
		<fmt:message key='datadictionary.code' />：</td>
		<td><input name="code" id="dcode"></td>
	</tr>
	<tr>
		<td>
		<fmt:message key='datadictionary.name' />：</td>
		<td><input name="name" id="dname"></td>
	</tr>
	<tr>
		<td><fmt:message key='datadictionary.type' />：</td>
		<td><input id="type" style="width: 150px" name="type" /></td>
	</tr>
	<tr>
		<td><fmt:message key='datadictionary.remark' />：</td>
		<td><input id="remark" style="width: 150px" name="remark" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input id="dengjiben" style="width: 150px"style="display: none" name="dengjiben"/></td>
		<td></td>
		<td><input id="dengjiXiao" style="display: none"  name="dengjiXiao" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input id="dengjiao"  style="display: none" name="dengjiao" /></td>
		<td></td>
		<td><input id="dengjiTong" style="display: none"  name="dengjiTong" /></td>
	</tr>
</table>
</form>
</div>
</body>
</html>