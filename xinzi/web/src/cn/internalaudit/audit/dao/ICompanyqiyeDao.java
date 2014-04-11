package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.base.Companyqiye;


public interface ICompanyqiyeDao extends IBaseDao<Companyqiye> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Companyqiye> findCompanyqiyeByTpye(Integer type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Companyqiye> findCompanyqiyeByName(Integer type,
			String name);
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public Companyqiye findCompanyqiyeByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<Companyqiye> findCompanyqiyeByCode(String code,
			Integer type);
	public Companyqiye findCompanyqiyeByCode(String code, String type);
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<Companyqiye> findCompanyqiyeByid(Long id, Integer type);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<Companyqiye> findCompanyqiyeByName1(String name, int type);
	
	public List<Companyqiye> findAll();
	
}
