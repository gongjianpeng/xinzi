package cn.internalaudit.audit.entitys;

import javax.persistence.*;

/** 
 * @generated 
 * 
 */
@Table(name = "LogInfo")
@SuppressWarnings("serial")
@Entity
public class LogInfo implements java.io.Serializable {
	/** 
	 * @generated  
	 */
	@Column
	@Id
	private java.lang.Long id;
	/** 
	 * @generated
	 * 
	 * @describe 
	 * @display 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private java.util.Date logDate;
	/** 
	 * @generated
	 * 
	 * @display 
	 * @describe 
	 */
	@Column
	private java.lang.String logLevel;
	/** 
	 * @generated
	 * 
	 * @describe 
	 * @display 
	 */
	@Column
	private java.lang.String logContent;
	/** 
	 * @generated
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "person")
	private cn.internalaudit.audit.entitys.Person person;
	/** 
	 * @generated
	 * 
	 */
	@Column
	private java.lang.Boolean isDelete = false;

	/** 
	 * @generated
	 * 
	 * 
	 */
	public java.lang.Long getId() {
		return this.id;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setId(java.lang.Long value) {
		this.id = value;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public java.util.Date getLogDate() {
		return this.logDate;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setLogDate(java.util.Date value) {
		this.logDate = value;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public java.lang.String getLogLevel() {
		return this.logLevel;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setLogLevel(java.lang.String value) {
		this.logLevel = value;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public java.lang.String getLogContent() {
		return this.logContent;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setLogContent(java.lang.String value) {
		this.logContent = value;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public cn.internalaudit.audit.entitys.Person getPerson() {
		return this.person;
	}

	/** 
	 * @generated
	 * 
	 * 
	 */
	public void setPerson(cn.internalaudit.audit.entitys.Person value) {
		this.person = value;
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
