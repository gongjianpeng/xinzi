package cn.internalaudit.audit.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IPersonDao;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.utils.Utils;

@Repository
public class PersonDao extends BaseDao<Person> implements IPersonDao {

	public List<Person> findByLoginName(String loginName) {
		String sql = "select p from Person p where p.loginName = ?";
		List<Person> persons =  getHibernateTemplate().find(sql, loginName);
		return persons;
	}

	public List<Person> findByLoginNameAndName(Person person) {
		StringBuffer hql = new StringBuffer();
		hql.append("select p from Person p where 1=1");
		
		String loginName ="";
		String name = "";
		if(!Utils.nullOrEmpty(person.getLoginName())){
			
			loginName = Utils.getSqlLikeString(person.getLoginName());
			
			hql.append(" and  p.loginName like  '"+ loginName+"'");
		}
		
		if(!Utils.nullOrEmpty(person.getName())){
			name = Utils.getSqlLikeString(person.getName());
			hql.append(" and p.name like '"+ name+"'");
		}
		
		
		return  super.getJpaTemplate().find(hql.toString());
	}

	public List<Person> findByDepartmentId(long id) {
		
		   StringBuffer hql= new StringBuffer();
		   hql.append("select d from Person d where d.department.id = ?");
		   List<Person> list =  this.getJpaTemplate().find(hql.toString(),id);
		   if(list == null){
			   list = Collections.EMPTY_LIST;
		   }
		   return list;
	}

	@Override
	public List<Person> findByDepartmentName(String departmentName) {
		StringBuffer hql= new StringBuffer();
		hql.append("select d from Person d where d.department.name = ?");
		return getJpaTemplate().find(hql.toString(),departmentName);
	}
	/*****
	 * 查询某角色的人员
	 * @param roleName 角色名
	 * @return 人员列表
	 */
	@Override
	public List<Person> findByRoleName(String roleName) {
		StringBuffer hql= new StringBuffer();
		hql.append("select p from Person p join p.roless r where r.name = ?");
		return getJpaTemplate().find(hql.toString(),roleName);
	}
	/*****
	 * 查询指定分行的指定角色的人员
	 * @param roleName
	 * @return
	 */
	@Override
	public List<Person> findByRoleName(Long offshootBankId, String roleName) {
		StringBuffer hql= new StringBuffer();
		hql.append("select p from Person p join p.roless r where r.name = ? and p.department.organization.id = ?");
		return getJpaTemplate().find(hql.toString(),roleName,offshootBankId);
	}


    @Override
    public List<Person> validname(String name) {
        StringBuffer hql= new StringBuffer();
        hql.append("select p from Person p  where p.loginName = ?");
        return getJpaTemplate().find(hql.toString(),name);
    }

    @Override
    public Person findById(long id)
    {
        String sql = "select p from Person p  where p.id = ?";             
        List<Person> PersonList = getHibernateTemplate().find(
                sql, id);
        return DataAccessUtils.singleResult(PersonList);
    }
}
