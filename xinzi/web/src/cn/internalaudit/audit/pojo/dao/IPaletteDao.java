package cn.internalaudit.audit.pojo.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.pojo.Palette;

public interface IPaletteDao extends IBaseDao<Palette>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Palette> findPaletteByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Palette> findAll();
    
    public List<Palette> findPaletteByid(Long id);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Palette> findByParms(Map map);
}
