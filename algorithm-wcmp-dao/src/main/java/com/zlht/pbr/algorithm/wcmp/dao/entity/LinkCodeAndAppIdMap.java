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
@TableName(value = "link_code_and_appid_map")
public class LinkCodeAndAppIdMap {

    private String linkCode;
    private String appId;
    private String appSecret;
    @ApiModelProperty(value = "更新时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date updateTime;
}