package cn.internalaudit.audit.controller;

import org.springframework.stereotype.Controller;

@Controller
public class DepartmentController {
	
//	@Resource(name = "OrganizationBo")
//	IOrganizationBo organizationBo;
//	@Resource(name = "DepartmentBo")
//	private IDepartmentBo departMentBo;
//	
//	@RequestMapping(value = "/pages/agency/getDepartMentData.do")
//	public String getDepartMentData(Locale locale, Model model,	HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		return null;
//	}
//	
//	@RequestMapping(value = "/pages/agency/getOrganization.do")
//	public String getOrganization(Locale locale, Model model,	HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		response.setHeader("Cache-Control","no-cache");
//		response.setHeader("Pragma","no-cache");
//		response.setDateHeader("Expires",0); 
//	//	response.setCharacterEncoding("UTF-8");
//		response.setHeader("Content-Type", "text/html");
//		PrintWriter writer = response.getWriter();
//
//		TreeModel treemodel=new TreeModel<Organization>();
//		
//		List<Organization> list=this.organizationBo.findByHeadquarters();
//		writer.write(treemodel.createTreeNodes(list).toString());
//		
//		writer.flush();
//
//		return null;
//	}
}
