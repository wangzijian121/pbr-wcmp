package com.zlht.pbr.algorithm.wcmp.service;


import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;
import com.zlht.pbr.algorithm.wcmp.utils.Result;

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
     * 获取用户自己的识别历史
     *
     * @param linkCode
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<SpotHistory> querySpotHistoryByUserId(String linkCode, int userId, int currentPage, int pageSize);
}

