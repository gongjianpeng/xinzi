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
	    action : '../../uploadlogo.do',
	    swf : '../../ui/om-fileupload.swf',
	    fileExt : '*.png;*.jpg;*.ico;*.gif;',
	  	fileDesc : '图片文件',	    
	    onComplete : function(event,ID,fileObj,response,data){
	    	$("#dialog-upload").omDialog('close');
	    	//$('#logo').removeAttr('src').attr('src','../../images/logo.png');
	    	//window.parent.refreshLogo();
			//$.omMessageTip.show({title:"提示",content:"Logo设置成功！",timeout : 3500});
			window.location.reload();
		}
   });
   $('#uploadButton').omButton({
   	  width : 100,
   	  onClick:function(){
   	  	 $( "#dialog-upload").omDialog('open');
   	  }
   });
   
   window.parent.refreshLogo();
});

</script>
</head>
<body>

<img id="logo" width="300" height="300"  src="/fileupload/logo/20131216111633-126978493/20131128042613670.jpg"/><br/>
<input id="uploadButton" type="button" value="设置Logo"/>
<div id="dialog-upload" title="上传Logo">
	<input id="file_upload" name="file_upload" type="file"/>
	<div id="response" style="font-weight: bold; color: red;"></div>
</div>

</body>
</html>