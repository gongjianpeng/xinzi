package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Doorstyle;

public interface IDoorstyleDao extends IBaseDao<Doorstyle>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Doorstyle> findDoorstyleByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Doorstyle> findAll();
    
    public List<Doorstyle> findDoorstyleByid(Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Doorstyle> findByParms(Map map);
}
