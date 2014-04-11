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

import cn.internalaudit.audit.bo.IParameterjxsBo;
import cn.internalaudit.audit.entitys.Parameterjxs;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

@Controller
public class ParameterjxsController
{
    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    @Resource(name = "ParameterjxsBo")
    public IParameterjxsBo parameterjxsBo;
    
    @Resource
	private LoginInfo loginInfo;
    /**
     * 
     * 显示基本参数信息
     * 无条件查询
     * @param locale
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/pages/company/getParameterjxs.do")
    public String getParameterjxs(Locale locale, Model model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codSet(response);
        String jibenName = request.getParameter("jibenName");
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
        DataModel datamodel = new DataModel<Parameterjxs>();
        int end = start + limit;
        Map map = new HashMap();
        if (!Utils.nullOrEmpty(jibenName)){
            String userNameap=new String(jibenName.getBytes( "iso-8859-1"),"UTF-8" );
            map.put("name", "%"+userNameap+"%");
        }
        List<Parameterjxs> list = null;
//        if (map.size() == 0) {
//            list = quoteBo.findAll();
//        } else {
//            list = quoteBo.findByParms(map);
//        }
        if (!Utils.nullOrEmpty(orgid))
			map.put("org", orgid);
		
		if (map.size()>0) {
			list = parameterjxsBo.findByParm(map);
		} else if(map.size()>1) {
			list = parameterjxsBo.findByParm(map);
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
    @RequestMapping(value = "/pages/company/saveParameterjxs.do")
    public String saveParameterjxs(HttpServletRequest request,
            HttpServletResponse response, Parameterjxs vperson) throws Exception {

        codSet(response);
        String operation = request.getParameter("operation");
        Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
     
        PrintWriter writer = response.getWriter();
        if (ADD.equals(operation)) {
        	vperson.setOrg(orgid);
        	parameterjxsBo.save(vperson);
            writer.write("true");
        } else if (UPDATE.equals(operation)) {
            vperson.setOrg(orgid);
            parameterjxsBo.update(vperson);
            writer.write("true");
        } else if (DELETE.equals(operation)) {
            parameterjxsBo.delete(vperson);
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
