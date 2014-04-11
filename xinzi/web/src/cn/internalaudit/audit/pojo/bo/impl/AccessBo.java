package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.Accessories;
import cn.internalaudit.audit.pojo.bo.IAccessBo;
import cn.internalaudit.audit.pojo.dao.IAccessDao;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */

@Service("AccessBo")
public class AccessBo extends Bo<Accessories, IAccessDao>
		implements IAccessBo {

	@Resource(name = "accessDao")
	private IAccessDao accessDao;

	@Override
	protected IAccessDao getDao() {
		return accessDao;
	}

	public List<Accessories> findAccessoriesByTpye(Integer type) {

		return getDao().findAccessoriesByTpye(type);
	}

	public Accessories findAccessoriesByName(String type,
			String name) {

		return getDao().findAccessoriesByName(type, name);
	}

	@Override
	public List<Accessories> findAccessoriesByCode(String code,
			Integer type) {

		return getDao().findAccessoriesByCode(code, type);
	}

	@Override
	public List<Accessories> findAccessoriesByid(Long id, Integer type) {
		// TODO Auto-generated method stub
		return getDao().findAccessoriesByid(id, type);
	}

	// @Override
	// public List<Accessories> findAccessoriesByNameOrType(String name,
	// String code, int type) {
	//		
	// return this.getDao().findAccessoriesByNameOrType(name, code,type);
	// }

	@Override
	public boolean isAvailableByCode(Accessories Accessories,
			String code, int type) {
		List<Accessories> Accessoriess = findAccessoriesByCode(code,
				type);
		if (Accessories.getId() == null) {
			if (Accessoriess == null || Accessoriess.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Accessories p : Accessoriess) {
				if (p.getId().longValue() != Accessories.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean isAvailableByName(Accessories Accessories,
			String name, int type) {
		List<Accessories> Accessoriess = findAccessoriesByName1(name,
				type);
		if (Accessories.getId() == null) {
			if (Accessoriess == null || Accessoriess.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Accessories p : Accessoriess) {
				if (p.getId().longValue() != Accessories.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public List<Accessories> findAccessoriesByName1(String name, int type) {
		return this.getDao().findAccessoriesByName1(name, type);
	}

	@Override
	public void add(Accessories mode) {
		// TODO Auto-generated method stub
		accessDao.save(mode);
	}

	@Override
	public void delete(Accessories mode) {
		// TODO Auto-generated method stub
		accessDao.remove(mode);
	}

	@Override
	public List<Accessories> findByParms(Map map) {
		// TODO Auto-generated method stub
		String ql = "select e from Accessories e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like '" + map.get(name) + "' ";
			}
		}
		return this.find(ql);
	}

	@Override
	public void update(Accessories mode) {
		// TODO Auto-generated method stub
		accessDao.merge(mode);
	}
	public List<Accessories> findAll(){
		return getDao().findAll();
	}

	@Override
	public Accessories findAccessoriesByCode(String code, String type) {
		return getDao().findAccessoriesByCode(code, type);
	}

	@Override
	public List<Accessories> findAccessoriesByName(Integer type, String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
