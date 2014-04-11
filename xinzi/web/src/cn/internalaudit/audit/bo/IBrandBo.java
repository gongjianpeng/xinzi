package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.Brand;

public interface IBrandBo extends IBo<Brand>
{
   
    
    /**
     * 
     * @param type
     * @param name
     * @return
     */
   
 

    
    public void add(Brand mode);

    public Brand update(Brand mode);

    public void delete(Brand mode);
    
    public List<Brand> findByParms(Map map);
    public List<Brand> findAll();
    
    public List<Brand> findBrandByName(String name,Long id);



	List<Brand> findByParmsgetDao(Map map);
	
	public Brand findBrandByName(String name);
}
