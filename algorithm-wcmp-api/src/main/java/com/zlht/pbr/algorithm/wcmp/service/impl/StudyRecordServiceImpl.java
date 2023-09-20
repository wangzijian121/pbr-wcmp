package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.dao.entity.StudyRecord;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.StudyRecordMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.security.AuthLinkCodeServiceI;
import com.zlht.pbr.algorithm.wcmp.service.StudyRecordServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudyRecordServiceImpl extends BaseServiceImpl implements StudyRecordServiceI {

    @Autowired
    private StudyRecordMapper studyRecordMapper;

    @Autowired
    private ManagementClient managementClient;

    @Autowired
    private AuthLinkCodeServiceI authLinkCodeServiceI;

    /**
     * 创建学习记录
     *
     * @param studyRecord
     * @return
     */
    @Override
    public Map<String, Object> createStudyRecord(StudyRecord studyRecord) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            studyRecordMapper.insert(studyRecord);
            map.put("data", studyRecord);
            putMsg(map, Status.SUCCESS.getCode(), "创建学习记录成功！");
            managementClient.reportData(studyRecord.getLinkCode(), "user_usage_time_today", studyRecord.getStudyDuration());
        } catch (Exception e) {
            putMsg(map, 400, "创建学习记录失败！");
        }
        return map;
    }

    /**
     * 查询学习记录
     *
     * @param linkCode
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Result queryStudyRecord(String linkCode, int userId, int currentPage, int pageSize) {
        Result result = new Result();
        if (!authLinkCodeServiceI.checkLinkCodeValidity(linkCode)) {
            result.setMsg("linkCode错误!！");
            result.setCode(400);
            return result;
        }
        Page<StudyRecord> page = new Page<>(currentPage, pageSize);

        Page<Map<String, Object>> examPage = studyRecordMapper.queryStudyRecordPage(page, linkCode,userId);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(examPage.getRecords());
        result.setCode(200);
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(pageInfo);
        return result;
    }
}
