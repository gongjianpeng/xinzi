<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
img {
	border:medium none;
	border-width:0;
	padding:0 0 0 0;	
}
.border{ border:1px solid #CCC; padding:2px;}
.b1{border:1px solid #900; padding:2px;}
</style>
<link rel="stylesheet" type="text/css" href="../../themes/apusic/om-all.css" />
<script src="../../jquery/js/jquery.min.js"></script>
<script src="../../ui/om-core.js"></script>
<script src="../../ui/om-mouse.js"></script>
<script src="../../ui/om-menu.js"></script>
<script src="../../ui/om-draggable.js"></script>
<script src="../../ui/om-position.js"></script>
<script src="../../ui/om-resizable.js"></script>
<script src="../../ui/om-dialog.js"></script>
<script src="../../ui/om-panel.js"></script>
<script src="../../ui/om-combo.js"></script>
<script src="../../ui/om-tree.js"></script>
<script src="../../ui/om-borderlayout.js"></script>
<script src="../../ui/om-messagebox.js"></script>
<script src="../../ui/om-messagetip.js"></script>
<script src="../../ui/om-button.js"></script>
<script src="../../ui/om-fileupload.js"></script>

<script>
$(document).ready(function() {
	$("#dialog-upload").omDialog({
		autoOpen: false,
		width: 420,
		height: 280,
		modal: true,
		buttons: [{
               text : "<fmt:message key='targetAim.upload'/>", 
               click : function () {
					$('#file_upload').omFileUpload('upload');
               }
           }, {
               text : "<fmt:message key='dialog.cancel'/>", 
               click : function () {                    
                   $("#dialog-upload" ).omDialog("close");
               }
           }]
		
    });
    $('#file_upload').omFileUpload({	 	
	 	method : 'GET',
	    action : '../../uploadhome.do',
	    swf : '../../ui/om-fileupload.swf',
	    fileExt : '*.png;*.jpg;*.ico;*.gif;',
	  	fileDesc : '图片文件',	    
	    onComplete : function(event,ID,fileObj,response,data){	 
	    	$("#dialog-upload").omDialog('close');
			alert("设置成功，请刷新主页！");
			window.location.reload();
		}
   });
   $('#uploadButton').omButton({
   	  width : 100,
   	  onClick:function(){
   	  	 $( "#dialog-upload").omDialog('open');
   	  }
   });
});
</script>
<script language="javascript">
$(function(){
 $("img").addClass("border");
 $("img").mouseover(function(){
   $("img").addClass("b1");     
 })
 $("img").mouseout(function(){
   $("img").removeClass("b1");     
 })
})
</script>
</head>
<body>

<img id="img" width="600" height="400" src="../../images/home001.jpg"/><br/>
<input id="uploadButton" type="button" value="设置主页"/>
<div id="dialog-upload" title="上传主页">
	<input id="file_upload" name="file_upload" type="file"/>
	<div id="response" style="font-weight: bold; color: red;"></div>
</div>
</body>
</html>