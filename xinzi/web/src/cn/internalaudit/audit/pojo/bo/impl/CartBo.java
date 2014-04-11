package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.Cart;
import cn.internalaudit.audit.pojo.bo.ICartBo;
import cn.internalaudit.audit.pojo.dao.ICartDao;
@Service
public class CartBo extends Bo<Cart, ICartDao> implements ICartBo
{ 
	@Resource
    private ICartDao cartDao;

	@Override
	protected ICartDao getDao() {
		return cartDao;
	}

	@Override
	public void delete(Cart mode) {
		cartDao.remove(mode);
	}
	@Override
	public List<Cart> findByParms(Map map) {
		return cartDao.findByParms(map);
	}

 
	@Override
	public void update(Cart mode) {
		cartDao.merge(mode);
	}

	@Override
	public List<Cart> findCartById(Long id) {
		 
		return cartDao.findCartById(id);
	}	
}
