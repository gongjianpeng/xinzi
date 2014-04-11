package cn.internalaudit.audit.entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @generated
 */
@Table(name = "Person")
@SuppressWarnings("serial")
@Entity
public class Person extends BaseEntity {
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String name;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String englishName;
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
	private java.lang.String sex;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String cardType;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String cardNumber;
	/**
	 * @generated
	 * @describe
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String phone;
	/**
     * @generated
     * @describe
     * @display 
     */
    @Column(length = 255)
    private java.lang.String telphone;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String address;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String email;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String remark;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String loginName;
	
	
	@Column
	private String BeginDate;//测试开始时间
	
	public String getBeginDate() {
		return BeginDate;
	}

	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	@Column
	private String EndDate;//测试结束时间
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String password;
	@Column(length = 20)
    private java.lang.Boolean activated;//是否激活
    @Column(length = 255)
    private java.lang.String randomCode;// 随机码(激活帐户时使用)
	
	public java.lang.String getRandomCode()
    {
        return randomCode;
    }

    public void setRandomCode(java.lang.String randomCode)
    {
        this.randomCode = randomCode;
    }

    public java.lang.Boolean getActivated()
    {
        return activated;
    }

    public void setActivated(java.lang.Boolean activated)
    {
        this.activated = activated;
    }
    @Column(length = 255)
	private java.lang.String filelogo;
	
	@Column(length = 255)
	private java.lang.String filezhuye;
	
	public java.lang.String getFilelogo() {
		return filelogo;
	}

	public void setFilelogo(java.lang.String filelogo) {
		this.filelogo = filelogo;
	}

	public java.lang.String getFilezhuye() {
		return filezhuye;
	}

	public void setFilezhuye(java.lang.String filezhuye) {
		this.filezhuye = filezhuye;
	}
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(columnDefinition = "int")
	private java.lang.Boolean enable = true;
	
	@Column(columnDefinition="character(10) default 'person'")
	private String classes = "person";
	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}
	/**
	 * @generated
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "person")
	private java.util.List<cn.internalaudit.audit.entitys.Authorities> authoritiess = new java.util.ArrayList<cn.internalaudit.audit.entitys.Authorities>();
	/**
	 * @generated
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "department")
	private cn.internalaudit.audit.entitys.Department department;
	
	
	private String organizationId;
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	private String departmentId;
	private String organizationName;
	private String departmentName;
	/**
	 * @generated
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, targetEntity = cn.internalaudit.audit.entitys.Roles.class)
	@JoinTable(inverseJoinColumns = { @JoinColumn(name = "roless") }, name = "Person_Roles", joinColumns = { @JoinColumn(name = "persons") })
	private java.util.List<cn.internalaudit.audit.entitys.Roles> roless = new java.util.ArrayList<cn.internalaudit.audit.entitys.Roles>();

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "person")
	private java.util.List<cn.internalaudit.audit.entitys.LogInfo> logInfos = new java.util.ArrayList<cn.internalaudit.audit.entitys.LogInfo>();


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
	public java.lang.String getEnglishName() {
		return this.englishName;
	}



	/**
	 * @generated
	 */
	public void setEnglishName(java.lang.String value) {
		this.englishName = value;
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
	public java.lang.String getSex() {
		return this.sex;
	}

	/**
	 * @generated
	 */
	public void setSex(java.lang.String value) {
		this.sex = value;
	}

	/**
	 * @generated
	 */
	public java.lang.String getCardType() {
		return this.cardType;
	}

	/**
	 * @generated
	 */
	public void setCardType(java.lang.String value) {
		this.cardType = value;
	}

	/**
	 * @generated
	 */
	public java.lang.String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * @generated
	 */
	public void setCardNumber(java.lang.String value) {
		this.cardNumber = value;
	}
	/**
     * @generated
     */
	public java.lang.String getTelphone()
    {
        return telphone;
    }
	/**
     * @generated
     */
    public void setTelphone(java.lang.String telphone)
    {
        this.telphone = telphone;
    }

    /**
	 * @generated
	 */
	public java.lang.String getPhone() {
		return this.phone;
	}

	/**
	 * @generated
	 */
	public void setPhone(java.lang.String value) {
		this.phone = value;
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
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * @generated
	 */
	public void setEmail(java.lang.String value) {
		this.email = value;
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
	public java.lang.String getLoginName() {
		return this.loginName;
	}

	/**
	 * @generated
	 */
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}

	/**
	 * @generated
	 */
	public java.lang.String getPassword() {
		return this.password;
	}

	/**
	 * @generated
	 */
	public void setPassword(java.lang.String value) {
		this.password = value;
	}

	/**
	 * @generated
	 */
	public java.lang.Boolean getEnable() {
		return this.enable;
	}

	/**
	 * @generated
	 */
	public void setEnable(java.lang.Boolean value) {
		this.enable = value;
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
	public cn.internalaudit.audit.entitys.Department getDepartment() {
		return this.department;
	}

	/**
	 * @generated
	 */
	public void setDepartment(cn.internalaudit.audit.entitys.Department value) {
		this.department = value;
	}

	/**
	 * @generated
	 */
	public java.util.List<cn.internalaudit.audit.entitys.Roles> getRoless() {
		return this.roless;
	}

	/**
	 * @generated
	 */
	public void setRoless(java.util.List<cn.internalaudit.audit.entitys.Roles> value) {
		this.roless = value;
	}

	/**
	 * @generated
	 */
	public java.util.List<cn.internalaudit.audit.entitys.LogInfo> getLogInfos() {
		return this.logInfos;
	}

	/**
	 * @generated
	 */
	public void setLogInfos(
			java.util.List<cn.internalaudit.audit.entitys.LogInfo> value) {
		this.logInfos = value;
	}
	public List getChildren() {
		List list = new ArrayList();
		return list;
	}
}
