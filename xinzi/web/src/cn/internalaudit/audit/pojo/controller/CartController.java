package cn.internalaudit.audit.pojo.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.Cart;
import cn.internalaudit.audit.pojo.CommodityUser;
import cn.internalaudit.audit.pojo.OrderDetail;
import cn.internalaudit.audit.pojo.bo.ICartBo;
import cn.internalaudit.audit.pojo.bo.ICommodityUserBo;
import cn.internalaudit.audit.security.LoginInfo;

/**
 */
@Controller
public class CartController { 
	
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	

	@Resource
	public ICartBo cartBo;
	
	@Resource
	private LoginInfo loginInfo;
	
	@Resource
	private  ICommodityUserBo   commodityUserBo;
	
	/**
	 * 添加购物车
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/home/cart/saveCart.do")
	public String addCart(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			//1、获得页面数据
			Long goodsId=new Long(request.getParameter("goodsId"));
			
			
			//2、根据商品ID查询商品
			List<Cart>  goods=cartBo.findCartById(goodsId);
			Cart cart=null;
			if(goods!=null){
				for (Iterator iterator = goods.iterator(); iterator.hasNext();) {
					cart= (Cart) iterator.next();
					//3、把商品加入到购物车内
					cart.setProName(cart.getProName());
					cart.setBrand(cart.getBrand());
					cart.setDoorstylename(cart.getDoorstylename());
					cart.setColour(cart.getColour());
					cart.setMenheight(cart.getMenheight());
					cart.setSizekd(cart.getSizekd());
					cart.setType(cart.getType());
					cart.setOpento(cart.getOpento());
					cart.setTcount(cart.getTcount());
					cart.setRemark(cart.getRemark());
				}
				//4、把购物车放到session范围中		
				//从session获得购物车
				HttpSession session=request.getSession();
				List<Cart> cartList=(List<Cart>)session.getAttribute("cartList");
				/*
				 * 判断购物车是否为空,如果为空(说明第一次购买),需要新建一个购物车列表。
				   如果不为空，在原来基础上添加一个购物车
				*/
				if(cartList==null){
					cartList=new ArrayList<Cart>();
				}
				
				//判断是否购买了同一种商品，如果是商品数量加一.
				
				boolean isbuy=false;
				for(int i=0;i<cartList.size();i++){
					Cart carts=cartList.get(i);
					long cardGoodsId=carts.getId();
					//说明已经购买了同一个商品 
					if(cardGoodsId==goodsId){
						carts.setNumber(carts.getNumber()+1);
						isbuy=true;
						break;
					}
				}
				
				if(!isbuy){
					cartList.add(cart);		
				}
				session.setAttribute("cartList", cartList);
			}
			
		} catch (Throwable e) {
		 
			e.printStackTrace();
		}
		
		//5、响应
		return "/home/cart/buyer_cart";
	}
	
	/***
	 * 更新购物车
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	//@RequestMapping(value = "/home/cart/updateCart.do")
	//public String updateCart(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response){
		
	//	return null;
	//}
	
	/***
	 * 删除购物车一个商品
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/home/cart/delCartGoods.do")
	public void delCartGoods(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		JSONObject rest=new JSONObject();
		//1、获得页面数据
		//得到商品ID
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		//String[] goodsidArray=request.getParameterValues("cart");
		
		//从session获得购物车
		HttpSession session=request.getSession();
		List<CommodityUser> cartList=(List<CommodityUser>)session.getAttribute("cartList");
		//通过列表remove方法,删除一个购物车对象
		for(int i=0;i<cartList.size();i++){
			CommodityUser cart=cartList.get(i);
			if(goodsId==cart.getId()){
				System.out.println("购物车中删除---"+goodsId+"---"+cart.getId());
				
				cartList.remove(cart);
				System.out.println("---------购物车里 ，执行删除1----------");
				commodityUserBo.delete(cart);
				System.out.println("---------购物车里 ，执行删除2----------");
				break;
			}
		}
		//响应
		if(cartList.size()==0){
			//return  new ModelAndView("/home/cart/buyer_cart");
			rest.put("result", "0");
			try {
				response.getWriter().write(rest.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}else{
			rest.put("result", "1");
			try {
				response.getWriter().write(rest.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			//return new ModelAndView("/home/cart/clear_cart");
	}
	
	/***
	 * 删除选中的商品
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/home/cart/delCartSelectGoods.do")
	public ModelAndView delCartSelectGoods(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//1、获得页面数据
		//得到商品ID
		int goodsId=Integer.parseInt(request.getParameter("goodsId"));
		//String[] goodsidArray=request.getParameterValues("cart");
		
		//从session获得购物车
		HttpSession session=request.getSession();
		List<Cart> cartList=(List<Cart>)session.getAttribute("cartList");
		//通过列表remove方法,删除一个购物车对象
		for(int i=0;i<cartList.size();i++){
			Cart cart=cartList.get(i);
			if(goodsId==cart.getId()){
				cartList.remove(cart);
				break;
			}
		}
		//响应
		if(cartList.size()==0){
			return  new ModelAndView("/home/cart/buyer_cart");
		}else
			return new ModelAndView("/home/cart/clear_cart");
	}
	
	
	


}
