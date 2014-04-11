package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.DataDictionary;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
public interface IDataDictionaryBo extends IBo<DataDictionary> {
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

	public boolean isAvailableByCode(DataDictionary dataDictionary,
			String code, int type);

	public boolean isAvailableByName(DataDictionary dataDictionary,
			String name, int type);

	public void add(DataDictionary mode);

	public void update(DataDictionary mode);

	public void delete(DataDictionary mode);

	public List<DataDictionary> findByParms(Map map);
    public DataDictionary findDataDictionaryByCode(String code, String type);
	
}
