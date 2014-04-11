package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.base.Companyqiye;


public interface ICompanyqiyeBo extends IBo<Companyqiye> {
	
	
	/*******
	 * 
	 * @param Companyqiye 
	 * @return
	 */
	public List<Companyqiye> findById(long id);
	/******
	 * 
	 * @param departmentName 
	 * @return 
	 */
	public List<Companyqiye> findByName(String Name);
	
	public void add(Companyqiye mode);

	public void update(Companyqiye mode);

	public void delete(Companyqiye mode);

	public List<Companyqiye> findByParms(Map map);
    public Companyqiye findCompanyqiyeByCode(String code, String type);
	List<Companyqiye> findByParmsandpinpai(Map map, String pinpai);
	
}
