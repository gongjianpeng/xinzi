package cn.internalaudit.audit.pojo.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.pojo.OrderDetail;
import cn.internalaudit.audit.pojo.dao.IOrderDetailDao;
@Repository
public class OrderDetailDao extends BaseDao<OrderDetail> implements IOrderDetailDao
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
 
//    public List<OrderDetail> findAll()
//    {
//    	 String sql = "select b.* from OrderDetail b ";
//    	 return this.find(ql);
//    }
//    
    @Override
    public List<OrderDetail> findByParms(Map map,String orgid)
    {
    	String ql = "select b from OrderDetail b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and " + "b."+name + " = " + map.get(name) + " ";
            }
        }
        ql +="or b.code="+orgid;
        System.out.println("--orderDetail---"+ql);
        return this.find(ql);
    }
    
    
    @Override
    public List<OrderDetail> findByParmsandorg(Map map,String org)
    {
    	String ql = "select b from OrderDetail b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and " + "b."+name + " = " + map.get(name) + " ";
            }
        }
        ql +="and b.org="+org;
        System.out.println("--orderDetail---"+ql);
        return this.find(ql);
    }
    
    
    @Override
    public List<OrderDetail> findCartById(final Long id)
    {
    	final String hql="select * from OrderDetail where id = ?";
        try {
			return getHibernateTemplate(id, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;  
    }

    @Override
	public void add(OrderDetail entity) {
		 
		this.getJpaTemplate().save(entity);
	}
    
    
    /**************
     * 
     * @param addEntity 返回一个实体...
     * @param 如果返回具体的字段就query.addScalar("字段","参数")
     * @param id
     * @param hql
     * @return
     */
	private List<OrderDetail> getHibernateTemplate(final Long id, final String hql) {
		HibernateTemplate tmpl = this.getHibernateTemplate(); 
		return tmpl.execute(new HibernateCallback<List<OrderDetail>>() {  
		    public List<OrderDetail> doInHibernate(Session session)  
		        throws HibernateException, SQLException {  
		        SQLQuery query = session.createSQLQuery(hql);  
		        query.addEntity(OrderDetail.class);
		        query.setLong(0, id);
		        List<OrderDetail> results = query.list();    
		        return results;  
		    }  
		});
	}

	
}
