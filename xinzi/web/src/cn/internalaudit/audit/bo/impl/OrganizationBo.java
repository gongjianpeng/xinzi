package cn.internalaudit.audit.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IOrganizationBo;
import cn.internalaudit.audit.dao.IOrganizationDao;
import cn.internalaudit.audit.entitys.Organization;

@Service("OrganizationBo")
public class OrganizationBo extends Bo<Organization,IOrganizationDao> implements IOrganizationBo {
	
	@Autowired(required = true)
	private IOrganizationDao organizationDao;

	@Override
	protected IOrganizationDao getDao() {
		return organizationDao;
	}
	public boolean isAvailableByCode(Organization offshootBank, String code) {
		List<Organization> offshootBanks = findByCode(code);
		if (offshootBank.getId() == null) {
			if (offshootBanks == null || offshootBanks.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Organization p : offshootBanks) {
				if (p.getId().longValue() != offshootBank.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public List<Organization> findByCode(String code) {
		return organizationDao.findByCode(code);
	}
	@Override
	public List<Organization> findByName(String name) {
		return organizationDao.findByName(name);
	}
	@Override
	public boolean isAvailableByName(Organization offshootBank, String name) {
		List<Organization> offshootBanks = findByName(name);
		if (offshootBank.getId() == null) {
			if (offshootBanks == null || offshootBanks.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Organization p : offshootBanks) {
				if (p.getId().longValue() != offshootBank.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public List<Organization> getFindByNameOrCode(String parentOrganization,String code,String name) {
		return organizationDao.getFindByNameOrCode(parentOrganization,code,name);
	}
	@Override
	public List<Organization> findByNotHeadquarters() {
		return getDao().findByNotHeadquarters();
	}
	@Override
	public boolean checkHeadquarters(Organization offshootBank) {
		
		String id=null;
		List<Organization> offshootBanks = findByHeadquarters2(id);
		if (offshootBank.getId() == null) {
			if (offshootBanks == null || offshootBanks.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Organization p : offshootBanks) {
				if (p.getId().longValue() != offshootBank.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public List<Organization> findByHeadquarters() {
		return getDao().findByHeadquarters();
	}
	@Override
	public List<Organization> findByHeadquarters2(String id) {
		return getDao().findByHeadquarters2(id);
	}
	
	@Override
	public List<Organization> findOrgByNoDelete() {
		return getDao().findOrgByNoDelete();
	}
	

}
