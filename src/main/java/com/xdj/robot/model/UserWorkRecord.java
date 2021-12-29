package com.xdj.robot.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 人员工时记录表
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserWorkRecord extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * '姓名'
     */
    private String userName;

    /**
     * '电话'
     */
    private String phone;

    /**
     * '工作日期'
     */
    private Date workDate;

    /**
     * '地点'
     */
    private String address;

    /**
     * '工作时长'
     */
    private String workTime;

    /**
     * '加班时长'
     */
    private String outWorkTime;


}
