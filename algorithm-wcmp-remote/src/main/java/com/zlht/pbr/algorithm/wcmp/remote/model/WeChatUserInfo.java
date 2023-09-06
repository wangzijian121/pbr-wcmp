package com.zlht.pbr.algorithm.wcmp.remote.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 微信登录后返回用户信息
 *
 * @author zijian Wang
 */
@Data
public class WeChatUserInfo {
    private String openid;
    private String sessionKey;
    private String unionId;
    private int errCode;
    private String errMsg;
}