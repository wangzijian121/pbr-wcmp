package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * 考试服务接口
 *
 * @author zijian Wang
 */
public interface ExamServiceI extends BaseServiceI {


    /**
     * 下载试题模板文件
     *
     * @return
     */
    ResponseEntity downloadExamXlsxTemplate();


    /**
     * 检查模板规范性
     *
     * @param uuid uuid
     * @return
     */
    Map<String, Object> checkExamXlsxTemplate(String uuid);

    /**
     * 查询考试
     *
     * @param type
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Exam>> queryExamList(int type, int currentPage, int pageSize);


    /**
     * 创建考试
     *
     * @param
     * @param exam
     * @return
     */

    Map<String, Object> createExam(Exam exam);

    /**
     * 更新考试
     *
     * @param
     * @param id
     * @param exam
     * @return
     */
    Map<String, Object> updateExam(int id, Exam exam);

    /**
     * 删除考试
     *
     * @param
     * @param id
     * @return
     */
    Map<String, Object> deleteExam(int id);


    /**
     * 通过考试ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkExamExistById(int id);
}
