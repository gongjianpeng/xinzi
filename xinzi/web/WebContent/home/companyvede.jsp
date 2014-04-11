<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
   <head>
	<title>企业视频</title>      
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" >
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="../home/resource/css/default-style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../home/build/mediaelementplayer.min.css" />
	<link href="../home/resource/css/reset.css" rel="stylesheet" type="text/css" />
     <script type="text/javascript" src="./../home/build/jquery.js"></script>
     <script type="text/javascript" src="../home/build/mediaelementplayer.js"></script>
	<script type="text/javascript" src="../home/build/mediaelement-and-player.js"></script>
	<script type="text/javascript" src="../home/build/mediaelement-and-player.min.js"></script>
	
	<script src="../home/build/mep-feature-loop.js"></script>
	
	
	<style type="text/css">
		:fullscreen {
		position:fixed;
		top:0;
		left:0;
		right:0;
		bottom:0;
		z-index:2147483647;
		background:black;
		width:100%;
		height:100%;
		}

	</style>
	<script>
	
		
	
         $(function(){
		var loginperson=$('#loginperson').val();
			
			if(loginperson==null||loginperson==""){
			
			window.location='j_spring_security_logout';
			return false;
			
			}else{
			
			return true;
			loadProductData();
			}
		
		function loadProductData(){
		$.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"pages/company/getDealer2.do?start=0&limit=1", 
	      		dataType: "json", 
			    success: function(responseText){
			  alert(",,,,");
			 
			    }
			
		})
	}
	})

	</script>
	<script type="text/javascript">
	
		$(function(){  
			$('video').mediaelementplayer({
				enableAutosize: true,
				//poster: '<%=basePath%>/home/resource/images/vedio_pic1.jpg',
				features: ['playpause','progress','current','duration','tracks','volume','fullscreen'],
				backlightTimeout: 200
			});
		})
		
	
	
	</script>
	</head>
<body>
   <div class="wrapper">
	<div class="header">
  	<div class="logo">
    	<a href="index.html"><img src="<%=basePath%>home/resource/images/public/logo.png" width="103" height="62" alt="logo" /></a>
      <h1><input id="loginperson" value="${loginInfo.loginPerson.name}" type="hidden" >欢迎&nbsp;${loginInfo.loginPerson.name}</input></h1>
    </div>
    <!-- 
    <div class="header_search">
    	<input type="text" class="text" />
      <input type="submit" class="submit" value="" />
    </div>
    <div class="buy_car">购物车(<a href="buy_car_1.html">2</a>)</a></div>
     -->
    <div class="clearfix"></div>
    <div class="nav">
       <a href="<%=basePath%>index.jsp" target="_blank">订单管理</a>
      <a href="<%=basePath%>home/product/searchProduct.do" target="_blank">门类在线</a>
      <a href="<%=basePath%>home/diy/diy.html" target="_blank">DIY</a>
      <a href="<%=basePath%>home/dsi/index.jsp" target="_blank" >电子画册</a>
      <a href="<%=basePath%>index.jsp" target="_blank">客户管理</a>
      <a href="<%=basePath%>index.jsp" target="_blank">企业设置</a>
      <a href="<%=basePath%>index.jsp" target="_blank">设置中心</a>
    </div>
  </div>
	<div class="main clearfix">
  		<div class="c_banner"><img src="<%=basePath%>home/resource/images/company_banner.jpg" /></div>
        <div class="m_left fl">
        	<div class="pro_fl">
            	<h3><em><img src="<%=basePath%>home/resource/images/public/sort_ico.png" /></em>企业展示</h3>
                <ul class="fl_xl">
                    <li><span><a href="#"><img src="<%=basePath%>home/resource/images/ico01.png" /></a></span><a href="<%=basePath%>pages/company/qiyeDisplay.do">企业简介</a></li>
                	<li><span><a href="#"><img src="<%=basePath%>home/resource/images/ico01.png" /></a></span><a href="<%=basePath%>home/companyvede.jsp">企业视频</a></li>
                </ul>
            </div>
        </div>
        <div class="m_right fr">
        	  <div class="Title m_l_31"></div>
              <div class="cm_video">
                    <div class="video">
                  	
                    <tr><td>
                <c:forEach items="${dealList}" var="pro">
			<font size="4" color="white">企业介绍</font>
			<video controls="controls"  preload="none" width="780" height="470" poster="<%=path%>/johndyer-mediaelement-d2c8ce4/media/1.png"> 
				<source src="<%=basePath%>home/resource/movie/video/${pro.video}"   type="video/mp4" />
			</video>
			</c:forEach>
			</td>
		</tr>
		 
           </div>
                    <ul>
                        <li><a href="#"><img src="<%=basePath%>home/resource/images/vedio_pic2.jpg"/></a></li>
                        <li><a href="#"><img src="<%=basePath%>home/resource/images/vedio_pic2.jpg"/></a></li>
                        <li><a href="#"><img src="<%=basePath%>home/resource/images/vedio_pic2.jpg"/></a></li>
                        <li><a href="#"><img src="<%=basePath%>home/resource/images/vedio_pic2.jpg"/></a></li>
                        <li><a href="#"><img src="<%=basePath%>home/resource/images/vedio_pic2.jpg"/></a></li>
                    </ul>
              </div>
        </div>
    </div>
</div>
<div class="clear"></div>
<div class="footer">
  <div class="footer_top">
  	<a href="#"><img src="<%=basePath%>home/resource/images/public/footer_1.png" width="126" height="60" alt="" /></a>
    <a href="#"><img src="<%=basePath%>home/resource/images/public/footer_2.png" width="125" height="60" alt="" /></a>
    <a href="#"><img src="<%=basePath%>home/resource/images/public/footer_3.png" width="128" height="60" alt="" /></a>
    <a href="#"><img src="<%=basePath%>home/resource/images/public/footer_4.png" width="100" height="60" alt="" /></a>
  </div>
  <div class="footer_nav">
  	<a href="#">典尚</a>|<a href="#">公司介绍</a>|<a href="#">联系我们</a>|<a href="#">友情链接</a>|<a href="#">网站地图</a>
  </div>
</div>

  </body>
</html>
