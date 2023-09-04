package com.zlht.pbr.algorithm.wcmp.remote.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zi jian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteResult<T> {

    private Integer code;

    /**
     * message
     */
    private String msg;

    /**
     * data
     */
    private T data;

}