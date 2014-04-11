package cn.internalaudit.audit.entitys.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * @generated企业管理经销商
 */
@Table(name = "Companyqiye")
@SuppressWarnings("serial")
@Entity
public class Companyqiye extends BaseEntity {
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */

	
	@Column(length = 255)
	private java.lang.String companyname;
	
	@Column(length = 255)
	private java.lang.String status;
	
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(java.lang.String companyname) {
		this.companyname = companyname;
	}
	public java.lang.String getShengName() {
		return shengName;
	}
	public void setShengName(java.lang.String shengName) {
		this.shengName = shengName;
	}
	public java.lang.String getDiqu() {
		return diqu;
	}
	public void setDiqu(java.lang.String diqu) {
		this.diqu = diqu;
	}
	public java.lang.String getPinpai() {
		return pinpai;
	}
	public void setPinpai(java.lang.String pinpai) {
		this.pinpai = pinpai;
	}
	public java.lang.String getSalefor() {
		return salefor;
	}
	public void setSalefor(java.lang.String salefor) {
		this.salefor = salefor;
	}
	public java.lang.String getHeyue() {
		return heyue;
	}
	public void setHeyue(java.lang.String heyue) {
		this.heyue = heyue;
	}
	public java.lang.String getLianxi() {
		return lianxi;
	}
	public void setLianxi(java.lang.String lianxi) {
		this.lianxi = lianxi;
	}
	public java.lang.String getZuoji() {
		return zuoji;
	}
	public void setZuoji(java.lang.String zuoji) {
		this.zuoji = zuoji;
	}
	public java.lang.String getChuanzhen() {
		return chuanzhen;
	}
	public void setChuanzhen(java.lang.String chuanzhen) {
		this.chuanzhen = chuanzhen;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public java.lang.String getBaojia() {
		return baojia;
	}
	public void setBaojia(java.lang.String baojia) {
		this.baojia = baojia;
	}
	public java.lang.String getWuliu() {
		return wuliu;
	}
	public void setWuliu(java.lang.String wuliu) {
		this.wuliu = wuliu;
	}
	public java.lang.String getWuliutel() {
		return wuliutel;
	}
	public void setWuliutel(java.lang.String wuliutel) {
		this.wuliutel = wuliutel;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String shengName;
	@Column(length = 255)
	private java.lang.String type;
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
	@Column(length = 255)
	private java.lang.String diqu;
	@Column(length = 255)
	private java.lang.String code;
	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String pinpai;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String salefor;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String heyue;
	/**
	 * @generated
	 * @describe
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String lianxi;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String zuoji;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String chuanzhen;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String address;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 * @describe 
	 * @display 
	 */
	
	@Column(length = 255)
	private java.lang.String baojia;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String wuliu;
	/**
	 * @generated
	 * @describe 
	 * @display 
	 */
	@Column(length = 255)
	private java.lang.String wuliutel;
	@Column(length = 255)
	private java.lang.String remark;
	
}
