package com.zlht.pbr.algorithm.wcmp.controller.admin;


import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.service.ResourceServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author zijian Wang
 */

@RestController
@RequestMapping("/wechat")
@Api(tags = "文件管理")
public class FileController extends BaseController {

    private static final Logger logger = LogManager.getLogger(FileController.class);
    @Autowired
    private ResourceServiceI resourceServiceI;

    @ApiOperation(value = "上传资源", notes = "上传资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "文件", paramType = "form", value = "文件", dataType = "file", required = true)
    })
    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result upload(@RequestPart("file") MultipartFile file) {
        Map<String, Object> map = null;
        try {
            map = resourceServiceI.createResource(file);
        } catch (Exception e) {
            logger.error(e.getMessage(), "文件上传失败！");
        }
        return returnDataList(map);
    }

    @ApiOperation(value = "删除资源", notes = "删除资源")

    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam int resourceId) {
        Map<String, Object> map = null;
        try {
            map = resourceServiceI.deleteResource(resourceId);
        } catch (Exception e) {
            logger.error(e.getMessage(), "文件上传失败！");
        }
        return returnDataList(map);
    }

    @ApiOperation(value = "下载接口")
    @ApiImplicitParam(name = "uuid", value = "资源的uuid", paramType = "query", required = true, dataType = "String")
    @GetMapping("/download")
    public ResponseEntity download(@RequestParam int resourceId) {
        return resourceServiceI.downloadResource(resourceId);
    }
}

