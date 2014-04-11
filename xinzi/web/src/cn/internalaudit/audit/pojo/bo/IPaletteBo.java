package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Palette;

public interface IPaletteBo extends IBo<Palette>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Palette> findPaletteByName(String name);

    
    public void add(Palette mode);

    public void update(Palette mode);

    public void delete(Palette mode);
    
    public List<Palette> findByParms(Map map);
    public List<Palette> findAll();
    public List<Palette> findPaletteByid(Long	 id);
    
  
}
