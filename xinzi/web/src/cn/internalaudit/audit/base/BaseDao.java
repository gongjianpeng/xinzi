package cn.internalaudit.audit.base;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.internalaudit.audit.security.LoginInfo;
import cn.internalaudit.audit.utils.GenericUtils;

public class BaseDao<E extends IEntity> extends HibernateDaoSupport implements
		IBaseDao<E> {

	/** 
	 * 
	 */
	@Autowired
	@Qualifier("loginInfo")
	private LoginInfo loginInfo;
	@Resource
	private SessionFactory mySessionFactory;

	@PostConstruct
	void injectSessionFactory() {
		super.setSessionFactory(mySessionFactory);
	}

	private static final long serialVersionUID = 1L;
	protected final Class<E> entityClass;

	public BaseDao() {
		this.entityClass = GenericUtils.getParameterClass(this.getClass());
	}

	public E find(long id) {
		return (E) getJpaTemplate().get(entityClass, id);
	}

	public void persist(final E entity) {
		if (loginInfo.getLoginPerson() != null) {
			entity.setCreatePersonId(loginInfo.getLoginPerson().getId());
			entity.setCreatePerson(loginInfo.getLoginPerson().getName());
			entity.setCreateDate(new Date());
			entity.setLastEditPersonId(loginInfo.getLoginPerson().getId());
			entity.setLastEditPerson(loginInfo.getLoginPerson().getName());
			entity.setLastEditDate(new Date());
		}
		getJpaTemplate().persist(entity);
	}

	public E merge(final E entity) {
		if (loginInfo.getLoginPerson() != null) {
			entity.setLastEditPersonId(loginInfo.getLoginPerson().getId());
			entity.setLastEditPerson(loginInfo.getLoginPerson().getName());
			entity.setLastEditDate(new Date());
		}
		return getJpaTemplate().merge(entity);
	}

	public void remove(final long id) {
		E entity = (E) getJpaTemplate().get(entityClass, id);
		remove(entity);
	}

	public void remove(E entity) {
		getJpaTemplate().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		String ql = "select e from " + entityClass.getName()
				+ " e where e.isDelete = false or e.isDelete is null";
		return getJpaTemplate().find(ql);
	}

	@SuppressWarnings("unchecked")
	public List find(String querySring) {
		return getJpaTemplate().find(querySring);
	}

	public List<E> findByParms(Map map) {
		String ql = "select e from " + entityClass.getName()
				+ " e where (e.isDelete = false or e.isDelete is null)";
		Object[] values = null;
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				if (map.get(name) == null) {
					map.remove(name);
					ql += " and " + name + " is null ";
				} else {
					String value = map.get(name).toString().trim();
					if (value.startsWith("%")||value.endsWith("%")) {
						ql += " and " + name + " like ? ";
					} else {
						ql += " and " + name + " = ? ";
					}
				}
			}
			if (map.size() <= 0)
				values = null;
			else
				values = map.values().toArray();
		}
		return getJpaTemplate().find(ql, values);
	}

	public List<E> findByLikeParms(Map map) {
		String ql = "select e from " + entityClass.getName()
				+ " e where (e.isDelete = false or e.isDelete is null)";
		Object[] values = null;
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like ? ";
			}
			values = map.values().toArray();
			for (Object value : values) {

				if (value instanceof Boolean) {
					if ((Boolean) value == true)
						value = "1";
					if ((Boolean) value == false)
						value = "0";
				}
			}
		}
		return getJpaTemplate().find(ql, values);
	}

	public HibernateTemplate getJpaTemplate() {
		return getHibernateTemplate();
	}

	@Override
	public E save(E entity) {
		if (entity.getId() == null) {
			persist(entity);
		} else {
			entity = merge(entity);
		}

		return entity;
	}

	@Override
	public void logicRemove(E entity) {
		String hql = "update " + entityClass.getName()
				+ " e set e.isDelete = true where e.id = ?";
		getHibernateTemplate().bulkUpdate(hql, entity.getId());
	}

	@Override
	public void logicRemove(long id) {
		String hql = "update " + entityClass.getName()
				+ " e set e.isDelete = true where e.id = ?";
		getHibernateTemplate().bulkUpdate(hql, id);
	}
	public void bulkUpdate(String sql) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().bulkUpdate(sql);
	}
	@Override
	public List<E> findAllByIsDelete() {
		String ql = "select e from " + entityClass.getName()
				+ " e where e.isDelete = true";
		return getJpaTemplate().find(ql);
	}
}
