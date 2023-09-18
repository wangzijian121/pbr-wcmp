package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.ExamRecord;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;

import java.util.Map;

/**
 * 考试记录接口
 *
 * @author zijian Wang
 */
public interface ExamRecordServiceI extends BaseServiceI {


    /**
     * 学生-考试完成，录入成绩
     *
     * @param linkCode
     * @param examRecord
     * @return
     */
    Map<String, Object> commitExam(String linkCode, ExamRecord examRecord);


    /**
     * 学生-查询成绩
     *
     * @param linkCode
     * @param userId
     * @param examId
     * @return
     */
    Map<String, Object> queryExamScore(String linkCode, int userId, int examId);

    /**
     * 机构管理员-查询成绩
     *
     * @param linkCode
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<ExamRecord>> queryAllExamScoreList(String linkCode, int currentPage, int pageSize);
}
