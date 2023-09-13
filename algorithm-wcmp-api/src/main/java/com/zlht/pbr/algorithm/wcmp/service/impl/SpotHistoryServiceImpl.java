package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.SpotHistoryMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.SpotHistoryServiceI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class SpotHistoryServiceImpl extends BaseServiceImpl implements SpotHistoryServiceI {

    private static final Logger logger = LogManager.getLogger(SpotHistoryServiceImpl.class);

    @Autowired
    private SpotHistoryMapper spotHistoryMapper;

    /**
     * 记录识别历史
     *
     * @param linkCode
     * @param spotHistory
     * @return
     */
    @Override
    public Map<String, Object> createSpotHistory(String linkCode, SpotHistory spotHistory) {

        Map<String, Object> map = new HashMap<>(3);
        try {
            spotHistoryMapper.insert(spotHistory);
            putMsg(map, Status.SUCCESS.getCode(), "创建识别成功！");
        } catch (Exception e) {
            String errMsg = "创建识别成功";
            logger.error("createSpotHistory() method .message={}, spotHistory={}", errMsg, spotHistory, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    /**
     * 获取识别历史
     *
     * @param linkCode
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> getSpotHistory(String linkCode, int userId) {
        return null;
    }
}
