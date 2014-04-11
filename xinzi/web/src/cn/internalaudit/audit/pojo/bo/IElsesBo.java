package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Elses;

public interface IElsesBo extends IBo<Elses>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Elses> findElsesByName(String name);

    
    public void add(Elses mode);

    public void update(Elses mode);

    public void delete(Elses mode);
    
    public List<Elses> findByParms(Map map);
    public List<Elses> findAll();
    public List<Elses> findElsesByid(Long	 id);
    
  
}
