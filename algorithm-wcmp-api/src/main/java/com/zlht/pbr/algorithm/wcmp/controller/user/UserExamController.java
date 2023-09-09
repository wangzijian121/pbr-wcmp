package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
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
@RequestMapping("/wechat/user/exam")
@Api(tags = "学生考试接口")
public class UserExamController extends BaseController {


    @Autowired
    private ExamServiceI examServiceI;

    /**
     * 学生查询考试信息
     *
     * @return exam
     */
    @ApiOperation(value = "查询考试", notes = "查询考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "linkCode", value = "链接码", dataTypeClass = String.class)
    })

    @GetMapping(value = "/getExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Exam>> queryExamList(@RequestParam(required = false, defaultValue = "1") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String linkCode) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return examServiceI.queryExamList(linkCode,currentPage, pageSize);
    }

}
