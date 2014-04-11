package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IDataDictionaryBo;
import cn.internalaudit.audit.dao.IDataDictionaryDao;
import cn.internalaudit.audit.entitys.DataDictionary;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
@Service("DataDictionaryBo")
public class DataDictionaryBo extends Bo<DataDictionary, IDataDictionaryDao>
		implements IDataDictionaryBo {

	@Resource(name = "dataDictionaryDao")
	private IDataDictionaryDao dataDictionaryDao;

	@Override
	protected IDataDictionaryDao getDao() {
		return dataDictionaryDao;
	}

	public List<DataDictionary> findDataDictionaryByTpye(Integer type) {

		return getDao().findDataDictionaryByTpye(type);
	}

	public List<DataDictionary> findDataDictionaryByTpye(String type) {

		return getDao().findDataDictionaryByStringType(type);
	}
	
	public List<DataDictionary> findDataDictionaryByName(Integer type,
			String name) {

		return getDao().findDataDictionaryByName(type, name);
	}
	public DataDictionary findDataDictionaryByName(String type,
			String name) {

		return getDao().findDataDictionaryByName(type, name);
	}

	@Override
	public List<DataDictionary> findDataDictionaryByCode(String code,
			Integer type) {

		return getDao().findDataDictionaryByCode(code, type);
	}

	@Override
	public List<DataDictionary> findDataDictionaryByid(Long id, Integer type) {
		// TODO Auto-generated method stub
		return getDao().findDataDictionaryByid(id, type);
	}

	// @Override
	// public List<DataDictionary> findDataDictionaryByNameOrType(String name,
	// String code, int type) {
	//		
	// return this.getDao().findDataDictionaryByNameOrType(name, code,type);
	// }

	@Override
	public boolean isAvailableByCode(DataDictionary dataDictionary,
			String code, int type) {
		List<DataDictionary> dataDictionarys = findDataDictionaryByCode(code,
				type);
		if (dataDictionary.getId() == null) {
			if (dataDictionarys == null || dataDictionarys.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (DataDictionary p : dataDictionarys) {
				if (p.getId().longValue() != dataDictionary.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean isAvailableByName(DataDictionary dataDictionary,
			String name, int type) {
		List<DataDictionary> dataDictionarys = findDataDictionaryByName1(name,
				type);
		if (dataDictionary.getId() == null) {
			if (dataDictionarys == null || dataDictionarys.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (DataDictionary p : dataDictionarys) {
				if (p.getId().longValue() != dataDictionary.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public List<DataDictionary> findDataDictionaryByName1(String name, int type) {
		return this.getDao().findDataDictionaryByName1(name, type);
	}

	@Override
	public void add(DataDictionary mode) {
		// TODO Auto-generated method stub
		dataDictionaryDao.save(mode);
	}

	@Override
	public void delete(DataDictionary mode) {
		// TODO Auto-generated method stub
		dataDictionaryDao.remove(mode);
	}

	@Override
	public List<DataDictionary> findByParms(Map map) {
		// TODO Auto-generated method stub
		String ql = "select e from DataDictionary e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like '" + map.get(name) + "' ";
			}
		}
		return this.find(ql);
	}

	@Override
	public void update(DataDictionary mode) {
		// TODO Auto-generated method stub
		dataDictionaryDao.merge(mode);
	}
	public List<DataDictionary> findAll(){
		return getDao().findAll();
	}

	@Override
	public DataDictionary findDataDictionaryByCode(String code, String type) {
		return getDao().findDataDictionaryByCode(code, type);
	}

	@Override
	public List<DataDictionary> findDataDictionaryByStringType(String type) {
	
		return getDao().findDataDictionaryByStringType(type);
	}
}
