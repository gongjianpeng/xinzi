package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.RolesAuthorities;


/**
 * 
 * 
 * @author bd02
 * 
 */
public interface IRolesAuthoritiesDao extends IBaseDao<RolesAuthorities> {
	public List<RolesAuthorities> findAuthorByRolesId(Long id);

	public RolesAuthorities findAuthorByAuthorName(Long id,String name);
	
	public void deleteRolesAuthorities(String ids);
}
