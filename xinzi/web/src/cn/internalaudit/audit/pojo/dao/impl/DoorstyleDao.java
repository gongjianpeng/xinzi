package cn.internalaudit.audit.pojo.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.pojo.Doorstyle;
import cn.internalaudit.audit.pojo.dao.IDoorstyleDao;

@Repository
public class DoorstyleDao extends BaseDao<Doorstyle> implements IDoorstyleDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Doorstyle> findAll()
    {
        String sql = "select b from Doorstyle b ";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Doorstyle> findDoorstyleByName(String name) {
        String sql = "select b from Doorstyle b where b.name = ? ";
        return this.getHibernateTemplate().find(sql, name);
    }
    @Override
    public List<Doorstyle> findByParms(Map map)
    {
        String ql = "select b from Doorstyle b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and b." + name + " = '" + map.get(name) + "' ";
            }
        }
        return this.find(ql);
    }

	@Override
	public List<Doorstyle> findDoorstyleByid(Long id) {
		  String sql = "select b from Doorstyle b where b.id = ? ";
	        return this.getHibernateTemplate().find(sql, id);
	    
	}
}
