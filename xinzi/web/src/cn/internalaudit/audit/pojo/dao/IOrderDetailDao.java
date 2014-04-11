package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.OrderDetail;

public interface IOrderDetailDao extends IBaseDao<OrderDetail>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<OrderDetail> findCartById(final Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<OrderDetail> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<OrderDetail> findByParms(Map param);
    
    
    public void add(OrderDetail entity);
	List<OrderDetail> findByParmsandorg(Map map, String org);
	List<OrderDetail> findByParms(Map map, String orgid);
}
