package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "algorithm_configuration")
public class AlgorithmConfiguration {


    @ApiModelProperty(value = "小程序ID")
    private String linkCode;

    @ApiModelProperty(value = "算法唯一ID", required = true)
    private Integer algorithmId;

    @ApiModelProperty(value = "算法名称", required = true)
    private String name;

    @ApiModelProperty(value = "算法调用地址", required = true)
    private String url;

    @ApiModelProperty(value = "体育种类")
    private String sportCategory;

    @ApiModelProperty(value = "是否启用 (1：启用 0：禁用)")
    private Integer enable;

    @ApiModelProperty(value = "模板")
    private String content;

    @ApiModelProperty(value = "更新时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date updateTime;
}