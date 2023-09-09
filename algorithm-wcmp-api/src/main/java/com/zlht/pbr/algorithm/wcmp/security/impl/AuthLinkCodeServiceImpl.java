package com.zlht.pbr.algorithm.wcmp.security.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.LinkCodeAndAppIdMap;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.LinkCodeAndAppIdMapMapper;
import com.zlht.pbr.algorithm.wcmp.security.AuthLinkCodeServiceI;
import com.zlht.pbr.algorithm.wcmp.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zjian Wang
 */
@Service
public class AuthLinkCodeServiceImpl extends BaseServiceImpl implements AuthLinkCodeServiceI {
    @Autowired
    private LinkCodeAndAppIdMapMapper linkCodeAndAppIdMapMapper;

    @Override
    public boolean checkLinkCodeValidity(String linkCode) {
        QueryWrapper<LinkCodeAndAppIdMap> queryWrapper = new QueryWrapper();
        return linkCodeAndAppIdMapMapper.exists(queryWrapper);
    }
}
