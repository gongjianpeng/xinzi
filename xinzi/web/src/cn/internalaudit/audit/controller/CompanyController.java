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

import cn.internalaudit.audit.bo.ICompanyBo;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CompanyController {
	@Resource(name = "CompanyBo")
	ICompanyBo companyBo;
	
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	@Resource
	private LoginInfo loginInfo;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @RequestParam("name") String name,
	 * @throws Exception
	 */
	@RequestMapping(value = "/pages/company/getCompany.do")
	public String getCompany(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		codSet(response);

		String companyname = request.getParameter("companyname");
		String code = request.getParameter("code");
		Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
	    String  loginpersonname=loginPerson.getName();
		
		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		
		DataModel datamodel = new DataModel<Company>();
		int end = start + limit;
		Map map = new HashMap();
		List<Company> list = null;
		if (!Utils.nullOrEmpty(companyname)) {
			String companyname2 = new String(companyname.getBytes("iso-8859-1"),
					"UTF-8");
			map.put("companyname", companyname2 );
		}
		
		if (!Utils.nullOrEmpty(orgid)){
			map.put("org", orgid);
		}
		list = companyBo.findByParms(map);


		
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
	
	
	
	
	
	@RequestMapping(value = "/pages/company/companyOperate.do")
	public String companyOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Company entity) throws Exception {
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
			
			companyBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			
			String orgid2=entity.getOrg();
			System.out.println("更新的时候查询到的"+orgid2);
			entity.setOrg(orgid2);
			companyBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			companyBo.delete(entity);
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
