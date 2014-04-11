<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
    <head>
		<title>企业展示</title>
	    <meta http-equiv="X-UA-Compatible" content="IE=Edge" >
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="<%=basePath%>home/resource/css/default-style.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>home/resource/css/reset.css" rel="stylesheet" type="text/css" />
		
		<link href="<%=basePath%>home/resource/css/commom_style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>home/resource/css/component_style.css" rel="stylesheet" type="text/css" />

<script src="<%=basePath%>home/resource/js/jquery-1.9.1.js" type="text/javascript" ></script>
<script src="<%=basePath%>home/resource/js/tab_jay.js" type="text/javascript" ></script>
<script src="<%=basePath%>home/resource/js/formvalidatorregex.js" type="text/javascript" ></script>
<script src="<%=basePath%>home/resource/js/formvalidator.js" type="text/javascript" ></script>

<link href="<%=basePath%>home/resource/css/jquery-ui/jquery.rollbar.css" rel="stylesheet"/>
<link href="<%=basePath%>home/resource/css/jquery-ui/jquery.ui.all.css"  rel="stylesheet" />
 <style type="text/css"> 
input 
{ 
  border-top:0px ; 
  border-left:0px ; 
  border-right:0px ; 
  width:800px;
  height:200px;
} 
</style> 
<!-- jQuery-ui -->
<script src="<%=basePath%>home/resource/js/jquery-ui/jquery.mousewheel.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/jquery-ui/jquery.rollbar.min.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/jquery-ui/jquery.select.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/jquery-ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/jquery-ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/jquery-ui/jquery.ui.accordion.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/jquery-ui/jquery.adslider.js" type="text/javascript"></script>

<script src="<%=basePath%>home/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<!--DIY Moduler -->
<script src="<%=basePath%>home/resource/js/diy_searchJs.js" type="text/javascript"></script>


<script src="<%=basePath%>home/resource/js/jquery-ui/easeljs-0.7.0.min.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/product/diy_cpanel.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/product/diy_material.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/product/diy_ui.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/product/diy_core.js" type="text/javascript"></script>
<script src="<%=basePath%>home/resource/js/product/diy_canvas.js" type="text/javascript"></script>

<!-- cookie -->
<script src="<%=basePath%>home/resource/js/cookie.js" type="text/javascript"></script>
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
	
	
	<script>
function check(textbox){
//得到当前form对象
var oform=textbox.form;
var endLength=oform.elements.length-1;

//判断是否为最后一个元素
if(oform.elements[endLength]!=textbox){
    //判断是否长度相等
    if(textbox.value.length==textbox.getAttribute("maxlength")){
      for(i=0;i<=endLength;i++){
          if(oform.elements[i]==textbox){
               //判断下一个对象是否为隐藏对象
               if(oform.elements[i+1].type!="hidden"){
                   oform.elements[i+1].focus();
              }
          }
      }
  }
}



}
</script>
	</head>
  
 <body>
 

<div class="wrapper">
	<div class="header">
  	<div class="logo">
    	<a href="#"><img src="<%=basePath%>home/resource/images/public/logo.png" width="103" height="62" alt="logo" /></a>
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
     
      <a href="<%=basePath%>home/product/searchProduct.do" target="_blank">门类在线</a>
      <a href="<%=basePath%>home/diy/diy.html" target="_blank">DIY</a>
      <a href="<%=basePath%>home/dsi/index.jsp" target="_blank" >电子画册</a>
      <a href="<%=basePath%>index.jsp" target="_blank">客户管理</a>
      <a href="<%=basePath%>index.jsp" target="_blank">企业设置</a>
       <a href="<%=basePath%>index.jsp" target="_blank">订单管理</a>
      <a href="<%=basePath%>index.jsp" target="_blank">设置中心</a>
    </div>
  </div> 
	<div class="main clearfix">
  		<div class="c_banner"  style="width:1190px;height:358px"> <img src="<%=basePath%>home/resource/pic/pic/${dealer.picturebook}"  width="1190" height="357" /></div>
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
        	<div class="com">
            	<div class="Title"><img src="<%=basePath%>home/resource/images/comtitle.gif" /></div>
                <p class="t_indent" id="cdtail" readonly="readonly" class="input"   />
              
                <br />
                <p class="t_indent" id="edtail">${dealer.intro}</p>
                <p class="t_indent" id="edtail">${dealer.remark}</p>
                <br />
                <br />
                <h3></h3>
                <p></p>
                <br />
               
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
