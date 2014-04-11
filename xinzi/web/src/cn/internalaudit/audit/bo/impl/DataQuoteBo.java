package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IDataQuoteBo;
import cn.internalaudit.audit.dao.IDataQuoteDao;
import cn.internalaudit.audit.entitys.DataQuote;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
@Service
public class DataQuoteBo extends Bo<DataQuote, IDataQuoteDao>
		implements IDataQuoteBo {

	@Resource
	private IDataQuoteDao dataQuoteDao;

	@Override
	protected IDataQuoteDao getDao() {
		return dataQuoteDao;
	}

	public List<DataQuote> findDataQuoteByTpye(Integer type) {

		return getDao().findDataQuoteByTpye(type);
	}

	public List<DataQuote> findDataQuoteByTpye(String type) {

		return getDao().findDataQuoteByStringType(type);
	}
	
	public List<DataQuote> findDataQuoteByName(Integer type,
			String name) {

		return getDao().findDataQuoteByName(type, name);
	}
	public DataQuote findDataQuoteByName(String type,
			String name) {

		return getDao().findDataQuoteByName(type, name);
	}

	@Override
	public List<DataQuote> findDataQuoteByCode(String code,
			Integer type) {

		return getDao().findDataQuoteByCode(code, type);
	}

	@Override
	public List<DataQuote> findDataQuoteByid(Long id, Integer type) {
		// TODO Auto-generated method stub
		return getDao().findDataQuoteByid(id, type);
	}

	// @Override
	// public List<DataQuote> findDataQuoteByNameOrType(String name,
	// String code, int type) {
	//		
	// return this.getDao().findDataQuoteByNameOrType(name, code,type);
	// }

	@Override
	public boolean isAvailableByCode(DataQuote DataQuote,
			String code, int type) {
		List<DataQuote> DataQuotes = findDataQuoteByCode(code,
				type);
		if (DataQuote.getId() == null) {
			if (DataQuotes == null || DataQuotes.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (DataQuote p : DataQuotes) {
				if (p.getId().longValue() != DataQuote.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean isAvailableByName(DataQuote DataQuote,
			String name, int type) {
		List<DataQuote> DataQuotes = findDataQuoteByName1(name,
				type);
		if (DataQuote.getId() == null) {
			if (DataQuotes == null || DataQuotes.size() < 1) {
				return true;
			} else {
				return false;
			}
		} else {
			for (DataQuote p : DataQuotes) {
				if (p.getId().longValue() != DataQuote.getId().longValue()) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public List<DataQuote> findDataQuoteByName1(String name, int type) {
		return this.getDao().findDataQuoteByName1(name, type);
	}

	@Override
	public void add(DataQuote mode) {
		// TODO Auto-generated method stub
		dataQuoteDao.save(mode);
	}

	@Override
	public void delete(DataQuote mode) {
		// TODO Auto-generated method stub
		dataQuoteDao.remove(mode);
	}

	@Override
	public List<DataQuote> findByParms(Map map) {
		// TODO Auto-generated method stub
		String ql = "select e from DataQuote e where (e.isDelete = false or e.isDelete is null)";
		if (map.size() > 0) {
			Set<String> names = map.keySet();
			for (String name : names) {
				ql += " and " + name + " like " + map.get(name) + " ";
			}
		}
		return this.find(ql);
	}

	@Override
	public void update(DataQuote mode) {
		// TODO Auto-generated method stub
		dataQuoteDao.merge(mode);
	}
	public List<DataQuote> findAll(){
		return getDao().findAll();
	}

	@Override
	public DataQuote findDataQuoteByCode(String code, String type) {
		return getDao().findDataQuoteByCode(code, type);
	}

	@Override
	public List<DataQuote> findDataQuoteByStringType(String type) {
	
		return getDao().findDataQuoteByStringType(type);
	}
	/**
	 * 根据  btype  去查询  报价设置 中  色板 设定每平方的价格
	 * by gjp  20140312
	 * @param btype
	 * @return
	 */
	@Override
	public DataQuote findDataQuoteByBtype(String btype,String bpinpai) {
		return getDao().findDataQuoteByBtype(btype,bpinpai);
	}
	
}
