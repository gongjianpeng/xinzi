package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IRolesDao;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;


/**
 * 
 * 
 * @author 
 * 
 */
@Repository
public class RolesDao extends BaseDao<Roles> implements IRolesDao {

	public List<Roles> rolesList(String name) {
		String sql = "select r from Roles r where r.name like ?";
		List<Roles> rolesList = getHibernateTemplate().find(sql,
				"%" + name + "%");
		return rolesList;
	}
	/****
	 * 
	 * @param personId
	 * @return 
	 */
	@Override
	public List<Roles> findByPersonId(Long personId) {
		StringBuffer hql= new StringBuffer();
		hql.append("select r from Roles r join r.persons p where p.id = ?");
		return getJpaTemplate().find(hql.toString(),personId);
	}

    /**
     * 
     */
    @Override
    public Roles findRoleIdByName(String name){
        String sql = "select r from Roles r  where r.name = ?";             
        List<Roles> role = getHibernateTemplate().find(
                sql, name);
        return DataAccessUtils.singleResult(role);
    }
}
