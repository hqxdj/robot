package com.xdj.robot.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.model.UserWorkRecord;
import lombok.Data;

import java.util.Date;

/**
 * @author 谢道吉
 * @date 2021/12/29 16:56
 * @Description
 **/

@Data
public class UserWorkRecordDto extends Page<UserWorkRecord> {

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
