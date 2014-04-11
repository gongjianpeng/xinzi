package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Organization;


public interface IOrganizationDao extends IBaseDao<Organization> {
	public List<Organization> findByCode(String code);
	
	public List<Organization> findByName(String name);
	
	public List<Organization> getFindByNameOrCode(String parentOrganization,String code,String name);
	/*****
	 * 
	 * @return
	 */
	public List<Organization> findByNotHeadquarters();
	/*****
	 * 
	 * @return
	 */
	public List<Organization> findByHeadquarters();
	/*****
	 * 
	 * @return
	 */
	public List<Organization> findOrgByNoDelete();
	/***
	 * 
	 * @param offshootBank
	 * @return
	 */
	public boolean checkHeadquarters(Organization offshootBank);

	List<Organization> findByHeadquarters2(String id);
}
