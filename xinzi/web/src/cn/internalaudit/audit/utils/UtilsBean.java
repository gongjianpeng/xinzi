package cn.internalaudit.audit.utils;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.component.grid.CheckboxSelectionModel;

@ManagedBean(name = "utilsBean", scope = ManagedBeanScope.REQUEST)
public class UtilsBean implements Serializable {
	/***************
	 * 应用程 序上下文
	 */
	@ManagedProperty
	private String context ;

	public String getContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) facesContext
				.getExternalContext().getContext();
	    return context.getContextPath();		
	}
	/********
	 * 表格选择模型，复选框
	 */
	@ManagedProperty
	private CheckboxSelectionModel checkbox = new CheckboxSelectionModel();	

}
