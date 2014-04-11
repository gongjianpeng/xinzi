package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.Commodity;
import cn.internalaudit.audit.pojo.bo.ICommodityBo;
import cn.internalaudit.audit.pojo.dao.ICommodityDao;
@Service("CommodityBo")
public class CommodityBo extends Bo<Commodity, ICommodityDao> implements ICommodityBo
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "commodityDao")
    private ICommodityDao commodityDao;
   
    
    @Override
    protected ICommodityDao getDao()
    {
        return commodityDao;
    }
    
    @Override
    public List<Commodity> findCommodityByTypeId(String id)
    {
        
        return commodityDao.findCommodityByTypeId(id);
    }

    @Override
    public List<Commodity> findCommodityById(long id)
    {
        
        return commodityDao.findCommodityById(id);
    }

	@Override
	public void delete(Commodity mode) {
		
		commodityDao.remove(mode);
	}

	@Override
	public List<Commodity> findByParms(Map map) {
		
		System.out.println("BO--------------");
		return commodityDao.findByParms(map);
	}
  
	
	@Override
	public List<Commodity> findByParmsandorg(Map map,String orgid) {
		
		System.out.println("BO--------------");
		return commodityDao.findByParmsandorg(map, orgid);
	}

	@Override
	public List<Commodity> findCommodityByid(Long id) {
		
		return commodityDao.findCommodityById(id);
	}

	@Override
	public void update(Commodity mode) {
		
		commodityDao.merge(mode);
		
	}

	@Override
	public List<Commodity> findCommodityByproClass(String proclass) {
		
		return commodityDao.findCommodityByproClass(proclass);
	}

	@Override
	public List<Commodity> findProLimitAll() {
		
		return commodityDao.findProLimitAll();
	}

	@Override
	public List<Commodity> findProductListAll(Map params) {
		return commodityDao.findProductListAll(params);
	}
}
