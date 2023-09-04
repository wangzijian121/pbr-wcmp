package com.zlht.pbr.algorithm.wcmp.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class StudyRecord {
    private Integer id;
    private Integer userId;
    private Integer courseId;
    private Integer lessonId;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Date updateTime;
}