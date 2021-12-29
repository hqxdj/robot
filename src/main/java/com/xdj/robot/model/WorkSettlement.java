package com.xdj.robot.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 工时结算表
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkSettlement extends Model {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
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
