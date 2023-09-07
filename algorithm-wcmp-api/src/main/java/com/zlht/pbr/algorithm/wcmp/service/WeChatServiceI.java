package com.zlht.pbr.algorithm.wcmp.service;

import java.util.Map;

/**
 * 微信服务接口
 * @author zijian Wang
 */
public interface WeChatServiceI extends BaseServiceI{

     /**
      * jsCode 获取Session
      * @param jsCode
      * @return
      */
     Map<String, Object> code2Session(String jsCode);
}
