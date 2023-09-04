package com.zlht.pbr.algorithm.wcmp.remote.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zi jian Wang
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "remote.management")
public class ManagementConfiguration {

    private String ip;
    private Integer port;

}
