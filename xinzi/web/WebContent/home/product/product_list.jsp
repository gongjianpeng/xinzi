<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>产品列表</title>
       <jsp:include page="../common/include.jsp"></jsp:include>
	<script type="text/javascript">
	
	 
	
	/**************根据产品类型去查询产品信息*****************/
	function productByTypeList(){
		var proName=$("#proName").text();
		$.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"getProductByType.do", 
	      		dataType: "json",
	      		data:{productName:proName},
			    success: function(responseText){
			    }
		})
	}
	
	
	
	
	
	/************加载右边产品分类数据*****************/
	function productDataRight(){
		$.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"productDataRight.do", 
	      		dataType: "json", 
			    success: function(responseText){
					//遍历返回数组的每一个实体
					var jsonArr=responseText.data;
	    	   		$.each(jsonArr,function(index, pro) {  
	    	   			//<li><a href="#"><img src="images/img1.jpg" /></a><br /><em><a href="#">万圣节骷髅头烛台</a></em></li>
	    	   		    var img="<a href='<%=basePath%>home/product/getProductByType.do?productName="+pro.name+"' id='proName' target='_blank'><img src='<%=basePath%>"+pro.photo+"'/></a><br/>";
	    	   		    var str="";
	    	   			var proclassUL=$("#rightClass").attr("class","rightClass");
	    	   		 	var proName="<em><a href='<%=basePath%>home/product/getProductByType.do?productName="+pro.name+"' id='proName' target='_blank'>"+pro.name+"</a></em>";
	    	   		 	str=img+proName;
	    	   		 	proclassUL.append("<li>"+str+"</li>");
		           })
			    }
		})
	}
	/*************加载所有数据************/
	function loadProductData(){
	var brandName=$("#brandName").text();
		$.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"home/product/searchProduct.do", 
	      		dataType: "text", 
	      		data:{brandName:brandName},
			    success: function(responseText){
			    }
		})
	}
	
	
	$(function(){
		
		//productDataRight();
		
		loadProductData();
	})
	</script>
  </head>
 <body>
 
<input id="brandName" name="brandName" value="<%=session.getAttribute("brandName")%>"   ></input>
  <div class="wrapper">
  
  <!--================== 头部==Begin================= -->
	 <jsp:include page="../common/header.jsp"></jsp:include>
  <!--================== 头部=End================== -->
   
	<div class="main">
  		<div class="n_banner"><img src="<%=basePath%>home/resource/images/n_banner.jpg" /></div>
        <div class="m_left fl">
        	<!-- 导入左边产品分类菜单 -->
        	 <jsp:include page="../common/left.jsp"></jsp:include>
        </div>
        
        <!-- 右边 -->
         <div class="m_right fr">
        	<div class="pro_list">
            	<ul class="rightClass" id="rightClass">
                	<c:choose>
                		<c:when test="${!empty searchproductList}">
	               			<c:forEach items="${searchproductList}" var="pro">
			           			<li>
			           				<a href="<%=basePath%>home/diy/getProductDetail.do?proId=${pro.id}&proName=${pro.proName}&brand=${pro.brand}">
			           				<img src="<%=basePath%>${pro.photo}" /></a><br />
			           				<em>
			           					<a href="<%=basePath%>home/diy/getProductDetail.do?proId=${pro.id}&proName=${pro.proName}&brand=${pro.brand}">
			           					${pro.proName}  ${pro.number} 	${pro.type} 
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
            
            <c:choose>
               		<c:when test="${!empty searchproductList}">
               		 <div class="clear"></div>
		            <div  style="float: left;margin-left: 200px;margin-top: 32px;">
						<span><a href="${pageContext.request.contextPath}/home/product/searchProduct.do?page=1">首页</a></span>
					 <c:choose>
			             <c:when test="${page-1<1}">
			           		 <span style="font-size: 20px;"> 上一页</span>
			             </c:when>
			             <c:otherwise>
			                 <span style="font-size: 20px;"> <a href="${pageContext.request.contextPath}/home/product/searchProduct.do?page=${page-1}">上一页</a></span>
			             </c:otherwise>
		             </c:choose>
		          
		             <c:choose>
		                 <c:when test="${page>(total/pageSize)}">
		                 	 <span style="font-size: 20px;">下一页</span>
			             </c:when>
			             <c:otherwise>
			             	<span> <a href="${pageContext.request.contextPath}/home/product/searchProduct.do?page=${page+1}">下一页</a></span>
			             </c:otherwise>
		             </c:choose>
				     	<span> <a href="${pageContext.request.contextPath}/home/product/searchProduct.do?page=<fmt:formatNumber value="${(total/pageSize)+1}" pattern="#"/>">尾页</a></span>
			    		&nbsp;&nbsp;<span style="font-size: 20px;">第<font style="color:red">${page}</font>页&nbsp;/&nbsp;总<font style="color:red ">${total}</font>页</span>
		    		</div>
               		</c:when>
              </c:choose>
           
        </div>
    </div>
</div>


<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
