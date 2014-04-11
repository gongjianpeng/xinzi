package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.Commodity;

public interface ICommodityBo extends IBo<Commodity>
{
	public List<Commodity> findProLimitAll() ;
	
	public List<Commodity> findProductListAll(Map params);
    public List<Commodity> findCommodityByTypeId(String id);
    public List<Commodity> findCommodityById(long id);
    public void update(Commodity mode);

    public void delete(Commodity mode);
    
    public List<Commodity> findByParms(Map map);
    public List<Commodity> findAll();
    public List<Commodity> findCommodityByid(Long	 id);
    public List<Commodity> findCommodityByproClass(String proclass);

	List<Commodity> findByParmsandorg(Map map, String orgid);
}
