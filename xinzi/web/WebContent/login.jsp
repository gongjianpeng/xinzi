<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" >
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<%=basePath%>home/resource/css/component_style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>home/resource/css/login.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>home/resource/js/jquery-1.9.1.js" type="text/javascript" ></script>
<script src="<%=basePath%>home/resource/js/tab_jay.js" type="text/javascript" ></script>
<script src="<%=basePath%>home/resource/js/diy_searchJs.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/cookie.js" type="text/javascript"></script>
<title></title>

<script>
	var win = window;
	var vparent = win.parent;
	if(win != vparent) {
		while(win != vparent) {
		    win = vparent;
		    vparent = win.parent;
		}
		win.location.href="login.do";
	}	
	function tiger(){
       var url = window.location.href;
       var par = url.substring(url.indexOf('?')+1);      
       if(par == "error=1"){
       alert("您输入的用户名或密码有误");
           document.getElementById('msg').innerHTML="<b><fmt:message key='login.errAlert'/></b><font color='red' size='2'><fmt:message key='login.errAlert1'/></font>";
       }/*else if(par=="error=2"){
    	   document.getElementById('msg').innerHTML="<b><fmt:message key='login.errAlert'/></b><font color='red' size='2'><fmt:message key='login.errAlert2'/></font>";
       }else if(par=="error=3"){
    	   document.getElementById('msg').innerHTML="<b><fmt:message key='login.errAlert'/></b><font color='red' size='2'><fmt:message key='login.errAlert3'/></font>";
       }else{
    	   document.getElementById('msg').style.display="none";
       } */     
	}
	function setLocale(l){	    
	    $.post('infom/setLocale.do',
	    {locale:l},function(){
	    	 location.reload();   
			});
	}
 </script>
  <script>
 function sc1(){ 
document.getElementById("lg_wrap").style.top=(document.documentElement.scrollTop+(document.documentElement.clientHeight-document.getElementById("lg_wrap").offsetHeight)/2)+"px"; 
document.getElementById("lg_wrap").style.left=(document.documentElement.scrollLeft+(document.documentElement.clientWidth-document.getElementById("lg_wrap").offsetWidth)/2)+"px"; 
} 
</SCRIPT> 
<SCRIPT LANGUAGE="JavaScript"> 
<!-- 
function scall(){ 
sc1();
} 
window.onscroll=scall; 
window.onresize=scall; 
window.onload=scall; 
//--> 
function  outvalue(){
            document.getElementById("j_username").value= j_username;
			document.getElementById("j_password").value= j_password;
				if(j_username!=null&&j_username!=""&&j_password!=null&&j_password!=""){
				alert("1");
				return ture;
				}else{
				alert("2");
				return false;
				}
			
}
</SCRIPT>


</head>

<body class="login_bd" onload="checkAutoLogin()">

<form name="loginForm" action="j_spring_security_check" method="post">
<div class="lg_wrap">
	<div class="logo"><img src="<%=basePath%>home/resource/images/login/logo.png" /></div>
    <table width="100%" class="proceTab" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="48">用户名</td>
        <td width="244"><input name="j_username" type="text" class="wbk" id="textfield" maxlength="12" /></td>
        <td rowspan="2"><input type="submit" name="button" class="org_bt" id="btnSubmitt" value=" " onclick="outvalue()" /></td>
      </tr>
      <tr>
        <td>密 &nbsp; 码</td>
        <td><input name="j_password" type="password" class="wbk" id="textfield" maxlength="12"/></td>
      </tr>     
      <tr>
        <td>&nbsp;</td>
        <td colspan="2" class="mmz"><span><input type="checkbox" style="color: white" name="rempwd" id="rempwd" /><label></label>记住密码</span>
        <a href="<%=basePath%>home/register.jsp" >帐户注册</a><a href="forgotPwd.jsp">忘记密码</a></td>
      </tr>      
    </table>
    <div id="msg" style="margin-left: 400px;"></div>
      <div id="Layer2"></div>
</div>
</form>
<script>
tiger();
</script>
<script type="text/javascript">

function checkAutoLogin(){
	var j_username = getCookie("musicConsole","j_username");
	var j_password = getCookie("musicConsole","j_password");
	var rempwd = getCookie("musicConsole","rempwd");
	if(j_username!=null&&j_username!=""&&j_password!=null&&j_password!=""){
		j_username = j_username.replace("j_username=","");
		j_password = j_password.replace("j_password=","");
		rempwd = rempwd.replace("rempwd=","");
		
			document.getElementById("j_username").value= j_username;
			document.getElementById("j_password").value= j_password;
			document.getElementById("rempwd").checked= "checked";
	
	}else{
		document.getElementById("j_username").focus();
	}
}

function doLogin(){
	alert("请查看Cookie文件");
}


</script>

</body>
</html>
