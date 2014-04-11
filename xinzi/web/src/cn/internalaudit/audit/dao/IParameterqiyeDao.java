package cn.internalaudit.audit.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Parameterqiye;

/**
 * 基本参数数据接口
 * <功能详细描述>
 * 
 * @author  make
 * @version  [Webset V100R002C01, 2013-12-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IParameterqiyeDao extends IBaseDao<Parameterqiye>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Parameterqiye> findParameterqiyeByName(String name);
    
    public List<Parameterqiye> findParameterqiyeByType(String type);
    
    public List<Parameterqiye> findParameterqiyeByNamechick(String name,String chicunk);
    
    public List<Parameterqiye> findParameterqiyeByName(String name,String type);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Parameterqiye> findParameterqiyeByNamechickgao(String type, String chichun);
    
    public List<Parameterqiye> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Parameterqiye> findByParms(Map map);
}
