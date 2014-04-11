package cn.internalaudit.audit.entitys;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.internalaudit.audit.base.IEntity;

import javax.persistence.*;

/** 
 * @generated
 */
@Table(name = "Authorities")
@SuppressWarnings("serial")
@Entity
public class Authorities implements Serializable, IEntity {
	/** 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private java.lang.Long id;
	/** 
	 * @generated
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "person")
	private cn.internalaudit.audit.entitys.Person person;
	/** 
	 * @generated
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "authoritieskey")
	private cn.internalaudit.audit.entitys.AuthoritiesKey authoritiesKey;
	/** 
	 * @generated
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private java.util.Date createDate;
	/** 
	 * @generated
	 */
	@Column
	private java.lang.String createPerson;
	/** 
	 * @generated
	 */
	@Column
	private java.lang.Long createPersonId;
	/** 
	 * @generated
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private java.util.Date lastEditDate;
	/** 
	 * @generated
	 */
	@Column
	private java.lang.String lastEditPerson;
	/** 
	 * @generated
	 */
	@Column
	private java.lang.Long lastEditPersonId;
	/** 
	 * @generated
	 * 
	 */
	@Column
	private java.lang.Boolean isDelete = false;

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
	public java.lang.Long getId() {
		return this.id;
	}

	/** 
	 * @generated
	 */
	public void setId(java.lang.Long value) {
		this.id = value;
	}

	/** 
	 * @generated
	 */
	public cn.internalaudit.audit.entitys.Person getPerson() {
		return this.person;
	}

	/** 
	 * @generated
	 */
	public void setPerson(cn.internalaudit.audit.entitys.Person value) {
		this.person = value;
	}

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
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/** 
	 * @generated
	 */
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	/** 
	 * @generated
	 */
	public java.lang.String getCreatePerson() {
		return this.createPerson;
	}

	/** 
	 * @generated
	 */
	public void setCreatePerson(java.lang.String value) {
		this.createPerson = value;
	}

	/** 
	 * @generated
	 */
	public java.lang.Long getCreatePersonId() {
		return this.createPersonId;
	}

	/** 
	 * @generated
	 */
	public void setCreatePersonId(java.lang.Long value) {
		this.createPersonId = value;
	}

	/** 
	 * @generated
	 */
	public java.util.Date getLastEditDate() {
		return this.lastEditDate;
	}

	/** 
	 * @generated
	 */
	public void setLastEditDate(java.util.Date value) {
		this.lastEditDate = value;
	}

	/** 
	 * @generated
	 */
	public java.lang.String getLastEditPerson() {
		return this.lastEditPerson;
	}

	/** 
	 * @generated
	 */
	public void setLastEditPerson(java.lang.String value) {
		this.lastEditPerson = value;
	}

	/** 
	 * @generated
	 */
	public java.lang.Long getLastEditPersonId() {
		return this.lastEditPersonId;
	}

	/** 
	 * @generated
	 */
	public void setLastEditPersonId(java.lang.Long value) {
		this.lastEditPersonId = value;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public java.lang.Boolean getIsDelete() {
		return this.isDelete;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setIsDelete(java.lang.Boolean value) {
		this.isDelete = value;
	}
}
