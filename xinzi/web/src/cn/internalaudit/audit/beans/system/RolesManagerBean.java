package cn.internalaudit.audit.beans.system;

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
import org.operamasks.faces.component.tree.impl.UITreeNode;
import org.operamasks.faces.user.ajax.PartialUpdateManager;
import org.operamasks.faces.user.ajax.UpdateLevel;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.bo.IAuthoritiesKeyBo;
import cn.internalaudit.audit.bo.IRolesAuthoritiesBo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.entitys.AuthoritiesKey;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.entitys.RolesAuthorities;

@ManagedBean(name = "pages.system.rolesManager2Bean", scope = ManagedBeanScope.REQUEST)
@SuppressWarnings("unchecked")
public class RolesManagerBean {
	@LocalString(basename = "messages.messages_rolesManager")
	private Map<String, String> messages;

	@Inject("RolesBo")
	private IRolesBo rolesBo;
	@Inject("#{AuthoritiesKeyBo}")
	private IAuthoritiesKeyBo authorBo;
	@Inject("RolesAuthoritiesBo")
	private IRolesAuthoritiesBo rolesAuthoritiesBo;
	@Bind
	private UIDataGrid rolesGrid;
	@Bind(id = "authoritiesGrid")
	private UIDataGrid authoritiesGrid;
	@Bind
	private UIDataGrid authoritiesListListGrid;
	@Bind
	private String name;
	@SaveState
	private Long id;
	@Bind
	private UIWindow dialog3;
	@Bind(id = "tree")
	private UITree tree;
	@Bind
	@SaveState
	private String content;
	@Bind
	private UIWindow dialog;
	@Bind
	private UIWindow dialog2;
	@ManagedProperty
	private List<Roles> rolesList;
	@ManagedProperty
	private List<AuthoritiesKey> authoritiesKeyList;
	@Bind
	@ManagedProperty
	private List<RolesAuthorities> rolesAuthorList;
	@ManagedProperty
	@SaveState
	private Roles roles;
	@ManagedProperty
	@SaveState
	private AuthoritiesKey authoritiesKey;
	@ManagedProperty
	@SaveState
	private RolesAuthorities rolesAuthor;

	@Inject
	private PartialUpdateManager updateManager;
	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;

	/**
	 * 
	 * 
	 * @return
	 */
	public List<Roles> getRolesList() {
		if (null == content || "".equals(content)) {
			rolesList = rolesBo.findAll();
		} else {
			rolesList = rolesBo.rolesList(content);
		}
		// rolesGrid.setSelectedRow(-1)
		return rolesList;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	@Bind(id = "authoritiesListListGrid")
	public List<AuthoritiesKey> getAuthoritiesList() {
		authoritiesKeyList = authorBo.findAll();
		return authoritiesKeyList;
	}

	/**
	 * 
	 */
	public void create() {
		roles = new Roles();
		this.name = null;
		updateManager.markUpdate(true, UpdateLevel.Data, "rolesForm");
		this.dialog.show();
	}

	/**
	 * 
	 */
	public void save() {
		roles.setName(name);
		rolesBo.save(roles);
		rolesGrid.reload();
		this.dialog.close();
	}

	/**
	 *
	 */
	@SuppressWarnings("deprecation")
	public void remove() {
		Object[] objs = rolesGrid.getSelectedValues();
		for (Object object : objs) {
			roles = (Roles) object;

			List<Person> personList = roles.getPersons();

			rolesAuthorList = rolesAuthoritiesBo.findAuthorByRolesId(roles
					.getId());
			if (personList != null && personList.size() > 0) {
				Browser.execClientScript("alert('"
						+ messages.get("role.delete.prompt.person") + "');");
				return;
			}
			if (rolesAuthorList == null || rolesAuthorList.size() == 0) {
				rolesBo.remove(roles);
			} else {
				Browser
						.execClientScript("alert('"
								+ messages.get("role.delete.prompt.permission")
								+ "');");
				return;
			}

		}

		rolesGrid.reload();
		rolesGrid.setSelectedRow(-1);
		rolesGrid.repaint();
	}

	/**
	 * 修改信息
	 */
	public void edit() {
		Object[] objs = rolesGrid.getSelectedValues();
		if (null != objs && objs.length > 0) {
			roles = (Roles) objs[0];
			name = roles.getName();

			this.dialog.show();
			this.dialog.repaint();
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void assign() {
		authoritiesListListGrid.setSelectedRow(-1);
		updateManager.markUpdate(true, UpdateLevel.Data, "authorForm");
		dialog2.show();

	}

	/**
	 * 
	 */
	public void search() {
		rolesGrid.reload();
	}

	/**
	 * 
	 */
	public void saveAuthor() {
		Object[] objs = rolesGrid.getSelectedValues();
		if (objs != null && objs.length > 0) {
			roles = (Roles) objs[0];
		}
		Object[] objcts = authoritiesListListGrid.getSelectedValues();
		if (objcts != null && objcts.length > 0) {
			for (int i = 0; i < objcts.length; i++) {
				RolesAuthorities rolesAuthors = new RolesAuthorities();
				authoritiesKey = (AuthoritiesKey) objcts[i];
				rolesAuthor = rolesAuthoritiesBo.findAuthorByAuthorName(roles
						.getId(), authoritiesKey.getName());
				if (rolesAuthor != null) {
					continue;
				}
				rolesAuthors.setAuthoritiesKey(authoritiesKey);
				rolesAuthors.setRoles(roles);
				rolesAuthoritiesBo.save(rolesAuthors);

			}
		}

		rolesGrid.reload();
		authoritiesGrid.reload();
		this.dialog2.close();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List<RolesAuthorities> getRolesAuthorList() {
		if (id == null || "".equals(id)) {
			id = 0L;
		}
		return rolesAuthoritiesBo.findAuthorByRolesId(id);
	}

	/**
	 *
	 */
	@Action(id = "rolesGrid", event = "onrowselect")
	public void showAouthor() {
		Object[] objs = rolesGrid.getSelectedValues();
		if (objs != null && objs.length > 0) {
			roles = (Roles) objs[0];
			id = roles.getId();

		}
		authoritiesGrid.setSelections(null);
		authoritiesGrid.reload();

	}

	/**
	 * 
	 */
	@Action
	public void reset() {
		this.dialog.close();
	}

	/**
	 * 
	 */
	@Action
	public void cancel() {
		this.dialog3.close();
	}

	public void showDialog() {
		Object[] objs = rolesGrid.getSelectedValues();
		if (objs.length == 1) {
			this.dialog3.show();
		} else {
			Browser.execClientScript("window.confirm('"
					+ messages.get("role.permissions.distribution.prompt")
					+ "');");
		}

	}

	@Bind(id = "tree")
	private TreeDataProvider2 treeData = new TreeDataProviderAdapter() {
		public Object[] getChildren(Object node) {
			String root = messages.get("role.permissions.list");
			if (node == null) {
				return new Object[] { root };
			} else if (node instanceof String) {
				authoritiesKeyList = authorBo.findAll();
				AuthoritiesKey[] obj = new AuthoritiesKey[authoritiesKeyList
						.size()];
				for (int i = 0; i < authoritiesKeyList.size(); i++) {
					obj[i] = authoritiesKeyList.get(i);
				}
				return obj;
			}
			return null;

		}

		public Boolean isChecked(Object node) {
			return false;
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
			} else if (node instanceof AuthoritiesKey) {
				return ((AuthoritiesKey) node).getName();
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
			// TODO Auto-generated method stub
			return false;
		}

		// @Override
		// public String getId(Object userData) {
		// return userData.toString();
		// }

	};

	public void saveRolesAuthor() {

		Object[] objs = rolesGrid.getSelectedValues();

		if (objs != null && objs.length > 0) {
			roles = (Roles) objs[0];
		}
		if (tree.getCheckedNodes() != null) {
			for (UITreeNode node : tree.getCheckedNodes()) {

				RolesAuthorities rolesAuthors = new RolesAuthorities();
				if (node.getUserData() instanceof AuthoritiesKey) {
					AuthoritiesKey authoritiesKey = (AuthoritiesKey) node
							.getUserData();

					rolesAuthor = rolesAuthoritiesBo.findAuthorByAuthorName(
							roles.getId(), authoritiesKey.getName());
					if (rolesAuthor != null) {
						continue;
					}
					rolesAuthors.setAuthoritiesKey(authoritiesKey);
					rolesAuthors.setRoles(roles);
					rolesAuthoritiesBo.save(rolesAuthors);
				}
			}
		}

		rolesGrid.reload();
		authoritiesGrid.reload();
		this.dialog3.close();

	}

	@Action(id = "viewDel", event = "onclick")
	public void viewDel() {
		Object[] objs = authoritiesGrid.getSelectedValues();
		if (objs == null || objs.length == 0) {
			Browser.execClientScript("window.alert('"
					+ messages.get("srole.delete.confirm") + "');");
			return;
		}
		for (Object obj : objs) {
			RolesAuthorities viewSelectedRowData = (RolesAuthorities) obj;
			rolesAuthoritiesBo.remove(viewSelectedRowData);
		}
		authoritiesGrid.reload();
	}

}
