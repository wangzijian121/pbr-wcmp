package com.zlht.pbr.algorithm.wcmp.controller;


import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.SessionServiceI;
import com.zlht.pbr.algorithm.wcmp.service.UserServicesI;
import com.zlht.pbr.algorithm.wcmp.service.WeChatServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "用户管理")
public class UserController extends BaseController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserServicesI userServices;

    @Autowired
    private WeChatServiceI weChatServiceI;

    @Autowired
    private SessionServiceI sessionServiceI;

    /**
     * 查询用户信息
     *
     * @return User
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "用户类型:(0:管理员,1:机构管理员,2: 开发者,3 机构用户)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "nickname", value = "用户昵称", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getUser")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<User>> queryUserList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                @RequestParam(required = false, defaultValue = "-1") int type,
                                                @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String nickname) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return userServices.queryUserList(loginUser, type, currentPage, pageSize, nickname);
    }

    /**
     * 登录
     *
     * @param jsCode
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsCode", value = "jsCode", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public Result login(@RequestParam String jsCode,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        // user ip check
        String ip = getClientIpAddress(request);
        if (StringUtils.isEmpty(ip)) {
            return error(10125, "获取不到IP！");
        }
        Map<String, Object> map = weChatServiceI.code2Session(jsCode);
        System.out.println(map);
        String checkKey = "code";
        if (map.get(checkKey).equals(Status.SUCCESS.getCode())) {

        }
        return returnDataList(map);
    }


    /**
     * 用户注销
     *
     * @return
     */
    @ApiOperation(value = "用户登出", notes = "用户登出")
    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public Result logout(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                         HttpServletRequest request) {
        String ip = getClientIpAddress(request);
        Map<String, Object> map = sessionServiceI.signOut(ip, loginUser);
        request.removeAttribute("session.user");
        return returnDataList(map);
    }
}
