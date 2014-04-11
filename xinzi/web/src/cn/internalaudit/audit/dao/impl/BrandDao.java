package cn.internalaudit.audit.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IBrandDao;
import cn.internalaudit.audit.entitys.Brand;
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.pojo.Elses;
import cn.internalaudit.audit.security.LoginInfo;
@Repository
public class BrandDao extends BaseDao<Brand> implements IBrandDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
   
    
    @Override
    public List<Brand> findAll()
    {
        String sql = "select b from Brand b ";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Brand> findBrandByName(String name) {
        String sql = "select b from Brand b where b.name = ?";
        System.out.println("FindByname"+sql);
        return this.getHibernateTemplate().find(sql, name);
    }
    

    @Override
    public List<Brand> findBrandByName(String name,Long id) {
        String sql = "select b from Brand b where b.name=?  and b.org = ?";
        System.out.println("FindByname"+sql);
        return this.getHibernateTemplate().find(sql, name);
    }
    
    @Override
    public List<Brand> findByParms(Map map)
    {
    	
    	

        String ql = "select b from Brand b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and b."+name+"=" + map.get(name) + " ";
            }
        }
   
        System.out.println("BrandDao 层输出"+ql);
        return this.find(ql);
    }
    
    @Override
	public Brand findDatabrandById(Long id) {
		String  sql="from Brand d  where d.id = :id";//u.name=:myName"; 
		String paramName="id";
		List<Brand> lists=getHibernateTemplate().findByNamedParam(sql, paramName, id);
		Brand objs=lists.get(0);
		//return DataAccessUtils.singleResult(lists);
		return objs;
	}
    
    
    @Override
	public Brand findDatabrandByname(String name) {
		String  sql="from Brand d  where d.name = :name";//u.name=:myName"; 
		String paramName="name";
		List<Brand> lists=getHibernateTemplate().findByNamedParam(sql, paramName, name);
		Brand objs=lists.get(0);
		//return DataAccessUtils.singleResult(lists);
		return objs;
	}
}
