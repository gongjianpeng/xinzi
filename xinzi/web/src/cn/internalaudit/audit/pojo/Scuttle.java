package cn.internalaudit.audit.pojo;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Scuttle entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Scuttle", catalog = "xinzi")
public class Scuttle  extends  BaseEntity  implements java.io.Serializable {

	// Fields

	private String name;
	private String contents;
	private String sname;
	private String type;
	private String field2;
	private String field;
	private String code;
	private String remark;

	// Constructors

	/** default constructor */
	public Scuttle() {
	}

	/** full constructor */
	public Scuttle(String name, String contents, String sname, String type,
			String field2, String field, Timestamp crtime,
			Timestamp createDate, String createPerson, Long createPersonId,
			Integer isDelete, Timestamp lastEditDate, String lastEditPerson,
			Long lastEditPersonId, String org, String code, String remark,
			Set<Commodity> commodities) {
		this.name = name;
		this.contents = contents;
		this.sname = sname;
		this.type = type;
		this.field2 = field2;
		this.field = field;
		this.code = code;
		this.remark = remark;
	}

//	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
//	Commodity commodity;
	// Property accessors


	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	

//	public Commodity getCommodity() {
//		return commodity;
//	}
//
//	public void setCommodity(Commodity commodity) {
//		this.commodity = commodity;
//	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "contents")
	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Column(name = "sname")
	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "field2")
	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	@Column(name = "field")
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
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


}