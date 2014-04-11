
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
</style>
<link rel="stylesheet" type="text/css" href="../../themes/apusic/om-all.css" />
<link href="../../js/jupload/uploadify.css" rel="stylesheet" type="text/css" />
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

<script src="../../js/jupload/jquery.uploadify.js"></script>
<script src="../../js/jupload/jquery.uploadify.min.js"></script>
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
				   	        id:"dealerDiv",
				   	        //title:"",
				   	     	header:false,
				   	        region:"center"
				   	    }],
				   	    fit : true,
				   	    spacing:2
		    });
		    
		   
            $('#DealerTable').omGrid({
             title : '企业信息列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    	//	'<a id="create" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		             '<a id="uploadButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/sort_up.gif" title="上传"/>&nbsp;上传视频&nbsp;</a>'+
		          '<a id="uploadButton2" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/sort_up.gif" title="上传"/>&nbsp;上传广告图片&nbsp;</a>'+
		        
		            '</div>',
	            dataSource : 'getDealer.do' ,
		        limit : 15,
		        showIndex : false,
		        autoFit : true,
		        height:'fit',
		        colModel : [
		                    {header :"企业名称", name : 'dename', align : 'center'},
		                    {header : "电话", name : 'tel', align : 'center'},
							{header : "地址", name : 'address', align : 'center'}, 
							{header : "传真", name : 'fax', align : 'center'},
							{header : "类型", name : 'type', align : 'center'},
						
							{header : "企业简介", name : 'intro', align : 'center'},
		                    {header : "视频", name : 'video', align : 'center'},	
							{header : "广告图片", name : 'picturebook', align : 'center'},	
						
							{header : "备注", name : 'remark', align : 'center'}	
							],		
                
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
            
            
           
            var dialog = $("#dialog-form").omDialog({
            	height:410,
        		width:680,
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
                $("input[name='dename']",dialog).val(rowData.dename);
                $("input[name='tel']",dialog).val(rowData.tel);
                $("input[name='fax']",dialog).val(rowData.fax);
                $("input[name='address']",dialog).val(rowData.address);
                $("textarea[name='remark']",dialog).val(rowData.remark);
                $("input[name='type']",dialog).val(rowData.type);  
                
                $("textarea[name='intro']",dialog).val(rowData.intro);
                $("input[name='video']",dialog).val(rowData.video);
                $("textarea[name='picturebook']",dialog).val(rowData.picturebook);
 
                
                	$("input[name='org']",dialog).val(rowData.org);
                 	$("input[name='createPerson']",dialog).val(rowData.createPerson);
                 	$("input[name='createPersonId']",dialog).val(rowData.createPersonId);
                
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#DealerTable').omGrid('setData','getDealer.do');
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
            $('#modify').die().live('click', function() {
                var selections=$('#DealerTable').omGrid('getSelections',true);
                if (selections.length == 0) {
                    $.omMessageBox.alert({title:'提示',content:'请至少选择一行记录！'});
                      return false;
                }
                isAdd = false;
                $('#operation').val("update");
                showDialog("<fmt:message key='dialog.submit.modify'/>",selections[0]);//Display dialog
            });
            
              $('#delete').die().live('click', function(e) {
                var selections=$('#DealerTable').omGrid('getSelections',true);
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
		                $.post('dealtwoOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#DealerTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             $('#query').bind('click', function(e) {
               var dename=$('#dename').val();
                var type=$('#type').val();
               // var qType=$('#qType').val();
                if(dename==""&&type===""){ //No query criteria to, to display all data
                    $('#DealerTable').omGrid("setData", 'getDealer.do');
                }else{ //Query, display the query data
                    $('#DealerTable').omGrid("setData", 'getDealer.do?dename='+encodeURI(dename)+'&type='+encodeURI(type));
                }
                
            });
            
            // Validate form
            var validator = $('#ipForm').validate({
                rules : {
                    dename : {required : true},
                    type : {required : true}
                                      
                }, 
                messages : {
                    dename : {required : "*"},//<fmt:message key='table.name.confirm'/>
                    type : {required : "*"}//<fmt:message key='table.type.confirm'/>
                                      
                }
            });
            
            
              /******************************************/
         	$('#uploadButton').die().live('click', function(e) {
			 	var selections=$('#DealerTable').omGrid('getSelections',true);
				if (selections.length == 0) {
				 $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
					return false;
				}
		        //$( "#dialog-upload").omDialog('open');
		       var toDeleteRecordID=selections[0].id;
		   //    alert(toDeleteRecordID);
		    $('#file_upload').uploadify( {
			'debug':'false',
			'swf' : '../../js/jupload/uploadify.swf',//上传按钮的图片，默认是这个flash文件
		    'uploader' : '<%=basePath%>FileUploadServletqiye?ID='+toDeleteRecordID,  //上传所处理的服务器
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
				  $('#DealerTable').omGrid('reload');
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
	/***********************************************************************/
	
	$('#uploadButton2').die().live('click', function(e) {
			 	var selections=$('#DealerTable').omGrid('getSelections',true);
				if (selections.length == 0) {
				 $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
					return false;
				}
		        //$( "#dialog-upload").omDialog('open');
		       var toDeleteRecordID=selections[0].id;
		   //    alert(toDeleteRecordID);
		    $('#file_upload').uploadify( {
			'debug':'false',
			'swf' : '../../js/jupload/uploadify.swf',//上传按钮的图片，默认是这个flash文件
		    'uploader' : '<%=basePath%>FileUploadServletqiye2?ID='+toDeleteRecordID,  //上传所处理的服务器
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
				  $('#DealerTable').omGrid('reload');
				}
		
		});
	 
      
      $("#uploadButton2").click(function(){
    	  document.getElementById('light').style.display='block';
 		  document.getElementById('fade').style.display='block';
      });
        $("#colseWindow").click(function(){
    	  document.getElementById('light').style.display='none';
    	  document.getElementById('fade').style.display='none';
      });
      });    
      /*********************/
	 
			
        });
    </script>

</head>
<fmt:bundle basename="classpath:messages.messages_qiyeman" />
<body style="overflow: hidden">
<div id="panel">
<form id="queryForm">
<table  border="0" cellspacing="0" cellpadding="0">
			<tr height="30" align="left">
				<td text-align:center align="right">企业名称: 
				<input id="dename" name="dename" />
				</td>
				<td text-align:center align="left">
			
				<input id="query" type="button" class="button09"  value="查询"/></td>
			</tr>
		</table>
</form>
</div>
<div id="dealerDiv" style="width: 'auto'">
<table id="DealerTable" style="table-layout:fixed;"></table>

<div id="dealerDiv" style="width: 'auto'">
<table id="DealerTable"></table>
</div>
<div id="dialog-form">
<form id="ipForm" action="dealtwoOperate.do" method="post" ><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
<table>
			<tr>
				<td colspan="1" rowspan="1" align="right">企业名称:</td>
				<td colspan="1" rowspan="1"><input id="dename" name="dename"></input></td>
				<td colspan="1" rowspan="1" align="right">电话:</td>
				<td colspan="1" rowspan="1"><input id="tel" name="tel" width="120" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"> </input></td>
			</tr>
		
			<tr>
				<td colspan="1" rowspan="1" align="right">传真:</td>
				<td colspan="1" rowspan="1"><input id="fax" name="fax" onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"></input></td>
				<td colspan="1" rowspan="1" align="right">地址:</td>
				<td colspan="1" rowspan="1"><input id="address" name="address" width="120"> </input></td>
			</tr>
		
			<tr>
				<td colspan="1" rowspan="1" align="right">视频:</td>
				<td colspan="1" rowspan="1">
					<input id="video" name="video" width="120"> </input>
					 
				</td>
					<!-- 
				</tr>
 				<td colspan="1" rowspan="1" align="right">电子画册:</td>
				<td colspan="1" rowspan="1">
					<input type="file"  id="filepicturebook" name="filepicturebook" width="120"> </input>
				 
				</td>
				<input type="hidden" id="video" name="video" width="120"> </input>
 				<input  type="hidden" name="picturebook" width="120"> </input>
		
			
			 -->
			<tr>
				<td colspan="1" rowspan="1" align="right">类型:</td>
				<td colspan="1" rowspan="1"><input id="type" name="type" width="120"> </input></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="right">简介：</td>
				<td colspan="5"><textarea name="intro"	style="width: 400px; height: 90px"></textarea></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="right">备注：</td>
				<td colspan="5"><textarea name="remark"	style="width: 400px; height: 90px"></textarea></td>
			</tr>
			<tr>
				<td colspan="1" rowspan="1" align="right">其他介绍：</td>
				<td colspan="5"><textarea name="picturebook"	style="width: 400px; height: 90px"></textarea></td>
			</tr>
			<tr style="display: none;">
				<td><input id="org" name="org"></input></td>
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