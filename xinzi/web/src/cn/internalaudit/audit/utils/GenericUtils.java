package cn.internalaudit.audit.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.operamasks.faces.developer.util.FacesUtils;
import org.operamasks.faces.util.Flash;


public class GenericUtils {

	@SuppressWarnings("unchecked")
	public static final <E> Class<E> getParameterClass( Class genericClass )
    {
        return getParameterClass( genericClass, 0 );
    }

    @SuppressWarnings("unchecked")
	public static final <E> Class<E> getParameterClass( Class genericClass, int parameterIndex )
    {
        Type genType = genericClass.getGenericSuperclass();

        if ( genType instanceof ParameterizedType )
        {
            Type[] params = ( (ParameterizedType) genType ).getActualTypeArguments();

            if ( ( params != null ) && ( params.length >= parameterIndex ) )
            {
                return (Class<E>) params[parameterIndex];
            }
        }
        return null;
   
    }
    /*********
     * 获取页面参数值
     * @param param_name 参数名
     * @return 参数值
     */
	@SuppressWarnings("static-access")
	public static String getParamValue(String param_name) {
		javax.faces.context.FacesContext ctx = javax.faces.context.FacesContext
				.getCurrentInstance();   
		java.util.Map<String, String> appMap = ctx.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		return appMap.get(param_name);
	}
	
	public static String getContext(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) facesContext
				.getExternalContext().getContext();
	    return context.getContextPath();	    
	}
	/*********
	 * 获取SessionMap对像
	 * @return SessionMap对像
	 */
	public static Flash getSessionMap(){
		return FacesUtils.getFlash();		
	}
	
}
