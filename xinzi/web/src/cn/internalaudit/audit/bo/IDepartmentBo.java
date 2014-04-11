package cn.internalaudit.audit.bo;

import java.util.List;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Department;


/**
 * 
 * @author 
 *
 */
public interface IDepartmentBo extends IBo<Department>{
	
	public List<Department> findByOrganizationId(long id);
	
	public List<Department> getFindByNameOrCode(String code,String name,String organizationId);
	
	public boolean isAvailableByCode(Department department, String code,long offShootBankId);
	
	public boolean isAvailableByName(Department department, String name,long offShootBankId);
	
	public List<Department> findByCode(String code,long offShootBankId);
	
	public List<Department> findByName(String name,long offBank);
	

}
