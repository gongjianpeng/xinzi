<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
	<title>验证用户信息</title>
      <jsp:include page="common/include.jsp"></jsp:include>
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
	    	<div class="tab_reg company"> </div>
	  	</div>
  		<div class="reg_content">
  			<div class="car_nav">
	    		<ul>
	      		<li class="cur active" ><h2><i>1</i>填写账号信息</h2><span></span></li>
	        	<li class="cur active"><h2><i>2</i>验证账户信息</h2><span></span></li>
	        	<li><h2><i>3</i>注册成功</h2></li>
	       	 	</ul>
    		</div>
    		<div class="reg_form">
		    	<ul class="company">
		      		<li>
			        	<div class="yzxx">
			            	<p class="m_t_25">您的注册信息已提交，需要等待运营商的审核，请注意查收你的邮件<em><a href="#"></a></em></p>
			            </div>
	        		</li>
	        	</ul>
    		</div>
  	</div>
</div>
  </body>
</html>
