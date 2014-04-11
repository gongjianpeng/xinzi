package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.ICompanyDao;
import cn.internalaudit.audit.dao.IDataDictionaryDao;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.entitys.base.Dealer;


/**
 * 
 * @author
 * 
 */
@Repository
public class CompanyDao extends BaseDao<Company> implements
		ICompanyDao {

	public List<Company> findCompanyByTpye(Integer type) {

		String sql = "select d from Company d";
		List<Company> CompanyList = getHibernateTemplate().find(
				sql, type);
		return CompanyList;

	}

	/**
	 * 
	 */

	public List<Company> findCompanyByName(Integer type,
			String name) {
		String sql = "select d from Company d where d.type=" + type
				+ "and d.name like ? order by d.type";
		List<Company> CompanyList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return CompanyList;
	}
	/**
	 * 
	 */

	public Company findCompanyByName(String type,
			String name) {
		String sql = "select d from Company d where d.type = ? and d.name = ?";				
		List<Company> CompanyList = getHibernateTemplate().find(
				sql, type, name);
		
		return DataAccessUtils.singleResult(CompanyList);
	}


	@Override
	public List<Company> findCompanyByCode(String code,
			Integer type) {
		String hql = "select d from Company d where d.code=? and d.type=? order by d.type";
		List<Company> CompanyList = getHibernateTemplate().find(
				hql, code, type);
		return CompanyList;
	}

	@Override
	public List<Company> findCompanyByid(Long id, Integer type) {
		String hql = "select d from Company d where d.id=? and d.type=? order by d.type";
		List<Company> CompanyList = getHibernateTemplate().find(
				hql, id, type);
		return CompanyList;
	}

	@Override
	public List<Company> findCompanyByName1(String name, int type) {
		String sql = "select d from Company d where d.name = ? and d.type = ? order by d.type";
		return this.getHibernateTemplate().find(sql, name, type);
	}
	public List<Company> findAll(){
		String sql = "select d from Company d ";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public Company findCompanyByCode(String code, String type) {
		String  sql=" select d  from  Company d  where d.code = ? and d.type=?";
		List<Company> CompanyList = getHibernateTemplate().find(sql,code,type);
		return DataAccessUtils.singleResult(CompanyList);
	}

	@Override
	public Company findOrgid(String orgid) {
		String sql ="from Company d where d.org=?";
		List<Company> DealerList = getHibernateTemplate().find(sql,orgid);
		return DataAccessUtils.singleResult(DealerList);
	}
	@Override
	public Company findDatacompanyById(Long id) {
		String  sql="from Company d  where d.id = :id";//u.name=:myName"; 
		String paramName="id";
		List<Company> lists=getHibernateTemplate().findByNamedParam(sql, paramName, id);
		Company objs=lists.get(0);
		//return DataAccessUtils.singleResult(lists);
		return objs;
	}
}
