package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 学习数据
 *
 * @author zijian Wang
 */
@Data
public class StudyData {
    private Integer id;
    private Integer userId;
    private Integer courseId;
    private Integer duration;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}