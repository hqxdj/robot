package com.xdj.robot.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 谢道吉
 * @date 2021/12/29 10:52
 * @Description
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends Page<User> {

    private Integer id;

    private String userName;

    private String sex;

    private String phone;
}
