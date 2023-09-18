package com.zlht.pbr.algorithm.wcmp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学习数据
 *
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyData {


    private Integer studyTimeToday;
    private Integer studyTimeWeek;
    private Integer studyTimeMonth;


    private Integer checkInTotal;
    private Integer checkInDaysWeek;
    private Integer checkInDaysMonth;

    private List<Map<String, Object>> pointsWeek;

    private List<Map<String, Object>> pointMonth;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;
}