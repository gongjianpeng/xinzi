package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.bo.IBrandjxsBo;
import cn.internalaudit.audit.dao.IBrandjxsDao;
import cn.internalaudit.audit.entitys.Brandjxs;
import cn.internalaudit.audit.pojo.Palette;
import cn.internalaudit.audit.pojo.bo.IPaletteBo;
import cn.internalaudit.audit.pojo.dao.IPaletteDao;
@Service("PaletteBo")
public class PaletteBo extends Bo<Palette, IPaletteDao> implements IPaletteBo
{
    
    @Resource(name = "paletteDao")
    private IPaletteDao paletteDao;
   
    @Override
    protected IPaletteDao getDao()
    {
        return paletteDao;
    }

    @Override
    public void add(Palette mode)
    {       
        paletteDao.save(mode);
    }

    @Override
    public void delete(Palette mode)
    {
        paletteDao.remove(mode);
    }

    @Override
    public List<Palette> findPaletteByName(String name)
    {
        return getDao().findPaletteByName(name);
    }

    @Override
    public List<Palette> findByParms(Map map)
    {
        return getDao().findByParms(map);
    }

    @Override
    public void update(Palette mode)
    {
        paletteDao.merge(mode);
    }
    public List<Palette> findAll(){
        return getDao().findAll();
    }

	@Override
	public List<Palette> findPaletteByid(Long id) {
		// TODO Auto-generated method stub
		return paletteDao.findPaletteByid(id);
	}

}
