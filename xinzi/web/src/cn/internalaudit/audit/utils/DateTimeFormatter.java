package cn.internalaudit.audit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.operamasks.faces.annotation.DefineConverter;

@DefineConverter(id = "dateTimeFormatter")
public class DateTimeFormatter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		Object obj = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			if(!"".equals(value))
				obj = df.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if (value instanceof Date) {
			SimpleDateFormat formatter = (SimpleDateFormat) SimpleDateFormat
					.getInstance();
			formatter.applyPattern("yyyy-MM-dd HH:mm");
			return formatter.format(value);
		}
		return value == null ? "" : value.toString();
	}

}
