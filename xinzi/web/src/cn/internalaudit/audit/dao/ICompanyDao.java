package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.base.Company;


public interface ICompanyDao extends IBaseDao<Company> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Company> findCompanyByTpye(Integer type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Company> findCompanyByName(Integer type,
			String name);
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public Company findCompanyByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<Company> findCompanyByCode(String code,
			Integer type);
	public Company findCompanyByCode(String code, String type);
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<Company> findCompanyByid(Long id, Integer type);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<Company> findCompanyByName1(String name, int type);
	
	public List<Company> findAll();

	public Company findOrgid(String orgid);

	Company findDatacompanyById(Long id);
	
}
