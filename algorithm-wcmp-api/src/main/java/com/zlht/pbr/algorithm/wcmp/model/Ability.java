package com.zlht.pbr.algorithm.wcmp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 考试表
 *
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ability {

    private Map<String, Object> score;
    private Map<String, Object> ability;

}