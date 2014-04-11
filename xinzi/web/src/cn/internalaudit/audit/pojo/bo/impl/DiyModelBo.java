package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IDataDictionaryBo;
import cn.internalaudit.audit.dao.IDataDictionaryDao;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.pojo.DiyModel;
import cn.internalaudit.audit.pojo.bo.IDiyModelBo;
import cn.internalaudit.audit.pojo.dao.IDiyModelDao;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
@Service("DiyModelBo")
public class DiyModelBo extends Bo<DiyModel, IDiyModelDao>
		implements IDiyModelBo {

	@Resource(name = "diyModelDao")
	private IDiyModelDao diyModelDao;

	@Override
	protected IDiyModelDao getDao() {
		return diyModelDao;
	}

	public List<DiyModel> findDiyModelByType(String Codetype) {

		return getDao().findDiyModelByStringType(Codetype);
	}
	
	public List<DiyModel> findDiyModelByName(Integer type,
			String name) {

		return getDao().findDiyModelByName(type, name);
	}
	public DiyModel findDiyModelByName(String type,
			String name) {

		return getDao().findDiyModelByName(type, name);
	}

	@Override
	public List<DiyModel> findDiyModelByCode(String code,
			Integer type) {

		return getDao().findDiyModelByCode(code, type);
	}

	@Override
	public List<DiyModel> findDiyModelByid(Long id, Integer type) {
		// TODO Auto-generated method stub
		return getDao().findDiyModelByid(id, type);
	}

	// @Override
	// public List<DiyModel> findDiyModelByNameOrType(String name,
	// String code, int type) {
	//		
	// return this.getDao().findDiyModelByNameOrType(name, code,type);
	// }

	@Override
	public boolean isAvailableByCode(DiyModel DiyModel,
			String code, int type) {
		List<DiyModel> DiyModels = findDiyModelByCode(code,
				type);
		if (DiyModel.getId() == null) {
			if (DiyModels == null || DiyModels.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (DiyModel p : DiyModels) {
				if (p.getId().longValue() != DiyModel.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean isAvailableByName(DiyModel DiyModel,
			String name, int type) {
		List<DiyModel> DiyModels = findDiyModelByName1(name,
				type);
		if (DiyModel.getId() == null) {
			if (DiyModels == null || DiyModels.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (DiyModel p : DiyModels) {
				if (p.getId().longValue() != DiyModel.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public List<DiyModel> findDiyModelByName1(String name, int type) {
		return this.getDao().findDiyModelByName1(name, type);
	}

	@Override
	public void add(DiyModel mode) {
		// TODO Auto-generated method stub
		diyModelDao.save(mode);
	}

	@Override
	public void delete(DiyModel mode) {
		// TODO Auto-generated method stub
		diyModelDao.remove(mode);
	}

	@Override
	public List<DiyModel> findByParms(Map map) {
		// TODO Auto-generated method stub
		String ql = "select e from DiyModel e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like '" + map.get(name) + "' ";
			}
		}
		return this.find(ql);
	}

	@Override
	public void update(DiyModel mode) {
		// TODO Auto-generated method stub
		diyModelDao.merge(mode);
	}
	public List<DiyModel> findAll(){
		return getDao().findAll();
	}

	@Override
	public DiyModel findDiyModelByCode(String code, String type) {
		return getDao().findDiyModelByCode(code, type);
	}

	@Override
	public List<DiyModel> findDiyModelByStringcodetype(String codetype) {
		// TODO Auto-generated method stub
		return getDao().findDiyModelByType(codetype);
	}

	@Override
	public List<DiyModel> findDiyModelByCodeName(String code, String name) {
		
		return  getDao().findDiyModelByCodeName(code,name);
	}
	
public List<DiyModel> findDiyModelByName(String name) {
		
		return  getDao().findDiyModelByName(name);
	}

@Override
public List<DiyModel> findDiyModelByMoidname(String userfordmodelid, String name) {
	//  查询 用户上传的模版替换图片数据
	return diyModelDao.findDiyModelByMoidname(userfordmodelid, name);
}

@Override
public List<DiyModel> findDiyModelBydmodelid(String userfordmodelid) {
	//  查询 用户上传的模版替换图片数据
	return diyModelDao.findDiyModelBydmodelid(userfordmodelid);
}

}
