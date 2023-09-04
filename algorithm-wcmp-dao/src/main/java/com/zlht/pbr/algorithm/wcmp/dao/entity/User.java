package com.zlht.pbr.algorithm.wcmp.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * 用户表
 * @author zi jian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user", autoResultMap = true)
public class User {

    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @ApiModelProperty(value = "用户类型（0:管理员 1:学生）", required = true)
    private Integer type;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "昵称", required = false)
    private String nickname;

    @ApiModelProperty(value = "性别(0:女 1:男)", required = true)
    private Integer gender;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "使用时长")
    private long durationOfUse;

    @ApiModelProperty(value = "打卡天数")
    private Integer clockingDays;

    @ApiModelProperty(value = "创建时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;

    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "", required = true)
    private Map<String, Object> attr;
}