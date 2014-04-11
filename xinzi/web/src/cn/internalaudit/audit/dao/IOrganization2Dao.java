package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Organization2;


public interface IOrganization2Dao extends IBaseDao<Organization2> {
	public List<Organization2> findByCode(String code);
	
	public List<Organization2> findByName(String name);
	public List<Organization2> findByRemark(String remark);
	
	public List<Organization2> getFindByNameOrCode(String parentOrganization2,String code,String name);
	/*****
	 * 
	 * @return
	 */
	public List<Organization2> findByNotHeadquarters();
	/*****
	 * 
	 * @return
	 */
	public List<Organization2> findByHeadquarters();
	/*****
	 * 
	 * @return
	 */
	public List<Organization2> findByNoparentOrganizationID(Long id);
	
	public List<Organization2> findOrgByNoDelete();
	/***
	 * 
	 * @param offshootBank
	 * @return
	 */
	public boolean checkHeadquarters(Organization2 offshootBank);

	List<Organization2> findByHeadquarters2(String id);
}
