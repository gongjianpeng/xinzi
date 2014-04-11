package cn.internalaudit.audit.pojo.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.DiyModel;


/**
 * 
 * data dictionary
 * 
 * @author bd02
 * 
 */
public interface IDiyModelDao extends IBaseDao<DiyModel> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<DiyModel> findDiyModelByType(String codetype);
	
	public List<DiyModel> findDiyModelByStringType(String codetype);
	
	public List<DiyModel> findDiyModelByCodeName(String code,String name);

	/**
	 * 
	 * @param codetype
	 * @param name
	 * @return
	 */
	public List<DiyModel> findDiyModelByName(Integer codetype,
			String name);
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
	public DiyModel findDiyModelByCode(String code, String codetype);
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
	
	public List<DiyModel> findAll();
	
	public List<DiyModel> findDiyModelByMoidname(String userfordmodelid,String name);
	public List<DiyModel> findDiyModelBydmodelid(String userfordmodelid);

}
