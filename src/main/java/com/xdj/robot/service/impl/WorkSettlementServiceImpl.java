package com.xdj.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.WorkPriceDto;
import com.xdj.robot.dto.WorkSettlementDto;
import com.xdj.robot.model.WorkPrice;
import com.xdj.robot.model.WorkSettlement;
import com.xdj.robot.mapper.WorkSettlementMapper;
import com.xdj.robot.service.IWorkSettlementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 工时结算表 服务实现类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Service
public class WorkSettlementServiceImpl extends ServiceImpl<WorkSettlementMapper, WorkSettlement> implements IWorkSettlementService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkSettlement addWorkSettlement(WorkSettlementDto dto) {
        WorkSettlement workSettlement = new WorkSettlement();
        BeanUtils.copyProperties(dto,workSettlement);
        baseMapper.insert(workSettlement);
        return workSettlement;
    }

    @Override
    public WorkSettlement findWorkSettlement(WorkSettlementDto dto) {
        WorkSettlement workSettlement = new WorkSettlement();
        BeanUtils.copyProperties(dto,workSettlement);
        QueryWrapper<WorkSettlement> queryWrapper = new QueryWrapper<>(workSettlement);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkSettlement updateWorkSettlement(WorkSettlementDto dto) {
        WorkSettlement workSettlement = new WorkSettlement();
        WorkSettlement settlement = baseMapper.selectById(dto.getId());
        if (ObjectUtils.isEmpty(settlement)) {
            return new WorkSettlement();
        }
        BeanUtils.copyProperties(dto,settlement);
        baseMapper.updateById(settlement);
        return settlement;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWorkSettlement(WorkSettlementDto dto) {
        baseMapper.deleteById(dto.getId());
    }

    @Override
    public Page<WorkSettlement> pageUserWorkRecord(WorkSettlementDto dto) {
        WorkSettlement workSettlement = new WorkSettlement();
        BeanUtils.copyProperties(dto, workSettlement);
        QueryWrapper<WorkSettlement> wrapper = new QueryWrapper<>(workSettlement);
        return baseMapper.selectPage(dto, wrapper);
    }
}
