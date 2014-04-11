package cn.internalaudit.audit.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IDepartmentDao;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.utils.Utils;


@Repository
public class DepartmentDao extends BaseDao<Department> implements
		IDepartmentDao {

	@Override
	public List<Department> getFindByNameOrCode(String code, String name,
			String organizationId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select o from Department o where 1 = 1");
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
	public List<Department> findByCode(String code, long offShootBankId) {
		String hql = "select d from Department d where d.code = ? and d.organization.id = ?";
		return this.getHibernateTemplate().find(hql, code, offShootBankId);
	}

	@Override
	public List<Department> findByName(String name, long offShootBankId) {
		//如果公司id传为0,表示忽略公司，查所有公司中的部门
		if (offShootBankId == 0) {
			String hql = "select d from Department d where d.name = ?";
			return this.getHibernateTemplate().find(hql, name);
		} else {
			String hql = "select d from Department d where d.name = ? and d.organization.id = ?";
			return this.getHibernateTemplate().find(hql, name, offShootBankId);
		}
	}

	@Override
	public List<Department> findByOrganizationId(long id) {
		List<Department> alllist = new ArrayList<Department>();
		StringBuffer hql = new StringBuffer();
		hql.append("select d from Department d where d.organization.id = ?");
		List<Department> list = this.getJpaTemplate().find(hql.toString(), id);
		for (Department dept : list) {
			alllist.add(dept);
			getChildrenDept(dept, alllist);
		}
		// if (list == null) {
		// list = Collections.EMPTY_LIST;
		// }
		return alllist;
	}

	private void getChildrenDept(Department dept, List list) {
		if (dept.getDepartments().size() > 0) {
			list.addAll(dept.getDepartments());
			for (Department dept1 : dept.getDepartments()) {
				getChildrenDept(dept1, list);
			}
		}
	}
}
