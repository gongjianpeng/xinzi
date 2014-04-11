<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.internalaudit.audit.entitys.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>尚典首页</title>
  	<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jay.js"></script>
<script type="text/javascript" src="js/searchJs.js"></script>
    <jsp:include page="common/include.jsp"></jsp:include>
  </head>
 

  
  <script type="text/javascript">
		$(function(){
		loginPersonforBrand();
		getMyName();
		
			var loginperson=$('#loginperson').val();
			
			if(loginperson==null||loginperson==""){
			
				window.location.href='<%=basePath%>login.do';
			return false;
			
			}else{
			
			return true;
			}
			
		})
		
	</script>

<body class="index_bg">

<script type="text/javascript">
function exit(){
	window.location.href='<%=basePath%>login.do';
}


            function  pinpaiNameid(name){
           //  alert(name);
             
             $.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"pindex.do", 
	      		dataType: "json",
			    success: function(responseText){
			    	//$('#box,.dialog-boxs').hide();
			    }
			
			 //       document.getElementById("box").style.display='block';//显示id="r1"的div标签
			    });
			  
			   //   alert(name);
		
        //   session.setAttribute("pinpaisession",name); 
         //     var myName="session.setAttribute("name")%>"; 
         // alert(myName);
            
            
            }

/**************根据产品类型去查询产品信息
* 1 当前 登录人，
 2 经销商   获取它代理的品牌 ，返回数据展示.
 3 如果是企业 同样显示查询自己的品牌，返回数据.
*****************/
	function loginPersonforBrand(){
		//var proName=$("#proName").text()
		$.ajax({
				type: "POST", 
				
				url:'<%=basePath%>'+"home/getLoginPersonBrand.do", 
	      		dataType: "text",
	      		//data:{productName:proName},
			    success: function(responseText){
			    	var jsonArr=responseText.data;
			    	//alert(jsonArr);//zh这里的jsonArr 
			    	$.each(jsonArr,function(index, pro) {  
	    	   			
	    	   		 	
		           })
			    }
		})
	}
	$(document).ready(function(){
	
		loginPersonforBrand();
	});

</script>
<script language="JavaScript"> 
function getMyName(){ 
   //var myName="<%=session.getAttribute("brandName")%>"; 
  
    }	
</script> 
<body >


<font size="6"  style="color:#FFFAF0" ></font><input id="" name="" value="<%=session.getAttribute("brandName")%>" readonly="readonly" type="hidden" ></input>

<div id="page" style="overflow:hidden;">
	<div id="north-panel" >
	
		<div style="height:60px">
			<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				<tr>
				   
					<td scope="row" class="out" ><font size="6" > </font>	</td>
					<td width="150" align="center" scope="row" >
					<b> <input id="loginperson" value="${loginInfo.loginPerson.name}" type="hidden" ><font size="6"  style="color:#FFFAF0;width:200px;" > 欢迎登录&nbsp;${loginInfo.loginPerson.name}</font></input></b>
					</td>
					<td align="left" class="out" scope="row" style="width: 210px;">
					    <a  href="#" style="color:#FFFAF0"  onclick="exit()" >退出</a>
					 
									</td>
				</tr>
			</table>
		</div>
		   


<div class="In_wrap">
	<div class="In_logo"><img src="<%=basePath%>home/resource/images/Index_logo.png" /></div>
    <div class="banner">
    	<span><img class="pc" src="<%=basePath%>home/resource/images/banner_bg.png" /></span>
        <div id="focus">
        <ul>
            <li><img src="<%=basePath%>home/resource/images/banner_1.png"/></li>
            <li><img src="<%=basePath%>home/resource/images/banner_2.png"/></li>
        </ul>
        </div>
    </div>
    <div class="In_menu">
    	<ul>
            <li><a href="<%=basePath%>pages/company/qiyeDisplay.do" target="_blank">企业展示</a></li>
            <li><a href="<%=basePath%>home/dsi/index.jsp" target="_blank" >电子画册</a></li>

            <li><a href="<%=basePath%>home/diy/diy.html" target="_blank">产品定制</a></li>
            <li><a href="<%=basePath%>home/product/searchProduct.do" target="_blank">网上商城</a></li>
            <li><a href="<%=basePath%>index.jsp" target="_blank">订单管理</a></li>
            <li><a href="<%=basePath%>index.jsp" target="_blank">客户管理</a></li>
            <li><a href="<%=basePath%>index.jsp" target="_blank"  class="inputClick">设置中心</a></li>
        
        </ul>
    </div>
    <div id="123">
  
              
     <table border="1" cellspacing="0" cellpadding="0" width="100%" style="align:center;">
    <tr bgcolor="ff9900" style="font-weight:bold;">
   
      
  
   </table>
 </div>
</div>
  <!-- 右边 -->
       

<!--弹出框-->
<div class="dialog-boxs"></div>
<div id="box" class="pop_box" onload="loginPersonforBrand()"> <img class="imgClose" src="images/closeimg.gif">
    <div class="pop_box_Con">
    	    <a  href="<%=basePath%>home/getLoginPersonBrand.do" style="color:#3D9140;font-weight:bold;font-size:18px"  onclick="loginPersonforBrand()" >查看授权品牌</a>
     
    	<p>典尚DIY</p> 	<p>您选择的品牌:</p>
    	<c:choose>
                		<c:when test="${!empty searchproductList}" >
    	    <c:forEach items="${searchproductList}" var="pro">
			           			<li>
			           				<em>
			           					<a id="pinpaiNameid" name="pinpaiName" href="" onclick="pinpaiNameid('${pro.name}')" >
			           						${pro.name}
			           					</a>
			           				</em>
			           			</li>
			           		</c:forEach> 
			           			</c:when>
                		<c:otherwise>
	           				
			           		
	           				
	           		</c:otherwise>
                	</c:choose>
			           		<c:choose>
                		<c:when test="${!empty sb2}">
			           		  <c:forEach items="${sb2}" var="pro">
			           			<li>
			           				<em>
			           					<a id="pinpaiNameid" name="pinpaiName" href="" onclick="pinpaiNameid('${pro}')" >
			           						${pro}
			           					</a>
			           				</em>
			           			</li>
			           		</c:forEach> 
			           		</c:when>
                		<c:otherwise>
	           					<span class="tip_error">
			           			<!--  <img alt="" src="<%=basePath%>home/resource/images/xl_ico.png">典尚温馨提示您！很抱歉未找到品牌!  -->
			           			</span>
	           		</c:otherwise>
                	</c:choose>
			           		
			           		</div>
    </div>
</div>
</body>
</html>