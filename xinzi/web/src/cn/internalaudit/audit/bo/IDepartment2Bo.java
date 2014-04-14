package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Department2;

/**
 * 
 * @author
 * 
 */
public interface IDepartment2Bo extends IBo<Department2> {

	public List<Department2> findByOrganizationId(long id);

	public List<Department2> getFindByNameOrCode(String code, String name,
			String organizationId);

	public boolean isAvailableByCode(Department2 department, String code,
			long offShootBankId);

	public boolean isAvailableByName(Department2 department, String name,
			long offShootBankId);

	public List<Department2> findByCode(String code, long offShootBankId);

	public List<Department2> findByName(String name, long offBank);

	public List<Department2> findByNoparentOrganizationID(long did3);

	public List<Department2> findBykuanRemark(String remark);

	public List<Department2> findBykuanRemark(Map map);

	public void add(Department2 mode);

	public Department2 update(Department2 mode);

	public void delete(Department2 mode);

	public Department2 findDataDepartment2ById(Long id);
	
	public List<Department2> findByParms(Map map);

	List<Department2> findByNoparentOrganizationIDBychs(long did3,
			String inputname3);

}
