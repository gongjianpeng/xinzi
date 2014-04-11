package cn.internalaudit.audit.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import cn.internalaudit.audit.bo.IDataQuoteBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.DataQuote;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DataQuoteController {

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	@Resource
	public IDataQuoteBo dataQuoteBo;
	
	
	
	@Resource
	private LoginInfo loginInfo;
		
		@RequestMapping(value ="/pages/company/operateDataQuote.do")
		public String operateDataQuote(Locale locale, Model model,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			String operation = request.getParameter("operation");
			// query
			String startStr = request.getParameter("start");
			String limitStr = request.getParameter("limit");
			int start = Integer.parseInt(startStr);
			int limit = Integer.parseInt(limitStr);
			int end = start + limit;

			DataModel datamodel = new DataModel<DataQuote>();
			String code = request.getParameter("qCode");
			String name = request.getParameter("qName");
			String type = request.getParameter("qType");
			if (code == null && name == null && type == null) {
				List<DataQuote> entitys = new ArrayList<DataQuote>();
				for (DataQuote entity : dataQuoteBo.findAll()) {
					entitys.add(entity);
				}
				int total = entitys.size();
				end = end > total ? total : end;
				if (start <= end) {
					datamodel.setRows(entitys.subList(start, end));
				}
				datamodel.setTotal(total);
			} else {
				HashMap parms = new HashMap();
				if (code != null && code != "") {
					parms.put("code", "%" + code + "%");
				}
				if (name != null && name != "") {
					String dname=new String(name.getBytes( "iso-8859-1"),"UTF-8" );
					parms.put("name", "%" + dname + "%");
				}
				if (type != null && type != "") {
					parms.put("type", "%" + type + "%");
				}
				List<DataQuote> entitys = new ArrayList<DataQuote>();
				for (DataQuote entity : dataQuoteBo.findByParms(parms)) {
					entitys.add(entity);
				}
				int total = entitys.size();
				end = end > total ? total : end;
				if (start <= end) {
					datamodel.setRows(entitys.subList(start, end));
				}
				datamodel.setTotal(total);
			}
			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.fromObject(datamodel).toString());
			writer.flush();
			return null;
		}

		
		@RequestMapping(value ="/pages/company/operateDataQuotede.do")
		public String operateDataQuotede(Locale locale, Model model,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			String operation = request.getParameter("operation");

			DataModel datamodel = new DataModel<DataQuote>();
			
			String type = "dengji";
			if ( type == null) {
				List<DataQuote> entitys = new ArrayList<DataQuote>();
				for (DataQuote entity : dataQuoteBo.findAll()) {
					entitys.add(entity);
				}
				int total = entitys.size();
				
				datamodel.setTotal(total);
			} else {
				HashMap parms = new HashMap();
				if (type != null && type != "") {
					parms.put("type", "%" + type + "%");
				}
				List<DataQuote> entitys = new ArrayList<DataQuote>();
				for (DataQuote entity : dataQuoteBo.findByParms(parms)) {
					entitys.add(entity);
				}
				int total = entitys.size();
				
			}
			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.fromObject(datamodel).toString());
			writer.flush();
			return null;
		}
		
	@RequestMapping(value = "/pages/company/DataQuoteOperate.do")
	public String DataQuoteController(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DataQuote entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		  //获取登录ID
     	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
        
		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			entity.setOrg(orgid);
			dataQuoteBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			dataQuoteBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			dataQuoteBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/company/getDataQuotes.do")
	public String getDataQuotes(Locale locale, Model model,
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
		DataModel datamodel = new DataModel<DataQuote>();
		int end = start + limit;
		List<DataQuote> entitys = new ArrayList<DataQuote>();
		
		 Map map = new HashMap();
	       
	        if (!Utils.nullOrEmpty(orgid)){
				map.put("org", orgid);
	        }
	        
		entitys = dataQuoteBo.findByParms(map);
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
	
	
	@RequestMapping(value = "/pages/company/getDataQuotesquery.do")
	public String getDataQuotesquery(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		
//		Person loginPerson = loginInfo.getLoginPerson();
//
//		String orgid = loginPerson.getOrganizationId();
		
		String quoteName = request.getParameter("quoteName");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<DataQuote>();
		int end = start + limit;
		List<DataQuote> entitys = new ArrayList<DataQuote>();
		HashMap parms = new HashMap();
		
		if (quoteName != null && quoteName != "") {
			String quoteName2=new String(quoteName.getBytes( "iso-8859-1"),"UTF-8" );
			parms.put("name", "'%" + quoteName2 + "%'");
		}
		
//		if (!Utils.nullOrEmpty(orgid)){
//			parms.put("org", orgid);
//		}
		entitys = dataQuoteBo.findByParms(parms);
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

	
	
	@RequestMapping(value = "/pages/company/DataQuoteBywite.do")
	public String DataQuoteBywite(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String  type="wite";
		String  code="www";
		DataQuote  data=new DataQuote();
		data=dataQuoteBo.findDataQuoteByCode(code, type);
		if(data!=null){
			String dataname=data.getName();
			PrintWriter writer = response.getWriter();
		    writer.write(dataname);
			writer.flush();
		}
		return null;
	}
	
	
	

}
