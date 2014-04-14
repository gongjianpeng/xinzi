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
  	<title>我的购物车</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=Edge" >
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="<%=basePath%>home/resource/css/commom_style.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>home/resource/css/component_style.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>jquery/js/jquery.min.js" type="text/javascript" ></script>
	
	<style type="text/css">
	.cart_number{
		width:34px; height:20px; border:1px solid #ccc;
		margin:0 5px; display:inline-block;line-height:22px;
	}
	</style>
	<script type="text/javascript">
		/***********全选***********/
		function checkAll(){
		  	$("input[type='checkbox']").each(function() { 
				$(this).prop("checked", true); 
				var ddid=$("#delIds").val();
				
			//	location.href="<%=basePath%>home/cart/delCartGoods.do?goodsId="+ddid;
			}); 
		}
		/***********反选***********/
		function NocheckAll(){
			var goodsCart=$("input[type='checkbox']");
			for(i=0;i<goodsCart.length;i++){			
				if(goodsCart[i].checked){
					goodsCart[i].checked=false;
				}else{
					goodsCart[i].checked=true;
				}
			}
		}
		
		function checkdeleAll(){
		
			$("input[type='checkbox']").each(function() { 
				$(this).prop("checked", true); 
				var goodsId=$("#delIds").val();
				$.post("<%=basePath%>home/cart/delCartGoods.do",{goodsId:goodsId},function(responseText){
					var result=responseText.result;
					if(result=="1"){
						window.location.reload();//自动刷新本页面
					}else{
						window.location.href="<%=basePath%>home/cart/clear_cart.jsp";
					}
				},"JSON");
			
			});
		
				
		
			
		
		}
		
		/*****删除购物车的一个商品**/
		function delCartGoods(val){
			if(confirm("数据不可恢复!请确定是否删除!")){
				location.href="<%=basePath%>home/cart/delCartGoods.do?goodsId="+val;
			}
			//$("#buyNum").html("");
			//$("#total").html("");
		}
		/*****删除选中购物车的一个商品**/
		function delCartSelectGoods(val){
			var cflag=$("input[type='checkbox']").is(':checked')
			alert(cflag);
			console.log("cflag=="+cflag);
			return ;
			if(cflag){
				if(confirm("数据不可恢复!请确定是否删除!")){
					location.href="<%=basePath%>home/cart/delCartSelectGoods.do?goodsId="+val;
				}
			}else{
				alert("您还没选中要删除的商品,请选中商品再删除!");
				return ;
			}
		}
		
		/***********不允许输入非法数字必须是0-9的数字***********/
		function checkNumber(e){
			var temp=true;
			switch(e.keyCode){
			    case 48:break;
				case 49:break;
				case 50:break;
				case 51:break;
				case 52:break;
				case 53:break;
				case 54:break;
				case 55:break;
				case 56:break;
				case 57:break;
				case 8:break;
				case 96:break;
				case 97:break;
				case 98:break;
				case 99:break;
				case 100:break;
				case 101:break;
				case 102:break;
				case 103:break;
				case 104:break;
				case 105:break;
				case 46:break;
				case 110:break;
				case 190:break;
				default:temp= false;
   			}
		   if($("input[name='number']").val().length>7&&e.keyCode!="8"){
			   temp=false;
		   }
   		 return temp;
  	}
			
		
	  var one = 0;
	 
	  function Mycheck(){
	  
	  
	    	//var one = 0;
     var count = 0;
      var obj = $("em[class='xiaojiPrice']");
     for(var i=0;i<obj.length;i++){
     	
     	one+=parseInt(obj.eq(i).html());
     	  console.log(xiaojiPrice+"   "+one);
     }
    
    
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
	  
     //$(document).on("click","#deleteButton",function(){})
        function deletecart(aa){
      		 
     	 	var  goodsId=aa; //注意Id必须为唯一的、。。
     	 	//alert(goodsId);
              if(confirm("数据不可恢复!请确定是否删除!")){
				$.post("<%=basePath%>home/cart/delCartGoods.do",{goodsId:goodsId},function(responseText){
					var result=responseText.result;
					if(result=="1"){
						window.location.reload();//自动刷新本页面
					}else{
						window.location.href="<%=basePath%>home/cart/clear_cart.jsp";
					}
				},"JSON");
			}
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
	
		$(function(){
			 var inputs=$(".a");
	 console.log(inputs.length);  
	 var cartsee=inputs.length;
	 
	  Mycheck();
	  shoufu();
	  Mycheck2();
	
	//Mycheck2();
			
		})
	</script>
	
	 
	
 </head>
 <body>
<div class="wrapper">


	<!-- 头部 -->
	<jsp:include page="../common/header.jsp"></jsp:include>
	
  <div class="clear"></div>
	<div class="main buy_content">
  	<div class="car_nav">
    	<ul>
      	<li class="cur active"><h2><i>1</i>我的购物车</h2><span></span></li>
        <li><h2><i>2</i>填写核对订单</h2><span></span></li>
        <li><h2><i>3</i>订单提交成功</h2></li>
      </ul>
    </div>
    
   
    <div class="order_list">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="b_cars">
          <thead class="buy_car_head">
              <tr align="center">
                <td>
                	<input type="checkbox" value=""  name=""/>
                	<a href="javascript:checkAll();">全选</a>
                	<a href="javascript:NocheckAll();">反选</a>
                </td>
                <td width="50%">商品名称</td>
                <td>价格</td>
                <td>数量</td>
                <td>小计</td>
                <td width="15%">操作</td>
              
              </tr>
          </thead>
          	<c:if test="${cartList!=null}">
        		<c:forEach items="${cartList}" var="cart" varStatus="vs">
         				<tr class="under_line" id="tr">
		                <td align="center">
		                	<input type="hidden" value="${cart.id}"  class="a"/>
		                	<input name="goodCartCheck" type="checkbox" id="${vs.count}" />
		                </td>
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
		                <td class="jg" align="center">￥<em name="goodsPrice">${cart.cartunitprice}</em></td>
		                <td class="te_c" align="center">
		                	<span>
		                		 
		                		<em class="num" id="xiaosizett44" name="xiaosizett44">${cart.cartsize}
		                	
		                		</em>
		                	</span>
		                </td>
		                <td>
		                	￥<em  class="xiaojiPrice"  id="xiaojiPrice"   name="xiaojiPrice">${cart.carttotalprice}</em>
		                </td>
		               
		                <td align="center"> <a id="deleteButton" href ="javascript:deletecart(${cart.id})" title="删除" >删除</a>
		                <input type="hidden" name="delIds" value="${cart.id}" id="delIds"/></td>
		              </tr>
         		</c:forEach>
          	</c:if>
        </table>
        <div class="jg_Total">
            <span class="fr p_r_30">
            	<em><i id="#">  <input id="cartsee" name="cartsee" readonly="readonly"   style="border-left-style:none;border-right-style:none;width:20px;height:10px"  ></i>件商品&nbsp;&nbsp; 总计：</em> <em class="f_14_c">￥<i class="f_26"  id="total2" name="total2"></i>
            	<input type="text" size="3"  name="total" id="total"  readonly="true"  >
            	</em>
            </span>
            <a href="javascript:checkAll();">全选</a>
            <a href="javascript:NocheckAll();">反选</a>
            <a href="javascript:checkdeleAll();">选择批量删除</a>
            <input type="submit" name="Submit" value="商品总价 " id="Pro_Total" onClick="Mycheck()">
        </div>
        <div class="first_pay fr">
        	<span>首付款：￥<input type="text" size="3"  name="totaltwo" id="totaltwo" readonly="true"> 
        	              ×<input  id="bili"  name="bili"  value="20" type="text" class="f_p_text01"  onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');"/>
        	              %=<input name="shoufu" id="shoufu"  value="" type="text" class="f_p_text02"   onMouseOver="shoufu()"      onKeyUp="this.value=this.value.replace(/[^0-9\.]/g,'');" readonly="true"/>元</span>
            <br />
       	   <a href="<%=basePath %>home/order/check_orders.jsp"> <input class="tj_btn m_t_40" type="submit" value="去结算" />  </a>
        </div>
        
     </div>
  </div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include> 
 
</body>
</html>
 
