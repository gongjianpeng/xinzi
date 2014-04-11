package cn.internalaudit.audit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IOrganization2Dao;
import cn.internalaudit.audit.dao.IOrganizationDao;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Organization2;
import cn.internalaudit.audit.utils.Utils;

@Repository
public class Organization2Dao extends BaseDao<Organization2> implements
		IOrganization2Dao {

	@Override
	public List<Organization2> findByCode(String code) {
		String hql = "select c from Organization2 c where c.code = ?";
		return this.getHibernateTemplate().find(hql, code);
	}

	@Override
	public List<Organization2> findByName(String name) {
		String hql = "select n from Organization2 n where n.name = ?";
		return this.getHibernateTemplate().find(hql, name);
	}
	
	@Override
	public List<Organization2> findByRemark(String remark) {
		System.out.println("=====查询了 remark");
		String hql = "select n from Organization2 n where n.remark = ?";
		return this.getHibernateTemplate().find(hql, remark);
	}

	public List<Organization2> getFindByNameOrCode(String parentOrganization,String code, String name) {
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
	public List<Organization2> findByNotHeadquarters() {
		String hql = "select n from Organization2 n where n.isHeadquarters = false";
		return this.getHibernateTemplate().find(hql);
	}
	
	public List<Organization2> findByNoparentOrganizationID(Long id) {
		String hql = "select n from Organization2 n where n.isHeadquarters = false and n.parentOrganization = "+id;
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Organization2> findByHeadquarters() {
		String hql = "select n from Organization2 n where n.isHeadquarters = true and (n.isDelete = 0 or n.isDelete is null)";
//		Map map = new HashMap();
//		map.put("isHeadquarters", true);
//		return this.findByParms(map);
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	
	public List<Organization2> findByHeadquarters2(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append("select n from Organization2 n where n.isHeadquarters = true and (n.isDelete = 0 or n.isDelete is null)  and n" +
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
	

	public boolean checkHeadquarters(Organization2 offshootBank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Organization2> findOrgByNoDelete() {
		String hql = "select o from Organization2 o where (o.isDelete = false or o.isDelete is null)";
		return this.getHibernateTemplate().find(hql);
	}
}
