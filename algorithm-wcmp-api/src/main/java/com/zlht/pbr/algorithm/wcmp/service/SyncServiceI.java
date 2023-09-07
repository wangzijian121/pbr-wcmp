package com.zlht.pbr.algorithm.wcmp.service;

/**
 * 微信服务接口
 *
 * @author zijian Wang
 */
public interface SyncServiceI extends BaseServiceI {


    /**
     * 同步算法
     *
     * @return
     */
    void syncAlgorithm();

    /**
     * 同步机构链接代码与appID 的映射
     */
    void syncInstitutionLinkCodeAndAppId();
}
