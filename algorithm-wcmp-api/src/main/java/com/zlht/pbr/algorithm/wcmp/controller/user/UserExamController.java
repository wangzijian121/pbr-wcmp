package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.dao.entity.ExamRecord;
import com.zlht.pbr.algorithm.wcmp.model.Question;
import com.zlht.pbr.algorithm.wcmp.service.ExamRecordServiceI;
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

import java.util.List;

/**
 * @author zijian Wang
 */
@RestController
@RequestMapping("/wechat/{linkCode}/user/exam")
@Api(tags = "学生考试接口")
public class UserExamController extends BaseController {


    @Autowired
    private ExamServiceI examServiceI;

    @Autowired
    private ExamRecordServiceI examRecordServiceI;

    /**
     * 学生查询考试信息
     *
     * @return exam
     */
    @ApiOperation(value = "查询考试", notes = "查询考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class)
    })

    @GetMapping(value = "/getExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Exam>> queryExamList(@RequestParam(required = false, defaultValue = "1") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @PathVariable(required = false) String linkCode) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return examServiceI.queryExamList(linkCode, currentPage, pageSize);
    }

    /**
     * 查询学生成绩
     *
     * @return exam
     */
    @ApiOperation(value = "查询学生成绩", notes = "查询学生成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examId", value = "考试ID", dataTypeClass = int.class),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataTypeClass = int.class)
    })

    @GetMapping(value = "/queryExamScore")
    @ResponseStatus(HttpStatus.OK)
    public Result<Exam> queryExamScore(@RequestParam int examId,
                                   @RequestParam int userId,
                                   @PathVariable String linkCode) {

        return returnDataList(examRecordServiceI.queryExamScore(linkCode, userId, examId));
    }

    /**
     * 提交考试信息
     *
     * @return exam
     */
    @ApiOperation(value = "提交考试信息", notes = "提交考试信息")
    @PostMapping(value = "/commitExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<ExamRecord> commitExam(@RequestBody ExamRecord examRecord,
                                             @PathVariable String linkCode) {

        return returnDataList(examRecordServiceI.commitExam(linkCode, examRecord));
    }

    @ApiOperation(value = "查看考试内容", notes = "查看考试内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "resourceId", dataTypeClass = String.class)
    })
    @GetMapping(value = "/queryExamContent")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<Question>> queryExamContent(@RequestParam int  resourceId) {

        return examServiceI.queryExamContent(resourceId);
    }


}
