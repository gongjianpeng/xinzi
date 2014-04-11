
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
             title : 'diy上传列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="create"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '<a id="uploadButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/sort_up.gif" title="上传"/>&nbsp;上传图片&nbsp;</a>'+
		        
		            '</div>',
	           	dataSource :'getDiyEnddata.do',
	           	limit : 100,
	            height : 'fit',
                width : '100%',
                showIndex : false,
                colModel : [ 
                               {header : "代码", name : 'code', align : 'left', width :100 }, 
                              {header : "名称", name : 'name', align : 'left', width :200 },
                            //   {header : "前缀路径", name : 'inputname3', align : 'left', width : },
                              {header : "图片地址", name : 'address', align : 'left', width : 300 },
                               {header : "品牌LOGO", name : 'address', align : 'left', width:200,
                                renderer : function(colValue, rowData, rowIndex) {
                                 if (colValue != "") {
                                  return ' <div style="color:red; height:40px; width:60px;">'+' <img src="<%=basePath%>home/diy/'+rowData.address+'"    ></img></div>';
                                  
                                     } else if (colValue == "") {
                                        return '<div style="color:red; height:40px; width:100px;"><b>'+colValue+ '</b></div>';
                                     }
                                     return colValue;
                                 }},
                              {header : "关键值", name : 'remark', align : 'left', width :300 }
              
                ],
                
                
             
                
                
                onRowDblClick:function(rowIndex,rowData){
			        isAdd = false;
			        $('#operation').val("update");
                    showDialog("<fmt:message key='dialog.submit.modify'/>",rowData);//Display dialog
				}
            });
           
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
                if(rowData.address!=null)
               		$("input[name='address']",dialog).val(rowData.address);
               		 if(rowData.inputname3!=null)
               		$("input[name='inputname3']",dialog).val(rowData.inputname3);
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
				 	$('#dataDictionaryTable').omGrid('setData','getDiyEnddata.do');
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
		                $.post('diyurlDataOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
		                    $('#dataDictionaryTable').omGrid('reload');
		                    $('#operation').val("");
		                    $.omMessageTip.show({title: "提示", content: "<fmt:message key='dialog.submit.success.delete'/>", timeout: 1500});
		                });
                   	}
                 });
             });
             $('#query').bind('click', function(e) {
              
                var qName=$('#qName').val();
              //  var qType=$('#qType').val();
                if(qName===""){ //No query criteria to, to display all data
                    $('#dataDictionaryTable').omGrid("setData", 'getDiyEnddata.do');
                }else{ //Query, display the query data
                    $('#dataDictionaryTable').omGrid("setData", 'getDiybyNameQuery.do?qName='+encodeURI(qName));
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
            
            /******************************************/
        
         /******************************************/
         	$('#uploadButton').die().live('click', function(e) {
			 	var selections=$('#dataDictionaryTable').omGrid('getSelections',true);
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
		    'uploader' : '<%=basePath%>FileUploadServlet?ID='+toDeleteRecordID,  //上传所处理的服务器
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
	 
        });
    </script>
<script type="text/javascript">
$(document).ready(function() {
	
     
	});
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
		 <input
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
<form id="ipForm" action="diyurlDataOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
<table>
	<tr>
		<td>
		代码：</td>
		<td><input name="code" id="dcode"></td>
	</tr>
	<tr>
		<td>
		名称：</td>
		<td><input name="name" id="dname"></td>
	</tr>
	<tr>
		<td></td>
		<td><input id="inputname3" type="hidden" name="inputname3" /></td>
	</tr>
	<tr>
		<td>图片地址：</td>
		<td><input id="address" style="width: 150px" name="address" /></td>
		
	</tr>
	<tr>
		<td>关键值：</td>
		<td><input id="remark" style="width: 150px" name="remark" /></td>
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