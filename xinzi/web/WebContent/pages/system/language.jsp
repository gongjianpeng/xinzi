<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../themes/apusic/om-all.css" />
<script src="../../jquery/js/jquery.min.js"></script>
<script src="../../ui/om-core.js"></script>
<script src="../../ui/om-mouse.js"></script>
<script src="../../ui/om-dialog.js"></script>
<script src="../../ui/om-panel.js"></script>
<script src="../../ui/om-combo.js"></script>
<script src="../../ui/om-button.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#locale').omCombo({
	       dataSource:[
	       	{text:"<fmt:message key='top.chinese' />",value:'zh_CN'}, 
	       	{text:"<fmt:message key='top.english' />",value:'en_US'}
	       ],
	       width : '80px',
	       value:"${loginInfo.locale}",
	       editable:false,
	       listMaxHeight : 'auto',
	       onValueChange:function(target,newValue,oldValue,event){
	           if(oldValue!=''){
		    	  setLocale(newValue);
		       }
		   }
	   });
	   function setLocale(l){
		  $.post('../../setLocale.do',{locale:l},function(){
			  parent.location.reload();
			 // window.location.href="/pages/userSetup/language.do";
	      });
		  return false;
	   }
});
</script>
</head>
<body>
<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FBF9F8">
<tr>
<td align="right">
<font style="font-family: '宋体';font-size: 12px;"><fmt:message key='usersetup.chooselanguage' />：</font>
</td>
<td>
<input id="locale"/>
</td>
</tr>
</table>
</body>
</html>