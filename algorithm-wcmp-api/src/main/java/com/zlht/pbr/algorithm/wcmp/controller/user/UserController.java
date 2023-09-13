package com.zlht.pbr.algorithm.wcmp.controller.user;


import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("/wechat/{linkCode}/user")
public class UserController extends BaseController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserServicesI userServices;

    @Autowired
    private WeChatServiceI weChatServiceI;


    /**
     * 登录
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "wx.login的code", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "encryptedData", value = "用户加密数据", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "iv", value = "iv", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public Result login(@PathVariable String linkCode,
                        @RequestParam String code,
                        @RequestParam String encryptedData,
                        @RequestParam String iv,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        // user ip check
        String ip = getClientIpAddress(request);
        if (StringUtils.isEmpty(ip)) {
            return error(10125, "获取不到IP！");
        }
        Map<String, Object> map = weChatServiceI.login(linkCode, code, encryptedData, iv);
        String codeStr = "code";
        if (!map.get(codeStr).equals(Status.SUCCESS.getCode())) {
            return returnDataList(map);
        }
        response.setStatus(200);
        Map<String, Object> cookieMap = (Map<String, Object>) map.get("data");
        for (Map.Entry<String, Object> cookieEntry : cookieMap.entrySet()) {
            Cookie cookie = new Cookie(cookieEntry.getKey(), (String) cookieEntry.getValue());
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        return returnDataList(map);
    }

}
