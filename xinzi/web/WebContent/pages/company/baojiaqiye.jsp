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
		<!-- by make start -->
		<link href="../../css/all_css.css" rel="stylesheet" type="text/css" />
		
		<!-- by make end -->
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
			   	        id:"baojiaqiyeDiv",
			   	     	header:false,
			   	        region:"center"
			   	    }],
			   	    fit:true,
			   	    spacing:2
	    }); 
			$('#baojiaqiyeTable').omGrid({
             title : '报价信息列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="addButton"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="updateButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="deleteButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '</div>',
		        dataSource : 'getDataQuotes.do' ,
		        limit : 15,
		        showIndex : false,
		        autoFit : true,
		        height:'fit',
		        colModel : [
		                      {header : "报价名称", name : 'name', align : 'center'},                              
                              {header : "实际价格", name : 'reality', align : 'center'},
                              {header : "折扣比例", name : 'discount', align : 'center'},
                               {header : "品牌", name : 'bpinpai', align : 'center'},
                                {header : "类型", name : 'btype', align : 'center'},
                                 {header : "类型名称", name : 'btypename', align : 'center'},
                                  {header : "多平米/价格", name : 'bpriceone', align : 'center'},
                                   {header : "超出平米/价格", name : 'chaochuprice', align : 'center'},
                                   {header : "材质", name : 'bcaizhi', align : 'center'},
                                    {header : "材质型号", name : 'bcaizhinum', align : 'center'},
                                     {header : "单价/价格", name : 'bdanjiac', align : 'center'},
                                      {header : "单位", name : 'danwei', align : 'center'},
                              {header : "备注", name : 'remark', align : 'center'}
               			 ],							
				onRowDblClick:function(rowIndex,rowData){
				    isAdd = false;
			        $('#operation').val("update");
                    showDialog("修改",rowData);//Display dialog
				}
	        });
	        var dialog = $("#dialog-form").omDialog({
            	height:520,
        		width:600,
                autoOpen : false,
                modal : true,
                resizable : true,
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
			    $("input[name='reality']",dialog).val(data.reality); 
			    $("input[name='discount']",dialog).val(data.discount); 
			      $("input[name='bpinpai']",dialog).val(data.bpinpai); 
			          $("input[name='btype']",dialog).val(data.btype); 
			       $("input[name='btype']",dialog).val(data.btype); 
			       $("input[name='btype']",dialog).val(data.btype); 
			$("input[name='btypename']",dialog).val(data.btypename);
			$("input[name='bpriceone']",dialog).val(data.bpriceone);
			$("input[name='chaochuprice']",dialog).val(data.chaochuprice);
			$("input[name='bcaizhi']",dialog).val(data.bcaizhi);
			$("input[name='bcaizhinum']",dialog).val(data.bcaizhinum); 
			
			$("input[name='bdanjiac']",dialog).val(data.bdanjiac);
			$("input[name='danwei']",dialog).val(data.danwei); 
			
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
				 	$('#baojiaqiyeTable').omGrid('setData','getDataQuotes.do');
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
                var selections=$('#baojiaqiyeTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                   $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
            		return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("修改成功",selections[0]);//Display dialog
            });
            
            $('#deleteButton').die().live('click', function(e) {
                var selections=$('#baojiaqiyeTable').omGrid('getSelections',true);
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
		                $.post('DataQuoteOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#baojiaqiyeTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             $('#query').bind('click', function(e) {
                var qName=$('#quoteName').val();
                 $('#baojiaqiyeTable').omGrid("setData", 'getDataQuotesquery.do?quoteName='+encodeURI(qName));
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
				报价名称:<input id="quoteName" name="quoteName" class="input1"/>
				<input id="query" type="button" class="button09" value="查询"/></td>
			</tr>
		</table>
	</form>
	</div>
	<div id="baojiaqiyeDiv" class="div1">
		<table id="baojiaqiyeTable" class="table2"></table>
	</div>
	
	 
		
	<div id="dialog-form">
	<form id="ipForm" action="DataQuoteOperate.do" method="post"><input name="operation" id="operation" style="display: none" /> <input name="id" style="display: none">
		<table>
			<tr>
				<td>报价名称：</td>
				<td><input name="name" id="name" ></td>
				<td>实际价格：</td>
				<td><input name="reality" id="reality" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"></td>
			</tr>
			<tr>
				<td>折扣比例：</td>
				<td><input id="discount" name="discount" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"/></td>
			</tr>
			
			<tr>
				<td>品牌：</td>
				<td><input name="bpinpai" id="bpinpai" ></td>
				<td>类型：</td>
				<td><input name="btype" id="btype" ></td>
			</tr>
			<tr>
				<td>类型名称：</td>
				<td><input name="btypename" id="btypename" ></td>
				<td>多平米/价格：</td>
				<td><input name="bpriceone" id="bpriceone" ></td>
			</tr>
			<tr>
				<td>材质：</td>
				<td><input name="bcaizhi" id="bcaizhi" ></td>
				<td>材质型号：</td>
				<td><input name="bcaizhinum" id="bcaizhinum" ></td>
			</tr>
			<tr>
				<td>单价/价格：</td>
				<td><input name="bdanjiac" id="bdanjiac" ></td>
				
			</tr>
			
			<tr>
				<td>超出平米/价格：</td>
				<td><input name="chaochuprice" id="chaochuprice" ></td>
				<td>单位：</td>
				<td><input name="danwei" id="danwei" ></td>
			</tr>
                       
                                  
                           
			
			<tr>
				<td style="float: right; line-height:60px;">备注：</td>
				<td><textarea name="remark"></textarea></td>
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