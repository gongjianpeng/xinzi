package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Cart;

public interface ICartDao extends IBaseDao<Cart>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Cart> findCartById(final Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Cart> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Cart> findByParms(Map param);
    
}
