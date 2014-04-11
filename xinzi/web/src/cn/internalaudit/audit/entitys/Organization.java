package cn.internalaudit.audit.entitys;

import java.io.Serializable;
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

import org.hibernate.annotations.ForeignKey;

import cn.internalaudit.audit.base.IEntity;

/**
 * @generated
 */
@Table(name = "Organization")
@SuppressWarnings("serial")
@Entity
public class Organization extends BaseEntity implements Serializable, IEntity {
	/**
	 * @generated
	 * @describe
	 * @display
	 */
	@Column(length = 255)
	private java.lang.String code;
	/**
	 * @generated
	 * @describe
	 * @display
	 */
	@Column(length = 255)
	private java.lang.String name;
	/**
	 * @generated
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "organization")
	private java.util.List<cn.internalaudit.audit.entitys.Department> departments = new java.util.ArrayList<cn.internalaudit.audit.entitys.Department>();
	

	
	/**
	 * @generated
	 * @describe
	 * @display
	 */
	@Column
	private java.lang.String remark;
	/**
	 * @generated
	 */

	/**
	 * @generated
	 * @describe
	 * @display
	 */
	@Column
	private java.lang.String address;

	/**
	 * @generated
	 * 
	 * @describe
	 * @display
	 */
	@Column(columnDefinition = "int")
	private java.lang.Boolean isHeadquarters;

	@Column(columnDefinition="character(10) default 'company'")
	private String classes = "company";

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public java.util.List<cn.internalaudit.audit.entitys.Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(
			java.util.List<cn.internalaudit.audit.entitys.Organization> organizations) {
		this.organizations = organizations;
	}

	public cn.internalaudit.audit.entitys.Organization getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(
			cn.internalaudit.audit.entitys.Organization parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentOrganization")
	private java.util.List<cn.internalaudit.audit.entitys.Organization> organizations = new java.util.ArrayList<cn.internalaudit.audit.entitys.Organization>();
	/**
	 *@generated
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentOrganization")
	@ForeignKey(name = "ORGANIZATION_PARENTORGANIZATION")
	private cn.internalaudit.audit.entitys.Organization parentOrganization;

	/**
	 * @generated
	 */
	public java.lang.String getCode() {
		return this.code;
	}

	/**
	 * @generated
	 */
	public void setCode(java.lang.String value) {
		this.code = value;
	}

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
	public java.util.List<cn.internalaudit.audit.entitys.Department> getDepartments() {
		return this.departments;
	}

	/**
	 * @generated
	 */
	public void setDepartments(
			java.util.List<cn.internalaudit.audit.entitys.Department> value) {
		this.departments = value;
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
	public java.lang.String getAddress() {
		return this.address;
	}

	/**
	 * @generated
	 */
	public void setAddress(java.lang.String value) {
		this.address = value;
	}

	/**
	 * @generated
	 * 
	 * 
	 */
	public java.lang.Boolean getIsHeadquarters() {
		return this.isHeadquarters;
	}

	/**
	 * @generated
	 * 
	 * 
	 */
	public void setIsHeadquarters(java.lang.Boolean value) {
		this.isHeadquarters = value;
	}

	public List getChildren() {
		return this.getOrganizations();
	}

	public List getAllChildren() {
		List list = new ArrayList();
		List<Department> depts = this.getDepartments();
		for (Department dept : depts) {
			if (!dept.getIsDelete()) {
				list.add(dept);
			}
		}
		List<Organization> orgs = this.getOrganizations();
		for (Organization org : orgs) {
			if (!org.getIsDelete()) {
				list.add(org);
			}
		}
		return list;
	}



}
