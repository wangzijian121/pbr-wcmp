package com.zlht.pbr.algorithm.wcmp.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.wcmp.client.WeChatClient;
import com.zlht.pbr.algorithm.wcmp.configuration.WcServerConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.LinkCodeAndAppIdMapMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.UserMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.WeChatServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.RandomGeneratorUtils;
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
    private WeChatClient weChatServiceClient;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LinkCodeAndAppIdMapMapper linkCodeAndAppIdMapMapper;

    @Override
    public Map<String, Object> login(String linkCode, String code, String encryptedData, String iv) {
        Map<String, Object> map = new HashMap<>(3);
        ObjectMapper objectMapper = new ObjectMapper();
//        get appId and secret
        Map<String, String> selectMapByLinkCode = linkCodeAndAppIdMapMapper.selectMapByLinkCode(linkCode);
        String appId = selectMapByLinkCode.get("appId");
        String secret = selectMapByLinkCode.get("appSecret");

        String code2SessionRes = weChatServiceClient.code2Session("authorization_code", appId, secret, code);
        Map<String, Object> code2SessionResMap;
        try {
            code2SessionResMap = objectMapper.readValue(code2SessionRes, Map.class);
            System.out.println(code2SessionResMap);
            String checkKeyStr = "session_key";
            String checkOpenIdStr = "openid";
            if (code2SessionResMap.containsKey(checkKeyStr) && code2SessionResMap.containsKey(checkOpenIdStr)) {
                String sessionKey = code2SessionResMap.get(checkKeyStr).toString();
                String openId = code2SessionResMap.get(checkOpenIdStr).toString();
                //decrypt
                WxBizDataCryptUtil wxBizDataCryptUtil = new WxBizDataCryptUtil(appId, sessionKey);
                JSONObject jsonObject = wxBizDataCryptUtil.decrypt(encryptedData, iv);

                QueryWrapper userQueryWrapper = new QueryWrapper();
                userQueryWrapper.eq("open_id", openId);
                User user;
                user = userMapper.selectOne(userQueryWrapper);
//                check user exists
                if (user != null) {
                    user.setSessionKey(sessionKey);
                    userMapper.update(user, userQueryWrapper);
                    putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                } else {
                    //TODO is Admin?？？
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
                    putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                }
                Map<String, Object> useMap = new HashMap<>(1);
                useMap.put("nickname", user.getNickname());
                map.put("data", useMap);
            } else {
                String errmsgStr = "errmsgStr";
                String reason = "未知";
                if (code2SessionResMap.containsKey(errmsgStr)) {
                    reason = code2SessionResMap.get(errmsgStr).toString();
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

    public String convertErrorCode(int errorCode) {
        switch (errorCode) {
            case 40029:
                return "无效的code，js_code无效";
            case 45011:
                return "API调用频率超限，请稍候再试";
            case 40226:
                return "高风险等级用户，小程序登录被拦截";
            case -1:
                return "系统错误，请稍候再试";
            default:
                return null;
        }
    }
}
