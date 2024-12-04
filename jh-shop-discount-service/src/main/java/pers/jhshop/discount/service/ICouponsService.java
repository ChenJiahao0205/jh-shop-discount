package pers.jhshop.discount.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.discount.model.entity.Coupons;
import pers.jhshop.discount.model.req.CouponsCreateReq;
import pers.jhshop.discount.model.req.CouponsQueryReq;
import pers.jhshop.discount.model.req.CouponsUpdateReq;
import pers.jhshop.discount.model.vo.CouponsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
public interface ICouponsService extends IService<Coupons> {

    void createBiz(CouponsCreateReq createReq);

    void updateBiz(CouponsUpdateReq updateReq);

    CouponsVO getByIdBiz(Long id);

    Page<CouponsVO> pageBiz(CouponsQueryReq queryReq);

    Page<Coupons> page(CouponsQueryReq queryReq);

    List<Coupons> listByQueryReq(CouponsQueryReq queryReq);

    Map<Long, Coupons> getIdEntityMap(List<Long> ids);

    Coupons getOneByQueryReq(CouponsQueryReq queryReq);

}
