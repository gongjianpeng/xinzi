package cn.internalaudit.audit.pojo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Frame entity. 边框
 */
@Entity
@Table(name = "Plicalbum", catalog = "xinzi")
public class Plicalbum extends  BaseEntity implements java.io.Serializable {

	

	private String name;
	private String contents;
	private String filurl;
	
	
	public String getFilurl() {
		return filurl;
	}



	public void setFilurl(String filurl) {
		this.filurl = filurl;
	}


	private String type;
	private String field;
	private String field2;

	public String getField2() {
		return field2;
	}



	public void setField2(String field2) {
		this.field2 = field2;
	}


	private Timestamp crtime;
	private String code;
	private String remark;
	
	
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






	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}





	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}



	public Timestamp getCrtime() {
		return crtime;
	}



	public void setCrtime(Timestamp crtime) {
		this.crtime = crtime;
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


//
//	public Commodity getCommodity() {
//		return commodity;
//	}
//
//
//
//	public void setCommodity(Commodity commodity) {
//		this.commodity = commodity;
//	}




	// Constructors

	/** default constructor */
	public Plicalbum() {
	}

	
//	
//	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
//	Commodity commodity;

	

	
}