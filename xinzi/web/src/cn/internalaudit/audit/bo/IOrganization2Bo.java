package cn.internalaudit.audit.bo;

import java.util.List;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Organization2;


public interface IOrganization2Bo extends IBo<Organization2> {
	/*********
	 * 
	 * @param code
	 * @return
	 */
	public List<Organization2> findByCode(String code);
	/********
	 * 
	 * @param name
	 * @return
	 */
	
	public List<Organization2> findByName(String name);
	
	public List<Organization2> findByRemark(String remark);
	
	public List<Organization2> findByNoparentOrganizationID(Long id);
	
	/********
	 * 
	 * @param offshootBank
	 * @param code
	 * @return
	 */
	public boolean isAvailableByCode(Organization2 offshootBank, String code);
	
	/********
	 * 	
	 * @param offshootBank
	 * @param name
	 * @return
	 */
	public boolean isAvailableByName(Organization2 offshootBank, String name);
	
	
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
	public List<Organization2> findByHeadquarters2(String id);
	/***
	 * 
	 * @param offshootBank
	 * @return
	 */
	public boolean checkHeadquarters(Organization2 offshootBank);
	/*****
	 * 
	 * @return
	 */
	public List<Organization2> findOrgByNoDelete();

}
