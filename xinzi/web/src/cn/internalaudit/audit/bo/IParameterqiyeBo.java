package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Parameterqiye;

public interface IParameterqiyeBo extends IBo<Parameterqiye>
{

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Parameterqiye> findParameterqiyeByName(String name);
    public List<Parameterqiye> findParameterqiyeByType(String type);
    
    public List<Parameterqiye> findParameterqiyeByTypeAchik(String type,String chicunk);
    public List<Parameterqiye> findParameterqiyeByTypeAchigao(String type,String chicun);
    
    public void add(Parameterqiye mode);

    public void update(Parameterqiye mode);

    public void delete(Parameterqiye mode);
    
    public List<Parameterqiye> findByParms(Map map);
    public List<Parameterqiye> findAll();
    
    public List<Parameterqiye> findBynameAndType(String name,String type);
}
