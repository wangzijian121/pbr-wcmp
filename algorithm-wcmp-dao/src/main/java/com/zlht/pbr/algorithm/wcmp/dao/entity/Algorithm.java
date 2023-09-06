package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

/**
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "algorithm")
public class Algorithm {

    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(value = "小程序ID")
    private String appId;

    @ApiModelProperty(value = "算法名称", required = true)
    private String name;

    @ApiModelProperty(value = "体育种类")
    private String sport_category;

    @ApiModelProperty(value = "模板")
    private Integer content;

    @ApiModelProperty(value = "0：启用 1：未启用")
    private Integer enable;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;
}