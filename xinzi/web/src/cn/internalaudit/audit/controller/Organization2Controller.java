package cn.internalaudit.audit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.internalaudit.audit.bo.IDepartment2Bo;
import cn.internalaudit.audit.bo.impl.Organization2Bo;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.entitys.Organization2;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.DiyModel;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.TreeModel;
import cn.internalaudit.audit.utils.Utils;

@Controller
public class Organization2Controller {
	@Resource(name = "Organization2Bo")
	Organization2Bo organizationBo;
	@Resource(name = "Department2Bo")
	private IDepartment2Bo departmentBo;
	@Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
	@Resource
	private LoginInfo loginInfo;	


	@RequestMapping(value = "/pages/system/getAllOrganization2.do")
	public String getAllOrganization2(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		codSet(response);
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<Organization2>();
		datamodel.setRows(this.organizationBo.findOrgByNoDelete());
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/getOrganizationTree2.do")
	public String getOrganization(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.codSet(response);
		PrintWriter writer = response.getWriter();
		
		Person loginPerson = loginInfo.getLoginPerson();
        String  loginper=loginPerson.getName();
         Long logid=loginPerson.getId();
        String	orgid=loginPerson.getOrganizationId();
	    String orgname=loginPerson.getOrganizationName();
	    System.out.println("系统登录用户为："+loginper+"公司名称为"+orgname);
		String bid = request.getParameter("bid");

		TreeModel treemodel = new TreeModel<Organization2>();
        // String id2=Long.toString(logid); 
		List<Organization2> list=null;
		if(loginper.equals("admin")){
			System.out.println("+++++"+loginper);
			 list = this.organizationBo.findByHeadquarters();
		}else{
			list=this.organizationBo.findByHeadquarters2(orgid);
		}
		writer.write(treemodel.createTreeNodes(list, Department2.class).toString());
		writer.flush();
		
		return null;
	}

	@RequestMapping(value = "/pages/system/getOrganizationByTreeId2.do")
	public String getOrganizationByTreeId2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		codSet(response);
		Map map = new HashMap<String, String>();
		Organization2 org = new Organization2();
		String id = request.getParameter("id");
		List list = new ArrayList();
		if (id != null) {
			org.setId(Long.valueOf(id));
			map.put("parentOrganization", org);
			list = this.organizationBo.findByParm(map);
		}
		DataModel datamodel = new DataModel<Organization2>();
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());

		writer.flush();
		return null;
	}

	/*
	 * Organizations to save
	 */
	@RequestMapping(value = "/pages/system/organizationSave2.do")
	public String organizationSave2(HttpServletRequest request,
			HttpServletResponse response, Organization2 orgv) throws Exception {
		codSet(response);
		response.setContentType("text/html");
		Organization2 org = new Organization2();
		if (orgv.getId() != null) {
			org = organizationBo.find(orgv.getId());
		}
		DataModel dataModel = new DataModel<Organization2>();
		dataModel.copy(orgv, org);

		String parent = request.getParameter("par");
		if (!Utils.nullOrEmpty(parent)) {
			org.setParentOrganization(this.organizationBo.find(Long
					.valueOf(parent)));
		}
		String isHeader = request.getParameter("isHeadquarters");
		if (!Utils.nullOrEmpty(isHeader) && isHeader.equals("true")) {
			org.setIsHeadquarters(true);
		} else {
			org.setIsHeadquarters(false);
		}

		this.organizationBo.save(org);

		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/organizationDelete2.do")
	public String organizationDelete2(HttpServletRequest request,
			HttpServletResponse response, Organization2 org) throws Exception {
		codSet(response);
		response.setContentType("text/html");

		this.organizationBo.remove(org);
		response.getWriter().write("true");
		response.getWriter().flush();
		return null;
	}

	/*
	 * Load drop-down box
	 */
	@RequestMapping(value = "/pages/controlCriterion/getComboData2.do")
	public String getComboData2(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String comboType)
			throws ServletException, IOException {
		codSet(response);
		DataModel dataModel = new DataModel<Organization2>();
		List list = null;
		if (comboType.equals("org")) {
			list = this.organizationBo.findOrgByNoDelete();
		}
		PrintWriter writer = response.getWriter();
		writer.write(dataModel.setRows(list).toString());
		writer.flush();
		return null;
	}
	
	
	
	


	@RequestMapping(value = "/pages/system/getDepartmentsByOrg2.do")
	public List<Department2> getDepartmentsList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		codSet(response);

		List list = new ArrayList();
		String orgId = request.getParameter("orgId");
		if (orgId != null) {
			list = departmentBo.findByOrganizationId(Long.valueOf(orgId));
		}
		String deptId = request.getParameter("deptId");
		if (deptId != null) {
			list = departmentBo.find(Long.valueOf(deptId)).getDepartments();
		}
		DataModel datamodel = new DataModel<Department2>();
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/departmentSave2.do")
	public String departmentSave2(HttpServletRequest request,
			HttpServletResponse response, Department2 deptv) throws Exception {
		codSet(response);
		response.setContentType("text/html");
System.out.println("---------开始--------------------");
		Department2 dept = new Department2();
		if (deptv.getId() != null) {
			dept = departmentBo.find(deptv.getId());
			System.out.println("---------开始-2-------------------");
		} else {
			String parentId = request.getParameter("parentId");
			String isOrg = request.getParameter("isOrg");
			System.out.println("parentId"+parentId+"isOrg"+isOrg);
			if (!Utils.nullOrEmpty(parentId)) {
				
				System.out.println("---------开始--3------------------");
				if (!Utils.nullOrEmpty(isOrg)) {
					dept.setOrganization(this.organizationBo.find(Long
							.valueOf(parentId)));
				
				} else {
					System.out.println("---------开始---4-----------------");
					Department2 parentDepartment = this.departmentBo.find(Long
							.valueOf(parentId));
					dept.setParentDepartment(parentDepartment);
				}
			}
		}
		System.out.println("---------结束----dep----------------");
		DataModel datamodel = new DataModel<Department2>();
		//dept.setOrganization(this.organizationBo.find(Long.valueOf(parentId)));
		datamodel.copy(deptv, dept);
		this.departmentBo.save(dept);
		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/departmentDelete2.do")
	public String departmentDelete2(HttpServletRequest request,
			HttpServletResponse response, Department2 deptv) throws Exception {
		codSet(response);
		response.setContentType("text/html");

		if (deptv.getId() != null) {
			List list = departmentBo.find(deptv.getId()).getAllChildren();
			if (list.size() > 0) {
				response.getWriter().write("Exists children,can't delete!");
			} else {
				this.departmentBo.remove(deptv.getId());
				response.getWriter().write("true");
			}
		} else {
			response.getWriter().write("false");
		}

		return null;
	}

	/*
	 * find organization by code
	 */
	@RequestMapping(value = "/pages/system/queryOrganizationByCode2.do")
	public String queryOrganizationByCode2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		codSet(response);
		String serCode = request.getParameter("serCode");
		List<Organization2> orgs = this.organizationBo.getFindByNameOrCode(null,
				serCode, null);
		PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<Organization2>();
		datamodel.setRows(orgs);
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}
	@RequestMapping(value = "/uploadhome2.do")
	public String importCertificate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		defaultProcessFileUpload(request, response);
		if ("onerror".equals(request.getParameter("testcase"))) {
			throw new IOException();
		}

		return null;
	}
	
	public String getSavePath(HttpServletRequest request,String fileName) {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File floder = new File(realPath + "/images");
		return floder + "/"+fileName;
	}
	
	private void defaultProcessFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException, FileUploadException {
		ServletFileUpload upload = new ServletFileUpload();
		upload.setHeaderEncoding("UTF-8");
		InputStream stream = null;
		BufferedOutputStream bos = null;
		String fileUrl = "";
		byte[] fileBufTemp = new byte[1024];
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemIterator iter;
			iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				stream = item.openStream();
				if (!item.isFormField()) {
					String fileName = "home001.jpg"; //item.getName();
					String savePath=getSavePath(request,fileName);
					
					File file = new File(savePath);
					if (!file.exists()) {
						file.createNewFile();
					}
					bos = new BufferedOutputStream(new FileOutputStream(file));
					int length;
					while ((length = stream.read(fileBufTemp)) != -1) {
						bos.write(fileBufTemp, 0, length);
					}
					bos.flush();
					bos.close();
				
				}
			}
		}
		response.getWriter().write("true");
	}

	public void codSet(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setDateHeader("Expires", 0);
	}
}
