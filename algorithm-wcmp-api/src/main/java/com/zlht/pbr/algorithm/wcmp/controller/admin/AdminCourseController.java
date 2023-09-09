package com.zlht.pbr.algorithm.wcmp.controller.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "机构管理员-课程接口")
@RequestMapping("/wechat/{linkCode}/admin/course")
public class AdminCourseController extends BaseController {

    @Autowired
    private CourseServiceI courseServiceI;

    /**
     * 查询课程信息
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
    public Result<PageInfo<Course>> queryCourseList(@PathVariable String linkCode,
                                                    @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return courseServiceI.queryCourseList(linkCode, currentPage, pageSize);
    }

    /**
     * 创建课程
     *
     * @return Course
     */
    @ApiOperation(value = "创建课程", notes = "创建课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkCode", value = "小程序链接码", dataTypeClass = String.class)
    })
    @PostMapping(value = "/createCourse")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<Course> createCourse(@PathVariable String linkCode, @RequestBody Course course) {
        Map<String, Object> map = courseServiceI.createCourse(linkCode, course);
        return returnDataList(map);
    }

    /**
     * 更新课程
     *
     * @return Course
     */
    @ApiOperation(value = "更新课程", notes = "更新课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的课程ID", required = true, dataTypeClass = int.class),
    })
    @PutMapping(value = "/updateCourse")
    @ResponseStatus(HttpStatus.OK)
    public Result<Course> updateCourse(@RequestParam int id,
                                       @PathVariable String linkCode,
                                       @RequestBody Course course) {
        Map<String, Object> map = courseServiceI.updateCourse(linkCode, id, course);
        return returnDataList(map);
    }

    /**
     * 删除课程
     *
     * @return Course
     */
    @ApiOperation(value = "删除课程", notes = "删除课程")
    @DeleteMapping(value = "/deleteCourse")
    @ResponseStatus(HttpStatus.OK)
    public Result<Course> deleteCourse(@PathVariable String linkCode,
                                       @RequestParam int id) {
        Map<String, Object> map = courseServiceI.deleteCourse(linkCode, id);
        return returnDataList(map);
    }
}
