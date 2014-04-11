package cn.internalaudit.audit.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<E extends IEntity> extends Serializable {
	/*******
	 * 
	 * 
	 * @param id
	 *            
	 * @return 
	 */
	public E find(long id);

	/******
	 * 
	 * 
	 * @param entity
	 */
	public void persist(E entity);

	/******
	 * 
	 * 
	 * @param entity
	 * @return
	 */
	public E merge(E entity);

	/******
	 * 
	 * 
	 * @param entity
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
	
	public void bulkUpdate(String sql);
	/*******
	 * 
	 * 
	 * @return 
	 */
	public List<E> findAll();
	/********
	 * 
	 * @return 
	 */
	public List<E> findAllByIsDelete();
	/********
	 * 
	 * @return 
	 */
	public List<E> findByParms(Map map);
	/********
	 *
	 * @return 
	 */
	public List find(String querySring);
}