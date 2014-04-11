package cn.internalaudit.audit.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Order", catalog = "xinzi")
public class Order extends  BaseEntity implements java.io.Serializable {

	// Fields

	private Long admId;
	private String number;
	private String ordertime;
	private String deliverytime;
	private String address;
	private String userName;
	private String comments;
	private String code;
	private String remark;

	/**
	 * 新添加字段Begin
	 */
	private String company;
	private String zip;
	private String fax;
	private String telephone;
	private String mobile;
	/**
	 * 新添加字段End
	 */
	// Constructors

	/** default constructor */
	public Order() {
	}

	/** full constructor */
	public Order(Long admId, String number, String ordertime,
			String deliverytime, String address, String comments,
			Timestamp createDate, String createPerson, Long createPersonId,
			Integer isDelete, Timestamp lastEditDate, String lastEditPerson,
			Long lastEditPersonId, String org, String code, String remark,
			String company, String zip, String fax, String telephone, String mobile
			) {
		this.admId = admId;
		this.number = number;
		this.ordertime = ordertime;
		this.deliverytime = deliverytime;
		this.address = address;
		this.comments = comments;
		this.code = code;
		this.remark = remark;
		this.company=company;
		this.zip=zip;
		this.fax=fax;
		this.telephone=telephone;
		this.telephone=telephone;
		
	}


	@Column(name = "adm_id")
	public Long getAdmId() {
		return this.admId;
	}

	public void setAdmId(Long admId) {
		this.admId = admId;
	}

	@Column(name = "number")
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "ordertime", length = 19)
	public String getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	@Column(name = "deliverytime", length = 19)
	public String getDeliverytime() {
		return this.deliverytime;
	}

	public void setDeliverytime(String deliverytime) {
		this.deliverytime = deliverytime;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}



	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}