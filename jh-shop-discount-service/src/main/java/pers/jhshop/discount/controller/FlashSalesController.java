package pers.jhshop.discount.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.discount.consts.JhShopDiscountApiConstants;
import pers.jhshop.discount.model.req.FlashSalesCreateReq;
import pers.jhshop.discount.model.req.FlashSalesQueryReq;
import pers.jhshop.discount.model.req.FlashSalesUpdateReq;
import pers.jhshop.discount.model.vo.FlashSalesVO;
import pers.jhshop.discount.service.IFlashSalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 秒杀活动表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@RestController
@RequestMapping(JhShopDiscountApiConstants.API_USER + "flashSales")
@RequiredArgsConstructor
public class FlashSalesController {
    private final IFlashSalesService flashSalesService;

    @PostMapping("create")
    public ResultBo create(@RequestBody FlashSalesCreateReq createReq) {
        flashSalesService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody FlashSalesUpdateReq updateReq) {
        flashSalesService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<FlashSalesVO> getById(Long id) {
        FlashSalesVO vo = flashSalesService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<FlashSalesVO>> page(@RequestBody FlashSalesQueryReq queryReq) {
        Page page = flashSalesService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

