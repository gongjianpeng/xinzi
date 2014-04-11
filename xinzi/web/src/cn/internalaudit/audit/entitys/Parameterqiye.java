package cn.internalaudit.audit.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [Webset V100R002C01, 2013-12-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Table(name = "Parameterqiye")
@SuppressWarnings("serial")
@Entity
public class Parameterqiye extends BaseEntity
{

    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 20)
    private java.lang.String name;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 20)
    private java.lang.String price;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 20)
    private java.lang.String unit;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 20)
    private java.lang.String chichun;
    
    
    @Column(length = 20)
    private java.lang.String chichunk;
    
    @Column(length = 20)
    private java.lang.String minsize;
    
    @Column(length = 20)
    private java.lang.String maxsize;
    
    
    @Column(length = 20)
    private java.lang.String qiwidth;  // 气窗 宽度
    
    @Column(length = 20)
    private java.lang.String qiheight; // 气窗 高度
    
    
    
    public java.lang.String getQiwidth() {
		return qiwidth;
	}
	public void setQiwidth(java.lang.String qiwidth) {
		this.qiwidth = qiwidth;
	}
	public java.lang.String getQiheight() {
		return qiheight;
	}
	public void setQiheight(java.lang.String qiheight) {
		this.qiheight = qiheight;
	}
	public java.lang.String getMinsize() {
		return minsize;
	}
	public void setMinsize(java.lang.String minsize) {
		this.minsize = minsize;
	}
	public java.lang.String getMaxsize() {
		return maxsize;
	}
	public void setMaxsize(java.lang.String maxsize) {
		this.maxsize = maxsize;
	}
	public java.lang.String getChichunk() {
		return chichunk;
	}
	public void setChichunk(java.lang.String chichunk) {
		this.chichunk = chichunk;
	}
	/**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 30)
    private java.lang.String guige;
    
    @Column(length = 30)
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
    @Column(length = 300)
    private java.lang.String remark;
    
    public java.lang.String getName()
    {
        return name;
    }
    public void setName(java.lang.String name)
    {
        this.name = name;
    }
    
    public java.lang.String getRemark()
    {
        return remark;
    }
    public void setRemark(java.lang.String remark)
    {
        this.remark = remark;
    }
    public java.lang.String getPrice()
    {
        return price;
    }
    public void setPrice(java.lang.String price)
    {
        this.price = price;
    }
    public java.lang.String getUnit()
    {
        return unit;
    }
    public void setUnit(java.lang.String unit)
    {
        this.unit = unit;
    }
    public java.lang.String getChichun()
    {
        return chichun;
    }
    public void setChichun(java.lang.String chichun)
    {
        this.chichun = chichun;
    }
    public java.lang.String getGuige()
    {
        return guige;
    }
    public void setGuige(java.lang.String guige)
    {
        this.guige = guige;
    }
    
}
