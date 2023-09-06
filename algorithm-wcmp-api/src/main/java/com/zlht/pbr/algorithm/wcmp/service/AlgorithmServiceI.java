package com.zlht.pbr.algorithm.wcmp.service;

import java.util.Map;

/**
 * 微信服务接口
 *
 * @author zijian Wang
 */
public interface AlgorithmServiceI extends BaseServiceI {


    /**
     * 同步算法
     *
     * @return
     */
    Map<String, Object> syncAlgorithm();
}
