package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.dao.entity.ExamRecord;
import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;
import com.zlht.pbr.algorithm.wcmp.service.ExamRecordServiceI;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
import com.zlht.pbr.algorithm.wcmp.service.SpotHistoryServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
@RestController
@RequestMapping("/wechat/{linkCode}/user/spot")
@Api(tags = "识别历史接口")
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
    public Result queryExamList(@RequestBody SpotHistory spotHistory,
                                @PathVariable(required = false) String linkCode) {
        Map<String, Object> map = spotHistoryServiceI.createSpotHistory(linkCode, spotHistory);
        return returnDataList(map);
    }

}
