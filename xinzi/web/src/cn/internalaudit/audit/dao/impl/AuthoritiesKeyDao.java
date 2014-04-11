package cn.internalaudit.audit.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.internalaudit.audit.base.BaseDao;

import cn.internalaudit.audit.dao.IAuthoritiesKeyDao;
import cn.internalaudit.audit.entitys.AuthoritiesKey;
import cn.internalaudit.audit.entitys.DataDictionary;

@Repository
public class AuthoritiesKeyDao extends BaseDao<AuthoritiesKey> implements IAuthoritiesKeyDao{


	public List<AuthoritiesKey> checkValue(String value) {
		
		String queryString = "select a from AuthoritiesKey a where a.value = ?";
		
		List<AuthoritiesKey> list = getHibernateTemplate().find(queryString,new Object[]{value});
		return list;
	}
	public List<AuthoritiesKey> checkValueByName(String name) {
        
        String queryString = "select a from AuthoritiesKey a where a.name = ?";
        
        List<AuthoritiesKey> list = getHibernateTemplate().find(queryString,new Object[]{name});
        return list;
    }

	
	 
	public List<AuthoritiesKey> findByParms(Map map) {
		String ql = "select e from  AuthoritiesKey  e where (e.isDelete = false or e.isDelete is null)  ";
//		Object[] values = null;
//		if (map.size() > 0) {
//			Set<String> names = map.keySet();
//			for (String name : names) {
//				ql += " and " + name + " == '" + map.get(name) + "' ";
//			}
//		}
		System.out.println("1层中 输出的ql为"+ql);
		Object[] values = null;
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				if (map.get(name) == null) {
					map.remove(name);
					ql += " and " + name + " is null ";
					System.out.println("1层中 输出的ql为"+ql);
				} else {
					String value = map.get(name).toString().trim();
					if (value.startsWith("%")||value.endsWith("%")) {
						ql += " and " + name + " like ? ";
					} else {
					//	ql += " and " + name + " = "+value;
						ql+="and e.id="+value;
						System.out.println("2层中 输出的ql为"+ql);
					}
				}
			}
			if (map.size() <= 0)
				values = null;
			else
				values = map.values().toArray();
		}
		System.out.println("BaseDao层中 输出的ql为"+ql);
		return this.find(ql);// getJpaTemplate().find(ql, values);
	}
	 
	

	
}
