package cn.internalaudit.audit.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.internalaudit.audit.bo.IDepartment2Bo;
import cn.internalaudit.audit.bo.impl.Organization2Bo;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.DiyModel;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.CreateGUID;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

@Controller
public class Department2Controller {

	@Resource(name = "Organization2Bo")
	Organization2Bo organizationBo;
	@Resource(name = "Department2Bo")
	private IDepartment2Bo departmentBo;
	
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	@Resource
	private LoginInfo loginInfo;

	//	
	// @RequestMapping(value = "/pages/agency/getDepartMentData.do")
	// public String getDepartMentData(Locale locale, Model model,
	// HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// return null;
	// }
	//	
	@RequestMapping(value = "/pages/company/getDiyEnddata.do")
	public String getDiyEnddata(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		// response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html");

		String companyqiyename = request.getParameter("companyqiyename");

		String code = request.getParameter("code");

		Person loginPerson = loginInfo.getLoginPerson();
		String orgid = loginPerson.getOrganizationId();

		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}

		DataModel datamodel = new DataModel<Department2>();
		int end = start + limit;
		Map map = new HashMap();
		if (!Utils.nullOrEmpty(companyqiyename)) {
			String companyqiyename2 = new String(companyqiyename
					.getBytes("iso-8859-1"), "UTF-8");
			map.put("companyname", "'" + companyqiyename2 + "'");
		}
		if (!Utils.nullOrEmpty(code))
			map.put("code", "'" + code + "'");
		List<Department2> list = null;
		if (!Utils.nullOrEmpty(orgid))
			map.put("org", orgid);

		// if (map.size()>0) {
		// list = departmentBo.findBykuanRemark(map);
		// } else if(map.size()>1) {
		list = departmentBo.findAll();
		// }
		int total = list.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
		datamodel.setTotal(total);
		response.setCharacterEncoding("utf-8");

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;

	}
	
	
		
		@RequestMapping(value = "/pages/company/getDiybyNameQuery.do")
		public String getDiybyNameQuery(Locale locale, Model model,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {

			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			// response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Type", "text/html");

			String qName = request.getParameter("qName");



			Person loginPerson = loginInfo.getLoginPerson();
			String orgid = loginPerson.getOrganizationId();

			String bid = request.getParameter("bid");
			String startStr = request.getParameter("start");
			String limitStr = request.getParameter("limit");
			int start = Integer.parseInt(startStr);
			int limit = Integer.parseInt(limitStr);
			if (limit == 0) {
				limit = Integer.MAX_VALUE;
			}

			DataModel datamodel = new DataModel<Department2>();
			int end = start + limit;
			Map map = new HashMap();
			if (!Utils.nullOrEmpty(qName)) {
				String qName2 = new String(qName
						.getBytes("iso-8859-1"), "UTF-8");
				map.put("name", "'" + qName2 + "'");
			}
			
			List<Department2> list = null;
			if (!Utils.nullOrEmpty(orgid)){
				map.put("org", orgid);
			}
			// if (map.size()>0) {
			// list = departmentBo.findBykuanRemark(map);
			// } else if(map.size()>1) {
			list = departmentBo.findByParms(map);
			// }
			int total = list.size();
			end = end > total ? total : end;
			if (start <= end) {
				datamodel.setRows(list.subList(start, end));
			}
			datamodel.setTotal(total);
			response.setCharacterEncoding("utf-8");

			response.setContentType("text/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.fromObject(datamodel).toString());
			writer.flush();
			return null;

		}
	

	@RequestMapping(value = "/pages/company/getDiybyid.do")
	public String getDiybyid(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html");

		String bid = request.getParameter("bid");
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}

		DataModel datamodel = new DataModel<Department2>();
		int end = start + limit;
		Map map = new HashMap();
		List<Department2> list = null;
		list = departmentBo.find(bid);
		int total = list.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
		datamodel.setTotal(total);
		response.setCharacterEncoding("utf-8");

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;

	}

	@RequestMapping(value = "/pages/company/diyurlDataOperate.do")
	public String diyurlDataOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Department2 entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		Person loginPerson = loginInfo.getLoginPerson();
		String orgid = loginPerson.getOrganizationId();

		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			entity.setOrg(orgid);
			departmentBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			entity.setOrg(orgid);
			departmentBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			departmentBo.delete(entity);
			writer.write("true");
		}

		writer.flush();
		return null;
	}

	/************************** 右边输入框 中： 点击确定后 根据款式 去查询 ********************/
	@RequestMapping(value = "/pages/company/getDiyRightData.do")
	public void getDiyRightData(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DiyModel diyModel) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String callback = request.getParameter("callback");

		String inputname = request.getParameter("inputname"); // 选择是 用户输入进去的款式
		System.out.println(">>" + inputname);
		String flow = request.getParameter("flow");

		JSONArray resultleft = new JSONArray();
		JSONObject ret = new JSONObject();
		JSONArray arrayya12 = new JSONArray();
		// JSONArray arrayya12=new JSONArray();
		HashMap parms = new HashMap();
		if (!Utils.nullOrEmpty(inputname)) {

			String inputname2 = new String(inputname.getBytes("iso-8859-1"),
					"UTF-8");
			parms.put("inputname", "%" + inputname2 + "%");
		}
		List<Department2> listleft = departmentBo.findBykuanRemark(parms);
		// findDiyModelByType(codetype);//organization2Bo.findByName("气窗");
		String name = null;
		String img = null;
		String scale = null;
		String x = null;
		String y = null;
		String type = null;
		String typename = null;
		List l = new ArrayList();
		for (int k = 0; k < listleft.size(); k++) {
			Long id = listleft.get(k).getId();
			name = listleft.get(k).getName();
			img = listleft.get(k).getAddress();

			typename = listleft.get(k).getRemark();

			System.out.println("one _" + id + "name_" + name + "img_" + img);

			arrayya12.add(buildjsonDia(id, name, img, typename));
		}
		System.out.println(" two " + l);
		resultleft.add(buildjsonDiarray("", "name", arrayya12));

		System.out.println("^" + resultleft);
		response.setCharacterEncoding("utf-8");

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		// ret.put("data1", bu.toString());
		ret.put("data2", resultleft.toString());
		// writer.write( ret.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	
	 
	/**
	 * 图片上传
	 * @param file
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/pages/company/upload.do")
	public void uploadImages(@RequestParam(value = "file", required = false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
			String recordId=request.getParameter("recordId");
			//String fileName = request.getParameter("file");
			JSONObject rest=new JSONObject();
			try {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				//ModelAndView mav =new ModelAndView();  
				String path = request.getSession().getServletContext().getRealPath("upload/images");  
				String fileName = file.getOriginalFilename();  
				System.out.println(path);  
				int i=fileName.lastIndexOf(".");
				fileName=sdf.format(new Date())+fileName.substring(i, i+4);
				
				File targetFile = new File(path, fileName);  
				if(!targetFile.exists()){  
				    targetFile.mkdirs();  
				}  
				
				file.transferTo(targetFile);  
				rest.put("result", "1");
				response.getWriter().write(rest.toString());
		      //model.addAttribute("fileUrl", request.getContextPath()+"/uploadImages/"+fileName);  
		      //mav.addObject("fileUrl", request.getContextPath()+"/upload/images/"+fileName);
		      //mav.setViewName("uploadSuccess");
			 //return mav;
			} catch (IOException e) {
				rest.put("result", "0");
				try {
					response.getWriter().write(rest.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
	
	
	public static void main(String[] args) {
		CreateGUID.createGuId();
		String image="梁继龙.png";
		int i=image.lastIndexOf(".");
		
		String a=CreateGUID.createGuId()+image.substring(i, i+4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String b=sdf.format(new Date())+image.substring(i, i+4);
		System.out.println(b);
		
	}

	
	
	private JSONObject buildjsonDia(Long id, String name, String img,
			String typename) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("typename", typename);

		return result;
	}

	private JSONObject buildjsonDiarray(String id, String name, List l) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", l);

		return result;
	}

	private JSONObject buildjsonlist(Long id, String name, JSONObject listto) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", listto);

		return result;
	}

	private JSONObject buildjsonlistleft(Long id, String name, String img,
			String type, String x, String y) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("type", type);
		result.put("x", x);
		result.put("y", y);

		return result;
	}

	private JSONObject buildjsonlistdan(Long id, String name, String img,
			String type) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("type", type);

		return result;
	}

	@RequestMapping(value = "/pages/company/getRiskdata.do")
	public String getRiskdata(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long pid) throws Exception {

		List<Department2> list = (List<Department2>) departmentBo.find(pid);
		List<Department2> disList = new ArrayList<Department2>();
		for (Department2 rs : list) {
			// if((Utils.nullOrEmpty(status) || status.contains(rs.getStatus()))
			// &&(defectGrade==null||defectGrade.contains(rs.getDefectGrade().equals("")?"-":rs.getDefectGrade()))){
			disList.add(rs);

			// }

		}
		DataModel dataModel = new DataModel<Department2>();
		dataModel.setRows(disList);
		Utils.setHeader(response);
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(dataModel).toString());
		writer.flush();
		return null;
	}

	// @SuppressWarnings("unchecked")
	// public void doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	//
	// response.setContentType("text/html;charset=utf-8;");
	// request.setCharacterEncoding("utf-8");
	// PrintWriter out = response.getWriter();
	// // 设置接收的编码格式
	// request.setCharacterEncoding("UTF-8");
	// Date date = new Date();// 获取当前时间
	// SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");
	// SimpleDateFormat sdfFolder = new SimpleDateFormat("yyMM");
	// String newfileName = sdfFileName.format(date);// 文件名称
	// String fileRealPath = "";// 文件存放真实地址
	//
	// String fileRealResistPath = "";// 文件存放真实相对路径
	//
	// // 名称 界面编码 必须 和request 保存一致..否则乱码
	// String name = request.getParameter("name");
	// String id= request.getParameter("ID");
	// System.out.println("------"+id);
	//
	// String firstFileName = "";
	// // 获得容器中上传文件夹所在的物理路径
	// String savePath = this.getServletConfig().getServletContext()
	// .getRealPath("/") + "upload\\" + newfileName + "\\";
	// System.out.println("路径" + savePath + "; name:" + name);

	// File file = new File(savePath);
	// if (!file.isDirectory()) {
	// file.mkdirs();
	// }
	//
	// try {
	// DiskFileItemFactory fac = new DiskFileItemFactory();
	// ServletFileUpload upload = new ServletFileUpload(fac);
	// upload.setHeaderEncoding("UTF-8");
	// // 获取多个上传文件
	// List fileList = fileList = upload.parseRequest(request);
	// // 遍历上传文件写入磁盘
	// Iterator it = fileList.iterator();
	// while (it.hasNext()) {
	// Object obit = it.next();
	// if (obit instanceof DiskFileItem) {
	// System.out.println("xxxxxxxxxxxxx");
	// DiskFileItem item = (DiskFileItem) obit;
	//
	// // 如果item是文件上传表单域
	// // 获得文件名及路径
	// String fileName = item.getName();
	// if (fileName != null) {
	// firstFileName = item.getName().substring(
	// item.getName().lastIndexOf("\\") + 1);
	// String formatName = firstFileName
	// .substring(firstFileName.lastIndexOf("."));// 获取文件后缀名
	// fileRealPath = savePath + newfileName + formatName;// 文件存放真实地址
	//
	// BufferedInputStream in = new BufferedInputStream(item
	// .getInputStream());// 获得文件输入流
	// BufferedOutputStream outStream = new BufferedOutputStream(
	// new FileOutputStream(new File(fileRealPath)));// 获得文件输出流
	// Streams.copy(in, outStream, true);// 开始把文件写到你指定的上传文件夹
	// // 上传成功，则插入数据库
	// if (new File(fileRealPath).exists()) {
	// // 虚拟路径赋值
	// fileRealResistPath = sdfFolder.format(date)
	// + "/"
	// + fileRealPath.substring(fileRealPath
	// .lastIndexOf("\\") + 1);
	// // 保存到数据库
	// System.out.println("保存到数据库:");
	// System.out.println("name:" + name);
	// System.out.println("虚拟路径:" + fileRealResistPath);
	// }
	//
	// }
	// }
	// }
	// } catch (org.apache.commons.fileupload.FileUploadException ex) {
	// ex.printStackTrace();
	// System.out.println("没有上传文件");
	// return;
	// }
	// response.getWriter().write("1");
	// out.flush();
	// out.close();
	//
	// }

}
