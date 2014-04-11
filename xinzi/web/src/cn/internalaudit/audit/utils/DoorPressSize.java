package cn.internalaudit.audit.utils;

public class DoorPressSize {
	private DoorBo doorBo;

	
//	普通压模	   出头模	小门对开   	子门	    广西加长

	// 单门   ：普通压模 520X1600   ||  出头模520X(1680--2500) ||小门对开 360X1600 ||子门 	180X1600 ||广西加长 	450X2200
    /**
     * @return   单门  
     * */
	private boolean singleDoor(DoorBo doorBo) {
		
		//普通压模 
		if(doorBo.getWidth() >= 520 && doorBo.getLength() < 1600){
			
			return true;
		}
		//出头模
		if(doorBo.getWidth() >= 520 && doorBo.getLength() >= 1680 && doorBo.getLength() < 2500){
			
			return true;
		}
		//小门对开
		if(doorBo.getWidth() >= 360 && doorBo.getLength() < 1600){
			
			return true;
		}
		//子门 
		if(doorBo.getWidth() >= 180 &&  doorBo.getLength() < 1600){
			
			return true;
		}
		
		//广西加长
		if(doorBo.getWidth() >= 450  && doorBo.getLength() < 2200){
			
			return true;
		}
		 return false;
	}
	
	
	public DoorBo getDoorBo() {
		return doorBo;
	}

	public void setDoorBo(DoorBo doorBo) {
		this.doorBo = doorBo;
	}
	

}
