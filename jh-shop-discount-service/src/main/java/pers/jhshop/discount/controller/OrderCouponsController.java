package pers.jhshop.discount.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.discount.consts.JhShopDiscountApiConstants;
import pers.jhshop.discount.model.req.OrderCouponsCreateReq;
import pers.jhshop.discount.model.req.OrderCouponsQueryReq;
import pers.jhshop.discount.model.req.OrderCouponsUpdateReq;
import pers.jhshop.discount.model.vo.OrderCouponsVO;
import pers.jhshop.discount.service.IOrderCouponsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 订单优惠应用表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@RestController
@RequestMapping(JhShopDiscountApiConstants.API_USER + "orderCoupons")
@RequiredArgsConstructor
public class OrderCouponsController {
    private final IOrderCouponsService orderCouponsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody OrderCouponsCreateReq createReq) {
        orderCouponsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody OrderCouponsUpdateReq updateReq) {
        orderCouponsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<OrderCouponsVO> getById(Long id) {
        OrderCouponsVO vo = orderCouponsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<OrderCouponsVO>> page(@RequestBody OrderCouponsQueryReq queryReq) {
        Page page = orderCouponsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

