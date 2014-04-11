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

import cn.internalaudit.audit.bo.IBrandjxsBo;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

@Controller
public class BrandjxsController
{
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    @Resource(name = "BrandjxsBo")
    public IBrandjxsBo brandjxsBo;
    
    @Resource
	private LoginInfo loginInfo;
    
    /**
     * 
     * 显示所有品牌信息
     * 无条件查询
     * @param locale
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/pages/company/getBrandjxs.do")
    public String getBrandjxs(Locale locale, Model model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        String brandName = request.getParameter("brandName");
        Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
        
        String startStr = request.getParameter("start");
        String limitStr = request.getParameter("limit");
        int start = Integer.parseInt(startStr);
        int limit = Integer.parseInt(limitStr);
        if (limit == 0) {
            limit = Integer.MAX_VALUE;
        }
        PrintWriter writer = response.getWriter();
        DataModel datamodel = new DataModel<Brandjxs>();
        int end = start + limit;
        Map map = new HashMap();
        if (!Utils.nullOrEmpty(brandName)){
            String dname=new String(brandName.getBytes( "iso-8859-1"),"UTF-8" );
            map.put("name", "%" + dname + "%");
        }
        List<Brandjxs> list = null;
        if (!Utils.nullOrEmpty(orgid))
			map.put("org", orgid);
		
		if (map.size()>0) {
			list = brandjxsBo.findByParm(map);
		} else if(map.size()>1) {
			list = brandjxsBo.findByParm(map);
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
    @RequestMapping(value = "/pages/company/saveBrandjxs.do")
    public String saveBrandjxs(HttpServletRequest request,
            HttpServletResponse response, Brandjxs vperson) throws Exception {

        codSet(response);
        Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
        String operation = request.getParameter("operation");
        PrintWriter writer = response.getWriter();
        if (ADD.equals(operation)) {
        	vperson.setOrg(orgid);
            brandjxsBo.save(vperson);
            writer.write("true");
        } else if (UPDATE.equals(operation)) {
            vperson.setOrg(orgid);
        	System.out.println("点击更新之后，");
            brandjxsBo.update(vperson);
            writer.write("true");
        } else if (DELETE.equals(operation)) {
            brandjxsBo.delete(vperson);
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
