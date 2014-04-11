package cn.internalaudit.audit.utils;

public class DoorBo  {
	
	private  String doorXing;  //门型 
	
	private int length; //门高
	private int width;  //门宽
	
	private int dieLength; //压花高
	private int dieWidth;  //压花宽
	
	
	private  String Presstype;  //压花
	
	private  String Presstypechild; //花枝款式
	private  String Presstypech; //花枝款式
	
	
	public String getPresstype() {
		return Presstype;
	}
	public void setPresstype(String presstype) {
		Presstype = presstype;
	}
	public int getDieLength() {
		return dieLength;
	}
	public void setDieLength(int dieLength) {
		this.dieLength = dieLength;
	}
	public int getDieWidth() {
		return dieWidth;
	}
	public void setDieWidth(int dieWidth) {
		this.dieWidth = dieWidth;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

}
