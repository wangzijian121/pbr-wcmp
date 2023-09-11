package com.zlht.pbr.algorithm.wcmp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.LinkCodeAndAppIdMap;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface LinkCodeAndAppIdMapMapper extends BaseMapper<LinkCodeAndAppIdMap> {
    /**
     * 通过linkCode 查询 appId 和 secret
     * @param linkCode
     * @return
     */
    @Select("select app_id as appId , app_secret as appSecret from   link_code_and_appid_map where link_code=#{link_code} ")
    Map<String, String> selectMapByLinkCode(@Param("link_code") String linkCode);
}
