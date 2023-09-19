package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zijian Wang
 */
@Data
public class SpotHistory {

    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String linkCode;
    private Integer userId;
    private Integer algorithmId;
    private String result;
    private String file;

    @ApiModelProperty(value = "更新时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;
}



