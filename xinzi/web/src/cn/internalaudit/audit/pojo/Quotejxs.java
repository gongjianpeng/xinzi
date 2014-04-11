package cn.internalaudit.audit.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Quotejxs entity. 报价，经销商
 */
@Entity
@Table(name = "Quotejxs", catalog = "xinzi")
public class Quotejxs extends  BaseEntity  implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer reality;
	private Integer discount;
	private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemark() {
		return remark;
	}

	private String remark;
	private String type;
	private String dengjiben;
	private String dengjiXiao;
	private String dengjiTong;
	private String dengjiao;

	// Constructors

	/** default constructor */
	public Quotejxs() {
	}

	/** full constructor */
	public Quotejxs(String name, Integer reality, Integer discount,
			Timestamp createDate, String createPerson, Long createPersonId,
			Integer isDelete, Timestamp lastEditDate, String lastEditPerson,
			Long lastEditPersonId, String org, String code, String remark,
			String type, String dengjiben, String dengjiXiao,
			String dengjiTong, String dengjiao) {
		this.name = name;
		this.reality = reality;
		this.discount = discount;
		this.code = code;
		this.remark = remark;
		this.type = type;
		this.dengjiben = dengjiben;
		this.dengjiXiao = dengjiXiao;
		this.dengjiTong = dengjiTong;
		this.dengjiao = dengjiao;
	}


	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "reality")
	public Integer getReality() {
		return this.reality;
	}

	public void setReality(Integer reality) {
		this.reality = reality;
	}

	@Column(name = "discount")
	public Integer getDiscount() {
		return this.discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "dengjiben")
	public String getDengjiben() {
		return this.dengjiben;
	}

	public void setDengjiben(String dengjiben) {
		this.dengjiben = dengjiben;
	}

	@Column(name = "dengjiXiao")
	public String getDengjiXiao() {
		return this.dengjiXiao;
	}

	public void setDengjiXiao(String dengjiXiao) {
		this.dengjiXiao = dengjiXiao;
	}

	@Column(name = "dengjiTong")
	public String getDengjiTong() {
		return this.dengjiTong;
	}

	public void setDengjiTong(String dengjiTong) {
		this.dengjiTong = dengjiTong;
	}

	@Column(name = "dengjiao")
	public String getDengjiao() {
		return this.dengjiao;
	}

	public void setDengjiao(String dengjiao) {
		this.dengjiao = dengjiao;
	}

}