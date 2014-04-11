package cn.internalaudit.audit.utils;

import java.util.List;

public class DoorType {

	private DoorBo doorBo;
	
	
	private void  test(){
		booleanDoor(doorBo);
		
	}

	/**
	 * 门的类型总判断
	 * 
	 * @return 1 单门
	 * @return 2 单门
	 * @return 3 单门
	 * @return 4 单门
	 * @return 5 单门
	 * @return 6 无此类型
	 */
	private boolean booleanDoor(DoorBo doorBo) {

//		if (singleDoor(doorBo)) {
//			return true;
//		}
//		if (childDoor(doorBo)) {
//			return true;
//		}
//		if (dualopenDoor(doorBo)) {
//			return true;
//		}
//		if (fourOpencmDoor(doorBo)) {
//			return true;
//		}
//		if (fourOpenDoor(doorBo)) {
//			return true;
//		}
		return false;
	}

	/**
	 * 门的尺寸(mm)
	 * 
	 * @param doorBo
	 * @return 
	 * @return
	 */
	// 单门 ：普通960X2050 || 气窗960X2500
	public static String singleDoor(DoorBo doorBo) {
         List list=null;
		if (doorBo.getWidth() >= 960 &&  doorBo.getWidth() < 1200
				&& doorBo.getLength() >= 2050 && doorBo.getLength() < 2200) {
            System.out.println("11");
            list.add(1, "单门普通");
			//return list;
		}

		if (doorBo.getWidth() >= 960 &&  doorBo.getWidth() < 1200
				&& doorBo.getLength() >= 2500 && doorBo.getLength() < 2500) {
			list.add(2, "单门气窗");
			//return list;
		}
		System.out.println("33");
		return "0";
	}

	// 子母门 ：普通1200X2200 || 气窗1200X2500
	private boolean childDoor(DoorBo doorBo) {

		if (doorBo.getWidth() >= 1200 && doorBo.getWidth() < 1920
				&& doorBo.getLength() >= 2200 && doorBo.getLength() < 2250) {

			return true;
		}

		if (doorBo.getWidth() >= 1200 && doorBo.getWidth() < 1920
				&& doorBo.getLength() >= 2500 && doorBo.getLength() < 2500) {

			return true;
		}
		return false;
	}

	// 双开门: 普通 1920X2250|| 气窗 1920X2500
	private boolean dualopenDoor(DoorBo doorBo) {

		if (doorBo.getWidth() >= 1920 && doorBo.getWidth() < 2400
				&& doorBo.getLength() >= 2250 && doorBo.getLength() < 2250) {

			return true;
		}

		if (doorBo.getWidth() >= 1920 && doorBo.getWidth() < 2400
				&& doorBo.getLength() >= 2500 && doorBo.getLength() < 2500) {

			return true;
		}
		return false;
	}

	// 四开子母门 ： 普通 2400X2250 || 气窗 2400X2500
	private boolean fourOpencmDoor(DoorBo doorBo) {

		if (doorBo.getWidth() >= 2400 && doorBo.getWidth() < 3840
				&& doorBo.getLength() >= 2250 && doorBo.getLength() < 2250) {

			return true;
		}

		if (doorBo.getWidth() >= 2400 && doorBo.getWidth() < 3840
				&& doorBo.getLength() >= 2500 && doorBo.getLength() < 2500) {

			return true;
		}
		return false;
	}

	// 四开门 ： 普通 3840X2250 || 3840X2500
	private boolean fourOpenDoor(DoorBo doorBo) {

		if (doorBo.getWidth() >=3840 && doorBo.getLength() >= 2250 ) {

			return true;
		}

		if (doorBo.getWidth() >=3840 && doorBo.getLength() >= 2250 ) {

			return true;
		}
		return false;
	}

	/**
	 * 门压花尺寸(mm)
	 * return 0 :不可以做门
	 * return 1 :普通压模
	 * return 2 :出头模
	 * return 3 :小门对开
	 * return 4 :子门
	 * return 5 :广西加长
	 */
	private String commonDie(DoorBo doorBo) {

		// 判断是否可以做门
		if (!booleanDoor(doorBo)) {
			return  "0";
		}
		
		//普通压模
		if ((doorBo.getDieWidth() >= 520 && doorBo.getDieLength() >= 1600) 
				||(doorBo.getDieWidth() >= 620 && doorBo.getDieLength() >= 1800) 
				|| (doorBo.getDieWidth() >= 680 && doorBo.getDieLength() >= 1800)  ) {
			
			return "1";
		}
		// 出头模
		if ((doorBo.getDieWidth() >= 520 && doorBo.getDieLength() >= 1600 && doorBo.getDieLength() < 2500)
				||(doorBo.getDieWidth() >= 560 && doorBo.getDieLength() >= 1760 && doorBo.getDieLength() < 2500)
				||(doorBo.getDieWidth() >= 520 && doorBo.getDieLength() >= 1760 && doorBo.getDieLength() < 2500)
				||(doorBo.getDieWidth() >= 580 && doorBo.getDieLength() >= 1800 && doorBo.getDieLength() < 2500)
				||(doorBo.getDieWidth() >= 560 && doorBo.getDieLength() >= 1800 && doorBo.getDieLength() < 2500)
				) {

			return "2";
		}
		//小门对开
		if (doorBo.getDieWidth() >= 360 && doorBo.getDieLength() >= 1600) {
			
			if(dualopenDoor(doorBo) || fourOpenDoor(doorBo)){
				return "3";
			}
		}
		//子门
		if ((doorBo.getDieWidth() >= 180 && doorBo.getDieLength() >= 1600)
				||(doorBo.getDieWidth() >= 230 && doorBo.getDieLength() >= 1600)
				||(doorBo.getDieWidth() >= 220 && doorBo.getDieLength() >= 1600)
				||(doorBo.getDieWidth() >= 240 && doorBo.getDieLength() >= 1600)
				||(doorBo.getDieWidth() >= 260 && doorBo.getDieLength() >= 1600)
				) {
			
			if(childDoor(doorBo) || fourOpencmDoor(doorBo)){
				return "4";
			}
			
			
		}
		//广西加长
		if ((doorBo.getDieWidth() >= 450 && doorBo.getDieLength() >= 2200)
				||(doorBo.getDieWidth() >= 360 && doorBo.getDieLength() >= 2200)
				||(doorBo.getDieWidth() >= 380 && doorBo.getDieLength() >= 2200)
				||(doorBo.getDieWidth() >= 620 && doorBo.getDieLength() >= 2200)
				) {
			
			if(dualopenDoor(doorBo) || fourOpenDoor(doorBo)){
				return "5";
			}
		}
		return "0";
	}

	 
	/**
	 * 门面压花种类			
     * return 0 :压花
     * 1----4 ：花枝类
	 * return 1 : 整花
	 * return 2 :上下花
	 * return 3 :上花下封板
	 * return 4 :整板割孔
	 * 
	 * 传过来是  压花 0 ，则可以做门
	 */
	private String doorPresstype(DoorBo doorBo){
		if(doorBo.getPresstype().equals("0") || doorBo.getPresstype() == "0" ){
			booleanDoor(doorBo);
		}
		
		return null;
	}
	
	
	
	
	public DoorBo getDoorBo() {
		return doorBo;
	}

	
	public void setDoorBo(DoorBo doorBo) {
		this.doorBo = doorBo;
	}

	public void singleDoor() {

		
		
	}
	
	public static void main(String[] args) {
		DoorBo doorBo=new DoorBo();
		doorBo.setLength(1000);
		doorBo.setWidth(2150);
		singleDoor(doorBo);
	}
}
