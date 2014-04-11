package cn.internalaudit.audit.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IDataQuoteDao;
import cn.internalaudit.audit.entitys.DataQuote;
import cn.internalaudit.audit.entitys.Parameterqiye;


/**
 * 
 * @author
 * 
 */
@Repository
public class DataQuoteDao extends BaseDao<DataQuote> implements
		IDataQuoteDao {

	public List<DataQuote> findDataQuoteByTpye(Integer type) {

		String sql = "select d from DataQuote d where d.type=? and d.isDelete = false order by d.type";
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(
				sql, type);
		return DataQuoteList;

	}
	
	public List<DataQuote> findDataQuoteByStringType(String type) {

		String sql = "select d from DataQuote d where d.type=? and d.isDelete = false order by d.type";
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(
				sql, type);
		return DataQuoteList;

	}


	/**
	 * 
	 */

	public List<DataQuote> findDataQuoteByName(Integer type,
			String name) {
		String sql = "select d from DataQuote d where d.type=" + type
				+ "and d.name like ? order by d.type";
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return DataQuoteList;
	}
	/**
	 * 
	 */

	public DataQuote findDataQuoteByName(String type,
			String name) {
		String sql = "select d from DataQuote d where d.type = ? and d.name = ?";				
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(
				sql, type, name);
		
		return DataAccessUtils.singleResult(DataQuoteList);
	}


	@Override
	public List<DataQuote> findDataQuoteByCode(String code,
			Integer type) {
		String hql = "select d from DataQuote d where d.code=? and d.type=? order by d.type";
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(
				hql, code, type);
		return DataQuoteList;
	}

	@Override
	public List<DataQuote> findDataQuoteByid(Long id, Integer type) {
		String hql = "select d from DataQuote d where d.id=? and d.type=? order by d.type";
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(
				hql, id, type);
		return DataQuoteList;
	}

	@Override
	public List<DataQuote> findDataQuoteByName1(String name, int type) {
		String sql = "select d from DataQuote d where d.name = ? and d.type = ? order by d.type";
		return this.getHibernateTemplate().find(sql, name, type);
	}
	public List<DataQuote> findAll(){
		String sql = "select d from DataQuote d order by d.type";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public DataQuote findDataQuoteByCode(String code, String type) {
		String  sql=" select d  from  DataQuote d  where d.code = ? and d.type=?";
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(sql,code,type);
		return DataAccessUtils.singleResult(DataQuoteList);
	}
	
	@Override
	public DataQuote findDataQuoteByBtype(String btype,String bpinpai) {
		String  sql=" select d  from  DataQuote d  where d.btype=? and d.bpinpai=?";
		List<DataQuote> DataQuoteList = getHibernateTemplate().find(sql,btype,bpinpai);
		return DataAccessUtils.singleResult(DataQuoteList);
	}
	
	
	  @Override
	    public List<DataQuote> findByParms(Map map)
	    {
	        String ql = "select q from Parameterqiye q where (q.isDelete = false or q.isDelete is null)";
	        if (map.size() > 0) {
	            Set<String> names = map.keySet();
	            for (String name : names) {
	                ql += " and q." + name + " = '" + map.get(name) + "' ";
	            }
	        }
	        System.out.println("DataQuote---"+ql);
	       List<DataQuote> DataQuoteList=this.find(ql);
	       return (List<DataQuote>) DataAccessUtils.singleResult(DataQuoteList);
	    }

	   
	    
}
