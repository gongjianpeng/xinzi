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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.TreeModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AuthoizeController {

	@Resource(name = "OrganizationBo")
	IOrganizationBo organizationBo;
	@Resource(name = "DepartmentBo")
	IDepartmentBo departmentBo;
	@Resource(name = "PersonBo")
	IPersonBo personBo;
	@Resource(name = "RolesBo")
	private IRolesBo rolesBo;
	@Resource
	private LoginInfo loginInfo;	


	 
	// 授权管理中的 person下拉树读取。
	@RequestMapping(value = "/pages/system/getPersonTree.do")
	public String getOrganizationTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utils.setHeader(response);
		
		PrintWriter writer = response.getWriter();
		TreeModel treemodel = new TreeModel<Organization>();
		
		Person loginPerson = loginInfo.getLoginPerson();
        String  loginper=loginPerson.getName();
        Long logid=loginPerson.getId();
        String	orgid=loginPerson.getOrganizationId();
	    String orgname=loginPerson.getOrganizationName();
	    System.out.println("Auth系统登录用户为："+loginper+"公司名称为"+orgname);
	    
		List<Organization> list = this.organizationBo.findByHeadquarters2(orgid);
		
		writer.write(treemodel.createTreeNodes(list, Person.class).toString());

		writer.flush();

		return null;
	}

	@RequestMapping(value = "/pages/system/getRolesByPersonId.do")
	public String getRoles(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");

		String personId = request.getParameter("personId");
		String no = null;
		
		System.out.println(personId);
		DataModel datamodel = new DataModel<Roles>();
		Map map = new HashMap();
		List<Roles> list = new ArrayList();
		if (!Utils.nullOrEmpty(personId) && Utils.isNumeric(personId)) {
			Person person = personBo.find(Long.valueOf(personId));
			list = person.getRoless();
			System.out.println("Auth   personID为"+person.getId());
			
		}else if(personId==no){
			//查询 角色，根据当前用户去过滤，查找当前用户设定的角色。
			//list = rolesBo.findAll();
			Person loginPerson = loginInfo.getLoginPerson();
	        String  loginper=loginPerson.getName();
	        Long logid=loginPerson.getId();
	        String	orgid=loginPerson.getOrganizationId();
		    String orgname=loginPerson.getOrganizationName();
			System.out.println("Auth  条件查询 ");
		    map.put("createPersonId", logid);  
		    map.put("createPerson", loginper);  
		    list = rolesBo.findByParm(map);
		    
		}
		
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/getPersonsByParent.do")
	public String getPersons(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");

		String parentId = request.getParameter("parentId");
		String isorg = request.getParameter("isorg");

		DataModel datamodel = new DataModel<Person>();
		Map map = new HashMap();

		List<Person> list = new ArrayList();
		if (!Utils.nullOrEmpty(parentId)) {
			Object obj = null;
			if (!Utils.nullOrEmpty(isorg)) {
				obj = organizationBo.find(Long.valueOf(parentId));
			} else {
				obj = departmentBo.find(Long.valueOf(parentId));
			}
			this.getPersons(obj, list);
		}
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

	private List<Person> getPersons(Object obj, List<Person> list) {
		if (obj instanceof Organization) {
			List<Department> deptList = ((Organization) obj).getDepartments();
			for (Department dept : deptList) {
				list.addAll(dept.getPersons());
			}
			List<Organization> orgList = ((Organization) obj)
					.getOrganizations();
			for (Organization org : orgList) {
				getPersons(org, list);
			}
		} else if (obj instanceof Department) {
			list.addAll(((Department) obj).getPersons());
		}
		return null;
	}

	@RequestMapping(value = "/pages/system/setRoles.do")
	public String setRoles(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Long personId,
			@RequestParam String rolesStr) throws Exception {
		
		Utils.setHeader(response);

		if (personId != null) {
			this.personBo.setRoles(personId, rolesStr);
		}

		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/removeRoles.do")
	public String removeRoles(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Long personId,
			@RequestParam String rolesStr) throws Exception {
		
		Utils.setHeader(response);

		if (personId != null) {
			this.personBo.removeRoles(personId, rolesStr);
		}

		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}

}
