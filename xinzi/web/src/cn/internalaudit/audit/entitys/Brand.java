package cn.internalaudit.audit.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @generated
 */
@Table(name = "Brand")
@SuppressWarnings("serial")
@Entity
public class Brand extends BaseEntity
{
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 255)
    private java.lang.String name;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 255)
    private java.lang.String logo;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 255)
    private java.lang.String contents;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 255)
    private java.lang.String culture;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 255)
    private java.lang.String video;
    /**
     * @generated
     * @describe 
     * @display 
     */
    @Column(length = 100)
    private java.util.Date crtime;
    
    
    @Column(length = 255)
    private java.lang.String type;
    
    @Column(length = 255)
    private java.lang.String FileUrl;
    
    @Column(length = 255)
    private java.lang.String readname;
    
    
    
    public java.lang.String getReadname() {
		return readname;
	}
	public void setReadname(java.lang.String readname) {
		this.readname = readname;
	}
	public java.lang.String getFileUrl()
    {
        return FileUrl;
    }
    public void setFileUrl(java.lang.String fileUrl)
    {
        FileUrl = fileUrl;
    }
    public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getName()
    {
        return name;
    }
    public void setName(java.lang.String name)
    {
        this.name = name;
    }
    public java.lang.String getLogo()
    {
        return logo;
    }
    public void setLogo(java.lang.String logo)
    {
        this.logo = logo;
    }
    public java.lang.String getContents()
    {
        return contents;
    }
    public void setContents(java.lang.String contents)
    {
        this.contents = contents;
    }
    public java.lang.String getCulture()
    {
        return culture;
    }
    public void setCulture(java.lang.String culture)
    {
        this.culture = culture;
    }
    public java.lang.String getVideo()
    {
        return video;
    }
    public void setVideo(java.lang.String video)
    {
        this.video = video;
    }
    public java.util.Date getCrtime()
    {
        return crtime;
    }
    public void setCrtime(java.util.Date crtime)
    {
        this.crtime = crtime;
    }
   
}
