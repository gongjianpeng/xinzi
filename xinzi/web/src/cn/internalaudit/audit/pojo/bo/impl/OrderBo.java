package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.Order;
import cn.internalaudit.audit.pojo.bo.IOrderBo;
import cn.internalaudit.audit.pojo.dao.IOrderDao;
@Service
public class OrderBo extends Bo<Order, IOrderDao> implements IOrderBo
{ 
	@Resource
    private IOrderDao orderDao;

	@Override
	protected IOrderDao getDao() {
		return orderDao;
	}

	@Override
	public void delete(Order mode) {
		orderDao.remove(mode);
	}
	@Override
	public List<Order> findByParms(Map map) {
		return orderDao.findByParms(map);
	}

 
	@Override
	public void update(Order mode) {
		orderDao.merge(mode);
	}

	@Override
	public List<Order> findCartById(Long id) {
		 
		return orderDao.findCartById(id);
	}

	@Override
	public void add(Order mode) {
		
	}

	  
}
