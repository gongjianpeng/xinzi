package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.DataDictionary;


/**
 * 
 * data dictionary
 * 
 * @author bd02
 * 
 */
public interface IDataDictionaryDao extends IBaseDao<DataDictionary> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<DataDictionary> findDataDictionaryByTpye(Integer type);
	
	public List<DataDictionary> findDataDictionaryByStringType(String type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<DataDictionary> findDataDictionaryByName(Integer type,
			String name);
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public DataDictionary findDataDictionaryByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<DataDictionary> findDataDictionaryByCode(String code,
			Integer type);
	public DataDictionary findDataDictionaryByCode(String code, String type);
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<DataDictionary> findDataDictionaryByid(Long id, Integer type);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<DataDictionary> findDataDictionaryByName1(String name, int type);
	
	public List<DataDictionary> findAll();

}
