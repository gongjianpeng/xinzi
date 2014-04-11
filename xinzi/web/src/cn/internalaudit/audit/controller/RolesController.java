package cn.internalaudit.audit.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.internalaudit.audit.bo.IAuthoritiesKeyBo;
import cn.internalaudit.audit.bo.IRolesAuthoritiesBo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.entitys.AuthoritiesKey;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.entitys.RolesAuthorities;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RolesController {

	@Resource(name = "RolesBo")
	private IRolesBo rolesBo;

	@Resource(name = "RolesAuthoritiesBo")
	private IRolesAuthoritiesBo rolesAuthoritiesBo;

	@Resource(name = "AuthoritiesKeyBo")
	private IAuthoritiesKeyBo authoritiesKeyBo;
	
	@Resource
	private LoginInfo loginInfo;
	
	@RequestMapping(value = "/pages/system/getRoles.do")
	public String getRoles(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.codSet(response);
		String rolesNameapp = request.getParameter("rolesName");
		DataModel datamodel = new DataModel<Roles>();
		List<Roles> list = new ArrayList();
		
		Person loginPerson = loginInfo.getLoginPerson();
        String  loginper=loginPerson.getName();
	
        String	orgid=loginPerson.getOrganizationId();
	    String orgname=loginPerson.getOrganizationName();
	    
		if (!Utils.nullOrEmpty(rolesNameapp)) {
			String rolesName=new String(rolesNameapp.getBytes( "iso-8859-1"),"UTF-8" );
			System.out.println("rolesName的信息为："+rolesName);
			Map map = new HashMap();
			map.put("name", "%" + rolesName + "%");
			map.put("createPerson", loginper);
			list = rolesBo.findByParm(map);
		} else {
			Map map = new HashMap();
			map.put("createPerson", loginper);
			list = rolesBo.findByParm(map);
			//list = rolesBo.findAll();
		}
		
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/saveRoles.do")
	public String saveRoles(HttpServletRequest request,
			HttpServletResponse response, Roles rolesv) throws Exception {
		request.setCharacterEncoding("UTF-8");
		this.codSet(response);
		response.setContentType("text/html");

		Roles roles = new Roles();
		if (rolesv.getId() != null) {
			roles = rolesBo.find(rolesv.getId());

		}
		String[] dataAuthorLevel=request.getParameterValues("dataLevel");
		String dataLevelStr=StringUtils.join(dataAuthorLevel, ":");
		roles.setName(rolesv.getName());
		roles.setDataAuthorLevel(dataLevelStr);
		rolesBo.save(roles);
		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/deleteRoles.do")
	public String deleteRoles(HttpServletRequest request,
			HttpServletResponse response, Roles roles) throws Exception {

		this.codSet(response);

		PrintWriter writer = response.getWriter();
	if (roles.getId() != null) {
			roles = rolesBo.find(roles.getId());
			if (roles.getPersons().size() > 0) {
			writer.write("请在授权管理中，移除授权该角色的用户");
			} else {
				rolesBo.remove(roles);
				writer.write("true");
			}
		} else {
			writer.write("false");
		}

		writer.flush();
		return null;
	}

	/* RolesAuthorities********************************************** */
	@RequestMapping(value = "/pages/system/getRolesAuthorities.do")
	public String getRolesAuthorities(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.codSet(response);

		String rolesId = request.getParameter("rolesId");
		List<RolesAuthorities> list = new ArrayList();
		if (!Utils.nullOrEmpty(rolesId)) {
			list = rolesAuthoritiesBo
					.findAuthorByRolesId(Long.valueOf(rolesId));
		}
		DataModel datamodel = new DataModel<RolesAuthorities>();
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

/*	@RequestMapping(value = "/pages/system/getAuthoritiesKey.do")
	public String getAuthoritiesKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.codSet(response);

		List<AuthoritiesKey> list = new ArrayList();
		list = authoritiesKeyBo.findAll();
		DataModel datamodel = new DataModel<AuthoritiesKey>();
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}
	*/

	@RequestMapping(value = "/pages/system/setAuthoritiesKey.do")
	public String setAuthoritiesKey(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Long rolesId,
			@RequestParam String authorKeys) throws Exception {

		this.codSet(response);
		response.setContentType("text/html");

		if (rolesId != null) {
			Roles roles = this.rolesBo.find(rolesId);

			rolesBo.addAuthor(rolesId, authorKeys);
			this.rolesBo.save(roles);
		}

		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}



	@RequestMapping(value = "/pages/system/removeRolesAuthorities.do")
	public String removeRolesAuthorities(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.codSet(response);

		String selectedStr = request.getParameter("selectedStr");
		selectedStr = selectedStr.substring(0, selectedStr.length() - 1);
		if (!Utils.nullOrEmpty(selectedStr)) {
			rolesAuthoritiesBo.deleteRolesAuthorities(selectedStr);
		}

		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}

	private int indexOf(String rolesAuthorKeyId,
			List<RolesAuthorities> rolesAuthoritiess) {
		int i = 0;
		for (RolesAuthorities rat : rolesAuthoritiess) {
			if (rat.getId().longValue() == Long.valueOf(rolesAuthorKeyId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	public void codSet(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setDateHeader("Expires", 0);
	}
}
