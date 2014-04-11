package cn.internalaudit.audit.pojo.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.pojo.DiyModel;
import cn.internalaudit.audit.pojo.dao.IDiyModelDao;


/**
 * 
 * @author
 * 
 */
@Repository
public class DiyModelDao extends BaseDao<DiyModel> implements
		IDiyModelDao {

	public List<DiyModel> findDiyModelByType(String codetype) {

		String sql = "select d.name , d.img , d.type , d.scale , d.x , d.y from DiyModel d where d.codetype=? and d.isDelete = false";
		List<DiyModel> DiyModelList = getHibernateTemplate().find(
				sql, codetype);
		return DiyModelList;

	}
	
	public List<DiyModel> findDiyModelByTpye(String codetype) {

		String sql = "select  d.name , d.img , d.type , d.scale , d.x , d.y from DiyModel d where  d.isDelete = false";
		List<DiyModel> DiyModelList = getHibernateTemplate().find(
				sql, codetype);
		return DiyModelList;

	}
	
	public List<DiyModel> findDiyModelByStringType(String codetype) {

		String sql = "select d from DiyModel d where d.codetype=? and d.isDelete = false ";
		List<DiyModel> DiyModelList = getHibernateTemplate().find(sql, codetype);
		return DiyModelList;

	}


	/**
	 * 
	 */

	public List<DiyModel> findDiyModelByName(Integer codetype,
			String name) {
		String sql = "select d from DiyModel d where d.codetype=" + codetype
				+ "and d.name like ? order by d.codetype";
		List<DiyModel> DiyModelList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return DiyModelList;
	}
	/**
	 * 
	 */

	public DiyModel findDiyModelByName(String codetype,
			String name) {
		String sql = "select d from DiyModel d where d.codetype = ? and d.name = ?";				
		List<DiyModel> DiyModelList = getHibernateTemplate().find(
				sql, codetype, name);
		
		return DataAccessUtils.singleResult(DiyModelList);
	}


	@Override
	public List<DiyModel> findDiyModelByCode(String code,
			Integer codetype) {
		String hql = "select d from DiyModel d where d.code=? and d.codetype=? order by d.codetype";
		List<DiyModel> DiyModelList = getHibernateTemplate().find(
				hql, code, codetype);
		return DiyModelList;
	}

	@Override
	public List<DiyModel> findDiyModelByid(Long id, Integer codetype) {
		String hql = "select d from DiyModel d where d.id=? and d.codetype=? order by d.codetype";
		List<DiyModel> DiyModelList = getHibernateTemplate().find(
				hql, id, codetype);
		return DiyModelList;
	}

	@Override
	public List<DiyModel> findDiyModelByName1(String name, int codetype) {
		String sql = "select d from DiyModel d where d.name = ? and d.codetype = ? order by d.codetype";
		return this.getHibernateTemplate().find(sql, name, codetype);
	}
	
	public List<DiyModel> findDiyModelByName(String name) {
		String sql = "select d from DiyModel d where d.name = ? order by d.name ";
		return this.getHibernateTemplate().find(sql, name);
	}
	public List<DiyModel> findAll(){
		String sql = "select d from DiyModel d order by d.codetype";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public DiyModel findDiyModelByCode(String code, String codetype) {
		String  sql=" select d  from  DiyModel d  where d.code = ? and d.codetype=?";
		List<DiyModel> DiyModelList = getHibernateTemplate().find(sql,code,codetype);
		return DataAccessUtils.singleResult(DiyModelList);
	}

	@Override
	public List<DiyModel> findDiyModelByCodeName(String code, String name) {
		String sql = "select d from DiyModel d where d.code = ? and d.name = ? order by d.codetype";
		return this.getHibernateTemplate().find(sql, code, name);
	}

	public List<DiyModel> findDiyModelBydmodelid(String userfordmodelid) {
		String sql = "select d from DiyModel d where  d.userfordmodelid = ? order by d.userfordmodelid";
		return this.getHibernateTemplate().find(sql,  userfordmodelid);
	}
	
	public List<DiyModel> findDiyModelByMoidname(String userfordmodelid,String name) {
		String sql = "select d from DiyModel d where  d.userfordmodelid = ? and d.name = ? order by d.userfordmodelid";
		return this.getHibernateTemplate().find(sql,  userfordmodelid,name);
	}
	
}
