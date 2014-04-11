package cn.internalaudit.audit.pojo.controller;


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

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.Frame;
import cn.internalaudit.audit.pojo.Plicalbum;
import cn.internalaudit.audit.pojo.bo.IFrameBo;
import cn.internalaudit.audit.pojo.bo.IPlicalbumBo;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PlicalbumController {

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	

	@Resource
	public IPlicalbumBo plicalbumBo;
	
		
		
	 @Resource
		private LoginInfo loginInfo;
		
		
	@RequestMapping(value = "/pages/company/plicalbumOperate.do")
	public String plicalbumOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Plicalbum entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			plicalbumBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			plicalbumBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			plicalbumBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/company/getDataPlicalbums.do")
	public String getDataPlicalbums(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		Object pinname=session.getAttribute("brandName");
		System.out.println("======电子画册中=="+pinname);
		 //获取登录ID
	   	Person loginPerson = loginInfo.getLoginPerson();
	       String	orgid=loginPerson.getOrganizationId();
	       
	    //获取当前用户选择的品牌
	       
	       Map map = new HashMap();
	       
//	       if (!Utils.nullOrEmpty(orgid)){
//				map.put("org", orgid);
//	       }
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<Plicalbum>();
		int end = start + limit;
		List<Plicalbum> entitys = new ArrayList<Plicalbum>();
		entitys = plicalbumBo.findByParms(map);
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

	
	
	@RequestMapping(value = "/pages/company/getDataPlicalbum2s.do")
	public String getDataPlicalbum2s(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		
		Object pinname=session.getAttribute("brandName");
		System.out.println("======电子画册中=="+pinname);
		 //获取登录ID
	   	Person loginPerson = loginInfo.getLoginPerson();
	       String	orgid=loginPerson.getOrganizationId();
	       
	    //获取当前用户选择的品牌
	       //String pinpai="美心门";
	       String pinpai=(String) pinname;
	    	 //  request.getParameter("pinpai");
	       System.out.println("Pli 用户选择的品牌为"+pinpai);
	       Map map = new HashMap();
//	       if (!Utils.nullOrEmpty(pinpai)){
//	    	String   pinpai2=new String(pinpai.getBytes( "iso-8859-1"),"UTF-8");
//	            map.put("name",pinpai2);//
//	        }
	       map.put("name",pinpai);//
//	       if (!Utils.nullOrEmpty(orgid)){
//				map.put("org", orgid);
//	       }
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<Plicalbum>();
		int end = start + limit;
		List<Plicalbum> entitys = new ArrayList<Plicalbum>();
		entitys = plicalbumBo.findByParms(map);
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
