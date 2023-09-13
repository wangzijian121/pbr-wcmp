package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.dao.entity.ExamRecord;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.ExamRecordMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.ExamRecordServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class ExamRecordServiceImpl extends BaseServiceImpl implements ExamRecordServiceI {

    @Autowired
    private ExamRecordMapper examRecordMapper;

    @Override
    public Map<String, Object> commitExam(String linkCode, ExamRecord examRecord) {

        Map<String, Object> map = new HashMap<>(3);
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.eq("exam_id", examRecord.getExamId());
        queryWrapper.eq("user_id", examRecord.getUserId());
        ExamRecord examRecordHistory = examRecordMapper.selectOne(queryWrapper);
        if (examRecordHistory != null) {
            examRecordHistory.setCount(examRecordHistory.getCount() + 1);
            examRecordHistory.setScore(examRecord.getScore());
            examRecordMapper.updateById(examRecordHistory);

        } else {
            examRecord.setLinkCode(linkCode);
            examRecordMapper.insert(examRecord);
        }
        putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        return map;
    }

    @Override
    public Map<String, Object> queryExamScore(String linkCode, int userId, int examId) {
        Map<String, Object> map = new HashMap<>(3);
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.eq("exam_id", examId);
        queryWrapper.eq("user_id", userId);
        ExamRecord examRecord = examRecordMapper.selectOne(queryWrapper);
        map.put("data", examRecord);
        putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        return map;
    }
}
