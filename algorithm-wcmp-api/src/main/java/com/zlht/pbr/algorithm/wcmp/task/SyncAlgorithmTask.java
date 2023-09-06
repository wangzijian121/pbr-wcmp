package com.zlht.pbr.algorithm.wcmp.task;

import com.zlht.pbr.algorithm.wcmp.service.AlgorithmServiceI;
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
     private AlgorithmServiceI algorithmServiceI;

    @Scheduled(cron = "0/5 * *  * * ? ")
    public void execute() {
        algorithmServiceI.syncAlgorithm();
    }
}