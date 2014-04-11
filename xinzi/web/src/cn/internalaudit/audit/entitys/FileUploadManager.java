package cn.internalaudit.audit.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.internalaudit.audit.base.IEntity;


/** 
 * @generated 
 * 文件管理
 */
@Table(name = "FileUploadManager")
@SuppressWarnings("serial")
@Entity
public class FileUploadManager implements java.io.Serializable, IEntity {
	/** 
	 * @generated  
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private java.lang.Long id;
	/** 
	 * @generated
	 */
	@Column
	private java.lang.String path;
	/** 
	 * @generated
	 */
	@Column
	private java.lang.String filename;
	/** 
	 * @generated
	 */
	@Column
	private java.lang.String title;
	
	@Column
	private java.lang.String fileSize;
	public java.lang.String getFileSize() {
		return fileSize;
	}

	public void setFileSize(java.lang.String fileSize) {
		this.fileSize = fileSize;
	}

	/** 
	 * @generated
	 */
	@Column
	private java.lang.String remark;
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
	 */
	@Column
	private java.lang.Boolean isDelete;
	/** 
	 * @generated类型，文件，文件夹
	 */
	@Column
	@Enumerated(EnumType.ORDINAL)
	private cn.internalaudit.audit.constant.FileType type;
	/** 
	 * @generated
	 * 
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "parent")
	private java.util.List<cn.internalaudit.audit.entitys.FileUploadManager> fileUploadManagers = new java.util.ArrayList<cn.internalaudit.audit.entitys.FileUploadManager>();
	/** 
	 * @generated
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "parent")
	private cn.internalaudit.audit.entitys.FileUploadManager parent;

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
	public java.lang.String getPath() {
		return this.path;
	}

	/** 
	 * @generated
	 */
	public void setPath(java.lang.String value) {
		this.path = value;
	}

	/** 
	 * @generated
	 */
	public java.lang.String getFilename() {
		return this.filename;
	}

	/** 
	 * @generated
	 */
	public void setFilename(java.lang.String value) {
		this.filename = value;
	}

	/** 
	 * @generated
	 */
	public java.lang.String getTitle() {
		return this.title;
	}

	/** 
	 * @generated
	 */
	public void setTitle(java.lang.String value) {
		this.title = value;
	}

	/** 
	 * @generated
	 */
	public java.lang.String getRemark() {
		return this.remark;
	}

	/** 
	 * @generated
	 */
	public void setRemark(java.lang.String value) {
		this.remark = value;
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
	 */
	public java.lang.Boolean getIsDelete() {
		return this.isDelete;
	}

	/** 
	 * @generated
	 */
	public void setIsDelete(java.lang.Boolean value) {
		this.isDelete = value;
	}

	/** 
	 * @generated
	 */
	public cn.internalaudit.audit.constant.FileType getType() {
		return this.type;
	}

	/** 
	 * @generated
	 */
	public void setType(cn.internalaudit.audit.constant.FileType value) {
		this.type = value;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public java.util.List<cn.internalaudit.audit.entitys.FileUploadManager> getFileUploadManagers() {
		return this.fileUploadManagers;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setFileUploadManagers(
			java.util.List<cn.internalaudit.audit.entitys.FileUploadManager> value) {
		this.fileUploadManagers = value;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public cn.internalaudit.audit.entitys.FileUploadManager getParent() {
		return this.parent;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setParent(cn.internalaudit.audit.entitys.FileUploadManager value) {
		this.parent = value;
	}
}
