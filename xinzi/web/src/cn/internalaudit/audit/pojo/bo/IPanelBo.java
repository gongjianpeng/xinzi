package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.Panel;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
public interface IPanelBo extends IBo<Panel> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<Panel> findPanelByTpye(Integer type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Panel> findPanelByName(Integer type,
			String name);
	
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public Panel findPanelByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<Panel> findPanelByCode(String code,
			Integer type);

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

	public boolean isAvailableByCode(Panel Panel,
			String code, int type);

	public boolean isAvailableByName(Panel Panel,
			String name, int type);

	public void add(Panel mode);

	public void update(Panel mode);

	public void delete(Panel mode);

	public List<Panel> findByParms(Map map);
    public Panel findPanelByCode(String code, String type);
	
}
