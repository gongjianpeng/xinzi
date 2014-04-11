package cn.internalaudit.audit.entitys.base;


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

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * @generated
 */
@Table(name = "Dealer")
@SuppressWarnings("serial")
@Entity
public class Dealer extends BaseEntity {
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */

	
	@Column(length = 255)
	private java.lang.String dename;
	@Column(length = 255)
    private java.lang.String decode;
	@Column(length = 255)
    private java.lang.String deperson;
	@Column(length = 255)
    private java.lang.String email;
	
	@Column(length = 255)
	private java.lang.String tel;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String fax;
	
	@Column(length = 255)
	private java.lang.String type;
	
	//add by gongjianpeng begin
	@Column(length = 2000)
	private java.lang.String intro;
	@Column(length = 500)
	private java.lang.String video;
	@Column(length = 500)
	private java.lang.String picturebook;
	
	@Column(length = 255)
	private java.lang.String chuanzhen;
	
	@Column(length = 255)
	private java.lang.String companyname;
	
	
	
	public java.lang.String getChuanzhen() {
		return chuanzhen;
	}
	public void setChuanzhen(java.lang.String chuanzhen) {
		this.chuanzhen = chuanzhen;
	}
	public java.lang.String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(java.lang.String companyname) {
		this.companyname = companyname;
	}
	public java.lang.String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(java.lang.String businessNumber) {
		this.businessNumber = businessNumber;
	}
	@Column(length = 255)
	private java.lang.String businessNumber;//新添加字段
	
	
	
	public java.lang.String getIntro() {
		return intro;
	}
	public void setIntro(java.lang.String intro) {
		this.intro = intro;
	}
	public java.lang.String getVideo() {
		return video;
	}
	public void setVideo(java.lang.String video) {
		this.video = video;
	}
	public java.lang.String getPicturebook() {
		return picturebook;
	}
	public void setPicturebook(java.lang.String picturebook) {
		this.picturebook = picturebook;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 1000)
	private java.lang.String address;
	public java.lang.String getDename() {
		return dename;
	}
	public void setDename(java.lang.String dename) {
		this.dename = dename;
	}
	public java.lang.String getTel() {
		return tel;
	}
	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}
	public java.lang.String getFax() {
		return fax;
	}
	public void setFax(java.lang.String fax) {
		this.fax = fax;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	@Column(length = 255)
	private java.lang.String remark;
    public java.lang.String getDecode()
    {
        return decode;
    }
    public void setDecode(java.lang.String decode)
    {
        this.decode = decode;
    }
    public java.lang.String getDeperson()
    {
        return deperson;
    }
    public void setDeperson(java.lang.String deperson)
    {
        this.deperson = deperson;
    }
    public java.lang.String getEmail()
    {
        return email;
    }
    public void setEmail(java.lang.String email)
    {
        this.email = email;
    }
	
}
