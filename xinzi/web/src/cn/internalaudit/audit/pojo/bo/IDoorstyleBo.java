package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Doorstyle;

public interface IDoorstyleBo extends IBo<Doorstyle>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Doorstyle> findDoorstyleByName(String name);

    
    public void add(Doorstyle mode);

    public void update(Doorstyle mode);

    public void delete(Doorstyle mode);
    
    public List<Doorstyle> findByParms(Map map);
    public List<Doorstyle> findAll();
    public List<Doorstyle> findDoorstyleByid(Long	 id);
    
  
}
