package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.OrderDetail;

public interface IOrderDetailBo extends IBo<OrderDetail>
{
    public List<OrderDetail> findCartById(Long id);
    public void add(OrderDetail mode);
    public void update(OrderDetail mode);
    public void delete(OrderDetail mode);
    public List<OrderDetail> findByParms(Map map);
    public List<OrderDetail> findAll();
	List<OrderDetail> findByParmsandorg(Map map, String org);
	List<OrderDetail> findByParms(Map map, String orgid); 
}
