package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.wcmp.controller.admin.AlgorithmConfigurationController;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Exam;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    public Result<PageInfo<Exam>> queryExamList(int type, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public Map<String, Object> createExam(Exam exam) {
        return null;
    }

    @Override
    public Map<String, Object> updateExam(int id, Exam exam) {
        return null;
    }

    @Override
    public Map<String, Object> deleteExam(int id) {
        return null;
    }

    @Override
    public boolean checkExamExistById(int id) {
        return false;
    }
}
