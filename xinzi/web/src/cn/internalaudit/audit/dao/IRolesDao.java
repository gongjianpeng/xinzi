package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Roles;


/**
 * 
 * 
 * @author bd02
 * 
 */
public interface IRolesDao extends IBaseDao<Roles> {
	public List<Roles> rolesList(String name);
	/****
	 * 
	 * @param personId
	 * @return 
	 */
	public List<Roles> findByPersonId(Long personId);
	
	public Roles findRoleIdByName(String name);
}
