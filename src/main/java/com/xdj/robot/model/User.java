package com.xdj.robot.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String sex;

    private String phone;


}
