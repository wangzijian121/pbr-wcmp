package com.zlht.pbr.algorithm.wcmp.controller.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "机构管理员-考试接口")
@RequestMapping("/wechat/{linkCode}/admin/exam")
public class AdminExamController extends BaseController {

    @Autowired
    private ExamServiceI examServiceI;

    @ApiOperation(value = "下载试题模板")
    @GetMapping("/download")
    public ResponseEntity download(@ApiParam(hidden = true) @PathVariable("linkCode") String linkCode) {
        return examServiceI.downloadExamXlsxTemplate();
    }


    /**
     * 检查上传表格模板规范
     *
     * @return Exam
     */
    @ApiOperation(value = "检查上传表格模板规范", notes = "检查上传表格模板规范")
    @PostMapping(value = "/checkExamXlsxTemplate")
    @ResponseStatus(HttpStatus.OK)
    public Result<Exam> checkExamXlsxTemplate(@RequestParam String uuid) {
        Map<String, Object> map = examServiceI.checkExamXlsxTemplate(uuid);
        return returnDataList(map);
    }

    @ApiOperation(value = "查询考试", notes = "查询考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
    })

    @GetMapping(value = "/getExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Exam>> queryExamList(@RequestParam(required = false, defaultValue = "1") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @PathVariable String linkCode) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return examServiceI.queryExamList(linkCode, currentPage, pageSize);
    }

    /**
     * 创建考试
     *
     * @return Exam
     */
    @ApiOperation(value = "创建考试", notes = "创建考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkCode", value = "小程序链接码", dataTypeClass = String.class)
    })
    @PostMapping(value = "/createExam")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<Exam> createExam(@PathVariable String linkCode, @RequestBody Exam exam) {
        Map<String, Object> map = examServiceI.createExam(linkCode,exam);
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
                                   @PathVariable String linkCode,
                                   @RequestBody Exam exam) {
        Map<String, Object> map = examServiceI.updateExam(linkCode, id, exam);
        return returnDataList(map);
    }

    /**
     * 删除考试
     *
     * @return Exam
     */
    @ApiOperation(value = "删除考试", notes = "删除考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要删除的考试ID", required = true, dataTypeClass = int.class)
    })
    @DeleteMapping(value = "/deleteExam")
    @ResponseStatus(HttpStatus.OK)
    public Result<Exam> deleteExam(@PathVariable String linkCode,
                                   @RequestParam int id) {
        Map<String, Object> map = examServiceI.deleteExam(linkCode, id);
        return returnDataList(map);
    }
}
