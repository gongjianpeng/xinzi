package cn.internalaudit.audit.beans.index;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.BeforeRender;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.LocalString;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.annotation.SaveState;
import org.operamasks.faces.developer.util.FacesUtils;
import org.operamasks.faces.user.util.Browser;

import cn.internalaudit.audit.utils.IdcardValidator;

/**
 * 
 * @author
 */
@ManagedBean(name = "mainBean", scope = ManagedBeanScope.REQUEST)
public class MainBean {
	private String departmentName;
	
	@Bind
	private UIForm queryForm;
	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;
	
	@LocalString(basename = "messages.messages_infom")
	private Map<String, String> messages;
	/**
	 * 
	 */
	@Bind
	@ManagedProperty
	public String txtNumber;

	/**
	 * 
	 */
	@ManagedProperty
	@Bind
	public String txtCardId;

	//@Validate(message = "#{msgs['main.lengthValidate']}")
	public boolean validateTxtCardId(String strCardId) {
		IdcardValidator iv = new IdcardValidator();
		if (iv.isValidatedAllIdcard(strCardId)) {
			return true;
		}
		return false;
	}

	/**
	 *
	 */
	@Bind
	@ManagedProperty
	public String txtLoanerName;
	/**
	 * 
	 */
	@Bind
	@ManagedProperty
	//@ValidateLength(minimum = 3, maximum = 20, message = "#{msgs['main.lengthValidate']}")
	public String txtRequNumber;
	/**
	 * 
	 */
	@Bind
	@ManagedProperty
	public String txtTelPhone;
	/**
	 * 
	 */
	@Bind
	@ManagedProperty
	//@ValidateLength(minimum = 2, maximum = 30, message = "#{msgs['main.lengthValidate2']}")
	public String txtCompanyName;

	/**
	 * 
	 */
	@SaveState
	@ManagedProperty
	private String queryState;

	/**
	 * 
	 * @param isPostBack
	 */
	@BeforeRender
	protected void beforeRender(boolean isPostBack) {
		queryState = "basic";
	}

	@Action
	public void advancedSearch() {
		queryState = "advanced";
	}

	/**
	 * 
	 * @return
	 */
	@Action
	public String mistinessSearch() {
		if (txtCardId.trim().equals("") && txtLoanerName.trim().equals("")
				&& txtRequNumber.trim().equals("")
				&& txtTelPhone.trim().equals("")
				&& txtCompanyName.trim().equals("")) {

			Browser.alert(messages.get("main.searchValidate"));
			return "";
		}
		return "pages/query/queryMistiness.xhtml";
	}

	/**
	 * 
	 * @return
	 */
	@Action
	public String exactnessSearch() {
		queryState = "basic";

		if (txtNumber.trim().equals("")) {
			Browser.alert(messages.get("main.searchValidate3"));
			Browser.execClientScript("txtNumber.focus();");
			return "";
		}

		return "pages/query/queryExactness.xhtml";
	}

	/**
	 * 
	 */
	@Action
	public void cancel() {
		txtCardId = "";
		txtCompanyName = "";
		txtLoanerName = "";
		txtRequNumber = "";
		txtTelPhone = "";
		clearSubmitValue(queryForm);
		queryState = "basic";
	}

	/**
	 * 
     * 
     */
	private void clearSubmitValue(UIComponent root) {
		Iterator<UIComponent> children = FacesUtils.createChildrenIterator(
				root, false);
		while (children.hasNext()) {
			UIComponent c = children.next();
			if (c instanceof EditableValueHolder) {
				((EditableValueHolder) c).setSubmittedValue(null);
				((EditableValueHolder) c).setValue("");
			}
		}
	}

}
