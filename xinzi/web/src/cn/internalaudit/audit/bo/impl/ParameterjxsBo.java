package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IParameterjxsBo;
import cn.internalaudit.audit.dao.IParameterjxsDao;
import cn.internalaudit.audit.dao.impl.ParameterjxsDao;
import cn.internalaudit.audit.entitys.Parameterjxs;
@Service("ParameterjxsBo")
public class ParameterjxsBo extends Bo<Parameterjxs, IParameterjxsDao> implements IParameterjxsBo
{
    @Resource(name = "parameterjxsDao")
    private IParameterjxsDao parameterjxsDao;
    @Override
    protected IParameterjxsDao getDao()
    {
        return parameterjxsDao;
    }

    @Override
    public void add(Parameterjxs mode)
    {       
        parameterjxsDao.save(mode);
    }

    @Override
    public void delete(Parameterjxs mode)
    {
        parameterjxsDao.remove(mode);
    }

    @Override
    public List<Parameterjxs> findParameterjxsByName(String name)
    {
        return getDao().findParameterjxsByName(name);
    }

    @Override
    public List<Parameterjxs> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Parameterjxs mode)
    {
        parameterjxsDao.merge(mode);
    }
    public List<Parameterjxs> findAll(){
        return getDao().findAll();
    }
}
