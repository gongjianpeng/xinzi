package cn.internalaudit.audit.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.internalaudit.audit.entitys.BaseEntity;

/**
 * Commodity entity. @author MyEclipse Persistence Tools
 */
@Table(name = "commodityUser")
@Entity
public class Cart  extends BaseEntity implements java.io.Serializable {
	/** default constructor */
	public Cart() {
	}
	
	private String number;  // 商品编号
	private String standard;//  规格
	private String colour;// 颜色
	private String brand;// 品牌
	private String amount;// 数量
	private String type;// 类型
	private String size;// 尺寸长度
	private String sizekd;// 尺寸宽度
	private String proclass;// 产品系列
	private String photo;//  商品图片
	private String opento;// 开向
	private String code;//   
	private String remark;// 
	private String name;// 
	private String menheight;// 门体高
	private String wallply; //墙体厚度
	private String tcount; //樘数
	private String proName;
	/****** panel   色板*****/
	private String panelname;
	private String pancontents;
	private String pansname;
	private String pantype;
	private String panfield2;
	private String panfield;
	private String pancrtime; 
	private String pancode;
	private String panorg;
	
	private String panremark;
	
	/******  doorstyle *****/
   //private String doorstyleid;
	
	private String doorstylename;  // 名称
	private String doorstylecontents; //描述
	private String doorstylesname; //款式名称
	private String doorstyletype; //类型
	private String doorstylefield;  //
	private String doorstylefield2;
	private String doorstylecrtime;
	
	private String doorstyleorg;
	private String doorstylecode;
	private String doorstyleremark;
	
	/***********/
	
	private String framename;
	private String framecontents;
	private String framesname;
	private String frametype;
	private String framefield;
	private String framefield2;
	private String framecrtime;
	private String frameorg;
	private String framecode;
	private String frameremark;
	
	/***** 色板  seban  begin ******/
	
	private String palettename;
	private String palettecontents;
	private String palettesname;
	private String palettetype;
	private String palettefield;
	private String palettefield2;
	private String palettecrtime;
	private String paletteorg;
	private String palettecode;
	private String paletteremark;
	/***********/
	

	/***** 色板  seban  begin ******/
	
	private String scuttlename;
	private String scuttlecontents;
	private String scuttlesname;
	private String scuttletype;
	private String scuttlefield;
	private String scuttlefield2;
	private String scuttlecrtime;
	private String scuttleorg;
	private String scuttlecode;
	private String scuttleremark;

	/***** 色板  seban  begin ******/
	
	
	private String accessoriesname;
	private String accessoriescontents;
	private String accessoriessname;
	private String accessoriestype;
	private String accessoriesfield;
	private String accessoriesfield2;
	private String accessoriescrtime;
	private String accessoriesorg;
	private String accessoriescode;
	private String accessoriesremark;
	
	/*****else  seban  begin ******/
	
	
	private String elsesname;
	private String elsescontents;
	private String elsessname;
	private String elsestype;
	private String elsesfield;
	private String elsesfield2;
	private String elsescrtime;
	private String elsesorg;
	private String elsescode;
	
	private String status;
	private String dingzhiprice;
	private String cartor;
	private String cartunitprice;
	private String cartsize;
	private String carttotalprice;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDingzhiprice() {
		return dingzhiprice;
	}

	public void setDingzhiprice(String dingzhiprice) {
		this.dingzhiprice = dingzhiprice;
	}

	public String getCartor() {
		return cartor;
	}

	public void setCartor(String cartor) {
		this.cartor = cartor;
	}

	public String getCartunitprice() {
		return cartunitprice;
	}

	public void setCartunitprice(String cartunitprice) {
		this.cartunitprice = cartunitprice;
	}

	public String getCartsize() {
		return cartsize;
	}

	public void setCartsize(String cartsize) {
		this.cartsize = cartsize;
	}

	public String getCarttotalprice() {
		return carttotalprice;
	}

	public void setCarttotalprice(String carttotalprice) {
		this.carttotalprice = carttotalprice;
	}

	public String getProclass() {
		return proclass;
	}

	public void setProclass(String proclass) {
		proclass=(proclass==null)?"": proclass;
		this.proclass = proclass;
	}

	public String getSizekd() {
		return sizekd;
	}

	public void setSizekd(String sizekd) {
		sizekd=(sizekd==null)?"": sizekd;
		this.sizekd = sizekd;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		proName=(proName==null)?"": proName;
		this.proName = proName;
	}

	public String getMenheight() {
		return menheight;
	}

	public void setMenheight(String menheight) {
		menheight=(menheight==null)?"": menheight;
		this.menheight = menheight;
	}

	public String getWallply() {
		return wallply;
	}

	public void setWallply(String wallply) {
		wallply=( wallply==null)?"": wallply;
		this.wallply = wallply;
	}

	public String getTcount() {
		return tcount;
	}

	public void setTcount(String tcount) {
		tcount=( tcount==null)?"": tcount;
		this.tcount = tcount;
	}

	private String elsesremark;
	
	public String getElsesname() {
		return elsesname;
	}

	public void setElsesname(String elsesname) {
		elsesname=( elsesname==null)?"": elsesname;
		this.elsesname = elsesname;
	}

	public String getElsescontents() {
		return elsescontents;
	}

	public void setElsescontents(String elsescontents) {
		elsescontents=( elsescontents==null)?"": elsescontents;
		this.elsescontents = elsescontents;
	}

	public String getElsessname() {
		return elsessname;
	}

	public void setElsessname(String elsessname) {
		elsessname=( elsessname==null)?"": elsessname;
		this.elsessname = elsessname;
	}

	public String getElsestype() {
		return elsestype;
	}

	public void setElsestype(String elsestype) {
		elsestype=( elsestype==null)?"": elsestype;
		this.elsestype = elsestype;
	}

	public String getElsesfield() {
		return elsesfield;
	}

	public void setElsesfield(String elsesfield) {
		elsesfield=( elsesfield==null)?"": elsesfield;
		this.elsesfield = elsesfield;
	}

	public String getElsesfield2() {
		return elsesfield2;
	}

	public void setElsesfield2(String elsesfield2) {
		elsesfield2=( elsesfield2==null)?"": elsesfield2;
		this.elsesfield2 = elsesfield2;
	}

	public String getElsescrtime() {
		return elsescrtime;
	}

	public void setElsescrtime(String elsescrtime) {
		elsescrtime=( elsescrtime==null)?"": elsescrtime;
		this.elsescrtime = elsescrtime;
	}

	public String getElsesorg() {
		return elsesorg;
	}

	public void setElsesorg(String elsesorg) {
		elsesorg=( elsesorg==null)?"": elsesorg;
		this.elsesorg = elsesorg;
	}

	public String getElsescode() {
		return elsescode;
	}

	public void setElsescode(String elsescode) {
		elsescode=( elsescode==null)?"": elsescode;
		this.elsescode = elsescode;
	}

	public String getElsesremark() {
		return elsesremark;
	}

	public void setElsesremark(String elsesremark) {
		elsesremark=( elsesremark==null)?"": elsesremark;
		this.elsesremark = elsesremark;
	}
	
	public String getPancrtime() {
		return pancrtime;
	}

	public void setPancrtime(String pancrtime) {
		pancrtime=( pancrtime==null)?"": pancrtime;
		this.pancrtime = pancrtime;
	}

	public String getPanorg() {
		return panorg;
	}

	public void setPanorg(String panorg) {
		panorg=( panorg==null)?"": panorg;
		this.panorg = panorg;
	}

	public String getDoorstylecrtime() {
		return doorstylecrtime;
	}

	public void setDoorstylecrtime(String doorstylecrtime) {
		doorstylecrtime=( doorstylecrtime==null)?"": doorstylecrtime;
		this.doorstylecrtime = doorstylecrtime;
	}
	
	
	public String getAccessoriesname() {
		return accessoriesname;
	}

	public void setAccessoriesname(String accessoriesname) {
		accessoriesname=( accessoriesname==null)?"": accessoriesname;
		this.accessoriesname = accessoriesname;
	}

	public String getAccessoriescontents() {
		return accessoriescontents;
	}

	public void setAccessoriescontents(String accessoriescontents) {
		accessoriescontents=( accessoriescontents==null)?"": accessoriescontents;
		this.accessoriescontents = accessoriescontents;
	}

	public String getAccessoriessname() {
		return accessoriessname;
	}

	public void setAccessoriessname(String accessoriessname) {
		accessoriessname=( accessoriessname==null)?"": accessoriessname;
		this.accessoriessname = accessoriessname;
	}

	public String getAccessoriestype() {
		return accessoriestype;
	}

	public void setAccessoriestype(String accessoriestype) {
		accessoriestype=( accessoriestype==null)?"": accessoriestype;
		this.accessoriestype = accessoriestype;
	}

	public String getAccessoriesfield() {
		return accessoriesfield;
	}

	public void setAccessoriesfield(String accessoriesfield) {
		accessoriesfield=( accessoriesfield==null)?"": accessoriesfield;
		this.accessoriesfield = accessoriesfield;
	}

	public String getAccessoriesfield2() {
		return accessoriesfield2;
	}

	public void setAccessoriesfield2(String accessoriesfield2) {
		accessoriesfield2=( accessoriesfield2==null)?"": accessoriesfield2;
		this.accessoriesfield2 = accessoriesfield2;
	}

	public String getAccessoriescrtime() {
		return accessoriescrtime;
	}

	public void setAccessoriescrtime(String accessoriescrtime) {
		accessoriescrtime=( accessoriescrtime==null)?"": accessoriescrtime;
		this.accessoriescrtime = accessoriescrtime;
	}

	public String getAccessoriesorg() {
		return accessoriesorg;
	}

	public void setAccessoriesorg(String accessoriesorg) {
		accessoriesorg=( accessoriesorg==null)?"": accessoriesorg;
		this.accessoriesorg = accessoriesorg;
	}

	public String getAccessoriescode() {
		return accessoriescode;
	}

	public void setAccessoriescode(String accessoriescode) {
		accessoriescode=( accessoriescode==null)?"": accessoriescode;
		this.accessoriescode = accessoriescode;
	}

	public String getAccessoriesremark() {
		return accessoriesremark;
	}

	public void setAccessoriesremark(String accessoriesremark) {
		accessoriesremark=( accessoriesremark==null)?"": accessoriesremark;
		this.accessoriesremark = accessoriesremark;
	}

	public String getScuttlename() {
		return scuttlename;
	}

	public void setScuttlename(String scuttlename) {
		scuttlename=( scuttlename==null)?"": scuttlename;
		this.scuttlename = scuttlename;
	}

	public String getScuttlecontents() {
		return scuttlecontents;
	}

	public void setScuttlecontents(String scuttlecontents) {
		scuttlecontents=( scuttlecontents==null)?"": scuttlecontents;
		this.scuttlecontents = scuttlecontents;
	}

	public String getScuttlesname() {
		return scuttlesname;
	}

	public void setScuttlesname(String scuttlesname) {
		scuttlesname=( scuttlesname==null)?"": scuttlesname;
		this.scuttlesname = scuttlesname;
	}

	public String getScuttletype() {
		return scuttletype;
	}

	public void setScuttletype(String scuttletype) {
		scuttletype=( scuttletype==null)?"": scuttletype;
		this.scuttletype = scuttletype;
	}

	public String getScuttlefield() {
		return scuttlefield;
	}

	public void setScuttlefield(String scuttlefield) {
		scuttlefield=( scuttlefield==null)?"": scuttlefield;
		this.scuttlefield = scuttlefield;
	}

	public String getScuttlefield2() {
		return scuttlefield2;
	}

	public void setScuttlefield2(String scuttlefield2) {
		scuttlefield2=( scuttlefield2==null)?"": scuttlefield2;
		this.scuttlefield2 = scuttlefield2;
	}

	public String getScuttlecrtime() {
		return scuttlecrtime;
	}

	public void setScuttlecrtime(String scuttlecrtime) {
		scuttlecrtime=( scuttlecrtime==null)?"": scuttlecrtime;
		this.scuttlecrtime = scuttlecrtime;
	}

	public String getScuttleorg() {
		return scuttleorg;
	}

	public void setScuttleorg(String scuttleorg) {
		scuttleorg=( scuttleorg==null)?"": scuttleorg;
		this.scuttleorg = scuttleorg;
	}

	public String getScuttlecode() {
		return scuttlecode;
	}

	public void setScuttlecode(String scuttlecode) {
		scuttlecode=( scuttlecode==null)?"": scuttlecode;
		this.scuttlecode = scuttlecode;
	}

	public String getScuttleremark() {
		
		return scuttleremark;
	}

	public void setScuttleremark(String scuttleremark) {
		scuttleremark=( scuttleremark==null)?"": scuttleremark;
		this.scuttleremark = scuttleremark;
	}

	/***********/
	
	public String getPalettename() {
		return palettename;
	}

	public void setPalettename(String palettename) {
		palettename=( palettename==null)?"": palettename;
		this.palettename = palettename;
	}

	public String getPalettecontents() {
		return palettecontents;
	}

	public void setPalettecontents(String palettecontents) {
		palettecontents=( palettecontents==null)?"": palettecontents;
		this.palettecontents = palettecontents;
	}

	public String getPalettesname() {
		return palettesname;
	}

	public void setPalettesname(String palettesname) {
		palettesname=( palettesname==null)?"": palettesname;
		this.palettesname = palettesname;
	}

	public String getPalettetype() {
		return palettetype;
	}

	public void setPalettetype(String palettetype) {
		palettetype=( palettetype==null)?"": palettetype;
		this.palettetype = palettetype;
	}

	public String getPalettefield() {
		return palettefield;
	}

	public void setPalettefield(String palettefield) {
		palettefield=( palettefield==null)?"": palettefield;
		this.palettefield = palettefield;
	}

	public String getPalettefield2() {
		return palettefield2;
	}

	public void setPalettefield2(String palettefield2) {
		palettefield2=( palettefield2==null)?"": palettefield2;
		this.palettefield2 = palettefield2;
	}

	public String getPalettecrtime() {
		return palettecrtime;
	}

	public void setPalettecrtime(String palettecrtime) {
		palettecrtime=( palettecrtime==null)?"": palettecrtime;
		this.palettecrtime = palettecrtime;
	}

	public String getPaletteorg() {
		return paletteorg;
	}

	public void setPaletteorg(String paletteorg) {
		 paletteorg=( paletteorg==null)?"": paletteorg;
		this.paletteorg = paletteorg;
	}

	public String getPalettecode() {
		return palettecode;
	}

	public void setPalettecode(String palettecode) {
		palettecode=(palettecode==null)?"":palettecode;
		this.palettecode = palettecode;
	}

	public String getPaletteremark() {
		return paletteremark;
	}

	public void setPaletteremark(String paletteremark) {
		paletteremark=(paletteremark==null)?"":paletteremark;
		this.paletteremark = paletteremark;
	}

	public String getFramename() {
		return framename;
	}

	public void setFramename(String framename) {
		framename=(framename==null)?"":framename;
		this.framename = framename;
	}

	public String getFramecontents() {
		return framecontents;
	}

	public void setFramecontents(String framecontents) {
		framecontents=(framecontents==null)?"":framecontents;
		this.framecontents = framecontents;
	}

	public String getFramesname() {
		return framesname;
	}

	public void setFramesname(String framesname) {
		framesname=(framesname==null)?"":framesname;
		this.framesname = framesname;
	}

	public String getFrametype() {
		return frametype;
	}

	public void setFrametype(String frametype) {
		frametype=(frametype==null)?"":frametype;
		this.frametype = frametype;
	}

	public String getFramefield() {
		return framefield;
	}

	public void setFramefield(String framefield) {
		framefield=(framefield==null)?"":framefield;
		this.framefield = framefield;
	}

	public String getFramefield2() {
		return framefield2;
	}

	public void setFramefield2(String framefield2) {
		framefield2=(framefield2==null)?"":framefield2;
		this.framefield2 = framefield2;
	}

	public String getFramecrtime() {
		return framecrtime;
	}

	public void setFramecrtime(String framecrtime) {
		framecrtime=(framecrtime==null)?"":framecrtime;
		this.framecrtime = framecrtime;
	}

	public String getFrameorg() {
		return frameorg;
	}

	public void setFrameorg(String frameorg) {
		frameorg=(frameorg==null)?"":frameorg;
		this.frameorg = frameorg;
	}

	public String getFramecode() {
		return framecode;
	}

	public void setFramecode(String framecode) {
		framecode=(framecode==null)?"":framecode;
		this.framecode = framecode;
	}

	public String getFrameremark() {
		return frameremark;
	}

	public void setFrameremark(String frameremark) {
		frameremark=(frameremark==null)?"":frameremark;
		this.frameremark = frameremark;
	}

	
	public String getDoorstylename() {
		return doorstylename;
	}

	public void setDoorstylename(String doorstylename) {
		doorstylename=(doorstylename==null)?"":doorstylename;
		this.doorstylename = doorstylename;
	}

	public String getDoorstylecontents() {
		return doorstylecontents;
	}

	public void setDoorstylecontents(String doorstylecontents) {
		doorstylecontents=(doorstylecontents==null)?"":doorstylecontents;
		this.doorstylecontents = doorstylecontents;
	}

	public String getDoorstylesname() {
		return doorstylesname;
	}

	public void setDoorstylesname(String doorstylesname) {
		doorstylesname=(doorstylesname==null)?"":doorstylesname;
		this.doorstylesname = doorstylesname;
	}

	public String getDoorstyletype() {
		return doorstyletype;
	}

	public void setDoorstyletype(String doorstyletype) {
		doorstyletype=(doorstyletype==null)?"":doorstyletype;
		this.doorstyletype = doorstyletype;
	}

	public String getDoorstylefield() {
		return doorstylefield;
	}

	public void setDoorstylefield(String doorstylefield) {
		doorstylefield=(doorstylefield==null)?"":doorstylefield;
		this.doorstylefield = doorstylefield;
	}

	public String getDoorstylefield2() {
		return doorstylefield2;
	}

	public void setDoorstylefield2(String doorstylefield2) {
		doorstylefield2=(doorstylefield2==null)?"":doorstylefield2;
		this.doorstylefield2 = doorstylefield2;
	}

	public String getDoorstyleorg() {
		return doorstyleorg;
	}

	public void setDoorstyleorg(String doorstyleorg) {
		doorstyleorg=(doorstyleorg==null)?"":doorstyleorg;
		this.doorstyleorg = doorstyleorg;
	}

	public String getDoorstylecode() {
		return doorstylecode;
	}

	public void setDoorstylecode(String doorstylecode) {
		doorstylecode=(doorstylecode==null)?"":doorstylecode;
		this.doorstylecode = doorstylecode;
	}

	public String getDoorstyleremark() {
		return doorstyleremark;
	}

	public void setDoorstyleremark(String doorstyleremark) {
		doorstyleremark=(doorstyleremark==null)?"":doorstyleremark;
		this.doorstyleremark = doorstyleremark;
	}

	
	public String getPancontents() {
		return pancontents;
	}

	public void setPancontents(String pancontents) {
		pancontents=(pancontents==null)?"":pancontents;
		this.pancontents = pancontents;
	}

	public String getPansname() {
		return pansname;
	}

	public void setPansname(String pansname) {
		pansname=(pansname==null)?"":pansname;
		this.pansname = pansname;
	}

	public String getPantype() {
		return pantype;
	}

	public void setPantype(String pantype) {
		pantype=(pantype==null)?"":pantype;
		this.pantype = pantype;
	}

	public String getPanfield2() {
		return panfield2;
	}

	public void setPanfield2(String panfield2) {
		panfield2=(panfield2==null)?"":panfield2;
		this.panfield2 = panfield2;
	}

	public String getPanfield() {
		return panfield;
	}

	public void setPanfield(String panfield) {
		panfield=(panfield==null)?"":panfield;
		this.panfield = panfield;
	}

	public String getPancode() {
		return pancode;
	}

	public void setPancode(String pancode) {
		pancode=(pancode==null)?"":pancode;
		this.pancode = pancode;
	}

	public String getPanremark() {
		return panremark;
	}

	public void setPanremark(String panremark) {
		panremark=(panremark==null)?"":panremark;
		this.panremark = panremark;
	}

	public String getPanelname() {
		return panelname;
	}

	public void setPanelname(String panelname) {
		panelname=(panelname==null)?"":panelname;
		this.panelname = panelname;
	}

	

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		standard=(number==null)?"":number;
		this.number = number;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		standard=(standard==null)?"":standard;
		this.standard = standard;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		colour=(colour==null)?"":colour;
		this.colour = colour;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		brand=(brand==null)?"":brand;
		this.brand = brand;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		amount=(amount==null)?"":amount;
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		type=(type==null)?"":type;
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		size=(size==null)?"":size;
		this.size = size;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		photo=(photo==null)?"":photo;
		this.photo = photo;
	}

	public String getOpento() {
		return opento;
	}

	public void setOpento(String opento) {
		opento=(opento==null)?"":opento;
		this.opento = opento;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		code=(code==null)?"":code;
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		remark=(remark==null)?"":remark;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name=(name==null)?"":name;
		this.name = name;
	}

 
	

	
}