package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.ICompanyDao;
import cn.internalaudit.audit.dao.IDataDictionaryDao;
import cn.internalaudit.audit.dao.IDealerDao;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.entitys.base.Dealer;


/**
 * 
 * @author
 * 
 */
@Repository
public class DealerDao extends BaseDao<Dealer> implements
		IDealerDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Dealer> findDealerByTpye(Integer type) {

		String sql = "select d from Dealer d";
		List<Dealer> DealerList = getHibernateTemplate().find(
				sql, type);
		return DealerList;

	}

	/**
	 * 
	 */

	public List<Dealer> findDealerByName(Integer type,
			String name) {
		String sql = "select d from Dealer d where d.type=" + type
				+ "and d.name like ? order by d.type";
		List<Dealer> DealerList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return DealerList;
	}
	/**
	 * 
	 */

	public Dealer findDealerByName(String type,
			String name) {
		String sql = "select d from Dealer d where d.type = ? and d.name = ?";				
		List<Dealer> DealerList = getHibernateTemplate().find(
				sql, type, name);
		
		return DataAccessUtils.singleResult(DealerList);
	}


	@Override
	public List<Dealer> findDealerByCode(String code,
			Integer type) {
		String hql = "select d from Dealer d where d.code=? and d.type=? order by d.type";
		List<Dealer> DealerList = getHibernateTemplate().find(
				hql, code, type);
		return DealerList;
	}

	@Override
	public List<Dealer> findDealerByid(Long id, Integer type) {
		String hql = "select d from Dealer d where d.id=? and d.type=? order by d.type";
		List<Dealer> DealerList = getHibernateTemplate().find(
				hql, id, type);
		return DealerList;
	}

	@Override
	public List<Dealer> findDealerByName1(String name, int type) {
		String sql = "select d from Dealer d where d.name = ? and d.type = ? order by d.type";
		return this.getHibernateTemplate().find(sql, name, type);
	}
	public List<Dealer> findAll(){
		String sql = "select d from Dealer d ";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public Dealer findDealerByCode(String code, String type) {
		String  sql=" select d  from  Dealer d  where d.code = ? and d.type=?";
		List<Dealer> DealerList = getHibernateTemplate().find(sql,code,type);
		return DataAccessUtils.singleResult(DealerList);
	}
	
	@Override
	public Dealer findDataDealerById(Long id) {
		String  sql="from Dealer d  where d.id = :id";//u.name=:myName"; 
		String paramName="id";
		List<Dealer> lists=getHibernateTemplate().findByNamedParam(sql, paramName, id);
		Dealer objs=lists.get(0);
		//return DataAccessUtils.singleResult(lists);
		return objs;
	}

	@Override
	public Dealer findOrgid(String id) {
		String sql ="from Dealer d where d.org=?";
		List<Dealer> DealerList = getHibernateTemplate().find(sql,id);
		return DataAccessUtils.singleResult(DealerList);
	}

}
