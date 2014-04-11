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
@Table(name = "Department2")
@SuppressWarnings("serial")
@Entity
public class Department2 extends BaseEntity implements Serializable {
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
	
	
	@Column
	private java.lang.String inputname;
	
	
	@Column
	private java.lang.String inputname2;
	
	
	@Column
	private java.lang.String inputname3;
	
//	@Column
//	private java.lang.String organization2;
//	
//	public java.lang.String getOrganization2() {
//		return organization2;
//	}
//
//	public void setOrganization2(java.lang.String organization2) {
//		this.organization2 = organization2;
//	}

	public java.lang.String getInputname() {
		return inputname;
	}

	public void setInputname(java.lang.String inputname) {
		this.inputname = inputname;
	}

	public java.lang.String getInputname2() {
		return inputname2;
	}

	public void setInputname2(java.lang.String inputname2) {
		this.inputname2 = inputname2;
	}

	public java.lang.String getInputname3() {
		return inputname3;
	}

	public void setInputname3(java.lang.String inputname3) {
		this.inputname3 = inputname3;
	}

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
	private cn.internalaudit.audit.entitys.Organization2 organization;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "parentDepartment")
	private List<Department2> departments = new ArrayList<Department2>();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "parentDepartment")
	private cn.internalaudit.audit.entitys.Department2 parentDepartment;
	
	public cn.internalaudit.audit.entitys.Department2 getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(
			cn.internalaudit.audit.entitys.Department2 parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public List<Department2> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department2> departments) {
		this.departments = departments;
	}


	
	
	public cn.internalaudit.audit.entitys.Organization2 getOrganization() {
		return organization;
	}

	public void setOrganization(
			cn.internalaudit.audit.entitys.Organization2 organization) {
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
