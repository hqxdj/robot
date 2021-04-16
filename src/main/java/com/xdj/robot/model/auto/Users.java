package com.xdj.robot.model.auto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xdj
 * @since 2021-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Users extends Model {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Boolean enabled;


}
