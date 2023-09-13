package com.zlht.pbr.algorithm.wcmp.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Token;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.TokenMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author zi jian Wang
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(LoginHandlerInterceptor.class);
    @Value("${session.timeout}")
    private int sessionTimeout;


    @Autowired
    private TokenMapper tokenMapper;


    @Autowired
    private UserMapper userMapper;

    /**
     * 使用拦截器对登录进行校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("token");
        User user = null;
        //sessionId in cookie
        Cookie[] cookie = request.getCookies();
        if (cookie == null) {
            response.setStatus(401);
            return false;
        }
        for (Cookie c : cookie) {
            if (("token").equals(c.getName()) && !StringUtils.isEmpty(c.getValue())) {
                user = getUserByToken(request, response, c.getValue());
                break;
            }
            return false;
        }
        if (token == null) {
            logger.error("session is null ");
            return false;
        }
        request.setAttribute("session.user", user);
        return true;
    }

    private User getUserByToken(HttpServletRequest request, HttpServletResponse response, String tokenStr) {
        QueryWrapper<Token> queryWrapper = new QueryWrapper();
        queryWrapper.eq("token", tokenStr);
        Token token = tokenMapper.selectOne(queryWrapper);
        if (token == null) {
            response.setStatus(401);
            logger.info("未找到token,请登录后重试！" + token.getId());
            request.setAttribute("session.user", null);
            return null;
        }
        if (new Date().after(token.getExpireTime())) {
            response.setStatus(401);
            logger.info("用户session已过期!");
            request.setAttribute("session.user", null);
            return null;
        } else {
            User user = userMapper.selectById(token.getUserId());
            return user;
        }
    }
}