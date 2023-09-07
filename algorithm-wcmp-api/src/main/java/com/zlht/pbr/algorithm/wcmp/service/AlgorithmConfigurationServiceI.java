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
     * 查询算法
     *
     * @param appId
     * @return
     */
    Result<AlgorithmConfiguration> getAlgorithmByAppId(String appId);

    /**
     * 启用/停止算法
     *
     * @param appId
     * @param algorithmId
     * @param enable
     * @return
     */
    Map<String, Object> setAlgorithmAvailability(String appId, int algorithmId, boolean enable);
}
