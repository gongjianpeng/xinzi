<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>订单提交成功</title>
     <jsp:include page="../common/include.jsp"></jsp:include>
	<style type="text/css">
	.error {
		color: red;
		padding-left:2px;
	}
	</style>
	 
  </head>
<body class="register">
	 <div class="wrapper">
		<div class="reg_header">
	  		<a href="#"><img src="<%=basePath%>home/resource/images/register_logo.png" width="99" height="69" /></a>
	    	<div class="tab_reg company"></div>
	  	</div>
  		<div class="reg_content">
  			<div class="car_nav">
		    <ul>
			      	<li class="cur"><h2><i>1</i>我的购物车</h2><span></span></li>
			        <li class="cur"><h2><i>2</i>填写核对订单</h2><span></span></li>
			        <li class="cur finish"><h2><i>3</i>订单提交成功</h2></li>
		      </ul>
   			 </div>
    		<div class="reg_form">
		    	<ul class="company">
		      	<li>
		        	  
		            <em>
		            	<img src="<%=basePath%>home/resource/images/xl_ico.png"  class="fl"/>
		            		<h3>
		            		订单已经提交成功,您的订单号是
		            		<%
		            			//String OrderNO=request.getParameter("OrderNO");
		            			//out.println(OrderNO);
		            		%>${requestScope.OrderNo} ,支付请联系电商联系人支付,或者在线支付! 我们会尽快给您发货.
		            		</h3>
		            </em>
		           
        		</li>
		      	</ul>
		       
    	</div>
  	</div>
</div>
  </body>
</html>
