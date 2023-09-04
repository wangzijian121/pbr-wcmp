package com.zlht.pbr.algorithm.wcmp.api.remote.service;

import com.zlht.pbr.algorithm.wcmp.remote.model.Earining;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.util.MultiValueMap;

/**
 * @author zi jian Wang
 */
public interface EarningServiceI {

    /**
     * 获取收益
     * @param userId
     * @param currentPage
     * @param pageSize
     * @param name
     * @param values
     * @return
     */
    Result<Earining> getEarning(int userId, int currentPage, int pageSize, String name, MultiValueMap<String, String> values);
}
