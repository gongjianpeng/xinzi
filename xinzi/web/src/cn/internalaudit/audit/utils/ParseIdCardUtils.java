package cn.internalaudit.audit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.internalaudit.audit.exception.input.OperationException;
/**
 * 功能： 解析身份证号码的生日，性别。出生地。
 * @author 杨毅
 *
 */
public class ParseIdCardUtils {
	
	// 每位加权因子
	private int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	// 第18位校检码
	private String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5",
			"4", "3", "2" };
	
	/**
	 * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
	 * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
	 * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
	 * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
	 * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
	 * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
	 */
	protected String codeAndCity[][] = { { "11", "北京" }, { "12", "天津" },
			{ "13", "河北" }, { "14", "山西" }, { "15", "内蒙古" }, { "21", "辽宁" },
			{ "22", "吉林" }, { "23", "黑龙江" }, { "31", "上海" }, { "32", "江苏" },
			{ "33", "浙江" }, { "34", "安徽" }, { "35", "福建" }, { "36", "江西" },
			{ "37", "山东" }, { "41", "河南" }, { "42", "湖北" }, { "43", "湖南" },
			{ "44", "广东" }, { "45", "广西" }, { "46", "海南" }, { "50", "重庆" },
			{ "51", "四川" }, { "52", "贵州" }, { "53", "云南" }, { "54", "西藏" },
			{ "61", "陕西" }, { "62", "甘肃" }, { "63", "青海" }, { "64", "宁夏" },
			{ "65", "新疆" }, { "71", "台湾" }, { "81", "香港" }, { "82", "澳门" },
			{ "91", "国外" } };

		private String cityCode[] = { "11", "12", "13", "14", "15", "21", "22",
			"23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
			"44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
			"64", "65", "71", "81", "82", "91" };
	/**
	 * 将15位的身份证转成18位身份证
	 * 
	 * @param idcard
	 * @return
	 */
	public String convertId18bitBy15bit(String idcard) {
		String idcard17 = null;
		// 非15位身份证
		if (idcard.length() != 15) {
			return null;
		}
     
		if (isDigital(idcard)) {
			// 获取出生年月日
			String birthday = idcard.substring(6, 12);
			Date birthdate = null;
			try {
				birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cday = Calendar.getInstance();
			cday.setTime(birthdate);
			String year = String.valueOf(cday.get(Calendar.YEAR));

			idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

			char c[] = idcard17.toCharArray();
			String checkCode = "";

			if (null != c) {
				int bit[] = new int[idcard17.length()];

				// 将字符数组转为整型数组
				bit = converCharToInt(c);
				int sum17 = 0;
				sum17 = getPowerSum(bit);

				// 获取和值与11取模得到余数进行校验码
				checkCode = getCheckCodeBySum(sum17);
				// 获取不到校验位
				if (null == checkCode) {
					return null;
				}

				// 将前17位与第18位校验码拼接
				idcard17 += checkCode;
			}
		} else { // 身份证包含数字
			return null;
		}
		return idcard17;
	}
	
	
	/**
	 * 数字验证
	 * 
	 * @param str
	 * @return
	 */
	public boolean isDigital(String str) {
		return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
	}
	
	/**
	 * 将字符数组转为整型数组
	 * 
	 * @param c
	 * @return
	 * @throws NumberFormatException
	 */
	public int[] converCharToInt(char[] c) throws NumberFormatException {
		int[] a = new int[c.length];
		int k = 0;
		for (char temp : c) {
			a[k++] = Integer.parseInt(String.valueOf(temp));
		}
		return a;
	}
	
	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param bit
	 * @return
	 */
	public int getPowerSum(int[] bit) {

		int sum = 0;

		if (power.length != bit.length) {
			return sum;
		}

		for (int i = 0; i < bit.length; i++) {
			for (int j = 0; j < power.length; j++) {
				if (i == j) {
					sum = sum + bit[i] * power[j];
				}
			}
		}
		return sum;
	}
	
	/**
	 * 将和值与11取模得到余数进行校验码判断
	 * 
	 * @param checkCode
	 * @param sum17
	 * @return 校验位
	 */
	public String getCheckCodeBySum(int sum17) {
		String checkCode = null;
		switch (sum17 % 11) {
		case 10:
			checkCode = "2";
			break;
		case 9:
			checkCode = "3";
			break;
		case 8:
			checkCode = "4";
			break;
		case 7:
			checkCode = "5";
			break;
		case 6:
			checkCode = "6";
			break;
		case 5:
			checkCode = "7";
			break;
		case 4:
			checkCode = "8";
			break;
		case 3:
			checkCode = "9";
			break;
		case 2:
			checkCode = "x";
			break;
		case 1:
			checkCode = "0";
			break;
		case 0:
			checkCode = "1";
			break;
		}
		return checkCode;
	}
	/**
	 * 功能：解析身份证中的生日信息
	 * @return 生日日期
	 * @throws OperationException 
	 */
	public Date parseID2BirthDay(String cardId) throws OperationException{
		
		//获取身份证信息中生日数字代码
		String birthday = cardId.substring(6, 14);
		String year = birthday.substring(0,4);
		String month = birthday.substring(4,6);
		String day = birthday.substring(6,8);
		String tempDate = year +"/"+month+"/"+day;
		
		//声明日期对象
		Date birthdate = null;
		
		
		try{
			//将身份证中的时间格式转化为yyyy/mm/dd
			birthdate = new SimpleDateFormat("yyyy/MM/dd").parse(tempDate);
			
		}catch(ParseException e ){
			throw new OperationException("录单中解析日期出错!");
		}
		
		return birthdate;
	}
	
	/**
	 * 功能：解析身份证中的生日信息获取客户当前年龄
	 * @return 客户当前年龄(与当前的系统时间)
	 * @throws OperationException 
	 */
	public String parseID2Age(String cardId) throws OperationException{
		String age = "";
		Date birthDate = parseID2BirthDay(cardId);
		try{
		int reduceDay = Utils.days(birthDate, new Date());
		int ageTemp = Math.round(reduceDay / 365);
		age = String.valueOf(ageTemp);
		}catch(Exception e){
			throw new OperationException("录单中解析年龄！");
		}
		return age;
	}
	/**
	 * 功能：解析身份证中信息获取客户性别
	 * @return 客户性别
	 * @throws OperationException 
	 */
	public String  parseID2Sex(String cardId) throws OperationException{
		
		String sex = "";
		try{
		//获取身份证信息中性别数字代码
		int sexNumber =Integer.valueOf(cardId.substring(16,17));
		
		if(sexNumber % 2 == 0){
			sex = "女" ;
		}else{
			sex = "男";
		}
		
		}catch(Exception e){
			throw new OperationException("录单中解析身份证信息中的性别信息");
		}
		return sex;
	}
	

	/**
	 * 功能：解析身份证中信息获取客户出生地
	 * @return 客户出生地
	 * @throws OperationException 
	 */
	public String  parseID2BornPlace(String cardId) throws OperationException{
		
		String city ="";
		try{
			//获取身份证信息中客户出生地数字代码
			int provinceCode =Integer.valueOf(cardId.substring(0, 2));

					for(int i =0;i<codeAndCity.length;i++){
						String codeCity[] = codeAndCity[i];
						for(int j = 0 ;j<codeCity.length;j++){
							if(j%2==0){
								String code = codeCity[j];
								if(Integer.valueOf(code)==Integer.valueOf(provinceCode)){
									  city = codeCity[j+1];
									  break;
								 }  
							}
						}
					}
		}catch(Exception e){
			throw new OperationException("录单中解析身份证信息中的客户出生地出错！");
		
		}
		return city;
	}
	
//	public static void main(String[] args) throws Exception {
//       
//        String idcard18 = "51072719840407101x";
//        ParseIdCardUtils parseCardUtils = new ParseIdCardUtils();
//    
////       idcard15 = parseCardUtils.convertId18bitBy15bit(idcard15);
////       System.out.println(idcard15);
//       System.out.println(parseCardUtils.parseID2BirthDay(idcard18));
//       System.out.println(parseCardUtils.parseID2Age(idcard18));
//       System.out.println(parseCardUtils.parseID2Sex(idcard18));
//       System.out.println(parseCardUtils.parseID2BornPlace(idcard18));
//    
//
//}
	
	
}
