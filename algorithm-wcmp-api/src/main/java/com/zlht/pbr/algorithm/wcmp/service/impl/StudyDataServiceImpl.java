package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.zlht.pbr.algorithm.wcmp.dao.mapper.StudyRecordMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.model.StudyData;
import com.zlht.pbr.algorithm.wcmp.service.StudyDataServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import com.zlht.pbr.algorithm.wcmp.utils.TimeUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
public class StudyDataServiceImpl extends BaseServiceImpl implements StudyDataServiceI {

    @Autowired
    private StudyRecordMapper studyRecordMapper;

    /**
     * 通过用户ID获取学习数据
     *
     * @param linkCode
     * @param userId
     * @return
     */
    @Override
    public Result<StudyData> getUserStudyDataByUserId(String linkCode, int userId) {

        Result<StudyData> result = new Result<>();
//        本周时间范围
        Map<String, Date> dateMap = TimeUtils.getCurrentWeekRange(new Date());
        Date startOfWeek = dateMap.get("startOfWeek");
        Date endOfWeek = dateMap.get("endOfWeek");
        int count = studyRecordMapper.getCheckInDaysWeek(linkCode, userId, startOfWeek, endOfWeek);
        StudyData studyData = new StudyData();
        studyData.setCheckInDaysWeek(count);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(studyData);
        return result;
    }
}
