package com.zlht.pbr.algorithm.wcmp.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 体育能力
 *
 * @author zijian Wang
 */
@Data
public class SportsAbility {
    private Integer id;
    private Integer userId;
    private Integer abilityType;
    private Integer value;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}