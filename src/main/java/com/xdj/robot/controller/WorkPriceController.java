package com.xdj.robot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.UserWorkRecordDto;
import com.xdj.robot.dto.WorkPriceDto;
import com.xdj.robot.model.UserWorkRecord;
import com.xdj.robot.model.WorkPrice;
import com.xdj.robot.service.IWorkPriceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工时报价表 前端控制器
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/work-price")
public class WorkPriceController {

    private final IWorkPriceService iWorkPriceService;

    /**
     * 新增工时报价
     *
     * @param dto
     * @return
     */
    @PostMapping("/add-work-price")
    public WorkPrice addUserWorkRecord(@RequestBody WorkPriceDto dto) {
        return iWorkPriceService.addWorkPrice(dto);
    }

    /**
     * 查询工时报价
     *
     * @param dto
     * @return
     */
    @PostMapping("/find-work-price")
    public WorkPrice findUserWorkRecord(@RequestBody WorkPriceDto dto) {
        return iWorkPriceService.findWorkPrice(dto);
    }

    /**
     * 更新工时报价
     *
     * @param dto
     * @return
     */
    @PostMapping("/update-work-price")
    public WorkPrice updateUserWorkRecord(@RequestBody WorkPriceDto dto) {
        return iWorkPriceService.updateWorkPrice(dto);
    }

    /**
     * 删除工时报价
     *
     * @param dto
     * @return
     */
    @PostMapping("/delete-work-price")
    public void deleteUserWorkRecord(@RequestBody WorkPriceDto dto) {
        iWorkPriceService.deleteWorkPrice(dto);
    }


    /**
     * 删除工时报价
     *
     * @param dto
     * @return
     */
    @PostMapping("/page-work-price")
    public Page<WorkPrice> pageUserWorkRecord(@RequestBody WorkPriceDto dto) {
        return iWorkPriceService.pageUserWorkRecord(dto);
    }

}
