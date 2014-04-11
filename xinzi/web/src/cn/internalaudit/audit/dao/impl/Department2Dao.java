package cn.internalaudit.audit.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IDepartment2Dao;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Department2;
import cn.internalaudit.audit.utils.Utils;


@Repository
public class Department2Dao extends BaseDao<Department2> implements
		IDepartment2Dao {

	@Override
	public List<Department2> getFindByNameOrCode(String code, String name,
			String organizationId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select o from Department2 o where 1 = 1");
		if (code != null) {
			code = Utils.getSqlLikeString(code);
			hql.append(" and o.code like '" + code + "'");
		}
		if (name != null) {
			name = Utils.getSqlLikeString(name);
			hql.append(" and o.name like '" + name + "'");
		}
		if (!Utils.nullOrEmpty(organizationId)) {
			hql.append(" and o.organization.id=" + organizationId);
		}
		return this.getHibernateTemplate().find(hql.toString());

	}

	@Override
	public List<Department2> findByCode(String code, long offShootBankId) {
		String hql = "select d from Department2 d where d.code = ? and d.organization.id = ?";
		return this.getHibernateTemplate().find(hql, code, offShootBankId);
	}

	@Override
	public List<Department2> findByName(String name, long offShootBankId) {
		//如果公司id传为0,表示忽略公司，查所有公司中的部门
		if (offShootBankId == 0) {
			String hql = "select d from Department2 d where d.name = ?";
			return this.getHibernateTemplate().find(hql, name);
		} else {
			String hql = "select d from Department2 d where d.name = ? and d.organization.id = ?";
			return this.getHibernateTemplate().find(hql, name, offShootBankId);
		}
	}

	@Override
	public List<Department2> findByOrganizationId(long id) {
		List<Department2> alllist = new ArrayList<Department2>();
		System.out.println("--------------执行了  部门查询------------");
		StringBuffer hql = new StringBuffer();
		hql.append("select d from Department2 d where d.organization.id = ?");
		List<Department2> list = this.getJpaTemplate().find(hql.toString(), id);
		for (Department2 dept : list) {
			alllist.add(dept);
			getChildrenDept(dept, alllist);
		}
		// if (list == null) {
		// list = Collections.EMPTY_LIST;
		// }
		return alllist;
	}
	
	public List<Department2> findByNoparentOrganizationID(Long id) {
		String hql = "select n from Department2 n where  n.organization2 = "+id;
		return this.getHibernateTemplate().find(hql);
	}

	private void getChildrenDept(Department2 dept, List list) {
		if (dept.getDepartments().size() > 0) {
			list.addAll(dept.getDepartments());
			for (Department2 dept1 : dept.getDepartments()) {
				getChildrenDept(dept1, list);
			}
		}
	}

	@Override
	public List<Department2> findBykuanRemark(Map map)  {
	
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
	public List<Department2> findBykuanRemark(String remark) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Department2 findDataDepartment2ById(Long id) {
		String  sql="from Department2 d  where d.id = :id";//u.name=:myName"; 
		String paramName="id";
		List<Department2> lists=getHibernateTemplate().findByNamedParam(sql, paramName, id);
		Department2 objs=lists.get(0);
		//return DataAccessUtils.singleResult(lists);
		return objs;
	}
	
	
	 @Override
	    public List<Department2> findByParms(Map map)
	    {
	        String ql = "select b from Department2 b where (b.isDelete = false or b.isDelete is null)";
	        if (map.size() > 0) {
	            Set<String> names = map.keySet();
	            for (String name : names) {
	                ql += " and b."+name+"=" + map.get(name) + " ";
	            }
	        }
	        System.out.println("Department2 Dao 层输出"+ql);
	        return this.find(ql);
	    }
	    
}
