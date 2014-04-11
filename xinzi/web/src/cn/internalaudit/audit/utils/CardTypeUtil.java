package cn.internalaudit.audit.utils;



/**
 * 号码验证
 * @author robin
 *
 */
public class CardTypeUtil {
	/**
	 * 身份证
	 */
	public static final String ID_NUMBER = "ID_NUMBER"; 
	/**
	 * 申请号
	 */
	public static final String APP_NUMBER = "APP_NUMBER";
	/**
	 * 合同号
	 */
	public static final String PACT_NUMBER = "PACT_NUMBER"; 
	
	/**
	 * 验证字符串是否为身份证/合同号/申请号
	 * @param number : 身份证/合同号/申请号
	 * @return : ID_NUMBER/PACT_NUMBER/APP_NUMBER
	 */
	public static String checkCardType(String number){
		if(number.trim().equals("")){
			return "";
		}
		number = number.trim();
		IdcardValidator iv = new IdcardValidator();
		boolean  bool_idCard =false;
	
			bool_idCard = iv.isValidatedAllIdcard(number);
		
		if(bool_idCard){
			return CardTypeUtil.ID_NUMBER;
		}
		if(number.toUpperCase().startsWith("APP")){
			return CardTypeUtil.APP_NUMBER;
		}else{
			return CardTypeUtil.PACT_NUMBER;
		} 
	}
	
	/**
	 * 测试
	 * @param arg
	 */
	public static void main(String arg[]){
		String idcard15 = "513323860417001";
        String idcard18 = "510219198207175";
		IdcardValidator iv = new IdcardValidator();
		boolean flag = false;
		flag = iv.isValidatedAllIdcard(idcard18);
		System.out.println(flag);
	}
}
