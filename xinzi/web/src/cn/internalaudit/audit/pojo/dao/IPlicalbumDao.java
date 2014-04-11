package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.pojo.Plicalbum;

public interface IPlicalbumDao extends IBaseDao<Plicalbum>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Plicalbum> findPlicalbumByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Plicalbum> findAll();
    
    public List<Plicalbum> findPlicalbumByid(Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Plicalbum> findByParms(Map map);
    Plicalbum findDataplirById(Long id);
}
