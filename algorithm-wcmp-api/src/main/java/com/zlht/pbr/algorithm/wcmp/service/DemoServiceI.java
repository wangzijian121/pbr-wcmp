package com.zlht.pbr.algorithm.wcmp.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author zijian Wang
 */
@FeignClient(url = "http://127.0.0.1:8080",name = "login",value = "login")
public interface DemoServiceI {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    String login(@RequestHeader MultiValueMap<String, String> headers,
                 @RequestParam("username") String username,
                 @RequestParam("password") String password);
}
