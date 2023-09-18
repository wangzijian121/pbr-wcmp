package com.zlht.pbr.algorithm.wcmp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zi jian Wang
 */
public interface ExamMapper extends BaseMapper<Exam> {


    /**
     * 检查用户考试次数
     *
     * @param examId
     * @param userId
     * @return
     */
    @Select("select (select count from exam_record er   where er.exam_id = #{exam_id} and er.user_id = #{user_id}) <\n" +
            "       (select exam_count from exam a where a.id = #{exam_id}) as result")
    boolean checkExamCount(@Param("exam_id") int examId, @Param("user_id") int userId);

}
