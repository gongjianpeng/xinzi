package cn.internalaudit.audit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.internalaudit.audit.exception.input.OperationException;
import cn.internalaudit.audit.exception.input.ValidateException;

/**
 * 功能：实现的是验证方法
 * @author 杨毅
 *
 */
public class PatternUtils {
	/**
	 * 功能：验证电子邮件验证器
	 * @param mail
	 * @return
	 * @throws OperationException
	 */
	public static void checkEmail(String mail) throws ValidateException{  
		   String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";  
		    Pattern   p   =   Pattern.compile(regex);  
		    Matcher   m   =   p.matcher(mail);  
		    boolean isFlag = m.find();
		    if(!isFlag){
		    	throw new ValidateException();
		    }
    } 
	/**
	 * 功能：验证身份证验证器
	 * @param idcard
	 *        身份证
	 * @throws OperationException
	 *         
	 */
	public static  void checkIdCard(String idcard) throws ValidateException{
		//创建一个身份证验证器
   	   ValidateIdCardUtil idcardValidator = new ValidateIdCardUtil();
   	   boolean isFlag = false;
   	   //验证身份证号码
       isFlag =idcardValidator.isValidatedAllIdcard(idcard);
       if(!isFlag){
    	  throw new ValidateException("您输入的身份证号码无效！");
       }
      
	}
	
//	public static void main(String[] args) throws Exception {
//		PatternUtils patterUtils = new PatternUtils();
//        System.out.println(patterUtils.checkEmail(mail));
//     }
}
