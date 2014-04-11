package cn.internalaudit.audit.pojo.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Accessories;


/**
 * 
 * data dictionary
 * 
 * @author bd02
 * 
 */

public interface IAccessDao extends IBaseDao<Accessories> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Accessories> findAccessoriesByTpye(Integer type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Accessories> findAccessoriesByName(Integer type,
			String name);
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public Accessories findAccessoriesByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<Accessories> findAccessoriesByCode(String code,
			Integer type);
	public Accessories findAccessoriesByCode(String code, String type);
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<Accessories> findAccessoriesByid(Long id, Integer type);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<Accessories> findAccessoriesByName1(String name, int type);
	

}
