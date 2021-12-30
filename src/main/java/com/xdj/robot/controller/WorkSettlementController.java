package com.xdj.robot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.WorkSettlementDto;
import com.xdj.robot.model.WorkSettlement;
import com.xdj.robot.service.IWorkSettlementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工时结算表 前端控制器
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/work-settlement")
public class WorkSettlementController {

    private final IWorkSettlementService iWorkSettlementService;

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PostMapping("/add-work-settlement")
    public WorkSettlement addWorkSettlement(WorkSettlementDto dto) {
        return iWorkSettlementService.addWorkSettlement(dto);
    }

    /**
     * 查询
     *
     * @param dto
     * @return
     */
    @PostMapping("/find-work-settlement")
    public WorkSettlement findWorkSettlement(WorkSettlementDto dto) {
        return iWorkSettlementService.findWorkSettlement(dto);
    }

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    @PostMapping("/update-work-settlement")
    public WorkSettlement updateWorkSettlement(WorkSettlementDto dto) {
        return iWorkSettlementService.updateWorkSettlement(dto);
    }

    /**
     * 删除
     *
     * @param dto
     */
    @PostMapping("/delete-work-settlement")
    public void deleteWorkSettlement(WorkSettlementDto dto) {
        iWorkSettlementService.deleteWorkSettlement(dto);
    }

    /**
     * page query
     *
     * @param dto
     * @return
     */
    @PostMapping("/page-work-settlement")
    public Page<WorkSettlement> pageWorkSettlement(WorkSettlementDto dto) {
        return iWorkSettlementService.pageUserWorkRecord(dto);
    }

}
