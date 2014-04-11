package cn.internalaudit.audit.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IParameterjxsDao;
import cn.internalaudit.audit.entitys.Parameterjxs;
@Repository
public class ParameterjxsDao extends BaseDao<Parameterjxs> implements IParameterjxsDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Parameterjxs> findAll()
    {
        String sql = "select q from Parameterjxs q order by q.name";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Parameterjxs> findParameterjxsByName(String name) {
        String sql = "select q from Parameterjxs q where q.name = ? order by q.name";
        return this.getHibernateTemplate().find(sql, name);
    }
    @Override
    public List<Parameterjxs> findByParms(Map map)
    {
        String ql = "select q from Parameterjxs q where (q.isDelete = false or q.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and " + name + " like '" + map.get(name) + "' ";
            }
        }
        return this.find(ql);
    }
}
