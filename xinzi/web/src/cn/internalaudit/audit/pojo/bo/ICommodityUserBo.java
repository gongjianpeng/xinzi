package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.CommodityUser;

public interface ICommodityUserBo extends IBo<CommodityUser>
{
	public List<CommodityUser> findProLimitAll() ;
	
	public List<CommodityUser> findProductListAll(Map params);
    public List<CommodityUser> findCommodityUserByTypeId(String id);
    public List<CommodityUser> findCommodityUserById(long id);
    public List<CommodityUser> getTotalprice(long id);
    public void update(CommodityUser mode);

    public void delete(CommodityUser mode);
    
    public List<CommodityUser> findByParms(Map map);
    public List<CommodityUser> findAll();
    public List<CommodityUser> findCommodityUserByid(Long	 id);
    
    public List<CommodityUser> findCommodityUserByidstatus(Long id,String status,String brandName);
    public List<CommodityUser> findCommodityUserByproClass(String proclass);
}
