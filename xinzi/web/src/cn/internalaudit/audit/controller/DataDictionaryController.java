package cn.internalaudit.audit.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.bo.IDataDictionaryBo;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.utils.DataModel;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DataDictionaryController {

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	@Resource(name = "DataDictionaryBo")
	public IDataDictionaryBo dataDictionaryBo;
	
		
		@RequestMapping(value ="/pages/system/operateDataDictionary.do")
		public String operateDataDictionary(Locale locale, Model model,
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

			DataModel datamodel = new DataModel<DataDictionary>();
			String code = request.getParameter("qCode");
			String name = request.getParameter("qName");
			String type = request.getParameter("qType");
			if (code == null && name == null && type == null) {
				List<DataDictionary> entitys = new ArrayList<DataDictionary>();
				for (DataDictionary entity : dataDictionaryBo.findAll()) {
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
				List<DataDictionary> entitys = new ArrayList<DataDictionary>();
				for (DataDictionary entity : dataDictionaryBo.findByParms(parms)) {
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

		
		@RequestMapping(value ="/pages/system/operateDataDictionaryde.do")
		public String operateDataDictionaryde(Locale locale, Model model,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			String operation = request.getParameter("operation");

			DataModel datamodel = new DataModel<DataDictionary>();
			
			String type = "dengji";
			if ( type == null) {
				List<DataDictionary> entitys = new ArrayList<DataDictionary>();
				for (DataDictionary entity : dataDictionaryBo.findAll()) {
					entitys.add(entity);
				}
				int total = entitys.size();
				
				datamodel.setTotal(total);
			} else {
				HashMap parms = new HashMap();
				if (type != null && type != "") {
					parms.put("type", "%" + type + "%");
				}
				List<DataDictionary> entitys = new ArrayList<DataDictionary>();
				for (DataDictionary entity : dataDictionaryBo.findByParms(parms)) {
					entitys.add(entity);
				}
				int total = entitys.size();
				
			}
			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.fromObject(datamodel).toString());
			writer.flush();
			return null;
		}
		
	@RequestMapping(value = "/pages/system/dataDictionaryOperate.do")
	public String dataDictionaryController(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DataDictionary entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			dataDictionaryBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			dataDictionaryBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			dataDictionaryBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/getDataDictionarys.do")
	public String getDataDictionarys(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<DataDictionary>();
		int end = start + limit;
		List<DataDictionary> entitys = new ArrayList<DataDictionary>();
		entitys = dataDictionaryBo.findAll();
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

	
	
	@RequestMapping(value = "/pages/system/dataDictionaryBywite.do")
	public String dataDictionaryBywite(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String  type="wite";
		String  code="www";
		DataDictionary  data=new DataDictionary();
		data=dataDictionaryBo.findDataDictionaryByCode(code, type);
		if(data!=null){
			String dataname=data.getName();
			PrintWriter writer = response.getWriter();
		    writer.write(dataname);
			writer.flush();
		}
		return null;
	}
	
	
	

}
