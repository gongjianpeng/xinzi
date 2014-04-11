package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.Plicalbum;
import cn.internalaudit.audit.pojo.bo.IPlicalbumBo;
import cn.internalaudit.audit.pojo.dao.IPlicalbumDao;
@Service
public class PlicalbumBo extends Bo<Plicalbum, IPlicalbumDao> implements IPlicalbumBo
{
    
    @Resource
    private IPlicalbumDao plicalbumDao;
   
    @Override
    protected IPlicalbumDao getDao()
    {
        return plicalbumDao;
    }

    @Override
    public void add(Plicalbum mode)
    {       
    	plicalbumDao.save(mode);
    }

    @Override
    public void delete(Plicalbum mode)
    {
    	plicalbumDao.remove(mode);
    }

//    @Override
//    public List<Plicalbum> findPlicalbumName(String name)
//    {
//        return getDao().findPlicalbumByName(name);
//    }

    @Override
    public List<Plicalbum> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Plicalbum mode)
    {
    	plicalbumDao.merge(mode);
    }
    public List<Plicalbum> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Plicalbum> findPlicAlbumByid(Long id) {
		// TODO Auto-generated method stub
		return plicalbumDao.findPlicalbumByid(id);
	}

	@Override
	public List<Plicalbum> findPlicAlbumName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
