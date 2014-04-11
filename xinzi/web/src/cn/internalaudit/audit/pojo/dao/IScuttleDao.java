package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Scuttle;

public interface IScuttleDao extends IBaseDao<Scuttle>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Scuttle> findScuttleByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Scuttle> findAll();
    
    public List<Scuttle> findScuttleByid(Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Scuttle> findByParms(Map map);
}
