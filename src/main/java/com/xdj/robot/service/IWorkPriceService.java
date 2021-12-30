package com.xdj.robot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.WorkPriceDto;
import com.xdj.robot.model.WorkPrice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工时报价表 服务类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
public interface IWorkPriceService extends IService<WorkPrice> {
    /**
     * 新增工时报价
     *
     * @param dto
     * @return
     */
    WorkPrice addWorkPrice(WorkPriceDto dto);

    /**
     * 查询工时报价
     *
     * @param dto
     * @return
     */
    WorkPrice findWorkPrice(WorkPriceDto dto);

    /**
     * 更新工时报价
     *
     * @param dto
     * @return
     */
    WorkPrice updateWorkPrice(WorkPriceDto dto);

    /**
     * 删除工时报价
     *
     * @param dto
     * @return
     */
    void deleteWorkPrice(WorkPriceDto dto);

    /**
     * 分页查询报价
     * @param dto
     * @return
     */
    Page<WorkPrice> pageUserWorkRecord(WorkPriceDto dto);
}
