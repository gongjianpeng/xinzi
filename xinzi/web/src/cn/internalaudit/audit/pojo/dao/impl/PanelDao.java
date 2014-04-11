package cn.internalaudit.audit.pojo.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.pojo.Panel;
import cn.internalaudit.audit.pojo.dao.IPanelDao;


/**
 * 
 * @author
 * 
 */
@Repository
public class PanelDao extends BaseDao<Panel> implements
		IPanelDao {

	public List<Panel> findPanelByTpye(Integer type) {

		String sql = "select d from Panel d where d.type=? and d.isDelete = false order by d.type";
		List<Panel> PanelList = getHibernateTemplate().find(
				sql, type);
		return PanelList;

	}

	/**
	 * 
	 */

	public List<Panel> findPanelByName(Integer type,
			String name) {
		String sql = "select d from Panel d where d.type=" + type
				+ "and d.name like ? order by d.type";
		List<Panel> PanelList = getHibernateTemplate().find(
				sql, "%" + name + "%");
		return PanelList;
	}
	/**
	 * 
	 */

	public Panel findPanelByName(String type,
			String name) {
		String sql = "select d from Panel d where d.type = ? and d.name = ?";				
		List<Panel> PanelList = getHibernateTemplate().find(
				sql, type, name);
		
		return DataAccessUtils.singleResult(PanelList);
	}


	@Override
	public List<Panel> findPanelByCode(String code,
			Integer type) {
		String hql = "select d from Panel d where d.code=? and d.type=? order by d.type";
		List<Panel> PanelList = getHibernateTemplate().find(
				hql, code, type);
		return PanelList;
	}

	@Override
	public List<Panel> findPanelByid(Long id, Integer type) {
		String hql = "select d from Panel d where d.id=? and d.type=? order by d.type";
		List<Panel> PanelList = getHibernateTemplate().find(
				hql, id, type);
		return PanelList;
	}

	@Override
	public List<Panel> findPanelByName1(String name, int type) {
		String sql = "select d from Panel d where d.name = ? and d.type = ? order by d.type";
		return this.getHibernateTemplate().find(sql, name, type);
	}
	public List<Panel> findAll(){
		String sql = "select d from Panel d order by d.type";
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public Panel findPanelByCode(String code, String type) {
		String  sql=" select d  from  Panel d  where d.code = ? and d.type=?";
		List<Panel> PanelList = getHibernateTemplate().find(sql,code,type);
		return DataAccessUtils.singleResult(PanelList);
	}

	@Override
	public List<Panel> findpanelByName(Integer type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panel findpanelByName(String type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Panel> findpanelByTpye(Integer type) {
		// TODO Auto-generated method stub
		return null;
	}

}
