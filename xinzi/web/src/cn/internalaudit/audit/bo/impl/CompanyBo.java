package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.ICompanyBo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.dao.ICompanyDao;
import cn.internalaudit.audit.dao.IDepartmentDao;
import cn.internalaudit.audit.dao.IOrganizationDao;
import cn.internalaudit.audit.dao.IPersonDao;
import cn.internalaudit.audit.dao.IRolesDao;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

@Service("CompanyBo")
public class CompanyBo extends Bo<Company,ICompanyDao> implements ICompanyBo {
	@Autowired(required = true)
	private ICompanyDao companydao;
	

	@Override
	protected ICompanyDao getDao() {
		return companydao;
	}

	@Override
	public List<Company> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> findByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Company mode) {
		  companydao.save(mode);
		
	}

	@Override
	public void delete(Company mode) {
		// TODO Auto-generated method stub
		companydao.remove(mode);
	}

	@Override
	public List<Company> findByParms(Map map) {
		String ql = "select e from Company e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and e." + name + " = '" + map.get(name) + "' ";
			}
		}
		System.out.println("jingxiaoshwneg"+ql);
		return this.find(ql);
	}
	
	@Override
	public Company findCompanyByCode(String code, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Company mode) {
		companydao.merge(mode);
	}
	public List<Company> findAll(){
		return getDao().findAll();
	}

	@Override
	public Company findOrgid(String orgid) {
		// TODO Auto-generated method stub
		return companydao.findOrgid(orgid);
	}


}
