package cn.internalaudit.audit.dao;

import java.util.List;

import cn.internalaudit.audit.base.IBaseDao;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.DataQuote;


/**
 * 
 * data dictionary
 * 
 * @author bd02
 * 
 */
public interface IDataQuoteDao extends IBaseDao<DataQuote> {
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<DataQuote> findDataQuoteByTpye(Integer type);
	
	public List<DataQuote> findDataQuoteByStringType(String type);

	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<DataQuote> findDataQuoteByName(Integer type,
			String name);
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public DataQuote findDataQuoteByName(String type,
			String name);

	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	public List<DataQuote> findDataQuoteByCode(String code,
			Integer type);
	public DataQuote findDataQuoteByCode(String code, String type);
	/**
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List<DataQuote> findDataQuoteByid(Long id, Integer type);

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public List<DataQuote> findDataQuoteByName1(String name, int type);
	
	public List<DataQuote> findAll();
	/**
	 * 根据  btype  去查询  报价设置 中  色板 设定每平方的价格
	 * by gjp  20140312
	 * @param btype
	 * @return
	 */
	DataQuote findDataQuoteByBtype(String btype,String bpinpai);

}
