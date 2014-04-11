package cn.internalaudit.audit.controller;

import java.io.PrintWriter;
import java.util.HashMap;
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
import cn.internalaudit.audit.bo.ICompanyqiyeBo;
import cn.internalaudit.audit.entitys.BaseEntity;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.base.Companyqiye;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 * 企业管理经销商
 */
@Controller
public class CompanyqiyeController {
	@Resource(name = "CompanyqiyeBo")
	ICompanyqiyeBo companyqiyeBo;
	
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	
	public static final String LOCK = "lock";

	public static final String TRUELOCK = "truelock";
	@Resource
	private LoginInfo loginInfo;
	
	@Resource(name = "BrandBo")
    public IBrandBo brandBo;
	
	/********
	 * 经销商管理中， 品牌下拉数据
	 * 查询 品牌表 数据
	 * brand --table
	 * name ---品牌name
	 * */
	@RequestMapping(value = "/pages/compnay/pinpaiComboData.do")
	public String pinpaiComboData(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String type = request.getParameter("type");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
		
		
		 String name = request.getParameter("brandName");
	     
	        //获取登录ID
	    	Person loginPerson = loginInfo.getLoginPerson();
	        String	orgid=loginPerson.getOrganizationId();
		List list = null;
		

		Map map = new HashMap();
		map.put("org", orgid);
		list = brandBo.findByParm(map);
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
	
	
	@RequestMapping(value = "/pages/compnay/pinpaiComboData2.do")
	public String pinpaiComboData2(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String type = request.getParameter("type");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
		
		
		 String name = request.getParameter("brandName");
	     
	        //获取登录ID
	    	Person loginPerson = loginInfo.getLoginPerson();
	        String	orgid=loginPerson.getOrganizationId();
		List list = null;
		

		Map map = new HashMap();
		map.put("org", orgid);
		list = brandBo.findByParm(map);
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
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @RequestParam("name") String name,
	 * @throws Exception
	 */
	@RequestMapping(value = "/pages/company/getCompanyqiye4.do")
	public String getCompanyqiye4(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String type = request.getParameter("type");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
		
		
		 String name = request.getParameter("brandName");
	     
	        //获取登录ID
	    	Person loginPerson = loginInfo.getLoginPerson();
	        String	orgid=loginPerson.getOrganizationId();
		List list = null;
		
System.out.println("查询品牌下拉框为"+orgid);
		Map map = new HashMap();
		map.put("type", type);
		map.put("org", orgid);
		list = brandBo.findByParmsgetDao(map);
		PrintWriter writer = response.getWriter();

		if (null != list && list.size() > 0) {
			DataModel dataModel = new DataModel<BaseEntity>();
			writer.write(dataModel.setRows(list).toString());
			//writer.write("[{'text':'','value':''}]");
		} else {
			writer.write("[{'text':'','value':''}]");
		}
		writer.flush();
		return null;
	}
	
	
	
	@RequestMapping(value = "/pages/company/getCompanyqiye.do")
	public String getCompanyqiye(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		codSet(response);

		String companyqiyename = request.getParameter("companyqiyename");
		String code = request.getParameter("code");
		Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
	 
		
		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		
		DataModel datamodel = new DataModel<Companyqiye>();
		int end = start + limit;
		Map map = new HashMap();
		if (!Utils.nullOrEmpty(companyqiyename)){
			String companyqiyename2=new String(companyqiyename.getBytes( "iso-8859-1"),"UTF-8" );
			map.put("companyname", companyqiyename2);
		}
		if (!Utils.nullOrEmpty(code))
			map.put("code", "%"+code+"%");
		List<Companyqiye> list = null;
		if (!Utils.nullOrEmpty(orgid))
			map.put("org",orgid);
		
		if (map.size()>0) {
			list = companyqiyeBo.findByParms(map);
		} else if(map.size()>1) {
			list = companyqiyeBo.findByParms(map);
		}
		int total = list.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
		datamodel.setTotal(total);
	    PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @RequestParam("name") String name,
	 * @throws Exception
	 */
	@RequestMapping(value = "/pages/company/getCompanyqiye2.do")
	public String getCompanyqiye2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		codSet(response);

		String pinpai = request.getParameter("pinpai");
		String code = request.getParameter("code");
		Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
       
        String companyqiyename = loginPerson.getOrganizationName();
		System.out.println(pinpai+"------------------------------");
		
		
		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		
		DataModel datamodel = new DataModel<Companyqiye>();
		int end = start + limit;
		Map map = new HashMap();

		List<Companyqiye> list = null;

	    if (!Utils.nullOrEmpty(companyqiyename)){
			String companyqiyename2=new String(companyqiyename.getBytes( "iso-8859-1"),"UTF-8" );
			map.put("companyname", companyqiyename2);
		}
	    if (!Utils.nullOrEmpty(pinpai)){
			String pinpai2=new String(pinpai.getBytes( "iso-8859-1"),"UTF-8" );
		
			list = companyqiyeBo.findByParmsandpinpai(map, pinpai2);
		}
		else{
			
			list = companyqiyeBo.findByParms(map);
		} 
//		else if(map.size()>1) {
//			list = companyqiyeBo.findByParms(map);
//		}
		int total = list.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
		datamodel.setTotal(total);
	    PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

	
	  /*****经销商 对授权的品牌进行查询
	   * 企业中进行添加经销商的信息， 设定条件
	   * 条件  次企业的名称 333
	   * 
	   * ****/
	@RequestMapping(value = "/pages/company/getCompanyqiye3.do")
	public String getCompanyqiye3(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		codSet(response);

		//String companyqiyename = request.getParameter("companyqiyename");
		String code = request.getParameter("code");
		Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
        String companyqiyename = loginPerson.getOrganizationName();
		System.out.println(companyqiyename+"------------------------------");
		
		
		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		
		DataModel datamodel = new DataModel<Companyqiye>();
		int end = start + limit;
		Map map = new HashMap();
	   if (!Utils.nullOrEmpty(companyqiyename)){
			String companyqiyename2=new String(companyqiyename.getBytes( "iso-8859-1"),"UTF-8" );
			map.put("companyname", companyqiyename2);
		}
		/*if (!Utils.nullOrEmpty(code))
			map.put("code", "%"+code+"%");*/
		List<Companyqiye> list = null;
//		if (!Utils.nullOrEmpty(orgid))
//			map.put("org",orgid);
//		
		if (map.size()>0) {
			list = companyqiyeBo.findByParms(map);
		} 
		int total = list.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
		datamodel.setTotal(total);
	    PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}
	
	@RequestMapping(value = "/pages/company/companyqiyeOperate.do")
	public String companyqiyeOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Companyqiye entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
	 
		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			entity.setOrg(orgid);
			
			companyqiyeBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
		    entity.setOrg(orgid);
			companyqiyeBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			companyqiyeBo.delete(entity);
			writer.write("true");
		}
		else if (LOCK.equals(operation)) {
			entity.setOrg(orgid);
			entity.setStatus("收到品牌解除申请");
			companyqiyeBo.update(entity);
			writer.write("true");
		}
		else if (TRUELOCK.equals(operation)) {
			
			entity.setStatus("解除授权");
			companyqiyeBo.delete(entity);
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
