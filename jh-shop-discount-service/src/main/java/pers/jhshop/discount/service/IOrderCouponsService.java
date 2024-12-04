package pers.jhshop.discount.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.discount.model.entity.OrderCoupons;
import pers.jhshop.discount.model.req.OrderCouponsCreateReq;
import pers.jhshop.discount.model.req.OrderCouponsQueryReq;
import pers.jhshop.discount.model.req.OrderCouponsUpdateReq;
import pers.jhshop.discount.model.vo.OrderCouponsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单优惠应用表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
public interface IOrderCouponsService extends IService<OrderCoupons> {

    void createBiz(OrderCouponsCreateReq createReq);

    void updateBiz(OrderCouponsUpdateReq updateReq);

    OrderCouponsVO getByIdBiz(Long id);

    Page<OrderCouponsVO> pageBiz(OrderCouponsQueryReq queryReq);

    Page<OrderCoupons> page(OrderCouponsQueryReq queryReq);

    List<OrderCoupons> listByQueryReq(OrderCouponsQueryReq queryReq);

    Map<Long, OrderCoupons> getIdEntityMap(List<Long> ids);

    OrderCoupons getOneByQueryReq(OrderCouponsQueryReq queryReq);

}
