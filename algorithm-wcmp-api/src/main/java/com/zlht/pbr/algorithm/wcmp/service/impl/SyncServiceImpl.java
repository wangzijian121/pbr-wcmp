package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zlht.pbr.algorithm.wcmp.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.dao.entity.AlgorithmConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.entity.LinkCodeAndAppIdMap;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.AlgorithmConfigurationMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.LinkCodeAndAppIdMapMapper;
import com.zlht.pbr.algorithm.wcmp.service.SyncServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zijian Wang
 */
@Service
public class SyncServiceImpl extends BaseServiceImpl implements SyncServiceI {

    private static final Logger logger = LogManager.getLogger(SyncServiceImpl.class);

    @Autowired
    private ManagementClient managementClient;
    @Autowired
    private AlgorithmConfigurationMapper algorithmConfigurationMapper;
    @Autowired
    private LinkCodeAndAppIdMapMapper linkCodeAndAppIdMapMapper;

    @Override
    public void syncAlgorithm() {

        Result<List<AlgorithmConfiguration>> result = managementClient.syncAlgorithm();
        System.out.println(result);
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
                algorithmConfiguration.setUpdateTime(new Date());
                algorithmConfigurationMapper.insert(algorithmConfiguration);
            }
        }
    }

    @Override
    public void syncInstitutionLinkCodeAndAppId() {
        Result<List<LinkCodeAndAppIdMap>> result = managementClient.syncLinkCodeAndAppId();
        System.out.println(result);
        List<LinkCodeAndAppIdMap> list = result.getData();
        try {
            linkCodeAndAppIdMapMapper.delete(new QueryWrapper<>());
            for (LinkCodeAndAppIdMap linkCodeAndAppIdMap : list) {
                linkCodeAndAppIdMap.setUpdateTime(new Date());
                linkCodeAndAppIdMapMapper.insert(linkCodeAndAppIdMap);
            }
        } catch (Exception e) {
            // 手动回滚事务
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

}
