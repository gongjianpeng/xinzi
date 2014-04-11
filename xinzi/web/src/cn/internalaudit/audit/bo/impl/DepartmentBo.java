package cn.internalaudit.audit.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IDepartmentBo;
import cn.internalaudit.audit.dao.IDepartmentDao;
import cn.internalaudit.audit.entitys.Department;


@Service("DepartmentBo")
public class DepartmentBo extends Bo<Department, IDepartmentDao> implements IDepartmentBo {
	@Autowired(required = true)
	private IDepartmentDao departmentDao;

	@Override
	protected IDepartmentDao getDao(){
		return departmentDao;
	}
	 
	
	public List<Department> getFindByNameOrCode(String code,String name,String organizationId){
		return departmentDao.getFindByNameOrCode(code, name,organizationId);
	}
	@Override
	public boolean isAvailableByCode(Department department, String code,long offShootBankId) {
		List<Department> departments = findByCode(code,offShootBankId);
	if (department.getId() == null) {
		if (departments == null || departments.size() < 1) {
			return true;
		} else {
			return false;
		}
	} else {
		for (Department p : departments) {
			if (p.getId().longValue() != department.getId().longValue()) {					
				return false;
			}
		}
		return true;
	}}
	@Override
	public boolean isAvailableByName(Department department, String name, long offShootBankId) {
		List<Department> departments = findByName(name,offShootBankId);
		if (department.getId() == null) {
			if (departments == null || departments.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Department p : departments) {
				if (p.getId().longValue() != department.getId().longValue()) {					
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public List<Department> findByCode(String code,long offShootBankId) {
		return departmentDao.findByCode(code,offShootBankId);
	}
	@Override
	public List<Department> findByName(String name,long offShootBankId) {
		return departmentDao.findByName(name,offShootBankId);
	}
	@Override
	public List<Department> findByOrganizationId(long id) {
		// TODO Auto-generated method stub
		return departmentDao.findByOrganizationId(id);
	}
}
