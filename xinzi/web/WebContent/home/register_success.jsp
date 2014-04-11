<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>注册成功</title>
     <jsp:include page="common/include.jsp"></jsp:include>
	<style type="text/css">
	.error {
		color: red;
		padding-left:2px;
	}
	</style>
	<script type="text/javascript">
		function tologin(){
			window.location.href='<%=basePath%>'+"login.do";
		}
	</script>
  </head>
<body class="register">
	 <div class="wrapper">
		<div class="reg_header">
	  		<a href="#"><img src="<%=basePath%>home/resource/images/register_logo.png" width="99" height="69" /></a>
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
	      		<li><h2><i>1</i>填写账号信息</h2><span></span></li>
	        	<li><h2><i>2</i>验证账户信息</h2><span></span></li>
	        	<li class="cur active"><h2><i>3</i>注册成功</h2></li>
	       	 	</ul>
    		</div>
    		<div class="reg_form">
		    	<ul class="company">
		      	<li>
		        	<div class="zccg">
		            <em><img src="<%=basePath%>home/resource/images/xl_ico.png"  class="fl"/>恭喜您，注册成功！</em>
		            <input type="button" id="button" onclick="tologin()" class="button_3" value="进入登录页面" />
		            </div>
        		</li>
		      	</ul>
		      	<ul class="dealer">
		         <li>
		        	<div class="zccg">
		            <em><img src="<%=basePath%>home/resource/images/xl_ico.png"  class="fl"/>恭喜您，注册成功！</em>
		            <input type="button2" class="button_3" value="进入设置中心" />
		            </div>
       			</li>
		      </ul>
    	</div>
  	</div>
</div>
  </body>
</html>
