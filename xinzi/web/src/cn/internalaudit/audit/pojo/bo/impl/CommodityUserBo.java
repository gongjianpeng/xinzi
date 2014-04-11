package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.CommodityUser;
import cn.internalaudit.audit.pojo.bo.ICommodityUserBo;
import cn.internalaudit.audit.pojo.dao.ICommodityUserDao;
@Service
public class CommodityUserBo extends Bo<CommodityUser, ICommodityUserDao> implements ICommodityUserBo
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
    private ICommodityUserDao commodityuserDao;
   
    
    @Override
    protected ICommodityUserDao getDao()
    {
        return commodityuserDao;
    }
    
    @Override
    public List<CommodityUser> findCommodityUserByTypeId(String id)
    {
        
        return commodityuserDao.findCommodityUserByTypeId(id);
    }

    @Override
    public List<CommodityUser> findCommodityUserById(long id)
    {
        
        return commodityuserDao.findCommodityUserById(id);
    }

	@Override
	public void delete(CommodityUser mode) {
		
		commodityuserDao.remove(mode);
	}

	@Override
	public List<CommodityUser> findByParms(Map map) {
		
		System.out.println("BO--------------");
		return commodityuserDao.findByParms(map);
	}

	@Override
	public List<CommodityUser> findCommodityUserByid(Long id) {
		
		return commodityuserDao.findCommodityUserById(id);
	}

	@Override
	public void update(CommodityUser mode) {
		
		commodityuserDao.merge(mode);
		
	}

	@Override
	public List<CommodityUser> findCommodityUserByproClass(String proclass) {
		
		return commodityuserDao.findCommodityUserByproClass(proclass);
	}

	@Override
	public List<CommodityUser> findProLimitAll() {
		
		return commodityuserDao.findProLimitAll();
	}

	@Override
	public List<CommodityUser> findProductListAll(Map params) {
		return commodityuserDao.findProductListAll(params);
	}

	@Override
	public List<CommodityUser> getTotalprice(long id) {
		return commodityuserDao.getTotalprice(id);
	}

	@Override
	public List<CommodityUser> findCommodityUserByidstatus(Long id,
			String status,String brandName) {
		// TODO Auto-generated method stub
		return commodityuserDao.findCommodityUserByIdstatus(id, status,brandName);
	}
}
