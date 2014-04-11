package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.base.Company;


public interface ICompanyBo extends IBo<Company> {
	/*******
	 * 
	 * 
	 * @param loginName
	 *            
	 * @return 
	 */
	
	/********
	 * 
	 * 
	 * @param loginName
	 * @return
	 */
	
	
	/*******
	 *
	 * @param Company 
	 * @return 
	 */
	
	/*******
	 * 
	 * @param Company 
	 * @return
	 */
	public List<Company> findById(long id);
	/******
	 * 
	 * @param departmentName 
	 * @return 
	 */
	public List<Company> findByName(String Name);
	
	public void add(Company mode);

	public void update(Company mode);

	public void delete(Company mode);

	public List<Company> findByParms(Map map);
    public Company findCompanyByCode(String code, String type);
	public Company findOrgid(String orgid);
	
}
