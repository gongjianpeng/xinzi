package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.Person;
import cn.internalaudit.audit.entitys.base.Company;
import cn.internalaudit.audit.entitys.base.Dealer;


public interface IDealerBo extends IBo<Dealer> {
	
	
	
	public List<Dealer> findById(long id);
	/******
	 * 
	 * @param departmentName 
	 * @return 
	 */
	public List<Dealer> findByName(String Name);
	
	public void add(Dealer mode);

	public void update(Dealer mode);

	public void delete(Dealer mode);

	public List<Dealer> findByParms(Map map);
    public Dealer findDealerByCode(String code, String type);
    public Dealer findOrgid(String id );
	List<Dealer> findByParms2deng(Map map);
	
}
