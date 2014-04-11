package cn.internalaudit.audit.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Parameterjxs;

/**
 * 基本参数数据接口
 * <功能详细描述>
 * 
 * @author  make
 * @version  [Webset V100R002C01, 2013-12-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IParameterjxsDao extends IBaseDao<Parameterjxs>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Parameterjxs> findParameterjxsByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Parameterjxs> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Parameterjxs> findByParms(Map map);
}
