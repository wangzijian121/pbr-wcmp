package com.zlht.pbr.algorithm.wcmp.client;


import com.zlht.pbr.algorithm.wcmp.dao.entity.AlgorithmConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.entity.LinkCodeAndAppIdMap;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * 微信客户端
 *
 * @author zijian Wang
 */
@FeignClient(url = "${remote.management.ip}", name = "management")
public interface ManagementClient {

    /**
     * 更新机构授权的算法
     *
     * @return
     */
    @RequestMapping(value = "/wechat/getInstitutionAlgorithm", method = RequestMethod.GET)
    Result<List<AlgorithmConfiguration>> syncAlgorithm();


    /**
     * 根据appId更新机构算法
     *
     * @return
     */
    @RequestMapping(value = "/wechat/getInstitutionLinkCodeAndAppId", method = RequestMethod.GET)
    Result<List<LinkCodeAndAppIdMap>> syncLinkCodeAndAppId();

}
