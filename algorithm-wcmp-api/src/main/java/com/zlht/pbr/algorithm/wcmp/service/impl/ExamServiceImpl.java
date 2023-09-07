package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.controller.admin.AlgorithmConfigurationController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.ExamMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 考试服务
 *
 * @author zijian Wang
 */
@Service
public class ExamServiceImpl extends BaseServiceImpl implements ExamServiceI {

    private static final Logger logger = LogManager.getLogger(ExamServiceImpl.class);
    /**
     * 文件磁盘路径
     */
    @Value("${files.upload.local.path}")
    private String fileUploadPath;


    @Autowired
    private ExamMapper examMapper;

    @Override
    public ResponseEntity downloadExamXlsxTemplate() {

        Map<String, Object> map = new HashMap<>(3);
        String fileName = "考试模板.xlsx";
        // 从文件存储中读取文件
        File file = new File(fileUploadPath + fileName);
        InputStreamResource inputStreamResource = null;
        try {
            inputStreamResource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            String errMsg = "文件未找到";
            logger.error("downloadExamXlsxTemplate() method .message={}", errMsg, e);
            throw new RuntimeException(e);
        }
        // 设置HTTP头
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(inputStreamResource);
    }

    @Override
    public Map<String, Object> checkExamXlsxTemplate(String uuid) {
        return null;
    }

    @Override
    public Result<PageInfo<Exam>> queryExamList(int currentPage, int pageSize, String appId) {
        Result result = new Result();
        Page<Exam> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Exam> wapper = new QueryWrapper<Exam>();
        if (appId != null) {
            wapper.eq("app_id", appId);
        }
        Page<Exam> examPage = examMapper.selectPage(page, wapper);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(examPage.getRecords());
        result.setCode(200);
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(pageInfo);
        return result;
    }

    @Override
    public Map<String, Object> createExam(Exam exam) {
        Map<String, Object> map = new HashMap<>(3);

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", exam.getName());
        //exist?
        if (examMapper.exists(queryWrapper)) {
            putMsg(map, 400, "考试已存在!");
            return map;
        }
        try {
            examMapper.insert(exam);
            putMsg(map, Status.SUCCESS.getCode(), "创建考试成功！");
        } catch (Exception e) {
            String errMsg = "创建考试失败";
            logger.error("createExam() method .message={}, exam={}", errMsg, exam, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> updateExam(int id, Exam exam) {
        Map<String, Object> map = new HashMap<>(3);

        if (!checkExamExistById(id)) {
            putMsg(map, 400, "所更新的考试ID不存在!");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", exam.getName());
        checkWrapper.ne("id", id);
        //exist?
        if (examMapper.exists(checkWrapper)) {
            putMsg(map, 400, "考试名已存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            putMsg(map, Status.SUCCESS.getCode(), "更新考试成功！");
            examMapper.update(exam, queryWrapper);
        } catch (Exception e) {
            String errMsg = "更新考试失败";
            logger.error("updateExam() method .message={}, exam={}", errMsg, exam, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteExam(int id) {
        Map<String, Object> map = new HashMap<>(3);
        if (!checkExamExistById(id)) {
            putMsg(map, 400, "所删除的考试ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            examMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除考试成功！");
        } catch (Exception e) {
            String errMsg = "删除考试失败";
            logger.error("deleteExam() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public boolean checkExamExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return examMapper.exists(queryWrapper);
    }
}
