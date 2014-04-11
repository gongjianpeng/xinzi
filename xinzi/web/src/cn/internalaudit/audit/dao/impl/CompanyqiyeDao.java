package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.ICompanyqiyeDao;
import cn.internalaudit.audit.dao.IDataDictionaryDao;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.base.Companyqiye;


/**
 * 
 * @author
 * 
 */
@Repository
public class CompanyqiyeDao extends BaseDao<Companyqiye> implements
		ICompanyqiyeDao {

	public List<Companyqiye> findCompanyqiyeByTpye(Integer type) {

		String sql = "select d from Companyqiye d";
		List<Companyqiye> CompanyList = getHibernateTemplate().find(
				sql, type);
		return CompanyList;

	}

	/**
	 * 
	 */

	public List<Companyqiye> findCompanyqiyeByName(Integer type,
			String name) {
		String sql = "select d from Companyqiye d where d.type=" + type
				+ "and d.name like ? order by d.type";
		List<Companyqiye> CompanyList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return CompanyList;
	}
	/**
	 * 
	 */

	public Companyqiye findCompanyqiyeByName(String type,
			String name) {
		String sql = "select d from Companyqiye d where d.type = ? and d.name = ?";				
		List<Companyqiye> CompanyList = getHibernateTemplate().find(
				sql, type, name);
		
		return DataAccessUtils.singleResult(CompanyList);
	}


	@Override
	public List<Companyqiye> findCompanyqiyeByCode(String code,
			Integer type) {
		String hql = "select d from Companyqiye d where d.code=? and d.type=? order by d.type";
		List<Companyqiye> CompanyList = getHibernateTemplate().find(
				hql, code, type);
		return CompanyList;
	}

	@Override
	public List<Companyqiye> findCompanyqiyeByid(Long id, Integer type) {
		String hql = "select d from Companyqiye d where d.id=? and d.type=? order by d.type";
		List<Companyqiye> CompanyList = getHibernateTemplate().find(
				hql, id, type);
		return CompanyList;
	}

	@Override
	public List<Companyqiye> findCompanyqiyeByName1(String name, int type) {
		String sql = "select d from Companyqiye d where d.name = ? and d.type = ? order by d.type";
		return this.getHibernateTemplate().find(sql, name, type);
	}
	public List<Companyqiye> findAll(){
		String sql = "select d from Companyqiye d ";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public Companyqiye findCompanyqiyeByCode(String code, String type) {
		String  sql=" select d  from  Companyqiye d  where d.code = ? and d.type=?";
		List<Companyqiye> CompanyList = getHibernateTemplate().find(sql,code,type);
		return DataAccessUtils.singleResult(CompanyList);
	}

}
