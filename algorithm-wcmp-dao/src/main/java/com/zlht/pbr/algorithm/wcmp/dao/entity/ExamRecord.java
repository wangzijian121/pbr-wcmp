package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 考试记录
 *
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "exam_record")
public class ExamRecord {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String linkCode;
    private String userId;
    private String examId;
    private Integer count;
    private Integer score;
    @ApiModelProperty(name = "create_time", value = "提交时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date time;

}