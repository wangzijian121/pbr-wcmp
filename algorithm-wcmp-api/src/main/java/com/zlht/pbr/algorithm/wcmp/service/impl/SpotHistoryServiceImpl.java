package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.SpotHistoryMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.security.AuthLinkCodeServiceI;
import com.zlht.pbr.algorithm.wcmp.service.SpotHistoryServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
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
    private ManagementClient managementClient;

    @Autowired
    private SpotHistoryMapper spotHistoryMapper;
    @Autowired
    private AuthLinkCodeServiceI authLinkCodeServiceI;

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
            managementClient.reportData(linkCode, "algorithm_count_today", 1);
        } catch (Exception e) {
            String errMsg = "创建识别失败！";
            logger.error("createSpotHistory() method .message={}, spotHistory={}", errMsg, spotHistory, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }


    @Override
    public Result<SpotHistory> querySpotHistoryByUserId(String linkCode, int userId, int currentPage, int pageSize) {
        Result result = new Result();
        if (!authLinkCodeServiceI.checkLinkCodeValidity(linkCode)) {
            result.setMsg("linkCode错误!！");
            result.setCode(400);
            return result;
        }
        Page<SpotHistory> page = new Page<>(currentPage, pageSize);

        QueryWrapper<SpotHistory> queryWrapper = new QueryWrapper<SpotHistory>();
        if (linkCode != null) {
            queryWrapper.eq("link_code", linkCode);
            queryWrapper.eq("user_id", userId);
        }
        Page<SpotHistory> spotHistoryPage = spotHistoryMapper.selectPage(page, queryWrapper);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(spotHistoryPage.getRecords());
        result.setCode(200);
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(pageInfo);
        return result;
    }
}
