package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Elses;


public interface IElsesDao extends IBaseDao<Elses>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Elses> findElsesByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Elses> findAll();
    
    public List<Elses> findElsesByid(Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Elses> findByParms(Map map);
}
