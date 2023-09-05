package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.zlht.pbr.algorithm.wcmp.service.DemoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

@Service
public class Demo {

    @Autowired
    private DemoServiceI demoServiceI;

    public String  login(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList("Basic YWRtaW46QFdUTDE5OTIwMTE4MDI3MQ=="));
        headers.add("Content-Type","text/plain");
        return demoServiceI.login(headers,"root","123456");
    }

}
