package cn.internalaudit.audit.bo;

import java.util.List;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Organization;


public interface IOrganizationBo extends IBo<Organization> {
	/*********
	 * 
	 * @param code
	 * @return
	 */
	public List<Organization> findByCode(String code);
	/********
	 * 
	 * @param name
	 * @return
	 */
	
	public List<Organization> findByName(String name);
	/********
	 * 
	 * @param offshootBank
	 * @param code
	 * @return
	 */
	public boolean isAvailableByCode(Organization offshootBank, String code);
	
	/********
	 * 	
	 * @param offshootBank
	 * @param name
	 * @return
	 */
	public boolean isAvailableByName(Organization offshootBank, String name);
	
	
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
	public List<Organization> findByHeadquarters2(String id);
	/***
	 * 
	 * @param offshootBank
	 * @return
	 */
	public boolean checkHeadquarters(Organization offshootBank);
	/*****
	 * 
	 * @return
	 */
	public List<Organization> findOrgByNoDelete();

}
