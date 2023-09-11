package com.zlht.pbr.algorithm.wcmp.client;


import com.zlht.pbr.algorithm.wcmp.model.WeChatUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 微信客户端
 *
 * @author zijian Wang
 */
@FeignClient(url = "https://api.weixin.qq.com", name = "wechat")
public interface WeChatClient {

    /**
     * 转为session
     * @param grantType
     * @param appid
     * @param secret
     * @param jsCode
     * @return
     */
    @GetMapping(value = "/sns/jscode2session")
    String  code2Session(@RequestParam("grant_type") String grantType,
                                    @RequestParam("appid") String appid,
                                    @RequestParam("secret") String secret,
                                    @RequestParam("js_code") String jsCode);

}
