package cn.internalaudit.audit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.bo.IParameterqiyeBo;
import cn.internalaudit.audit.entitys.BaseEntity;
import cn.internalaudit.audit.entitys.Parameterqiye;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.Commodity;
import cn.internalaudit.audit.pojo.bo.ICommodityBo;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

@Controller
public class ParameterqiyeController
{
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    @Resource(name = "ParameterqiyeBo")
    public IParameterqiyeBo parameterqiyeBo;
    
    @Resource(name = "CommodityBo")
	public ICommodityBo commodityBo;
    
    @Resource
	private LoginInfo loginInfo;
    /**
     * 
     * 显示基本参数信息
     * 无条件查询
     * @param locale
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/pages/company/getParameterqiyes.do")
    public String getParameterqiyes(Locale locale, Model model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        String jibenName = request.getParameter("jibenName");
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
        DataModel datamodel = new DataModel<Parameterqiye>();
        int end = start + limit;
        Map map = new HashMap();
        if (!Utils.nullOrEmpty(jibenName)){
            String userNameap=new String(jibenName.getBytes( "iso-8859-1"),"UTF-8" );
            map.put("name", "%"+userNameap+"%");
        }
        List<Parameterqiye> list = null;
        if (!Utils.nullOrEmpty(orgid))
			map.put("org", orgid);
		
		if (map.size()>0) {
			list = parameterqiyeBo.findByParm(map);
			
		} else if(map.size()>1) {
			list = parameterqiyeBo.findByParm(map);
		}
        int total = list.size();
        end = end > total ? total : end;
        if (start <= end) {
            datamodel.setRows(list.subList(start, end));
        }
        datamodel.setTotal(total);
        writer.write(JSONObject.fromObject(datamodel).toString());
        writer.flush();

        return null;
    }
    
    
	@RequestMapping(value = "/pages/company/proclassComboData.do")
	public String getComboData(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
		List list = null;
		System.out.println("type wei "+type);
		Map map = new HashMap();
		map.put("type", type);
		list = parameterqiyeBo.findByParm(map);
		PrintWriter writer = response.getWriter();
		if (null != list && list.size() > 0) {
			DataModel dataModel = new DataModel<BaseEntity>();
			writer.write(dataModel.setRows(list).toString());
		} else {
			writer.write("[{'text':'','value':''}]");
		}
		writer.flush();
		return null;
	}
	
	
	/**
	 *  第一部查询  所有的系列
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value = "/productClassData.do")
	public void getProductClassData(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse response, Commodity entity) {
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			JSONArray result = new JSONArray();
			JSONObject ret = new JSONObject();

			String type = "proclass";
			Map map = new HashMap();
			map.put("type", type);
			List<Parameterqiye> list = parameterqiyeBo.findByParm(map);
			list=(list==null)?new ArrayList<Parameterqiye>():list;
			
			Long id = null;
			String name = null;
			for (int k = 0; k < list.size(); k++) {
				id = list.get(k).getId();
				name = list.get(k).getName();
				name=(name==null)?"":name;
				result.add(buildSe(id, name));
			}

			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			ret.put("size", list.size());
			ret.put("data", result.toString());
			writer.write(ret.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/productDataRight.do")
	public void getProductDataRight(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse response, Commodity entity) {
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			JSONArray result = new JSONArray();
			JSONObject ret = new JSONObject();

			 
			List<Commodity> list =commodityBo.findProLimitAll();
			list=(list==null)?new ArrayList<Commodity>():list;
			
			Long id = null;
			String name = null;
			String photo="";
			for (int k = 0; k < list.size(); k++) {
				id = list.get(k).getId();
				name = list.get(k).getName();
				name=(name==null)?"":name;
				photo=list.get(k).getPhoto();
				photo=(photo==null)?"":photo;
				JSONObject data = new JSONObject();
				
				data.put("id", id);
				data.put("name", name);
				data.put("photo", photo);
				
				result.add(data);
			}

			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			ret.put("size", list.size());
			ret.put("data", result.toString());
			writer.write(ret.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/home/product/getProductByType.do")
	public String getProductByType(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, Commodity entity,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			 String productName=request.getParameter("productName"); 
			if(!productName.equals("")||productName!=null){
			 String productName2=new String(productName.getBytes( "iso-8859-1"),"UTF-8" );
			if(!productName2.equals("")){
				Map map=new HashMap();
				Object pinname=session.getAttribute("brandName");
				System.out.println("======网上商城type中=="+pinname);
				 String pinpai=(String) pinname;
			    	if(!Utils.nullOrEmpty(pinpai)){
				 map.put("brand", pinpai);
			    	}
			    	
				map.put("proclass", productName2);
				List<Commodity> lists=commodityBo.findByParms(map);//.findCommodityByproClass(productName2,brand);
				model.addAttribute("prodList", lists);
			}
			else{
				return "/home/product/product_info";
			}
			}
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		return "/home/product/product_info";
	}
	
	
	/**
	 * 产品搜索与分页
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/home/product/searchProduct.do")
    public String getProductSearchList(Locale locale, Model model,  HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws Exception {
        codSet(response);
		Map params = new HashMap();
		
		String condition = request.getParameter("searchCondition");
		String brandName2=request.getParameter("brandName");
		String brandName=null;
		 if (!Utils.nullOrEmpty(brandName2)){
			 brandName=new String(brandName2.getBytes( "iso-8859-1"),"UTF-8");
       
        }
		String name22=null;
		 if (!Utils.nullOrEmpty(condition)){
			 name22=new String(condition.getBytes( "iso-8859-1"),"UTF-8");
       
        }
		int page = Integer.valueOf(request.getParameter("page")==null?"1":request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize")==null?"12":request.getParameter("pageSize"));
		int start = (page <= 0 ? 0 : page - 1) * pageSize;

		int total = 0;
		
		List<Commodity> lists=null;
		if (condition == null || condition.equals("")) {
			Object pinname=session.getAttribute("brandName");
			System.out.println("======网上商城中品牌的名称为：=="+pinname);
			 String pinpai=(String) pinname;
		    	if(!Utils.nullOrEmpty(pinpai)){
			 params.put("brand", pinpai);
		    	}
			params.put("condition","");
			//params.put("", brandName);
            params.put("start", start);
			params.put("pageSize", pageSize);
			lists = commodityBo.findProductListAll(params);
			total=lists.size();
		}else{
			if (!Utils.nullOrEmpty(condition)){
				 name22=new String(condition.getBytes( "iso-8859-1"),"UTF-8");
	          System.out.println(" 查询条件222"+name22);
	        }
			String proname=new String(condition.getBytes( "iso-8859-1"),"UTF-8" );
			Object pinname=session.getAttribute("brandName");
			System.out.println("======else=网上商城中品牌的名称为=="+pinname);
			 String pinpai=(String) pinname;
		    	if(!Utils.nullOrEmpty(pinpai)){
			 params.put("brand", pinpai);
		    	}
            params.put("condition", name22);
            params.put("start", start);
			params.put("pageSize", pageSize);
			lists = commodityBo.findProductListAll(params);
			total=lists.size();
		}
	    
        request.setAttribute("keyword", condition);
        request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("pageSize", pageSize);
		
    	model.addAttribute("searchproductList", lists);
		 
		return "/home/product/product_list";
    }
	

	/**
	 * 
	 *@param proName
	 *@param proId
	 *@param brand
	 * 根据列表中的数据，点击 图片，或文字，查询到
	 */
	@RequestMapping(value = "/home/diy/getProductDetail.do")
	public String getProductDetail(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, Commodity entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	
		String proId=request.getParameter("proId"); 
		 String brand2 = new String(request.getParameter("brand").getBytes("iso-8859-1"),"utf-8");  //转换格式          
		 String proName2 = new String(request.getParameter("proName").getBytes("iso-8859-1"),"utf-8");  //转换格式          
		
		if(proId!=null){
			List<Commodity> lists=commodityBo.findCommodityById(Long.parseLong(proId));
			//request.setAttribute("proDiyDetailval2", lists) ;
			model.addAttribute("proDiyDetail", lists);
		 
		}
		return "/home/diy/diy";
	}
	
	 
	
	/**************************************点击系列 ，系列作为查询条件，返回 门 数据，list*************************************************************/
	@RequestMapping(value = "/pages/company/proclassData2.do")
	public void getComboDatapro2(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		 String callback ="callback"; //request.getParameter("callback");
		//String proclassname = request.getParameter("proclassname");
	    String proclassname = new String(request.getParameter("proclassname").getBytes("iso-8859-1"),"utf-8");  //转换格式          

	
		System.out.println(",,,,,,,,,," + proclassname);
		JSONArray result = new JSONArray();
		JSONObject ret=new JSONObject(); 
			String type = proclassname;
		List<Commodity> list = null;
		 JSONArray  arrayya12=new JSONArray();
		
		list = commodityBo.findCommodityByproClass(proclassname);
		Long id=null;
		String name=null;
		String img=null;
		
		  for(int k=0;k<list.size();k++){ 
			  id=list.get(k).getId();     //门的 id
		   name=list.get(k).getBrand();   //  门的 品牌
		   img=list.get(k).getPhoto();  //照片路径
		   arrayya12.add(buildproright(id,name,img));
		  }

	
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		ret.put("data", arrayya12.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}
	private JSONObject buildSe(long id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);

		return result;
	}
	
	private JSONObject buildproright(long id, String name,String img) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		

		return result;
	}

	
	private JSONObject buildjsonDiarray(String id, String name,List l) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", l);
		

		return result;
	}
	
	
	private JSONObject buildjsonDia(Long id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
	
		

		return result;
	}
    
    @RequestMapping(value = "/pages/company/getParameterqiyesBynt.do")
    public String getParameterqiyesBynt(Locale locale, Model model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        String name2 = request.getParameter("jibenName");   //  name
        String name=new String(name2.getBytes( "iso-8859-1"),"UTF-8" );
        String type2 = request.getParameter("type");   //  name
        String type=new String(type2.getBytes( "iso-8859-1"),"UTF-8" );
        Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
     
//        String startStr = request.getParameter("start");
//        String limitStr = request.getParameter("limit");
//        int start = Integer.parseInt(startStr);
//        int limit = Integer.parseInt(limitStr);
//        if (limit == 0) {
//            limit = Integer.MAX_VALUE;
//        }
        PrintWriter writer = response.getWriter();
        DataModel datamodel = new DataModel<Parameterqiye>();
      //  int end = start + limit;
        Map map = new HashMap();
//        if (!Utils.nullOrEmpty(jibenName)){
//            String userNameap=new String(jibenName.getBytes( "iso-8859-1"),"UTF-8" );
//            map.put("name", "%"+userNameap+"%");
//        }
        List<Parameterqiye> list = null;
//        if (!Utils.nullOrEmpty(orgid))
//			map.put("org", orgid);
//		
//		if (map.size()>0) {
//			list = parameterqiyeBo.findByParm(map);
//			
//		} else if(map.size()>1) {
			list = parameterqiyeBo.findParameterqiyeByName(name);
			
			//list = parameterqiyeBo.findAll();
	//	}
        int total = list.size();
        System.out.println("查询到的条数"+total);
//        end = end > total ? total : end;
//        if (start <= end) {
//            datamodel.setRows(list.subList(start, end));
//        }
        datamodel.setTotal(total);
        writer.write(JSONObject.fromObject(datamodel).toString());
        writer.flush();

        return null;
    }
    
    
    @RequestMapping(value = "/pages/company/saveParameterqiye.do")
    public String saveParameterqiye(HttpServletRequest request,
            HttpServletResponse response, Parameterqiye vperson) throws Exception {

        codSet(response);
        String operation = request.getParameter("operation");
        Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
     
        PrintWriter writer = response.getWriter();
        if (ADD.equals(operation)) {
        	vperson.setOrg(orgid);
        	parameterqiyeBo.save(vperson);
            writer.write("true");
        } else if (UPDATE.equals(operation)) {
            vperson.setOrg(orgid);
            parameterqiyeBo.update(vperson);
            writer.write("true");
        } else if (DELETE.equals(operation)) {
            parameterqiyeBo.delete(vperson);
            writer.write("true");
        }
        writer.flush();
        return null;
    }
    
    public void codSet(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setDateHeader("Expires", 0);
    }
}
