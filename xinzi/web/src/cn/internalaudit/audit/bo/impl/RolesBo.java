package cn.internalaudit.audit.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IRolesBo;
import cn.internalaudit.audit.dao.IAuthoritiesKeyDao;
import cn.internalaudit.audit.dao.IRolesDao;
import cn.internalaudit.audit.dao.impl.RolesDao;
import cn.internalaudit.audit.entitys.AuthoritiesKey;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.entitys.RolesAuthorities;


/**
 * 
 * 
 * @author bd02
 * 
 */
@Service("RolesBo")
public class RolesBo extends Bo<Roles, IRolesDao> implements IRolesBo {
	@Autowired(required = true)
	private IRolesDao rolesDao;

	@Autowired(required = true)
	private IAuthoritiesKeyDao authoritiesKeyDao;
	
	@Override
	protected IRolesDao getDao() {
		return rolesDao;
	}

	public List<Roles> rolesList(String name) {

		return getDao().rolesList(name);
	}
	/****
	 * 
	 * @param personId
	 * @return 
	 */
	@Override
	public List<Roles> findByPersonId(Long personId) {
		return getDao().findByPersonId(personId);
	}
	
	/****
	 * Long rolesId,String authorKeys
	 *this.rolesBo , RoleDao ,
	 *authoritiesKeyBo
	 */
	public void addAuthor(Long rolesId,String authorKeys){
		Roles roles = this.find(rolesId);
		String[] authorKeyIds = authorKeys.split(",");
		for (String authorKeyId : authorKeyIds) {
			RolesAuthorities rolesAuthorities = new RolesAuthorities();
			AuthoritiesKey authoritiesKey =authoritiesKeyDao.find(Long.valueOf(authorKeyId));
			rolesAuthorities.setAuthoritiesKey(authoritiesKey);
			rolesAuthorities.setRoles(roles);
		//	String parentKeyName=authoritiesKey.getParentAuthoritiesKey()==null?"":authoritiesKey.getParentAuthoritiesKey().getName()+'-';
			rolesAuthorities.setName(authoritiesKey.getFullName());
			List<RolesAuthorities> rolesAuthoritiess = roles.getRolesAuthoritiess();
			if (!exsits(authoritiesKey, rolesAuthoritiess)) {
				roles.getRolesAuthoritiess().add(rolesAuthorities);
			}
		}
		this.save(roles);
	}

	private boolean exsits(AuthoritiesKey authoritiesKey,
			List<RolesAuthorities> rolesAuthoritiess) {
		for (RolesAuthorities rolesAuthor : rolesAuthoritiess) {
			if (rolesAuthor.getAuthoritiesKey() == authoritiesKey) {
				return true;
			}
		}
		return false;
	}
	@Override
    public Roles findRoleIdByName(String name)
    {
        System.out.println("rolesBo---------");
        return rolesDao.findRoleIdByName(name);
    }
	
}
