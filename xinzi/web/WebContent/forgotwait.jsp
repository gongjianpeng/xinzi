<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  <title>等待审核密码</title>
  <jsp:include page="home/common/include.jsp"></jsp:include>
<style type="text/css">
	.error {
		color: red;
		padding-left:2px;
	}
	.boxHd {
	height: 33px;
	overflow: hidden;
	margin-bottom: 24px;
	border-bottom: 1px dotted #c8c8c8;
	font-size: 20px;
	font-weight: normal;
	font-family: "Microsoft YaHei", \5fae\8f6f\96c5\9ed1, arial, \5b8b\4f53;
	color: #323232;
}
</style>
<script type="text/javascript">

</script>
  </head>
<body class="register">
	 <div class="wrapper">
		<div class="reg_header">
	  		<a href="#"><img src="<%=basePath%>/home/resource/images/register_logo.png" width="99" height="69" /></a>
	    	<div class="tab_reg company">
	    		<!--<ul>
	        	<li name="company" class="hover">企业注册</li>
	        	<li name="dealer">经销商注册</li>
	      		</ul>
	    	--></div>
	  	</div>
  		<div class="reg_content">
  			<div class="car_nav">
	    		<ul>
	      		<li class="cur"><h2><i>1</i>填写邮箱</h2><span></span></li>
	        	<li class="cur active"><h2><i>2</i>等待消息</h2><span></span></li>
	        	<li ><h2><i>3</i>找到密码</h2></li>
	       	 	</ul>
    		</div>
    		<div class="reg_form">
		    	<ul class="company">
		    	<h1 class="boxHd">
									请稍等，您的申请正在通过后台审核，我们将会把结果发送到您的邮箱。
								</h1>
		          	 
	        	</ul>
    	</div>
  	</div>
</div>
  </body>
</html>
