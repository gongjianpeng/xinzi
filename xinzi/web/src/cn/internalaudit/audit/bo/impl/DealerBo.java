package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.ICompanyBo;
import cn.internalaudit.audit.bo.IDealerBo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.dao.ICompanyDao;
import cn.internalaudit.audit.dao.IDealerDao;
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
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

@Service("DealerBo")
public class DealerBo extends Bo<Dealer,IDealerDao> implements IDealerBo {
	@Autowired(required = true)
	private IDealerDao dealerdao;
	

	@Override
	protected IDealerDao getDao() {
		return dealerdao;
	}

	@Override
	public List<Dealer> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dealer> findByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Dealer mode) {
		dealerdao.save(mode);
		
	}

	@Override
	public void delete(Dealer mode) {
		// TODO Auto-generated method stub
		dealerdao.remove(mode);
	}

	@Override
	public List<Dealer> findByParms(Map map) {
		String ql = "select e from Dealer e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like '" + map.get(name) + "' ";
			}
		}
		System.out.println("DealerBo"+ql);
		return this.find(ql);
	}
	
	@Override
	public List<Dealer> findByParms2deng(Map map) {
		String ql = "select e from Dealer e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and e." + name + " = '" + map.get(name) + "' ";
			}
		}
		System.out.println("dealg"+ql);
		return this.find(ql);
	}
	
	@Override
	public Dealer findDealerByCode(String code, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Dealer mode) {
		dealerdao.merge(mode);
	}
	public List<Dealer> findAll(){
		return getDao().findAll();
	}

	@Override
	public Dealer findOrgid(String id) {
		// TODO Auto-generated method stub
		return dealerdao.findOrgid(id);
	}


}
