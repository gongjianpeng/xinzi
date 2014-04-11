package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Brandjxs;

public interface IBrandjxsBo extends IBo<Brandjxs>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Brandjxs> findBrandjxsByName(String name);

    
    public void add(Brandjxs mode);

    public void update(Brandjxs mode);

    public void delete(Brandjxs mode);
    
    public List<Brandjxs> findByParms(Map map);
    public List<Brandjxs> findAll();
    public List<Brandjxs> findBrandByid(Long	 id);
    
  
}
