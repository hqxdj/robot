package com.xdj.robot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.UserWorkRecordDto;
import com.xdj.robot.model.UserWorkRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 人员工时记录表 服务类
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
public interface IUserWorkRecordService extends IService<UserWorkRecord> {

    /**
     * 新增工时记录
     * @param dto
     * @return
     */
    UserWorkRecord addUserWorkRecord(UserWorkRecordDto dto);

    /**
     * 查询工时记录
     * @param dto
     * @return
     */
    UserWorkRecord findUserWorkRecord(UserWorkRecordDto dto);

    /**
     * 更新工时记录
     * @param dto
     * @return
     */
    UserWorkRecord updateUserWorkRecord(UserWorkRecordDto dto);

    /**
     * 删除工时记录
     * @param dto
     * @return
     */
    void deleteUserWorkRecord(UserWorkRecordDto dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    Page<UserWorkRecord> pageUserWorkRecord(UserWorkRecordDto dto);
}
