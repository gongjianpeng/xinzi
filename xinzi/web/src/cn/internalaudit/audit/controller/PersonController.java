package cn.internalaudit.audit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.LocalString;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.ValidateLength;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.bo.IAuthoritiesKeyBo;
import cn.internalaudit.audit.bo.ICompanyBo;
import cn.internalaudit.audit.bo.IDataDictionaryBo;
import cn.internalaudit.audit.bo.IDealerBo;
import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.bo.IRolesAuthoritiesBo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.bo.impl.DealerBo;
import cn.internalaudit.audit.entitys.BaseEntity;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PersonController {
    @Resource(name = "PersonBo")
    IPersonBo personBo;
    @Resource(name = "DataDictionaryBo")
    public IDataDictionaryBo dataDictionaryBo;
    @Resource(name = "OrganizationBo")
    public IOrganizationBo organizationBo;
    @Resource(name = "DepartmentBo")
    private IDepartmentBo departmentBo;
    @Resource(name = "DealerBo")//企业
    public IDealerBo dealerBo;
    @Resource(name = "CompanyBo")//经销商
    public ICompanyBo companyBo;
    @Resource(name = "RolesBo")
    private IRolesBo rolesBo;

    @Resource(name = "RolesAuthoritiesBo")
    private IRolesAuthoritiesBo rolesAuthoritiesBo;

    @Resource(name = "AuthoritiesKeyBo")
    private IAuthoritiesKeyBo authoritiesKeyBo;
    @Resource
    private LoginInfo loginInfo;    
    
    public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
    
    
    @LocalString(basename = "messages.messages_audthoize")
    private Map<String, String> messages;
    @Bind
    private String oldPassword;
    
    @Bind
    @ManagedProperty
    @ValidateLength(minimum=6,maximum=18)
    private String newPassword;
    
    @ManagedProperty("#{loginInfo.locale}")
    private Locale locale;
    
    @Bind
    @ManagedProperty
    @ValidateLength(minimum=6,maximum=18)
    private String repeatNewPassword;
    
    @ManagedProperty("#{loginInfo.loginPerson.loginName}")
    private String loginName;
    
    @ManagedProperty("#{loginInfo.loginPerson}")
    private Person person;
    
    @ManagedProperty("#{loginInfo.loginPerson.password}")
    private String loginPassword;
    
    
    
    /**********************变量*****************************/
    private static final String FROM = "a100120014@163.com";
    private static final String CHECK_CODE = "checkCode";
    

    /**
     * Simply selects the home view to render by returning its name.
     * 得到用户信息
     * @RequestParam("name") String name,
     * @throws Exception
     */
    @RequestMapping(value = "/pages/system/getPerson.do")
    public String getPerson(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        codSet(response);//转换显示层传过来的数据格式，防止存入数据库乱码
        String loginName = request.getParameter("loginName");
        String code = request.getParameter("code");
        
        Person loginPerson = loginInfo.getLoginPerson();
        String  loginper=loginPerson.getName();
       
    
        
        String orgid=loginPerson.getOrganizationId(); //机构id
        String orgname=loginPerson.getOrganizationName();//机构名称
        String bid = request.getParameter("bid");
        
        String startStr = request.getParameter("start");
        String limitStr = request.getParameter("limit");
        int start = Integer.parseInt(startStr);
        int limit = Integer.parseInt(limitStr);
        if (limit == 0) {
            limit = Integer.MAX_VALUE;
        }
        
        DataModel datamodel = new DataModel<Person>();
        int end = start + limit;
        
        List<Person> list = null;
        Map map = new HashMap();
        if (loginper.equals("admin")) {
        	if (!Utils.nullOrEmpty(loginName)){
                String loginName2=new String(loginName.getBytes( "iso-8859-1"),"UTF-8" );
                map.put("loginName", loginName2);
            }
          
            list = personBo.findByParm(map);
        }
        
        else{
            
           
            if (!Utils.nullOrEmpty(code)){
                String code2=new String(code.getBytes( "iso-8859-1"),"UTF-8" );
                map.put("name", code2);
            }
            if (!Utils.nullOrEmpty(loginName)){
                map.put("loginName", loginName);
            }
            if (!Utils.nullOrEmpty(orgid)){
                map.put("organizationid", orgid);
            }
        
        if (map.size()>0) {
            list = personBo.findByParm(map);
        }
        }
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

    @RequestMapping(value = "/pages/system/validePersonExsits.do")
    public String validePersonExsits(HttpServletRequest request,
            HttpServletResponse response, Person vperson) throws IOException {
        codSet(response);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        if (personBo.isAvailableByLoginName(vperson, vperson.getLoginName())) {
            writer.write("true");
        } else {
            writer.write("false");
        }
        writer.flush();
        return null;
    }
    
    
    	/********注册模块    Begin********************************************************************************************************/   
        /**
         * 验证注册填写的会员名（登录名）是否已经注册
         */
        @RequestMapping(value = "/validUserName.do")
        public void validUserName(HttpServletRequest request,  HttpServletResponse response, Person vperson){
        	PrintWriter out=null;
            try {
            	JSONObject jsonObj=new JSONObject();
            	out=response.getWriter();
				List<Person> list = null;
				String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");  //转换格式       
				list = personBo.validname(name); //注册填写的登陆账号，作为参数查询
				
				//如果查询结果不为空，返回true；否则返回false
				if(list.size()!=0){
					jsonObj.put("result", "true");
					out.write(jsonObj.toString());
				}else{
					jsonObj.put("result", "false");
					out.write(jsonObj.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        /**
         * 验证通过后，把注册信息添加到数据库
         * 
         * @param request
         * @param response
         * @param person
         * @param dealer
         * @param org
         * @param dep
         * @param comp
         * @param role
         */
        @RequestMapping(value = "/enterpriseRegister.do")
        public void enterpriseRegister(HttpServletRequest request, HttpServletResponse response, Person person ,Organization org,Department dep,Dealer dealer,Roles role)  {
        	
    	    codSet(response);
        	JSONObject jsonObj=new JSONObject();
            
            try {
				 String option = request.getParameter("option");
				
				// String company_name=new String(request.getParameter("company_name").getBytes("iso-8859-1"),"utf-8");
				 String company_name2 = request.getParameter("company_name");  //转换格式  
				
		
				 String com_businessNumber=request.getParameter("com_businessNumber");
				 String com_address2 = request.getParameter("com_address");  //转换格式          
				// String com_address=new String(request.getParameter("com_address").getBytes("iso-8859-1"),"utf-8");
				 String com_username=request.getParameter("com_username");
				 String com_email=request.getParameter("com_email");
				 String com_password=request.getParameter("com_password");
				 String com_answer=request.getParameter("com_answer");
				 String com_phone=request.getParameter("com_phone");
				 String com_fax=request.getParameter("com_fax");
				 
				 String captcha=request.getParameter("captcha");
				 
				 String code = request.getSession().getAttribute("captcha").toString();// session的验证码
					 
					
				if (captcha.equals(code)) {
					
					//机构
					org.setName(company_name2); // 乱码
					org.setAddress(com_address2);// 乱码
					org.setIsHeadquarters(true);
					organizationBo.save(org);
					if(option.equals("qiye")){                
					    //企业
						dealer.setType("企业管理平台");
						dealer.setOrg(org.getId().toString());
						dealer.setCompanyname(company_name2);
						dealer.setAddress(com_address2);
						dealer.setEmail(com_email);
						dealer.setChuanzhen(com_fax);
						dealer.setBusinessNumber(com_businessNumber);
					    dealerBo.save(dealer);
					    dep.setRemark("企业管理平台");
					    person.setRemark("企业管理平台");
					}
					//部门
					dep.setOrganization(org);
					
					dep.setAddress(com_address2); //  乱码
					dep.setName("部门");
					departmentBo.save(dep);
					//person
					
					person.setEnable(false);//账号是否经过审批。
					person.setTelphone(com_phone);
					person.setEmail(com_email);
					person.setDepartmentId(dep.getId().toString());
					person.setLoginName(com_username);
					person.setCode(com_username);
					person.setName(com_username);
					person.setAddress(com_address2);  // 乱码
					person.setDepartmentName("部门"); // 增加部门
					person.setPassword(Utils.encodeSHA(com_password, com_username));
					//person.setCreatePerson(createPerson);
//				
					// vperson.setOrganizationId("10");
					String ttid= org.getId().toString();    
					person.setOrganizationId(ttid);
					person.setOrganizationName(company_name2); //乱码
					//person.setEnable(true);
					person.setDepartment(dep);
					personBo.save(person);
					
					role = rolesBo.findRoleIdByName(person.getRemark());
					String roleid = role.getId().toString();
					
					// loginInfo 中增加数据
					loginInfo.setLoginPerson(person);
					
					
					if(role != null){
					    personBo.setRoles(person.getId(), roleid);
					}
					jsonObj.put("status", "success");
					jsonObj.put("msg", "注册成功!");
					
				}else{
					jsonObj.put("status", "failre");
					jsonObj.put("msg", "注册失败,验证码错误!");
				}
				response.getWriter().write(jsonObj.toString());
			
			} catch (IOException e) {
				jsonObj.put("result", "failre");
				try {
					response.getWriter().write(jsonObj.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
        }
        
        /***
         * 经销商
         * @param request
         * @param response
         * @param person
         * @param dealer
         * @param org
         * @param depart
         * @param company
         * @param role
         */
        @RequestMapping(value = "/dealerRegister.do")
        public void dealerRegister(HttpServletRequest request, HttpServletResponse response, Person person ,Company comp,Organization org,Department depart,Roles role)  {
            JSONObject jsonObj=new JSONObject();
            
            try {
				 String option = request.getParameter("option");
				
				 String d_company=request.getParameter("d_company");
				 String d_userName=request.getParameter("d_userName");
				 String d_email=request.getParameter("d_email");
				 String d_password=request.getParameter("d_password");
				 String d_answer=request.getParameter("d_answer");
				 String d_phone=request.getParameter("d_phone");
				 String d_address=request.getParameter("d_address");
				 String d_fax=request.getParameter("d_fax");
				 
				 String captcha=request.getParameter("d_captcha");
				 
				 String code = request.getSession().getAttribute("captcha").toString();// session的验证码
					 
					
				if (captcha.equals(code)) {
					//机构
					org.setName(d_company);
					org.setAddress(d_address);
					org.setIsHeadquarters(true);
					
					organizationBo.save(org);
					if(option.equals("jxs")){                
					    //经销商
						comp.setType("经销商管理平台");
						comp.setOrg(org.getId().toString());
						comp.setAddress(d_address);
						comp.setEmail(d_email);
						comp.setFax(d_fax);
						companyBo.save(comp);
						
					    depart.setRemark("经销商管理平台");
					    person.setRemark("经销商管理平台");
					}
					//部门
					depart.setOrganization(org);
					depart.setName("部门");
					departmentBo.save(depart);
					//person
					
					person.setEmail(d_email);
					person.setCode(d_company);
					person.setAddress(d_address);  // 乱码
					person.setDepartmentName("部门"); // 增加部门
					person.setName(d_userName);
					person.setEnable(false);//账号是否经过审批。
					person.setTelphone(d_phone);
					person.setDepartmentId(depart.getId().toString());
					person.setLoginName(d_userName);
					person.setPassword(Utils.encodeSHA(d_password, d_userName));
					String ttid= org.getId().toString();    
					person.setOrganizationId(ttid);
					person.setOrganizationName(d_company);
					person.setDepartmentName("部门"); // 增加部门
					person.setDepartment(depart);
					//person.setEnable(true); 这里先设置为未激活
					
					personBo.save(person);
					
					role = rolesBo.findRoleIdByName(person.getRemark());
					String roleid = role.getId().toString();
					
					if(role != null){
						System.out.println("----------------------------------------");
					    personBo.setRoles(person.getId(), roleid);
					}
					jsonObj.put("status", "success");
					jsonObj.put("msg", "注册成功!");
				}else{
					jsonObj.put("status", "failre");
					jsonObj.put("msg", "注册失败,验证码错误！");
				}
				response.getWriter().write(jsonObj.toString());
			
			} catch (IOException e) {
				jsonObj.put("result", "failre");
				try {
					response.getWriter().write(jsonObj.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
        }
        
	    /**
	     * 发送注册成功到邮箱
	     */
        public void sendAccountActivateEmail(Person person){
            Session session = getSession();
            MimeMessage message = new MimeMessage(session);
            try {
                message.setSubject("帐户激活邮件");
                message.setSentDate(new Date());
                message.setFrom(new InternetAddress(FROM));//FROM为发送邮件的邮箱，可以为平台的一个邮箱
                message.setRecipient(RecipientType.TO, new InternetAddress(person.getEmail()));
                message.setText("欢迎注册典尚diy系统，在本系统中您可以定制 您的产品，进行购买.");
                message.setContent("<a href='" + generateActivateLink(person)+"'>点击激活帐户</a>","text/html;charset=utf-8");
              
                // 发送邮件
                Transport.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * 发送注册失败到邮箱
         */
        public void sendAccountActivateEmailFail(Person person){
            Session session = getSession();
            MimeMessage message = new MimeMessage(session);
            try {
                message.setSubject("帐户提示邮件");
                message.setSentDate(new Date());
                message.setFrom(new InternetAddress(FROM));
                message.setRecipient(RecipientType.TO, new InternetAddress(person.getEmail()));
                message.setContent("对不起，您的注册信息没有通过运营商审核","text/html;charset=utf-8");
                // 发送邮件
                Transport.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * 生成帐户激活链接
         */
        public String generateActivateLink(Person person) {
           // Long aa = person.getId();
           // person = personBo.activity(aa);
            person.setActivated(verifyCheckcode(person));//改变激活状态
            person.setEnable(true);
            personBo.update(person);
            //
            return "http://aiyi.lunxuu.com:8080/xinzi/home/register_success.jsp";
        }
        /**
         * 验证校验码是否和注册时发送的验证码一致
         * @param user 要激活的帐户
         * @param checkcode 注册时发送的校验码
         * @return 如果一致返回true，否则返回false
         */
        public static boolean verifyCheckcode(Person person) {
            String checkCode = generateCheckcode(person);
            return generateCheckcode(person).equals(checkCode);
        }
        /**
         * 生成验证帐户的MD5校验码
         * @param user  要激活的帐户
         * @return 将用户名和密码组合后，通过md5加密后的16进制格式的字符串
         */
        public static String generateCheckcode(Person person) {
            String Name = person.getName();
            String randomCode = person.getRandomCode();
            return md5(Name + ":" + randomCode);
        }
        private static String md5(String string) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("md5");
                md.update(string.getBytes());
                byte[] md5Bytes = md.digest();
                return bytes2Hex(md5Bytes);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            
            return null;
        }
        private static String bytes2Hex(byte[] byteArray)
        {
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++)
            {
                if(byteArray[i] >= 0 && byteArray[i] < 16)
                {
                    strBuf.append("0");
                }
                strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
            }
            return strBuf.toString();
        }
        public Session getSession() {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", "smtp.163.com");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String password = "111aaa";                   
//                     InputStream is = PersonController.class.getResourceAsStream("password.dat");
//                    InputStream is = ;
//                    byte[] b = new byte[1024];
//                    try {
//                        int len = is.read(b);
//                        password = new String(b,0,len);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    return new PasswordAuthentication(FROM, password);
                }
                
            });
            return session;
            
        }
        
        
/**********注册模块   End******************************************************************************************************
    /**
     * 将bo 层的代码放到bo层。
     * 
     * @RequestParam("name") String org,String dept,String   enable,
     * @throws Exception
     */
    @RequestMapping(value = "/pages/system/savePerson.do")
    public String savePerson(HttpServletRequest request,
            HttpServletResponse response, Person vperson) throws Exception {

        codSet(response);
        String org = request.getParameter("organizationv");
        String dept = request.getParameter("departmentv");
        String enable = request.getParameter("enable");
        Person loginPerson = loginInfo.getLoginPerson();
		String orgid = loginPerson.getOrganizationId();
		String  pingtaitype=loginPerson.getRemark();
	
        
		if(pingtaitype=="经销商管理平台"||pingtaitype.equals("经销商管理平台")){
        	 vperson.setRemark("经销商管理平台");
        }else{
        	 vperson.setRemark("企业管理平台");
        }
       
        
        personBo.saveperson(vperson, dept, org, enable);
        
        PrintWriter writer = response.getWriter();
        writer.write("true");
        writer.flush();
        return null;
    }

    @RequestMapping(value = "/pages/system/deletePerson.do")
    public String deletePerson(HttpServletRequest request,
            HttpServletResponse response, Person person) throws Exception {
        codSet(response);
        response.setContentType("text/html");
        Long id = person.getId();
        PrintWriter writer = response.getWriter();
        if (id != null) {
            personBo.remove(id);
            writer.write("true");
        } else {
            writer.write("false");
        }
        writer.flush();
        return null;
    }
    @RequestMapping(value = "/pages/system/sendEmails.do")
    public String sendEmail(HttpServletRequest request,
            HttpServletResponse response, Person person) throws Exception {
        codSet(response);
        response.setContentType("text/html");
        String operation = request.getParameter("operation");
        
        Long emailid = person.getId();
       
        person = personBo.activity(emailid);
        
        PrintWriter writer = response.getWriter();
        if (person.getEmail() == null || "".equals(person.getEmail())) {
            
            writer.write("false");
        } else {
            sendAccountActivateEmail(person);//发送到邮箱
            
            writer.write("true");
        }
        writer.flush();
        return null;
    }
    @RequestMapping(value = "/pages/system/sendEmailFail.do")
    public String sendEmailFail(HttpServletRequest request,
            HttpServletResponse response, Person person) throws Exception {
        codSet(response);
        response.setContentType("text/html");
        String operation = request.getParameter("operation");
        
        Long emailid = person.getId();
       
        person = personBo.activity(emailid);
        
        
        PrintWriter writer = response.getWriter();
        if (person.getEmail() == null || "".equals(person.getEmail())) {
            
            writer.write("false");
        } else {
            sendAccountActivateEmailFail(person);
            writer.write("true");
        }
        writer.flush();
        return null;
    }

    @RequestMapping(value = "/pages/system/comboData.do")
    public String getComboData(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        this.codSet(response);
   	 String callback = request.getParameter("callback");
	 JSONObject ret=new JSONObject(); 
	
        List list = null;

//        if ("organizationv".equals(type)) {
//            //拿到当前的公司的id  ，去查询当前公司的 子公司和机构
//            Person loginPerson = loginInfo.getLoginPerson();
//         String orgid=loginPerson.getOrganizationId();
//         String orgname=loginPerson.getOrganizationName();
//         Map map = new HashMap();
//             long idw=Long.valueOf(orgid);
//                map.put("id", idw);
//            
//             list = organizationBo.findByParm(map);
//            
//        } else if ("departmentv".equals(type)) {
//            if (Utils.nullOrEmpty(id) == false)
//                list = departmentBo.findByOrganizationId(Long.valueOf(id));
//        } else {
//            Map map = new HashMap();
//            map.put("type", type);
//            list = dataDictionaryBo.findByParm(map);
//            System.out.println("-----------------从 person中查询 得到下拉框的数据--------------------------");
//        }
//        PrintWriter writer = response.getWriter();
//
//        if (null != list && list.size() > 0) {
//            DataModel dataModel = new DataModel<BaseEntity>();
//            DataDictionary d=new DataDictionary();
//            JSONArray result2=new JSONArray();
//            for (int i = 0; i < list.size(); i++) {  
//            	d=(DataDictionary)list.get(i); //注意向下转型   
//            	//取出StudentInfo对象中的参数   
//             long	idd=d.getId();  
//             String ids=Long.toString(idd);
//            String	name=d.getName();
//          
//            result2.add(build(ids,name));
//            } 
//          
//            //writer.write(dataModel.setRows(list).toString());
//            JSONObject result=new JSONObject();
//            
//            String json = null;
//            json=JsonUtil.list2json(result2);
//            response.reset();
//    		response.setCharacterEncoding("utf-8");
//    		response.setContentType("text/json; charset=UTF-8");
//    	//	PrintWriter writer = response.getWriter();
//
//    		ret.put("data", json.toString());
//    		writer.write(callback + "(" + ret.toString() + ")");
//          // writer.write(json.toString());
//        } else {
//            writer.write("[{'text':'','value':''}]");
//        }
//        writer.flush();
//        return null;
//    }
//    

	if ("organizationv".equals(type)) {
		//list = organizationBo.findAll();
		 Person loginPerson = loginInfo.getLoginPerson();
       String orgid=loginPerson.getOrganizationId();
       String orgname=loginPerson.getOrganizationName();
		list = organizationBo.findByName(orgname);
	} else if ("departmentv".equals(type)) {
		if (Utils.nullOrEmpty(id) == false)
			list = departmentBo.findByOrganizationId(Long.valueOf(id));
	} else {
		Map map = new HashMap();
		map.put("type", type);
		list = dataDictionaryBo.findByParm(map);
	}
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
    
    private JSONObject build(String id,String name){
        JSONObject result=new JSONObject();
        result.put("id", id);
        result.put("name", name);
        return result;
    }
    /**
     * 
     * 验证用户名
     * <功能详细描述>
     */
    @RequestMapping(value = "index.do")
    public String getInfo(HttpSession session){
        session.setAttribute("loginInfo", loginInfo);
       // return "/index";
        return "/home/index";
    }
    @RequestMapping(value = "/setLocale.do")
    public String setLocale(HttpSession session, HttpServletResponse response,
            Locale locale) throws Exception {
        response.setContentType("text/html");
        session.setAttribute("locale", locale);
        loginInfo.setLocale(locale);
        PrintWriter writer = response.getWriter();
        writer.write(locale.toString());
        return null;
    }

    @RequestMapping(value = "/uploadlogo.do")
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
    
    private void defaultProcessFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
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
                    String fileName = "logo.png"; //item.getName();
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
    
    @RequestMapping(value ="/pages/system/updatePasswordOperate.do")
    public String updatePasswordOperate(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String oldPassword = request.getParameter("oldPassword");
        String  newPassword = request.getParameter("newPassword");
        String  repeatNewPassword = request.getParameter("repeatNewPassword");
        System.out.println("旧密码为 "+oldPassword+"新密码为"+newPassword+"确认密码为"+repeatNewPassword);
        Person loginPerson = loginInfo.getLoginPerson();
        String  loginper=loginPerson.getName();
        String loginpassword=loginPerson.getPassword();
        System.out.println("从logininfo中得到的用户密码为"+loginpassword);
        System.out.println("结束 ");
        PrintWriter writer = response.getWriter();
        writer.flush();
        return null;
    }
    
    
    public void codSet(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-Type", "text/json;charset=UTF-8");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
    }

    public void setRolesBo(IRolesBo rolesBo)
    {
        this.rolesBo = rolesBo;
    }

    public IRolesBo getRolesBo()
    {
        return rolesBo;
    }

    public void setRolesAuthoritiesBo(IRolesAuthoritiesBo rolesAuthoritiesBo)
    {
        this.rolesAuthoritiesBo = rolesAuthoritiesBo;
    }

    public IRolesAuthoritiesBo getRolesAuthoritiesBo()
    {
        return rolesAuthoritiesBo;
    }

    public void setAuthoritiesKeyBo(IAuthoritiesKeyBo authoritiesKeyBo)
    {
        this.authoritiesKeyBo = authoritiesKeyBo;
    }

    public IAuthoritiesKeyBo getAuthoritiesKeyBo()
    {
        return authoritiesKeyBo;
    }
    
    
    
    @RequestMapping(value = "/pages/system/setpersonTimeData.do")
	public String setpersonTimeData(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Person entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String operation = request.getParameter("operation");
		String id=request.getParameter("id");
		String beginDate = request.getParameter("testBeginDate");
		String endDate = request.getParameter("testEndDate");
		System.out.println(""+beginDate+""+endDate+""+operation+"--"+id);
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			//dataDictionaryBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			entity=personBo.find(Long.valueOf(id));
			entity.setBeginDate(beginDate);
			entity.setEndDate(endDate);
			
			personBo.update(entity);
			writer.write("true");
		} 
		
		writer.flush();
		return null;
	}
}
