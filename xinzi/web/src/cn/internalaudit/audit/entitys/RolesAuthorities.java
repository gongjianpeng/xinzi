package cn.internalaudit.audit.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** 
 * @generated
 */
@Table(name = "RolesAuthorities")
@SuppressWarnings("serial")
@Entity
public class RolesAuthorities extends BaseEntity {
	/** 
	 * @generated
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "authoritieskey")
	private cn.internalaudit.audit.entitys.AuthoritiesKey authoritiesKey;
	/** 
	 * @generated
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "roles")
	private cn.internalaudit.audit.entitys.Roles roles;
	

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	@Column(length = 255, name = "")
	private java.lang.String name;

	/** 
	 * @generated
	 */
	public cn.internalaudit.audit.entitys.AuthoritiesKey getAuthoritiesKey() {
		return this.authoritiesKey;
	}

	/** 
	 * @generated
	 */
	public void setAuthoritiesKey(cn.internalaudit.audit.entitys.AuthoritiesKey value) {
		this.authoritiesKey = value;
	}

	/** 
	 * @generated
	 */
	public cn.internalaudit.audit.entitys.Roles getRoles() {
		return this.roles;
	}

	/** 
	 * @generated
	 */
	public void setRoles(cn.internalaudit.audit.entitys.Roles value) {
		this.roles = value;
	}

}
