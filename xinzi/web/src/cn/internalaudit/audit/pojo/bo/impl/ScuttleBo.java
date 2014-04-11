package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IBrandjxsBo;
import cn.internalaudit.audit.dao.IBrandjxsDao;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Scuttle;
import cn.internalaudit.audit.pojo.bo.IScuttleBo;
import cn.internalaudit.audit.pojo.dao.IScuttleDao;
@Service("ScuttleBo")
public class ScuttleBo extends Bo<Scuttle, IScuttleDao> implements IScuttleBo
{
    
    @Resource(name = "scuttleDao")
    private IScuttleDao scuttleDao;
   
    @Override
    protected IScuttleDao getDao()
    {
        return scuttleDao;
    }

    @Override
    public void add(Scuttle mode)
    {       
    	scuttleDao.save(mode);
    }

    @Override
    public void delete(Scuttle mode)
    {
    	scuttleDao.remove(mode);
    }

    @Override
    public List<Scuttle> findScuttleByName(String name)
    {
        return getDao().findScuttleByName(name);
    }

    @Override
    public List<Scuttle> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Scuttle mode)
    {
    	scuttleDao.merge(mode);
    }
    public List<Scuttle> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Scuttle> findScuttleByid(Long id) {
		// TODO Auto-generated method stub
		return scuttleDao.findScuttleByid(id);
	}

}
