package pers.jhshop.discount.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.discount.model.req.FlashSalesCreateReq;
import pers.jhshop.discount.model.req.FlashSalesQueryReq;
import pers.jhshop.discount.model.req.FlashSalesUpdateReq;
import pers.jhshop.discount.model.vo.FlashSalesVO;
import pers.jhshop.discount.model.entity.FlashSales;
import pers.jhshop.discount.mapper.FlashSalesMapper;
import pers.jhshop.discount.service.IFlashSalesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.jhshop.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
 * <p>
 * 秒杀活动表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlashSalesServiceImpl extends ServiceImpl<FlashSalesMapper, FlashSales> implements IFlashSalesService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(FlashSalesCreateReq createReq) {


        FlashSales entity = new FlashSales();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(FlashSalesUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        FlashSales entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("秒杀活动表不存在");
        }

        // 2.重复性判断
        FlashSales entityToUpdate = new FlashSales();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public FlashSalesVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        FlashSales entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("秒杀活动表不存在");
        }

        FlashSalesVO vo = new FlashSalesVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<FlashSalesVO> pageBiz(FlashSalesQueryReq queryReq) {
        Page<FlashSales> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<FlashSales> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<FlashSalesVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<FlashSales> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<FlashSalesVO> vos = records.stream().map(record -> {
            FlashSalesVO vo = new FlashSalesVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<FlashSales> page(FlashSalesQueryReq queryReq) {
        Page<FlashSales> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<FlashSales> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<FlashSales> listByQueryReq(FlashSalesQueryReq queryReq) {
        LambdaQueryWrapper<FlashSales> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<FlashSales> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, FlashSales> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<FlashSales> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(FlashSales::getId, ids);
        List<FlashSales> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(FlashSales::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public FlashSales getOneByQueryReq(FlashSalesQueryReq queryReq) {
        LambdaQueryWrapper<FlashSales> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<FlashSales> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<FlashSales> getLambdaQueryWrapper(FlashSalesQueryReq queryReq) {
        LambdaQueryWrapper<FlashSales> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), FlashSales::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getProductId()), FlashSales::getProductId, queryReq.getProductId());
        queryWrapper.eq(Objects.nonNull(queryReq.getDiscountPrice()), FlashSales::getDiscountPrice, queryReq.getDiscountPrice());
        queryWrapper.eq(Objects.nonNull(queryReq.getStartTime()), FlashSales::getStartTime, queryReq.getStartTime());
        queryWrapper.eq(Objects.nonNull(queryReq.getEndTime()), FlashSales::getEndTime, queryReq.getEndTime());
        queryWrapper.eq(Objects.nonNull(queryReq.getStock()), FlashSales::getStock, queryReq.getStock());
        queryWrapper.eq(Objects.nonNull(queryReq.getStatus()), FlashSales::getStatus, queryReq.getStatus());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), FlashSales::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), FlashSales::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), FlashSales::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
