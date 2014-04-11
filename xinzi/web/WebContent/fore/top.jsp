<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jay.js"></script>
  </head>
  
  <body>
    <div class="logo">
    	<a href="index.html"><img src="images/public/logo.png" width="103" height="62" alt="logo" /></a>
      	<h1>欢迎&nbsp;${loginInfo.loginPerson.name}</h1>
      	<a  href="#" style="color:#FFFAF0"  onclick="exit()" >退出</a>
    </div>
    <div class="header_search">
    	<input type="text" class="text" />
      <input type="submit" class="submit" value="" />
    </div>
    <div class="buy_car"><a href="orderlist.jsp" target="_top">购物车(<i>2</i>)</a></div>
    <div class="clearfix"></div>
    <div class="nav">
      <a href="orderlist.jsp" target="_top" class="hover">订单管理</a>
      <a href="doorlist.jsp" target="_top" class="hover">门类在线</a>
      <a href="#">DIY</a>
      <a href="#">电子画册</a>
      <a href="#">客户管理</a>
      <a href="#">企业设置</a>
      <a href="#">展示中心</a>
    </div>
  </body>
</html>
