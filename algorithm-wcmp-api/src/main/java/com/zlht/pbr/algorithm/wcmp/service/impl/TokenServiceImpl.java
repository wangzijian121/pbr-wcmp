package com.zlht.pbr.algorithm.wcmp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Token;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.TokenMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.TokenServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.EncryptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * token service implement
 *
 * @author zi jian Wang
 */
@Service
public class TokenServiceImpl extends BaseServiceImpl implements TokenServiceI {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private TokenMapper tokenMapper;

    /**
     * create token
     *
     * @param user user
     * @return token string
     */
    @Override
    public String createOrUpdateToken(User user) {
        long now = System.currentTimeMillis();
        long expireTime = System.currentTimeMillis() + 24L * 60L * 60L * 1000L;
        Token token = new Token();

        String tokenStr = EncryptionUtils.getMd5(String.valueOf(user.getId() + expireTime + now));
        token.setUserId(user.getId());
        token.setToken(tokenStr);
        Date date = new Date(expireTime);
        token.setExpireTime(date);
        tokenMapper.insert(token);

        QueryWrapper<Token> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        Token tokenQuery = tokenMapper.selectOne(queryWrapper);
        if (tokenQuery == null) {
            tokenMapper.insert(token);
        } else {
            token.setId(tokenQuery.getId());
            tokenMapper.updateById(token);
        }
        return token.getToken();
    }

    /**
     * sign out
     * remove ip restrictions
     *
     * @param ip        no use
     * @param loginUser login user
     */
    @Override
    public Map<String, Object> signOut(String ip, User loginUser) {
        Map<String, Object> map = new HashMap<>(3);

        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id", loginUser.getId());
            queryWrapper.eq("ip", ip);
            Token token = tokenMapper.selectOne(queryWrapper);
            //delete token
            int delete = tokenMapper.deleteById(token.getId());
            if (delete > 0) {
                putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                return map;
            }
        } catch (Exception e) {
            logger.warn("userId : {} , ip : {} , find more one token", loginUser.getId(), ip);
        }
        return map;
    }
}
