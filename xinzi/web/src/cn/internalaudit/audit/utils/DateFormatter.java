package cn.internalaudit.audit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;

import org.operamasks.faces.annotation.Convert;
import org.operamasks.faces.annotation.DefineConverter;
import org.operamasks.faces.annotation.Format;
import org.operamasks.faces.component.layout.UIFacelet;
import org.operamasks.faces.convert.DateTimeConverter;

@DefineConverter(id = "outputDateFormatter")
public class DateFormatter {

	@Format
	public String format(Object value) {
		if (value instanceof Date) {
			SimpleDateFormat formatter = (SimpleDateFormat) SimpleDateFormat
					.getInstance();
			formatter.applyPattern("yyyy-MM-dd");
			return formatter.format(value);
		}
		return value == null ? "" : value.toString();
	}

	@Convert
	public Object convert(String value) {
		FacesContext context = FacesContext.getCurrentInstance();
		DateTimeConverter converter = (DateTimeConverter) context
				.getApplication().createConverter(
						DateTimeConverter.CONVERTER_ID);
		converter.setPattern("yyyy-MM-dd");
		return converter.getAsObject(context, new UIFacelet(), value);
	}

}
