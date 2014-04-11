package cn.internalaudit.audit.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IOrganization2Bo;
import cn.internalaudit.audit.dao.IOrganization2Dao;
import cn.internalaudit.audit.entitys.Organization2;

@Service("Organization2Bo")
public class Organization2Bo extends Bo<Organization2,IOrganization2Dao> implements IOrganization2Bo {
	
	@Autowired(required = true)
	private IOrganization2Dao organization2Dao;

	@Override
	protected IOrganization2Dao getDao() {
		return organization2Dao;
	}
	public boolean isAvailableByCode(Organization2 offshootBank, String code) {
		List<Organization2> offshootBanks = findByCode(code);
		if (offshootBank.getId() == null) {
			if (offshootBanks == null || offshootBanks.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Organization2 p : offshootBanks) {
				if (p.getId().longValue() != offshootBank.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public List<Organization2> findByCode(String code) {
		return organization2Dao.findByCode(code);
	}
	@Override
	public List<Organization2> findByName(String name) {
		return organization2Dao.findByName(name);
	}
	@Override
	public boolean isAvailableByName(Organization2 offshootBank, String name) {
		List<Organization2> offshootBanks = findByName(name);
		if (offshootBank.getId() == null) {
			if (offshootBanks == null || offshootBanks.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Organization2 p : offshootBanks) {
				if (p.getId().longValue() != offshootBank.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public List<Organization2> getFindByNameOrCode(String parentOrganization,String code,String name) {
		return organization2Dao.getFindByNameOrCode(parentOrganization,code,name);
	}
	@Override
	public List<Organization2> findByNotHeadquarters() {
		return getDao().findByNotHeadquarters();
	}
	@Override
	public boolean checkHeadquarters(Organization2 offshootBank) {
		
		String id=null;
		List<Organization2> offshootBanks = findByHeadquarters2(id);
		if (offshootBank.getId() == null) {
			if (offshootBanks == null || offshootBanks.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Organization2 p : offshootBanks) {
				if (p.getId().longValue() != offshootBank.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	
	public List<Organization2> findByNoparentOrganizationID(Long id) {
		
		return  organization2Dao.findByNoparentOrganizationID(id);
	}
	public List<Organization2> findByRemark(String remark){
		
		return organization2Dao.findByRemark(remark);
		
	}
	
	
	@Override
	public List<Organization2> findByHeadquarters() {
		return getDao().findByHeadquarters();
	}
	@Override
	public List<Organization2> findByHeadquarters2(String id) {
		return getDao().findByHeadquarters2(id);
	}
	
	@Override
	public List<Organization2> findOrgByNoDelete() {
		return getDao().findOrgByNoDelete();
	}
	

}
