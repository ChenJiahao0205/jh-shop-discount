package pers.jhshop.discount.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.discount.model.entity.FlashSales;
import pers.jhshop.discount.model.req.FlashSalesCreateReq;
import pers.jhshop.discount.model.req.FlashSalesQueryReq;
import pers.jhshop.discount.model.req.FlashSalesUpdateReq;
import pers.jhshop.discount.model.vo.FlashSalesVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 秒杀活动表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
public interface IFlashSalesService extends IService<FlashSales> {

    void createBiz(FlashSalesCreateReq createReq);

    void updateBiz(FlashSalesUpdateReq updateReq);

    FlashSalesVO getByIdBiz(Long id);

    Page<FlashSalesVO> pageBiz(FlashSalesQueryReq queryReq);

    Page<FlashSales> page(FlashSalesQueryReq queryReq);

    List<FlashSales> listByQueryReq(FlashSalesQueryReq queryReq);

    Map<Long, FlashSales> getIdEntityMap(List<Long> ids);

    FlashSales getOneByQueryReq(FlashSalesQueryReq queryReq);

}
