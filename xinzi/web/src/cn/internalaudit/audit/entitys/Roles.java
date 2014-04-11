package cn.internalaudit.audit.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** 
 * @generated 
 */
@Table(name = "Roles")
@SuppressWarnings("serial")
@Entity
public class Roles extends BaseEntity implements java.io.Serializable {
	
	/** 
	 * @generated
	 * @describe     
	 * @display  
	 */
	@Column(length = 255)
	private java.lang.String name;
	
	/**
	 * 数据权限级别：all 全部、org 公司级、dept 部门级、project 项目级、self 员工级
	 */
	@Column(columnDefinition="character(50) default 'self'")
	private String dataAuthorLevel;
	
	/** 
	 * @generated
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "roles")
	private java.util.List<cn.internalaudit.audit.entitys.RolesAuthorities> rolesAuthoritiess = new java.util.ArrayList<cn.internalaudit.audit.entitys.RolesAuthorities>();
	/** 
	 * @generated
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, mappedBy = "roless", targetEntity = cn.internalaudit.audit.entitys.Person.class)
	private java.util.List<cn.internalaudit.audit.entitys.Person> persons = new java.util.ArrayList<cn.internalaudit.audit.entitys.Person>();
	
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

	public String getDataAuthorLevel() {
		return dataAuthorLevel;
	}

	public void setDataAuthorLevel(String dataAuthorLevel) {
		this.dataAuthorLevel = dataAuthorLevel;
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
	public java.util.List<cn.internalaudit.audit.entitys.Person> getPersons() {
		return this.persons;
	}

	/** 
	 * @generated
	 */
	public void setPersons(java.util.List<cn.internalaudit.audit.entitys.Person> value) {
		this.persons = value;
	}
}
