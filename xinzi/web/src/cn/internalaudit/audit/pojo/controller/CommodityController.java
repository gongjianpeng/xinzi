package cn.internalaudit.audit.pojo.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;

import cn.internalaudit.audit.bo.ICompanyBo;
import cn.internalaudit.audit.bo.IDataDictionaryBo;
import cn.internalaudit.audit.bo.IDealerBo;
import cn.internalaudit.audit.bo.IDepartment2Bo;
import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.bo.IParameterqiyeBo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.bo.impl.Organization2Bo;
import cn.internalaudit.audit.entitys.BaseEntity;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.entitys.Organization2;
import cn.internalaudit.audit.entitys.Parameterqiye;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.pojo.Commodity;
import cn.internalaudit.audit.pojo.DiyModel;
import cn.internalaudit.audit.pojo.Panel;
import cn.internalaudit.audit.pojo.bo.ICommodityBo;
import cn.internalaudit.audit.pojo.bo.IDiyModelBo;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.DoorBo;
import cn.internalaudit.audit.utils.DoorType;
import cn.internalaudit.audit.utils.JsonUtil;
import cn.internalaudit.audit.utils.Utils;

@Controller
public class CommodityController {
	@Resource(name = "CommodityBo")
	public ICommodityBo commodityBo;

	@Resource(name = "PersonBo")
	IPersonBo personBo;
	@Resource(name = "DataDictionaryBo")
	public IDataDictionaryBo dataDictionaryBo;
	
	@Resource(name = "OrganizationBo")
	public IOrganizationBo organizationBo;
	
	@Resource(name = "DepartmentBo")
	private IDepartmentBo departmentBo;
	@Resource(name = "DealerBo")
	// 企业
	public IDealerBo dealerBo;
	@Resource(name = "CompanyBo")
	// 经销商
	public ICompanyBo companyBo;
	@Resource(name = "RolesBo")
	private IRolesBo rolesBo;
	
	
	@Resource(name = "DiyModelBo")
	public IDiyModelBo diyModelBo;
	
//	@Resource(name = "DiyModelSebanBo")
//	public IDiyModelSebanBo diyModelsebanBo;

	
	
	@Resource(name = "Organization2Bo")
	Organization2Bo organization2Bo;
	@Resource(name = "Department2Bo")
	private IDepartment2Bo department2Bo;

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	
	// 12套模版 常量 设置     2014 -02-11   根据尺寸判断之后 获取 常量中的一个， 返回设定 模版之一的数据 ， 
	// 分成 2部分  ，自己独有的，  和公共的。
	// 之前要判断：   尺寸， 门把手，  压花尺寸。
	/***
	 * 1  尺寸计算 ，  目前不清楚怎么设置，
	 * 2 返回 自己独有的数据，   （门头，门柱，门框，）
	 * 3 返回公共的数据，  可以先做  （气窗,铰链,色板）  开向		锁具	锁芯	门铃	猫眼	下档  板材	板材厚度	玻璃
	 * 3.1： 
	 * 
	 * 4  四层机构 变成 可添加的： 压花，花枝，
	 * ***/
	public static final String MONE = "mone";
	public static final String MTWO = "mtwo";
	public static final String MTHREE = "mthree";
	public static final String MFOUR = "mfour";
	public static final String MFIVE = "mfive";
	public static final String MSIX = "msix";
	public static final String MSEVEN = "mseven";
	public static final String MEIGHT = "meight";
	public static final String MNINE = "mnine";
	public static final String MTEN = "mten";
	public static final String MELEVEN = "meleven";
	public static final String MTWELVE = "mtwelve";
	
	@Resource
	private LoginInfo loginInfo;
	
	 
	@Resource(name = "ParameterqiyeBo")
    public IParameterqiyeBo parameterqiyeBo;
	
	
	 public static String[] list2Array(List<String> list){
		//  String[] strs = (String[])list.toArray();
		  String[] strs1 = list.toArray(new String[list.size()]);
		  return strs1;
		 }
	
	
//	list = parameterqiyeBo.findBynameAndType(name, type);
	/***
	 * x  宽 ，   y 高。
	 *  根据这个方法，获取用户的尺寸，判断最大最小值， 返回  适合 做什么门
	 *  查询到所有门尺寸的规格， 获取前台穿过来的，进行匹配，返回   5个门的一个。
	 * *
	 * @return **/
	public String DoorOneJu(HttpServletRequest request,
			HttpServletResponse response,String dWidth,String dLength) throws Exception {
		
		//String dWidthone = request.getParameter("dWidth");
		//String dLengthone = request.getParameter("dLength");
		List<Parameterqiye> list = null;
		String name="门尺寸"; 
		String name2=new String(name.getBytes( "iso-8859-1"),"UTF-8" );
		list = parameterqiyeBo.findParameterqiyeByName(name2);
		
		for(int i=0;i<list.size();i++)    {
		    System.out.println(list.get(i));
		  }
		Iterator it1 = list.iterator();
		  while(it1.hasNext())  
	        {  
			  it1.next();  
	            System.out.println(it1.next());  
	        }  
		 

		return "";
	}

	/********
	 * 1 ：获取前台点击确定 获取到的 参数： 尺寸 长 dLength， 宽dWidth ，门体高 dHeight， 墙体厚度 dTrad。
	 * 其他数据： 品牌，产品编号，门型，樘数。 2 ：调用 doorstyle类方法， 根据参数长宽高，进行判断， 获得结论为 门 尺寸
	 * (普通，带气窗，),门压花尺寸(普通压模，出头模，小门对开，子门，广西加长) 3： 产生的结果 会影响 门头款式， 门柱款式，门框， 气窗，立柱
	 * 根据2产生的结果判断。 4 ：其他条件不需要根据门判断。
	 * **/

	@RequestMapping(value = "/pages/company/DoorJudge.do")
	public String DoorJudge(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.codSet(response);
		
       /**
        * 未 加上的验证
        *   输入的参数为空
        * */
		
		// 接收 点击确定之后接收过来的参数
		String dLength = request.getParameter("dLength");
		String dWidth = request.getParameter("dWidth");
		if (dLength == null || dLength.equals("") || dWidth == null || dWidth.equals("")
				//	|| DieWidth == null || DieWidth.equals("")	|| DieLength == null || DieLength.equals("")		
		) {
			
				System.out.println("得到的数值为空，请输入 尺寸");
		} else {
		int dLengthone = Integer.parseInt(dLength);
		int dWidthone = Integer.parseInt(dWidth);
		System.out.println("..+"+dLengthone+dWidthone);
		List<Parameterqiye> list2 = null;
		String name="门尺寸"; 
		String name2=new String(name.getBytes( "iso-8859-1"),"UTF-8" );
		list2 = parameterqiyeBo.findParameterqiyeByName("门尺寸");
		Map maponeshow=new HashMap(); //存放 尺寸判断后，是否适合那些门， 也许是多个。

		List listone = new ArrayList();
		 for(int i=0;i<list2.size();i++)    {
			
			String x=list2.get(i).getMinsize();  int xone = Integer.parseInt(x);
			String ax=list2.get(i).getMaxsize();   int axone = Integer.parseInt(ax);
			String y=list2.get(i).getChichun();  int yone = Integer.parseInt(y);
			System.out.println(xone+"..."+axone+".."+yone);
			
			if(dWidthone>xone && dWidthone < axone &&  dLengthone > yone){
				System.out.println("这个是符合条件的"+list2.get(i).getType()+"...");
				String nameoneshowval=list2.get(i).getType();
			
				listone.add(nameoneshowval);
			}
		  }
		/**************************第二部分  判断这个门是带气窗还是普通， 尺寸为后台配置************************************************/
		String namemenxing="门型";
		List<Parameterqiye> listmenxing = null;
		String namemenxing2=new String(namemenxing.getBytes( "iso-8859-1"),"UTF-8" );
		listmenxing = parameterqiyeBo.findParameterqiyeByType("门型");
		System.out.println("门类型的数量为"+listmenxing.size());
		/***for 循环开始**/
		  //定义一个数组 
		List<String> listhh = new ArrayList<String>();  
            for(int i=0;i<listmenxing.size();i++)    {
			String x=listmenxing.get(i).getQiwidth();  int xqione = Integer.parseInt(x);  //带气窗的宽度
			String ax=listmenxing.get(i).getQiheight();   int axqione = Integer.parseInt(ax); //带气窗的高度
			String yw=listmenxing.get(i).getChichunk();  int ywone = Integer.parseInt(yw);  //普通宽度
			String yl=listmenxing.get(i).getChichun();  int ylone= Integer.parseInt(yl); // 普通高度
			int widthjieguo=dWidthone-ywone;
		
		     if(widthjieguo<0){	
		    	 widthjieguo=Math.abs(widthjieguo);
		    	// System.out.println(">>1>>"+widthjieguo);
		    		String s=String.valueOf(Math.abs(widthjieguo));
		    	//	 System.out.println(">>22>>"+s);
		    		listhh.add(s);
	            }else if(widthjieguo>0){
	            	String s2=String.valueOf(widthjieguo);
	            	listhh.add(s2);
	            }
            
           }
            
            String[] array = (String[]) listhh.toArray(new String[listhh.size()]);  
            	int[] ia=new int[array.length];
            	 List listintss = new ArrayList();
            for(int i=0;i<array.length;i++){
            	ia[i]=Integer.valueOf(array[i]);
            	  // System.out.println("#数组中获得的数值 为#"+ia[i]);  
            	   if(ia[i]>0){
            		 //  System.out.println("...l");
            	   }
            	   if(ia[i]<0){
            		  // System.out.println("...ll");
            	   }
            	   listintss.add(ia[i]);
            	}

        		System.out.println("获取最小值的为："+Collections.min(listintss));
        		Integer ee=(Integer) Collections.min(listintss);
        		//已经获得的最小值 ， 用户输入的宽+ 最小值，就是最接近的参数 ，宽。 dLength
        		List<Parameterqiye> listkuan = null;
       if(dWidthone<180||dWidth==null||dWidth.equals("")||dLength==null||dLength.equals("")){
    	System.out.println("查询到的数据为空");   
       }else{ 	
      
        		if((dWidthone>180&&dWidthone<960)||(dWidthone<1200&&dWidthone>1080)||(dWidthone<1920&&dWidthone>1500)||(dWidthone<2400&&dWidthone>2160)||(dWidthone<3840&&dWidthone>2780)){
        		int onewidthall=dWidthone+ee;  //+Collections.min(listintss);
                System.out.println("得到和系统中最接近的值为i:"+onewidthall);
        		String menxingchicunk=String.valueOf(onewidthall);
        		String type="门型";
        		String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
        		listkuan = parameterqiyeBo.findParameterqiyeByTypeAchik("门型",menxingchicunk);
        		System.out.println("门《为"+listkuan.size());
        		}
        		else
        		if(dWidthone==960||dWidthone==1200||dWidthone==1920||dWidthone==3840||dWidthone==2400){
            		int onewidthall=dWidthone;  //+Collections.min(listintss);
                    System.out.println("==:"+onewidthall);
            		String menxingchicunk=String.valueOf(onewidthall);
            		String type="门型";
            		String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
            		listkuan = parameterqiyeBo.findParameterqiyeByTypeAchik("门型",menxingchicunk);
            		System.out.println("=="+listkuan.size());
            		}
        		else
        		if(dWidthone>960||dWidthone>1200||dWidthone>1920||dWidthone>3840||dWidthone>2400){
        			// 如果是 差值
        			int onewidthall2=dWidthone-ee;//+Collections.min(listintss);
                    System.out.println("2得到和系统中最接近的值为i:"+onewidthall2);
                  // 根据门尺寸宽，门型 ，获得 名称
            		String menxingchicunk2=String.valueOf(onewidthall2);
            		String type="门型";
            		String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
            		listkuan = parameterqiyeBo.findParameterqiyeByTypeAchik("门型",menxingchicunk2);
            		System.out.println("2门》量为"+listkuan.size());
        			
        		}
        		System.out.println("3门类型的数量为"+listkuan.size());
        		
        		//获取这个门的 name 这里的问题， 如果获取 参数为空  list 
        		/******************* 门型   宽度      获得**********************/ 
        		String  EndOneNamemen=listkuan.get(0).getName();
        		String  EndOneWidthmen=listkuan.get(0).getChichunk();
        		String  EndOneheightmen=listkuan.get(0).getChichun();
        		int EndOneheightmen2 = Integer.parseInt(EndOneheightmen);
               System.out.println("获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen+"/");
        		/********************高度获得，比较 决定   开始**********************/ 
        		String mengao2=listkuan.get(0).getChichun();    
        		//if(mengao2==null||mengao2.equals("")){ int mengao = 0;    }else{  }
        		int mengaoone = Integer.parseInt(mengao2);
        		String qichugao2=listkuan.get(0).getQiheight();
        		int qichugao = Integer.parseInt(qichugao2);
        		int gaochaone=dLengthone-mengaoone;
        		int gaoqicchaone=dLengthone-qichugao;
        		if(gaochaone<0||gaoqicchaone<0){
        			gaochaone=Math.abs(gaochaone);
        			gaoqicchaone=Math.abs(gaoqicchaone);
        		}
        		int mengaotwo=0;
        		if((gaochaone-gaoqicchaone)<0){
        			 mengaotwo=mengaoone;
        			System.out.println(",,1,,"+mengaotwo);
        		}
        		if((gaochaone-gaoqicchaone)>0){
        			 mengaotwo=qichugao;
        			System.out.println(",,2,,"+mengaotwo);
        		}
        		 // 高度获得
        		System.out.println(",,3,,"+mengaotwo);
        		/********************高度获得，比较 决定   结束，获取最终结果**********************/ 
        		String EndOneXingmen;
        		if(mengaotwo==EndOneheightmen2){
        			EndOneXingmen="putong";
        			System.out.println("这个门是个普通的门");
        		}else{
        			EndOneXingmen="qichuang";
        			System.out.println("这个门是个带气窗的门");
        		}
        		
        	System.out.println("获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen+"/");
        	System.out.println("门的普通或气窗"+EndOneXingmen);
	
        		/********************这个 门 的 宽高 获 得**********************/ 
    		/************第三部分  判断这个门是带气窗还是普通，结束，判断拉手，根据不同的门有1个，或2个*********************************************/
    		String namebashou="门尺寸"; 
    		List<Parameterqiye> listba = null;
    		String name2bashou=new String(namebashou.getBytes( "iso-8859-1"),"UTF-8" );
    		listba = parameterqiyeBo.findParameterqiyeByName("门把手");
    		String menbashouwidth=listba.get(0).getChichunk();
    		System.out.println("门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth);
    		int menbashouwidthin = Integer.parseInt(menbashouwidth);
    		int menshouWidth=0;
    		int menEndWidthmen = Integer.parseInt(EndOneWidthmen);
    		List<Parameterqiye> listkuanyamo = null;
    		List<String> listhh11 = new ArrayList<String>();  
    		List<String> listhh12 = new ArrayList<String>(); 
    		List<String> listhh21 = new ArrayList<String>();
    		List<String> listhh22 = new ArrayList<String>();
    		List<String> listhh23 = new ArrayList<String>();
    		List<String> listhh31 = new ArrayList<String>();
    		List<String> listhh32 = new ArrayList<String>();
    		List<String> listhh33 = new ArrayList<String>();
    		List<String> listhh34 = new ArrayList<String>();
    		List<String> listhh41 = new ArrayList<String>();
    		List<String> listhh42 = new ArrayList<String>();
    		List<String> listhh43 = new ArrayList<String>();
    		
    		List<String> listhh51 = new ArrayList<String>();
    		List<String> listhh52 = new ArrayList<String>();
    		List<String> listhh53 = new ArrayList<String>();
    		List<String> listhh54 = new ArrayList<String>();
    		System.out.println("获取门的名称："+EndOneNamemen);
    		List<String> listkuanyamohh = new ArrayList<String>(); 
    		// 压花判断   第一层，判断单门，字母门，如果是 门把手则为 1个，
    		/****
    		 * 1.1 获取普通压模的 每个 宽度，放到数组中，进行比较，如果 宽度在（门宽减去-把手宽后）之内，这些尺寸都可以做
    		 * 1.2 把上述尺寸放在list中， 
    		 * 2.1 如果是出头模 
    		 * **/
    		 Map map = new HashMap();
    		
    		if(EndOneNamemen.equals("单门")){
    			int	qichuangheightdan = 0;  //
    			menshouWidth=menbashouwidthin;
    			int yahuakuanMen=menEndWidthmen-menshouWidth;
    			System.out.println("1获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen);
    			if(1==1){
    				listkuanyamo = parameterqiyeBo.findBynameAndType("普通压模", "门压花尺寸");
        			for(int i=0;i<listkuanyamo.size();i++){
        				String sssl=listkuanyamo.get(i).getChichunk();
        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
        				listhh11.add(sssl);
        			}
        			String[] array2 = (String[])listhh11.toArray(new String[listhh11.size()]);  
        	        int[] ia2=new int[array2.length];
        	          List listkuanyamonew = new ArrayList();
        	            for(int i=0;i<array2.length;i++){
        	            	ia2[i]=Integer.valueOf(array2[i]);
        	            	  // System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
        	            	   int  cfo=yahuakuanMen-ia2[i];
        	            	   if(cfo>0){
        	            		//   System.out.println("...l");
        	            		   listkuanyamonew.add(ia2[i]);
        	            	   }else{
        	            		//   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
        	            	   }
        	            	}
        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
     // c测试普通压模 单门  一个门把手， 获取  	最接近的尺寸 的宽，        		
//        	        		for(int i=0;i<listkuanyamonew.size();i++){
//        	        			System.out.println("输出list中才每一项"+listkuanyamonew.get(i));
//        	        		}
        	  	//根据这个数值 ，查找到，适合这个尺寸的高度，判断气窗是否可以安装，如果合适则为可以用，将他添加到 临时表中
        	        		List<Parameterqiye> yahuaendList = null;
        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
        	        //		这个门的高度 ，获取是普通的，还是带气窗的，如果是普通的，则压花高度小于门高度，	
        	        		String yaheight=null;
        	        		for(int i=0;i<yahuaendList.size();i++){
        	        			 yaheight=yahuaendList.get(i).getChichun();
        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
        	    				
        	    			}
        	        
        	        		int     yaheiheight2= Integer.parseInt(yaheight);
        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
        	        		if(kk>0){
        	        			if(EndOneXingmen=="putong"){
        	        				System.out.println("压花高度在门高度之内，是个普通的门");
        	        			}else if(EndOneXingmen=="qichuang"){
        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
        	        				//在这里  门高度-压花-低挡-中档;
        	       	List<Parameterqiye> yahuadang1List = null;
        	    	List<Parameterqiye> yahuadang2List = null;
        	    	String type="门档";
        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
        	       	String zhongdang=null;
            		for(int i=0;i<yahuadang1List.size();i++){
            			zhongdang=yahuadang1List.get(i).getChichun();
        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
        				
        			}
            		int     zhongdang2= Integer.parseInt(zhongdang);
            		String didang2=null;
            		for(int i=0;i<yahuadang2List.size();i++){
            			didang2=yahuadang2List.get(i).getChichun();
        				System.out.println("循环中获取是 底档高度为的:"+didang2);
        			}
            		int  didang= Integer.parseInt(didang2);
        	        qichuangheightdan=kk-zhongdang2-didang;
        	       if(qichuangheightdan<0){qichuangheightdan=0;}
        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
        	        			}
        	        		}
        	    System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
        	        	    		 +"1获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
        	        	    		 "获取门的名称："+EndOneNamemen+
        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
        	        	    		 "门的普通或气窗"+EndOneXingmen+
        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
    				
    			} 
    			
    			 String dataleft=null;
    			 if(EndOneNamemen.equals("单门")&& EndOneXingmen.equals("putong")){
    				 System.out.println("单门普通 1");
    				  dataleft="1";
    			 }else{
    				 
    				 System.out.println("单门气窗 1");
    				  dataleft="2";
    			 }
    			
    			
    			//普通压模 (单门，子母门)
    			 if(2==2){
    				System.out.println("———————————————————2—————————————————");
    				listkuanyamo = parameterqiyeBo.findBynameAndType("出头模", "门压花尺寸");
        			for(int i=0;i<listkuanyamo.size();i++){
        				String sssl=listkuanyamo.get(i).getChichunk();
        				listhh12.add(sssl);
        			}
        			String[] array2 = (String[])listhh12.toArray(new String[listhh12.size()]);  
        	        int[] ia2=new int[array2.length];
        	          List listkuanyamonew2 = new ArrayList();
        	            for(int i=0;i<array2.length;i++){
        	            	ia2[i]=Integer.valueOf(array2[i]);
        	            	   int  cfo=yahuakuanMen-ia2[i];
        	            	   if(cfo>0){
        	            		   System.out.println("...l");
        	            		   listkuanyamonew2.add(ia2[i]);
        	            	   }else{
        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
        	            	   }
        	            	}
        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew2));

        	        		List<Parameterqiye> yahuaendList = null;
        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew2));
        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
        	        		String yaheight=null;
        	        		for(int i=0;i<yahuaendList.size();i++){
        	        			 yaheight=yahuaendList.get(i).getChichun();
        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
        	    				
        	    			}
        	        		int     yaheiheight2= Integer.parseInt(yaheight);
        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
        	        		if(kk>0){
        	        			if(EndOneXingmen=="putong"){
        	        				System.out.println("压花高度在门高度之内，是个普通的门");
        	        			}else if(EndOneXingmen=="qichuang"){
        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
        	        				//在这里  门高度-压花-低挡-中档;
        	       	List<Parameterqiye> yahuadang1List = null;
        	    	List<Parameterqiye> yahuadang2List = null;
        	    	String type="门档";
        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
        	       	String zhongdang=null;
            		for(int i=0;i<yahuadang1List.size();i++){
            			zhongdang=yahuadang1List.get(i).getChichun();
        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
        			}
            		int     zhongdang2= Integer.parseInt(zhongdang);
            		String didang2=null;
            		for(int i=0;i<yahuadang2List.size();i++){
            			didang2=yahuadang2List.get(i).getChichun();
        				System.out.println("循环中获取是 底档高度为的:"+didang2);
        			}
            		int  didang= Integer.parseInt(didang2);
        	        qichuangheightdan=kk-zhongdang2-didang;
        	       if(qichuangheightdan<0){qichuangheightdan=0;}
        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
        	        			}
        	        		}
        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
        	        	    		 "获取门的名称："+EndOneNamemen+
        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
        	        	    		 "门的普通或气窗"+EndOneXingmen+
        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
    				
    			}
    			 
    			 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
        	    		 +"门高度为:"+EndOneheightmen
        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
        	    		 "获取门的名称："+EndOneNamemen+
        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
        	    		 "门的普通或气窗"+EndOneXingmen+
        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);
    	         // 压花尺寸，+气窗尺寸，门把手 数量， 
    			 // 门名称  ，
    		//	 String dataleft=null;
    			 if(EndOneNamemen.equals("单门")&& EndOneXingmen.equals("putong")){
    				 System.out.println("单门普通  2");
    				  dataleft="1";
    			 }else{
    				 
    				 System.out.println("单门气窗  2");
    				  dataleft="2";
    			 }
    			 this.getDoorJsonData(request, response, EndOneNamemen, dataleft);
    			 
    			 
    		}
    		/**************************  如果是多门的 其他减去 2个宽度*  开始*************************************************/
            if(EndOneNamemen.equals("子母门")){
            	int	qichuangheightdan = 0;  //
    			menshouWidth=menbashouwidthin;
    			int yahuakuanMen=menEndWidthmen-menshouWidth;
    			System.out.println("1获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen);
    			if(1==1){
    				listkuanyamo = parameterqiyeBo.findBynameAndType("普通压模", "门压花尺寸");
        			for(int i=0;i<listkuanyamo.size();i++){
        				String sssl=listkuanyamo.get(i).getChichunk();
        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
        				listhh21.add(sssl);
        			}
        			String[] array2 = (String[])listhh21.toArray(new String[listhh21.size()]);  
        	        int[] ia2=new int[array2.length];
        	          List listkuanyamonew = new ArrayList();
        	            for(int i=0;i<array2.length;i++){
        	            	ia2[i]=Integer.valueOf(array2[i]);
        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
        	            	   int  cfo=yahuakuanMen-ia2[i];
        	            	   if(cfo>0){
        	            		   System.out.println("...l");
        	            		   listkuanyamonew.add(ia2[i]);
        	            	   }else{
        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
        	            	   }
        	            	}
        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
  
        	        		List<Parameterqiye> yahuaendList = null;
        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
        	        		String yaheight=null;
        	        		for(int i=0;i<yahuaendList.size();i++){
        	        			 yaheight=yahuaendList.get(i).getChichun();
        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
        	    				
        	    			}
        	        
        	        		int     yaheiheight2= Integer.parseInt(yaheight);
        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
        	        		if(kk>0){
        	        			if(EndOneXingmen=="putong"){
        	        				System.out.println("压花高度在门高度之内，是个普通的门");
        	        			}else if(EndOneXingmen=="qichuang"){
        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
        	        				//在这里  门高度-压花-低挡-中档;
        	       	List<Parameterqiye> yahuadang1List = null;
        	    	List<Parameterqiye> yahuadang2List = null;
        	    	String type="门档";
        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
        	       	String zhongdang=null;
            		for(int i=0;i<yahuadang1List.size();i++){
            			zhongdang=yahuadang1List.get(i).getChichun();
        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
        				
        			}
            		int     zhongdang2= Integer.parseInt(zhongdang);
            		String didang2=null;
            		for(int i=0;i<yahuadang2List.size();i++){
            			didang2=yahuadang2List.get(i).getChichun();
        				System.out.println("循环中获取是 底档高度为的:"+didang2);
        			}
            		int  didang= Integer.parseInt(didang2);
        	        qichuangheightdan=kk-zhongdang2-didang;
        	       if(qichuangheightdan<0){qichuangheightdan=0;}
        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
        	        			}
        	        		}
        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
        	        	    		 +"1获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
        	        	    		 "获取门的名称："+EndOneNamemen+
        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
        	        	    		 "门的普通或气窗"+EndOneXingmen+
        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
        	        		 
    				
    			} //普通压模 (单门，子母门)
    			
    			 String dataleft=null;
    			 if(EndOneNamemen.equals("子母门")&& EndOneXingmen.equals("putong")&&listba.size()==1){
    				 System.out.println("子母门普通");
    				  dataleft="3";
    			 }else{
    				 
    				 System.out.println("单门子母门门头");
    				  dataleft="4";
    			 }
    			
    			
    			 if(2==2){
    				 
    				System.out.println("———————————————————2—————————————————");
    				listkuanyamo = parameterqiyeBo.findBynameAndType("出头模", "门压花尺寸");
        			for(int i=0;i<listkuanyamo.size();i++){
        				String sssl=listkuanyamo.get(i).getChichunk();
        				listhh22.add(sssl);
        			}
        			String[] array2 = (String[])listhh22.toArray(new String[listhh22.size()]);  
        	        int[] ia2=new int[array2.length];
        	          List listkuanyamonew2 = new ArrayList();
        	            for(int i=0;i<array2.length;i++){
        	            	ia2[i]=Integer.valueOf(array2[i]);
        	            	   int  cfo=yahuakuanMen-ia2[i];
        	            	   if(cfo>0){
        	            		   System.out.println("...l");
        	            		   listkuanyamonew2.add(ia2[i]);
        	            	   }else{
        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
        	            	   }
        	            	}
        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew2));

        	        		List<Parameterqiye> yahuaendList = null;
        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew2));
        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
        	        		String yaheight=null;
        	        		for(int i=0;i<yahuaendList.size();i++){
        	        			 yaheight=yahuaendList.get(i).getChichun();
        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
        	    				
        	    			}
        	        		int     yaheiheight2= Integer.parseInt(yaheight);
        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
        	        		if(kk>0){
        	        			if(EndOneXingmen=="putong"){
        	        				System.out.println("压花高度在门高度之内，是个普通的门");
        	        			}else if(EndOneXingmen=="qichuang"){
        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
        	        				//在这里  门高度-压花-低挡-中档;
        	       	List<Parameterqiye> yahuadang1List = null;
        	    	List<Parameterqiye> yahuadang2List = null;
        	    	String type="门档";
        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
        	       	String zhongdang=null;
            		for(int i=0;i<yahuadang1List.size();i++){
            			zhongdang=yahuadang1List.get(i).getChichun();
        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
        			}
            		int     zhongdang2= Integer.parseInt(zhongdang);
            		String didang2=null;
            		for(int i=0;i<yahuadang2List.size();i++){
            			didang2=yahuadang2List.get(i).getChichun();
        				System.out.println("循环中获取是 底档高度为的:"+didang2);
        			}
            		int  didang= Integer.parseInt(didang2);
        	        qichuangheightdan=kk-zhongdang2-didang;
        	       if(qichuangheightdan<0){qichuangheightdan=0;}
        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
        	        			}
        	        		}
        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
        	        	    		 "获取门的名称："+EndOneNamemen+
        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
        	        	    		 "门的普通或气窗"+EndOneXingmen+
        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
    			}
    		//	 String dataleft=null;
    			 if(EndOneNamemen.equals("子母门")&& EndOneXingmen.equals("putong")&&listba.size()==1){
    				 System.out.println("子母门普通");
    				  dataleft="3";
    			 }else{
    				 
    				 System.out.println("单门子母门门头");
    				  dataleft="4";
    			 }
    		
    			 if(3==3){
    				System.out.println("——————————————————3—————————————————");
    			
    				listkuanyamo = parameterqiyeBo.findBynameAndType("子门", "门压花尺寸");
        			for(int i=0;i<listkuanyamo.size();i++){
        				String sssl=listkuanyamo.get(i).getChichunk();
        				listhh23.add(sssl);
        			}
        			String[] array2 = (String[])listhh23.toArray(new String[listhh23.size()]);  
        	        int[] ia2=new int[array2.length];
        	          List listkuanyamonew2 = new ArrayList();
        	            for(int i=0;i<array2.length;i++){
        	            	ia2[i]=Integer.valueOf(array2[i]);
        	            	   int  cfo=yahuakuanMen-ia2[i];
        	            	   if(cfo>0){
        	            		 //  System.out.println("...l");
        	            		   listkuanyamonew2.add(ia2[i]);
        	            	   }else{
        	            		 //  System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
        	            	   }
        	            	}
        	        		//System.out.println("子门获取最大值的为："+Collections.max(listkuanyamonew2));

        	        		List<Parameterqiye> yahuaendList = null;
        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew2));
        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
        	        		String yaheight=null;
        	        		for(int i=0;i<yahuaendList.size();i++){
        	        			 yaheight=yahuaendList.get(i).getChichun();
        	    				//System.out.println("循环中获取是子门尺寸高度为的:"+yaheight);
        	    			}
        	        		int     yaheiheight2= Integer.parseInt(yaheight);
        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
         //   System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
        	        		if(kk>0){
        	        			if(EndOneXingmen=="putong"){
        	        				System.out.println("压花高度在门高度之内，是个普通的门");
        	        			}else if(EndOneXingmen=="qichuang"){
        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
        	        				//在这里  门高度-压花-低挡-中档;
        	       	List<Parameterqiye> yahuadang1List = null;
        	    	List<Parameterqiye> yahuadang2List = null;
        	    	String type="门档";
        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
        	       	String zhongdang=null;
            		for(int i=0;i<yahuadang1List.size();i++){
            			zhongdang=yahuadang1List.get(i).getChichun();
        				//System.out.println("循环中获取是 中档高度为的:"+zhongdang);
        			}
            		int     zhongdang2= Integer.parseInt(zhongdang);
            		String didang2=null;
            		for(int i=0;i<yahuadang2List.size();i++){
            			didang2=yahuadang2List.get(i).getChichun();
        				//System.out.println("循环中获取是 底档高度为的:"+didang2);
        			}
            		int  didang= Integer.parseInt(didang2);
        	        qichuangheightdan=kk-zhongdang2-didang;
        	       if(qichuangheightdan<0){qichuangheightdan=0;}
        	     //   System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
        	        			}
        	        		}
        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
        	        	    		 "获取门的名称："+EndOneNamemen+
        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
        	        	    		 "门的普通或气窗"+EndOneXingmen+
        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
    			}
    			 //子母门， 子门结束*
    		//	 String dataleft=null;
    			 if(EndOneNamemen.equals("子母门")&& EndOneXingmen.equals("putong")&&listba.size()==1){
    				 System.out.println("子母门普通");
    				  dataleft="3";
    			 }else{
    				 
    				 System.out.println("单门子母门门头");
    				  dataleft="4";
    			 }
				
				
				this.getDoorJsonData(request, response, EndOneNamemen,dataleft);
    			
    		}
            
    		if(EndOneNamemen.equals("双开门")){
    			//
    			int	qichuangheightdan = 0;  //
    			menshouWidth=menbashouwidthin+menbashouwidthin;
    			int yahuakuanMen=menEndWidthmen-menshouWidth;
    			     System.out.println("双开门"+menshouWidth+"压花的宽为:"+yahuakuanMen);
    			     if(1==1){
    	    				listkuanyamo = parameterqiyeBo.findBynameAndType("普通压模", "门压花尺寸");
    	        			for(int i=0;i<listkuanyamo.size();i++){
    	        				String sssl=listkuanyamo.get(i).getChichunk();
    	        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
    	        				listhh31.add(sssl);
    	        			}
    	        			String[] array2 = (String[])listhh31.toArray(new String[listhh31.size()]);  
    	        	        int[] ia2=new int[array2.length];
    	        	          List listkuanyamonew = new ArrayList();
    	        	            for(int i=0;i<array2.length;i++){
    	        	            	ia2[i]=Integer.valueOf(array2[i]);
    	        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
    	        	            	   int  cfo=yahuakuanMen-ia2[i];
    	        	            	   if(cfo>0){
    	        	            		   System.out.println("...l");
    	        	            		   listkuanyamonew.add(ia2[i]);
    	        	            	   }else{
    	        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
    	        	            	   }
    	        	            	}
    	        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
    	  
    	        	        		List<Parameterqiye> yahuaendList = null;
    	        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
    	        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
    	        	        		String yaheight=null;
    	        	        		for(int i=0;i<yahuaendList.size();i++){
    	        	        			 yaheight=yahuaendList.get(i).getChichun();
    	        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
    	        	    				
    	        	    			}
    	        	        
    	        	        		int     yaheiheight2= Integer.parseInt(yaheight);
    	        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
    	        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
    	            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
    	        	        		if(kk>0){
    	        	        			if(EndOneXingmen=="putong"){
    	        	        				System.out.println("压花高度在门高度之内，是个普通的门");
    	        	        			}else if(EndOneXingmen=="qichuang"){
    	        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
    	        	        				//在这里  门高度-压花-低挡-中档;
    	        	       	List<Parameterqiye> yahuadang1List = null;
    	        	    	List<Parameterqiye> yahuadang2List = null;
    	        	    	String type="门档";
    	        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
    	        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
    	        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
    	        	       	String zhongdang=null;
    	            		for(int i=0;i<yahuadang1List.size();i++){
    	            			zhongdang=yahuadang1List.get(i).getChichun();
    	        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
    	        				
    	        			}
    	            		int     zhongdang2= Integer.parseInt(zhongdang);
    	            		String didang2=null;
    	            		for(int i=0;i<yahuadang2List.size();i++){
    	            			didang2=yahuadang2List.get(i).getChichun();
    	        				System.out.println("循环中获取是 底档高度为的:"+didang2);
    	        			}
    	            		int  didang= Integer.parseInt(didang2);
    	        	        qichuangheightdan=kk-zhongdang2-didang;
    	        	       if(qichuangheightdan<0){qichuangheightdan=0;}
    	        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
    	        	        			}
    	        	        		}
    	        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
    	        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
    	        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
    	        	        	    		 "获取门的名称："+EndOneNamemen+
    	        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
    	        	        	    		 "门的普通或气窗"+EndOneXingmen+
    	        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
    	    				
    	    			} 
    			     String dataleft=null;
    			     if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("putong")){
        				 System.out.println("双开门+门头");
        				  dataleft="5";
        			 }else if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("qichuang")){
        				 
        				 System.out.println("双开门+气窗");
        				  dataleft="6";
        			 }
    			     
    			     if(1==1){
 	    				listkuanyamo = parameterqiyeBo.findBynameAndType("出头模", "门压花尺寸");
 	        			for(int i=0;i<listkuanyamo.size();i++){
 	        				String sssl=listkuanyamo.get(i).getChichunk();
 	        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
 	        				listhh32.add(sssl);
 	        			}
 	        			String[] array2 = (String[])listhh32.toArray(new String[listhh32.size()]);  
 	        	        int[] ia2=new int[array2.length];
 	        	          List listkuanyamonew = new ArrayList();
 	        	            for(int i=0;i<array2.length;i++){
 	        	            	ia2[i]=Integer.valueOf(array2[i]);
 	        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
 	        	            	   int  cfo=yahuakuanMen-ia2[i];
 	        	            	   if(cfo>0){
 	        	            		   System.out.println("...l");
 	        	            		   listkuanyamonew.add(ia2[i]);
 	        	            	   }else{
 	        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
 	        	            	   }
 	        	            	}
 	        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
 	  
 	        	        		List<Parameterqiye> yahuaendList = null;
 	        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
 	        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
 	        	        		String yaheight=null;
 	        	        		for(int i=0;i<yahuaendList.size();i++){
 	        	        			 yaheight=yahuaendList.get(i).getChichun();
 	        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
 	        	    				
 	        	    			}
 	        	        
 	        	        		int     yaheiheight2= Integer.parseInt(yaheight);
 	        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
 	        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
 	            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
 	        	        		if(kk>0){
 	        	        			if(EndOneXingmen=="putong"){
 	        	        				System.out.println("压花高度在门高度之内，是个普通的门");
 	        	        			}else if(EndOneXingmen=="qichuang"){
 	        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
 	        	        				//在这里  门高度-压花-低挡-中档;
 	        	       	List<Parameterqiye> yahuadang1List = null;
 	        	    	List<Parameterqiye> yahuadang2List = null;
 	        	    	String type="门档";
 	        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
 	        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
 	        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
 	        	       	String zhongdang=null;
 	            		for(int i=0;i<yahuadang1List.size();i++){
 	            			zhongdang=yahuadang1List.get(i).getChichun();
 	        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
 	        				
 	        			}
 	            		int     zhongdang2= Integer.parseInt(zhongdang);
 	            		String didang2=null;
 	            		for(int i=0;i<yahuadang2List.size();i++){
 	            			didang2=yahuadang2List.get(i).getChichun();
 	        				System.out.println("循环中获取是 底档高度为的:"+didang2);
 	        			}
 	            		int  didang= Integer.parseInt(didang2);
 	        	        qichuangheightdan=kk-zhongdang2-didang;
 	        	       if(qichuangheightdan<0){qichuangheightdan=0;}
 	        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
 	        	        			}
 	        	        		}
 	        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
 	        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
 	        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
 	        	        	    		 "获取门的名称："+EndOneNamemen+
 	        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
 	        	        	    		 "门的普通或气窗"+EndOneXingmen+
 	        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
 	    				
 	    			} 
    	//		     String dataleft=null;
    			     if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("putong")){
        				 System.out.println("双开门+门头");
        				  dataleft="5";
        			 }else if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("qichuang")){
        				 
        				 System.out.println("双开门+气窗");
        				  dataleft="6";
        			 }
    			    //*******************小门对开*******************
    			     
    			     if(1==1){
  	    				listkuanyamo = parameterqiyeBo.findBynameAndType("出头模", "门压花尺寸");
  	        			for(int i=0;i<listkuanyamo.size();i++){
  	        				String sssl=listkuanyamo.get(i).getChichunk();
  	        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
  	        				listhh33.add(sssl);
  	        			}
  	        			String[] array2 = (String[])listhh33.toArray(new String[listhh33.size()]);  
  	        	        int[] ia2=new int[array2.length];
  	        	          List listkuanyamonew = new ArrayList();
  	        	            for(int i=0;i<array2.length;i++){
  	        	            	ia2[i]=Integer.valueOf(array2[i]);
  	        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
  	        	            	   int  cfo=yahuakuanMen-ia2[i];
  	        	            	   if(cfo>0){
  	        	            		   System.out.println("...l");
  	        	            		   listkuanyamonew.add(ia2[i]);
  	        	            	   }else{
  	        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
  	        	            	   }
  	        	            	}
  	        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
  	  
  	        	        		List<Parameterqiye> yahuaendList = null;
  	        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
  	        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
  	        	        		String yaheight=null;
  	        	        		for(int i=0;i<yahuaendList.size();i++){
  	        	        			 yaheight=yahuaendList.get(i).getChichun();
  	        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
  	        	    				
  	        	    			}
  	        	        
  	        	        		int     yaheiheight2= Integer.parseInt(yaheight);
  	        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
  	        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
  	            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
  	        	        		if(kk>0){
  	        	        			if(EndOneXingmen=="putong"){
  	        	        				System.out.println("压花高度在门高度之内，是个普通的门");
  	        	        			}else if(EndOneXingmen=="qichuang"){
  	        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
  	        	        				//在这里  门高度-压花-低挡-中档;
  	        	       	List<Parameterqiye> yahuadang1List = null;
  	        	    	List<Parameterqiye> yahuadang2List = null;
  	        	    	String type="门档";
  	        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
  	        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
  	        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
  	        	       	String zhongdang=null;
  	            		for(int i=0;i<yahuadang1List.size();i++){
  	            			zhongdang=yahuadang1List.get(i).getChichun();
  	        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
  	        				
  	        			}
  	            		int     zhongdang2= Integer.parseInt(zhongdang);
  	            		String didang2=null;
  	            		for(int i=0;i<yahuadang2List.size();i++){
  	            			didang2=yahuadang2List.get(i).getChichun();
  	        				System.out.println("循环中获取是 底档高度为的:"+didang2);
  	        			}
  	            		int  didang= Integer.parseInt(didang2);
  	        	        qichuangheightdan=kk-zhongdang2-didang;
  	        	       if(qichuangheightdan<0){qichuangheightdan=0;}
  	        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
  	        	        			}
  	        	        		}
  	        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
  	        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
  	        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
  	        	        	    		 "获取门的名称："+EndOneNamemen+
  	        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
  	        	        	    		 "门的普通或气窗"+EndOneXingmen+
  	        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
  	    				
  	    			} 
    	//		     String dataleft=null;
    			     if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("putong")){
        				 System.out.println("双开门+门头");
        				  dataleft="5";
        			 }else if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("qichuang")){
        				 
        				 System.out.println("双开门+气窗");
        				  dataleft="6";
        			 }
    			     //*************************广西加长****************************
    			     if(1==1){
   	    				listkuanyamo = parameterqiyeBo.findBynameAndType("出头模", "门压花尺寸");
   	        			for(int i=0;i<listkuanyamo.size();i++){
   	        				String sssl=listkuanyamo.get(i).getChichunk();
   	        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
   	        				listhh34.add(sssl);
   	        			}
   	        			String[] array2 = (String[])listhh34.toArray(new String[listhh34.size()]);  
   	        	        int[] ia2=new int[array2.length];
   	        	          List listkuanyamonew = new ArrayList();
   	        	            for(int i=0;i<array2.length;i++){
   	        	            	ia2[i]=Integer.valueOf(array2[i]);
   	        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
   	        	            	   int  cfo=yahuakuanMen-ia2[i];
   	        	            	   if(cfo>0){
   	        	            		   System.out.println("...l");
   	        	            		   listkuanyamonew.add(ia2[i]);
   	        	            	   }else{
   	        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
   	        	            	   }
   	        	            	}
   	        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
   	  
   	        	        		List<Parameterqiye> yahuaendList = null;
   	        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
   	        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
   	        	        		String yaheight=null;
   	        	        		for(int i=0;i<yahuaendList.size();i++){
   	        	        			 yaheight=yahuaendList.get(i).getChichun();
   	        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
   	        	    				
   	        	    			}
   	        	        
   	        	        		int     yaheiheight2= Integer.parseInt(yaheight);
   	        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
   	        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
   	            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
   	        	        		if(kk>0){
   	        	        			if(EndOneXingmen=="putong"){
   	        	        				System.out.println("压花高度在门高度之内，是个普通的门");
   	        	        			}else if(EndOneXingmen=="qichuang"){
   	        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
   	        	        				//在这里  门高度-压花-低挡-中档;
   	        	       	List<Parameterqiye> yahuadang1List = null;
   	        	    	List<Parameterqiye> yahuadang2List = null;
   	        	    	String type="门档";
   	        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
   	        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
   	        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
   	        	       	String zhongdang=null;
   	            		for(int i=0;i<yahuadang1List.size();i++){
   	            			zhongdang=yahuadang1List.get(i).getChichun();
   	        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
   	        				
   	        			}
   	            		int     zhongdang2= Integer.parseInt(zhongdang);
   	            		String didang2=null;
   	            		for(int i=0;i<yahuadang2List.size();i++){
   	            			didang2=yahuadang2List.get(i).getChichun();
   	        				System.out.println("循环中获取是 底档高度为的:"+didang2);
   	        			}
   	            		int  didang= Integer.parseInt(didang2);
   	        	        qichuangheightdan=kk-zhongdang2-didang;
   	        	       if(qichuangheightdan<0){qichuangheightdan=0;}
   	        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
   	        	        			}
   	        	        		}
   	        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
   	        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
   	        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
   	        	        	    		 "获取门的名称："+EndOneNamemen+
   	        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
   	        	        	    		 "门的普通或气窗"+EndOneXingmen+
   	        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
   	    				
   	    			}
    			   //  this.getDoorJsonData(request, response, EndOneNamemen);
    			     
    //			    String dataleft=null;
    			     if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("putong")){
        				 System.out.println("双开门+门头");
        				  dataleft="5";
        			 }else if(EndOneNamemen.equals("双开门")&& EndOneXingmen.equals("qichuang")){
        				 
        				 System.out.println("双开门+气窗");
        				  dataleft="6";
        			 }
    			     // 双开+  合金铜门  ，没办法在这里去定义 ，只好去
    			 	//String dataleft = null;
					this.getDoorJsonData(request, response, EndOneNamemen, dataleft);
    			     
    			     
    		}
			if(EndOneNamemen.equals("四开子母门")){
				int	qichuangheightdan = 0;
			    			menshouWidth=menbashouwidthin+menbashouwidthin;
			    			int yahuakuanMen=menEndWidthmen-menshouWidth;
			      System.out.println("四开子母门"+menshouWidth+"压花的宽为:"+yahuakuanMen);
			      //*************************广西加长***************************
 			     if(1==1){
	    				listkuanyamo = parameterqiyeBo.findBynameAndType("普通压模", "门压花尺寸");
	        			for(int i=0;i<listkuanyamo.size();i++){
	        				String sssl=listkuanyamo.get(i).getChichunk();
	        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
	        				listhh41.add(sssl);
	        			}
	        			String[] array2 = (String[])listhh41.toArray(new String[listhh41.size()]);  
	        	        int[] ia2=new int[array2.length];
	        	          List listkuanyamonew = new ArrayList();
	        	            for(int i=0;i<array2.length;i++){
	        	            	ia2[i]=Integer.valueOf(array2[i]);
	        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
	        	            	   int  cfo=yahuakuanMen-ia2[i];
	        	            	   if(cfo>0){
	        	            		   System.out.println("...l");
	        	            		   listkuanyamonew.add(ia2[i]);
	        	            	   }else{
	        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
	        	            	   }
	        	            	}
	        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
	  
	        	        		List<Parameterqiye> yahuaendList = null;
	        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
	        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
	        	        		String yaheight=null;
	        	        		for(int i=0;i<yahuaendList.size();i++){
	        	        			 yaheight=yahuaendList.get(i).getChichun();
	        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
	        	    				
	        	    			}
	        	        
	        	        		int     yaheiheight2= Integer.parseInt(yaheight);
	        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
	        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
	            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
	        	        		if(kk>0){
	        	        			if(EndOneXingmen=="putong"){
	        	        				System.out.println("压花高度在门高度之内，是个普通的门");
	        	        			}else if(EndOneXingmen=="qichuang"){
	        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
	        	        				//在这里  门高度-压花-低挡-中档;
	        	       	List<Parameterqiye> yahuadang1List = null;
	        	    	List<Parameterqiye> yahuadang2List = null;
	        	    	String type="门档";
	        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
	        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
	        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
	        	       	String zhongdang=null;
	            		for(int i=0;i<yahuadang1List.size();i++){
	            			zhongdang=yahuadang1List.get(i).getChichun();
	        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
	        				
	        			}
	            		int     zhongdang2= Integer.parseInt(zhongdang);
	            		String didang2=null;
	            		for(int i=0;i<yahuadang2List.size();i++){
	            			didang2=yahuadang2List.get(i).getChichun();
	        				System.out.println("循环中获取是 底档高度为的:"+didang2);
	        			}
	            		int  didang= Integer.parseInt(didang2);
	        	        qichuangheightdan=kk-zhongdang2-didang;
	        	       if(qichuangheightdan<0){qichuangheightdan=0;}
	        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
	        	        			}
	        	        		}
	        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
	        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
	        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
	        	        	    		 "获取门的名称："+EndOneNamemen+
	        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
	        	        	    		 "门的普通或气窗"+EndOneXingmen+
	        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
	    				
	    			} 
 			    String dataleft=null;
			     if(EndOneNamemen.equals("四开子母门")&& EndOneXingmen.equals("putong")){
    				 System.out.println("四开子母门+门头");
    				  dataleft="8";
    			 }else if(EndOneNamemen.equals("四开子母门")&& EndOneXingmen.equals("qichuang")){
    				 
    				 System.out.println("四开子母门+气窗");
    				  dataleft="9";
    			 }
 			    //*************************广西加长***************************
			     if(1==1){
	    				listkuanyamo = parameterqiyeBo.findBynameAndType("出头模", "门压花尺寸");
	        			for(int i=0;i<listkuanyamo.size();i++){
	        				String sssl=listkuanyamo.get(i).getChichunk();
	        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
	        				listhh42.add(sssl);
	        			}
	        			String[] array2 = (String[])listhh42.toArray(new String[listhh42.size()]);  
	        	        int[] ia2=new int[array2.length];
	        	          List listkuanyamonew = new ArrayList();
	        	            for(int i=0;i<array2.length;i++){
	        	            	ia2[i]=Integer.valueOf(array2[i]);
	        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
	        	            	   int  cfo=yahuakuanMen-ia2[i];
	        	            	   if(cfo>0){
	        	            		   System.out.println("...l");
	        	            		   listkuanyamonew.add(ia2[i]);
	        	            	   }else{
	        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
	        	            	   }
	        	            	}
	        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
	  
	        	        		List<Parameterqiye> yahuaendList = null;
	        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
	        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
	        	        		String yaheight=null;
	        	        		for(int i=0;i<yahuaendList.size();i++){
	        	        			 yaheight=yahuaendList.get(i).getChichun();
	        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
	        	    				
	        	    			}
	        	        
	        	        		int     yaheiheight2= Integer.parseInt(yaheight);
	        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
	        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
	            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
	        	        		if(kk>0){
	        	        			if(EndOneXingmen=="putong"){
	        	        				System.out.println("压花高度在门高度之内，是个普通的门");
	        	        			}else if(EndOneXingmen=="qichuang"){
	        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
	        	        				//在这里  门高度-压花-低挡-中档;
	        	       	List<Parameterqiye> yahuadang1List = null;
	        	    	List<Parameterqiye> yahuadang2List = null;
	        	    	String type="门档";
	        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
	        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
	        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
	        	       	String zhongdang=null;
	            		for(int i=0;i<yahuadang1List.size();i++){
	            			zhongdang=yahuadang1List.get(i).getChichun();
	        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
	        				
	        			}
	            		int     zhongdang2= Integer.parseInt(zhongdang);
	            		String didang2=null;
	            		for(int i=0;i<yahuadang2List.size();i++){
	            			didang2=yahuadang2List.get(i).getChichun();
	        				System.out.println("循环中获取是 底档高度为的:"+didang2);
	        			}
	            		int  didang= Integer.parseInt(didang2);
	        	        qichuangheightdan=kk-zhongdang2-didang;
	        	       if(qichuangheightdan<0){qichuangheightdan=0;}
	        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
	        	        			}
	        	        		}
	        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
	        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
	        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
	        	        	    		 "获取门的名称："+EndOneNamemen+
	        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
	        	        	    		 "门的普通或气窗"+EndOneXingmen+
	        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
	    				
	    			} 
			     
		//	     String dataleft=null;
 			     if(EndOneNamemen.equals("四开子母门")&& EndOneXingmen.equals("putong")){
     				 System.out.println("四开子母门+门头");
     				  dataleft="8";
     			 }else if(EndOneNamemen.equals("四开子母门")&& EndOneXingmen.equals("qichuang")){
     				 
     				 System.out.println("四开子母门+气窗");
     				  dataleft="9";
     			 }
			     //*************************广西加长****************************
			     if(1==1){
	    				listkuanyamo = parameterqiyeBo.findBynameAndType("子门", "门压花尺寸");
	        			for(int i=0;i<listkuanyamo.size();i++){
	        				String sssl=listkuanyamo.get(i).getChichunk();
	        				System.out.println("循环中获取是普通压模尺寸的:"+sssl);
	        				listhh43.add(sssl);
	        			}
	        			String[] array2 = (String[])listhh43.toArray(new String[listhh43.size()]);  
	        	        int[] ia2=new int[array2.length];
	        	          List listkuanyamonew = new ArrayList();
	        	            for(int i=0;i<array2.length;i++){
	        	            	ia2[i]=Integer.valueOf(array2[i]);
	        	            	   System.out.println("#数组中获得的数值 为普通压模"+ia2[i]); 
	        	            	   int  cfo=yahuakuanMen-ia2[i];
	        	            	   if(cfo>0){
	        	            		   System.out.println("...l");
	        	            		   listkuanyamonew.add(ia2[i]);
	        	            	   }else{
	        	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
	        	            	   }
	        	            	}
	        	        		System.out.println("普通压模获取最大值的为："+Collections.max(listkuanyamonew));
	  
	        	        		List<Parameterqiye> yahuaendList = null;
	        	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
	        	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
	        	        		String yaheight=null;
	        	        		for(int i=0;i<yahuaendList.size();i++){
	        	        			 yaheight=yahuaendList.get(i).getChichun();
	        	    				System.out.println("循环中获取是普通压模尺寸高度为的:"+yaheight);
	        	    				
	        	    			}
	        	        
	        	        		int     yaheiheight2= Integer.parseInt(yaheight);
	        	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
	        	        		int  kk=EndOneheightmenfillya-yaheiheight2;
	            System.out.println("门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk);
	        	        		if(kk>0){
	        	        			if(EndOneXingmen=="putong"){
	        	        				System.out.println("压花高度在门高度之内，是个普通的门");
	        	        			}else if(EndOneXingmen=="qichuang"){
	        	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
	        	        				//在这里  门高度-压花-低挡-中档;
	        	       	List<Parameterqiye> yahuadang1List = null;
	        	    	List<Parameterqiye> yahuadang2List = null;
	        	    	String type="门档";
	        	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
	        	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
	        	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
	        	       	String zhongdang=null;
	            		for(int i=0;i<yahuadang1List.size();i++){
	            			zhongdang=yahuadang1List.get(i).getChichun();
	        				System.out.println("循环中获取是 中档高度为的:"+zhongdang);
	        				
	        			}
	            		int     zhongdang2= Integer.parseInt(zhongdang);
	            		String didang2=null;
	            		for(int i=0;i<yahuadang2List.size();i++){
	            			didang2=yahuadang2List.get(i).getChichun();
	        				System.out.println("循环中获取是 底档高度为的:"+didang2);
	        			}
	            		int  didang= Integer.parseInt(didang2);
	        	        qichuangheightdan=kk-zhongdang2-didang;
	        	       if(qichuangheightdan<0){qichuangheightdan=0;}
	        	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
	        	        			}
	        	        		}
	        	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
	        	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
	        	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
	        	        	    		 "获取门的名称："+EndOneNamemen+
	        	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
	        	        	    		 "门的普通或气窗"+EndOneXingmen+
	        	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
	    				
	    			} 
			    // this.getDoorJsonData(request, response, EndOneNamemen);
			     
 		//	    String dataleft=null;
 			     if(EndOneNamemen.equals("四开子母门")&& EndOneXingmen.equals("putong")){
     				 System.out.println("四开子母门+门头");
     				  dataleft="8";
     			 }else if(EndOneNamemen.equals("四开子母门")&& EndOneXingmen.equals("qichuang")){
     				 
     				 System.out.println("四开子母门+气窗");
     				  dataleft="9";
     			 }
 			      //  四开+气窗   +门头


 			 
 			    
 			

				this.getDoorJsonData(request, response, EndOneNamemen,dataleft);
			   
			 }
			
	if(EndOneNamemen.equals("四开门")){
				int	qichuangheightdan = 0;
				menshouWidth=menbashouwidthin+menbashouwidthin;
				int yahuakuanMen=menEndWidthmen-menshouWidth;
	     if(1==1){
			listkuanyamo = parameterqiyeBo.findBynameAndType("普通压模", "门压花尺寸");
			for(int i=0;i<listkuanyamo.size();i++){
				String sssl=listkuanyamo.get(i).getChichunk();
				listhh51.add(sssl);
			}
			String[] array2 = (String[])listhh51.toArray(new String[listhh51.size()]);  
	        int[] ia2=new int[array2.length];
	          List listkuanyamonew = new ArrayList();
	            for(int i=0;i<array2.length;i++){
	            	ia2[i]=Integer.valueOf(array2[i]);
	            	   int  cfo=yahuakuanMen-ia2[i];
	            	   if(cfo>0){
	            		   System.out.println("...l");
	            		   listkuanyamonew.add(ia2[i]);
	            	   }else{
	            	   }
	            	}
	        		List<Parameterqiye> yahuaendList = null;
	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
	        		String yaheight=null;
	        		for(int i=0;i<yahuaendList.size();i++){
	        			 yaheight=yahuaendList.get(i).getChichun();
	    			}
	        
	        		int     yaheiheight2= Integer.parseInt(yaheight);
	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
	        		int  kk=EndOneheightmenfillya-yaheiheight2;
	        		if(kk>0){
	        			if(EndOneXingmen=="putong"){
	        			}else if(EndOneXingmen=="qichuang"){
	       	List<Parameterqiye> yahuadang1List = null;
	    	List<Parameterqiye> yahuadang2List = null;
	    	String type="门档";
	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
	       	String zhongdang=null;
    		for(int i=0;i<yahuadang1List.size();i++){
    			zhongdang=yahuadang1List.get(i).getChichun();
				
			}
    		int     zhongdang2= Integer.parseInt(zhongdang);
    		String didang2=null;
    		for(int i=0;i<yahuadang2List.size();i++){
    			didang2=yahuadang2List.get(i).getChichun();
			}
    		int  didang= Integer.parseInt(didang2);
	        qichuangheightdan=kk-zhongdang2-didang;
	       if(qichuangheightdan<0){qichuangheightdan=0;}
	        System.out.println("四开门气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
	        			}
	        		}
	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
	        	    		 "获取门的名称："+EndOneNamemen+
	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
	        	    		 "门的普通或气窗"+EndOneXingmen+
	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
			
		} 
	     String dataleft = null;
	  	
	     if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("putong")){
			 System.out.println("四开门+门头");
			  dataleft="11";
		 }else if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("qichuang")){
			 
			 System.out.println("四开门+气窗");
			  dataleft="12";
		 }
	    //*************************广西加长****************************
     if(1==1){
			listkuanyamo = parameterqiyeBo.findBynameAndType("出头模", "门压花尺寸");
			for(int i=0;i<listkuanyamo.size();i++){
				String sssl=listkuanyamo.get(i).getChichunk();
				listhh52.add(sssl);
			}
			String[] array2 = (String[])listhh52.toArray(new String[listhh52.size()]);  
	        int[] ia2=new int[array2.length];
	          List listkuanyamonew = new ArrayList();
	            for(int i=0;i<array2.length;i++){
	            	ia2[i]=Integer.valueOf(array2[i]);
	            	   int  cfo=yahuakuanMen-ia2[i];
	            	   if(cfo>0){
	            		   listkuanyamonew.add(ia2[i]);
	            	   }else{
	            	   }
	            	}
	        		List<Parameterqiye> yahuaendList = null;
	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
	        		String yaheight=null;
	        		for(int i=0;i<yahuaendList.size();i++){
	        			 yaheight=yahuaendList.get(i).getChichun();
	    			}
	        		int     yaheiheight2= Integer.parseInt(yaheight);
	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
	        		int  kk=EndOneheightmenfillya-yaheiheight2;
	        		if(kk>0){
	        			if(EndOneXingmen=="putong"){
	        			}else if(EndOneXingmen=="qichuang"){
	       	List<Parameterqiye> yahuadang1List = null;
	    	List<Parameterqiye> yahuadang2List = null;
	    	String type="门档";
	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
	       	String zhongdang=null;
    		for(int i=0;i<yahuadang1List.size();i++){
    			zhongdang=yahuadang1List.get(i).getChichun();
			}
    		int     zhongdang2= Integer.parseInt(zhongdang);
    		String didang2=null;
    		for(int i=0;i<yahuadang2List.size();i++){
    			didang2=yahuadang2List.get(i).getChichun();
			}
    		int  didang= Integer.parseInt(didang2);
	        qichuangheightdan=kk-zhongdang2-didang;
	       if(qichuangheightdan<0){qichuangheightdan=0;}
	        System.out.println("气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan);
	        			}
	        		}
	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
	        	    		 "获取门的名称："+EndOneNamemen+
	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
	        	    		 "门的普通或气窗"+EndOneXingmen+
	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
			
		} 
     
    // String dataleft = null;
  	
     if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("putong")){
		 System.out.println("四开门+门头");
		  dataleft="11";
	 }else if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("qichuang")){
		 
		 System.out.println("四开门+气窗");
		  dataleft="12";
	 }
     //*************************广西加长****************************
     if(1==1){
			listkuanyamo = parameterqiyeBo.findBynameAndType("小门对开", "门压花尺寸");
			for(int i=0;i<listkuanyamo.size();i++){
				String sssl=listkuanyamo.get(i).getChichunk();
				listhh53.add(sssl);
			}
			String[] array2 = (String[])listhh53.toArray(new String[listhh53.size()]);  
	        int[] ia2=new int[array2.length];
	          List listkuanyamonew = new ArrayList();
	            for(int i=0;i<array2.length;i++){
	            	ia2[i]=Integer.valueOf(array2[i]);
	            	   int  cfo=yahuakuanMen-ia2[i];
	            	   if(cfo>0){
	            		   listkuanyamonew.add(ia2[i]);
	            	   }else{
	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
	            	   }
	            	}
	        		List<Parameterqiye> yahuaendList = null;
	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
	        		String yaheight=null;
	        		for(int i=0;i<yahuaendList.size();i++){
	        			 yaheight=yahuaendList.get(i).getChichun();
	    				
	    			}
	        
	        		int     yaheiheight2= Integer.parseInt(yaheight);
	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
	        		int  kk=EndOneheightmenfillya-yaheiheight2;
	        		if(kk>0){
	        			if(EndOneXingmen=="putong"){
	        				System.out.println("压花高度在门高度之内，是个普通的门");
	        			}else if(EndOneXingmen=="qichuang"){
	        				System.out.println("压花高度在门高度之内，是个带气窗的门");
	        				//在这里  门高度-压花-低挡-中档;
	       	List<Parameterqiye> yahuadang1List = null;
	    	List<Parameterqiye> yahuadang2List = null;
	    	String type="门档";
	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
	       	String zhongdang=null;
    		for(int i=0;i<yahuadang1List.size();i++){
    			zhongdang=yahuadang1List.get(i).getChichun();
				
			}
    		int     zhongdang2= Integer.parseInt(zhongdang);
    		String didang2=null;
    		for(int i=0;i<yahuadang2List.size();i++){
    			didang2=yahuadang2List.get(i).getChichun();
			}
    		int  didang= Integer.parseInt(didang2);
	        qichuangheightdan=kk-zhongdang2-didang;
	       if(qichuangheightdan<0){qichuangheightdan=0;}
	        			}
	        		}
	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
	        	    		 "获取门的名称："+EndOneNamemen+
	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
	        	    		 "门的普通或气窗"+EndOneXingmen+
	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
			
		} 
    // String dataleft = null;
  	
     if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("putong")){
		 System.out.println("四开门+门头");
		  dataleft="11";
	 }else if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("qichuang")){
		 
		 System.out.println("四开门+气窗");
		  dataleft="12";
	 }
     
     //*************************广西加长****************************
     if(1==1){
			listkuanyamo = parameterqiyeBo.findBynameAndType("广西加长", "门压花尺寸");
			for(int i=0;i<listkuanyamo.size();i++){
				String sssl=listkuanyamo.get(i).getChichunk();
				listhh54.add(sssl);
			}
			String[] array2 = (String[])listhh54.toArray(new String[listhh54.size()]);  
	        int[] ia2=new int[array2.length];
	          List listkuanyamonew = new ArrayList();
	            for(int i=0;i<array2.length;i++){
	            	ia2[i]=Integer.valueOf(array2[i]);
	            	   int  cfo=yahuakuanMen-ia2[i];
	            	   if(cfo>0){
	            		   listkuanyamonew.add(ia2[i]);
	            	   }else{
	            		   System.out.println("...ll.. 门宽减去压花 为负值"+ia2[i]);
	            	   }
	            	}
	        		List<Parameterqiye> yahuaendList = null;
	        		String yamoendheshi=String.valueOf(Collections.max(listkuanyamonew));
	        		yahuaendList=parameterqiyeBo.findParameterqiyeByTypeAchik("门压花尺寸",yamoendheshi);
	        		String yaheight=null;
	        		for(int i=0;i<yahuaendList.size();i++){
	        			 yaheight=yahuaendList.get(i).getChichun();
	    			}
	        		int     yaheiheight2= Integer.parseInt(yaheight);
	        		int EndOneheightmenfillya = Integer.parseInt(EndOneheightmen);
	        		int  kk=EndOneheightmenfillya-yaheiheight2;
	        		if(kk>0){
	        			if(EndOneXingmen=="putong"){
	        			}else if(EndOneXingmen=="qichuang"){
	        				//在这里  门高度-压花-低挡-中档;
	       	List<Parameterqiye> yahuadang1List = null;
	    	List<Parameterqiye> yahuadang2List = null;
	    	String type="门档";
	    	String type2=new String(type.getBytes( "iso-8859-1"),"UTF-8" );
	       	yahuadang1List=parameterqiyeBo.findBynameAndType("中档","门档");
	       	yahuadang2List=parameterqiyeBo.findBynameAndType("底档","门档");
	       	String zhongdang=null;
    		for(int i=0;i<yahuadang1List.size();i++){
    			zhongdang=yahuadang1List.get(i).getChichun();
				
			}
    		int     zhongdang2= Integer.parseInt(zhongdang);
    		String didang2=null;
    		for(int i=0;i<yahuadang2List.size();i++){
    			didang2=yahuadang2List.get(i).getChichun();
			}
    		int  didang= Integer.parseInt(didang2);
	        qichuangheightdan=kk-zhongdang2-didang;
	       if(qichuangheightdan<0){qichuangheightdan=0;}
	        			}
	        		}
	        		 System.out.println("用户最终："+"气窗的门宽度为:"+EndOneWidthmen+"气窗的高度为:"+qichuangheightdan
	        	    		 +"门高度为:"+EndOneheightmenfillya+"压花高度为:"+yaheiheight2+"差值为:"+kk
	        	    		 +"获取门把手的尺寸"+menshouWidth+"压花的宽为:"+yahuakuanMen+
	        	    		 "获取门的名称："+EndOneNamemen+
	        	    		 "门把手的数量为"+listba.size()+"获取门把手的尺寸"+menbashouwidth+
	        	    		 "门的普通或气窗"+EndOneXingmen+
	        	    		 "获取到门名："+EndOneNamemen+" 宽"+"/"+EndOneWidthmen+" 高"+EndOneheightmen);	
	        		 
			
		} 
    // this.getDoorJsonData(request, response, EndOneNamemen);
     //四开子母+气窗   四开子母+门头
   
 	//String dataleft = null;
 	
	     if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("putong")){
			 System.out.println("四开门+门头");
			  dataleft="11";
		 }else if(EndOneNamemen.equals("四开门")&& EndOneXingmen.equals("qichuang")){
			 
			 System.out.println("四开门+气窗");
			  dataleft="12";
		 }
	this.getDoorJsonData(request, response, EndOneNamemen,dataleft);
     
	}
	/**************************************************压花判断完毕***************************************************************************/

	//Iterator itone = listone.iterator();
    		
    		/**************************  如果是单门  门把手 减去一个尺寸宽， 其他减去 2个宽度*  开始*************************************************/
        	 // 目前获取到的参数：门型(门型) 门putong ，最接近的尺寸：宽和高 
    		/**
    		 * 这里最终获得： 门(门型，宽，高，普通或气窗)。
			    1：用户输入 的是门洞尺寸。
			    2： 其中包含了：门把手，压花，中档，下档，气窗。
			           如果有子门，则在 设定比例获取子门，和母门的宽。
			      计算规则：
			            用户输入的宽-门把手的宽===（子门+母门）
			            压花  在 子门 和母门 之内。
			           气窗的宽度 ==门的宽度。
			           气窗的高度 ==门的高度--下档-中档- 门的尺寸。
			           
			    普通压模	出头模	小门对开	子门	广西加长     这个几个参数分别对应了 压花尺寸，
			    单门，子母门，双开门，四开子母门，四开门，  这个5个门是在后台配置死的，名称。
    		 * */
    		//1 判断是什么门,
    		//if(=="单门"||=="子母门"){总宽度-把手宽度=压花宽度 }
    		//if(=="双开多双门"){总宽度-把手宽度=压花宽度 }
    		// 压花宽度  去和 压花尺寸中的做匹配， 和(普通,出头莫的宽度去匹配最小值)
    		
    		
    		
    		
    		
    		/**************************  如果是单门  门把手 减去一个尺寸宽， 其他减去 2个宽度****结束**********************************************/
            
		String comment = request.getParameter("comment"); //judge, comment  品牌
		String proNumber = request.getParameter("proNumber"); //product manager 产品编号
		String doorType = request.getParameter("doorType"); //doorType  门型
		String thorax = request.getParameter("thorax");  // thorax, chest, hollow space, throat 樘数
		String hallheight = request.getParameter("hallheight");  //foyer, hallheight, hallway, vestibule  门体高
		String wallthickness = request.getParameter("wallthickness");  //  wallthickness 墙体厚度
		
		
//			int dLength2 = Integer.parseInt(dLength);
//			int dWidth2 = Integer.parseInt(dWidth);
//			String dHeight = request.getParameter("dHeight");
//			String dTrad = request.getParameter("dTrad");
		
			// 尺寸得到后，调用DoorType 方法,输出结果，得到 门 尺寸在那些门尺寸之间， 门压花尺寸。

			DoorType doortype = new DoorType();
			DoorBo doorBo = new DoorBo();
		
			doortype.setDoorBo(doorBo);
			JSONObject ret=new JSONObject(); 
			 String callback = request.getParameter("callback");
			//ret.put("total", 8);
			List<String> list = null;
			
			
		}
       }
		// List list2=null;
		// PrintWriter writer = response.getWriter();
		//
		// if (null != list && list.size() > 0) {
		// DataModel dataModel = new DataModel<BaseEntity>();
		// //writer.write(dataModel.setRows(list).toString());
		// JSONObject result=new JSONObject();
		// String json = null;
		// json=JsonUtil.list2json(list);
		// writer.write(json.toString());
		// } else {
		// writer.write("[{'text':'','value':''}]");
		// }
		// writer.flush();
		// return null;
		return null;
	}
	
	private JSONObject buildModeljson(String modelId,JSONArray  array) {
		JSONObject result = new JSONObject();
		result.put("id", modelId);
		result.put("data", array);
		

		return result;
	}
	
	
	private JSONObject buildModeljsontodiy(String modelId,String color,JSONArray  array) {
		JSONObject result = new JSONObject();
		result.put("id", modelId);
		result.put("color", color);
		result.put("data", array);
		

		return result;
	}
	
     
	 
	// 添加模版数据
	private JSONObject buildjsonlistleft(Long id, String name,String img,String type,String x,String y,String typename) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("type", type);
		result.put("x", x);
		result.put("y", y);
		result.put("typename", typename);

		return result;
	}
	
	
	// 判断完压花之后，获取这个门的名称，  把这个门的名称 私有的， 和公共的  右边数据返回。	 
	 public String getDoorJsonData(HttpServletRequest request,
	            HttpServletResponse response,String  EndOneNamemen,String dataleft) throws Exception {
			System.out.println(""+EndOneNamemen);
			//声明 ret
			JSONObject ret=new JSONObject(); 
			JSONObject rettest=new JSONObject(); 
			
			JSONArray retArr=new JSONArray(); 
			 String callback = request.getParameter("callback");
				JSONArray resultleft = new JSONArray();
				/************************************************1--中间的图片数据***************************************************/
			//	String dataleft;
				String codetype=null;
				String modelid=null;
				System.out.println("dataleft 的数值为"+dataleft);
				// 判断接收过来的参数， 去查询那个模版
				if(dataleft.equals("1")||dataleft == "1"){  codetype="101";  modelid="101";	}
				if(dataleft.equals("2")||dataleft == "2"){  codetype="102";  modelid="102"; 	}
				if(dataleft.equals("3")||dataleft == "3"){  codetype="103";  modelid="103"; }
				if(dataleft.equals("4")||dataleft == "4"){  codetype="104";  modelid="104";	}
				if(dataleft.equals("5")||dataleft == "5"){  codetype="201";  modelid="201"; 	}
				if(dataleft.equals("6")||dataleft == "6"){  codetype="202";  modelid="202"; 	}
				if(dataleft.equals("7")||dataleft == "7"){  codetype="203";  modelid="203";	}
				if(dataleft.equals("8")||dataleft == "8"){  codetype="404";  modelid="404";	}
				if(dataleft.equals("9")||dataleft == "9"){  codetype="405"; 	modelid="405"; }
				if(dataleft.equals("10")||dataleft == "10"){  codetype="403";  modelid="403"; }
				if(dataleft.equals("11")||dataleft == "11"){  codetype="401";  modelid="401"; }
				if(dataleft.equals("12")||dataleft == "12"){  codetype="402";   modelid="402";}
				
				  List<DiyModel> listleft=diyModelBo.findDiyModelByType(codetype);//organization2Bo.findByName("气窗");
				   String name=null;
				   String img=null;
				   String scale=null;
				   String x=null;
				   String y=null;
				   String type=null;
				   String typename=null;
				   String typenameid=null;
				   
					  for(int k=0;k<listleft.size();k++){ 
						  Long id = listleft.get(k).getId();
						name=listleft.get(k).getName();
						img=listleft.get(k).getImg();
						scale=listleft.get(k).getScale();
						x=listleft.get(k).getX();
						y=listleft.get(k).getY();
						type=listleft.get(k).getType();
						
						typename=listleft.get(k).getTypename();
						typenameid=listleft.get(k).getTypenameid();
						
						 System.out.println("one _"+id+"name_"+name+"img_"+img+"scale_"+scale+"x_"+x+"y_"+y+"");
						 
						 resultleft.add(buildjsonlistleft(id, name,img, type, x, y,typename));
						
					  }
					
					  rettest=buildModeljsontodiy(modelid,"#4f434b",resultleft);
					  System.out.println(" two "+rettest);
			
			
			String data=""; // 这个是查询出来的：  压花/花枝
			/************************************************ 获取  左边是那一套模版数据**************************************************/
			System.out.println("dataleft ====   "+dataleft+"codetype ===="+codetype);
			
		
			/************************************************1--门面款式***************************************************/
			List list1=new ArrayList();
			JSONObject result1= new JSONObject();
			JSONObject result1onebo= new JSONObject();
			JSONArray result1yahua= new JSONArray();
			JSONObject result2hua = new JSONObject();
			JSONObject result3pinjie = new JSONObject();
			JSONObject result3add = new JSONObject();
			JSONArray result1boli = new JSONArray();
			JSONObject result1ya = new JSONObject();
			JSONArray result1huazhi = new JSONArray();
			
			JSONArray result1pinjieadd = new JSONArray();
			
			JSONObject resultpinjie = new JSONObject();
			// 这个  最中的数据
			JSONArray result2Arrayres = new JSONArray();
			
			List<Organization2> listmenm=organization2Bo.findByRemark("menmiankuanshi");//findByName("门面款式");  
			long  idmenm=listmenm.get(0).getId();
			List<Organization2> listmenmto=organization2Bo.findByNoparentOrganizationID(idmenm);
			
			
			List<Organization2> listbopinjie=organization2Bo.findByRemark("pinjie");//findByName("拼接");  
			long  idbopinjie=listbopinjie.get(0).getId();
		//	List<Organization2> listpinjiedata=organization2Bo.findByNoparentOrganizationID(idbopinjie);
			
			
		   //
			List<Organization2> listhuazhi=organization2Bo.findByRemark("huazhi");//findByName("花枝");  
			long  idhuazhi=listhuazhi.get(0).getId();
//			List<Organization2> listhuazhixia=organization2Bo.findByNoparentOrganizationID(idhuazhi);
//			JSONObject result1kthree = new JSONObject();
//			JSONObject array1yahuazhi = new JSONObject();
//			JSONArray  array1yahua=new JSONArray();
//			Long ziid1three;
//			for(int i=0;i<listhuazhixia.size();i++){
//			data=listhuazhixia.get(i).getName();
//			ziid1three=listhuazhixia.get(i).getId();
//			//result1kthree.put(ziid1three, data);
//			array1yahuazhi=buildjsonlong(ziid1three,data);
//			array1yahua.add(array1yahuazhi);
//			}
			//result1pinjieadd.add(buildjsonDiarrayty(idhuazhi,"花枝","huazhi"));
			//result2hua=buildjsonNameDataArr("花枝",array1yahua);
			
			
//			JSONObject result3pinjiearr = new JSONObject();
//			
//			result3pinjiearr.put("",result2hua);
			
			//
			List<Organization2> listboli=organization2Bo.findByRemark("boli");//findByName("玻璃");  
			long  idboli=listboli.get(0).getId();
			List<Organization2> listbolidata=organization2Bo.findByNoparentOrganizationID(idboli);
			JSONObject result1ktwo = new JSONObject();
			JSONArray result1ktwoarray = new JSONArray();
			Long ziid1one;
			for(int i=0;i<listbolidata.size();i++){ 
				data=listbolidata.get(i).getName();
				ziid1one=listbolidata.get(i).getId();
				
				result1ktwoarray.add(buildjson3(ziid1one, data));
			}
			result1onebo=buildjsonDiarraytyright(idboli,"玻璃",result1ktwoarray,"boli");//buildjsonNameData("玻璃",result1ktwo);
		//	System.out.println("玻璃查询到的为"+result1onebo.toString());
			// 玻璃添加到集合
			result1pinjieadd.add(result1onebo);
		
			
			result3pinjie=buildjsonlistright(idbopinjie,"拼接",result1pinjieadd,"pinjie");
			
			System.out.println("kkkk222"+result3pinjie.toString());
		
			//查询压花的
			
			List<Organization2> listyahu=organization2Bo.findByRemark("yahua");//findByName("压花");  
			long  idyahua=listyahu.get(0).getId();
//			List<Organization2> listyahuadata=organization2Bo.findByNoparentOrganizationID(idyahua);
//			
//			JSONObject result1kfour = new JSONObject(); JSONObject array1yajs = new JSONObject();
//			JSONArray  array1ya=new JSONArray();
//			Long ziid1four;
//			for(int i=0;i<listyahuadata.size();i++){ 
//				data=listyahuadata.get(i).getName();
//				ziid1four=listyahuadata.get(i).getId();
//				//result1kfour.put(ziid1four, data);
//				array1yajs=buildjsonlong(ziid1four,data);
//				array1ya.add(array1yajs);
//			}
		//	System.out.println("  第一层中要 返回 "+array1yajs);
			
		//	result1ya=buildjsonNameDataArr("压花",array1ya);
			
		  result1ya=buildjsonDiarrayty(idyahua,"压花","yahua");
		  
		  JSONArray result3mmarr = new JSONArray();
		  
		  result3mmarr.add(result1ya);
		  result3mmarr.add(result3pinjie);
		  JSONObject result3menmian = new JSONObject();
		  result3menmian=buildjsonNameDataArr("门面款式",result3mmarr);
			JSONObject result1one = new JSONObject();
			result1one=buildjson("","");
			result2Arrayres.add(buildjsonallObejct("1", "款式",result3menmian,"kuanshi",result1one));
			
			/************************************************2--子门面款式***************************************************/
			
			List<Organization2> listzikexuan=organization2Bo.findByRemark("kexuan");//findByName("可选");  
			long  listzikexuanid=listzikexuan.get(0).getId();
			List<Organization2> listkexuandata=organization2Bo.findByNoparentOrganizationID(listzikexuanid);
			JSONObject result2zikexobj = new JSONObject();
			JSONArray result2zikarray = new JSONArray();
			//Long zikeid;
			String datazikett;
			Long  dataidktt;
			String typenameziktt;
			// 循环可选下面的
			for(int i=0;i<listkexuandata.size();i++){ 
				datazikett=listkexuandata.get(i).getName();
				dataidktt=listkexuandata.get(i).getId();
				typenameziktt=listkexuandata.get(i).getRemark();
				result2zikarray.add(buildjsonDiarrayty(dataidktt,datazikett,typenameziktt));
			}
			
			result1onebo=buildjsonDiarraytyright(listzikexuanid,"可选",result2zikarray,"kexuan");
			
			System.out.println("可选为 ：："+result1onebo);
			
			
		
			JSONObject result2 = new JSONObject();
			  JSONArray result2zimen = new JSONArray();
			  JSONArray result2zimen2 = new JSONArray();
			  JSONObject result2peitao = new JSONObject();
			JSONObject result3zi1 = new JSONObject();
			List<Organization2> listzimen=organization2Bo.findByRemark("zimenmainkuanshi");//findByName("子门面款式");  
			long  idszimen=listzimen.get(0).getId();
			List<Organization2> listozimen=organization2Bo.findByNoparentOrganizationID(idszimen);
		      String datazi=null;
		      Long dataziid = null;
		      String typenamezi=null;
		      
			for(int i=0;i<listozimen.size();i++){
    			datazi=listozimen.get(i).getName();
    			dataziid=listozimen.get(i).getId();
    			typenamezi=listozimen.get(i).getRemark();
    			System.out.println("---"+dataziid+"--------"+datazi+"---------"+typenamezi);
    		//	result2zimen.add(buildjsonDiarrayty(dataziid,datazi,typenamezi));
			}
			/*****************/
			
			List<Organization2> listzileibie=organization2Bo.findByName("子门款式");   //findByRemark("zimenkuanshi");
			long  idleibie=listzileibie.get(0).getId();
			List<Organization2> listozileibi2=organization2Bo.findByNoparentOrganizationID(idleibie);
			  String datazi2=null;
		      Long dataziid2= null;
		      String typenamezi2=null;
		      
			for(int i=0;i<listozileibi2.size();i++){
    			datazi2=listozileibi2.get(i).getName();
    			dataziid2=listozileibi2.get(i).getId();
    			typenamezi2=listozileibi2.get(i).getRemark();
    			System.out.println("--2-"+dataziid2+"---2-----"+datazi2+"-----2----"+typenamezi2);
    			if(typenamezi2.equals("peitao")){
    				result2peitao=buildjsonDiarrayty(dataziid2,datazi2,typenamezi2);
    			}
			}
			/*******************/
			
			result2zimen.add(result2peitao);//添加配套
			
			result2zimen.add(result1onebo);//添加可选
			result3zi1=buildjsonDiarraytyright(dataziid,datazi,result2zimen,typenamezi);
			System.out.println("result3zi1  nn "+result3zi1);
			
			

		
		
			  JSONObject result2one = new JSONObject();
			result2one=buildjson("","");
			if(codetype=="103"||codetype=="104"||codetype=="203"||codetype=="404"||codetype=="405"){
			result2Arrayres.add(buildjsonallObejct("2", "子门面款式",result3zi1,"zikuanshi",result2one));
			}
			/***********************************************3--气窗**************************************************/
			
			JSONObject result3two = new JSONObject();
			JSONObject result3 = new JSONObject(); JSONArray  array3=new JSONArray();
			List<Organization2> listqichuang=organization2Bo.findByRemark("qichuang");//findByName("气窗");  
			long  idqc=listqichuang.get(0).getId();
			List<Organization2> listoqichuang=organization2Bo.findByNoparentOrganizationID(idqc);
			Long ziid3;
			String 	typename3=null;
			for(int i=0;i<listoqichuang.size();i++){
    			data=listoqichuang.get(i).getName();
    			ziid3=listoqichuang.get(i).getId();
    			typename3=listoqichuang.get(i).getRemark();
    			result3two=buildjsonDiarrayty(ziid3,data,typename3);
    		//	result3two=buildjsonlong(ziid3,data);
    			array3.add(result3two);
    			//result3two.put(ziid3, data);
			}
			JSONObject result3o = new JSONObject();
			result3o=buildjsonNameDataArr("气窗",array3);
			JSONObject result3one = new JSONObject();
			result3one=buildjson("","");
			if(codetype=="102"||codetype=="402"||codetype=="403"||codetype=="402"||codetype=="405"){
			result2Arrayres.add(buildjsonallObejct("3", "气窗",result3o,"qichuang",result3one));
			}
             /***********************************************4--边框******************
              * 单开子门+门头  &单开字母
              * 四开+气窗&四开+气窗+门头
              *  边框款式中：门头 .门柱    &边框都可以去选择
              * ********************************/
			codetype="403";
			System.out.println("依次输出的为001："+codetype);
			if(codetype=="103"||codetype=="104"||codetype=="403"||codetype=="201"||codetype=="203"||codetype=="404"||codetype=="401"){
				//门头
				
			JSONObject result4 = new JSONObject();
			JSONObject result4two = new JSONObject();JSONArray  array4=new JSONArray();
			List<Organization2> listmenc=organization2Bo.findByRemark("biankuang");//findByName("边框款式");  
			long  idqch=listmenc.get(0).getId();
			List<Organization2> listbiank=organization2Bo.findByNoparentOrganizationID(idqch);
			Long ziid4;
			String 	typename4=null;
			for(int i=0;i<listbiank.size();i++){
    			data=listbiank.get(i).getName();
    			ziid4=listbiank.get(i).getId();
    			typename4=listbiank.get(i).getRemark();
    			
    			
    			//if(data.equals("门头款式")){
    			List<Organization2> listbianmentou2=organization2Bo.findByName(data); 
    			String namementou=null;
    			long menttid;
    			String typenamemenkk=null;
    			for(int iop=0;iop<listbianmentou2.size();iop++){
    				menttid=listbianmentou2.get(iop).getId();
    				namementou=listbianmentou2.get(iop).getName();
    				typenamemenkk=listbianmentou2.get(iop).getRemark();
    				System.out.println("依次输出的为："+namementou+typenamemenkk);
    				
    				result4two=buildjsonDiarrayty(ziid4,namementou,typenamemenkk);
    				
    			}
			
    			array4.add(result4two);
    			//result4two.put(ziid4, data);
    		//	}
			}
			JSONObject result4o = new JSONObject();
			result4o=buildjsonNameDataArr("边框样式",array4);
			
			JSONObject result4one = new JSONObject();
			result4one=buildjson("","");
			result2Arrayres.add(buildjsonallObejct("4", "边框样式",result4o,"biankkuangyangshi",result4one));
			}
			/********************
			 * 其他模版都是 一个，可以去选择边框
			 * 下面是门框 边框
			 * *****************/
			else if(codetype=="401"||codetype=="101"||codetype=="102"||codetype=="202"||codetype=="405"||codetype=="402"||codetype=="403"){
				JSONObject result4 = new JSONObject();
				JSONObject result4two = new JSONObject();JSONArray  array4=new JSONArray();
				List<Organization2> listmenc=organization2Bo.findByRemark("biankuang");//findByName("边框款式");  
				long  idqch=listmenc.get(0).getId();
				List<Organization2> listbiank=organization2Bo.findByNoparentOrganizationID(idqch);
				Long ziid4;
				String 	typename4=null;
				for(int i=0;i<listbiank.size();i++){
	    			data=listbiank.get(i).getName();
	    			ziid4=listbiank.get(i).getId();
	    			typename4=listbiank.get(i).getRemark();
	    			result4two=buildjsonDiarrayty(ziid4,data,typename4);
	    			//result4two=buildjsonlong(ziid4,data);
	    			if(data.equals("门框款式")){
	    			array4.add(result4two);
	    			}
	    			//result4two.put(ziid4, data);
				}
				JSONObject result4o = new JSONObject();
				result4o=buildjsonNameDataArr("边框样式",array4);
				JSONObject result4one = new JSONObject();
				result4one=buildjson("","");
				result2Arrayres.add(buildjsonallObejct("4", "边框样式",result4o,"biankkuangyangshi",result4one));
				
			}
			/************************************************5--色板***************************************************/
			List list5=new ArrayList();
			JSONObject result5 = new JSONObject();
			JSONObject result5two = new JSONObject();
			JSONArray  array5=new JSONArray();
			List<Organization2> listseban=organization2Bo.findByRemark("seban");//findByName("色板");  
			long  idseban=listseban.get(0).getId();
			List<Organization2> listoseban=organization2Bo.findByNoparentOrganizationID(idseban);
			Long ziid5;
			String 	typename5=null;
			for(int i=0;i<listoseban.size();i++){
    			data=listoseban.get(i).getName();
    			ziid5=listoseban.get(i).getId();
    			typename5=listoseban.get(i).getRemark();
    			result5two=buildjsonDiarrayty(ziid5,data,typename5);
    		//	result5two=buildjsonlong(ziid5,data);
    			array5.add(result5two);
    			//result5two.put(ziid5, data);		
    			}
			JSONObject result5o = new JSONObject();
			result5o=buildjsonNameDataArr("色板",array5);
			JSONObject result5one = new JSONObject();
			result5one=buildjson("","");
			result2Arrayres.add(buildjsonallObejct("5", "色板",result5o,"seban",result5one));
			/*************************************************6开向***************************************************/
			List list6=new ArrayList();
			JSONObject result6 = new JSONObject();
			JSONObject result6two = new JSONObject(); 
			JSONArray  array6=new JSONArray();
			List<Organization2> listokaixiang=organization2Bo.findByRemark("kaixiang");//findByName("开向");  
			long  idkx=listokaixiang.get(0).getId();
			List<Organization2> listokx=organization2Bo.findByNoparentOrganizationID(idkx);
			Long ziid6;
			String typename6=null;
			for(int i=0;i<listokx.size();i++){
    			data=listokx.get(i).getName();
    			ziid6=listokx.get(i).getId();
    			typename6=listokx.get(i).getRemark();
    			result6two=buildjsonDiarrayty(ziid6,data,typename6);
    			//result6two=buildjsonlong(ziid6,data);
    			array6.add(result6two);
    			//result6two.put(ziid6, data);
			}
			JSONObject result6o = new JSONObject();
			result6o=buildjsonNameDataArr("开向",array6);
			JSONObject result6one = new JSONObject();
			result6one=buildjson("","");
			result2Arrayres.add(buildjsonallObejct("6", "开向",result6o,"kaixiang",result6one));
			/************************************************板材****************************************************/
			List list7=new ArrayList();
			JSONObject result7 = new JSONObject();
			JSONObject result7two = new JSONObject();
			JSONArray  array7=new JSONArray();
			List<Organization2> listbancai=organization2Bo.findByRemark("bancai");//findByName("板材");  
			long  idbancai=listbancai.get(0).getId();
			List<Organization2> listbanc=organization2Bo.findByNoparentOrganizationID(idbancai);
			Long ziid7;
			String typename7=null;
			for(int i=0;i<listbanc.size();i++){
    			data=listbanc.get(i).getName();
    			ziid7=listbanc.get(i).getId();
    			//result7two.put(ziid7, data);
    			typename7=listbanc.get(i).getRemark();
    			result7two=buildjsonDiarrayty(ziid7,data,typename7);
    			
    			array7.add(result7two);
			}
			JSONObject result7o = new JSONObject();
			result7o=buildjsonNameDataArr("板材",array7);
			JSONObject result7one = new JSONObject();
			result7one=buildjson("","");
			result2Arrayres.add(buildjsonallObejct("7", "板材",result7o,"bancai",result7one));
			/************************************************其他****************************************************/
			JSONObject result8 = new JSONObject();
			JSONObject result8two = new JSONObject();
			JSONArray  array8=new JSONArray();
			List<Organization2> listqita=organization2Bo.findByRemark("qita");//findByName("其它");  
			long  idqita=listqita.get(0).getId();
			List<Organization2> listoqita=organization2Bo.findByNoparentOrganizationID(idqita);
			Long ziid8;
			String typename8=null;
			for(int i=0;i<listoqita.size();i++){
    			data=listoqita.get(i).getName();
    			ziid8=listoqita.get(i).getId();
    			typename8=listoqita.get(i).getRemark();
    			result8two=buildjsonDiarrayty(ziid8,data,typename8);
    			
    			array8.add(result8two);
    		//	result8two.put(ziid8, data);
			}
			JSONObject result8o = new JSONObject();
			result8o=buildjsonNameDataArr("其他",array8);
			
			JSONObject result8one = new JSONObject();
			result8one=buildjson("","");
			result2Arrayres.add(buildjsonallObejct("8", "其它",result8o,"qita",result8one));
			
			/*************************************************配件***************************************************/
			List list9=new ArrayList();
			JSONObject result9 = new JSONObject();
			JSONObject result910 = new JSONObject();
			JSONObject result9two = new JSONObject();
			JSONObject result9three = new JSONObject();
			JSONArray  array=new JSONArray();
			JSONArray  array91=new JSONArray();
		  //   JSONArray array = jsonObject.getJSONArray("jsonArray");   
			List<Organization2> listoo=organization2Bo.findByRemark("peijian");//findByName("配件");  
			long  id=listoo.get(0).getId();
			List<Organization2> listo=organization2Bo.findByNoparentOrganizationID(id);
			Long ziid;
			String typename9=null;
			for(int i=0;i<listo.size();i++){
    			data=listo.get(i).getName();
    			ziid=listo.get(i).getId();
    			typename9=listo.get(i).getRemark();
    			result9two=buildjsonDiarrayty(ziid,data,typename9);
    			array.add(result9two);
    		//	result9two.put(ziid, data);
			}
			JSONObject result9o = new JSONObject();
			result9o=buildjsonNameDataArr("配件",array);
			JSONObject result9one = new JSONObject();
			result9one=buildjson("","");
			
			
			
			result2Arrayres.add(buildjsonallObejct("9", "配件",result9o,"peijian",result9one));
			
			/******************************************11**********************************************************/

			
			JSONArray  arrayendff=new JSONArray();
			arrayendff.add(result2Arrayres);

			response.reset(); 
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();

			ret.put("data2", rettest.toString());
			ret.put("data", arrayendff.toString());
			writer.write(callback + "(" + ret.toString() + ")");
			//writer.write(ret.toString());
		//	writer.write(str);
			writer.flush();

		 return null;
		 
		 
	 }
		@RequestMapping(value = "/pages/company/getDoorJsonkexuan.do")
	 public String getDoorJsonkexuan(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
			response.setContentType("text/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
		
			//声明   gjp20140302
			JSONObject ret=new JSONObject(); 
			JSONObject rettest=new JSONObject(); 
			
			JSONArray arrayqichuang22=new JSONArray(); 
			 String callback = request.getParameter("callback");
				JSONArray resultleft = new JSONArray();
	       String chstyle=request.getParameter("chstyle");
	       PrintWriter writer = response.getWriter();
	       if(chstyle.equals("")||chstyle.equals(null)){
	    	   
	    	   
	    	   
	    	   arrayqichuang22.add("");
	    	   writer.write("false");
	    	   writer.flush();

	    		 return null;
	       }
	       else
	         if(chstyle.equals("kexuan")){
			System.out.println("用户点击了可选设置，接着请求 子款式");
			// 可选返回  2个参数：
			List<Organization2> listDppziyaoento=organization2Bo.findByRemark("kexuan"); //findByName("子压花");
			Long zyidoneto = listDppziyaoento.get(0).getId();
			String zyname3oneto=listDppziyaoento.get(0).getName();
			String tpname=listDppziyaoento.get(0).getRemark();
		   List<Organization2> listDmen3org=organization2Bo.findByNoparentOrganizationID(zyidoneto);
		   String typenameziyaone=null;
		   String typenamezipone=null;
		   long typenameonetoid;
		   String typenameoneto=null;
		   List  lzyaoneto=new ArrayList();
		   for(int k=0;k<listDmen3org.size();k++){ 
			   typenameonetoid=listDmen3org.get(k).getId();
			   typenamezipone=listDmen3org.get(k).getName();
			   typenameoneto=listDmen3org.get(k).getRemark();
			   lzyaoneto.add(buildjsonDiarrayty(typenameonetoid,typenamezipone,typenameoneto));
		   
		   }
			arrayqichuang22.add(buildjsonDiarray(zyidoneto,zyname3oneto,lzyaoneto,tpname));	
		}
		response.reset(); 
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
	//	PrintWriter writer = response.getWriter();
		ret.put("data", arrayqichuang22.toString());
		writer.write(callback+"(" + ret.toString() + ")");
		//writer.write(ret.toString());
	//	writer.write(str);
		writer.flush();
	 return null;
	 
	 
 }


	@RequestMapping(value = "/pages/company/comboData.do")
	public String getComboData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		this.codSet(response);

		List list = null;

		if ("organizationv".equals(type)) {
			// 拿到当前的公司的id ，去查询当前公司的 子公司和机构
			Person loginPerson = loginInfo.getLoginPerson();
			String orgid = loginPerson.getOrganizationId();
			String orgname = loginPerson.getOrganizationName();
			Map map = new HashMap();

			long idw = Long.valueOf(orgid);
			map.put("id", idw);

			list = organizationBo.findByParm(map);

		} else if ("departmentv".equals(type)) {
			if (Utils.nullOrEmpty(id) == false)
				list = departmentBo.findByOrganizationId(Long.valueOf(id));
		} else {
			Map map = new HashMap();
			map.put("type", type);
			list = dataDictionaryBo.findByParm(map);
			System.out.println("-----------------从 person中查询 得到下拉框的数据--------------------------");
		}
		PrintWriter writer = response.getWriter();

		if (null != list && list.size() > 0) {
			DataModel dataModel = new DataModel<BaseEntity>();
			// writer.write(dataModel.setRows(list).toString());
			JSONObject result = new JSONObject();
			String json = null;
			json = JsonUtil.list2json(list);
			writer.write(json.toString());
		} else {
			writer.write("[{'text':'','value':''}]");
		}
		writer.flush();
		return null;
	}
	
	// 单独的一个可选 提供方法  ，返回  2个字
	
	/****************** 门面 款式 ，右部部数据 ******************/
	@RequestMapping(value = "/pages/company/sdoorComboSelect.do")
	public void sdoorComboSelect(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String callback = request.getParameter("callback");
		String chstyle = request.getParameter("chstyle");
		 JSONArray  arrayqichuang22=new JSONArray();
		 JSONObject ret = new JSONObject();
		
		List<Organization2> listDppziyaoento=organization2Bo.findByRemark("kexuan"); //findByName("子压花");
		Long zyidoneto = listDppziyaoento.get(0).getId();
		String zyname3oneto=listDppziyaoento.get(0).getName();
		String tpname=listDppziyaoento.get(0).getRemark();
	   List<Organization2> listDmen3org=organization2Bo.findByNoparentOrganizationID(zyidoneto);
	   String typenameziyaone=null;
	   String typenamezipone=null;
	   long typenameonetoid;
	   String typenameoneto=null;
	   List  lzyaoneto=new ArrayList();
	   for(int k=0;k<listDmen3org.size();k++){ 
		   typenameonetoid=listDmen3org.get(k).getId();
		   typenamezipone=listDmen3org.get(k).getName();
		   typenameoneto=listDmen3org.get(k).getRemark();
		   lzyaoneto.add(buildjsonDiarrayty(typenameonetoid,typenamezipone,typenameoneto));
	   
	   }
		arrayqichuang22.add(buildjsonDiarray(zyidoneto,zyname3oneto,lzyaoneto,tpname));	
		

		System.out.println(" 底部列表对象为"+arrayqichuang22); 
		


		response.reset();
		response.setCharacterEncoding("utf-8");

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ret.put("data", arrayqichuang22.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}
	
	
	
	
	
	

	/****************** 门面 款式 ，右部部数据 ******************/
	@RequestMapping(value = "/pages/company/sdoorCombo.do")
	public void sdoorCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		

		String chstyle = request.getParameter("chstyle");
	
		String chsnumber= request.getParameter("typename");
		String dibuname= request.getParameter("typename");
		
		System.out.println("传过来的参数为："+chstyle);
		JSONArray result = new JSONArray();
		JSONObject resultend = new JSONObject(); 
		
		JSONArray resultArraydoorend = new JSONArray(); 
		String callback = request.getParameter("callback");
		JSONObject ret=new JSONObject(); 
		//查询门面款式的 子类， (压花，花枝)  第一层 
		long Did;
		String Ddata;
		long Did2 = 0;
		String Ddata2 = null;
		 JSONArray  arrayhuazhione=new JSONArray();
		 JSONArray  arrayhuazhitwo=new JSONArray();
		 JSONArray  arrayDi1end=new JSONArray();
		 
		 JSONArray  arrayqichuang=new JSONArray();
		 
			if(chstyle.equals("")||(chstyle == null)){
				
				arrayqichuang.add("");
				}
			 /********************************************配套 
			  * 这里需要传过来 2个参数， 第一个是 chstyle  点击配套， 第二个是：typename  用户在父结构 中选择  是压花  还是  花枝
			  * 通过判断  chstyle，
			  * 再判断： typename,
			  * 3判断底部数据  类型diname
			  * 
                ***************************************/

			
			if(chstyle.equals("peitao")){
				System.out.println("用户点击了配套设置，接着请求 子款式");
				if(chsnumber.equals("yahua")||chsnumber.equals("pinjie")){
					
					System.out.println("用户的父款式是 压花");
					// 根据父类中选定的款式
					//压花>> 普通
					// 接着去  子父节点中中找到对应的    压花>> 普通
				//	dibuname
					
					List<Organization2> listpeitaozikuan=organization2Bo.findByRemark("peitaokuanshi");//findByName("配套子款式");
					Long id = listpeitaozikuan.get(0).getId();
					String name3=listpeitaozikuan.get(0).getName();
					/*
					用户点击了配套设置，接着请求 子款式
					用户的父款式是 压花
					得到这个父结构的ID为普通
					 底部列表对象为[{"id":174,"name":"普通","list":[],"typename":"putong"},""]
					*/
					
					  List<Organization2> listptaonode=organization2Bo.findByNoparentOrganizationID(id);
					  String  PDataname=null;
					  long DPid;
					  String typenamepeitao=null;
					  for(int k=0;k<listptaonode.size();k++){ 
						  
						  DPid=listptaonode.get(k).getId();
						  PDataname=listptaonode.get(k).getName();
						  
						  typenamepeitao=listptaonode.get(k).getRemark();
							System.out.println("得到这个父结构的ID为"+DPid+"----name----"+PDataname);
							List  lpt=new ArrayList();
							// 这个需要根据前台查询到   子类中  是否有这个  父亲节点下的标签数据
							  List<Organization2> listptaonodepeitao=organization2Bo.findByRemark("peitaokuanshi");	
							  for(int y=0;y<listptaonodepeitao.size();y++){ 
								  String nameyy=listptaonodepeitao.get(y).getName();
								  Long idyy=listptaonodepeitao.get(y).getId();
							 System.out.println("__________---"+nameyy);
							 List<Organization2> listptaonodetwo=organization2Bo.findByNoparentOrganizationID(idyy);
							  for(int yz=0;yz<listptaonodetwo.size();yz++){ 
								  String nameyyz=listptaonodetwo.get(yz).getName();
								  
							if(PDataname==nameyyz||PDataname.equals(nameyyz)){
										System.out.println("子类款式中 也有这个  普通的");
										List<Department2> listDPaert=department2Bo.findByNoparentOrganizationID(DPid); // 查询到  9个节点下的数据
										  String Ddapt = null;
											 Long	Didpt = null;
											 String Addresspt = null;
													for(int z=0;z<listDPaert.size();z++){ 
														Ddapt=listDPaert.get(z).getName();
														 Didpt=listDPaert.get(z).getId();
														 Addresspt=listDPaert.get(z).getAddress();
														 System.out.println(""+Ddapt+">>"+Didpt);
														 lpt.add(buildjsonDia(Didpt,Ddapt,Addresspt));
													 }
													System.out.println(">MM>"+lpt);
									}
							  }
							  }
									arrayqichuang.add(buildjsonDiarray(DPid,PDataname,lpt,typenamepeitao));
					  }
					
					
				}
				
				
				
				if(chsnumber.equals("pinjie")){
					
					System.out.println("用户的父款式是 花枝else");	
				}else{
					System.out.println("用户的父款式是 花枝else2");	
					//arrayqichuang.add("");
				}
				
			}
			
			/**************************可选 ************************************************/
			

		
			
			
//			else{
//				arrayqichuang.add("");	
//				
//			}
			
			
//			if((dibuname.equals("")||(dibuname == null))){
//				arrayqichuang.add((""));	
//				System.out.println("----kongd zhido ");
//			}
//		else{
			if(chstyle.equals("kexuan")&&dibuname.equals("ziyahua")){
				   List<Organization2> listDppziya=organization2Bo.findByRemark("ziyahua"); //findByName("子压花");
					Long zyid = listDppziya.get(0).getId();
					String zyname3=listDppziya.get(0).getName();
				   List<Organization2> listDmen3cziya=organization2Bo.findByNoparentOrganizationID(zyid);
				   String typenameziya=null;
				   for(int k=0;k<listDmen3cziya.size();k++){ 
						String Ddata3ziyz = listDmen3cziya.get(k).getName();
						Long Did3ziyz = listDmen3cziya.get(k).getId();
						
						String typenameziyz = listDmen3cziya.get(k).getRemark();
					
						System.out.println("得到这个父结构的ID为"+Did3ziyz+Ddata3ziyz);
						List<Department2> listDmenqianziya=department2Bo.findByNoparentOrganizationID(Did3ziyz); // 查询到  9个节点下的数据
						List  lzya=new ArrayList();
						  String Ddataq3z = null;
							 Long	Didq3z = null;
							 String Addressz = null;
							 String typenamezyz = null;
						for(int z=0;z<listDmenqianziya.size();z++){ 
							Ddataq3z=listDmenqianziya.get(z).getName();
							 Didq3z=listDmenqianziya.get(z).getId();
							 Addressz=listDmenqianziya.get(z).getAddress();
							 typenamezyz=listDmenqianziya.get(z).getRemark();
							 System.out.println(">>>>>ziyahua"+Didq3z+""+Ddataq3z+""+""+Addressz+""+""+typenamezyz+""+"");
							 lzya.add(buildjsonDiakaix(Didq3z,Ddataq3z,Addressz,typenamezyz));
						 }
						arrayqichuang.add(buildjsonDiarray(Did3ziyz,Ddata3ziyz,lzya,typenameziyz));	
						 
				   }
			}
//			else{
//				arrayqichuang.add("");	
//				
//			}
			
			/**************************************************************************/
			
			if(chstyle.equals("kexuan")&&dibuname.equals("zipinjie")){
				   List<Organization2> listDppzpinjie=organization2Bo.findByRemark("zipinjie"); //.findByName("子拼接");
					Long zpinid = listDppzpinjie.get(0).getId();
					String zpinname3=listDppzpinjie.get(0).getName();
				   List<Organization2> listDmen3zipinjie=organization2Bo.findByNoparentOrganizationID(zpinid);
				   String typenamepinjie=null;
				   for(int k=0;k<listDmen3zipinjie.size();k++){ 
						String Ddata3zipinjie = listDmen3zipinjie.get(k).getName();
						Long Did3zipinjie = listDmen3zipinjie.get(k).getId();
						String typenamezipin = listDmen3zipinjie.get(k).getRemark();
						System.out.println("得到这个父结构的ID为"+Did3zipinjie+Ddata3zipinjie);
						List<Department2> listDmenqianzipin=department2Bo.findByNoparentOrganizationID(Did3zipinjie); // 查询到  9个节点下的数据
						List  lzya=new ArrayList();
						  String Ddataq3zpin = null;
							 Long	Didq3zpin = null;
							 String Addresszpin = null;
							 String typenamezpin = null;
						for(int z=0;z<listDmenqianzipin.size();z++){ 
							Ddataq3zpin=listDmenqianzipin.get(z).getName();
							Didq3zpin=listDmenqianzipin.get(z).getId();
							Addresszpin=listDmenqianzipin.get(z).getAddress();
							typenamezpin=listDmenqianzipin.get(z).getRemark();
							 lzya.add(buildjsonDiakaix(Didq3zpin,Ddataq3zpin,Addresszpin,typenamezpin));
						 }
						arrayqichuang.add(buildjsonDiarray(Did3zipinjie,Ddata3zipinjie,lzya,typenamezipin));	
						 
				  
				   }
			}
//			else{
//				arrayqichuang.add("");	
//				
//			}
			
		//	}
			 /************/
			
			
			
			
		 /*******************************************************************************************/
		if(chstyle.equals("yahua")||chstyle.equals("pinjie")){
		List<Organization2> listDmen=organization2Bo.findByRemark("menmiankuanshi");//findByName("门面款式");
		for(int i=0;i<listDmen.size();i++){ 
			Ddata=listDmen.get(i).getName();
			Did=listDmen.get(i).getId();
			System.out.println("门面款式1查询到"+Did);
			List<Organization2> listDmen2=organization2Bo.findByNoparentOrganizationID(Did);
							for(int j=0;j<listDmen2.size();j++){ 
								Ddata2=listDmen2.get(j).getName();
								Did2=listDmen2.get(j).getId();
								System.out.println("门面款式2查询到"+Did2+"名字为:"+Ddata2);
								// 以上是固定的结构：
								//map.put(Did2, Ddata2);
							}
						}
			JSONObject resultDi1 = new JSONObject();
			 JSONArray  arrayDi1=new JSONArray();
			 
			 
			 JSONArray  arrayya12=new JSONArray();
			 JSONArray  arrayDi1end2=new JSONArray();
			 JSONObject resultya12t = new JSONObject(); 
			 JSONArray  arrayya12t=new JSONArray();
			 JSONArray  arrayDi1end2t=new JSONArray();
			 
			long Did3 = 0;
			String Ddata3 = null;
		   if(chstyle.equals("yahua")){
			   List<Organization2> listDpp=organization2Bo.findByRemark("yahua");//findByName("压花");
				Long id = listDpp.get(0).getId();
				String name3=listDpp.get(0).getName();
			   List<Organization2> listDmen3c=organization2Bo.findByNoparentOrganizationID(id);
			   String typename=null;
			   for(int k=0;k<listDmen3c.size();k++){ 
					Ddata3=listDmen3c.get(k).getName();
					Did3=listDmen3c.get(k).getId();
					typename=listDmen3c.get(k).getRemark();
					System.out.println("得到这个父结构的ID为"+Did3+Ddata3);
					List<Department2> listDmenqian=department2Bo.findByNoparentOrganizationID(Did3); // 查询到  9个节点下的数据
					List  l=new ArrayList();
					  String Ddataq3 = null;
						 Long	Didq3 = null;
						 String Address = null;
					for(int z=0;z<listDmenqian.size();z++){ 
						 Ddataq3=listDmenqian.get(z).getName();
						 Didq3=listDmenqian.get(z).getId();
						 Address=listDmenqian.get(z).getAddress();
						 l.add(buildjsonDia(Didq3,Ddataq3,Address));
					 }
					arrayqichuang.add(buildjsonDiarray(Did3,Ddata3,l,typename));	
					 
			   }
			//   System.out.println("end "+arrayDi1end);
			   
			//  resultend=buildjsondata(arrayDi1end);
			//   resultArraydoorend.add(buildjsondata(arrayDi1end));
			
		   }
			   // 最终添加门面款式 / 压花
		   
		   if(chstyle.equals("pinjie")){
			   List<Organization2> listDpp2=organization2Bo.findByRemark("pinjie");//findByName("拼接");
				Long iddd = listDpp2.get(0).getId();
				 System.out.println("2----花枝--然后去查询结构，把子节点的标签列表查询出来---");
				   List<Organization2> listDmen3c2=organization2Bo.findByNoparentOrganizationID(iddd);
				   long Did32=0;
				   String Ddata32 = null;
				   for(int k=0;k<listDmen3c2.size();k++){ 
						Ddata32=listDmen3c2.get(k).getName();
						Did32=listDmen3c2.get(k).getId();
						System.out.println(Ddata32+Did32);
						  List<Organization2> listDpp=organization2Bo.findByNoparentOrganizationID(Did32);//查询到 花枝下的 子节点
						  
						  //循环 子节点 ，把其中的数据组装
						 
								if(Ddata32.equals("花枝")){
									String  typename=null;
									 for(int w=0;w<listDpp.size();w++){ 
											Ddata3=listDpp.get(w).getName();
											Did3=listDpp.get(w).getId();
											typename=listDpp.get(w).getRemark();
									System.out.println("得到这个父结构的ID为"+Did3+Ddata3);
									List<Department2> listDmenqian=department2Bo.findByNoparentOrganizationID(Did3); // 查询到  9个节点下的数据
									
									List  l=new ArrayList();
									  String Ddataq3 = null;
										 Long	Didq3 = null;
										 String Address = null;
									for(int z=0;z<listDmenqian.size();z++){ 
										 Ddataq3=listDmenqian.get(z).getName();
										 Didq3=listDmenqian.get(z).getId();
										 Address=listDmenqian.get(z).getAddress();
										 l.add(buildjsonDia(Didq3,Ddataq3,Address));
									 }
									arrayqichuang.add(buildjsonDiarray(Did3,Ddata3,l,typename));	
							   }	
								}

				   }
			   
			   
		
		   
		   }
		   }  
	
		
		
		  /*************************6 开向**************************************/
		
		  
		 
		  if (chstyle.equals("kaixiang")) {
		  long Didqckx = 0;
			String Ddataqckx = null;
			String imgkaix=null;
			   List<Organization2> listDpp=organization2Bo.findByRemark("kaixiang");//findByName("开向");
				Long id = listDpp.get(0).getId();
				String name3=listDpp.get(0).getName();
				List<Department2> listDmen3c=department2Bo.findByNoparentOrganizationID(id);
			  // List<Organization2> listDmen3c=organization2Bo.findByNoparentOrganizationID(id);
				String  typenamekx=null;
				   List  l=new ArrayList();
					   for(int k=0;k<listDmen3c.size();k++){ 
				
						   Ddataqckx=listDmen3c.get(k).getName();
						   Didqckx=listDmen3c.get(k).getId();
						   imgkaix=listDmen3c.get(k).getAddress();
						   typenamekx=listDmen3c.get(k).getRemark();
							
						   //System.out.println("得到这个父结构的ID为"+Didqckx+Ddataqckx); 
//							List<Department2> listDmenqian=department2Bo.findByNoparentOrganizationID(Didqckx); // 查询到  9个节点下的数据
//							  String Ddataqc3 = null;
//								 Long	Didqc3 = null;
//								 String Addressqc = null;
//							for(int z=0;z<listDmenqian.size();z++){ 
//								 Ddataqc3=listDmenqian.get(z).getName();
//								 Didqc3=listDmenqian.get(z).getId();
//								 Addressqc=listDmenqian.get(z).getAddress();
							// }
							 l.add(buildjsonDiakaix(Didqckx,Ddataqckx,imgkaix,typenamekx));
							
					   }
					   arrayqichuang.add(buildjsonDiarray(Didqckx,name3,l,typenamekx));	
			//   System.out.println("end 气窗 "+arrayqichuang);
			   //resultend=buildjsondata(arrayqichuang);
			   //
			 // resultArraydoorend.add(arrayqichuang);
		   }
		  
		  /**************************3  气窗***************************************/
		
		  
		 
		  if (chstyle.equals("qichuang")) {
		  long Didqc = 0;
			String Ddataqc = null;
			   List<Organization2> listDpp=organization2Bo.findByRemark("qichuang");//findByName("气窗");
				Long id = listDpp.get(0).getId();
				String name3=listDpp.get(0).getName();
			   List<Organization2> listDmen3c=organization2Bo.findByNoparentOrganizationID(id);
				String  typename=null;
					   for(int k=0;k<listDmen3c.size();k++){ 
						   Ddataqc=listDmen3c.get(k).getName();
						   Didqc=listDmen3c.get(k).getId();
						   typename=listDmen3c.get(k).getRemark();
							System.out.println("得到这个父结构的ID为"+Didqc+Ddataqc); 
							List<Department2> listDmenqian=department2Bo.findByNoparentOrganizationID(Didqc); // 查询到  9个节点下的数据
							List  l=new ArrayList();
							  String Ddataqc3 = null;
								 Long	Didqc3 = null;
								 String Addressqc = null;
							for(int z=0;z<listDmenqian.size();z++){ 
								 Ddataqc3=listDmenqian.get(z).getName();
								 Didqc3=listDmenqian.get(z).getId();
								 Addressqc=listDmenqian.get(z).getAddress();
								 l.add(buildjsonDia(Didqc3,Ddataqc3,Addressqc));
							 }
							arrayqichuang.add(buildjsonDiarray(Didqc,Ddataqc,l,typename));	
							
					   }
			//   System.out.println("end 气窗 "+arrayqichuang);
			   //resultend=buildjsondata(arrayqichuang);
			   //
			 // resultArraydoorend.add(arrayqichuang);
		   }
		  
		  /************************8**其它***************************************/
			
		  
			 
		  if (chstyle.equals("qita")) {
		  long Didqitac = 0;
			String Ddataqitac = null;
			   List<Organization2> listqita=organization2Bo.findByRemark("qita");//findByName("其它");
				Long id = listqita.get(0).getId();
				String name3=listqita.get(0).getName();
			   List<Organization2> listDmqita=organization2Bo.findByNoparentOrganizationID(id);
				String  typename=null;
					   for(int k=0;k<listDmqita.size();k++){ 
						   Ddataqitac=listDmqita.get(k).getName();
						   Didqitac=listDmqita.get(k).getId();
						   typename=listDmqita.get(k).getRemark();
							System.out.println("得到这个父结构的ID为"+Didqitac+Ddataqitac); 
							
							   List<Department2> listDmqitatwo=department2Bo.findByNoparentOrganizationID(Didqitac); // 查询到  9个节点下的数据
							List  l=new ArrayList();
							  String Ddataqtc3 = null;
								 Long	Didqtc3 = null;
								 String Addressqtc = null;
							for(int z=0;z<listDmqitatwo.size();z++){ 
								 Ddataqtc3=listDmqitatwo.get(z).getName();
								 Didqtc3=listDmqitatwo.get(z).getId();
								 
								 l.add(buildjsonlong(Didqtc3,Ddataqtc3));
							 }
							arrayqichuang.add(buildjsonDiarray(Didqitac,Ddataqitac,l,typename));	
						//	arrayqichuang.add(buildjsonlong(Didqitac,Ddataqitac));	
							
					   }
			   System.out.println("end 气窗 "+arrayqichuang);
			   //resultend=buildjsondata(arrayqichuang);
			   //
			 // resultArraydoorend.add(arrayqichuang);
		   }
		  
			   /***************************9 其他*************************************/
		  
			   /***************************5 色板***************************************/

			   if (chstyle.equals("seban")) {
					String modelid= request.getParameter("id"); // 获取右边模版的ID
				   System.out.println("获取模版的id为:--"+modelid);
			   long Diseban = 0;
				String Ddaseban = null;
				   List<Organization2> listDseban=organization2Bo.findByRemark("seban");//findByName("色板");
					Long idseban = listDseban.get(0).getId();
					String name3se=listDseban.get(0).getName();
				   List<Organization2> listDsebanc=organization2Bo.findByNoparentOrganizationID(idseban);
					String  typename=null;
				   for(int k=0;k<listDsebanc.size();k++){ 
					   Ddaseban=listDsebanc.get(k).getName();
					   Diseban=listDsebanc.get(k).getId();
					   typename=listDsebanc.get(k).getRemark();
						System.out.println("得到这个父结构的ID为"+Diseban+Ddaseban); 
						List<Department2> listDmseban=department2Bo.findByNoparentOrganizationID(Diseban); // 查询到  9个节点下的数据
						List  lseban=new ArrayList();
						  String Dsebannei3 = null;
							 Long	Didseban3 = null;
							 String Addressebanc = null;
							 String colorree = null;
							 String  code = null;
							 
						for(int u=0;u<listDmseban.size();u++){ 
							List li=null;
							code=listDmseban.get(u).getCode();
							Dsebannei3=listDmseban.get(u).getName();
							Didseban3=listDmseban.get(u).getId();
							Addressebanc=listDmseban.get(u).getAddress();
							colorree=listDmseban.get(u).getRemark();
							
							
							if(colorree!=null||!colorree.equals(" ")||!colorree.equals("")){
								 System.out.println("------------chaxun"+colorree+""+colorree+"");
							
									 lseban.add(buildjsonDiaSeban(Didseban3,Dsebannei3,Addressebanc,colorree,li));
	 
								
					       	System.out.println("kone"+lseban.toString());
							}
							if(colorree==null||colorree.equals("")){
								System.out.println("kkongd  "+Dsebannei3);
								
							}
							
							
							   JSONArray  arraysebanone=new JSONArray();
							 if(colorree.equals("")||colorree ==null)
							{
								
								 // 模版的ID 得到后 查询到目前这个 模版的数据，遍历后这个 数据，拿到type=1 的数据。
								 // 从色板 表中得到 color 为空的数值： 查询到很多条数据  
								 //  id  name  img  typename   gjp
								 // 第一部  查询到 这个模版中的数据  type=1 的话，查询到 这个  符合这个模版的数据，还要根据名字做个数组分开
								 
								// modelid="404";
								String userfordmodelid=modelid;
								  List<DiyModel> listleftone=diyModelBo.findDiyModelBydmodelid(userfordmodelid);//.findDiyModelSebanByType(modelid);//organization2Bo.findByName("气窗");
								System.out.println("size we"+listleftone.size());
								  String name=null;
								   String img=null;
								   String scale=null;
								   String x=null;
								   String y=null;
								   String type=null;
								   String typenamess=null;
								   String typenameid=null;
								   Long id=null;
									  for(int seid=0;seid<listleftone.size();seid++){ 
										  id = listleftone.get(seid).getId();
										name=listleftone.get(seid).getName();
										img=listleftone.get(seid).getImg();
										scale=listleftone.get(seid).getScale();
										x=listleftone.get(seid).getX();
										y=listleftone.get(seid).getY();
										type=listleftone.get(seid).getType();
										typename=listleftone.get(seid).getTypename();
										typenameid=listleftone.get(seid).getTypenameid();
										 System.out.println("one 99090_"+id+"name_"+name+"img_"+img+"scale_"+scale+"x_"+x+"y_"+y+"");
										  arraysebanone.add(buildjsonlistleft(id, name,img, type, x, y,typename));
									  }
									  
									  lseban.add(buildjsonDiaSeban(Didseban3,Dsebannei3,Addressebanc,"",arraysebanone));
										
							}
										
										 
								//		 */
//								 
//								 //  第二部    ---diyModelSebanBo
//								// 这里根据 code  name 去diymodel里查询， 数据，
//								System.out.println(""+code+""+Dsebannei3);
//								  List<DiyModel> listleft=diyModelBo.findDiyModelByName(Dsebannei3);//organization2Bo.findByName("气窗");
//									// gjp
//								  List  lsebannei=new ArrayList();
//								  String Ddatasename=null;
//								  Long Ddataseid;
//								  String DAddressebanc=null;
//								  String  imgseban=null;
//								  for(int z=0;z<listleft.size();z++){ 
//										 Ddatasename=listleft.get(z).getName();
//										 Ddataseid=listleft.get(z).getId();
//											imgseban=listleft.get(u).getImg();
//										 System.out.println("------------chaxun"+Ddatasename+""+Ddataseid+"");
//										 
//										// list.add(buildjsonDiakaix(Ddataseid,Ddatasename,DAddressebanc));
//										 lsebannei.add(buildjsonDia(Ddataseid,Ddatasename,imgseban));
//									
//									//	 l.add(buildjsonlong(Didqtc3,Ddataqtc3));
//									 }
//									 lseban.add(buildjsonDiaSeban(Didseban3,Dsebannei3,Addressebanc,"",lsebannei));
//								 // lseban.add(e)
						
						 }
						arrayqichuang.add(buildjsonDiarray(Diseban,Ddaseban,lseban,typename));	
						
						
				   }
				 //  System.out.println("end 色板 "+arrayqichuang);
				   //resultend=buildjsondata(arrayseban);
				  // resultArraydoorend.add(buildjsondata(arrayseban));
	      }
			   

			   
			   
				   /***************************边框 ***************************************/
//				   JSONArray  arraybimentou=new JSONArray();
//				   JSONArray  arraybimentou2=new JSONArray();
//				   JSONArray  arraybimentou3=new JSONArray();
					if (chstyle.equals("mentoukuanshi")||chstyle.equals("menkuangkuanshi")||chstyle.equals("menzhukuanshi")) {
				   String Ddatabiank=null;
				   Long Didbiank;
				   String  modeldibuId=request.getParameter("modeldibuId");
				   System.out.println("得到的模版ID为"+modeldibuId);
//				   JSONArray  arraybimentou=new JSONArray();
//				   JSONArray  arraybimentou2=new JSONArray();
//				   JSONArray  arraybimentou3=new JSONArray();
				 
				   List<Organization2> listDbiank=organization2Bo.findByRemark("biankuang");//findByName("边框款式");
					for(int i=0;i<listDbiank.size();i++){ 
						Didbiank=listDbiank.get(i).getId();
						Ddatabiank=listDbiank.get(i).getName();
						System.out.println("边框款式1查询到"+Didbiank);
						List<Organization2> listDmenbiank=organization2Bo.findByNoparentOrganizationID(Didbiank);
						String Ddata2biank=null;
						Long Did2biank;
						for(int j=0;j<listDmenbiank.size();j++){ 
							 Ddata2biank = listDmenbiank.get(j).getName();
							Did2biank=listDmenbiank.get(j).getId();
						//	System.out.println("边框款式2查询到"+Did2biank+"名字为:"+Ddata2biank);
							// 以上是固定的结构：
							  List<Organization2> listDmentou=organization2Bo.findByNoparentOrganizationID(Did2biank);//查询到门头款式下的 子节点
							  if (chstyle.equals("mentoukuanshi")) {
							    long Didbiamen1 = 0;
								String Ddatabianmen1 = null;
							  if(Ddata2biank.equals("门头款式")){
								  String  typename=null;
									 for(int dw=0;dw<listDmentou.size();dw++){ 
										 Ddatabianmen1=listDmentou.get(dw).getName();
											Didbiamen1=listDmentou.get(dw).getId();
											typename=listDmentou.get(dw).getRemark();
								//	System.out.println("得到这个父结构的ID为"+Didbiamen1+Ddatabianmen1);
									List<Organization2> listDmenbinak1=organization2Bo.findByNoparentOrganizationID(Didbiamen1); // 查询到  9个节点下的数据
									
									
									
									
									List  lbinantwo=new ArrayList();
									  String Ddatabiantwo = null;
										 Long	Didbiantwo = null;
										 String Addressbtwo = null;
										 String remarkmentou =null;
									for(int z=0;z<listDmenbinak1.size();z++){ 
										Ddatabiantwo=listDmenbinak1.get(z).getName();
										 Didbiantwo=listDmenbinak1.get(z).getId();
										 Addressbtwo=listDmenbinak1.get(z).getAddress();
										 remarkmentou=listDmenbinak1.get(z).getRemark();
										 
										 System.out.println("门头款式的输出为i:"+remarkmentou);
										   if(modeldibuId=="101"||modeldibuId.equals("101")||modeldibuId=="102"||modeldibuId.equals("102")){
											//单门门头
												if(remarkmentou.equals("danmentou")||remarkmentou=="danmentou"||remarkmentou.equals("danmenmenzhu")||remarkmentou=="danmenmenzhu"){
													System.out.println("111");
													// System.out.println("门头 "+Ddatabiantwo+Didbiantwo+Addressbtwo);
													 // 查询到  边框款式 >门头款式>(门头款式，门柱款式)， 查询这2个下面的数据
														List<Department2> listorgbk=department2Bo.findByNoparentOrganizationID(Didbiantwo); // 查询到  9个节点下的数据
														  String Ddatab3 = null;
															 Long	Didbia3 = null;
															 String Addre3 = null;
														for(int f=0;f<listorgbk.size();f++){ 
															Ddatab3=listorgbk.get(f).getName();
															 Didbia3=listorgbk.get(f).getId();
															 Addre3=listorgbk.get(f).getAddress();
															 lbinantwo.add(buildjsonDia(Didbia3,Ddatab3,Addre3));
														}
												}
										   }
										if(modeldibuId=="103"||modeldibuId=="104"||modeldibuId.equals("103")||modeldibuId.equals("104")){
											//字母门头
						if(remarkmentou.equals("zimumentou")||remarkmentou=="zimumentou"||remarkmentou.equals("zimumenzhu")||remarkmentou=="zimumenzhu"){
							System.out.println("222");	
							// System.out.println("门头 "+Ddatabiantwo+Didbiantwo+Addressbtwo);
							 // 查询到  边框款式 >门头款式>(门头款式，门柱款式)， 查询这2个下面的数据
								List<Department2> listorgbk=department2Bo.findByNoparentOrganizationID(Didbiantwo); // 查询到  9个节点下的数据
								  String Ddatab3 = null;
									 Long	Didbia3 = null;
									 String Addre3 = null;
								for(int f=0;f<listorgbk.size();f++){ 
									Ddatab3=listorgbk.get(f).getName();
									 Didbia3=listorgbk.get(f).getId();
									 Addre3=listorgbk.get(f).getAddress();
									 lbinantwo.add(buildjsonDia(Didbia3,Ddatab3,Addre3));
								}
											}
										}
										
										if(modeldibuId=="201"||modeldibuId=="202"||modeldibuId=="203"||modeldibuId.equals("201")||modeldibuId.equals("201")||modeldibuId.equals("203")){
											//双开门+
						if(remarkmentou.equals("shuangkaimentou")||remarkmentou=="shuangkaimentou"||remarkmentou.equals("shuangkaimenzhu")||remarkmentou=="shuangkaimenzhu"||remarkmentou.equals("mentoukuanshi")||remarkmentou=="mentoukuanshi"||
								remarkmentou.equals("menkuangkuanshi")||remarkmentou=="menkuangkuanshi"){
							System.out.println("333");	
							// System.out.println("门头 "+Ddatabiantwo+Didbiantwo+Addressbtwo);
							 // 查询到  边框款式 >门头款式>(门头款式，门柱款式)， 查询这2个下面的数据
								List<Department2> listorgbk=department2Bo.findByNoparentOrganizationID(Didbiantwo); // 查询到  9个节点下的数据
								  String Ddatab3 = null;
									 Long	Didbia3 = null;
									 String Addre3 = null;
								for(int f=0;f<listorgbk.size();f++){ 
									Ddatab3=listorgbk.get(f).getName();
									 Didbia3=listorgbk.get(f).getId();
									 Addre3=listorgbk.get(f).getAddress();
									 lbinantwo.add(buildjsonDia(Didbia3,Ddatab3,Addre3));
								}
											}
										}
										if(modeldibuId=="401"||modeldibuId=="402"||modeldibuId=="403"||modeldibuId.equals("401")||modeldibuId.equals("402")||modeldibuId.equals("403")){
											//四开门+门
						if(remarkmentou.equals("sikaimentou")||remarkmentou=="sikaimentou"||remarkmentou.equals("sikaimenzhu")||remarkmentou=="sikaimenzhu"){
							System.out.println("444");		
							// System.out.println("门头 "+Ddatabiantwo+Didbiantwo+Addressbtwo);
							 // 查询到  边框款式 >门头款式>(门头款式，门柱款式)， 查询这2个下面的数据
								List<Department2> listorgbk=department2Bo.findByNoparentOrganizationID(Didbiantwo); // 查询到  9个节点下的数据
								  String Ddatab3 = null;
									 Long	Didbia3 = null;
									 String Addre3 = null;
								for(int f=0;f<listorgbk.size();f++){ 
									Ddatab3=listorgbk.get(f).getName();
									 Didbia3=listorgbk.get(f).getId();
									 Addre3=listorgbk.get(f).getAddress();
									 lbinantwo.add(buildjsonDia(Didbia3,Ddatab3,Addre3));
								}
											}
										}
										
										if(modeldibuId=="404"||modeldibuId=="405"||modeldibuId.equals("404")||modeldibuId.equals("405")){
					         	if(remarkmentou.equals("sikaizimumentou")||remarkmentou=="sikaizimumentou"||remarkmentou.equals("sikaizimumenzhu")||remarkmentou=="sikaizimumenzhu"){
					         		System.out.println("555");	
					         	// System.out.println("门头 "+Ddatabiantwo+Didbiantwo+Addressbtwo);
									 // 查询到  边框款式 >门头款式>(门头款式，门柱款式)， 查询这2个下面的数据
										List<Department2> listorgbk=department2Bo.findByNoparentOrganizationID(Didbiantwo); // 查询到  9个节点下的数据
										  String Ddatab3 = null;
											 Long	Didbia3 = null;
											 String Addre3 = null;
										for(int f=0;f<listorgbk.size();f++){ 
											Ddatab3=listorgbk.get(f).getName();
											 Didbia3=listorgbk.get(f).getId();
											 Addre3=listorgbk.get(f).getAddress();
											 lbinantwo.add(buildjsonDia(Didbia3,Ddatab3,Addre3));
										}
											}
										}			 
										 
										
											
										 
										
									 }
									arrayqichuang.add(buildjsonDiarray(Didbiamen1,Ddatabianmen1,lbinantwo,typename));	
									
							
									  
							   }	
								//	 System.out.println("门头 33"+arrayqichuang);
									   //resultend=buildjsondata(arraybimentou);
									  // resultArraydoorend.add(buildjsondata(arraybimentou));
								}
						} //if
							  if (chstyle.equals("menkuangkuanshi")) {
								  
							  /******************************************************************************/
							  long Didbiamen2 = 0;
								String Ddatabianmen2 = null;
								String  modeldibumkId=request.getParameter("modeldibumenkId");
								
							  if(Ddata2biank.equals("门框款式")){
								  String  tyremark=null;
									 for(int dw=0;dw<listDmentou.size();dw++){ 
										 Ddatabianmen2=listDmentou.get(dw).getName();
										 Didbiamen2=listDmentou.get(dw).getId();
										 tyremark=listDmentou.get(dw).getRemark();
									System.out.println("得到这个父结构的ID为"+Didbiamen2+Ddatabianmen2);
									
									
									  if(modeldibuId=="101"||modeldibuId.equals("101")||modeldibuId=="102"||modeldibuId.equals("102")){
											//单门门头
			      // 	if(tyremark.equals("danmenmenkuang")||tyremark=="danmenmenkuang"){
													System.out.println("111");
													List<Department2> listDmenbinak1=department2Bo.findByNoparentOrganizationID(Didbiamen2); // 查询到  9个节点下的数据
													
													List  lbinantwo2=new ArrayList();
													  String Ddatabiantwo2 = null;
														 Long	Didbiantwo2 = null;
														 String Addressbtwo2 = null;
													for(int z=0;z<listDmenbinak1.size();z++){ 
														Ddatabiantwo2=listDmenbinak1.get(z).getName();
														 Didbiantwo2=listDmenbinak1.get(z).getId();
														 Addressbtwo2=listDmenbinak1.get(z).getAddress();
														 lbinantwo2.add(buildjsonDia(Didbiantwo2,Ddatabiantwo2,Addressbtwo2));
													 }
													arrayqichuang.add(buildjsonDiarray(Didbiamen2,Ddatabianmen2,lbinantwo2,tyremark));	
														
					//							}
										   }
										if(modeldibuId=="103"||modeldibuId=="104"||modeldibuId.equals("103")||modeldibuId.equals("104")){
											//字母门头
					//	if(tyremark.equals("zimumenkuang")||tyremark=="zimumenkuang"){
							System.out.println("222");	
							List<Department2> listDmenbinak1=department2Bo.findByNoparentOrganizationID(Didbiamen2); // 查询到  9个节点下的数据
							
							List  lbinantwo2=new ArrayList();
							  String Ddatabiantwo2 = null;
								 Long	Didbiantwo2 = null;
								 String Addressbtwo2 = null;
							for(int z=0;z<listDmenbinak1.size();z++){ 
								Ddatabiantwo2=listDmenbinak1.get(z).getName();
								 Didbiantwo2=listDmenbinak1.get(z).getId();
								 Addressbtwo2=listDmenbinak1.get(z).getAddress();
								 lbinantwo2.add(buildjsonDia(Didbiantwo2,Ddatabiantwo2,Addressbtwo2));
							 }
							arrayqichuang.add(buildjsonDiarray(Didbiamen2,Ddatabianmen2,lbinantwo2,tyremark));	
											}
						//				}
										
										if(modeldibuId=="201"||modeldibuId=="202"||modeldibuId=="203"||modeldibuId.equals("201")||modeldibuId.equals("201")||modeldibuId.equals("203")){
											//双开门+
					//	if(tyremark.equals("shuangkaimenkuang")||tyremark=="shuangkaimenkuang"){
							System.out.println("333");
							List<Department2> listDmenbinak1=department2Bo.findByNoparentOrganizationID(Didbiamen2); // 查询到  9个节点下的数据
							
							List  lbinantwo2=new ArrayList();
							  String Ddatabiantwo2 = null;
								 Long	Didbiantwo2 = null;
								 String Addressbtwo2 = null;
							for(int z=0;z<listDmenbinak1.size();z++){ 
								Ddatabiantwo2=listDmenbinak1.get(z).getName();
								 Didbiantwo2=listDmenbinak1.get(z).getId();
								 Addressbtwo2=listDmenbinak1.get(z).getAddress();
								 lbinantwo2.add(buildjsonDia(Didbiantwo2,Ddatabiantwo2,Addressbtwo2));
							 }
							arrayqichuang.add(buildjsonDiarray(Didbiamen2,Ddatabianmen2,lbinantwo2,tyremark));	
							
						//					}
										}
										if(modeldibuId=="401"||modeldibuId=="402"||modeldibuId=="403"||modeldibuId.equals("401")||modeldibuId.equals("402")||modeldibuId.equals("403")){
											//四开门+门
					//	if(tyremark.equals("sikaimenkuang")||tyremark=="sikaimenkuang"){
							System.out.println("444");		
							List<Department2> listDmenbinak1=department2Bo.findByNoparentOrganizationID(Didbiamen2); // 查询到  9个节点下的数据
							
							List  lbinantwo2=new ArrayList();
							  String Ddatabiantwo2 = null;
								 Long	Didbiantwo2 = null;
								 String Addressbtwo2 = null;
							for(int z=0;z<listDmenbinak1.size();z++){ 
								Ddatabiantwo2=listDmenbinak1.get(z).getName();
								 Didbiantwo2=listDmenbinak1.get(z).getId();
								 Addressbtwo2=listDmenbinak1.get(z).getAddress();
								 lbinantwo2.add(buildjsonDia(Didbiantwo2,Ddatabiantwo2,Addressbtwo2));
							 }
							arrayqichuang.add(buildjsonDiarray(Didbiamen2,Ddatabianmen2,lbinantwo2,tyremark));	
						
								
					//						}
										}
										
										if(modeldibuId=="404"||modeldibuId=="405"||modeldibuId.equals("404")||modeldibuId.equals("405")){
					       //  	if(tyremark.equals("sikaizimumenkuang")||tyremark=="sikaizimumenkuang"){
					         		System.out.println("555");	
                             List<Department2> listDmenbinak1=department2Bo.findByNoparentOrganizationID(Didbiamen2); // 查询到  9个节点下的数据
									
									List  lbinantwo2=new ArrayList();
									  String Ddatabiantwo2 = null;
										 Long	Didbiantwo2 = null;
										 String Addressbtwo2 = null;
									for(int z=0;z<listDmenbinak1.size();z++){ 
										Ddatabiantwo2=listDmenbinak1.get(z).getName();
										 Didbiantwo2=listDmenbinak1.get(z).getId();
										 Addressbtwo2=listDmenbinak1.get(z).getAddress();
										 lbinantwo2.add(buildjsonDia(Didbiantwo2,Ddatabiantwo2,Addressbtwo2));
									 }
									arrayqichuang.add(buildjsonDiarray(Didbiamen2,Ddatabianmen2,lbinantwo2,tyremark));	
											}
						//				}			 
										 
										
									
									  
							   }	
									 System.out.println("门框"+arrayqichuang);
								
									//   resultArraydoorend.add(buildjsondata(arraybimentou2));
								}
							  } //if
							/**************************************3********************************
							  if (chstyle.equals("biankz")) {
							  long Didbiamen3 = 0;
								String Ddatabianmen3 = null;
							  if(Ddata2biank.equals("门柱款式")){
									 for(int dw=0;dw<listDmentou.size();dw++){ 
										 Ddatabianmen3=listDmentou.get(dw).getName();
										 Didbiamen3=listDmentou.get(dw).getId();
									System.out.println("得到这个父结构的ID为"+Didbiamen3+Ddatabianmen3);
									List<Department2> listDmenbinak1=department2Bo.findByNoparentOrganizationID(Didbiamen3); // 查询到  9个节点下的数据
									
									List  lbinantwo3=new ArrayList();
									  String Ddatabiantwo3 = null;
										 Long	Didbiantwo3 = null;
										 String Addressbtwo3 = null;
									for(int z=0;z<listDmenbinak1.size();z++){ 
										Ddatabiantwo3=listDmenbinak1.get(z).getName();
										 Didbiantwo3=listDmenbinak1.get(z).getId();
										 Addressbtwo3=listDmenbinak1.get(z).getAddress();
										 lbinantwo3.add(buildjsonDia(Didbiantwo3,Ddatabiantwo3,Addressbtwo3));
									 }
									arrayqichuang.add(buildjsonDiarray(Didbiamen3,Ddatabianmen3,lbinantwo3));	
									  
							   }	
									 System.out.println("门柱 "+arrayqichuang);
									 // resultend=buildjsondata(arraybimentou3);
									//  resultArraydoorend.add(buildjsondata(arraybimentou3));
								}
							
							  }// if********/
						}
					}
	                        }
					/***************************配件***************************************/
					//  JSONArray  arraypeijian=new JSONArray();
					if (chstyle.equals("peijian")) {
					   long Dipeijian = 0;
						String DNpeijian = null;
						   List<Organization2> listpeijian=organization2Bo.findByRemark("peijian");//findByName("配件");
						   
							 Dipeijian = listpeijian.get(0).getId();
							 DNpeijian=listpeijian.get(0).getName();
						   List<Organization2> listDpeijian=organization2Bo.findByNoparentOrganizationID(Dipeijian);
						   String  typename=null;
						   for(int k=0;k<listDpeijian.size();k++){ 
							   String Ddapeijian2 = listDpeijian.get(k).getName();
							   Long Dipeijain2 = listDpeijian.get(k).getId();
							   typename = listDpeijian.get(k).getRemark();
								System.out.println("得到这个父结构的ID为"+Dipeijain2+Dipeijain2); 
								List<Department2> listDpeijian2=department2Bo.findByNoparentOrganizationID(Dipeijain2); // 查询到  9个节点下的数据
								List  lsebpeijian=new ArrayList();
								  String Dpeijiannei3 = null;
									 Long	Dipeij3 = null;
									 String Addresspeijc = null;
								for(int u=0;u<listDpeijian2.size();u++){ 
									Dpeijiannei3=listDpeijian2.get(u).getName();
									Dipeij3=listDpeijian2.get(u).getId();
									Addresspeijc=listDpeijian2.get(u).getAddress();
									lsebpeijian.add(buildjsonDia(Dipeij3,Dpeijiannei3,Addresspeijc));
								 }
								
								arrayqichuang.add(buildjsonDiarray(Dipeijain2,Ddapeijian2,lsebpeijian,typename));	
								
						   }
						   System.out.println("end 色板 "+arrayqichuang);
						   
							 // resultend=buildjsondata(arraypeijian);
							  
							//  resultArraydoorend.add(buildjsondata(arraypeijian));
				  
					}
					/****************************7 板材*********************************/
					
					if (chstyle.equals("bancai")) {
						   long Disebanbcai = 0;
							String Ddasebanbcai = null;
							   List<Organization2> listDsebanbanc=organization2Bo.findByRemark("bancai");//findByName("板材");
								Long idsebanbc = listDsebanbanc.get(0).getId();
								String name3sebanc=listDsebanbanc.get(0).getName();
							   List<Organization2> listDsebanck=organization2Bo.findByNoparentOrganizationID(idsebanbc);
								String  typenamebc=null;
							   for(int k=0;k<listDsebanck.size();k++){ 
								   Ddasebanbcai=listDsebanck.get(k).getName();
								   Disebanbcai=listDsebanck.get(k).getId();
								   typenamebc=listDsebanck.get(k).getRemark();
									System.out.println("得到这个父结构的ID为"+Disebanbcai+Ddasebanbcai); 
									List<Department2> listDmsebanbck=department2Bo.findByNoparentOrganizationID(Disebanbcai); // 查询到  9个节点下的数据
									
									List  lsebanbc=new ArrayList();
									  String Dsebancai12 = null;
										 Long	Didsebancai12 = null;
										 String Addressebancai12 = null;
										 String typenamebckd=null;
									for(int u=0;u<listDmsebanbck.size();u++){ 
										Dsebancai12=listDmsebanbck.get(u).getName();
										Didsebancai12=listDmsebanbck.get(u).getId();
										Addressebancai12=listDmsebanbck.get(u).getAddress();
										typenamebckd=listDmsebanbck.get(u).getRemark();
										lsebanbc.add(buildjsonDia(Didsebancai12,Dsebancai12,typenamebckd));
									 }
									
									arrayqichuang.add(buildjsonDiarray(idsebanbc,Ddasebanbcai,lsebanbc,typenamebc));	
									
							   }
							 //  System.out.println("end 色板 "+arrayqichuang);
							   //resultend=buildjsondata(arrayseban);
							  // resultArraydoorend.add(buildjsondata(arrayseban));
				      }
					
			/***********************拼接 开始***************************************/

		
//						   StringBuffer buf = new StringBuffer(); 
//							buf.append("[").append(arrayDi1end); 
//							buf.append(", ").append(arrayhuazhione);
//							buf.append(", ").append(arrayhuazhitwo);
//						buf.append(", ").append(arraybimentou);
//							buf.append(", ").append(arraybimentou2); 
//							buf.append(", ").append(arraybimentou3);
//							buf.append(", ").append(arraypeijian);  //色板
//							buf.append(", ").append(arrayseban); 
//							buf.append(", ").append(arrayqichuang).append("]");  
							//buf.append(", ").append(result8); 
							//buf.append(", ").append(result9).append("]"); 
							//    buf.append(" ").append(result9); 
							  //  buf.append(" ").append(result2); 
							
//							String str = buf.toString(); 
					
							System.out.println(" 底部列表对象为"+arrayqichuang); 
							
		
				
							response.reset();
							response.setCharacterEncoding("utf-8");

							response.setContentType("text/json; charset=UTF-8");
							PrintWriter writer = response.getWriter();
							ret.put("data", arrayqichuang.toString());
							writer.write(callback + "(" + ret.toString() + ")");
							writer.flush();
		
				} // else  
			
			
			
	//}

	private JSONObject buildsoor(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);

		return result;
	}

	/**************************** 门款式 底部数据  压花 款式**********************************************/
	@RequestMapping(value = "/pages/company/doordtyaCombo.do")
	public void doordtyaCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String chstyle = request.getParameter("chstyle"); // 选择是压花 还是花枝
		String flow = request.getParameter("flow");

		JSONArray result = new JSONArray();
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		 ret.put("total", 4);
		
		 
		  JSONArray  arrayqichuang=new JSONArray();
		  if (chstyle.equals("qichuang")) {
		  long Didqc = 0;
			String Ddataqc = null;
			   List<Organization2> listDpp=organization2Bo.findByRemark("qichuang");//findByName("气窗");
				Long id = listDpp.get(0).getId();
				String name3=listDpp.get(0).getName();
				
			   List<Organization2> listDmen3c=organization2Bo.findByNoparentOrganizationID(id);
			  
					   for(int k=0;k<listDmen3c.size();k++){ 
						   Ddataqc=listDmen3c.get(k).getName();
						   Didqc=listDmen3c.get(k).getId();
							System.out.println("得到这个父结构的ID为"+Didqc+Ddataqc); 
							List<Department2> listDmenqian=department2Bo.findByNoparentOrganizationID(Didqc); // 查询到  9个节点下的数据
							List  l=new ArrayList();
							  String Ddataqc3 = null;
								 Long	Didqc3 = null;
								 String Addressqc = null;
							for(int z=0;z<listDmenqian.size();z++){ 
								 Ddataqc3=listDmenqian.get(z).getName();
								 Didqc3=listDmenqian.get(z).getId();
								 Addressqc=listDmenqian.get(z).getAddress();
								 l.add(buildjsonDia(Didqc3,Ddataqc3,Addressqc));
							 }
							arrayqichuang.add(buildjsonDiarray(Didqc,Ddataqc,l,"qichuang"));	
							
					   }
			   System.out.println("end 气窗 "+arrayqichuang);
			  
		   }
		  
		  
		  
		 

		response.reset();
		response.setCharacterEncoding("utf-8");

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ret.put("data", arrayqichuang.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildflowone(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}

	/**************************** 门款式 底部数据 **********************************************/
	@RequestMapping(value = "/pages/company/doordtCombo.do")
	public void doordtCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String chstyle = request.getParameter("chstyle"); // 选择是压花 还是花枝
		String flow = request.getParameter("flow");

		JSONArray result = new JSONArray();
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		
		if (chstyle == null) {
			result.add(buildflow("none", "请先选择门面款式","")); // 选择花枝 款式
		} else if ((chstyle.equals("02") || chstyle == "02")
				&& (flow.equals("01") || flow == "01")) {

			result.add(buildflow("1", "铁艺花","img/huazhi/01.png"));
			result.add(buildflow("2", " 铸铝花","img/huazhi/01.png"));
			result.add(buildflow("3", "不锈钢一类花","img/huazhi/01.png"));
			result.add(buildflow("4", "不锈钢二类花","img/huazhi/01.png"));
			result.add(buildflow("5", "不锈钢三类花","img/huazhi/01.png"));
			result.add(buildflow("6", " 不锈钢四类花","img/huazhi/01.png"));
			result.add(buildflow("7", "不锈钢五类花","img/huazhi/01.png"));
			result.add(buildflow("8", "不锈钢六类花","img/huazhi/01.png"));
			result.add(buildflow("9", "不锈钢七类花","img/huazhi/01.png"));
			result.add(buildflow("10", "不锈钢精品花","img/huazhi/01.png"));
			result.add(buildflow("11", "不锈钢工艺花","img/huazhi/01.png"));
			 ret.put("total", 11);
		} else if (chstyle.equals("02") || chstyle == "02" || flow.equals("02")
				|| flow == "02") {
			result.add(buildflow("1", "不锈钢一类花","img/huazhi/01.png"));
			result.add(buildflow("2", " 不锈钢二类花","img/huazhi/01.png"));
			result.add(buildflow("3", "不锈钢三类花","img/huazhi/01.png"));
			result.add(buildflow("4", "不锈钢四类花","img/huazhi/01.png"));
			result.add(buildflow("5", "不锈钢五类花","img/huazhi/01.png"));
			result.add(buildflow("6", " 不锈钢六类花","img/huazhi/01.png"));
			result.add(buildflow("7", "不锈钢七类花","img/huazhi/01.png"));
			result.add(buildflow("8", "不锈钢精品花","img/huazhi/01.png"));
			result.add(buildflow("9", "不锈钢工艺花","img/huazhi/01.png"));
			 ret.put("total", 9);
		} else if (chstyle.equals("02") || chstyle == "02" || flow.equals("03")
				|| flow == "03") {
			result.add(buildflow("1", "不锈钢一类花","img/huazhi/01.png"));
			result.add(buildflow("2", " 不锈钢二类花","img/huazhi/01.png"));
			result.add(buildflow("3", "不锈钢三类花","img/huazhi/01.png"));
			result.add(buildflow("4", "不锈钢四类花","img/huazhi/01.png"));
			result.add(buildflow("5", "不锈钢五类花","img/huazhi/01.png"));
			result.add(buildflow("6", " 不锈钢六类花","img/huazhi/01.png"));
			result.add(buildflow("7", "不锈钢七类花","img/huazhi/01.png"));
			result.add(buildflow("8", "不锈钢精品花","img/huazhi/01.png"));
			result.add(buildflow("9", "不锈钢工艺花","img/huazhi/01.png"));
			 ret.put("total", 9);
		} else if (chstyle.equals("02") || chstyle == "02" || flow.equals("04")
				|| flow == "04") {
			result.add(buildflow("1", "不锈钢一类花","img/huazhi/01.png"));
			result.add(buildflow("2", " 不锈钢二类花","img/huazhi/01.png"));
			result.add(buildflow("3", "不锈钢三类花","img/huazhi/01.png"));
			result.add(buildflow("4", "不锈钢四类花","img/huazhi/01.png"));
			result.add(buildflow("5", "不锈钢五类花","img/huazhi/01.png"));
			result.add(buildflow("6", " 不锈钢六类花","img/huazhi/01.png"));
			result.add(buildflow("7", "不锈钢七类花","img/huazhi/01.png"));
			result.add(buildflow("8", "不锈钢精品花","img/huazhi/01.png"));
			result.add(buildflow("9", "不锈钢工艺花","img/huazhi/01.png"));
			ret.put("total", 9);
		}

		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildflow(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}

	/****************** 门面 下方的玻璃 ******************/
	@RequestMapping(value = "/pages/company/sdoorboliCombo.do")
	public void sdoorboliCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String chstyle = request.getParameter("chstyle");
		JSONArray result = new JSONArray();
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		 

		if (chstyle == null) {
			result.add(buildsoorboli("none", "请先选择门面款式压花款式"));
		} else if (chstyle.equals("02") || chstyle == "02") {
			// 选择了压花款式
			// 返回数据
			result.add(buildsoorboli("1", "普通"));
			result.add(buildsoorboli("2", "镀膜"));
			result.add(buildsoorboli("3", "磨纱"));
			result.add(buildsoorboli("4", "钢化"));
			result.add(buildsoorboli("5", "艺术"));
			ret.put("total", 5);

			// 调用玻璃的方法

		}

		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildsoorboli(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);

		return result;
	}

	/****************** 子门  右边 下拉框 数据*****************/
	@RequestMapping(value = "/pages/company/zidoorCombo.do")
	public void zidoorCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String chstyle = request.getParameter("chstyle");
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		 ret.put("total", 8);
		JSONArray result = new JSONArray();

		if (chstyle == null) {
			result.add(buildzioor("none", "请先选择子门面款式"));
		} else if (chstyle.equals("01") || chstyle == "01") {
			// 选择了压花款式

			// 返回数据

			System.out.println(",,,,,,,,,,");
			result.add(buildzioor("1", "可选"));
			result.add(buildzioor("2", "新欧典"));
			result.add(buildzioor("3", "闭月羞花"));
			result.add(buildzioor("4", "大三方"));
			result.add(buildzioor("5", "一帆风顺"));
			result.add(buildzioor("6", "长三方"));
			result.add(buildzioor("7", "平安万福"));
			result.add(buildzioor("8", "配套"));
			// 调用玻璃的方法

		} else if (chstyle.equals("02") || chstyle == "02") {
			// 选择了花枝款式
			result.add(buildzioor("1", "压花"));
			result.add(buildzioor("2", "铁艺花"));
			result.add(buildzioor("3", "花枝"));
		}

		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildzioor(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);

		return result;
	}

	/**************************** 子门款式 底部数据 **********************************************/
	@RequestMapping(value = "/pages/company/zidoordtCombo.do")
	public void zidoordtCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String chstyle = request.getParameter("chstyle"); // 选择是压花 还是花枝
		String flow = request.getParameter("flow");
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		 ret.put("total", 8);
		
		JSONArray result = new JSONArray();

		if (chstyle == null) {
			result.add(buildzidt("none", "请先选择门面款式","")); // 选择花枝 款式
		} else if ((chstyle.equals("02") || chstyle == "02")
				&& (flow.equals("01") || flow == "01")) {

			result.add(buildzidt("1", "子门1","/img/zimen/01.png"));
			result.add(buildzidt("2", " 子门2","/img/zimen/01.png"));
			result.add(buildzidt("3", "子门3","/img/zimen/01.png"));
			result.add(buildzidt("4", "子门4","/img/zimen/01.png"));
			result.add(buildzidt("5", "子门5","/img/zimen/01.png"));
			result.add(buildzidt("6", "子门6","/img/zimen/01.png"));
			result.add(buildzidt("7", "子门7","/img/zimen/01.png"));
			result.add(buildzidt("8", "子门8","/img/zimen/01.png"));

		} else if (chstyle.equals("02") || chstyle == "02" || flow.equals("02")
				|| flow == "02") {
			result.add(buildzidt("1", "子门1","/img/zimen/01.png"));
			result.add(buildzidt("2", " 子门2","/img/zimen/01.png"));
			result.add(buildzidt("3", "子门3","/img/zimen/01.png"));
			result.add(buildzidt("4", "子门4","/img/zimen/01.png"));
			result.add(buildzidt("5", "子门5","/img/zimen/01.png"));
			result.add(buildzidt("6", "子门6","/img/zimen/01.png"));
			result.add(buildzidt("7", "子门7","/img/zimen/01.png"));
			result.add(buildzidt("8", "子门8","/img/zimen/01.png"));
		} else if (chstyle.equals("02") || chstyle == "02" || flow.equals("03")
				|| flow == "03") {
			result.add(buildzidt("1", "Z01","/img/zimen/01.png"));
			result.add(buildzidt("2", " Z02","/img/zimen/01.png"));
			result.add(buildzidt("3", "Z03","/img/zimen/01.png"));
			result.add(buildzidt("4", "Z04","/img/zimen/01.png"));
			result.add(buildzidt("5", "Z05","/img/zimen/01.png"));
			result.add(buildzidt("6", " Z06","/img/zimen/01.png"));
			result.add(buildzidt("7", "Z07","/img/zimen/01.png"));
			result.add(buildzidt("8", "Z08","/img/zimen/01.png"));

		}

		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildzidt(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}

	/******************** 气窗 **********************/
	@RequestMapping(value = "/pages/company/cascadeCombo.do")
	public void cascadeCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String qichuang = request.getParameter("qichuang");
		
		JSONArray result = new JSONArray();
		String callback = request.getParameter("callback");
		JSONObject ret=new JSONObject(); 
		ret.put("total", 8);
		if (qichuang == null) {
			result.add(buildQi("none", "请先选择气窗",""));
		} else if (qichuang.equals("01") || qichuang == "01") {
			

			result.add(buildQi("1", "1号","img/qich/01.png"));
			result.add(buildQi("2", "2号","img/qich/01.png"));
			result.add(buildQi("3", "3号","img/qich/01.png"));
			result.add(buildQi("4", "4号","img/qich/01.png"));
			result.add(buildQi("5", "5号","img/qich/01.png"));
			result.add(buildQi("6", "6号","img/qich/01.png"));
			result.add(buildQi("7", "7号","img/qich/01.png"));
			result.add(buildQi("8", "8号","img/qich/01.png"));
		} else if (qichuang.equals("02") || qichuang == "02") {
			result.add(buildQi("1", "1号","img/qich/01.png"));
			result.add(buildQi("2", "2号","img/qich/01.png"));
			result.add(buildQi("3", "3号","img/qich/01.png"));
			result.add(buildQi("4", "4号","img/qich/01.png"));
			result.add(buildQi("5", "5号","img/qich/01.png"));
			result.add(buildQi("6", "6号","img/qich/01.png"));
			result.add(buildQi("7", "7号","img/qich/01.png"));
			result.add(buildQi("8", "8号","img/qich/01.png"));
		} else if (qichuang.equals("03") || qichuang == "03") {
			result.add(buildQi("1", "1号","img/qich/01.png"));
			result.add(buildQi("2", "2号","img/qich/01.png"));
			result.add(buildQi("3", "3号","img/qich/01.png"));
			result.add(buildQi("4", "4号","img/qich/01.png"));
			result.add(buildQi("5", "5号","img/qich/01.png"));
			result.add(buildQi("6", "6号","img/qich/01.png"));
			result.add(buildQi("7", "7号","img/qich/01.png"));
			result.add(buildQi("8", "8号","img/qich/01.png"));
		} else if (qichuang.equals("04") || qichuang == "04") {
			result.add(buildQi("1", "1号","img/qich/01.png"));
			result.add(buildQi("2", "2号","img/qich/01.png"));
			result.add(buildQi("3", "3号","img/qich/01.png"));
			result.add(buildQi("4", "4号","img/qich/01.png"));
			result.add(buildQi("5", "5号","img/qich/01.png"));
			result.add(buildQi("6", "6号","img/qich/01.png"));
			result.add(buildQi("7", "7号","img/qich/01.png"));
			result.add(buildQi("8", "8号","img/qich/01.png"));
		}

		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildQi(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}

	/****************************** 色板   ***********************************************/
	@RequestMapping(value = "/pages/company/sebanCombo.do")
	public void sebanCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String seban = request.getParameter("seban");
		System.out.println(",,,,,,,,,," + seban);
		JSONArray result = new JSONArray();
		JSONObject ret=new JSONObject(); 
		String callback = request.getParameter("callback");
		ret.put("total", 8);
		if (seban == null) {
			result.add(buildSe("none", "请先选择色板",""));
		} else if (seban.equals("01") || seban == "01") {
			System.out.println(",,,,,,,,,,");

			result.add(buildSe("1", "准铜1号","img/diypic/01.png"));
			result.add(buildSe("2", " 准黑铜","img/diypic/01.png"));
			result.add(buildSe("3", "准紫铜","img/diypic/01.png"));
			result.add(buildSe("4", "准红铜","img/diypic/01.png"));
			result.add(buildSe("5", "仿真紫铜","img/diypic/01.png"));
			result.add(buildSe("6", "仿真铜","img/diypic/01.png"));
			result.add(buildSe("7", "7号","img/diypic/01.png"));
			result.add(buildSe("8", "8号","img/diypic/01.png"));
		} else if (seban.equals("02") || seban == "02") {
			result.add(buildSe("1", "深灰色","img/diypic/01.png"));
			result.add(buildSe("2", "纯白色","img/diypic/01.png"));
			result.add(buildSe("3", "星际绿","img/diypic/01.png"));
			result.add(buildSe("4", "金黄色","img/diypic/01.png"));
			result.add(buildSe("5", "电信蓝","img/diypic/01.png"));
			result.add(buildSe("6", " 洒红","img/diypic/01.png"));
			result.add(buildSe("7", " 湖蓝","img/diypic/01.png"));
			result.add(buildSe("8", "深咖啡2","img/diypic/01.png"));
		}
       System.out.println("^"+result);
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
        
		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildSe(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}
	
	
	
	/****************************** 边框  以一个下拉框选择  ***********************************************/
	/***************************** 边框  右侧下拉框**********************************************/
	@RequestMapping(value = "/pages/company/biankCombo.do")
	public void biankCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String biank = request.getParameter("biank");
		System.out.println(",,,,,,,,,," + biank);
		JSONArray result = new JSONArray();
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		
		if (biank == null) {
			result.add(buildbiank("none", "请先选择边框"));
		} else if (biank.equals("01") || biank == "01") {

			result.add(buildbiank("1", "子母门头"));
			result.add(buildbiank("2", " 双开门头"));
			result.add(buildbiank("3", "四开子母门头"));
			result.add(buildbiank("4", "四开门头"));
			 ret.put("total", 4);

		} else if (biank.equals("02") || biank == "02") {

			result.add(buildbiank("1", "单门门柱"));
			result.add(buildbiank("2", "子母门柱"));
			result.add(buildbiank("3", "双开门柱"));
			result.add(buildbiank("4", "四开子母门柱"));
			result.add(buildbiank("5", "四开门柱"));
			 ret.put("total", 5);

		} else if (biank.equals("03") || biank == "03") {
			result.add(buildbiank("1", "单门门框"));
			result.add(buildbiank("2", "子母门框"));
			result.add(buildbiank("3", "双开门框"));
			result.add(buildbiank("4", "四开子母门框"));
			result.add(buildbiank("5", "四开门框"));
			 ret.put("total", 5);

		}

		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildbiank(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);

		return result;
	}

	/***************************** 边框 底部数据 **********************************************/
	@RequestMapping(value = "/pages/company/biankdtCombo.do")
	public void biankdtCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String biank = request.getParameter("biank");
		String biankdt = request.getParameter("biankdt");
		String biankzhu = request.getParameter("biankzhu");
		System.out.println(",,,,,,,,,," + biank);
		JSONArray result = new JSONArray();
		 String callback = request.getParameter("callback");
		 JSONObject ret=new JSONObject(); 
		 ret.put("total", 8);
		if (biank == null) {
			result.add(buildbiankdt("none", "请先选择门头",""));
		} else if (biank.equals("01") || biank == "01" || biankdt.equals("01")
				|| biankdt == "01") {
			result.add(buildbiankdt("1", "门头1号","img/mentou/01.png"));
			result.add(buildbiankdt("2", " 门头2号","img/mentou/01.png"));
			result.add(buildbiankdt("3", "门头3号","img/mentou/01.png"));
			result.add(buildbiankdt("4", "门头4号","img/mentou/01.png"));
			result.add(buildbiankdt("5", "门头5号","img/mentou/01.png"));
			result.add(buildbiankdt("6", " 门头6号","img/mentou/01.png"));
			result.add(buildbiankdt("7", "门头7号","img/mentou/01.png"));
			result.add(buildbiankdt("8", "门头8号","img/mentou/01.png"));
		} else if (biank.equals("01") || biank == "01" || biankdt.equals("02")
				|| biankdt == "02") {
			result.add(buildbiankdt("1", "门头1号","img/mentou/02.png"));
			result.add(buildbiankdt("2", " 门头2号","img/mentou/02.png"));
			result.add(buildbiankdt("3", "门头3号","img/mentou/02.png"));
			result.add(buildbiankdt("4", "门头4号","img/mentou/02.png"));
			result.add(buildbiankdt("5", "门头5号","img/mentou/02.png"));
			result.add(buildbiankdt("6", " 门头6号","img/mentou/02.png"));
			result.add(buildbiankdt("7", "门头7号","img/mentou/02.png"));
			result.add(buildbiankdt("8", "门头8号","img/mentou/02.png"));
		} else if (biank.equals("01") || biank == "01" || biankdt.equals("03")
				|| biankdt == "03") {
			result.add(buildbiankdt("1", "门头1号","img/mentou/03.png"));
			result.add(buildbiankdt("2", " 门头2号","img/mentou/03.png"));
			result.add(buildbiankdt("3", "门头3号","img/mentou/03.png"));
			result.add(buildbiankdt("4", "门头4号","img/mentou/03.png"));
			result.add(buildbiankdt("5", "门头5号","img/mentou/03.png"));
			result.add(buildbiankdt("6", " 门头6号","img/mentou/03.png"));
			result.add(buildbiankdt("7", "门头7号","img/mentou/03.png"));
			result.add(buildbiankdt("8", "门头8号","img/mentou/03.png"));
		} else if (biank.equals("01") || biank == "01" || biankdt.equals("04")
				|| biankdt == "04") {
			result.add(buildbiankdt("1", "门头1号","img/mentou/04.png"));
			result.add(buildbiankdt("2", " 门头2号","img/mentou/04.png"));
			result.add(buildbiankdt("3", "门头3号","img/mentou/04.png"));
			result.add(buildbiankdt("4", "门头4号","img/mentou/04.png"));
			result.add(buildbiankdt("5", "门头5号","img/mentou/04.png"));
			result.add(buildbiankdt("6", " 门头6号","img/mentou/04.png"));
			result.add(buildbiankdt("7", "门头7号","img/mentou/04.png"));
			result.add(buildbiankdt("8", "门头8号","img/mentou/04.png"));
		}

		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}
	/***************************** 边框 底部数据 **********************************************/
	@RequestMapping(value = "/pages/company/biankzhuCombo.do")
	public void biankzhuCombo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
        String callback = request.getParameter("callback");
		String biank = request.getParameter("biank");
		String biankzhu = request.getParameter("biankzhu");
	
		JSONArray result = new JSONArray();
		JSONObject ret=new JSONObject(); 
		ret.put("total", 8);
		
		if (biank == null) {
			result.add(buildbiankzhu("none", "请先选择门柱",""));
		} else if (biank.equals("02") || biank == "02" || biankzhu.equals("01")
				|| biankzhu == "01") {
			result.add(buildbiankzhu("1", "门柱1号","img/menzhu/01.png"));
			result.add(buildbiankzhu("2", " 门柱2号","img/menzhu/01.png"));
			result.add(buildbiankzhu("3", "门柱3号","img/menzhu/01.png"));
			result.add(buildbiankzhu("4", "门柱4号","img/menzhu/01.png"));
			result.add(buildbiankzhu("5", "门柱5号","img/menzhu/01.png"));
			result.add(buildbiankzhu("6", " 门柱6号","img/menzhu/01.png"));
			result.add(buildbiankzhu("7", "门柱7号","img/menzhu/01.png"));
			result.add(buildbiankzhu("8", "门柱8号","img/menzhu/01.png"));
		} else if (biank.equals("02") || biank == "02" || biankzhu.equals("02")
				|| biankzhu == "02") {
			result.add(buildbiankzhu("1", "门柱1号","img/menzhu/01.png"));
			result.add(buildbiankzhu("2", " 门柱2号","img/menzhu/01.png"));
			result.add(buildbiankzhu("3", "门柱3号","img/menzhu/01.png"));
			result.add(buildbiankzhu("4", "门柱4号","img/menzhu/01.png"));
			result.add(buildbiankzhu("5", "门柱5号","img/menzhu/01.png"));
			result.add(buildbiankzhu("6", " 门柱6号","img/menzhu/01.png"));
			result.add(buildbiankzhu("7", "门柱7号","img/menzhu/01.png"));
			result.add(buildbiankzhu("8", "门柱8号","img/menzhu/01.png"));
		} else if (biank.equals("02") || biank == "02" || biankzhu.equals("03")
				|| biankzhu == "03") {
			result.add(buildbiankzhu("1", "门柱1号","img/menzhu/01.png"));
			result.add(buildbiankzhu("2", " 门柱2号","img/menzhu/01.png"));
			result.add(buildbiankzhu("3", "门柱3号","img/menzhu/01.png"));
			result.add(buildbiankzhu("4", "门柱4号","img/menzhu/01.png"));
			result.add(buildbiankzhu("5", "门柱5号","img/menzhu/01.png"));
			result.add(buildbiankzhu("6", " 门柱6号","img/menzhu/01.png"));
			result.add(buildbiankzhu("7", "门柱7号","img/menzhu/01.png"));
			result.add(buildbiankzhu("8", "门柱8号","img/menzhu/01.png"));
		} else if (biank.equals("02") || biank == "02" || biankzhu.equals("04")
				|| biankzhu == "04") {
			result.add(buildbiankzhu("1", "门柱1号","img/menzhu/01.png"));
			result.add(buildbiankzhu("2", " 门柱2号","img/menzhu/01.png"));
			result.add(buildbiankzhu("3", "门柱3号","img/menzhu/01.png"));
			result.add(buildbiankzhu("4", "门柱4号","img/menzhu/01.png"));
			result.add(buildbiankzhu("5", "门柱5号","img/menzhu/01.png"));
			result.add(buildbiankzhu("6", " 门柱6号","img/menzhu/01.png"));
			result.add(buildbiankzhu("7", "门柱7号","img/menzhu/01.png"));
			result.add(buildbiankzhu("8", "门柱8号","img/menzhu/01.png"));
		} else if (biank.equals("02") || biank == "02" || biankzhu.equals("05")
				|| biankzhu == "05") {
			result.add(buildbiankzhu("1", "门柱1号","img/menzhu/01.png"));
			result.add(buildbiankzhu("2", " 门柱2号","img/menzhu/01.png"));
			result.add(buildbiankzhu("3", "门柱3号","img/menzhu/01.png"));
			result.add(buildbiankzhu("4", "门柱4号","img/menzhu/01.png"));
			result.add(buildbiankzhu("5", "门柱5号","img/menzhu/01.png"));
			result.add(buildbiankzhu("6", " 门柱6号","img/menzhu/01.png"));
			result.add(buildbiankzhu("7", "门柱7号","img/menzhu/01.png"));
			result.add(buildbiankzhu("8", "门柱8号","img/menzhu/01.png"));
		}
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");

		//writer.write(result.toString());
		writer.flush();

	}



	

	@RequestMapping(value = "/pages/company/backpic.do")
	public void backpic(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		 String callback = request.getParameter("callback");
		String biank = request.getParameter("biank");

		String biankkuang = request.getParameter("biankkuang");
		System.out.println(",,,,,,,,,," + biank);
		JSONArray result = new JSONArray();
		JSONObject ret=new JSONObject(); 
		
		result.add(buildSe("1", "背景图1","img/diybackpic/01.jpg"));
		result.add(buildSe("2", "背景图2","img/diybackpic/02.jpg"));
		result.add(buildSe("3", "背景图3","img/diybackpic/03.jpg"));
		result.add(buildSe("4", "背景图4","img/diybackpic/04.jpg"));
		result.add(buildSe("5", "背景图5","img/diybackpic/05.jpg"));
		result.add(buildSe("6", "背景图6","img/diybackpic/06.jpg"));
		result.add(buildSe("7", "背景图7","img/diybackpic/07.jpg"));
		result.add(buildSe("8", "背景图8","img/diybackpic/08.jpg"));
		
//		if (biank == null) {
//			result.add(buildbiankkuang("none", "请先选择边框",null));
//		} else if (biank.equals("03") || biank == "03"
//				|| biankkuang.equals("01") || biankkuang == "01") {
//
//			result.add(buildbiankkuang("1", "门框1号","img/menk/01.png"));
//			result.add(buildbiankkuang("2", " 门框2号","img/menk/01.png"));
//			result.add(buildbiankkuang("3", "门框3号","img/menk/01.png"));
//			result.add(buildbiankkuang("4", "门框4号","img/menk/01.png"));
//			result.add(buildbiankkuang("5", "门框5号","img/menk/01.png"));
//			result.add(buildbiankkuang("6", " 门框6号","img/menk/01.png"));
//			result.add(buildbiankkuang("7", "门框7号","img/menk/01.png"));
//			result.add(buildbiankkuang("8", "门框8号","img/menk/01.png"));
//			result.add(buildbiankkuang("9", "15公分宽边","img/menk/01.png"));
//			result.add(buildbiankkuang("10", "15公分竹节边","img/menk/01.png"));
//			ret.put("total", 10);
//		} else if (biank.equals("03") || biank == "03"
//				|| biankkuang.equals("02") || biankkuang == "02") {
//			result.add(buildbiankkuang("1", "门框1号","img/menk/02.png"));
//			result.add(buildbiankkuang("2", " 门框2号","img/menk/02.png"));
//			result.add(buildbiankkuang("3", "门框3号","img/menk/02.png"));
//			result.add(buildbiankkuang("4", "门框4号","img/menk/02.png"));
//			result.add(buildbiankkuang("5", "门框5号","img/menk/02.png"));
//			result.add(buildbiankkuang("6", " 门框6号","img/menk/02.png"));
//			result.add(buildbiankkuang("7", "门框7号","img/menk/02.png"));
//			result.add(buildbiankkuang("8", "门框8号","img/menk/02.png"));
//			result.add(buildbiankkuang("9", "15公分宽边","img/menk/02.png"));
//			result.add(buildbiankkuang("10", "15公分竹节边","img/menk/02.png"));
//			ret.put("total", 10);
//		} else if (biank.equals("03") || biank == "03"
//				|| biankkuang.equals("03") || biankkuang == "03") {
//			result.add(buildbiankkuang("1", "门框1号","img/menk/01.png"));
//			result.add(buildbiankkuang("2", " 门框2号","img/menk/01.png"));
//			result.add(buildbiankkuang("3", "门框3号","img/menk/01.png"));
//			result.add(buildbiankkuang("4", "门框4号","img/menk/01.png"));
//			result.add(buildbiankkuang("5", "门框5号","img/menk/01.png"));
//			result.add(buildbiankkuang("6", " 门框6号","img/menk/01.png"));
//			result.add(buildbiankkuang("7", "门框7号","img/menk/01.png"));
//			result.add(buildbiankkuang("8", "门框8号","img/menk/01.png"));
//			result.add(buildbiankkuang("9", "15公分宽边","img/menk/01.png"));
//			result.add(buildbiankkuang("10", "15公分竹节边","img/menk/01.png"));
//			ret.put("total", 10);
//		} else if (biank.equals("03") || biank == "03"
//				|| biankkuang.equals("04") || biankkuang == "04") {
//			result.add(buildbiankkuang("1", "门框1号","img/menk/04.png"));
//			result.add(buildbiankkuang("2", " 门框2号","img/menk/04.png"));
//			result.add(buildbiankkuang("3", "门框3号","img/menk/04.png"));
//			result.add(buildbiankkuang("4", "门框4号","img/menk/04.png"));
//			result.add(buildbiankkuang("5", "门框5号","img/menk/04.png"));
//			result.add(buildbiankkuang("6", " 门框6号","img/menk/04.png"));
//			result.add(buildbiankkuang("7", "门框7号","img/menk/04.png"));
//			result.add(buildbiankkuang("8", "门框8号","img/menk/04.png"));
//			result.add(buildbiankkuang("9", "15公分宽边","img/menk/04.png"));
//			result.add(buildbiankkuang("10", "15公分竹节边","img/menk/04.png"));
//			ret.put("total", 10);
//		} else if (biank.equals("03") || biank == "03"
//				|| biankkuang.equals("05") || biankkuang == "05") {
//			result.add(buildbiankkuang("1", "门框1号","img/menk/05.png"));
//			result.add(buildbiankkuang("2", " 门框2号","img/menk/05.png"));
//			result.add(buildbiankkuang("3", "门框3号","img/menk/05.png"));
//			result.add(buildbiankkuang("4", "门框4号","img/menk/05.png"));
//			result.add(buildbiankkuang("5", "门框5号","img/menk/05.png"));
//			result.add(buildbiankkuang("6", " 门框6号","img/menk/05.png"));
//			result.add(buildbiankkuang("7", "门框7号","img/menk/05.png"));
//			result.add(buildbiankkuang("8", "门框8号","img/menk/05.png"));
//			result.add(buildbiankkuang("9", "15公分宽边","img/menk/05.png"));
//			result.add(buildbiankkuang("10", "15公分竹节边","img/menk/05.png"));
//			ret.put("total", 10);
//		}
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		ret.put("data", result.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildbiankkuang(String id, String name,String url) {
		JSONObject result = new JSONObject();

		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}

	private JSONObject buildbiankzhu(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}

	private JSONObject buildbiankdt(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}

	/****************************** 背景图   ***********************************************/
	

	private JSONObject buildbackpic(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}
	
	
	
	private JSONObject buildpei(String id, String name,String url) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", url);

		return result;
	}
	
	
	@RequestMapping(value = "/pages/company/cdityOperate.do")
	public String cdityOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			Commodity entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
        Long personid=loginPerson.getId();

		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			entity.setOrg(orgid);
			commodityBo.save(entity);
			System.out.println("cdityOperate.do 执行了一次");
			writer.write("true");
		} else if (UPDATE.equals(operation)) {
			entity.setOrg(orgid);
			commodityBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			commodityBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	@RequestMapping(value = "/pages/company/getDoors.do")
	public String getDoors(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String operation = request.getParameter("operation");

		// query
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		int end = start + limit;

		DataModel datamodel = new DataModel<Commodity>();

		// if (brand == null && number == null && size == null && tcount ==
		// null&& menheight == null&& wallply == null) {
		// List<Commodity> entitys = new ArrayList<Commodity>();
		// for (Commodity entity : commodityBo.findAll()) {
		// entitys.add(entity);
		// }
		// int total = entitys.size();
		// end = end > total ? total : end;
		// if (start <= end) {
		// datamodel.setRows(entitys.subList(start, end));
		// }
		// datamodel.setTotal(total);
		// } else {
		// HashMap parms = new HashMap();
		// if (code != null && code != "") {
		// parms.put("code", "%" + code + "%");
		// }
		// if (name != null && name != "") {
		// String dname=new String(name.getBytes( "iso-8859-1"),"UTF-8" );
		// parms.put("name", "%" + dname + "%");
		// }
		// if (type != null && type != "") {
		// parms.put("type", "%" + type + "%");
		// }
		List entitys = new ArrayList();
		// for (Commodity entity : commodityBo.) {
		// entitys.add(entity);
		// }
		// 查询所有
		entitys = commodityBo.findAll();

		Iterator itor = entitys.iterator();
		while (itor.hasNext()) {
			Object[] queryArray = (Object[]) itor.next();
			Commodity coo = (Commodity) queryArray[0];
			Panel panel = (Panel) queryArray[1];
			System.out.println("dao create:" + coo.getName());
			System.out.println("dao Content:" + panel.getSname());
			System.out.println("dao jm:" + panel.getId());
		}

		int total = entitys.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(entitys.subList(start, end));
			// }
			datamodel.setTotal(total);
		}
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();
		return null;
	}

	/*********** 这个方法 **********/
	@RequestMapping(value = "/pages/company/getDoordetail.do")
	public String getDoordetail(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		codSet(response);
		//获取登录ID
    	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
		// 分页
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		// PrintWriter writer = response.getWriter();
		DataModel datamodel = new DataModel<Commodity>();
		int end = start + limit;

		List<Commodity> list = null;
		 Map map = new HashMap();
	     
	        if (!Utils.nullOrEmpty(orgid)){
				map.put("org", orgid);
	        }
	        
		// if (parms.size()>0) {
		// list = commodityBo.findByParm(parms);
		// }else {

		list = commodityBo.findByParms(map);

		// JSONArray jsonArray2 = JSONArray.fromObject( list );
		// System.out.println("。。。。。。。。。"+jsonArray2);
		// List<Commodity> entitys = new ArrayList<Commodity>();
		// for (Commodity entity : commodityBo.findAll()) {
		// entitys.add(entity);
		// }

		int total = list.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(list.subList(start, end));
		}
		datamodel.setTotal(total);

		String json = JSONObject.fromObject(datamodel).toString();
		JSONObject jobj = JSONObject.fromObject(json);

		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();

		return null;

		// String xml = new XMLSerializer().write(jobj);
		// System.out.println("xml wei "+xml);

		// return jsonArray2;

	}
    /*****
     * 
     * 
     * 
     * 
     * ****/
	@RequestMapping(value = "/pages/company/operateDataDoor.do")
	public String operateDataDoor(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		//获取登录ID
    	Person loginPerson = loginInfo.getLoginPerson();
        String	orgid=loginPerson.getOrganizationId();
		String operation = request.getParameter("operation");
		// query
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		int start = 1;
		int limit = 15;
		int end = start + limit;

		DataModel datamodel = new DataModel<Commodity>();
		String brand = request.getParameter("qbrand");
		String number = request.getParameter("qnumber");
		String qdoorstyle = request.getParameter("qdoorstyle");
		String size = request.getParameter("qsize");
		String sizekd = request.getParameter("qsizekd");
		// String tcount = request.getParameter("qtcount");
		String menheight = request.getParameter("qmenheight");
		
		System.out.println("查询开始" + brand + number);
		// if (brand == null && number == null && size == null&& tcount == null
		// && menheight == null&& wallply == null) {
		// List<Commodity> entitys = new ArrayList<Commodity>();
		// for (Commodity entity : commodityBo.findAll()) {
		// entitys.add(entity);
		// }
		// int total = entitys.size();
		// end = end > total ? total : end;
		// if (start <= end) {
		// datamodel.setRows(entitys.subList(start, end));
		// }
		// datamodel.setTotal(total);
		// } else {
		HashMap parms = new HashMap();

		if (brand != null && brand != "") {
			String brand2 = new String(brand.getBytes("iso-8859-1"), "UTF-8");
			parms.put("brand", "'%" + brand2 + "%'");
			System.out.println("品牌加上''之后");
		}
		if (number != null && number != "") {
			String number2 = new String(number.getBytes("iso-8859-1"), "UTF-8");
			parms.put("number", "'%" + number2 + "%'");
			System.out.println("品牌加2上''之后");
		}
		if (size != null && size != "") {
			parms.put("size", "'%" + size + "%'");
		}
		if (sizekd != null && sizekd != "") {
			parms.put("sizekd", "'%" + sizekd + "%'");
		}
		if (qdoorstyle != null && qdoorstyle != "") {
			String qdoorstyle2 = new String(qdoorstyle.getBytes("iso-8859-1"),
					"UTF-8");
			parms.put("type", "'%" + qdoorstyle2 + "%'");
		}

		if (menheight != null && menheight != "") {
			parms.put("menheight", "'%" + menheight + "%'");
		}
//		if (wallply != null && wallply != "") {
//			parms.put("wallply", "'" + wallply + "'");
//		}
		List<Commodity> entitys = new ArrayList<Commodity>();
		// for (Commodity entity : commodityBo.findByParms(parms)) {
		// entitys.add(entity);
		// }
		//System.out.println("---" + parms.size());
		entitys = commodityBo.findByParmsandorg(parms, orgid);
		//System.out.println("--+结束-");
		int total = entitys.size();
		end = end > total ? total : end;
		if (start <= end) {
			datamodel.setRows(entitys.subList(start, end));
		}
		datamodel.setTotal(total);

		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(datamodel).toString());
		writer.flush();

		return null;
	}
	
	
	 private JSONObject buildjsonNameDataArr(String name, JSONArray result3mmarr) {
			JSONObject result = new JSONObject();
			result.put("name", name);
			result.put("data", result3mmarr);

			return result;
		}
	 
	 private JSONObject buildjsonNameDataObject(String name, JSONObject result3mmarr) {
			JSONObject result = new JSONObject();
			result.put("name", name);
			result.put("data", result3mmarr);

			return result;
		}
	

		private JSONObject buildjsonNameData(String name, JSONObject result3mmarr) {
			JSONObject result = new JSONObject();
			result.put("name", name);
			result.put("data", result3mmarr);

			return result;
		}


	private JSONObject buildjson(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);

		return result;
	}
	
	private JSONObject buildjsonlong(Long id, String name) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);

		return result;
	}
	private JSONObject buildjsondata(JSONArray data) {
		JSONObject result = new JSONObject();
		result.put("data", data);
		return result;
	}
	
	private JSONObject buildjsonlist(Long id, String name,JSONObject listto) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", listto);

		return result;
	}
	
	private JSONObject buildjsonall(String id, String name,List list,List def) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", list);
		result.put("def", def);

		return result;
	}
	
	
	// 右边使用， 普通层级关系
	private JSONObject buildjsonallObejct(String id, String name,JSONObject object,String typename,JSONObject def) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", object);
		result.put("typename", typename);
		result.put("def", def);

		return result;
	}
	
	private JSONObject buildjsonallOneArr(String id, String name,JSONArray object,JSONObject def) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("data", object);
		result.put("def", def);

		return result;
	}
	// 右边使用
	private JSONObject buildjsonlistright(Long id, String name,JSONArray listto,String typename) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", listto);
	
		result.put("typename", typename);

		return result;
	}
	
	//  底部标签 使用,  增加type类型，  拼音。
	private JSONObject buildjsonDiarray(Long id, String name,List l,String typename) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", l);
		
		result.put("typename", typename);
		

		return result;
	}
	
	// 右边使用，，
	private JSONObject buildjsonDiarrayty(Long id, String name,String typename) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("typename", typename);
		

		return result;
	}
	
	// 右边使用，，玻璃使用
	private JSONObject buildjsonDiarraytyright(Long id, String name,JSONArray object,String typename) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("data", object);
		result.put("typename", typename);
		

		return result;
	}
	
	
	//底部标签数据使用
	private JSONObject buildjsonDia(Long id, String name,String img) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		

		return result;
	}
	//底部标签数据使用  色板
	private JSONObject buildjsonDiaSeban(Long id, String name,String img,String color,List object) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("color", color);
		result.put("list", object);
		

		return result;
	}
	
	//底部开向使用
	private JSONObject buildjsonDiakaix(Long id, String name,String img,String typename) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("img", img);
		result.put("typename", typename);

		return result;
	}
	
	private JSONObject buildjsonallOneArr2(String id, String name,JSONObject object,JSONObject def) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("name", name);
		result.put("list", object);
		result.put("def", def);

		return result;
	}
	
	private JSONObject buildjsonNameData( String name,String[] arrayListbolidata) {
		JSONObject result = new JSONObject();
		
		result.put("name", name);
		result.put("Data", arrayListbolidata);

		return result;
	}
	
	
	
	private JSONObject buildjson2(String id, String name) {
		JSONObject result2 = new JSONObject();
		result2.put("id", id);
		result2.put("name", name);
		return result2;
	}
	
	private JSONObject buildjson3(Long id, String name) {
		JSONObject result2 = new JSONObject();
		result2.put("id", id);
		result2.put("name", name);
		return result2;
	}

	public void codSet(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-Type", "text/json;charset=UTF-8");
		response.setDateHeader("Expires", 0);
	}
}
