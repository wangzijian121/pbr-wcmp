package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Course;
import com.zlht.pbr.algorithm.wcmp.dao.entity.StudyData;
import com.zlht.pbr.algorithm.wcmp.service.StudyDataServiceI;
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
@Api(tags = "学生学习")
@RequestMapping("/wechat/{linkCode}/admin/studyData")
public class StudyDataController extends BaseController {

    @Autowired
    private StudyDataServiceI studyDataServiceI;

    /**
     * 获取学生学习数据
     *
     * @return StudyData
     */
    @ApiOperation(value = "获取学生学习数据", notes = "获取学生学习数据")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "userId", value = "用户ID", dataTypeClass = int.class)
    })

    @GetMapping(value = "/getUserStudyDataByUserId")
    @ResponseStatus(HttpStatus.OK)
    public Result getUserStudyDataByUserId(@RequestParam int userId,
                                           @PathVariable String linkCode) {
        return studyDataServiceI.getUserStudyDataByUserId(linkCode, userId);
    }
}
