<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


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
 