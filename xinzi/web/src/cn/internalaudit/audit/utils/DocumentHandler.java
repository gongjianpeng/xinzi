package cn.internalaudit.audit.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler {
	   private Configuration configuration = null;  
	   public DocumentHandler() {  
	      configuration = new Configuration();  
	      configuration.setDefaultEncoding("utf-8");  
	   }  
	   
	   private Map dataMap = new HashMap<String, Object>();
	   
	   
	   private String templateFileName;
	   
	   private ServletContext context;
	   
	   public void setServletContext(ServletContext context){
		   this.context = context;
	   }
	   /***
	    * 设置模板文件名 ,ftl
	    * @param templateFileName
	    */
	   public void setTemplateFileName(String templateFileName){
		   this.templateFileName = templateFileName;		   
	   }
	   
	   /**
	    * 设置数据模型
	    * @param dataMap
	    */
	   public void setDataMap(Map dataMap){
		   this.dataMap = dataMap;
	   }
	   
	   public void createDoc() {  
	      //要填入模本的数据文件  
	        
	      //getData(dataMap);  
	      //设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，  
	      //这里我们的模板是放在com.havenliu.document.template包下面  
//	      configuration.setClassForTemplateLoading(this.getClass(), "/cn/internalaudit/audit/utils");  
	      configuration.setServletContextForTemplateLoading(context, "templates");
	      Template t=null;  
	      try {  
	         //test.ftl为要装载的模板  
	         t = configuration.getTemplate(this.templateFileName);  
	      } catch (IOException e) {  
	         e.printStackTrace();  
	      }  
	      //输出文档路径及名称  
	      File outFile = new File("D:/outFile.doc");  
	      Writer out = null;  
	      try {  
	         out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
	      } catch (FileNotFoundException e1) {  
	         e1.printStackTrace();  
	      }  
	      try {  
	         t.process(dataMap, out);  
	      } catch (TemplateException e) {  
	         e.printStackTrace();  
	      } catch (IOException e) {  
	         e.printStackTrace();  
	      }  
	   }  
	   /**  
	   * 注意dataMap里存放的数据Key值要与模板中的参数相对应  
	   * @param dataMap  
	   */ 
//	   private void getData(Map dataMap)  
//	   {  
//	      dataMap.put("company", "张三建筑公司");  
//	      dataMap.put("year", "2012");  
//	      dataMap.put("user", "小兵张嘎");  
	     
//	      List _table1=new ArrayList();  
//	      Table1 t1=new Table1();  
//	      t1.setDate("2010-10-1");  
//	      t1.setText("制定10月开发计划内容。");  
//	      _table1.add(t1);  
//	      Table1 t2=new Table1();  
//	      t2.setDate("2010-10-2");  
//	      t2.setText("开会讨论开发计划");  
//	      _table1.add(t2);  
//	      dataMap.put("table1", _table1);  
//	      List _table2=new ArrayList();  
//	      for(int i=0;i&lt;5;i++)  
//	      {  
//	         Table2 _t2=new Table2();  
//	         _t2.setDetail("测试开发计划"+i);  
//	         _t2.setPerson("张三——"+i);  
//	         _t2.setBegindate("2010-10-1");  
//	         _t2.setFinishdate("2010-10-31");  
//	         _t2.setRemark("备注信息");  
//	         _table2.add(_t2);  
//	      }  
//	      dataMap.put("table2", _table2);  
//	   }  
	   public static void main(String[] arg ){
		   DocumentHandler hander = new DocumentHandler();
		   hander.createDoc();
		   System.out.println("OK!");
	   }
	} 