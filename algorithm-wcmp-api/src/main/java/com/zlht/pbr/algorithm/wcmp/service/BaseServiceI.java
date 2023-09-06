package com.zlht.pbr.algorithm.wcmp.service;


import com.zlht.pbr.algorithm.wcmp.dao.entity.User;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface BaseServiceI<T> {

    /**
     * 发送消息
     *
     * @param result
     * @param code
     * @param msg
     */
    void putMsg(Map<String, Object> result, int code, String msg);

    /**
     * 检查权限
     *
     * @param userId
     * @return
     */
    boolean canOperator(User operateUser);

}
