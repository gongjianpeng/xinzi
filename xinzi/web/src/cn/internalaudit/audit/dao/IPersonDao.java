package cn.internalaudit.audit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Person;

public interface IPersonDao extends IBaseDao<Person> {
/*******
 * 
 * @param loginName 
 * @return
 */
	public List<Person> findByLoginName(String loginName);
	/*******
	 *
	 * @param Person 
	 * @return 
	 */
	public List<Person> findByLoginNameAndName(Person person);
	
	/*******
	 * 
	 * @param Person 
	 * @return 
	 */
	public List<Person> findByDepartmentId(long id);
	/******
	 * 
	 * @param departmentName 
	 * @return 
	 */
	public List<Person> findByDepartmentName(String departmentName);
	
	/*****
	 * 
	 * @param roleName
	 * @return
	 */
	public List<Person> findByRoleName(String roleName);
	
	/*****
	 *
	 * @param roleName
	 * @return
	 */
	public List<Person> findByRoleName(Long offshootBankId ,String roleName);
	
	/**
	 * 
	 */
	public List<Person> validname(String name);
	/**
	 * 
	 */
	public Person findById(long id);
	
}
