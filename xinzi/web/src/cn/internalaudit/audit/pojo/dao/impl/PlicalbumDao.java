package cn.internalaudit.audit.pojo.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.pojo.Plicalbum;
import cn.internalaudit.audit.pojo.dao.IPlicalbumDao;

@Repository
public class PlicalbumDao extends BaseDao<Plicalbum> implements IPlicalbumDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Plicalbum> findAll()
    {
        String sql = "select b from Plicalbum b ";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Plicalbum> findPlicalbumByName(String name) {
        String sql = "select b from Plicalbum b where b.name = ? ";
        return this.getHibernateTemplate().find(sql, name);
    }
    @Override
    public List<Plicalbum> findByParms(Map map)
    {
        String ql = "select b from Plicalbum b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and b." + name + " = '" + map.get(name) + "' ";
            }
        }
        System.out.println(ql+"最后输出的结果 pli");
        return this.find(ql);
    }

	@Override
	public List<Plicalbum> findPlicalbumByid(Long id) {
		  String sql = "select b from Plicalbum b where b.id = ? ";
	        return this.getHibernateTemplate().find(sql, id);
	    
	}
	
	@Override
	public Plicalbum findDataplirById(Long id) {
		String  sql="from Plicalbum d  where d.id = :id";//u.name=:myName"; 
		String paramName="id";
		List<Plicalbum> lists=getHibernateTemplate().findByNamedParam(sql, paramName, id);
		Plicalbum objs=lists.get(0);
		//return DataAccessUtils.singleResult(lists);
		return objs;
	}
}
