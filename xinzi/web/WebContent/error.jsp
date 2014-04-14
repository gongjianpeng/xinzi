<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>404错误页面</title>
<link rel="stylesheet" type="text/css" href="styles.css" />
</head>

<body style="text-align:center;background-color:#B0E0E6" >

<div></div>


    <h1>页面不存在，或者用户失效，请重新登录</h1>
    <h2>The page does not exist, or the user failed, please re login.</h2>



<p>返回首页：<a href="<%=basePath%>login.jsp" target="_blank">登录</a>　
　</p>

<p></p>

 <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
       <tr>
         <form name="loading">
         <td align="center">
         <p><font color="gray">系统将自动跳转到首页，请稍候……</font></p>
         <p>
         <input type="text" name="chart" size="47" style="font-family:Arial;font-weight:bolder;color:gray;background-color:white;padding:0px;border-style:none;">
         <br>
         <input type="text" name="percent" size="47" style="font-family:Arial;color:gray;text-align:center;border-width:medium;border-style:none;margin-top:5px;">
         <script language="javascript">
         var bar=0
         var line="||"
         var amount="||"
         count()
         function count(){
         bar=bar+2
         amount=amount+line
         document.loading.chart.value=amount
         document.loading.percent.value=bar+"%"
         if (bar<99){
         setTimeout("count()",90);}
         else{
         window.location = "<%=basePath%>login.jsp";}
         }
         </script>
         </p>
         </td>
      </form>
      </tr>
    </table>
</body>
</html>
