package cn.internalaudit.audit.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IRolesAuthoritiesBo;
import cn.internalaudit.audit.dao.IRolesAuthoritiesDao;
import cn.internalaudit.audit.entitys.RolesAuthorities;


/**
 * 
 * 
 * @author bd02
 * 
 */
@Service("RolesAuthoritiesBo")
public class RolesAuthoritiesBo extends
		Bo<RolesAuthorities, IRolesAuthoritiesDao> implements
		IRolesAuthoritiesBo {
	@Autowired(required = true)
	private IRolesAuthoritiesDao rolesAuthoritiesDao;

	@Override
	protected IRolesAuthoritiesDao getDao() {
		return rolesAuthoritiesDao;
	}

	public List<RolesAuthorities> findAuthorByRolesId(Long id) {

		return this.getDao().findAuthorByRolesId(id);
	}

	public RolesAuthorities findAuthorByAuthorName(Long id,String name) {
		return this.getDao().findAuthorByAuthorName(id,name);
	}
	
	public void deleteRolesAuthorities(String ids){
		this.getDao().deleteRolesAuthorities(ids);
	}
}
