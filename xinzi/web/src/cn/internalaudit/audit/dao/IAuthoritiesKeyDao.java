package cn.internalaudit.audit.dao;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.AuthoritiesKey;


public interface IAuthoritiesKeyDao extends IBaseDao<AuthoritiesKey> {

	/**
	 * 
	 * @param value
	 * @return
	 */
	public List<AuthoritiesKey> checkValue(String value);
	public List<AuthoritiesKey> findByParms(Map map);
	public List<AuthoritiesKey> checkValueByName(String name);
}
