package cn.internalaudit.audit.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.internalaudit.audit.base.IEntity;

/**
 *@generated
 */
@MappedSuperclass
public abstract class BaseEntity implements IEntity, Serializable {

	/**
	 *@generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column
	private String org;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private java.util.Date createDate;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date lastEditDate;
	@Column
	private String createPerson;
	@Column
	private Long createPersonId;
	@Column
	private Long lastEditPersonId;
	@Column
	private String lastEditPerson;
	@Column(columnDefinition="int")
	private java.lang.Boolean isDelete=false;

	public java.util.Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(java.util.Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getLastEditPerson() {
		return lastEditPerson;
	}

	public void setLastEditPerson(String lastEditPerson) {
		this.lastEditPerson = lastEditPerson;
	}

	/**
	 *@generated
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 *@generated
	 */
	public void setId(Long value) {
		this.id = value;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.lang.Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(java.lang.Boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public Long getCreatePersonId() {
		// TODO Auto-generated method stub
		return createPersonId;
	}

	@Override
	public Long getLastEditPersonId() {
		// TODO Auto-generated method stub
		return lastEditPersonId;
	}

	@Override
	public void setCreatePersonId(Long value) {
		this.createPersonId = value;

	}

	@Override
	public void setLastEditPersonId(Long value) {
		this.lastEditPersonId = value;
		// TODO Auto-generated method stub

	}
}