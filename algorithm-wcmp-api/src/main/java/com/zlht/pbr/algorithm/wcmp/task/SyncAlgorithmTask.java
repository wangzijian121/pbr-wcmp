package com.zlht.pbr.algorithm.wcmp.task;

import com.zlht.pbr.algorithm.wcmp.service.AlgorithmConfigurationServiceI;
import feign.RetryableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 同步算法任务
 *
 * @author zijian Wang
 **/
@Component
public class SyncAlgorithmTask {

    private final static Logger logger = LogManager.getLogger(SyncAlgorithmTask.class);
    @Autowired
    private AlgorithmConfigurationServiceI algorithmConfigurationServiceI;

    @Scheduled(cron = "0/60 * *  * * ? ")
    public void executeSyncAlgorithm() {
        try {
            algorithmConfigurationServiceI.syncAlgorithm();
        } catch (RetryableException retryableException) {
            String errMsg = "同步算法失败！";
            logger.warn("executeSyncAlgorithm(), message={}", errMsg);
        }
    }
}