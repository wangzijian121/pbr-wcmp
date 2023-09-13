package com.zlht.pbr.algorithm.wcmp.controller.user;


import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.AlgorithmConfiguration;
import com.zlht.pbr.algorithm.wcmp.service.AlgorithmConfigurationServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "机构管理员-算法与算法配置")
@RequestMapping("/wechat/{linkCode}/admin/algorithm")
public class UserAlgorithmController extends BaseController {

    private static final Logger logger = LogManager.getLogger(UserAlgorithmController.class);
    @Autowired
    private AlgorithmConfigurationServiceI algorithmConfigurationServiceI;

    /**
     * 获取机构学生可用算法
     *
     * @param linkCode
     */
    @ApiOperation(value = "获取机构学生可用算法", notes = "获取机构学生可用算法")
    @GetMapping(value = "/getEnableAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<AlgorithmConfiguration> getEnableAlgorithm(@PathVariable(required = false) String linkCode) {

        return algorithmConfigurationServiceI.getEnableAlgorithm(linkCode);
    }
}
