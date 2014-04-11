package cn.internalaudit.audit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.RandomUtils;
import org.operamasks.faces.annotation.SaveState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.bo.IBrandBo;
import cn.internalaudit.audit.bo.ICompanyBo;
import cn.internalaudit.audit.bo.ICompanyqiyeBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.entitys.base.Companyqiye;
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.pojo.Commodity;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * 企业品牌--控制层
 * <功能详细描述>
 * 
 * @author  make
 * @version  [Webset V100R002C01, 2013-12-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
public class BrandController
{
    
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    @Resource(name = "BrandBo")
    public IBrandBo brandBo;
    @Resource(name = "CompanyBo")
	ICompanyBo companyBo;
    
    @Resource(name = "CompanyqiyeBo")
	ICompanyqiyeBo companyqiyeBo;
    @Resource
	private LoginInfo loginInfo;
    

    	
    @RequestMapping(value = "/pindex.do")
    public String pindex(Locale locale, Model model,HttpSession session,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        //获取查询条件--品牌名称
        String brandName = request.getParameter("pinName");
        request.setAttribute("brandName", brandName);
		session.setAttribute("brandName", brandName);
    	model.addAttribute("brandName2", brandName);
	 
		return "/home/index2";
    }
    
    	
    	/**
    	 * 页面第一步 中加载品牌 数据
    	 * @param locale
    	 * @param model
    	 * @param request
    	 * @param response
    	 * @return
    	 * 1 如果是经销商 ，则去查询经销商中的数据品牌
    	 * 2，如果是 企业，则查询 自己企业的数据品牌
    	 * @throws Exception
    	 */
    	@RequestMapping(value = "/home/getLoginPersonBrand.do")
        public String getLoginPersonBrand(Locale locale, Model model,  HttpServletRequest request, HttpServletResponse response,HttpSession session)
                throws Exception {
            codSet(response);
    		Map map = new HashMap();
    		
    	 
    		
    		List<Brand> lists=null;
    		List listjxs=null;
    		Brand brand=new Brand();
    		
    		Person loginPerson = loginInfo.getLoginPerson();
    		String orgid = loginPerson.getOrganizationId();
    		String  pingtaitype=loginPerson.getRemark();
    		// 如果是企业用户登录了系统，这里返回 企业自己的展示信息。
    		Dealer dealer=null;
    		String companyName2=loginPerson.getOrganizationName();
    		
    		String companyName=new String(companyName2.getBytes( "iso-8859-1"),"UTF-8");
    		List<Companyqiye> listcomqiye = null;
    		if(pingtaitype=="经销商管理平台"||pingtaitype.equals("经销商管理平台")){
    			map.put("companyname", companyName);
    			listcomqiye=companyqiyeBo.findByParms(map);
    			StringBuilder  sb=null;
    			String sb2=null;
    			if(listcomqiye.size()>0){
    				String pinpaiName=null;
    				
    				sb = new StringBuilder();
			    	for (int i = 0; i < listcomqiye.size(); i++) {
						 pinpaiName=listcomqiye.get(i).getPinpai();
						 sb.append(pinpaiName);
						
						
					}
			    	if(sb!=null||!Utils.nullOrEmpty(sb.toString())||!"".equals(sb)){
			    //		listjxs.add(sb);
			    		 sb2=sb.toString();
		    			String s1[]=sb2.split(",  ");
		    			for(int x=0;x<s1.length;x++){
		    				String s2[]=s1[x].split(",");
		    				System.out.println(s2[0]);
		    				String ss=s2[0];
		    				System.out.println(ss);
		    			//	 brand.setName(s2[0]);
		    			}
			    	}
			    	
			    	System.out.println("经销商登录后查询到 的品牌为"+pinpaiName+"。。。。。"+sb);
			    
    			}
    			
    	
				//lists.add(brand);
    		//	lists.add(sb);
    		// 可能多个企业  授权 品牌给经销商 ，那么 list  接收，  接着循环出list中对应每个企业的品牌数据， 
    			// 品牌数据接着    分开，插入 新的list   返回数据。  这里是返回
    		//	session.setAttribute(, arg1);  你把数据都放到 request对象里面了 js获取不了，用
    			 request.setAttribute("sb2", sb2);
    			 response.getWriter().print(sb2);
    				
    	        	model.addAttribute("sb2", sb2);
    	    		 
    	    		return "/home/index2";
    			
    		}
      else{
    	//  企业登录 admin 是这里
    	  if (!Utils.nullOrEmpty(orgid)){
				map.put("org", orgid);}
    			lists = brandBo.findByParms(map);
      }
    		
    	    
            request.setAttribute("searchproductList", lists);
            response.getWriter().print(lists.toString());
        	model.addAttribute("searchproductList2", lists);
    		 
    		return "/home/index2";
        }
    
    /**
     * 
     *1： 显示企业自己所有品牌信息
     *2：根据品牌名称查询
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/pages/company/getBrands.do")
    public String getBrands(Locale locale, Model model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        //获取查询条件--品牌名称
        String brandName = request.getParameter("brandName");
        System.out.println("brandName"+brandName);
        //获取登录ID
    	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
	 
        //分页
        String startStr = request.getParameter("start");
        String limitStr = request.getParameter("limit");
        int start = Integer.parseInt(startStr);
        int limit = Integer.parseInt(limitStr);
        if (limit == 0) {
            limit = Integer.MAX_VALUE;
        }
        PrintWriter writer = response.getWriter();
        DataModel datamodel = new DataModel<Brand>();
        int end = start + limit;
        Map map = new HashMap();
        String name=null;
        if (!Utils.nullOrEmpty(brandName)){
             name=new String(brandName.getBytes( "iso-8859-1"),"UTF-8");
            map.put("name", "'"+name+"'");//
        }
        List<Brand> list = null;
        
        if (!Utils.nullOrEmpty(orgid)){
			map.put("org", orgid);
        }
        
		
//		if(brandName==null||brandName.equals("")) {
//			  System.out.println("brandName2"+name);
//			 
//			list = brandBo.findAll();
//		}else{
//			String name2=null;
//			
//			  if (!Utils.nullOrEmpty(brandName)){
//		             name2=new String(brandName.getBytes( "iso-8859-1"),"UTF-8");
//		           
//		        }
			list = brandBo.findByParms(map);
			
//		}
        
        int total = list.size();
        end = end > total ? total : end;
        if (start <= end) {
            datamodel.setRows(list.subList(start, end));
        }
        datamodel.setTotal(total);
        writer.write(JSONObject.fromObject(datamodel).toString());
        writer.flush();

        return null;
    }
    
    
    /**
     * 
     *1： 显示企业自己所有品牌信息
     *2：根据品牌名称查询
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/pages/company/getBrands2.do")
    public String getBrands2(Locale locale, Model model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        //获取查询条件--品牌名称
        String brandName = request.getParameter("brandName");
        System.out.println("brandName"+brandName);
        //获取登录ID
    	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
	 
        //分页
        String startStr = request.getParameter("start");
        String limitStr = request.getParameter("limit");
        int start = Integer.parseInt(startStr);
        int limit = Integer.parseInt(limitStr);
        if (limit == 0) {
            limit = Integer.MAX_VALUE;
        }
        PrintWriter writer = response.getWriter();
        DataModel datamodel = new DataModel<Brand>();
        int end = start + limit;
        Map map = new HashMap();
        String name=null;
        if (!Utils.nullOrEmpty(brandName)){
             name=new String(brandName.getBytes( "iso-8859-1"),"UTF-8");
            map.put("name", "'"+name+"'");//
        }
        List<Brand> list = null;
        
        if (!Utils.nullOrEmpty(orgid)){
			map.put("org", orgid);
        }
        
		
//		if(brandName==null||brandName.equals("")) {
//			  System.out.println("brandName2"+name);
//			 
//			list = brandBo.findAll();
//		}else{
//			String name2=null;
//			
//			  if (!Utils.nullOrEmpty(brandName)){
//		             name2=new String(brandName.getBytes( "iso-8859-1"),"UTF-8");
//		           
//		        }
			list = brandBo.findByParms(map);
			
//		}
        
        int total = list.size();
        end = end > total ? total : end;
        if (start <= end) {
            datamodel.setRows(list.subList(start, end));
        }
        datamodel.setTotal(total);
        writer.write(JSONObject.fromObject(datamodel).toString());
        writer.flush();

        return null;
    }
    
    
    
    @RequestMapping(value = "/pages/company/getBrandbyName.do")
    public String getBrandbyName(Locale locale, Model model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        //获取查询条件--品牌名称
        String brandName = request.getParameter("brandName");
        System.out.println("brandName"+brandName);
        //获取登录ID
    	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
	 
        //分页
        String startStr = request.getParameter("start");
        String limitStr = request.getParameter("limit");
        int start = Integer.parseInt(startStr);
        int limit = Integer.parseInt(limitStr);
        if (limit == 0) {
            limit = Integer.MAX_VALUE;
        }
        PrintWriter writer = response.getWriter();
        DataModel datamodel = new DataModel<Brand>();
        int end = start + limit;
       
        String dname=null;
        List<Brand> list = null;
        
        
     
        
        if (!Utils.nullOrEmpty(brandName)){
             dname=new String(brandName.getBytes( "iso-8859-1"),"UTF-8");
          System.out.println("dname..."+dname);
  		  list = (List<Brand>) brandBo.findBrandByName(dname);
        }else{
        	
        	list = brandBo.findAll();
        }
     

		
        
        int total = list.size();
        end = end > total ? total : end;
        if (start <= end) {
            datamodel.setRows(list.subList(start, end));
        }
        datamodel.setTotal(total);
        writer.write(JSONObject.fromObject(datamodel).toString());
        writer.flush();

        return null;
    }
    
    @RequestMapping(value = "/pages/company/saveBrand.do")
    public String saveBrand(HttpServletRequest request,
            HttpServletResponse response, Brand vperson) throws Exception {

        codSet(response);
        //获取登录ID
     	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
        
        //获取从页面传过来的operation参数，选择执行哪个功能
        String operation = request.getParameter("operation");
        PrintWriter writer = response.getWriter();
        if (ADD.equals(operation)) {
        	vperson.setOrg(orgid);
        	String type="type";
        	vperson.setType(type);
        	String readname=request.getParameter("name");
        
        	vperson.setReadname(readname);
            brandBo.save(vperson);
            writer.write("true");
        } else if (UPDATE.equals(operation)) {
            vperson.setOrg(orgid);
            brandBo.update(vperson);
            writer.write("true");
        } else if (DELETE.equals(operation)) {
            brandBo.delete(vperson);
            writer.write("true");
        }
        writer.flush();
        return null;
    }
    
    /****
    @RequestMapping(value = "/pages/company/getPinpaiComboData.do")
	public String getPinpaiComboData(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = "type";
		System.out.println("控制层中:"+type);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
		List<Brand> list = null;
		   Person loginPerson = loginInfo.getLoginPerson();
	        String	orgid=loginPerson.getOrganizationId();
		 
		Map map = new HashMap();
		map.put("type", type);
		map.put("org", orgid);
		list = brandBo.findByParms(map);
	//	list = brandBo.findBrandByName(type);
		if(list.size()>0){
							
		}
		for (Iterator i = list.iterator(); i.hasNext();)  
			 
		PrintWriter writer = response.getWriter();

		if (null != list && list.size() > 0) {
			DataModel dataModel = new DataModel<BaseEntity>();
			writer.write(dataModel.setRows(list).toString());
		} else {
			writer.write("[{'text':'','value':''}]");
		}
		writer.flush();
		return null;
	}
    **/
/*********上传  start***********************************************************************************************************************************/
 // 上传文件的保存路径，相对于应用的根目录
    private static final String UPLOAD_PIC_PATH = "/fileupload/brand/";

    byte[] imgBufTemp = new byte[1024];

    private ServletContext servletContext;
    
    
    
    /***
     * 上传文件
     */
    @RequestMapping(value = "/pages/company/uploadbrand.do")
    public String importCertificate(HttpServletRequest request,
            HttpServletResponse response, Brand vperson) throws Exception {
        servletContext = request.getSession().getServletContext();
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/html;charset=UTF-8");
        defaultProcessFileUpload(request, response);
        if ("onerror".equals(request.getParameter("testcase"))) {
            throw new IOException();
        }

        return null;
    }

 
    /**
     * 生成保存上传文件的磁盘路径
     * 
     * @param fileName
     * @return
     */
    public String getSavePath(String fileName) {
        String realPath = servletContext.getRealPath("/");
        File floder = new File(realPath + UPLOAD_PIC_PATH);
        if (!floder.exists()) {
            floder.mkdirs();
        }
        String timeFloder = fileName.substring(0,fileName.lastIndexOf("/"));
        File timeF = new File(floder.getPath()+"/"+timeFloder);
        if(!timeF.exists()){
            timeF.mkdirs();
        }
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        return realPath + UPLOAD_PIC_PATH + timeFloder+"/"+ fileName;
    }

    /**
     * 根据文件URl获取的磁盘真实路径 *
     * 
     * @param fileUrl
     * @return
     */
    public String getRealPath(String fileUrl) {
        String realPath = servletContext.getRealPath("/");
        return realPath + fileUrl;
    }

    /**
     * 生成访问上传成功后的文件的URL地址
     * 
     * @param fileName
     * @return
     */
    public String getFileUrl(String fileName) {
        return servletContext.getContextPath() + UPLOAD_PIC_PATH + fileName;
    }
    
    @SaveState
    private String brandId = "";

    private void defaultProcessFileUpload(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        ServletFileUpload upload = new ServletFileUpload();
        upload.setHeaderEncoding("UTF-8");
        brandId = request.getParameter("ID");
      
        InputStream stream = null;
        BufferedOutputStream bos = null;
        String fileUrl = "";
        if (!Utils.nullOrEmpty(brandId)) {
            try {
                if (ServletFileUpload.isMultipartContent(request)) {
                    FileItemIterator iter;
                    iter = upload.getItemIterator(request);
                    int i = 0;
                    while (iter.hasNext()) {
                        FileItemStream item = iter.next();
                        stream = item.openStream();
                        if (!item.isFormField()) {
                            String prefix = new java.text.SimpleDateFormat(
                                    "yyyyMMddHHmmss").format(new Date())
                                    + "-" + RandomUtils.nextInt();
                            // 得到文件的扩展名(无扩展名时将得到全名)
                            String ext = item.getName().substring(
                                    item.getName().lastIndexOf(".") + 1);
                            String fileName = prefix+"/"+item.getName();
                            //String fileName = prefix + "." + ext;
                            String savePath = getSavePath(fileName);
                            if (i > 0) {
                                fileUrl += "$";
                            }
                            fileUrl += getFileUrl(fileName);
                            File file = new File(savePath);
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            bos = new BufferedOutputStream(
                                    new FileOutputStream(file));
                            int length;
                            while ((length = stream.read(imgBufTemp)) != -1) {
                                bos.write(imgBufTemp, 0, length);
                            }
                            bos.flush();
                            bos.close();
                            /******************************************************************/
                            Brand brand=this.brandBo.find(Long.valueOf(brandId));
                           // RiskPaper riskPaper = this.riskPaperBo.find(Long.valueOf(brandId));
                            if (brand != null) {
                                String updateUrl = UPLOAD_PIC_PATH + fileName;
                                /*if (!Utils.nullOrEmpty(brand.getFileUrl())) {
                                    updateUrl = brand.getFileUrl() + "$"+ UPLOAD_PIC_PATH + fileName;
                                } else {
                                    updateUrl = UPLOAD_PIC_PATH + fileName;
                                }*/
                                System.out.println(brand.getFileUrl()+"==================");
                                brand.setFileUrl(updateUrl);
                                brand = this.brandBo.update(brand);
                            }
                            
                            /**********************************/
                            i++;
                            StringBuilder json = new StringBuilder();
                            json.append("{");
                            json.append("'");
                            json.append("fileUrl");
                            json.append("':'");
                            json.append(brand.getFileUrl());
                            json.append("'");
                            json.append("}");
                            response.getWriter().write(json.toString());
                        }
                    }
                }
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
/***************上传 end***********************************************************************************************************/    
    public void codSet(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setDateHeader("Expires", 0);
    }
}
