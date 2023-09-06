package com.zlht.pbr.algorithm.wcmp.task;

import com.zlht.pbr.algorithm.wcmp.service.AlgorithmConfigurationServiceI;
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
    @Autowired
    private AlgorithmConfigurationServiceI algorithmConfigurationServiceI;

    @Scheduled(cron = "0/60 * *  * * ? ")
    public void execute() {
        algorithmConfigurationServiceI.syncAlgorithm();
    }
}