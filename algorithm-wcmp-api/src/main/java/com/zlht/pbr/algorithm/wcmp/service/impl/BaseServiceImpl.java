package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.BaseServiceI;

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
        return operateUser != null;
    }

}
