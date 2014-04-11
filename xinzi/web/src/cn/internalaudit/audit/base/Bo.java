package cn.internalaudit.audit.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public abstract class Bo<E extends IEntity, DAO extends IBaseDao<E>> implements
		IBo<E> {

	private static final long serialVersionUID = 1L;

	@Transactional(readOnly = true)
	public E find(long id) {
		return getDao().find(id);
	}
	
	public E save(E entity) {
		if (entity.getId() == null) {
			getDao().persist(entity);
			return entity;
		} else {
			return getDao().merge(entity);
		}
	}
	
	public void remove(long id) {
		getDao().remove(id);
	}

	public void remove(E entity) {
		remove(entity.getId());
	}
	
	public void removeIds(Long[] ids){
		for(long id:ids){
			remove(id);
		}
	}

	@Transactional(readOnly = true,rollbackFor=Exception.class)
	public List<E> findAll() {
		return getDao().findAll();
	}
	@Transactional(readOnly = true,rollbackFor=Exception.class)
	public List<E> findByParm(Map map) {
		return getDao().findByParms(map);
	}
	@Transactional(readOnly = true,rollbackFor=Exception.class)
	public List find(String ql) {
		return getDao().find(ql);
	}
	@Override
	@Transactional(readOnly = true,rollbackFor=Exception.class)
	public List<E> findAllByIsDelete() {
		return getDao().findAllByIsDelete();
	}
	public void bulkUpdate(String sql){
		getDao().bulkUpdate(sql);
	}
	@Override
	public void logicRemove(E entity) {
		getDao().logicRemove(entity);	
	}

	@Override
	public void logicRemove(long id) {
		getDao().logicRemove(id);	
	}
	protected abstract DAO getDao();
}
