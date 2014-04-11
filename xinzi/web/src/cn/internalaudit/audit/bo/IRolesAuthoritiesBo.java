package cn.internalaudit.audit.bo;

import java.util.List;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.RolesAuthorities;


public interface IRolesAuthoritiesBo extends IBo<RolesAuthorities> {
	public List<RolesAuthorities> findAuthorByRolesId(Long id);

	public RolesAuthorities findAuthorByAuthorName(Long id,String name);
	
	public void deleteRolesAuthorities(String ids);
}
