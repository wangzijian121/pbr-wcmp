package com.zlht.pbr.algorithm.wcmp.controller;


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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "算法配置")
public class AlgorithmConfigurationController extends BaseController {

    private static final Logger logger = LogManager.getLogger(AlgorithmConfigurationController.class);
    @Autowired
    private AlgorithmConfigurationServiceI algorithmConfigurationServiceI;

    /**
     * 获取机构下所有算法
     *
     * @return User
     */
    @ApiOperation(value = "获取机构下所有算法", notes = "获取机构下所有算法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appId", dataTypeClass = String.class)
    })
    @GetMapping(value = "/wechat/getAlgorithmConfiguration")
    @ResponseStatus(HttpStatus.OK)
    public Result<AlgorithmConfiguration> getAlgorithmByAppId(@RequestParam(required = false) String appId) {

        return algorithmConfigurationServiceI.getAlgorithmByAppId(appId);
    }

}
