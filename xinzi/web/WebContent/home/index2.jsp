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
	//	loginPersonforBrand();
	var mynamelod=$("#myname").val();
	if(mynamelod==null){
	//$("#myname").val("");
	
	}
		
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
             var indexname=name;
           //  alert("*"+indexname+"*");
             var tt=indexname.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   
              //alert("*.."+tt+"*");
             $.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"pindex.do", 
	      		dataType: "json",
	      		data:{pinName:tt},
			    success: function(responseText){
			    	//$('#box,.dialog-boxs').hide();
			    }
			
			 //       document.getElementById("box").style.display='block';//显示id="r1"的div标签
			    });
			  
			      //alert(name);
		
        //   session.setAttribute("pinpaisession",name); 
               var myname="<%=session.getAttribute("brandName")%>"; 
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
	      		dataType: "json",
	      		//data:{productName:proName},
			    success: function(responseText){
			    	var jsonArr=responseText.data;
			    	alert(jsonArr);
			    	$.each(jsonArr,function(index, pro) {  
	    	   			
	    	   		 	
		           })
			    }
		})
	}
	

</script>
<ol><li><br></li></ol>
<script language="JavaScript"> 
function getMyName(){ 
   var myname="<%=session.getAttribute("brandName")%>"; 
 //  alert(myName); 
  $("#myname").val(myname);
}	
function turnoff(){
 var ss=$("#myp").val().trim();
if(ss == null||ss==""||ss =="null"){
alert("当前无品牌");
return false;
}
if(ss =="null"){
//alert("...l"+ss+"l...");
//document.getElementById(box).class.display="none";

}else{
$('#box,.dialog-boxs').hide();
}
}

function check(id){
    var ss=$("#myp").val().trim();
if(ss == null||ss==""||ss =="null"){
alert("当前无品牌");

}else{
var url='';
switch(id){
	case 1:url='<%=basePath%>pages/company/qiyeDisplay.do';
	break;
	case 2:url='<%=basePath%>home/dsi/index.jsp';
	break;
	case 3:url='<%=basePath%>home/diy/diy.html';
	break;
	case 4:url='<%=basePath%>home/product/searchProduct.do';
	break;
}
console.log(url);
 window.location.href=url;
}
 return  false;       
       
   
}
</script> 
<body>


<input id="" name="" value="<%=session.getAttribute("brandName")%>"   type="hidden"></input>

<div id="page" style="overflow:hidden;">
	<div id="north-panel" >
	
		<div style="height:60px">
			<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				<tr>
				   
					<td scope="row" class="out" ><font size="6" > </font>	</td>
					<td width="250" align="center" scope="row" >
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
            <li id="btn1"><a href="javascript:void(0);" target="_blank"  onmouseup="check(1)">企业展示</a></li>
            <li id="btn2"><a href="javascript:void(0);"  onmouseup="check(2)" >电子画册</a></li>
            <li id="btn3"><a href="javascript:void(0);"  onmouseup="check(3)">产品定制</a></li>
            <li id="btn4"><a href="javascript:void(0);"  onmouseup="check(4)">网上商城</a></li>
            
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
<div id="box" class="pop_box">  <img class="imgClose" src="images/closeimg.gif"> 
    <div class="pop_box_Con">
    	      <a  href="<%=basePath%>home/getLoginPersonBrand.do" style="color:#FFFAF0"  onclick="loginPersonforBrand()" >查看授权品牌</a>
    
    	<p>典尚DIY</p> 	<p>您选择的品牌:<input id="myp" name="myp" value="<%=session.getAttribute("brandName")%>"   readonly="readonly"></input><input type="button" value="进入" onclick="turnoff()" ></p>
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