
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
             title : '电子画册列表'+
		    		'<div  style="float:right;padding-right:20px;">&nbsp;&nbsp;'+
		    		'<a id="create"   href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/add.gif" title="新增" />&nbsp;新增&nbsp;</a>'+
		            '<a id="modify" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/edit.gif" title="修改" />&nbsp;修改&nbsp;</a>'+
		            '<a id="delete" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/delete.gif" title="删除" />&nbsp;删除&nbsp;</a>'+
		            '<a id="uploadButton" href="javascript:void(0)"><img src="<%=basePath%>home/resource/images/icons/sort_up.gif" title="上传"/>&nbsp;上传图片&nbsp;</a>'+
		        
		            '</div>',
	           	dataSource :'getDataPlicalbums.do',
	           	limit : 20,
	            height : 'fit',
                width : '100%',
                showIndex : false,
                colModel : [ 
                               {header : "企业", name : 'contents', align : 'left', width : 300 },
                              {header : "品牌", name : 'name', align : 'left', width : 200 },
                              {header : "读取路径", name : 'filurl', align : 'left', width :200 },
                               {header : "创建人", name : 'createPerson', align : 'left', width : 200},
                             {header : "图片", name : 'filurl',width : 200, align : 'center',
                                renderer : function(colValue, rowData, rowIndex) {
                                 if (colValue != "") {
                                  return ' <div style="color:red; height:60px; width:160px;">'+' <img src="<%=basePath%>/picalbum/'+rowData.filurl+'"    ></img></div>';
                                  
                                     } else if (colValue == "") {
                                        return '<div style="color:red; height:40px; width:100px;"><b>'+colValue+ '</b></div>';
                                     }
                                     return colValue;
                                 }}
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
                if(rowData.contents!=null)
               		$("input[name='contents']",dialog).val(rowData.contents);
               		 if(rowData.filurl!=null)
               		$("input[name='filurl']",dialog).val(rowData.filurl);
                if(rowData.remark!=null)
                	$("input[name='remark']",dialog).val(rowData.remark);
                	$("input[name='org']",dialog).val(rowData.org);
                		$("input[name='createPerson']",dialog).val(rowData.createPerson);
                dialog.omDialog("option", "title", title);
                dialog.omDialog("open");//Display dialog
            };
            //Point when the submit button to submit data to the background in dialog and perform the appropriate Add or modify operation
            function showResponse(data){
            	if(data!=null&&data=="true"){
				 	$("#dialog-form").omDialog("close");  
			 		$('#operation').val("");
				 	$('#dataDictionaryTable').omGrid('setData','getDataPlicalbums.do');
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
		                $.post('plicalbumOperate.do',{operation:'delete',id:toDeleteRecordID},function(){
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
                if(qCode==""&&qName===""&&qType==""){ //No query criteria to, to display all data
                    $('#dataDictionaryTable').omGrid("setData", 'plicalbumOperate.do');
                }else{ //Query, display the query data
                    $('#dataDictionaryTable').omGrid("setData", 'plicalbumOperate.do?qName='+encodeURI(qName)+'&qType='+encodeURI(qType));
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
			//	if (selections.length == 0) {
			//	 $.omMessageBox.alert({title:'提示',content:'请选择数据行！'});
			//		return false;
			//	}
		       var tt=$("#filename").val().trim();
		      // alert(tt);
		       if(tt==""){
		       alert("请填写品牌名称");
		     //  return false;
		       }else{
		       var tt=$("#filename").val().trim();
		      
		       alert(tt);
		       }
		    $('#file_upload').uploadify( {
			'debug':'false',
			'swf' : '../../js/jupload/uploadify.swf',//上传按钮的图片，默认是这个flash文件
		    'uploader' : '<%=basePath%>FileUploadServletpic?ID='+tt,  //上传所处理的服务器
		 //   'uploader' : 'getdep.do?ID='+toDeleteRecordID,  //上传所处理的服务器
			'cancelImg' : '../../js/jupload/uploadify-cancel.png',//取消图片
			'method':'post',
			//'folder' : '/upload',//上传后，所保存文件的路径
			'queueID' : 'fileQueue',//上传显示进度条的那个div
			   'auto' : false,                                        //设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传 
	            'multi' : true,                                        //multi ：设置为true时可以上传多个文件。 
	            'simUploadLimit' : 20,                        //simUploadLimit ：允许同时上传的个数 默认值：1 
	         
	            'progressData' : 'speed',        //('percentage：百分比' or 'speed'：速度) 进度显示类型
	            'removeCompleted' : true,        //完成上传的是否自动消失
	            //'buttonImage' :'images/button.jpg',        //按钮图片
	            'removeTimeout' :0,                        //上传完成进度条消失的时间
			'buttonText' : '请选择文件',
			progressData : 'percentage',
			'auto' : false,
			
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
<input id="pinname" name="pinname" value="<%=session.getAttribute("brandName")%>"></input>
<div id="panel">
<form id="queryForm">
<table style="width: 100%;">
	请输入上传品牌名称：	<input id="filename" name="filename" />
</table>
</form>
</div>

<div id="dataDictionaryDiv" style="width: 'auto'">
<table id="dataDictionaryTable"></table>
</div>
<div id="dialog-form">
<form id="ipForm" action="plicalbumOperate.do" method="post"><input name="operation"
	id="operation" style="display: none" /> <input name="id" style="display: none">
<table>
	<tr>
		<td>
		企业：</td>
		<td><input name="contents" id="contents"></td>
	</tr>
	<tr>
		<td>
		名称：</td>
		<td><input name="name" id="name"></td>
	</tr>
	
	<tr>
		<td>图片地址：</td>
		<td><input id="filurl" style="width: 150px" name="filurl" /></td>
		
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