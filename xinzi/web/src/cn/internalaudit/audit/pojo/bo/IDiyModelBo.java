package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.pojo.DiyModel;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
public interface IDiyModelBo extends IBo<DiyModel> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<DiyModel> findDiyModelByType(String codetype);
 
	
	public List<DiyModel> findDiyModelByStringcodetype(String codetype);
	
	/**
	 * 
	 * @param codetype
	 * @param name
	 * @return
	 */
	public List<DiyModel> findDiyModelByName(Integer codetype,
			String name);
	
	public List<DiyModel> findDiyModelByCodeName(String code,
			String name);
	public List<DiyModel> findDiyModelBydmodelid(String userfordmodelid);
		
	
	public List<DiyModel> findDiyModelByMoidname(String userfordmodelid,String name);
		
	
	
	public List<DiyModel> findDiyModelByName(String name);
	/**
	 * 
	 * @param codetype
	 * @param name
	 * @return
	 */
	public DiyModel findDiyModelByName(String codetype,
			String name);

	/**
	 * 
	 * @param code
	 * @param codetype
	 * @return
	 */
	public List<DiyModel> findDiyModelByCode(String code,
			Integer codetype);

	/**
	 * 
	 * @param id
	 * @param codetype
	 * @return
	 */
	public List<DiyModel> findDiyModelByid(Long id, Integer codetype);

	/**
	 * 
	 * @param name
	 * @param codetype
	 * @return
	 */
	public List<DiyModel> findDiyModelByName1(String name, int codetype);

	public boolean isAvailableByCode(DiyModel DiyModel,
			String code, int codetype);

	public boolean isAvailableByName(DiyModel DiyModel,
			String name, int codetype);

	public void add(DiyModel mode);

	public void update(DiyModel mode);

	public void delete(DiyModel mode);

	public List<DiyModel> findByParms(Map map);
    public DiyModel findDiyModelByCode(String code, String codetype);
	
}
