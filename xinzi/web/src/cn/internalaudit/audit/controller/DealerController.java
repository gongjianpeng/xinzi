package cn.internalaudit.audit.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.internalaudit.audit.bo.IBrandBo;
import cn.internalaudit.audit.bo.ICompanyBo;
import cn.internalaudit.audit.bo.IDealerBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DealerController {
	@Resource(name = "DealerBo")
	IDealerBo dealerBo;
	
	
	@Resource(name = "CompanyBo")
	ICompanyBo companyBo;
	
	 @Resource(name = "BrandBo")
	    public IBrandBo brandBo;

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
	@RequestMapping(value = "/pages/company/getDealer2.do")
	public String getDealer2(HttpServletRequest request,
			HttpServletResponse response,Locale locale, Model model) throws Exception {

		codSet(response);
		HttpSession session=request.getSession();
//		String dename = request.getParameter("dename");
//		String type = request.getParameter("type");

		Person loginPerson = loginInfo.getLoginPerson();

		String orgid = loginPerson.getOrganizationId();
		int orgid2=Integer.parseInt(orgid);
		String loginpersonname = loginPerson.getName();
		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}

		DataModel datamodel = new DataModel<Dealer>();
		int end = start + limit;
		Map map = new HashMap();
		List<Dealer> list = null;
		List<Company> list2 = null;
	    // if (loginpersonname != "admin") {
			if (!Utils.nullOrEmpty(loginpersonname)) {
				String loginpersonname2 = new String(loginpersonname.getBytes("iso-8859-1"),
						"UTF-8");
				map.put("createPerson",loginpersonname2);
			}
			//	map.put("createPersonId", orgid2);
			
			//if (map.size() > 0) {
				list = dealerBo.findByParms2deng(map);
				int total = list.size();
//				end = end > total ? total : end;
//				if (start <= end) {
//					datamodel.setRows(list.subList(start, end));
//				}
				session.setAttribute("dealList2", list);
				model.addAttribute("dealList", list);
				
				
				if(total<1){
					
					list2 = companyBo.findByParms(map);
					session.setAttribute("dealList2", list2);
					model.addAttribute("dealList", list2);
					 total = list.size();
				}
//		
		
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
	

		return  "/home/companyvede";
	}

	
	@RequestMapping(value = "/pages/company/getDealer.do")
	public String getDealer(HttpServletRequest request,
			HttpServletResponse response,Locale locale, Model model) throws Exception {

		codSet(response);
		HttpSession session=request.getSession();
		String dename = request.getParameter("dename");
		String type = request.getParameter("type");

		Person loginPerson = loginInfo.getLoginPerson();

		String orgid = loginPerson.getOrganizationId();
		String loginpersonname = loginPerson.getName();
		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}

		DataModel datamodel = new DataModel<Dealer>();
		int end = start + limit;
		Map map = new HashMap();
		List<Dealer> list = null;
		if (loginpersonname.equals("admin")) {
			System.out.println("+++企业查询中 admin++" + loginpersonname);
			if (!Utils.nullOrEmpty(dename)) {
				String dename2 = new String(dename.getBytes("iso-8859-1"),
						"UTF-8");
				map.put("dename", "%" + dename2 + "%");
			}
			if (!Utils.nullOrEmpty(type)){
				map.put("type", "%" + type + "%");
			}
			
			list = dealerBo.findByParm(map);
			//list = dealerBo.findAll();
			
		} else if (loginpersonname != "admin") {
			if (!Utils.nullOrEmpty(dename)) {
				String dename2 = new String(dename.getBytes("iso-8859-1"),
						"UTF-8");
				map.put("dename", "%" + dename2 + "%");
			}
			if (!Utils.nullOrEmpty(type))
				map.put("type", "%" + type + "%");

			if (!Utils.nullOrEmpty(orgid))
				map.put("org", orgid);

			if (map.size() > 0) {
				list = dealerBo.findByParm(map);
			} else if (map.size() > 1) {
				list = dealerBo.findByParm(map);
			}
		}
		int total = list.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
		session.setAttribute("dealList", list);
		
		model.addAttribute(list);
		datamodel.setTotal(total);
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}
	

	 
	@RequestMapping(value = "/pages/company/dealerOperate.do")
	public String dealerOperate(@RequestParam MultipartFile filevideo,
			@RequestParam MultipartFile filepicturebook, Locale locale,
			Model model, HttpServletRequest request,
			HttpServletResponse response, Dealer entity) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		Person loginPerson = loginInfo.getLoginPerson();
		String loginper = loginPerson.getName();

		String orgid = loginPerson.getOrganizationId();
		String orgname = loginPerson.getOrganizationName();
		System.out.println("DealerController系统登录用户为：" + loginper + "公司名称为"
				+ orgname);

		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {

			/*
			 * //add by gongjianpeng begin System.out.println("上传文件开始"); String
			 * path =
			 * request.getSession().getServletContext().getRealPath("upload");
			 * String fileNameVideo = filevideo.getOriginalFilename(); String
			 * fileNamePic = filepicturebook.getOriginalFilename();
			 * 
			 * System.out.println(path);
			 * System.out.println(fileNameVideo+"////"+fileNamePic); File
			 * targetFileVideo = new File(path, fileNameVideo); File
			 * targetFilePic = new File(path, fileNamePic);
			 * if(!targetFileVideo.exists()){ targetFileVideo.mkdirs(); }
			 * System.out.println("lllllljjjjjjjj" +fileNameVideo);
			 * System.out.println("1111111" +filevideo.getSize()); //保存 try {
			 * System.out.println(filevideo.toString());
			 * 
			 * filevideo.transferTo(targetFileVideo);
			 * filepicturebook.transferTo(targetFilePic); } catch (Exception e)
			 * { e.printStackTrace(); } //model.addAttribute("fileUrl",
			 * request.getContextPath()+"/upload/"+fileName);
			 * System.out.println("5555555555" +filevideo.getSize());
			 * entity.setPicturebook("/upload/"+fileNamePic);
			 * entity.setVideo("/upload/"+fileNameVideo);
			 */
			// 文件结束
			// add by gongjianpeng end

			 
			entity.setPicturebook(this.saveFile(filepicturebook, request));
			entity.setVideo(this.saveFile(filevideo,request));

			entity.setOrg(orgid);
			dealerBo.save(entity);
			response.getWriter().write("true");
		} else if (UPDATE.equals(operation)) {
			entity.setOrg(orgid);

			/*// add by gongjianpeng begin
			System.out.println("上传文件开始");

			if (filevideo != null && filevideo.getSize() != 0) {

				String pathvideo = request.getSession().getServletContext()
						.getRealPath(entity.getVideo());
				deleteFolder(pathvideo);

				String path = request.getSession().getServletContext()
						.getRealPath("upload");

				String fileNameVideo = filevideo.getOriginalFilename();
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + fileNameVideo);

				System.out.println(path);
				File targetFileVideo = new File(path, fileNameVideo);

				System.out.println("@@@@@@@@@@@@@@@@@@@@@@"
						+ targetFileVideo.getPath());

				if (!targetFileVideo.exists()) {
					targetFileVideo.mkdirs();
				}
				System.out.println("1111111" + filevideo.getSize());
				// 保存
				try {
					System.out.println(filevideo.toString());

					filevideo.transferTo(targetFileVideo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// model.addAttribute("fileUrl",
				// request.getContextPath()+"/upload/"+fileName);
				System.out.println("5555555555" + filevideo.getSize());
				entity.setVideo("/upload/" + fileNameVideo);
				// 文件结束
			}

			// add by gongjianpeng end
*/
			if (filevideo != null && filevideo.getSize() != 0) {
				this.deleteFile(entity.getVideo(), request);
				entity.setVideo(this.saveFile(filevideo,request));
			}
			if (filepicturebook != null && filepicturebook.getSize() != 0) {
				this.deleteFile(entity.getPicturebook(), request);
 				entity.setPicturebook(this.saveFile(filepicturebook, request));
				
			}
			
			dealerBo.update(entity);
			
			response.setCharacterEncoding("utf-8");

			response.setContentType("text/json; charset=UTF-8");
			response.getWriter().write("true");

		} else if (DELETE.equals(operation)) {
			
			this.deleteFile(entity.getPicturebook(), request);
			this.deleteFile(entity.getVideo(), request);
			
			dealerBo.delete(entity);
			
			
			response.setCharacterEncoding("utf-8");

			response.setContentType("text/json; charset=UTF-8");
			response.getWriter().write("true");
		}
		writer.flush();
		return null;
	}
	
	
	@RequestMapping(value = "/pages/company/dealtwoOperate.do")
	public String dealtwoOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Dealer entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		Person loginPerson = loginInfo.getLoginPerson();
		String loginper = loginPerson.getName();

		String orgid = loginPerson.getOrganizationId();
		String orgname = loginPerson.getOrganizationName();
		System.out.println("DealerController系统登录用户为：" + loginper + "公司名称为"
				+ orgname);


		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			entity.setOrg(orgid);
			dealerBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			entity.setOrg(orgid);
			dealerBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			dealerBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	public String saveFile(MultipartFile file, HttpServletRequest request) {
		
		if (file != null && file.getSize() != 0) {

			String path = request.getSession().getServletContext().getRealPath(
					"upload");

			String fileName = file.getOriginalFilename();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + fileName);

			System.out.println(path);
			File targetFile = new File(path, fileName);

			System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + targetFile.getPath());

			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			System.out.println("1111111" + file.getSize());
			// 保存
			try {
				System.out.println(file.toString());

				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// model.addAttribute("fileUrl",
			// request.getContextPath()+"/upload/"+fileName);
			System.out.println("5555555555" + file.getSize());

			return "/upload/" + fileName;
		}
		return "";
	}

	public boolean deleteFile(String Path, HttpServletRequest request) {
		String spath = request.getSession().getServletContext().getRealPath(
				Path);
		boolean flag = false;
		File file = new File(spath);
		// 判断目录或文件是否存在
		if (file.exists()) {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				file.delete();
				flag = true;

			}
		}
		return false;
	}

	
	
	public void codSet(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setDateHeader("Expires", 0);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param locale
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pages/company/qiyeDisplay.do")
	public String getqiyeDisplay(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		Person loginPerson = loginInfo.getLoginPerson();
		String orgid = loginPerson.getOrganizationId();
		String  pingtaitype=loginPerson.getRemark();
		// 如果是企业用户登录了系统，这里返回 企业自己的展示信息。
		Dealer dealer=null;
		
		
		
		
		if(pingtaitype=="经销商管理平台"||pingtaitype.equals("经销商管理平台")){
			
			// 如果是jxs登录系统后，选择品牌， 显示这个品牌所在 企业的信息。
			Object pinname=session.getAttribute("brandName");
			System.out.println("======Dealer中=="+pinname);
		       String basepinpai=(String) pinname;
			// 获取品牌 ，这里默认一个品牌
			//String  basepinpai="美心门";
			Brand brand=brandBo.findBrandByName(basepinpai);
			String orgid2=brand.getOrg();
		     
			
		
			// 根据品牌获取到所在机构
			 dealer=dealerBo.findOrgid(orgid2);
			
			// 根据机构获得orgid 
		      String qiyeDetail=dealer.getIntro();
				model.addAttribute("dealer",dealer);
			
			//返回数据
		
			
		

		}else{
			
			//if(pingtaitype=="企业管理平台"){
				
				 dealer=dealerBo.findOrgid(orgid);
				String qiyeDetail=dealer.getIntro();
				model.addAttribute("dealer",dealer);
				//return"/home/company";
			//}
		//	else
		}
		return"/home/company";
		
	}
	
		
		
		
	
}
