package com.zlht.pbr.algorithm.wcmp.security;

/**
 * @author zijian Wang
 */
public interface AuthLinkCodeServiceI {

    /**
     * 检测链接码的有效性
     *
     * @param linkCode
     * @return
     */
    boolean checkLinkCodeValidity(String linkCode);
}
