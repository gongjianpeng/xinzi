package cn.internalaudit.audit.utils;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.operamasks.faces.annotation.DefineConverter;
import org.operamasks.faces.validator.CommonRegexpPatterns;

@DefineConverter(id = "dollarFormatter")
public class DollarConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}
		value = value.replace(",", "").replace("$", "");
		try {
			BigDecimal dec = new BigDecimal(value);
			return dec;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "转换错误",
					"转换出错，请输入合法的数字（可使用$前缀与,分隔）"), e);
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		String numStr = value.toString();
		if(numStr.indexOf(".")!=-1)
		numStr = numStr.substring(0, numStr.indexOf("."));
		if (numStr.matches(CommonRegexpPatterns.INTEGER_NUMBER)) {
			numStr = String.valueOf(value);
			numStr = numStr.replace(",", "");
			StringBuffer sb = new StringBuffer();
			for (int pos = numStr.length() - 1, i = 1; pos >= 0; pos--, i++) {
				sb.insert(0, numStr.charAt(pos));
				if (i % 3 == 0 && pos != 0
						&& !(pos == 1 && numStr.charAt(0) == '-')) {
					sb.insert(0, ',');
				}
			}
			/*if (sb.charAt(0) == '-') {
				sb.insert(1, '$');
			} else {
				sb.insert(0, '$');
			}*/
			return sb.toString();
		} else {
			throw new ConverterException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "转换错误", "转换出错，模型数据必须是整数。"));
		}
	}
	

}
