package cn.internalaudit.audit.pojo.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.bo.IBrandBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.CommodityUser;
import cn.internalaudit.audit.pojo.Order;
import cn.internalaudit.audit.pojo.OrderDetail;
import cn.internalaudit.audit.pojo.bo.ICommodityUserBo;
import cn.internalaudit.audit.pojo.bo.IOrderBo;
import cn.internalaudit.audit.pojo.bo.IOrderDetailBo;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.DateUtils;
import cn.internalaudit.audit.utils.Utils;

/**
 */
@Controller
public class OrderController { 
	
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String UPDATE2= "update2";
	public static final String UPDATE3 = "update3";
	public static final String UPDATE4= "update4";
	public static final String UPDATE5 = "update5";
	public static final String UPDATE6 = "update6";
	public static final String UPDATE9 = "update9";
	public static final String DELETE = "delete";
	public static final String JXSUPDATE3 = "jxsupdate3";
	public static final String JXSUPDATE4 = "jxsupdate4";
	public static final String JXSUPDATE5 = "jxsupdate5";
	public static final String JXSUPDATE6 = "jxsupdate6";
	public static final String JXSUPDATE8 = "jxsupdate8";
	public static final String JXSDELETE = "jxsdelete";
	
	public static final String ORDERK = "orderk";

	@Resource
	public IOrderBo orderBo;
	@Resource
	public IOrderDetailBo orderDetailBo;
	@Resource
	private LoginInfo loginInfo;
	
	@Resource
	private  ICommodityUserBo   commodityUserBo;
	
	   @Resource(name = "BrandBo")
	    public IBrandBo brandBo;
	    
	
	/**
	 * 保存收货订单信息
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/home/order/saveReceiptInfo.do")
	public void saveReceiptInfo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		 JSONObject ret=new JSONObject();
		 try {
			  String buyerId=request.getParameter("buyerId");
			  String orderId=request.getParameter("orderId");
			  String userName=request.getParameter("userName");
			  String telephone=request.getParameter("telephone");
			  String mobile=request.getParameter("mobile");
			  String address=request.getParameter("address");
			  String zip=request.getParameter("zip");
			  String company=request.getParameter("company");
			  String deliveryTime=request.getParameter("deliveryTime");
			  String ordertime=request.getParameter("ordertime");//接单时间
			  String remark=request.getParameter("remark");
			 
			  
			  Person loginPerson = loginInfo.getLoginPerson();
				
			  Order entity=new Order();
			  entity.setCreatePerson(loginPerson.getName());// 客户姓名
			  entity.setCreatePersonId(loginPerson.getId());
			  entity.setUserName(userName);
			  entity.setZip(zip);
			  entity.setMobile(mobile);//联系电话
			  entity.setTelephone(telephone);//手机
			  entity.setOrdertime(ordertime);//接单时间
			  entity.setDeliverytime(deliveryTime);//  交货时间
			  entity.setAddress(address);// 收货地址
			  entity.setCompany(company);//公司
			  entity.setRemark(remark);//备注
			  
			  request.getSession().setAttribute("order", entity);
			  
			  orderBo.save(entity);
			  
			  ret.put("result", "success");
			  
			  response.getWriter().write(ret.toString());
		} catch (Exception e) {
			 ret.put("result", "failre");
			 try {
				response.getWriter().write(ret.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 生产订单
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/home/order/submitOrder.do")
	public void submitOrder(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
			JSONObject rest=new JSONObject();
			String userName=request.getParameter("userName");
			String shoumobile=request.getParameter("mobile");
			String shouzip=request.getParameter("zip");
			String shoutelephone=request.getParameter("telephone");
			String shouordertime=request.getParameter("ordertime");
			String shoudeliveryTime=request.getParameter("deliveryTime");
			String shouaddress=request.getParameter("address");
			String shoucompany=request.getParameter("company");
			String shouremark=request.getParameter("remark");
			
			
			
		try {
			 //OrderDetail od=(OrderDetail)request.getSession().getAttribute("order");//订单详情信息
			String orderNo="NO" + DateUtils.textForNow("yyyyMMddHHmmssS", new Date());
			 //1.从购物车的cartlist获取数据出来
			 List<CommodityUser> cartList=(List<CommodityUser>)request.getSession().getAttribute("cartList");//货物信息
			//2变量这个list保存到详情表(orderDetail)
			for (Iterator iterator = cartList.iterator(); iterator.hasNext();) {
				CommodityUser c = (CommodityUser) iterator.next();
                 // 点击  去结算 后，改变购物车中的数据状态
				c.setStatus(ORDERK);
				commodityUserBo.update(c);
				/*************gjp0327**********/
				
				OrderDetail odd = new OrderDetail();
				Person loginPerson = loginInfo.getLoginPerson();
                String org=loginPerson.getOrganizationId();
				Date time = new Date();
				int createtime = (int) time.getTime();

				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd   HH:mm:ss ");
				System.out.println(df.format(new Date()));
				String time2 = df.format(new Date());
				Date newDate = null;
				try {
					newDate = df.parse(time2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/****获取这个品牌 是那个 厂商的*/
				String brandname=c.getBrand();
				
				if(brandname!=null||!"".equals(brandname)){
					//查询 这个品牌 
					Map map = new HashMap();
					map.put("name", "'"+brandname+"'");
		     	List<Brand>	brand=brandBo.findByParmsgetDao(map);
		     	String  brandorg=brand.get(0).getOrg();
		     	odd.setCode(brandorg);
		     	
				}
//				
				System.out.println(""+brandname);
				
				odd.setCreateDate(newDate);
                odd.setOrg(org);
				odd.setCreatePerson(loginPerson.getName());
				odd.setUnitPrice(c.getCartunitprice());
				odd.setTotalprice(c.getCarttotalprice());
				odd.setType(c.getType());
				odd.setOpento(c.getOpento());
				odd.setDoorstylename(c.getDoorstylename());
				odd.setRemark(c.getRemark());
				odd.setMenheight(c.getMenheight());
				odd.setBrand(c.getBrand());
				odd.setSizekd(c.getSizekd());
				odd.setGoodsId(c.getId() + "");
				odd.setCreatePersonId(loginPerson.getId());
				odd.setProName(c.getProName());
				odd.setOrderNo(orderNo);// 生成订单号
				odd.setNumber(c.getNumber());
				odd.setOrderState("等待处理订单");

				odd.setAccessoriesname(c.getAccessoriesname());

				odd.setCreateDate(c.getCreateDate());
				odd.setCreatePerson(c.getCreatePerson());

				odd.setAccessoriescode(c.getAccessoriescode());
				odd.setAccessoriescontents(c.getAccessoriescontents());
				odd.setAccessoriestype(c.getAccessoriestype());

				odd.setDoorstylecode(c.getDoorstylecode());
				odd.setDoorstylesname(c.getDoorstylesname());
				odd.setDoorstyletype(c.getDoorstyletype());
				odd.setDoorstylefield(c.getDoorstylefield());
				odd.setDoorstylefield2(c.getDoorstylefield2());

				odd.setElsescontents(c.getElsescontents()); // 其他备注
				odd.setElsescode(c.getElsescode()); // 
				odd.setElsesfield(c.getElsesfield()); //
				odd.setElsesfield2(c.getElsesfield2());
				odd.setElsesname(c.getElsesname());
				odd.setElsesorg(c.getElsescode());
				odd.setElsesremark(c.getElsesremark());
				odd.setElsessname(c.getElsessname());
				odd.setElsestype(c.getElsestype());

				odd.setFramecode(c.getFramecode());
				odd.setFramecontents(c.getFramecontents());
				odd.setFramefield(c.getFramefield());
				odd.setFramefield2(c.getFramefield2());
				odd.setFrameremark(c.getFrameremark());
				odd.setFramesname(c.getFramesname());
				odd.setFramename(c.getFramename());
				odd.setFrametype(c.getFrametype());

				odd.setPalettecode(c.getPalettecode());
				odd.setPalettecontents(c.getPalettecontents());
				odd.setPalettefield(c.getPalettefield());
				odd.setPalettefield2(c.getPalettefield2());
				odd.setPalettename(c.getPalettename());
				odd.setPaletteremark(c.getPaletteremark());
				odd.setPalettesname(c.getPalettesname());
				odd.setPalettetype(c.getPalettetype());

				odd.setPancode(c.getPancode()); // 色板
				odd.setPancontents(c.getPancontents());
				odd.setPanelname(c.getPanelname());
				odd.setPanfield(c.getPanfield());
				odd.setPanfield2(c.getPanfield2());
				odd.setPanremark(c.getPanremark());
				odd.setPansname(c.getPanelname());
				odd.setPantype(c.getPantype());

				odd.setScuttlecode(c.getScuttlecode()); // 气窗
				odd.setScuttlecontents(c.getScuttlecontents());
				odd.setScuttlefield(c.getScuttlefield());
				odd.setScuttlefield2(c.getScuttlefield2());
				odd.setAccessoriesname(c.getScuttlename());
				odd.setScuttleremark(c.getScuttleremark());
				odd.setScuttlesname(c.getScuttlesname());
				odd.setScuttletype(c.getScuttletype());

				odd.setSuojuName(c.getSuojuName());
				odd.setSuoxName(c.getSuoxName());
				odd.setMenlingName(c.getMenlingName());
				odd.setMaoyanName(c.getMaoyanName());
				odd.setXiadangName(c.getXiadangName());
				odd.setToujiaName(c.getToujiaName());
				odd.setLashouName(c.getLashouName());
				//收获人信息
				odd.setOrdershouName(userName);
				odd.setShouzip(shouzip);
				odd.setShoutelephone(shoutelephone);
				odd.setShouremark(shouremark);
				odd.setShouordertime(shouordertime);
				odd.setShoumobile(shoumobile);
				odd.setShoudeliveryTime(shoudeliveryTime);
				odd.setShoucompany(shoucompany);
				odd.setShouaddress(shouaddress);
				

				orderDetailBo.add(odd);

			}  
			 request.setAttribute("OrderNO", orderNo);
			 rest.put("OrderNO", orderNo);
			 rest.put("result", "success");
			 response.getWriter().write(rest.toString());
		} catch (IOException e) {
			 rest.put("result", "failre");
			 try {
				response.getWriter().write(rest.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "/pages/company/getDataOrderDetails.do")
	public String getDataOrderDetails(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		//获取登录ID
    	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();

		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<OrderDetail>();
		int end = start + limit;
		List<OrderDetail> entitys = new ArrayList<OrderDetail>();
		
		 Map map = new HashMap();
	      
	        if (!Utils.nullOrEmpty(orgid)){
				map.put("org", orgid);
	        }
	       
		entitys = orderDetailBo.findByParms(map,orgid);
		int total = entitys.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(entitys.subList(start, end));
		}
		datamodel.setTotal(total);
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();

		return null;
	}

	
	/**
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/home/order/getOrderNo.do")
	public String getOrderNo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		String OrderNo=request.getParameter("OrderNo");
		request.setAttribute("OrderNo", OrderNo);
		return "/home/order/order_success";
	}
	 
	
	@RequestMapping(value = "/pages/company/orderOperateqiye.do")
	public String ordereOperateqiye(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			OrderDetail entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		 Person loginPerson = loginInfo.getLoginPerson();
		 String cdname=loginPerson.getName();
		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		
		String id=request.getParameter("id");
		if (ADD.equals(operation)) {
			orderDetailBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
	
			entity.setOrderState("订单受理中");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			
			writer.write("true");
		} 
		else if (UPDATE2.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("厂商取消订单");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}else if (UPDATE3.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("生产中");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}else if (UPDATE4.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("已发货");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}else if (UPDATE5.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("交易成功");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}
		else if (UPDATE9.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("等待支付");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}
		
		else if (DELETE.equals(operation)) {
			entity.setLastEditPerson(cdname);
			orderDetailBo.delete(entity);
			writer.write("true");
		}
		
		
		
		else if (JXSUPDATE3.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("经销商取消订单");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}else if (JXSUPDATE4.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("经销商已支付");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}else if (JXSUPDATE5.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("确认收货");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}else if (JXSUPDATE6.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("交易成功");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}else if (JXSUPDATE8.equals(operation)) {
			entity=orderDetailBo.find(Long.valueOf(id));
			entity.setOrderState("取消订单");
			entity.setLastEditPerson(cdname);
			orderDetailBo.update(entity);
			writer.write("true");
		}
		
		else if (JXSDELETE.equals(operation)) {
			entity.setLastEditPerson(cdname);
			orderDetailBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}





@RequestMapping(value = "/pages/company/orderOperatejxs.do")
public String ordereOperatejxs(Locale locale, Model model,
		HttpServletRequest request, HttpServletResponse response,
		OrderDetail entity) throws Exception {
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	 Person loginPerson = loginInfo.getLoginPerson();
	 String cdname=loginPerson.getName();
	String operation = request.getParameter("operation");
	PrintWriter writer = response.getWriter();
	
	String id=request.getParameter("id");
	if (ADD.equals(operation)) {
		orderDetailBo.save(entity);
		writer.write("true");
	} else if (UPDATE.equals(operation)) {
		entity=orderDetailBo.find(Long.valueOf(id));
		entity.setOrderState("订单受理中");
		entity.setLastEditPerson(cdname);
		orderDetailBo.update(entity);
		
		writer.write("true");
	} 
	 
	else if (JXSUPDATE3.equals(operation)) {
		entity=orderDetailBo.find(Long.valueOf(id));
		entity.setOrderState("经销商取消订单");
		entity.setLastEditPerson(cdname);
		orderDetailBo.update(entity);
		writer.write("true");
	}else if (JXSUPDATE4.equals(operation)) {
		entity=orderDetailBo.find(Long.valueOf(id));
		entity.setOrderState("经销商已支付");
		entity.setLastEditPerson(cdname);
		orderDetailBo.update(entity);
		writer.write("true");
	}else if (JXSUPDATE5.equals(operation)) {
		entity=orderDetailBo.find(Long.valueOf(id));
		entity.setOrderState("确认收货");
		entity.setLastEditPerson(cdname);
		orderDetailBo.update(entity);
		writer.write("true");
	}else if (JXSUPDATE6.equals(operation)) {
		entity=orderDetailBo.find(Long.valueOf(id));
		entity.setOrderState("交易成功");
		entity.setLastEditPerson(cdname);
		orderDetailBo.update(entity);
		writer.write("true");
	}else if (JXSUPDATE8.equals(operation)) {
		entity=orderDetailBo.find(Long.valueOf(id));
		entity.setOrderState("取消订单");
		entity.setLastEditPerson(cdname);
		orderDetailBo.update(entity);
		writer.write("true");
	}
	
	else if (JXSDELETE.equals(operation)) {
		entity.setLastEditPerson(cdname);
		orderDetailBo.delete(entity);
		writer.write("true");
	}
	writer.flush();
	return null;
}


@RequestMapping(value = "/pages/company/getDataOrderDetailsQuery.do")
public String getDataOrderDetailsQuery(Locale locale, Model model,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	Person loginPerson = loginInfo.getLoginPerson();
	String orgid = loginPerson.getOrganizationId();


	String startStr = request.getParameter("start");
	String limitStr = request.getParameter("limit");
	int start = Integer.parseInt(startStr);
	int limit = Integer.parseInt(limitStr);
	if (limit == 0) {
		limit = Integer.MAX_VALUE;
	}
	PrintWriter writer = response.getWriter();
	DataModel datamodel = new DataModel<OrderDetail>();
	//接收前台穿过来的数据
	String qcode=request.getParameter("qcode");
	String qName=request.getParameter("qName");
	String qstarttime=request.getParameter("qstarttime");
	String qendtime=request.getParameter("qendtime");
	String qcolour=request.getParameter("qcolour");
	String qtype=request.getParameter("qtype");
	
	  
  System.out.println(""+qcode+qName+qstarttime+qendtime+qcolour+qtype);
	
	
	// 放到map里面
	Map map = new HashMap();
	if (!Utils.nullOrEmpty(qName)) {
		String qName2 = new String(qName.getBytes("iso-8859-1"), "UTF-8");
		map.put("name","'"+qName2+"'");
	}
	if (!Utils.nullOrEmpty(qcode))
		map.put("orderNo","'"+qcode+"'");
	
	if (!Utils.nullOrEmpty(orgid)){
		map.put("org", orgid);
	}
//	if (!Utils.nullOrEmpty(qcolour)) {
//		String qcolour2 = new String(qcolour.getBytes("iso-8859-1"), "UTF-8");
//		map.put("colour","'"+qcolour2+"'");
//	}
//	if (!Utils.nullOrEmpty(qtype)) {
//		String qtype2 = new String(qtype.getBytes("iso-8859-1"), "UTF-8");
//		map.put("type","'"+qtype2+"'");
//	}
//	if (!Utils.nullOrEmpty(qstarttime)){
//		map.put("ordertime", qstarttime);
//	}
//	if (!Utils.nullOrEmpty(qstarttime)){
//		map.put("deliverytime", qstarttime);
//	}
	
	int end = start + limit;
	List<OrderDetail> entitys = new ArrayList<OrderDetail>();
	entitys = orderDetailBo.findByParmsandorg(map, orgid);
	int total = entitys.size();
	end = end > total ? total : end;
	if (start <= end) {
		datamodel.setRows(entitys.subList(start, end));
	}
	datamodel.setTotal(total);
	writer.write(JSONObject.fromObject(datamodel).toString());
	writer.flush();

	return null;
}

}
