package cn.internalaudit.audit.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.entitys.Organization2;


public interface IDepartment2Dao extends IBaseDao<Department2> {

	public List<Department2> findByOrganizationId(long id);
	
	public List<Department2> getFindByNameOrCode(String code,String name,String organizationId);
	
	public List<Department2> findByCode(String code,long offShootBankId);
	
	public List<Department2> findByName(String name,long offBank);
	
	public List<Department2> findByNoparentOrganizationID(Long id);

	public List<Department2> findBykuanRemark(String remark);
	public List<Department2> findBykuanRemark(Map map);
	public Department2 findDataDepartment2ById(Long id);
	public List<Department2> findByParms(Map map);
}
