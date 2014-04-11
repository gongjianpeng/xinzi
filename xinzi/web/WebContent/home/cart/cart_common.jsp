<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
  <head>
  	<title></title>
  		<jsp:include page="../common/include.jsp"></jsp:include>
</head>
<body >
 <input type="hidden" value="${userInfo.id}"  name="userId"  id="userId"/>
 <input id="brandName" name="brandName" value="<%=session.getAttribute("brandName")%>"   ></input>
 <%

String userId=request.getParameter("userId");
//response.sendRedirect(basePath+"home/cart/getCartdetail.do?userId="+userId);
%>
 <script type="text/javascript">
 	function loadProductByUser(){
			var userId=$("#userId").val();
			var  brandName=$("#brandName").val();
			//alert(userId);
			$.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"home/cart/getCartdetail.do", 
	      		dataType: "json", 
	      		data:{userId:userId,brandName:brandName},
			    success: function(responseText){
					 var result=responseText.result;
					 if(result=="1"){
						 window.location.href="<%=basePath%>home/cart/buyer_cart.jsp";
					 }
			    }
			})
		}
 
 	$(function(){
 		loadProductByUser();
 	})
 </script>
</body>
</html>
