package com.xdj.robot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.WorkSettlementDto;
import com.xdj.robot.model.WorkSettlement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工时结算表 服务类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
public interface IWorkSettlementService extends IService<WorkSettlement> {
    /**
     * 添加工时结算记录
     * @param dto
     * @return
     */
    WorkSettlement addWorkSettlement(WorkSettlementDto dto);

    /**
     * 查询
     * @param dto
     * @return
     */
    WorkSettlement findWorkSettlement(WorkSettlementDto dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    WorkSettlement updateWorkSettlement(WorkSettlementDto dto);

    /**
     * 删除
     * @param dto
     */
    void deleteWorkSettlement(WorkSettlementDto dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    Page<WorkSettlement> pageUserWorkRecord(WorkSettlementDto dto);
}
