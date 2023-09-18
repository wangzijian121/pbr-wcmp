package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author zijian Wang
 */

@Data
@NoArgsConstructor
@TableName("study_record")
public class StudyRecord {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    /**
     * 小程序链接码
     */
    private String linkCode;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 学习时长
     */
    private Integer studyDuration;
    @ApiModelProperty(value = "更新时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;
}