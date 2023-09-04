package com.zlht.pbr.algorithm.wcmp.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author zi jian Wang
 */
@Configuration
@EnableAutoConfiguration
@MapperScan("com.zlht.pbr.algorithm.wcmp.dao.mapper")
public class DaoConfiguration {
}
