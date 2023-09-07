package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 考试表
 *
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "exam")
public class Exam {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private String name;

    private String description;

    @ApiModelProperty(value = "考试开始时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date startTime;

    @ApiModelProperty(value = "考试结束时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date endTime;

    private String uuid;

    private Integer examCount;

    @ApiModelProperty(name = "create_time", value = "创建时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;

}