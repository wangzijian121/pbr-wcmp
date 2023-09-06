package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zlht.pbr.algorithm.wcmp.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.dao.entity.AlgorithmConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.AlgorithmConfigurationMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.AlgorithmConfigurationServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zijian Wang
 */
@Service
public class AlgorithmConfigurationServiceImpl extends BaseServiceImpl implements AlgorithmConfigurationServiceI {

    private static final Logger logger = LogManager.getLogger(AlgorithmConfigurationServiceImpl.class);

    @Autowired
    private ManagementClient managementClient;
    @Autowired
    private AlgorithmConfigurationMapper algorithmConfigurationMapper;


    @Override
    public void syncAlgorithm() {

        Map<String, Object> map = new HashMap<>();
        Result<List<AlgorithmConfiguration>> result = managementClient.syncAlgorithm();
        List<AlgorithmConfiguration> list = result.getData();

        //获取idList
        List<String> appIdList = list.stream()
                .map(AlgorithmConfiguration::getAppId)
                .collect(Collectors.toList());

        //clear appId
        QueryWrapper<AlgorithmConfiguration> queryWrapperDeleteAppId = new QueryWrapper<>();
        queryWrapperDeleteAppId.notIn("app_id", appIdList);
        algorithmConfigurationMapper.delete(queryWrapperDeleteAppId);

        //delete 同步后不存在的算法
        for (String appId : appIdList) {
            List<Integer> algorithmIdList = list.stream()
                    .filter(algorithmConfiguration -> algorithmConfiguration.getAppId().equals(appId))
                    .map(AlgorithmConfiguration::getAlgorithmId)
                    .collect(Collectors.toList());

            //clear algorithm_id
            QueryWrapper<AlgorithmConfiguration> queryWrapperDelete = new QueryWrapper<>();
            queryWrapperDelete.eq("app_id", appId).
                    and(wrapper -> wrapper.notIn("algorithm_id", algorithmIdList));
            algorithmConfigurationMapper.delete(queryWrapperDelete);
        }

        //update 同步后相同的算法
        for (AlgorithmConfiguration algorithmConfiguration : list) {

            QueryWrapper checkWrapper = new QueryWrapper<>();
            checkWrapper.eq("app_id", algorithmConfiguration.getAppId());
            checkWrapper.eq("algorithm_id", algorithmConfiguration.getAlgorithmId());
            if (algorithmConfigurationMapper.selectCount(checkWrapper) > 0) {
                // 记录存在，执行更新操作
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("app_id", algorithmConfiguration.getAppId());
                updateWrapper.eq("algorithm_id", algorithmConfiguration.getAlgorithmId());
                updateWrapper.set("name", algorithmConfiguration.getName());
                updateWrapper.set("sport_category", algorithmConfiguration.getSportCategory());
                updateWrapper.set("content", algorithmConfiguration.getContent());
                updateWrapper.set("update_time", LocalDateTime.now());
                algorithmConfigurationMapper.update(null, updateWrapper);
            } else {
                // 记录不存在，执行插入操作
                algorithmConfiguration.setAppId(algorithmConfiguration.getAppId());
                algorithmConfiguration.setEnable(0);
                algorithmConfiguration.setUpdate_time(LocalDateTime.now());
                algorithmConfigurationMapper.insert(algorithmConfiguration);
            }
        }
    }

    @Override
    public Result<AlgorithmConfiguration> getAlgorithmByAppId(String appId) {

        Result result = new Result();
        QueryWrapper<AlgorithmConfiguration> queryWrapper = new QueryWrapper();
        queryWrapper.eq("app_id", appId);
        List<AlgorithmConfiguration> algorithmConfigurationList = algorithmConfigurationMapper.selectList(queryWrapper);
        result.setData(algorithmConfigurationList);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> setAlgorithmAvailability(String appId, int algorithmId, boolean enable) {
        Map<String, Object> map = new HashMap<>();

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("app_id", appId);
        updateWrapper.eq("algorithm_id", algorithmId);
        updateWrapper.set("enable", enable);
        try {
            algorithmConfigurationMapper.update(null, updateWrapper);
            putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        } catch (Exception e) {
            String errMsg = "更新算法启用失败！";
            logger.error("setAlgorithmAvailability() method .message={}, appId={} ,algorithmId={}", errMsg, appId, algorithmId);
            putMsg(map, 400, errMsg);
        }
        return map;
    }
}
