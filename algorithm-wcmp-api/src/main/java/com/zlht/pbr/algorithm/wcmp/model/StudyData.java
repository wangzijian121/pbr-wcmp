package com.zlht.pbr.algorithm.wcmp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 学习数据
 *
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyData {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private String linkCode;

    @ApiModelProperty(value = "用户ID", required = true)
    private Integer userId;

    private Integer studyTimeWeek;

    private Integer studyTimeMonth;

    private Integer checkInDaysWeek;

    private Integer checkInDaysMonth;

    private String pointsWeek;

    private String pointMonth;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;
}