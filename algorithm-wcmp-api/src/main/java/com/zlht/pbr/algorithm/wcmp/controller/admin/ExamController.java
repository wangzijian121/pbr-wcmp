package com.zlht.pbr.algorithm.wcmp.controller.admin;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
}
