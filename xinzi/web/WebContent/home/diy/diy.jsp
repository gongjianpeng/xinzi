<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
    <head>
		<title>DIY定制模块</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Access-Control-Allow-Origin" content="*">
		<link href="<%=basePath%>home/diy/css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>home/diy/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>home/diy/css/jquery.rollbar.css" />
		<link rel="stylesheet" href="<%=basePath%>home/diy/base/jquery.ui.all.css">
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery-2.0.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery.rollbar.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery.select.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery.ui.core.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery.ui.accordion.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/jquery.adslider.js"></script>
		<!--DIY Moduler -->
		<script type="text/javascript" src="<%=basePath%>home/diy/js/lib/easeljs-0.7.0.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/diy/main.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/diy/cpanel.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/diy/data.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/diy/material.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/diy/ui.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/diy/core.js"></script>
		<script type="text/javascript" src="<%=basePath%>home/diy/js/diy/canvas.js"></script>
	
	<script type="text/javascript">
	function addFavorite(){
	      var title = document.title;
	      var URL = document.URL;
	      try {
	           window.external.addFavorite(URL, title);          //ie
	      } catch(e) {
	           try {
	               window.sidebar.addPanel(title, URL, "");     //firefox
	           } catch(e) {
	                 alert("加入收藏，请使用Ctrl+D进行添加");     //chrome opera safari
	           }
	      }
	}
</script>
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
	</head>
	

  
<body  class="diybg">
	  <input id="loginperson" value="${loginInfo.loginPerson.name}" type="hidden" />
		<div class="details clearfix">
			<div class="d_header">
				<div class="d_logo fl">
					<a href="#"><img src="<%=basePath%>home/resource/images/DIY2_logo.png" /></a>
				</div>
				<div class="d_r fr">
					<em><img src="<%=basePath%>home/resource/images/DIY2_05.png" class="fl" /><a href="<%=basePath%>home/cart/gobuycat.do">购物车</a></em>
					<em class="m_l_30">
						<img src="<%=basePath%>home/resource/images/DIY2_08.png" class="fl" />
						<a onclick="addFavorite();" style="cursor: pointer;">收藏夹</a>
					</em>
				</div>
			</div>
			
		<!-- 详情detail Begin -->
		<c:if test="${proDiyDetail!=null}">
			<c:forEach var="proDiy" items="${proDiyDetail}">
			<div class="d_Main  clearfix">
				<div class="M_top clearfix">
					<div class="Door_detail fl">
						<h3>${proDiy.brand}门业DIY</h3>
					</div>
					<div class="Door_chose fr">
						<div class="deliv fr">
							<input type="hidden" name="deliv" />
							<div class="shtitle fl">
								<em>非标门</em>
								<div class="selectauto">
									<a class="cur" href="javascript:void(0)" value="01">不锈钢门</a>
									<a href="javascript:void(0)" value="02">花式门</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- door_c -->
				<div class="door_c">
					
							<div class="detail_body">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="60">品牌</td>
										<td width="140">
										<input id="brand" name="" type="text"  class="gray_no" value="${proDiy.brand}" onmouseover="this.select()"  />
										</td>
										<td width="60">产品编号</td>
										<td width="140">
										<input id="id" name="" type="text" class="gray_no" value="${proDiy.number}" onmouseover="this.select()"  />
										</td>
										<td width="60">门型</td>
										<td width="140">
										<input id="model" name="" type="text"  class="gray_no" value="${proDiy.type}" onmouseover="this.select()"  />
										</td>
										<td width="40">尺寸</td>
										<td width="200">
										宽：<input id="size_w" name="" type="text" class="gray_yes" value="" onmouseover="this.select()" style="width:55px;"/><i>mm</i> &nbsp;&nbsp;高：
										<input id="size_h" name="" type="text" class="gray_yes" value="" onmouseover="this.select()" style="width:55px;" /><i>mm</i>
										</td>
									</tr>
									<tr>
										<td>樘数</td>
										<td width="140">
										<input id="quantity" name="" type="text" class="gray_yes" value="" onmouseover="this.select()"  />
										</td>
										<td>门体高</td>
										<td width="140">
										<input id="height" name="" type="text" class="gray_yes" value="" onmouseover="this.select()" style="width:135px;" /><i>mm</i>
										</td>
										<td>墙体厚度</td>
										<td width="140">
										<input id="thickness" name="" type="text" class="gray_yes" value="" onmouseover="this.select()" style="width:135px;" /><i>mm</i>
										</td>
										<td>&nbsp;</td>
										<td>
										<input id="submitInfo" name="" type="button" value="确 定" class="qd_btn popClick fr"/>
										</td>
									</tr>
								</table>
							</div>
					
					
					<div class="pack_up">
						<em>收起</em>
					</div>
				</div>
				<div class="door_content">
					<div id="cpanel" class="c_right clearfix fr">
						<div id="accordion">

						</div>
						<div class="price_box m_t_10">
							<span>
								<input id="calcBtn" name="" type="button" value="计算价格" class="price_btn"/>
							</span>
							<div id="calculator" class="js_price" style="display: none;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="price">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>数量</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><em id="price1"></em></td>
										<td>×</td>
										<td>
							          <i id="pnum"></i>
										</td>
										<td>=</td>
										<td><em  id="price2"></em></td>
									</tr>
								</table>
							</div>
						</div>
						<span class="Bc_btns"><i class="bc_btn"><a id="orderBtn" href="<%=basePath%>pages/company/getdiyProductOrder.do">立即下单</a></i>
							<i class="bc_btn" style="margin-right:-8px;">
								<a id="addcartBtn"  href="<%=basePath%>pages/company/getdiyProductaddCat.do?operation=add">加入购物车</a>
							</i>
						</span>
					</div>
					<div class="c_left fl">
						<div id="category" class="c_left_body">
							<div class="door">
								<em><a id="fullscreenBtn" class="door_btn" href="javascript:void(0)">全屏显示</a> &nbsp;
								<a id="bgBtn" class="door_btn" href="javascript:void(0)">切换背景</a> </em>
                                <div class="door_bg">
									<div class="door_imgs">
										<canvas id="demoCanvas" width="550" height="500" >
												<img src="<%=basePath%>${proDiy.photo}" />
										</canvas>
									</div>
								</div>
							</div>
							<div class="clear"></div>
							<div id="material">
								<div class="jqzosub clearfix">
									<div id="View_list">
										<ul class="manuList" id="imgFrame">
											 
										</ul>
									</div>
									<span id="prev_btn"><a href="javascript:void(0)"></a></span>
									<span id="next_btn"><a href="javascript:void(0)"></a></span>
								</div>
								<div class="view">
									<h3>请选择一款样式</h3>
									<div id="example">
										<div class="imga">
											<ul>
												 
											</ul>
										</div>
										
									</div>
								</div>
                                <div style="position:absolute; top:200px; height:20px;overflow:hidden; color:#fff;">none</div>
							</div>

						</div>
					</div>

				</div>
			</div>
		</c:forEach>	
	<!-- 详情detail End -->		
	</c:if>

 </div>
		
		<!--弹出框

		<div class="dialogs"></div>
		<div class="dialog-box">
			<em>加载中...<em>
		</div>
-->
	</body>
</html>
