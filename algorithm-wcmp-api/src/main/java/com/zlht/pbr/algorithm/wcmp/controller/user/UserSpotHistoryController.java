package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;
import com.zlht.pbr.algorithm.wcmp.service.SpotHistoryServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zijian Wang
 */
@RestController
@RequestMapping("/wechat/{linkCode}/user/spot")
@Api(tags = "AI识别接口")
public class UserSpotHistoryController extends BaseController {


    @Autowired
    private SpotHistoryServiceI spotHistoryServiceI;

    /**
     * 录入识别历史
     *
     * @return exam
     */
    @ApiOperation(value = "录入识别历史", notes = "录入识别历史")
    @PostMapping(value = "/createSpotHistory")
    @ResponseStatus(HttpStatus.OK)
    public Result createSpotHistory(@RequestBody SpotHistory spotHistory,
                                    @PathVariable(required = false) String linkCode) {
        Map<String, Object> map = spotHistoryServiceI.createSpotHistory(linkCode, spotHistory);
        return returnDataList(map);
    }

    /**
     * 查询用户识别历史
     *
     * @return exam
     */
    @ApiOperation(value = "查询用户识别历史", notes = "查询用户识别历史")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataTypeClass = int.class),
    })
    @GetMapping(value = "/querySpotHistoryList")
    @ResponseStatus(HttpStatus.OK)
    public Result querySpotHistoryList(
            @PathVariable String linkCode,
            @RequestParam int userId,
            @RequestParam(required = false, defaultValue = "1") int currentPage,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Result result = spotHistoryServiceI.querySpotHistoryByUserId(linkCode, userId, currentPage, pageSize);
        return result;
    }

}
