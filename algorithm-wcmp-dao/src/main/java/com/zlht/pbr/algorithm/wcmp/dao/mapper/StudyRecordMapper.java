package com.zlht.pbr.algorithm.wcmp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.dao.entity.StudyRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zijian Wang
 */
public interface StudyRecordMapper extends BaseMapper<StudyRecord> {

    /**
     * 查询学习记录
     *
     * @param page
     * @param linkCode
     * @param userId
     * @return
     */
    @Select("select sr.id," +
            "       sr.link_code as linkCode," +
            "       sr.user_id as userId," +
            "       sr.course_id as courseId," +
            "       c.name," +
            "       c.cover_img coverImg," +
            "       sr.study_duration as studyDuration," +
            "       sr.create_time as createTime" +
            " from  course c" +
            "         left join study_record sr on c.id = sr.course_id" +
            " where sr.link_code = #{link_code}" +
            "  and user_id = #{user_id}")
    Page<Map<String, Object>> queryStudyRecordPage(Page<?> page,
                                                   @Param("link_code") String linkCode,
                                                   @Param("user_id") int userId);

    /**
     * 获取今日学习时间
     *
     * @param linkCode
     * @param userId
     * @return
     */
    @Select("select ifnull(sum(study_duration),0)" +
            " from study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}" +
            "  and Date(create_time) = CURDATE()")
    int getStudyTimeToday(String linkCode, int userId);

    /**
     * 获取签到天数
     *
     * @param linkCode
     * @param userId
     * @return
     */
    @Select("select count(distinct date(create_time))" +
            " from study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}")
    int getCheckInTotal(String linkCode, int userId);

    /**
     * 本周学习时间
     *
     * @param linkCode
     * @param userId
     * @param startOfWeek
     * @param endOfWeek
     * @return
     */
    @Select("select ifnull(sum(study_duration),0)" +
            " from study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}" +
            "  and create_time >#{startOfWeek}" +
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
    @Select("select ifnull(sum(study_duration),0)" +
            " from study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}" +
            "  and create_time >= #{startOfMonth}" +
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
    @Select("select count(1)" +
            " from study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}" +
            "  and create_time >#{startOfWeek}" +
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
    @Select("select count(1)" +
            " from study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}" +
            "  and create_time >= #{startOfMonth}" +
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
    @Select("SELECT DATE(create_time) AS study_date, SUM(study_duration) AS total_study_duration" +
            " FROM study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}" +
            "  and create_time > #{startOfWeek}" +
            "  and create_time <= #{endOfWeek}" +
            " GROUP BY study_date" +
            " ORDER BY study_date;")
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
    @Select("SELECT DATE(create_time) AS study_date, SUM(study_duration) AS total_study_duration" +
            " FROM study_record" +
            " where link_code = #{linkCode}" +
            "  and user_id = #{userId}" +
            "  and create_time >= #{startOfMonth}" +
            "  and create_time <= #{endOfMonth}" +
            " GROUP BY study_date" +
            " ORDER BY study_date;")
    List<Map<String, Object>> getPointMonth(String linkCode, int userId, Date startOfMonth, Date endOfMonth);
}
