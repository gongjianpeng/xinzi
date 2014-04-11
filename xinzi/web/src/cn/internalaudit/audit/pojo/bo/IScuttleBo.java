package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Scuttle;

public interface IScuttleBo extends IBo<Scuttle>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Scuttle> findScuttleByName(String name);

    
    public void add(Scuttle mode);

    public void update(Scuttle mode);

    public void delete(Scuttle mode);
    
    public List<Scuttle> findByParms(Map map);
    public List<Scuttle> findAll();
    public List<Scuttle> findScuttleByid(Long	 id);
    
  
}
