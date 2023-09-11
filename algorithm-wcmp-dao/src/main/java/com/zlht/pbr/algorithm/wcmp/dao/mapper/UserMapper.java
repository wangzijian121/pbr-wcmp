package com.zlht.pbr.algorithm.wcmp.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author zi jian Wang
 */
public interface UserMapper extends BaseMapper<User> {


    /**
     * 通过openId更新session_key
     *
     * @param linCode
     * @param sessionKey
     * @param openId
     */
    @Update("update user set session_key=#{session_key} where open_id=#{open_id}")
    void updateSessionKeyByOpenId(@Param("link_code") String linCode,
                                  @Param("session_key") String sessionKey,
                                  @Param("open_id") String openId);

}
