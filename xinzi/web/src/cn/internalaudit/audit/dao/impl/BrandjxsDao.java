package cn.internalaudit.audit.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IBrandjxsDao;
import cn.internalaudit.audit.entitys.Brandjxs;

@Repository
public class BrandjxsDao extends BaseDao<Brandjxs> implements IBrandjxsDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Brandjxs> findAll()
    {
        String sql = "select b from Brandjxs b ";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Brandjxs> findBrandjxsByName(String name) {
        String sql = "select b from Brandjxs b where b.name = ? ";
        return this.getHibernateTemplate().find(sql, name);
    }
    @Override
    public List<Brandjxs> findByParms(Map map)
    {
        String ql = "select b from Brandjxs b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and " + name + " like '" + map.get(name) + "' ";
            }
        }
        return this.find(ql);
    }

	@Override
	public List<Brandjxs> findbrandByid(Long id) {
		  String sql = "select b from Brandjxs b where b.id = ? ";
	        return this.getHibernateTemplate().find(sql, id);
	    
	}
}
