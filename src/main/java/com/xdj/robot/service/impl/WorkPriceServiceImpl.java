package com.xdj.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.UserWorkRecordDto;
import com.xdj.robot.dto.WorkPriceDto;
import com.xdj.robot.model.UserWorkRecord;
import com.xdj.robot.model.WorkPrice;
import com.xdj.robot.mapper.WorkPriceMapper;
import com.xdj.robot.service.IWorkPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 工时报价表 服务实现类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Service
public class WorkPriceServiceImpl extends ServiceImpl<WorkPriceMapper, WorkPrice> implements IWorkPriceService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkPrice addWorkPrice(WorkPriceDto dto) {
        WorkPrice workPrice = new WorkPrice();
        BeanUtils.copyProperties(dto,workPrice);
        baseMapper.insert(workPrice);
        return workPrice;
    }

    @Override
    public WorkPrice findWorkPrice(WorkPriceDto dto) {
        WorkPrice workPrice = new WorkPrice();
        BeanUtils.copyProperties(dto,workPrice);
        QueryWrapper<WorkPrice> queryWrapper = new QueryWrapper<>(workPrice);
       return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkPrice updateWorkPrice(WorkPriceDto dto) {
        WorkPrice workPrice = new WorkPrice();
        WorkPrice price = baseMapper.selectById(dto.getId());
        if (ObjectUtils.isEmpty(price)) {
            return new WorkPrice();
        }
        BeanUtils.copyProperties(dto,price);
        baseMapper.updateById(price);
        return price;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWorkPrice(WorkPriceDto dto) {
        baseMapper.deleteById(dto.getId());
    }

    @Override
    public Page<WorkPrice> pageUserWorkRecord(WorkPriceDto dto) {
        WorkPrice workPrice = new WorkPrice();
        BeanUtils.copyProperties(dto, workPrice);
        QueryWrapper<WorkPrice> wrapper = new QueryWrapper<>(workPrice);
        return baseMapper.selectPage(dto, wrapper);
    }

}
