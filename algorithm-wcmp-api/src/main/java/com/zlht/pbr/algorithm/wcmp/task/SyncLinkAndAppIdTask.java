package com.zlht.pbr.algorithm.wcmp.task;

import com.zlht.pbr.algorithm.wcmp.service.impl.SyncServiceImpl;
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
public class SyncLinkAndAppIdTask {

    private final static Logger logger = LogManager.getLogger(SyncLinkAndAppIdTask.class);
    @Autowired
    private SyncServiceImpl syncService;

    @Scheduled(cron = "0/6 10 *  * * ? ")
    public void executeSyncLinkAndAppId() {
        try {
            syncService.syncInstitutionLinkCodeAndAppId();
        } catch (RetryableException retryableException) {
            String errMsg = "同步链接和小程序ID失败！";
            logger.warn("executeSyncLinkAndAppId(), message={}", errMsg);
        }
    }
}