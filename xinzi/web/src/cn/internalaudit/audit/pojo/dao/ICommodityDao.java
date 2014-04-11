package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Commodity;

public interface ICommodityDao extends IBaseDao<Commodity>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Commodity> findCommodityById(long id);
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public Commodity findDataCommodityById(long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Commodity> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Commodity> findByParms(Map map);
    
    
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Commodity> findCommodityByTypeId(String id);
    
    public List<Commodity> findCommodityByproClass(String proclass);
	public List<Commodity> findProLimitAll();
	
	public List<Commodity> findProductListAll(Map params);
	
	public Integer getTotal(String params);
	List<Commodity> findByParmsandorg(Map map, String orgid);
}
