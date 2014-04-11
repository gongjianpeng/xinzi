package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Parameterjxs;

public interface IParameterjxsBo extends IBo<Parameterjxs>
{

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Parameterjxs> findParameterjxsByName(String name);

    
    public void add(Parameterjxs mode);

    public void update(Parameterjxs mode);

    public void delete(Parameterjxs mode);
    
    public List<Parameterjxs> findByParms(Map map);
    public List<Parameterjxs> findAll();
}
