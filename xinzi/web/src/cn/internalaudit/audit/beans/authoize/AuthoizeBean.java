package cn.internalaudit.audit.beans.authoize;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.Inject;
import org.operamasks.faces.annotation.LocalString;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.component.grid.impl.UIDataGrid;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.component.tree.base.TreeDataProvider2;
import org.operamasks.faces.component.tree.base.TreeDataProviderAdapter;
import org.operamasks.faces.component.tree.impl.UITree;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;

@ManagedBean(name = "pages.person.authoizeBean1", scope = ManagedBeanScope.REQUEST)
public class AuthoizeBean {

	@Inject("#{OrganizationBo}")
	private IOrganizationBo offshootBankBo;

	@Inject("#{DepartmentBo}")
	private IDepartmentBo departmentBo;

	@Inject("#{RolesBo}")
	private IRolesBo rolesBo;

	@Bind
	private UITree tree;

	@Inject("#{PersonBo}")
	private IPersonBo personBo;

	@Bind
	@ManagedProperty
	@SaveState
	private List<Roles> rolesList;

	@Bind
	@ManagedProperty
	@SaveState
	private List<Roles> personForRolesList;

	@Bind
	private UIWindow dialog;

	@ManagedProperty
	@SaveState
	private Roles roles;

	@ManagedProperty
	@SaveState
	private Person person;

	@LocalString(basename = "messages.messages_audthoize")
	private Map<String, String> messages;

	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;
	@Bind
	private UIDataGrid grid;

	@Bind
	private UIDataGrid gridAllRolelist;

	@Bind(id = "tree")
	private TreeDataProvider2 treeData = new TreeDataProviderAdapter() {
		public Object[] getChildren(Object node) {

			if (node == null) {
				return new Object[] { "company" };
			}
			if (node == "company") {
				List<Organization> list = offshootBankBo.findAll();

				Organization[] objectArray = new Organization[list.size()];

				for (int i = 0; i < list.size(); i++) {
					objectArray[i] = list.get(i);
				}
				return objectArray;
			}
			if (node instanceof Organization) {
				long id = ((Organization) node).getId();
				List<Department> list = departmentBo.findByOrganizationId(id);
				Department[] objectArray = new Department[list.size()];

				for (int i = 0; i < list.size(); i++) {
					objectArray[i] = list.get(i);
				}
				return objectArray;
			}
			if (node instanceof Department) {
				long id = ((Department) node).getId();
				List<Person> list = personBo.findByDepartmentId(id);
				Person[] objectArray = new Person[list.size()];

				for (int i = 0; i < list.size(); i++) {
					objectArray[i] = list.get(i);
				}
				return objectArray;
			}

			return null;
		}

		public Boolean isChecked(Object node) {

			return null;
		}

		public boolean isCascade(Object node) {
			return true;
		}

		public String getIcon(Object node) {
			return "";
		}

		public String getText(Object node) {
			if (node instanceof String) {
				return node.toString();
			} else if (node instanceof Organization) {
				if (((Organization) node).getName() == null
						|| ((Organization) node).getName().equals("")) {
					return ((Organization) node).getId().toString();
				}
				return ((Organization) node).getName();
			} else if (node instanceof Department) {
				if (((Department) node).getName() == null
						|| ((Department) node).getName().equals("")) {
					return ((Department) node).getId().toString();
				}
				return ((Department) node).getName();
			} else if (node instanceof Person) {
				if (((Person) node).getName() == null
						|| ((Person) node).getName().equals("")) {
					return ((Person) node).getId().toString();
				}
				return ((Person) node).getName();
			}
			return null;
		}

		public String getHref(Object node) {
			return null;
		}

		public String getHrefTarget(Object node) {
			return null;
		}

		public boolean isExpanded(Object arg0) {

			return false;
		}
	};

	@Action
	public void tree_onselect() {
		roleDisplay();
	}

	@Action
	public void tree_onclick() {
		roleDisplay();
	}

	@Action
	private void roleDisplay() {
		if (tree == null) {
			return;
		}
		if (tree.getEventNode() != null) {
			Object object = tree.getEventNode().getUserData();
			if (tree.getCheckedNodes() != null) {

				if (object instanceof Person) {
					personForRolesList = ((Person) object).getRoless();
					person = (Person) object;
					grid.reload();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Action
	public void create() {
		if (person != null) {
			rolesList = rolesBo.findAll();
			for (int i = 0; i < personForRolesList.size(); i++) {
				Roles rolesPerson = personForRolesList.get(i);
				for (int j = 0; j < rolesList.size(); j++) {
					Roles role = rolesList.get(j);
					if (role.getId().longValue() == rolesPerson.getId()
							.longValue()) {
						rolesList.remove(role);
					}

				}
			}

			this.dialog.show();

			gridAllRolelist.reload();
			gridAllRolelist.setSelectedRow(-1);
		} else {
			Browser.execClientScript("alert('"
					+ messages.get("audthoize.selectPerson") + "');");
		}

	}

	@SuppressWarnings("deprecation")
	@Action
	public void remove() {
		Object[] selecteds = grid.getSelectedValues();
		if (person != null) {

			for (Object o : selecteds) {
				personForRolesList.remove(o);
			}
			person.setRoless(personForRolesList);
			personBo.save(person);
		}

		grid.reload();
		grid.setSelectedRow(-1);
	}

	@SuppressWarnings("deprecation")
	@Action
	public void save() {
		Object[] selecteds = gridAllRolelist.getSelectedValues();
		if (selecteds != null) {
			for (int i = 0; i < selecteds.length; i++) {
				roles = (Roles) selecteds[i];
				personForRolesList.add(roles);
			}
		}
		if (person != null) {
			person.setRoless(personForRolesList);
			personBo.save(person);
			personForRolesList = person.getRoless();
		}
		dialog.close();
		grid.reload();
		grid.setSelectedRow(-1);

	}

	@SuppressWarnings("deprecation")
	@Action
	public void cancel() {

		dialog.close();
		grid.setSelectedRow(-1);

	}

}
