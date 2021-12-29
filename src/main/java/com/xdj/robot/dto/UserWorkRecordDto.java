package com.xdj.robot.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 谢道吉
 * @date 2021/12/29 16:56
 * @Description
 **/

@Data
public class UserWorkRecordDto {

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
