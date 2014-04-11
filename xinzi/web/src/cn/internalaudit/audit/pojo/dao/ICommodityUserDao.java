package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.CommodityUser;

public interface ICommodityUserDao extends IBaseDao<CommodityUser>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<CommodityUser> findCommodityUserById(long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<CommodityUser> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<CommodityUser> findByParms(Map map);
    
    
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<CommodityUser> findCommodityUserByTypeId(String id);
    
    public List<CommodityUser> findCommodityUserByproClass(String proclass);
	public List<CommodityUser> findProLimitAll();
	
	public List<CommodityUser> findProductListAll(Map params);
	
	public Integer getTotal(String params);
	
	public List<CommodityUser>  getTotalprice(long id);

	List<CommodityUser> findCommodityUserByIdstatus(long id, String status,
			String brandName);
}
