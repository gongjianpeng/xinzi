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
import cn.internalaudit.audit.entitys.base.Dealer;
import cn.internalaudit.audit.pojo.Commodity;
import cn.internalaudit.audit.pojo.dao.ICommodityDao;

@Repository
public class CommodityDao extends BaseDao<Commodity> implements ICommodityDao {
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Commodity> findAll() {
		// select bm from BusinessMode bm where bm.isRoot = true and
		// bm.organization.id = ? order by code"
		// select r from Roles r join r.persons p where p.id = ?
		// elect d from Department d where d.code = ? and d.organization.id =
		// ?";
		// String sql =
		// "select b from Commodity b,Panel p where b.panel.id=p.id ";
		String sql = "select b from Commodity b  ";

		return this.getHibernateTemplate().find(sql);

	}

	@Override
	public List<Commodity> findByParms(Map map) {
		String ql = "select b from Commodity b where (b.isDelete = false or b.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				// ql += " and " + name + " like '" + map.get(name) + "' ";
				ql += " and " + "b." + name + " = '" + map.get(name) + "' ";
			}
		}
		System.out.println("门查询 " + ql);
		return this.find(ql);
	}
     
	@Override
	public List<Commodity> findByParmsandorg(Map map,String orgid) {
		String ql = "select b from Commodity b where (b.isDelete = false or b.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				// ql += " and " + name + " like '" + map.get(name) + "' ";
				ql += " and " + "b." + name + " like " + map.get(name) + " ";
			}
		}
		ql +="and b.org = "+orgid;
		System.out.println("门查询 " + ql);
		return this.find(ql);
	}
	@Override
	public List<Commodity> findCommodityById(long id) {
		String ql = "select b from Commodity b where (b.isDelete = false or b.isDelete is null) and b.id = ?";
		// StringBuffer hql= new StringBuffer();
		System.out.println("查询商品" + ql);
		// hql.append("select b  from Commodity b  where b.id = ?");
		return getJpaTemplate().find(ql.toString(), id);
	}
	@Override
	public Commodity findDataCommodityById(long id) {
		// TODO Auto-generated method stub

		String sql = "from Commodity d  where d.id = :id";// u.name=:myName";
		String paramName = "id";
		List<Commodity> lists = getHibernateTemplate().findByNamedParam(sql,paramName, id);
		Commodity objs = lists.get(0);
		// return DataAccessUtils.singleResult(lists);
		return objs;

	}

	@Override
	public List<Commodity> findCommodityByTypeId(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append("select c from Commodity c where c.type = ?");
		return getJpaTemplate().find(hql.toString(), id);
	}


	@Override
	public List<Commodity> findCommodityByproClass(String proclass) {
		System.out.println("proclass    ..." + proclass);
		StringBuffer hql = new StringBuffer();

		hql.append("select b  from Commodity b  where b.proclass =?");
		return getJpaTemplate().find(hql.toString(), proclass);
	}

	/***
	 * 默认加载12条数据出来
	 */
	public List<Commodity> findProLimitAll() {
		String hql = "select * from commodity  limit 12 ";
		return getHibernateTemplate(hql);

	}

	/***
	 * 根据条件查询所有产品
	 */
	public List<Commodity> findProductListAll(Map params) {
		String hql = "";
        String brand=params.get("brand").toString();
		String condition = params.get("condition").toString();
		String start = params.get("start").toString();
		String pageSize = params.get("pageSize").toString();
		if (condition.equals("")) {
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("select * from commodity c where c.brand = ")
				.append("'" + brand + "'");
					//.append("'%").append("").append("%'");
			hql = buffer1.toString();
			System.out.println("全部查询"+hql);
			return getHibernateTemplate(hql);
		} else {
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("select * from commodity c where c.brand LIKE ")
					.append("'%" + condition + "%'");
			buffer2.append(" ORDER BY createDate DESC LIMIT ").append(start)
					.append(",").append(pageSize);
			hql = buffer2.toString();
			System.out.println("全部查询"+hql);
			return getHibernateTemplate(hql);
		}
	}

	/**************
	 * 
	 * @param addEntity
	 *            返回一个实体...
	 * @param 如果返回具体的字段就query
	 *            .addScalar("字段","参数")
	 * @param id
	 * @param hql
	 * @return
	 */
	private List<Commodity> getHibernateTemplate(final String hql) {
		HibernateTemplate tmpl = this.getHibernateTemplate();
		return tmpl.execute(new HibernateCallback<List<Commodity>>() {
			public List<Commodity> doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(hql);
				query.addEntity(Commodity.class);
				List<Commodity> results = query.list();
				return results;
			}
		});
	}

	@Override
	public Integer getTotal(String params) {

		return null;
	}

	

}
