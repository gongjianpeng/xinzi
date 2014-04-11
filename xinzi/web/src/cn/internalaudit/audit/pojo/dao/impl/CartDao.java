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
import cn.internalaudit.audit.pojo.Cart;
import cn.internalaudit.audit.pojo.dao.ICartDao;
@Repository
public class CartDao extends BaseDao<Cart> implements ICartDao
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Cart> findAll()
    {
    	 String sql = "select b.* from Commodity b ";
    	 return  getHibernateTemplate(null,sql);
         // return this.getHibernateTemplate().find(sql);
    }
    
    @Override
    public List<Cart> findByParms(Map map)
    {
    	String ql = "select b.* from Commodity b where (b.isDelete = false or b.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and " + "b."+name + " = " + map.get(name) + " ";
            }
        }
        return this.find(ql);
    }
    
    
    @Override
    public List<Cart> findCartById(final Long id)
    {
    	final String hql="select * from CommodityUser where id = ?";
        try {
			return getHibernateTemplate(id, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;  
    }

    
    /**************
     * 
     * @param addEntity 返回一个实体...
     * @param 如果返回具体的字段就query.addScalar("字段","参数")
     * @param id
     * @param hql
     * @return
     */
	private List<Cart> getHibernateTemplate(final Long id, final String hql) {
		HibernateTemplate tmpl = this.getHibernateTemplate(); 
		return tmpl.execute(new HibernateCallback<List<Cart>>() {  
		    public List<Cart> doInHibernate(Session session)  
		        throws HibernateException, SQLException {  
		        SQLQuery query = session.createSQLQuery(hql);  
		        query.addEntity(Cart.class);
		        query.setLong(0, id);
		        List<Cart> results = query.list();    
		        return results;  
		    }  
		});
	}
}
