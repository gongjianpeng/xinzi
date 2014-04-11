package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IDataDictionaryDao;
import cn.internalaudit.audit.entitys.DataDictionary;


/**
 * 
 * @author
 * 
 */
@Repository
public class DataDictionaryDao extends BaseDao<DataDictionary> implements
		IDataDictionaryDao {

	public List<DataDictionary> findDataDictionaryByTpye(Integer type) {

		String sql = "select d from DataDictionary d where d.type=? and d.isDelete = false order by d.type";
		List<DataDictionary> dataDictionaryList = getHibernateTemplate().find(
				sql, type);
		return dataDictionaryList;

	}
	
	public List<DataDictionary> findDataDictionaryByStringType(String type) {

		String sql = "select d from DataDictionary d where d.type=? and d.isDelete = false order by d.type";
		List<DataDictionary> dataDictionaryList = getHibernateTemplate().find(
				sql, type);
		return dataDictionaryList;

	}


	/**
	 * 
	 */

	public List<DataDictionary> findDataDictionaryByName(Integer type,
			String name) {
		String sql = "select d from DataDictionary d where d.type=" + type
				+ "and d.name like ? order by d.type";
		List<DataDictionary> dataDictionaryList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return dataDictionaryList;
	}
	/**
	 * 
	 */

	public DataDictionary findDataDictionaryByName(String type,
			String name) {
		String sql = "select d from DataDictionary d where d.type = ? and d.name = ?";				
		List<DataDictionary> dataDictionaryList = getHibernateTemplate().find(
				sql, type, name);
		
		return DataAccessUtils.singleResult(dataDictionaryList);
	}


	@Override
	public List<DataDictionary> findDataDictionaryByCode(String code,
			Integer type) {
		String hql = "select d from DataDictionary d where d.code=? and d.type=? order by d.type";
		List<DataDictionary> dataDictionaryList = getHibernateTemplate().find(
				hql, code, type);
		return dataDictionaryList;
	}

	@Override
	public List<DataDictionary> findDataDictionaryByid(Long id, Integer type) {
		String hql = "select d from DataDictionary d where d.id=? and d.type=? order by d.type";
		List<DataDictionary> dataDictionaryList = getHibernateTemplate().find(
				hql, id, type);
		return dataDictionaryList;
	}

	@Override
	public List<DataDictionary> findDataDictionaryByName1(String name, int type) {
		String sql = "select d from DataDictionary d where d.name = ? and d.type = ? order by d.type";
		return this.getHibernateTemplate().find(sql, name, type);
	}
	public List<DataDictionary> findAll(){
		String sql = "select d from DataDictionary d order by d.type";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public DataDictionary findDataDictionaryByCode(String code, String type) {
		String  sql=" select d  from  DataDictionary d  where d.code = ? and d.type=?";
		List<DataDictionary> dataDictionaryList = getHibernateTemplate().find(sql,code,type);
		return DataAccessUtils.singleResult(dataDictionaryList);
	}

}
