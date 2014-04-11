package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.Plicalbum;

public interface IPlicalbumBo extends IBo<Plicalbum>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Plicalbum> findPlicAlbumName(String name);

    
    public void add(Plicalbum mode);

    public void update(Plicalbum mode);

    public void delete(Plicalbum mode);
    
    public List<Plicalbum> findByParms(Map map);
    public List<Plicalbum> findAll();
    public List<Plicalbum> findPlicAlbumByid(Long	 id);
    
  
}
