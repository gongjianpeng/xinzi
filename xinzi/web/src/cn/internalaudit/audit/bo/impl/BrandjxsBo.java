package cn.internalaudit.audit.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IBrandjxsBo;
import cn.internalaudit.audit.dao.IBrandjxsDao;
import cn.internalaudit.audit.entitys.Brandjxs;
@Service("BrandjxsBo")
public class BrandjxsBo extends Bo<Brandjxs, IBrandjxsDao> implements IBrandjxsBo
{
    
    @Resource(name = "brandjxsDao")
    private IBrandjxsDao brandjxsDao;
   
    @Override
    protected IBrandjxsDao getDao()
    {
        return brandjxsDao;
    }

    @Override
    public void add(Brandjxs mode)
    {       
        brandjxsDao.save(mode);
    }

    @Override
    public void delete(Brandjxs mode)
    {
        brandjxsDao.remove(mode);
    }

    @Override
    public List<Brandjxs> findBrandjxsByName(String name)
    {
        return getDao().findBrandjxsByName(name);
    }

    @Override
    public List<Brandjxs> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Brandjxs mode)
    {
        brandjxsDao.merge(mode);
    }
    public List<Brandjxs> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Brandjxs> findBrandByid(Long id) {
		// TODO Auto-generated method stub
		return brandjxsDao.findbrandByid(id);
	}

}
