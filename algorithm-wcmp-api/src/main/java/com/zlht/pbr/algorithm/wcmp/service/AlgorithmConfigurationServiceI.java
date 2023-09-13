package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.AlgorithmConfiguration;
import com.zlht.pbr.algorithm.wcmp.utils.Result;

import java.util.Map;

/**
 * 微信服务接口
 *
 * @author zijian Wang
 */
public interface AlgorithmConfigurationServiceI extends BaseServiceI {



    /**
     * 管理员-查询算法
     *
     * @param linkCode
     * @return
     */
    Result<AlgorithmConfiguration> getAlgorithmByAppId(String linkCode);

    /**
     * 管理员-启用/停止算法
     *
     * @param linkCode
     * @param algorithmId
     * @param enable
     * @return
     */
    Map<String, Object> setAlgorithmAvailability(String linkCode, int algorithmId, boolean enable);

    /**
     * 学生-查询算法
     *
     * @param linkCode
     * @return
     */
    Result<AlgorithmConfiguration> getEnableAlgorithm(String linkCode);
}
