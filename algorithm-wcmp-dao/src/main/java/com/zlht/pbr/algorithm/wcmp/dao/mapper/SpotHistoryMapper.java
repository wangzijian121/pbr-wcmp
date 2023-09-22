package com.zlht.pbr.algorithm.wcmp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface SpotHistoryMapper extends BaseMapper<SpotHistory> {

    /**
     * 查询识别历史
     *
     * @param page
     * @param userId
     * @param linkCode
     * @return
     */
    @Select("select sh.id,\n" +
            "       sh.link_code,\n" +
            "       sh.user_id,\n" +
            "       sh.algorithm_id,\n" +
            "       ac.name,\n" +
            "       sh.result,\n" +
            "       sh.file,\n" +
            "       sh.create_time\n" +
            "from spot_history sh\n" +
            "         left join algorithm_configuration ac on sh.algorithm_id = ac.algorithm_id\n" +
            "where ac.link_code = #{link_code} and user_id =#{user_id} order by sh.create_time  desc ")
    Page<Map<String, Object>> querySpotHistoryPage(Page<?> page,
                                                   @Param("user_id") int userId,
                                                   @Param("link_code") String linkCode);
}
