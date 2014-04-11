package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.Order;

public interface IOrderBo extends IBo<Order>
{
    public List<Order> findCartById(Long id);
    public void add(Order mode);
    public void update(Order mode);
    public void delete(Order mode);
    public List<Order> findByParms(Map map);
    public List<Order> findAll(); 
}
