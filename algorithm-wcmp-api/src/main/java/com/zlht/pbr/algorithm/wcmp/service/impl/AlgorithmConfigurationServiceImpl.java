package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.AlgorithmConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.entity.LinkCodeAndAppIdMap;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.AlgorithmConfigurationMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.LinkCodeAndAppIdMapMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.AlgorithmConfigurationServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 算法配置
 *
 * @author zijian Wang
 */
@Service
public class AlgorithmConfigurationServiceImpl extends BaseServiceImpl implements AlgorithmConfigurationServiceI {

    private static final Logger logger = LogManager.getLogger(AlgorithmConfigurationServiceImpl.class);

    @Autowired
    private LinkCodeAndAppIdMapMapper linkCodeAndAppIdMapMapper;

    @Autowired
    private AlgorithmConfigurationMapper algorithmConfigurationMapper;


    @Override
    public Result<AlgorithmConfiguration> getAlgorithmByAppId(String linkCode) {

        Result result = new Result();
        QueryWrapper<LinkCodeAndAppIdMap> queryWrapperLink = new QueryWrapper<>();
        queryWrapperLink.eq("link_code",linkCode);
        LinkCodeAndAppIdMap linkCodeAndAppIdMap = linkCodeAndAppIdMapMapper.selectOne(queryWrapperLink);

        QueryWrapper<AlgorithmConfiguration> queryWrapper = new QueryWrapper();
        queryWrapper.eq("app_id", linkCodeAndAppIdMap.getAppId());
        List<AlgorithmConfiguration> algorithmConfigurationList = algorithmConfigurationMapper.selectList(queryWrapper);
        result.setData(algorithmConfigurationList);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> setAlgorithmAvailability(String linkCode, int algorithmId, boolean enable) {
        Map<String, Object> map = new HashMap<>(3);
        QueryWrapper<LinkCodeAndAppIdMap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("link_code",linkCode);
        LinkCodeAndAppIdMap linkCodeAndAppIdMap = linkCodeAndAppIdMapMapper.selectOne(queryWrapper);

        UpdateWrapper<AlgorithmConfiguration> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("app_id", linkCodeAndAppIdMap.getAppId());
        updateWrapper.eq("algorithm_id", algorithmId);
        updateWrapper.set("enable", enable);
        try {
            algorithmConfigurationMapper.update(null, updateWrapper);
            putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        } catch (Exception e) {
            String errMsg = "更新算法启用失败！";
            logger.error("setAlgorithmAvailability() method .message={}, linkCode={} ,algorithmId={}", errMsg, linkCode, algorithmId);
            putMsg(map, 400, errMsg);
        }
        return map;
    }
}
