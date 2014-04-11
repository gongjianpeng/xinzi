<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/formvalidator.js"></script>
<script type="text/javascript" src="js/formvalidatorregex.js"></script>
<script type="text/javascript" src="js/jay.js"></script>
<script language="javascript" src="is/DateTimeMask.js" type="text/javascript"></script>
  </head>
  
  <body>
    <div class="wrapper">
	<div class="header">
  	<iframe id="top" name="top" frameborder="0" width="100%;" height="110px;" src="top.jsp"></iframe>
  </div>
  <div class="clear"></div>
	<div class="main buy_content">
  	<div class="car_nav">
    	<ul>
      	<li class="cur"><h2><i>1</i>我的购物车</h2><span></span></li>
        <li class="cur active"><h2><i>2</i>填写核对订单</h2><span></span></li>
        <li><h2><i>3</i>订单提交成功</h2></li>
      </ul>
    </div>
    <div class="form_con">
    	<h2 class="title">填写核对订单信息</h2>
      <form action="" name="" id="center_form">
      <ul>
      	<li><span>客户姓名：</span><input type="text" class="text" id="user_name" index="0" /></li>
        <li><span>联系电话：</span><input type="text" class="text" value="手机号码" id="phone" index="1" /><i>或</i><input type="text" class="text" value="固定电话" id="tel" index="2" /></li>
        <li><span>接单时间：</span><input type="text" class="text" id="csny" index="3" /></li>
        <li><span>交货时间：</span><input type="text" class="text" id="jh_time" index="4" /></li>
        <li><span>收货地址：</span><input type="text" class="text text_2" id="address" index="5" /></li>
        <li><span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span><textarea class="text text_con" index="6">送货前确认是否在家</textarea></li>
      </ul>
      <input type="submit" value="保存收货人信息" class="button_1" /><span class="add_linkers">添加联系人名单</span>
      </form>
    </div>
    <div class="order_list">
      <div class="title_1">发货清单</div>
      <table width="100%" cellspacing="0" cellpadding="0" class="b_cars">
        <thead class="buy_car_head">
            <tr align="center">
            	<td width="1%">&nbsp;</td>
              <td width="45%" align="left">商品名称</td>
              <td>价格</td>
              <td>数量</td>
            </tr>
        </thead>
            <tr>
            	<td width="4%">&nbsp;</td>
              <td>
              <div class="goods">
                  <img src="images/small_img.png" class="fl m_r_14" />
                  <span>
                  <em class="f_14">订单编号：5145245124552</em>
                  <em><i>品牌：年红</i><i>类型：双开门</i></em>
                  <em><i>尺寸：宽1400mm  高2500mm</i></em>
                  <em><i>款式名称：月满西楼</i><i>开向：外右</i><i>颜色：仿真五号</i></em>
                  <em><i>备注：</i></em>
                  </span>
              </div>
              </td>
              <td class="jg" align="center">￥<em>2290.00</em></td>
              <td class="te_c" align="center"><span>1</span></td>
            </tr>
            <tr>
            	<td width="1%">&nbsp;</td>
              <td>
              <div class="goods">
                  <img src="images/small_img.png" class="fl m_r_14" />
                  <span>
                  <em class="f_14">订单编号：5145245124552</em>
                  <em><i>品牌：年红</i><i>类型：双开门</i></em>
                  <em><i>尺寸：宽1400mm  高2500mm</i></em>
                  <em><i>款式名称：月满西楼</i><i>开向：外右</i><i>颜色：仿真五号</i></em>
                  <em><i>备注：</i></em>
                  </span>
              </div>
              </td>
              <td class="jg" align="center">￥<em>2290.00</em></td>
              <td class="te_c" align="center"><span>1</span></td>
            </tr>
            <tr>
            	<td width="4%">&nbsp;</td>
              <td>
              <div class="goods">
                  <img src="images/small_img.png" class="fl m_r_14" />
                  <span>
                  <em class="f_14">订单编号：5145245124552</em>
                  <em><i>品牌：年红</i><i>类型：双开门</i></em>
                  <em><i>尺寸：宽1400mm  高2500mm</i></em>
                  <em><i>款式名称：月满西楼</i><i>开向：外右</i><i>颜色：仿真五号</i></em>
                  <em><i>备注：</i></em>
                  </span>
              </div>
              </td>
              <td class="jg" align="center">￥<em>2290.00</em></td>
              <td class="te_c" align="center"><span>1</span></td>
            </tr>
            <tr>
            	<td width="1%">&nbsp;</td>
              <td>
              <div class="goods">
                  <img src="images/small_img.png" class="fl m_r_14" />
                  <span>
                  <em class="f_14">订单编号：5145245124552</em>
                  <em><i>品牌：年红</i><i>类型：双开门</i></em>
                  <em><i>尺寸：宽1400mm  高2500mm</i></em>
                  <em><i>款式名称：月满西楼</i><i>开向：外右</i><i>颜色：仿真五号</i></em>
                  <em><i>备注：</i></em>
                  </span>
              </div>
              </td>
              <td class="jg" align="center">￥<em>2290.00</em></td>
              <td class="te_c" align="center"><span>1</span></td>
            </tr>
      </table>
      <div class="order_accounts">
      	<ul>
         <li>4件商品，总商品金额：<em>4580.00</em></li>
          <li>首付款：<em>4580.00</em></li>
          <li><input class="tj_btn" type="button" value="提交订单" name=""></input></li>
          <a href="ordervali.jsp" class="tj_btn m_t_40" target="_self">提交链接</a>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="footer">
  <div class="footer_top">
  	<a href="#"><img src="images/public/footer_1.png" width="126" height="60" alt="" /></a>
    <a href="#"><img src="images/public/footer_2.png" width="125" height="60" alt="" /></a>
    <a href="#"><img src="images/public/footer_3.png" width="128" height="60" alt="" /></a>
    <a href="#"><img src="images/public/footer_4.png" width="100" height="60" alt="" /></a>
  </div>
  <div class="footer_nav">
  	<a href="#">典尚</a>|<a href="#">公司介绍</a>|<a href="#">联系我们</a>|<a href="#">友情链接</a>|<a href="#">网站地图</a>
  </div>
</div>
<script type="text/javascript">
<!--
$(function(){
	$.formValidator.initConfig({autotip:true,formid:"center_form",onerror:function(msg){}});
	$("#user_name").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).inputValidator({min:2,max:20,onerror:"用户名应该为2-20位之间"});
	$("#phone").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机或者电话号码格式错误"});
	$("#tel").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"tel",datatype:"enum",onerror:"手机或者电话号码格式错误"});
	$("#jd_time").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"date",datatype:"enum",onerror:"请输入正确的日期"});
	$("#jh_time").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({regexp:"date",datatype:"enum",onerror:"请输入正确的日期"});
	$("#address").formValidator({onshow:"&nbsp;",oncorrect:"&nbsp;"}).regexValidator({min:8,max:60,onerror:"请输入正确的地址"});
})
-->
</script>
  </body>
</html>
