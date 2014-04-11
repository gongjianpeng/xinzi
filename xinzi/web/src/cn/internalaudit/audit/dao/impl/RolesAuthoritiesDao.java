package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IRolesAuthoritiesDao;
import cn.internalaudit.audit.entitys.RolesAuthorities;


/**
 * 
 * 
 * @author 
 * 
 */
@Repository
public class RolesAuthoritiesDao extends BaseDao<RolesAuthorities> implements
		IRolesAuthoritiesDao {


	public List<RolesAuthorities> findAuthorByRolesId(Long id) {
		String sql = "select r from RolesAuthorities r where r.roles.id=? order by r.name";
		List<RolesAuthorities> rolesAuthorList = getHibernateTemplate().find(
				sql, id);
		return rolesAuthorList;
	}

	public RolesAuthorities findAuthorByAuthorName(Long id, String name) {
		String sql = "select r from RolesAuthorities r where r.roles.id=? and r.authoritiesKey.name=?";
		List<RolesAuthorities> rolesList = getHibernateTemplate().find(sql, id,
				name);
		if (rolesList == null || rolesList.size() == 0) {
			return null;
		}
		return (RolesAuthorities) rolesList.get(0);
	}
	
	public void deleteRolesAuthorities(String ids){
		String sql = "delete from RolesAuthorities where id in("+ids+")";
		this.getHibernateTemplate().bulkUpdate(sql);
	}
}
