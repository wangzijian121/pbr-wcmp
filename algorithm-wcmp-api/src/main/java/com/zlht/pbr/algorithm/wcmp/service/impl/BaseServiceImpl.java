package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.service.BaseServiceI;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.remote.client.ManagementClient;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseServiceI<T> {

    public void putMsg(Map<String, Object> result, int code, String msg) {
        result.put("code", code);
        result.put("msg", msg);
        if (code == Status.SUCCESS.getCode()) {
            result.put("status", Status.SUCCESS);
        }
    }

    @Override
    public boolean canOperator(User operateUser) {
        return operateUser != null ;
    }

    @Override
    public HttpHeaders loadForManagementClient(ManagementClient managementClient, MultiValueMap<String, String> values) {
        for (Map.Entry<String, List<String>> entry : values.entrySet()) {
            String key = entry.getKey();
            List<String> valueList = entry.getValue();
            for (String value : valueList) {
                managementClient.getHeaders().set(key, value);
            }
        }
        return managementClient.getHeaders();
    }
}
