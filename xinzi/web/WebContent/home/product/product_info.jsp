<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>产品列表</title>
      <jsp:include page="../common/include.jsp"></jsp:include>
      <style type="text/css">
      	.tip_error{
	      	font-size: 30px;
			font-weight: 700;
			color: #333;
			margin-top:200px;
			margin-bottom: 200px
      	}
      </style>
  </head>
  <script type="text/javascript">
function exit(){
	window.location='j_spring_security_logout';
}
</script>
 <body>

  <div class="wrapper">
  
	 <jsp:include page="../common/header.jsp"></jsp:include>
  
	<div class="main">
  		<div class="n_banner"><img src="<%=basePath%>/home/resource/images/n_banner.jpg" /></div>
        <div class="m_left fl">
        		<!-- 导入左边产品分类菜单 -->
        	  <jsp:include page="../common/left.jsp"></jsp:include>
        </div>
       <div class="m_right fr">
       	<div class="pro_list">
           	<ul class="proList_ul_class" id="proList_ul_class"> 
           	
           		<c:choose>
	           		<c:when test="${!empty prodList}">
	           				<c:forEach items="${prodList}" var="pro">
		           			<li>
		           				<a href="<%=basePath%>home/diy/getProductDetail.do?proId=${pro.id}&proName=${pro.proName}&brand=${pro.brand}">
		           				<img src="<%=basePath%>${pro.photo}" /></a><br />
		           				<em>
		           					<a href="<%=basePath%>home/diy/getProductDetail.do?proId=${pro.id}&proName=${pro.proName}&brand=${pro.brand}">
		           						${pro.proName}
		           					</a>
		           					<a href="<%=basePath%>home/diy/getProductDetail.do?proId=${pro.id}&proName=${pro.proName}&brand=${pro.brand}">
		           						${pro.number}
		           					</a>
		           					<a href="<%=basePath%>home/diy/getProductDetail.do?proId=${pro.id}&proName=${pro.proName}&brand=${pro.brand}">
		           						${pro.type}
		           					</a>
		           				
		           				</em>
		           			</li>
		           		</c:forEach> 
	           		</c:when>
	           		<c:otherwise>
	           				<div style="width: 100%;height: 400px;text-align: center;float: left;margin-top: 200px;">
			           			<span class="tip_error">
			           			<img alt="" src="<%=basePath%>home/resource/images/xl_ico.png">典尚温馨提示您！很抱歉该产品类型没货!
			           			</span>
	           				</div>
	           		</c:otherwise>
           		</c:choose>
           	 
           	</ul>
           </div>
           
           
           <div class="clear"></div>
           <!-- 
           <div class="page">
           	<em class="fl"><a href="#"><img src="<%=basePath%>home/resource/images/l_prev.png" /></a></em>
               <span><a href="#">1</a>
               <a href="#">2</a>
               <a href="#" class="cur">3</a>
               <a href="#">4</a>
               <a href="#">5</a>
               <a href="#">...</a>
               <a href="#">179</a>
               </span>
               <em class="fr"><a href="#"><img src="<%=basePath%>home/resource/images/r_next.png" /></a></em>
           </div>
            -->
       </div>
        
    </div>
</div>

<!-- 导入底部 -->
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
