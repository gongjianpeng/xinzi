package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.Accessories;
import cn.internalaudit.audit.pojo.Panel;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
public interface IAccessBo extends IBo<Accessories> {
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

	public boolean isAvailableByCode(Accessories Accessories,
			String code, int type);

	public boolean isAvailableByName(Accessories Accessories,
			String name, int type);

	public void add(Accessories mode);

	public void update(Accessories mode);

	public void delete(Accessories mode);

	public List<Accessories> findByParms(Map map);
    public Accessories findAccessoriesByCode(String code, String type);
	
}
