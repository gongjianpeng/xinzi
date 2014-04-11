<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子杂志</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-21468787-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  
  

</script>
<script type="text/javascript">
		$(function(){
			var loginperson=$('#loginperson').val();
			
			if(loginperson==null||loginperson==""){
			
			window.location='j_spring_security_logout';
			return false;
			
			}else{
			
			return true;
			}
		})
	</script>
</head>

<body>
      <h1><input id="loginperson" value="${loginInfo.loginPerson.name}" type="hidden" ></input></h1>
<div class="headeline"></div>


<!--演示内容开始-->
<link type="text/css" href="css/book.css" rel="stylesheet" />

<div id="book" style="margin:0 auto;">

	<div class="arrow" style="left: -27px;">
		<div class="mask"></div>
		<div class="al" title="上一页"></div>
	</div>

	<div id="flip">
		<div class="container">
			<div class="label"></div>
			<div class="content"></div>
			<div class="pager"></div>
		</div>
		<div class="overlayer ie_l"><img src="images/flip_r.png" width="100%" height="627" /></div>
	</div>
	
	<div id="left">
		<div class="label"></div>
		<div class="content">
			<div class="default"><img alt="" src="images/2012032317.jpg" height="441" width="277"><p align="center"></p></div>
		</div>
		<div class="pager"></div>
	</div>
	
	<div id="right">
	
		<div class="label"></div>
	
		<div class="content">
			<div class="default"><img src="images/taojiefm2.jpg" style="cursor:pointer;" onclick="$('#book .arrow .ar').click();"/></div>
		</div>
		<div class="glide">
			<div style="left: 50%;"></div>
		</div>
	
	</div>
	
	<div id="foot"></div>

	<div class="arrow" style="left:965px;">
		<div class="mask"></div>
		<div class="ar" title="下一页"></div>
	</div>
	
</div>

<script type="text/javascript" src="js/jqueryui-1.8.min.js"></script>
<script type="text/javascript" src="js/page.js"></script>
<script type="text/javascript" src="js/book.js"></script>

<!--演示内容结束-->
<div style="clear:both"></div>

<br />
<br />
</div>
</body>
</html>