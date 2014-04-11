package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IDepartment2Bo;
import cn.internalaudit.audit.dao.IDepartment2Dao;
import cn.internalaudit.audit.entitys.Department2;


@Service("Department2Bo")
public class Department2Bo extends Bo<Department2, IDepartment2Dao> implements IDepartment2Bo {
	@Autowired(required = true)
	private IDepartment2Dao department2Dao;

	@Override
	protected IDepartment2Dao getDao(){
		return department2Dao;
	}
	 
	
	public List<Department2> getFindByNameOrCode(String code,String name,String organizationId){
		return department2Dao.getFindByNameOrCode(code, name,organizationId);
	}
	@Override
	public boolean isAvailableByCode(Department2 department, String code,long offShootBankId) {
		List<Department2> departments = findByCode(code,offShootBankId);
	if (department.getId() == null) {
		if (departments == null || departments.size() < 1) {
			return true;
		} else {
			return false;
		}
	} else {
		for (Department2 p : departments) {
			if (p.getId().longValue() != department.getId().longValue()) {					
				return false;
			}
		}
		return true;
	}}
	@Override
	public boolean isAvailableByName(Department2 department, String name, long offShootBankId) {
		List<Department2> departments = findByName(name,offShootBankId);
		if (department.getId() == null) {
			if (departments == null || departments.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Department2 p : departments) {
				if (p.getId().longValue() != department.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public List<Department2> findByCode(String code,long offShootBankId) {
		return department2Dao.findByCode(code,offShootBankId);
		
	}
	@Override
	public List<Department2> findByName(String name,long offShootBankId) {
		return department2Dao.findByName(name,offShootBankId);
	}
	@Override
	public List<Department2> findByOrganizationId(long id) {
		// TODO Auto-generated method stub
		return department2Dao.findByOrganizationId(id);
	}
	
	
	public List<Department2> findByNoparentOrganizationID(Long id){
		
		return department2Dao.findByOrganizationId(id);
	}


	@Override
	public List<Department2> findByNoparentOrganizationID(long did3) {
		// TODO Auto-generated method stub
		return department2Dao.findByOrganizationId(did3);
	}


	@Override
	public List<Department2> findBykuanRemark(String remark) {
		// TODO Auto-generated method stub
		return department2Dao.findBykuanRemark(remark);
	}
	
	public List<Department2> findBykuanRemark(Map map){
		String ql = "select e from Department2 e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like '" + map.get(name) + "' ";
			}
		}
		return this.find(ql);
	}


	@Override
	public void add(Department2 mode) {
		// TODO Auto-generated method stub
		department2Dao.save(mode);
	}


	@Override
	public void delete(Department2 mode) {
		// TODO Auto-generated method stub
		department2Dao.remove(mode);
	}


	@Override
	public Department2 update(Department2 mode) {
		// TODO Auto-generated method stub
		return  department2Dao.merge(mode);
	}
		
	public Department2 findDataDepartment2ById(Long id){
		
		return department2Dao.findDataDepartment2ById(id);
	}


	@Override
	public List<Department2> findByParms(Map map) {
		// TODO Auto-generated method stub
		return department2Dao.findByParms(map);
	}
	
}
