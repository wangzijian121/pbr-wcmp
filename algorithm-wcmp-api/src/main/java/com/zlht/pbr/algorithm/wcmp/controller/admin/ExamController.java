package com.zlht.pbr.algorithm.wcmp.controller.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "考试接口")
@RequestMapping("/wechat/exam")
public class ExamController extends BaseController {

    @Autowired
    private ExamServiceI examServiceI;

    @ApiOperation(value = "下载试题模板")
    @GetMapping("/download")
    public ResponseEntity download() {
        return examServiceI.downloadExamXlsxTemplate();
    }

    /**
     * 查询考试信息
     *
     * @return exam
     */
    @ApiOperation(value = "查询考试", notes = "查询考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "考试名", dataTypeClass = String.class)
    })

    @GetMapping(value = "/getExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Exam>> queryExamList(@RequestParam(required = false, defaultValue = "1") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return examServiceI.queryExamList(currentPage, pageSize, name);
    }

    /**
     * 创建考试
     *
     * @return Exam
     */
    @ApiOperation(value = "创建考试", notes = "创建考试")
    @PostMapping(value = "/createExam")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<Exam> createExam(@RequestBody Exam exam) {
        Map<String, Object> map = examServiceI.createExam(exam);
        return returnDataList(map);
    }

    /**
     * 更新考试
     *
     * @return Exam
     */
    @ApiOperation(value = "更新考试", notes = "更新考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的考试ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<Exam> updateExam(@RequestParam int id,
                                   @RequestBody Exam exam) {
        Map<String, Object> map = examServiceI.updateExam(id, exam);
        return returnDataList(map);
    }

    /**
     * 删除考试
     *
     * @return Exam
     */
    @ApiOperation(value = "删除考试", notes = "删除考试")
    @DeleteMapping(value = "/deleteExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<Exam> deleteExam(@RequestParam int id) {
        Map<String, Object> map = examServiceI.deleteExam(id);
        return returnDataList(map);
    }
}
