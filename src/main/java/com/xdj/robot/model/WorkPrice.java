package com.xdj.robot.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 工时报价表
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkPrice extends Model {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
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
