package com.zlht.pbr.algorithm.wcmp.remote.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 微信服务器配置
 * @author zi jian Wang
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "remote.wechat")
public class WcServerConfiguration {

    private String url;
    private String appid;
    private String secret;
}
