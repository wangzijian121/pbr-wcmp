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
 * 学习表
 *
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "course")
public class Course {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String linkCode;
    private String name;
    private Integer type;
    private String coverImg;
    private String mediaFile;
    private String description;
    @ApiModelProperty(name = "create_time", value = "创建时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;

    @ApiModelProperty(name = "update_time", value = "更新时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date updateTime;
}

