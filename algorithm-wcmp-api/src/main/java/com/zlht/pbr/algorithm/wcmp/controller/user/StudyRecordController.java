package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Course;
import com.zlht.pbr.algorithm.wcmp.dao.entity.StudyRecord;
import com.zlht.pbr.algorithm.wcmp.service.StudyRecordServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zijian Wang
 */
@RestController
@Api(tags = "学生课程接口")
@RequestMapping("/wechat/{linkCode}/user/studyRecord")
public class StudyRecordController extends BaseController {

    @Autowired
    private StudyRecordServiceI studyRecordServiceI;

    /**
     * 查询学生课程记录
     *
     * @return StudyRecord
     */
    @ApiOperation(value = "查询学生课程记录", notes = "查询学生课程记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataTypeClass = int.class)
    })

    @GetMapping(value = "/queryStudyRecordList")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Course>> queryStudyRecordList(@RequestParam int userId,
                                                         @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                         @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                         @PathVariable String linkCode) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return studyRecordServiceI.queryStudyRecord(linkCode, userId, currentPage, pageSize);
    }

    /**
     * 创建学生课程记录
     *
     * @return StudyRecord
     */
    @ApiOperation(value = "创建学生课程记录", notes = "创建学生课程记录")
    @PostMapping(value = "/createStudyRecord")
    @ResponseStatus(HttpStatus.OK)
    public Result createStudyRecord(@RequestBody StudyRecord studyRecord,
                                    @PathVariable(required = false) String linkCode) {
        Map<String, Object> map = studyRecordServiceI.createStudyRecord(studyRecord);
        return returnDataList(map);
    }
}
