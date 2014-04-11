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
import cn.internalaudit.audit.pojo.Elses;
import cn.internalaudit.audit.pojo.bo.IElsesBo;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ElsesController {

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	

	@Resource(name = "ElsesBo")
	public IElsesBo elsesBo;
	
	   
    @Resource
	private LoginInfo loginInfo;
		

		
		
	@RequestMapping(value = "/pages/company/elsesOperate.do")
	public String elsesOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Elses entity) throws Exception {
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
			elsesBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			elsesBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			elsesBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/company/getDataElses.do")
	public String getDataElses(Locale locale, Model model,
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
		DataModel datamodel = new DataModel<Elses>();
		int end = start + limit;
		List<Elses> entitys = new ArrayList<Elses>();
		entitys = elsesBo.findByParms(map);
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
