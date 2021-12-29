package com.xdj.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdj.robot.dto.UserDto;
import com.xdj.robot.mapper.UserMapper;
import com.xdj.robot.model.User;
import com.xdj.robot.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    private final UserMapper userMapper;

    /**
     * 新增用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User addUser(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userMapper.insert(user);
        return user;
    }


    @Override
    public User findUser(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        Wrapper<User> queryWrapper = new QueryWrapper<>(user);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUser(UserDto dto) {
        Integer id = dto.getId();
        User user = baseMapper.selectById(id);
        BeanUtils.copyProperties(dto,user);
        baseMapper.updateById(user);
        return baseMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(UserDto userDto) {
        baseMapper.deleteById(userDto.getId());
    }
}
