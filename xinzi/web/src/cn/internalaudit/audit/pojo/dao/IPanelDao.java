package cn.internalaudit.audit.pojo.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Panel;


/**
 * 
 * data dictionary
 * 
 * @author bd02
 * 
 */
public interface IPanelDao extends IBaseDao<Panel> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Panel> findpanelByTpye(Integer type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Panel> findpanelByName(Integer type,
			String name);
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public Panel findpanelByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<Panel> findPanelByCode(String code,
			Integer type);
	public Panel findPanelByCode(String code, String type);
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<Panel> findPanelByid(Long id, Integer type);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<Panel> findPanelByName1(String name, int type);
	

}
