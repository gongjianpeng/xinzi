package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.Doorstyle;
import cn.internalaudit.audit.pojo.bo.IDoorstyleBo;
import cn.internalaudit.audit.pojo.dao.IDoorstyleDao;
@Service("DoorstyleBo")
public class DoorstyleBo extends Bo<Doorstyle, IDoorstyleDao> implements IDoorstyleBo
{
    
    @Resource(name = "doorstyleDao")
    private IDoorstyleDao doorstyleDao;
   
    @Override
    protected IDoorstyleDao getDao()
    {
        return doorstyleDao;
    }

    @Override
    public void add(Doorstyle mode)
    {       
    	doorstyleDao.save(mode);
    }

    @Override
    public void delete(Doorstyle mode)
    {
        doorstyleDao.remove(mode);
    }

    @Override
    public List<Doorstyle> findDoorstyleByName(String name)
    {
        return getDao().findDoorstyleByName(name);
    }

    @Override
    public List<Doorstyle> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Doorstyle mode)
    {
        doorstyleDao.merge(mode);
    }
    public List<Doorstyle> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Doorstyle> findDoorstyleByid(Long id) {
		// TODO Auto-generated method stub
		return doorstyleDao.findDoorstyleByid(id);
	}

}
