package com.xdj.robot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdj.robot.dto.UserWorkRecordDto;
import com.xdj.robot.model.UserWorkRecord;
import com.xdj.robot.service.IUserWorkRecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 人员工时记录表 前端控制器
 * </p>
 *
 * @author xdj
 * @since 2021-12-29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user-work-record")
public class UserWorkRecordController {

    private final IUserWorkRecordService iUserWorkRecordService;

    /**
     * 新增工时记录
     *
     * @param dto
     * @return
     */
    @PostMapping("/add-user-work-record")
    public UserWorkRecord addUserWorkRecord(@RequestBody UserWorkRecordDto dto) {
        return iUserWorkRecordService.addUserWorkRecord(dto);
    }

    /**
     * 查询工时记录
     *
     * @param dto
     * @return
     */
    @PostMapping("/find-user-work-record")
    public UserWorkRecord findUserWorkRecord(@RequestBody UserWorkRecordDto dto) {
        return iUserWorkRecordService.findUserWorkRecord(dto);
    }

    /**
     * 更新工时记录
     *
     * @param dto
     * @return
     */
    @PostMapping("/update-user-work-record")
    public UserWorkRecord updateUserWorkRecord(@RequestBody UserWorkRecordDto dto) {
        return iUserWorkRecordService.updateUserWorkRecord(dto);
    }

    /**
     * 删除工时记录
     *
     * @param dto
     * @return
     */
    @PostMapping("/delete-user-work-record")
    public void deleteUserWorkRecord(@RequestBody UserWorkRecordDto dto) {
        iUserWorkRecordService.deleteUserWorkRecord(dto);
    }

    /**
     * 分页查询工时记录
     *
     * @param dto
     * @return
     */
    @PostMapping("/page-user-work-record")
    public Page<UserWorkRecord> pageUserWorkRecord(@RequestBody UserWorkRecordDto dto) {
        return iUserWorkRecordService.pageUserWorkRecord(dto);
    }
}
