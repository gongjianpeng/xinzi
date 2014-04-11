package cn.internalaudit.audit.bo;

import java.util.List;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Person;


public interface IPersonBo extends IBo<Person> {
    /*******
     * 
     * 
     * @param loginName
     *            
     * @return 
     */
    public List<Person> findByLoginName(String loginName);
    
    /********
     * 
     * 
     * @param loginName
     * @return
     */
    public boolean isAvailableByLoginName(Person person, String loginName);
    
    
    /*******
     *
     * @param Person 
     * @return 
     */
    public List<Person> findByLoginNameAndName(Person person);
    
    /*******
     * 
     * @param Person 
     * @return
     */
    public List<Person> findByDepartmentId(long id);
    /******
     * 
     * @param departmentName 
     * @return 
     */
    public List<Person> findByDepartmentName(String departmentName);
    /*****
     * 
     * @param roleName
     * @return
     */
    public List<Person> findByRoleName(String roleName);
    /*****
     *
     * @param roleName
     * @return
     */
    public List<Person> findByRoleName(Long offshootBankId ,String roleName);
    
    public  void saveperson(Person vperson,String dept,String org,String enable) throws Exception;
    
    public void setRoles(Long personId,String rolesStr);
    
    public void removeRoles(Long personId,String rolesStr);
    public List<Person> validname(String name);	
	public Person activity(long id);
	 public Person update(Person mode);
}
