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

import cn.internalaudit.audit.bo.IAuthoritiesKeyBo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.entitys.AuthoritiesKey;
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
public class AuthoritiesKeyController {

    @Resource
    private LoginInfo loginInfo;
	@Resource(name = "AuthoritiesKeyBo")
	private IAuthoritiesKeyBo authoritiesKeyBo;
	@Resource(name = "RolesBo")
    private IRolesBo rolesBo;

	@RequestMapping(value = "/pages/system/getAuthoritiesKeyTree.do")
	public String getAuthoritiesKeyTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.codSet(response);
		//获取登录账号Id
		Person loginPerson = loginInfo.getLoginPerson();
		Long perid = loginPerson.getId();
	
		//根据登录账号Id获取role.Name
		List<Roles> roleid = rolesBo.findByPersonId(perid);
		//根据role.name获取authorities.id
		List<AuthoritiesKey> authoritiesKey = authoritiesKeyBo.checkValueByName(roleid.get(0).getName());
		
		PrintWriter writer = response.getWriter();
		TreeModel treemodel = new TreeModel<AuthoritiesKey>();
		Map map = new HashMap();
		map.put("parentAuthoritiesKey", authoritiesKey.get(0).getId());
		List<AuthoritiesKey> list = this.authoritiesKeyBo.findByParm(map);
		writer.write(treemodel.createTreeNodes(list).toString());

		writer.flush();

		return null;
	}
	
	@RequestMapping(value = "/pages/system/getAuthoritiesKeyTreerole.do")
	public String getAuthoritiesKeyTreerole(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.codSet(response);
		PrintWriter writer = response.getWriter();

		TreeModel treemodel = new TreeModel<AuthoritiesKey>();
		Map map = new HashMap();
		map.put("parentAuthoritiesKey", null);
		//"%" + code + "%")
		//String namet="企业管理";
	    //parms.put("code", "%" + code + "%");
		//这里得到 登录用户的权限ID，那么在授权的时候，只是显示这个ID下的权限。
	    // map.put("id", 18);
		List<AuthoritiesKey> list = this.authoritiesKeyBo.findBymaps(map);
		writer.write(treemodel.createTreeNodes(list).toString());

		writer.flush();

		return null;
	}

	@RequestMapping(value = "/pages/system/getAuthoritiesKey.do")
	public String getAuthoritiesKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.codSet(response);

		DataModel datamodel = new DataModel<AuthoritiesKey>();

		List<AuthoritiesKey> list = new ArrayList();
		list = authoritiesKeyBo.findAll();
		
		datamodel.setRows(list);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
		
	}

	@RequestMapping(value = "/pages/system/saveAuthoritiesKey.do")
	public String saveAuthoritiesKey(HttpServletRequest request,
			HttpServletResponse response, AuthoritiesKey authoritiesKeyv,
			@RequestParam Long parentId) throws Exception {
		request.setCharacterEncoding("UTF-8");
		this.codSet(response);
		response.setContentType("text/html");

		AuthoritiesKey authoritiesKey = new AuthoritiesKey();
		if (authoritiesKeyv.getId() != null) {
			authoritiesKey = authoritiesKeyBo.find(authoritiesKeyv.getId());
		}
		if (parentId != null) {
			AuthoritiesKey parentAuthor = authoritiesKeyBo.find(parentId);
			authoritiesKey.setParentAuthoritiesKey(parentAuthor);
		}
		DataModel datamodel = new DataModel<AuthoritiesKey>();
		datamodel.copy(authoritiesKeyv, authoritiesKey);
		authoritiesKeyBo.save(authoritiesKey);

		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/system/deleteAuthoritiesKey.do")
	public String deleteAuthoritiesKey(HttpServletRequest request,
			HttpServletResponse response, AuthoritiesKey authoritiesKeyv)
			throws Exception {

		this.codSet(response);
		response.setContentType("text/html");

		authoritiesKeyBo.remove(authoritiesKeyv.getId());

		PrintWriter writer = response.getWriter();
		writer.write("true");
		writer.flush();
		return null;
	}
	public void codSet(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Type", "text/xml;charset=UTF-8");
		response.setDateHeader("Expires", 0);
	}

    public void setLoginInfo(LoginInfo loginInfo)
    {
        this.loginInfo = loginInfo;
    }

    public LoginInfo getLoginInfo()
    {
        return loginInfo;
    }
}
