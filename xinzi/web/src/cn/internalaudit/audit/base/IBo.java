package cn.internalaudit.audit.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBo<E extends IEntity> extends Serializable {
	/*******
	 * 
	 * 
	 * @param id
	 *            
	 * @return 
	 */
	public E find(long id);

	/*******
	 * 
	 * 
	 * @param entity
	 *          
	 * @return 
	 */
	public E save(E entity);

	/*******
	 *
	 * 
	 * @param id
	 *            
	 */
	public void remove(long id);

	/*******
	 * 
	 * 
	 * @param entity
	 *            
	 */
	public void remove(E entity);
	
	/******
	 *
	 * @param entity 
	 */
	public void logicRemove(E entity);
	
	/******
	 * 
	 * @param entity 
	 */
	public void logicRemove(long id);
	
	
	/******
	 * 
	 * @param ids
	 */
	public void removeIds(Long[] ids);

	/*******
	 * 
	 * 
	 * @return 
	 */
	public List<E> findAll();
	
	public void bulkUpdate(String sql);
	/********
	 * 
	 * @return 
	 */
	public List<E> findAllByIsDelete();

	public List<E> findByParm(Map map);
	
	public List find(String ql);

}
