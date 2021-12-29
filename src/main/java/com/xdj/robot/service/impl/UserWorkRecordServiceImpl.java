package com.xdj.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xdj.robot.dto.UserWorkRecordDto;
import com.xdj.robot.model.UserWorkRecord;
import com.xdj.robot.mapper.UserWorkRecordMapper;
import com.xdj.robot.service.IUserWorkRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdj.robot.util.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 人员工时记录表 服务实现类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@Service
public class UserWorkRecordServiceImpl extends ServiceImpl<UserWorkRecordMapper, UserWorkRecord> implements IUserWorkRecordService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserWorkRecord addUserWorkRecord(UserWorkRecordDto dto) {
        UserWorkRecord userWorkRecord = new UserWorkRecord();
        BeanUtils.copyProperties(dto, userWorkRecord);
        baseMapper.insert(userWorkRecord);
        return userWorkRecord;
    }

    @Override
    public UserWorkRecord findUserWorkRecord(UserWorkRecordDto dto) {
        UserWorkRecord userWorkRecord = new UserWorkRecord();
        BeanUtils.copyProperties(dto,userWorkRecord);
        QueryWrapper<UserWorkRecord> wrapper = new QueryWrapper<>(userWorkRecord);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public UserWorkRecord updateUserWorkRecord(UserWorkRecordDto dto) {
        Integer id = dto.getId();
        UserWorkRecord userWorkRecord = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(userWorkRecord)) {
            return new UserWorkRecord();
        }
        BeanUtils.copyProperties(dto,userWorkRecord);
        baseMapper.updateById(userWorkRecord);
        return baseMapper.selectById(id);
    }

    @Override
    public void deleteUserWorkRecord(UserWorkRecordDto dto) {
        baseMapper.deleteById(dto.getId());
    }
}
