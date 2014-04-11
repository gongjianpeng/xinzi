package cn.internalaudit.audit.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * @generated
 */
@Table(name = "DataQuote")
@SuppressWarnings("serial")
@Entity
public class DataQuote extends BaseEntity implements Serializable{

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
	private java.lang.String reality;
	
	@Column(length = 255)
	private java.lang.String discount;
	@Column(length = 255)
	private java.lang.String bpinpai;
	@Column(length = 255)
	private java.lang.String btype;
	@Column(length = 255)
	private java.lang.String btypename;
	
	@Column(length = 255)
	private java.lang.String bpriceone;
	
	
	public java.lang.String getBpriceone() {
		return bpriceone;
	}

	public void setBpriceone(java.lang.String bpriceone) {
		this.bpriceone = bpriceone;
	}

	@Column(length = 255)
	private java.lang.String bcaizhi;
	
	@Column(length = 255)
	private java.lang.String bcaizhinum;
	
	
	
	@Column(length = 255)
	private java.lang.String remark;
	
	@Column(length = 255)
	private java.lang.String danwei;  //单位
	
	
	
	@Column(length = 255)
	private java.lang.String chaochuprice;  //超出后每平方多少钱
	

	public java.lang.String getDanwei() {
		return danwei;
	}

	public void setDanwei(java.lang.String danwei) {
		this.danwei = danwei;
	}

	public java.lang.String getChaochuprice() {
		return chaochuprice;
	}

	public void setChaochuprice(java.lang.String chaochuprice) {
		this.chaochuprice = chaochuprice;
	}

	public java.lang.String getReality() {
		return reality;
	}

	public void setReality(java.lang.String reality) {
		this.reality = reality;
	}

	public java.lang.String getDiscount() {
		return discount;
	}

	public void setDiscount(java.lang.String discount) {
		this.discount = discount;
	}

	public java.lang.String getBpinpai() {
		return bpinpai;
	}

	public void setBpinpai(java.lang.String bpinpai) {
		this.bpinpai = bpinpai;
	}

	public java.lang.String getBtype() {
		return btype;
	}

	public void setBtype(java.lang.String btype) {
		this.btype = btype;
	}

	public java.lang.String getBtypename() {
		return btypename;
	}

	public void setBtypename(java.lang.String btypename) {
		this.btypename = btypename;
	}

	public java.lang.String getBcaizhi() {
		return bcaizhi;
	}

	public void setBcaizhi(java.lang.String bcaizhi) {
		this.bcaizhi = bcaizhi;
	}

	public java.lang.String getBcaizhinum() {
		return bcaizhinum;
	}

	public void setBcaizhinum(java.lang.String bcaizhinum) {
		this.bcaizhinum = bcaizhinum;
	}



	public java.lang.String getBdanjiac() {
		return bdanjiac;
	}

	public void setBdanjiac(java.lang.String bdanjiac) {
		this.bdanjiac = bdanjiac;
	}

	@Column(length = 255)
	private java.lang.String bdanjiac;
	
	
	

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
