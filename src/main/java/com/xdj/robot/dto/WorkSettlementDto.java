package com.xdj.robot.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.model.WorkSettlement;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author 谢道吉
 * @date 2021/12/30 9:46
 * @Description
 **/

@Data
public class WorkSettlementDto extends Page<WorkSettlement> {

    private Integer id;

    /**
     * 被结算人
     */
    private String userName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 结算日期
     */
    private LocalDate settlementDate;

    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;

    /**
     * 结算银行
     */
    private String settlementBank;

    /**
     * 结算卡号
     */
    private String settlementNo;

    /**
     * 结算人（包工头）
     */
    private String settlementUser;

    /**
     * 结算人电话
     */
    private String settlementPhone;

}
