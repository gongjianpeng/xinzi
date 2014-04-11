package cn.internalaudit.audit.beans.agency;

import java.util.List;
import java.util.Locale;

import javax.faces.model.SelectItem;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.Inject;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.Required;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.annotation.SelectItems;
import org.operamasks.faces.component.grid.impl.UIDataGrid;
import org.operamasks.faces.component.html.impl.UIIFrame;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.utils.Utils;

@ManagedBean(name = "pages.agency.organizationBean-remove", scope = ManagedBeanScope.REQUEST)
public class OrganizationBean {
	@Inject("#{OrganizationBo}")
	private IOrganizationBo organizationBo;

	@ManagedProperty
	@SaveState
	private List<Organization> organizationList;

	public List<Organization> getOffshootBankList() {
		if (Utils.nullOrEmpty(searchCode) && Utils.nullOrEmpty(searchName) && Utils.nullOrEmpty(parentOrganization)) {
			organizationList = organizationBo.findAll();

		}else{
			organizationList = organizationBo.getFindByNameOrCode(parentOrganization,searchCode,
					searchName);
		}
		return organizationList;

	}

	@SaveState
	private Organization offshootBank;
	
	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;
	@Bind
	@Required(message = "Can not be empty!")
	private String address;

	@Bind
	@SaveState
	private UIDataGrid grid;

	@Bind
	@SaveState
	private String searchCode;

	@Bind
	@SaveState
	private String searchName;
	
	@Bind
	@SaveState
	private String parentOrganization;
	
	@Bind
	@SaveState
	private String parentOrganizationName;
	
	@Bind
	@SaveState
	private Boolean isHeadquarters;

	@Bind
	private UIIFrame editIframe;
	@Required(message = "Code can not be empty！")
	@Bind
	private String code;
	@Required(message = "Branch name can not be empty！")
	@Bind
	private String name;

	@Bind
	private String remark;

	/***********
	 * Dialog
	 */
	@Bind
	private UIWindow dilog;

	@Bind
	private List<Organization> organizations;
	@ManagedProperty
	private Organization organization;

	@Bind(id = "parent")
	private String parent;

	@Bind
	@SelectItems
	public SelectItem[] getParentName() {
		organizations = organizationBo.findAll();
		SelectItem[] temp = new SelectItem[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			organization = organizations.get(i);
			temp[i] = new SelectItem(organization.getId(), organization
					.getName());
		}
		return temp;
	}

	public void save() {

		offshootBank.setCode(code);
		offshootBank.setName(name);
		offshootBank.setAddress(address);
		offshootBank.setRemark(remark);
		offshootBank.setIsHeadquarters(isHeadquarters);
		if (parent != null && parent != "") {
			organization = organizationBo.find(Long.valueOf(parent));
		}
		offshootBank.setParentOrganization(organization);

		if (!organizationBo.isAvailableByCode(offshootBank, code)) {
			Browser.execClientScript("alert('Code duplication！');");
			return;
		}
		if (!organizationBo.isAvailableByName(offshootBank, name)) {
			Browser.execClientScript("alert('Branch Name repeat');");
			return;
		}
		if (organization != null && name.equals(organization.getName())) {
			Browser.execClientScript("alert('Organizations do not choose the current parent organization');");
			return;
		}
		if (offshootBank.getIsHeadquarters() != true && organization == null) {
			Browser.execClientScript("alert('Parent organization can not be empty！');");
			return;
		}
		if (offshootBank.getIsHeadquarters() == true) {
			if (!organizationBo.checkHeadquarters(offshootBank)) {
				Browser.execClientScript("alert('Only one headquarters');");
				return;
			}
		}
		if (offshootBank.getIsHeadquarters() == true && organization != null) {
			Browser.execClientScript("alert('The current organization of the headquarters of the parent organization can not be added！');");
			return;
		}
		
		offshootBank = organizationBo.save(offshootBank);
		code = "";
		name = "";
		remark = "";
		address = "";
		isHeadquarters = false;
		grid.reload();
		Browser.execClientScript("$('#myTree').omTree('setData','getOrganization.do');$('#myTree').omTree('refresh');");
		this.canale();

	}

	public void edit() {
		Object[] object = grid.getSelectedValues();
		if (object != null && object.length > 0) {
			offshootBank = (Organization) object[0];
			code = offshootBank.getCode();
			name = offshootBank.getName();
			remark = offshootBank.getRemark();
			address = offshootBank.getAddress();
			isHeadquarters = offshootBank.getIsHeadquarters();
			dilog.show();
			dilog.repaint();
			// editIframe.load("editOffshootBank.xhtml");
		}
	}

	public void remove() {
		Object[] selecteds = grid.getSelectedValues();
		for (Object o : selecteds) {
			organizationBo.remove((Organization) o);
		}
		grid.reload();
	}

	public void create() {
		offshootBank = new Organization();
		dilog.show();
	}

	@Action
	public void clicent() {
//		organizationList = organizationBo.getFindByNameOrCode(parentOrganization,searchCode,
//				searchName);
		Organization org = organizationBo.find(Long.valueOf(parentOrganization)).getParentOrganization();
		if(org==null){
			parentOrganizationName = "";
		}else{
			parentOrganizationName = "Parent organization："+ org.getName();
		}
		grid.reload();
	}

	@Action
	public void canale() {
		dilog.close();
	}

}
