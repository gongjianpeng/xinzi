package cn.internalaudit.audit.beans.agency;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.Inject;
import org.operamasks.faces.annotation.LocalString;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.Required;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.annotation.SelectItems;
import org.operamasks.faces.component.grid.impl.UIDataGrid;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.user.ajax.PartialUpdateManager;
import org.operamasks.faces.user.ajax.UpdateLevel;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.utils.Utils;

@ManagedBean(name = "pages.agency.departmentBean", scope = ManagedBeanScope.REQUEST)
public class DepartmentBean {
	@Inject("#{DepartmentBo}")
	private IDepartmentBo departmentBo;
	
	@Inject("#{OrganizationBo}")
	private IOrganizationBo offshootBankBo;
	
	@Required(message = "#{msgs['department.departmentCodeNull']}")
	@Bind
	private String code;
	
	@Bind
	private List<Organization> offsBankList;
	@ManagedProperty
	private Organization organization;
	
	@Required(message = "#{msgs['department.organzationNull']}")
	@Bind
	private String offBank;
	
	@Required(message = "#{msgs['department.departmentNameNull']}")
	@Bind
	private String name;
	
	@Bind
	@SaveState
	private String departmentName;
	
	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;
	
	@Bind
	@SaveState
	private String departmentCode;
	
	@Required(message = "#{msgs['department.shortNameNull']}")
	@Bind
	private String shortName;

	@Bind
	private String remark;
	
	@Bind
	@SaveState
	private String organizationId;

	@ManagedProperty
	@SaveState
	private Department department;
	
	@SaveState
	@ManagedProperty
	private List<Department> departmentsList;
	
	@LocalString(basename = "messages.messages_ljz")
	private Map<String, String> messages;
	
	@Inject
	private PartialUpdateManager updateManager;

	/***********
	 * dialog
	 */
	@Bind
	private UIWindow showDialog;

	@Bind(id = "grid")
	private UIDataGrid grid;

	public List<Department> getDepartmentsList() {
		if(Utils.nullOrEmpty(departmentName) && Utils.nullOrEmpty(departmentCode) && Utils.nullOrEmpty(organizationId)){
			departmentsList = departmentBo.findAll();
		}else{
			departmentsList=departmentBo.getFindByNameOrCode(departmentCode, departmentName,organizationId);
		}
			return departmentsList;
		
	}

	public void save() {
		organization=offshootBankBo.find(Long.valueOf(offBank));
		department.setCode(code);
		department.setName(name);
		department.setShortName(shortName);
		department.setRemark(remark);
		department.setOrganization(organization);
		if (!departmentBo.isAvailableByCode(department, code,organization.getId())) {
			Browser.execClientScript("alert('"
					+ messages.get("departmetn.departmentCodeValidate") + "');");
			return;
		}
		if (!departmentBo.isAvailableByName(department, name, organization.getId())) {
			Browser.execClientScript("alert('"
					+ messages.get("departmetn.departmentNameValidate") + "');");
			return;
		}
		department = departmentBo.save(department);
		code="";
		name="";
		shortName="";
		offBank="";
		remark="";
		grid.reload();
		showDialog.close();
	}

	public void remove() {
		Object objects[] = grid.getSelectedValues();
		for (Object o : objects) {
			Department department = (Department) o;
			departmentBo.remove(department);
		}
		grid.reload();
	}

	public void endit() {
		Object object[] = grid.getSelectedValues();
		if (object != null && object.length > 0) {
			department = (Department) object[0];
			code = department.getCode();
			name = department.getName();
			shortName = department.getShortName();
			remark = department.getRemark();
			this.offBank = department.getOrganization().getId().toString();
			showDialog.show();
			showDialog.repaint();
			// editIframe.load("editOffshootBank.xhtml");
		}
	}

	@Bind
	@SelectItems
	public SelectItem[] getOffBankValue() {
		offsBankList = offshootBankBo.findAll();
		SelectItem[] temp = new SelectItem[offsBankList.size()];
		for (int i = 0; i < offsBankList.size(); i++) {
			organization = offsBankList.get(i);
			temp[i] = new SelectItem(organization.getId(), organization.getName());
		}
		return temp;
	}

	public void create() {
		department = new Department();
		code="";
		updateManager.markUpdate(true,UpdateLevel.Data,"departmentFrm");
		this.showDialog.show();
	}
	@Action
	public void cancel() {
		showDialog.close();
	}
	@Action(id = "search", immediate = true)
	public void search() {
		grid.reload();
	}
}
