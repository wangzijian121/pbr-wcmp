package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.dao.entity.ExamRecord;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.ExamMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.ExamRecordMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.security.AuthLinkCodeServiceI;
import com.zlht.pbr.algorithm.wcmp.service.ExamRecordServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
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

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private AuthLinkCodeServiceI authLinkCodeServiceI;

    @Override
    public Map<String, Object> commitExam(String linkCode, ExamRecord examRecord) {

        Map<String, Object> map = new HashMap<>(3);
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.eq("exam_id", examRecord.getExamId());
        queryWrapper.eq("user_id", examRecord.getUserId());
        ExamRecord examRecordHistory = examRecordMapper.selectOne(queryWrapper);

        if (examRecordHistory != null) {
            boolean check=examMapper.checkExamCount(examRecord.getExamId(), examRecord.getUserId());
            if (!check) {
                putMsg(map, 400, "提交次数用尽");
                return map;
            }
            examRecordHistory.setCount(examRecordHistory.getCount() + 1);
            examRecordHistory.setScore(examRecord.getScore());
            examRecordMapper.updateById(examRecordHistory);
        } else {
            examRecord.setLinkCode(linkCode);
            examRecord.setCount(1);
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

    /**
     * 机构管理员-查询成绩
     *
     * @param linkCode
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Result queryAllExamScoreList(String linkCode, int currentPage, int pageSize,String nickname,int  examId) {

        Result result = new Result();
        if (!authLinkCodeServiceI.checkLinkCodeValidity(linkCode)) {
            result.setMsg("linkCode错误!！");
            result.setCode(400);
            return result;
        }
        Page<ExamRecord> page = new Page<>(currentPage, pageSize);

        Page<Map<String, Object>> examPage = examRecordMapper.queryExamRecordPage(page,linkCode,examId,nickname);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(examPage.getRecords());
        result.setCode(200);
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(pageInfo);
        return result;
    }
}
