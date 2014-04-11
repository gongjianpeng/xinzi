package cn.internalaudit.audit.pojo.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.pojo.Accessories;
import cn.internalaudit.audit.pojo.dao.IAccessDao;


/**
 * 
 * @author
 * 
 */
@Repository
public class AccessDao extends BaseDao<Accessories> implements
		IAccessDao {

	public List<Accessories> findPanelByTpye(Integer type) {

		String sql = "select d from Accessories d where d.type=? and d.isDelete = false order by d.type";
		List<Accessories> AccessoriesList = getHibernateTemplate().find(
				sql, type);
		return AccessoriesList;

	}

	/**
	 * 
	 */

	public List<Accessories> findAccessoriesByName(Integer type,
			String name) {
		String sql = "select d from Accessories d where d.type=" + type
				+ "and d.name like ? order by d.type";
		List<Accessories> AccessoriesList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return AccessoriesList;
	}
	/**
	 * 
	 */

	public Accessories findAccessoriesByName(String type,
			String name) {
		String sql = "select d from Accessories d where d.type = ? and d.name = ?";				
		List<Accessories> AccessoriesList = getHibernateTemplate().find(
				sql, type, name);
		
		return DataAccessUtils.singleResult(AccessoriesList);
	}


	@Override
	public List<Accessories> findAccessoriesByCode(String code,
			Integer type) {
		String hql = "select d from Accessories d where d.code=? and d.type=? order by d.type";
		List<Accessories> AccessoriesList = getHibernateTemplate().find(
				hql, code, type);
		return AccessoriesList;
	}

	@Override
	public List<Accessories> findAccessoriesByid(Long id, Integer type) {
		String hql = "select d from Accessories d where d.id=? and d.type=? order by d.type";
		List<Accessories> AccessoriesList = getHibernateTemplate().find(
				hql, id, type);
		return AccessoriesList;
	}

	@Override
	public List<Accessories> findAccessoriesByName1(String name, int type) {
		String sql = "select d from Accessories d where d.name = ? and d.type = ? order by d.type";
		return this.getHibernateTemplate().find(sql, name, type);
	}
	public List<Accessories> findAll(){
		String sql = "select d from Accessories d order by d.type";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public Accessories findAccessoriesByCode(String code, String type) {
		String  sql=" select d  from  Accessories d  where d.code = ? and d.type=?";
		List<Accessories> AccessoriesList = getHibernateTemplate().find(sql,code,type);
		return DataAccessUtils.singleResult(AccessoriesList);
	}

	@Override
	public List<Accessories> findAccessoriesByTpye(Integer type) {
		// TODO Auto-generated method stub
		return null;
	}



}
