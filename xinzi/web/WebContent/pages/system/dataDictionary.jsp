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
		    
        
            $('#dataDictionaryTable').omGrid({
             title : '基础数据列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="create"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '</div>',
	           	dataSource :'getDataDictionarys.do',
	           	limit : 100,
	            height : 'fit',
                width : '100%',
                showIndex : false,
                colModel : [ 
                               {header : "<fmt:message key='datadictionary.code'/>", name : 'code', align : 'left', width : $('#dataDictionaryDiv').outerWidth()*0.1}, 
                              {header : "<fmt:message key='datadictionary.name'/>", name : 'name', align : 'left', width : $('#dataDictionaryDiv').outerWidth()*0.2},
                              {header : "<fmt:message key='datadictionary.type'/>", name : 'type', align : 'left', width : $('#dataDictionaryDiv').outerWidth()*0.2},
                              {header : "<fmt:message key='datadictionary.remark'/>", name : 'remark', align : 'left', width : $('#dataDictionaryDiv').outerWidth()*0.2}
              
                ],
                
                
             
                
                
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
            /**
			var gridHeight = $(window).height()-160;           
			$("#dataDictionaryTable").omGrid("resize" , {width:'100%',height:gridHeight});
	         var panel = $("#panel").omPanel({
                width:'100%',
                height:'40px',
                iconCls: "search",
                header: true,
                title:'查询',
                collapsed: false,
                collapsible: false,
                closable: false,
                onCollapse:function(){
                	gridHeight =  $(window).height() - 90;          
					$("#dataDictionaryTable").omGrid("resize" , {width:'100%',height:gridHeight});
                },
                onExpand:function(){
                	gridHeight =  $(window).height() - 160;           
					$("#dataDictionaryTable").omGrid("resize" , {width:'100%',height:gridHeight});
                }
            });
            ***/
            var dialog = $("#dialog-form").omDialog({
            	height:300,
        		width:400,
                autoOpen : false,
                modal : true,
                resizable : false,
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
				 	$('#dataDictionaryTable').omGrid('setData','getDataDictionarys.do');
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
            	/*var $code=$("#dcode").val();
            	if($code==null){
            		alert("请你输入编码!");
            	}*/
            
                isAdd = true;
                $('#operation').val("add");
                showDialog("<fmt:message key='dialog.submit.add'/>");//Display dialog
            });
            $('#modify').die().live('click', function() {
                var selections=$('#dataDictionaryTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    
                     $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
                    return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
            $('#delete').die().live('click', function(e) {
                var selections=$('#dataDictionaryTable').omGrid('getSelections',true);
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
		                $.post('dataDictionaryOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             $('#query').bind('click', function(e) {
              
                var qName=$('#qName').val();
                var qType=$('#qType').val();
                if(qName===""&&qType==""){ //No query criteria to, to display all data
                    $('#dataDictionaryTable').omGrid("setData", 'operateDataDictionary.do');
                }else{ //Query, display the query data
                    $('#dataDictionaryTable').omGrid("setData", 'operateDataDictionary.do?qName='+encodeURI(qName)+'&qType='+encodeURI(qType));
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
<form id="queryForm">
<table style="width: 100%;">
	<tr>
		<td valign="middle">
		<fmt:message key='datadictionary.name' />：</font> <input id="qName" style="width: 15%"> <font
			size="2.5px" style="width: 4%">
		<fmt:message key='datadictionary.type' />：</font> <input id="qType" style="width: 15%"> <input
			id="query" type="button" value="<fmt:message key='datadictionary.search'/>" class="button09">
		</td>
	</tr>
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
	
</table>
</form>
</div>
</body>
</html>