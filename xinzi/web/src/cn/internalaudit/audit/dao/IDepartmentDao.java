package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Department;


public interface IDepartmentDao extends IBaseDao<Department> {

	public List<Department> findByOrganizationId(long id);
	
	public List<Department> getFindByNameOrCode(String code,String name,String organizationId);
	
	public List<Department> findByCode(String code,long offShootBankId);
	
	public List<Department> findByName(String name,long offBank);

}
