package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.model.StudyData;
import com.zlht.pbr.algorithm.wcmp.utils.Result;

import java.util.Map;

/**
 * @author zijian Wang
 */
public interface UserServiceI extends BaseServiceI {

    /**
     * 用户登录
     *
     * @param linCode
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    Map<String, Object> login(String linCode, String code, String encryptedData, String iv);

    /**
     * 通过用户ID获取学习数据
     *
     * @param linkCode
     * @param userId
     * @return Map<String, Object>
     */
    Result<StudyData> getUserStudyDataByUserId(String linkCode, int userId);


    /**
     * 通过用户Id获取用户体育能力
     *
     * @param linkCode
     * @param userId
     * @return
     */
    Map<String,Object> getUserAbilityByUserId(String linkCode, int userId);

}
