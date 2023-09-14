package com.zlht.pbr.algorithm.wcmp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.StudyRecord;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.Map;

/**
 * @author zijian Wang
 */
public interface StudyRecordMapper extends BaseMapper<StudyRecord> {


    /**
     * 本周学习时间
     *
     * @param date
     * @return
     */
    @Select("")
    int getStudyTimeWeek(Date date);

    /**
     * 本月学习时间
     *
     * @param date
     * @return
     */
    @Select("")
    int getStudyTimeMonth(Date date);

    /**
     * 本周签到次数
     *
     * @param linkCode
     * @param userId
     * @param startOfWeek
     * @param endOfWeek
     * @return
     */
    @Select("select count(1)\n" +
            "from study_record\n" +
            "where link_code = #{linkCode}\n" +
            "  and user_id = #{userId}\n" +
            "  and create_time > #{startOfWeek}\n" +
            "  and create_time <= #{endOfWeek}")
    int getCheckInDaysWeek(String linkCode, int userId, Date startOfWeek, Date endOfWeek);

    /**
     * 本月签到次数
     *
     * @param date
     * @return
     */
    @Select("")
    int getCheckInDaysMonth(Date date);

    /**
     * 本周图表点位
     *
     * @param date
     * @return
     */
    @Select("")
    Map<String, Object> getPointsWeek(Date date);

    /**
     * 本月图表点位
     *
     * @param date
     * @return
     */
    @Select("")
    Map<String, Object> getPointMonth(Date date);
}
