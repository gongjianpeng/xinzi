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

/**
 * @generated
 */
@Table(name = "Department")
@SuppressWarnings("serial")
@Entity
public class Department extends BaseEntity implements Serializable {
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
	
	@Column
	private java.lang.String address;
	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String shortName;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String remark;
	
	@Column(columnDefinition="character(10) default 'department'")
	private String classes = "department";
	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	/**
	 * @generated
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST}, mappedBy = "department")
	private java.util.List<cn.internalaudit.audit.entitys.Person> persons = new java.util.ArrayList<cn.internalaudit.audit.entitys.Person>();
	/**
	 * @generated
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "organization")
	private cn.internalaudit.audit.entitys.Organization organization;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "parentDepartment")
	private List<Department> departments = new ArrayList<Department>();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "parentDepartment")
	private cn.internalaudit.audit.entitys.Department parentDepartment;
	
	public cn.internalaudit.audit.entitys.Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(
			cn.internalaudit.audit.entitys.Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}


	
	
	public cn.internalaudit.audit.entitys.Organization getOrganization() {
		return organization;
	}

	public void setOrganization(
			cn.internalaudit.audit.entitys.Organization organization) {
		this.organization = organization;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

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
	public java.lang.String getShortName() {
		return this.shortName;
	}

	/**
	 * @generated
	 */
	public void setShortName(java.lang.String value) {
		this.shortName = value;
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
	public java.util.List<cn.internalaudit.audit.entitys.Person> getPersons() {
		return this.persons;
	}

	/**
	 * @generated
	 */
	public void setPersons(
			java.util.List<cn.internalaudit.audit.entitys.Person> value) {
		this.persons = value;
	}
	
	public List getChildren() {
		return this.getDepartments();
	}
	
	public List getAllChildren() {
		List list = new ArrayList();
		List<Person> persons = this.getPersons();
		for (Person person : persons) {
			if (person.getEnable()) {
				list.add(person);
			}
		}
		list.addAll(this.getDepartments());
		return list;
	}
}
