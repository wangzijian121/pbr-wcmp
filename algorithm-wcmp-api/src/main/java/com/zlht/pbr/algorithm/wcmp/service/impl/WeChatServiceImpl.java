package com.zlht.pbr.algorithm.wcmp.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.wcmp.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.client.WeChatClient;
import com.zlht.pbr.algorithm.wcmp.configuration.WcServerConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.LinkCodeAndAppIdMapMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.UserMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.TokenServiceI;
import com.zlht.pbr.algorithm.wcmp.service.WeChatServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.RandomGeneratorUtils;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import com.zlht.pbr.algorithm.wcmp.utils.WxBizDataCryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class WeChatServiceImpl extends BaseServiceImpl implements WeChatServiceI {

    private static final Logger logger = LogManager.getLogger(WeChatServiceImpl.class);
    @Autowired
    WcServerConfiguration wcServerConfiguration;

    @Autowired
    private ManagementClient managementClient;

    @Autowired
    private WeChatClient weChatServiceClient;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LinkCodeAndAppIdMapMapper linkCodeAndAppIdMapMapper;

    @Autowired
    private TokenServiceI tokenServiceI;

    @Override
    public Map<String, Object> login(String linkCode, String code, String encryptedData, String iv) {
        Map<String, Object> map = new HashMap<>(3);
        ObjectMapper objectMapper = new ObjectMapper();
//        get appId and secret
        Map<String, String> selectMapByLinkCode = linkCodeAndAppIdMapMapper.selectMapByLinkCode(linkCode);
        String appId = selectMapByLinkCode.get("appId");
        String secret = selectMapByLinkCode.get("appSecret");

        String code2SessionRes = weChatServiceClient.code2Session("authorization_code", appId, secret, code);
        Map<String, Object> sessionOpenIdMap;
        try {
            sessionOpenIdMap = objectMapper.readValue(code2SessionRes, Map.class);
            System.out.println(sessionOpenIdMap);
            String checkKeyStr = "session_key";
            String checkOpenIdStr = "openid";
            if (sessionOpenIdMap.containsKey(checkKeyStr) && sessionOpenIdMap.containsKey(checkOpenIdStr)) {
                String sessionKey = sessionOpenIdMap.get(checkKeyStr).toString();
                String openId = sessionOpenIdMap.get(checkOpenIdStr).toString();
                //decrypt
                WxBizDataCryptUtil wxBizDataCryptUtil = new WxBizDataCryptUtil(appId, sessionKey);
                JSONObject jsonObject = wxBizDataCryptUtil.decrypt(encryptedData, iv);

                QueryWrapper userQueryWrapper = new QueryWrapper();
                userQueryWrapper.eq("open_id", openId);
                User user;
                user = userMapper.selectOne(userQueryWrapper);
                user.setType(adminOrNot(linkCode, openId) ? 2 : 3);
//                check user exists
                if (user != null) {
                    user.setSessionKey(sessionKey);
                    userMapper.update(user, userQueryWrapper);
                    //Sync
                    reportUserData(appId, user, 0);
                    putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                } else {
                    user = new User();
                    user.setType(0);
                    user.setNickname("微信用户" + RandomGeneratorUtils.generateRandomChars(5));
                    user.setOpenId(openId);
                    user.setLinkCode(linkCode);
                    user.setSessionKey(sessionKey);
                    user.setUpdateTime(new Date());
                    user.setGender(jsonObject.getInt("gender"));
                    user.setCreateTime(new Date());
                    userMapper.insert(user);
                    //Sync
                    reportUserData(appId, user, 1);
                    putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                }
                //createOrUpdateToken
                String token = tokenServiceI.createOrUpdateToken(user);
                Map<String, Object> useMap = new HashMap<>(1);
                useMap.put("nickname", user.getNickname());
                useMap.put("token", token);
                map.put("data", useMap);
            } else {
                String errMsgStr = "errMsgStr";
                String reason = "未知";
                if (sessionOpenIdMap.containsKey(errMsgStr)) {
                    reason = sessionOpenIdMap.get(errMsgStr).toString();
                }
                String errMsg = "未获取到session_key和openid,登录失败,原因:" + reason;
                logger.error("login() method .message={}, jsCode={}", errMsg, code);
                putMsg(map, 400, errMsg);
                return map;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    boolean adminOrNot(String linkCode, String openId) {

        Result<Map<String, Object>> result = managementClient.adminOrNot(linkCode, openId);

        return Boolean.getBoolean(result.getData().get("adminOrNot").toString());
    }

    private void reportUserData(String appId, User user, int event) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("nickname", user.getNickname());
        map.put("openId", user.getOpenId());
        map.put("appId", appId);
        managementClient.reportUser(map, event);
    }
}
