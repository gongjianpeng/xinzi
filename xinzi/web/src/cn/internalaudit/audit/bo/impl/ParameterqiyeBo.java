package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IParameterqiyeBo;
import cn.internalaudit.audit.dao.IParameterqiyeDao;
import cn.internalaudit.audit.entitys.Parameterqiye;
@Service("ParameterqiyeBo")
public class ParameterqiyeBo extends Bo<Parameterqiye, IParameterqiyeDao> implements IParameterqiyeBo
{
    @Resource(name = "parameterqiyeDao")
    private IParameterqiyeDao parameterqiyeDao;
    @Override
    protected IParameterqiyeDao getDao()
    {
        return parameterqiyeDao;
    }

    @Override
    public void add(Parameterqiye mode)
    {       
        parameterqiyeDao.save(mode);
    }

    @Override
    public void delete(Parameterqiye mode)
    {
        parameterqiyeDao.remove(mode);
    }

    @Override
    public List<Parameterqiye> findParameterqiyeByName(String name)
    {
        return getDao().findParameterqiyeByName(name);
    }
    
    @Override
    public List<Parameterqiye> findBynameAndType(String name,String type)
    {
        return getDao().findParameterqiyeByName(name, type);
    }

    @Override
    public List<Parameterqiye> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Parameterqiye mode)
    {
        parameterqiyeDao.merge(mode);
    }
    public List<Parameterqiye> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Parameterqiye> findParameterqiyeByType(String type) {
		// TODO Auto-generated method stub
		  return getDao().findParameterqiyeByType(type);
	}

	@Override
	public List<Parameterqiye> findParameterqiyeByTypeAchik(String type,
			String chicunk) {
		return parameterqiyeDao.findParameterqiyeByNamechick(type, chicunk);
	}
	
	@Override
	public List<Parameterqiye> findParameterqiyeByTypeAchigao(String type,
			String chichun) {
		return parameterqiyeDao.findParameterqiyeByNamechickgao(type, chichun);
	}
}
