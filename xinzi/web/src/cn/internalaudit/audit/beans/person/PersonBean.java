package cn.internalaudit.audit.beans.person;

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
import org.operamasks.faces.annotation.Validate;
import org.operamasks.faces.component.form.impl.UICombo;
import org.operamasks.faces.component.grid.impl.UIDataGrid;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.utils.Utils;

@ManagedBean(name = "pages.person.person1Bean", scope = ManagedBeanScope.REQUEST)
public class PersonBean {
	@ManagedProperty
	@SaveState
	private Person person;
	@Inject("#{PersonBo}")
	private IPersonBo personBo;

	@Inject("#{OrganizationBo}")
	private IOrganizationBo organizationBo;

	@Inject("#{DepartmentBo}")
	private IDepartmentBo departmentBo;

	@Bind
	private UIDataGrid grid;
	@SaveState
	@Bind
	private UIWindow dialog;

	// @Bind(id = "organizatonComboBox")
	// private UICombo organizatonComboBox;

	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;

	@Bind(id = "organization")
//	@Required(message = "#{msgs['person.confimonnull']}")
	private String organization;

	// @Bind(id = "department")
	// private UICombo departmentComboBox;

	@Bind
	@SaveState
	@Required(message = "#{msgs['dialog.departmentNotNull']}")
	private Long department;

	@Bind
	private String name;

	@Bind
	private String englishName;

	@Bind
	private String code;

	@Bind
	@Required(message = "#{msgs['dialog.sexNotNull']}")
	private String sex;

	@Bind
	private String cardType;

	@Bind
	private String cardNumber;

	@Validate(message = "#{msgs['dialog.validateCardNumber']}")
	private boolean validateCardNumber(String cardNumber) {

		return cardNumber.length() == 18;

	}

	@Bind
	private String phone;

	@Bind
	private String address;

	@Bind
	private String email;

	@Validate(message = "#{msgs['dialog.passwordValide']}")
	private boolean validateEmail(String email) {
		return email.contains("@") && email.lastIndexOf(".") != -1
				&& email.lastIndexOf(".") > email.lastIndexOf("@");
	}

	@Bind
	private String remark;

	@Bind
	private String loginName;

	@Bind
	@SaveState
	private String roleName;

	@Bind
	@SaveState
	private String roleLoginName;

	@Bind
	@SaveState
	private String password;
	@Required(message = "#{msgs['dialog.pssswordConfirmNotNull']}")
	@Bind
	private String rePassword;

	@Bind
	private Boolean enable = true;

	
	private SelectItem[] selectDepartment;

	// private Long organizationId;

	@LocalString(basename = "messages.messages_person")
	private Map<String, String> messages;

	@ManagedProperty
	@SaveState
	private List<Person> personList;

	public List<Person> getPersonList() {

		if (Utils.nullOrEmpty(roleName) && Utils.nullOrEmpty(roleLoginName)) {
			personList = personBo.findAll();
		}
		return personList;
	}
	@Bind(id = "organization")
	private UICombo organizationComboBox;
	
	private SelectItem[] selectOrganization;

	@Bind(id = "selectOrganization")
	@SelectItems
	public SelectItem[] getSelectOrganization() {
		List<Organization> list = organizationBo.findAll();
		selectOrganization = new SelectItem[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Organization organization = list.get(i);
			selectOrganization[i] = new SelectItem(organization.getId()
					.toString(), organization.getName());
		}
		return selectOrganization;
	}
	@Bind(id = "selectDepartment")
	@SelectItems
	public SelectItem[] getSelectDepartment() {
		// if (organizatonComboBox.getValue() != null) {
		// organizaton = Long.valueOf(organizatonComboBox.getValue()
		// .toString());
		if (organization != null) {
			List<Department> list = departmentBo.findByOrganizationId(Long.valueOf(organization));
			if (list != null && list.size() > 0) {
				selectDepartment = new SelectItem[list.size()];
				for (int i = 0; i < list.size(); i++) {
					Department department = list.get(i);
					selectDepartment[i] = new SelectItem(department.getId(),
							department.getName());
				}

			}
		} else {
			selectDepartment = null;
		}
		return selectDepartment;
	}

//	public void getDepartmentForCombo() {
//
//		if (organization != null) {
//			List<Department> list = departmentBo
//					.findByOrganizationId(Long.valueOf(organization));
//			SelectItem[] departmentItems = null;
//			if (list != null && list.size() > 0) {
//				departmentItems = new SelectItem[list.size()];
//				for (int i = 0; i < list.size(); i++) {
//					Department department = list.get(i);
//					departmentItems[i] = new SelectItem(department.getId(),
//							department.getName());
//				}
//
//			}
//			selectDepartment = departmentItems;
//		} else {
//			selectDepartment = null;
//		}
//
//	}

	@Action
	public void save() {
		Long departmentId = 0L;
		if (null != department) {
			departmentId = department;
		}
		Department department = departmentBo.find(departmentId);
		person.setDepartment(department);
		person.setAddress(address);
		person.setCardNumber(cardNumber);
		person.setCardType(cardType);
		person.setEmail(email);
		person.setCode(code);
		person.setEnable(enable);
		person.setEnglishName(englishName);
		person.setName(name);
		person.setPhone(phone);
		person.setRemark(remark);
		person.setSex(sex);
		if (personBo.isAvailableByLoginName(person, loginName)) {
			person.setLoginName(loginName);
		} else {
			Browser.execClientScript("alert('"
					+ messages.get("dialog.loginNameValide") + "');");
			return;
		}

		if (password.length() == 40 && person.getPassword().equals(password)) {
			person.setPassword(password);
		} else if (password.length() < 6 || password.length() > 16) {
			Browser.execClientScript("alert('"
					+ messages.get("dialog.passwordValide2") + "');");
			return;
		} else if (password.trim().equals(rePassword) == false) {
			Browser.execClientScript("alert('"
					+ messages.get("dialog.passwordValide3") + "');");
			return;
		} else {
			person
					.setPassword(Utils.encodeSHA(password, person
							.getLoginName()));
		}
		personBo.save(person);
		dialog.close();
		grid.reload();

	}

	@Action
	public void create() {
		person = new Person();
		this.clear();
		// getSelectOrganizations();
	//	getSelectDepartment();
		dialog.show();
	}

	@Action
	public void edit() {
		Object[] object = grid.getSelectedValues();
		if (object != null && object.length > 0) {
			person = (Person) object[0];
			// organizatonComboBox.setValue(person.getDepartment()
			// .getOrganization().getId());
			// organizationId = Long.valueOf(person.getDepartment()
			// .getOrganization().getId());
			department = person.getDepartment().getId();
			organization = String.valueOf(person.getDepartment().getOrganization().getId());
		//	getSelectOrganizations();
			getSelectDepartment();
			// departmentComboBox.setValue(person.getDepartment().getId());
			address = person.getAddress();
			cardNumber = person.getCardNumber();
			cardType = person.getCardType();
			email = person.getEmail();
			code = person.getCode();
			enable = person.getEnable();
			englishName = person.getEnglishName();
			loginName = person.getLoginName();
			name = person.getName();
			password = person.getPassword();
			phone = person.getPhone();
			remark = person.getRemark();
			sex = person.getSex();
			dialog.show();
			dialog.repaint();
		}

	}

	@Action
	public void remove() {

		Object[] selecteds = grid.getSelectedValues();
		for (Object o : selecteds) {
			personBo.remove((Person) o);
		}
		grid.reload();
	}

	@Action
	public void offShootBankOnChange() {

	}

	@Action
	public void findByLoginNameandName() {
		person = new Person();
		person.setName(roleName);
		person.setLoginName(roleLoginName);
		personList = personBo.findByLoginNameAndName(person);
		grid.reload();

	}

	@Action
	public void cancel() {

		dialog.close();
	}

	public void clear() {
		this.name = "";
		this.address = "";
		this.cardNumber = "";
		this.code = "";
		this.department = null;
		this.rePassword = "";
		this.email = "";
		this.enable = false;
		this.englishName = "";
		this.loginName = "";
		this.phone = "";
		this.remark = "";
		dialog.repaint();
	}

	// @Action(immediate = true)
	// public void organizaton_onselect() {
	// String a = "";
	// if (organizatonComboBox.getValue() != null) {
	// organizationId = Long.valueOf(organizatonComboBox.getValue()
	// .toString());
	// getDepartmentForCombo();
	// } else {
	// Browser.alert("please select depart.");
	// return;
	// }
	//
	// }

}
