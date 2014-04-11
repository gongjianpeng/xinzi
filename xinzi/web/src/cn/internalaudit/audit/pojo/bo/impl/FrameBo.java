package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IBrandjxsBo;
import cn.internalaudit.audit.dao.IBrandjxsDao;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Frame;
import cn.internalaudit.audit.pojo.bo.IFrameBo;
import cn.internalaudit.audit.pojo.dao.IFrameDao;
@Service("FrameBo")
public class FrameBo extends Bo<Frame, IFrameDao> implements IFrameBo
{
    
    @Resource(name = "frameDao")
    private IFrameDao frameDao;
   
    @Override
    protected IFrameDao getDao()
    {
        return frameDao;
    }

    @Override
    public void add(Frame mode)
    {       
        frameDao.save(mode);
    }

    @Override
    public void delete(Frame mode)
    {
        frameDao.remove(mode);
    }

    @Override
    public List<Frame> findFrameName(String name)
    {
        return getDao().findFrameByName(name);
    }

    @Override
    public List<Frame> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Frame mode)
    {
    	frameDao.merge(mode);
    }
    public List<Frame> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Frame> findFrameByid(Long id) {
		// TODO Auto-generated method stub
		return frameDao.findFrameByid(id);
	}

}
