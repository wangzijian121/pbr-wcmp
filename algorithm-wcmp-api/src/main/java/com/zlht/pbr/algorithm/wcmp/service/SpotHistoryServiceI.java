package com.zlht.pbr.algorithm.wcmp.service;


import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;

import java.util.Map;

/**
 * @author zijian Wang
 */
public interface SpotHistoryServiceI extends BaseServiceI {


    /**
     * 记录识别历史
     *
     * @param linkCode
     * @param spotHistory
     * @return
     */
    Map<String, Object> createSpotHistory(String linkCode,  SpotHistory spotHistory);


    /**
     * 获取识别历史
     *
     * @param linkCode
     * @param userId
     * @return
     */
    Map<String, Object> getSpotHistory(String linkCode, int userId);
}

