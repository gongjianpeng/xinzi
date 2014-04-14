<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>填写核对订单</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=Edge" >
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="<%=basePath%>home/resource/css/commom_style.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>home/resource/css/component_style.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>jquery/js/jquery.min.js" type="text/javascript" ></script>
	<jsp:include page="../common/include.jsp"></jsp:include>
	
	<script type="text/javascript">
		
		$(function(){
			
			
			$("#divSearchTitle") .click( function() {
				$("#divSearch").slideToggle("slow");
				if ($("#spSlide").attr("class") == "glyphicon glyphicon-chevron-up") {
					$("#spSlide").removeClass("glyphicon-chevron-up").addClass('glyphicon-chevron-down');
				} else {
					$("#spSlide").removeClass("glyphicon-chevron-down").addClass('glyphicon-chevron-up');
				}
			});
			
		  $("#saveShouHuoInfo").click(function(){
			  
			   var userName=$("#user_name").val() ;// 客户姓名
			   var mobile=$("#phone").val();//联系电话
			   var telephone=$("#tel").val();//手机
			   var ordertime=$("#jd_time").val();//接单时间
			   var zip=$("#zip").val();//  交货时间
			   var deliveryTime=$("#jh_time").val();//  交货时间
			   var address=$("#address").val();// 收货地址
			   var company=$("#company").val();//公司
			   var remark=$("#remark").val();//备注
		  
			  $.ajax({
				type: "POST", 
				url:'<%=basePath%>home/order/saveReceiptInfo.do', 
	      		dataType: "json", 
	      		data: {
		      		 userName: userName  ,// 客户姓名
				     mobile:mobile ,//联系电话
				     zip:zip,//邮政编码
				     telephone:telephone,//手机
				     ordertime:ordertime,//接单时间
				     deliveryTime:deliveryTime,//  交货时间
				     address:address,// 收货地址
				     company:company,//公司
				     remark:remark//备注
				},success: function(responseText){ 
	      			var flag=responseText.result;
	      			if(flag=="success"){
	      				alert("保存成功!!"); 
	      				return ;
	      			}
	      			else{
	      				alert("保存失败!"); 
	      			}
	      		}
	  	     });
		  });//保存收货人信息end
		  
		  
		  
		  //提交订单
		  $("#submitOrder").click(function(){
			   var userName=$("#user_name").val().trim() ;// 客户姓名
			   var mobile=$("#phone").val();//联系电话
			   var telephone=$("#tel").val();//手机
			   var ordertime=$("#jd_time").val();//接单时间
			   var zip=$("#zip").val();//  交货时间
			   var deliveryTime=$("#jh_time").val();//  交货时间
			   var address=$("#address").val();// 收货地址
			   var company=$("#company").val();//公司
			   var remark=$("#remark").val();//备注
			  
			   if(userName==null||userName==""||address==null||address==""||company==null||company==""||ordertime==null||ordertime==""){
			   alert("请填写完整收货人信息");
			   return false;
			   }else{
			  
			     $.post("<%=basePath%>home/order/submitOrder.do",
			     {operation:'orderin',
			    
			       userName: userName  ,// 客户姓名
				     mobile:mobile ,//联系电话
				     zip:zip,//邮政编码
				     telephone:telephone,//手机
				     ordertime:ordertime,//接单时间
				     deliveryTime:deliveryTime,//  交货时间
				     address:address,// 收货地址
				     company:company,//公司
				     remark:remark//备注
			      },
			     function(responseText){
			 	  	var result=responseText.result;
			 	  	var OrderNO=responseText.OrderNO;
			 	  	console.log(responseText.OrderNO)
			 	  	 
			 	  	if(result=="success"){
			            alert("订单已经提交！");
			            window.location.href="<%=basePath%>home/order/getOrderNo.do?OrderNo="+OrderNO;
			 	  	}else{
			 	  	   alert("订单提交失败！");
			 	  	}
       			},"JSON");
       			};
		  });
		  
		
		
		  
		});
	</script>
	
	  <script type="text/javascript">
	  
	    var one = 0;
	 
	  function Mycheck(){
      	/****
	   $("em[name='xiaojiPrice']").each(function () {
		     var xiaojiPrice=$("em[name='xiaojiPrice']").html();
		     one += parseFloat(xiaojiPrice);
		     console.log(xiaojiPrice+"   "+one);  
		  
        });*****/
          var count = 0;
      var obj = $("em[class='xiaojiPrice']");
     for(var i=0;i<obj.length;i++){
     	
     	one+=parseInt(obj.eq(i).html());
     	  console.log(xiaojiPrice+"   "+one);
     }
       
       //alert(one);
       var total=0;
       var totaltwo=0;
       //alert(one);
       total=$("#total").val(one); 
       totaltwo=$("#totaltwo").val(one); 
       $("#Pro_Total").attr("disabled","disabled"); 
        
	  }
	  
	  function shoufu(){
	    var totaltwo=$("#totaltwo").val(); 
	    var bili=$("#bili").val(); 
	   
	    var bili2=parseFloat(bili);
	    
	    var shoufa=totaltwo*bili2/100;
	    
	   // alert(shoufa);
	     shoufu=$("#shoufu").val(shoufa); 
	  }
		  
		  var  tone=0;
	 function Mycheck2(){
      	//var one = 0;
     var count = 0;
      var obj = $("em[class='num']");
     for(var i=0;i<obj.length;i++){
     	
     	count+=parseInt(obj.eq(i).html());
     }
     
     $("#cartsee").val(count);
      return;
	  
       
     
       
	  }
		  
		  
	  </script>
	
	 <script type="text/javascript">
	 
	 
	 
 	function loadProductByUser(){
			var userId=$("#userId").val();
			//alert(userId);
			$.ajax({
				type: "POST", 
				url:'<%=basePath%>'+"home/cart/getCartdetail2.do", 
	      		dataType: "json", 
	      		data:{userId:userId},
			    success: function(responseText){
					 var result=responseText.result;
					 if(result=="1"){
						 window.location.href="<%=basePath%>home/order/check_orders.jsp";
					 }
			    }
			})
		}
 
 	$(function(){
 		loadProductByUser();
 		
			 
	 
	  Mycheck();
	  shoufu();
			 Mycheck2();
	
 	})
 </script>
 </head>
 <body>
  <input value="${userInfo.id}"  name="userId"  id="userId"  type="hidden"/>
 <%

String userId=request.getParameter("userId");
//response.sendRedirect(basePath+"home/cart/getCartdetail.do?userId="+userId);
%>
<div class="wrapper">
	<!-- 头部 -->
	<jsp:include page="../common/header.jsp"></jsp:include>
	
  <div class="clear"></div>
	<div class="main buy_content">
  	<div class="car_nav">
    	<ul>
      	<li class="cur"><h2><i>1</i>我的购物车</h2><span></span></li>
        <li class="cur active"><h2><i>2</i>填写核对订单</h2><span></span></li>
        <li><h2><i>3</i>订单提交成功</h2></li>
      </ul>
    </div>
    
    
    <div class="form_con" id="divSearch">
    	<h2 class="title" id="titleMsg">填写核对订单信息</h2>
	      <ul>
	      	<li><span>客户姓名：</span><input type="text" class="text" id="user_name" index="0" /></li>
	        <li>
		        <span>联系电话：</span>
		        <input type="text" class="text" value="手机号码" id="phone" index="1" />
		        <i>或</i><input type="text" class="text" value="固定电话" id="tel" index="2" />
		     </li>
		     <li><span>邮政编码：</span><input type="text" class="text" id="zip"  /></li>
	        <li><span>接单时间：</span><input type="text" class="text" id="jd_time"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" index="3" /></li>
	        <li><span>交货时间：</span><input type="text" class="text" id="jh_time"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" index="4" /></li>
	         <li><span>公司：</span><input type="text" class="text" id="company"  /></li>
	        <li><span>收货地址：</span><input type="text" class="text text_2" id="address" index="5" /></li>
	        <li><span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span><textarea class="text text_con" index="6">送货前确认是否在家</textarea></li>
	      </ul>
	      <input type="submit" value="保存收货人信息" class="button_1"  id="saveShouHuoInfo"/> 
	       
    </div>
    
    
    
    <div class="order_list">
      <div class="title_1">发货清单</div>
      <table width="100%" cellspacing="0" cellpadding="0" class="b_cars">
        <thead class="buy_car_head">
            <tr align="center">
            	<td width="1%">&nbsp;</td>
              <td width="45%" align="left">商品名称</td>
              <td>单价</td>
              <td>数量</td>
              <td>小计</td>
            </tr>
        </thead>
        	<c:if test="${cartList!=null}">
        		<c:forEach items="${cartList}" var="cart" varStatus="vs">
            	<tr>
            	<td width="4%">&nbsp;</td>
            
		                	<input type="hidden" value="${cart.id}"  class="a"/>
		        
		              
             	 <td>
              	  <div class="goods">
		                    <img src="<%=basePath%>home/resource/imageforcart/${cart.photo}" style="width:100px;height:150px" class="fl m_r_14" />
		                    <span>
			                   
			                    <em><i>品牌：${cart.brand}</i><i>类型：${cart.type}</i><i></i></em>
			                    <em><i>尺寸：宽 ${cart.sizekd} 高${cart.size}</i></em>
			                    <em><i>款式名称： ${cart.doorstylename}</i>
			                    	<i>开向：${cart.opento}</i>
			                    	<i>颜色：${cart.colour}</i>
			                    </em>
			                    <em><i>锁具： ${cart.suojuName}</i>
			                    	<i>猫眼：${cart.maoyanName}</i>
			                    	<i>拉手：${cart.lashouName}</i>
			                    </em>
			                    <em><i>备注：${cart.remark}</i></em>
			                     <em><i>ID：${cart.id}</i></em>
		                    </span>
		                </div>
              	</td>
              	<td class="jg" align="center"><em   id="xiaosizett55" name="xiaosizett55">${cart.cartunitprice}</td>
              	 <td class="te_c" align="center"><span><em class="num" id="xiaosizett44" name="xiaosizett44">${cart.cartsize}</span></td>
              <td class="jg" align="center" >￥<em  class="xiaojiPrice"  id="xiaojiPrice"   name="xiaojiPrice">${cart.carttotalprice}</em></td>
             
              
            </tr>
           </c:forEach>
          </c:if>
      </table>
      
      <div class="jg_Total">
            <span class="fr p_r_30">
            	<em><i id="buyNum"> <input id="cartsee" name="cartsee" readonly="readonly"   style="border-left-style:none;border-right-style:none;width:25px;height:10px"  ></i>件商品&nbsp;&nbsp; 总计：</em> <em class="f_14_c">￥<i class="f_26"  id="total2" name="total2"></i>
            	<input type="text" size="3"  name="total" id="total"  readonly="true">
            	</em>
            </span>
            <input type="submit" name="Submit" value="商品总价 " id="Pro_Total" onClick="Mycheck()">
        </div>
        <div class="first_pay fr">
        	<span>首付款：￥<input type="text" size="3"  name="totaltwo" id="totaltwo" readonly="true"> 
        	              ×<input  id="bili"  name="bili"  value="20" type="text" class="f_p_text01"  onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"/>
        	              %=<input name="shoufu" id="shoufu"  value="" type="text" class="f_p_text02"   onMouseOver="shoufu()"      onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');" readonly="true"/>元</span>
            <br />
       	   <a href="<%=basePath %>home/order/check_orders.jsp"> <br>  </a>
       	   <li><input class="tj_btn" type="submit" value="提交订单" name="submitOrder" id="submitOrder"></input></li>
        </div>
        <!-- 
      <div class="order_accounts">
      	<ul>
      	  <input type="submit" name="Submit" value="商品总价 " id="Pro_Total" onClick="Mycheck()">
         <li>${cart.cartsize}件商品，总商品金额：<em id="total2" name="total2">${cart.carttotalprice}</em></li>
         <input type="text" size="3"  name="total" id="total"  readonly="true">
        </ul>
          
        	<span>首付款：￥<input type="text" size="3"  name="totaltwo" id="totaltwo" readonly="true"> 
        	              ×<input  id="bili"  name="bili"  value="20" type="text" class="f_p_text01"  onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"/>
        	              %=<input name="shoufu" id="shoufu"  value="" type="text" class="f_p_text02"   onMouseOver="shoufu()"      onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');" readonly="true"/>元</span>
            <br />
      <li><input class="tj_btn" type="submit" value="提交订单" name="submitOrder" id="submitOrder"></input></li>
        </div>
        
    </div>
     -->
  </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include> 

<script type="text/javascript">
<!--
$(function(){
	//$.formValidator.initConfig({autotip:true,formid:"center_form",onerror:function(msg){}});
	//$("#user_name").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).inputValidator({min:2,max:20,onerror:"用户名应该为2-20位之间"});
	//$("#phone").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机或者电话号码格式错误"});
	//$("#tel").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"tel",datatype:"enum",onerror:"手机或者电话号码格式错误"});
	//$("#jd_time").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"date",datatype:"enum",onerror:"请输入正确的日期"});
	//$("#jh_time").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"date",datatype:"enum",onerror:"请输入正确的日期"});
})
-->
</script>
</body>
</html> 
