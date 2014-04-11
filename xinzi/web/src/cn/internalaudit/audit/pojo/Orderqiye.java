package cn.internalaudit.audit.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Orderqiye entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Orderqiye", catalog = "xinzi")
public class Orderqiye  extends  BaseEntity implements java.io.Serializable {

	// Fields

	private String address;
	private Long admId;
	private String comments;
	private Timestamp deliverytime;
	private String number;
	private Timestamp ordertime;

	// Constructors

	/** default constructor */
	public Orderqiye() {
	}

	/** full constructor */
	public Orderqiye(Timestamp createDate, String createPerson,
			Long createPersonId, Integer isDelete, Timestamp lastEditDate,
			String lastEditPerson, Long lastEditPersonId, String org,
			String address, Long admId, String comments,
			Timestamp deliverytime, String number, Timestamp ordertime) {
		this.address = address;
		this.admId = admId;
		this.comments = comments;
		this.deliverytime = deliverytime;
		this.number = number;
		this.ordertime = ordertime;
	}




	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "adm_id")
	public Long getAdmId() {
		return this.admId;
	}

	public void setAdmId(Long admId) {
		this.admId = admId;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "deliverytime", length = 19)
	public Timestamp getDeliverytime() {
		return this.deliverytime;
	}

	public void setDeliverytime(Timestamp deliverytime) {
		this.deliverytime = deliverytime;
	}

	@Column(name = "number")
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "ordertime", length = 19)
	public Timestamp getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

}