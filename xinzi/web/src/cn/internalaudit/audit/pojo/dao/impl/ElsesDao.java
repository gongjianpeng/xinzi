package cn.internalaudit.audit.pojo.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.pojo.Elses;
import cn.internalaudit.audit.pojo.dao.IElsesDao;

@Repository
public class ElsesDao extends BaseDao<Elses> implements IElsesDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Elses> findAll()
    {
        String sql = "select b from Elses b ";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Elses> findElsesByName(String name) {
        String sql = "select b from Elses b where b.name = ? ";
        return this.getHibernateTemplate().find(sql, name);
    }
    @Override
    public List<Elses> findByParms(Map map)
    {
        String ql = "select b from Elses b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and b." + name + " = '" + map.get(name) + "' ";
            }
        }
        return this.find(ql);
    }

	@Override
	public List<Elses> findElsesByid(Long id) {
		  String sql = "select b from Elses b where b.id = ? ";
	        return this.getHibernateTemplate().find(sql, id);
	    
	}
}
