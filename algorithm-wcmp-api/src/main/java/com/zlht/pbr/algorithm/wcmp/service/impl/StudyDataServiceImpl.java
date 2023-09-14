package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.zlht.pbr.algorithm.wcmp.dao.mapper.StudyRecordMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.model.StudyData;
import com.zlht.pbr.algorithm.wcmp.service.StudyDataServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import com.zlht.pbr.algorithm.wcmp.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
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
        Map<String, Date> weekDateMap = TimeUtils.getCurrentWeekRange(new Date());
        Date startOfWeek = weekDateMap.get("startOfWeek");
        Date endOfWeek = weekDateMap.get("endOfWeek");

        Map<String, Date> monthDateMap = TimeUtils.getCurrentMonthRange(new Date());
        Date startOfMonth = monthDateMap.get("startOfMonth");
        Date endOfMonth = monthDateMap.get("endOfMonth");

        int studyTimeWeek = studyRecordMapper.getStudyTimeWeek(linkCode, userId, startOfWeek, endOfWeek);
        int studyTimeMonth = studyRecordMapper.getStudyTimeMonth(linkCode, userId, startOfMonth, endOfMonth);
        int checkInDaysWeekCount = studyRecordMapper.getCheckInDaysWeek(linkCode, userId, startOfWeek, endOfWeek);
        int checkInDaysMonthCount = studyRecordMapper.getCheckInDaysMonth(linkCode, userId, startOfMonth, endOfMonth);
        List<Map<String, Object>> pointsWeek = studyRecordMapper.getPointsWeek(linkCode, userId, startOfWeek, endOfWeek);
        List<Map<String, Object>> pointsMonth = studyRecordMapper.getPointMonth(linkCode, userId, startOfMonth, endOfMonth);

        StudyData studyData = new StudyData();
        studyData.setStudyTimeWeek(studyTimeWeek);
        studyData.setStudyTimeMonth(studyTimeMonth);
        studyData.setCheckInDaysWeek(checkInDaysWeekCount);
        studyData.setCheckInDaysMonth(checkInDaysMonthCount);
        studyData.setPointsWeek(pointsWeek);
        studyData.setPointMonth(pointsMonth);

        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(studyData);
        return result;
    }
}
