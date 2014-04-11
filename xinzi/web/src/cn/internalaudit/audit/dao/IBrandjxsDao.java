package cn.internalaudit.audit.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Brandjxs;

public interface IBrandjxsDao extends IBaseDao<Brandjxs>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Brandjxs> findBrandjxsByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Brandjxs> findAll();
    
    public List<Brandjxs> findbrandByid(Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Brandjxs> findByParms(Map map);
}
