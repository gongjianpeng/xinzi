package cn.internalaudit.audit.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Elses entity. diy 模版数据
 */
@Entity
@Table(name = "DiyModel", catalog = "xinzi")
public class DiyModel  extends  BaseEntity implements java.io.Serializable {

	// Fields
	@Column(length = 255)
	private String codetype;
	@Column(length = 255)
	private String name;
	@Column(length = 255)
	private String img;
	@Column(length = 255)
	private String type;
	@Column(length = 255)
	private String scale;
	@Column(length = 255)
	private String x;
	@Column(length = 255)
	private String y;

	
	@Column(length = 255)
	private String typename;

	@Column(length = 255)
	private String typenameid;
	
	@Column(length = 255)
	private String color;
	
	
	@Column(length = 255)
	private String useraddmodel;
	
	@Column(length = 255)
	private String userfordmodelid;
	
	
	@Column(length = 255)
	private String userfortype;


	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypenameid() {
		return typenameid;
	}

	public void setTypenameid(String typenameid) {
		this.typenameid = typenameid;
	}

	public String getCodetype() {
		return codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}



}