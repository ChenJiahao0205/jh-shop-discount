package pers.jhshop.discount.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.discount.model.req.CouponsCreateReq;
import pers.jhshop.discount.model.req.CouponsQueryReq;
import pers.jhshop.discount.model.req.CouponsUpdateReq;
import pers.jhshop.discount.model.vo.CouponsVO;
import pers.jhshop.discount.model.entity.Coupons;
import pers.jhshop.discount.mapper.CouponsMapper;
import pers.jhshop.discount.service.ICouponsService;
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
 * 优惠券表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponsServiceImpl extends ServiceImpl<CouponsMapper, Coupons> implements ICouponsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(CouponsCreateReq createReq) {
        if (Objects.isNull(createReq.getCode())) {
            throw new ServiceException("优惠券代码不能为空");
        }



        Coupons entity = new Coupons();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(CouponsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Coupons entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("优惠券表不存在");
        }

        // 2.重复性判断
        Coupons entityToUpdate = new Coupons();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public CouponsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Coupons entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("优惠券表不存在");
        }

        CouponsVO vo = new CouponsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<CouponsVO> pageBiz(CouponsQueryReq queryReq) {
        Page<Coupons> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Coupons> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<CouponsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Coupons> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<CouponsVO> vos = records.stream().map(record -> {
            CouponsVO vo = new CouponsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Coupons> page(CouponsQueryReq queryReq) {
        Page<Coupons> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Coupons> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Coupons> listByQueryReq(CouponsQueryReq queryReq) {
        LambdaQueryWrapper<Coupons> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Coupons> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Coupons> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Coupons> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Coupons::getId, ids);
        List<Coupons> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Coupons::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Coupons getOneByQueryReq(CouponsQueryReq queryReq) {
        LambdaQueryWrapper<Coupons> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Coupons> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Coupons> getLambdaQueryWrapper(CouponsQueryReq queryReq) {
        LambdaQueryWrapper<Coupons> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Coupons::getId, queryReq.getId());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getCode()), Coupons::getCode, queryReq.getCode());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getCodeLike()), Coupons::getCode, queryReq.getCodeLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getType()), Coupons::getType, queryReq.getType());
        queryWrapper.eq(Objects.nonNull(queryReq.getDiscount()), Coupons::getDiscount, queryReq.getDiscount());
        queryWrapper.eq(Objects.nonNull(queryReq.getMinOrder()), Coupons::getMinOrder, queryReq.getMinOrder());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFrom()), Coupons::getValidFrom, queryReq.getValidFrom());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidUntil()), Coupons::getValidUntil, queryReq.getValidUntil());
        queryWrapper.eq(Objects.nonNull(queryReq.getStatus()), Coupons::getStatus, queryReq.getStatus());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Coupons::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Coupons::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Coupons::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
