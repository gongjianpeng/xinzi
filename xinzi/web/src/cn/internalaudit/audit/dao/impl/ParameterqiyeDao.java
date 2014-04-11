package cn.internalaudit.audit.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;
import cn.internalaudit.audit.dao.IParameterqiyeDao;
import cn.internalaudit.audit.entitys.Parameterqiye;
@Repository
public class ParameterqiyeDao extends BaseDao<Parameterqiye> implements IParameterqiyeDao
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    @Override
    public List<Parameterqiye> findAll()
    {
        String sql = "select q from Parameterqiye q order by q.name";
        return this.getHibernateTemplate().find(sql);
    }
  
    @Override
    public List<Parameterqiye> findParameterqiyeByName(String name) {
        String sql = "select q from Parameterqiye q where q.name = ? order by q.name";
        System.out.println("name by"+sql);
        return this.getHibernateTemplate().find(sql, name);
    }
    
    @Override
    public List<Parameterqiye> findParameterqiyeByName(String name,String type) {
    	 //System.out.println("02-12--查询设置门尺寸"+name+type);
        String sql = "select q from Parameterqiye q where q.name = ? and q.type = ? order by q.name";
       // System.out.println("02-12--查询设置门尺寸"+sql);
        return this.getHibernateTemplate().find(sql, name,type);
    }
    
    @Override
    public List<Parameterqiye> findByParms(Map map)
    {
        String ql = "select q from Parameterqiye q where (q.isDelete = false or q.isDelete is null)";
        if (map.size() > 0) {
            Set<String> names = map.keySet();
            for (String name : names) {
                ql += " and " + name + " like '" + map.get(name) + "' ";
            }
        }
        return this.find(ql);
    }

	@Override
	public List<Parameterqiye> findParameterqiyeByType(String type) {
		// System.out.println("02-12--查询设置门类型"+type);
		  String sql = "select q from Parameterqiye q where q.type = ? order by q.type";
	      //  System.out.println("type by"+sql);
	        return this.getHibernateTemplate().find(sql, type);
	    }

	@Override
	public List<Parameterqiye> findParameterqiyeByNamechick(String type,
			String chichunk) {
		// System.out.println("02-12--查询设置门类型"+type+chichunk);
		 String sql = "select q from Parameterqiye q where q.type = ? and q.chichunk = ? order by q.type";
	      //  System.out.println("name by"+sql);
	        return this.getHibernateTemplate().find(sql, type,chichunk);
	}
	
	@Override
	public List<Parameterqiye> findParameterqiyeByNamechickgao(String type,
			String chichun) {
		// System.out.println("02-12--查询设置门类型"+type+chichun);
		 String sql = "select q from Parameterqiye q where q.type = ? and q.chichun = ? order by q.type";
	      //  System.out.println("name by"+sql);
	        return this.getHibernateTemplate().find(sql, type,chichun);
	}
}
