package com.zlht.pbr.algorithm.wcmp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.dao.entity.ExamRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface ExamRecordMapper extends BaseMapper<ExamRecord> {

    /**
     * 查询考试成绩
     *
     * @param page
     * @param linkCode
     * @param examId
     * @param keyword
     * @return
     */
    @Select("select er.id," +
            " er.link_code as linkCode," +
            " er.user_id as userId," +
            " u.nickname ," +
            " er.exam_id as examId," +
            " e.name," +
            " er.count," +
            " er.score," +
            " er.time" +
            " from exam_record er" +
            "         left join exam e on e.id = er.exam_id" +
            "         left join user u on er.user_id = u.id" +
            " where er.link_code = #{link_code}" +
            " and (#{exam_id}  = -1 OR er.exam_id=#{exam_id}) " +
            " and (#{keyword} IS NULL OR u.nickname LIKE CONCAT('%', #{keyword}, '%'))")
    Page<Map<String, Object>> queryExamRecordPage(Page<?> page,
                                                  @Param("link_code") String linkCode,
                                                  @Param("exam_id") Integer examId,
                                                  @Param("keyword") String keyword);
}
