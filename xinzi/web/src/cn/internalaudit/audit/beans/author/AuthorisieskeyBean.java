package cn.internalaudit.audit.beans.author;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.DataModel;
import org.operamasks.faces.annotation.Inject;
import org.operamasks.faces.annotation.LocalString;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.Required;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.component.grid.impl.UIDataGrid;
import org.operamasks.faces.component.layout.impl.UIWindow;
import org.operamasks.faces.user.ajax.PartialUpdateManager;
import org.operamasks.faces.user.ajax.UpdateLevel;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.bo.IAuthoritiesKeyBo;
import cn.internalaudit.audit.constant.Constant;
import cn.internalaudit.audit.entitys.AuthoritiesKey;

@ManagedBean(name = "pages.author.authorisieskey1Bean", scope = ManagedBeanScope.REQUEST)
public class AuthorisieskeyBean {

	@LocalString(basename="messages.messages_authoritiesManager")
	private Map<String, String> messages;
	
	@Bind
	private String id;
	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;
	@Bind
	@Required(message = "#{msgs['authorities.confirm']}")
	private String name;
	
	@Bind
	private String value;
	
	
	@Inject
	private PartialUpdateManager dauthorUpdate;
	
	@Inject("#{AuthoritiesKeyBo}")
	private IAuthoritiesKeyBo authorBo;
	
	@SaveState
	protected String dauthorSelectedRow;
	
	@DataModel(id = "authorGrid")
	public List<AuthoritiesKey> getAuthor(){
		return authorBo.findAll();
	}
	
	@Bind(id = "authorGrid")
	private UIDataGrid authorGrid;
	
	
	@Bind(id = "dauthorAddDialog")
	private UIWindow dauthorAddDialog;
	
	@Action(id = "authorkeyAdd", event = "onclick")
    public void authorkeyAdd() {
		name = null;
		value = Constant.AUTHORROLE;
		
		dauthorUpdate.markUpdate(true, UpdateLevel.Data, "dauthorAddForm");
		dauthorAddDialog.show();
	}
	
	@Action(id = "authorkeySaveAdd", event = "onclick")
	public void authorkeySaveAdd(){
		authorkeyAddsave();
		
	}
	
	@Action(id = "authorkeyCloseAdd", event = "onclick")
	public void authorkeyCloseAdd(){
		dauthorAddDialog.close();
	}
	
	public void authorkeyAddsave(){
		AuthoritiesKey author = new AuthoritiesKey();
		author.setName(name);
		
		if(authorBo.isAvailableByValue(author,value)){
			author.setValue(value);
			authorBo.save(author);
			
			dauthorReload();
			dauthorAddDialog.close();
		}else{
			Browser.execClientScript("window.alert('"+messages.get("authorities.keyValue.confirm")+"');");
		}
	}
	
	

	@Bind(id = "dauthorEditDialog")
	private UIWindow dauthorEditDialog;
	
	@Action(id = "authorGrid", event = "ondblclick")
    public void authorkeyEdit_dblclick() {
		Object[] obj = authorGrid.getSelectedValues();
		AuthoritiesKey author = (AuthoritiesKey) obj[0];
	    id = author.getId().toString();
	    name = author.getName();
	    value = author.getValue();
	    	
	    dauthorSelectedRow = id;
	    dauthorUpdate.markUpdate(true, UpdateLevel.Data, "dauthorEditForm");
	    dauthorEditDialog.show();
	}
	
	@Action(id = "authorGrid", event = "onclick")
    public void authorkey_click() {
		Object[] obj = authorGrid.getSelectedValues();
		AuthoritiesKey author = (AuthoritiesKey) obj[0];
	    id = author.getId().toString();
	    name = author.getName();
	    value = author.getValue();
	    	
	    dauthorSelectedRow = id;
	    dauthorUpdate.markUpdate(true, UpdateLevel.Data, "dauthorEditForm");
	}
	
	@Action(id = "authorkeyEdit", event = "onclick")
	public void authorkeyEdit(){
		Object[] obj = authorGrid.getSelectedValues();
		if (obj == null || obj.length == 0) {
			Browser.execClientScript("window.alert('"+messages.get("authorities.sava.confirm")+"');");
			return;
		}
	    if (obj.length > 0) {
	    	AuthoritiesKey author = (AuthoritiesKey) obj[0];
	    	id = author.getId().toString();
	    	name = author.getName();
	    	value = author.getValue();
	    	
	    	dauthorSelectedRow = id;
	    	dauthorUpdate.markUpdate(true, UpdateLevel.Data, "dauthorEditForm");
	    	dauthorEditDialog.show();
	    }
	}
	
	@Action(id = "authorkeySaveEdit", event="onclick")
	public void authorkeySaveEdit(){
		authorkeyEditsave();
	}
	
	@Action(id = "authorkeyCloseEdit", event="onclick")
	public void authorkeyCloseEdit(){
		dauthorEditDialog.close();
	}
	
	public void authorkeyEditsave(){
		AuthoritiesKey author = new AuthoritiesKey();
		author.setId(new Long(dauthorSelectedRow));
		author.setName(name);
		
		if(authorBo.isAvailableByValue(author,value)){
			author.setValue(value);
			authorBo.save(author);
			
			dauthorReload();
			dauthorEditDialog.close();
		}else{
			Browser.execClientScript("window.alert('"+messages.get("authorities.keyValue.confirm")+"');");
		}
	}
	
	@Action(id = "authorkeyDel", event = "onclick")
    public void authorkeyDel() {
		Object[] obj = authorGrid.getSelectedValues();
		if (obj == null || obj.length == 0) {
			Browser.execClientScript("window.alert('"+messages.get("authorities.sava.confirm")+"');");
			return;
		}
	    if (obj.length > 0) {
	    	AuthoritiesKey author = (AuthoritiesKey) obj[0];
	    	id = author.getId().toString();
	    	dauthorSelectedRow = id;
	    	
	    	authorBo.remove(new Long(dauthorSelectedRow));
	    	dauthorReload();
	    }
	}
	
	
	public void dauthorReload() {
		authorGrid.setSelections(new int[] { -1 });
		authorGrid.reload();
	}
	
	
}
