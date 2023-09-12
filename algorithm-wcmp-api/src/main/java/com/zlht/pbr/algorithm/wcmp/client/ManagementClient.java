package com.zlht.pbr.algorithm.wcmp.client;


import com.zlht.pbr.algorithm.wcmp.dao.entity.AlgorithmConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.entity.LinkCodeAndAppIdMap;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


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


    /**
     * 是否为管理员
     *
     * @param linkCode
     * @param openId
     * @return
     */
    @RequestMapping(value = "/wechat/adminOrNot", method = RequestMethod.POST)
    Result<Map<String, Object>> adminOrNot(@RequestParam String linkCode, @RequestParam String openId);

    /**
     * 上报用户数据
     *
     * @param linkCode
     * @param event
     * @return
     */
    @RequestMapping(value = "/wechat/reportUserData", method = RequestMethod.POST)
    Result<Map<String, Object>> reportUser(@RequestBody Map<String, Object> linkCode, @RequestParam int event);
}
