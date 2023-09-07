package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.client.WeChatClient;
import com.zlht.pbr.algorithm.wcmp.configuration.WcServerConfiguration;
import com.zlht.pbr.algorithm.wcmp.service.WeChatServiceI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    WeChatClient weChatServiceClient;

    @Override
    public Map<String, Object> code2Session(String jsCode) {
        Map<String, Object> map = new HashMap<>();
/*        try {
            WeChatUserInfo userInfo = weChatServiceClient.login(jsCode);
            String errMsg = convertErrorCode(userInfo.getErrCode());
            if (errMsg == null) {
                map.put("session_key", userInfo.getSessionKey());
                map.put("openid", userInfo.getOpenid());
                putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());

            } else {
                logger.error("login() method .message={}, jsCode={}", errMsg, jsCode);
                putMsg(map, 400, errMsg);
                return map;
            }
        } catch (Exception e) {
            String errMsg = "请求失败！";
            logger.error("login() method .message={}, jsCode={}", errMsg, jsCode);
        }*/
        map.put("session_key", "2jbas5pCjnf2vAFNFxnBwA==");
        map.put("openid", "oFDqp5ebx3IpacqU36g0JOxMdSLI");
        putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
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
