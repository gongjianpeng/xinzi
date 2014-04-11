package cn.internalaudit.audit.pojo.bo;

import java.util.List;
import java.util.Map;

import cn.internalaudit.audit.base.IBo;
import cn.internalaudit.audit.pojo.Cart;

public interface ICartBo extends IBo<Cart>
{
    public List<Cart> findCartById(Long id);
    public void update(Cart mode);
    public void delete(Cart mode);
    public List<Cart> findByParms(Map map);
    public List<Cart> findAll(); 
}
