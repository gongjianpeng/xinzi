package cn.internalaudit.audit.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.Brand;

public interface IBrandDao extends IBaseDao<Brand>
{
    

    /**
     * 
     * @param type
     * @param name
     * @return
     */
    public List<Brand> findBrandByName(String name);
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    
    public List<Brand> findAll();
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public List<Brand> findByParms(Map map);
    
	public Brand findDatabrandById(Long id);
	
    public List<Brand> findBrandByName(String name,Long id) ;
	Brand findDatabrandByname(String name);
}
