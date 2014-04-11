package cn.internalaudit.audit.pojo.controller;


import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.pojo.DiyModel;
import cn.internalaudit.audit.pojo.bo.IDiyModelBo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DiyModelController {

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	@Resource(name = "DiyModelBo")
	public IDiyModelBo diyModelBo;
	
		
	
		
	@RequestMapping(value = "/pages/company/DiyModelOperate.do")
	public String DiyModelController(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DiyModel entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			diyModelBo.save(entity);
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			diyModelBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			diyModelBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}
	
	
	

	/*****************************	//@RequestMapping(value = "/pages/company/getDataDiyModels.do")********************/
	@RequestMapping(value = "/pages/company/getdiyCombo.do")
	public void sebanCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DiyModel diyModel) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);


		String codetype = request.getParameter("codetype"); // 选择是压花 还是花枝
		String flow = request.getParameter("flow");

		JSONArray resultleft = new JSONArray();
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		
		
		 
		 
		//String codetype="1";
		  long Didqc = 0;
			String Ddataqc = null;
			   List<DiyModel> listleft=diyModelBo.findDiyModelByType(codetype);//organization2Bo.findByName("气窗");
			   String name=null;
			   String img=null;
			   String scale=null;
			   String x=null;
			   String y=null;
			   String type=null;
				  for(int k=0;k<listleft.size();k++){ 
					  Long id = listleft.get(k).getId();
					name=listleft.get(k).getName();
					img=listleft.get(k).getImg();
					scale=listleft.get(k).getScale();
					x=listleft.get(k).getX();
					y=listleft.get(k).getY();
					type=listleft.get(k).getType();
					 System.out.println("one _"+id+"name_"+name+"img_"+img+"scale_"+scale+"x_"+x+"y_"+y+"");
					 
					 resultleft.add(buildjsonlistleft(id, name,img, type, x, y));
				  }
				  System.out.println(" two "+resultleft);
		
       System.out.println("^"+resultleft);
        response.reset();
		response.setCharacterEncoding("utf-8");

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ret.put("data", resultleft.toString());
		writer.write( ret.toString());
		writer.flush();


	}

	

	



	

	
	private JSONObject buildjsonlistleft(Long id, String name,String img,String type,String x,String y) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("type", type);
		result.put("x", x);
		result.put("y", y);

		return result;
	}
	private JSONObject buildjsonlistdan(Long id, String name,String img,String type) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("type", type);
		

		return result;
	}
	

}
