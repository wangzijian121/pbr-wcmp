package com.zlht.pbr.algorithm.wcmp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.StudyRecord;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zijian Wang
 */
public interface StudyRecordMapper extends BaseMapper<StudyRecord> {


    /**
     * 本周学习时间
     *
     * @param linkCode
     * @param userId
     * @param startOfWeek
     * @param endOfWeek
     * @return
     */
    @Select("select sum(study_duration)\n" +
            "from study_record\n" +
            "where link_code = #{linkCode}\n" +
            "  and user_id = #{userId}\n" +
            "  and create_time > #{startOfWeek}\n" +
            "  and create_time <= #{endOfWeek}")
    int getStudyTimeWeek(String linkCode, int userId, Date startOfWeek, Date endOfWeek);

    /**
     * 本月学习时间
     *
     * @param linkCode
     * @param userId
     * @param startOfMonth
     * @param endOfMonth
     * @return
     */
    @Select("select sum(study_duration)\n" +
            "from study_record\n" +
            "where link_code = #{linkCode}\n" +
            "  and user_id = #{userId}\n" +
            "  and create_time > #{startOfMonth}\n" +
            "  and create_time <= #{endOfMonth}")
    int getStudyTimeMonth(String linkCode, int userId, Date startOfMonth, Date endOfMonth);

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
     * @param linkCode
     * @param userId
     * @param startOfMonth
     * @param endOfMonth
     * @return
     */
    @Select("select count(1)\n" +
            "from study_record\n" +
            "where link_code = #{linkCode}\n" +
            "  and user_id = #{userId}\n" +
            "  and create_time > #{startOfMonth}\n" +
            "  and create_time <= #{endOfMonth}")
    int getCheckInDaysMonth(String linkCode, int userId, Date startOfMonth, Date endOfMonth);

    /**
     * 本周图表点位
     *
     * @param linkCode
     * @param userId
     * @param startOfWeek
     * @param endOfWeek
     * @return
     */
    @Select("SELECT DATE(create_time) AS study_date, SUM(study_duration) AS total_study_duration\n" +
            "FROM `pose-wcmp`.study_record\n" +
            "where link_code = #{linkCode}\n" +
            "  and user_id = #{userId}\n" +
            "  and create_time > #{startOfWeek}\n" +
            "  and create_time <= #{endOfWeek}\n" +
            "GROUP BY study_date\n" +
            "ORDER BY study_date;")
    List<Map<String, Object>> getPointsWeek(String linkCode, int userId, Date startOfWeek, Date endOfWeek);

    /**
     * 本月图表点位
     *
     * @param linkCode
     * @param userId
     * @param startOfMonth
     * @param endOfMonth
     * @return
     */
    @Select("SELECT DATE(create_time) AS study_date, SUM(study_duration) AS total_study_duration\n" +
            "FROM `pose-wcmp`.study_record\n" +
            "where link_code = #{linkCode}\n" +
            "  and user_id = #{userId}\n" +
            "  and create_time > #{startOfMonth}\n" +
            "  and create_time <= #{endOfMonth}\n" +
            "GROUP BY study_date\n" +
            "ORDER BY study_date;")
    List<Map<String, Object>> getPointMonth(String linkCode, int userId, Date startOfMonth, Date endOfMonth);
}
