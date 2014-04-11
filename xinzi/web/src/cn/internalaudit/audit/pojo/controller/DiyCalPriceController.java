package cn.internalaudit.audit.pojo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.internalaudit.audit.bo.IDataQuoteBo;
import cn.internalaudit.audit.bo.IDepartment2Bo;
import cn.internalaudit.audit.bo.impl.Organization2Bo;
import cn.internalaudit.audit.bo.impl.OrganizationBo;
import cn.internalaudit.audit.entitys.DataQuote;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.entitys.Organization2;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.pojo.Cart;
import cn.internalaudit.audit.pojo.CommodityUser;
import cn.internalaudit.audit.pojo.DiyModel;
import cn.internalaudit.audit.pojo.bo.ICartBo;
import cn.internalaudit.audit.pojo.bo.ICommodityUserBo;
import cn.internalaudit.audit.pojo.bo.IDiyModelBo;
import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.CreateGUID;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.ImageUtil;
import cn.internalaudit.audit.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DiyCalPriceController {

	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	@Resource(name = "DiyModelBo")
	public IDiyModelBo diyModelBo;

	@Resource
	Organization2Bo organizationBo;
	@Resource
	private IDepartment2Bo departmentBo;

	@Resource
	public ICommodityUserBo commodityUserBo;

	@Resource
	public ICartBo cartBo;

	// 不同品牌的报价设置
	@Resource
	public IDataQuoteBo dataQuoteBo;

	@Resource
	private LoginInfo loginInfo;

	// 加入购物车后的状态
	public static final String CARTIN = "cartin";
	// 立即下单后的状态
	public static final String CARTORDER = "cartorder";

	/***************************** //@RequestMapping(value = "/pages/company/getDataDiyModels.do")***计算价格 *****************/
	@RequestMapping(value = "/pages/company/getdiyProduct.do")
	public void getdiyProduct(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DiyModel diyModel) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String callback = request.getParameter("callback");

		String o11 = request.getParameter("pp"); // 选择是品牌
		String o12 = request.getParameter("mx"); // 选择是门型 模版--id
		String o13 = request.getParameter("num"); // 选择是樘数
		String o14 = request.getParameter("mh"); // 选择是门 高 --
		String o15 = request.getParameter("mw"); // 选择是 门宽 --
		String o16 = request.getParameter("mhd"); // 选择是厚度

		String o21 = request.getParameter("ks"); // 选择是款式
		String o22 = request.getParameter("zks"); // 选择是 子款式
		String o23 = request.getParameter("bmk"); // 选择是 门框
		String o24 = request.getParameter("bmt"); // 选择是 门头
		String o25 = request.getParameter("bmz"); // 选择是 门柱
		String o26 = request.getParameter("sb"); // 选择是 色板 ----
		String o27 = request.getParameter("kx"); // 选择是 开向 ---
		String o28 = request.getParameter("bhd"); // 选择是 板材厚度--
		String o29 = request.getParameter("bcz"); // 选择是 板材材质---

		// String o30 = request.getParameter("qita"); // 选择是 立柱--
		String o31 = request.getParameter("qlz"); // 选择是 立柱--
		String o32 = request.getParameter("psj"); // 选择是 锁具--
		String o33 = request.getParameter("psx"); // 选择是锁芯--
		String o34 = request.getParameter("pml"); // 选择是 门铃--
		String o35 = request.getParameter("pmy"); // 选择是 猫眼--
		String o36 = request.getParameter("pxd"); // 选择是下档--
		String o37 = request.getParameter("pls"); // 选择是拉手--
		String o38 = request.getParameter("pjl"); // 选择是铰链--

		String o41 = request.getParameter("img"); // 选择是 图片的路径

		String o17 = request.getParameter("bl"); // 玻璃 --------
		String o18 = request.getParameter("qc"); // 气窗 -------
		String o42 = request.getParameter("pbmq"); // 选择是闭门器 --
		String o43 = request.getParameter("ptouj"); // 选择是门头加------

		System.out.println(" !-计算价格-- " + o11 + "--" + o12 + "" + o13 + "---"
				+ o14 + "--" + o15 + "" + o16 + "---" + o17 + o18);
		System.out.println(" !!--- " + o21 + "--" + o22 + "" + o23 + "---"
				+ o24 + "--" + o25 + "----" + o26 + "---" + o27 + "--" + o28
				+ "--" + o29 + "---");
		System.out.println(" !!!--- " + "//" + o31 + "--" + o32 + "" + o33
				+ "---" + o34 + "--" + o35 + "---" + o36 + "---" + o37 + "--"
				+ o38 + "---");
		System.out.println(" !!!计算价格!--- " + o41 + "---" + o42 + "----" + o43);

		// !!!--- null//0--149152---154--155206---161165---
		// !!!!--- null---210----0

		double calEndALlproduct = 0;

		// LZ立柱 前台判断 已完成 未保存到配置表（ 名称 数量 总价 三个字段）
		String lizhuName = null;
		String QuerylizhucheckName = null;
		double callzprice3 = 0;
		if (o31.equals("0") || o31 == "0") {
			System.out.println("----这个o31 ==0");
		} else if (o31 != null && !o31.equals("") && o31 != "0") {
			System.out.println("----这个o31 " + o31);
			Department2 depart = departmentBo.findDataDepartment2ById(Long
					.valueOf(o31));
			lizhuName = depart.getName();
			QuerylizhucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
															// ,再加上这个品牌的名称
															// 去，报价系统里面查询

			System.out.println("lizhu" + lizhuName + "checkName"
					+ QuerylizhucheckName);
			// LZ去报价系统里查询：
			DataQuote dquotelz = null;
			String bpinpailz = "pinpai2";
			dquotelz = dataQuoteBo.findDataQuoteByBtype(QuerylizhucheckName,
					bpinpailz);
			System.out.println("根据用户选择的立柱查询字段查询到这个立柱的对应品牌价格为:"
					+ dquotelz.getBtype() + dquotelz.getBpriceone());
			String callzprice = dquotelz.getBdanjiac(); // 这是用户选择立柱的价格
			// double callzprice=Double.parseDouble(callzprice);
			double callzprice2 = Integer.parseInt(callzprice);
			callzprice3 = callzprice2 * 2; // 立柱选择的是每对 *2 ;
			System.out.println(" 其它   结束  ，价格为" + callzprice3);

		}

		calEndALlproduct = callzprice3; // 立柱计算 001

		// 判断开向 已经完成，还未记录到 用户配置表
		String endkaixiang = null;
		if (o27.equals("0") || o27 == "0") {
			System.out.println("----这个o27 ==0");
		} else if (o27 != null && !o27.equals("")) {
			Department2 depart = departmentBo.findDataDepartment2ById(Long
					.valueOf(o27));
			endkaixiang = depart.getName();
			System.out.println("开向输出为:" + endkaixiang);

		}
		// 判断压花 ，如果是反凸压花， 则每平米 20 元/ 获取ID，查询父节点的名称中是否包好凸。
		String fantumen = null;
		if (o21.equals("0") || o21 == "0") {
			System.out.println("----这个o21 ==0");
		} else if (o21 != null && !o21.equals("")) {
			Department2 depart = departmentBo.findDataDepartment2ById(Long
					.valueOf(o21));
			Long id22 = depart.getOrganization().getId();
			Organization2 organ = organizationBo.find(depart.getOrganization()
					.getId());
			String kuanshiName = organ.getName();
			if (kuanshiName.contains("凸") == true) {
				fantumen = "fantumen";
			} else {
				fantumen = "nofantu";
			}
			System.out.println("这是个一个" + id22 + kuanshiName + fantumen);
		}

		// 判断 玻璃
		String endboli = null;
		if (o17.equals("0") || o17 == "0") {
			System.out.println("----这个o17 ==0");
		} else if (o17 != null && !o17.equals("")) {
			endboli = "endboli";
		}

		// 判断门型
		if (o12.equals("0") || o12 == "0") {
			System.out.println("----这个o12 ==0");
		} else if (o12 != null && !o12.equals("")) {
			if (o12.equals("101") || o12 == "101") {
				System.out.println(" 这是101一个：单门普通 ");
			}
			if (o12.equals("102") || o12 == "102") {
				System.out.println(" 这是102一个：单门气窗 ");
			}
			if (o12.equals("103") || o12 == "103") {
				System.out.println(" 这是103一个：单开子母门普通");
			}
			if (o12.equals("104") || o12 == "104") {
				System.out.println(" 这是104一个：单开子母门+门头 ");
			}
			if (o12.equals("201") || o12 == "201") {
				System.out.println(" 这是201一个： 双开门+门头 ");
			}
			if (o12.equals("202") || o12 == "202") {
				System.out.println(" 这是202一个：双开门+气窗 ");
			}
			if (o12.equals("203") || o12 == "203") {
				System.out.println(" 这是203一个： 双开门+合金铜门 ");
			}
			if (o12.equals("404") || o12 == "404") {
				System.out.println(" 这是404一个： 四开字母门+门头 ");
			}
			if (o12.equals("405") || o12 == "405") {
				System.out.println(" 这是405一个：四开字母门+气窗 ");
			}
			if (o12.equals("403") || o12 == "403") {
				System.out.println(" 这是403一个：四开门+门头+气窗 ");
			}
			if (o12.equals("401") || o12 == "401") {
				System.out.println(" 这是401一个：四开门+门头 ");
			}
			if (o12.equals("402") || o12 == "402") {
				System.out.println(" 这是402一个：四开门+气窗 ");
			}

		}
		// 判断尺寸
		BigDecimal menpingfangAll;
		double c;
		if (o14.equals("0") || o15.equals("0") || o14 == "0" || o15 == "0") {
			System.out.println("----这个o14 ==0");
		} else if (o14 != null && !o14.equals("") && o15 != null
				&& !o15.equals("")) {

			double Mheightdaxiao = Integer.parseInt(o15); // 门宽 比大小用到
			double Mwidthdaxiao = Integer.parseInt(o14); // 门高 比大小用到
			BigDecimal Mwidth2 = new BigDecimal(Mwidthdaxiao); // 门宽
			BigDecimal Mheight2 = new BigDecimal(Mheightdaxiao); // 门高
			BigDecimal menpingfang1 = new BigDecimal(Mwidthdaxiao)
					.divide(new BigDecimal(1000));
			BigDecimal menpingfang2 = new BigDecimal(Mheightdaxiao)
					.divide(new BigDecimal(1000));
			menpingfangAll = menpingfang1.multiply(menpingfang2); // 平方 数
			double a = Mwidthdaxiao / 1000;
			double b = Mheightdaxiao / 1000;
			c = a * b;
			String s = new DecimalFormat("#.###").format(c);
			System.out.println(" NO2:  第二部获得门的 平方数" + menpingfangAll
					+ " int   数值为： " + s);
			if (c > 1) {
				System.out.println(" 大于2平方");
			}
			double k = Double.parseDouble(s);
			System.out.println(" 大K" + k);

			// 判断门 平方数的 和 多出的
			double menpingfang;
			double duochumenpingfang = 0;
			String dcopy = null; // 差值转换

			if (o12.equals("101") || o12.equals("102")) { // 单门
				if (c < 2) {
					menpingfang = 2;
					duochumenpingfang = 0;
				} else {
					menpingfang = c;
					duochumenpingfang = c - 2;
					dcopy = new DecimalFormat("#.###")
							.format(duochumenpingfang);
				}
				System.out
						.println(" P小于2平方one" + "门平方为" + c + "多出的平方为" + dcopy);

			}
			if (o12.equals("103") || o12.equals("103")) { // 单开字母
				if (c < 2.46) {
					System.out.println(" p小于two");
					menpingfang = 2.46;
				}
				if (o18 != null || !o18.equals("")) {
					if (c > 2.46 && c < 3) {
						System.out.println(" p小于three");
						menpingfang = 3;
					} else {
						menpingfang = c;
						duochumenpingfang = c - 3;
						dcopy = new DecimalFormat("#.###")
								.format(duochumenpingfang);
					}
				}
				System.out.println(" P单开字母one" + "门平方为" + c + "多出的平方为" + dcopy);
			}

			if (o12.equals("201") || o12.equals("202") || o12.equals("203")) { // 双开
				if (c > 2.46 && c < 3.075) {
					System.out.println(" P小于");
					menpingfang = 3.075;
				}
				if (o18 != null || !o18.equals("")) {
					if (c > 3.075 && c < 4) {
						System.out.println(" p小于four");
						menpingfang = 4;
						c = 4;
					} else {
						menpingfang = c;
						duochumenpingfang = c - 4;
						dcopy = new DecimalFormat("#.###")
								.format(duochumenpingfang);

					}
				}
				System.out.println(" P双开one" + "门平方为" + c + "多出的平方为" + dcopy);
			}

			if (o12.equals("401") || o12.equals("402") || o12.equals("403")) { // 四开门
				menpingfang = c;
				System.out.println(" p小于five" + menpingfang);

			}
			if (o12.equals("404") || o12.equals("405")) { // 四开子母门
				menpingfang = c;
				System.out.println(" p小于six" + menpingfang);
			}

			/***
			 * 第三部分 判断色板 从前台获得的name ，前缀都加上 k 。 定义的字段 前缀都加上 f。 zhuntongtype 准铜 1
			 * zhuntongbolitype 准铜玻璃 2 gaofangtype 高仿铜门 3 gaofangbolitype 高仿玻璃 4
			 * hejinheitong 合金黑铜5 *
			 **/
			Department2 entity = null;
			String fsebanname = null;
			String fsebanname2 = null;
			double Endpriceseban = 0;

			String Queryconditionsseban = null;
			if (o26.equals("0") || o26 == "0") {
				System.out.println("----这个o26 ==0");
			} else if (!o26.equals("") || o26 != null) {
				System.out.println("----这个o26 !=NULL    ");

				entity = departmentBo
						.findDataDepartment2ById(Long.valueOf(o26));
				entity.getName();
				String ksebanname = entity.getInputname2(); // 获取这个色板的类型，(1234
															// ,)
				if (ksebanname.equals("zhuntongtype")) {
					fsebanname = "准铜";
					fsebanname2 = "zhuntongtype";
					Queryconditionsseban = "sebanprice";

				}
				if (ksebanname.equals("zhuntongbolitype")) {
					fsebanname = "准铜玻璃";
					fsebanname2 = "zhuntongbolitype";
					Queryconditionsseban = "sebanprice2";
				}
				if (ksebanname.equals("gaofangtype")) {
					fsebanname = "高仿铜门";
					fsebanname2 = "gaofangtype";
					Queryconditionsseban = "sebanprice3";
				}
				if (ksebanname.equals("gaofangbolitype")) {
					fsebanname = "高仿玻璃";
					fsebanname2 = "gaofangbolitype";
					Queryconditionsseban = "sebanprice4";
				}

				System.out.println("----这个o26 !=NULL    ");

				if (ksebanname.equals("hejinheitong")) {
					fsebanname = "合金黑铜";
					fsebanname2 = "hejinheitong";
					System.out.println("p336");
					Queryconditionsseban = "sebanprice5";
				}
				if (ksebanname.equals("")) {
					fsebanname2 = "zhuntongtype";
					Queryconditionsseban = "sebanprice";
				}

				// zheli qudiao l }
				System.out.println("获取这个门选择的色板为:" + fsebanname + fsebanname2
						+ "和这个 色板的对应类型为：" + Queryconditionsseban);
				// 获取这个色板的每平方多少钱
				DataQuote dquote = null;

				Map map = new HashMap();
				List<Dealer> list = null;

				String bpinpai = "pinpai2";
				dquote = dataQuoteBo.findDataQuoteByBtype(Queryconditionsseban,
						bpinpai);
				System.out.println("根据色板查询到这个门的色板为:" + dquote.getBtype()
						+ dquote.getBpriceone());
				String calsebanprice = dquote.getBpriceone();

				double calsebanprice2 = Integer.parseInt(calsebanprice); // 每平方的价格

				// 简易复合门 和 豪华复合门 双倍 无法判断计算。 需要根据网上商城那里传过来的门型去判断。

				double danoneprice;
				// 单开
				if (o12.equals("0") || o12 == "0") {
					System.out.println("----这个o12 ==0");
				} else if (o12.equals("101") || o12.equals("102")) {
					System.out.println("获取这个门宽为:" + Mwidthdaxiao + "高度为:"
							+ Mwidthdaxiao);
					System.out.println("门平方为" + c + "多出的平方为" + dcopy);
					System.out.println("获取这个门选择的色板为:" + fsebanname
							+ fsebanname2 + "和这个 气窗的价格：" + Endpriceseban);
					danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
					System.out.println(danoneprice + "....");
					// 气窗的价格 再加上，如果没有气窗 有的话,
					if (!o18.equals("") || o18 != null) {
					} else {
						danoneprice = danoneprice + 20 * c;
					}
					// 如果是反凸门
					if (fantumen.equals("fantumen")) {
						danoneprice = danoneprice + 20 * c;
					}
					// 如果是 玻璃， 判断玻璃的暂时去掉
					// if(endboli.equals("endboli")){
					// danoneprice=danoneprice+50;
					// }
					calEndALlproduct = calEndALlproduct + danoneprice;
				}
				// 子母门
				if (o12.equals("0") || o12 == "0") {
					System.out.println("----这个o12 ==0");
				} else if (o12.equals("103") || o12.equals("104")) {

					danoneprice = calsebanprice2 * c;

					// 高仿 和高仿玻璃 每平方*40元
					if (fsebanname2.equals("gaofangtype")
							|| fsebanname2.equals("gaofangbolitype")) {
						danoneprice = danoneprice + c * 40;
						System.out.println(danoneprice + ">>>>33333..");
					}
					// 气窗
					if (!o18.equals("") || o18 != null) {
					} else {
						danoneprice = danoneprice + 20 * c;
					}
					if (fantumen.equals("fantumen")) {
						danoneprice = danoneprice + 20 * c;
					}// 如果是 玻璃，
//					if (endboli.equals("endboli") || endboli == endboli) {
//						danoneprice = danoneprice + 50;
//					}
					calEndALlproduct = calEndALlproduct + danoneprice;
					System.out.println(danoneprice + ">>>>22..");
				}

				// 双开
				if (o12.equals("0") || o12 == "0") {
					System.out.println("----这个o12 ==0");
				} else if (o12.equals("201") || o12.equals("202")
						|| o12.equals("203")) {
					System.out.println("获取这个门宽为:" + Mwidthdaxiao + "高度为:"
							+ Mwidthdaxiao);
					System.out.println("门平方为" + c + "多出的平方为" + dcopy);
					System.out.println("获取这个门选择的色板为:" + fsebanname
							+ fsebanname2 + "和这个 气窗的价格：" + Endpriceseban);
					danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
					System.out.println(danoneprice + "..3..");
					if (!o18.equals("") || o18 != null) {
					} else {
						danoneprice = danoneprice + 20 * c;

					}
					if (fantumen.equals("fantumen")) {
						danoneprice = danoneprice + 20 * c;
					}
					calEndALlproduct = calEndALlproduct + danoneprice;
				}
				// 四开子母门
				if (o12.equals("0") || o12 == "0") {
					System.out.println("----这个o12 ==0");
				} else if (o12.equals("401") || o12.equals("402")
						|| o12.equals("403")) {
					danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
					System.out.println(danoneprice + "..4..");
					if (!o18.equals("") || o18 != null) {
					} else {
						danoneprice = danoneprice + 20 * c;

					}
					if (fantumen.equals("fantumen")) {
						danoneprice = danoneprice + 20 * c;
					}
					calEndALlproduct = calEndALlproduct + danoneprice;
				}
				// 四开门
				if (o12.equals("0")) {
					System.out.println("----这个o12 ==0");
				} else if (o12.equals("404") || o12.equals("405")) {
					danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
					if (!o18.equals("") || o18 != null) {
					} else {
						danoneprice = danoneprice + 20 * c;
					}
					if (fantumen.equals("fantumen")) {
						danoneprice = danoneprice + 20 * c;
					}
					calEndALlproduct = calEndALlproduct + danoneprice;
					System.out.println(danoneprice + "..5.");
				}

				// LZ 门头 琉璃瓦门头 前台判断 已完成 未保存到配置表（ 名称 数量 总价 三个字段）
				String mentouName = null;
				String QuerymentoucheckName = null;
				double calmentouprice2 = 0;
				double calmentouprice3 = 0;
				if (o24.equals("0") || o24 == "0") {
					System.out.println("----这个o24 ==0");
				} else if (o24 != null && !o24.equals("") && o24 != "0") {
					System.out.println("----这个o24 " + o24);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o24));
					mentouName = depart.getName();
					QuerymentoucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询

					System.out.println(" 门头" + mentouName + "checkName="
							+ QuerymentoucheckName);
					// LZ去报价系统里查询：
					DataQuote dquotemt = null;
					String bpinpaimt = "pinpai2";
					dquotemt = dataQuoteBo.findDataQuoteByBtype(
							QuerymentoucheckName, bpinpaimt);
					// System.out.println("根据用户选择的门头查询字段查询到这个门头的对应品牌价格为:"+dquotemt.getBtype()+dquotemt.getBpriceone());
					String calmentouprice = dquotemt.getBdanjiac(); // 这是用户选择
																	// 门头的价格
					String calmentoupriceone = dquotemt.getBpriceone();

					double calmentoupriceone2 = Double
							.parseDouble(calmentoupriceone);
					double calmentouprice5 = Integer.parseInt(calmentouprice);
					// 超过2000mm 多出的 = ( 乘以10)

					// 获得高度 Mwidthdaxiao
					if (Mwidthdaxiao > 2000) {
						calmentouprice2 = (Mwidthdaxiao - 2000)
								* calmentoupriceone2;
					}
					calmentouprice3 = calmentouprice5 + calmentouprice2;
					// ;

					calEndALlproduct = calEndALlproduct + calmentouprice3;
					System.out.println("   门头  结束  ，价格为" + calmentouprice3
							+ "计算总价暂时为" + calEndALlproduct);

				}

			} // gjp 0316

			// 判断其他配件的选择 头加
			String pjtoujiaName = null;
			String QuerytoujiacheckName = null;
			double caltoujiaprice2 = 0;
			if (o43.equals("0") || o43 == "0") {
				System.out.println("----这个o43 ==0");
			} else if (o43 != null && !o43.equals("") && o43 != "0") {
				System.out.println("----这个o43 " + o43);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o43));
				pjtoujiaName = depart.getName();
				QuerytoujiacheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("配件 头加的名称" + pjtoujiaName + "checkName"
						+ QuerytoujiacheckName);
				// LZ去报价系统里查询：
				DataQuote dquotetoujia = null;
				String bpinpaitoujia = "pinpai2";
				dquotetoujia = dataQuoteBo.findDataQuoteByBtype(
						QuerytoujiacheckName, bpinpaitoujia);
				// System.out.println("根据用户选择的头加查询字段查询到这个头加的对应品牌价格为:"+dquotetoujia.getBtype()+dquotetoujia.getBpriceone());
				String caltoujiaprice = dquotetoujia.getBdanjiac(); // 这是用户选择立柱的价格
				// double callzprice=Double.parseDouble(callzprice);
				caltoujiaprice2 = Integer.parseInt(caltoujiaprice);
				// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
				System.out.println(" 配件 头加  结束  ，价格为" + caltoujiaprice2);

				calEndALlproduct = calEndALlproduct + caltoujiaprice2;

			}

			// 判断其他配件的选择 锁具
			String peijianName = null;
			String QuerysuojuucheckName = null;
			double calsuojuprice2 = 0;
			if (o32.equals("0") || o32 == "0") {
				System.out.println("----这个o32 ==0");
			} else if (o32 != null && !o32.equals("") && o32 != "0") {
				System.out.println("----这个o32 " + o32);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o32));
				peijianName = depart.getName();
				QuerysuojuucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("配件 锁具的名称" + peijianName + "checkName"
						+ QuerysuojuucheckName);
				// LZ去报价系统里查询：
				DataQuote dquotesuoju = null;
				String bpinpaisuoju = "pinpai2";
				dquotesuoju = dataQuoteBo.findDataQuoteByBtype(
						QuerysuojuucheckName, bpinpaisuoju);
				// System.out.println("根据用户选择的锁具查询字段查询到这个锁具的对应品牌价格为:"+dquotesuoju.getBtype()+dquotesuoju.getBpriceone());
				String calsuojuprice = dquotesuoju.getBdanjiac(); // 这是用户选择立柱的价格
				// double callzprice=Double.parseDouble(callzprice);
				calsuojuprice2 = Integer.parseInt(calsuojuprice);
				// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
				calEndALlproduct = calEndALlproduct + calsuojuprice2;
				System.out.println(" 配件 锁具  结束  ，价格为" + calsuojuprice2);
			}

			// 判断其他配件的选择 锁芯
			String pjsuoxinName = null;
			String QuerysuoxincheckName = null;
			double calsuoxinprice2 = 0;
			if (o33.equals("0") || o33 == "0") {
				System.out.println("----这个o33 ==0");
			} else if (o33 != null && !o33.equals("") && o33 != "0") {
				System.out.println("----这个o33 " + o33);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o33));
				pjsuoxinName = depart.getName();
				QuerysuoxincheckName = depart.getInputname2(); // 确定这个用户选择是是什么锁芯
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("配件锁芯的名称" + peijianName + "checkName"
						+ QuerysuoxincheckName);
				// LZ去报价系统里查询：
				DataQuote dquotesuoxin = null;
				String bpinpaisuoju = "pinpai2";
				dquotesuoxin = dataQuoteBo.findDataQuoteByBtype(
						QuerysuoxincheckName, bpinpaisuoju);
				// System.out.println("根据用户选择的锁具查询字段查询到这个锁具的对应品牌价格为:"+dquotesuoxin.getBtype()+dquotesuoxin.getBpriceone());
				String calsuoxinprice = dquotesuoxin.getBdanjiac(); // 这是用户选择锁芯的价格
				// double callzprice=Double.parseDouble(callzprice);
				calsuoxinprice2 = Integer.parseInt(calsuoxinprice);
				// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
				calEndALlproduct = calEndALlproduct + calsuoxinprice2;

				System.out.println(" 配件 锁芯 结束  ，价格为" + calsuoxinprice2);
			}

			// 判断其他配件的选择 门铃
			String pjmenlingName = null;
			String QuerymenlingcheckName = null;
			double calmenlingprice2 = 0;
			if (o34.equals("0") || o34 == "0") {
				System.out.println("----这个o34 ==0");
			} else if (o34 != null && !o34.equals("") && o34 != "0") {
				System.out.println("----这个o34 " + o34);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o34));
				pjmenlingName = depart.getName();
				QuerymenlingcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （门铃）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("配件 （门铃）的名称" + pjmenlingName + "checkName="
						+ QuerymenlingcheckName);
				// LZ去报价系统里查询：
				DataQuote dquotemenling = null;
				String bpinpaimenling = "pinpai2";
				dquotemenling = dataQuoteBo.findDataQuoteByBtype(
						QuerymenlingcheckName, bpinpaimenling);
				// System.out.println("根据用户选择的 (门铃)查询字段查询到这个 (门铃)的对应品牌价格为:"+dquotemenling.getBtype()+dquotemenling.getBpriceone());
				String calmenlingprice = dquotemenling.getBdanjiac(); // 这是用户选择锁芯的价格
				// double callzprice=Double.parseDouble(callzprice);
				calmenlingprice2 = Integer.parseInt(calmenlingprice);
				// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;

				calEndALlproduct = calEndALlproduct + calmenlingprice2;
				// System.out.println(" 配件 (门铃) 结束  ，价格为"+calmenlingprice2);
			}

			// 判断其他配件的选择 猫眼
			String pjmaoyanName = null;
			String QuerymaoyancheckName = null;
			double calmaoyanprice2 = 0;
			if (o35.equals("0") || o35 == "0") {
				System.out.println("----这个o35 ==0");
			} else if (o34 != null && !o35.equals("") && o35 != "0") {
				System.out.println("----这个o35 " + o35);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o35));
				pjmaoyanName = depart.getName();
				QuerymaoyancheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （猫眼）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				// System.out.println("配件 （猫眼）的名称"+pjmaoyanName+"checkName="+QuerymaoyancheckName);
				// LZ去报价系统里查询：
				DataQuote dquotemaoyan = null;
				String bpinpaimaoyan = "pinpai2";
				dquotemaoyan = dataQuoteBo.findDataQuoteByBtype(
						QuerymenlingcheckName, bpinpaimaoyan);
				System.out
						.println("根据用户选择的 (猫眼)查询字段查询到这个 (猫眼)的对应品牌价格为:"
								+ dquotemaoyan.getBtype()
								+ dquotemaoyan.getBpriceone());
				String calmaoyanprice = dquotemaoyan.getBdanjiac(); // 这是用户选择锁芯的价格
				// double callzprice=Double.parseDouble(callzprice);
				calmaoyanprice2 = Integer.parseInt(calmaoyanprice);
				// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;

				calEndALlproduct = calEndALlproduct + calmaoyanprice2;
				System.out.println(" 配件 (猫眼) 结束  ，价格为" + calmaoyanprice2);
			}

			// 判断其他配件的选择 下档
			String pjxiadangName = null;
			String QueryxiadangcheckName = null;
			double calxiadangprice2 = 0;
			if (o36.equals("0") || o36 == "0") {
				System.out.println("----这个o36 ==0");
			} else if (o36 != null && !o36.equals("") && o36 != "0") {
				System.out.println("----这个o36 " + o36);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o36));
				pjxiadangName = depart.getName();
				QueryxiadangcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （下档）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("配件 （下档）的名称" + pjxiadangName + "checkName="
						+ QueryxiadangcheckName);
				// LZ去报价系统里查询：
				DataQuote dquotexiadang = null;
				String bpinpaimaoyan = "pinpai2";
				dquotexiadang = dataQuoteBo.findDataQuoteByBtype(
						QuerymenlingcheckName, bpinpaimaoyan);
				System.out.println("根据用户选择的 (下档)查询字段查询到这个 (下档)的对应品牌价格为:"
						+ dquotexiadang.getBtype()
						+ dquotexiadang.getBpriceone());
				String calxiadangprice = dquotexiadang.getBdanjiac(); // 这是用户选择锁芯的价格
				// double callzprice=Double.parseDouble(callzprice);
				calxiadangprice2 = Integer.parseInt(calxiadangprice);
				// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
				calEndALlproduct = calEndALlproduct + calxiadangprice2;
				System.out.println(" 配件 (下档) 结束  ，价格为" + calxiadangprice2);
			}

			// 判断其他配件的选择 拉手 * 2
			String pjlashouName = null;
			String QuerylashoucheckName = null;
			double callashouprice3 = 0;
			if (o37.equals("0") || o37 == "0") {
				System.out.println("----这个o37 ==0");
			} else if (o37 != null && !o37.equals("") && o37 != "0") {
				System.out.println("----这个o37 " + o37);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o37));
				pjlashouName = depart.getName();
				QuerylashoucheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （拉手）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("配件 （拉手）的名称" + pjlashouName + "checkName="
						+ QuerylashoucheckName);
				// LZ去报价系统里查询：
				DataQuote dquotelashou = null;
				String bpinpaimaoyan = "pinpai2";
				dquotelashou = dataQuoteBo.findDataQuoteByBtype(
						QuerymenlingcheckName, bpinpaimaoyan);
				// System.out.println("根据用户选择的 (拉手)查询字段查询到这个 (拉手)的对应品牌价格为:"+dquotelashou.getBtype()+dquotelashou.getBpriceone());
				String callashouprice = dquotelashou.getBdanjiac(); // 这是用户选择锁芯的价格
				// double callzprice=Double.parseDouble(callzprice);
				double callashouprice2 = Integer.parseInt(callashouprice);
				callashouprice3 = callashouprice2 * 2; // 立柱选择的是每对 *2 ;
				calEndALlproduct = calEndALlproduct + callashouprice3;
				System.out.println(" 配件 (拉手) 结束  ，价格为" + callashouprice3);
			}

			// 判断其他配件的选择 铰链 * 2
			String pjjiaolianName = null;
			String QueryjiaoliancheckName = null;
			double caljiaolianprice3 = 0;
			if (o38.equals("0") || o38 == "0") {
				System.out.println("----这个o38 ==0");
			} else if (o38 != null && !o38.equals("") && o38 != "0") {
				System.out.println("----这个o38 " + o38);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o38));
				pjjiaolianName = depart.getName();
				QueryjiaoliancheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （铰链）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
				System.out.println("配件 （铰链）的名称" + pjjiaolianName + "checkName="
						+ QueryjiaoliancheckName);
				// LZ去报价系统里查询：
				DataQuote dquotejiaolian = null;
				String bpinpaimaoyan = "pinpai2";
				dquotejiaolian = dataQuoteBo.findDataQuoteByBtype(
						QuerymenlingcheckName, bpinpaimaoyan);
				// System.out.println("根据用户选择的 (铰链)查询字段查询到这个 (铰链)的对应品牌价格为:"+dquotejiaolian.getBtype()+dquotejiaolian.getBpriceone());
				String caljiaolianprice = dquotejiaolian.getBdanjiac(); // 这是用户选择锁芯的价格
				// double callzprice=Double.parseDouble(callzprice);
				double caljiaolianprice2 = Integer.parseInt(caljiaolianprice);
				// caljiaolianprice3=caljiaolianprice2*2; //立柱选择的是每对 *2 ;

				calEndALlproduct = calEndALlproduct + caljiaolianprice2;
				System.out.println(" 配件 (铰链) 结束  ，价格为" + caljiaolianprice2);
			}

			// 判断其他配件的选择 闭门器
			String pjbimenqiName = null;
			String QuerybimenqicheckName = null;
			double calbimenqiprice3 = 0;
			if (o42.equals("0") || o42 == "0") {
				System.out.println("----这个o42 ==0");
			} else if (o42 != null && !o42.equals("") && o42 != "0") {
				System.out.println("----这个o42 " + o42);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o42));
				pjbimenqiName = depart.getName();
				QuerybimenqicheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （闭门器）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("配件 （闭门器）的名称" + pjbimenqiName + "checkName="
						+ QuerybimenqicheckName);
				// LZ去报价系统里查询：
				DataQuote dquotebimenqi = null;
				String bpinpaimaoyan = "pinpai2";
				dquotebimenqi = dataQuoteBo.findDataQuoteByBtype(
						QuerymenlingcheckName, bpinpaimaoyan);
				// System.out.println("根据用户选择的 (闭门器)查询字段查询到这个 (闭门器)的对应品牌价格为:"+dquotebimenqi.getBtype()+dquotebimenqi.getBpriceone());
				String calbimenqiprice = dquotebimenqi.getBdanjiac(); // 这是用户选择锁芯的价格
				// double callzprice=Double.parseDouble(callzprice);
				double calbimenqiprice2 = Integer.parseInt(calbimenqiprice);
				// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
				calEndALlproduct = calEndALlproduct + calbimenqiprice2;
				System.out.println(" 配件 (闭门器) 结束  ，价格为" + calbimenqiprice2);
			}

			// 判断 材质 cz 厚度 * 每平方的价格

			String czhouduName = null;
			String QueryczcheckName = null;
			double calbimenqiprice2 = 0;
			if (o27.equals("0") || o27 == "0") {
				System.out.println("----这个o27 ==0");
			} else if (o27 != null && !o27.equals("") && o27 != "0") {
				System.out.println("----这个o27 " + o27);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o27));
				czhouduName = depart.getName();
				QueryczcheckName = depart.getInputname2(); // 确定这个用户选择是是什么 （闭门器）
															// ,再加上这个品牌的名称
															// 去，报价系统里面查询
				System.out.println("材质（厚度）的名称" + czhouduName + "checkName="
						+ QueryczcheckName);
				// LZ去报价系统里查询：
				DataQuote dquotehoudu = null;
				String bpinpaihoudu = "pinpai2";
				// dquotehoudu=dataQuoteBo.findDataQuoteByBtype(QueryczcheckName,bpinpaihoudu);
				// System.out.println("根据用户选择的 (厚度)查询字段查询到这个 (厚度)的对应品牌价格为:"+dquotehoudu.getBtype()+dquotehoudu.getBpriceone());
				// String calbimenqiprice=dquotehoudu.getBdanjiac(); // 这是用户选择
				// 这个门厚度，每平方的价格
				// double callzprice=Double.parseDouble(callzprice);
				// calbimenqiprice2 = Integer.parseInt(calbimenqiprice);
				// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
				calEndALlproduct = calEndALlproduct + calbimenqiprice2;
				System.out.println("  (厚度) 结束  ，价格为" + calbimenqiprice2);
			}

			// 板材 ，材质
			String czcaizhiName = null;
			String QuerycaizhicheckName = null;
			double calcaizhiqiprice3 = 0;
			if (o28.equals("0") || o28 == "0") {
				System.out.println("----这个o28 ==0");
			} else if (o28 != null && !o28.equals("") && o28 != "0") {
				System.out.println("----这个o28 " + o28);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o28));
				czcaizhiName = depart.getName();
				QuerycaizhicheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （材质）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
				System.out.println("材质（材质）的名称" + czcaizhiName + "checkName="
						+ QuerycaizhicheckName);
				// LZ去报价系统里查询：
				// DataQuote dquotecaizhi=null;
				// String bpinpaicaizhi="pinpai2";
				// dquotecaizhi=dataQuoteBo.findDataQuoteByBtype(QuerycaizhicheckName,bpinpaicaizhi);
				// System.out.println("根据用户选择的 (材质)查询字段查询到这个 (材质)的对应品牌价格为:"+dquotecaizhi.getBtype()+dquotecaizhi.getBpriceone());
				// String calcaizhiqiprice=dquotecaizhi.getBdanjiac(); // 这是用户选择
				// 这个门材质，每平方的价格
				// double callzprice=Double.parseDouble(callzprice);
				// calcaizhiqiprice3 = Integer.parseInt(calcaizhiqiprice);
				// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
				// System.out.println("  (材质) 结束  ，价格为"+calcaizhiqiprice3);
			}

			System.out.println(calEndALlproduct + "最终获得的价格为");

		}// 最外层 判断色板youzhi

		// }

		// 数据 计算完毕之后，进行返回数据 ，
		StringBuffer bu = new StringBuffer();
		bu.append(" !--- " + o11 + "--" + o12 + "" + o13 + "---" + o14 + "--"
				+ o15 + "" + o16 + "---");
		bu.append(" !!--- " + o21 + "--" + o22 + "" + o23 + "---" + o24 + "--"
				+ o25 + "" + o26 + "---" + o27 + "--" + o28 + "" + o29);
		bu.append(" !!!--- " + o31 + "--" + o32 + "" + o33 + "---" + o34 + "--"
				+ o35 + "" + o36 + "---" + o37 + "" + o38 + "---");
		bu.append(" !!!!--- " + o41);
		JSONArray resultleft = new JSONArray();
		JSONObject ret = new JSONObject();

		long id = 1;
		double sp2 = calEndALlproduct;
		int sp = (int) calEndALlproduct;
		int Mtangshu = 0;
		int tp = sp;

		if ("".equals(o13) || o13 == null) {
			Mtangshu = 1;
		} else {

			Mtangshu = Integer.parseInt(o13);
			tp = sp * Mtangshu;
		}
		resultleft.add(buildjsonnumber(id, sp, Mtangshu, tp));

		System.out.println("^" + resultleft);
		response.reset();
		response.setCharacterEncoding("utf-8");

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ret.put("data1", bu.toString());
		ret.put("data2", resultleft.toString());
		// writer.write( ret.toString());
		writer.write(callback + "(" + ret.toString() + ")");
		writer.flush();

	}

	private JSONObject buildjsonnumber(Long id, double sp, int number, double tp) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("sp", sp); // 单价
		result.put("number", number); // 樘数
		result.put("tp", tp); // 总价
		return result;
	}

	private JSONObject buildjsonnumber2(Long id, String sp, String number,
			String tp) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("sp", sp); // 单价
		result.put("number", number); // 樘数
		result.put("tp", tp); // 总价
		return result;
	}

	/*****************************
	 * //@RequestMapping(value = "/pages/company/getDataDiyModels.do") 点击下单* 商品
	 * ID， 商品名， 商品图片， 单价 ，数量 ，小计
	 * 
	 * 经销商 点击 立即下单
	 * 
	 * *
	 ******************/
	@RequestMapping(value = "/pages/company/getdiyProductOrder.do")
	public String getdiyProductOrder(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DiyModel diyModel, CommodityUser entity,HttpSession session) throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String callback = request.getParameter("callback");
		String operation = request.getParameter("operation");

		String o11 = request.getParameter("pp"); // 选择是品牌
		String o12 = request.getParameter("mx"); // 选择是门型 模版--id
		String o13 = request.getParameter("num"); // 选择是樘数
		String o14 = request.getParameter("mh"); // 选择是门 高 --
		String o15 = request.getParameter("mw"); // 选择是 门宽 --
		String o16 = request.getParameter("mhd"); // 选择是厚度

		String o21 = request.getParameter("ks"); // 选择是款式
		String o22 = request.getParameter("zks"); // 选择是 子款式
		String o23 = request.getParameter("bmk"); // 选择是 门框
		String o24 = request.getParameter("bmt"); // 选择是 门头
		String o25 = request.getParameter("bmz"); // 选择是 门柱
		String o26 = request.getParameter("sb"); // 选择是 色板 ----
		String o27 = request.getParameter("kx"); // 选择是 开向 ---
		String o28 = request.getParameter("bhd"); // 选择是 板材厚度--
		String o29 = request.getParameter("bcz"); // 选择是 板材材质---

		// String o30 = request.getParameter("qita"); // 选择是 立柱--
		String o31 = request.getParameter("qlz"); // 选择是 立柱--
		String o32 = request.getParameter("psj"); // 选择是 锁具--
		String o33 = request.getParameter("psx"); // 选择是锁芯--
		String o34 = request.getParameter("pml"); // 选择是 门铃--
		String o35 = request.getParameter("pmy"); // 选择是 猫眼--
		String o36 = request.getParameter("pxd"); // 选择是下档--
		String o37 = request.getParameter("pls"); // 选择是拉手--
		String o38 = request.getParameter("pjl"); // 选择是铰链--

		String o41 = request.getParameter("img"); // 选择是 图片的路径

		String o17 = request.getParameter("bl"); // 玻璃 --------
		String o18 = request.getParameter("qc"); // 气窗 -------
		String o42 = request.getParameter("pbmq"); // 选择是闭门器 --
		String o43 = request.getParameter("ptouj"); // 选择是门头加------

		System.out.println("!点击  立即下单---  !--- " + o11 + "--" + o12 + "----"
				+ o13 + "---" + o14 + "--" + o15 + "----" + o16 + "---");
		System.out.println(" !!--- " + o21 + "--" + o22 + "---" + o23 + "---"
				+ o24 + "--" + o25 + "----" + o26 + "---" + o27 + "--" + o28
				+ "---" + o29 + "---");
		System.out.println(" !!!--- " + o31 + "--" + o32 + "---" + o33 + "---"
				+ o34 + "--" + o35 + "---" + o36 + "---" + o37 + "---" + o38
				+ "---");
		System.out.println(" !!!!点击  立即下单---!!!!--- " + o41);

		// entity.setNumber(number); //商品编号
		//entity.setBrand(o11); // 品牌
		 String  brandname=null;
			if("".equals(o11) || o11 == null){
				Object pinname=session.getAttribute("brandName");
				 brandname=(String) pinname;
				 o11=brandname;
			}
			entity.setBrand(o11); // 品牌
			
		entity.setSize(o14); // 高
		entity.setSizekd(o15); // 宽
	//	entity.setTcount(o13);
		if ("".equals(o13) || o13 == null || o13.equals("0") || o13 == "0") {
			o13 = "1";
			System.out.println("==null  ");
			entity.setTcount(o13);
		}
		entity.setHoudu(o16); // 厚度
		double calEndALlproduct = 0;
		if (o31 != null && o26 != null && o21 != null && o14 != null
				&& o15 != null) {
			System.out.println("获得了这个门为" + o12);
			// 如果不为空 则去计算

			// LZ立柱 前台判断 已完成 未保存到配置表（ 名称 数量 总价 三个字段）
			String lizhuName = null;
			String QuerylizhucheckName = null;
			double callzprice3 = 0;
			if (o31.equals("0") || o31 == "0") {
				System.out.println("----这个o31 ==0");
			} else if (o31 != null && !o31.equals("") && o31 != "0") {
				System.out.println("----这个o31 " + o31);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o31));
				lizhuName = depart.getName();
				QuerylizhucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询

				System.out.println("lizhu" + lizhuName + "checkName"
						+ QuerylizhucheckName);
				// LZ去报价系统里查询：
				DataQuote dquotelz = null;
				String bpinpailz = "pinpai2";
				dquotelz = dataQuoteBo.findDataQuoteByBtype(
						QuerylizhucheckName, bpinpailz);
				System.out.println("根据用户选择的立柱查询字段查询到这个立柱的对应品牌价格为:"
						+ dquotelz.getBtype() + dquotelz.getBpriceone());
				String callzprice = dquotelz.getBdanjiac(); // 这是用户选择立柱的价格
				// double callzprice=Double.parseDouble(callzprice);
				double callzprice2 = Integer.parseInt(callzprice);
				callzprice3 = callzprice2 * 2; // 立柱选择的是每对 *2 ;
				System.out.println(" 其它   结束  ，价格为" + callzprice3);

			}

			entity.setElsessname(lizhuName);

			calEndALlproduct = callzprice3; // 立柱计算 001

			// 判断开向 已经完成，还未记录到 用户配置表
			String endkaixiang = null;
			if (o27.equals("0") || o27 == "0") {
				System.out.println("----这个o27 ==0");
			} else if (o27 != null && !o27.equals("")) {
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o27));
				endkaixiang = depart.getName();
				System.out.println("开向输出为:" + endkaixiang);

			}
			entity.setOpento(endkaixiang);
			// 判断压花 ，如果是反凸压花， 则每平米 20 元/ 获取ID，查询父节点的名称中是否包好凸。
			String fantumen = null;
			String kuanshiName = null;
			if (o21.equals("0") || o21 == "0") {
				System.out.println("----这个o21 ==0");
			} else if (o21 != null && !o21.equals("")) {
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o21));
				Long id22 = depart.getOrganization().getId();
				Organization2 organ = organizationBo.find(depart
						.getOrganization().getId());
				kuanshiName = organ.getName();
				if (kuanshiName.contains("凸") == true) {
					fantumen = "fantumen";
				} else {
					fantumen = "nofantu";
				}
				System.out.println("这是个一个" + id22 + kuanshiName + fantumen);
			}

			entity.setDoorstyletype(kuanshiName);

			// 判断 玻璃
			String endboli = null;
			if (o17.equals("0") || o17 == "0") {
				System.out.println("----这个o17 ==0");
			} else if (o17 != null && !o17.equals("")) {
				endboli = "endboli";
			}
			entity.setDoorstylefield(endboli);

			// 判断门型
			String typenamecart = null;
			if (o12.equals("0") || o12 == "0") {
				System.out.println("----这个o12 ==0");
			} else if (o12 != null && !o12.equals("")) {
				if (o12.equals("101") || o12 == "101") {
					System.out.println(" 这是101一个：单门普通 ");
					typenamecart = "单门普通";
				}
				if (o12.equals("102") || o12 == "102") {
					System.out.println(" 这是102一个：单门气窗 ");
					typenamecart = "单门气窗";
				}
				if (o12.equals("103") || o12 == "103") {
					System.out.println(" 这是103一个：单开子母门普通");
					typenamecart = "单开子母门普通";
				}
				if (o12.equals("104") || o12 == "104") {
					System.out.println(" 这是104一个：单开子母门+门头 ");
					typenamecart = "单开子母门+门头";
				}
				if (o12.equals("201") || o12 == "201") {
					System.out.println(" 这是201一个： 双开门+门头 ");
					typenamecart = "双开门+门头";
				}
				if (o12.equals("202") || o12 == "202") {
					System.out.println(" 这是202一个：双开门+气窗 ");
					typenamecart = "双开门+气窗";
				}
				if (o12.equals("203") || o12 == "203") {
					System.out.println(" 这是203一个： 双开门+合金铜门 ");
					typenamecart = "双开门+合金铜门";
				}
				if (o12.equals("404") || o12 == "404") {
					System.out.println(" 这是404一个： 四开字母门+门头 ");
					typenamecart = "四开字母门+门头";
				}
				if (o12.equals("405") || o12 == "405") {
					System.out.println(" 这是405一个：四开字母门+气窗 ");
					typenamecart = "四开字母门+气窗";
				}
				if (o12.equals("403") || o12 == "403") {
					System.out.println(" 这是403一个：四开门+门头+气窗 ");
					typenamecart = "四开门+门头+气窗";
				}
				if (o12.equals("401") || o12 == "401") {
					System.out.println(" 这是401一个：四开门+门头 ");
					typenamecart = "四开门+门头";
				}
				if (o12.equals("402") || o12 == "402") {
					System.out.println(" 这是402一个：四开门+气窗 ");
					typenamecart = "四开门+气窗";
				}

			}

			entity.setType(typenamecart);

			// 判断尺寸
			BigDecimal menpingfangAll;
			double c;
			if (o14.equals("0") || o15.equals("0") || o14 == "0" || o15 == "0") {
				System.out.println("----这个o14 ==0");
			} else if (o14 != null && !o14.equals("") && o15 != null
					&& !o15.equals("")) {

				double Mheightdaxiao = Integer.parseInt(o15); // 门宽 比大小用到
				double Mwidthdaxiao = Integer.parseInt(o14); // 门高 比大小用到
				BigDecimal Mwidth2 = new BigDecimal(Mwidthdaxiao); // 门宽
				BigDecimal Mheight2 = new BigDecimal(Mheightdaxiao); // 门高
				BigDecimal menpingfang1 = new BigDecimal(Mwidthdaxiao)
						.divide(new BigDecimal(1000));
				BigDecimal menpingfang2 = new BigDecimal(Mheightdaxiao)
						.divide(new BigDecimal(1000));
				menpingfangAll = menpingfang1.multiply(menpingfang2); // 平方 数
				double a = Mwidthdaxiao / 1000;
				double b = Mheightdaxiao / 1000;
				c = a * b;
				String s = new DecimalFormat("#.###").format(c);
				System.out.println(" NO2:  第二部获得门的 平方数" + menpingfangAll
						+ " int   数值为： " + s);
				if (c > 1) {
					System.out.println(" 大于2平方");
				}
				double k = Double.parseDouble(s);
				System.out.println(" 大K" + k);

				// 判断门 平方数的 和 多出的
				double menpingfang;
				double duochumenpingfang = 0;
				String dcopy = null; // 差值转换

				if (o12.equals("101") || o12.equals("102")) { // 单门
					if (c < 2) {
						menpingfang = 2;
						duochumenpingfang = 0;
					} else {
						menpingfang = c;
						duochumenpingfang = c - 2;
						dcopy = new DecimalFormat("#.###")
								.format(duochumenpingfang);
					}
					System.out.println(" P小于2平方one" + "门平方为" + c + "多出的平方为"
							+ dcopy);

				}
				if (o12.equals("103") || o12.equals("103")) { // 单开字母
					if (c < 2.46) {
						System.out.println(" p小于two");
						menpingfang = 2.46;
					}
					if (o18 != null || !o18.equals("")) {
						if (c > 2.46 && c < 3) {
							System.out.println(" p小于three");
							menpingfang = 3;
						} else {
							menpingfang = c;
							duochumenpingfang = c - 3;
							dcopy = new DecimalFormat("#.###")
									.format(duochumenpingfang);
						}
					}
					System.out.println(" P单开字母one" + "门平方为" + c + "多出的平方为"
							+ dcopy);
				}

				if (o12.equals("201") || o12.equals("202") || o12.equals("203")) { // 双开
					if (c > 2.46 && c < 3.075) {
						System.out.println(" P小于");
						menpingfang = 3.075;
					}
					if (o18 != null || !o18.equals("")) {
						if (c > 3.075 && c < 4) {
							System.out.println(" p小于four");
							menpingfang = 4;
							c = 4;
						} else {
							menpingfang = c;
							duochumenpingfang = c - 4;
							dcopy = new DecimalFormat("#.###")
									.format(duochumenpingfang);

						}
					}
					System.out.println(" P双开one" + "门平方为" + c + "多出的平方为"
							+ dcopy);
				}

				if (o12.equals("401") || o12.equals("402") || o12.equals("403")) { // 四开门
					menpingfang = c;
					System.out.println(" p小于five" + menpingfang);

				}
				if (o12.equals("404") || o12.equals("405")) { // 四开子母门
					menpingfang = c;
					System.out.println(" p小于six" + menpingfang);
				}

				/***
				 * 第三部分 判断色板 从前台获得的name ，前缀都加上 k 。 定义的字段 前缀都加上 f。 zhuntongtype
				 * 准铜 1 zhuntongbolitype 准铜玻璃 2 gaofangtype 高仿铜门 3
				 * gaofangbolitype 高仿玻璃 4 hejinheitong 合金黑铜5 *
				 **/
				Department2 entityse = null;
				String fsebanname = null;
				String fsebanname2 = null;
				double Endpriceseban = 0;

				String Queryconditionsseban = null;
				if (o26.equals("0") || o26 == "0") {
					System.out.println("----这个o26 ==0");
				} else if (!o26.equals("") || o26 != null) {
					System.out.println("----这个o26 !=NULL    ");

					entityse = departmentBo.findDataDepartment2ById(Long
							.valueOf(o26));
					entityse.getName();
					String ksebanname = entityse.getInputname2(); // 获取这个色板的类型，(1234
																	// ,)
					if (ksebanname.equals("zhuntongtype")) {
						fsebanname = "准铜";
						fsebanname2 = "zhuntongtype";
						Queryconditionsseban = "sebanprice";

					}
					if (ksebanname.equals("zhuntongbolitype")) {
						fsebanname = "准铜玻璃";
						fsebanname2 = "zhuntongbolitype";
						Queryconditionsseban = "sebanprice2";
					}
					if (ksebanname.equals("gaofangtype")) {
						fsebanname = "高仿铜门";
						fsebanname2 = "gaofangtype";
						Queryconditionsseban = "sebanprice3";
					}
					if (ksebanname.equals("gaofangbolitype")) {
						fsebanname = "高仿玻璃";
						fsebanname2 = "gaofangbolitype";
						Queryconditionsseban = "sebanprice4";
					}

					System.out.println("----这个o26 !=NULL    ");

					if (ksebanname.equals("hejinheitong")) {
						fsebanname = "合金黑铜";
						fsebanname2 = "hejinheitong";
						System.out.println("p336");
						Queryconditionsseban = "sebanprice5";
					}
					if (ksebanname.equals("")) {
						fsebanname2 = "zhuntongtype";
						Queryconditionsseban = "sebanprice";
					}
					entity.setPanelname(fsebanname);
					entity.setPanfield(Queryconditionsseban);
					// zheli qudiao l }
					System.out.println("获取这个门选择的色板为:" + fsebanname
							+ fsebanname2 + "和这个 色板的对应类型为："
							+ Queryconditionsseban);
					// 获取这个色板的每平方多少钱
					DataQuote dquote = null;

					Map map = new HashMap();
					List<Dealer> list = null;

					String bpinpai = "pinpai2";
					dquote = dataQuoteBo.findDataQuoteByBtype(
							Queryconditionsseban, bpinpai);
					System.out.println("根据色板查询到这个门的色板为:" + dquote.getBtype()
							+ dquote.getBpriceone());
					String calsebanprice = dquote.getBpriceone();

					double calsebanprice2 = Integer.parseInt(calsebanprice); // 每平方的价格

					// 简易复合门 和 豪华复合门 双倍 无法判断计算。 需要根据网上商城那里传过来的门型去判断。

					double danoneprice;
					// 单开
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("101") || o12.equals("102")) {
						System.out.println("获取这个门宽为:" + Mwidthdaxiao + "高度为:"
								+ Mwidthdaxiao);
						System.out.println("门平方为" + c + "多出的平方为" + dcopy);
						System.out.println("获取这个门选择的色板为:" + fsebanname
								+ fsebanname2 + "和这个 气窗的价格：" + Endpriceseban);
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						System.out.println(danoneprice + "....");
						// 气窗的价格 再加上，如果没有气窗 有的话,
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;
						}
						// 如果是反凸门
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						// 如果是 玻璃， 判断玻璃的暂时去掉
						// if(endboli.equals("endboli")){
						// danoneprice=danoneprice+50;
						// }
						calEndALlproduct = calEndALlproduct + danoneprice;
					}
					// 子母门
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("103") || o12.equals("104")) {

						danoneprice = calsebanprice2 * c;

						// 高仿 和高仿玻璃 每平方*40元
						if (fsebanname2.equals("gaofangtype")
								|| fsebanname2.equals("gaofangbolitype")) {
							danoneprice = danoneprice + c * 40;
							System.out.println(danoneprice + ">>>>33333..");
						}
						// 气窗
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;
						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}// 如果是 玻璃，
						if (endboli.equals("endboli")) {
							danoneprice = danoneprice + 50;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
						System.out.println(danoneprice + ">>>>22..");
					}

					// 双开
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("201") || o12.equals("202")
							|| o12.equals("203")) {
						System.out.println("获取这个门宽为:" + Mwidthdaxiao + "高度为:"
								+ Mwidthdaxiao);
						System.out.println("门平方为" + c + "多出的平方为" + dcopy);
						System.out.println("获取这个门选择的色板为:" + fsebanname
								+ fsebanname2 + "和这个 气窗的价格：" + Endpriceseban);
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						System.out.println(danoneprice + "..3..");
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;

						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
					}
					// 四开子母门
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("401") || o12.equals("402")
							|| o12.equals("403")) {
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						System.out.println(danoneprice + "..4..");
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;

						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
					}
					// 四开门
					if (o12.equals("0")) {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("404") || o12.equals("405")) {
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;
						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
						System.out.println(danoneprice + "..5.");
					}

					// LZ 门头 琉璃瓦门头 前台判断 已完成 未保存到配置表（ 名称 数量 总价 三个字段）
					String mentouName = null;
					String QuerymentoucheckName = null;
					double calmentouprice2 = 0;
					double calmentouprice3 = 0;
					if (o24.equals("0") || o24 == "0") {
						System.out.println("----这个o24 ==0");
					} else if (o24 != null && !o24.equals("") && o24 != "0") {
						System.out.println("----这个o24 " + o24);
						Department2 depart = departmentBo
								.findDataDepartment2ById(Long.valueOf(o24));
						mentouName = depart.getName();
						QuerymentoucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																		// ,再加上这个品牌的名称
																		// 去，报价系统里面查询

						System.out.println(" 门头" + mentouName + "checkName="
								+ QuerymentoucheckName);
						// LZ去报价系统里查询：
						DataQuote dquotemt = null;
						String bpinpaimt = "pinpai2";
						dquotemt = dataQuoteBo.findDataQuoteByBtype(
								QuerymentoucheckName, bpinpaimt);
						System.out
								.println("根据用户选择的门头查询字段查询到这个门头的对应品牌价格为:"
										+ dquotemt.getBtype()
										+ dquotemt.getBpriceone());
						String calmentouprice = dquotemt.getBdanjiac(); // 这是用户选择
																		// 门头的价格
						String calmentoupriceone = dquotemt.getBpriceone();

						double calmentoupriceone2 = Double
								.parseDouble(calmentoupriceone);
						double calmentouprice5 = Integer
								.parseInt(calmentouprice);
						// 超过2000mm 多出的 = ( 乘以10)

						// 获得高度 Mwidthdaxiao
						if (Mwidthdaxiao > 2000) {
							calmentouprice2 = (Mwidthdaxiao - 2000)
									* calmentoupriceone2;
						}
						calmentouprice3 = calmentouprice5 + calmentouprice2;
						// ;

						calEndALlproduct = calEndALlproduct + calmentouprice3;
						System.out.println("   门头  结束  ，价格为" + calmentouprice3
								+ "计算总价暂时为" + calEndALlproduct);

					}
					entity.setFramename(mentouName);

				} // gjp 0316

				// 判断其他配件的选择 头加
				String pjtoujiaName = null;
				String QuerytoujiacheckName = null;
				double caltoujiaprice2 = 0;
				if (o43.equals("0") || o43 == "0") {
					System.out.println("----这个o43 ==0");
				} else if (o43 != null && !o43.equals("") && o43 != "0") {
					System.out.println("----这个o43 " + o43);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o43));
					pjtoujiaName = depart.getName();
					QuerytoujiacheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 头加的名称" + pjtoujiaName + "checkName"
							+ QuerytoujiacheckName);
					// LZ去报价系统里查询：
					DataQuote dquotetoujia = null;
					String bpinpaitoujia = "pinpai2";
					dquotetoujia = dataQuoteBo.findDataQuoteByBtype(
							QuerytoujiacheckName, bpinpaitoujia);
					System.out.println("根据用户选择的头加查询字段查询到这个头加的对应品牌价格为:"
							+ dquotetoujia.getBtype()
							+ dquotetoujia.getBpriceone());
					String caltoujiaprice = dquotetoujia.getBdanjiac(); // 这是用户选择立柱的价格
					// double callzprice=Double.parseDouble(callzprice);
					caltoujiaprice2 = Integer.parseInt(caltoujiaprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					System.out.println(" 配件 头加  结束  ，价格为" + caltoujiaprice2);

					calEndALlproduct = calEndALlproduct + caltoujiaprice2;

				}
				entity.setToujiaName(pjtoujiaName);

				// 判断其他配件的选择 锁具
				String peijianName = null;
				String QuerysuojuucheckName = null;
				double calsuojuprice2 = 0;
				if (o32.equals("0") || o32 == "0") {
					System.out.println("----这个o32 ==0");
				} else if (o32 != null && !o32.equals("") && o32 != "0") {
					System.out.println("----这个o32 " + o32);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o32));
					peijianName = depart.getName();
					QuerysuojuucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 锁具的名称" + peijianName + "checkName"
							+ QuerysuojuucheckName);
					// LZ去报价系统里查询：
					DataQuote dquotesuoju = null;
					String bpinpaisuoju = "pinpai2";
					dquotesuoju = dataQuoteBo.findDataQuoteByBtype(
							QuerysuojuucheckName, bpinpaisuoju);
					System.out.println("根据用户选择的锁具查询字段查询到这个锁具的对应品牌价格为:"
							+ dquotesuoju.getBtype()
							+ dquotesuoju.getBpriceone());
					String calsuojuprice = dquotesuoju.getBdanjiac(); // 这是用户选择立柱的价格
					// double callzprice=Double.parseDouble(callzprice);
					calsuojuprice2 = Integer.parseInt(calsuojuprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calsuojuprice2;
					System.out.println(" 配件 锁具  结束  ，价格为" + calsuojuprice2);
				}
				entity.setSuojuName(peijianName);

				// 判断其他配件的选择 锁芯
				String pjsuoxinName = null;
				String QuerysuoxincheckName = null;
				double calsuoxinprice2 = 0;
				if (o33.equals("0") || o33 == "0") {
					System.out.println("----这个o33 ==0");
				} else if (o33 != null && !o33.equals("") && o33 != "0") {
					System.out.println("----这个o33 " + o33);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o33));
					pjsuoxinName = depart.getName();
					QuerysuoxincheckName = depart.getInputname2(); // 确定这个用户选择是是什么锁芯
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件锁芯的名称" + peijianName + "checkName"
							+ QuerysuoxincheckName);
					// LZ去报价系统里查询：
					DataQuote dquotesuoxin = null;
					String bpinpaisuoju = "pinpai2";
					dquotesuoxin = dataQuoteBo.findDataQuoteByBtype(
							QuerysuoxincheckName, bpinpaisuoju);
					System.out.println("根据用户选择的锁具查询字段查询到这个锁具的对应品牌价格为:"
							+ dquotesuoxin.getBtype()
							+ dquotesuoxin.getBpriceone());
					String calsuoxinprice = dquotesuoxin.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calsuoxinprice2 = Integer.parseInt(calsuoxinprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calsuoxinprice2;

					System.out.println(" 配件 锁芯 结束  ，价格为" + calsuoxinprice2);
				}
				entity.setSuoxName(pjsuoxinName);

				// 判断其他配件的选择 门铃
				String pjmenlingName = null;
				String QuerymenlingcheckName = null;
				double calmenlingprice2 = 0;
				if (o34.equals("0") || o34 == "0") {
					System.out.println("----这个o34 ==0");
				} else if (o34 != null && !o34.equals("") && o34 != "0") {
					System.out.println("----这个o34 " + o34);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o34));
					pjmenlingName = depart.getName();
					QuerymenlingcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （门铃）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （门铃）的名称" + pjmenlingName
							+ "checkName=" + QuerymenlingcheckName);
					// LZ去报价系统里查询：
					DataQuote dquotemenling = null;
					String bpinpaimenling = "pinpai2";
					dquotemenling = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimenling);
					System.out.println("根据用户选择的 (门铃)查询字段查询到这个 (门铃)的对应品牌价格为:"
							+ dquotemenling.getBtype()
							+ dquotemenling.getBpriceone());
					String calmenlingprice = dquotemenling.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calmenlingprice2 = Integer.parseInt(calmenlingprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;

					calEndALlproduct = calEndALlproduct + calmenlingprice2;
					System.out.println(" 配件 (门铃) 结束  ，价格为" + calmenlingprice2);
				}
				entity.setMenlingName(pjmenlingName);

				// 判断其他配件的选择 猫眼
				String pjmaoyanName = null;
				String QuerymaoyancheckName = null;
				double calmaoyanprice2 = 0;
				if (o35.equals("0") || o35 == "0") {
					System.out.println("----这个o35 ==0");
				} else if (o34 != null && !o35.equals("") && o35 != "0") {
					System.out.println("----这个o35 " + o35);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o35));
					pjmaoyanName = depart.getName();
					QuerymaoyancheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （猫眼）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （猫眼）的名称" + pjmaoyanName
							+ "checkName=" + QuerymaoyancheckName);
					// LZ去报价系统里查询：
					DataQuote dquotemaoyan = null;
					String bpinpaimaoyan = "pinpai2";
					dquotemaoyan = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (猫眼)查询字段查询到这个 (猫眼)的对应品牌价格为:"
							+ dquotemaoyan.getBtype()
							+ dquotemaoyan.getBpriceone());
					String calmaoyanprice = dquotemaoyan.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calmaoyanprice2 = Integer.parseInt(calmaoyanprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;

					calEndALlproduct = calEndALlproduct + calmaoyanprice2;
					System.out.println(" 配件 (猫眼) 结束  ，价格为" + calmaoyanprice2);
				}
				entity.setMaoyanName(pjmaoyanName);

				// 判断其他配件的选择 下档
				String pjxiadangName = null;
				String QueryxiadangcheckName = null;
				double calxiadangprice2 = 0;
				if (o36.equals("0") || o36 == "0") {
					System.out.println("----这个o36 ==0");
				} else if (o36 != null && !o36.equals("") && o36 != "0") {
					System.out.println("----这个o36 " + o36);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o36));
					pjxiadangName = depart.getName();
					QueryxiadangcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （下档）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （下档）的名称" + pjxiadangName
							+ "checkName=" + QueryxiadangcheckName);
					// LZ去报价系统里查询：
					DataQuote dquotexiadang = null;
					String bpinpaimaoyan = "pinpai2";
					dquotexiadang = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (下档)查询字段查询到这个 (下档)的对应品牌价格为:"
							+ dquotexiadang.getBtype()
							+ dquotexiadang.getBpriceone());
					String calxiadangprice = dquotexiadang.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calxiadangprice2 = Integer.parseInt(calxiadangprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calxiadangprice2;
					System.out.println(" 配件 (下档) 结束  ，价格为" + calxiadangprice2);
				}
				entity.setXiadangName(pjxiadangName);

				// 判断其他配件的选择 拉手 * 2
				String pjlashouName = null;
				String QuerylashoucheckName = null;
				double callashouprice3 = 0;
				if (o37.equals("0") || o37 == "0") {
					System.out.println("----这个o37 ==0");
				} else if (o37 != null && !o37.equals("") && o37 != "0") {
					System.out.println("----这个o37 " + o37);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o37));
					pjlashouName = depart.getName();
					QuerylashoucheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （拉手）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （拉手）的名称" + pjlashouName
							+ "checkName=" + QuerylashoucheckName);
					// LZ去报价系统里查询：
					DataQuote dquotelashou = null;
					String bpinpaimaoyan = "pinpai2";
					dquotelashou = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (拉手)查询字段查询到这个 (拉手)的对应品牌价格为:"
							+ dquotelashou.getBtype()
							+ dquotelashou.getBpriceone());
					String callashouprice = dquotelashou.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					double callashouprice2 = Integer.parseInt(callashouprice);
					callashouprice3 = callashouprice2 * 2; // 立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + callashouprice3;
					System.out.println(" 配件 (拉手) 结束  ，价格为" + callashouprice3);
				}
				entity.setLashouName(pjlashouName);
				// 判断其他配件的选择 铰链 * 2
				String pjjiaolianName = null;
				String QueryjiaoliancheckName = null;
				double caljiaolianprice3 = 0;
				if (o38.equals("0") || o38 == "0") {
					System.out.println("----这个o38 ==0");
				} else if (o38 != null && !o38.equals("") && o38 != "0") {
					System.out.println("----这个o38 " + o38);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o38));
					pjjiaolianName = depart.getName();
					QueryjiaoliancheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																		// （铰链）
																		// ,再加上这个品牌的名称
																		// 去，报价系统里面查询
					System.out.println("配件 （铰链）的名称" + pjjiaolianName
							+ "checkName=" + QueryjiaoliancheckName);
					// LZ去报价系统里查询：
					DataQuote dquotejiaolian = null;
					String bpinpaimaoyan = "pinpai2";
					dquotejiaolian = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (铰链)查询字段查询到这个 (铰链)的对应品牌价格为:"
							+ dquotejiaolian.getBtype()
							+ dquotejiaolian.getBpriceone());
					String caljiaolianprice = dquotejiaolian.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					double caljiaolianprice2 = Integer
							.parseInt(caljiaolianprice);
					// caljiaolianprice3=caljiaolianprice2*2; //立柱选择的是每对 *2 ;

					calEndALlproduct = calEndALlproduct + caljiaolianprice2;
					System.out.println(" 配件 (铰链) 结束  ，价格为" + caljiaolianprice2);
				}

				// 判断其他配件的选择 闭门器
				String pjbimenqiName = null;
				String QuerybimenqicheckName = null;
				double calbimenqiprice3 = 0;
				if (o42.equals("0") || o42 == "0") {
					System.out.println("----这个o42 ==0");
				} else if (o42 != null && !o42.equals("") && o42 != "0") {
					System.out.println("----这个o42 " + o42);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o42));
					pjbimenqiName = depart.getName();
					QuerybimenqicheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （闭门器）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （闭门器）的名称" + pjbimenqiName
							+ "checkName=" + QuerybimenqicheckName);
					// LZ去报价系统里查询：
					DataQuote dquotebimenqi = null;
					String bpinpaimaoyan = "pinpai2";
					dquotebimenqi = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (闭门器)查询字段查询到这个 (闭门器)的对应品牌价格为:"
							+ dquotebimenqi.getBtype()
							+ dquotebimenqi.getBpriceone());
					String calbimenqiprice = dquotebimenqi.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					double calbimenqiprice2 = Integer.parseInt(calbimenqiprice);
					// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calbimenqiprice2;
					System.out.println(" 配件 (闭门器) 结束  ，价格为" + calbimenqiprice2);
				}
				entity.setBimenqiName(pjbimenqiName);

				// 判断 材质 cz 厚度 * 每平方的价格

				String czhouduName = null;
				String QueryczcheckName = null;
				double calbimenqiprice2 = 0;
				if (o27.equals("0") || o27 == "0") {
					System.out.println("----这个o27 ==0");
				} else if (o27 != null && !o27.equals("") && o27 != "0") {
					System.out.println("----这个o27 " + o27);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o27));
					czhouduName = depart.getName();
					QueryczcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （闭门器）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
					System.out.println("材质（厚度）的名称" + czhouduName + "checkName="
							+ QueryczcheckName);
					// LZ去报价系统里查询：
					DataQuote dquotehoudu = null;
					String bpinpaihoudu = "pinpai2";
					// dquotehoudu=dataQuoteBo.findDataQuoteByBtype(QueryczcheckName,bpinpaihoudu);
					// System.out.println("根据用户选择的 (厚度)查询字段查询到这个 (厚度)的对应品牌价格为:"+dquotehoudu.getBtype()+dquotehoudu.getBpriceone());
					// String calbimenqiprice=dquotehoudu.getBdanjiac(); //
					// 这是用户选择 这个门厚度，每平方的价格
					// double callzprice=Double.parseDouble(callzprice);
					// calbimenqiprice2 = Integer.parseInt(calbimenqiprice);
					// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calbimenqiprice2;
					System.out.println("  (厚度) 结束  ，价格为" + calbimenqiprice2);
				}
				entity.setHoudu(czhouduName);

				// 板材 ，材质
				String czcaizhiName = null;
				String QuerycaizhicheckName = null;
				double calcaizhiqiprice3 = 0;
				if (o28.equals("0") || o28 == "0") {
					System.out.println("----这个o28 ==0");
				} else if (o28 != null && !o28.equals("") && o28 != "0") {
					System.out.println("----这个o28 " + o28);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o28));
					czcaizhiName = depart.getName();
					QuerycaizhicheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （材质）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("材质（材质）的名称" + czcaizhiName
							+ "checkName=" + QuerycaizhicheckName);
					// LZ去报价系统里查询：
					// DataQuote dquotecaizhi=null;
					// String bpinpaicaizhi="pinpai2";
					// dquotecaizhi=dataQuoteBo.findDataQuoteByBtype(QuerycaizhicheckName,bpinpaicaizhi);
					// System.out.println("根据用户选择的 (材质)查询字段查询到这个 (材质)的对应品牌价格为:"+dquotecaizhi.getBtype()+dquotecaizhi.getBpriceone());
					// String calcaizhiqiprice=dquotecaizhi.getBdanjiac(); //
					// 这是用户选择 这个门材质，每平方的价格
					// double callzprice=Double.parseDouble(callzprice);
					// calcaizhiqiprice3 = Integer.parseInt(calcaizhiqiprice);
					// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
					// System.out.println("  (材质) 结束  ，价格为"+calcaizhiqiprice3);
				}
				entity.setCaizhi(czcaizhiName);

				System.out.println(calEndALlproduct + "最终获得的价格为");

				System.out.println(entity.getPanelname() + "最终获得的  色板那么为");

			}
		}//

		JSONArray resultleft3 = new JSONArray();
		JSONObject ret3 = new JSONObject();
		long id = 1;

		double sp2 = calEndALlproduct;
		int sp = (int) sp2;
		int Mtangshu = 0;
		int tp = sp;
		// o13="1";

		if ("".equals(o13) || o13 == null) {
			Mtangshu = 1;
			o13="1";
		} else {
			Mtangshu = Integer.parseInt(o13);
			tp = sp * Mtangshu;
		}
		resultleft3.add(buildjsonnumber(id, sp, Mtangshu, tp));
		String imgStr = o41;
		String imgid = CreateGUID.createGuId();

		String filePath = request.getServletPath();
		String filePath2 = request.getSession().getServletContext()
				.getRealPath(request.getRequestURI());

		String filePath3 = request.getSession().getServletContext()
				.getRealPath("//");
		System.out.println(filePath + filePath2 + filePath3);
		String photoName=CreateGUID.createGuId() + ".png";
		String imgFile = filePath3+"\\home\\resource\\imageforcart\\" + photoName;
	
		// 转换图片
		ImageUtil.generateImage(imgStr, imgFile);
		resultleft3.add(buildjsonimg(imgid, imgFile));
		PrintWriter writer = response.getWriter();

		// entity.setCartor("y");
		entity.setPhoto(photoName);
		entity.setCartunitprice(String.valueOf(sp)); // 单价
		entity.setCartsize(o13); // 数量
		entity.setCarttotalprice(String.valueOf(tp)); // 总价
		entity.setStatus(CARTORDER); // 加入 立即下单的状态
		entity.setTcount(o13);
		Person loginPerson = loginInfo.getLoginPerson();
		System.out.println("..." + loginPerson.getId() + loginPerson.getName());
		String org = loginPerson.getOrganizationId();
		// if(entity.getPanelname()!=null||!entity.getPanelname().equals("")){
		if (entity.getPanelname() == null) {
			entity.setCartor("N");
		} else {
			entity.setCartor("y");
			entity.setOrg(org);
			commodityUserBo.save(entity);

		}
		System.out.println("刚刚添加的 产品是：" + entity.getId());

		model.addAttribute("userInfo", loginPerson);
		request.setAttribute("userInfo", loginPerson);

		return "/home/order/check_orders";
		// return null;

	}

	/*************************** 点击 加入购物车 ********************/
	@RequestMapping(value = "/pages/company/getdiyProductaddCat.do")
	public String getdiyProductaddCat(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			DiyModel diyModel, CommodityUser entity, HttpSession session)
			throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String operation = request.getParameter("operation");
		String callback = request.getParameter("callback");

		String o11 = request.getParameter("pp"); // 选择是品牌
		String o12 = request.getParameter("mx"); // 选择是门型 模版--id
		String o13 = request.getParameter("num"); // 选择是樘数
		String o14 = request.getParameter("mh"); // 选择是门 高 --
		String o15 = request.getParameter("mw"); // 选择是 门宽 --
		String o16 = request.getParameter("mhd"); // 选择是厚度

		String o21 = request.getParameter("ks"); // 选择是款式
		String o22 = request.getParameter("zks"); // 选择是 子款式
		String o23 = request.getParameter("bmk"); // 选择是 门框
		String o24 = request.getParameter("bmt"); // 选择是 门头
		String o25 = request.getParameter("bmz"); // 选择是 门柱
		String o26 = request.getParameter("sb"); // 选择是 色板 ----
		String o27 = request.getParameter("kx"); // 选择是 开向 ---
		String o28 = request.getParameter("bhd"); // 选择是 板材厚度--
		String o29 = request.getParameter("bcz"); // 选择是 板材材质---

		// String o30 = request.getParameter("qita"); // 选择是 立柱--
		String o31 = request.getParameter("qlz"); // 选择是 立柱--
		String o32 = request.getParameter("psj"); // 选择是 锁具--
		String o33 = request.getParameter("psx"); // 选择是锁芯--
		String o34 = request.getParameter("pml"); // 选择是 门铃--
		String o35 = request.getParameter("pmy"); // 选择是 猫眼--
		String o36 = request.getParameter("pxd"); // 选择是下档--
		String o37 = request.getParameter("pls"); // 选择是拉手--
		String o38 = request.getParameter("pjl"); // 选择是铰链--

		String o41 = request.getParameter("img"); // 选择是 图片的路径

		String o17 = request.getParameter("bl"); // 玻璃 --------
		String o18 = request.getParameter("qc"); // 气窗 -------
		String o42 = request.getParameter("pbmq"); // 选择是闭门器 --
		String o43 = request.getParameter("ptouj"); // 选择是门头加------

		System.out.println("!点击  加入购物车1---  !--- " + o11 + "--" + o12 + "----"
				+ o13 + "---" + o14 + "--" + o15 + "----" + o16 + "---");
		System.out.println(" !!--- " + o21 + "--" + o22 + "---" + o23 + "---"
				+ o24 + "--" + o25 + "----" + o26 + "---" + o27 + "--" + o28
				+ "---" + o29 + "---");
		System.out.println(" !!!--- " + o31 + "--" + o32 + "---" + o33 + "---"
				+ o34 + "--" + o35 + "---" + o36 + "---" + o37 + "---" + o38
				+ "---");
		System.out.println(" !!!!点击  加入购物车1---!!!!--- " + o41);

		// entity.setNumber(number); //商品编号
		
	 String  brandname=null;
		if("".equals(o11) || o11 == null){
			Object pinname=session.getAttribute("brandName");
			 brandname=(String) pinname;
			 o11=brandname;
		}
		entity.setBrand(o11); // 品牌
		if ("".equals(o13) || o13 == null || o13.equals("0") || o13 == "0") {
			o13 = "1";
			System.out.println("==null  ");
			entity.setTcount(o13);
		}
		entity.setSize(o14); // 高
		entity.setSizekd(o15); // 宽
	//	entity.setTcount(o13);
		entity.setHoudu(o16); // 厚度
		double calEndALlproduct = 0;
		if (o31 != null && o26 != null && o21 != null && o14 != null
				&& o15 != null) {
			System.out.println("获得了这个门为" + o12);
			// 如果不为空 则去计算

			// LZ立柱 前台判断 已完成 未保存到配置表（ 名称 数量 总价 三个字段）
			String lizhuName = null;
			String QuerylizhucheckName = null;
			double callzprice3 = 0;
			if (o31.equals("0") || o31 == "0") {
				System.out.println("----这个o31 ==0");
			} else if (o31 != null && !o31.equals("") && o31 != "0") {
				System.out.println("----这个o31 " + o31);
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o31));
				lizhuName = depart.getName();
				QuerylizhucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询

				System.out.println("lizhu" + lizhuName + "checkName"
						+ QuerylizhucheckName);
				// LZ去报价系统里查询：
				DataQuote dquotelz = null;
				String bpinpailz = "pinpai2";
				dquotelz = dataQuoteBo.findDataQuoteByBtype(
						QuerylizhucheckName, bpinpailz);
				System.out.println("根据用户选择的立柱查询字段查询到这个立柱的对应品牌价格为:"
						+ dquotelz.getBtype() + dquotelz.getBpriceone());
				String callzprice = dquotelz.getBdanjiac(); // 这是用户选择立柱的价格
				// double callzprice=Double.parseDouble(callzprice);
				double callzprice2 = Integer.parseInt(callzprice);
				callzprice3 = callzprice2 * 2; // 立柱选择的是每对 *2 ;
				System.out.println(" 其它   结束  ，价格为" + callzprice3);

			}

			entity.setElsessname(lizhuName);

			calEndALlproduct = callzprice3; // 立柱计算 001

			// 判断开向 已经完成，还未记录到 用户配置表
			String endkaixiang = null;
			if (o27.equals("0") || o27 == "0") {
				System.out.println("----这个o27 ==0");
			} else if (o27 != null && !o27.equals("")) {
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o27));
				endkaixiang = depart.getName();
				System.out.println("开向输出为:" + endkaixiang);

			}
			entity.setOpento(endkaixiang);
			// 判断压花 ，如果是反凸压花， 则每平米 20 元/ 获取ID，查询父节点的名称中是否包好凸。
			String fantumen = null;
			String kuanshiName = null;
			if (o21.equals("0") || o21 == "0") {
				System.out.println("----这个o21 ==0");
			} else if (o21 != null && !o21.equals("")) {
				Department2 depart = departmentBo.findDataDepartment2ById(Long
						.valueOf(o21));
				Long id22 = depart.getOrganization().getId();
				Organization2 organ = organizationBo.find(depart
						.getOrganization().getId());
				kuanshiName = organ.getName();
				if (kuanshiName.contains("凸") == true) {
					fantumen = "fantumen";
				} else {
					fantumen = "nofantu";
				}
				System.out.println("这是个一个" + id22 + kuanshiName + fantumen);
			}

			entity.setDoorstyletype(kuanshiName);

			// 判断 玻璃
			String endboli = null;
			if (o17.equals("0") || o17 == "0") {
				System.out.println("----这个o17 ==0");
			} else if (o17 != null && !o17.equals("")) {
				endboli = "endboli";
			}
			entity.setDoorstylefield(endboli);

			// 判断门型
			String typenamecart = null;
			if (o12.equals("0") || o12 == "0") {
				System.out.println("----这个o12 ==0");
			} else if (o12 != null && !o12.equals("")) {
				if (o12.equals("101") || o12 == "101") {
					System.out.println(" 这是101一个：单门普通 ");
					typenamecart = "单门普通";
				}
				if (o12.equals("102") || o12 == "102") {
					System.out.println(" 这是102一个：单门气窗 ");
					typenamecart = "单门气窗";
				}
				if (o12.equals("103") || o12 == "103") {
					System.out.println(" 这是103一个：单开子母门普通");
					typenamecart = "单开子母门普通";
				}
				if (o12.equals("104") || o12 == "104") {
					System.out.println(" 这是104一个：单开子母门+门头 ");
					typenamecart = "单开子母门+门头";
				}
				if (o12.equals("201") || o12 == "201") {
					System.out.println(" 这是201一个： 双开门+门头 ");
					typenamecart = "双开门+门头";
				}
				if (o12.equals("202") || o12 == "202") {
					System.out.println(" 这是202一个：双开门+气窗 ");
					typenamecart = "双开门+气窗";
				}
				if (o12.equals("203") || o12 == "203") {
					System.out.println(" 这是203一个： 双开门+合金铜门 ");
					typenamecart = "双开门+合金铜门";
				}
				if (o12.equals("404") || o12 == "404") {
					System.out.println(" 这是404一个： 四开字母门+门头 ");
					typenamecart = "四开字母门+门头";
				}
				if (o12.equals("405") || o12 == "405") {
					System.out.println(" 这是405一个：四开字母门+气窗 ");
					typenamecart = "四开字母门+气窗";
				}
				if (o12.equals("403") || o12 == "403") {
					System.out.println(" 这是403一个：四开门+门头+气窗 ");
					typenamecart = "四开门+门头+气窗";
				}
				if (o12.equals("401") || o12 == "401") {
					System.out.println(" 这是401一个：四开门+门头 ");
					typenamecart = "四开门+门头";
				}
				if (o12.equals("402") || o12 == "402") {
					System.out.println(" 这是402一个：四开门+气窗 ");
					typenamecart = "四开门+气窗";
				}

			}

			entity.setType(typenamecart);

			// 判断尺寸
			BigDecimal menpingfangAll;
			double c;
			if (o14.equals("0") || o15.equals("0") || o14 == "0" || o15 == "0") {
				System.out.println("----这个o14 ==0");
			} else if (o14 != null && !o14.equals("") && o15 != null
					&& !o15.equals("")) {

				double Mheightdaxiao = Integer.parseInt(o15); // 门宽 比大小用到
				double Mwidthdaxiao = Integer.parseInt(o14); // 门高 比大小用到
				BigDecimal Mwidth2 = new BigDecimal(Mwidthdaxiao); // 门宽
				BigDecimal Mheight2 = new BigDecimal(Mheightdaxiao); // 门高
				BigDecimal menpingfang1 = new BigDecimal(Mwidthdaxiao)
						.divide(new BigDecimal(1000));
				BigDecimal menpingfang2 = new BigDecimal(Mheightdaxiao)
						.divide(new BigDecimal(1000));
				menpingfangAll = menpingfang1.multiply(menpingfang2); // 平方 数
				double a = Mwidthdaxiao / 1000;
				double b = Mheightdaxiao / 1000;
				c = a * b;
				String s = new DecimalFormat("#.###").format(c);
				System.out.println(" NO2:  第二部获得门的 平方数" + menpingfangAll
						+ " int   数值为： " + s);
				if (c > 1) {
					System.out.println(" 大于2平方");
				}
				double k = Double.parseDouble(s);
				System.out.println(" 大K" + k);

				// 判断门 平方数的 和 多出的
				double menpingfang;
				double duochumenpingfang = 0;
				String dcopy = null; // 差值转换

				if (o12.equals("101") || o12.equals("102")) { // 单门
					if (c < 2) {
						menpingfang = 2;
						duochumenpingfang = 0;
					} else {
						menpingfang = c;
						duochumenpingfang = c - 2;
						dcopy = new DecimalFormat("#.###")
								.format(duochumenpingfang);
					}
					System.out.println(" P小于2平方one" + "门平方为" + c + "多出的平方为"
							+ dcopy);

				}
				if (o12.equals("103") || o12.equals("103")) { // 单开字母
					if (c < 2.46) {
						System.out.println(" p小于two");
						menpingfang = 2.46;
					}
					if (o18 != null || !o18.equals("")) {
						if (c > 2.46 && c < 3) {
							System.out.println(" p小于three");
							menpingfang = 3;
						} else {
							menpingfang = c;
							duochumenpingfang = c - 3;
							dcopy = new DecimalFormat("#.###")
									.format(duochumenpingfang);
						}
					}
					System.out.println(" P单开字母one" + "门平方为" + c + "多出的平方为"
							+ dcopy);
				}

				if (o12.equals("201") || o12.equals("202") || o12.equals("203")) { // 双开
					if (c > 2.46 && c < 3.075) {
						System.out.println(" P小于");
						menpingfang = 3.075;
					}
					if (o18 != null || !o18.equals("")) {
						if (c > 3.075 && c < 4) {
							System.out.println(" p小于four");
							menpingfang = 4;
							c = 4;
						} else {
							menpingfang = c;
							duochumenpingfang = c - 4;
							dcopy = new DecimalFormat("#.###")
									.format(duochumenpingfang);

						}
					}
					System.out.println(" P双开one" + "门平方为" + c + "多出的平方为"
							+ dcopy);
				}

				if (o12.equals("401") || o12.equals("402") || o12.equals("403")) { // 四开门
					menpingfang = c;
					System.out.println(" p小于five" + menpingfang);

				}
				if (o12.equals("404") || o12.equals("405")) { // 四开子母门
					menpingfang = c;
					System.out.println(" p小于six" + menpingfang);
				}

				/***
				 * 第三部分 判断色板 从前台获得的name ，前缀都加上 k 。 定义的字段 前缀都加上 f。 zhuntongtype
				 * 准铜 1 zhuntongbolitype 准铜玻璃 2 gaofangtype 高仿铜门 3
				 * gaofangbolitype 高仿玻璃 4 hejinheitong 合金黑铜5 *
				 **/
				Department2 entityse = null;
				String fsebanname = null;
				String fsebanname2 = null;
				double Endpriceseban = 0;

				String Queryconditionsseban = null;
				if (o26.equals("0") || o26 == "0") {
					System.out.println("----这个o26 ==0");
				} else if (!o26.equals("") || o26 != null) {
					System.out.println("----这个o26 !=NULL    ");

					entityse = departmentBo.findDataDepartment2ById(Long
							.valueOf(o26));
					entityse.getName();
					String ksebanname = entityse.getInputname2(); // 获取这个色板的类型，(1234
																	// ,)
					if (ksebanname.equals("zhuntongtype")) {
						fsebanname = "准铜";
						fsebanname2 = "zhuntongtype";
						Queryconditionsseban = "sebanprice";

					}
					if (ksebanname.equals("zhuntongbolitype")) {
						fsebanname = "准铜玻璃";
						fsebanname2 = "zhuntongbolitype";
						Queryconditionsseban = "sebanprice2";
					}
					if (ksebanname.equals("gaofangtype")) {
						fsebanname = "高仿铜门";
						fsebanname2 = "gaofangtype";
						Queryconditionsseban = "sebanprice3";
					}
					if (ksebanname.equals("gaofangbolitype")) {
						fsebanname = "高仿玻璃";
						fsebanname2 = "gaofangbolitype";
						Queryconditionsseban = "sebanprice4";
					}

					System.out.println("----这个o26 !=NULL    ");

					if (ksebanname.equals("hejinheitong")) {
						fsebanname = "合金黑铜";
						fsebanname2 = "hejinheitong";
						System.out.println("p336");
						Queryconditionsseban = "sebanprice5";
					}
					if (ksebanname.equals("")) {
						fsebanname2 = "zhuntongtype";
						Queryconditionsseban = "sebanprice";
					}
					entity.setPanelname(fsebanname);
					entity.setPanfield(Queryconditionsseban);
					// zheli qudiao l }
					System.out.println("获取这个门选择的色板为:" + fsebanname
							+ fsebanname2 + "和这个 色板的对应类型为："
							+ Queryconditionsseban);
					// 获取这个色板的每平方多少钱
					DataQuote dquote = null;

					Map map = new HashMap();
					List<Dealer> list = null;

					String bpinpai = "pinpai2";
					dquote = dataQuoteBo.findDataQuoteByBtype(
							Queryconditionsseban, bpinpai);
					System.out.println("根据色板查询到这个门的色板为:" + dquote.getBtype()
							+ dquote.getBpriceone());
					String calsebanprice = dquote.getBpriceone();

					double calsebanprice2 = Integer.parseInt(calsebanprice); // 每平方的价格

					// 简易复合门 和 豪华复合门 双倍 无法判断计算。 需要根据网上商城那里传过来的门型去判断。

					double danoneprice;
					// 单开
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("101") || o12.equals("102")) {
						System.out.println("获取这个门宽为:" + Mwidthdaxiao + "高度为:"
								+ Mwidthdaxiao);
						System.out.println("门平方为" + c + "多出的平方为" + dcopy);
						System.out.println("获取这个门选择的色板为:" + fsebanname
								+ fsebanname2 + "和这个 气窗的价格：" + Endpriceseban);
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						System.out.println(danoneprice + "....");
						// 气窗的价格 再加上，如果没有气窗 有的话,
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;
						}
						// 如果是反凸门
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						// 如果是 玻璃， 判断玻璃的暂时去掉
						// if(endboli.equals("endboli")){
						// danoneprice=danoneprice+50;
						// }
						calEndALlproduct = calEndALlproduct + danoneprice;
					}
					// 子母门
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("103") || o12.equals("104")) {

						danoneprice = calsebanprice2 * c;

						// 高仿 和高仿玻璃 每平方*40元
						if (fsebanname2.equals("gaofangtype")
								|| fsebanname2.equals("gaofangbolitype")) {
							danoneprice = danoneprice + c * 40;
							System.out.println(danoneprice + ">>>>33333..");
						}
						// 气窗
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;
						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}// 如果是 玻璃，
						if (endboli.equals("endboli")) {
							danoneprice = danoneprice + 50;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
						System.out.println(danoneprice + ">>>>22..");
					}

					// 双开
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("201") || o12.equals("202")
							|| o12.equals("203")) {
						System.out.println("获取这个门宽为:" + Mwidthdaxiao + "高度为:"
								+ Mwidthdaxiao);
						System.out.println("门平方为" + c + "多出的平方为" + dcopy);
						System.out.println("获取这个门选择的色板为:" + fsebanname
								+ fsebanname2 + "和这个 气窗的价格：" + Endpriceseban);
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						System.out.println(danoneprice + "..3..");
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;

						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
					}
					// 四开子母门
					if (o12.equals("0") || o12 == "0") {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("401") || o12.equals("402")
							|| o12.equals("403")) {
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						System.out.println(danoneprice + "..4..");
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;

						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
					}
					// 四开门
					if (o12.equals("0")) {
						System.out.println("----这个o12 ==0");
					} else if (o12.equals("404") || o12.equals("405")) {
						danoneprice = calsebanprice2 * c; // 单门每平方 * 平方数 = 单门 总价
						if (!o18.equals("") || o18 != null) {
						} else {
							danoneprice = danoneprice + 20 * c;
						}
						if (fantumen.equals("fantumen")) {
							danoneprice = danoneprice + 20 * c;
						}
						calEndALlproduct = calEndALlproduct + danoneprice;
						System.out.println(danoneprice + "..5.");
					}

					// LZ 门头 琉璃瓦门头 前台判断 已完成 未保存到配置表（ 名称 数量 总价 三个字段）
					String mentouName = null;
					String QuerymentoucheckName = null;
					double calmentouprice2 = 0;
					double calmentouprice3 = 0;
					if (o24.equals("0") || o24 == "0") {
						System.out.println("----这个o24 ==0");
					} else if (o24 != null && !o24.equals("") && o24 != "0") {
						System.out.println("----这个o24 " + o24);
						Department2 depart = departmentBo
								.findDataDepartment2ById(Long.valueOf(o24));
						mentouName = depart.getName();
						QuerymentoucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																		// ,再加上这个品牌的名称
																		// 去，报价系统里面查询

						System.out.println(" 门头" + mentouName + "checkName="
								+ QuerymentoucheckName);
						// LZ去报价系统里查询：
						DataQuote dquotemt = null;
						String bpinpaimt = "pinpai2";
						dquotemt = dataQuoteBo.findDataQuoteByBtype(
								QuerymentoucheckName, bpinpaimt);
						System.out
								.println("根据用户选择的门头查询字段查询到这个门头的对应品牌价格为:"
										+ dquotemt.getBtype()
										+ dquotemt.getBpriceone());
						String calmentouprice = dquotemt.getBdanjiac(); // 这是用户选择
																		// 门头的价格
						String calmentoupriceone = dquotemt.getBpriceone();

						double calmentoupriceone2 = Double
								.parseDouble(calmentoupriceone);
						double calmentouprice5 = Integer
								.parseInt(calmentouprice);
						// 超过2000mm 多出的 = ( 乘以10)

						// 获得高度 Mwidthdaxiao
						if (Mwidthdaxiao > 2000) {
							calmentouprice2 = (Mwidthdaxiao - 2000)
									* calmentoupriceone2;
						}
						calmentouprice3 = calmentouprice5 + calmentouprice2;
						// ;

						calEndALlproduct = calEndALlproduct + calmentouprice3;
						System.out.println("   门头  结束  ，价格为" + calmentouprice3
								+ "计算总价暂时为" + calEndALlproduct);

					}
					entity.setFramename(mentouName);

				} // gjp 0316

				// 判断其他配件的选择 头加
				String pjtoujiaName = null;
				String QuerytoujiacheckName = null;
				double caltoujiaprice2 = 0;
				if (o43.equals("0") || o43 == "0") {
					System.out.println("----这个o43 ==0");
				} else if (o43 != null && !o43.equals("") && o43 != "0") {
					System.out.println("----这个o43 " + o43);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o43));
					pjtoujiaName = depart.getName();
					QuerytoujiacheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 头加的名称" + pjtoujiaName + "checkName"
							+ QuerytoujiacheckName);
					// LZ去报价系统里查询：
					DataQuote dquotetoujia = null;
					String bpinpaitoujia = "pinpai2";
					dquotetoujia = dataQuoteBo.findDataQuoteByBtype(
							QuerytoujiacheckName, bpinpaitoujia);
					System.out.println("根据用户选择的头加查询字段查询到这个头加的对应品牌价格为:"
							+ dquotetoujia.getBtype()
							+ dquotetoujia.getBpriceone());
					String caltoujiaprice = dquotetoujia.getBdanjiac(); // 这是用户选择立柱的价格
					// double callzprice=Double.parseDouble(callzprice);
					caltoujiaprice2 = Integer.parseInt(caltoujiaprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					System.out.println(" 配件 头加  结束  ，价格为" + caltoujiaprice2);

					calEndALlproduct = calEndALlproduct + caltoujiaprice2;

				}
				entity.setToujiaName(pjtoujiaName);

				// 判断其他配件的选择 锁具
				String peijianName = null;
				String QuerysuojuucheckName = null;
				double calsuojuprice2 = 0;
				if (o32.equals("0") || o32 == "0") {
					System.out.println("----这个o32 ==0");
				} else if (o32 != null && !o32.equals("") && o32 != "0") {
					System.out.println("----这个o32 " + o32);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o32));
					peijianName = depart.getName();
					QuerysuojuucheckName = depart.getInputname2(); // 确定这个用户选择是是什么门柱
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 锁具的名称" + peijianName + "checkName"
							+ QuerysuojuucheckName);
					// LZ去报价系统里查询：
					DataQuote dquotesuoju = null;
					String bpinpaisuoju = "pinpai2";
					dquotesuoju = dataQuoteBo.findDataQuoteByBtype(
							QuerysuojuucheckName, bpinpaisuoju);
					System.out.println("根据用户选择的锁具查询字段查询到这个锁具的对应品牌价格为:"
							+ dquotesuoju.getBtype()
							+ dquotesuoju.getBpriceone());
					String calsuojuprice = dquotesuoju.getBdanjiac(); // 这是用户选择立柱的价格
					// double callzprice=Double.parseDouble(callzprice);
					calsuojuprice2 = Integer.parseInt(calsuojuprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calsuojuprice2;
					System.out.println(" 配件 锁具  结束  ，价格为" + calsuojuprice2);
				}
				entity.setSuojuName(peijianName);

				// 判断其他配件的选择 锁芯
				String pjsuoxinName = null;
				String QuerysuoxincheckName = null;
				double calsuoxinprice2 = 0;
				if (o33.equals("0") || o33 == "0") {
					System.out.println("----这个o33 ==0");
				} else if (o33 != null && !o33.equals("") && o33 != "0") {
					System.out.println("----这个o33 " + o33);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o33));
					pjsuoxinName = depart.getName();
					QuerysuoxincheckName = depart.getInputname2(); // 确定这个用户选择是是什么锁芯
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件锁芯的名称" + peijianName + "checkName"
							+ QuerysuoxincheckName);
					// LZ去报价系统里查询：
					DataQuote dquotesuoxin = null;
					String bpinpaisuoju = "pinpai2";
					dquotesuoxin = dataQuoteBo.findDataQuoteByBtype(
							QuerysuoxincheckName, bpinpaisuoju);
					System.out.println("根据用户选择的锁具查询字段查询到这个锁具的对应品牌价格为:"
							+ dquotesuoxin.getBtype()
							+ dquotesuoxin.getBpriceone());
					String calsuoxinprice = dquotesuoxin.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calsuoxinprice2 = Integer.parseInt(calsuoxinprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calsuoxinprice2;

					System.out.println(" 配件 锁芯 结束  ，价格为" + calsuoxinprice2);
				}
				entity.setSuoxName(pjsuoxinName);

				// 判断其他配件的选择 门铃
				String pjmenlingName = null;
				String QuerymenlingcheckName = null;
				double calmenlingprice2 = 0;
				if (o34.equals("0") || o34 == "0") {
					System.out.println("----这个o34 ==0");
				} else if (o34 != null && !o34.equals("") && o34 != "0") {
					System.out.println("----这个o34 " + o34);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o34));
					pjmenlingName = depart.getName();
					QuerymenlingcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （门铃）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （门铃）的名称" + pjmenlingName
							+ "checkName=" + QuerymenlingcheckName);
					// LZ去报价系统里查询：
					DataQuote dquotemenling = null;
					String bpinpaimenling = "pinpai2";
					dquotemenling = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimenling);
					System.out.println("根据用户选择的 (门铃)查询字段查询到这个 (门铃)的对应品牌价格为:"
							+ dquotemenling.getBtype()
							+ dquotemenling.getBpriceone());
					String calmenlingprice = dquotemenling.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calmenlingprice2 = Integer.parseInt(calmenlingprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;

					calEndALlproduct = calEndALlproduct + calmenlingprice2;
					System.out.println(" 配件 (门铃) 结束  ，价格为" + calmenlingprice2);
				}
				entity.setMenlingName(pjmenlingName);

				// 判断其他配件的选择 猫眼
				String pjmaoyanName = null;
				String QuerymaoyancheckName = null;
				double calmaoyanprice2 = 0;
				if (o35.equals("0") || o35 == "0") {
					System.out.println("----这个o35 ==0");
				} else if (o34 != null && !o35.equals("") && o35 != "0") {
					System.out.println("----这个o35 " + o35);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o35));
					pjmaoyanName = depart.getName();
					QuerymaoyancheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （猫眼）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （猫眼）的名称" + pjmaoyanName
							+ "checkName=" + QuerymaoyancheckName);
					// LZ去报价系统里查询：
					DataQuote dquotemaoyan = null;
					String bpinpaimaoyan = "pinpai2";
					dquotemaoyan = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (猫眼)查询字段查询到这个 (猫眼)的对应品牌价格为:"
							+ dquotemaoyan.getBtype()
							+ dquotemaoyan.getBpriceone());
					String calmaoyanprice = dquotemaoyan.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calmaoyanprice2 = Integer.parseInt(calmaoyanprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;

					calEndALlproduct = calEndALlproduct + calmaoyanprice2;
					System.out.println(" 配件 (猫眼) 结束  ，价格为" + calmaoyanprice2);
				}
				entity.setMaoyanName(pjmaoyanName);

				// 判断其他配件的选择 下档
				String pjxiadangName = null;
				String QueryxiadangcheckName = null;
				double calxiadangprice2 = 0;
				if (o36.equals("0") || o36 == "0") {
					System.out.println("----这个o36 ==0");
				} else if (o36 != null && !o36.equals("") && o36 != "0") {
					System.out.println("----这个o36 " + o36);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o36));
					pjxiadangName = depart.getName();
					QueryxiadangcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （下档）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （下档）的名称" + pjxiadangName
							+ "checkName=" + QueryxiadangcheckName);
					// LZ去报价系统里查询：
					DataQuote dquotexiadang = null;
					String bpinpaimaoyan = "pinpai2";
					dquotexiadang = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (下档)查询字段查询到这个 (下档)的对应品牌价格为:"
							+ dquotexiadang.getBtype()
							+ dquotexiadang.getBpriceone());
					String calxiadangprice = dquotexiadang.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					calxiadangprice2 = Integer.parseInt(calxiadangprice);
					// double callzprice3=callzprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calxiadangprice2;
					System.out.println(" 配件 (下档) 结束  ，价格为" + calxiadangprice2);
				}
				entity.setXiadangName(pjxiadangName);

				// 判断其他配件的选择 拉手 * 2
				String pjlashouName = null;
				String QuerylashoucheckName = null;
				double callashouprice3 = 0;
				if (o37.equals("0") || o37 == "0") {
					System.out.println("----这个o37 ==0");
				} else if (o37 != null && !o37.equals("") && o37 != "0") {
					System.out.println("----这个o37 " + o37);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o37));
					pjlashouName = depart.getName();
					QuerylashoucheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （拉手）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （拉手）的名称" + pjlashouName
							+ "checkName=" + QuerylashoucheckName);
					// LZ去报价系统里查询：
					DataQuote dquotelashou = null;
					String bpinpaimaoyan = "pinpai2";
					dquotelashou = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (拉手)查询字段查询到这个 (拉手)的对应品牌价格为:"
							+ dquotelashou.getBtype()
							+ dquotelashou.getBpriceone());
					String callashouprice = dquotelashou.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					double callashouprice2 = Integer.parseInt(callashouprice);
					callashouprice3 = callashouprice2 * 2; // 立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + callashouprice3;
					System.out.println(" 配件 (拉手) 结束  ，价格为" + callashouprice3);
				}
				entity.setLashouName(pjlashouName);
				// 判断其他配件的选择 铰链 * 2
				String pjjiaolianName = null;
				String QueryjiaoliancheckName = null;
				double caljiaolianprice3 = 0;
				if (o38.equals("0") || o38 == "0") {
					System.out.println("----这个o38 ==0");
				} else if (o38 != null && !o38.equals("") && o38 != "0") {
					System.out.println("----这个o38 " + o38);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o38));
					pjjiaolianName = depart.getName();
					QueryjiaoliancheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																		// （铰链）
																		// ,再加上这个品牌的名称
																		// 去，报价系统里面查询
					System.out.println("配件 （铰链）的名称" + pjjiaolianName
							+ "checkName=" + QueryjiaoliancheckName);
					// LZ去报价系统里查询：
					DataQuote dquotejiaolian = null;
					String bpinpaimaoyan = "pinpai2";
					dquotejiaolian = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (铰链)查询字段查询到这个 (铰链)的对应品牌价格为:"
							+ dquotejiaolian.getBtype()
							+ dquotejiaolian.getBpriceone());
					String caljiaolianprice = dquotejiaolian.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					double caljiaolianprice2 = Integer
							.parseInt(caljiaolianprice);
					// caljiaolianprice3=caljiaolianprice2*2; //立柱选择的是每对 *2 ;

					calEndALlproduct = calEndALlproduct + caljiaolianprice2;
					System.out.println(" 配件 (铰链) 结束  ，价格为" + caljiaolianprice2);
				}
				entity.setJiaolianName(pjjiaolianName);

				// 判断其他配件的选择 闭门器
				String pjbimenqiName = null;
				String QuerybimenqicheckName = null;
				double calbimenqiprice3 = 0;
				if (o42.equals("0") || o42 == "0") {
					System.out.println("----这个o42 ==0");
				} else if (o42 != null && !o42.equals("") && o42 != "0") {
					System.out.println("----这个o42 " + o42);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o42));
					pjbimenqiName = depart.getName();
					QuerybimenqicheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （闭门器）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("配件 （闭门器）的名称" + pjbimenqiName
							+ "checkName=" + QuerybimenqicheckName);
					// LZ去报价系统里查询：
					DataQuote dquotebimenqi = null;
					String bpinpaimaoyan = "pinpai2";
					dquotebimenqi = dataQuoteBo.findDataQuoteByBtype(
							QuerymenlingcheckName, bpinpaimaoyan);
					System.out.println("根据用户选择的 (闭门器)查询字段查询到这个 (闭门器)的对应品牌价格为:"
							+ dquotebimenqi.getBtype()
							+ dquotebimenqi.getBpriceone());
					String calbimenqiprice = dquotebimenqi.getBdanjiac(); // 这是用户选择锁芯的价格
					// double callzprice=Double.parseDouble(callzprice);
					double calbimenqiprice2 = Integer.parseInt(calbimenqiprice);
					// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calbimenqiprice2;
					System.out.println(" 配件 (闭门器) 结束  ，价格为" + calbimenqiprice2);
				}
				entity.setBimenqiName(pjbimenqiName);
				// 判断 材质 cz 厚度 * 每平方的价格

				String czhouduName = null;
				String QueryczcheckName = null;
				double calbimenqiprice2 = 0;
				if (o27.equals("0") || o27 == "0") {
					System.out.println("----这个o27 ==0");
				} else if (o27 != null && !o27.equals("") && o27 != "0") {
					System.out.println("----这个o27 " + o27);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o27));
					czhouduName = depart.getName();
					QueryczcheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																// （闭门器）
																// ,再加上这个品牌的名称
																// 去，报价系统里面查询
					System.out.println("材质（厚度）的名称" + czhouduName + "checkName="
							+ QueryczcheckName);
					// LZ去报价系统里查询：
					DataQuote dquotehoudu = null;
					String bpinpaihoudu = "pinpai2";
					// dquotehoudu=dataQuoteBo.findDataQuoteByBtype(QueryczcheckName,bpinpaihoudu);
					// System.out.println("根据用户选择的 (厚度)查询字段查询到这个 (厚度)的对应品牌价格为:"+dquotehoudu.getBtype()+dquotehoudu.getBpriceone());
					// String calbimenqiprice=dquotehoudu.getBdanjiac(); //
					// 这是用户选择 这个门厚度，每平方的价格
					// double callzprice=Double.parseDouble(callzprice);
					// calbimenqiprice2 = Integer.parseInt(calbimenqiprice);
					// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
					calEndALlproduct = calEndALlproduct + calbimenqiprice2;
					System.out.println("  (厚度) 结束  ，价格为" + calbimenqiprice2);
				}
				entity.setHoudu(czhouduName);

				// 板材 ，材质
				String czcaizhiName = null;
				String QuerycaizhicheckName = null;
				double calcaizhiqiprice3 = 0;
				if (o28.equals("0") || o28 == "0") {
					System.out.println("----这个o28 ==0");
				} else if (o28 != null && !o28.equals("") && o28 != "0") {
					System.out.println("----这个o28 " + o28);
					Department2 depart = departmentBo
							.findDataDepartment2ById(Long.valueOf(o28));
					czcaizhiName = depart.getName();
					QuerycaizhicheckName = depart.getInputname2(); // 确定这个用户选择是是什么
																	// （材质）
																	// ,再加上这个品牌的名称
																	// 去，报价系统里面查询
					System.out.println("材质（材质）的名称" + czcaizhiName
							+ "checkName=" + QuerycaizhicheckName);
					// LZ去报价系统里查询：
					// DataQuote dquotecaizhi=null;
					// String bpinpaicaizhi="pinpai2";
					// dquotecaizhi=dataQuoteBo.findDataQuoteByBtype(QuerycaizhicheckName,bpinpaicaizhi);
					// System.out.println("根据用户选择的 (材质)查询字段查询到这个 (材质)的对应品牌价格为:"+dquotecaizhi.getBtype()+dquotecaizhi.getBpriceone());
					// String calcaizhiqiprice=dquotecaizhi.getBdanjiac(); //
					// 这是用户选择 这个门材质，每平方的价格
					// double callzprice=Double.parseDouble(callzprice);
					// calcaizhiqiprice3 = Integer.parseInt(calcaizhiqiprice);
					// calbimenqiprice3=calbimenqiprice2*2; //立柱选择的是每对 *2 ;
					// System.out.println("  (材质) 结束  ，价格为"+calcaizhiqiprice3);
				}
				entity.setCaizhi(czcaizhiName);

				System.out.println(calEndALlproduct + "最终获得的价格为");

				System.out.println(entity.getPanelname() + "最终获得的  色板那么为");

			}
		}//

		JSONArray resultleft3 = new JSONArray();
		JSONObject ret3 = new JSONObject();
		long id = 1;

		double sp2 = calEndALlproduct;
		int sp = (int) sp2;
		int Mtangshu = 0;
		int tp = sp;
		// o13="1";

		if ("".equals(o13) || o13 == null) {
			Mtangshu = 1;
			tp = sp * Mtangshu;
		} else {
			Mtangshu = Integer.parseInt(o13);
			tp = sp * Mtangshu;
		}
		resultleft3.add(buildjsonnumber(id, sp, Mtangshu, tp));
		String imgStr = o41;
		System.out.println(""+imgStr+"......."+o41);
		String imgid = CreateGUID.createGuId();
		// session.getServletContext().

		String filePath = request.getServletPath();
		String filePath2 = request.getSession().getServletContext()
				.getRealPath(request.getRequestURI());

		String filePath3 = request.getSession().getServletContext()
				.getRealPath("//");
		System.out.println(filePath + filePath2 + filePath3);
		String photoName=CreateGUID.createGuId() + ".png";
		String imgFile = filePath3+"\\home\\resource\\imageforcart\\" + photoName;
		
		// 转换图片
		ImageUtil.generateImage(imgStr, imgFile);
		resultleft3.add(buildjsonimg(imgid, imgFile));
		PrintWriter writer = response.getWriter();

		String pathone = request.getServletPath();
		String pathone2 = request.getContextPath();
		System.out.println("。。。。。。。。。。" + pathone + pathone2);

		System.out.println("加入购物车后 o13" + o13);
		// entity.setCartor("y");
		if ("".equals(o13) || o13 == null) {
			o13 = "1";
			System.out.println("==null  ");
			entity.setTcount(o13);
		}

		Person loginPerson = loginInfo.getLoginPerson();
		String org = loginPerson.getOrganizationId();
		entity.setCartunitprice(String.valueOf(sp)); // 单价
		entity.setPhoto(photoName);
		entity.setCartsize(o13); // 数量
	
		entity.setTcount(o13);
		entity.setStatus(CARTIN); // 加入到购物车中的状态
		System.out.println("----输出的数量为" + 013);
		entity.setCarttotalprice(String.valueOf(tp)); // 总价
		
		

		// if(entity.getPanelname()!=null||!entity.getPanelname().equals("")){
		if (entity.getPanelname() == null) {
			entity.setCartor("N");
		} else {
			entity.setCartor("y");
			entity.setOrg(org);
			commodityUserBo.save(entity);
		}

		model.addAttribute("userInfo", loginPerson);

		return "/home/cart/cart_common";
		// return null;

	}

	public String addCart(Model model, HttpServletRequest request,
			HttpServletResponse response, Long goodId,HttpSession session) {

		try {
			// 1、获得页面数据
			// Long goodsId=new Long(request.getParameter("goodsId"));

			// 2、根据商品ID查询商品
			List<Cart> goods = cartBo.findCartById(goodId);
			Cart cart = null;
			if (goods != null) {
				for (Iterator iterator = goods.iterator(); iterator.hasNext();) {
					cart = (Cart) iterator.next();
					// 3、把商品加入到购物车内
					cart.setProName(cart.getProName());
					String brandname=cart.getBrand();
					if(!"".equals(brandname) || brandname!= null){
						Object pinname=session.getAttribute("brandName");
						 brandname=(String) pinname;
					}
					cart.setBrand(brandname);
					cart.setDoorstylename(cart.getDoorstylename());
					cart.setColour(cart.getColour());
					cart.setMenheight(cart.getMenheight());
					cart.setSizekd(cart.getSizekd());
					cart.setSize(cart.getSize());
					cart.setType(cart.getType());
					cart.setOpento(cart.getOpento());
					cart.setTcount(cart.getTcount());
					cart.setRemark(cart.getRemark());
System.out.println("这是。。。。。one");
				}
				// 4、把购物车放到session范围中
				// 从session获得购物车
				session = request.getSession();
				List<Cart> cartList = (List<Cart>) session
						.getAttribute("cartList");
				/*
				 * 判断购物车是否为空,如果为空(说明第一次购买),需要新建一个购物车列表。 如果不为空，在原来基础上添加一个购物车
				 */
				cartList = (cartList == null) ? new ArrayList<Cart>()
						: cartList;

				// 判断是否购买了同一种商品，如果是商品数量加一.

				boolean isbuy = false;
				for (int i = 0; i < cartList.size(); i++) {
					Cart carts = cartList.get(i);
					Long cardGoodsId = carts.getId();
					// 说明已经购买了同一个商品
					if (cardGoodsId == goodId) {
						carts.setNumber(carts.getNumber() + 1);
						isbuy = true;
						break;
					}
				}

				if (!isbuy) {
					cartList.add(cart);
				}
			}

		} catch (Throwable e) {

			e.printStackTrace();
		}

		// 5、响应
		return "/home/cart/buyer_cart";

	}

	public String addCartOrder(Model model, HttpServletRequest request,
			HttpServletResponse response, Long goodId,HttpSession session) {

		try {
			// 1、获得页面数据
			// Long goodsId=new Long(request.getParameter("goodsId"));

			// 2、根据商品ID查询商品
			List<Cart> goods = cartBo.findCartById(goodId);
			Cart cart = null;
			if (goods != null) {
				for (Iterator iterator = goods.iterator(); iterator.hasNext();) {
					cart = (Cart) iterator.next();
					// 3、把商品加入到购物车内
					
					String brandname=cart.getBrand();
					if(!"".equals(brandname) || brandname!= null){
						Object pinname=session.getAttribute("brandName");
						 brandname=(String) pinname;
					}
					cart.setBrand(brandname);
					cart.setProName(cart.getProName());
					//cart.setBrand(cart.getBrand());
					cart.setDoorstylename(cart.getDoorstylename());
					cart.setColour(cart.getColour());
					cart.setMenheight(cart.getMenheight());
					cart.setSizekd(cart.getSizekd());
					cart.setSize(cart.getSize());
					cart.setType(cart.getType());
					cart.setOpento(cart.getOpento());
					cart.setRemark(cart.getRemark());
					System.out.println("这是。。。。。two");
				}
				// 4、把购物车放到session范围中
				// 从session获得购物车
				 session = request.getSession();
				List<Cart> cartList = (List<Cart>) session
						.getAttribute("cartList");
				/*
				 * 判断购物车是否为空,如果为空(说明第一次购买),需要新建一个购物车列表。 如果不为空，在原来基础上添加一个购物车
				 */
				cartList = (cartList == null) ? new ArrayList<Cart>()
						: cartList;

				// 判断是否购买了同一种商品，如果是商品数量加一.

				boolean isbuy = false;
				for (int i = 0; i < cartList.size(); i++) {
					Cart carts = cartList.get(i);
					Long cardGoodsId = carts.getId();
					// 说明已经购买了同一个商品
					if (cardGoodsId == goodId) {
						carts.setNumber(carts.getNumber() + 1);
						isbuy = true;
						break;
					}
				}

				if (!isbuy) {
					cartList.add(cart);
				}
			}

		} catch (Throwable e) {

			e.printStackTrace();
		}

		// 5、响应
		return "/home/order/check_orders";

	}

	private JSONObject buildjsonimg(String id, String img) {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("img", img);

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

	@RequestMapping(value = "/pages/company/CommodityUserOperate.do")
	public String CommodityUserOperate(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			CommodityUser entity) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String operation = request.getParameter("operation");
		PrintWriter writer = response.getWriter();
		if (ADD.equals(operation)) {
			// 记录 增加数据的用户
			entity.setStatus("订单");
			entity.setDingzhiprice("1242141");
			entity.setCartor("y");
			commodityUserBo.save(entity);
			Long commid = entity.getId();

			model.addAttribute("goodsId", commid);

			writer.write("true" + commid);
		} else if (UPDATE.equals(operation)) {
			commodityUserBo.update(entity);
			writer.write("true");
		} else if (DELETE.equals(operation)) {
			commodityUserBo.delete(entity);
			writer.write("true");
		}
		writer.flush();
		return null;
	}

	/**
	 * swk
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/home/cart/gobuycat.do")
	public String gocat(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse response, CommodityUser entity) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		Person loginPerson = loginInfo.getLoginPerson();
		System.out.println("..." + loginPerson.getId() + loginPerson.getName());
		model.addAttribute("userInfo", loginPerson);
		return "/home/cart/cart_common";
	}

	@RequestMapping(value = "/home/cart/getCartdetail.do")
	public void getCartdetail(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			CommodityUser entity,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String userId = request.getParameter("userId");
		String brandName=request.getParameter("brandName");
		
		Long user_Id = new Long(userId);
		JSONObject rest = new JSONObject();
		String status = "cartin";
		try {
			// 1、获得页面数据
			if (userId != null) {
				List<CommodityUser> lists = commodityUserBo.findCommodityUserByidstatus(user_Id, status,brandName);
				
				request.getSession().setAttribute("cartList", lists);
				rest.put("result", "1");
				response.getWriter().write(rest.toString());
			}
		} catch (Throwable e) {
			rest.put("result", "0");
			try {
				response.getWriter().write(rest.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		// return "/home/cart/buyer_cart";
	}

	@RequestMapping(value = "/home/cart/getCartdetail2.do")
	public void getCartdetail2(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			CommodityUser entity,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String userId = request.getParameter("userId");
		String brandName2=request.getParameter("brandName");
		
		Object brandName3=session.getAttribute("brandName");
		  String brandName=(String)brandName3;
		Long user_Id = null;
		if (userId != null || !"".equals(userId)) {
			user_Id = new Long(userId);
		}
		System.out.println("userId......输出为" + userId);
		if (userId == null || "".equals(userId)) {
			Person loginPerson = loginInfo.getLoginPerson();
			System.out.println("..." + loginPerson.getId()
					+ loginPerson.getName());
			// model.addAttribute("userInfo", loginPerson);
			user_Id = loginPerson.getId();
		}
		// else{
		//			
		// user_Id=new Long(userId);
		// }
		JSONObject rest = new JSONObject();
		System.out.println("user_id" + user_Id);
		try {
			// 1、获得页面数据
			if (userId != null) {
				String status = "cartorder";
				List<CommodityUser> lists = commodityUserBo
						.findCommodityUserByidstatus(user_Id,status,brandName);
				for (int i = 0; i < lists.size(); i++) {
					System.out.println(lists.get(i).getName());
				}
				request.getSession().setAttribute("cartList", lists);
				rest.put("result", "1");
				response.getWriter().write(rest.toString());
			}
		} catch (Throwable e) {
			rest.put("result", "0");
			try {
				response.getWriter().write(rest.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		// return "/home/cart/buyer_cart";
	}

}
