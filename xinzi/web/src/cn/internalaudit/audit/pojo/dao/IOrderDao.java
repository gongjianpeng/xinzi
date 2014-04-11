package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Order;

public interface IOrderDao extends IBaseDao<Order>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Order> findCartById(final Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Order> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Order> findByParms(Map param);
    
    
    public void add(Order entity);
}
