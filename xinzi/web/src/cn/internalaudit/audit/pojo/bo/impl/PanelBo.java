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
import cn.internalaudit.audit.pojo.Panel;
import cn.internalaudit.audit.pojo.bo.IPanelBo;
import cn.internalaudit.audit.pojo.dao.IPanelDao;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
@Service("PanelBo")
public class PanelBo extends Bo<Panel, IPanelDao>
		implements IPanelBo {

	@Resource(name = "panelDao")
	private IPanelDao PanelDao;

	@Override
	protected IPanelDao getDao() {
		return PanelDao;
	}

	public List<Panel> findPanelByTpye(Integer type) {

		return getDao().findpanelByTpye(type);
	}

	public Panel findPanelByName(String type,
			String name) {

		return getDao().findpanelByName(type, name);
	}

	@Override
	public List<Panel> findPanelByCode(String code,
			Integer type) {

		return getDao().findPanelByCode(code, type);
	}

	@Override
	public List<Panel> findPanelByid(Long id, Integer type) {
		// TODO Auto-generated method stub
		return getDao().findPanelByid(id, type);
	}

	// @Override
	// public List<Panel> findPanelByNameOrType(String name,
	// String code, int type) {
	//		
	// return this.getDao().findPanelByNameOrType(name, code,type);
	// }

	@Override
	public boolean isAvailableByCode(Panel Panel,
			String code, int type) {
		List<Panel> Panels = findPanelByCode(code,
				type);
		if (Panel.getId() == null) {
			if (Panels == null || Panels.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Panel p : Panels) {
				if (p.getId().longValue() != Panel.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean isAvailableByName(Panel Panel,
			String name, int type) {
		List<Panel> Panels = findPanelByName1(name,
				type);
		if (Panel.getId() == null) {
			if (Panels == null || Panels.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (Panel p : Panels) {
				if (p.getId().longValue() != Panel.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public List<Panel> findPanelByName1(String name, int type) {
		return this.getDao().findPanelByName1(name, type);
	}

	@Override
	public void add(Panel mode) {
		// TODO Auto-generated method stub
		PanelDao.save(mode);
	}

	@Override
	public void delete(Panel mode) {
		// TODO Auto-generated method stub
		PanelDao.remove(mode);
	}

	@Override
	public List<Panel> findByParms(Map map) {
		// TODO Auto-generated method stub
		String ql = "select e from Panel e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like '" + map.get(name) + "' ";
			}
		}
		return this.find(ql);
	}

	@Override
	public void update(Panel mode) {
		// TODO Auto-generated method stub
		PanelDao.merge(mode);
	}
	public List<Panel> findAll(){
		return getDao().findAll();
	}

	@Override
	public Panel findPanelByCode(String code, String type) {
		return getDao().findPanelByCode(code, type);
	}

	@Override
	public List<Panel> findPanelByName(Integer type, String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
