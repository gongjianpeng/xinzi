package cn.internalaudit.audit.pojo.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.internalaudit.audit.base.Bo;
import cn.internalaudit.audit.pojo.OrderDetail;
import cn.internalaudit.audit.pojo.bo.IOrderDetailBo;
import cn.internalaudit.audit.pojo.dao.IOrderDetailDao;
@Service
public class OrderDetailBo extends Bo<OrderDetail, IOrderDetailDao> implements IOrderDetailBo
{ 
	@Resource
    private IOrderDetailDao orderDetailDao;

	@Override
	protected IOrderDetailDao getDao() {
		return orderDetailDao;
	}

	@Override
	public void delete(OrderDetail mode) {
		orderDetailDao.remove(mode);
	}
	@Override
	public List<OrderDetail> findByParms(Map map ,String orgid) {
		return orderDetailDao.findByParms(map,orgid);
	}

	@Override
	public List<OrderDetail> findByParmsandorg(Map map,String org) {
		return orderDetailDao.findByParmsandorg(map, org);
	}
 
	@Override
	public void update(OrderDetail mode) {
		orderDetailDao.merge(mode);
	}

	@Override
	public List<OrderDetail> findCartById(Long id) {
		 
		return orderDetailDao.findCartById(id);
	}

	@Override
	public void add(OrderDetail mode) {
		orderDetailDao.add(mode);
	}

	@Override
	public List<OrderDetail> findByParms(Map map) {
		// TODO Auto-generated method stub
		return orderDetailDao.findByParms(map);
	}

	  
}
