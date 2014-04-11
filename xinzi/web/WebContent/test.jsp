<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>jsonp</title>
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<link rel="stylesheet" type="text/css" href="js/jupload/uploadify.css">
	<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="js/jupload/jquery.uploadify.min.js"></script>
	<script>
	$(document).ready(function() {
		$('#file_upload').uploadify( {
			'debug':'true',
			'swf' : 'js/jupload/uploadify.swf',//上传按钮的图片，默认是这个flash文件
			'uploader' : 'FileUploadServlet',//上传所处理的服务器
			'cancelImg' : 'js/jupload/uploadify-cancel.png',//取消图片
			'method':'post',
			'folder' : '/upload',//上传后，所保存文件的路径
			'queueID' : 'fileQueue',//上传显示进度条的那个div
			'buttonText' : '请选择文件',
			//'onUploadComplete': function(file){alert('The file'+file.name+'finished processing!')},//每个文件上传成功后的函数
			progressData : 'percentage',
			'auto' : false,
			'multi' : true,
			//'onSelect':function(file){
			//alert("文件"+file.name+"被选择了！");
			//}
			//'onQueueComplete' : function(queueData) {
			//	alert(queueData.filesQueued + 'files were successfully!')
			//},//当队列中的所有文件上传成功后，弹出共有多少个文件上传成功
			'onDisable' : function() {
				alert('uploadify is disable');
			},//在调用disable方法时候触发
			//'onCancel':function(){alert('你取消了文件上传')}
			//'onUploadStart' : function(file) {//在调用上传前触发
			//alert('The file ' + file.name + ' is being uploaded.')}
			'onError' : function(errorType,errObj) {
				alert('The error was: ' + errObj.info)
			}

		});
	});
</script>
  </head >
 
  <body >
	<div id="fileQueue"></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true">
		<p>
			<!-- 加上“*”表示当第一个文件上传成功后，立即上传以后队列中的文件，否则需要自己手动 -->
			<a href="javascript:$('#file_upload').uploadify('upload','*')">上传</a>|
			<a
				href="javascript:$('#file_upload').uploadify('cancel',$('.uploadifive-queue-item').first().data('file'))">取消上传</a>
			<a href="javascript:$('#file_upload').uploadify('cancel','*')">清空所有的上传文件</a>
			<a href="javascript:$('#file_upload').uploadify('stop','*')">暂停</a>
			<!-- 如果填入true则表示禁用上传按钮 -->
			<a href="javascript:$('#file_upload').uploadify('disable','true')">禁用</a>
			<a href="javascript:$('#file_upload').uploadify('debug')">调试</a>
		</p>
		
		
		<p></p>
    <form action="upload.do" method="post" enctype="multipart/form-data">  
		    <input type="file" name="file" /> 
		    <input type="submit" value="上传" />
	    </form>
	   
  </body>
</html>
