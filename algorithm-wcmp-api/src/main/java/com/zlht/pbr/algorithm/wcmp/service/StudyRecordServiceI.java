package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.StudyRecord;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zijian Wang
 */
@RestController
public interface StudyRecordServiceI extends BaseServiceI {

    /**
     * 创建学习记录
     *
     * @param studyRecord
     * @return
     */
    Map<String, Object> createStudyRecord(StudyRecord studyRecord);

    /**
     * 查询学习记录
     *
     * @param linkCode
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result queryStudyRecord(String linkCode, int userId, int currentPage, int pageSize);

}
