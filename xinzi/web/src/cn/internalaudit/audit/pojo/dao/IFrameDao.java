package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Frame;

public interface IFrameDao extends IBaseDao<Frame>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Frame> findFrameByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Frame> findAll();
    
    public List<Frame> findFrameByid(Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Frame> findByParms(Map map);
}
