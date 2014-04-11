package cn.internalaudit.audit.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class Utils {

	public static final String dateFormatStr = "yyyy-MM-dd";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static <T> List<T> toList(T[] bsels) {
		if (Utils.nullOrEmpty(bsels)) {
			return Collections.emptyList();
		}
		List<T> l = new ArrayList<T>();
		for (T t : bsels) {
			l.add(t);
		}
		return l;
	}

	public static boolean nullOrEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean nullOrEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean nullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static boolean nullOrEmpty(String str, boolean trimSpace) {
		if (str == null) {
			return true;
		}
		return trimSpace ? str.trim().isEmpty() : str.isEmpty();
	}

	public static boolean nullOrEmpty(Object[] array) {
		return array == null || array.length <= 0;
	}

	public static boolean nullOrEmpty(byte[] array) {
		return array == null || array.length <= 0;
	}

	public static boolean nullOrEmpty(int[] array) {
		return array == null || array.length <= 0;
	}

	public static boolean nullOrEmpty(long[] array) {
		return array == null || array.length <= 0;
	}

	public static boolean equals(Integer src, Integer dest) {
		if (src == null && dest == null) {
			return true;
		}
		return src != null && src.equals(dest);
	}

	public static boolean equals(String src, String dest) {
		if (src == null && dest == null) {
			return true;
		}
		return src != null && src.equals(dest);
	}

	/********
	 * MD5加密
	 * 
	 * @param str
	 *            要加密的字符串
	 * @param slat
	 *            加密盐
	 * @return 加密后的MD5字符串
	 */
	public static String encodeMd5(String str, String slat) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		return md5.encodePassword(str, slat);
	}

	/********
	 * SHA加密
	 * 
	 * @param str
	 *            要加密的字符串
	 * @param slat
	 *            加密盐
	 * @return 加密后的SHA字符串
	 */

	public static String encodeSHA(String str, String slat) {
		ShaPasswordEncoder sha = new ShaPasswordEncoder();
		return sha.encodePassword(str, slat);
	}

	/***********
	 * 文件大小格式化显示
	 */
	public static final long KB = 1024;

	public static final long MB = KB * KB;

	public static final long GB = KB * MB;

	public static String fileSize(long fileSize) {
		if (fileSize <= 0) {
			return "";
		} else if (fileSize < MB) {
			BigDecimal b = new BigDecimal((double) fileSize / KB);
			return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "K";
		} else if (fileSize < GB) {
			BigDecimal b = new BigDecimal((double) fileSize / MB);
			return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "M";
		} else {
			BigDecimal b = new BigDecimal((double) fileSize / GB);
			return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "G";
		}
	}

	/*********
	 * 日期向后移动
	 * 
	 * @param date
	 *            日期基点
	 * @param field
	 *            要移运的日期 部份，如:Calendar.YEAR,Calendar.MONTH
	 * @param amount
	 *            移动的值
	 * @return
	 */
	public static Date dateAdd(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	public static Date addYears(Date date, int amount) {
		return dateAdd(date, Calendar.YEAR, amount);
	}

	public static Date addMonths(Date date, int amount) {
		return dateAdd(date, Calendar.MONTH, amount);
	}

	public static Date addDays(Date date, int amount) {
		return dateAdd(date, Calendar.DATE, amount);
	}

	public static Date addHours(Date date, int amount) {
		return dateAdd(date, Calendar.HOUR_OF_DAY, amount);
	}

	public static Date addMinutes(Date date, int amount) {
		return dateAdd(date, Calendar.MINUTE, amount);
	}

	public static Date addSecond(Date date, int amount) {
		return dateAdd(date, Calendar.SECOND, amount);
	}

	public static Date addMilliseconds(Date date, int amount) {
		return dateAdd(date, Calendar.MILLISECOND, amount);
	}

	public static Date onlyDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date today() {
		return onlyDate(new Date());
	}

	public static Date tomorrow() {
		return addDays(today(), 1);
	}

	public static Date yesterday() {
		return addDays(today(), -1);
	}

	public static boolean isToday(Date date) {
		return onlyDate(date).equals(today());
	}

	public static final long DayMilliseconds = 86400000;// 24 * 60 * 60 * 1000

	public static int days(Date startDate, Date endDate) {
		return (int) ((endDate.getTime() - startDate.getTime()) / DayMilliseconds) + 1;
	}

	public static int dayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/******
	 *判断指定日期是否是周六或周日
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		int dayOfWeek = Utils.dayOfWeek(date);
		return dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY;
	}

	public static int weekends(Date startDate, Date endDate) {
		int days = days(startDate, endDate);
		int m = days % 7;
		if (m == 0) {
			return 2 * days / 7;
		} else {
			int w = 2 * (days - m) / 7;
			int d = dayOfWeek(endDate);
			if (m >= d + 1) {
				w += 2;
			} else if (m == d || d == Calendar.SATURDAY) {
				w += 1;
			}
			return w;
		}
	}

	/***************************************************************************
	 * 二进制流转化成byte[]
	 */
	public static byte[] InputStreamToByte(InputStream iStrm)
			throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int ch;
		while ((ch = iStrm.read()) != -1) {
			stream.write(ch);
		}
		byte data[] = stream.toByteArray();
		stream.flush();
		stream.close();
		return data;
	}

	/***************************************************************************
	 * 解决汉字编码问题
	 * 
	 * @param str
	 * @return
	 */
	public static String toChinese(String str) {
		String strnew = null;
		if (str != null) {
			try {
				return URLEncoder.encode(URLDecoder.decode(str, "UTF-8"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return strnew;
	}
	public static String str2UTF8(String str){
		if(str != null){
		  try {
			return new String(str.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return null;
	}
	public static String UTF8ToISO(String str){
		if(str != null){
		  try {
			return new String(str.getBytes("utf-8"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return null;
	}
	/*******
	 * 得到适用于Sql语名like条件符合的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getSqlLikeString(String str) {
		if (str != null) {
			return "%" + str + "%";
		}
		return null;
	}

	/********
	 * 将指定数字格式化为长度为7位，不够时前面加0
	 * 
	 * @param number
	 * @return
	 */
	public static String numberToFormatLenth7(Long number) {
		String str = String.valueOf(number);
		String format = "";
		for (int i = 0; i < 7 - str.length(); i++) {
			format = format + "0";
		}
		return format + str;
	}

	/***************************************************************************
	 * 获得服务器真实路径
	 * 
	 * @param file_path
	 *            目标目录
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String getSavePath(String file_path, String fileName) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) facesContext
				.getExternalContext().getContext();
		String folder = context.getRealPath("/") + file_path;
		File dir = new File(folder);
		if (!dir.exists()) {
			dir.mkdir();
		}

		String path = context.getRealPath("/") + file_path + fileName;
		return path;
	}

	/**
	 * 获得服务器绝对路径
	 * 
	 * @return
	 */
	public static String getServerUtterlyPath() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) facesContext
				.getExternalContext().getContext();
		return context.getRealPath("/");
	}

	/**
	 * 获得工程根目录
	 * 
	 * @return
	 */
	public static String getRootPath() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) facesContext
				.getExternalContext().getContext();
		return context.getContextPath();
	}

	/**
	 * 将Object数组转换为String数组
	 * 
	 * @author robin
	 * @param obj
	 *            []
	 * @return
	 */
	public static String[] convertObjArrToStrArr(Object obj[]) {
		if (Utils.nullOrEmpty(obj)) {
			return null;
		}
		String[] str = new String[obj.length];
		for (int i = 0; i < obj.length; i++) {
			str[i] = (String) obj[i];
		}
		return str;
	}

	public static String toUTF8(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	/***
	 * 取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String fileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}
	/***
	 * 取文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String fileNameNotExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	public static void setHeader(HttpServletResponse response){
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
	}
	
	/**
     * 封装将json对象转换为java集合对象
     * 
     * @param <T>
     * @param clazz
     * @param jsons 
     * @return
     */
    public static <T> List<T> getJavaCollection(T clazz, String jsons) {
        List<T> objs=null;
        JSONArray jsonArray=(JSONArray)JSONSerializer.toJSON(jsons);
        if(jsonArray!=null){
            objs=new ArrayList<T>();
            List list=(List)JSONSerializer.toJava(jsonArray);
            for(Object o:list){
                JSONObject jsonObject=JSONObject.fromObject(o);
                T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }
}
