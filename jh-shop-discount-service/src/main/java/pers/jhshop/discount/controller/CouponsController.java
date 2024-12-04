package pers.jhshop.discount.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.discount.consts.JhShopDiscountApiConstants;
import pers.jhshop.discount.model.req.CouponsCreateReq;
import pers.jhshop.discount.model.req.CouponsQueryReq;
import pers.jhshop.discount.model.req.CouponsUpdateReq;
import pers.jhshop.discount.model.vo.CouponsVO;
import pers.jhshop.discount.service.ICouponsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 优惠券表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@RestController
@RequestMapping(JhShopDiscountApiConstants.API_USER + "coupons")
@RequiredArgsConstructor
public class CouponsController {
    private final ICouponsService couponsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody CouponsCreateReq createReq) {
        couponsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody CouponsUpdateReq updateReq) {
        couponsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<CouponsVO> getById(Long id) {
        CouponsVO vo = couponsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<CouponsVO>> page(@RequestBody CouponsQueryReq queryReq) {
        Page page = couponsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

