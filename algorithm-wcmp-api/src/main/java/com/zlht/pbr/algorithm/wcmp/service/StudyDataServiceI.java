package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.model.StudyData;
import com.zlht.pbr.algorithm.wcmp.utils.Result;

import java.util.Map;

/**
 * @author zijian Wang
 */
public interface StudyDataServiceI extends BaseServiceI {

    /**
     * 通过用户ID获取学习数据
     *
     * @param linkCode
     * @param userId
     * @return Map<String,Object>
     */
    Result<StudyData> getUserStudyDataByUserId(String linkCode, int userId);

}
