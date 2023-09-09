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
     * 机构管理员-下载试题模板文件
     *
     * @return
     */
    ResponseEntity downloadExamXlsxTemplate();


    /**
     * 机构管理员-检查模板规范性
     *
     * @param uuid uuid
     * @return
     */
    Map<String, Object> checkExamXlsxTemplate(String uuid);

    /**
     * 查询考试
     *
     * @param linkCode
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Exam>> queryExamList(String  linkCode,int currentPage, int pageSize);


    /**
     * 创建考试
     *
     * @param exam
     * @return
     */

    Map<String, Object> createExam(String linkCode,Exam exam);

    /**
     * 更新考试
     *
     * @param linkCode
     * @param id
     * @param exam
     * @return
     */
    Map<String, Object> updateExam(String  linkCode,int id, Exam exam);

    /**
     * 删除考试
     *
     * @param linkCode
     * @param id
     * @return
     */
    Map<String, Object> deleteExam(String  linkCode,int id);


    /**
     * 通过考试ID判断是否存在
     *
     * @param linkCode
     * @param id
     * @return
     */
    boolean checkExamExistById(String linkCode,int id);
}
