package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.AuthoritiesKey;


public interface IAuthoritiesKeyBo extends IBo<AuthoritiesKey> {

	/**
	 *
	 * @param value
	 * @return
	 */
	public List<AuthoritiesKey> checkValue(String value);
	
	/**
	 *
	 * @param author
	 * @param value
	 * @return
	 */
	public boolean isAvailableByValue(AuthoritiesKey author, String value);
	// gjp  2013 12 3
	public List<AuthoritiesKey> findBymaps(Map  map);
	
	public List<AuthoritiesKey> checkValueByName(String name);
}
