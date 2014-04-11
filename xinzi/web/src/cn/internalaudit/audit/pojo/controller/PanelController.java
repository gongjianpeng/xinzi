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

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.Panel;
import cn.internalaudit.audit.pojo.bo.IPanelBo;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PanelController {

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	@Resource(name = "PanelBo")
	public IPanelBo panelBo;
	 @Resource
		private LoginInfo loginInfo;
		
	
		
	@RequestMapping(value = "/pages/company/panelOperate.do")
	public String panelController(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Panel entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
        Long personid=loginPerson.getId();
		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			entity.setOrg(orgid);
			panelBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
		
			panelBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			panelBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/company/getDataPanels.do")
	public String getDataPanels(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		 //获取登录ID
	   	Person loginPerson = loginInfo.getLoginPerson();
	       String	orgid=loginPerson.getOrganizationId();
	       Map map = new HashMap();
	       
	       if (!Utils.nullOrEmpty(orgid)){
				map.put("org", orgid);
	       }

		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<Panel>();
		int end = start + limit;
		List<Panel> entitys = new ArrayList<Panel>();
		entitys = panelBo.findByParms(map);
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
