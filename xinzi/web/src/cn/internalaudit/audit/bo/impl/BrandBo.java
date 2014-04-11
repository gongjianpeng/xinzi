package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IBrandBo;
import cn.internalaudit.audit.dao.IBrandDao;
import cn.internalaudit.audit.entitys.Brand;
@Service("BrandBo")
public class BrandBo extends Bo<Brand, IBrandDao> implements IBrandBo
{
    
    @Resource(name = "brandDao")
    private IBrandDao brandDao;
   
    @Override
    protected IBrandDao getDao()
    {
        return brandDao;
    }

    @Override
    public void add(Brand mode)
    {       
        brandDao.save(mode);
    }

    @Override
    public void delete(Brand mode)
    {
        brandDao.remove(mode);
    }

    @Override
    public Brand findBrandByName(String name)
    {
    	return brandDao.findDatabrandByname(name);
    }

    @Override
    public List<Brand> findByParms(Map map)
    {
    	System.out.println("BrandBo 层输出");
        return getDao().findByParms(map);
    }
    
    
    @Override
    public List<Brand> findByParmsgetDao(Map map)
    {
    	System.out.println("BrandBo getDao层输出");
        return getDao().findByParms(map);
    }

    @Override
    public Brand update(Brand mode)
    {
        
    	return brandDao.merge(mode);
    }
    public List<Brand> findAll(){
        return getDao().findAll();
    }

    
    public List<Brand> findBrandByName(String name,Long id) {
    	
    	return brandDao.findBrandByName(name, id);
    	
    }
	
	

	

}
