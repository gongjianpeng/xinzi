package cn.internalaudit.audit.entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @generated
 */
@Table(name = "AuthoritiesKey")
@Entity
public class AuthoritiesKey extends BaseEntity {

	/**
	 * @generated
	 * @describe
	 * @display
	 * @describe
	 * @display
	 */
	@Column
	private java.lang.String name;
	/**
	 * @generated
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "authoritiesKey")
	private java.util.List<cn.internalaudit.audit.entitys.Authorities> authoritiess = new java.util.ArrayList<cn.internalaudit.audit.entitys.Authorities>();
	/**
	 * @generated
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "authoritiesKey")
	private java.util.List<cn.internalaudit.audit.entitys.RolesAuthorities> rolesAuthoritiess = new java.util.ArrayList<cn.internalaudit.audit.entitys.RolesAuthorities>();

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentAuthoritiesKey")
	private AuthoritiesKey parentAuthoritiesKey;

	public AuthoritiesKey getParentAuthoritiesKey() {
		return parentAuthoritiesKey;
	}

	public String createFullName(AuthoritiesKey autorKey, String fullName) {
		if (fullName == null) {
			fullName = autorKey.getName();
		} else {
			fullName = autorKey.getName() + "-" + fullName;
		}
		AuthoritiesKey parentAutorKey = autorKey.getParentAuthoritiesKey();
		if (parentAutorKey != null) {
			fullName=createFullName(parentAutorKey, fullName);
		}

		return fullName;
	}

	public String getFullName() {
		return createFullName(this, null);
	}

	public void setParentAuthoritiesKey(AuthoritiesKey parentAuthoritiesKey) {
		this.parentAuthoritiesKey = parentAuthoritiesKey;
	}

	public List<AuthoritiesKey> getAuthoritiesKeys() {
		return authoritiesKeys;
	}

	public void setAuthoritiesKeys(List<AuthoritiesKey> authoritiesKeys) {
		this.authoritiesKeys = authoritiesKeys;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "parentAuthoritiesKey")
	private List<AuthoritiesKey> authoritiesKeys = new ArrayList<AuthoritiesKey>();
	/**
	 * @generated
	 * @describe
	 * @display
	 */
	@Column
	private java.lang.String value;

	/**
	 * @generated
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * @generated
	 */
	public void setName(java.lang.String value) {
		this.name = value;
	}

	/**
	 * @generated
	 */
	public void setKey(java.lang.String value) {
		this.value = value;
	}

	/**
	 * @generated
	 */
	public java.util.List<cn.internalaudit.audit.entitys.Authorities> getAuthoritiess() {
		return this.authoritiess;
	}

	/**
	 * @generated
	 */
	public void setAuthoritiess(
			java.util.List<cn.internalaudit.audit.entitys.Authorities> value) {
		this.authoritiess = value;
	}

	/**
	 * @generated
	 */
	public java.util.List<cn.internalaudit.audit.entitys.RolesAuthorities> getRolesAuthoritiess() {
		return this.rolesAuthoritiess;
	}

	/**
	 * @generated
	 */
	public void setRolesAuthoritiess(
			java.util.List<cn.internalaudit.audit.entitys.RolesAuthorities> value) {
		this.rolesAuthoritiess = value;
	}

	/**
	 * @generated
	 */
	public java.lang.String getValue() {
		return this.value;
	}

	/**
	 * @generated
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}

	public List<AuthoritiesKey> getChildren() {
		return this.getAuthoritiesKeys();
	}
}
