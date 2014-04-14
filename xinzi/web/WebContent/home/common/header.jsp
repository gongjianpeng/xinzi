<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
<!--
.diy-search-input {
	border-top-left-radius: 5px; /*给文本框的左边顶部画5个像素的圆边框*/
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	border-bottom-left-radius: 5px;
}
 
-->
</style>

<script type="text/javascript">


	function search(){
		var search=$("#searchCondition").val();
		
		location.href="<%=basePath%>home/product/searchProduct.do?searchCondition="+search;
	}
	
</script>
 <div class="header">
	  	<div class="logo">
	    	<a href="<%=basePath%>home/index.jsp"><img src="<%=basePath%>home/resource/images/public/logo.png" width="103" height="62" alt="logo" /></a>
	     <h1>欢迎&nbsp;${loginInfo.loginPerson.name}</h1>
      	
	    </div>
	    <!-- 搜索条件 -->
	    <div class="header_search">
	   			 <input type="text" class="text" name="searchCondition" id="searchCondition" value="${param.keyword==null?"":param.keyword}" placeholder="请输入"/>
	      	<a href="javascript:search()"><input type="button" class="submit diy-search-input" /></a>
	    </div>
	    
	    <div class="buy_car"><a href="<%=basePath%>pages/company/getdiyProductaddCat.do">购物车</a></div>
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
   