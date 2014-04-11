package cn.internalaudit.audit.utils;

import java.io.Serializable;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.component.grid.CheckboxSelectionModel;
/**************
 * 表格DataGrid的行选择模式定义
 * @author zhangyong
 *
 */
@ManagedBean(name = "gridSelectModel", scope = ManagedBeanScope.REQUEST)
public class GridSelectModel implements Serializable {
	@ManagedProperty
	private CheckboxSelectionModel checkbox = new CheckboxSelectionModel();
 
}
