package com.zlht.pbr.algorithm.wcmp.api.remote.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pbr.algorithm.wcmp.api.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.api.remote.service.ReviewServicesI;
import com.zlht.pbr.algorithm.wcmp.model.Review;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  zi jian Wang
 */
@RestController
@Api(tags = "开发者审核管理")
public class ReviewController extends BaseController {

    @Autowired
    ReviewServicesI reviewServicesI;


    /**
     * 开发者-提交审核
     *
     * @return Result
     */
    @ApiOperation(value = "开发者-提交审核", notes = "开发者-提交审核")
    @PostMapping(value = "/wcmp/commitReview")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<Review> createReview(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                       @RequestBody Review review, HttpServletRequest request) {

        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("X-Real-IP", getClientIpAddress(request));
        values.add("sessionId", getSessionByRequest(request));
        return reviewServicesI.commitReview(userId, review, values);
    }

    /**
     * 开发者-查询审核信息
     *
     * @return review
     */
    @ApiOperation(value = "开发者-查询算法审核信息", notes = "开发者-查询算法审核信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "算法名", dataTypeClass = String.class),
            @ApiImplicitParam(name = "type", value = "数据类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)", dataTypeClass = String.class)
    })
    @GetMapping(value = "/wcmp/getReview")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo> queryReviewList(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                            @RequestParam(required = false, defaultValue = "1") int currentPage,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize,
                                            @RequestParam(required = false) String name,
                                            @RequestParam String type,
                                            HttpServletRequest request) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("X-Real-IP", getClientIpAddress(request));
        values.add("sessionId", getSessionByRequest(request));
        return reviewServicesI.queryReviewList(userId, currentPage, pageSize, name, type, values);
    }
}
