package com.zlht.pbr.algorithm.wcmp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zi jian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @ApiModelProperty(value = "提交名", required = true)
    private String commitName;
    @ApiModelProperty(value = "支持的体育类型", required = true)
    private String sportType;
    @ApiModelProperty(value = "开发者ID", required = true)
    private Integer wcmpId;
    @ApiModelProperty(value = "数据集类型(0普通数据集 1专用数据集)", required = true)
    private int type;
    @ApiModelProperty(value = "算法或数据集文件", required = true)
    private String file;
    @ApiModelProperty(value = "文档", required = true)
    private String docs;
    @ApiModelProperty(value = "数据集样例", required = true)
    private String demo;
    @ApiModelProperty(value = "mark", required = true)
    private String mark;
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;
    @ApiModelProperty(value = "提交时间", required = true)
    private Date createTime;

}