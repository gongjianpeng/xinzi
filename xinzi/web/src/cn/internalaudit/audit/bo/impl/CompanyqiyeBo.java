package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.ICompanyqiyeBo;
import cn.internalaudit.audit.dao.ICompanyqiyeDao;
import cn.internalaudit.audit.entitys.Parameterjxs;
import cn.internalaudit.audit.entitys.base.Companyqiye;

@Service("CompanyqiyeBo")
public class CompanyqiyeBo extends Bo<Companyqiye,ICompanyqiyeDao> implements ICompanyqiyeBo {
	@Autowired(required = true)
	private ICompanyqiyeDao companyqiyedao;
	

	@Override
	protected ICompanyqiyeDao getDao() {
		return companyqiyedao;
	}

	@Override
	public List<Companyqiye> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Companyqiye> findByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Companyqiye mode) {
		  companyqiyedao.save(mode);
		
	}

	@Override
	public void delete(Companyqiye mode) {
		// TODO Auto-generated method stub
		companyqiyedao.remove(mode);
	}

	@Override
	public List<Companyqiye> findByParms(Map map) {
		String ql = "select e from Companyqiye e where (e.isDelete = false or e.isDelete is null)";
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
	public List<Companyqiye> findByParmsandpinpai(Map map,String pinpai) {
		String ql = "select e from Companyqiye e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and e." + name + " = '" + map.get(name) + "' ";
			}
		}
		ql+="and e.pinpai like "+"'%"+pinpai+"%'";
		System.out.println("jingxiao pinpai"+ql);
		return this.find(ql);
	}
	
	
//	 @Override
//	    public List<Parameterjxs> findByParms(Map map)
//	    {
//	        String ql = "select q from Parameterjxs q where (q.isDelete = false or q.isDelete is null)";
//	        if (map.size() > 0) {
//	            Set<String> names = map.keySet();
//	            for (String name : names) {
//	                ql += " and " + name + " like '" + map.get(name) + "' ";
//	            }
//	        }
//	        return this.find(ql);
//	    }
	
	@Override
	public Companyqiye findCompanyqiyeByCode(String code, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Companyqiye mode) {
		companyqiyedao.merge(mode);
	}
	public List<Companyqiye> findAll(){
		return getDao().findAll();
	}


}
