package cn.internalaudit.audit.beans.index;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.annotation.ManagedProperty;
import org.operamasks.faces.component.html.impl.UIIFrame;

import cn.internalaudit.audit.security.LoginInfo;

import com.opensymphony.xwork2.inject.Inject;

@ManagedBean(name = "indexBean", scope = ManagedBeanScope.REQUEST)
public class IndexBean implements Serializable {
	@Inject("#{loginInfo}")
	private LoginInfo loginInfo;
	@Bind
	@ManagedProperty("#{loginInfo.loginPerson.name}")
	private String loginName;
	@Bind
	@ManagedProperty("#{loginInfo.loginTime}")
	private Date loginTime;
	
	@Bind
	@ManagedProperty
	public UIIFrame middleIframe;	
	
	@ManagedProperty("#{loginInfo.locale}")
	private Locale locale;
}
