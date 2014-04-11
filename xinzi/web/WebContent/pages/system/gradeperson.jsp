
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
				   	        height:80
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
		    
        
            $('#dataDengjiTable').omGrid({
             title : '等级列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="create"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '</div>',
	           	dataSource :'getDataDengJi.do',
	           	limit : 0,
	            height : 'fit',
                width : '100%',
                colModel : [ 
                   {header : "等级编号" , name : 'dengjiId', align : 'left',width : $('#dataDictionaryDiv').outerWidth()*0.1},
                          {header : "类型", name : 'type' , align : 'left',width : $('#dataDictionaryDiv').outerWidth()*0.1},
                                {header : "职称", name : 'dengjiName' , align : 'left',width : $('#dataDictionaryDiv').outerWidth()*0.1},
                                {header : "基本工资" , name : 'dengjiBen', align : 'left' ,width : $('#dataDictionaryDiv').outerWidth()*0.1},
                                {header : "公司绩效" , name : 'dengjiXiao', align : 'left',width : $('#dataDictionaryDiv').outerWidth()*0.1}, 
                                {header : "交通费" , name : 'dengjiao', align : 'left',width : $('#dataDictionaryDiv').outerWidth()*0.1}, 
                                {header : "通讯费" , name : 'dengjiTong', align : 'left',width : $('#dataDictionaryDiv').outerWidth()*0.1}
                   
                ],
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
            
            var dialog = $("#dialog-form").omDialog({
            	height:500,
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
                $("input[name='dengjiId']",dialog).val(rowData.dengjiId);
                $("input[name='dengjiName']",dialog).val(rowData.dengjiName);
                $("input[name='dengjiBen']",dialog).val(rowData.dengjiBen);
                $("input[name='dengjiXiao']",dialog).val(rowData.dengjiXiao);
                $("input[name='dengjiao']",dialog).val(rowData.dengjiao);
                $("input[name='dengjiTong']",dialog).val(rowData.dengjiTong);
                $("input[name='dengtype']",dialog).val(rowData.dengtype);
                
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#dataDengjiTable').omGrid('setData','getDataDengji.do');
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
            	/*var $code=$("#dcode").val();
            	if($code==null){
            		alert("请你输入编码!");
            	}*/
            
                isAdd = true;
                $('#operation').val("add");
                showDialog("<fmt:message key='dialog.submit.add'/>");//Display dialog
            });
            $('#modify').die().live('click', function() {
                var selections=$('#dataDengjiTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    alert("<fmt:message key='dialog.submit.modify.confirm'/>");
                    return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
            $('#delete').die().live('click', function(e) {
                var selections=$('#dataDengjiTable').omGrid('getSelections',true);
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
		                $.post('DengJiOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#dataDengjiTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
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
    
<script type="text/javascript">
/*function CheckBaseAdd(){

	var code=$("#dcode").val();
	var type=$("#type").val();
	if(code=="www"){
		alert(code+"编码已经存在!");
		$("#dcode").val("");
		$("#dcode").focus(); 
		return false;
	} 
	if(type=="wite"){
		alert(type+"类型已经存在!");
		$("#type").val(""); 
		$("#type").focus(); 
		return false;
	}else {
		$("#type").focus();
		$("#dcode").focus();
		return true;
	}
 }
 */
</script>
</head>
<fmt:bundle basename="classpath:messages.messages_dataDictionary" />
<body style="overflow: hidden">
<div id="panel">
</div>

<div id="dataDictionaryDiv" style="width: 'auto'">
<table id="dataDengjiTable"></table>
</div>
<div id="dialog-form">
<form id="ipForm" action="DengJiOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
<table>
	<tr>
		<td>
		等级编号：</td>
		<td><input name="dengjiId" id="dengjiId"></td>
	</tr>
	<tr>
		<td>类型：</td>
		<td><input id="dengtype" style="width: 150px" name="dengtype" /></td>
	</tr>
	<tr>
		<td>
		职称：</td>
		<td><input name="dengjiName" id="dengjiName"></td>
	</tr>
	<tr>
		<td>基本工资：</td>
		<td><input id="dengjiBen" style="width: 150px" name="dengjiBen" /></td>
	</tr>
	<tr>
		<td>公司绩效：</td>
		<td><input id="dengjiXiao" style="width: 150px" name="dengjiXiao" /></td>
	</tr>
	<tr>
		<td>交通：</td>
		<td><input id="dengjiao" style="width: 150px" name="dengjiao" /></td>
	</tr>
	
	
	
	<tr>
		<td>通讯：</td>
		<td><input id="dengjiTong" style="width: 150px" name="dengjiTong" /></td>
	</tr>
</table>
</form>
</div>
</body>
</html>