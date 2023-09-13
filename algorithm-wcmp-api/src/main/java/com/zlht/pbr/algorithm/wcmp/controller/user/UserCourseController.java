package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Course;
import com.zlht.pbr.algorithm.wcmp.service.CourseServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zijian Wang
 */
@RestController
@RequestMapping("/wechat/{linkCode}/user/course")
@Api(tags = "学生课程接口")
public class UserCourseController extends BaseController {


    @Autowired
    private CourseServiceI courseServiceI;

    /**
     * 学生查询课程信息
     *
     * @return course
     */
    @ApiOperation(value = "查询课程", notes = "查询课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class)
    })

    @GetMapping(value = "/getCourse")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Course>> queryCourseList(@RequestParam(required = false, defaultValue = "1") int currentPage,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                    @PathVariable(required = false) String linkCode) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return courseServiceI.queryCourseList(linkCode, currentPage, pageSize);
    }
}
