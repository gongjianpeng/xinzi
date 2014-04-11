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
		<link href="../../js/jupload/uploadify.css" rel="stylesheet" type="text/css" />
		<link type="text/css" rel="stylesheet" href="../../css/validatorTidyMode.css" />
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
		<script src="../../js/formvalidator.js" type="text/javascript"></script>
		<script src="../../js/formvalidatorregex.js" type="text/javascript"></script>
		<script src="../../js/jupload/jquery.uploadify.js"></script>
         <script src="../../js/jupload/jquery.uploadify.min.js"></script>
		<script language="javascript" src="../../js/DateTimeMask.js" type="text/javascript"></script>
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
 .black_overlay{ 
	  display: none;  position: absolute; 
	  top: 0%;  left: 0%;  width: 100%;  height: 100%;  
	  background-color: black;  z-index:1001;  
	  -moz-opacity: 0.8;  opacity:.80; 
	  filter: alpha(opacity=80); 
 }  
  .white_content {  
	  display: none;  position: absolute;  
	  top: 25%;  left: 25%;  width: 250px;  
	  height: 180px;  padding: 16px;  
	  border: 16px solid #cad6e6;  
	  background-color: white;  
	  z-index:1002;  overflow: auto;  
  }  

input[type='text'] {
	margin: 0px;
	padding: 0px;
	height: 18px;
	width: 140px
}
img.door{
	height: 40px;
	with:30px;
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
			   	        id:"pinpaiqiyeDiv",
			   	     	header:false,
			   	        region:"center"
			   	    }],
			   	    fit:true,
			   	    spacing:2
	    }); 
			$('#pinpaiqiyeTable').omGrid({
             title : '品牌列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="addButton"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="updateButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="deleteButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '<a id="uploadButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/sort_up.gif" title="上传" />&nbsp;上传品牌logo&nbsp;</a>'+
		        
		            '</div>',
		        dataSource : 'getBrands.do' ,
		        limit : 15,
		        showIndex : false,
		        autoFit : true,
		        height:'fit',
		        colModel : [{header : "品牌名称", name : 'name', align : 'center'},                              
                             {header : "路径", name : 'FileUrl', align : 'center'},
                              {header : "品牌LOGO", name : 'FileUrl', align : 'center',
                                renderer : function(colValue, rowData, rowIndex) {
                                 if (colValue != "") {
                                  return ' <div style="color:red; height:40px; width:60px;">'+' <img src="<%=basePath%>/brandpic/'+rowData.FileUrl+'"    ></img></div>';
                                  
                                     } else if (colValue == "") {
                                        return '<div style="color:red; height:40px; width:100px;"><b>'+colValue+ '</b></div>';
                                     }
                                     return colValue;
                                 }},
                              
                              {header : "品牌简介", name : 'contents', align : 'center'},
                              {header : "品牌文化", name : 'culture', align : 'center'}
                              

               			 ],							
				onRowDblClick:function(rowIndex,rowData){
				    isAdd = false;
			        $('#operation').val("update");
                    showDialog("修改",rowData);//Display dialog
			
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
			    $("textarea[name='contents']",dialog).val(data.contents); 
			    $("textarea[name='culture']",dialog).val(data.culture);
			    	$("input[name='FileUrl']",dialog).val(data.FileUrl);
			    	$("input[name='type']",dialog).val(data.type);
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
				 	$('#pinpaiqiyeTable').omGrid('setData','getBrands.do');
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
                var selections=$('#pinpaiqiyeTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                   $.omMessageBox.alert({  content:'<fmt:message key='dialog.submit.modify.confirm'/>' });
                    return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("修改成功",selections[0]);//Display dialog
            });
            
            $('#deleteButton').die().live('click', function(e) {
                var selections=$('#pinpaiqiyeTable').omGrid('getSelections',true);
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
		                $.post('saveBrand.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#pinpaiqiyeTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "<fmt:message key='dialog.submit.success'/>", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
			/****************************上传******************************************/	   
				$('#uploadButton').die().live('click', function(e) {
			 	var selections=$('#pinpaiqiyeTable').omGrid('getSelections',true);
				if (selections.length == 0) {
				 $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
					return false;
				}
		        $( "#dialog-upload").omDialog('open');
		      var toDeleteRecordID=selections[0].id;
		   //    alert(toDeleteRecordID);
		    $('#file_upload').uploadify( {
			'debug':'false',
			'swf' : '../../js/jupload/uploadify.swf',//上传按钮的图片，默认是这个flash文件
		    'uploader' : '<%=basePath%>FileUploadServletBrand?ID=' + toDeleteRecordID,  //上传所处理的服务器
		 //   'uploader' : 'getdep.do?ID='+toDeleteRecordID,  //上传所处理的服务器
			'cancelImg' : '../../js/jupload/uploadify-cancel.png',//取消图片
			'method':'post',
			'folder' : '/upload',//上传后，所保存文件的路径
			'queueID' : 'fileQueue',//上传显示进度条的那个div
			'buttonText' : '请选择文件',
			progressData : 'percentage',
			'auto' : false,
			'multi' : false,
			'onDisable' : function() {
				alert('uploadify is disable');
			},//在调用disable方法时候触发
			'onError' : function(errorType,errObj) {
				alert('The error was: ' + errObj.info)
			},
			'onUploadSuccess' : function(file, data, response){
				  $('#dataDictionaryTable').omGrid('reload');
				}
		
		});
	 
      
      $("#uploadButton").click(function(){
    	  document.getElementById('light').style.display='block';
 		  document.getElementById('fade').style.display='block';
      });
      
      $("#colseWindow").click(function(){
    	  document.getElementById('light').style.display='none';
    	  document.getElementById('fade').style.display='none';
      });
			});    
		    
	 
				
 
				  
   /*******************************************************************************************************************/             
                $('#query222').bind('click', function(e) {
                var qName=$('#brandName').val().trim();
                 $('#pinpaiqiyeTable').omGrid("setData", 'getBrands.do?brandName='+encodeURI(qName));
              });
              
              
              $("#query").click(function(){
              var qName=$('#brandName').val().trim();
		        $('#pinpaiqiyeTable').omGrid('setData','getBrands.do?brandName='+encodeURI(qName));
		    });
            
            // Validate form
          var validator = $('#ipForm').validate({
                rules : {
                    name : {required : true},
                    contents : {required : true}
                }, 
                messages : {
                    name : {required : "必填！"},
                    contents : {required : "必填！"}
                }
        });
        
    });
    </script>
    <script type="text/javascript">
    	function praseDate(date){
		return date.fullYear + '-' + date.month + '-' + date.day + ' ' + date.hours + ':' + date.minutes + ':' + date.seconds;
	}
    	
    </script>
    
</head>
<fmt:bundle basename="messages.messages_brand" />
<body style="overflow: hidden">
	<div id="panel">
	<form id="queryForm">
		<table class="table1" style="float: left">
			<tr class="tr1">
				<td class="td1"> 
				p品牌名称:<input id="brandName" name="brandName" class="input1"/>
				<input id="query" type="button" class="button09"  value="查询"/></td>
			</tr>
		</table>
		

	</form>
	</div>
	<div id="pinpaiqiyeDiv" class="div1">
		<table id="pinpaiqiyeTable" class="table2"></table>
	</div>
		
	<div id="dialog-form">
	<form id="ipForm" action="saveBrand.do" method="post" ><input name="operation" id="operation" style="display: none" /> <input name="id" style="display: none">
		<table class="grid_layout">
			<tr>
				<td><span style="color: red">*</span>品牌名称：</td>
				<td><input name="name" id="name"  class="input_text"></td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>品牌介绍：</td>
				<td><textarea name="contents" id="contents"  style="width:240px;height:60px"  ></textarea></td>
			</tr>
			<tr>
				<td>品牌文化：</td>
				<td><textarea name="culture" id="culture"  style="width:240px;height:60px" ></textarea></td>
			</tr>
			
			
			<tr style="display: none;">
			<td><input id="FileUrl" name="FileUrl"></input></td>
				<td><input id="org" name="org"></input></td>	<td><input id="type" name="type" value="type"></input></td>
				<td><input id="createPerson" name="createPerson"></input></td>
				<td><input id="createPersonId" name="createPersonId"></input></td>
			</tr>
		</table>
		</form>
		</div>
		
		
		
<div id="light" class="white_content"> 
	 <div id="fileQueue"></div>
		<div >
			<input id="file_upload" name="file_upload" type="file" multiple="true"/>
		</div>
		
	 	<div style="float: left;margin-left: 20px;margin-top: 40px;margin-right: 50px;">
			<a href="javascript:$('#file_upload').uploadify('upload','*')" style="cursor: pointer;"> <input type="button" value="上传"></a>
		</div>
		<div style="float: left;margin-left: 20px;margin-top: 40px;margin-right: 60px;">
			<a href="javascript:void(0)" id="colseWindow" style="cursor: pointer;"> <input type="button" value="取消"></a>
		
		</div>
 
</div> 
<div id="fade" class="black_overlay"> </div>
	</body>
</html>