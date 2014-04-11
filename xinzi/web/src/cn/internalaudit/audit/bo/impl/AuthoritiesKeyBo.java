package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IAuthoritiesKeyBo;
import cn.internalaudit.audit.dao.IAuthoritiesKeyDao;
import cn.internalaudit.audit.entitys.AuthoritiesKey;
import cn.internalaudit.audit.entitys.DataDictionary;


@Service("AuthoritiesKeyBo")
public class AuthoritiesKeyBo extends Bo<AuthoritiesKey, IAuthoritiesKeyDao> implements IAuthoritiesKeyBo {

	@Autowired(required = true)
	private IAuthoritiesKeyDao authoritiesKeyDao;

	@Override
	protected IAuthoritiesKeyDao getDao() {
		return authoritiesKeyDao;
	}
	
	public List<AuthoritiesKey> checkValue(String value) {
		return getDao().checkValue(value);
	}
	public List<AuthoritiesKey> checkValueByName(String name){
	    return getDao().checkValueByName(name);
	}

	
	public boolean isAvailableByValue(AuthoritiesKey author, String value) {
		List<AuthoritiesKey> list = checkValue(value);
		if(author.getId()==null){
			if(list == null || list.size()<1){
				return true;
			}else{
				return false;
			}
		}else{
			for(AuthoritiesKey a : list){
				if(a.getValue().equals(value))
					if(!a.getId().equals(author.getId()))				
						return false;
			}
			return true;
		}
	}
   
	public List<AuthoritiesKey> findBymaps(Map  map) {
		return authoritiesKeyDao.findByParms(map);
	}



	

}
