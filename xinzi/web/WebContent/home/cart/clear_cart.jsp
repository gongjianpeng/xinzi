<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>我的购物车</title>
	<jsp:include page="../common/include.jsp"></jsp:include>
	
 </head>
 <body>
<div class="wrapper">

	<!-- 头部 -->
<jsp:include page="../common/header.jsp"></jsp:include>
	
  	 
          <div style="float: left;margin-left: 380px;font-size: 20px;margin-top: 35px;">
          	<img alt="" src="<%=basePath%>home/resource/images/xl_ico.png">
          	购物车是空的,请<a href="<%=basePath%>home/diy/diy.html">点击继续</a>添加商品到购物车
          </div>
          	 
</div>
 
<jsp:include page="../common/footer.jsp"></jsp:include> 
 
</body>
</html>
 
