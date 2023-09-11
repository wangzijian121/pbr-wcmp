package com.zlht.pbr.algorithm.wcmp.service;

import java.util.Map;

/**
 * 微信服务接口
 *
 * @author zijian Wang
 */
public interface WeChatServiceI extends BaseServiceI {


    /**
     * jsCode 获取Session
     *
     * @param linCode
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    Map<String, Object> login(String linCode, String code, String encryptedData, String iv);


}
