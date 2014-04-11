package cn.internalaudit.audit.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Doorstyle entity. 门面款式
 */
@SuppressWarnings("serial")
/**
 * @author a
 *
 */
@Entity
@Table(name = "Doorstyle")
public class Doorstyle extends  BaseEntity implements java.io.Serializable {

	// Fields

	private String name;
	private String contents;
	private String sname;
	private String type;
	private String field2;
	private String field;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	private String code;
	private String remark;

	// Constructors

	/** default constructor */
	public Doorstyle() {
	}


	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	Commodity commodity;



	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}


	
}