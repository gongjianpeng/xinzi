package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IBrandjxsBo;
import cn.internalaudit.audit.dao.IBrandjxsDao;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Elses;
import cn.internalaudit.audit.pojo.bo.IElsesBo;
import cn.internalaudit.audit.pojo.dao.IElsesDao;
@Service("ElsesBo")
public class ElsesBo extends Bo<Elses, IElsesDao> implements IElsesBo
{
    
    @Resource(name = "elsesDao")
    private IElsesDao elsesDao;
   
    @Override
    protected IElsesDao getDao()
    {
        return elsesDao;
    }

    @Override
    public void add(Elses mode)
    {       
        elsesDao.save(mode);
    }

    @Override
    public void delete(Elses mode)
    {
        elsesDao.remove(mode);
    }

    @Override
    public List<Elses> findElsesByName(String name)
    {
        return getDao().findElsesByName(name);
    }

    @Override
    public List<Elses> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Elses mode)
    {
        elsesDao.merge(mode);
    }
    public List<Elses> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Elses> findElsesByid(Long id) {
		// TODO Auto-generated method stub
		return elsesDao.findElsesByid(id);
	}

}
