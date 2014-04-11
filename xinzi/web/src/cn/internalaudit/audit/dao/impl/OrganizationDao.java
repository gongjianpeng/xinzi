package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IOrganizationDao;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.utils.Utils;

@Repository
public class OrganizationDao extends BaseDao<Organization> implements
		IOrganizationDao {

	@Override
	public List<Organization> findByCode(String code) {
		String hql = "select c from Organization c where c.code = ?";
		return this.getHibernateTemplate().find(hql, code);
	}

	@Override
	public List<Organization> findByName(String name) {
		String hql = "select n from Organization n where n.name = ?";
		return this.getHibernateTemplate().find(hql, name);
	}

	public List<Organization> getFindByNameOrCode(String parentOrganization,String code, String name) {
		StringBuffer hql = new StringBuffer();
		hql.append("select o from Organization o where isDelete = 0");
		if (!Utils.nullOrEmpty(code)) {
			code = Utils.getSqlLikeString(code);
			hql.append(" and o.code like '"+ code+"'");
		}
	   if (!Utils.nullOrEmpty(name)) {
		   name = Utils.getSqlLikeString(name);
			hql.append(" and o.name like '"+ name+"'");
		}
	   if (!Utils.nullOrEmpty(parentOrganization)) {
			hql.append(" and o.parentOrganization = "+ parentOrganization);
		}
		return this.getHibernateTemplate().find(hql.toString());
		
	}

	@Override
	public List<Organization> findByNotHeadquarters() {
		String hql = "select n from Organization n where n.isHeadquarters = false";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Organization> findByHeadquarters() {
		String hql = "select n from Organization n where n.isHeadquarters = true and (n.isDelete = 0 or n.isDelete is null)";
//		Map map = new HashMap();
//		map.put("isHeadquarters", true);
//		return this.findByParms(map);
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	
	public List<Organization> findByHeadquarters2(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append("select n from Organization n where n.isHeadquarters = true and (n.isDelete = 0 or n.isDelete is null)  and n" +
				".id="+id);
	//	hql = "";
//		Map map = new HashMap();
//		map.put("isHeadquarters", true);
//		return this.findByParms(map);
		if (!Utils.nullOrEmpty(id)) {
			//id = Utils.getSqlLikeString(id);
			//hql.append("+id+");
		}
		System.out.println("Dao层输出的hql为"+hql);
		return this.getHibernateTemplate().find(hql.toString());
	}
	

	public boolean checkHeadquarters(Organization offshootBank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Organization> findOrgByNoDelete() {
		String hql = "select o from Organization o where (o.isDelete = false or o.isDelete is null)";
		return this.getHibernateTemplate().find(hql);
	}
}
