package com.zlht.pbr.algorithm.wcmp.client;


import com.zlht.pbr.algorithm.wcmp.model.WeChatUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信客户端
 *
 * @author zijian Wang
 */
@FeignClient(url = "${remote.wechat.url}", name = "wechat")
public interface WeChatClient {

    /**
     * 微信登录
     *
     * @param jsCode
     * @return
     */
    @RequestMapping(value = "/sns/jscode2session", method = RequestMethod.GET)
    WeChatUserInfo login(@RequestParam("jsCode") String jsCode);

}
