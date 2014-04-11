package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.base.Dealer;


public interface IDealerDao extends IBaseDao<Dealer> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Dealer> findDealerByTpye(Integer type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Dealer> findDealerByName(Integer type,
			String name);
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public Dealer findDealerByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<Dealer> findDealerByCode(String code,
			Integer type);
	public Dealer findDealerByCode(String code, String type);
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<Dealer> findDealerByid(Long id, Integer type);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<Dealer> findDealerByName1(String name, int type);
	
	public List<Dealer> findAll();
	
	
	public Dealer findDataDealerById(Long id);
	
	public Dealer findOrgid(String id );
	
}
