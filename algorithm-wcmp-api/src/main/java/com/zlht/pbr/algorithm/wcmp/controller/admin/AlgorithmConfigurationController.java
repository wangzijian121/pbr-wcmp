package com.zlht.pbr.algorithm.wcmp.controller.admin;


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
public class AlgorithmConfigurationController extends BaseController {

    private static final Logger logger = LogManager.getLogger(AlgorithmConfigurationController.class);
    @Autowired
    private AlgorithmConfigurationServiceI algorithmConfigurationServiceI;

    /**
     * 获取机构下所有算法
     *
     * @param linkCode
     */
    @ApiOperation(value = "获取机构下所有算法", notes = "获取机构下所有算法")
    @GetMapping(value = "/getAlgorithmConfiguration")
    @ResponseStatus(HttpStatus.OK)
    public Result<AlgorithmConfiguration> getAlgorithmByAppId(@PathVariable(required = false) String linkCode) {

        return algorithmConfigurationServiceI.getAlgorithmByAppId(linkCode);
    }


    /**
     * 设置算法可用性
     *
     * @param linkCode
     * @param algorithmId
     * @param enable
     * @return
     */
    @ApiOperation(value = "设置算法可用性", notes = "设置算法可用性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "algorithmId", value = "算法ID", dataTypeClass = int.class),
            @ApiImplicitParam(name = "enable", value = "是否启用", dataTypeClass = Boolean.class)
    })
    @PutMapping(value = "/getAlgorithmAvailability")
    @ResponseStatus(HttpStatus.OK)
    public Result<AlgorithmConfiguration> setAlgorithmAvailability(@PathVariable(required = false) String linkCode,
                                                                   @RequestParam(required = false) int algorithmId,
                                                                   @RequestParam(required = false) Boolean enable) {

        Map<String, Object> map = algorithmConfigurationServiceI.setAlgorithmAvailability(linkCode, algorithmId, enable);
        return returnDataList(map);
    }

}
