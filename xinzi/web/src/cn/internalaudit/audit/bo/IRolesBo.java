package cn.internalaudit.audit.bo;

import java.util.List;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Roles;


/**
 * 
 * 
 * @author bd02
 * 
 */
public interface IRolesBo extends IBo<Roles> {
	/**
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public List<Roles> rolesList(String name);
	/****
	 * 
	 * @param personId
	 * @return 
	 */
	public List<Roles> findByPersonId(Long personId);
	
	public Roles findRoleIdByName(String name);
	
	public void addAuthor(Long rolesId,String authorKeys);
}
