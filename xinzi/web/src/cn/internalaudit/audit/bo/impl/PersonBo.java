package cn.internalaudit.audit.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IPersonBo;
import cn.internalaudit.audit.dao.IDepartmentDao;
import cn.internalaudit.audit.dao.IOrganizationDao;
import cn.internalaudit.audit.dao.IPersonDao;
import cn.internalaudit.audit.dao.IRolesDao;
import cn.internalaudit.audit.entitys.Department;
import cn.internalaudit.audit.entitys.Organization;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.Roles;
import cn.internalaudit.audit.utils.DataModel;
import cn.internalaudit.audit.utils.Utils;

@Service("PersonBo")

public class PersonBo extends Bo<Person,IPersonDao> implements IPersonBo {
    
  //  private static PersonBo instance = new PersonBo();
       
    @Autowired(required = true)
    private IPersonDao personDao;
    
    @Autowired(required = true)
    private IDepartmentDao departmentDao;
    
    @Autowired(required = true)
    private IOrganizationDao organizationDao;
    
    @Autowired(required = true)
    private IRolesDao rolesDao ;
    
    @Override
    protected IPersonDao getDao() {
        return personDao;
    }

    public List<Person> findByLoginName(String loginName) {
        return getDao().findByLoginName(loginName);
    }

    public boolean isAvailableByLoginName(Person person, String loginName) {
        List<Person> persons = findByLoginName(loginName);
        if (person.getId() == null) {
            if (persons == null || persons.size() < 1) {
                return true;
            } else {
                return false;
            }
        } else {
            for (Person p : persons) {
                if (p.getId().longValue() != person.getId().longValue()) {                  
                    return false;
                }
            }
            return true;
        }
    }
    
    /*******
     * 根据登陆名 以及用户名 模糊查询用户信息
     * @param Person 人员对象
     * @return 人员对象，如果登录名不存在，返回Null
     */
    public List<Person> findByLoginNameAndName(Person person){
        return personDao.findByLoginNameAndName(person);
    }

    public List<Person> findByDepartmentId(long id) {
        // TODO Auto-generated method stub
        return personDao.findByDepartmentId(id);
    }
    /****
     * 根据当前的部门名称查新当前部门的人员
     */
    @Override
    public List<Person> findByDepartmentName(String departmentName) {
        return getDao().findByDepartmentName(departmentName);
    }
    /*****
     * 查询某角色的人员
     * @param roleName 角色名
     * @return 人员列表
     */
    @Override
    public List<Person> findByRoleName(String roleName) {
        return getDao().findByRoleName(roleName);
    }

    @Override
    public List<Person> findByRoleName(Long offshootBankId, String roleName) {
        return getDao().findByRoleName(offshootBankId, roleName);
    }
    
    public  void saveperson(Person vperson,String dept,String org,String enable) throws Exception{
        
        Person person = new Person();
        if (vperson.getId() != null) {
            person = personDao.find(vperson.getId()); 
        }
        String password=person.getPassword();
        DataModel datamodel = new DataModel<Person>();
        datamodel.copy(vperson, person);
        person.setPassword(password);
        if (!Utils.nullOrEmpty(dept)) {   
            Department department = departmentDao.find(Long.valueOf(dept));
            person.setDepartment(department);
            person.setDepartmentId(dept);
            person.setDepartmentName(department.getName());
        }
        if (!Utils.nullOrEmpty(org)) {
            Organization organization = this.organizationDao.find(Long
                    .valueOf(org));
            person.setOrganizationId(org);
            person.setOrganizationName(organization.getName());
        }
        if (!Utils.nullOrEmpty(enable)) {
            person.setEnable(true);
        } else {
            person.setEnable(false);
        }
        if (vperson.getPassword().length() != 0) {
            person.setPassword(Utils.encodeSHA(vperson.getPassword(), vperson
                    .getLoginName()));
        }
        this.save(person);
    }
    
    public void setRoles(Long personId,String rolesStr){
        Person person = this.find(personId);
        String[] rolesIds = rolesStr.split(",");
        for (String roleId : rolesIds) {
            if (Utils.nullOrEmpty(roleId))
                continue;
            Roles role = this.rolesDao.find(Long.valueOf(roleId));
            if (!person.getRoless().contains(role)) {
                person.getRoless().add(role);
            }
        }
        this.save(person);
    }
    
    public void removeRoles(Long personId,String rolesStr){
        Person person = this.find(personId);
        String[] rolesIds = rolesStr.split(",");
        for (String roleId : rolesIds) {
            Roles role = rolesDao.find(Long.valueOf(roleId));
            if (person.getRoless().contains(role)) {
                person.getRoless().remove(role);
            }
        }
        this.save(person);
    }

    @Override
    public List<Person> validname(String name)
    {
        return personDao.validname(name);
    }
    @Override
    public Person activity(long id)
    {
        return personDao.findById(id);
    }

    @Override
    public Person update(Person mode)
    {
        
        return personDao.merge(mode);
    }
}
