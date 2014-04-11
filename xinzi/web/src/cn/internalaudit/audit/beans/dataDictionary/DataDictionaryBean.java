package cn.internalaudit.audit.beans.dataDictionary;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.Inject;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.annotation.SelectItems;
import org.operamasks.faces.component.form.impl.UICombo;
import org.operamasks.faces.component.grid.impl.UIDataGrid;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.user.ajax.PartialUpdateManager;
import org.operamasks.faces.user.ajax.UpdateLevel;

import cn.internalaudit.audit.beans.index.IndexBean;
import cn.internalaudit.audit.bo.IDataDictionaryBo;
import cn.internalaudit.audit.entitys.DataDictionary;

@ManagedBean(name = "pages.dataDictionary.dataDictionaryBean", scope = ManagedBeanScope.REQUEST)
public class DataDictionaryBean {
	@Inject("DataDictionaryBo")
	private IDataDictionaryBo dataDictionaryBo;
	@Bind
	private UIDataGrid dataGrid;
	@Bind
	@ManagedProperty
	private String code;
	@Bind
	private String name;
	@Bind
	@SaveState
	private String content;
	@Bind
	@SaveState
	private String type;
	@Bind
	private String remark;

	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;

	@Bind
	@ManagedProperty
	@SaveState
	private String dataTpye;
	@Bind
	private String postValue;
	@Bind
	private String industryValue;
	@Bind
	@ManagedProperty
	private String typeName;
	@Bind
	@ManagedProperty
	@SaveState
	private DataDictionary dataDictionary;
	@Bind
	private UIWindow dialog;

	@Bind
	@ManagedProperty
	private List<DataDictionary> dataDictionaryList;
	@Inject
	private PartialUpdateManager UpdateManager;

	@ManagedProperty("#{indexBean}")
	@SaveState
	private IndexBean indexBean;

	// @Bind(id = "industryItems")
	// @SelectItems
	// @SaveState
	private SelectItem[] industryItems;
	
	@Bind(id = "type")
	private UICombo typeComboBox;
	// @PostConstruct
	@Bind(id = "industryItems")
	@SelectItems
	SelectItem[] getIndustryItems() {
		String ql = "select distinct o.type,o.remark from DataDictionary o ";
		List<Object[]> list = dataDictionaryBo.find(ql);
		industryItems = new SelectItem[list.size()];
		for (int i = 0; i < list.size(); i++) {
			industryItems[i] = new SelectItem(list.get(i)[0].toString(), list.get(i)[0].toString());
		}
		return industryItems;
	}

	String getItemLabel(SelectItem[] items) {
		for (SelectItem item : items) {
			if (item.getValue().equals(item.getValue()))
				return item.getLabel();
		}
		return null;
	}

	public List<DataDictionary> getDataDictionaryList() {
		if (null == content || "".equals(content)) {
			dataDictionaryList = dataDictionaryBo.findAll();
		} else {
			Map map = new HashMap();
			map.put("name", content);
			dataDictionaryList = dataDictionaryBo.findByParm(map);
		}

		return dataDictionaryList;
	}

	public void create() {
		dataDictionary = new DataDictionary();
		name = null;
		UpdateManager.markUpdate(true, UpdateLevel.Data, "dataForm3");
		this.dialog.show();
	}
//		private String ToUTF8(String string) throws UnsupportedEncodingException{
//			    // Convert from Unicode to UTF-8
//			    byte[] utf8 = string.getBytes("UTF-8");
//		
//			    // Convert from UTF-8 to Unicode
//			    string = new String(utf8, "UTF-8");
//			    return string;
//		}
	public void save() throws UnsupportedEncodingException {
		dataDictionary.setCode(code);
		dataDictionary.setName(name);
		dataDictionary.setType(type);
		dataDictionary.setRemark(remark);
		dataDictionaryBo.save(dataDictionary);
		dataGrid.reload();
		code = "";
		name = "";
		remark = "";
		this.dialog.close();

	}

	public void remove() {
		Object[] objs = dataGrid.getSelectedValues();
		for (Object object : objs) {
			dataDictionaryBo.remove((DataDictionary) object);
		}
		dataGrid.reload();
	}

	public void edit() {
		Object[] objs = dataGrid.getSelectedValues();
		if (objs != null && objs.length > 0) {
			dataDictionary = (DataDictionary) objs[0];
			code = dataDictionary.getCode();
			name = dataDictionary.getName();
			type = dataDictionary.getType();
			remark = dataDictionary.getRemark();
			dialog.show();
			this.dialog.repaint();
		}
	}

	/**
	 * 
	 */
	public void search() {
		dataGrid.reload();
	}

	@Action
	public void reset() {
		this.dialog.close();
	}

//	@Action(immediate=true)
//    public void typeComboBox_onselect(){
//    	remark=getItemLabel(this.industryItems);
//    }
}
