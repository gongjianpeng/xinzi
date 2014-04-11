package cn.internalaudit.audit.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import cn.internalaudit.audit.entitys.Person;
@SuppressWarnings("all")
public class LoginInfo implements Serializable {
	/********
	 * 
	 */
	private Person loginPerson;

	/********
	 * 
	 */
	private Date loginTime;

	private Locale locale;
	
	private Set dataLevelSet;

	public Set getDataLevelSet() {
		return dataLevelSet;
	}

	public void setDataLevelSet(Set dataLevelSet) {
		this.dataLevelSet = dataLevelSet;
	}

	public Person getLoginPerson() {
		return loginPerson;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setLoginPerson(Person loginPerson) {
		this.loginPerson = loginPerson;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}
