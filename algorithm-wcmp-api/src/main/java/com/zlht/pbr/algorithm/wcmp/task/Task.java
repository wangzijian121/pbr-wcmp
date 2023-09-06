package com.zlht.pbr.algorithm.wcmp.task;

import com.zlht.pbr.algorithm.wcmp.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Algorithm;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务的使用
 *
 * @author
 **/
@Component
public class Task {
    @Autowired
    ManagementClient managementClient;

    @Scheduled(cron = "0/5 * *  * * ? ")
    public void execute() {
        Result<List<Algorithm>> result = managementClient.syncAlgorithm("wxc62afc144417346e");
        List<Algorithm> algorithmList = result.getData();
        for (Algorithm algorithm : algorithmList) {
            System.out.println(algorithm);
        }
    }
}