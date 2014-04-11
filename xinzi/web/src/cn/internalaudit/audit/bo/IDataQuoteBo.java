package cn.internalaudit.audit.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.entitys.DataDictionary;
import cn.internalaudit.audit.entitys.DataQuote;


/**
 * 数字字典
 * 
 * @author bd02
 * 
 */
public interface IDataQuoteBo extends IBo<DataQuote> {
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

	public boolean isAvailableByCode(DataQuote DataQuote,
			String code, int type);

	public boolean isAvailableByName(DataQuote DataQuote,
			String name, int type);

	public void add(DataQuote mode);

	public void update(DataQuote mode);

	public void delete(DataQuote mode);

	public List<DataQuote> findByParms(Map map);
    public DataQuote findDataQuoteByCode(String code, String type);


    public DataQuote findDataQuoteByBtype(String btype,String bpinpai);
	
}
