package com.xdj.robot.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.model.WorkPrice;
import lombok.Data;

/**
 * @author 谢道吉
 * @date 2021/12/30 9:45
 * @Description
 **/

@Data
public class WorkPriceDto extends Page<WorkPrice> {

    private Integer id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 包工头地址
     */
    private String address;

    /**
     * 工时单价
     */
    private String price;
}
