package cn.internalaudit.audit.pojo.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.pojo.Frame;
import cn.internalaudit.audit.pojo.dao.IFrameDao;

@Repository
public class FrameDao extends BaseDao<Frame> implements IFrameDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Frame> findAll()
    {
        String sql = "select b from Frame b ";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Frame> findFrameByName(String name) {
        String sql = "select b from Frame b where b.name = ? ";
        return this.getHibernateTemplate().find(sql, name);
    }
    @Override
    public List<Frame> findByParms(Map map)
    {
        String ql = "select b from Frame b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and b." + name + " = '" + map.get(name) + "' ";
            }
        }
        return this.find(ql);
    }

	@Override
	public List<Frame> findFrameByid(Long id) {
		  String sql = "select b from Brandjxs b where b.id = ? ";
	        return this.getHibernateTemplate().find(sql, id);
	    
	}
}
