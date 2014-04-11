<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<title>典尚DIY定制模块</title>
     <jsp:include page="../common/include.jsp"></jsp:include>
 </head>
 <body>
		<div class="details clearfix">
			<div class="d_header">
				<div class="d_logo fl">
					<a href="#"><img src="<%=basePath%>home/resource/images/DIY2_logo.png" /></a>
				</div>
				<div class="d_r fr">
					<em><img src="<%=basePath%>home/resource/images/DIY2_05.png" class="fl" /><a href="#">购物车</a></em>
					<em class="m_l_30"><img src="<%=basePath%>home/resource/images/DIY2_08.png" class="fl" /><a href="#">收藏夹</a></em>
				</div>
			</div>
			<div class="d_Main  clearfix">
				<div class="M_top clearfix">
					<div class="Door_detail fl">
						<h3>XXX门业DIY</h3>
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
					<!-- 详情detail Begin -->
					
					<c:if test="${proDiyDetail!=null}">
						
						<c:forEach var="proDiy" items="${proDiyDetail}">
							<div class="detail_body">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="60">品牌</td>
										<td width="140">
										<input id="brand" name="" type="text" disabled="disabled" class="gray_no" value="${proDiy.brand}" onmouseover="this.select()"  />
										</td>
										<td width="60">产品编号</td>
										<td width="140">
										<input id="id" name="" type="text" disabled="disabled" class="gray_no" value="${proDiy.number}" onmouseover="this.select()"  />
										</td>
										<td width="60">门型</td>
										<td width="140">
										<input id="model" name="" type="text" disabled="disabled" class="gray_no" value="${proDiy.type}" onmouseover="this.select()"  />
										</td>
										<td width="40">尺寸</td>
										<td width="200">
										长：<input id="size_w" name="" type="text" class="gray_yes" value="2350" onmouseover="this.select()" style="width:55px;"/><i>mm</i> &nbsp;&nbsp;宽：
										<input id="size_h" name="" type="text" class="gray_yes" value="1000" onmouseover="this.select()" style="width:55px;" /><i>mm</i>
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
						</c:forEach>	
						
					</c:if>
					
					<!-- 详情detail End -->
					
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
										<td><em>￥347.00</em></td>
										<td>×</td>
										<td>
										<i>2</i>
										</td>
										<td>=</td>
										<td><em class="f_24">￥1025.00</em></td>
									</tr>
								</table>
							</div>
						</div>
						<span class="Bc_btns"><i class="bc_btn"><a id="orderBtn" href="###">立即下单</a></i>
							<i class="bc_btn" style="margin-right:-8px;">
								<a id="addcartBtn" href="location.href='<%=basePath%>home/cart/saveCart.do?goodsId=38">加入购物车</a>
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
											<c:if test="${proDiyDetail!=null}">
												<c:forEach var="proDiy" items="${proDiyDetail}">
													<img src="<%=basePath%>${proDiy.photo}" />
												</c:forEach>
											</c:if>
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

		</div>
		<!--弹出框-->

		<div class="dialogs"></div>
		<div class="dialog-box">
			<em>加载中...<em>
		</div>

	</body>
</html>
