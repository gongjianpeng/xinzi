package cn.internalaudit.audit.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * @generated
 */
@Table(name = "DataDictionary")
@SuppressWarnings("serial")
@Entity
public class DataDictionary extends BaseEntity implements Serializable{

	/** 
	 * @generated
	 * @describe     
	 * @display  
	 */
	private java.lang.String type;
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
	
	@Column(length = 255)
	private java.lang.String remark;

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	
	

}
