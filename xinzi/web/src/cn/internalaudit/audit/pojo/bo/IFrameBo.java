package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Frame;

public interface IFrameBo extends IBo<Frame>
{
    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Frame> findFrameName(String name);

    
    public void add(Frame mode);

    public void update(Frame mode);

    public void delete(Frame mode);
    
    public List<Frame> findByParms(Map map);
    public List<Frame> findAll();
    public List<Frame> findFrameByid(Long	 id);
    
  
}
