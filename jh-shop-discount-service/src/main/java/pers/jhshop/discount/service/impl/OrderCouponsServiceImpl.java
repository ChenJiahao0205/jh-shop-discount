package pers.jhshop.discount.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.discount.model.req.OrderCouponsCreateReq;
import pers.jhshop.discount.model.req.OrderCouponsQueryReq;
import pers.jhshop.discount.model.req.OrderCouponsUpdateReq;
import pers.jhshop.discount.model.vo.OrderCouponsVO;
import pers.jhshop.discount.model.entity.OrderCoupons;
import pers.jhshop.discount.mapper.OrderCouponsMapper;
import pers.jhshop.discount.service.IOrderCouponsService;
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
 * 订单优惠应用表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCouponsServiceImpl extends ServiceImpl<OrderCouponsMapper, OrderCoupons> implements IOrderCouponsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(OrderCouponsCreateReq createReq) {


        OrderCoupons entity = new OrderCoupons();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(OrderCouponsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        OrderCoupons entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单优惠应用表不存在");
        }

        // 2.重复性判断
        OrderCoupons entityToUpdate = new OrderCoupons();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public OrderCouponsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        OrderCoupons entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("订单优惠应用表不存在");
        }

        OrderCouponsVO vo = new OrderCouponsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<OrderCouponsVO> pageBiz(OrderCouponsQueryReq queryReq) {
        Page<OrderCoupons> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<OrderCoupons> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<OrderCouponsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<OrderCoupons> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<OrderCouponsVO> vos = records.stream().map(record -> {
            OrderCouponsVO vo = new OrderCouponsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<OrderCoupons> page(OrderCouponsQueryReq queryReq) {
        Page<OrderCoupons> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<OrderCoupons> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<OrderCoupons> listByQueryReq(OrderCouponsQueryReq queryReq) {
        LambdaQueryWrapper<OrderCoupons> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<OrderCoupons> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, OrderCoupons> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<OrderCoupons> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(OrderCoupons::getId, ids);
        List<OrderCoupons> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(OrderCoupons::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public OrderCoupons getOneByQueryReq(OrderCouponsQueryReq queryReq) {
        LambdaQueryWrapper<OrderCoupons> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<OrderCoupons> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<OrderCoupons> getLambdaQueryWrapper(OrderCouponsQueryReq queryReq) {
        LambdaQueryWrapper<OrderCoupons> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), OrderCoupons::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getOrderId()), OrderCoupons::getOrderId, queryReq.getOrderId());
        queryWrapper.eq(Objects.nonNull(queryReq.getCouponId()), OrderCoupons::getCouponId, queryReq.getCouponId());
        queryWrapper.eq(Objects.nonNull(queryReq.getDiscount()), OrderCoupons::getDiscount, queryReq.getDiscount());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), OrderCoupons::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), OrderCoupons::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), OrderCoupons::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
